package com.github.dcal12.web_cache.cache.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Created by Douglas Callaway on 8/29/16.
 */
@XmlRootElement
public class DownloadRequest {

    private String fileName;
    private List<String> cachedBlocks;
    private String chunkMethod;

    public DownloadRequest() {}

    @XmlElement
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @XmlJavaTypeAdapter(StringListAdapter.class)
    @XmlElement
    public List<String> getCachedBlocks() {
        return cachedBlocks;
    }

    public void setCachedBlocks(List<String> cachedBlocks) {
        this.cachedBlocks = cachedBlocks;
    }

    @XmlElement
    public String getChunkMethod() {
        return chunkMethod;
    }

    public void setChunkMethod(String chunkMethod) {
        this.chunkMethod = chunkMethod;
    }
}
