package info.pedrodonte.sg.dao;

import info.pedrodonte.sg.dto.SgTbUserRol;
import info.pedrodonte.util.GenericDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

@Stateless
public class SgTbUserRolDAO extends GenericDAO<SgTbUserRol, Long> {

	public SgTbUserRolDAO() {
		super(SgTbUserRol.class);
	}

	public SgTbUserRol buscarRol(long usuarioId, long rolId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String query = "Select m From SgTbUserRol m Where m.sgTbRol.rolId = :rolId and m.sgTbUser.usuarioId = :usuarioId "
				+ "and CURRENT_TIMESTAMP BETWEEN m.validoDesde And m.validoHasta";
		parameters.put("rolId", rolId);
		parameters.put("usuarioId", usuarioId);
		return super.findOneResult(query, parameters);
	}

	public List<SgTbUserRol> buscarRolesAsociados(long usuarioId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String query = "Select m From SgTbUserRol m Where m.sgTbUser.usuarioId = :usuarioId "
				+ "and CURRENT_TIMESTAMP BETWEEN m.validoDesde And m.validoHasta";
		parameters.put("usuarioId", usuarioId);
		return super.findManyResult(query, parameters);
	}
	
}
