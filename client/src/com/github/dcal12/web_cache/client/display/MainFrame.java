package com.github.dcal12.web_cache.client.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Douglas Callaway on 8/12/16.
 */

public class MainFrame extends JFrame {

    private static JMenuBar menuBar = new JMenuBar();
    private static Menu cacheMenu = new Menu();

    public MainFrame(FileListPanel fileListPanel) {

        super("File Browser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add frame components
        menuBar.add(cacheMenu);
        add(Menu.CACHE_LABEL);
        setJMenuBar(menuBar);
        setContentPane(fileListPanel);

        pack();
        setLocationRelativeTo(null);
    }


    private static class Menu extends JMenu {

        final static JLabel CACHE_LABEL = new JLabel("Cache");
        static JMenuItem logViewerItem = new JMenuItem("view log");
        static LogViewer logViewer = new LogViewer();
        static JMenuItem clearMenuItem = new JMenuItem("clear cache");

        Menu() {
            super("Cache");
            add(logViewerItem);
            logViewerItem.addActionListener(actionEvent -> {
                logViewer.pack();
                logViewer.setVisible(true);
                logViewer.setLocationRelativeTo(null);
                logViewer.toFront();
            });
            add(clearMenuItem);
        }

        static class LogViewer extends JDialog {

            private static JTextArea logTextArea = new JTextArea(20, 50);
            private static JButton okayButton = new JButton("okay");

            LogViewer() {
                setModal(false);
                setResizable(true);
                logTextArea.setEditable(false);

                add(new JLabel("Cache Server Events"), BorderLayout.NORTH);
                add(new JScrollPane(logTextArea), BorderLayout.WEST);
                add(okayButton, BorderLayout.SOUTH);
                okayButton.addActionListener(actionEvent -> setVisible(false));
            }
        }
    }

    public void setLogText(List<String> logEntries) {
        Menu.LogViewer.logTextArea.setText("");
        logEntries.forEach(s -> Menu.LogViewer.logTextArea.append(s + '\n'));
    }

    public void addLogContentHandler(ActionListener listener) {
        // Pass log contents get function to menu item
        Menu.logViewerItem.addActionListener(listener);
    }

    public void addClearCacheHandler(ActionListener listener) {
        // Pass cache clear function to menu item
        Menu.clearMenuItem.addActionListener(listener);
    }
}
