package info.pedrodonte.consultas.jsf.controllers;

import java.util.Date;

import info.pedrodonte.base.ejb.PersonaEJB;
import info.pedrodonte.base.vo.VoPersona;
import info.pedrodonte.consultas.ejb.ConsultaEJB;
import info.pedrodonte.consultas.vo.VoConsulta;
import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.protask.excepciones.RegistrosNoEncontradosException;
import info.pedrodonte.util.AbsMantenedorMB;
import info.pedrodonte.util.CrudGenericServiceApi;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.event.FlowEvent;

@Named
@SessionScoped
public class ConsultaMBean extends AbsMantenedorMB<VoConsulta> { // controller ,
																	// vo

	private static final long serialVersionUID = 123456789L;
	
	private Logger logger = Logger.getLogger(getClass());

	@Inject
	private ConsultaEJB serviceEJB; // EJB
	
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
			logger.info("doBuscarPersona:"+cpoIdentificador);
			personaEnConsulta = personaEJB.obtenerPorIdentificador(cpoIdentificador);
			System.out.println(personaEnConsulta);
			if (personaEnConsulta == null) {
				personaEnConsulta = new VoPersona();
				personaEnConsulta.setIdentificador(cpoIdentificador);
				mensajesMB.msgWarn("La persona NO esta registrada, para continuar debe llenar el formulario de Persona");
			}
		} catch (RegistrosNoEncontradosException | ErrorDelSistemaException e) {
			e.printStackTrace();
		}
	}
	
	public void doGuardarPersona(ActionEvent event) {
		try {
			logger.info("doGuardarPersona:"+personaEnConsulta.getIdentificador());
			personaEnConsulta = personaEJB.nuevoRegistro(personaEnConsulta);
			System.out.println(personaEnConsulta);
			mensajesMB.msgInfo("Registro de Persona guardado");
		} catch (ErrorDelSistemaException e) {
			e.printStackTrace();
		}
	}

	public String onFlowProcess(FlowEvent event) {
		logger.info("Old Step: "+event.getOldStep());
		logger.info("New Step: "+event.getNewStep());
		
		if (event.getOldStep().equals("persona")) {
			if (personaEnConsulta.getIdPersona() == 0) {
				mensajesMB.msgWarn("La persona NO esta registrada, para continuar debe llenar el formulario de Persona");
				return event.getOldStep();
			}else{
				doNuevoRegistroFormulario(null);
				getRegistroEnEdicion().setFechaConsulta(new Date());
				getRegistroEnEdicion().setVoPersona(personaEnConsulta);
			}
		}
		
		if (event.getOldStep().equals("consulta")) {
			
		}
		
		return event.getNewStep();
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

}
