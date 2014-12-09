/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.MembercategorymasterFacade;
import beans.MemberdetailFacade;
import entity.Buyermemberdetail;
import entity.Membercategorymaster;

import entity.Memberdetail;
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
    private MembercategorymasterFacade memberCategoryMasterFacade;
    
    @EJB
    MemberdetailFacade memberDetailFacade;
    
    private String memberCategry="cat01";
    private Memberdetail member=new Memberdetail();
    private Buyermemberdetail buyerMemberDetail=new Buyermemberdetail();
    
    @PostConstruct
    public void init(){
        
    
    }

    public String getMemberCategry() {
        return memberCategry;
    }

    public void setMemberCategry(String memberCategry) {
        this.memberCategry = memberCategry;
    }
    
    public Memberdetail getMember() {
        return member;
    }

    public void setMember(Memberdetail member) {
        this.member = member;
    }

    public Buyermemberdetail getBuyerMemberDetail() {
        return buyerMemberDetail;
    }

    public void setBuyerMemberDetail(Buyermemberdetail buyerMemberDetail) {
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
        
      // member.setPassword(encryptPassword(member.getPassword()));
        member.setBuyermemberdetail(buyerMemberDetail);
        member.setMemberid("agent"+(memberDetailFacade.count()+1));
        member.setMembercategoryid(memberCategoryMasterFacade.find(memberCategry));
        memberDetailFacade.create(member);
        
    }

    
    
    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public MemberManagedBean() {
        
    }
    
}
