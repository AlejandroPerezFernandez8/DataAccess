//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2023.10.14 a las 05:51:49 PM CEST 
//


package Modelo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para vacaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="vacaType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Raza" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Sexo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Edad" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="id_vaca" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="id_matadero" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vacaType", propOrder = {
    "raza",
    "sexo",
    "edad"
})
public class VacaType {

    @XmlElement(name = "Raza", required = true)
    protected String raza;
    @XmlElement(name = "Sexo", required = true)
    protected String sexo;
    @XmlElement(name = "Edad")
    protected int edad;
    @XmlAttribute(name = "id_vaca", required = true)
    protected String idVaca;
    @XmlAttribute(name = "id_matadero", required = true)
    protected String idMatadero;

    public VacaType(String raza, String sexo, int edad, String idVaca, String idMatadero) {
        this.raza = raza;
        this.sexo = sexo;
        this.edad = edad;
        this.idVaca = idVaca;
        this.idMatadero = idMatadero;
    }

    public VacaType() {
    }

    
    
    /**
     * Obtiene el valor de la propiedad raza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRaza() {
        return raza;
    }

    /**
     * Define el valor de la propiedad raza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRaza(String value) {
        this.raza = value;
    }

    /**
     * Obtiene el valor de la propiedad sexo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Define el valor de la propiedad sexo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSexo(String value) {
        this.sexo = value;
    }

    /**
     * Obtiene el valor de la propiedad edad.
     * 
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Define el valor de la propiedad edad.
     * 
     */
    public void setEdad(int value) {
        this.edad = value;
    }

    /**
     * Obtiene el valor de la propiedad idVaca.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdVaca() {
        return idVaca;
    }

    /**
     * Define el valor de la propiedad idVaca.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdVaca(String value) {
        this.idVaca = value;
    }

    /**
     * Obtiene el valor de la propiedad idMatadero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMatadero() {
        return idMatadero;
    }

    /**
     * Define el valor de la propiedad idMatadero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMatadero(String value) {
        this.idMatadero = value;
    }

    @Override
    public String toString() {
        return "Vaca{" + "raza=" + raza + ", sexo=" + sexo + ", edad=" + edad + ", idVaca=" + idVaca + ", idMatadero=" + idMatadero + '}';
    }

    
    
}
