
import com.sun.xml.internal.ws.util.ByteArrayDataSource;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.swing.event.ListSelectionListener;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class Main {

    private static Hashtable<String, byte[]> cachedFiles;
    private static List<String> hashOrder;

    static {
        cachedFiles = new Hashtable<>();
        hashOrder = new ArrayList<>();
    }

    /**
     * Adapted from the example by Mirko Seifert and rogerdpack at
     * https://stackoverflow.com/questions/3405195/divide-array-into-smaller-parts
     * Retrieved 23/08/2016
     */
    public static List<byte[]> chunkBytes(byte[] bytes, int chunkSize) {

        List<byte[]> byteChunks = new ArrayList<>();

        for (int i = 0; i < bytes.length; i+=chunkSize) {
            int endOfChunk = Math.min(bytes.length, i + chunkSize);
            byteChunks.add(Arrays.copyOfRange(bytes, i, endOfChunk));
        }

        return byteChunks;
    }

    public static String hash(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(bytes);
        bytes = messageDigest.digest();

        // convert to hex
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02X", b));
        }

        return hex.toString();
    }

    public static void main(String[] args) throws IOException {
        FileDataSource dataSource = new FileDataSource("/home/user/classes/COMPSCI-711/assignments/web-cache/server/files/Assignment1.pdf");
        DataHandler handler = new DataHandler(dataSource);

        // break received data into byte chunks
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        handler.writeTo(outputStream);

        List<byte[]> result = chunkBytes(outputStream.toByteArray(), 2048);
        result.forEach(bytes -> {
            try {
                // add chunks to hash table
                String hash = hash(bytes);
                cachedFiles.put(hash, bytes);
                hashOrder.add(hash);
                System.out.println("add: " + hash);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        });

        hashOrder.forEach(s -> {
            byte[] bytes = cachedFiles.get(s);
            System.out.print("\n read: ");
            for (byte b : bytes) {
                System.out.print(b + ", ");
            }

        });

        // write byte array to file
        ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
        hashOrder.forEach(s -> {
            try {
                outputStream2.write(cachedFiles.get(s));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        DataSource byteDataSource = new ByteArrayDataSource(outputStream2.toByteArray(), "application/octet-stream");
        DataHandler handlerFromBytes = new DataHandler(byteDataSource);
        FileOutputStream fileOutputStream = new FileOutputStream("/home/user/classes/COMPSCI-711/assignments/web-cache/server/files/Assignment1-2.pdf");
        handlerFromBytes.writeTo(fileOutputStream);
        fileOutputStream.flush();

        System.out.println(outputStream.size());
        System.out.println(dataSource.getContentType());
    }
}
