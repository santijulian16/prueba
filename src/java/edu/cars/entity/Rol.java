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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Julian
 */
@Entity
@Table(name = "rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"),
    @NamedQuery(name = "Rol.findByRolid", query = "SELECT r FROM Rol r WHERE r.rolid = :rolid"),
    @NamedQuery(name = "Rol.findByRoltipo", query = "SELECT r FROM Rol r WHERE r.roltipo = :roltipo")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ROLID")
    private Integer rolid;
    @Size(max = 50)
    @Column(name = "ROLTIPO")
    private String roltipo;
    @ManyToMany(mappedBy = "rolCollection")
    private Collection<Usuario> usuarioCollection;

    public Rol() {
    }

    public Rol(Integer rolid) {
        this.rolid = rolid;
    }

    public Integer getRolid() {
        return rolid;
    }

    public void setRolid(Integer rolid) {
        this.rolid = rolid;
    }

    public String getRoltipo() {
        return roltipo;
    }

    public void setRoltipo(String roltipo) {
        this.roltipo = roltipo;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolid != null ? rolid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.rolid == null && other.rolid != null) || (this.rolid != null && !this.rolid.equals(other.rolid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.cars.entity.Rol[ rolid=" + rolid + " ]";
    }
    
}
