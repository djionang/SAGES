package ages.exception;

public class ElementNOtFoundException extends AgesException{
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructeur de base, avec message d'erreur en paramètre
	 * @param msg message d'erreur
	 */
	public ElementNOtFoundException(String annee, String classe) {
		super("Element "+classe +": "+annee+" non trouvé");
	}
	
	/**
	 * Constructeur avec cause d'erreur en parametre.
	 * 
	 * @param msg the error message associated with the exception
	 * @param cause the root cause of the exception
	 */
	public ElementNOtFoundException(String key, Throwable cause) {
		super("Utilisateur "+key+" non trouvé", cause);
	}
}
