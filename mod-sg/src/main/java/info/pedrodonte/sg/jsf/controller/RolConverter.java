
package info.pedrodonte.sg.jsf.controller;

import info.pedrodonte.util.JsfUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import info.pedrodonte.sg.vo.VoRol;
import info.pedrodonte.sg.ejb.RolEJB;

@Named
public class RolConverter implements Converter {
	
	@Inject
	RolEJB serviceEJB;
	
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

		if (object instanceof VoRol) {
			VoRol o = (VoRol) object;
			return getStringKey((o.getRolId() == 0) ? -1 : o
					.getRolId());
		} else {
			Logger.getLogger(this.getClass().getName()).log(
					Level.SEVERE,
					"object "+object+" is of type "+object.getClass().getName()+"; expected type: "+VoRol.class.getName()+"");
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


