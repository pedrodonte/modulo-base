
package modulo.usuarios.jsf.controller;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import util.AbsMantenedorMB;
import util.CrudGenericServiceApi;
import modulo.usuarios.ejb.UserRolEJB;
import modulo.usuarios.vo.VoUser;
import modulo.usuarios.vo.VoUserRol;

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
