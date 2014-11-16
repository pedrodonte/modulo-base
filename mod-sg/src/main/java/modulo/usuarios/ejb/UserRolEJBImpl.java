
package modulo.usuarios.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.usuarios.dao.DTORolDeUsuarioDAO;
import modulo.usuarios.dto.DTORolDeUsuario;
import modulo.usuarios.vo.HelperMapper;
import modulo.usuarios.vo.VoUserRol;

@Stateless
public class UserRolEJBImpl implements UserRolEJB { //EJB EJBImpl

	@Inject
	DTORolDeUsuarioDAO sgTbUserRolDAO;
	
	HelperMapper helperMapper = new HelperMapper();
	
	@Override
	public VoUserRol nuevoRegistro(VoUserRol registro) throws ErrorDelSistemaException {
		
		DTORolDeUsuario dto = helperMapper.toDTO(registro);
		sgTbUserRolDAO.save(dto);
		
		return registro;
	}

	@Override
	public VoUserRol actualizarRegistro(VoUserRol registro)	throws ErrorDelSistemaException {
		
		DTORolDeUsuario dto = helperMapper.toDTO(registro);
		sgTbUserRolDAO.update(dto);
		
		return registro;
	}

	@Override
	public void eliminarRegistro(VoUserRol registro) throws ErrorDelSistemaException {
		
		throw new UnsupportedOperationException("Metodo Sin Implementar");

	}

	@Override
	public List<VoUserRol> obtenerRegistros()	throws ErrorDelSistemaException, RegistrosNoEncontradosException {
		
		List<VoUserRol> registros = new ArrayList<>();
		
		for (DTORolDeUsuario dto : sgTbUserRolDAO.findAll()) {
			VoUserRol vo = helperMapper.toVO(dto);
			registros.add(vo);
		}
		
		return registros;
	}
	
	@Override
	public VoUserRol obtenerRegistroPorID(long id)
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {
		DTORolDeUsuario dto = sgTbUserRolDAO.find(id);
		return helperMapper.toVO(dto);
	}

}
