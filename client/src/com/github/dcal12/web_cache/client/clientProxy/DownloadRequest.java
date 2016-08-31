
package com.github.dcal12.web_cache.client.clientProxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for downloadRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="downloadRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cachedBlocks" type="{http://jaxb.dev.java.net/array}stringArray" minOccurs="0"/>
 *         &lt;element name="chunkMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "downloadRequest", propOrder = {
    "cachedBlocks",
    "chunkMethod",
    "fileName"
})
public class DownloadRequest {

    protected StringArray cachedBlocks;
    protected String chunkMethod;
    protected String fileName;

    /**
     * Gets the value of the cachedBlocks property.
     * 
     * @return
     *     possible object is
     *     {@link StringArray }
     *     
     */
    public StringArray getCachedBlocks() {
        return cachedBlocks;
    }

    /**
     * Sets the value of the cachedBlocks property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringArray }
     *     
     */
    public void setCachedBlocks(StringArray value) {
        this.cachedBlocks = value;
    }

    /**
     * Gets the value of the chunkMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChunkMethod() {
        return chunkMethod;
    }

    /**
     * Sets the value of the chunkMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChunkMethod(String value) {
        this.chunkMethod = value;
    }

    /**
     * Gets the value of the fileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the value of the fileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

}
