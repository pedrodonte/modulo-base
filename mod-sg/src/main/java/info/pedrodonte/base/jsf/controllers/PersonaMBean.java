package info.pedrodonte.base.jsf.controllers;

import info.pedrodonte.base.ejb.PersonaEJB;
import info.pedrodonte.base.vo.VoPersona;
import info.pedrodonte.util.AbsMantenedorMB;
import info.pedrodonte.util.CrudGenericServiceApi;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
//fullEJB
//fullVO

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
