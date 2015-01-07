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
import beans.PropertyImagesFacade;
import beans.PropertyLocationMasterFacade;
import entity.MemberDetail;
import entity.PropertyApproval;
import entity.PropertyCategoryMaster;
import entity.PropertyDetails;
import entity.PropertyImages;
import entity.PropertyLocationMaster;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import javax.faces.event.AjaxBehaviorEvent;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import pagination.JsfUtil;

/**
 *
 * @author Tomas
 */
@ManagedBean
@SessionScoped
public class PropertyManagedBean {
    @EJB
    private PropertyImagesFacade propertyImagesFacade;
    

    @EJB
    private PropertyCategoryMasterFacade propertyCategoryFacade;
    
    @EJB
    private PropertyDetailsFacade propertyDetailsFacade;
    
    @EJB
    private PropertyLocationMasterFacade propertyLocationFacade;
    
    
    
    
    
    @EJB
    private MemberDetailFacade memberDetailsFacade;
    
    private List<byte[]> listOfImageByteArrays;
    
    private static final int BUFFER_SIZE=6124;
    
    private PropertyCategoryMaster propertyCategory;
    
    private PropertyDetails propertyDetails;
    
    private PropertyImages propertyImages;
    
    private Map<String, Object> propertyCategoriesMap;
    
    private PropertyLocationMaster propertyLocation;
    
    private boolean editFormRendered=false;
    
    
    private int pageSize;
    private int currentPage;

    public PropertyDetailsFacade getPropertyDetailsFacade() {
        return propertyDetailsFacade;
    }
    
    

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public void ajaxSetPageSize(AjaxBehaviorEvent event, int page){
        setCurrentPage(page);
    }
    
    public List<PropertyDetails> getRangeOfPropertyDetails(int page){
    return propertyDetailsFacade.findRangeForQueryResult(new int[]{page*pageSize,page*pageSize+pageSize-1}, PropertyDetailsFacade.VALID_PROPERTY_QUERY);
    }
    
    public String getValidPropertyQueryString(){
    return PropertyDetailsFacade.VALID_PROPERTY_QUERY;}

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
        pageSize=5;
        propertyCategory=new PropertyCategoryMaster();
        propertyDetails=new PropertyDetails();
        propertyLocation=new PropertyLocationMaster();
        propertyImages=new PropertyImages();
        listOfImageByteArrays=new ArrayList<>();
        
    
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
        
         for(byte[] image:listOfImageByteArrays){
             propertyImages.setImageId(propertyImagesFacade.getNewId());
        propertyImages.setPropertyId(propertyDetailsFacade.find(propertyid));
        propertyImages.setPropertyImage(image);
        propertyImagesFacade.create(propertyImages);
        }
        
         JsfUtil.addSuccessMessage("Property has been succesfully saved with id:"+propertyid);
    }
    
    public String getCurrentUserName(){
        FacesContext context=FacesContext.getCurrentInstance();
        return context.getExternalContext().getRemoteUser();
    }
    
    public void propertyImageUpload(FileUploadEvent event){
        boolean titleImage=Boolean.valueOf(event.getComponent().getAttributes().get("isTitleImage").toString());
        InputStream inputStream= null;
        try {
            inputStream = event.getFile().getInputstream();
            if(titleImage){
            propertyDetails.setImage(IOUtils.toByteArray(inputStream));}
            else{
            listOfImageByteArrays.add(IOUtils.toByteArray(inputStream));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(PropertyManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(PropertyManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        JsfUtil.addSuccessMessage("Image "+event.getFile().getFileName()+"has been uploaded succesfully.");
    
    }
    
   
    
    public String findPropertyCategoryName(String categoryId){
    return propertyCategoryFacade.getCategoryNameByCategoryId(categoryId);
    }
    
    public void viewPropertyDetails(String propertyId){
        propertyDetails=propertyDetailsFacade.find(propertyId);
    propertyLocation=propertyDetails.getLocationId();
    propertyCategory=propertyDetails.getCategoryId();
        
        try {
            //FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("propertyId", propertyDetails.getPropertyId());
            FacesContext.getCurrentInstance().getExternalContext().redirect("/RealEstateMS-war/faces/propertyDetails.xhtml?propertyId="+propertyId);
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
    public void deleteProperty(AjaxBehaviorEvent event,String propertyId){
        propertyDetails=propertyDetailsFacade.find(propertyId);
        propertyDetailsFacade.remove(propertyDetails);
        JsfUtil.addSuccessMessage("Property with id " + propertyId + " has been successfully deleted.");
    
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
 public List<PropertyDetails> getAllPropertyDetailsList(){
         List <PropertyDetails> list=propertyDetailsFacade.findAll();
        return list;
    }
    
     public List<PropertyDetails> getPropertyDetailsList(){
         List <PropertyDetails> list=propertyDetailsFacade.listValidProperty();
        return list;
    }
    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public PropertyManagedBean() {
        
    }
    
}
