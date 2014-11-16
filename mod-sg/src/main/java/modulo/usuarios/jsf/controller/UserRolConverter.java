
package modulo.usuarios.jsf.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import util.JsfUtil;
import modulo.usuarios.ejb.UserRolEJB;
import modulo.usuarios.vo.VoUserRol;

@Named
public class UserRolConverter implements Converter {
	
	@Inject
	UserRolEJB serviceEJB;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value == null || value.length() == 0
				|| JsfUtil.isDummySelectItem(component, value)) {
			return null;
		}

		try {
			return serviceEJB.obtenerRegistroPorID(getKey(value));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object == null
				|| (object instanceof String && ((String) object).length() == 0)) {
			return null;
		}

		if (object instanceof VoUserRol) {
			VoUserRol o = (VoUserRol) object;
			return getStringKey((o.getUsuarioRolId() == 0) ? -1 : o
					.getUsuarioRolId());
		} else {
			Logger.getLogger(this.getClass().getName()).log(
					Level.SEVERE,
					"object "+object+" is of type "+object.getClass().getName()+"; expected type: "+VoUserRol.class.getName()+"");
			return null;
		}
	}
	
	Integer getKey(String value) {
		Integer key;
		key = Integer.parseInt(value);
		return key;
	}

	String getStringKey(long l) {
		StringBuffer sb = new StringBuffer();
		sb.append(l);
		return sb.toString();
	}

}


