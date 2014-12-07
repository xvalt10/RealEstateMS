/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.AdministratorLoginFacade;
import beans.PropertyDetailsFacade;
import entity.AdministratorLogin;
import entity.PropertyDetails;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Tomas
 */
@ManagedBean
@RequestScoped
public class PropertyManagedBean {
    @EJB
    private AdministratorLoginFacade administratorLoginFacade;
    
    
    public List<AdministratorLogin> getAllPropertyDetails(){
return administratorLoginFacade.findAll();
}
    
    

    /**
     * Creates a new instance of PropertyManagedBean
     */
    public PropertyManagedBean() {
    }
    
}
