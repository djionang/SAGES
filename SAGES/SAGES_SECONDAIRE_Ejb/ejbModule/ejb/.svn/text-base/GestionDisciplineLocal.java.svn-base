package ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;

import entities.Absence;
import entities.Convocation;
import entities.Retard;
import entities.Sanction;
import entities.TypeSanction;


/**
 * Gestion des entités liées à la discipline
 * 
 */

@Local
public interface GestionDisciplineLocal {

	public void saveTypeSanction(String libelle, String description) throws DuplicateKeyException;

	public TypeSanction rechercherTypeSanction(int id);

	public void modifierTypeSanction(int id, String libelle, String description);

	public void supprimerTypeSanction(int id) throws ElementNOtFoundException;

	public List<TypeSanction> listerTypeSanctions();

	public void enregistrerSanction(String motif, int codetype, int duree,
			Date datedecision, Date dateeffet, List<String> elevescibles,
			String anneeAcEncours) throws ElementNOtFoundException;

	public void modifierSanction(int idsanction, String motif, int codetype,
			int duree, Date datedecision, Date dateeffet, boolean annule, String anneeAcEncours) throws ElementNOtFoundException;

	public void annulerSanction(int idsanction);

	public List<Sanction> listerSanctions(String annee);

	public Sanction rechercherSanction(int idsanction);

	public void enregistrerAbsence(Date dateabsence,  int justifie,int duree,
			String matriculeeleve, String anneeAcEncours) throws ElementNOtFoundException;

	public void modifierAbsence(int codeabsence, Date dateabsence, int duree,
			int justifie) throws ElementNOtFoundException;
	
	/**
	 * Supprimer une absence
	 * @param codeabsence
	 * @param anneeAcEncours annee academique en cours
	 * @throws ElementNOtFoundException 
	 */
	public void supprimerAbsence(int codeabsence, String anneeAcEncours) throws ElementNOtFoundException;
	
	/**
	 * Enregistrer un retard
	 * @param dateretard date du retard
	 * @param duree duree du retard
	 * @param justifie nombre d'heures justifies pour le retard
	 * @param matriculeeleve matricule de l'élève concerné
	 * @param anneeAcEncours année académique en cours
	 * @throws ElementNOtFoundException Propagée si l'élève n'est pas retrouvé
	 */
	public void enregistrerRetard(Date dateretard, int duree, int justifie,
			String matriculeeleve, String anneeAcEncours) throws ElementNOtFoundException;
	
	/**
	 * Listing des retards des élèves d'une calsse
	 * @param codeclasse
	 * @param anneeAcEncours année académique en cours
	 * @return
	 */
	public List<Retard> listerRetardsClasse(String codeclasse, String anneeAcEncours);

	/**
	 * Listing des retards d'un élève
	 * @param matriculeeleve
	 * @param anneeAcEncours année académique en cours
	 * @return
	 */
	public List<Retard> listerRetardsEleve(String matriculeeleve,
			String annee,Date datedebut, Date datefin);
	
	/**
	 * Recherche d'un retard enregistré
	 * @param coderetard
	 * @return
	 */
	public Retard rechercherRetard(int coderetard);

	/**
	 * Supprimer un retard
	 * @param coderetard code du retard a supprimer
	 * @throws ElementNOtFoundException propagée lorsque le retard à supprimer n'est pas retrouvé
	 */
	public void supprimerRetard(int coderetard) throws ElementNOtFoundException;

	/**
	 * Modifier un retard
	 * @param coderetard code du retard a modifier
	 * @param dateretard nouvelle date
	 * @param duree nouvelle dureé
	 * @param justifie nombre d'heures justifies
	 * @throws ElementNOtFoundException propagée lorsque le retard de code <b>coderetard</> n'est pas retrouvé
	 */
	public void modifierRetard(int coderetard, Date dateretard, int duree,
			int justifie) throws ElementNOtFoundException;

	/**
	 * Listing des absences
	 * @param matriculeeleve
	 * @param anneeAcEncours année académique en cours
	 * @return
	 */
	public List<Absence> listerAbsencesEleve(String matriculeeleve,
			String annee,Date datedebut, Date datefin);

	/**
	 * Listing des absences
	 * @param codeclasse
	 * @param anneeAcEncours
	 * @return
	 */
	public List<Absence> listerAbsencesClasse(String codeclasse, String anneeAcEncours);	
	
	public Absence rechercheAbsence(int codeabsence);

	public List<Sanction> listerSanctionsEleve(String matriculeeleve,
			String annee,Date datedebut, Date datefin);

	public Convocation rechercherConvocation(int idconvocation, String string);

	public void enregistrerConvocations(String libelle, String description,
			Date datedelivrance, Date dateeffet, List<String> elevescibles, String annee) throws ElementNOtFoundException;

	public void modifierConvocation(int idconvocation, String libelle,String description,
			Date datedelivrance, Date dateeffet, String matriculeeleve, String annee) throws ElementNOtFoundException;

	public List<Convocation> listerConvocations(String annee);

	public void supprimerConvocation(int idconvocation, String annee) throws ElementNOtFoundException;

	public List<Convocation> listerConvocations(Date datedebut, Date datefin);

}
