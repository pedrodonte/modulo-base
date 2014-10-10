package autenticacion.ejb;

import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import autenticacion.excepciones.ValidacionNegativaException;
import autenticacion.vo.CredencialSeguridad;

public interface LoginValidaCredencialEJB {
	
	public boolean validaCredencial(CredencialSeguridad credencialSeguridad) throws ValidacionNegativaException, ErrorDelSistemaException;

}
