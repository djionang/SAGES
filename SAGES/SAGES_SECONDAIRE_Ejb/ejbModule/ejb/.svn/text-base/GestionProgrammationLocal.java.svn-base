package ejb;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ages.exception.AnneeEnCoursNonDefinieException;
import ages.exception.ElementNOtFoundException;

import entities.Programmation;

/**
 * GEstionProgrammationLocal
 * Interface EJB des fonctions de gestion de la programmation
 * @author Brilland
 *
 */

@Local
public interface GestionProgrammationLocal {

	public List<Programmation> listerProgrammations(Date datedebut, Date datefin, String annee);

	public List<Programmation> listerProgrammationsCoursClasse(String codeclasse,Date datedebut,
			Date datefin, String anneeAcEncours);

	public void enregistrerProgrammationCours(String codeclasse, int codecours,
			Date debutlundi, Date finlundi, Date debutmardi, Date finmardi,
			Date debutmercredi, Date finmercredi, Date debutjeudi,
			Date finjeudi, Date debutvendredi, Date finvendredi,
			Date debutsamedi, Date finsamedi, String annee, String codeetablissement) throws AnneeEnCoursNonDefinieException, ElementNOtFoundException;

	public void editerProgrammationCours(String codeclasse, int codecours,
			Date debutlundi, Date finlundi, Date debutmardi, Date finmardi,
			Date debutmercredi, Date finmercredi, Date debutjeudi,
			Date finjeudi, Date debutvendredi, Date finvendredi,
			Date debutsamedi, Date finsamedi,String annee,String codeetablissement) throws AnneeEnCoursNonDefinieException, ElementNOtFoundException;

	public List<Programmation> rechercherProgrammationCoursJour(int codecours,
			Date datedebut, Date datefin,String annee);

	public List<Programmation> listerProgrammationsSalle(String codesalle,
			Date dateDebut, Date dateFin, String annee);

	public List<Programmation> listerProgrammationsEvt(Date dateDebut,
			Date dateFin, String anneeAcEncours);

	public List<String> listerTypesActivites(String annee,String etablissement);
	
	
}
