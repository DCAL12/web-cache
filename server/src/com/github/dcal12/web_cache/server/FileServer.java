package com.github.dcal12.web_cache.server;

/**
 * Created by Douglas Callaway on 8/7/16.
 */

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Service Endpoint Interface (SEI)
 * Adapted from the example by IBM developerWorks at
 * https://www.ibm.com/developerworks/library/ws-devaxis2part2/
 * Retrieved 07/08/2016
 */

@WebService
public interface FileServer {

    @WebMethod
    String[] listFiles();

    @WebMethod
    DataHandler downloadFile(String fileName);
}
