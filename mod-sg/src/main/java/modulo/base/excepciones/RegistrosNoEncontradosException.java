package modulo.base.excepciones;

public class RegistrosNoEncontradosException extends Exception {

	private static final long serialVersionUID = 1L;

	public RegistrosNoEncontradosException(String message, Throwable cause) {
		super(message, cause);
	}

	public RegistrosNoEncontradosException(String message) {
		super(message);
	}

}
