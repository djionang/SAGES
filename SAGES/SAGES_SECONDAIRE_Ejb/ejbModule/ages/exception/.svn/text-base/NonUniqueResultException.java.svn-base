package ages.exception;

public class NonUniqueResultException extends AgesException{
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructeur de base, avec message d'erreur en paramètre
	 * @param msg message d'erreur
	 */
	public NonUniqueResultException(String classe,String condition ) {
		super(classe+": Plusieurs resultats trouvés --> condition:"+condition);
	}
	
	/**
	 * Constructeur avec cause d'erreur en parametre.
	 * 
	 * @param msg the error message associated with the exception
	 * @param cause the root cause of the exception
	 */
	public NonUniqueResultException(String classe,String condition,Throwable cause) {
		super(classe+": Plusieurs resultats trouvés --> condition:"+condition, cause);
	}
}
