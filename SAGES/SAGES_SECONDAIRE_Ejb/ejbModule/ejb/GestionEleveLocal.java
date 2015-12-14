package ejb;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;

import entities.Eleve;

/**
 * GEstionEleveLocal
 * Interface EJB des fonctions de gestion des eleves
 * @author Brilland
 *
 */

@Local
public interface GestionEleveLocal {
	
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
	
	public List<Eleve> listerProvisoireElevesClasse(String codeclasse,String anneeAcademique);
	
	
	public boolean supprimerEleve(String matricule,String annee) throws ElementNOtFoundException;
	
	/**
	 * Enregistrement d'un nouvel élève
	 * @param matricule
	 * @param nom
	 * @param prenom
	 * @param dateNaissance
	 * @param lieuNaissance
	 * @param sexe
	 * @param nationalite
	 * @param photo
	 * @param codeClasse
	 * @param redoublant
	 * @param adresse
	 * @param email
	 * @param telephone
	 * @param boitePostale
	 * @param nomPere
	 * @param nomMere
	 * @param nomTuteur
	 * @param telephonePere
	 * @param telephoneMere
	 * @param telephoneTuteur
	 * @param professionPere
	 * @param professionMere
	 * @param professionTuteur
	 * @param emailPere
	 * @param emailMere
	 * @param emailTuteur
	 * @param ancien
	 * @param ancienEtablissement
	 * @param anneeAncienEtablissement
	 * @param classeAncienEtablissement
	 * @return le matricule de l'élève enregistré
	 * @throws ElementNOtFoundException 
	 * @throws DuplicateKeyException 
	 */
	public String enregistrerEleve(String matricule,String nom, String prenom, Date dateNaissance,
			String lieuNaissance, String sexe, String nationalite,
			String photo, String codeClasse, boolean redoublant,
			String adresse, String email, String telephone,
			String boitePostale, String nomPere, String nomMere,
			String nomTuteur, String telephonePere, String telephoneMere,
			String telephoneTuteur, String professionPere,
			String professionMere, String professionTuteur, String emailPere,
			String emailMere, String emailTuteur, boolean ancien,
			String ancienEtablissement, String anneeAncienEtablissement,
			String classeAncienEtablissement,String annee) throws ElementNOtFoundException, DuplicateKeyException;
	
	
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
   

	public List<Eleve> rechercherEleves(String motcle, String codeclasse,
			String filtre,String anneeAcEncours);
	
	public List<Eleve> rechercherElevesClasse(String motcle, String codeclasse,
			String anneeAcEncours);
	
	public List<Eleve> rechercherEleves(String motcle, String anneeAcEncours);
	
	
	/**
	 * Liste des eleves financierement Non en regle ( n'ayant payé leur Droits dans les délais) dans une classe
	 * @param codeClasse code de la classe
	 * @param anneeAcEncours annee academique en cours
	 * @return la liste des eleves non en regle
	 */
	public List<Eleve> listerElevesNERClasse(String codeClasse,
			String anneeAcEncours);
	
	/**
	 * Liste des eleves financierement en regle ( ayant payé leur Droits dans les délais) dans une classe
	 * @param codeClasse code de la classe
	 * @param anneeAcEncours annee academique en cours
	 * @return la liste des eleves en regle
	 */
	public List<Eleve> listerElevesERClasse(String codeClasse, String anneeAcEncours);
	
	/**
	 * Recherche d'un ou plusieurs élèves, suivant un mot clé et un filtre
	 * @param motcle mot clé a rechercher
	 * @param filtre peut etre "nom" ou "matricule", le champ  de recherche
	 * @param anneeAcEncours annee académique en cours
	 * @return la liste des élèves ayant des champs correspondant aux criteres de recherche
	 */
	public List<Eleve> rechercherElevesFiltre(String motcle, String filtre,
			String anneeAcEncours);
	
	/**
	 * Liste tous les élèves inscrits aucours d'une année académique
	 * @param anneeAcEncours année académique en cours
	 * @return
	 */
	public List<Eleve> listerElevesinscrits(String anneeAcEncours);
	
	
	/**
	 * Migrer un élève d'une classe à une autre
	 * @param matricule matricule de l'élève a migrer
	 * @param nouvelleClasse Nouvelle classe de l'élève
	 * @param annee annee académique en cours
	 * @throws ElementNOtFoundException Exception levée lorsque la classe ou l'élève n'est pas trouvé(e)
	 */
	public void migrateClass(String matricule, String nouvelleClasse,String annee) throws ElementNOtFoundException;
	
	/**
	 * Modifier les informations sur un élève
	 * @param ideleve 
	 * @param matricule
	 * @param nom
	 * @param prenom
	 * @param dateNaissance
	 * @param lieuNaissance
	 * @param sexe
	 * @param nationalite
	 * @param photo
	 * @param codeClasse
	 * @param redoublant
	 * @param adresse
	 * @param email
	 * @param telephone
	 * @param boitePostale
	 * @param nomPere
	 * @param nomMere
	 * @param nomTuteur
	 * @param telephonePere
	 * @param telephoneMere
	 * @param telephoneTuteur
	 * @param professionPere
	 * @param professionMere
	 * @param professionTuteur
	 * @param emailPere
	 * @param emailMere
	 * @param emailTuteur
	 * @param ancien
	 * @param ancienEtablissement
	 * @param anneeAncienEtablissement
	 * @param classeAncienEtablissement
	 * @param annee
	 * @throws ElementNOtFoundException
	 * @throws DuplicateKeyException 
	 */
	public void modifierEleve(int ideleve, String matricule, String nom, String prenom,
			Date dateNaissance, String lieuNaissance, String sexe,
			String nationalite, String photo, String codeClasse,
			boolean redoublant, String adresse, String email, String telephone,
			String boitePostale, String nomPere, String nomMere, String nomTuteur,
			String telephonePere, String telephoneMere, String telephoneTuteur,
			String professionPere, String professionMere, String professionTuteur,
			String emailPere, String emailMere, String emailTuteur, boolean ancien,
			String ancienEtablissement, String anneeAncienEtablissement,
			String classeAncienEtablissement,String annee) throws ElementNOtFoundException, DuplicateKeyException;
	
	
	public Eleve rechercheEleveId(int ideleve, String annee);
	
	public int effectif(String codeclasse);
	
	public List<Integer> listerElevesERClasseN(String codeClasse,
			String anneeAcEncours);


}
