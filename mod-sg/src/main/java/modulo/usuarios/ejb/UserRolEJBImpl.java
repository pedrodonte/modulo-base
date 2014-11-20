package modulo.usuarios.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.HelperMapeoException;
import modulo.base.excepciones.PersistenciaDAOException;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.usuarios.dao.DTORolDeUsuarioDAO;
import modulo.usuarios.dto.DTORolDeUsuario;
import modulo.usuarios.vo.HelperMapper;
import modulo.usuarios.vo.VoUserRol;

@Stateless
public class UserRolEJBImpl implements UserRolEJB { // EJB EJBImpl

	@Inject
	DTORolDeUsuarioDAO sgTbUserRolDAO;

	HelperMapper helperMapper = new HelperMapper();

	@Override
	public VoUserRol nuevoRegistro(VoUserRol registro)
			throws ErrorDelSistemaException {

		try {
			DTORolDeUsuario dto = helperMapper.toDTO(registro);
			sgTbUserRolDAO.save(dto);
		} catch (HelperMapeoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenciaDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return registro;
	}

	@Override
	public VoUserRol actualizarRegistro(VoUserRol registro)
			throws ErrorDelSistemaException {

		try {
			DTORolDeUsuario dto = helperMapper.toDTO(registro);
			sgTbUserRolDAO.update(dto);
		} catch (HelperMapeoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenciaDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return registro;
	}

	@Override
	public void eliminarRegistro(VoUserRol registro)
			throws ErrorDelSistemaException {

		throw new UnsupportedOperationException("Metodo Sin Implementar");

	}

	@Override
	public List<VoUserRol> obtenerRegistros() throws ErrorDelSistemaException,
			RegistrosNoEncontradosException {

		List<VoUserRol> registros = new ArrayList<>();

		try {
			for (DTORolDeUsuario dto : sgTbUserRolDAO.findAll()) {
				VoUserRol vo = helperMapper.toVO(dto);
				registros.add(vo);
			}
		} catch (HelperMapeoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenciaDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return registros;
	}

	@Override
	public VoUserRol obtenerRegistroPorID(long id)
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {
		try {
			DTORolDeUsuario dto = sgTbUserRolDAO.find(id);
			if (dto == null) {
				throw new RegistrosNoEncontradosException("No se encuentran registros para el parametro de entrada:"+id);
			}
			return helperMapper.toVO(dto);
		} catch (HelperMapeoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenciaDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
