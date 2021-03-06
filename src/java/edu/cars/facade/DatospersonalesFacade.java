/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cars.facade;

import edu.cars.entity.Datospersonales;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JoseLuis
 */
@Stateless
public class DatospersonalesFacade extends AbstractFacade<Datospersonales> {

    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DatospersonalesFacade() {
        super(Datospersonales.class);
    }
    
}
