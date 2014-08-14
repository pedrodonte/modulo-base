package info.pedrodonte.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

/**
 * @author Pedro
 * 
 */
public class JsfUtil {

	public void msgInfo(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", msg));
	}

	public void msgWarn(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "ALERTA", msg));
	}

	public void msgError(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", msg));
	}

	public void msgFatal(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "FATAL", msg));
	}

	public void devolverParametro(String nombreParametro, Object valorParamtro) {
		RequestContext.getCurrentInstance().addCallbackParam(nombreParametro,
				valorParamtro);
	}

	/**
	 * Seleccion inicial, por ejemplo el mensaje "Seleccione un Item" donde el
	 * item seleccionado es nulo pero si tiene label
	 * 
	 * @param component
	 * @param value
	 * @return
	 */
	public static boolean isDummySelectItem(UIComponent component, String value) {
		for (UIComponent children : component.getChildren()) {
			if (children instanceof UISelectItem) {
				UISelectItem item = (UISelectItem) children;
				if (item.getItemValue() == null
						&& item.getItemLabel().equals(value)) {
					return true;
				}
				break;
			}
		}
		return false;
	}

}
