package info.pedrodonte.sg.ejb;

import java.util.List;

import javax.ejb.Local;

import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.sg.vo.VoRol;
import info.pedrodonte.sg.vo.VoUser;
import info.pedrodonte.util.CrudGenericServiceApi;

@Local
public interface RolEJB extends CrudGenericServiceApi<VoRol> {

	public List<VoRol> obtenerAsociadosUsuario(VoUser user)
			throws ErrorDelSistemaException;

	public List<VoRol> obtenerNoAsociadosUsuario(VoUser user)
			throws ErrorDelSistemaException;

}
