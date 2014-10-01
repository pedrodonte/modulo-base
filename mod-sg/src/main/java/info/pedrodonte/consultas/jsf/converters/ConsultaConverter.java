package info.pedrodonte.consultas.jsf.converters;

import info.pedrodonte.base.ejb.PersonaEJB;
import info.pedrodonte.consultas.vo.VoConsulta;
import info.pedrodonte.util.JsfUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ConsultaConverter implements Converter {
	
	@Inject
	PersonaEJB serviceEJB;
	
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

		if (object instanceof VoConsulta) {
			VoConsulta o = (VoConsulta) object;
			return getStringKey((o.getConsultaId() == 0) ? -1 : o
					.getConsultaId());
		} else {
			Logger.getLogger(this.getClass().getName()).log(
					Level.SEVERE,
					"object "+object+" is of type "+object.getClass().getName()+"; expected type: "+VoConsulta.class.getName()+"");
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


