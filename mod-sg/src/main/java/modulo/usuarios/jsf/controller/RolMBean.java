
package modulo.usuarios.jsf.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import util.AbsMantenedorMB;
import util.CrudGenericServiceApi;
import modulo.usuarios.ejb.RolEJB;
import modulo.usuarios.vo.VoRol;

@Named
@SessionScoped
public class RolMBean extends AbsMantenedorMB<VoRol> { //controller , vo

	private static final long serialVersionUID = 123456789L;

	@Inject
	private RolEJB serviceEJB; //EJB

	public RolMBean() {
		super(VoRol.class);
	}

	@Override
	public CrudGenericServiceApi<VoRol> asignarCrudService() {
		return serviceEJB;
	}

}
