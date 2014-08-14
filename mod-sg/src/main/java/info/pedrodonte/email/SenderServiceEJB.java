package info.pedrodonte.email;

import info.pedrodonte.email.dto.EmailDTO;
import info.pedrodonte.email.exception.EmailInvalidoException;
import info.pedrodonte.email.exception.SendException;

import javax.ejb.Local;

@Local
public interface SenderServiceEJB {
	
	public boolean enviarEmail(EmailDTO correoElectronico) throws SendException, EmailInvalidoException;

}
