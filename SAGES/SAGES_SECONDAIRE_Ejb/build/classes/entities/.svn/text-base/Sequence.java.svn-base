package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the sequence database table.
 * 
 */
@Entity
@Table(name="SEQUENCE",uniqueConstraints =
@UniqueConstraint( columnNames = {"NUMEROSEQUENCE", "ANNEEACADEMIQUE"})
)
@NamedQueries({
	@NamedQuery(name="Sequence.findAllByYear", 
			query="select s from Sequence as s where s.supprime=:supprime and s.trimestre.annee.anneeacademique=:annee"),
	@NamedQuery(name="Sequence.findOneByYear",
			query="select s from Sequence as s where s.supprime=:supprime and s.numerosequence=:numero and s.trimestre.annee.anneeacademique=:annee"),
	@NamedQuery(name="Sequence.maxInsertedId", 
			query="select max(s.numerosequence) from Sequence as s where s.trimestre.annee.anneeacademique=:annee and s.supprime=:supprime"),
	@NamedQuery(name="Sequence.findByid",
			query="select s from Sequence as s where s.supprime=:supprime and s.idsequence=:id and s.trimestre.annee.anneeacademique=:annee"),
	
})
public class Sequence implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idsequence;
	private int numerosequence;
	private Date datefin;
	private Date dateimpressionreleve;
	private Date datedebut;
	private boolean supprime;
	private boolean cloture;
	private List<Evaluation> evaluations;
	private Trimestre trimestre;

    public Sequence() {
    }

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
	public int getIdsequence() {
		return idsequence;
	}

	public void setIdsequence(int idsequence) {
		this.idsequence = idsequence;
	}
	
	public int getNumerosequence() {
		return numerosequence;
	}


	public void setNumerosequence(int numerosequence) {
		this.numerosequence = numerosequence;
	}

	
    @Temporal( TemporalType.DATE)
	public Date getDateimpressionreleve() {
		return this.dateimpressionreleve;
	}

	public void setDateimpressionreleve(Date dateimpressionreleve) {
		this.dateimpressionreleve = dateimpressionreleve;
	}

	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	//bi-directional many-to-one association to Evaluation
	@OneToMany(mappedBy="sequence")
	public List<Evaluation> getEvaluations() {
		return this.evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}
	

	//bi-directional many-to-one association to Trimestre
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="ANNEEACADEMIQUE", referencedColumnName="ANNEEACADEMIQUE"),
		@JoinColumn(name="NUMEROTRIMESTRE", referencedColumnName="NUMEROTRIMESTRE")
		})
	public Trimestre getTrimestre() {
		return this.trimestre;
	}

	public void setTrimestre(Trimestre trimestre) {
		this.trimestre = trimestre;
	}

	@Temporal( TemporalType.DATE)
	public Date getDatefin() {
		return datefin;
	}


	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	@Temporal( TemporalType.DATE)
	public Date getDatedebut() {
		return datedebut;
	}


	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	public boolean isCloture() {
		return cloture;
	}

	public void setCloture(boolean cloture) {
		this.cloture = cloture;
	}
		
}