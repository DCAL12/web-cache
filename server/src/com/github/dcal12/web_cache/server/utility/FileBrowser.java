package com.github.dcal12.web_cache.server.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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

    public List<String> downloadFile(String filePath, String fileName) throws IOException {
        /**
         * Read the entire file as a list of lines (one line per entry)
         * Appropriate for small-medium files using the readAllLines
         * convenience method as documented at
         * https://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html#readAllLines%28java.nio.file.Path,%20java.nio.charset.Charset%29
         */

        Path path = FileSystems.getDefault().getPath(filePath, fileName);
        return Files.readAllLines(path);
    }
}