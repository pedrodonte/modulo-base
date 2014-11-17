package modulo.consulta_medica.jsf.controllers;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import modulo.consulta_medica.ejb.PrestadorMedicoEJB;
import modulo.consulta_medica.vo.VoPrestadorMedico;
import util.AbsMantenedorMB;
import util.CrudGenericServiceApi;

@Named
@SessionScoped
public class PrestadorMedicoMBean extends AbsMantenedorMB<VoPrestadorMedico> {
	
	private static final long serialVersionUID = 123456789L;

	Logger logger = Logger.getLogger(getClass());

	@Inject
	private PrestadorMedicoEJB serviceEJB;


	@Override
	public CrudGenericServiceApi<VoPrestadorMedico> asignarCrudService() {
		return serviceEJB;
	}

}
