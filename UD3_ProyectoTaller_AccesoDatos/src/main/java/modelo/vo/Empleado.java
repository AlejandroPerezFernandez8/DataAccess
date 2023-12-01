/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.vo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Alejandro.perezferna
 */
@Entity
@Table(name = "empleado")

public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codemp")
    private Integer codemp;
    @Basic(optional = false)
    @Column(name = "nomemp")
    private String nomemp;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salario")
    private Float salario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private Collection<Bonificacion> bonificacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private Collection<Reparacion> reparacionCollection;

    public Empleado() {
    }

    public Empleado(Integer codemp, String nomemp, Float salario, Collection<Bonificacion> bonificacionCollection, Collection<Reparacion> reparacionCollection) {
        this.codemp = codemp;
        this.nomemp = nomemp;
        this.salario = salario;
        this.bonificacionCollection = bonificacionCollection;
        this.reparacionCollection = reparacionCollection;
    }

    
    
    public Empleado(Integer codemp) {
        this.codemp = codemp;
    }

    public Empleado(Integer codemp, String nomemp) {
        this.codemp = codemp;
        this.nomemp = nomemp;
    }

    public Integer getCodemp() {
        return codemp;
    }

    public void setCodemp(Integer codemp) {
        this.codemp = codemp;
    }

    public String getNomemp() {
        return nomemp;
    }

    public void setNomemp(String nomemp) {
        this.nomemp = nomemp;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public Collection<Bonificacion> getBonificacionCollection() {
        return bonificacionCollection;
    }

    public void setBonificacionCollection(Collection<Bonificacion> bonificacionCollection) {
        this.bonificacionCollection = bonificacionCollection;
    }

    public Collection<Reparacion> getReparacionCollection() {
        return reparacionCollection;
    }

    public void setReparacionCollection(Collection<Reparacion> reparacionCollection) {
        this.reparacionCollection = reparacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codemp != null ? codemp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.codemp == null && other.codemp != null) || (this.codemp != null && !this.codemp.equals(other.codemp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.vo.Empleado[ codemp=" + codemp + " ]";
    }
    
}
