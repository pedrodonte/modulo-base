package info.pedrodonte.util;


import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.protask.excepciones.RegistrosNoEncontradosException;

import java.util.List;

public interface CrudGenericServiceApi<VO> {
	
	public VO nuevoRegistro(VO registro) throws ErrorDelSistemaException;
	public VO actualizarRegistro(VO registro) throws ErrorDelSistemaException;
	public void eliminarRegistro(VO registro) throws ErrorDelSistemaException;
	public List<VO> obtenerRegistros() throws ErrorDelSistemaException, RegistrosNoEncontradosException;
	public VO obtenerRegistroPorID(long id) throws ErrorDelSistemaException, RegistrosNoEncontradosException;

}
