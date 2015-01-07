/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tomas
 */
@Entity
@Table(name = "PropertyCategoryMaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PropertyCategoryMaster.findAll", query = "SELECT p FROM PropertyCategoryMaster p"),
    @NamedQuery(name = "PropertyCategoryMaster.findByCategoryId", query = "SELECT p FROM PropertyCategoryMaster p WHERE p.categoryId = :categoryId"),
    @NamedQuery(name = "PropertyCategoryMaster.findByCategoryName", query = "SELECT p FROM PropertyCategoryMaster p WHERE p.categoryName = :categoryName"),
    @NamedQuery(name = "PropertyCategoryMaster.findByCategoryDescription", query = "SELECT p FROM PropertyCategoryMaster p WHERE p.categoryDescription = :categoryDescription"),
    @NamedQuery(name = "PropertyCategoryMaster.findByBuyerCommission", query = "SELECT p FROM PropertyCategoryMaster p WHERE p.buyerCommission = :buyerCommission"),
    @NamedQuery(name = "PropertyCategoryMaster.findBySellerCommission", query = "SELECT p FROM PropertyCategoryMaster p WHERE p.sellerCommission = :sellerCommission"),
    @NamedQuery(name = "PropertyCategoryMaster.findByAgentCommission", query = "SELECT p FROM PropertyCategoryMaster p WHERE p.agentCommission = :agentCommission")})
public class PropertyCategoryMaster implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "buyerCommission")
    private int buyerCommission;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sellerCommission")
    private int sellerCommission;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "categoryId")
    private String categoryId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "categoryName")
    private String categoryName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "categoryDescription")
    private String categoryDescription;
    @Column(name = "agentCommission")
    private Integer agentCommission;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
    private Collection<PropertyDetails> propertyDetailsCollection;

    public PropertyCategoryMaster() {
    }

    public PropertyCategoryMaster(String categoryId) {
        this.categoryId = categoryId;
    }

    public PropertyCategoryMaster(String categoryId, String categoryName, String categoryDescription, Integer buyerCommission, Integer sellerCommission) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.buyerCommission = buyerCommission;
        this.sellerCommission = sellerCommission;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }


    public Integer getAgentCommission() {
        return agentCommission;
    }

    public void setAgentCommission(Integer agentCommission) {
        this.agentCommission = agentCommission;
    }

    @XmlTransient
    public Collection<PropertyDetails> getPropertyDetailsCollection() {
        return propertyDetailsCollection;
    }

    public void setPropertyDetailsCollection(Collection<PropertyDetails> propertyDetailsCollection) {
        this.propertyDetailsCollection = propertyDetailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryId != null ? categoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PropertyCategoryMaster)) {
            return false;
        }
        PropertyCategoryMaster other = (PropertyCategoryMaster) object;
        if ((this.categoryId == null && other.categoryId != null) || (this.categoryId != null && !this.categoryId.equals(other.categoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PropertyCategoryMaster[ categoryId=" + categoryId + " ]";
    }

    public int getBuyerCommission() {
        return buyerCommission;
    }

    public void setBuyerCommission(int buyerCommission) {
        this.buyerCommission = buyerCommission;
    }

    public int getSellerCommission() {
        return sellerCommission;
    }

    public void setSellerCommission(int sellerCommission) {
        this.sellerCommission = sellerCommission;
    }
    
}
