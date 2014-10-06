package info.pedrodonte.consultas.ejb;

import java.util.List;

import info.pedrodonte.consultas.vo.VoConsulta;
import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.protask.excepciones.RegistrosNoEncontradosException;
import info.pedrodonte.util.CrudGenericServiceApi;

public interface ConsultaEJB extends CrudGenericServiceApi<VoConsulta>{
	
	public List<VoConsulta> obtenerRegistrosPorPersona(long idPersona) throws ErrorDelSistemaException, RegistrosNoEncontradosException;

}
