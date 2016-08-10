package com.github.dcal12.web_cache.client;

import com.github.dcal12.web_cache.client.clientProxy.CacheServer;
import com.github.dcal12.web_cache.client.clientProxy.CacheServerAppService;
import com.github.dcal12.web_cache.client.display.FileListPanel;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Douglas Callaway on 8/7/16.
 */

public class FileBrowserClient {

    private static final String DOWNLOAD_LOCATION = "/home/user/Downloads/";
    private static JFrame mainFrame;
    private static FileListPanel fileListPanel;
    private static CacheServer clientProxy;

    static {
        // initialize GUI
        mainFrame = new JFrame("File Browser");
        fileListPanel = new FileListPanel();

        mainFrame.setContentPane(fileListPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);

        clientProxy = new CacheServerAppService().getCacheServerAppPort();
    }

    public static void main(String[] args) throws IOException {

        SwingUtilities.invokeLater(() -> {

            fileListPanel.addServerFileElements(clientProxy.listServerFiles().toArray());

            // GUI event handlers
            fileListPanel.addDownloadListener(actionEvent -> {

                List selectedItems = fileListPanel.getSelectedItems();

                selectedItems.forEach(item -> {

                    String fileName = String.valueOf(item);
                    Path path = FileSystems.getDefault().getPath(DOWNLOAD_LOCATION, fileName);

                    List<String> download = Arrays.asList(clientProxy.downloadFile(fileName));

                    try {
                        Files.write(path, download);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("downloaded '" + fileName + "'");
                });
            });

            // display GUI
            mainFrame.setVisible(true);
        });
    }
}
