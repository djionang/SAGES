package ages.exception;

public class UnknownUserException extends AgesException{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructeur de base, avec message d'erreur en paramètre
	 * @param msg message d'erreur
	 */
	public UnknownUserException(String username) {
		super("Utilisateur "+username+" inconnu");
	}
	
	/**
	 * Constructeur avec cause d'erreur en parametre.
	 * 
	 * @param msg the error message associated with the exception
	 * @param cause the root cause of the exception
	 */
	public UnknownUserException(String username, Throwable cause) {
		super("Utilisateur "+username+" inconnu", cause);
	}

}
