
package modulo.base.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import util.GenericDAO;
import modulo.base.dto.BsTbPersona;

@Stateless
public class BsTbPersonaDAO extends GenericDAO<BsTbPersona, Long>{ 
	
	public BsTbPersonaDAO() { 
		super(BsTbPersona.class); 
	}

	public BsTbPersona findByIdentificador(String identificador) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String query = "Select m From BsTbPersona m Where m.identificador = :identificador";
		parameters.put("identificador", identificador);
		return super.findOneResult(query, parameters);
	}
	
}
