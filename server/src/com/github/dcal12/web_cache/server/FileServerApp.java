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
	
	private static final String STORAGE = "files/";
	private static FileBrowser fileBrowser = FileBrowser.getInstance();
	private static String host = "http://localhost:8081/app";

    public FileServerApp() {
    }

    // Start the web server
    public static void main(String[] args) {
    	Endpoint.publish(host, new FileServerApp());
        System.out.println("File Server running at " + host);
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
