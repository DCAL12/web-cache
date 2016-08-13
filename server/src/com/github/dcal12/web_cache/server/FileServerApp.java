package com.github.dcal12.web_cache.server;

import com.github.dcal12.web_cache.server.utility.FileBrowser;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.io.IOException;

/**
 * Created by user on 8/7/16.
 */

@WebService(endpointInterface = "com.github.dcal12.web_cache.server.FileServer")
public class FileServerApp implements FileServer {

    private static final String STORAGE = "/home/user/classes/COMPSCI-711/assignments/web-cache/server/files/";
    private static FileBrowser fileBrowser;

    static {
        fileBrowser = FileBrowser.getInstance();
    }

    public FileServerApp() {
    }

    // Start the web server
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/app", new FileServerApp());
    }

    @Override
    public String[] listFiles() {
        return fileBrowser.listFiles(STORAGE);
    }

    @Override
    public String[] downloadFile(String fileName) {
        try {
            return fileBrowser.downloadFile(STORAGE, fileName).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
