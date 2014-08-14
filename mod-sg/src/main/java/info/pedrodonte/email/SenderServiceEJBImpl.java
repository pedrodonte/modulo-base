package info.pedrodonte.email;

import info.pedrodonte.email.dto.EmailDTO;
import info.pedrodonte.email.exception.EmailInvalidoException;
import info.pedrodonte.email.exception.SendException;

import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

@Stateless
public class SenderServiceEJBImpl implements SenderServiceEJB {

	Logger logger = Logger.getLogger(getClass());

	private static final String USERNAME = "develop.sender.mail@gmail.com";
	private static final String PASSWORD = "clave.qaws";
	private static final String SENDER = "Envío Automático<develop.sender.mail@gmail.com>";

	private static final boolean ENVIAR_EMAIL_HABILITADO = true;
	
	Session session = null;

	@Override
	public boolean enviarEmail(EmailDTO correoElectronico) throws SendException, EmailInvalidoException {
		
		boolean exitoOperacion = false;
		
		logger.debug("Enviando un correo...");
		
		if(!correoElectronico.esValido()){
			throw new EmailInvalidoException("Uno de los campos del correo electronico no cumple con los formatos establecidos.");
		}

		try {

			Properties props = new Properties();
			props.put("mail.smtp.host", "173.194.68.108");//smtp.gmail.com
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			if (session != null) {
				session = Session.getDefaultInstance(props,
						new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(USERNAME,
										PASSWORD);
							}
						});
			}
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SENDER));
			message.setRecipients(Message.RecipientType.TO,	correoElectronico.getDestinatariosArray());
			message.setSubject(correoElectronico.getAsunto());
			message.setContent(correoElectronico.getCuerpo(), "text/html; charset=utf-8");
			
			logger.debug("Enviando: "+correoElectronico);

			if(ENVIAR_EMAIL_HABILITADO){
				Transport.send(message);
			}
			exitoOperacion = true;
			
			
		} catch (AddressException e) {
			throw new SendException("Una dirección de correo esta mal formada, revice porfavor");
		} catch (MessagingException e) {
			e.printStackTrace();
			//throw new SendException("Error al enviar el correo, revice la configuración.");
		} catch (Exception e) {
			logger.error("Error inesperado al enviar Email", e);
			throw new SendException("Ocurre un error inesperado, contacte al administrador de sistema");
		}

		return exitoOperacion;
	}
	
	

}
