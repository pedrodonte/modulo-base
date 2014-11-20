package modulo.consulta_medica.jsf.controllers;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import modulo.base.ejb.PersonaEJB;
import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.base.vo.VoPersona;
import modulo.consulta_medica.ejb.ConsultaEJB;
import modulo.consulta_medica.vo.VoConsulta;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

import util.AbsMantenedorMB;
import util.CrudGenericServiceApi;

@Named
@SessionScoped
public class ConsultaMBean extends AbsMantenedorMB<VoConsulta> { 

	private static final long serialVersionUID = 123456789L;

	private Logger logger = Logger.getLogger(getClass());

	@Inject
	private ConsultaEJB serviceEJB;

	@Inject
	private PersonaEJB personaEJB;

	private VoPersona personaEnConsulta;
	private String cpoIdentificador;

	public ConsultaMBean() {
		super(VoConsulta.class);
		personaEnConsulta = new VoPersona();
	}

	@Override
	public CrudGenericServiceApi<VoConsulta> asignarCrudService() {
		return serviceEJB;
	}

	public void doBuscarPersona(ActionEvent event) {
		try {
			logger.info("doBuscarPersona:" + cpoIdentificador);
			personaEnConsulta = personaEJB
					.obtenerPorIdentificador(cpoIdentificador);
			
			RequestContext.getCurrentInstance().update("@(.registro-formulario)");
			
		} catch (ErrorDelSistemaException e) {
			super.mostrarMensaje(e.getMessage());
		} catch (RegistrosNoEncontradosException e) {
			super.mostrarMensaje(e.getMessage());
		}
	}

	

	public void doGuardarPersona(ActionEvent event) {
		try {
			logger.info("doGuardarPersona:"
					+ personaEnConsulta.getIdentificador());
			personaEnConsulta = personaEJB.nuevoRegistro(personaEnConsulta);
			System.out.println(personaEnConsulta);
			mensajesMB.msgInfo("Registro de Persona guardado");
		} catch (ErrorDelSistemaException e) {
			super.mostrarMensaje(e.getMessage());
		}
	}

	public String onFlowProcess(FlowEvent event) {
		logger.info("Old Step: " + event.getOldStep());
		logger.info("New Step: " + event.getNewStep());

		// Evento que ocurre despues de llenar el formulario de persona
		if (event.getOldStep().equals("persona")) {
			if (personaEnConsulta.getIdPersona() == 0) {
				mensajesMB
						.msgWarn("La persona NO esta registrada, para continuar debe llenar el formulario de Persona");
				return event.getOldStep();
			} else {
				doNuevoRegistroFormulario(null);
				getRegistroEnEdicion().setFechaConsulta(new Date());
				getRegistroEnEdicion().setTxtDesarrollo("");
				getRegistroEnEdicion().setVoPersona(personaEnConsulta);
				// MatenedorForm:wizardConsulta_back
				RequestContext.getCurrentInstance().addCallbackParam(
						"codExitoOperacion", true);
			}
		}

		// Evento que ocurre despues de llenar el desarrollo de la consulta ->
		if (event.getOldStep().equals("consulta")
				&& event.getNewStep().equals("observaciones")) {
			if (getRegistroEnEdicion().getTxtDesarrollo().trim().isEmpty()) {
				mensajesMB.msgError("El texto del desarrollo esta vacío");
				return event.getOldStep();
			} else {
				doGuardarRegistroFormulario(null);
			}
		}

		// NO permitir cambiar la consulta realizada el objeto persona. <-
		if (event.getOldStep().equals("consulta")
				&& event.getNewStep().equals("persona")) {
			logger.info(getRegistroEnEdicion());
			return event.getOldStep();
		}

		return event.getNewStep();
	}

	public void doFinalizarConsulta(ActionEvent event) {
		personaEnConsulta = new VoPersona();
		cpoIdentificador = "";
		doNuevoRegistroFormulario(event);
		mensajesMB.msgInfo("Consulta Finalizada");
	}

	public VoPersona getPersonaEnConsulta() {
		return personaEnConsulta;
	}

	public void setPersonaEnConsulta(VoPersona personaEnConsulta) {
		this.personaEnConsulta = personaEnConsulta;
	}

	public String getCpoIdentificador() {
		return cpoIdentificador;
	}

	public void setCpoIdentificador(String cpoIdentificador) {
		this.cpoIdentificador = cpoIdentificador;
	}
	
	
	public void doBuscarConsultas(ActionEvent event) {
		try {
			personaEnConsulta = personaEJB.obtenerPorIdentificador(cpoIdentificador);
			List<VoConsulta> consultas = serviceEJB.obtenerRegistrosPorPersona(personaEnConsulta.getIdPersona());
			
			
			if (getRegistrosCantidad() > 0) {
				mensajesMB.msgInfo("Cantidad de consultas : "+consultas.size());
				setRegistros(consultas);
				cpoIdentificador = "";
			}else{
				mensajesMB.msgInfo("Sin registros para el RUN: "+cpoIdentificador);
			}
			
			personaEnConsulta = new VoPersona();
			
		} catch (RegistrosNoEncontradosException e) {
			super.mostrarMensaje(e.getMessage());
		} catch (ErrorDelSistemaException e) {
			super.mostrarMensaje(e.getMessage());
		}
	}

}
