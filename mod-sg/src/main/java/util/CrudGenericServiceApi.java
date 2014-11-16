package util;


import java.util.List;

import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.RegistrosNoEncontradosException;

public interface CrudGenericServiceApi<VO> {
	
	public VO nuevoRegistro(VO registro) throws ErrorDelSistemaException;
	public VO actualizarRegistro(VO registro) throws ErrorDelSistemaException;
	public void eliminarRegistro(VO registro) throws ErrorDelSistemaException;
	public List<VO> obtenerRegistros() throws ErrorDelSistemaException, RegistrosNoEncontradosException;
	public VO obtenerRegistroPorID(long id) throws ErrorDelSistemaException, RegistrosNoEncontradosException;

}
