package ages.beans.etablissement.evaluation;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.beans.eleve.EleveBean;
import ages.exception.ElementNOtFoundException;

@ManagedBean(name="absenceEvBean")
@ViewScoped
public class AbsenceEvBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private int codesequence;
	private String codeclasse;
	private List<EleveBean> eleves;
	private List<EvaluationBean> evaluations;
	private String motifabsence;
	private int evaluation;
	private List<String> eleveschoisis;
	
	
	public AbsenceEvBean() {
	}
	

	public void setService(Service service) {
		this.service = service;
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
	 * @return the eleves
	 */
	public List<EleveBean> getEleves() {
		return eleves;
	}


	/**
	 * @param eleves the eleves to set
	 */
	public void setEleves(List<EleveBean> eleves) {
		this.eleves = eleves;
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


	/**
	 * @return the motifabsence
	 */
	public String getMotifabsence() {
		return motifabsence;
	}


	/**
	 * @param motifabsence the motifabsence to set
	 */
	public void setMotifabsence(String motifabsence) {
		this.motifabsence = motifabsence;
	}


	/**
	 * @return the evaluation
	 */
	public int getEvaluation() {
		return evaluation;
	}


	/**
	 * @param evaluation the evaluation to set
	 */
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}


	/**
	 * @return the eleveschoisis
	 */
	public List<String> getEleveschoisis() {
		return eleveschoisis;
	}


	/**
	 * @param eleveschoisis the eleveschoisis to set
	 */
	public void setEleveschoisis(List<String> eleveschoisis) {
		this.eleveschoisis = eleveschoisis;
	}
	
	/**
	 * Charger les évaluations, utile dans le cas ou on veut justifier une absence
	 * @param ev
	 */
	public void loadEvaluations(ActionEvent ev){
		if(codesequence==0){
			Repertoire.addMessageerreur("Aucune séquence sélectionnée");
			return;
		}
		
		if(codeclasse==null || codeclasse.isEmpty()){
			Repertoire.addMessageerreur("Aucune classe sélectionnée");
			return;
		}
		evaluations=this.service.listeevaluations(codesequence,codeclasse);
		eleves=this.service.listerElevesinscrits(codeclasse);
		
		if(evaluations.isEmpty())
			Repertoire.addMessageerreur("Aucune évaluation trouvée");
		
		if(eleves.isEmpty())
			Repertoire.addMessageerreur("Aucun élève trouvé");
	}

	
	/**
	 * Charger les évaluations, utile pour le cas d'annulation de justification
	 * @param ev
	 */
	public void loadEvaluationsOnly(ActionEvent ev){
		if(codesequence==0){
			Repertoire.addMessageerreur("Aucune séquence sélectionnée");
			return;
		}
		
		if(codeclasse==null || codeclasse.isEmpty()){
			Repertoire.addMessageerreur("Aucune classe sélectionnée");
			return;
		}
		evaluations=this.service.listeevaluations(codesequence,codeclasse);
		if(evaluations.isEmpty())
			Repertoire.addMessageerreur("Aucune évaluation trouvée");
	}

	/**
	 * Charge les élèves lorsque une évaluation est sélectionnée
	 */
	public void loadEleves(){
		eleves=this.service.listerElevesJustifies(codesequence,evaluation,codeclasse);
		if(eleves.isEmpty())
			Repertoire.addMessageerreur("Aucune absence justifiée");
	}
	
	public void loadElevesAbsents(){
		eleves=this.service.listerElevesAbsents(evaluation);
		if(eleves.isEmpty())
			Repertoire.addMessageerreur("Aucune absence justifiée");
	}
	
	
	/**
	 * Enregistre des justifications d'absence a un examen
	 * @return
	 */
	public String saveJustification(){
		if(evaluation==0){
			Repertoire.addMessageerreur("Aucune Evaluation fournie");
			return null;
		}
		
		if(eleveschoisis==null||eleveschoisis.isEmpty()){
			Repertoire.addMessageerreur("Aucun Elève sélectionné");
			return null;
		}
			
		try {
			this.service.savejustificationEvaluation(evaluation,eleveschoisis, motifabsence);
			
		} 
		catch (Exception e) {
			if(e.getCause()!=null){
				
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Notes Evaluation non trouvées");
						Repertoire.logError("Notes Evaluation non trouvées", getClass(), e);
					}
					else{
						Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
						Repertoire.logError("Erreur inconnue", getClass(), e);
					}
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				Repertoire.logError("Erreur innatendue", getClass(), e);
				
			}
			return null;
		}
		Repertoire.addMessageinfoEnregistrementOK("Justification absence");
		return OperationResults.navWithParam("visualiserNotes", null, null);
	}
	
	
	/**
	 * Annuler une justification
	 * @return
	 */
	public String annulerjustification(){
		if (this.evaluation!=0&&this.eleveschoisis!=null){
			try {
				this.service.annulerJustification(this.evaluation, eleveschoisis);
			} catch (Exception e) {
				if(e.getCause()!=null){
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Notes Evaluation Non trouvées");
						Repertoire.logError("Notes Evaluation non trouvée", getClass(), e);
					}
					
					else{
						Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
						Repertoire.logError("Erreur innatendue", getClass(), e);
					}
				}
				else{
					Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
					Repertoire.logError("Erreur innatendue", getClass(), e);
				}
				return null;
			}
			
			Repertoire.addMessageinfo("Annulation effectuée");
			return OperationResults.navWithParam("visualiserNotes", null, null);
		
		}
		Repertoire.addMessageerreur("Aucune classe sélectionnée");
		
		return null;
	}
}
