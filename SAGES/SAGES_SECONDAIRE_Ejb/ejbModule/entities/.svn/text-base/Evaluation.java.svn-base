package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the evaluation database table.
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name="Evaluation.findAll", 
				query="select e from Evaluation as e left join fetch e.programmations where e.supprime=:supprime and e.cour.annee.anneeacademique=:annee"),
	@NamedQuery(name="Evaluation.findByCode", 
				query="select distinct e from Evaluation as e left join fetch e.programmations where e.codeevaluation=:codeevaluation and e.supprime=:supprime"), 
	@NamedQuery(name="Evaluation.findPourcentageEvalue", 
				query="select sum(e.typeEvaluation.coefficient) from Evaluation as e where e.supprime=:supprime and e.cour=:cours and e.sequence.idsequence=:idsequence"), 
	@NamedQuery(name="Evaluation.findPourcentageEvalueMinus", 
				query="select sum(e.typeEvaluation.coefficient) from Evaluation as e where e.supprime=:supprime and e.cour=:cours and e.codeevaluation<>:code"), 
	@NamedQuery(name="Evaluation.findByClassSeq", 
				query="select distinct e from Evaluation as e left join fetch e.programmations where e.cour.annee.anneeacademique=:annee and e.cour.classe.codeclasse=:classe and e.supprime=:supprime and e.sequence.idsequence=:sequence"),
	@NamedQuery(name="Evaluation.findByClassSeqMat", 
				query="select distinct e from Evaluation as e left join fetch e.programmations where e.cour.annee.anneeacademique=:annee and e.cour.classe.codeclasse=:classe and e.cour.matiere.codematiere=:matiere and e.supprime=:supprime and e.sequence.idsequence=:sequence"),
	@NamedQuery(name="Evaluation.EleveEvalues", 
				query="select distinct e from Evaluation ev join ev.cour c join c.classe cl join cl.eleves e where c.annee.anneeacademique=:annee  and ev.supprime=:supprime and ev.codeevaluation=:evaluation"),


})

public class Evaluation implements Serializable {
	private static final long serialVersionUID = 1L;
	private int codeevaluation;
	private String libelle;
	private boolean supprime;
	private List<Composer> composers;
	private List<Programmation> programmations;
	private TypeEvaluation typeEvaluation;
	private Cour cour;
	private Sequence sequence;

    public Evaluation() {
    }

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCodeevaluation() {
		return this.codeevaluation;
	}

	public void setCodeevaluation(int codeevaluation) {
		this.codeevaluation = codeevaluation;
	}


	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	//bi-directional many-to-one association to Composer
	@OneToMany(mappedBy="evaluation")
	public List<Composer> getComposers() {
		return this.composers;
	}

	public void setComposers(List<Composer> composers) {
		this.composers = composers;
	}
		

	/**
	 * @return the programmations
	 */
	//bi-directional many-to-one association to Deroulersalleevaluation
	@OneToMany(mappedBy="evaluation")
	public List<Programmation> getProgrammations() {
		return programmations;
	}


	/**
	 * @param programmations the programmations to set
	 */
	public void setProgrammations(List<Programmation> programmations) {
		this.programmations = programmations;
	}


	//bi-directional many-to-one association to TypeEvaluation
    @ManyToOne
	@JoinColumn(name="TYPEEVALUATION")
	public TypeEvaluation getTypeEvaluation() {
		return this.typeEvaluation;
	}

	public void setTypeEvaluation(TypeEvaluation typeEvaluation) {
		this.typeEvaluation = typeEvaluation;
	}
	

	//bi-directional many-to-one association to Cour
    @ManyToOne
	@JoinColumn(name="CODECOURS")
	public Cour getCour() {
		return this.cour;
	}

	public void setCour(Cour cour) {
		this.cour = cour;
	}
	
	//bi-directional many-to-one association to Sequence
    @ManyToOne
    @JoinColumn(name="IDSEQUENCE")
	public Sequence getSequence() {
		return this.sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}
	
}