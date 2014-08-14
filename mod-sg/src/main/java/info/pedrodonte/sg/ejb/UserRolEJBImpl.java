
package info.pedrodonte.sg.ejb;

import info.pedrodonte.sg.dao.SgTbUserRolDAO;
import info.pedrodonte.sg.dto.SgTbUserRol;
import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.protask.excepciones.RegistrosNoEncontradosException;
import info.pedrodonte.sg.vo.VoUserRol;
import info.pedrodonte.sg.vo.HelperMapper;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserRolEJBImpl implements UserRolEJB { //EJB EJBImpl

	@Inject
	SgTbUserRolDAO sgTbUserRolDAO;
	
	HelperMapper helperMapper = new HelperMapper();
	
	@Override
	public VoUserRol nuevoRegistro(VoUserRol registro) throws ErrorDelSistemaException {
		
		SgTbUserRol dto = helperMapper.toDTO(registro);
		sgTbUserRolDAO.save(dto);
		
		return registro;
	}

	@Override
	public VoUserRol actualizarRegistro(VoUserRol registro)	throws ErrorDelSistemaException {
		
		SgTbUserRol dto = helperMapper.toDTO(registro);
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
		
		for (SgTbUserRol dto : sgTbUserRolDAO.findAll()) {
			VoUserRol vo = helperMapper.toVO(dto);
			registros.add(vo);
		}
		
		return registros;
	}
	
	@Override
	public VoUserRol obtenerRegistroPorID(long id)
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {
		SgTbUserRol dto = sgTbUserRolDAO.find(id);
		return helperMapper.toVO(dto);
	}

}
