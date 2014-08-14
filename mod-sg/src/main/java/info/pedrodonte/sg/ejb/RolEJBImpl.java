package info.pedrodonte.sg.ejb;

import info.pedrodonte.sg.dao.SgTbRolDAO;
import info.pedrodonte.sg.dao.SgTbUserRolDAO;
import info.pedrodonte.sg.dto.SgTbRol;
import info.pedrodonte.sg.dto.SgTbUserRol;
import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.protask.excepciones.RegistrosNoEncontradosException;
import info.pedrodonte.sg.vo.VoRol;
import info.pedrodonte.sg.vo.HelperMapper;
import info.pedrodonte.sg.vo.VoUser;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

@Stateless
public class RolEJBImpl implements RolEJB { // EJB EJBImpl

	@Inject
	SgTbRolDAO sgTbRolDAO;

	@Inject
	SgTbUserRolDAO userRolDAO;

	HelperMapper helperMapper = new HelperMapper();
	
	Logger logger = Logger.getLogger(getClass());

	@Override
	public VoRol nuevoRegistro(VoRol registro) throws ErrorDelSistemaException {

		SgTbRol dto = helperMapper.toDTO(registro);
		sgTbRolDAO.save(dto);

		return registro;
	}

	@Override
	public VoRol actualizarRegistro(VoRol registro)
			throws ErrorDelSistemaException {

		SgTbRol dto = helperMapper.toDTO(registro);
		sgTbRolDAO.update(dto);

		return registro;
	}

	@Override
	public void eliminarRegistro(VoRol registro)
			throws ErrorDelSistemaException {

		throw new UnsupportedOperationException("Metodo Sin Implementar");

	}

	@Override
	public List<VoRol> obtenerRegistros() throws ErrorDelSistemaException,
			RegistrosNoEncontradosException {

		List<VoRol> registros = new ArrayList<>();

		for (SgTbRol dto : sgTbRolDAO.findAll()) {
			VoRol vo = helperMapper.toVO(dto);
			registros.add(vo);
		}

		return registros;
	}

	@Override
	public VoRol obtenerRegistroPorID(long id) throws ErrorDelSistemaException,
			RegistrosNoEncontradosException {
		SgTbRol dto = sgTbRolDAO.find(id);
		return helperMapper.toVO(dto);
	}

	@Override
	public List<VoRol> obtenerAsociadosUsuario(VoUser user)
			throws ErrorDelSistemaException {

		List<VoRol> registros = new ArrayList<>();

		for (SgTbUserRol dto : userRolDAO.buscarRolesAsociados(user.getUsuarioId())) {
			VoRol vo = helperMapper.toVO(dto.getSgTbRol());
			registros.add(vo);
		}
		logger.info("Encontrado:"+registros.size());
		return registros;
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
