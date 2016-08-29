package com.github.dcal12.web_cache.cache.data;

import java.util.Date;

/**
 * Created by Douglas Callaway on 8/10/16.
 */

public class LogEntry {

    private Type type;
    private String fileName;
    private Date timestamp;
    private double cachedPercent;

    public LogEntry(String fileName) {
        this.type = Type.REQUEST;
        this.fileName = fileName;
        this.timestamp = new Date();
    }

    public LogEntry(String fileName, int cachedBytes, int fileSizeBytes) {
        this.type = Type.RESPONSE;
        this.fileName = fileName;
        this.timestamp = new Date();
        this.cachedPercent = (double) cachedBytes / fileSizeBytes * 100;
    }

    @Override
    public String toString() {

        switch (type) {

            case REQUEST:
                return "user request: " +
                        "file '" + fileName +
                        "' at " + timestamp.toString();

            case RESPONSE:
                return "response: " + String.format("%1$.1f", cachedPercent) + "% of '" +
                        fileName + "' was constructed with the cached data. " +
                        "Fragments sent to client at " + timestamp.toString();
        }
        return null;
    }

    private enum Type {
        REQUEST, RESPONSE
    }
}
