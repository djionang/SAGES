package ages.beans.enseignement;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.beans.enseignant.EnseignantBean;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;


@ManagedBean(name="coursBean")
@ViewScoped	
public class CoursBean extends GenericBean implements Serializable{

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private static final long serialVersionUID = 1L;

	private int codecours;
	private String libelle;
	private String annee;
	private String codematiere;
	private String codeclasse;
	private String codeenseignant;
	private String libelleenseignant;
	private String libellematiere;
	private String libelleclasse;
	private String description;
	private String libellegm;
	private int coeficient;
	private List<Integer> courstoprint;
	private List<EnseignantBean> enseignants;
	
	private List<CoursBean> listeCours;
	
	public CoursBean(){
		
	}

	public String getCodematiere() {
		return codematiere;
	}

	public void setCodematiere(String codematiere) {
		this.codematiere = codematiere;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	

	public int getCodecours() {
		return codecours;
	}

	public void setCodecours(int codecours) {
		this.codecours = codecours;
	}

	public String getCodeclasse() {
		return codeclasse;
	}

	public void setCodeclasse(String codeclasse) {
		this.codeclasse = codeclasse;
	}

	public String getCodeenseignant() {
		return codeenseignant;
	}

	public void setCodeenseignant(String codeenseignant) {
		this.codeenseignant = codeenseignant;
	}

	public String getLibelleenseignant() {
		return libelleenseignant;
	}

	public void setLibelleenseignant(String libelleenseignant) {
		this.libelleenseignant = libelleenseignant;
	}

	public String getLibellematiere() {
		return libellematiere;
	}

	public void setLibellematiere(String libellematiere) {
		this.libellematiere = libellematiere;
	}

	public String getLibelleclasse() {
		return libelleclasse;
	}

	public void setLibelleclasse(String libelleclasse) {
		this.libelleclasse = libelleclasse;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLibellegm() {
		return libellegm;
	}

	public void setLibellegm(String libellegm) {
		this.libellegm = libellegm;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public int getCoeficient() {
		return coeficient;
	}

	public void setCoeficient(int coeficient) {
		this.coeficient = coeficient;
	}

	/**
	 * @return the enseignants
	 */
	public List<EnseignantBean> getEnseignants() {
		return enseignants;
	}

	/**
	 * @param enseignants the enseignants to set
	 */
	public void setEnseignants(List<EnseignantBean> enseignants) {
		this.enseignants = enseignants;
	}

	

	public List<CoursBean> getListeCours() {
		return listeCours;
	}

	public void setListeCours(List<CoursBean> listeCours) {
		this.listeCours = listeCours;
	}

	public List<Integer> getCourstoprint() {
		return courstoprint;
	}

	public void setCourstoprint(List<Integer> courstoprint) {
		this.courstoprint = courstoprint;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public String saveCours(){
		try {
			this.codecours=this.service.saveCours(codeclasse,codematiere,libelle,description,libellegm,coeficient);
		} catch (Exception e) {
			if(e.getCause()!=null&&e.getCause().equals(DuplicateKeyException.class)){
				Repertoire.addMessageerreur("Cours déja existant");
			}
			else{
				Repertoire.logError("Erreur a l'enregistrement d'un Cours", getClass(), e);
				Repertoire.addMessageerreur("Erreur innatendue! Veuillez contacter l'administrateur");
			}
			return null;
		}
		Repertoire.addMessageinfoEnregistrementOK("Cours");
		return OperationResults.navWithParam("visualisation", "codecours", String.valueOf(codecours));
	}
	
	public String copieCours(){
		
		for(int i=0;i<listeCours.size();i++){
		try {
			this.codecours=this.service.copieCours(listeCours.get(i).getCodeclasse(),listeCours.get(i).getCodematiere(),listeCours.get(i).getLibelle(),listeCours.get(i).getDescription(),listeCours.get(i).getLibellegm(),listeCours.get(i).getCoeficient());
		} catch (Exception e) {
			if(e.getCause()!=null&&e.getCause().equals(DuplicateKeyException.class)){
				Repertoire.addMessageerreur("Cours déja existant");
			}
			else{
				Repertoire.logError("Erreur a l'enregistrement d'un Cours", getClass(), e);
				Repertoire.addMessageerreur("Erreur innatendue! Veuillez contacter l'administrateur");
			}
			return null;
		}
		
	}
		Repertoire.addMessageinfoEnregistrementOK("Cours");
		return "listing";
	}
	
	
	public void initcours(){
		if((this.codematiere!=null&& !this.codematiere.isEmpty()&&this.codeclasse!=null&& !this.codeclasse.isEmpty())||this.codecours!=0){
			this.service.initCours(this);
		}		
	}

	public void initcoursEns(){
		if((this.codematiere!=null&& !this.codematiere.isEmpty()&&this.codeclasse!=null&& !this.codeclasse.isEmpty())||this.codecours!=0){
			this.service.initCours(this);
			this.enseignants=this.service.listeenseignants();
		}		
	}
	
	
	public String supprimerCours(){
		try {
			this.service.supprimerCours(codecours);
		} catch (Exception e) {
			if(e.getCause()!=null&&e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Cours non trouvée");
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
			}
			return null;	
		}
		Repertoire.addMessageinfoSuppressionOK("Cours");
		return "listing";
	}
	
	
	public String modifierCours(){
		try {
			this.service.modifierCours(codecours,libelle,description,libellegm,coeficient,codeclasse);
		}
		catch (Exception e) {
			if(e.getCause()!=null&& e.getCause().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Matiere non trouvée");
			}
			else{
				e.printStackTrace();
				Repertoire.addMessageerreur("Erreur innatendue! Veuillez contacter l'administrateur");
			}
			return null;
		}
		Repertoire.addMessageinfoModificationOK("Matiere");
		return OperationResults.navWithParam("visualisation", "codematiere", codematiere);
	
	}
	
	public String saveEnsCours(){
		Repertoire.addMessageerreur("enseignant"+codeenseignant);
		try {
			this.service.modifierEnseignantCours(codecours,codeenseignant);
		}
		catch (Exception e) {
			if(e.getCause()!=null&&e.getCause().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Cours non trouvé");
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue! Veuillez contacter l'administrateur");
			}
			return null;
		}
		Repertoire.addMessageinfoModificationOK("Cours");
		return OperationResults.navWithParam("visualisation", "codecours", String.valueOf(codecours));
	
	}
	
	/**
	 * Charge les matieres d'une classe pour affichage dans le combo des matieres
	 * sur la page de creation des evaluations
	 */
	public void loadCoursClasse(){
		if(codeclasse!=null && !codeclasse.isEmpty()){
			listeCours=this.service.listecours(codeclasse);
			if(listeCours!=null && ! listeCours.isEmpty()){
				libelle=this.service.rechercherlibelleCours(listeCours.get(0).getCodematiere(),codeclasse);
			}
		}
		
	}
	
	
	/**
	 * Charge les matieres d'une classe pour affichage dans le combo des matieres
	 * sur la page de creation des evaluations
	 */
	public void loadCoursClasseN(){
		if(codeclasse!=null && !codeclasse.isEmpty()){
			listeCours=this.service.listecoursN(codeclasse);
			if(listeCours!=null && ! listeCours.isEmpty()){
				libelle=this.service.rechercherlibelleCours(listeCours.get(0).getCodematiere(),codeclasse);
			}
		}
		
	}
		
}
