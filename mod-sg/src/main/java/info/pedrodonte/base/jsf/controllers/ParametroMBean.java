package info.pedrodonte.base.jsf.controllers;

import info.pedrodonte.base.ejb.ParametroEJB;
import info.pedrodonte.base.vo.VoParametro;
import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.protask.excepciones.RegistrosNoEncontradosException;
import info.pedrodonte.util.AbsMantenedorMB;
import info.pedrodonte.util.CrudGenericServiceApi;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ParametroMBean extends AbsMantenedorMB<VoParametro> {

	private static final long serialVersionUID = 123456789L;

	private String NOMBRE_SISTEMA = "";
	private String SISTEMA_VERSION = "";
	private String FORMATO_FECHA_FULL = "";
	private String CORREO_CASILLA = "";
	private String CORREO_USUARIO = "";
	private String CORREO_CONTRASENA = "";
	private String CANTIDAD_REGISTROS_TABLA = "";
	private String CONSULTA_URL_CERTPREV = "";

	@Inject
	private ParametroEJB serviceEJB; // EJB

	public ParametroMBean() {
		super(VoParametro.class);
	}

	@Override
	public CrudGenericServiceApi<VoParametro> asignarCrudService() {
		return serviceEJB;
	}
	
	

	public String getNOMBRE_SISTEMA() {
		return NOMBRE_SISTEMA;
	}

	public String getSISTEMA_VERSION() {
		return SISTEMA_VERSION;
	}

	public String getFORMATO_FECHA_FULL() {
		return FORMATO_FECHA_FULL;
	}

	public String getCORREO_CASILLA() {
		return CORREO_CASILLA;
	}

	public String getCORREO_USUARIO() {
		return CORREO_USUARIO;
	}

	public String getCORREO_CONTRASENA() {
		return CORREO_CONTRASENA;
	}

	public String getCANTIDAD_REGISTROS_TABLA() {
		return CANTIDAD_REGISTROS_TABLA;
	}

	public String getCONSULTA_URL_CERTPREV() {
		return CONSULTA_URL_CERTPREV;
	}

	public ParametroEJB getServiceEJB() {
		return serviceEJB;
	}

	@Override
	public void inicializarVariablesInstancia() {

		super.inicializarVariablesInstancia();
		
		try {
			NOMBRE_SISTEMA = serviceEJB.obtenerRegistroPorID(ParametroEJB.P_NOMBRE_SISTEMA).getValor();
			SISTEMA_VERSION = serviceEJB.obtenerRegistroPorID(ParametroEJB.P_SISTEMA_VERSION).getValor();
		} catch (ErrorDelSistemaException e) {
			e.printStackTrace();
		} catch (RegistrosNoEncontradosException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
