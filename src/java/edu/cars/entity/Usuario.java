/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cars.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Julian
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsuid", query = "SELECT u FROM Usuario u WHERE u.usuid = :usuid"),
    @NamedQuery(name = "Usuario.findByUsulogin", query = "SELECT u FROM Usuario u WHERE u.usulogin = :usulogin"),
    @NamedQuery(name = "Usuario.findByUsupassword", query = "SELECT u FROM Usuario u WHERE u.usupassword = :usupassword")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USUID")
    private Integer usuid;
    @Size(max = 20)
    @Column(name = "USULOGIN")
    private String usulogin;
    @Size(max = 20)
    @Column(name = "USUPASSWORD")
    private String usupassword;
    @JoinTable(name = "usuario_rol", joinColumns = {
        @JoinColumn(name = "USUID", referencedColumnName = "USUID")}, inverseJoinColumns = {
        @JoinColumn(name = "ROLID", referencedColumnName = "ROLID")})
    @ManyToMany
    private Collection<Rol> rolCollection;
    @OneToMany(mappedBy = "usuid")
    private Collection<Datospersonales> datospersonalesCollection;

    public Usuario() {
    }

    public Usuario(Integer usuid) {
        this.usuid = usuid;
    }

    public Integer getUsuid() {
        return usuid;
    }

    public void setUsuid(Integer usuid) {
        this.usuid = usuid;
    }

    public String getUsulogin() {
        return usulogin;
    }

    public void setUsulogin(String usulogin) {
        this.usulogin = usulogin;
    }

    public String getUsupassword() {
        return usupassword;
    }

    public void setUsupassword(String usupassword) {
        this.usupassword = usupassword;
    }

    @XmlTransient
    public Collection<Rol> getRolCollection() {
        return rolCollection;
    }

    public void setRolCollection(Collection<Rol> rolCollection) {
        this.rolCollection = rolCollection;
    }

    @XmlTransient
    public Collection<Datospersonales> getDatospersonalesCollection() {
        return datospersonalesCollection;
    }

    public void setDatospersonalesCollection(Collection<Datospersonales> datospersonalesCollection) {
        this.datospersonalesCollection = datospersonalesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuid != null ? usuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuid == null && other.usuid != null) || (this.usuid != null && !this.usuid.equals(other.usuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.cars.entity.Usuario[ usuid=" + usuid + " ]";
    }
    
}
