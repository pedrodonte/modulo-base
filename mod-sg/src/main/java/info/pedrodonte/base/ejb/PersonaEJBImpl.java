package info.pedrodonte.base.ejb;


import info.pedrodonte.base.dao.BsTbPersonaDAO;
import info.pedrodonte.base.dto.BsTbPersona;
import info.pedrodonte.base.vo.VoPersona;
import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.protask.excepciones.RegistrosNoEncontradosException;
import info.pedrodonte.sg.vo.HelperMapper;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

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

		BsTbPersona dto = helperMapper.toDTO(registro);
		personaDAO.save(dto);
		registro.setIdPersona(dto.getIdPersona());
		
		return registro;
	}

	@Override
	public VoPersona actualizarRegistro(VoPersona registro)
			throws ErrorDelSistemaException {

		BsTbPersona dto = helperMapper.toDTO(registro);
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

		for (BsTbPersona dto : personaDAO.findAll()) {
			VoPersona vo = helperMapper.toVO(dto);
			registros.add(vo);
		}

		return registros;
	}

	@Override
	public VoPersona obtenerRegistroPorID(long id)
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {
		BsTbPersona dto = personaDAO.find(id);
		return helperMapper.toVO(dto);
	}


}
