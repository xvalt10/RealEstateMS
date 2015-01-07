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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tomas
 */
@Entity
@Table(name = "PropertyImages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PropertyImages.findAll", query = "SELECT p FROM PropertyImages p"),
    @NamedQuery(name = "PropertyImages.findByImageId", query = "SELECT p FROM PropertyImages p WHERE p.imageId = :imageId")})
public class PropertyImages implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "propertyImage")
    private byte[] propertyImage;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "imageId")
    private Integer imageId;
    @JoinColumn(name = "propertyId", referencedColumnName = "propertyId")
    @ManyToOne(optional = false)
    private PropertyDetails propertyId;

    public PropertyImages() {
    }

    public PropertyImages(Integer imageId) {
        this.imageId = imageId;
    }

    public PropertyImages(Integer imageId, byte[] propertyImage) {
        this.imageId = imageId;
        this.propertyImage = propertyImage;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }


    public PropertyDetails getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(PropertyDetails propertyId) {
        this.propertyId = propertyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imageId != null ? imageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PropertyImages)) {
            return false;
        }
        PropertyImages other = (PropertyImages) object;
        if ((this.imageId == null && other.imageId != null) || (this.imageId != null && !this.imageId.equals(other.imageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PropertyImages[ imageId=" + imageId + " ]";
    }

    public byte[] getPropertyImage() {
        return propertyImage;
    }

    public void setPropertyImage(byte[] propertyImage) {
        this.propertyImage = propertyImage;
    }
    
}
