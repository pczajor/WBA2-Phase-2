//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.08 at 02:23:42 PM MESZ 
//


package jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="sType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Fu�ball"/>
 *     &lt;enumeration value="Basketball"/>
 *     &lt;enumeration value="Tennis"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "sType")
@XmlEnum
public enum SType {

    @XmlEnumValue("Fu\u00dfball")
    FUSSBALL("Fu\u00dfball"),
    @XmlEnumValue("Basketball")
    BASKETBALL("Basketball"),
    @XmlEnumValue("Tennis")
    TENNIS("Tennis");
    private final String value;

    SType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SType fromValue(String v) {
        for (SType c: SType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
