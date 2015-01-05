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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "MemberDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MemberDetail.findAll", query = "SELECT m FROM MemberDetail m"),
    @NamedQuery(name = "MemberDetail.findByMemberId", query = "SELECT m FROM MemberDetail m WHERE m.memberId = :memberId"),
    @NamedQuery(name = "MemberDetail.findByMemberCategoryId", query = "SELECT m FROM MemberDetail m WHERE m.memberCategoryId = :memberCategoryId"),
    @NamedQuery(name = "MemberDetail.findByName", query = "SELECT m FROM MemberDetail m WHERE m.name = :name"),
    @NamedQuery(name = "MemberDetail.findByAddress", query = "SELECT m FROM MemberDetail m WHERE m.address = :address"),
    @NamedQuery(name = "MemberDetail.findByPincode", query = "SELECT m FROM MemberDetail m WHERE m.pincode = :pincode"),
    @NamedQuery(name = "MemberDetail.findByPhoneNnumber", query = "SELECT m FROM MemberDetail m WHERE m.phoneNnumber = :phoneNnumber"),
    @NamedQuery(name = "MemberDetail.findByMobileNumber", query = "SELECT m FROM MemberDetail m WHERE m.mobileNumber = :mobileNumber"),
    @NamedQuery(name = "MemberDetail.findByEmaiIId", query = "SELECT m FROM MemberDetail m WHERE m.emaiIId = :emaiIId"),
    @NamedQuery(name = "MemberDetail.findByNewsletterSubscription", query = "SELECT m FROM MemberDetail m WHERE m.newsletterSubscription = :newsletterSubscription"),
    @NamedQuery(name = "MemberDetail.findByUsername", query = "SELECT m FROM MemberDetail m WHERE m.username = :username"),
    @NamedQuery(name = "MemberDetail.findByPassword", query = "SELECT m FROM MemberDetail m WHERE m.password = :password")})
public class MemberDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "memberId")
    private String memberId;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 256)
    @Column(name = "address")
    private String address;
    @Size(max = 6)
    @Column(name = "pincode")
    private String pincode;
    @Size(max = 20)
    @Column(name = "phoneNnumber")
    private String phoneNnumber;
    @Size(max = 50)
    @Column(name = "mobileNumber")
    private String mobileNumber;
    @Size(max = 50)
    @Column(name = "emaiIId")
    private String emaiIId;
    @Column(name = "newsletterSubscription")
    private Boolean newsletterSubscription;
    @Size(max = 50)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postedBy")
    private Collection<PropertyDetails> propertyDetailsCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "memberDetail")
    private BuyerMemberDetail buyerMemberDetail;
    @JoinColumn(name = "memberCategoryId", referencedColumnName = "memberCategoryId")
    @ManyToOne(optional = false)
    private MemberCategoryMaster memberCategoryId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "memberDetail")
    private AgentMemberDetail agentMemberDetail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requestorId")
    private Collection<AdvertisementSubscriptionDetail> advertisementSubscriptionDetailCollection;

    public MemberDetail() {
    }

    public MemberDetail(String memberId, String name, String address, String pincode, String phoneNnumber, String mobileNumber, String emaiIId, Boolean newsletterSubscription, String username, String password, Collection<PropertyDetails> propertyDetailsCollection, BuyerMemberDetail buyerMemberDetail, MemberCategoryMaster memberCategoryId, AgentMemberDetail agentMemberDetail, Collection<AdvertisementSubscriptionDetail> advertisementSubscriptionDetailCollection) {
        this.memberId = memberId;
        this.name = name;
        this.address = address;
        this.pincode = pincode;
        this.phoneNnumber = phoneNnumber;
        this.mobileNumber = mobileNumber;
        this.emaiIId = emaiIId;
        this.newsletterSubscription = newsletterSubscription;
        this.username = username;
        this.password = password;
        this.propertyDetailsCollection = propertyDetailsCollection;
        this.buyerMemberDetail = buyerMemberDetail;
        this.memberCategoryId = memberCategoryId;
        this.agentMemberDetail = agentMemberDetail;
        this.advertisementSubscriptionDetailCollection = advertisementSubscriptionDetailCollection;
    }

    
    public MemberDetail(String memberId) {
        this.memberId = memberId;
    }

    public MemberDetail(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPhoneNnumber() {
        return phoneNnumber;
    }

    public void setPhoneNnumber(String phoneNnumber) {
        this.phoneNnumber = phoneNnumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmaiIId() {
        return emaiIId;
    }

    public void setEmaiIId(String emaiIId) {
        this.emaiIId = emaiIId;
    }

    public Boolean getNewsletterSubscription() {
        return newsletterSubscription;
    }

    public void setNewsletterSubscription(Boolean newsletterSubscription) {
        this.newsletterSubscription = newsletterSubscription;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<PropertyDetails> getPropertyDetailsCollection() {
        return propertyDetailsCollection;
    }

    public void setPropertyDetailsCollection(Collection<PropertyDetails> propertyDetailsCollection) {
        this.propertyDetailsCollection = propertyDetailsCollection;
    }

    public BuyerMemberDetail getBuyerMemberDetail() {
        return buyerMemberDetail;
    }

    public void setBuyerMemberDetail(BuyerMemberDetail buyerMemberDetail) {
        this.buyerMemberDetail = buyerMemberDetail;
    }

    public MemberCategoryMaster getMemberCategoryId() {
        return memberCategoryId;
    }

    public void setMemberCategoryId(MemberCategoryMaster memberCategoryId) {
        this.memberCategoryId = memberCategoryId;
    }

    public AgentMemberDetail getAgentMemberDetail() {
        return agentMemberDetail;
    }

    public void setAgentMemberDetail(AgentMemberDetail agentMemberDetail) {
        this.agentMemberDetail = agentMemberDetail;
    }

    @XmlTransient
    public Collection<AdvertisementSubscriptionDetail> getAdvertisementSubscriptionDetailCollection() {
        return advertisementSubscriptionDetailCollection;
    }

    public void setAdvertisementSubscriptionDetailCollection(Collection<AdvertisementSubscriptionDetail> advertisementSubscriptionDetailCollection) {
        this.advertisementSubscriptionDetailCollection = advertisementSubscriptionDetailCollection;
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
        if (!(object instanceof MemberDetail)) {
            return false;
        }
        MemberDetail other = (MemberDetail) object;
        if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MemberDetail[ memberId=" + memberId + " ]";
    }
    
}
