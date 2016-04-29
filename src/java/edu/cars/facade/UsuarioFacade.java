/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cars.facade;

import edu.cars.entity.Datospersonales;
import edu.cars.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JoseLuis
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
     public boolean consultarDocumento(String documento) {
        List<Datospersonales> objArregloUsuario = new ArrayList<>();
        Query q;
        q = em.createQuery("select d from Datospersonales d where d.datnumeroid = :docu");
        q.setParameter("docu", documento);
        objArregloUsuario = q.getResultList();
        
        if (objArregloUsuario.isEmpty()) {
            return false;
        } else {
            return true;
        }
        
    }
    
    public Datospersonales login(String nombreUsuario, String contrasena) {
        Datospersonales objDatos = new Datospersonales();
        objDatos.setDatnombre("Vacio");
        Query q;
        q = em.createQuery("select d from Usuario d where d.usulogin = :nom and d.usupassword =:con");
        q.setParameter("nom", nombreUsuario);
        q.setParameter("con", contrasena);
        
        List<Usuario> miLista = q.getResultList();
        if (miLista.isEmpty()) {
            return objDatos;            
        } else {
            q = em.createQuery("select d from Datospersonales d where d.datid = :id");
            q.setParameter("id", miLista.get(0).getUsuid());
            
            List<Datospersonales> listaDatosPersonales = q.getResultList();
            
            return listaDatosPersonales.get(0);
            
        }
        
    }
    
}
