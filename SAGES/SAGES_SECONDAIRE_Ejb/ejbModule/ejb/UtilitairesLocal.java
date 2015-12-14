package ejb;
import javax.ejb.Local;
import javax.mail.internet.AddressException;

import entities.Configuration;

import ages.exception.AdminstrateurNotFoundException;

/**
 * UtilitairesLocal
 * Gestion des services utilitaires comme la gestion des configurations de l'application, 
 * la gestion des utilisateurs non administrateurs et gestionnaires
 * @author Brilland
 *
 */

@Local
public interface UtilitairesLocal {
	
	public void envoyerMessage(String nom, String email, String message) throws AdminstrateurNotFoundException, AddressException;


	public int rechercherModeleBulletin(String codeetablissement);
	
	
	/**
	 * Recherche la configuration de l'application
	 * @return la configuration de l'application
	 */
	public Configuration rechercherConfiguration();

	void enregistrerModeleBulletin(int modele, String etablissement);
}
