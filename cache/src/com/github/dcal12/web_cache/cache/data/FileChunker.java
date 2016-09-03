package com.github.dcal12.web_cache.cache.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Douglas Callaway on 8/31/16.
 */
public enum FileChunker {
    SIMPLE(file -> {
        /*
          Adapted from the example by Mirko Seifert and rogerdpack at
          https://stackoverflow.com/questions/3405195/divide-array-into-smaller-parts
          Retrieved 23/08/2016
         */

        List<byte[]> byteChunks = new ArrayList<>();


        // return at least 1 block
        if(file.length <= Chunkable.chunkSize + 1) {
            byteChunks.add(file);
            return byteChunks;
        }

        for (int i = 0; i < file.length; i+= Chunkable.chunkSize) {
            int endOfChunk = Math.min(file.length, i + Chunkable.chunkSize);
            byte[] chunk = Arrays.copyOfRange(file, i, endOfChunk);
            byteChunks.add(chunk);
        }
        return byteChunks;
    }),

    RABIN(file -> {

        /*
          Implementation of Rabin-function-based block boundary algorithm described by Rhea, S. C., et al. (2003).
          Value-based web caching. Proceedings of the 12th international conference on World Wide Web, ACM.
         */

        List<byte[]> byteChunks = new ArrayList<>();
        int windowSize = 3;
        int prime = 541;

        // return at least 1 block
        if(file.length <= Chunkable.chunkSize + 1) {
            byteChunks.add(file);
            return byteChunks;
        }

        // first window's Rabin function
        double result = 0;
        for (int i = 0; i < windowSize; i++) { result += file[i] * Math.pow(prime, windowSize - i - 1); }

        // subsequent window Rabin functions
        int startOfChunk = 0;
        int endOfWindow = windowSize;
        for (int i = 1; i <= file.length - windowSize; i++) {

            result = (result - file[i - 1] * Math.pow(prime, windowSize - 1)) * prime + file[endOfWindow];

            if (result % Chunkable.chunkSize == 0 || endOfWindow == file.length - 1) {

                int endOfChunk = Math.min(file.length, endOfWindow);
                byte[] chunk = Arrays.copyOfRange(file, startOfChunk, endOfChunk + 1);
                byteChunks.add(chunk);

                startOfChunk = endOfChunk + 1;
            }
            endOfWindow++;
        }
        return byteChunks;
    });

    public Chunkable chunker;

    FileChunker(Chunkable chunker){ this.chunker = chunker; }
}