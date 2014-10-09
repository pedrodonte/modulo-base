package seguridad;

public class ValidacionNegativaException extends Exception {

	private static final long serialVersionUID = 1L;

	public ValidacionNegativaException() {
		super();
	}

	public ValidacionNegativaException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ValidacionNegativaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidacionNegativaException(String message) {
		super(message);
	}

	public ValidacionNegativaException(Throwable cause) {
		super(cause);
	}
	
	

}
