package com.github.dcal12.web_cache.client.display;

import com.github.dcal12.web_cache.client.data.SortedListModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Douglas Callaway on 8/8/16.
 * Adapted from 'Dual JList with buttons in between' example by java2s at
 * http://www.java2s.com/Code/Java/Swing-JFC/DualJListwithbuttonsinbetween.htm
 * Retrieved 08/08/2016
 */
public class FileListPanel extends JPanel {

    private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);
    private static final String SOURCE_LABEL = "Server Files";
    private static final String DOWNLOAD_BUTTON_LABEL = "Download";

    private JList serverFilesList;
    private SortedListModel serverFilesListModel;
    private JLabel serverLabel;
    private JButton downloadButton;
    private Object[] selectedItems;

    public FileListPanel() {
        setLayout(new GridBagLayout());
        serverLabel = new JLabel(SOURCE_LABEL);
        serverFilesListModel = new SortedListModel();
        serverFilesList = new JList(serverFilesListModel);
        add(serverLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                EMPTY_INSETS, 0, 0));
        add(new JScrollPane(serverFilesList), new GridBagConstraints(0, 1, 1, 5, .5, 1,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                EMPTY_INSETS, 0, 0));
        downloadButton = new JButton(DOWNLOAD_BUTTON_LABEL);
        add(downloadButton, new GridBagConstraints(1, 2, 1, 2, 0, 0.25,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                EMPTY_INSETS, 0, 0));
    }

    public void addServerFileElements(Object newValues[]) {
        fillListModel(serverFilesListModel, newValues);
    }

    private void fillListModel(SortedListModel model, Object newValues[]) {
        model.addAll(newValues);
    }

    public void addDownloadListener(ActionListener listener) {
        // pass selection listener to list panel
        downloadButton.addActionListener(listener);
    }

    public java.util.List getSelectedItems() {
        return serverFilesList.getSelectedValuesList();
    }
}
