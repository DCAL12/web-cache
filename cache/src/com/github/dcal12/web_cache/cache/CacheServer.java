package com.github.dcal12.web_cache.cache;

/**
 * Created by Douglas Callaway on 8/7/16.
 */

import com.github.dcal12.web_cache.cache.data.DownloadRequest;
import com.github.dcal12.web_cache.cache.data.DownloadResponse;

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
    String[] listServerFiles();

    @WebMethod
    DownloadResponse downloadFile(DownloadRequest downloadRequest);

    @WebMethod
    String[] getLog();
}
