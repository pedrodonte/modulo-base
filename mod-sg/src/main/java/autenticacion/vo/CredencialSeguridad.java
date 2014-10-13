package autenticacion.vo;

import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.util.HelperString;

import java.io.Serializable;

public class CredencialSeguridad implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;

	public CredencialSeguridad(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordEncripted() throws ErrorDelSistemaException{
		try {
			return HelperString.encrypt(password);
		} catch (Exception e) {
			throw new ErrorDelSistemaException("Problemas con la encriptación de la palabra");
		}
	}

	@Override
	public String toString() {
		return "CredencialSeguridad [username=" + username + ", password="
				+ password + "]";
	}

}
