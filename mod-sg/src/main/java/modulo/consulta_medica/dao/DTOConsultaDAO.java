
package modulo.consulta_medica.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import util.GenericDAO;
import modulo.base.excepciones.PersistenciaDAOException;
import modulo.consulta_medica.dto.DTOConsulta;

@Stateless
public class DTOConsultaDAO extends GenericDAO<DTOConsulta, Long>{ 
	
	public DTOConsultaDAO() { 
		super(DTOConsulta.class); 
	}

	public List<DTOConsulta> findByIdPersona(long idPersona) throws PersistenciaDAOException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String query = "Select m From DTOConsulta m Where m.paciente.idPersona = :idPersona";
		parameters.put("idPersona", idPersona);
		return super.findManyResult(query, parameters);
	}
	
}
