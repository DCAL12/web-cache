package example;

import proxySOAP.AppSOAP;
import proxySOAP.AppSOAPService;

/**
 * Created by Douglas Callaway on 8/7/16.
 */

public class HelloWorldClient {
  public static void main(String[] argv) {

      AppSOAP proxy = new AppSOAPService().getAppSOAPPort();
      System.out.println(proxy.greet("doug"));
  }
}
