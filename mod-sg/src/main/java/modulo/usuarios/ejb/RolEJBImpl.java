package modulo.usuarios.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.HelperMapeoException;
import modulo.base.excepciones.PersistenciaDAOException;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.usuarios.dao.DTORolDAO;
import modulo.usuarios.dao.DTORolDeUsuarioDAO;
import modulo.usuarios.dto.DTORol;
import modulo.usuarios.dto.DTORolDeUsuario;
import modulo.usuarios.vo.HelperMapper;
import modulo.usuarios.vo.VoRol;
import modulo.usuarios.vo.VoUser;

import org.apache.log4j.Logger;

@Stateless
public class RolEJBImpl implements RolEJB { // EJB EJBImpl

	@Inject
	DTORolDAO sgTbRolDAO;

	@Inject
	DTORolDeUsuarioDAO userRolDAO;

	HelperMapper helperMapper = new HelperMapper();
	
	Logger logger = Logger.getLogger(getClass());

	@Override
	public VoRol nuevoRegistro(VoRol registro) throws ErrorDelSistemaException {

		try {
			DTORol dto = helperMapper.toDTO(registro);
			sgTbRolDAO.save(dto);

			return registro;
		} catch (HelperMapeoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenciaDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public VoRol actualizarRegistro(VoRol registro)
			throws ErrorDelSistemaException {

		try {
			DTORol dto = helperMapper.toDTO(registro);
			sgTbRolDAO.update(dto);

			return registro;
		} catch (HelperMapeoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenciaDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void eliminarRegistro(VoRol registro)
			throws ErrorDelSistemaException {

		throw new UnsupportedOperationException("Metodo Sin Implementar");

	}

	@Override
	public List<VoRol> obtenerRegistros() throws ErrorDelSistemaException,
			RegistrosNoEncontradosException {

		try {
			List<VoRol> registros = new ArrayList<>();

			for (DTORol dto : sgTbRolDAO.findAll()) {
				VoRol vo = helperMapper.toVO(dto);
				registros.add(vo);
			}

			return registros;
		} catch (HelperMapeoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenciaDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public VoRol obtenerRegistroPorID(long id) throws ErrorDelSistemaException,
			RegistrosNoEncontradosException {
		try {
			DTORol dto = sgTbRolDAO.find(id);
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

	@Override
	public List<VoRol> obtenerAsociadosUsuario(VoUser user)
			throws ErrorDelSistemaException {

		try {
			List<VoRol> registros = new ArrayList<>();

			for (DTORolDeUsuario dto : userRolDAO.buscarRolesAsociados(user.getUsuarioId())) {
				VoRol vo = helperMapper.toVO(dto.getDtoRol());
				registros.add(vo);
			}
			logger.info("Encontrado:"+registros.size());
			return registros;
		} catch (HelperMapeoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenciaDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<VoRol> obtenerNoAsociadosUsuario(VoUser user)
			throws ErrorDelSistemaException {
		List<VoRol> rolesNoAsociados = new ArrayList<>();
		try {
			List<VoRol> asociados = obtenerAsociadosUsuario(user);
			List<VoRol> disponibles = obtenerRegistros();
			for (VoRol rol : disponibles) {
				if ( !asociados.contains(rol) ) {
					rolesNoAsociados.add(rol);
				}
			}
			
		} catch (RegistrosNoEncontradosException e) {
			e.printStackTrace();
		}
		logger.info("Encontrado:"+rolesNoAsociados.size());
		return rolesNoAsociados;
	}

}
