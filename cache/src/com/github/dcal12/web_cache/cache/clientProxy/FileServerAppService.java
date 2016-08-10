
package com.github.dcal12.web_cache.cache.clientProxy;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.7-b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "FileServerAppService", targetNamespace = "http://server.web_cache.dcal12.github.com/", wsdlLocation = "http://localhost:8080/app?wsdl")
public class FileServerAppService
        extends Service {

    private final static URL FILESERVERAPPSERVICE_WSDL_LOCATION;
    private final static WebServiceException FILESERVERAPPSERVICE_EXCEPTION;
    private final static QName FILESERVERAPPSERVICE_QNAME = new QName("http://server.web_cache.dcal12.github.com/", "FileServerAppService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/app?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        FILESERVERAPPSERVICE_WSDL_LOCATION = url;
        FILESERVERAPPSERVICE_EXCEPTION = e;
    }

    public FileServerAppService() {
        super(__getWsdlLocation(), FILESERVERAPPSERVICE_QNAME);
    }

    public FileServerAppService(WebServiceFeature... features) {
        super(__getWsdlLocation(), FILESERVERAPPSERVICE_QNAME, features);
    }

    public FileServerAppService(URL wsdlLocation) {
        super(wsdlLocation, FILESERVERAPPSERVICE_QNAME);
    }

    public FileServerAppService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, FILESERVERAPPSERVICE_QNAME, features);
    }

    public FileServerAppService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FileServerAppService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    private static URL __getWsdlLocation() {
        if (FILESERVERAPPSERVICE_EXCEPTION != null) {
            throw FILESERVERAPPSERVICE_EXCEPTION;
        }
        return FILESERVERAPPSERVICE_WSDL_LOCATION;
    }

    /**
     *
     * @return
     *     returns FileServer
     */
    @WebEndpoint(name = "FileServerAppPort")
    public FileServer getFileServerAppPort() {
        return super.getPort(new QName("http://server.web_cache.dcal12.github.com/", "FileServerAppPort"), FileServer.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FileServer
     */
    @WebEndpoint(name = "FileServerAppPort")
    public FileServer getFileServerAppPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server.web_cache.dcal12.github.com/", "FileServerAppPort"), FileServer.class, features);
    }

}
