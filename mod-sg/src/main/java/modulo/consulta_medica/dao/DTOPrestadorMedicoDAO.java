
package modulo.consulta_medica.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import modulo.consulta_medica.dto.DTOPrestadorMedico;
import util.GenericDAO;

@Stateless
public class DTOPrestadorMedicoDAO extends GenericDAO<DTOPrestadorMedico, Long>{ 
	
	public DTOPrestadorMedicoDAO() { 
		super(DTOPrestadorMedico.class); 
	}
	
	public DTOPrestadorMedico obtenerPrestador(String rut){
		String namedQuery = "select m from DTOPrestadorMedico where m.dtoPersona.identificador = :rut";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("rut", rut);
		return super.findOneResult(namedQuery, parameters);
	}

}
