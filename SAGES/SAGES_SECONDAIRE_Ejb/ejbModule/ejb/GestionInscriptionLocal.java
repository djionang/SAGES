package ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ages.exception.DroitsScolairesNonDefinis;
import ages.exception.ElementNOtFoundException;
import ages.exception.EleveDSCompletException;
import ages.exception.JPAException;
import ages.exception.MontantInscriptionInsuffisant;
import ages.exception.TotalVersementExcedantException;
import entities.Eleve;
import entities.ParametreTranche;
import entities.PreInscription;
import entities.Versement;

/**
 * Services liés aux preinscriptions et inscriptions
 * @author Brilland et berlin
 *
 */



@Local
public interface GestionInscriptionLocal {
	
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
	 * @param d 
	 * @param matricule 
	 * @return
	 * @throws ElementNOtFoundException 
	 */
	public String createPreInscription (String adresse,String annneeacademique,
			String  anneeancienetablissement,String codedossier, Date  datenaissance, 
			Date datepreinscription, String email,String emailmere,
			String emailpere,String etat,int loginauteur,String nationalite
			,String niveaudemande,String nom,String nomancienetablissement, String
			nommere,String nompere, String numeropayement,String optiondemande,
			String passwordaccess, String prenom, String professionmere, String
			professionpere,String sexe,String telephone,String telephonemere
			,String telephonepere, String classeancienetablissement,
			String nomtuteur,String professiontuteur,String emailtuteur,String telephonetuteur,String lieuNaissance,String boitePostale, double derniermoyenne, String matricule,float sommeverse) throws ElementNOtFoundException;
	
	
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
	 * @param matricule 
	 * @return
	 * @throws ElementNOtFoundException 
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
			String professiontuteur, String emailtuteur, String telephonetuteur,String lieuNaissance,String boitePostale,double dernieremoyenne, String matricule,float sommeverse) throws ElementNOtFoundException;
	
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
 * @throws ElementNOtFoundException 
    */
   public boolean invaliderPreinscription(String codedossier) throws ElementNOtFoundException;
   
   /**
    * retourne la liste des tranches que doit payer un eleve d'une classe
    * @param codeclasse classe dont la liste des tranches est recherchée
    * @param annee annee en cours
    * @return la liste des tranches
    */
   public List<ParametreTranche> listeTranchesClasse(String codeclasse, String annee);
   
   public List<String> affecterClasse(List<String> listedossiers,String classe,String annee);

	public List<Versement> listerVersementsEleve(String matricule, String annee);

	public void enregistrerParametreTranche(String type,String classe, String annee,
			float montant, Date dateDelai) throws ElementNOtFoundException;
    
	public void modifierParametreTranche(String type,String classe, String annee,
			float montant, Date dateDelai,int id) throws ElementNOtFoundException;
	
	public void supprimerParametreTranche(int numero, String codeclasse, String annee) throws ElementNOtFoundException;
		
	public List<Eleve> listerEleveRegulierClasse(String codeclasse,String annee);
	
	public boolean attribuerEnseignantPrincipal(String codeenseignant,String codeclasse,String annee);
	
	
	public List<Eleve> listerEleveNonRegulierClasse(String codeclasse,String annee);
	
	public boolean confirmeEleveOuiNon(String matriculeEleve,String annee) throws ElementNOtFoundException;
	
	public String enregistrerDroitScolaire(float montant,String matricule, String typeversement, Date dateVersement,String annee, String login) throws ElementNOtFoundException, MontantInscriptionInsuffisant, EleveDSCompletException, JPAException, DroitsScolairesNonDefinis, TotalVersementExcedantException;
		
	public List<Versement> listerVersementsEnregistres(Date datedebut, Date datefin, String annee) throws JPAException;
	
	public List<String> listeTypesTranches();


	public List<ParametreTranche> rechercheTranchesClasse(String codeclasse,
			int id, String anneeAcEncours);

	public boolean droitsScolairescomplet(String matricule, String annee);


	public void copierTranches(String codeclasse, List<String> classescibles,
			String anneeAcEncours) throws ElementNOtFoundException;


	public Versement rechercherVersement(String idversement,String annee);


	public List<PreInscription> PreinscriptionAttente(String anneeAcEncours);


	public List<PreInscription> PreinscriptionAttente(String anneeAcEncours,
			String codeniveau);


	public List<PreInscription> PreinscriptionRejette(String anneeAcEncours,
			String codeniveau);


	public List<PreInscription> PreinscriptionAccepte(String anneeAcEncours,
			String codeniveau);


	public Versement rechercherVersement(String id);


	public void modifierversement(String id, String typeversement,
			float montant, Date dateVers) throws ElementNOtFoundException;


	public void cloture(String anneeacademique);


	public void cloture(String anneeacademique, int idutilisateur,String anneea, String codeetablissement) throws ElementNOtFoundException;
	
	public List<String> listerAnnee();
	
	public List<String> listerAnneeAdmin();


	public List<Versement> listerVersementsClasse(String classe,
			String anneeAcEncours) throws JPAException;
	public float recherchedroitsScolaires(String matricule, String annee);


	public int enregistrerCertificat(int ideleve, String codeclasse,
			String anneeAcEncours) throws ElementNOtFoundException;

}
