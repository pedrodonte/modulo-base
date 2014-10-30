package info.pedrodonte.base.ejb;

import info.pedrodonte.base.vo.VoParametro;
import info.pedrodonte.util.CrudGenericServiceApi;

public interface ParametroEJB extends CrudGenericServiceApi<VoParametro>{

	public static long P_NOMBRE_SISTEMA = 1;
	public static long P_SISTEMA_VERSION = 2;
	public static long P_FORMATO_FECHA_FULL = 3;
	public static long P_CORREO_CASILLA = 4;
	public static long P_CORREO_USUARIO = 5;
	public static long P_CORREO_CONTRASENA = 6;
	public static long P_CANTIDAD_REGISTROS_TABLA = 7;
	public static long P_CONSULTA_URL_CERTPREV = 8;


}
