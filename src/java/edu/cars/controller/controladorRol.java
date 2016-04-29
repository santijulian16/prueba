/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cars.controller;

import edu.cars.entity.Rol;
import edu.cars.facade.RolFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author JoseLuis
 */
@Named(value = "controladorRol")
@SessionScoped
public class controladorRol implements Serializable {

    @Inject
    RolFacade rolFacade;
    Rol rol;

    private List<Rol> miListacategoria = new ArrayList<>();
    private Integer rolid;
    private String roltipo;
    private int estados = 0;

    /**
     * Creates a new instance of controladorRol
     */
    public controladorRol() {
    }

    public List<Rol> getMiListacategoria() {
        return rolFacade.findAll();
    }

    public void setMiListacategoria(List<Rol> miListacategoria) {
        this.miListacategoria = miListacategoria;
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

    public String contarRoles() {
        return "" + rolFacade.count();
    }

    public String eliminarRol(Rol lista) {
        rolFacade.remove(lista);
        estados = 2;
        return "lisRoles";
    }

    public String actualizarRol(Rol lista) {

        FacesContext faces = FacesContext.getCurrentInstance();
        ExternalContext externalContext = faces.getExternalContext();
        Map<String, String> params = (Map<String, String>) externalContext.getRequestParameterMap();
        String formularioAct = (params.get("formulario"));
        lista.setRoltipo(params.get(formularioAct + ":nombre"));
        rolFacade.edit(lista);
        estados = 1;
        return "lisRoles";
    }
    public String nuevoRol() {
        Rol newRol = new Rol();
        newRol.setRoltipo(roltipo);
        rolFacade.create(newRol);
        estados = 3;
        return "listRoles";
    }
    
    public int getEstados() {
        return estados;
    }

    public void setEstados(int estados) {
        this.estados = estados;
    }



}
