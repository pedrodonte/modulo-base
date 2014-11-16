
package modulo.consulta_medica.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import util.GenericDAO;
import modulo.consulta_medica.dto.CsTbConsulta;

@Stateless
public class CsTbConsultaDAO extends GenericDAO<CsTbConsulta, Long>{ 
	
	public CsTbConsultaDAO() { 
		super(CsTbConsulta.class); 
	}

	public List<CsTbConsulta> findByIdPersona(long idPersona) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String query = "Select m From CsTbConsulta m Where m.paciente.idPersona = :idPersona";
		parameters.put("idPersona", idPersona);
		return super.findManyResult(query, parameters);
	}
	
}
