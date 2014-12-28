/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.PropertyCategoryMaster;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomas
 */
@Stateless
public class PropertyCategoryMasterFacade extends AbstractFacade<PropertyCategoryMaster> {
    
    
    @PersistenceContext(unitName = "RealEstateMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public String getCategoryNameByCategoryId(String categoryId){
   PropertyCategoryMaster category= (PropertyCategoryMaster) em.createNamedQuery("PropertyCategoryMaster.findByCategoryId").setParameter("categoryId", categoryId).getSingleResult();
   return category.getCategoryName();
    }

    public PropertyCategoryMasterFacade() {
        super(PropertyCategoryMaster.class);
    }
    
}
