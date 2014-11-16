package modulo.consulta_medica.ejb;

import java.util.List;

import util.CrudGenericServiceApi;
import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.consulta_medica.vo.VoConsulta;

public interface ConsultaEJB extends CrudGenericServiceApi<VoConsulta>{
	
	public List<VoConsulta> obtenerRegistrosPorPersona(long idPersona) throws ErrorDelSistemaException, RegistrosNoEncontradosException;

}
