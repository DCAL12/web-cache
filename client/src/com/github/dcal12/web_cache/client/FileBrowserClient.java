package com.github.dcal12.web_cache.client;

import com.github.dcal12.web_cache.client.clientProxy.*;
import com.github.dcal12.web_cache.client.display.FileListPanel;
import com.github.dcal12.web_cache.client.display.MainFrame;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by Douglas Callaway on 8/7/16.
 */

public class FileBrowserClient {

    private static String downloadLocation = new File("").getAbsolutePath() + '/';
    private static HashMap<String, byte[]> cachedBlocks = null;
    private static MainFrame mainFrame;
    private static FileListPanel fileListPanel;
    private static CacheServer clientProxy;
    public static String chunkMethod = "Rabin";

    static {
        cachedBlocks = new HashMap<>();

        // initialize GUI
        fileListPanel = new FileListPanel(downloadLocation);
        mainFrame = new MainFrame(fileListPanel);

        // connect to cache server
        clientProxy = new CacheServerAppService().getCacheServerAppPort();
    }

    private static List<String> listClientFiles(String filePath) {
        List<File> files = Arrays.asList(new File(filePath).listFiles());

        return files.stream()
                .map(File::getName)
                .filter(s -> clientProxy.listServerFiles().contains(s))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {

        SwingUtilities.invokeLater(() -> {

            fileListPanel.addServerFileElements(clientProxy.listServerFiles().toArray());
            fileListPanel.addClientFileElements(listClientFiles(downloadLocation).toArray(new String[0]));

            // GUI EVENT HANDLERS

            // change download directory
            fileListPanel.addSelectDirectoryButtonListener(actionEvent -> {

                // show folder chooser dialog
                int userChoice = MainFrame.downloadDirectoryChooser.showOpenDialog(mainFrame);

                if (userChoice == JFileChooser.APPROVE_OPTION) {
                    downloadLocation = MainFrame.downloadDirectoryChooser.getSelectedFile().getAbsolutePath() + '/';
                    fileListPanel.setSelectedDirectoryLabel(downloadLocation);
                    fileListPanel.addClientFileElements(listClientFiles(downloadLocation).toArray(new String[0]));
                }
            });

            // download selected files
            fileListPanel.addDownloadListener(actionEvent -> {

                List<String> selectedItems = fileListPanel.getSelectedServerItems();

                selectedItems.forEach(item -> {
                    /*
                      Adapted from the example by IBM developerWorks at
                      https://www.ibm.com/developerworks/library/ws-devaxis2part2/
                      Retrieved 07/08/2016
                     */

                    long startTime = System.currentTimeMillis();

                    // package download request to cache server
                    DownloadRequest downloadRequest = new DownloadRequest();
                    downloadRequest.setFileName(String.valueOf(item));
                    StringArray cachedBlockHashes = new StringArray();
                    cachedBlockHashes.getItem().addAll(cachedBlocks.keySet());
                    downloadRequest.setCachedBlocks(cachedBlockHashes);
                    downloadRequest.setChunkMethod(chunkMethod);

                    DownloadResponse download = clientProxy.downloadFile(downloadRequest);

                    long completeTime = System.currentTimeMillis();
                    System.out.println("download completed from cache in " + (completeTime - startTime) + " ms");

                    startTime = System.currentTimeMillis();
                    download.getBlocks().getItem().forEach(blockElement ->
                            cachedBlocks.put(blockElement.getHash(), blockElement.getBlock()));

                    // reassemble byte chunks into single byte array
                    ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
                    download.getBlockOrder().forEach(hash -> {
                        try {
                            byteOutputStream.write(cachedBlocks.get(hash));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            
                    // write bytes to file
                    //DataHandler dataHandler = new DataHandler(byteArrayDataSource);
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(downloadLocation + downloadRequest.getFileName());
                        byteOutputStream.writeTo(fileOutputStream);
                        fileOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    completeTime = System.currentTimeMillis();
                    System.out.println("client processed file in " + (completeTime - startTime) + " ms");

                    System.out.println("downloaded '" + downloadRequest.getFileName() + "' at " + new Date());
                    mainFrame.setLogText(clientProxy.getLog());

                    fileListPanel.addClientFileElements(listClientFiles(downloadLocation).toArray(new String[0]));
                });
            });

            // preview a downloaded file
            fileListPanel.addClientFileSelectionListener(listSelectionEvent -> {

                if (listSelectionEvent.getValueIsAdjusting()) {

                    List<String> lines = new ArrayList<>();

                    try {
						BufferedReader reader = new BufferedReader(
                                new FileReader(downloadLocation + '/' + fileListPanel.getSelectedClientItem()));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            lines.add(line);
                        }
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    fileListPanel.setPreviewText(lines);
                }
            });

            // view cache server log
            mainFrame.addLogContentHandler(actionEvent -> mainFrame.setLogText(clientProxy.getLog()));

            // view cache contents
            mainFrame.addCacheContentHandler(actionEvent -> mainFrame.setCacheText(cachedBlocks));

            // clear cache contents
            mainFrame.addClearCacheHandler(actionEvent -> {
                cachedBlocks.clear();
                System.out.println("cleared client cache at " + new Date().toString());
            });

            // change chunk method
            mainFrame.addChunkMethodMenuHandler(actionEvent ->
                    chunkMethod = actionEvent.getActionCommand().toUpperCase());

            // display GUI
            mainFrame.setVisible(true);
        });
    }
}
