package modulo.base.ejb;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import modulo.base.dao.BsTbPersonaDAO;
import modulo.base.dto.DTOPersona;
import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.base.vo.VoPersona;
import modulo.usuarios.vo.HelperMapper;

import org.apache.log4j.Logger;

@Stateless
public class PersonaEJBImpl implements PersonaEJB { // EJB EJBImpl

	@Inject
	BsTbPersonaDAO personaDAO;

	HelperMapper helperMapper = new HelperMapper();
	Logger logger = Logger.getLogger(getClass());

	@Override
	public VoPersona nuevoRegistro(VoPersona registro)
			throws ErrorDelSistemaException {

		DTOPersona dto = helperMapper.toDTO(registro);
		personaDAO.save(dto);
		registro.setIdPersona(dto.getIdPersona());
		
		return registro;
	}

	@Override
	public VoPersona actualizarRegistro(VoPersona registro)
			throws ErrorDelSistemaException {

		DTOPersona dto = helperMapper.toDTO(registro);
		personaDAO.update(dto);

		return registro;
	}

	@Override
	public void eliminarRegistro(VoPersona registro)
			throws ErrorDelSistemaException {

		throw new UnsupportedOperationException("Metodo Sin Implementar");

	}

	@Override
	public List<VoPersona> obtenerRegistros() throws ErrorDelSistemaException,
			RegistrosNoEncontradosException {

		List<VoPersona> registros = new ArrayList<>();

		for (DTOPersona dto : personaDAO.findAll()) {
			VoPersona vo = helperMapper.toVO(dto);
			registros.add(vo);
		}

		return registros;
	}

	@Override
	public VoPersona obtenerRegistroPorID(long id)
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {
		DTOPersona dto = personaDAO.find(id);
		return helperMapper.toVO(dto);
	}

	@Override
	public VoPersona obtenerPorIdentificador(String identificador)
			throws RegistrosNoEncontradosException, ErrorDelSistemaException {
		DTOPersona dto = personaDAO.findByIdentificador(identificador);
		return helperMapper.toVO(dto);
	}


}
