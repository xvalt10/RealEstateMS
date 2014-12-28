/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.MemberDetailFacade;
import beans.PropertyApprovalFacade;
import beans.PropertyCategoryMasterFacade;
import beans.PropertyDetailsFacade;
import beans.PropertyLocationMasterFacade;
import entity.MemberDetail;
import entity.PropertyApproval;
import entity.PropertyCategoryMaster;
import entity.PropertyDetails;
import entity.PropertyLocationMaster;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Tomas
 */
@ManagedBean
@SessionScoped
public class PropertyManagedBean {
    

    @EJB
    private PropertyCategoryMasterFacade propertyCategoryFacade;
    
    @EJB
    private PropertyDetailsFacade propertyDetailsFacade;
    
    @EJB
    private PropertyLocationMasterFacade propertyLocationFacade;
    
    
    
    @EJB
    private MemberDetailFacade memberDetailsFacade;
    
    private static final int BUFFER_SIZE=6124;
    
    private PropertyCategoryMaster propertyCategory;
    
    private PropertyDetails propertyDetails;
    
    private Map<String, Object> propertyCategoriesMap;
    
    private PropertyLocationMaster propertyLocation;
    
    private boolean editFormRendered=false;

    public boolean isEditFormRendered() {
        return editFormRendered;
    }

    public void setEditFormRendered(boolean editFormRendered) {
        this.editFormRendered = editFormRendered;
    }
    
   

    public PropertyDetails getPropertyDetails() {
        return propertyDetails;
    }

    public void setPropertyDetails(PropertyDetails propertyDetails) {
        this.propertyDetails = propertyDetails;
    }
    
    

    public PropertyCategoryMaster getPropertyCategory() {
        return propertyCategory;
    }

    public void setPropertyCategory(PropertyCategoryMaster propertyCategory) {
        this.propertyCategory = propertyCategory;
    }
    
    @PostConstruct
    public void init(){
        propertyCategory=new PropertyCategoryMaster();
        propertyDetails=new PropertyDetails();
        propertyLocation=new PropertyLocationMaster();
        
    
    }
    
    public void addPropertyCategory(){
        
    String categoryId = String.valueOf(propertyCategoryFacade.count()+1);
    propertyCategory.setCategoryId(categoryId);
    propertyCategoryFacade.create(propertyCategory);
    
    
    }
    
    public void editProperty(){
        
     
         
        
//        propertyLocation.setLocationId(propertyDetails.getLocationId().getLocationId());
//        propertyLocation.setCity(propertyDetails.getCity());
//        propertyLocation.setCountry(propertyDetails.getCountry());
//        propertyLocation.setState(propertyDetails.getState());
//        propertyLocation.setLocality(propertyDetails.getRegion());
//        propertyLocationFacade.edit(propertyLocation);
//        
//        propertyDetails.setPropertyId(propertyDetails.getPropertyId());
//        propertyDetails.setLocationId(propertyLocation);
//        propertyDetails.setPostedBy(memberDetailsFacade.getMemberidByUsername(getCurrentUserName()));
        
        propertyDetailsFacade.edit(propertyDetails);
        
    }
    
    
    public void addProperty(){
        
               
        String propertyid=propertyDetailsFacade.getNewId();
        String locationid=propertyLocationFacade.getNewId();
        
        propertyLocation.setLocationId(locationid);
        propertyLocation.setCity(propertyDetails.getCity());
        propertyLocation.setCountry(propertyDetails.getCountry());
        propertyLocation.setState(propertyDetails.getState());
        propertyLocation.setLocality(propertyDetails.getRegion());
        propertyLocationFacade.create(propertyLocation);
        
        propertyDetails.setPropertyId(propertyid);
        propertyDetails.setLocationId(propertyLocation);
        propertyDetails.setPostedBy(memberDetailsFacade.getMemberidByUsername(getCurrentUserName()));
        
        propertyDetailsFacade.create(propertyDetails);
        
        
        
        
        
    }
    
    public String getCurrentUserName(){
        FacesContext context=FacesContext.getCurrentInstance();
        return context.getExternalContext().getRemoteUser();
    }
    
    public void propertyImageUpload(FileUploadEvent event){
        InputStream inputStream= null;
        try {
            inputStream = event.getFile().getInputstream();
            propertyDetails.setImage(IOUtils.toByteArray(inputStream));
            
        } catch (IOException ex) {
            Logger.getLogger(PropertyManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(PropertyManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
    public String findPropertyCategoryName(String categoryId){
    return propertyCategoryFacade.getCategoryNameByCategoryId(categoryId);
    }
    
    public void viewPropertyDetails(String propertyId){
        propertyDetails=propertyDetailsFacade.find(propertyId);
    propertyLocation=propertyDetails.getLocationId();
    propertyCategory=propertyDetails.getCategoryId();
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("propertyDetails.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PropertyManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void loadProperty(String propertyId){
        
    propertyDetails=propertyDetailsFacade.find(propertyId);
    propertyLocation=propertyDetails.getLocationId();
    propertyCategory=propertyDetails.getCategoryId();
    
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("editPropertyDetails.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PropertyManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
  public List<PropertyDetails> loadPropertyByMember(String username){
      MemberDetail member=memberDetailsFacade.getMemberidByUsername(username);
      setEditFormRendered(true);
  return propertyDetailsFacade.findPropertyByUser(member);
  }
    
    public List<PropertyCategoryMaster> getPropertyCategories(){
        return propertyCategoryFacade.findAll();
    }
    
    public List<PropertyLocationMaster> getPropertyLocations(){
        return propertyLocationFacade.findAll();
    }

     public List<PropertyDetails> getPropertyDetailsList(){
         List <PropertyDetails> list=propertyDetailsFacade.findAll();
        return list;
    }
    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public PropertyManagedBean() {
        
    }
    
}
