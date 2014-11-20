package modulo.base.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import modulo.base.dao.TbParametroDAO;
import modulo.base.dto.TbParametro;
import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.HelperMapeoException;
import modulo.base.excepciones.PersistenciaDAOException;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.base.vo.VoParametro;
import modulo.usuarios.vo.HelperMapper;

import org.apache.log4j.Logger;

@Stateless
public class ParametroEJBImpl implements ParametroEJB {

	HelperMapper helperMapper = new HelperMapper();
	Logger logger = Logger.getLogger(getClass());

	@Inject
	TbParametroDAO dao;

	@Override
	public VoParametro nuevoRegistro(VoParametro registro)
			throws ErrorDelSistemaException {

		try {
			TbParametro dto = helperMapper.toDTO(registro);
			dao.save(dto);
			return registro;
		} catch (HelperMapeoException e) {
			throw new ErrorDelSistemaException(e.getMessage(), e);
		} catch (PersistenciaDAOException e) {
			throw new ErrorDelSistemaException(e.getMessage(), e);
		}

	}

	@Override
	public VoParametro actualizarRegistro(VoParametro registro)
			throws ErrorDelSistemaException {

		try {
			TbParametro dto = helperMapper.toDTO(registro);
			dao.update(dto);

			return registro;
		} catch (HelperMapeoException e) {
			throw new ErrorDelSistemaException(e.getMessage(), e);		
		} catch (PersistenciaDAOException e) {
			throw new ErrorDelSistemaException(e.getMessage(), e);
		}

	}

	@Override
	public void eliminarRegistro(VoParametro registro)
			throws ErrorDelSistemaException {

		throw new UnsupportedOperationException("Metodo Sin Implementar");

	}

	@Override
	public List<VoParametro> obtenerRegistros()
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {

		List<VoParametro> registros = new ArrayList<>();

		try {
			for (TbParametro dto : dao.findAll()) {
				VoParametro vo = helperMapper.toVO(dto);
				registros.add(vo);
			}
		} catch (HelperMapeoException e) {
			throw new ErrorDelSistemaException(e.getMessage(), e);
		} catch (PersistenciaDAOException e) {
			throw new ErrorDelSistemaException(e.getMessage(), e);
		}

		return registros;
	}

	@Override
	public VoParametro obtenerRegistroPorID(long id)
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {
		try {
			TbParametro dto = dao.find(id);
			if (dto == null) {
				throw new RegistrosNoEncontradosException("No se encuentran registros para el parametro de entrada:"+id);
			}
			return helperMapper.toVO(dto);
		} catch (HelperMapeoException e) {
			throw new ErrorDelSistemaException(e.getMessage(), e);
		} catch (PersistenciaDAOException e) {
			throw new ErrorDelSistemaException(e.getMessage(), e);
		}
	}

}
