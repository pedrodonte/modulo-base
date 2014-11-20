
package modulo.usuarios.ejb;

import javax.ejb.Local;

import util.CrudGenericServiceApi;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.usuarios.vo.VoUser;

@Local
public interface UserEJB extends CrudGenericServiceApi<VoUser>{
	
	public void generarContrasena(VoUser usuario) throws RegistrosNoEncontradosException;
	public void cambiarContrasena(VoUser usuario, String contrasena, String confirmacion) throws RegistrosNoEncontradosException;
	public VoUser obtenerRegistroPorIdentificador(String username) throws RegistrosNoEncontradosException;
	public VoUser obtenerRegistroPersona(long idPersona) throws RegistrosNoEncontradosException;

}
