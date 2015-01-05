/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helperbeans;

import beans.MemberDetailFacade;
import beans.PropertyDetailsFacade;
import entity.MemberDetail;
import entity.PropertyDetails;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;

/**
 *
 * @author Tomas
 */
@Stateless
@LocalBean
public class MailSenderBean {
    
    private final static String PROPERTY_DETAILS_URL="http://localhost:39982/RealEstateMS-war/faces/propertyDetails.xhtml";

    @EJB
    private PropertyDetailsFacade propertyDetailsFacade;

    @EJB
    private MemberDetailFacade memberDetailFacade;

    @Resource(name = "mail/xvalt10")
    private Session mailSession;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void sendMail(String email, String subject, String body) throws NamingException, MessagingException {
        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject(subject);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
        message.setContent(body, "text/html");
        
        Transport.send(message);
    }

    @Schedule(dayOfWeek = "7", hour = "19", minute = "23", persistent = false)
    public void scheduledMail() {
        try {
            sendMail("xvalt10@gmail.com", "Realestate Management System - Notification", "Scheduled mail-17:50");
        } catch (NamingException | MessagingException ex) {
            Logger.getLogger(MailSenderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Schedule(dayOfWeek = "7", hour = "16", minute = "17", persistent = false)
    public void sendNewsletterToBuyers() {
        StringBuilder messageText = new StringBuilder("<h4>Realestate Management Newsletter\n\nList of realestate meeting your search criteria:</h4>");
        try {
            List<MemberDetail> listOfBuyers = memberDetailFacade.findAllBuyers();
            for (MemberDetail buyer : listOfBuyers) {
                List<PropertyDetails> propertySearchResults = propertyDetailsFacade.countPropertySearchResults(null, buyer.getBuyerMemberDetail().getLocationId().getLocality(), BigDecimal.ZERO, buyer.getBuyerMemberDetail().getPropertyBudget(), 0, buyer.getBuyerMemberDetail().getPropertyArea(), null);
                for (PropertyDetails property : propertySearchResults) {
                    messageText.append("<p>").append(property.getPropertyTitle()).append("</p>");
                    messageText.append("<p>Price:").append(property.getLumpsumCost()).append("</p>");
                    messageText.append("<p>Area:").append(property.getArea()).append("</p>");
                    messageText.append("<a href=\"").append(PROPERTY_DETAILS_URL).append("?propertyId=").append(property.getPropertyId()).append("\">").append("View realestate details").append("</a>\n");
                    messageText.append("<br></br>");
                    
                }
                sendMail(buyer.getEmaiIId(), "Realestate Management System - Newsletter", messageText.toString());
                messageText.setLength(0);
            }
            
        } catch (NamingException | MessagingException ex) {
            Logger.getLogger(MailSenderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
