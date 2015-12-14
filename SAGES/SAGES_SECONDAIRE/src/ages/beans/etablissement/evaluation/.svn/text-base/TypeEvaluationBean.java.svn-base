package ages.beans.etablissement.evaluation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import utils.OperationResults;
import utils.Service;
import utils.Repertoire;

import ages.beans.GenericBean;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;

@ManagedBean(name="typeEvaluationBean")
@RequestScoped
public class TypeEvaluationBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
		
	
	private String typeevaluation;
		
	private int coefficient;
	
	private String description;

	public TypeEvaluationBean() {
	}
	


	public void setService(Service service) {
		this.service = service;
	}

	

	/**
	 * @return the coefficient
	 */
	public int getCoefficient() {
		return coefficient;
	}



	/**
	 * @param coefficient the coefficient to set
	 */
	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}



	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}



	/**
	 * @return the typeevaluation
	 */
	public String getTypeevaluation() {
		return typeevaluation;
	}



	/**
	 * @param typeevaluation the typeevaluation to set
	 */
	public void setTypeevaluation(String typeevaluation) {
		this.typeevaluation = typeevaluation;
	}



	/**
	 * Enregistre la classe dans la base
	 * @return la chaine de navigation pur les cas erreur et succes
	 * @throws ElementNOtFoundException 
	 */
	public String savetypeEvaluation(){
		try {
			this.service.saveTypeEvaluation(typeevaluation, coefficient,description);
		} 
		catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(DuplicateKeyException.class)){
					Repertoire.addMessageerreur("Type Evaluation déja enregistré");
				}
				else{
						Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
						e.printStackTrace();
					}
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				e.printStackTrace();
			}

			return OperationResults.FAILURE;
		}
		Repertoire.addMessageinfoEnregistrementOK("Type Evaluation");
		return OperationResults.navWithParam("visualisation", "typeevaluation", typeevaluation);
	}
	
	public String modifiertypeevaluation(){
		try {
			this.service.modifierTypeEvaluation(typeevaluation, coefficient,description);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Evaluation Non trouvée");
					e.printStackTrace();
					return OperationResults.FAILURE;
				}
				
				else{
					Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
					e.printStackTrace();
					return OperationResults.FAILURE;
				}
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				e.printStackTrace();
				return OperationResults.FAILURE;
			}
			
		}
		Repertoire.addMessageinfo("Mise à jour sauvegardée");
		return OperationResults.navWithParam("visualisation", "typeevaluation",typeevaluation);
	}
	
	public String supprimertypeevaluation(){
		if (this.typeevaluation!=null){
			try {
				this.service.deleteTypeEvaluation(this.typeevaluation);
			} catch (Exception e) {
				if(e.getCause()!=null){
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Evaluation Non trouvée");
						e.printStackTrace();
						return OperationResults.FAILURE;
					}
					
					else{
						Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
						e.printStackTrace();
						return OperationResults.FAILURE;
					}
				}
				else{
					Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
					e.printStackTrace();
					return OperationResults.FAILURE;
				}
				
			}
			
			Repertoire.addMessageinfo("Suppression effectuée");
			return OperationResults.navWithParam("listing", null, null);
		
		}
		return null;
	}
	
	public void initTypeEvaluation(){
		
		if(this.typeevaluation!=null&!this.typeevaluation.isEmpty())
			this.service.initialiseTypeEvaluationBean(this);
	}
}
