package modulo.acr_certificados.jsf.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;

import util.JsfUtil;

@Named
@SessionScoped
public class AfiliacionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cpoRut = "";
	private String cpoUrl = "";

	JsfUtil jsfUtil = new JsfUtil();

	public String getCpoRut() {
		return cpoRut;
	}

	public void setCpoRut(String cpoRut) {
		this.cpoRut = cpoRut;
	}

	public void actionGenerar(ActionEvent event) {

		String realPath = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestContextPath();

		if (!validaRut()) {
			jsfUtil.msgWarn("Error en la validación del RUT");
			cpoUrl = realPath + "/statics/error-generico.xhtml";
		} else {

			cpoUrl = "http://90.0.0.150:8080/certificados/CertificadoAfiliacion?RUTDV="
					+ cpoRut;
			RequestContext.getCurrentInstance().update(
					"@(.registro-formulario)");
		}
	}

	public String getCpoUrl() {
		return cpoUrl;
	}

	public void setCpoUrl(String cpoUrl) {
		this.cpoUrl = cpoUrl;
	}

	public boolean validaRut() {
		boolean validacion = false;
		try {
			String rut = new String(cpoRut);
			rut = rut.toUpperCase();
			rut = rut.replace(".", "");
			rut = rut.replace("-", "");
			int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

			char dv = rut.charAt(rut.length() - 1);

			int m = 0, s = 1;
			for (; rutAux != 0; rutAux /= 10) {
				s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
			}
			if (dv == (char) (s != 0 ? s + 47 : 75)) {
				validacion = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return validacion;
	}

}
