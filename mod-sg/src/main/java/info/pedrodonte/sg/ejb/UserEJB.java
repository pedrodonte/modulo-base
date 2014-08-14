
package info.pedrodonte.sg.ejb;

import javax.ejb.Local;

import info.pedrodonte.protask.excepciones.RegistrosNoEncontradosException;
import info.pedrodonte.sg.vo.VoUser;
import info.pedrodonte.util.CrudGenericServiceApi;

@Local
public interface UserEJB extends CrudGenericServiceApi<VoUser>{
	
	public void generarContrasena(VoUser usuario) throws RegistrosNoEncontradosException;
	public void cambiarContrasena(VoUser usuario, String contrasena, String confirmacion) throws RegistrosNoEncontradosException;

}
