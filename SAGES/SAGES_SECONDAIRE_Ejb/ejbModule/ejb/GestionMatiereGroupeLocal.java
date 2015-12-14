package ejb;

import java.util.List;

import javax.ejb.Local;

import entities.Cour;
import entities.Groupematiere;
import entities.Matiere;


import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;


@Local
public interface GestionMatiereGroupeLocal {
	

	public void enregistrerMatiere(String codematiere,String libelle,String description) throws DuplicateKeyException;
	 
	public void modifierMatiere(String codematiere,String libelle,String description) throws ElementNOtFoundException;
	
	public void supprimerMatiere(String codematiere) throws ElementNOtFoundException;
	
	public void enregistrerGroupeMatiere(String libellegm,String description) throws DuplicateKeyException;
	 
	public void modifierGroupeMatiere(String libellegm,String description) throws ElementNOtFoundException;
	
	public void supprimerGroupeMatiere(String libellegm) throws ElementNOtFoundException;
	
	
	public void assignerGroupeMatiere(String code,String codematiere) throws ElementNOtFoundException;

	public entities.Matiere rechercherMatiere(String codematiere);

	public List<Matiere> listerMatieres();

	public void modifierAnimateurMatiere(String codematiere,
			String nouvelAnimateur) throws ElementNOtFoundException;

	public Groupematiere rechercherGroupeMatiere(String libelle);

	public List<Groupematiere> listerGroupesMatieres();

	public int enregistrerCours(String codeclasse, String codematiere,
			String libelle, String description, String libellegm, int coeficient, String string) throws ElementNOtFoundException;
	
	public int enregistrerCour(String codeclasse, String codematiere,
			String libelle, String description, String libellegm, int coeficient, String string) throws ElementNOtFoundException;

	public Cour rechercherCours(String codeclasse, String codematiere, String string);

	public Cour rechercherCours(int codecours, String string);

	public List<Cour> listerCours(String annee);

	public List<Cour> listerCours(String classe, String annee);

	public void modifierCours(int codecours, String libelle,
			String description, String libellegm, int coeficient, String annee, String string) throws ElementNOtFoundException;

	public void supprimerCours(int codecours,String annee) throws ElementNOtFoundException;

	public void modifierEnseignantCours(int codecours, String codeenseignant,String annee) throws ElementNOtFoundException;

	public List<Cour> listerCoursNonProgrammes(String codeclasse,
			String anneeAcEncours);

	public List<Cour> listerCoursProgrammes(String codeclasse,
			String anneeAcEncours);
	
	/**
	 * Lister les matieres d'une classe
	 * @param codeclasse code de la classe dont on veut lister les matieres
	 * @param etablissement etablissemnt concerné
	 * @param annee année académique en cours
	 * @return
	 */
	public List<Matiere> listerMatieresClasse(String codeclasse, String annee, String etablissement);


}
