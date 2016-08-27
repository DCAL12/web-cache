package com.github.dcal12.web_cache.client;

import com.github.dcal12.web_cache.client.clientProxy.CacheServer;
import com.github.dcal12.web_cache.client.clientProxy.CacheServerAppService;
import com.github.dcal12.web_cache.client.clientProxy.DownloadResponse;
import com.github.dcal12.web_cache.client.display.FileListPanel;
import com.github.dcal12.web_cache.client.display.MainFrame;
import com.sun.istack.ByteArrayDataSource;

import javax.activation.DataHandler;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by Douglas Callaway on 8/7/16.
 */

public class FileBrowserClient {

    private static String downloadLocation = "/home/user/Downloads/";
    private static HashMap<String, byte[]> cachedBlocks = null;
    private static MainFrame mainFrame;
    private static FileListPanel fileListPanel;
    private static CacheServer clientProxy;

    static {
        cachedBlocks = new HashMap<>();

        // initialize GUI
        fileListPanel = new FileListPanel("/home/user/Downloads/");
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

    public static void main(String[] args) throws IOException {

        SwingUtilities.invokeLater(() -> {

            fileListPanel.addServerFileElements(clientProxy.listServerFiles().toArray());
            fileListPanel.addClientFileElements(listClientFiles("/home/user/Downloads/").toArray(new String[0]));

            // GUI EVENT HANDLERS

            // change download directory
            fileListPanel.addSelectDirectoryButtonListener(actionEvent -> {

                // show folder chooser dialog
                int userChoice = MainFrame.downloadDirectoryChooser.showOpenDialog(mainFrame);

                if (userChoice == JFileChooser.APPROVE_OPTION) {
                    downloadLocation = MainFrame.downloadDirectoryChooser.getSelectedFile().getAbsolutePath();
                    fileListPanel.setSelectedDirectoryLabel("/home/user/Downloads/");
                    fileListPanel.addClientFileElements(listClientFiles("/home/user/Downloads/").toArray(new String[0]));
                }
            });

            // download selected files
            fileListPanel.addDownloadListener(actionEvent -> {

                List selectedItems = fileListPanel.getSelectedServerItems();

                selectedItems.forEach(item -> {

                    /**
                     * Adapted from the example by IBM developerWorks at
                     * https://www.ibm.com/developerworks/library/ws-devaxis2part2/
                     * Retrieved 07/08/2016
                     */

                    String fileName = String.valueOf(item);
                    DownloadResponse download = clientProxy.downloadFile(fileName);

                    System.out.println("hashes: ");
                    download.getBlockOrder().forEach(System.out::println);

                    System.out.println("new blocks: ");
                    download.getBlocks().getItem().forEach(blockElement -> {
                        System.out.println(blockElement.getHash() + ":");
                        for (byte b : blockElement.getBlock()){
                            System.out.println(b);
                        }

                        cachedBlocks.put(blockElement.getHash(), blockElement.getBlock());
                    });

                    // reconstruct file
                    ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
                    System.out.println("rebuilding...");
                    download.getBlockOrder().forEach(hash -> {
                        System.out.println(cachedBlocks.get(hash));
                        try {
                            byteOutputStream.write(cachedBlocks.get(hash));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    DataHandler dataHandler = new DataHandler(
                            new ByteArrayDataSource(byteOutputStream.toByteArray(), "application/octet-stream"));

                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream("/home/user/Downloads/" + fileName);
                        dataHandler.writeTo(fileOutputStream);
                        fileOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("downloaded '" + fileName + "' at " + new Date());
                    mainFrame.setLogText(clientProxy.getLog());

                    fileListPanel.addClientFileElements(listClientFiles("/home/user/Downloads/").toArray(new String[0]));
                });
            });

            // preview a downloaded file
            fileListPanel.addClientFileSelectionListener(listSelectionEvent -> {

                if (listSelectionEvent.getValueIsAdjusting()) {

                    List<String> lines = new ArrayList<>();

                    try {
                        BufferedReader reader = new BufferedReader(
                                new FileReader("/home/user/Downloads/" + fileListPanel.getSelectedClientItem()));


                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            lines.add(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    fileListPanel.setPreviewText(lines);
                }
            });

            // view cache server log
            mainFrame.addLogContentHandler(actionEvent -> mainFrame.setLogText(clientProxy.getLog()));

            // clear cache contents
            mainFrame.addClearCacheHandler(actionEvent -> {
                clientProxy.clearCache();
                mainFrame.setLogText(clientProxy.getLog());
            });

            // display GUI
            mainFrame.setVisible(true);
        });
    }
}
