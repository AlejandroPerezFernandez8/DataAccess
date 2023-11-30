/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.vo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Alejandro.perezferna
 */
@Embeddable
public class BonificacionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "codemp")
    private int codemp;
    @Basic(optional = false)
    @Column(name = "mes")
    private String mes;

    public BonificacionPK() {
    }

    public BonificacionPK(int codemp, String mes) {
        this.codemp = codemp;
        this.mes = mes;
    }

    public int getCodemp() {
        return codemp;
    }

    public void setCodemp(int codemp) {
        this.codemp = codemp;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codemp;
        hash += (mes != null ? mes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BonificacionPK)) {
            return false;
        }
        BonificacionPK other = (BonificacionPK) object;
        if (this.codemp != other.codemp) {
            return false;
        }
        if ((this.mes == null && other.mes != null) || (this.mes != null && !this.mes.equals(other.mes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.vo.BonificacionPK[ codemp=" + codemp + ", mes=" + mes + " ]";
    }
    
}
