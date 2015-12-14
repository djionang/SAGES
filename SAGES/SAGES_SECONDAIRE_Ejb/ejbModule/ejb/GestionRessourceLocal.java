/**
 * 
 */
package ejb;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ages.exception.AnneeEnCoursNonDefinieException;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;

import entities.Classe;
import entities.EnsTitulaire;
import entities.Enseignant;
import entities.Personnel;
import entities.Salle;
import entities.TypeSalle;

/**
 * @author Administrateur
 *
 */
@Local
public interface GestionRessourceLocal {

	

	public void enregistrerSalle(String codeSalle, int capacite, String description,
			int type,String libelle) throws DuplicateKeyException, ElementNOtFoundException;
	
	public void modifierSalle(String codesalle, String libelle,int type, int capacite,String description) throws ElementNOtFoundException;
	
	public void supprimerSalle(String codesalle) throws ElementNOtFoundException;
	
	public List<Salle> listerSalle();
	
	public List<Salle> listerOccupationsSalle(String codesalle, String annee);
	
	public Salle rechercherSalle(String codesalle);
	
	public void enregistrerClasse(String codeclasse,String annee,String option,
			int contenancemax,String libelle, String salleDefaut) throws ElementNOtFoundException, DuplicateKeyException, AnneeEnCoursNonDefinieException;
	
	public void modifierClasse(String codeclasse,String annee,String option,
			int contenancemax,String libelle, String salleDefaut) throws ElementNOtFoundException;
	
	public void supprimerClasse(String codeclasse,String annee) throws ElementNOtFoundException;

	/**
	 * Liste les types de salle de classe déja enregistrés
	 * @return
	 */
	public List<TypeSalle> listerTypesSalles();
	
	
	/**
	 * cette methode nous permet de lister toutes les clase d'un etablisement
	 * @return
	 */
	public List<Object[]> listerClasseWithSize(String annee);
	
	/**
	 * Enregisrement d'un nouvel enseignant
	 * Passe par l'enregistrement du personnel, de l'utilisateur et enfin de l'enseignant lui meme
	 * @param nomens
	 * @param prenomens
	 * @param photo
	 * @param sexe
	 * @param adresse
	 * @param email
	 * @param telephone
	 * @param civilite
	 * @param datearrivee
	 * @param loginens
	 * @param passens
	 * @param competences
	 * @param vacataire
	 * @param salairehoraire
	 * @param travailmensuel
	 *  @return le code de l'enseignant enregistré
	 */
	public String enregistrerEnseignant(String nomens, String prenomens,
			String photo, String sexe, String adresse, String email,
			String telephone, String civilite, Date datearrivee,
			String loginens, String passens, String competences,
			boolean vacataire, BigDecimal salairehoraire, int travailmensuel);
	
	/**
	 * Enregistrement d'un personnel
	 * Puis propagation de l'nregistrement dans les tables direction ou enseignant et login
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param email
	 * @param telephone
	 * @param civilite
	 * @param datearrivee
	 * @param fonction
	 * @param sexe
	 * @throws ElementNOtFoundException  cas ou l'etablissement de destination n'est pas retrouvé
	 */
	public void enregistrerPersonnel(String nom, String prenom, String adresse,
			String email, String telephone, String civilite, Date datearrivee,
			String fonction, String sexe,String etablissement) throws ElementNOtFoundException;
	
	/**
	 * Recherche d'un enseignant à partir de son code
	 * @param codeenseignant
	 * @return l'enseignant s'il es trouvé, null si non
	 */
	public Enseignant rechercheEnseignant(String codeenseignant);

	
	/**
	 * Supression d'un enseignant
	 * @param codeenseignant
	 * @throws ElementNOtFoundException 
	 */
	public void supprimerEnseignant(String codeenseignant) throws ElementNOtFoundException;

	/**
	 * Modification d'un enseignant
	 * @param codeenseignant
	 * @param nomens
	 * @param prenomens
	 * @param photo
	 * @param sexe
	 * @param adresse
	 * @param email
	 * @param telephone
	 * @param civilite
	 * @param datearrivee
	 * @param loginens
	 * @param passens
	 * @param competences
	 * @param vacataire
	 * @param salairehoraire
	 * @param travailmensuel
	 * @throws ElementNOtFoundException 
	 */
	public void modifierEnseignant(String codeenseignant, String nomens,
			String prenomens, String photo, String sexe, String adresse,
			String email, String telephone, String civilite, Date datearrivee,
			String competences,	BigDecimal salairehoraire, int travailmensuel) throws ElementNOtFoundException;
	/**
	 * Modification du statut d'un enseignant-- passe de vacataire a non vacataire ou vice versa
	 * @param codeenseignant
	 * @param vacataire
	 * @throws ElementNOtFoundException 
	 */
	public void modifierStatut(String codeenseignant, boolean vacataire) throws ElementNOtFoundException;
	
	/**
	 * Recherche d'un personnel par son code
	 * @param codepersonnel
	 * @return
	 */
	public Personnel recherchePersonnel(String codepersonnel);

	/**
	 * Modification d'un personnel
	 * @param codepersonnel
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param email
	 * @param telephone
	 * @param civilite
	 * @param datearrivee
	 * @param fonction
	 * @param sexe
	 * @throws ElementNOtFoundException 
	 */
	public void modifierPersonnel(String codepersonnel, String nom,
			String prenom, String adresse, String email, String telephone,
			String civilite, Date datearrivee, String fonction, String sexe) throws ElementNOtFoundException;

	/**
	 * Supression d'un personnel retrouvé a partir de son code
	 * @param codepersonnel
	 * @throws ElementNOtFoundException 
	 */
	public void supprimerPersonnel(String codepersonnel) throws ElementNOtFoundException;

	/**
	 * Listing de tout le personnel de l'établissement
	 * @return
	 */
	public List<Personnel> listerPersonnels();
	
	/**
	 * Listind de tous les enseignants de l'établissement
	 * @return
	 */
	public List<Enseignant> listerEnseignants();

	/**
	 * Enregistrer une salle de type atelier
	 * @param codeSalle
	 * @param capacite
	 * @param description
	 * @param libelle
	 * @throws DuplicateKeyException 
	 */
	public void enregistrerAtelier(String codeSalle, int capacite,
			String description, String libelle) throws DuplicateKeyException;

	public TypeSalle rechercherTypeSalle(int id);

	public void enregistrerTypeSalle(String libelle, String description) throws DuplicateKeyException;

	public void supprimerTypeSalle(int id) throws ElementNOtFoundException;

	public void modifierTypeSalle(int id, String libelle, String description) throws ElementNOtFoundException;

	public List<Personnel> listerPersonnelsLike(String query);

	public List<Object[]> listerClasseWithSize(String codeoption,
			String anneeAcEncours);

	public List<Object[]> listerEnseignantsClasse(String codeclasse);

	public void modifierDelegues(String codeclasse, String annee,
			String delegue1, String delegue2, String nouveautitulaire) throws ElementNOtFoundException;

	public List<Enseignant> listerEnseignants(String codeClasse);
	
	public Classe rechercheclasse(String codeclasse);
	
	public Enseignant rechercherenseignant(String codeclasse);

	public List<Object[]> listerClasseWithSize();

	public int effectif(String anneeEncours, Classe classe);
	public List<Object[]> listerClasseCours(String annee);
	
	public EnsTitulaire rechercheTitulaire(String annee,String codeclasse);
	

}
