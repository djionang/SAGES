package ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ages.exception.AnneeEnCoursNonDefinieException;
import ages.exception.ChevauchementDateException;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;
import ages.exception.NonUniqueResultException;
import entities.Annee;
import entities.Cycle;
import entities.Enseignement;
import entities.Etablissement;
import entities.Niveau;
import entities.OptionSerie;
import entities.Section;
import entities.Sequence;
import entities.Trimestre;

/**
 * Gestion des entités liées à l'etablissement
 * @author Brilland
 * Gérés ici:
 * Salles
 * Ateliers
 * Classes
 * 
 */

@Local
public interface GestionEtablissementLocal {	
		
		/**
		 * Retourne l'année académique en cours, telle que enregistrée dans la base
		 * @return
		 */
		public String getAnneeEnCours();
		

	   /**
	    * Lister les options disponibles su sein de l'etablissement
	    * @return
	    */
	   public List<OptionSerie> listerOptions();
	   
	   
	   /**
	    * Lister les differents niveaux disponibles au sein de l'etablissement
	    * @return
	    */
	   public List<Niveau> listerNiveaux();
	   		   
	   public void enregistrerOptionSerie(String codeoption, String libelle,String niveau) throws DuplicateKeyException, ElementNOtFoundException;
	   
	   public void enregistrerEtablissement(String codeetablissement, String nom,
				String logingest, String passgest) throws DuplicateKeyException;

		public void enregistrerEnseignement(String libelleens,
				String description, String type) throws DuplicateKeyException, ElementNOtFoundException;

		public void enregistrerSection(String codesection, String description,
				String libelle, String enseignement) throws ElementNOtFoundException, DuplicateKeyException;

		public void enregistrerCycle(String codeCycle, String libelle,
				String codesection) throws ElementNOtFoundException, DuplicateKeyException;

		public void enregistrerNiveau(String codeNiveau, String libelle,
				String codeCycle) throws ElementNOtFoundException, DuplicateKeyException;
		
		/**
		 * Liste les codes et acronymes des établissements
		 * @return liste de tableaux de String dont pour chaque element, s[0] represente le code, [1] represente l'acronyme 
		 */
		public List<Object[]> listerCodesEtablissement();

		public List<String> listercodesEnseignements();
		
		public List<Cycle> listerCycles();

		public List<Object[]> listerCodesSections();
		
		public List<String> enregistrerOptionSeries(String libelle,List<String> niveaux);

		public Etablissement rechercheEtablissement(String codeetablissement);


		public void modifierEtablissement(String codeetablissement, String nom,
				String acronyme, String devise, String logo, String type,
				String codepostal, String email, String telephone,
				String siteweb, String region, String departement,
				String arrondissement, String adresse, String pays, String devisepays) throws ElementNOtFoundException;
			
		public List<Enseignement> listerEnseignements();


		public void supprimerEnseignement(String libelleens) throws ElementNOtFoundException;

		public Enseignement rechercheEnseignement(String libelleens);


		public void modifierEnseignement(String libelleens, String description,
				String type) throws ElementNOtFoundException;


		public List<Section> listerSections();
		
		public Section rechercheSection(String codesection);
		
		public Cycle rechercheCycle(String codecycle);
		
		public Niveau rechercheNiveau(String codesection);
		
		public OptionSerie rechercheOption(String codeoption);
		
		public void supprimerSection(String codesection) throws ElementNOtFoundException;
		
		public void supprimerNiveau(String codeniveau) throws ElementNOtFoundException;
		
		public void supprimerCycle(String codecycle) throws ElementNOtFoundException;
		
		public void supprimerOption(String codeoption) throws ElementNOtFoundException;


		public void modifierSection(String codesection, String description,
				String libelle, String enseignement) throws DuplicateKeyException, ElementNOtFoundException;


		public void modifierCycle(String codeCycle, String libelle,
				String codesection) throws ElementNOtFoundException;


		public void modifierNiveau(String codeNiveau, String libelle,
				String codeCycle) throws ElementNOtFoundException;


		void modifierOption(String codeoption, String libelle, String niveau)
				throws ElementNOtFoundException;


		public String enregistrerAnneeAcademique(Date datedebut, Date datefin) throws DuplicateKeyException, ChevauchementDateException;


		public void supprimerEtablissement(String codeetablissement) throws ElementNOtFoundException;


		public List<Etablissement> listerEtablissements();


		public Object[] rechercheClasse(String codeClasse, String annee);


		public Annee rechercheAnnee(String anneeacademique,String etablissement) throws AnneeEnCoursNonDefinieException;
		
		public Annee rechercheAnneeI(String anneeacademique,String etablissement) throws AnneeEnCoursNonDefinieException;


		public Trimestre rechercheTrimestre(int numero, String anneeAcEncours);


		public Sequence rechercheSequence(int numero, String anneeAcEncours);

		/**
		 * Recherche l'année académique en cours au sein dd'un établissement
		 * @param etablissement code de l'etablissement
		 * @return l'année en cours dans l'etablissement
		 * @throws AnneeEnCoursNonDefinieException
		 */
		public Annee rechercheAnneeEnCours(String etablissement) throws AnneeEnCoursNonDefinieException;


		public void modifierAnnee(String anneeacademique, Date datedebut, Date datefin) throws ElementNOtFoundException, ChevauchementDateException;


		public void supprimerAnnee(String anneeacademique) throws ElementNOtFoundException;


		public void modifierTrimestre(int numero, Date datedebut, Date datefin, Date dateimpression,
				String anneeAcEncours) throws ElementNOtFoundException, ChevauchementDateException;


		public void supprimerTrimestre(int numero, String anneeAcEncours) throws ElementNOtFoundException;


		public void modifierSequence(int numero, Date datedebut, Date datefin,
				int trimestre, String anneeAcEncours) throws ElementNOtFoundException, ChevauchementDateException;


		public void enregistrerTrimestre(int numero, Date datedebut,
				Date datefin, Date dateimpressionbull,String anneeAcEncours) throws ElementNOtFoundException, ChevauchementDateException;


		public List<Trimestre> listerTrimestres(String anneeAcEncours);

		/**
		 * Enregistrer une séquence, pour une année académique, dans un trimestre
		 * @param numero
		 * @param datedebut date de début
		 * @param datefin date de fin
		 * @param trimestre trimestre
		 * @param annee
		 * @throws DuplicateKeyException Renvoyée si la séquence a deja été enregistrée
		 * @throws ElementNOtFoundException Renvoyée si un des éléments Trimestre ou année académique n'est pas enregistré
		 * @throws NonUniqueResultException Renvoyée si plusieurs trimestre sont enregistrés a ce code <b>trimestre</b>
		 * @throws ChevauchementDateException Renvoyée si la séquence chevauche avec une autre déja enregistrée
		 */
		public void enregistrerSequence(int numero, Date datedebut,
				Date datefin, int trimestre, String annee) throws DuplicateKeyException, ElementNOtFoundException, NonUniqueResultException, ChevauchementDateException;

		/**
		 * Prochain numéro de trimestre à enregistrer
		 * @param anneeAcEncours année académique en cours
		 * @return
		 */
		public int nextNumeroTrimestre(String anneeAcEncours);

		/**
		 * Prochain numéro de séquence à enregistrer, dans un ordre croissant des enregistrements
		 * @param anneeAcEncours
		 * @return
		 */
		public int nextNumeroSequence(String anneeAcEncours);

		/**
		 * Supprimer une séquence
		 * @param numero numero de ma séquence
		 * @param anneeAcEncours année académique en cours
		 * @throws ElementNOtFoundException renvoyée si la séquence de numero <b>numero</b> n'est pas trouvée
		 */
		public void supprimerSequence(int numero, String anneeAcEncours) throws ElementNOtFoundException;

		/**
		 * Listeing des séquences pour une année académique
		 * @param annee
		 * @return
		 */
		public List<Sequence> listerSequences(String annee);

		/**
		 * Listing des années académiques enregistrées
		 * @return
		 */
		public List<Annee> listerAnneesAcademiques();


		public List<OptionSerie> listerOptions(String niveauDemande);


		public void admodifierEtablissement(String codeetablissement,
				String nom, String logingest, String passgest) throws ElementNOtFoundException;

		/**
		 * Recherche les sequences d'un trimestre
		 * @param trimestre numéro du trimestre
		 * @param anneeAcEncours annee academiue en cours
		 * @return une liste d'integer contenant les numeros de sequence trouvées
		 */
		public List<Integer> rechercherSequencesTrimestre(int trimestre,
				String anneeAcEncours);


		List<Niveau> listerNiveauxB();



}
