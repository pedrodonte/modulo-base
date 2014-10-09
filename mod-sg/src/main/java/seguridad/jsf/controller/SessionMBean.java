package seguridad.jsf.controller;


import info.pedrodonte.base.ejb.PersonaEJB;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

@Named
@SessionScoped
public class SessionMBean implements Serializable { // controller ,
																	// vo

	private static final long serialVersionUID = 123456789L;

	Logger logger = Logger.getLogger(getClass());


	@Inject
	private PersonaEJB personaEJB;


}
