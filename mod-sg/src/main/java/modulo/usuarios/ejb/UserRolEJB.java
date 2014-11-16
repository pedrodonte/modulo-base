
package modulo.usuarios.ejb;

import javax.ejb.Local;

import util.CrudGenericServiceApi;
import modulo.usuarios.vo.VoUserRol;

@Local
public interface UserRolEJB extends CrudGenericServiceApi<VoUserRol>{

}
