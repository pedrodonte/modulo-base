package modulo.autenticacion.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;

import modulo.autenticacion.excepciones.ValidacionNegativaException;
import modulo.autenticacion.interfaces.IValidacionCredencial;
import modulo.autenticacion.vo.CredencialSeguridad;
import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.usuarios.ejb.UserEJB;
import modulo.usuarios.vo.VoUser;

import org.apache.log4j.Logger;

import util.HelperString;

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
			
			try {
				if (!usuario.getClave().equals(HelperString.encrypt(credencialSeguridad.getPassword()))) {
					throw new ValidacionNegativaException("registro no encontrado con el email ingresado.");
				}
			}catch(ValidacionNegativaException e){
				logger.info(credencialSeguridad.getPassword());
				try {
					logger.info(HelperString.encrypt(credencialSeguridad.getPassword()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				throw e;
			} catch (Exception e) {
				throw new ValidacionNegativaException("Error en la encriptación");
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
