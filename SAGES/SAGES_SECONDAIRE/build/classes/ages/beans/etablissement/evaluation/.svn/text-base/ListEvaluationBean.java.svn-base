package ages.beans.etablissement.evaluation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import utils.Service;
import ages.beans.GenericBean;

@ManagedBean(name="listEvaluationBean")
@ViewScoped
public class ListEvaluationBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<EvaluationBean> evaluations;
	
	private int sequence;
	private String classe;
	
	public void setService(Service service) {
		this.service = service;
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
	 * @return the sequence
	 */
	public int getSequence() {
		return sequence;
	}
	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	/**
	 * @return the classe
	 */
	public String getClasse() {
		return classe;
	}
	/**
	 * @param classe the classe to set
	 */
	public void setClasse(String classe) {
		this.classe = classe;
	}
	
	@PostConstruct
	public void initEvaluations(){
		setEvaluations(this.service.listeevaluations());
	}
	
	
	
	/**
	 * Rafraichit la fenetre des evaluations, avec en application le filtre émis
	 * @param ev
	 */
	public void refresh(ActionEvent ev){
		
		System.out.println("***********************************INSIDE***********sequence="+sequence+"*********classe="+classe+"****************");
		if(sequence==0){
			if(classe==null ||classe.isEmpty()){  //toutes les classes et toutes les sequences
				evaluations=this.service.listeevaluations();
			}
			else{	// toutes les sequences, pour une classe

				List<EvaluationBean> evals=this.service.listeevaluations();
				
				evaluations=new ArrayList<EvaluationBean>();
				
				for(int i=0;i<evals.size();i++){
					if(evals.get(i).getCodeclasse().compareTo(classe)==0){
						evaluations.add(evals.get(i));
					}
				}
			}
		}
		else{
			if(classe==null ||classe.isEmpty()){  //toutes les classes, une seule sequence

				List<EvaluationBean> evals=this.service.listeevaluations();
				
				evaluations=new ArrayList<EvaluationBean>();
				
				for(int i=0;i<evals.size();i++){
					if(evals.get(i).getCodesequence()==sequence){
						evaluations.add(evals.get(i));
					}
				}
			}
			else{	// une classe et une sequence
				evaluations=this.service.listeevaluations(sequence,classe);
			}
		}
		
	}

}
