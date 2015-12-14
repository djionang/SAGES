package ages.exception;


/**
 * Exception propagée lorsque le montant d'un versement ne correspondant pas a la somme cumulée de tranches consécutives pour l'éleve qui effectue le versement
 * @author Administrateur
 *
 */
public class CoursNonDefiniException extends AgesException{
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructeur de base, avec message d'erreur en paramètre
	 * @param msg message d'erreur
	 */
	public CoursNonDefiniException(String matiere, String classe) {
		super("Cours Non défini, Matiere: "+matiere+" Classe: "+classe);
	}
	
	/**
	 * Constructeur avec cause d'erreur en parametre.
	 * 
	 * @param msg the error message associated with the exception
	 * @param cause the root cause of the exception
	 */
	public CoursNonDefiniException(String matiere, String classe, Throwable cause) {
		super("Cours Non défini Matiere: "+matiere+" Classe: "+classe, cause);
	}
}
