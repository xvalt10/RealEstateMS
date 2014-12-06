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
@Table(name = "MemberCategoryMaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MemberCategoryMaster.findAll", query = "SELECT m FROM MemberCategoryMaster m"),
    @NamedQuery(name = "MemberCategoryMaster.findByMemberCategoryId", query = "SELECT m FROM MemberCategoryMaster m WHERE m.memberCategoryId = :memberCategoryId"),
    @NamedQuery(name = "MemberCategoryMaster.findByMemberCategoryName", query = "SELECT m FROM MemberCategoryMaster m WHERE m.memberCategoryName = :memberCategoryName"),
    @NamedQuery(name = "MemberCategoryMaster.findByMemberCategoryDescription", query = "SELECT m FROM MemberCategoryMaster m WHERE m.memberCategoryDescription = :memberCategoryDescription")})
public class MemberCategoryMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "memberCategoryId")
    private String memberCategoryId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "memberCategoryName")
    private String memberCategoryName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "memberCategoryDescription")
    private String memberCategoryDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberCategoryId")
    private Collection<MemberDetail> memberDetailCollection;

    public MemberCategoryMaster() {
    }

    public MemberCategoryMaster(String memberCategoryId) {
        this.memberCategoryId = memberCategoryId;
    }

    public MemberCategoryMaster(String memberCategoryId, String memberCategoryName, String memberCategoryDescription) {
        this.memberCategoryId = memberCategoryId;
        this.memberCategoryName = memberCategoryName;
        this.memberCategoryDescription = memberCategoryDescription;
    }

    public String getMemberCategoryId() {
        return memberCategoryId;
    }

    public void setMemberCategoryId(String memberCategoryId) {
        this.memberCategoryId = memberCategoryId;
    }

    public String getMemberCategoryName() {
        return memberCategoryName;
    }

    public void setMemberCategoryName(String memberCategoryName) {
        this.memberCategoryName = memberCategoryName;
    }

    public String getMemberCategoryDescription() {
        return memberCategoryDescription;
    }

    public void setMemberCategoryDescription(String memberCategoryDescription) {
        this.memberCategoryDescription = memberCategoryDescription;
    }

    @XmlTransient
    public Collection<MemberDetail> getMemberDetailCollection() {
        return memberDetailCollection;
    }

    public void setMemberDetailCollection(Collection<MemberDetail> memberDetailCollection) {
        this.memberDetailCollection = memberDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberCategoryId != null ? memberCategoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MemberCategoryMaster)) {
            return false;
        }
        MemberCategoryMaster other = (MemberCategoryMaster) object;
        if ((this.memberCategoryId == null && other.memberCategoryId != null) || (this.memberCategoryId != null && !this.memberCategoryId.equals(other.memberCategoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.MemberCategoryMaster[ memberCategoryId=" + memberCategoryId + " ]";
    }
    
}
