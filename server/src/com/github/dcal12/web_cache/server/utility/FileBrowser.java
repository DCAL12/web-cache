package com.github.dcal12.web_cache.server.utility;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import java.io.File;

/**
 * Created by Douglas Callaway on 8/7/16.
 */

public class FileBrowser {
    private static FileBrowser ourInstance = new FileBrowser();

    private FileBrowser() {
    }

    public static FileBrowser getInstance() {
        return ourInstance;
    }

    public String[] listFiles(String path) {
        File folder = new File(path);
        return folder.list();
    }

    public DataHandler downloadFile(String filePath, String fileName) {
        /**
         * Adapted from the example by IBM developerWorks at
         * https://www.ibm.com/developerworks/library/ws-devaxis2part2/
         * Retrieved 07/08/2016
         */

        FileDataSource dataSource = new FileDataSource(filePath + fileName);
        return new DataHandler(dataSource);
    }
}