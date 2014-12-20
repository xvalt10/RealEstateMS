/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.AdvertisementPackageFacade;
import beans.AdvertisementSubscriptionDetailFacade;
import entity.AdvertisementPackage;
import entity.PropertyDetails;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Tomas
 */
@ManagedBean
@RequestScoped
public class AdvertisementManagedBean {
    @EJB
    private AdvertisementPackageFacade advertisementPackageFacade;
    @EJB
    private AdvertisementSubscriptionDetailFacade advertisementSubscriptionDetailFacade;
    
    private AdvertisementPackage advertisementPackage;
    

    public AdvertisementPackage getAdvertisementPackage() {
        return advertisementPackage;
    }

    public void setAdvertisementPackage(AdvertisementPackage advertisementPackage) {
        this.advertisementPackage = advertisementPackage;
    }
    
    
    @PostConstruct
    public void init(){
    advertisementPackage=new AdvertisementPackage();
    }
    

    /**
     * Creates a new instance of AdvertisementManagedBean
     */
    
    
    public AdvertisementManagedBean() {
        
    }
    
    
    
    public void addPackage(){
    advertisementPackage.setPackageId(String.valueOf(advertisementPackageFacade.count()+1));
    advertisementPackageFacade.create(advertisementPackage);
    }
    
    public List<AdvertisementPackage> getAdvertisementPackageList(){
        return advertisementPackageFacade.findAll();
    }
    
    
}
