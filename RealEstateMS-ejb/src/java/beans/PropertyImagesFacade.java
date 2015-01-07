/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.PropertyImages;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomas
 */
@Stateless
public class PropertyImagesFacade extends AbstractFacade<PropertyImages> {
    @PersistenceContext(unitName = "RealEstateMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PropertyImagesFacade() {
        super(PropertyImages.class);
    }
    
      public Integer getNewId(){
        Integer maxid=(Integer) em.createNativeQuery("select MAX(imageId) from PropertyImages").getSingleResult();
        if (maxid==null){return 1;}
        return ++maxid;  
    }
}
