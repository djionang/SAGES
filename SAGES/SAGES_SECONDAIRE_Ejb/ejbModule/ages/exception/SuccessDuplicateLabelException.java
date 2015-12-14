package ages.exception;


/**
 * Classe d'exception
 * propag�e lorsqu'un enregistrement est effectu� (avec succes) dans la base, 
 * pour deux produits d'iDs diff�rents avec le m�me label
 * @author Brilswear
 *
 */
public class SuccessDuplicateLabelException extends AgesException{
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructeur de base, avec message d'erreur en param�tre
	 * @param msg message d'erreur
	 */
	public SuccessDuplicateLabelException(String label) {
		super("Enregistrement Effectu�e, malgr� '"+label+"' existant");
	}
	
	/**
	 * Constructeur avec cause d'erreur en parametre.
	 * 
	 * @param msg the error message associated with the exception
	 * @param cause the root cause of the exception
	 */
	public SuccessDuplicateLabelException(String label, Throwable cause) {
		super(label, cause);
	}
}
