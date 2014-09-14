
package info.pedrodonte.sg.jsf.controller;

import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.protask.excepciones.RegistrosNoEncontradosException;
import info.pedrodonte.sg.ejb.RolEJB;
import info.pedrodonte.sg.ejb.UserEJB;//fullEJB
import info.pedrodonte.sg.vo.VoRol;
import info.pedrodonte.sg.vo.VoUser;//fullVO
import info.pedrodonte.util.AbsMantenedorMB;
import info.pedrodonte.util.CrudGenericServiceApi;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

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
