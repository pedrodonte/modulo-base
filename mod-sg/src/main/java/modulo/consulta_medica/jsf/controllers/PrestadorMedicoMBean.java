package modulo.consulta_medica.jsf.controllers;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import modulo.base.ejb.PersonaEJB;
import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.base.vo.VoPersona;
import modulo.consulta_medica.ejb.PrestadorMedicoEJB;
import modulo.consulta_medica.vo.VoPrestadorMedico;
import modulo.usuarios.ejb.UserEJB;
import modulo.usuarios.vo.VoUser;

import org.apache.log4j.Logger;

import util.AbsMantenedorMB;
import util.CrudGenericServiceApi;

@Named
@SessionScoped
public class PrestadorMedicoMBean extends AbsMantenedorMB<VoPrestadorMedico> {
	
	private static final long serialVersionUID = 123456789L;

	Logger logger = Logger.getLogger(getClass());

	@Inject
	private PrestadorMedicoEJB serviceEJB;
	
	@Inject PersonaEJB personaEJB;
	
	@Inject UserEJB userEJB;

	private VoPersona personaEncontrada;
	private VoUser usuarioEncontrado;
	
	public PrestadorMedicoMBean() {
		super(VoPrestadorMedico.class);
	}

	@Override
	public CrudGenericServiceApi<VoPrestadorMedico> asignarCrudService() {
		return serviceEJB;
	}


	public void doBuscarPersona(ActionEvent event){
		try {
			setPersonaEncontrada(personaEJB.obtenerPorIdentificador(getRegistroEnEdicion().getRutPersona()));
			
			if (personaEncontrada != null) {
				getRegistroEnEdicion().setVoPersona(personaEncontrada);
				
				usuarioEncontrado = userEJB.obtenerRegistroPersona(personaEncontrada.getIdPersona());
				getRegistroEnEdicion().setVoUsuario(usuarioEncontrado);
			}
			
		} catch (RegistrosNoEncontradosException e) {
			super.mostrarMensaje(e.getMessage());
			super.mostrarMensaje("Para registrase como prestador debe exisitr como usuario en el sistema!");
		} catch (ErrorDelSistemaException e) {
			super.mostrarMensaje(e.getMessage());
		}
	}

	public VoPersona getPersonaEncontrada() {
		return personaEncontrada;
	}

	public void setPersonaEncontrada(VoPersona personaEncontrada) {
		this.personaEncontrada = personaEncontrada;
	}

	public VoUser getUsuarioEncontrado() {
		return usuarioEncontrado;
	}

	public void setUsuarioEncontrado(VoUser usuarioEncontrado) {
		this.usuarioEncontrado = usuarioEncontrado;
	}

}
