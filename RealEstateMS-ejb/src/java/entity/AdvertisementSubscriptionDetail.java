/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tomas
 */
@Entity
@Table(name = "AdvertisementSubscriptionDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdvertisementSubscriptionDetail.findAll", query = "SELECT a FROM AdvertisementSubscriptionDetail a"),
    @NamedQuery(name = "AdvertisementSubscriptionDetail.findByPackageId", query = "SELECT a FROM AdvertisementSubscriptionDetail a WHERE a.packageId = :packageId"),
    @NamedQuery(name = "AdvertisementSubscriptionDetail.findByDuration", query = "SELECT a FROM AdvertisementSubscriptionDetail a WHERE a.duration = :duration"),
    @NamedQuery(name = "AdvertisementSubscriptionDetail.findByDimension", query = "SELECT a FROM AdvertisementSubscriptionDetail a WHERE a.dimension = :dimension"),
    @NamedQuery(name = "AdvertisementSubscriptionDetail.findByAdvertisementStartDate", query = "SELECT a FROM AdvertisementSubscriptionDetail a WHERE a.advertisementStartDate = :advertisementStartDate"),
    @NamedQuery(name = "AdvertisementSubscriptionDetail.findByAdvertisementEndDate", query = "SELECT a FROM AdvertisementSubscriptionDetail a WHERE a.advertisementEndDate = :advertisementEndDate")})
public class AdvertisementSubscriptionDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "packageId")
    private String packageId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duration")
    private int duration;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "dimension")
    private String dimension;
    @Basic(optional = false)
    @NotNull
    @Column(name = "advertisementStartDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date advertisementStartDate;
    @Column(name = "advertisementEndDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date advertisementEndDate;
    @JoinColumn(name = "packageId", referencedColumnName = "packageId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private AdvertisementPackage advertisementPackage;
    @JoinColumn(name = "requestorId", referencedColumnName = "memberId")
    @ManyToOne(optional = false)
    private MemberDetail requestorId;

    public AdvertisementSubscriptionDetail() {
    }

    public AdvertisementSubscriptionDetail(String packageId) {
        this.packageId = packageId;
    }

    public AdvertisementSubscriptionDetail(String packageId, int duration, String dimension, Date advertisementStartDate) {
        this.packageId = packageId;
        this.duration = duration;
        this.dimension = dimension;
        this.advertisementStartDate = advertisementStartDate;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public Date getAdvertisementStartDate() {
        return advertisementStartDate;
    }

    public void setAdvertisementStartDate(Date advertisementStartDate) {
        this.advertisementStartDate = advertisementStartDate;
    }

    public Date getAdvertisementEndDate() {
        return advertisementEndDate;
    }

    public void setAdvertisementEndDate(Date advertisementEndDate) {
        this.advertisementEndDate = advertisementEndDate;
    }

    public AdvertisementPackage getAdvertisementPackage() {
        return advertisementPackage;
    }

    public void setAdvertisementPackage(AdvertisementPackage advertisementPackage) {
        this.advertisementPackage = advertisementPackage;
    }

    public MemberDetail getRequestorId() {
        return requestorId;
    }

    public void setRequestorId(MemberDetail requestorId) {
        this.requestorId = requestorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (packageId != null ? packageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdvertisementSubscriptionDetail)) {
            return false;
        }
        AdvertisementSubscriptionDetail other = (AdvertisementSubscriptionDetail) object;
        if ((this.packageId == null && other.packageId != null) || (this.packageId != null && !this.packageId.equals(other.packageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.AdvertisementSubscriptionDetail[ packageId=" + packageId + " ]";
    }
    
}
