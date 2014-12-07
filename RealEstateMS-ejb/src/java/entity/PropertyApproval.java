/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "PropertyApproval")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PropertyApproval.findAll", query = "SELECT p FROM PropertyApproval p"),
    @NamedQuery(name = "PropertyApproval.findByPropertyId", query = "SELECT p FROM PropertyApproval p WHERE p.propertyId = :propertyId"),
    @NamedQuery(name = "PropertyApproval.findByApprovalStatus", query = "SELECT p FROM PropertyApproval p WHERE p.approvalStatus = :approvalStatus"),
    @NamedQuery(name = "PropertyApproval.findByApprovalDescription", query = "SELECT p FROM PropertyApproval p WHERE p.approvalDescription = :approvalDescription"),
    @NamedQuery(name = "PropertyApproval.findByApproverId", query = "SELECT p FROM PropertyApproval p WHERE p.approverId = :approverId")})
public class PropertyApproval implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "propertyId")
    private String propertyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "approvalStatus")
    private String approvalStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "approvalDescription")
    private String approvalDescription;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "approverId")
    private String approverId;
    @JoinColumn(name = "propertyId", referencedColumnName = "propertyId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private PropertyDetails propertyDetails;

    public PropertyApproval() {
    }

    public PropertyApproval(String propertyId) {
        this.propertyId = propertyId;
    }

    public PropertyApproval(String propertyId, String approvalStatus, String approvalDescription, String approverId) {
        this.propertyId = propertyId;
        this.approvalStatus = approvalStatus;
        this.approvalDescription = approvalDescription;
        this.approverId = approverId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalDescription() {
        return approvalDescription;
    }

    public void setApprovalDescription(String approvalDescription) {
        this.approvalDescription = approvalDescription;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public PropertyDetails getPropertyDetails() {
        return propertyDetails;
    }

    public void setPropertyDetails(PropertyDetails propertyDetails) {
        this.propertyDetails = propertyDetails;
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
        if (!(object instanceof PropertyApproval)) {
            return false;
        }
        PropertyApproval other = (PropertyApproval) object;
        if ((this.propertyId == null && other.propertyId != null) || (this.propertyId != null && !this.propertyId.equals(other.propertyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PropertyApproval[ propertyId=" + propertyId + " ]";
    }
    
}
