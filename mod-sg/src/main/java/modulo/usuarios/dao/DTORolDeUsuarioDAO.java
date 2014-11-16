package modulo.usuarios.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import util.GenericDAO;
import modulo.usuarios.dto.DTORolDeUsuario;

@Stateless
public class DTORolDeUsuarioDAO extends GenericDAO<DTORolDeUsuario, Long> {

	public DTORolDeUsuarioDAO() {
		super(DTORolDeUsuario.class);
	}

	public DTORolDeUsuario buscarRol(long usuarioId, long rolId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String query = "Select m From DTORolDeUsuario m Where m.dtoRol.rolId = :rolId and m.dtoUsuario.usuarioId = :usuarioId "
				+ "and CURRENT_TIMESTAMP BETWEEN m.validoDesde And m.validoHasta";
		parameters.put("rolId", rolId);
		parameters.put("usuarioId", usuarioId);
		return super.findOneResult(query, parameters);
	}

	public List<DTORolDeUsuario> buscarRolesAsociados(long usuarioId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String query = "Select m From DTORolDeUsuario m Where m.dtoUsuario.usuarioId = :usuarioId "
				+ "and CURRENT_TIMESTAMP BETWEEN m.validoDesde And m.validoHasta";
		parameters.put("usuarioId", usuarioId);
		return super.findManyResult(query, parameters);
	}
	
}
