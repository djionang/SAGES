/**
 * 
 */
package ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;

import entities.CahierDeTexte;
import entities.PartieCour;

/**
 * @author Administrateur
 *
 */


@Local
public interface GestionCahierScolaireLocal {

	/**
	 * Recherche une entrée de cahier de texte a partir de son code
	 * @param codecdt code du cahier de texte
	 * @return l'entré cahier de texte si el est trouvée et non supprimée, null si non
	 */
	public CahierDeTexte rechercherCdt(int codecdt,String annee);

	/**
	 * recherche une partie ou un chapitre d'un cours
	 * @param codepartie code de la partie ou du chapitre
	 * @return une partie si el est trouvée, null si non
	 */
	public PartieCour rechercherPartieCours(int codepartie);

	/**
	 * Listing des parties ou chapitre d'un cours
	 * @param codecours code du cours dont les chapitres sont a lister
	 * @return la liste des dits chapitres non supprimés
	 */
	public List<PartieCour> listerPartiesCours(int codecours);
	
	/**
	 * Enregistre une entrée de cahier de texte
	 * @param selectedchapitres chapitres concernées pas l'entrée
	 * @param libelle libelle du cours du jour
	 * @param resume resume du jour concerné
	 * @param datejour date du jour 
	 * @param heuredebut heure de debut du cours
	 * @param heurefin heure de fin du cours
	 * @param annee année academique en cours
	 * @return
	 * @throws DuplicateKeyException propagée si une entrée sur le meme cours porte le meme libellé
	 * @throws ElementNOtFoundException 
	 */
	public int creerCahierTexte(List<Integer> selectedchapitres,
			String libelle, String resume, Date datejour, Date heuredebut,
			Date heurefin,String annee) throws DuplicateKeyException, ElementNOtFoundException;
	
	/**
	 * Modifier une entrée de cahier de texte
	 * @param codecdt code de l'entrée a modifier
	 * @param selectedchapitres chapitres concernés pas l'entrée
	 * @param libelle libelle
	 * @param resume resumé du travail effectué
	 * @param datejour date du jour
	 * @param heuredebut heure de debut
	 * @param heurefi heure de fin
	 * @param anneeac annee académique en cours
	 * @throws ElementNOtFoundException propagée si l'entré de code envoyée n'est pas retrouvée
	 */
	public void modifierCahierTexte(int codecdt, List<Integer> selectedchapitres,
			String libelle, String resume, Date datejour, Date heuredebut,
			Date heurefi,String anneeac) throws ElementNOtFoundException;
	
	/**
	 * Supprimer une entrée de chaier de texte enregistrée
	 * @param codetexte code de léntrée a supprimer
	 * @param annee année académique en cours
	 * @throws ElementNOtFoundException propagée si l'entrée de code rensigné n'est pas retrouvée
	 */
	public void supprimerCahierTexte(int codetexte,String annee) throws ElementNOtFoundException;
	
	/**
	 * Modifier un chapitre d'un cours
	 * @param codepartie code du chapitre
	 * @param libelle libelle du chapitre
	 * @param description description du chapitre
	 * @param datedebut nouvelle date de debut (prévisionnelle)
	 * @param datefin nouvelle date de fin (prévisionnelle)
	 * @param codecours code du cours concerné par le chapitre
	 * @param annee année académique en cours
	 * @throws ElementNOtFoundException propagée si le cahpitre n'est pas retrouvé ou s'il a été supprimé
	 */
	public void modifierChapitre(int codepartie, String libelle,String description,
			Date datedebut, Date datefin,int codecours,String annee) throws ElementNOtFoundException;
	
	/**
	 * Supprimer un chapitre enregistré
	 * @param codepartie code du chapitre a supprimer
	 * @param anneeAcEncours annee academique en cours
	 * @throws ElementNOtFoundException propagée si le chapitre n'est pas retrouvé
	 */
	public void supprimerChapitre(int codepartie, String anneeAcEncours) throws ElementNOtFoundException;
	
	/**
	 * Liste les chapitres d'un cours pour un enseignant donné
	 * @param codeenseignant
	 * @return
	 */
	public List<PartieCour> listerPartieCoursEnseignant(String codeenseignant);

	/**
	 * Listing des entrées de cahier de texte enregistrés
	 * @param codecours code du cours concerné par les dites entrées
	 * @param annee
	 * @return
	 */
	public List<CahierDeTexte> listerCdt(int codecours,String annee);
	
	
	/**
	 * Enregistrer un chapitre ou une partie d'un cours
	 * @param nouveaulibelle libelle de la partie
	 * @param nouvelledescription description 
	 * @param codecours code du cours concerné
	 * @param nouvelledatedebut date de debut dans le planing
	 * @param nouvelledatefin date de fin dans le planning
	 * @param annee annee academique en cours
	 * @throws DuplicateKeyException propagée lorsque le chapitre existe déja pour le cours
	 * @throws ElementNOtFoundException  propagée lorsque le cours n'est pas retrouvé
	 */
	public void creerChapitre(String nouveaulibelle,
			String nouvelledescription, int codecours, Date nouvelledatedebut,
			Date nouvelledatefin,String annee) throws DuplicateKeyException, ElementNOtFoundException;

	public int rechercherCodeCours(int codepartie);

	public List<Integer> listerCodesChapitresCdt(int codecdt);


}
