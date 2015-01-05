/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import helperbeans.MailSenderBean;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.MessagingException;
import javax.naming.NamingException;

/**
 *
 * @author Tomas
 */
@ManagedBean
@RequestScoped
public class MailManagedBean {
    @EJB
    private MailSenderBean mailSenderBean;
    
    private String name;
    private String email;
    private String messageBody;
    private String telephoneNumber;

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
    
    
    
    /**
     * Creates a new instance of MailManagedBean
     */
    public MailManagedBean() {
    }
    
   
    
    
    
    public void contactAgent(String agentEmail){
        StringBuilder message=new StringBuilder();
        message.append("You have received a contact request from ");
        message.append(name).append(".\n\n");
        message.append("Contact details - email:").append(email).append(" telephone: ").append(telephoneNumber).append("\n\n");
        message.append("Message text:").append(messageBody);
        
        try {
            mailSenderBean.sendMail(agentEmail, "Realestate Management System - Notification", message.toString());
        } catch (NamingException | MessagingException ex) {
            
            Logger.getLogger(MailManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
