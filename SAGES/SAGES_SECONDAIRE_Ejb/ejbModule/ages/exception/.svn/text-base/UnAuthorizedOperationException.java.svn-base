package ages.exception;

public class UnAuthorizedOperationException extends AgesException{
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructeur de base, avec message d'erreur en paramètre
	 * @param msg message d'erreur
	 */
	public UnAuthorizedOperationException(String operation,String user) {
		super("User: "+user+" non autorisé a effectuer l'operation "+operation);
	}
	
	/**
	 * Constructeur avec cause d'erreur en parametre.
	 * 
	 * @param msg the error message associated with the exception
	 * @param cause the root cause of the exception
	 */
	public UnAuthorizedOperationException(String user,String op, Throwable cause) {
		super("User"+user+" a tenté avec echec d'effectuer l'operation "+op, cause);
	}
}
