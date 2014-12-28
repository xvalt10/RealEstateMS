/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.AdvertisementPackage;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomas
 */
@Stateless
public class AdvertisementPackageFacade extends AbstractFacade<AdvertisementPackage> {
    @PersistenceContext(unitName = "RealEstateMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdvertisementPackageFacade() {
        super(AdvertisementPackage.class);
    }
    
    public String getNewId(){
        int maxid=(Integer) em.createNativeQuery("select MAX(CAST(packageId as INT)) from AdvertisementPackage").getSingleResult();
        return String.valueOf(maxid+1);  
    }
    
   
}
