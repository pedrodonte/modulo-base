
package info.pedrodonte.sg.dao;

import info.pedrodonte.sg.dto.SgTbRol;
import info.pedrodonte.util.GenericDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

@Stateless
public class SgTbRolDAO extends GenericDAO<SgTbRol, Long>{ 
	
	public SgTbRolDAO() { 
		super(SgTbRol.class); 
	}
	
	public List<SgTbRol> buscarNoRolesAsociados(List<SgTbRol> rolesAsociados) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String query = "Select m From SgTbRol m Where m not in :rolesAsociados";
		parameters.put("rolesAsociados", rolesAsociados);
		return super.findManyResult(query, parameters);
	}

	
}
