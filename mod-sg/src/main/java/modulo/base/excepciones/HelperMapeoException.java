package modulo.base.excepciones;

public class HelperMapeoException extends Exception {

	private static final long serialVersionUID = 5104394261560918486L;

	public HelperMapeoException() {
		super();
	}

	public HelperMapeoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public HelperMapeoException(String message, Throwable cause) {
		super(message, cause);
	}

	public HelperMapeoException(String message) {
		super(message);
	}

	public HelperMapeoException(Throwable cause) {
		super(cause);
	}
	
}
