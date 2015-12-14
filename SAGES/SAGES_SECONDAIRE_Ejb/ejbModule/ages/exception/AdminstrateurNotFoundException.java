package ages.exception;

public class AdminstrateurNotFoundException extends AgesException{
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructeur de base, avec message d'erreur en paramètre
	 * @param msg message d'erreur
	 */
	public AdminstrateurNotFoundException() {
		super("Aucun administrateur trouvé ");
	}
	
	/**
	 * Constructeur avec cause d'erreur en parametre.
	 * 
	 * @param msg the error message associated with the exception
	 * @param cause the root cause of the exception
	 */
	public AdminstrateurNotFoundException(Throwable cause) {
		super("Aucun administrateur trouvé", cause);
	}
}
