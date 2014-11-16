package modulo.email.exception;

public class EmailInvalidoException extends Exception {

	private static final long serialVersionUID = 6517069626506702802L;

	public EmailInvalidoException() {
		super();
	}

	public EmailInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailInvalidoException(String message) {
		super(message);
	}

	public EmailInvalidoException(Throwable cause) {
		super(cause);
	}
	
	

}
