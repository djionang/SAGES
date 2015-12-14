package ages.exception;

public class EleveDSCompletException extends AgesException{

private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructeur de base, avec message d'erreur en paramètre
	 * @param msg message d'erreur
	 */
	public EleveDSCompletException(String matricule) {
		super("Enregistrement non Effectué, cause: '"+matricule+"' a déja payé tous ses droits");
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
