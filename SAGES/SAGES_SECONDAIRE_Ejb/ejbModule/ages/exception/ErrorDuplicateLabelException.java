package ages.exception;

public class ErrorDuplicateLabelException extends AgesException{
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructeur de base, avec message d'erreur en param�tre
	 * @param msg message d'erreur
	 */
	public ErrorDuplicateLabelException(String label) {
		super("Enregistrement non Effectu�e, cause: '"+label+"' dupliqu�");
	}
	
	/**
	 * Constructeur avec cause d'erreur en parametre.
	 * 
	 * @param msg the error message associated with the exception
	 * @param cause the root cause of the exception
	 */
	public ErrorDuplicateLabelException(String label, Throwable cause) {
		super(label, cause);
	}
}
