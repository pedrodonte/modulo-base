
package info.pedrodonte.sg.jsf.controller;

import info.pedrodonte.sg.ejb.RolEJB;//fullEJB
import info.pedrodonte.sg.vo.VoRol;//fullVO
import info.pedrodonte.util.AbsMantenedorMB;
import info.pedrodonte.util.CrudGenericServiceApi;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
