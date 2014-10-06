package info.pedrodonte.consultas.jsf.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import fns.Certificado;
import fns.ClienteCertificadorURL;
import info.pedrodonte.base.ejb.PersonaEJB;
import info.pedrodonte.base.vo.VoPersona;
import info.pedrodonte.consultas.ejb.ConsultaEJB;
import info.pedrodonte.consultas.vo.VoConsulta;
import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.protask.excepciones.RegistrosNoEncontradosException;
import info.pedrodonte.util.AbsMantenedorMB;
import info.pedrodonte.util.CrudGenericServiceApi;
import info.pedrodonte.util.HelperString;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
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
				
				personaEnConsulta = buscarEnWSFns(cpoIdentificador);
				
				mensajesMB.msgWarn("La persona NO esta registrada, para continuar debe llenar el formulario de Persona");
				//mensajesMB.devolverParametro(nombreParametro, valorParamtro);
				RequestContext.getCurrentInstance().update("@(.registro-formulario)");
			}
		} catch (RegistrosNoEncontradosException | ErrorDelSistemaException e) {
			e.printStackTrace();
		}
	}
	
	private VoPersona buscarEnWSFns(String identi) {
		ClienteCertificadorURL basePersonas = new ClienteCertificadorURL();
		
		VoPersona persona = new VoPersona();
		try {
			String dgv = identi.split("-")[1];
			int rut = Integer.parseInt(identi.split("-")[0]);
			
			Certificado data = basePersonas.consultar(61607400, rut, dgv);
			
			persona = new VoPersona();
			persona.setApellidos( HelperString.cambioCharsetToUTF8(data.getApell1()+" "+data.getApell2()) );
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			persona.setFechaNacimiento(formatter.parse(data.getFec_nac()));
			persona.setNombres(HelperString.cambioCharsetToUTF8(data.getNombres()));
			
			if (data.getSex().equals("M")) {
				persona.setSexo(2);
			}else{
				persona.setSexo(1);
			}
			
		} catch (Exception e) {
			mensajesMB.msgWarn("Falla consulta a WS, se debe llenar manualmente.");
		}
		
		persona.setIdentificador(identi.toUpperCase());
		return persona;
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
		
		//Evento que ocurre despues de llenar el formulario de persona
		if (event.getOldStep().equals("persona")) {
			if (personaEnConsulta.getIdPersona() == 0) {
				mensajesMB.msgWarn("La persona NO esta registrada, para continuar debe llenar el formulario de Persona");
				return event.getOldStep();
			}else{
				doNuevoRegistroFormulario(null);
				getRegistroEnEdicion().setFechaConsulta(new Date());
				getRegistroEnEdicion().setTxtDesarrollo("");
				getRegistroEnEdicion().setVoPersona(personaEnConsulta);
			}
		}
		
		//Evento que ocurre despues de llenar el desarrollo de la consulta
		if (event.getOldStep().equals("consulta")) {
			if (getRegistroEnEdicion().getTxtDesarrollo().trim().isEmpty()) {
				mensajesMB.msgError("El texto del desarrollo esta vacío");
				return event.getOldStep();
			}else{
				doGuardarRegistroFormulario(null);
			}
		}
		
		//NO permitir cambiar la consulta realizada el objeto persona.
		if (event.getOldStep().equals("consulta") & event.getNewStep().equals("persona")) {
			return event.getOldStep();
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
