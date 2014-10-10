package autenticacion.jsf.controller;


import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.protask.excepciones.RegistrosNoEncontradosException;
import info.pedrodonte.sg.ejb.UserEJB;
import info.pedrodonte.sg.vo.VoUser;
import info.pedrodonte.util.JsfUtil;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import autenticacion.ejb.LoginValidaCredencialEJB;
import autenticacion.excepciones.ValidacionNegativaException;
import autenticacion.vo.CredencialSeguridad;

@Named
@SessionScoped
public class SessionMBean implements Serializable { // controller ,
																	// vo
	private static final long serialVersionUID = 123456789L;
	private Logger logger = Logger.getLogger(getClass());
	private JsfUtil mensajesMB = new JsfUtil();
	private CredencialSeguridad credencial = new CredencialSeguridad("", "");
	
	//Datos relevantes de la session
	private VoUser usuarioLogeado = new VoUser();

	@Inject
	private LoginValidaCredencialEJB credencialEJB;
	
	@Inject
	private UserEJB userEjb;
	
	public void doLogin(ActionEvent event) {
		try {
			credencialEJB.validaCredencial(credencial);
			mensajesMB.msgInfo(":)");
			setUsuarioLogeado(userEjb.obtenerRegistroPorIdentificador(credencial.getUsername()));
		} catch (ValidacionNegativaException e) {
			logger.error(e.getLocalizedMessage());
			mensajesMB.msgInfo(e.getMessage());
		} catch (ErrorDelSistemaException e) {
			mensajesMB.msgInfo("Error Del Sistema Exception");
		} catch (RegistrosNoEncontradosException e) {
			e.printStackTrace();
		}
		
	}

	public CredencialSeguridad getCredencial() {
		return credencial;
	}

	public void setCredencial(CredencialSeguridad credencial) {
		this.credencial = credencial;
	}

	public VoUser getUsuarioLogeado() {
		return usuarioLogeado;
	}

	public void setUsuarioLogeado(VoUser usuarioLogeado) {
		this.usuarioLogeado = usuarioLogeado;
	}


}
