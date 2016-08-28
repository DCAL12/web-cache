
package com.github.dcal12.web_cache.client.clientProxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.github.dcal12.web_cache.client.clientProxy package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DownloadResponse_QNAME = new QName("http://cache.web_cache.dcal12.github.com/", "downloadResponse");
    private final static QName _GetLog_QNAME = new QName("http://cache.web_cache.dcal12.github.com/", "getLog");
    private final static QName _ListServerFiles_QNAME = new QName("http://cache.web_cache.dcal12.github.com/", "listServerFiles");
    private final static QName _DownloadFile_QNAME = new QName("http://cache.web_cache.dcal12.github.com/", "downloadFile");
    private final static QName _DownloadFileResponse_QNAME = new QName("http://cache.web_cache.dcal12.github.com/", "downloadFileResponse");
    private final static QName _ClearCacheResponse_QNAME = new QName("http://cache.web_cache.dcal12.github.com/", "clearCacheResponse");
    private final static QName _DownloadRequest_QNAME = new QName("http://cache.web_cache.dcal12.github.com/", "downloadRequest");
    private final static QName _GetLogResponse_QNAME = new QName("http://cache.web_cache.dcal12.github.com/", "getLogResponse");
    private final static QName _ListServerFilesResponse_QNAME = new QName("http://cache.web_cache.dcal12.github.com/", "listServerFilesResponse");
    private final static QName _ClearCache_QNAME = new QName("http://cache.web_cache.dcal12.github.com/", "clearCache");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.github.dcal12.web_cache.client.clientProxy
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DownloadFile }
     * 
     */
    public DownloadFile createDownloadFile() {
        return new DownloadFile();
    }

    /**
     * Create an instance of {@link DownloadFileResponse }
     * 
     */
    public DownloadFileResponse createDownloadFileResponse() {
        return new DownloadFileResponse();
    }

    /**
     * Create an instance of {@link DownloadResponse }
     * 
     */
    public DownloadResponse createDownloadResponse() {
        return new DownloadResponse();
    }

    /**
     * Create an instance of {@link GetLog }
     * 
     */
    public GetLog createGetLog() {
        return new GetLog();
    }

    /**
     * Create an instance of {@link ListServerFiles }
     * 
     */
    public ListServerFiles createListServerFiles() {
        return new ListServerFiles();
    }

    /**
     * Create an instance of {@link ClearCache }
     * 
     */
    public ClearCache createClearCache() {
        return new ClearCache();
    }

    /**
     * Create an instance of {@link ClearCacheResponse }
     * 
     */
    public ClearCacheResponse createClearCacheResponse() {
        return new ClearCacheResponse();
    }

    /**
     * Create an instance of {@link DownloadRequest }
     * 
     */
    public DownloadRequest createDownloadRequest() {
        return new DownloadRequest();
    }

    /**
     * Create an instance of {@link GetLogResponse }
     * 
     */
    public GetLogResponse createGetLogResponse() {
        return new GetLogResponse();
    }

    /**
     * Create an instance of {@link ListServerFilesResponse }
     * 
     */
    public ListServerFilesResponse createListServerFilesResponse() {
        return new ListServerFilesResponse();
    }

    /**
     * Create an instance of {@link BlockElement }
     * 
     */
    public BlockElement createBlockElement() {
        return new BlockElement();
    }

    /**
     * Create an instance of {@link BlockElementArray }
     * 
     */
    public BlockElementArray createBlockElementArray() {
        return new BlockElementArray();
    }

    /**
     * Create an instance of {@link StringArray }
     * 
     */
    public StringArray createStringArray() {
        return new StringArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cache.web_cache.dcal12.github.com/", name = "downloadResponse")
    public JAXBElement<DownloadResponse> createDownloadResponse(DownloadResponse value) {
        return new JAXBElement<DownloadResponse>(_DownloadResponse_QNAME, DownloadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLog }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cache.web_cache.dcal12.github.com/", name = "getLog")
    public JAXBElement<GetLog> createGetLog(GetLog value) {
        return new JAXBElement<GetLog>(_GetLog_QNAME, GetLog.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListServerFiles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cache.web_cache.dcal12.github.com/", name = "listServerFiles")
    public JAXBElement<ListServerFiles> createListServerFiles(ListServerFiles value) {
        return new JAXBElement<ListServerFiles>(_ListServerFiles_QNAME, ListServerFiles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadFile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cache.web_cache.dcal12.github.com/", name = "downloadFile")
    public JAXBElement<DownloadFile> createDownloadFile(DownloadFile value) {
        return new JAXBElement<DownloadFile>(_DownloadFile_QNAME, DownloadFile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cache.web_cache.dcal12.github.com/", name = "downloadFileResponse")
    public JAXBElement<DownloadFileResponse> createDownloadFileResponse(DownloadFileResponse value) {
        return new JAXBElement<DownloadFileResponse>(_DownloadFileResponse_QNAME, DownloadFileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearCacheResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cache.web_cache.dcal12.github.com/", name = "clearCacheResponse")
    public JAXBElement<ClearCacheResponse> createClearCacheResponse(ClearCacheResponse value) {
        return new JAXBElement<ClearCacheResponse>(_ClearCacheResponse_QNAME, ClearCacheResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cache.web_cache.dcal12.github.com/", name = "downloadRequest")
    public JAXBElement<DownloadRequest> createDownloadRequest(DownloadRequest value) {
        return new JAXBElement<DownloadRequest>(_DownloadRequest_QNAME, DownloadRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLogResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cache.web_cache.dcal12.github.com/", name = "getLogResponse")
    public JAXBElement<GetLogResponse> createGetLogResponse(GetLogResponse value) {
        return new JAXBElement<GetLogResponse>(_GetLogResponse_QNAME, GetLogResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListServerFilesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cache.web_cache.dcal12.github.com/", name = "listServerFilesResponse")
    public JAXBElement<ListServerFilesResponse> createListServerFilesResponse(ListServerFilesResponse value) {
        return new JAXBElement<ListServerFilesResponse>(_ListServerFilesResponse_QNAME, ListServerFilesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearCache }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cache.web_cache.dcal12.github.com/", name = "clearCache")
    public JAXBElement<ClearCache> createClearCache(ClearCache value) {
        return new JAXBElement<ClearCache>(_ClearCache_QNAME, ClearCache.class, null, value);
    }

}
