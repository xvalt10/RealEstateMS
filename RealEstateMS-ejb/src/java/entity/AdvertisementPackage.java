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
@Table(name = "AdvertisementPackage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdvertisementPackage.findAll", query = "SELECT a FROM AdvertisementPackage a"),
    @NamedQuery(name = "AdvertisementPackage.findByPackageId", query = "SELECT a FROM AdvertisementPackage a WHERE a.packageId = :packageId"),
    @NamedQuery(name = "AdvertisementPackage.findByBannerDimension", query = "SELECT a FROM AdvertisementPackage a WHERE a.bannerDimension = :bannerDimension"),
    @NamedQuery(name = "AdvertisementPackage.findByBannerImageSize", query = "SELECT a FROM AdvertisementPackage a WHERE a.bannerImageSize = :bannerImageSize"),
    @NamedQuery(name = "AdvertisementPackage.findByBannerTextSize", query = "SELECT a FROM AdvertisementPackage a WHERE a.bannerTextSize = :bannerTextSize"),
    @NamedQuery(name = "AdvertisementPackage.findByCostPerDay", query = "SELECT a FROM AdvertisementPackage a WHERE a.costPerDay = :costPerDay"),
    @NamedQuery(name = "AdvertisementPackage.findByWeekDiscount", query = "SELECT a FROM AdvertisementPackage a WHERE a.weekDiscount = :weekDiscount"),
    @NamedQuery(name = "AdvertisementPackage.findByMonthDiscount", query = "SELECT a FROM AdvertisementPackage a WHERE a.monthDiscount = :monthDiscount"),
    @NamedQuery(name = "AdvertisementPackage.findByYearDiscount", query = "SELECT a FROM AdvertisementPackage a WHERE a.yearDiscount = :yearDiscount")})
public class AdvertisementPackage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "packageId")
    private String packageId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "bannerDimension")
    private String bannerDimension;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "bannerImageSize")
    private String bannerImageSize;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "bannerTextSize")
    private String bannerTextSize;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "costPerDay")
    private BigDecimal costPerDay;
    @Basic(optional = false)
    @NotNull
    @Column(name = "weekDiscount")
    private int weekDiscount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monthDiscount")
    private int monthDiscount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "yearDiscount")
    private int yearDiscount;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "advertisementPackage")
    private AdvertisementSubscriptionDetail advertisementSubscriptionDetail;

    public AdvertisementPackage() {
    }

    public AdvertisementPackage(String packageId) {
        this.packageId = packageId;
    }

    public AdvertisementPackage(String packageId, String bannerDimension, String bannerImageSize, String bannerTextSize, BigDecimal costPerDay, int weekDiscount, int monthDiscount, int yearDiscount) {
        this.packageId = packageId;
        this.bannerDimension = bannerDimension;
        this.bannerImageSize = bannerImageSize;
        this.bannerTextSize = bannerTextSize;
        this.costPerDay = costPerDay;
        this.weekDiscount = weekDiscount;
        this.monthDiscount = monthDiscount;
        this.yearDiscount = yearDiscount;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getBannerDimension() {
        return bannerDimension;
    }

    public void setBannerDimension(String bannerDimension) {
        this.bannerDimension = bannerDimension;
    }

    public String getBannerImageSize() {
        return bannerImageSize;
    }

    public void setBannerImageSize(String bannerImageSize) {
        this.bannerImageSize = bannerImageSize;
    }

    public String getBannerTextSize() {
        return bannerTextSize;
    }

    public void setBannerTextSize(String bannerTextSize) {
        this.bannerTextSize = bannerTextSize;
    }

    public BigDecimal getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(BigDecimal costPerDay) {
        this.costPerDay = costPerDay;
    }

    public int getWeekDiscount() {
        return weekDiscount;
    }

    public void setWeekDiscount(int weekDiscount) {
        this.weekDiscount = weekDiscount;
    }

    public int getMonthDiscount() {
        return monthDiscount;
    }

    public void setMonthDiscount(int monthDiscount) {
        this.monthDiscount = monthDiscount;
    }

    public int getYearDiscount() {
        return yearDiscount;
    }

    public void setYearDiscount(int yearDiscount) {
        this.yearDiscount = yearDiscount;
    }

    public AdvertisementSubscriptionDetail getAdvertisementSubscriptionDetail() {
        return advertisementSubscriptionDetail;
    }

    public void setAdvertisementSubscriptionDetail(AdvertisementSubscriptionDetail advertisementSubscriptionDetail) {
        this.advertisementSubscriptionDetail = advertisementSubscriptionDetail;
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
        if (!(object instanceof AdvertisementPackage)) {
            return false;
        }
        AdvertisementPackage other = (AdvertisementPackage) object;
        if ((this.packageId == null && other.packageId != null) || (this.packageId != null && !this.packageId.equals(other.packageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.AdvertisementPackage[ packageId=" + packageId + " ]";
    }
    
}
