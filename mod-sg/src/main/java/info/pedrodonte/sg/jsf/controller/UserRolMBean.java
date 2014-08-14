
package info.pedrodonte.sg.jsf.controller;

import info.pedrodonte.sg.ejb.UserRolEJB;//fullEJB
import info.pedrodonte.sg.vo.VoUser;
import info.pedrodonte.sg.vo.VoUserRol;//fullVO
import info.pedrodonte.util.AbsMantenedorMB;
import info.pedrodonte.util.CrudGenericServiceApi;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class UserRolMBean extends AbsMantenedorMB<VoUserRol> { //controller , vo

	private static final long serialVersionUID = 123456789L;

	@Inject
	private UserRolEJB serviceEJB; //EJB

	public UserRolMBean() {
		super(VoUserRol.class);
	}

	@Override
	public CrudGenericServiceApi<VoUserRol> asignarCrudService() {
		return serviceEJB;
	}

	@Override
	public void doNuevoRegistroFormulario(ActionEvent event) {
		super.doNuevoRegistroFormulario(event);
		getRegistroEnEdicion().setVoUser(new VoUser());
	}

	

}
