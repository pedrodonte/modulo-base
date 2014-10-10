package autenticacion.jsf.controller;

import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.protask.excepciones.RegistrosNoEncontradosException;
import info.pedrodonte.sg.ejb.UserEJB;
import info.pedrodonte.sg.vo.VoUser;
import info.pedrodonte.util.JsfUtil;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

	// Datos relevantes de la session
	private VoUser usuarioLogeado = new VoUser();

	@Inject
	private LoginValidaCredencialEJB credencialEJB;

	@Inject
	private UserEJB userEjb;

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		logger.info(session.getId());
	}

	public void doLogin(ActionEvent event) {
		try {
			credencialEJB.validaCredencial(credencial);
			mensajesMB.msgInfo(":)");
			setUsuarioLogeado(userEjb
					.obtenerRegistroPorIdentificador(credencial.getUsername()));
		} catch (ValidacionNegativaException e) {
			logger.error(e.getLocalizedMessage());
			mensajesMB.msgInfo(e.getMessage());
		} catch (ErrorDelSistemaException e) {
			mensajesMB.msgInfo("Error Del Sistema Exception");
		} catch (RegistrosNoEncontradosException e) {
			e.printStackTrace();
		}

	}

	private void iniciarLoginJaas() throws ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		httpServletRequest.login(credencial.getUsername(),
				credencial.getPassword());
	}

	private void redireccionarAutorizado() {
		logger.info("redireccionando a dashboard...");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		NavigationHandler nh = facesContext.getApplication()
				.getNavigationHandler();
		nh.handleNavigation(facesContext, null,
				"/pages/ver_consultas?faces-redirect=true");
	}

	public void doLogout(ActionEvent actionEvent) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		try {
			logger.info("cierra session.");
			httpServletRequest.getSession().invalidate();
			httpServletRequest.logout();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	private String obtenerIpConexion() {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String ip = httpServletRequest.getRemoteAddr();
		return ip;
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
