package modulo.autenticacion.ejb;

import modulo.autenticacion.excepciones.ValidacionNegativaException;
import modulo.autenticacion.vo.CredencialSeguridad;
import modulo.base.excepciones.ErrorDelSistemaException;

public interface LoginValidaCredencialEJB {
	
	public boolean validaCredencial(CredencialSeguridad credencialSeguridad) throws ValidacionNegativaException, ErrorDelSistemaException;

}
