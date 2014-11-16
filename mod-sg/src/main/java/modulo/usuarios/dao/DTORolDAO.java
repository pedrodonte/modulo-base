
package modulo.usuarios.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import util.GenericDAO;
import modulo.usuarios.dto.DTORol;

@Stateless
public class DTORolDAO extends GenericDAO<DTORol, Long>{ 
	
	public DTORolDAO() { 
		super(DTORol.class); 
	}
	
	public List<DTORol> buscarNoRolesAsociados(List<DTORol> rolesAsociados) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String query = "Select m From DTORol m Where m not in :rolesAsociados";
		parameters.put("rolesAsociados", rolesAsociados);
		return super.findManyResult(query, parameters);
	}

	
}
