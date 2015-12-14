package ages.exception;

public class EleveDSCompletException extends AgesException{

private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructeur de base, avec message d'erreur en param�tre
	 * @param msg message d'erreur
	 */
	public EleveDSCompletException(String matricule) {
		super("Enregistrement non Effectu�, cause: '"+matricule+"' a d�ja pay� tous ses droits");
	}
	
	/**
	 * Constructeur avec cause d'erreur en parametre.
	 * 
	 * @param msg the error message associated with the exception
	 * @param cause the root cause of the exception
	 */
	public EleveDSCompletException(String matricule, Throwable cause) {
		super(matricule, cause);
	}

}
