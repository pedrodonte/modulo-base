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

	}

	public void doLogin(ActionEvent event) {
		try {
			credencialEJB.validaCredencial(credencial);
			mensajesMB.msgInfo(":)");
			setUsuarioLogeado(userEjb
					.obtenerRegistroPorIdentificador(credencial.getUsername()));

			iniciarLoginJaas();
			redireccionarAutorizado();

		} catch (ValidacionNegativaException e) {
			logger.error(e.getLocalizedMessage());
			mensajesMB.msgInfo(e.getMessage());
		} catch (ErrorDelSistemaException e) {
			mensajesMB.msgInfo(e.getLocalizedMessage());
		} catch (RegistrosNoEncontradosException e) {
			mensajesMB.msgInfo(e.getLocalizedMessage());
		} catch (ServletException e) {
			mensajesMB.msgInfo(e.getLocalizedMessage());
			e.printStackTrace();
		}

	}

	/**
	 * Este metodo interactua directamente con el servidor de aplicaciones en
	 * donde intenta crear una instancia de Principal con los datos de la
	 * credencial, considerar que la clave debe tener la misma encriptación que
	 * tiene la base de datos de usuarios.
	 * 
	 * @throws ServletException
	 * @throws ErrorDelSistemaException
	 */
	private void iniciarLoginJaas() throws ServletException,
			ErrorDelSistemaException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		httpServletRequest.login(credencial.getUsername(),
				credencial.getPasswordEncripted());
	}

	private void redireccionarAutorizado() {
		logger.info("redireccionando a dashboard...");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		NavigationHandler nh = facesContext.getApplication()
				.getNavigationHandler();
		nh.handleNavigation(facesContext, null,
				"/pages/ver_consultas?faces-redirect=true");
	}

	private void redireccionarFormularioLogin() {
		logger.info("redireccionando a dashboard...");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		NavigationHandler nh = facesContext.getApplication()
				.getNavigationHandler();
		nh.handleNavigation(facesContext, null,
				"/public/login?faces-redirect=true");
	}

	public void doLogout(ActionEvent actionEvent) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		try {
			logger.info("cierra session.");
			httpServletRequest.getSession().invalidate();
			httpServletRequest.logout();
			redireccionarFormularioLogin();
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

	@Override
	public String toString() {
		return "SessionMBean [usuarioLogeado=" + usuarioLogeado + "]";
	}

}
