/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.AdvertisementSubscriptionDetail;
import entity.MemberDetail;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tomas
 */
@Stateless
public class AdvertisementSubscriptionDetailFacade extends AbstractFacade<AdvertisementSubscriptionDetail> {
    @PersistenceContext(unitName = "RealEstateMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdvertisementSubscriptionDetailFacade() {
        super(AdvertisementSubscriptionDetail.class);
    }
    
    public String getPackageByUserId(MemberDetail userId){
        Query query = em.createNamedQuery("AdvertisementSubscriptionDetail.findByRequestorId").setParameter("requestorId", userId);
        if(query.getResultList().isEmpty()){
        return "NoSubscription";
        }
        else{
      AdvertisementSubscriptionDetail adDetail=(AdvertisementSubscriptionDetail)query.getSingleResult();
      return adDetail.getPackageId();}
    }
    
}
