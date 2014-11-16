package modulo.email;

import javax.ejb.Local;

import modulo.email.dto.EmailDTO;
import modulo.email.exception.EmailInvalidoException;
import modulo.email.exception.SendException;

@Local
public interface SenderServiceEJB {
	
	public boolean enviarEmail(EmailDTO correoElectronico) throws SendException, EmailInvalidoException;

}
