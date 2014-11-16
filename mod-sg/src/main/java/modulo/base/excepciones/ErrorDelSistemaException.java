package modulo.base.excepciones;

public class ErrorDelSistemaException extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorDelSistemaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErrorDelSistemaException(String message) {
		super(message);
	}

}
