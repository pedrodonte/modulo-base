package util;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.RegistrosNoEncontradosException;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public abstract class AbsMantenedorMB<TIPO_DTO extends Cloneable> implements
		Serializable {

	protected enum ModosEdicion {
		OFF, EDIT, NEW
	};

	private Logger logger = Logger.getLogger(getClass());

	private boolean mostrarFormulario = false;
	private ModosEdicion modoFormulario = ModosEdicion.OFF;
	private List<TIPO_DTO> registros = new ArrayList<TIPO_DTO>();
	private CrudGenericServiceApi<TIPO_DTO> crudService;
	private Class<TIPO_DTO> itemClass;
	private TIPO_DTO registroSeleccionado;
	private TIPO_DTO registroEnEdicion;
	private int registrosCantidad;

	protected JsfUtil mensajesMB = new JsfUtil();

	public AbsMantenedorMB() {
	}

	public AbsMantenedorMB(Class<TIPO_DTO> itemClass) {
		this.itemClass = itemClass;
	}

	@PostConstruct
	public void inicializarVariablesInstancia() {
		setCrudService(asignarCrudService());
		try {
			doBuscarRegistros(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doHabilitarEdicion(ActionEvent event) {
		logger.info("Habilitando edición");
		setMostrarFormulario(true);
		setModoFormulario(ModosEdicion.EDIT);
	}

	@SuppressWarnings("unchecked")
	public void doVerRegistroFormulario(ActionEvent event) {
		logger.info("Ver Registro formulario");
		try {
			setRegistroEnEdicion((TIPO_DTO) itemClass.getMethod("clone")
					.invoke(registroSeleccionado));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		setMostrarFormulario(true);
		setModoFormulario(ModosEdicion.OFF);
	}

	/**
	 * Se setea un valor inicial en Registro en Edicion
	 * @param event
	 */
	public void doNuevoRegistroFormulario(ActionEvent event) {
		logger.info("Creando nuevo registro.");
		try {
			setRegistroEnEdicion(itemClass.newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NullPointerException e){
			e.printStackTrace();
		}
		setMostrarFormulario(true);
		setModoFormulario(ModosEdicion.NEW);
	}

	@SuppressWarnings("unchecked")
	public void doEditarRegistroFormulario(ActionEvent event) {
		logger.info("Editar registro formulario");
		try {
			setRegistroEnEdicion((TIPO_DTO) itemClass.getMethod("clone")
					.invoke(registroSeleccionado));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		setMostrarFormulario(true);
		setModoFormulario(ModosEdicion.EDIT);
	}

	public void doGuardarRegistroFormulario(ActionEvent event) {
		boolean codExitoOperacion = false;
		logger.info("Guardando cambios en registro:" + registroEnEdicion);
		logger.info("Modo Formulario:" + modoFormulario);
		try {

			if (modoFormulario.equals(ModosEdicion.EDIT)) {
				registroEnEdicion=crudService.actualizarRegistro(registroEnEdicion);
			} else if (modoFormulario.equals(ModosEdicion.NEW)) {
				registroEnEdicion=crudService.nuevoRegistro(registroEnEdicion);
			}
			doBuscarRegistros(event);
			setMostrarFormulario(false);
			setModoFormulario(ModosEdicion.OFF);
			inicializarVariablesInstancia();
			codExitoOperacion = true;
		} catch (Exception e) {
			e.printStackTrace();
			mensajesMB.msgFatal("Error al guardar el Registro");
		}
		mensajesMB.devolverParametro("codExitoOperacion", codExitoOperacion);
	}

	public void doCancelarRegistroFormulario(ActionEvent event) {
		setMostrarFormulario(false);
		setModoFormulario(ModosEdicion.OFF);
	}

	private void doBuscarRegistros(ActionEvent event)
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {
		registros = crudService.obtenerRegistros();
		registrosCantidad = registros.size();
	}
	
	public void mostrarMensaje(String msg){
		mensajesMB.msgInfo(msg);
	}

	public abstract CrudGenericServiceApi<TIPO_DTO> asignarCrudService();

	public boolean isMostrarFormulario() {
		return mostrarFormulario;
	}

	public void setMostrarFormulario(boolean mostrarFormulario) {
		this.mostrarFormulario = mostrarFormulario;
	}

	public ModosEdicion getModoFormulario() {
		return modoFormulario;
	}

	public void setModoFormulario(ModosEdicion modoFormulario) {
		this.modoFormulario = modoFormulario;
	}

	public List<TIPO_DTO> getRegistros() {
		return registros;
	}

	public void setRegistros(List<TIPO_DTO> registros) {
		this.registros = registros;
	}

	public TIPO_DTO getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public void setRegistroSeleccionado(TIPO_DTO registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}

	public TIPO_DTO getRegistroEnEdicion() {
		return registroEnEdicion;
	}

	public void setRegistroEnEdicion(TIPO_DTO registroEnEdicion) {
		this.registroEnEdicion = registroEnEdicion;
	}

	public int getRegistrosCantidad() {
		registrosCantidad = registros.size();
		return registrosCantidad;
	}

	public void setRegistrosCantidad(int registrosCantidad) {
		this.registrosCantidad = registrosCantidad;
	}

	public CrudGenericServiceApi<TIPO_DTO> getCrudService() {
		return crudService;
	}

	public void setCrudService(CrudGenericServiceApi<TIPO_DTO> crudService) {
		this.crudService = crudService;
	}

	public Class<TIPO_DTO> getItemClass() {
		return itemClass;
	}

	public void setItemClass(Class<TIPO_DTO> itemClass) {
		this.itemClass = itemClass;
	}

}
