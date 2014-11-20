package modulo.integracion.ejb;

import modulo.base.vo.VoPersona;
import modulo.integracion.excepciones.ErrorIntegracionException;

public interface IntegracionEJB {

	public VoPersona buscarPersona(String rut) throws ErrorIntegracionException;
	
}
