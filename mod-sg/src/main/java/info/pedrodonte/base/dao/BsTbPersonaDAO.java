
package info.pedrodonte.base.dao;

import info.pedrodonte.base.dto.BsTbPersona;
import info.pedrodonte.util.GenericDAO;

import javax.ejb.Stateless;

@Stateless
public class BsTbPersonaDAO extends GenericDAO<BsTbPersona, Long>{ 
	
	public BsTbPersonaDAO() { 
		super(BsTbPersona.class); 
	}
	
}
