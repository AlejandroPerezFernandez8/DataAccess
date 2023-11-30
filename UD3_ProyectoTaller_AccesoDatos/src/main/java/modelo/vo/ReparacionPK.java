/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.vo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alejandro.perezferna
 */
@Embeddable
public class ReparacionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "codemp")
    private int codemp;
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Basic(optional = false)
    @Column(name = "fechai")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechai;

    public ReparacionPK() {
    }

    public ReparacionPK(int codemp, String matricula, Date fechai) {
        this.codemp = codemp;
        this.matricula = matricula;
        this.fechai = fechai;
    }

    
    
    public int getCodemp() {
        return codemp;
    }

    public void setCodemp(int codemp) {
        this.codemp = codemp;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getFechai() {
        return fechai;
    }

    public void setFechai(Date fechai) {
        this.fechai = fechai;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codemp;
        hash += (matricula != null ? matricula.hashCode() : 0);
        hash += (fechai != null ? fechai.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReparacionPK)) {
            return false;
        }
        ReparacionPK other = (ReparacionPK) object;
        if (this.codemp != other.codemp) {
            return false;
        }
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        if ((this.fechai == null && other.fechai != null) || (this.fechai != null && !this.fechai.equals(other.fechai))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.vo.ReparacionPK[ codemp=" + codemp + ", matricula=" + matricula + ", fechai=" + fechai + " ]";
    }
    
}
