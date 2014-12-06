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
@Table(name = "AdministratorLogin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdministratorLogin.findAll", query = "SELECT a FROM AdministratorLogin a"),
    @NamedQuery(name = "AdministratorLogin.findByUserId", query = "SELECT a FROM AdministratorLogin a WHERE a.userId = :userId"),
    @NamedQuery(name = "AdministratorLogin.findByPassword", query = "SELECT a FROM AdministratorLogin a WHERE a.password = :password")})
public class AdministratorLogin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "userId")
    private String userId;
    @Size(max = 50)
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "approverId")
    private Collection<PropertyApproval> propertyApprovalCollection;

    public AdministratorLogin() {
    }

    public AdministratorLogin(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<PropertyApproval> getPropertyApprovalCollection() {
        return propertyApprovalCollection;
    }

    public void setPropertyApprovalCollection(Collection<PropertyApproval> propertyApprovalCollection) {
        this.propertyApprovalCollection = propertyApprovalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdministratorLogin)) {
            return false;
        }
        AdministratorLogin other = (AdministratorLogin) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.AdministratorLogin[ userId=" + userId + " ]";
    }
    
}
