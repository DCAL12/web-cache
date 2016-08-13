package com.github.dcal12.web_cache.client.display;

import com.github.dcal12.web_cache.client.data.SortedListModel;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

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
    private JLabel selectedDirectoryLabel;
    private JButton selectDirectoryButton;
    private JButton downloadButton;
    private JTextArea filePreview;

    public FileListPanel(String selectedDirectory) {
        setLayout(new GridBagLayout());

        JLabel serverLabel = new JLabel("Server Files");
        serverFilesListModel = new SortedListModel();
        serverFilesList = new JList(serverFilesListModel);

        JLabel downloadDirectoryLabel = new JLabel("Download to:");
        selectedDirectoryLabel = new JLabel(selectedDirectory);
        selectedDirectoryLabel.setFont(new Font("Courier", Font.PLAIN, 10));

        JLabel clientLabel = new JLabel("Client Files");
        clientFilesListModel = new SortedListModel();
        clientFilesList = new JList(clientFilesListModel);

        JLabel previewLabel = new JLabel("Preview");
        filePreview = new JTextArea();

        add(serverLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                EMPTY_INSETS, 0, 0));
        add(new JScrollPane(serverFilesList), new GridBagConstraints(0, 1, 1, 8, 0.5, 1,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                EMPTY_INSETS, 0, 0));

        add(downloadDirectoryLabel, new GridBagConstraints(1, 2, 1, 1, 0, 0.25,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                EMPTY_INSETS, 0, 0));
        add(selectedDirectoryLabel, new GridBagConstraints(1, 3, 1, 1, 0, 0.25,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                new Insets(0, 5, 0, 5), 0, 0));

        selectDirectoryButton = new JButton("Select...");
        add(selectDirectoryButton, new GridBagConstraints(1, 5, 1, 2, 0, 0.25,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                EMPTY_INSETS, 0, 0));

        downloadButton = new JButton("Download >>");
        add(downloadButton, new GridBagConstraints(1, 7, 1, 2, 0, 0.25,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                EMPTY_INSETS, 0, 0));

        add(clientLabel, new GridBagConstraints(2, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                EMPTY_INSETS, 0, 0));
        add(new JScrollPane(clientFilesList), new GridBagConstraints(2, 1, 1, 8, 0.5, 1,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                EMPTY_INSETS, 0, 0));

        add(previewLabel, new GridBagConstraints(3, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                EMPTY_INSETS, 0, 0));
        add(new JScrollPane(filePreview), new GridBagConstraints(3, 1, 1, 8, 0.5, 1,
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
        model.clear();
        model.addAll(newValues);
    }

    public void setSelectedDirectoryLabel(String selectedDirectory) {
        selectedDirectoryLabel.setText(selectedDirectory);
    }

    public void addSelectDirectoryButtonListener(ActionListener listener) {
        // pass directory select button listener
        selectDirectoryButton.addActionListener(listener);
    }
    public void addDownloadListener(ActionListener listener) {
        // pass download button listener
        downloadButton.addActionListener(listener);
    }

    public void addClientFileSelectionListener(ListSelectionListener listSelectionListener) {
        // pass selection listener
        clientFilesList.addListSelectionListener(listSelectionListener);
    }

    public void setPreviewText(List<String> lines) {
        filePreview.setText("");
        lines.forEach(s -> filePreview.append(s + '\n'));
    }

    public java.util.List getSelectedServerItems() {
        return serverFilesList.getSelectedValuesList();
    }

    public String getSelectedClientItem() {
        return (String) clientFilesList.getSelectedValue();
    }
}
