
package info.pedrodonte.consultas.dao;

import info.pedrodonte.consultas.dto.CsTbConsulta;
import info.pedrodonte.util.GenericDAO;

import javax.ejb.Stateless;

@Stateless
public class CsTbConsultaDAO extends GenericDAO<CsTbConsulta, Long>{ 
	
	public CsTbConsultaDAO() { 
		super(CsTbConsulta.class); 
	}
	
}
