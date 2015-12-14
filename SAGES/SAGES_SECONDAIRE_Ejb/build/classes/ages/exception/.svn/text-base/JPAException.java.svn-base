package ages.exception;

public class JPAException extends AgesException{
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructeur de base, avec message d'erreur en paramètre
	 * @param msg message d'erreur
	 */
	public JPAException(String where) {
		super("Erreur JPA "+where);
	}
	
	/**
	 * Constructeur avec cause d'erreur en parametre.
	 * 
	 * @param msg the error message associated with the exception
	 * @param cause the root cause of the exception
	 */
	public JPAException(String where, Throwable cause) {
		super("Erreur JPA "+where, cause);
	}
}
