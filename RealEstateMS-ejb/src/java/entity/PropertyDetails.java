/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tomas
 */
@Entity
@Table(name = "PropertyDetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PropertyDetails.findAll", query = "SELECT p FROM PropertyDetails p"),
    @NamedQuery(name = "PropertyDetails.findByPropertyId", query = "SELECT p FROM PropertyDetails p WHERE p.propertyId = :propertyId"),
    @NamedQuery(name = "PropertyDetails.findByCountry", query = "SELECT p FROM PropertyDetails p WHERE p.country = :country"),
    @NamedQuery(name = "PropertyDetails.findByState", query = "SELECT p FROM PropertyDetails p WHERE p.state = :state"),
    @NamedQuery(name = "PropertyDetails.findByCity", query = "SELECT p FROM PropertyDetails p WHERE p.city = :city"),
    @NamedQuery(name = "PropertyDetails.findByRegion", query = "SELECT p FROM PropertyDetails p WHERE p.region = :region"),
    @NamedQuery(name = "PropertyDetails.findByArea", query = "SELECT p FROM PropertyDetails p WHERE p.area = :area"),
    @NamedQuery(name = "PropertyDetails.findByRate", query = "SELECT p FROM PropertyDetails p WHERE p.rate = :rate"),
    @NamedQuery(name = "PropertyDetails.findByUser", query = "SELECT p FROM PropertyDetails p WHERE p.postedBy = :postedBy"),
    @NamedQuery(name = "PropertyDetails.findByLumpsumCost", query = "SELECT p FROM PropertyDetails p WHERE p.lumpsumCost = :lumpsumCost"),
    @NamedQuery(name = "PropertyDetails.findByPropertyDescription", query = "SELECT p FROM PropertyDetails p WHERE p.propertyDescription = :propertyDescription"),
    @NamedQuery(name = "PropertyDetails.findByPropertyTitle", query = "SELECT p FROM PropertyDetails p WHERE p.propertyTitle = :propertyTitle"),
    @NamedQuery(name = "PropertyDetails.findMaxPrice", query = "SELECT MAX(p.lumpsumCost) FROM PropertyDetails p"),
    @NamedQuery(name = "PropertyDetails.findMinPrice", query = "SELECT MIN(p.lumpsumCost) FROM PropertyDetails p"),
    @NamedQuery(name = "PropertyDetails.findMaxArea", query = "SELECT MAX(p.area) FROM PropertyDetails p"),
    @NamedQuery(name = "PropertyDetails.findMinArea", query = "SELECT MIN(p.area) FROM PropertyDetails p")
})
public class PropertyDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "propertyId")
    private String propertyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "state")
    private String state;
    @Size(max = 50)
    @Column(name = "city")
    private String city;
    @Size(max = 50)
    @Column(name = "region")
    private String region;
    @Basic(optional = false)
    @NotNull
    @Column(name = "area")
    private int area;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rate")
    private BigDecimal rate;
    @Column(name = "lumpsumCost")
    private BigDecimal lumpsumCost;
    @Lob
    @Column(name = "image")
    private byte[] image;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "propertyDescription")
    private String propertyDescription;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "propertyTitle")
    private String propertyTitle;
    @JoinColumn(name = "postedBy", referencedColumnName = "memberId")
    @ManyToOne(optional = false)
    private MemberDetail postedBy;
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
    @ManyToOne(optional = false)
    private PropertyCategoryMaster categoryId;
    @JoinColumn(name = "locationId", referencedColumnName = "locationId")
    @ManyToOne(optional = false)
    private PropertyLocationMaster locationId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "propertyDetails")
    private PropertyApproval propertyApproval;

    public PropertyDetails() {
    }

    public PropertyDetails(String propertyId) {
        this.propertyId = propertyId;
    }

    public PropertyDetails(String propertyId, String country, String state, int area, String propertyDescription, String propertyTitle) {
        this.propertyId = propertyId;
        this.country = country;
        this.state = state;
        this.area = area;
        this.propertyDescription = propertyDescription;
        this.propertyTitle = propertyTitle;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getLumpsumCost() {
        return lumpsumCost;
    }

    public void setLumpsumCost(BigDecimal lumpsumCost) {
        this.lumpsumCost = lumpsumCost;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public String getPropertyTitle() {
        return propertyTitle;
    }

    public void setPropertyTitle(String propertyTitle) {
        this.propertyTitle = propertyTitle;
    }

    public MemberDetail getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(MemberDetail postedBy) {
        this.postedBy = postedBy;
    }

    public PropertyCategoryMaster getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(PropertyCategoryMaster categoryId) {
        this.categoryId = categoryId;
    }

    public PropertyLocationMaster getLocationId() {
        return locationId;
    }

    public void setLocationId(PropertyLocationMaster locationId) {
        this.locationId = locationId;
    }

    public PropertyApproval getPropertyApproval() {
        return propertyApproval;
    }

    public void setPropertyApproval(PropertyApproval propertyApproval) {
        this.propertyApproval = propertyApproval;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (propertyId != null ? propertyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PropertyDetails)) {
            return false;
        }
        PropertyDetails other = (PropertyDetails) object;
        if ((this.propertyId == null && other.propertyId != null) || (this.propertyId != null && !this.propertyId.equals(other.propertyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PropertyDetails[ propertyId=" + propertyId + " ]";
    }
    
}
