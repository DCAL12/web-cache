package com.github.dcal12.web_cache.cache.data;

import java.util.List;

/**
 * Created by Douglas Callaway on 8/27/16.
 */
@FunctionalInterface
public interface Chunkable {
    public List<byte[]> chunk(byte[] file);
}
