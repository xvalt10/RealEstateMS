/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.AgentMemberDetailFacade;
import beans.BuyerMemberDetailFacade;
import beans.MemberCategoryMasterFacade;
import beans.MemberDetailFacade;
import beans.PropertyLocationMasterFacade;
import entity.AgentMemberDetail;
import entity.BuyerMemberDetail;
import entity.MemberCategoryMaster;

import entity.MemberDetail;
import entity.PropertyLocationMaster;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 *
 * @author Tomas
 */
@ManagedBean
@SessionScoped
public class MemberManagedBean {

    @EJB
    private PropertyLocationMasterFacade propertyLocationMasterFacade;
    @EJB
    private AgentMemberDetailFacade agentMemberDetailFacade;
    @EJB
    private BuyerMemberDetailFacade buyerMemberDetailFacade;
    @EJB
    private MemberCategoryMasterFacade memberCategoryMasterFacade;
    @EJB
    MemberDetailFacade memberDetailFacade;

    private int memberCategory;
    private MemberDetail member;
    private AgentMemberDetail agentMemberDetail;
    private BuyerMemberDetail buyerMemberDetail;
    private PropertyLocationMaster propertyLocation;

    @PostConstruct
    public void init() {
        memberCategory = 1;
        member = new MemberDetail();
        buyerMemberDetail = new BuyerMemberDetail();
        propertyLocation = new PropertyLocationMaster();
        agentMemberDetail = new AgentMemberDetail();
    }

    public int getMemberCategory() {
        return memberCategory;
    }

    public void setMemberCategory(int memberCategory) {
        this.memberCategory = memberCategory;
    }

    public MemberDetail getMember() {
        return member;
    }

    public void setMember(MemberDetail member) {
        this.member = member;
    }

    public BuyerMemberDetail getBuyerMemberDetail() {
        return buyerMemberDetail;
    }

    public void setBuyerMemberDetail(BuyerMemberDetail buyerMemberDetail) {
        this.buyerMemberDetail = buyerMemberDetail;
    }

    public PropertyLocationMaster getPropertyLocation() {
        return propertyLocation;
    }

    public void setPropertyLocation(PropertyLocationMaster propertyLocation) {
        this.propertyLocation = propertyLocation;
    }

    public AgentMemberDetail getAgentMemberDetail() {
        return agentMemberDetail;
    }

    public void setAgentMemberDetail(AgentMemberDetail agentMemberDetail) {
        this.agentMemberDetail = agentMemberDetail;
    }

    public String encryptPassword(String password) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            return new sun.misc.BASE64Encoder().encode(md.digest());
        } catch (NoSuchAlgorithmException e) {
            //_log.error("Failed to encrypt password.", e);
        }
        return "";
    }

    public String registerMember() {
        // member.setPassword(encryptPassword(member.getPassword()));
        member.setBuyerMemberDetail(buyerMemberDetail);
        String memberid = "member" + (memberDetailFacade.count() + 1);
        member.setMemberId(memberid);
        member.setMemberCategoryId(memberCategoryMasterFacade.find(String.valueOf(memberCategory)));

        if (memberCategory == 1) {
            String locationId = String.valueOf(propertyLocationMasterFacade.count() + 1);
            propertyLocation.setLocationId(locationId);
            buyerMemberDetail.setMemberId(memberid);
            buyerMemberDetail.setLocationId(propertyLocation);
            member.setBuyerMemberDetail(buyerMemberDetail);
            propertyLocationMasterFacade.create(propertyLocation);
           
        }
        
        memberDetailFacade.create(member);
        return "success";

    }

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public MemberManagedBean() {

    }

}
