package autenticacion.interfaces;

import autenticacion.excepciones.ValidacionNegativaException;
import autenticacion.vo.CredencialSeguridad;

public interface IValidacionCredencial {
	
	public void executaValidacion(CredencialSeguridad credencialSeguridad) throws ValidacionNegativaException;

}
