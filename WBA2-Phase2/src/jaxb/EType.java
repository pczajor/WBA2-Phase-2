//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.08 at 02:23:42 PM MESZ 
//


package jaxb;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="eType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Spielzeitraum" type="{}dType"/>
 *         &lt;element name="Sportart" type="{}sType"/>
 *         &lt;element name="Oertlichkeit" type="{}oType"/>
 *         &lt;element name="Spielerliste" type="{}slType"/>
 *         &lt;element name="Backlist" type="{}bType"/>
 *         &lt;element name="Admin" type="{}spielerType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Event-ID" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eType", propOrder = {
    "spielzeitraum",
    "sportart",
    "oertlichkeit",
    "spielerliste",
    "backlist",
    "admin"
})
public class EType {

    @XmlElement(name = "Spielzeitraum", required = true)
    protected DType spielzeitraum;
    @XmlElement(name = "Sportart", required = true)
    protected SType sportart;
    @XmlElement(name = "Oertlichkeit", required = true)
    protected OType oertlichkeit;
    @XmlElement(name = "Spielerliste", required = true)
    protected SlType spielerliste;
    @XmlElement(name = "Backlist", required = true)
    protected BType backlist;
    @XmlElement(name = "Admin", required = true)
    protected SpielerType admin;
    @XmlAttribute(name = "Event-ID")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger eventID;

    /**
     * Gets the value of the spielzeitraum property.
     * 
     * @return
     *     possible object is
     *     {@link DType }
     *     
     */
    public DType getSpielzeitraum() {
        return spielzeitraum;
    }

    /**
     * Sets the value of the spielzeitraum property.
     * 
     * @param value
     *     allowed object is
     *     {@link DType }
     *     
     */
    public void setSpielzeitraum(DType value) {
        this.spielzeitraum = value;
    }

    /**
     * Gets the value of the sportart property.
     * 
     * @return
     *     possible object is
     *     {@link SType }
     *     
     */
    public SType getSportart() {
        return sportart;
    }

    /**
     * Sets the value of the sportart property.
     * 
     * @param value
     *     allowed object is
     *     {@link SType }
     *     
     */
    public void setSportart(SType value) {
        this.sportart = value;
    }

    /**
     * Gets the value of the oertlichkeit property.
     * 
     * @return
     *     possible object is
     *     {@link OType }
     *     
     */
    public OType getOertlichkeit() {
        return oertlichkeit;
    }

    /**
     * Sets the value of the oertlichkeit property.
     * 
     * @param value
     *     allowed object is
     *     {@link OType }
     *     
     */
    public void setOertlichkeit(OType value) {
        this.oertlichkeit = value;
    }

    /**
     * Gets the value of the spielerliste property.
     * 
     * @return
     *     possible object is
     *     {@link SlType }
     *     
     */
    public SlType getSpielerliste() {
        return spielerliste;
    }

    /**
     * Sets the value of the spielerliste property.
     * 
     * @param value
     *     allowed object is
     *     {@link SlType }
     *     
     */
    public void setSpielerliste(SlType value) {
        this.spielerliste = value;
    }

    /**
     * Gets the value of the backlist property.
     * 
     * @return
     *     possible object is
     *     {@link BType }
     *     
     */
    public BType getBacklist() {
        return backlist;
    }

    /**
     * Sets the value of the backlist property.
     * 
     * @param value
     *     allowed object is
     *     {@link BType }
     *     
     */
    public void setBacklist(BType value) {
        this.backlist = value;
    }

    /**
     * Gets the value of the admin property.
     * 
     * @return
     *     possible object is
     *     {@link SpielerType }
     *     
     */
    public SpielerType getAdmin() {
        return admin;
    }

    /**
     * Sets the value of the admin property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpielerType }
     *     
     */
    public void setAdmin(SpielerType value) {
        this.admin = value;
    }

    /**
     * Gets the value of the eventID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEventID() {
        return eventID;
    }

    /**
     * Sets the value of the eventID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEventID(BigInteger value) {
        this.eventID = value;
    }

}
