package com.github.dcal12.web_cache.cache.data;

/**
 * Created by Douglas Callaway on 8/27/16.
 */
@FunctionalInterface
public interface Hashable {
    public String hash(byte[] file);
}