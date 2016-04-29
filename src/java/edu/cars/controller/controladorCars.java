/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cars.controller;

import edu.cars.entity.Vehiculo;
import edu.cars.entity.Categoria;

import edu.cars.facade.CategoriaFacade;
import edu.cars.facade.VehiculoFacade;
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
@Named(value = "controladorCars")
@SessionScoped
public class controladorCars implements Serializable {

    
    @Inject
    VehiculoFacade vehiculoFacade;
    @Inject
    CategoriaFacade categoriaFacade;
    
    
    Vehiculo miVehiculo;
    List<Vehiculo> miListaVehiculo = new ArrayList<>();
        
    private String vehplaca;
    private Integer vehmodelo;
    private String vehmarca;
    private String vehestado;
    private Integer vehprecio;
    private Integer  catid;
    private Integer datid;
    private Integer estados;

    public Integer getEstados() {
        return estados;
    }

    public void setEstados(Integer estados) {
        this.estados = estados;
    }

    public Vehiculo getMiVehiculo() {
        return miVehiculo;
    }

    public void setMiVehiculo(Vehiculo miVehiculo) {
        this.miVehiculo = miVehiculo;
    }
    
     public String cuantosVehiculos() {
       return ""+vehiculoFacade.count();
    }

    public List<Vehiculo> getMiListaVehiculo() {
        return (List<Vehiculo>) vehiculoFacade.findAll();
    }

 

    public String getVehplaca() {
        return vehplaca;
    }

    public void setVehplaca(String vehplaca) {
        this.vehplaca = vehplaca;
    }

    public Integer getVehmodelo() {
        return vehmodelo;
    }

    public void setVehmodelo(Integer vehmodelo) {
        this.vehmodelo = vehmodelo;
    }

    public String getVehmarca() {
        return vehmarca;
    }

    public void setVehmarca(String vehmarca) {
        this.vehmarca = vehmarca;
    }

    public String getVehestado() {
        return vehestado;
    }

    public void setVehestado(String vehestado) {
        this.vehestado = vehestado;
    }

    public Integer getVehprecio() {
        return vehprecio;
    }

    public void setVehprecio(Integer vehprecio) {
        this.vehprecio = vehprecio;
    }

    public Integer getCatid() {
        return catid;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public Integer getDatid() {
        return datid;
    }

    public void setDatid(Integer datid) {
        this.datid = datid;
    }

    
    /**
     * Creates a new instance of controladorCars
     */
    public controladorCars() {
    }
    
    
        public String contarCars() {
        return "" + vehiculoFacade.count();
    }

    public String eliminarCars(Vehiculo lista) {
        vehiculoFacade.remove(lista);
        estados = 2;
        return "listCart";
    }

    public String actualizarVehiculos(Vehiculo lista) {

        FacesContext faces = FacesContext.getCurrentInstance();
        ExternalContext externalContext = faces.getExternalContext();
        Map<String, String> params = (Map<String, String>) externalContext.getRequestParameterMap();
        String formularioAct = (params.get("formulario"));
       
        
        Vehiculo newVehiculo = new Vehiculo();
        newVehiculo.setVehplaca(params.get(formularioAct + ":vehplaca"));
        
        Categoria miCategoria = new Categoria();   
        miCategoria.setCatipo(params.get("catid"));
        
        newVehiculo.setCatid(miCategoria);
        newVehiculo.setVehmodelo(Integer.parseInt(params.get(formularioAct + ":vehmodelo")));
        newVehiculo.setVehmarca(params.get(formularioAct + ":vehmarca"));
        newVehiculo.setVehestado(params.get(formularioAct + ":vehestado"));
        newVehiculo.setVehprecio(Integer.parseInt(params.get(formularioAct + ":vehprecio")));
        

        vehiculoFacade.edit(newVehiculo);
        estados = 1;
        return "listCart";
    }
    
    
        public String updateVehi() {

        FacesContext faces = FacesContext.getCurrentInstance();
        ExternalContext externalContext = faces.getExternalContext();
        Map<String, String> params = (Map<String, String>) externalContext.getRequestParameterMap();
        String formularioAct = (params.get("formulario"));
       
        
        Vehiculo newVehiculo = new Vehiculo();
        newVehiculo.setVehplaca(params.get(formularioAct + ":placa"));
        
        Categoria miCategoria = new Categoria();   
        miCategoria.setCatipo(params.get(formularioAct + ":catid"));
        
        newVehiculo.setCatid(miCategoria);
        newVehiculo.setVehmodelo(Integer.parseInt(params.get(formularioAct + ":vehmodelo")));
        newVehiculo.setVehmarca(params.get(formularioAct + ":vehmarca"));
        newVehiculo.setVehestado(params.get(formularioAct + ":vehestado"));
        newVehiculo.setVehprecio(Integer.parseInt(params.get(formularioAct + ":vehprecio")));
        

        vehiculoFacade.edit(newVehiculo);
        estados = 1;
        return "listCart";
    }
    
    

    public String nuevoCart() {
        Vehiculo newVehiculo = new Vehiculo();
        newVehiculo.setVehplaca(vehplaca);
        
        Categoria miCategoria = new Categoria();
        miCategoria.setCatid(catid);

        newVehiculo.setCatid(miCategoria);
        
        newVehiculo.setVehmodelo(vehmodelo);
        newVehiculo.setVehmarca(vehmarca);
        newVehiculo.setVehestado(vehestado);
        newVehiculo.setVehprecio(vehprecio);
        
        vehiculoFacade.create(newVehiculo);
        estados = 3;
        return "listCart";
    }

    
    
    
}
