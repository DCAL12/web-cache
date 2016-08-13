package com.github.dcal12.web_cache.cache;

import com.github.dcal12.web_cache.cache.clientProxy.FileServer;
import com.github.dcal12.web_cache.cache.clientProxy.FileServerAppService;
import com.github.dcal12.web_cache.cache.data.LogEntry;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Douglas Callaway on 8/7/16.
 */

@WebService(endpointInterface = "com.github.dcal12.web_cache.cache.CacheServer")
public class CacheServerApp implements CacheServer {

    private static FileServer clientProxy;
    private static Hashtable<String, String[]> cachedFiles;
    private static List<LogEntry> log;

    static {
        clientProxy = new FileServerAppService().getFileServerAppPort();
        cachedFiles = new Hashtable<>();
        log = new ArrayList<>();
    }

    public CacheServerApp() {

    }

    // Start the web server
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9090/cache", new CacheServerApp());
    }

    @Override
    public String[] listCachedFiles() {

        List<String> fileNames = new ArrayList<>(cachedFiles.keySet());
        Collections.sort(fileNames); // sort a-z

        return fileNames.toArray(new String[0]);
    }

    @Override
    public String[] listServerFiles() {
        return clientProxy.listFiles();
    }

    @Override
    public String[] downloadFile(String fileName) {

        LogEntry request = new LogEntry(fileName);
        log.add(request);
        System.out.println(request);

        Boolean isCached = cachedFiles.containsKey(fileName);
        LogEntry response = new LogEntry(fileName, isCached);
        log.add(response);
        System.out.println(response);

        if (isCached) {
            return cachedFiles.get(fileName);
        }

        String[] downloadFromServer = clientProxy.downloadFile(fileName);
        cachedFiles.put(fileName, downloadFromServer);
        return downloadFromServer;
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
        cachedFiles.clear();
        LogEntry clear = new LogEntry();
        log.add(clear);
        System.out.println(clear);
    }
}
