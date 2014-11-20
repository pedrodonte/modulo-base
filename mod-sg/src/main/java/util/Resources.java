package util;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class Resources {
	
	@PersistenceContext(  
			  unitName="mod-sg",  
			  type=PersistenceContextType.TRANSACTION) 
	@Produces
	EntityManager em;
	
	@Resource(lookup="java:jboss/datasources/modBaseDS")
	@Produces
	DataSource modBaseDS;

	@Produces
	Logger getLogger(InjectionPoint ip) {
		String category = ip.getMember().getDeclaringClass().getName();
		return Logger.getLogger(category);
	}

	@Produces
	FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
}
