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

    private static FileServer clientProxy = new FileServerAppService().getFileServerAppPort();
    private static Hashtable<String, String[]> cachedFiles = new Hashtable<>();
    private static List<LogEntry> log = new ArrayList<>();
    private static String host = "http://localhost:9090/cache";

    public CacheServerApp() {

    }

    // Start the web server
    public static void main(String[] args) {
        Endpoint.publish(host, new CacheServerApp());
        System.out.println("Cache Server running at " + host);
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

        long startTime = System.currentTimeMillis();
        Boolean isCached = cachedFiles.containsKey(fileName);
        LogEntry response = new LogEntry(fileName, isCached);
        log.add(response);
        System.out.println(response);

        if (isCached) {
            long completeTime = System.currentTimeMillis();
            System.out.println("cache processed file in " + (completeTime - startTime) + " ms");
            return cachedFiles.get(fileName);
        }
        long completeTime = System.currentTimeMillis();
        System.out.println("cache processed file in " + (completeTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        String[] downloadFromServer = clientProxy.downloadFile(fileName);
        completeTime = System.currentTimeMillis();
        System.out.println("download completed from origin server in " + (completeTime - startTime) + " ms");

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
