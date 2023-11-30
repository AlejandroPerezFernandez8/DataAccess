/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Alejandro.perezferna
 */
@Entity
@Table(name = "cliente")

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codcli")
    private Integer codcli;
    @Basic(optional = false)
    @Column(name = "nomcli")
    private String nomcli;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "email")
    private String email;
    @Column(name = "tfno")
    private String tfno;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Coche> cocheList;

    public Cliente() {
    }

    public Cliente(Integer codcli, String nomcli, String direccion, String email, String tfno) {
        this.codcli = codcli;
        this.nomcli = nomcli;
        this.direccion = direccion;
        this.email = email;
        this.tfno = tfno;
    }

    
    
    public Cliente(Integer codcli) {
        this.codcli = codcli;
    }

    public Cliente(Integer codcli, String nomcli, String direccion) {
        this.codcli = codcli;
        this.nomcli = nomcli;
        this.direccion = direccion;
    }

    public Integer getCodcli() {
        return codcli;
    }

    public void setCodcli(Integer codcli) {
        this.codcli = codcli;
    }

    public String getNomcli() {
        return nomcli;
    }

    public void setNomcli(String nomcli) {
        this.nomcli = nomcli;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTfno() {
        return tfno;
    }

    public void setTfno(String tfno) {
        this.tfno = tfno;
    }

    public Collection<Coche> getCocheCollection() {
        return cocheList;
    }

    public void setCocheCollection(List<Coche> cocheList) {
        this.cocheList = cocheList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codcli != null ? codcli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.codcli == null && other.codcli != null) || (this.codcli != null && !this.codcli.equals(other.codcli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.vo.Cliente[ codcli=" + codcli + " ]";
    }
    
}
