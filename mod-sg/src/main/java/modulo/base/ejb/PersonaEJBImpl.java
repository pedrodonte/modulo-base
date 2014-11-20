package modulo.base.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import modulo.base.dao.BsTbPersonaDAO;
import modulo.base.dto.DTOPersona;
import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.HelperMapeoException;
import modulo.base.excepciones.PersistenciaDAOException;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.base.vo.VoPersona;
import modulo.integracion.ejb.IntegracionEJB;
import modulo.integracion.excepciones.ErrorIntegracionException;
import modulo.usuarios.vo.HelperMapper;

import org.apache.log4j.Logger;

@Stateless
public class PersonaEJBImpl implements PersonaEJB { // EJB EJBImpl

	@Inject
	BsTbPersonaDAO personaDAO;

	@Inject
	IntegracionEJB integracionEJB;

	HelperMapper helperMapper = new HelperMapper();
	Logger logger = Logger.getLogger(getClass());

	@Override
	public VoPersona nuevoRegistro(VoPersona registro)
			throws ErrorDelSistemaException {

		try {
			DTOPersona dto = helperMapper.toDTO(registro);
			personaDAO.save(dto);
			registro.setIdPersona(dto.getIdPersona());

			return registro;
		} catch (HelperMapeoException e) {
			throw new ErrorDelSistemaException(e.getMessage(), e);
		} catch (PersistenciaDAOException e) {
			throw new ErrorDelSistemaException(e.getMessage(), e);
		}
	}

	@Override
	public VoPersona actualizarRegistro(VoPersona registro)
			throws ErrorDelSistemaException {

		try {
			DTOPersona dto = helperMapper.toDTO(registro);
			personaDAO.update(dto);

			return registro;
		} catch (HelperMapeoException e) {
			throw new ErrorDelSistemaException(e.getMessage(), e);
		} catch (PersistenciaDAOException e) {
			throw new ErrorDelSistemaException(e.getMessage(), e);
		}
	}

	@Override
	public void eliminarRegistro(VoPersona registro)
			throws ErrorDelSistemaException {

		throw new UnsupportedOperationException("Metodo Sin Implementar");

	}

	@Override
	public List<VoPersona> obtenerRegistros() throws ErrorDelSistemaException,
			RegistrosNoEncontradosException {

		try {
			List<VoPersona> registros = new ArrayList<>();

			for (DTOPersona dto : personaDAO.findAll()) {
				VoPersona vo = helperMapper.toVO(dto);
				registros.add(vo);
			}

			return registros;
		} catch (HelperMapeoException e) {
			throw new ErrorDelSistemaException(e.getMessage(), e);
		} catch (PersistenciaDAOException e) {
			throw new ErrorDelSistemaException(e.getMessage(), e);
		}
	}

	@Override
	public VoPersona obtenerRegistroPorID(long id)
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {
		try {
			DTOPersona dto = personaDAO.find(id);
			if (dto == null) {
				throw new RegistrosNoEncontradosException(
						"No se encuentran registros para el parametro de entrada:"
								+ id);
			}
			return helperMapper.toVO(dto);
		} catch (HelperMapeoException e) {
			throw new ErrorDelSistemaException(e.getMessage(), e);
		} catch (PersistenciaDAOException e) {
			throw new ErrorDelSistemaException(e.getMessage(), e);
		}
	}

	@Override
	public VoPersona obtenerPorIdentificador(String identificador)
			throws RegistrosNoEncontradosException, ErrorDelSistemaException {
		VoPersona respuesta = null;
		try {
			DTOPersona dto = personaDAO.findByIdentificador(identificador);

			if (dto != null) {
				respuesta = helperMapper.toVO(dto);
			} else {

				respuesta = buscarPersonaEnSistemaExterno(identificador);
				
				if (respuesta == null) {
					throw new RegistrosNoEncontradosException(
							"No hay registros para el parametro ingresado. RUT="
									+ identificador);
				}

			}
		} catch (HelperMapeoException e) {
			throw new ErrorDelSistemaException(e.getMessage(), e);
		} catch (ErrorIntegracionException e) {
			throw new ErrorDelSistemaException(e.getMessage(), e);
		} catch (PersistenciaDAOException e) {
			throw new ErrorDelSistemaException(e.getMessage(), e);
		}
		return respuesta;

	}

	private VoPersona buscarPersonaEnSistemaExterno(String identificador)
			throws ErrorIntegracionException, RegistrosNoEncontradosException,
			ErrorDelSistemaException {
		VoPersona persona = integracionEJB.buscarPersona(identificador);

		if (persona != null) {

			nuevoRegistro(persona);

			return persona;

		} else {
			throw new RegistrosNoEncontradosException(
					"No hay registros para el parametro ingresado. RUT="
							+ identificador);
		}

	}

}
