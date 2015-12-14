package ejb;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.ejb.Local;

import ages.exception.CoursNonDefiniException;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;
import ages.exception.PourcentageEvaluationExedantException;

import entities.Composer;
import entities.Eleve;
import entities.Evaluation;
import entities.TypeEvaluation;

/**
 * GEstionEleveLocal
 * Interface EJB des fonctions de gestion des eleves
 * @author Brilland
 *
 */

@Local
public interface GestionExamensLocal {

	public List<TypeEvaluation> listerTypesEvaluations();

	public int enregistrerEvaluation(String libelle, String typeevaluation,
			String codematiere,String codeclasse, int codesequence, Date datedebut, Date datefin,
			String annee) throws ElementNOtFoundException, PourcentageEvaluationExedantException, CoursNonDefiniException;

	public void supprimerTypeEvaluation(String typeevaluation) throws ElementNOtFoundException;

	public void enregistrerTypeEvaluation(String typeevaluation,
			int coefficient, String description) throws DuplicateKeyException;

	public TypeEvaluation rechercheTypeEvaluation(String typeevaluation);

	public void modifierTypeEvaluation(String typeevaluation, int coefficient,
			String description) throws ElementNOtFoundException;

	public void modifierEvaluation(int codeevaluation, String libelle,
			String typeevaluation, String codematiere,String codeclasse, int codesequence,
			Date datedebut, Date datefin, String anneeAcEncours) throws ElementNOtFoundException, PourcentageEvaluationExedantException, CoursNonDefiniException;

	public List<Evaluation> listerEvaluation(String annee);

	public void supprimerEvaluation(int codeevaluation, String anneeAcEncours) throws ElementNOtFoundException;

	public Evaluation rechercheEvaluation(int codeevaluation);

	public List<Evaluation> listerEvaluation(int codesequence,
			String codeclasse, String annee);

	/**
	 * Lister les evaluations d'une classe, pour une matiere pendant une sequence
	 * @param codesequence
	 * @param codeclasse
	 * @param codematiere
	 * @param anneeAcEncours année académique en cours
	 * @return la liste des évaluations correspondant aux critères
	 */
	public List<Evaluation> listerEvaluation(int codesequence,
			String codeclasse, String codematiere, String anneeAcEncours);

	public List<Eleve> listerElevesJustifies(int codesequence,
			String codeclasse, int evaluation, String anneeAcEncours);

	public void justifierAbsence(int evaluation, List<String> eleveschoisis,
			String motifabsence, String anneeAcEncours) throws ElementNOtFoundException;

	public void annulerJustificationabeval(int evaluation,
			List<String> eleveschoisis, String anneeAcEncours,String login) throws ElementNOtFoundException;

	public List<Composer> listerComposNotes(int codeevaluation, String annee);

	public List<Eleve> listerElevesevalues(int codeevaluation,
			String anneeAcEncours);
	
	/**
	 * Enregistre une série de notes pour une évaluation
	 * @param notes liste des notes et leurs propriétaire dans une Hashmap<Matricule, note>
	 * @param codeevaluation evaluation concernée
	 * @param anneeAcEncours année académique d'enregistrement
	 * @param login 
	 * @throws ElementNOtFoundException 
	 */
	public String enregistrerNotes(Hashtable<String, Float> notes,
			int codeevaluation, String anneeAcEncours, String login) throws ElementNOtFoundException;

	public List<Object[]> listerElevesNotesEvalues(int codeevaluation,
			String anneeAcEncours);

	/**
	 * Listing des élèves n'ayant pas été évalué pour une evaluation donnée
	 * @param codeevaluation
	 * @param anneeAcEncours
	 * @return la liste des eleves non evalues pour une evaluation passée
	 */
	public List<Eleve> listerElevesNonEvalues(int codeevaluation,
			String anneeAcEncours);

	public List<Eleve> listerTableauxHonneur(String codeclasse, int trimestre,String annee);


	
	
}
