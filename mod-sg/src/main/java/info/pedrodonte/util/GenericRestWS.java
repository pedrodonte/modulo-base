package info.pedrodonte.util;

import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.protask.excepciones.RegistrosNoEncontradosException;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

/**
 * <p>
 *   A number of RESTful services implement GET operations on a particular type of entity. For
 *   observing the DRY principle, the generic operations are implemented in the <code>BaseEntityService</code>
 *   class, and the other services can inherit from here.
 * </p>
 *
 * <p>
 *    Subclasses will declare a base path using the JAX-RS {@link Path} annotation, for example:
 * </p>
 *
 * <pre>
 * <code>
 * &#064;Path("/widgets")
 * public class WidgetService extends BaseEntityService<Widget> {
 * ...
 * }
 * </code>
 * </pre>
 *
 * <p>
 *   will support the following methods:
 * </p>
 *
 * <pre>
 * <code>
 *   GET /widgets
 *   GET /widgets/:id
 *   GET /widgets/count
 * </code>
 * </pre>
 *
 *  <p>
 *     Subclasses may specify various criteria for filtering entities when retrieving a list of them, by supporting
 *     custom query parameters. Pagination is supported by default through the query parameters <code>first</code>
 *     and <code>maxResults</code>.
 * </p>
 *
 * <p>
 *     The class is abstract because it is not intended to be used directly, but subclassed by actual JAX-RS
 *     endpoints.
 * </p>
 *

 * @author Marius Bogoevici
 */
public abstract class GenericRestWS<T> {

    private CrudGenericServiceApi<T> crudGenericServiceApi;

    public CrudGenericServiceApi<T> getCrudGenericServiceApi() {
		return crudGenericServiceApi;
	}


	public void setCrudGenericServiceApi(
			CrudGenericServiceApi<T> crudGenericServiceApi) {
		this.crudGenericServiceApi = crudGenericServiceApi;
	}


    /**
     * <p>
     *   A method for retrieving all entities of a given type. Supports the query parameters <code>first</code>
     *   and <code>maxResults</code> for pagination.
     * </p>
     *
     * @param uriInfo application and request context information (see {@see UriInfo} class information for more details)
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<T> getAll(@Context UriInfo uriInfo) {
    	System.out.println("getAll");
        return getAll(uriInfo.getQueryParameters());
    }
    
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<T> getAll() {
//    	try {
//			return crudGenericServiceApi.obtenerRegistros();
//		} catch (ErrorDelSistemaException | RegistrosNoEncontradosException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	return null;
//    }
    
    public List<T> getAll(MultivaluedMap<String, String> queryParameters) {
        try {
			return crudGenericServiceApi.obtenerRegistros();
		} catch (ErrorDelSistemaException | RegistrosNoEncontradosException e) {
			e.printStackTrace();
		}
		return null;
    }

    /**
     * <p>
     *   A method for counting all entities of a given type
     * </p>
     *
     * @param uriInfo application and request context information (see {@see UriInfo} class information for more details)
     * @return
     */
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Long> getCount(@Context UriInfo uriInfo) {
         try {
			crudGenericServiceApi.obtenerRegistros().size();
		} catch (ErrorDelSistemaException | RegistrosNoEncontradosException e) {
			e.printStackTrace();
		}
         return null;
    }

    /**
     * <p>
     *     Metodo que retorna una instancia individial de un registros.
     * </p>
     * @param id entidad id
     * @return
     */
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public T getSingleInstance(@PathParam("id") Long id) {
        try {
			return crudGenericServiceApi.obtenerRegistroPorID(id);
		} catch (ErrorDelSistemaException | RegistrosNoEncontradosException e) {
			e.printStackTrace();
		}
        return null;
    }

}
