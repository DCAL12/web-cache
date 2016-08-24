package com.github.dcal12.web_cache.server;

import com.github.dcal12.web_cache.server.utility.FileBrowser;

import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import javax.xml.ws.soap.MTOM;

/**
 * Created by user on 8/7/16.
 */

@MTOM
@WebService(endpointInterface = "com.github.dcal12.web_cache.server.FileServer")
public class FileServerApp implements FileServer {

    private static final String STORAGE = "/home/user/classes/COMPSCI-711/assignments/web-cache/Part2/server/files/";
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
    public DataHandler downloadFile(String fileName) {
        return fileBrowser.downloadFile(STORAGE, fileName);
    }
}
