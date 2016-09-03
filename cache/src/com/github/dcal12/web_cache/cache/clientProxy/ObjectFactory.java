
package com.github.dcal12.web_cache.cache.clientProxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.github.dcal12.web_cache.cache.clientProxy package. 
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

    private final static QName _ListFilesResponse_QNAME = new QName("http://server.web_cache.dcal12.github.com/", "listFilesResponse");
    private final static QName _ListFiles_QNAME = new QName("http://server.web_cache.dcal12.github.com/", "listFiles");
    private final static QName _DownloadFileResponse_QNAME = new QName("http://server.web_cache.dcal12.github.com/", "downloadFileResponse");
    private final static QName _DownloadFile_QNAME = new QName("http://server.web_cache.dcal12.github.com/", "downloadFile");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.github.dcal12.web_cache.cache.clientProxy
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
     * Create an instance of {@link ListFilesResponse }
     * 
     */
    public ListFilesResponse createListFilesResponse() {
        return new ListFilesResponse();
    }

    /**
     * Create an instance of {@link ListFiles }
     * 
     */
    public ListFiles createListFiles() {
        return new ListFiles();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListFilesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.web_cache.dcal12.github.com/", name = "listFilesResponse")
    public JAXBElement<ListFilesResponse> createListFilesResponse(ListFilesResponse value) {
        return new JAXBElement<ListFilesResponse>(_ListFilesResponse_QNAME, ListFilesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListFiles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.web_cache.dcal12.github.com/", name = "listFiles")
    public JAXBElement<ListFiles> createListFiles(ListFiles value) {
        return new JAXBElement<ListFiles>(_ListFiles_QNAME, ListFiles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.web_cache.dcal12.github.com/", name = "downloadFileResponse")
    public JAXBElement<DownloadFileResponse> createDownloadFileResponse(DownloadFileResponse value) {
        return new JAXBElement<DownloadFileResponse>(_DownloadFileResponse_QNAME, DownloadFileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadFile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.web_cache.dcal12.github.com/", name = "downloadFile")
    public JAXBElement<DownloadFile> createDownloadFile(DownloadFile value) {
        return new JAXBElement<DownloadFile>(_DownloadFile_QNAME, DownloadFile.class, null, value);
    }

}
