package modulo.email.exception;

public class SendException extends Exception {

	private static final long serialVersionUID = 161848263L;

	public SendException() {
		super();
	}

	public SendException(String message, Throwable cause) {
		super(message, cause);
	}

	public SendException(String message) {
		super(message);
	}

	public SendException(Throwable cause) {
		super(cause);
	}

}
