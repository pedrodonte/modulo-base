package modulo.usuarios.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import util.GenericDAO;
import modulo.usuarios.dto.DTOUsuario;

@Stateless
public class DTOUsuarioDAO extends GenericDAO<DTOUsuario, Long> {

	public DTOUsuarioDAO() {
		super(DTOUsuario.class);
	}

	public DTOUsuario findByIdentificador(String identificador) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String query = "Select m From DTOUsuario m Where m.identificador = :identificador";
		parameters.put("identificador", identificador);
		return super.findOneResult(query, parameters);
	}
	
}
