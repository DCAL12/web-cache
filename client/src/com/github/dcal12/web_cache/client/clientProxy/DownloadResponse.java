
package com.github.dcal12.web_cache.client.clientProxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for downloadResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="downloadResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="blockOrder" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="blocks" type="{http://cache.web_cache.dcal12.github.com/}blockElementArray" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "downloadResponse", propOrder = {
    "blockOrder",
    "blocks"
})
public class DownloadResponse {

    protected List<String> blockOrder;
    protected BlockElementArray blocks;

    /**
     * Gets the value of the blockOrder property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the blockOrder property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBlockOrder().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getBlockOrder() {
        if (blockOrder == null) {
            blockOrder = new ArrayList<String>();
        }
        return this.blockOrder;
    }

    /**
     * Gets the value of the blocks property.
     * 
     * @return
     *     possible object is
     *     {@link BlockElementArray }
     *     
     */
    public BlockElementArray getBlocks() {
        return blocks;
    }

    /**
     * Sets the value of the blocks property.
     * 
     * @param value
     *     allowed object is
     *     {@link BlockElementArray }
     *     
     */
    public void setBlocks(BlockElementArray value) {
        this.blocks = value;
    }

}
