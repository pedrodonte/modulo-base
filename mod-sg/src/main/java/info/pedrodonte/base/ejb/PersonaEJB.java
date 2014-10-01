package info.pedrodonte.base.ejb;

import info.pedrodonte.base.vo.VoPersona;
import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.protask.excepciones.RegistrosNoEncontradosException;
import info.pedrodonte.util.CrudGenericServiceApi;

public interface PersonaEJB extends CrudGenericServiceApi<VoPersona>{
	
	public VoPersona obtenerPorIdentificador(String identificador) throws RegistrosNoEncontradosException, ErrorDelSistemaException;

}
