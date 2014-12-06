/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.AdministratorLogin;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomas
 */
@Stateless
public class AdministratorLoginFacade extends AbstractFacade<AdministratorLogin> {
    @PersistenceContext(unitName = "RealEstateMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministratorLoginFacade() {
        super(AdministratorLogin.class);
    }
    
}
