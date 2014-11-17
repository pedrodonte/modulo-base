package modulo.consulta_medica.ejb;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.consulta_medica.dao.DTOConsultaDAO;
import modulo.consulta_medica.dto.DTOConsulta;
import modulo.consulta_medica.vo.VoConsulta;
import modulo.usuarios.vo.HelperMapper;

import org.apache.log4j.Logger;

@Stateless
public class ConsultaEJBImpl implements ConsultaEJB { // EJB EJBImpl

	@Inject
	DTOConsultaDAO consultaDAO;

	HelperMapper helperMapper = new HelperMapper();
	Logger logger = Logger.getLogger(getClass());

	@Override
	public VoConsulta nuevoRegistro(VoConsulta registro)
			throws ErrorDelSistemaException {

		DTOConsulta dto = helperMapper.toDTO(registro);
		consultaDAO.save(dto);
		registro = helperMapper.toVO(dto);
		
		return registro;
	}

	@Override
	public VoConsulta actualizarRegistro(VoConsulta registro)
			throws ErrorDelSistemaException {

		DTOConsulta dto = helperMapper.toDTO(registro);
		consultaDAO.update(dto);

		return registro;
	}

	@Override
	public void eliminarRegistro(VoConsulta registro)
			throws ErrorDelSistemaException {

		throw new UnsupportedOperationException("Metodo Sin Implementar");

	}

	@Override
	public List<VoConsulta> obtenerRegistros() throws ErrorDelSistemaException,
			RegistrosNoEncontradosException {

		List<VoConsulta> registros = new ArrayList<>();

		for (DTOConsulta dto : consultaDAO.findAll()) {
			VoConsulta vo = helperMapper.toVO(dto);
			registros.add(vo);
		}

		return registros;
	}

	@Override
	public VoConsulta obtenerRegistroPorID(long id)
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {
		DTOConsulta dto = consultaDAO.find(id);
		return helperMapper.toVO(dto);
	}

	@Override
	public List<VoConsulta> obtenerRegistrosPorPersona(long idPersona)
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {
		List<VoConsulta> registros = new ArrayList<>();

		for (DTOConsulta dto : consultaDAO.findByIdPersona(idPersona)) {
			VoConsulta vo = helperMapper.toVO(dto);
			registros.add(vo);
		}

		return registros;
	}


}
