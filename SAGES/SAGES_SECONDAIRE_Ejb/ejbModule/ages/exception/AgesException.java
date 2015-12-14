package ages.exception;

public class AgesException extends Exception{
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de base, avec message d'erreur en paramètre
	 * @param msg message d'erreur
	 */
	public AgesException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructeur avec cause d'erreur en parametre.
	 * 
	 * @param msg the error message associated with the exception
	 * @param cause the root cause of the exception
	 */
	public AgesException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
