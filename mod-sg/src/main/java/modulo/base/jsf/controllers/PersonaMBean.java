package modulo.base.jsf.controllers;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
//fullEJB
//fullVO


import util.AbsMantenedorMB;
import util.CrudGenericServiceApi;
import modulo.base.ejb.PersonaEJB;
import modulo.base.vo.VoPersona;

@Named
@SessionScoped
public class PersonaMBean extends AbsMantenedorMB<VoPersona> { //controller , vo

	private static final long serialVersionUID = 123456789L;
	
	@Inject
	private PersonaEJB serviceEJB; //EJB
	
	public PersonaMBean() {
		super(VoPersona.class);
	}
	
	@Override
	public CrudGenericServiceApi<VoPersona> asignarCrudService() {
		return serviceEJB;
	}
	

}
