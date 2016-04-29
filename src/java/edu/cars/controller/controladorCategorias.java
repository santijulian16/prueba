/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cars.controller;

import edu.cars.entity.Categoria;
import edu.cars.facade.CategoriaFacade;
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
@Named(value = "controladorCategorias")
@SessionScoped
public class controladorCategorias implements Serializable {

  @Inject
    CategoriaFacade categoriaFacade;

    Categoria categoria;

    List<Categoria> miListacategoria = new ArrayList<>();
    private Integer catid;
    private String catipo;
    private int estados = 0;

    /**
     * Creates a new instance of controladorCategorias
     */
    public controladorCategorias() {
    }

    public int getEstados() {
        return estados;
    }

    public void setEstados(int estados) {
        this.estados = estados;
    }

    public List<Categoria> getMiListacategoria() {
        return categoriaFacade.findAll();
    }

    public void setMiListacategoria(List<Categoria> miListacategoria) {
        this.miListacategoria = miListacategoria;
    }

    public Integer getCatid() {
        return catid;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public String getCatipo() {
        return catipo;
    }

    public void setCatipo(String catipo) {
        this.catipo = catipo;
    }

    public String contarCategorias() {
        return "" + categoriaFacade.count();
    }

    public String eliminarCategoria(Categoria lista) {
        categoriaFacade.remove(lista);
        estados = 2;
        return "listCategories";
    }

    public String actualizarCategorias(Categoria lista) {

        FacesContext faces = FacesContext.getCurrentInstance();
        ExternalContext externalContext = faces.getExternalContext();
        Map<String, String> params = (Map<String, String>) externalContext.getRequestParameterMap();
        String formularioAct = (params.get("formulario"));
        lista.setCatipo(params.get(formularioAct + ":nombre"));
        categoriaFacade.edit(lista);
        estados = 1;
        return "listCategories";
    }

    public String nuevaCategoria() {
        Categoria newCategoria = new Categoria();
        newCategoria.setCatipo(catipo);
        categoriaFacade.create(newCategoria);
        estados = 3;
        return "listCategories";
    }

    
}
