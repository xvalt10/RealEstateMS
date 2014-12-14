/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.PropertyCategoryMasterFacade;
import entity.PropertyCategoryMaster;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Tomas
 */
@ManagedBean
@FacesConverter(value="propertyCategoryConverter")
public class PropertyCategoryConverter  implements Converter{

    @EJB
    PropertyCategoryMasterFacade propertyCategoryFacade;
    /**
     * Creates a new instance of PropertyCategoryConverter
     */
    public PropertyCategoryConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return propertyCategoryFacade.find(value); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((PropertyCategoryMaster) value).toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
