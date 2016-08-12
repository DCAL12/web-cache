package com.github.dcal12.web_cache.client;

import com.github.dcal12.web_cache.client.clientProxy.CacheServer;
import com.github.dcal12.web_cache.client.clientProxy.CacheServerAppService;
import com.github.dcal12.web_cache.client.display.FileListPanel;
import com.github.dcal12.web_cache.client.display.MainFrame;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by Douglas Callaway on 8/7/16.
 */

public class FileBrowserClient {

    private static final String DOWNLOAD_LOCATION = "/home/user/Downloads/";
    private static MainFrame mainFrame;
    private static FileListPanel fileListPanel;
    private static CacheServer clientProxy;

    static {
        // initialize GUI
        fileListPanel = new FileListPanel();
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
            fileListPanel.addClientFileElements(listClientFiles(DOWNLOAD_LOCATION).toArray(new String[0]));

            // GUI event handlers
            fileListPanel.addDownloadListener(actionEvent -> {

                List selectedItems = fileListPanel.getSelectedItems();

                selectedItems.forEach(item -> {

                    String fileName = String.valueOf(item);
                    Path path = FileSystems.getDefault().getPath(DOWNLOAD_LOCATION, fileName);

                    List<String> download = clientProxy.downloadFile(fileName);

                    try {
                        Files.write(path, download);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("downloaded '" + fileName + "' at " + new Date());
                    mainFrame.setLogText(clientProxy.getLog());

                    fileListPanel.addClientFileElements(listClientFiles(DOWNLOAD_LOCATION).toArray(new String[0]));
                });
            });

            mainFrame.addLogContentHandler(actionEvent -> mainFrame.setLogText(clientProxy.getLog()));

            mainFrame.addClearCacheHandler(actionEvent -> {
                clientProxy.clearCache();
                mainFrame.setLogText(clientProxy.getLog());
            });

            // display GUI
            mainFrame.setVisible(true);
        });
    }
}
