/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.vo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alejandro.perezferna
 */
@Entity
@Table(name = "reparacion")

public class Reparacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReparacionPK reparacionPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "importe")
    private Double importe;
    @Column(name = "fechaf")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaf;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Coche coche;
    @JoinColumn(name = "codemp", referencedColumnName = "codemp", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado;

    public Reparacion() {
    }

    public Reparacion(ReparacionPK reparacionPK, Double importe, Date fechaf) {
        this.reparacionPK = reparacionPK;
        this.importe = importe;
        this.fechaf = fechaf;
    }

    
    
    public Reparacion(ReparacionPK reparacionPK) {
        this.reparacionPK = reparacionPK;
    }

    public Reparacion(int codemp, String matricula, Date fechai) {
        this.reparacionPK = new ReparacionPK(codemp, matricula, fechai);
    }

    public ReparacionPK getReparacionPK() {
        return reparacionPK;
    }

    public void setReparacionPK(ReparacionPK reparacionPK) {
        this.reparacionPK = reparacionPK;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Date getFechaf() {
        return fechaf;
    }

    public void setFechaf(Date fechaf) {
        this.fechaf = fechaf;
    }

    public Coche getCoche() {
        return coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
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
        hash += (reparacionPK != null ? reparacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reparacion)) {
            return false;
        }
        Reparacion other = (Reparacion) object;
        if ((this.reparacionPK == null && other.reparacionPK != null) || (this.reparacionPK != null && !this.reparacionPK.equals(other.reparacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.vo.Reparacion[ reparacionPK=" + reparacionPK + " ]";
    }
    
}
