package reporteria.ejb;

import info.pedrodonte.consultas.vo.VoConsulta;
import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.protask.excepciones.RegistrosNoEncontradosException;

import java.util.Date;
import java.util.List;

import reporteria.vo.VwResumenConsultas;

public interface ReporteriaEJB {
	
	public List<VwResumenConsultas> consultasPeriodo(Date desde, Date hasta) throws ErrorDelSistemaException, RegistrosNoEncontradosException;
	public List<VwResumenConsultas> consultasPeriodoSexo(Date desde, Date hasta) throws ErrorDelSistemaException, RegistrosNoEncontradosException;

}
