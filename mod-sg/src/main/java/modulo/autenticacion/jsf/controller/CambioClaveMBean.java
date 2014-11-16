package modulo.autenticacion.jsf.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.usuarios.ejb.UserEJB;
import modulo.usuarios.vo.VoUser;

import org.apache.log4j.Logger;

import util.JsfUtil;

@Named
@SessionScoped
public class CambioClaveMBean implements Serializable {

	private static final long serialVersionUID = 123456789L;
	private Logger logger = Logger.getLogger(getClass());
	private JsfUtil mensajesMB = new JsfUtil();

	@Inject
	SessionMBean sessionMBean;

	@Inject
	private UserEJB userEjb;

	private VoUser usuarioLogeado = new VoUser();
	private String cpoActual = "";
	private String cpoContrasena = "";
	private String cpoConfirmacion = "";

	@PostConstruct
	public void init() {

	}

	public void doCambioClave(ActionEvent actionEvent) {
		try {
			usuarioLogeado = sessionMBean.getUsuarioLogeado();
			userEjb.cambiarContrasena(usuarioLogeado, cpoContrasena,
					cpoConfirmacion);
			redireccionar();
		} catch (RegistrosNoEncontradosException e) {
			e.printStackTrace();
			mensajesMB.msgError("Error en el proceso de Cambio.");
		}
	}

	private void redireccionar() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		NavigationHandler nh = facesContext.getApplication()
				.getNavigationHandler();
		nh.handleNavigation(facesContext, null,
				"/pages/ver_consultas?faces-redirect=true");
	}
	
	public String getCpoActual() {
		return cpoActual;
	}

	public void setCpoActual(String cpoActual) {
		this.cpoActual = cpoActual;
	}

	public String getCpoContrasena() {
		return cpoContrasena;
	}

	public void setCpoContrasena(String cpoContrasena) {
		this.cpoContrasena = cpoContrasena;
	}

	public String getCpoConfirmacion() {
		return cpoConfirmacion;
	}

	public void setCpoConfirmacion(String cpoConfirmacion) {
		this.cpoConfirmacion = cpoConfirmacion;
	}
	
}
