package modulo.usuarios.ejb;

import java.util.List;

import javax.ejb.Local;

import util.CrudGenericServiceApi;
import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.usuarios.vo.VoRol;
import modulo.usuarios.vo.VoUser;

@Local
public interface RolEJB extends CrudGenericServiceApi<VoRol> {

	public List<VoRol> obtenerAsociadosUsuario(VoUser user)
			throws ErrorDelSistemaException;

	public List<VoRol> obtenerNoAsociadosUsuario(VoUser user)
			throws ErrorDelSistemaException;

}
