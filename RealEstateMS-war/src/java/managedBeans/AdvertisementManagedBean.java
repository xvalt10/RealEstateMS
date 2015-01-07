/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.AdvertisementPackageFacade;
import beans.AdvertisementSubscriptionDetailFacade;
import beans.MemberDetailFacade;
import beans.PropertyApprovalFacade;
import beans.PropertyDetailsFacade;
import entity.AdvertisementPackage;
import entity.AdvertisementSubscriptionDetail;

import entity.PropertyApproval;
import entity.PropertyDetails;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Tomas
 */
@ManagedBean
@SessionScoped
public class AdvertisementManagedBean {
    @EJB
    private PropertyDetailsFacade propertyDetailsFacade;
    
    private final static String STATUS_PENDING="Pending";
    
    @EJB
    private MemberDetailFacade memberDetailFacade;
    
    @EJB
    private PropertyApprovalFacade propertyApprovalFacade;
    @EJB
    private AdvertisementPackageFacade advertisementPackageFacade;
    @EJB
    private AdvertisementSubscriptionDetailFacade advertisementSubscriptionDetailFacade;
    
    
    private AdvertisementPackage advertisementPackage;
     private PropertyApproval propertyApproval;
     private AdvertisementSubscriptionDetail adDetails;
     

    public AdvertisementSubscriptionDetail getAdDetails() {
        return adDetails;
    }

    public void setAdDetails(AdvertisementSubscriptionDetail adDetails) {
        this.adDetails = adDetails;
    }
     
     
    

    public AdvertisementPackage getAdvertisementPackage() {
        return advertisementPackage;
    }

    public void setAdvertisementPackage(AdvertisementPackage advertisementPackage) {
        this.advertisementPackage = advertisementPackage;
    }
    
    
    @PostConstruct
    public void init(){
    advertisementPackage=new AdvertisementPackage();
    propertyApproval=new PropertyApproval();
    adDetails=new AdvertisementSubscriptionDetail();
    
    }
    

    /**
     * Creates a new instance of AdvertisementManagedBean
     */
    
    
    public AdvertisementManagedBean() {
        
    }
    
    public void approveAdvertisement(String propertyId, String approverUsername, boolean requestApproved){
        String approverId=memberDetailFacade.getMemberidByUsername(approverUsername).getMemberId();
        propertyApproval.setPropertyId(propertyId);
        propertyApproval.setPropertyDetails(propertyDetailsFacade.find(propertyId));
        if(requestApproved){
        propertyApproval.setApprovalStatus("Approved");
        propertyApproval.setApprovalDescription("Advertisement can be published.");
        }
        else{
           propertyApproval.setApprovalStatus("Rejected.");
           propertyApproval.setApprovalDescription("Request for approval has been rejected.");
        }
        propertyApproval.setApproverId(approverId);
        propertyApprovalFacade.edit(propertyApproval);
        propertyApproval=new PropertyApproval();
        
        
    }
    
    public String getApprovalStatus(String propertyId){
        
        if (propertyApprovalFacade.find(propertyId)==null){
        return STATUS_PENDING;
        }
        else{
    return propertyApprovalFacade.find(propertyId).getApprovalStatus();}
    }
    
   
    
    public String subscribeToAdPackage(String packageId, String username){
        if(advertisementSubscriptionDetailFacade.getPackageByUserId(memberDetailFacade.getMemberidByUsername(username)).equalsIgnoreCase("NoSubscription")){
        adDetails.setPackageId(packageId);
        adDetails.setRequestorId(memberDetailFacade.getMemberidByUsername(username));
        adDetails.setDuration(calculateAdDurationIndDays());
        advertisementSubscriptionDetailFacade.create(adDetails);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Info:Subscription to advertisement package "+packageId+" has been successful." ,
                  ""  );
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }
        else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error:Subscription to advertisement package failed! You have already subscribed to advertisement package "+packageId+"." ,
                  ""  );
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;}
//            return "alreadySubscribed";
        
       
        
    }
    
    public int calculateAdDurationIndDays(){
        long diff=adDetails.getAdvertisementEndDate().getTime()-adDetails.getAdvertisementStartDate().getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
    public void loadPackage(String packageId){
        try {
            advertisementPackage=advertisementPackageFacade.find(packageId);
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("editAdvertisementPackage.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AdvertisementManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updatePackage(){
    
    advertisementPackageFacade.edit(advertisementPackage);
    advertisementPackage=new AdvertisementPackage();
    }
    
    public void removePackage(String packageId){
        advertisementPackage=advertisementPackageFacade.find(packageId);
        advertisementPackageFacade.remove(advertisementPackage);
        advertisementPackage=new AdvertisementPackage();
    }
    
    public void addPackage(){
    advertisementPackage.setPackageId(advertisementPackageFacade.getNewId());
    advertisementPackageFacade.create(advertisementPackage);
    advertisementPackage=new AdvertisementPackage();
    }
    
    public List<AdvertisementPackage> getAdvertisementPackageList(){
        return advertisementPackageFacade.findAll();
    }
    
    
}
