package ages.exception;

import java.util.Date;

public class EleveDupliqueException extends AgesException{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructeur de base, avec message d'erreur en paramètre
	 * @param msg message d'erreur
	 */
	public EleveDupliqueException(String name, Date dateNaissance) {
		super("Eleve "+name+" Né le "+dateNaissance+" Déja enregistré");
	}
	
	/**
	 * Constructeur avec cause d'erreur en parametre.
	 * 
	 * @param msg the error message associated with the exception
	 * @param cause the root cause of the exception
	 */
	public EleveDupliqueException(String name, Date dateNaissance, Throwable cause) {
		super("Eleve "+name+" Né le "+dateNaissance+" Déja enregistré", cause);
	}

}
