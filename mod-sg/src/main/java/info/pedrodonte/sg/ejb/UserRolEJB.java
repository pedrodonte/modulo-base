
package info.pedrodonte.sg.ejb;

import javax.ejb.Local;

import info.pedrodonte.sg.vo.VoUserRol;
import info.pedrodonte.util.CrudGenericServiceApi;

@Local
public interface UserRolEJB extends CrudGenericServiceApi<VoUserRol>{

}
