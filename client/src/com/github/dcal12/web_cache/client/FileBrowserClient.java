package com.github.dcal12.web_cache.client;

import com.github.dcal12.web_cache.client.display.FileListPanel;
import com.github.dcal12.web_cache.client.proxy.FileServer;
import com.github.dcal12.web_cache.client.proxy.FileServerAppService;

import javax.activation.DataHandler;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


/**
 * Created by Douglas Callaway on 8/7/16.
 */

public class FileBrowserClient {

    private static final String DOWNLOAD_LOCATION = "/home/user/Downloads/";
    private static JFrame mainFrame;
    private static FileListPanel fileListPanel;
    private static FileServer proxy;

    static {
        // initialize GUI
        mainFrame = new JFrame("File Browser");
        fileListPanel = new FileListPanel();

        mainFrame.setContentPane(fileListPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);

        proxy = new FileServerAppService().getFileServerAppPort();
    }

    public static void main(String[] args) throws IOException {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                fileListPanel.addServerFileElements(proxy.listFiles().toArray());

                // GUI event handlers
                fileListPanel.addDownloadListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        List selectedItems = fileListPanel.getSelectedItems();

                        selectedItems.forEach(item -> {

                            String fileName = String.valueOf(item);
                            FileOutputStream outputStream = null;

                            DataHandler dataHandler = proxy.downloadFile(fileName);

                            // check for a proper download path
                            try {
                                outputStream = new FileOutputStream(DOWNLOAD_LOCATION + fileName);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                            // save the downloaded file
                            try {
                                dataHandler.writeTo(outputStream);
                                outputStream.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            System.out.println("downloaded '" + fileName + "'");
                        });
                    }
                });

                // display GUI
                mainFrame.setVisible(true);
            }
        });
    }
}
