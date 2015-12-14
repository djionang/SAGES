package ages.exception;

public class DuplicateKeyException extends AgesException{
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructeur de base, avec message d'erreur en paramètre
	 * @param msg message d'erreur
	 */
	public DuplicateKeyException(String id) {
		super("Enregistrement non Effectuée, cause: '"+id+"' dupliqué");
	}
	
	/**
	 * Constructeur avec cause d'erreur en parametre.
	 * 
	 * @param msg the error message associated with the exception
	 * @param cause the root cause of the exception
	 */
	public DuplicateKeyException(String id, Throwable cause) {
		super(id, cause);
	}
}
