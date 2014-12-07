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
@Table(name = "PropertyLocationMaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PropertyLocationMaster.findAll", query = "SELECT p FROM PropertyLocationMaster p"),
    @NamedQuery(name = "PropertyLocationMaster.findByLocationId", query = "SELECT p FROM PropertyLocationMaster p WHERE p.locationId = :locationId"),
    @NamedQuery(name = "PropertyLocationMaster.findByCountry", query = "SELECT p FROM PropertyLocationMaster p WHERE p.country = :country"),
    @NamedQuery(name = "PropertyLocationMaster.findByState", query = "SELECT p FROM PropertyLocationMaster p WHERE p.state = :state"),
    @NamedQuery(name = "PropertyLocationMaster.findByCity", query = "SELECT p FROM PropertyLocationMaster p WHERE p.city = :city"),
    @NamedQuery(name = "PropertyLocationMaster.findByLocality", query = "SELECT p FROM PropertyLocationMaster p WHERE p.locality = :locality")})
public class PropertyLocationMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "locationId")
    private String locationId;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "city")
    private String city;
    @Size(max = 50)
    @Column(name = "locality")
    private String locality;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationId")
    private Collection<PropertyDetails> propertyDetailsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationId")
    private Collection<BuyerMemberDetail> buyerMemberDetailCollection;
    @OneToMany(mappedBy = "locationId")
    private Collection<AgentMemberDetail> agentMemberDetailCollection;

    public PropertyLocationMaster() {
    }

    public PropertyLocationMaster(String locationId) {
        this.locationId = locationId;
    }

    public PropertyLocationMaster(String locationId, String country, String state, String city) {
        this.locationId = locationId;
        this.country = country;
        this.state = state;
        this.city = city;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
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

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    @XmlTransient
    public Collection<PropertyDetails> getPropertyDetailsCollection() {
        return propertyDetailsCollection;
    }

    public void setPropertyDetailsCollection(Collection<PropertyDetails> propertyDetailsCollection) {
        this.propertyDetailsCollection = propertyDetailsCollection;
    }

    @XmlTransient
    public Collection<BuyerMemberDetail> getBuyerMemberDetailCollection() {
        return buyerMemberDetailCollection;
    }

    public void setBuyerMemberDetailCollection(Collection<BuyerMemberDetail> buyerMemberDetailCollection) {
        this.buyerMemberDetailCollection = buyerMemberDetailCollection;
    }

    @XmlTransient
    public Collection<AgentMemberDetail> getAgentMemberDetailCollection() {
        return agentMemberDetailCollection;
    }

    public void setAgentMemberDetailCollection(Collection<AgentMemberDetail> agentMemberDetailCollection) {
        this.agentMemberDetailCollection = agentMemberDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationId != null ? locationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PropertyLocationMaster)) {
            return false;
        }
        PropertyLocationMaster other = (PropertyLocationMaster) object;
        if ((this.locationId == null && other.locationId != null) || (this.locationId != null && !this.locationId.equals(other.locationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PropertyLocationMaster[ locationId=" + locationId + " ]";
    }
    
}
