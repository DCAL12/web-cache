package com.github.dcal12.web_cache.cache;

import com.github.dcal12.web_cache.cache.clientProxy.FileServer;
import com.github.dcal12.web_cache.cache.clientProxy.FileServerAppService;
import com.github.dcal12.web_cache.cache.data.*;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import javax.xml.ws.soap.MTOM;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Douglas Callaway on 8/7/16.
 */

@MTOM
@WebService(endpointInterface = "com.github.dcal12.web_cache.cache.CacheServer")
public class CacheServerApp implements CacheServer {

    private static FileServer clientProxy;
    private static List<LogEntry> log;

    static {
        clientProxy = new FileServerAppService().getFileServerAppPort();
        log = new ArrayList<>();
    }

    public CacheServerApp() {

    }

    // Start the web server
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9090/cache", new CacheServerApp());
    }

    @Override
    public String[] listServerFiles() {
        return clientProxy.listFiles();
    }

    @Override
    public DownloadResponse downloadFile(DownloadRequest downloadRequest) {

        // log request
        LogEntry request = new LogEntry(downloadRequest.getFileName());
        log.add(request);
        System.out.println(request);

        // download from server & divide file into blocks
        List<String> blockOrder = new ArrayList<>();
        Hashtable<String, byte[]> blocks = new Hashtable<>();

        FileChunker.simpleFileChunker
                .chunk(clientProxy.downloadFile(downloadRequest.getFileName()))
                .forEach(chunk -> {
                    String hash = MD5Hash.md5Hash.hash(chunk);
                    blockOrder.add(hash);

                    if (!downloadRequest.getCachedBlocks().contains(hash)) { blocks.put(hash, chunk); }
                });

        // package and send to client
        DownloadResponse downloadResponse = new DownloadResponse();
        downloadResponse.setBlockOrder(blockOrder);
        downloadResponse.setBlocks(blocks);
        return downloadResponse;
    }

    @Override
    public String[] getLog() {
        List<String> logEntries = log.stream()
                .map(LogEntry::toString)
                .collect(Collectors.toList());
        return logEntries.toArray(new String[0]);
    }

    @Override
    public void clearCache() {
        LogEntry clear = new LogEntry();
        log.add(clear);
        System.out.println(clear);
    }
}
