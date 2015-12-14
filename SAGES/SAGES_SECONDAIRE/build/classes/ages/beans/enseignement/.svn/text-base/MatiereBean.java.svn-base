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


@ManagedBean(name="matiereBean")
@ViewScoped
public class MatiereBean extends GenericBean implements Serializable{

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private static final long serialVersionUID = 1L;

	private String codematiere;
	private String libelle;
	private String description;
	private String animateur;
	private String codeanimateur;
	private String nouvelAnimateur;
	private List<EnseignantBean> enseignants;
	
	public MatiereBean(){
		
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	public String getAnimateur() {
		return animateur;
	}

	public void setAnimateur(String animateur) {
		this.animateur = animateur;
	}

	public String getCodeanimateur() {
		return codeanimateur;
	}

	public void setCodeanimateur(String codeanimateur) {
		this.codeanimateur = codeanimateur;
	}



	public String getNouvelAnimateur() {
		return nouvelAnimateur;
	}


	public void setNouvelAnimateur(String nouvelAnimateur) {
		this.nouvelAnimateur = nouvelAnimateur;
	}


	public List<EnseignantBean> getEnseignants() {
		return enseignants;
	}


	public void setEnseignants(List<EnseignantBean> enseignants) {
		this.enseignants = enseignants;
	}


	public String saveMatiere(){
		try {
			this.service.saveMatiere(codematiere,libelle,description);
		} catch (Exception e) {
			if(e.getCause()!=null&&e.getCause().equals(DuplicateKeyException.class)){
				Repertoire.addMessageerreur("Matiere déja existante");
			}
			else{
				Repertoire.logError("Erreur a l'enregistrement d'une matiere", getClass(), e);
				Repertoire.addMessageerreur("Erreur innatendue! Veuillez contacter l'administrateur");
			}
			return null;
		}
		Repertoire.addMessageinfoEnregistrementOK("Matiere");
		return OperationResults.navWithParam("visualisation", "codematiere", codematiere);
	}
	
	
	public void initmatiere(){
		if(this.codematiere!=null&& !this.codematiere.isEmpty()){
			this.service.initMatiere(this);
		}		
	}
	
	/**
	 * Pour l'animateur pedagogique
	 */
	public void initmatiereAnimateur(){
		if(this.codematiere!=null&& !this.codematiere.isEmpty()){
			this.service.initMatiere(this);
			this.enseignants=this.service.listeenseignants();
		}		
	}
	
	public String supprimerMatiere(){
		try {
			this.service.supprimerMatiere(codematiere);
		} catch (Exception e) {
			if(e.getCause()!=null&&e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Matiere non trouvée");
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
			}
			return null;	
		}
		Repertoire.addMessageinfoSuppressionOK("Matiere");
		return "listing";
	}
	
	public String modifierMatiere(){
		try {
			this.service.modifierMatiere(codematiere,libelle,description);
		}
		catch (Exception e) {
			if(e.getCause()!=null&&e.getCause().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Matiere non trouvée");
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue! Veuillez contacter l'administrateur");
			}
			return null;
		}
		Repertoire.addMessageinfoModificationOK("Matiere");
		return OperationResults.navWithParam("visualisation", "codematiere", codematiere);
	
	}
	
	public String saveAnimateur(){
		try {
			System.out.println("new one== " +nouvelAnimateur);
			this.service.modifierAnimateurMatiere(codematiere,nouvelAnimateur);
		}
		catch (Exception e) {
			if(e.getCause()!=null&&e.getCause().equals(ElementNOtFoundException.class)){
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
}
