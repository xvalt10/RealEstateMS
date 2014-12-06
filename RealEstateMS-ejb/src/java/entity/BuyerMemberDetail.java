/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "buyerMemberDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BuyerMemberDetail.findAll", query = "SELECT b FROM BuyerMemberDetail b"),
    @NamedQuery(name = "BuyerMemberDetail.findByMemberId", query = "SELECT b FROM BuyerMemberDetail b WHERE b.memberId = :memberId"),
    @NamedQuery(name = "BuyerMemberDetail.findByPropertyArea", query = "SELECT b FROM BuyerMemberDetail b WHERE b.propertyArea = :propertyArea"),
    @NamedQuery(name = "BuyerMemberDetail.findByPropertyBudget", query = "SELECT b FROM BuyerMemberDetail b WHERE b.propertyBudget = :propertyBudget")})
public class BuyerMemberDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "memberId")
    private String memberId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "propertyArea")
    private int propertyArea;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "propertyBudget")
    private BigDecimal propertyBudget;
    @JoinColumn(name = "memberId", referencedColumnName = "memberId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private MemberDetail memberDetail;
    @JoinColumn(name = "locationId", referencedColumnName = "locationId")
    @ManyToOne(optional = false)
    private PropertyLocationMaster locationId;

    public BuyerMemberDetail() {
    }

    public BuyerMemberDetail(String memberId) {
        this.memberId = memberId;
    }

    public BuyerMemberDetail(String memberId, int propertyArea, BigDecimal propertyBudget) {
        this.memberId = memberId;
        this.propertyArea = propertyArea;
        this.propertyBudget = propertyBudget;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getPropertyArea() {
        return propertyArea;
    }

    public void setPropertyArea(int propertyArea) {
        this.propertyArea = propertyArea;
    }

    public BigDecimal getPropertyBudget() {
        return propertyBudget;
    }

    public void setPropertyBudget(BigDecimal propertyBudget) {
        this.propertyBudget = propertyBudget;
    }

    public MemberDetail getMemberDetail() {
        return memberDetail;
    }

    public void setMemberDetail(MemberDetail memberDetail) {
        this.memberDetail = memberDetail;
    }

    public PropertyLocationMaster getLocationId() {
        return locationId;
    }

    public void setLocationId(PropertyLocationMaster locationId) {
        this.locationId = locationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberId != null ? memberId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BuyerMemberDetail)) {
            return false;
        }
        BuyerMemberDetail other = (BuyerMemberDetail) object;
        if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.BuyerMemberDetail[ memberId=" + memberId + " ]";
    }
    
}
