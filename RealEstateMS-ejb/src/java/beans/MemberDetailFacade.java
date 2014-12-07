/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.MemberDetail;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomas
 */
@Stateless
@Named("MemberDetailFacade")
public class MemberDetailFacade extends AbstractFacade<MemberDetail> {
    @PersistenceContext(unitName = "RealEstateMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MemberDetailFacade() {
        super(MemberDetail.class);
    }
    
}
