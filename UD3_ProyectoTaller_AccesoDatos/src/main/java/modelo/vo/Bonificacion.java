/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.vo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Alejandro.perezferna
 */
@Entity
@Table(name = "bonificacion")

public class Bonificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BonificacionPK bonificacionPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "importebonificado")
    private Float importebonificado;
    @JoinColumn(name = "codemp", referencedColumnName = "codemp", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado;

    public Bonificacion(BonificacionPK bonificacionPK, Float importebonificado, Empleado empleado) {
        this.bonificacionPK = bonificacionPK;
        this.importebonificado = importebonificado;
        this.empleado = empleado;
    }
    
    public Bonificacion() {
    }

    public Bonificacion(BonificacionPK bonificacionPK) {
        this.bonificacionPK = bonificacionPK;
    }

    public Bonificacion(int codemp, String mes) {
        this.bonificacionPK = new BonificacionPK(codemp, mes);
    }

    public BonificacionPK getBonificacionPK() {
        return bonificacionPK;
    }

    public void setBonificacionPK(BonificacionPK bonificacionPK) {
        this.bonificacionPK = bonificacionPK;
    }

    public Float getImportebonificado() {
        return importebonificado;
    }

    public void setImportebonificado(Float importebonificado) {
        this.importebonificado = importebonificado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bonificacionPK != null ? bonificacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bonificacion)) {
            return false;
        }
        Bonificacion other = (Bonificacion) object;
        if ((this.bonificacionPK == null && other.bonificacionPK != null) || (this.bonificacionPK != null && !this.bonificacionPK.equals(other.bonificacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.vo.Bonificacion[ bonificacionPK=" + bonificacionPK + " ]";
    }
    
}
