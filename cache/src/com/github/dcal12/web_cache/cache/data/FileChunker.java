package com.github.dcal12.web_cache.cache.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Douglas Callaway on 8/27/16.
 */
@FunctionalInterface
public interface FileChunker {

    int DEFAULT_CHUNK_SIZE = 3;

    public List<byte[]> chunk(byte[] file);

    FileChunker simpleFileChunker = file -> {
        /*
          Adapted from the example by Mirko Seifert and rogerdpack at
          https://stackoverflow.com/questions/3405195/divide-array-into-smaller-parts
          Retrieved 23/08/2016
         */
        int chunkSize = FileChunker.DEFAULT_CHUNK_SIZE;
        List<byte[]> byteChunks = new ArrayList<>();

        for (int i = 0; i < file.length; i+=chunkSize) {
            int endOfChunk = Math.min(file.length, i + chunkSize);
            byte[] chunk = Arrays.copyOfRange(file, i, endOfChunk);
            byteChunks.add(chunk);
        }

        return byteChunks;
    };
}
