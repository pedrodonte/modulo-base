package modulo.base.ejb;

import util.CrudGenericServiceApi;
import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.base.vo.VoPersona;

public interface PersonaEJB extends CrudGenericServiceApi<VoPersona>{
	
	public VoPersona obtenerPorIdentificador(String identificador) throws RegistrosNoEncontradosException, ErrorDelSistemaException;

}
