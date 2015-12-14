package ejb;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entities.Eleve;

@Remote
public interface GestionEleveRemote {
	/**
	 * Recherche d'un eleve dans la base
	 * @param matricule matricule de l'eleve
	 * @return l'eleve s'il est trouvé, null sinon
	 */
	public Eleve rechercheEleve(String matricule,String anneeAcademique);
	
	/**
	 * Liste tous les eleves de l'etablissement
	 * @return null si aucun eleve n'est trouvé, la liste sinon
	 */
	public List<Eleve> listerEleves(String anneeAcademique);
	
	public List<Eleve> listerElevesClasse(String codeclasse,String anneeAcademique);
	
	public boolean supprimerEleve(String matricule);
	
	/**
	 * cette methode nous permet de creer un  eleve
	 * @param adresse
	 * @param adressemere
	 * @param adressepere
	 * @param ancien
	 * @param anneeacademique
	 * @param anneeancienetablissement
	 * @param classeancienetablissement
	 * @param classe
	 * @param confirme
	 * @param datenaissance
	 * @param email
	 * @param emailmere
	 * @param emailpere
	 * @param login
	 * @param matricule
	 * @param nationalite
	 * @param nom
	 * @param nomancienetablissment
	 * @param nommere
	 * @param nompere
	 * @param password
	 * @param prenom
	 * @param photo
	 * @param professionmere
	 * @param professionpere
	 * @param redoublant
	 * @param sexe
	 * @param telephone
	 * @param telephonemere
	 * @param telephonepere
	 * @param nomtuteur
	 * @param emailtuteur
	 * @param professiontuteur
	 * @param telephonetuteur
	 * @return
	 */
	public String creerEleve(String adresse,String adressemere,String adressepere
			,Boolean ancien, String anneeacademique,String anneeancienetablissement,
			String classeancienetablissement, String classe, Boolean confirme,
			Date datenaissance, String  email, String emailmere, String emailpere,
			String login, String matricule, String nationalite, String nom,
			String nomancienetablissment, String nommere, String nompere, String
			password, String prenom, String photo,String professionmere, String 
			professionpere,Boolean redoublant, String sexe,String telephone,
			String telephonemere, String telephonepere,String nomtuteur,
			String emailtuteur,String professiontuteur,String telephonetuteur);
	
	
	/**
	 * cette methode nous permet de modifier un eleve
	 * @param adresse
	 * @param adressemere
	 * @param adressepere
	 * @param ancien
	 * @param anneeacademique
	 * @param anneeancienetablissement
	 * @param classeancienetablissement
	 * @param classe
	 * @param confirme
	 * @param datenaissance
	 * @param email
	 * @param emailmere
	 * @param emailpere
	 * @param login
	 * @param matricule
	 * @param nationalite
	 * @param nom
	 * @param nomancienetablissment
	 * @param nommere
	 * @param nompere
	 * @param password
	 * @param prenom
	 * @param photo
	 * @param professionmere
	 * @param professionpere
	 * @param redoublant
	 * @param sexe
	 * @param telephone
	 * @param telephonemere
	 * @param telephonepere
	 * @param nomtuteur
	 * @param emailtuteur
	 * @param professiontuteur
	 * @param telephonetuteur
	 * @return
	 */
	public boolean modifierEleve(String adresse,String adressemere,String adressepere
			,Boolean ancien, String anneeacademique,String anneeancienetablissement,
			String classeancienetablissement, String classe, Boolean confirme,
			Date datenaissance, String  email, String emailmere, String emailpere,
			String login, String matricule, String nationalite, String nom,
			String nomancienetablissment, String nommere, String nompere, String
			password, String prenom, String photo,String professionmere, String 
			professionpere,Boolean redoublant, String sexe,String telephone,
			String telephonemere, String telephonepere,String nomtuteur,
			String emailtuteur,String professiontuteur,String telephonetuteur);
	
   
   /**
    * cette methode retourne le nombre d'eleve de la classe
    * @param codeclasse
    * @return
    */
   public int Nombreeleveclasse(String codeclasse,String annee);

   /**
    * liste des eleves confirmes
    * @return
    */
   public List<Eleve> listeEleveConfirme(String codeclasse,String annee);
   
   
   /**
    * Liste des eleves non inscrit d'une classe donc la recherche se base sur le nom(LIKE)
    * @param codeclasse
    * @param nom
    * @return
    */
   public List<Eleve> listeEleveNonConfirme(String codeclasse,String nom);
   
   
   public List<String> AssignerClasseEleve(List<String> preinscriptions, String classe,String annee);	
}
