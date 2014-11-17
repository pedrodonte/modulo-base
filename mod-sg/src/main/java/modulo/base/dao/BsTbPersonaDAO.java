
package modulo.base.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import util.GenericDAO;
import modulo.base.dto.DTOPersona;

@Stateless
public class BsTbPersonaDAO extends GenericDAO<DTOPersona, Long>{ 
	
	public BsTbPersonaDAO() { 
		super(DTOPersona.class); 
	}

	public DTOPersona findByIdentificador(String identificador) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String query = "Select m From BsTbPersona m Where m.identificador = :identificador";
		parameters.put("identificador", identificador);
		return super.findOneResult(query, parameters);
	}
	
}
