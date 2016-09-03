package com.github.dcal12.web_cache.cache.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Douglas Callaway on 8/27/16.
 */
@XmlRootElement
public class DownloadResponse {

    private List<String> blockOrder = null;

    private Hashtable<String, byte[]> blocks = null;

    public DownloadResponse() {}

    @XmlElement
    public List<String> getBlockOrder() {
        return blockOrder;
    }

    public void setBlockOrder(List<String> blockOrder) {
        this.blockOrder = blockOrder;
    }

    @XmlJavaTypeAdapter(HashtableAdapter.class)
    public Hashtable<String, byte[]> getBlocks() {
        return blocks;
    }

    public void setBlocks(Hashtable<String, byte[]> blocks) {
        this.blocks = blocks;
    }
}
