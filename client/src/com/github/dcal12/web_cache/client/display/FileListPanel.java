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

    private JList serverFilesList;
    private SortedListModel serverFilesListModel;
    private JList clientFilesList;
    private SortedListModel clientFilesListModel;
    private JButton downloadButton;

    public FileListPanel() {
        setLayout(new GridBagLayout());

        JLabel serverLabel = new JLabel("Server Files");
        serverFilesListModel = new SortedListModel();
        serverFilesList = new JList(serverFilesListModel);

        JLabel clientLabel = new JLabel("Client Files");
        clientFilesListModel = new SortedListModel();
        clientFilesList = new JList(clientFilesListModel);

        add(serverLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                EMPTY_INSETS, 0, 0));
        add(new JScrollPane(serverFilesList), new GridBagConstraints(0, 1, 1, 5, .5, 1,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                EMPTY_INSETS, 0, 0));

        downloadButton = new JButton("Download >>");
        add(downloadButton, new GridBagConstraints(1, 2, 1, 2, 0, 0.25,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                EMPTY_INSETS, 0, 0));

        add(clientLabel, new GridBagConstraints(2, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                EMPTY_INSETS, 0, 0));
        add(new JScrollPane(clientFilesList), new GridBagConstraints(2, 1, 1, 5, 0.5, 1,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                EMPTY_INSETS, 0, 0));
    }

    public void addServerFileElements(Object newValues[]) {
        fillListModel(serverFilesListModel, newValues);
    }

    public void addClientFileElements(Object newValues[]) {
        fillListModel(clientFilesListModel, newValues);
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
