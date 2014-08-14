
package info.pedrodonte.sg.dao;

import info.pedrodonte.sg.dto.SgTbUser;
import info.pedrodonte.util.GenericDAO;
import javax.ejb.Stateless;

@Stateless
public class SgTbUserDAO extends GenericDAO<SgTbUser, Long>{ 
	
	public SgTbUserDAO() { 
		super(SgTbUser.class); 
	}
	
}
