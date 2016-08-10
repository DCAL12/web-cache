package com.github.dcal12.web_cache.cache.data;

import java.util.Date;

/**
 * Created by Douglas Callaway on 8/10/16.
 */

public class LogEntry {

    private Type type;
    private String fileName;
    private Date timestamp;
    private Boolean isCached;

    public LogEntry(String fileName) {
        this.type = Type.REQUEST;
        this.fileName = fileName;
        this.timestamp = new Date();
    }

    public LogEntry(String fileName, Boolean isCached) {
        this.type = Type.RESPONSE;
        this.fileName = fileName;
        this.isCached = isCached;
        this.timestamp = new Date();
    }

    @Override
    public String toString() {

        switch (type) {

            case REQUEST:
                return "user request: " +
                        "file " + fileName +
                        " at " + timestamp.toString();

            case RESPONSE:
                return isCached ? ("response: cached file" + fileName) :
                        ("response: file " + fileName + " downloaded from server");
        }
        return null;
    }

    private enum Type {
        REQUEST, RESPONSE
    }
}
