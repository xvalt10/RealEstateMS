/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.MemberDetail;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomas
 */
@Stateless
@Named("MemberDetailFacade")
public class MemberDetailFacade extends AbstractFacade<MemberDetail> {
    @EJB
    private MemberCategoryMasterFacade memberCategoryMasterFacade;
    
    
    @PersistenceContext(unitName = "RealEstateMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public MemberDetail getMemberidByUsername(String username){
        MemberDetail member=null;
        try{
        member=em.createNamedQuery("MemberDetail.findByUsername",MemberDetail.class).setParameter("username", username).getSingleResult();
        }catch(NoResultException e){
            
        }
        return member;
    }
    
    public List<MemberDetail> findAllBuyers(){
    return em.createNamedQuery("MemberDetail.findByMemberCategoryId").setParameter("memberCategoryId", memberCategoryMasterFacade.find("1")).getResultList();
    
    }

    public MemberDetailFacade() {
        super(MemberDetail.class);
    }
    
}
