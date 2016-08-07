package com.github.dcal12.web_cache.server;

import com.github.dcal12.web_cache.server.utility.FileBrowser;

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;

/**
 * Created by user on 8/7/16.
 */

@MTOM
@WebService(endpointInterface = "com.github.dcal12.web_cache.server.FileServer")
public class FileServerApp implements FileServer {

    private final String storage = "/home/user/classes/COMPSCI-711/assignments/web-cache/server/files/";
    private static FileBrowser fileBrowser = FileBrowser.getInstance();

    public FileServerApp() {
    }

    @WebMethod
    public String greet(String name) {
        return "hello " + name;
    }

    @Override
    public String[] listFiles() {
        return fileBrowser.listFiles(storage);
    }

    @Override
    public DataHandler downloadFile(String fileName) {
        return fileBrowser.downloadFile(storage, fileName);
    }
}
