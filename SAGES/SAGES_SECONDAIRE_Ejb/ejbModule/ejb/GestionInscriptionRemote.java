package ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entities.Eleve;
import entities.ParametreTranche;
import entities.PreInscription;



@Remote
public interface GestionInscriptionRemote {
	
	/**
	 * creation d'une preinscription
	 * @param adresse
	 * @param annneeacademique
	 * @param anneeancienetablissement
	 * @param codedossier
	 * @param datenaissance
	 * @param datepreinscription
	 * @param email
	 * @param emailmere
	 * @param emailpere
	 * @param etat
	 * @param loginauteur
	 * @param nationalite
	 * @param niveaudemande
	 * @param nom
	 * @param nomancienetablissement
	 * @param nommere
	 * @param nompere
	 * @param numeropayement
	 * @param optiondemande
	 * @param passwordaccess
	 * @param prenom
	 * @param professionmere
	 * @param professionpere
	 * @param sexe
	 * @param telephone
	 * @param telephonemere
	 * @param telephonepere
	 * @param classeancienetablissement
	 * @param nomtuteur
	 * @param professiontuteur
	 * @param emailtuteur
	 * @param telephonetuteur
	 * @return
	 */
	public String createPreInscription (String adresse,String annneeacademique,
			String  anneeancienetablissement,String codedossier, Date  datenaissance, 
			Date datepreinscription, String email,String emailmere,
			String emailpere,String etat,String loginauteur,String nationalite
			,String niveaudemande,String nom,String nomancienetablissement, String
			nommere,String nompere, String numeropayement,String optiondemande,
			String passwordaccess, String prenom, String professionmere, String
			professionpere,String sexe,String telephone,String telephonemere
			,String telephonepere, String classeancienetablissement,
			String nomtuteur,String professiontuteur,String emailtuteur,String telephonetuteur,String lieuNaissance,String boitePostale);
	
	
	/**
	 * 
	 * @param adresse
	 * @param anneeacademique
	 * @param anneeancienetablissement
	 * @param codedossier
	 * @param datenaissance
	 * @param datepreinscription
	 * @param email
	 * @param emailmere
	 * @param emailpere
	 * @param etat
	 * @param loginauteur
	 * @param nationalite
	 * @param niveaudemande
	 * @param nom
	 * @param nomancienetablissement
	 * @param nommere
	 * @param nompere
	 * @param numeropayement
	 * @param optiondemande
	 * @param passwordaccess
	 * @param prenom
	 * @param professionmere
	 * @param professionpere
	 * @param sexe
	 * @param telephone
	 * @param telephonemere
	 * @param telephonepere
	 * @param classeancienetablissement
	 * @param nomtuteur
	 * @param professiontuteur
	 * @param emailtuteur
	 * @param telephonetuteur
	 * @param lieuNaissance
	 * @param boitePostale
	 * @return
	 */
	public boolean modifierInscription(String adresse, 
			String anneeancienetablissement, String codedossier,
			Date datenaissance, Date datepreinscription, String email,
			String emailmere, String emailpere, String nationalite, String niveaudemande,
			String nom, String nomancienetablissement, String nommere,
			String nompere, String numeropayement, String optiondemande,
			String passwordaccess, String prenom, String professionmere,
			String professionpere, String sexe, String telephone,
			String telephonemere, String telephonepere,
			String classeancienetablissement, String nomtuteur,
			String professiontuteur, String emailtuteur, String telephonetuteur,String lieuNaissance,String boitePostale);
	
	/**
	 * cette methode permet de suprimer un dossier
	 * @param codedossier
	 * @return
	 */
	public boolean supprimerPreinscription(String codedossier);
	
	
	/**
	 * cette methode permet de lister les preinscription deja effectué
	 * @return
	 */
	public  List<PreInscription> listerPreInscription(String annee);
	
	
	/**
	 * Liste des eleves accpetes
	 * @return
	 */	
	public List<PreInscription> PreinscriptionAccepte(String codeannee);
	
	
	/**
	 * Liste des preinscription rejette
	 * @return
	 */
	public List<PreInscription> PreinscriptionRejette(String codeannee);
	
	
	
	public boolean enregistrerInscription(String matriculeEleve, Date dateVersement,String anneeAcademique,float montant);

   
   /**
    * cette methode nous permet de rechercher la une preinscriptions deja effectuée
    * @param codedossier
    * @param codeannee annee d'enregistrement et de validité du dossier
    * @return
    */
   public PreInscription recherchePreinscription(String codedossier,String codeannee);
   
   
   
   /**
    * Valide un dossier de preinscription
    * @param codedossier code du dossier a valider
    * @param classe classe retenue pour y transferer le nouvel eleve, null si aucune classe transmise
    * @return true si le transfert se passe bien, false sinon
    */
   public boolean validerPreinscription(String codedossier,String classe,String login,String anneac);
   
   
   
   /**
    * Valide un dossier de preinscription
    * @param codedossier code du dossier a valider
    * @param classe classe retenue pour y transferer le nouvel eleve, null si aucune classe transmise
    * @return true si le transfert se passe bien, false sinon
    */
   public boolean invaliderPreinscription(String codedossier);
   
   /**
    * retourne la liste des tranches que doit payer un eleve d'une classe
    * @param codeclasse classe dont la liste des tranches est recherchée
    * @param annee annee en cours
    * @return la liste des tranches
    */
   public List<ParametreTranche> listeTranchesClasse(String codeclasse, String annee);
   
   public List<String> affecterClasse(List<String> listedossiers,String classe,String annee);

	public boolean enregistrerParametreTranche(String type,String classe, String annee,
			float montant, Date dateDelai);
	
	public boolean modifierParametreTranche(String type,String classe, String annee,
			float montant, Date dateDelai,String id);
	
	public boolean supprimerParametreTranche(String id, String codeclasse);
	
	public List<ParametreTranche> listerParametreTrancheEleve(String matricule,String annee);

	public List<Eleve> listerEleveRegulierClasse(String codeclasse,String annee);
	
	public boolean attribuerEnseignantPrincipal(String codeenseignant,String codeclasse,String annee);
	
	
	public List<Eleve> listerEleveNonRegulierClasse(String codeclasse,String annee);
	
	public boolean confirmeEleveOuiNon(String matriculeEleve,String annee);


}
