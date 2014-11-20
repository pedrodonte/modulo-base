package modulo.usuarios.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import util.GenericDAO;
import modulo.base.excepciones.PersistenciaDAOException;
import modulo.usuarios.dto.DTOUsuario;

@Stateless
public class DTOUsuarioDAO extends GenericDAO<DTOUsuario, Long> {

	public DTOUsuarioDAO() {
		super(DTOUsuario.class);
	}

	public DTOUsuario findByIdentificador(String identificador) throws PersistenciaDAOException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String query = "Select m From DTOUsuario m Where m.identificador = :identificador";
		parameters.put("identificador", identificador);
		return super.findOneResult(query, parameters);
	}

	public DTOUsuario findByPersonaID(long idPersona) throws PersistenciaDAOException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String query = "Select m From DTOUsuario m Where m.dtoPersonaAsociada.idPersona = :idPersona";
		parameters.put("idPersona", idPersona);
		return super.findOneResult(query, parameters);
	}
	
}
