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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import pagination.JsfUtil;

/**
 *
 * @author Tomas
 */
@ManagedBean
@SessionScoped
public class MemberManagedBean {

    final private static int BUYER = 1;
    final private static int SELLER = 2;
    final private static int AGENT = 3;
    final private static int ADMIN = 4;

    final private static String MEMBER_PREFIX = "member";

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
    private String agentCategory;
    private MemberDetail member;
    private AgentMemberDetail agentMemberDetail;
    private BuyerMemberDetail buyerMemberDetail;
    private PropertyLocationMaster propertyLocation;
    private String confirmationPassword;

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public MemberManagedBean() {

    }

    @PostConstruct
    public void init() {
        memberCategory = 1;
        agentCategory = "domestic";
        member = new MemberDetail();
        buyerMemberDetail = new BuyerMemberDetail();
        propertyLocation = new PropertyLocationMaster();
        agentMemberDetail = new AgentMemberDetail();
    }

    public String getAgentCategory() {
        return agentCategory;
    }

    public void setAgentCategory(String agentCategory) {
        this.agentCategory = agentCategory;
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

        MessageDigest md;
        StringBuilder sb = new StringBuilder();
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

        //convert the byte to hex format method 1
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MemberManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sb.toString();
    }
    
    public String clearMemberDetails(){
        memberCategory = 1;
        agentCategory = "domestic";
        member = new MemberDetail();
        buyerMemberDetail = new BuyerMemberDetail();
        propertyLocation = new PropertyLocationMaster();
        agentMemberDetail = new AgentMemberDetail();
        confirmationPassword="";
        
        return "registration";
    }
    
    public String getUserRole(String username){
        if(memberDetailFacade.getMemberidByUsername(username)==null){
       return "no user found.";
       }
        else{
            String memberId=memberDetailFacade.getMemberidByUsername(username).getMemberId();
       return memberDetailFacade.find(memberId).getMemberCategoryId().getMemberCategoryName();}
    }

    public String loadMemberDetails(String username, String redirectOutcome) {
        member = memberDetailFacade.getMemberidByUsername(username);
        memberCategory = Integer.parseInt(member.getMemberCategoryId().getMemberCategoryId());
        if (memberCategory == BUYER) {
            propertyLocation = propertyLocationMasterFacade.find(member.getBuyerMemberDetail().getLocationId().getLocationId());

        } else if (memberCategory == AGENT) {
            propertyLocation = propertyLocationMasterFacade.find(member.getAgentMemberDetail().getLocationId().getLocationId());
        }
        
        return redirectOutcome;

    }

    public String updatePropertySearchCriteria(String username) {

        propertyLocation.setLocationId(memberDetailFacade.getMemberidByUsername(username).getBuyerMemberDetail().getLocationId().getLocationId());
        buyerMemberDetail.setMemberId(memberDetailFacade.getMemberidByUsername(username).getMemberId());
        buyerMemberDetail.setLocationId(propertyLocation);
        
        
        propertyLocationMasterFacade.edit(propertyLocation);
        memberDetailFacade.edit(member);

        return "success";
    }

    public String updateMemberDetails(String username) {
        member.setMemberId(memberDetailFacade.getMemberidByUsername(username).getMemberId());
        member.setPassword(encryptPassword(member.getPassword()));
        member.setMemberCategoryId(memberCategoryMasterFacade.find(String.valueOf(memberCategory)));

        if (memberCategory == BUYER || memberCategory == AGENT) {
            propertyLocation.setLocationId(memberDetailFacade.getMemberidByUsername(username).getBuyerMemberDetail().getLocationId().getLocationId());
        }
        if (memberCategory == BUYER) {
//            buyerMemberDetail.setMemberId(memberDetailFacade.getMemberidByUsername(username).getMemberId());
//            buyerMemberDetail.setLocationId(propertyLocation);
//            member.setBuyerMemberDetail(buyerMemberDetail);
            propertyLocationMasterFacade.edit(propertyLocation);
        }

        if (memberCategory == AGENT) {
            agentMemberDetail.setMemberId(memberDetailFacade.getMemberidByUsername(username).getMemberId());
            agentMemberDetail.setAgentType(agentCategory);
            agentMemberDetail.setLocationId(propertyLocation);
            member.setAgentMemberDetail(agentMemberDetail);
            propertyLocationMasterFacade.edit(propertyLocation);
        }

        memberDetailFacade.edit(member);
        return "success";

    }

    public String registerMember() {

        member.setPassword(encryptPassword(member.getPassword()));

        String memberid = MEMBER_PREFIX + (memberDetailFacade.getNewId());
        member.setMemberId(memberid);
        member.setMemberCategoryId(memberCategoryMasterFacade.find(String.valueOf(memberCategory)));
        String locationId = String.valueOf(propertyLocationMasterFacade.count() + 1);

        propertyLocation.setLocationId(locationId);
        if (memberCategory == BUYER) {
            buyerMemberDetail.setMemberId(memberid);
            buyerMemberDetail.setLocationId(propertyLocation);
            member.setBuyerMemberDetail(buyerMemberDetail);
            propertyLocationMasterFacade.create(propertyLocation);

        }

        if (memberCategory == AGENT) {
            agentMemberDetail.setMemberId(memberid);
            agentMemberDetail.setAgentType(agentCategory);
            agentMemberDetail.setLocationId(propertyLocation);
            member.setAgentMemberDetail(agentMemberDetail);

            propertyLocationMasterFacade.create(propertyLocation);

        }

        memberDetailFacade.create(member);
        JsfUtil.addSuccessMessage("User " + member.getUsername()+ "has been successfully registered. You can now sign in to enter the member section.");
        return null;

    }
    
    public void isUserNameUnique(AjaxBehaviorEvent event){
        if (memberDetailFacade.countMemberByUsername(member.getUsername())>0){
            JsfUtil.addErrorMessage("Username already exists. Choose a different one.");
        }
        else{
        JsfUtil.addSuccessMessage("You have picked a unique username. Proceed with the registration.");
        }
    
    }

    public String signOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";

    }

}
