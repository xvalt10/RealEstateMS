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
    
    private int memberCategry;

    public int getMemberCategry() {
        return memberCategry;
    }

    public void setMemberCategry(int memberCategry) {
        this.memberCategry = memberCategry;
    }
    
    
    
    
    
    
    
    private MemberDetail member=new MemberDetail();
    private BuyerMemberDetail buyerMemberDetail=new BuyerMemberDetail();

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
    
    
    @PostConstruct
    public void init(){
        
    member.setMemberCategoryId((MemberCategoryMaster)memberCategoryMasterFacade.find("1"));
    }
    

    public void changeCategory(){
    
    }
    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public MemberManagedBean() {
        
    }
    
}
