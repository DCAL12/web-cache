package com.github.dcal12.web_cache.cache.data;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Douglas Callaway on 8/27/16.
 */
public class BlockElement {

    @XmlElement public String hash;
    @XmlElement public byte[] block;

    public BlockElement(String hash, byte[] block) {
        this.hash = hash;
        this.block = block;
    }

    @SuppressWarnings("unused")
	private BlockElement() {}
}
