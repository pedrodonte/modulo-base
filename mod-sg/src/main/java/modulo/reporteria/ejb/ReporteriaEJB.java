package modulo.reporteria.ejb;

import java.util.Date;
import java.util.List;

import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.reporteria.vo.VwResumenConsultas;

public interface ReporteriaEJB {
	
	public List<VwResumenConsultas> consultasPeriodo(Date desde, Date hasta) throws ErrorDelSistemaException, RegistrosNoEncontradosException;
	public List<VwResumenConsultas> consultasPeriodoSexo(Date desde, Date hasta) throws ErrorDelSistemaException, RegistrosNoEncontradosException;

}
