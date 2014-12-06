/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.MemberCategoryMaster;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomas
 */
@Stateless
public class MemberCategoryMasterFacade extends AbstractFacade<MemberCategoryMaster> {
    @PersistenceContext(unitName = "RealEstateMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MemberCategoryMasterFacade() {
        super(MemberCategoryMaster.class);
    }
    
}
