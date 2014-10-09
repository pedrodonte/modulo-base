package seguridad.impl;

import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import seguridad.CredencialSeguridad;
import seguridad.IValidacionCredencial;
import seguridad.ValidacionNegativaException;
import seguridad.api.LoginValidaCredencialEJB;

@Stateless
public class LoginValidaCredencialEJBImpl implements LoginValidaCredencialEJB {
	
	Logger logger = Logger.getLogger(getClass());
	
	private boolean usuarioEncontrado = false;
	
	IValidacionCredencial validaExistencia = new IValidacionCredencial() {

		@Override
		public void executaValidacion(CredencialSeguridad credencialSeguridad)
				throws ValidacionNegativaException {
			
			if (usuarioEncontrado) {
				throw new ValidacionNegativaException("registro no encontrado con el email ingresado.");
			}

		}
	};

	IValidacionCredencial validaPassword = new IValidacionCredencial() {

		@Override
		public void executaValidacion(CredencialSeguridad credencialSeguridad)
				throws ValidacionNegativaException {
			
			if (usuarioEncontrado) {
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
