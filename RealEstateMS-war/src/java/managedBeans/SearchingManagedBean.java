/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.PropertyDetailsFacade;
import beans.PropertyLocationMasterFacade;
import entity.PropertyDetails;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
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

/**
 *
 * @author Tomas
 */
@ManagedBean
@SessionScoped
public class SearchingManagedBean {
    @EJB
    private PropertyLocationMasterFacade propertyLocationMasterFacade;
    @EJB
    private PropertyDetailsFacade propertyDetailsFacade;
    
    private String realestateType;
    private String realestateLocality;
    private BigDecimal realestatePrice;
    private Integer realestateArea;
    private String searchPhrase;
    
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private BigDecimal lowPrice;
    private BigDecimal highPrice;
    
    private Integer  minArea;
    private Integer maxArea;
    private Integer lowArea;
    private Integer highArea;
    
    private Integer numberOfSearchResults;

    public Integer getNumberOfSearchResults() {
        return numberOfSearchResults;
    }

    public void setNumberOfSearchResults(Integer numberOfSearchResults) {
        this.numberOfSearchResults = numberOfSearchResults;
    }
    
    

    public Integer getHighArea() {
        return highArea;
    }

    public void setHighArea(Integer highArea) {
        this.highArea = highArea;
        numberOfSearchResults=propertyDetailsFacade.countPropertySearchResults(realestateType, realestateLocality, lowPrice, highPrice, lowArea, highArea, searchPhrase).size();
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("searchForm:offersFound");
    }
    
    

    public Integer getMinArea() {
        return minArea;
    }

    public void setMinArea(Integer minArea) {
        this.minArea = minArea;
    }

    public Integer getMaxArea() {
        return maxArea;
    }

    public void setMaxArea(Integer maxArea) {
        this.maxArea = maxArea;
    }

    public Integer getLowArea() {
        return lowArea;
    }

    public void setLowArea(Integer lowArea) {
        this.lowArea = lowArea;
    }
    
    


    
    

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }
    
    public void viewSearchResults(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewSearchResults.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(SearchingManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
        numberOfSearchResults=propertyDetailsFacade.countPropertySearchResults(realestateType, realestateLocality, lowPrice, highPrice, lowArea, highArea, searchPhrase).size();
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("searchForm:offersFound");
    }

    public List<PropertyDetails> loadPropertySearchResults(){
        List<PropertyDetails> listOfSearchResults=propertyDetailsFacade.countPropertySearchResults(realestateType, realestateLocality, lowPrice, highPrice, lowArea, highArea, searchPhrase);
    return  listOfSearchResults;
    }
    
  public void countValidOffers( AjaxBehaviorEvent e){
    
  numberOfSearchResults=propertyDetailsFacade.countPropertySearchResults(realestateType, realestateLocality, lowPrice, highPrice, lowArea, highArea, searchPhrase).size();
  
  }
    
    
    @PostConstruct
    public void init(){
        minPrice=propertyDetailsFacade.findMinPrice().setScale(0,BigDecimal.ROUND_DOWN);
        maxPrice=propertyDetailsFacade.findMaxPrice().setScale(0,BigDecimal.ROUND_DOWN);
         minArea=propertyDetailsFacade.findMinArea();
        maxArea=propertyDetailsFacade.findMaxArea();
      lowPrice=minPrice;
      highPrice=maxPrice;
        lowArea=minArea;
        highArea=maxArea;
        numberOfSearchResults=propertyDetailsFacade.countPropertySearchResults(realestateType, realestateLocality, lowPrice, highPrice, lowArea, highArea, searchPhrase).size();
        
    }
    
    /**
     * Creates a new instance of SearchingManagedBean
     */
    public SearchingManagedBean() {
    }
    
      public String getRealestateType() {
        return realestateType;
    }

    public void setRealestateType(String realestateType) {
        this.realestateType = realestateType;
    }

    public String getRealestateLocality() {
        return realestateLocality;
    }

    public void setRealestateLocality(String realestateLocality) {
        this.realestateLocality = realestateLocality;
    }

    public BigDecimal getRealestatePrice() {
        return realestatePrice;
    }

    public void setRealestatePrice(BigDecimal realestatePrice) {
        this.realestatePrice = realestatePrice;
    }

    public Integer getRealestateArea() {
        return realestateArea;
    }

    public void setRealestateArea(Integer realestateArea) {
        this.realestateArea = realestateArea;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }
    
    
}
