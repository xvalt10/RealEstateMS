/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.MemberDetail;
import entity.PropertyDetails;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tomas
 */
@Stateless
public class PropertyDetailsFacade extends AbstractFacade<PropertyDetails> {
    public final static String VALID_PROPERTY_QUERY =     
            "select * from PropertyDetails pd "
          + "left join PropertyApproval pa on pd.propertyId=pa.propertyId "
          + "left join AdvertisementSubscriptionDetail asd on pd.postedBy=asd.requestorId"
                + " where pa.approvalStatus in ( 'Approved','Activated') "
                       + "and asd.advertisementEndDate >= GETDATE()";
    
    @PersistenceContext(unitName = "RealEstateMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PropertyDetailsFacade() {
        super(PropertyDetails.class);
    }
    
    public BigDecimal findMaxPrice(){
    return (BigDecimal)em.createNamedQuery("PropertyDetails.findMaxPrice").getSingleResult();
    }
     public BigDecimal findMinPrice(){
    return (BigDecimal)em.createNamedQuery("PropertyDetails.findMinPrice").getSingleResult();
    }
     
     public List<PropertyDetails> findPropertyByUser(MemberDetail member){
        return em.createNamedQuery("PropertyDetails.findByUser").setParameter("postedBy", member).getResultList();
     }
     
     
     public List<PropertyDetails> listValidProperty(){
         Query query= em.createNativeQuery(VALID_PROPERTY_QUERY,PropertyDetails.class);
         
         return (List<PropertyDetails>)query.getResultList();
         
     }
     
          
     
     public List<PropertyDetails> countPropertySearchResults(String realestateType, String realestateLocality, BigDecimal lowPrice, BigDecimal highPrice, Integer lowArea, Integer highArea, String searchPhrase){
       
         boolean realestateTypeParameterBound =false;
         boolean realestateLocalityParameterBound =false;
          boolean searchPhraseParameterBound =false;
          
         StringBuilder searchQuery=new StringBuilder("select * from PropertyDetails p "
               + "join PropertyLocationMaster l on p.locationId=l.locationId "
               + "join PropertyCategoryMaster c on p.categoryId=c.categoryId"
               + " where p.LumpsumCost >= ? and p.LumpsumCost <= ? "
               + "and p.Area >= ? and p.Area <= ?");
       
        if (searchPhrase!=null && !searchPhrase.equals("")){
       searchQuery.append(" and p.propertyDescription like ?");
       }
       if (realestateType!=null && !realestateType.equals("")){
       searchQuery.append(" and c.categoryName like ?");
       }
        if (realestateLocality !=null && !realestateLocality.equals("")){
       searchQuery.append(" and l.locality like ?");
       }
       
        Query query= em.createNativeQuery(searchQuery.toString(),PropertyDetails.class)
                 .setParameter(1, lowPrice)
                 .setParameter(2,highPrice)
                 .setParameter(3, lowArea)
                 .setParameter(4, highArea);
        
        if (searchPhrase!=null && !searchPhrase.equals("")){
            searchPhraseParameterBound=true;
       query.setParameter(5, "%"+searchPhrase+"%");
       }
       if (realestateType!=null && !realestateType.equals("")){
           realestateTypeParameterBound=true;
       query.setParameter(searchPhraseParameterBound?6:5, "%"+realestateType+"%");
       }
        if (realestateLocality !=null && !realestateLocality.equals("")){
       realestateLocalityParameterBound=true;
       query.setParameter(searchPhraseParameterBound?realestateLocalityParameterBound?7:6:5, "%"+realestateLocality+"%");
       }
                 
                 
                 
        
        return (List<PropertyDetails>)query.getResultList();
         
         
     
     }
     
      public Integer findMaxArea(){
    return (Integer)em.createNamedQuery("PropertyDetails.findMaxArea").getSingleResult();
    }
     public Integer findMinArea(){
    return (Integer)em.createNamedQuery("PropertyDetails.findMinArea").getSingleResult();
    }
     
    
     
     public String getNewId(){
        int maxid=(Integer) em.createNativeQuery("select MAX(CAST(propertyId as INT)) from PropertyDetails").getSingleResult();
        return String.valueOf(maxid+1);  
    }
}
