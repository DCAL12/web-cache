package com.github.dcal12.web_cache.client;

import com.github.dcal12.web_cache.client.proxy.FileServer;
import com.github.dcal12.web_cache.client.proxy.FileServerAppService;

import javax.activation.DataHandler;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by Douglas Callaway on 8/7/16.
 */

public class FileBrowserClient {
  public static void main(String[] args) throws IOException {

      FileServer proxy = new FileServerAppService().getFileServerAppPort();

      System.out.println(proxy.listFiles());

      DataHandler dataHandler = proxy.downloadFile("file1");
      FileOutputStream outputStream = new FileOutputStream("/home/user/Downloads/file1");
      dataHandler.writeTo(outputStream);
      outputStream.flush();

      System.out.println("download complete");
  }
}
