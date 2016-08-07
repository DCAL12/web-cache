package com.github.dcal12.web_cache.client;

import com.github.dcal12.web_cache.client.proxy.FileServer;
import com.github.dcal12.web_cache.client.proxy.FileServerAppService;


/**
 * Created by Douglas Callaway on 8/7/16.
 */

public class FileBrowserClient {
  public static void main(String[] argv) {

      FileServer proxy = new FileServerAppService().getFileServerAppPort();
      System.out.println(proxy.listFiles());
  }
}
