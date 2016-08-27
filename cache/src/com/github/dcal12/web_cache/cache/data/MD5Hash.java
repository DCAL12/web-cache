package com.github.dcal12.web_cache.cache.data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Douglas Callaway on 8/24/16.
 */
public class MD5Hash {

    public static Hasher md5Hash = file -> {
        /*
          Implementation of the MD5 hashing function as shown at
          http://www.avajava.com/tutorials/lessons/how-do-i-generate-an-md5-digest-for-a-string.html
          Retrieved 24/08/2016
         */
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(file);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return messageDigest != null ? toHex(messageDigest.digest()) : "";
    };

    private static String toHex(byte[] digest) {
        StringBuilder hex = new StringBuilder();
        for (byte b : digest) {
            hex.append(String.format("%02X", b));
        }
        return hex.toString();
    }
}
