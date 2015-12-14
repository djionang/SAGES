package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the type_evaluation database table.
 * 
 */
@Entity
@Table(name="type_evaluation")
@NamedQueries({ 
	@NamedQuery(name="TypeEvaluation.findAll", 
				query="select t from TypeEvaluation as t where t.supprime=:supprime"),
	@NamedQuery(name="TypeEvaluation.findByCode", 
				query="select t from TypeEvaluation as t where t.type=:typeevaluation and t.supprime=:supprime"), 
	})
public class TypeEvaluation implements Serializable {
	private static final long serialVersionUID = 1L;
	private String type;
	private int coefficient;
	private boolean supprime;
	private String description;
	private List<Evaluation> evaluations;

    public TypeEvaluation() {
    }


	@Id
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public int getCoefficient() {
		return this.coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	//bi-directional many-to-one association to Evaluation
	@OneToMany(mappedBy="typeEvaluation")
	public List<Evaluation> getEvaluations() {
		return this.evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
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
	
}