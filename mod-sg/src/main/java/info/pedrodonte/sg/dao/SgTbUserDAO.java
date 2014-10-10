package info.pedrodonte.sg.dao;

import info.pedrodonte.sg.dto.SgTbUser;
import info.pedrodonte.util.GenericDAO;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

@Stateless
public class SgTbUserDAO extends GenericDAO<SgTbUser, Long> {

	public SgTbUserDAO() {
		super(SgTbUser.class);
	}

	public SgTbUser findByIdentificador(String identificador) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String query = "Select m From SgTbUser m Where m.identificador = :identificador";
		parameters.put("identificador", identificador);
		return super.findOneResult(query, parameters);
	}

}
