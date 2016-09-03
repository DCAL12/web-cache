package com.github.dcal12.web_cache.cache;

/**
 * Created by Douglas Callaway on 8/7/16.
 */

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Service Endpoint Interface (SEI)
 * Adapted from the example by IBM developerWorks at
 * https://www.ibm.com/developerworks/library/ws-devaxis2part2/
 * Retrieved 07/08/2016
 */

@WebService
public interface CacheServer {

    @WebMethod
    String[] listCachedFiles();

    @WebMethod
    String[] listServerFiles();

    @WebMethod
    String[] downloadFile(String fileName);

    @WebMethod
    String[] getLog();

    @WebMethod
    void clearCache();
}
