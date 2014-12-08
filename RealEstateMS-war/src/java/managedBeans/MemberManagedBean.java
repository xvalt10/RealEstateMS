/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.MemberCategoryMasterFacade;
import beans.MemberDetailFacade;
import entity.BuyerMemberDetail;
import entity.MemberCategoryMaster;

import entity.MemberDetail;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Tomas
 */
@ManagedBean
@SessionScoped
public class MemberManagedBean {
    @EJB
    private MemberCategoryMasterFacade memberCategoryMasterFacade;
    
    @EJB
    MemberDetailFacade memberDetailFacade;
    
    private int memberCategry=1;
    private MemberDetail member=new MemberDetail();
    private BuyerMemberDetail buyerMemberDetail=new BuyerMemberDetail();
    
    @PostConstruct
    public void init(){
        
    
    }

    public int getMemberCategry() {
        return memberCategry;
    }

    public void setMemberCategry(int memberCategry) {
        this.memberCategry = memberCategry;
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
    
    public String encryptPassword(String password)   {  
  
    
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(password.getBytes());
      return new sun.misc.BASE64Encoder().encode(md.digest());
    } catch (NoSuchAlgorithmException e) {
      //_log.error("Failed to encrypt password.", e);
    }
    return "";
  }
    

    
    
    
    
    public void registerMember(){
        member.setPassword(encryptPassword(member.getPassword()));
        member.setBuyerMemberDetail(buyerMemberDetail);
        member.setMemberId("agent"+(memberDetailFacade.count()+1));
        member.setMemberCategoryId(memberCategoryMasterFacade.find(String.valueOf(memberCategry)));
        memberDetailFacade.create(member);
    
    }

    
    
    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public MemberManagedBean() {
        
    }
    
}
