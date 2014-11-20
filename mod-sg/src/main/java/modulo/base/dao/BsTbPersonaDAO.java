
package modulo.base.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import util.GenericDAO;
import modulo.base.dto.DTOPersona;
import modulo.base.excepciones.PersistenciaDAOException;

@Stateless
public class BsTbPersonaDAO extends GenericDAO<DTOPersona, Long>{ 
	
	public BsTbPersonaDAO() { 
		super(DTOPersona.class); 
	}

	public DTOPersona findByIdentificador(String identificador) throws PersistenciaDAOException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String query = "Select m From DTOPersona m Where m.identificador = :identificador";
		parameters.put("identificador", identificador);
		return super.findOneResult(query, parameters);
	}
	
}
