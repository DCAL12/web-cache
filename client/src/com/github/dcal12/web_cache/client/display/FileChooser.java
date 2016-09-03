package com.github.dcal12.web_cache.client.display;

import javax.swing.*;

/**
 * Created by Douglas Callaway on 8/8/16.
 */
public class FileChooser {
    @SuppressWarnings("rawtypes")
	public JList serverFiles;
    public JPanel fileListView;

    @SuppressWarnings({ "unused", "rawtypes" })
	private void createUIComponents() {
        serverFiles = new JList();
    }

}
