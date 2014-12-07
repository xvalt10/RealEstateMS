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
@Table(name = "agentMemberDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AgentMemberDetail.findAll", query = "SELECT a FROM AgentMemberDetail a"),
    @NamedQuery(name = "AgentMemberDetail.findByMemberId", query = "SELECT a FROM AgentMemberDetail a WHERE a.memberId = :memberId"),
    @NamedQuery(name = "AgentMemberDetail.findByAgentType", query = "SELECT a FROM AgentMemberDetail a WHERE a.agentType = :agentType")})
public class AgentMemberDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "memberId")
    private String memberId;
    @Size(max = 50)
    @Column(name = "agentType")
    private String agentType;
    @JoinColumn(name = "memberId", referencedColumnName = "memberId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private MemberDetail memberDetail;
    @JoinColumn(name = "locationId", referencedColumnName = "locationId")
    @ManyToOne
    private PropertyLocationMaster locationId;

    public AgentMemberDetail() {
    }

    public AgentMemberDetail(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
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
        if (!(object instanceof AgentMemberDetail)) {
            return false;
        }
        AgentMemberDetail other = (AgentMemberDetail) object;
        if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AgentMemberDetail[ memberId=" + memberId + " ]";
    }
    
}
