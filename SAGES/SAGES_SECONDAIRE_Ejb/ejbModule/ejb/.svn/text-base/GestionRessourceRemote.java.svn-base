/**
 * 
 */
package ejb;

import java.util.List;

import javax.ejb.Remote;

import entities.Salle;

/**
 * @author Administrateur
 *
 */
@Remote
public interface GestionRessourceRemote {
	

	public boolean enregistrerSalle(String codeSalle, int capacite, String description,
			String type);
	
	public boolean modifierSalle(String codesalle, String libelle,String type, int capacite);
	
	public boolean supprimerSalle(String codesalle);
	
	public List<Salle> listerSalle();
	
	public Salle rechercherSalle(String codesalle);
	
	public boolean enregistrerClasse(String codeclasse,String annee,String delegue1,String delegue2,
			int contenancemax,String libelle);
	
	public boolean modifierClasse(String codeclasse,String annee,String delegue1,String delegue2,
			int contenancemax,String libelle);
	
	public boolean supprimerClasse(String codeclasse,String annee);


}
