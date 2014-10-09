package seguridad.api;

import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;

import javax.ejb.Local;

import seguridad.CredencialSeguridad;
import seguridad.ValidacionNegativaException;

@Local
public interface LoginValidaCredencialEJB {
	
	public boolean validaCredencial(CredencialSeguridad credencialSeguridad) throws ValidacionNegativaException, ErrorDelSistemaException;

}
