package com.github.dcal12.web_cache.client.display;

import com.github.dcal12.web_cache.client.FileBrowserClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Douglas Callaway on 8/12/16.
 */

public class MainFrame extends JFrame {

    public static JFileChooser downloadDirectoryChooser;
    private static JMenuBar menuBar;
    private static Menu cacheMenu;

    static {
        downloadDirectoryChooser = new JFileChooser();
        downloadDirectoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        menuBar = new JMenuBar();
        cacheMenu = new Menu();
    }


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

    public void addChunkMethodMenuHandler(ActionListener listener) {
        // pass chunk method selector function to menu items
        for (JCheckBoxMenuItem item : Menu.ChunkMethodMenu.chunkMethodChoices){
            item.addActionListener(listener);
        }
    }

    private static class Menu extends JMenu {

        final static JLabel CACHE_LABEL = new JLabel("Cache");
        static JMenuItem logViewerItem = new JMenuItem("view log");
        static LogViewer logViewer = new LogViewer();
        static ChunkMethodMenu chunkMethodMenu = new ChunkMethodMenu();
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
            add(chunkMethodMenu);
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

        static class ChunkMethodMenu extends JMenu {
            final static String[] METHODS = new String[] {"Simple", "Rabin"};
            final static ButtonGroup chunkMethodChoicesGroup = new ButtonGroup();
            final static JCheckBoxMenuItem[] chunkMethodChoices = new JCheckBoxMenuItem[METHODS.length];

            ChunkMethodMenu() {
                super("chunk method");
                for (int i = 0; i < METHODS.length; i++) {
                    chunkMethodChoices[i] = new JCheckBoxMenuItem(METHODS[i]);
                    chunkMethodChoicesGroup.add(chunkMethodChoices[i]);
                    if (chunkMethodChoices[i].getActionCommand().equals(FileBrowserClient.chunkMethod)) {
                        chunkMethodChoices[i].setSelected(true);
                    }
                    add(chunkMethodChoices[i]);
                }
            }
        }
    }
}
