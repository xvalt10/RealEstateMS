/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.MemberDetailFacade;
import beans.PropertyCategoryMasterFacade;
import beans.PropertyDetailsFacade;
import beans.PropertyLocationMasterFacade;
import entity.PropertyCategoryMaster;
import entity.PropertyDetails;
import entity.PropertyLocationMaster;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
public class PropertyManagedBean {
    @EJB
    private PropertyCategoryMasterFacade propertyCategoryFacade;
    
    @EJB
    private PropertyDetailsFacade propertyDetailsFacade;
    
    @EJB
    private PropertyLocationMasterFacade propertyLocationFacade;
    
    private PropertyCategoryMaster propertyCategory;
    
    private PropertyDetails propertyDetails;
    
    private Map<String, Object> propertyCategoriesMap;
    
    private PropertyLocationMaster propertyLocation;

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
    
    
    public void addProperty(){
        
        String propertyid=String.valueOf(propertyDetailsFacade.count()+1);
        String locationid=String.valueOf(propertyLocationFacade.count()+1);
        propertyLocation.setLocationId(locationid);
        propertyLocation.setCity(propertyDetails.getCity());
        propertyLocation.setCountry(propertyDetails.getCountry());
        propertyLocation.setState(propertyDetails.getState());
        propertyLocation.setLocality(propertyDetails.getRegion());
        propertyDetails.setLocationId(propertyLocation);
        propertyDetailsFacade.create(propertyDetails);
        
    }
    
    public List<PropertyCategoryMaster> getPropertyCategories(){
        return propertyCategoryFacade.findAll();
    }
    
    public List<PropertyLocationMaster> getPropertyLocations(){
        return propertyLocationFacade.findAll();
    }

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public PropertyManagedBean() {
        
    }
    
}
