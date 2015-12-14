package ages.exception;

public class ClassToBeanCopyException extends AgesException{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructeur de base, avec message d'erreur en paramètre
	 * @param msg message d'erreur
	 */
	public ClassToBeanCopyException(String classe,String bean) {
		super("Impossible de parser "+classe+" en bean "+bean);
	}
	
	/**
	 * Constructeur avec cause d'erreur en parametre.
	 * 
	 * @param msg the error message associated with the exception
	 * @param cause the root cause of the exception
	 */
	public ClassToBeanCopyException(String classe, String bean, Throwable cause) {
		super("Impossible de parser "+classe+" en bean "+bean, cause);
	}

}
