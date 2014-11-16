package modulo.autenticacion.interfaces;

import modulo.autenticacion.excepciones.ValidacionNegativaException;
import modulo.autenticacion.vo.CredencialSeguridad;

public interface IValidacionCredencial {
	
	public void executaValidacion(CredencialSeguridad credencialSeguridad) throws ValidacionNegativaException;

}
