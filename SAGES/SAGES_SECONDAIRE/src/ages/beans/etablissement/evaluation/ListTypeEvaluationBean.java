package ages.beans.etablissement.evaluation;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import utils.Service;
import ages.beans.GenericBean;

@ManagedBean(name="listTypeEvaluationBean")
@RequestScoped
public class ListTypeEvaluationBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<TypeEvaluationBean> typeevaluations;
	
	
	public void setService(Service service) {
		this.service = service;
	}
	
	
	/**
	 * @return the typeevaluations
	 */
	public List<TypeEvaluationBean> getTypeevaluations() {
		return typeevaluations;
	}


	/**
	 * @param typeevaluations the typeevaluations to set
	 */
	public void setTypeevaluations(List<TypeEvaluationBean> typeevaluations) {
		this.typeevaluations = typeevaluations;
	}


	@PostConstruct
	public void initTypesEvaluation(){
		this.setTypeevaluations(this.service.listerTypesEvaluations());
	}

}
