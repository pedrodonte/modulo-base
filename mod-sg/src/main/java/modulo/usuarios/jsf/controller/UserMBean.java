
package modulo.usuarios.jsf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.usuarios.ejb.RolEJB;
import modulo.usuarios.ejb.UserEJB;
import modulo.usuarios.vo.VoRol;
import modulo.usuarios.vo.VoUser;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import util.AbsMantenedorMB;
import util.CrudGenericServiceApi;

@Named
@SessionScoped
public class UserMBean extends AbsMantenedorMB<VoUser> { //controller , vo

	private static final long serialVersionUID = 123456789L;
	
	private DualListModel<VoRol> roles = new DualListModel<>();

	@Inject
	private UserEJB serviceEJB; //EJB
	
	@Inject
	private RolEJB rolEJB;

	public UserMBean() {
		super(VoUser.class);
	}
	
	@Override
	public CrudGenericServiceApi<VoUser> asignarCrudService() {
		return serviceEJB;
	}

	public DualListModel<VoRol> getRoles() {
		return roles;
	}

	public void setRoles(DualListModel<VoRol> roles) {
		this.roles = roles;
	}
	
	public void onTransfer(TransferEvent event) {  
        StringBuilder builder = new StringBuilder();  
        for(Object item : event.getItems()) {  
            builder.append(((VoRol) item).getIdentificador()).append("<br />");  
        }  
          
        FacesMessage msg = new FacesMessage();  
        msg.setSeverity(FacesMessage.SEVERITY_INFO);  
        msg.setSummary("Items Transferred");  
        msg.setDetail(builder.toString());  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }

	@Override
	public void doGuardarRegistroFormulario(ActionEvent event) {
		getRegistroEnEdicion().setRoles(roles.getTarget());
		super.doGuardarRegistroFormulario(event);
	}

	@Override
	public void doNuevoRegistroFormulario(ActionEvent event) {
		super.doNuevoRegistroFormulario(event);
		
		try {
			List<VoRol> rolesNoAsociados = rolEJB.obtenerRegistros();
			List<VoRol> rolesAsociados = new ArrayList<>();
			roles = new DualListModel<>(rolesNoAsociados, rolesAsociados);
		} catch (ErrorDelSistemaException e) {
			e.printStackTrace();
		} catch (RegistrosNoEncontradosException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void doEditarRegistroFormulario(ActionEvent event) {
		super.doEditarRegistroFormulario(event);
		buscarRolesDelRegistro();
	}

	/**
	 * 
	 */
	private void buscarRolesDelRegistro() {
		try {
			List<VoRol> rolesNoAsociados = rolEJB.obtenerNoAsociadosUsuario(getRegistroEnEdicion());
			List<VoRol> rolesAsociados = rolEJB.obtenerAsociadosUsuario(getRegistroEnEdicion());
			roles = new DualListModel<>(rolesNoAsociados, rolesAsociados);
		} catch (ErrorDelSistemaException e) {
			e.printStackTrace();
		}
	} 
	
	
	
	@Override
	public void doHabilitarEdicion(ActionEvent event) {
		super.doHabilitarEdicion(event);
		buscarRolesDelRegistro();
	}

	@Override
	public void doVerRegistroFormulario(ActionEvent event) {
		super.doVerRegistroFormulario(event);
		buscarRolesDelRegistro();
	}

	public void doGenerarContrasena(ActionEvent event) {
		try {
			serviceEJB.generarContrasena(getRegistroSeleccionado());
		} catch (RegistrosNoEncontradosException e) {
			e.printStackTrace();
		}
	}

	

}
