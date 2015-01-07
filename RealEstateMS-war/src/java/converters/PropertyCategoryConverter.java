/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import beans.PropertyCategoryMasterFacade;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



/**
 *
 * @author Tomas
 */
@FacesConverter("propertyCategoryConverter")
public class PropertyCategoryConverter implements Converter{
    @EJB
    private PropertyCategoryMasterFacade propertyCategoryMasterFacade;
    

    /**
     * Creates a new instance of PropertyCategoryConverter
     */
    public PropertyCategoryConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return propertyCategoryMasterFacade.find(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
    
}
