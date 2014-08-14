package info.pedrodonte.email.dto;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class EmailDTO implements java.io.Serializable{
	
	private static final long serialVersionUID = 161848263L;
	
	private String asunto = "";
	private String cuerpo = "";
	private String destinatarios = "";

	private InternetAddress[] destinatariosArray;
	

	/**
	 * Los destinatarios deben ir separados por comas(,) El formato de los destinatarios es el siguiente:<br/>
	 *  <b>Ejemplo 1</b> <br>
	 *  pedrodonte@gmail.com, pcarrasco@xpi.cl, pascuero@viejos@com <br/>
	 *  
	 *  <b>Ejemplo 2</b> <br>
	 *  Pedrodonte &lt;pedrodonte@gmail.com&gt;, Pedro Carrasco&lt;pcarrasco@xpi.cl&gt;, Viejo Pascuero&lt;pascuero@viejos@com&gt; <br/>
	 *  
	 * @param asunto
	 * @param cuerpo
	 * @param destinatarios
	 * 
	 */
	public EmailDTO(String asunto, String cuerpo, String destinatarios) {
		super();
		this.asunto = asunto;
		this.cuerpo = cuerpo;
		this.destinatarios = destinatarios;
	}
	
	public boolean esValido(){
		boolean validez = true;
		
		try {
			
			asunto = asunto.trim();
			cuerpo = cuerpo.trim();
			destinatarios = destinatarios.trim();
			
			if(asunto.equals("")){
				validez = false;
			}
			
			if(cuerpo.equals("")){
				validez = false;
			}
			
			if(destinatarios.equals("")){
				validez = false;
			}
			
			destinatariosArray = getDestinatariosArray();
			
		} catch (Exception e) {
			validez = false;
		}
		
		return validez;
	}
	
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public InternetAddress[] getDestinatariosArray() throws AddressException {
		destinatariosArray = InternetAddress.parse(destinatarios);
		return destinatariosArray;
	}
	
	public void setDestinatariosArray(InternetAddress[] destinatariosArray) {
	}
	public String getDestinatarios() {
		return destinatarios;
	}
	
	
	/** Los destinatarios deben ir separados por comas(,) El formato de los destinatarios es el siguiente:<br/>
	 *  <b>Ejemplo 1</b> <br>
	 *  pedrodonte@gmail.com, pcarrasco@xpi.cl, pascuero@viejos@com <br/>
	 *  
	 *  <b>Ejemplo 2</b> <br>
	 *  Pedrodonte &lt;pedrodonte@gmail.com&gt;, Pedro Carrasco&lt;pcarrasco@xpi.cl&gt;, Viejo Pascuero&lt;pascuero@viejos@com&gt; <br/>
	 *  
	 *  
	 * @param destinatarios
	 */
	public void setDestinatarios(String destinatarios) {
		this.destinatarios = destinatarios;
	}

	@Override
	public String toString() {
		return "EmailDTO [asunto=" + asunto + ", cuerpo=" + cuerpo	+ ", destinatariosArray=" + destinatariosArray.length + "]";
	}

}
