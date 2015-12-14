package ages.beans.etablissement.note;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import net.sf.jasperreports.engine.JRException;

import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.beans.eleve.EleveBean;
import ages.beans.enseignement.MatiereBean;
import ages.beans.etablissement.evaluation.EvaluationBean;

@ManagedBean(name="eleveEvalBean")
@ViewScoped
public class EleveEvalBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
		
	
	private int codeevaluation;
	
	private String codematiere;
	private String codeclasse;
	private String matricule;
	private int codesequence;
	private float notes;
	
	private List<EvaluationBean> evaluations;
	
	private List<MatiereBean> listeMatieres;
	
	private List<EleveBean> listeeleves;
	
	public EleveEvalBean() {
	}
	


	public void setService(Service service) {
		this.service = service;
	}

	/**
	 * @return the codeevaluation
	 */
	public int getCodeevaluation() {
		return codeevaluation;
	}



	/**
	 * @param codeevaluation the codeevaluation to set
	 */
	public void setCodeevaluation(int codeevaluation) {
		this.codeevaluation = codeevaluation;
	}


	/**
	 * @return the codesequence
	 */
	public int getCodesequence() {
		return codesequence;
	}



	/**
	 * @param codesequence the codesequence to set
	 */
	public void setCodesequence(int codesequence) {
		this.codesequence = codesequence;
	}



	/**
	 * @return the codematiere
	 */
	public String getCodematiere() {
		return codematiere;
	}



	/**
	 * @param codematiere the codematiere to set
	 */
	public void setCodematiere(String codematiere) {
		this.codematiere = codematiere;
	}



	/**
	 * @return the codeclasse
	 */
	public String getCodeclasse() {
		return codeclasse;
	}



	/**
	 * @param codeclasse the codeclasse to set
	 */
	public void setCodeclasse(String codeclasse) {
		this.codeclasse = codeclasse;
	}



	/**
	 * @return the evaluations
	 */
	public List<EvaluationBean> getEvaluations() {
		return evaluations;
	}



	/**
	 * @param evaluations the evaluations to set
	 */
	public void setEvaluations(List<EvaluationBean> evaluations) {
		this.evaluations = evaluations;
	}

	public List<MatiereBean> getListeMatieres() {
		return listeMatieres;
	}



	public void setListeMatieres(List<MatiereBean> listeMatieres) {
		this.listeMatieres = listeMatieres;
	}



	public String getMatricule() {
		return matricule;
	}



	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}



	public List<EleveBean> getListeeleves() {
		return listeeleves;
	}



	public float getNotes() {
		return notes;
	}



	public void setNotes(float notes) {
		this.notes = notes;
	}



	public void loadElevesClasse(){
		if(codeclasse!=null && ! codeclasse.isEmpty())
			this.setListeeleves(this.service.listerEleveProvisoireClasse(codeclasse));
		
	}

	public void setListeeleves(List<EleveBean> listeeleves) {
		this.listeeleves = listeeleves;
	}



	public void loadEvaluations(ActionEvent e){
		this.evaluations=this.service.listeevaluations(codesequence, codeclasse,codematiere);
	}
	
	public String telechargerFormulaire(){
		try {
			this.service.telechargerformulairenotes(codeclasse);
		} catch (IOException e) {
			Repertoire.addMessageerreur("Erreur innatendue lors de la génération du formulaire de notes");
			Repertoire.logError("Erreur innatendue lors de la génération du formulaire de notes "+codeclasse, getClass(), e);
		}
		return null;
	}
	
	public String telechargerFormulaireToutes(){
		try {
			this.service.telechargerformulaireToutesnotes(codeclasse);
		} catch (IOException e) {
			Repertoire.addMessageerreur("Erreur innatendue lors de la génération du formulaire de notes");
			Repertoire.logError("Erreur innatendue lors de la génération du formulaire de notes "+codeclasse, getClass(), e);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Charge les matieres d'une classe pour affichage dans le combo des matieres
	 * sur la page de creation des evaluations
	 */
	public void loadMatieresClasse(){
		if(codeclasse!=null && !codeclasse.isEmpty()){
			listeMatieres=this.service.listematieresClasse(codeclasse);
		}
		
	}
	
}
