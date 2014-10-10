package autenticacion.ejb;

import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.protask.excepciones.RegistrosNoEncontradosException;
import info.pedrodonte.sg.ejb.UserEJB;
import info.pedrodonte.sg.vo.VoUser;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import autenticacion.excepciones.ValidacionNegativaException;
import autenticacion.interfaces.IValidacionCredencial;
import autenticacion.vo.CredencialSeguridad;

@Stateless
public class LoginValidaCredencialEJBImpl implements LoginValidaCredencialEJB {
	
	Logger logger = Logger.getLogger(getClass());
	
	private VoUser usuario = new VoUser();
	
	@Inject
	UserEJB userEJB;
	
	IValidacionCredencial validaExistencia = new IValidacionCredencial() {

		@Override
		public void executaValidacion(CredencialSeguridad credencialSeguridad)
				throws ValidacionNegativaException {
			
			try {
				usuario=userEJB.obtenerRegistroPorIdentificador(credencialSeguridad.getUsername());
			} catch (RegistrosNoEncontradosException e) {
				throw new ValidacionNegativaException("registro no encontrado con el email ingresado.");
			} catch (Exception e) {
				throw new ValidacionNegativaException("Error Inesperado");
			}
			
		}
	};

	IValidacionCredencial validaPassword = new IValidacionCredencial() {

		@Override
		public void executaValidacion(CredencialSeguridad credencialSeguridad)
				throws ValidacionNegativaException {
			
			if (!usuario.getClave().equals(credencialSeguridad.getPassword())) {
				throw new ValidacionNegativaException("registro no encontrado con el email ingresado.");
			}
			
		}
	};

	IValidacionCredencial validaEstado = new IValidacionCredencial() {

		@Override
		public void executaValidacion(CredencialSeguridad credencialSeguridad)
				throws ValidacionNegativaException {
			// TODO Auto-generated method stub

		}
	};

	@Override
	public boolean validaCredencial(CredencialSeguridad credencialSeguridad)
			throws ValidacionNegativaException, ErrorDelSistemaException {

		try {
			validaExistencia.executaValidacion(credencialSeguridad);
			validaPassword.executaValidacion(credencialSeguridad);
			validaEstado.executaValidacion(credencialSeguridad);
			
			logger.info("Credencial Valida:"+credencialSeguridad.toString());
			
			return true;
		} catch (ValidacionNegativaException e) {
			throw e;
		} catch (Exception e) {
			throw new ErrorDelSistemaException("Error irecuperable del sistema");
		}
		
	}

}
