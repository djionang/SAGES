package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the trimestre database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Trimestre.findAll", 
			query="select t from Trimestre as t where t.supprime=:supprime and t.annee.anneeacademique=:annee"),
	@NamedQuery(name="Trimestre.findByCode", 
			query="select t from Trimestre as t where t.supprime=:supprime and t.id.numerotrimestre=:numero and t.annee.anneeacademique=:annee"),
	@NamedQuery(name="Trimestre.maxInsertedId", 
			query="select max(t.id.numerotrimestre) from Trimestre as t where t.annee.anneeacademique=:annee and t.supprime=:supprime"),
	@NamedQuery(name="Trimestre.findSequences", 
			query="select s.numerosequence from Trimestre as t join t.sequences as s where t.annee.anneeacademique=:annee and t.supprime=:supprime and t.id.numerotrimestre=:numero"),

	})
public class Trimestre implements Serializable {
	private static final long serialVersionUID = 1L;
	private TrimestrePK id;
	private Date datedebut;
	private Date datefin;
	private Date dateimpressionbulletin;
	private boolean supprime;
	private List<Sequence> sequences;
	private Annee annee;

    public Trimestre() {
    }


	@EmbeddedId
	public TrimestrePK getId() {
		return this.id;
	}

	public void setId(TrimestrePK id) {
		this.id = id;
	}
	

    @Temporal( TemporalType.DATE)
	public Date getDatedebut() {
		return this.datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}


    @Temporal( TemporalType.DATE)
	public Date getDatefin() {
		return this.datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}


    @Temporal( TemporalType.DATE)
	public Date getDateimpressionbulletin() {
		return this.dateimpressionbulletin;
	}

	public void setDateimpressionbulletin(Date dateimpressionbulletin) {
		this.dateimpressionbulletin = dateimpressionbulletin;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	//bi-directional many-to-one association to Sequence
	@OneToMany(mappedBy="trimestre")
	public List<Sequence> getSequences() {
		return this.sequences;
	}

	public void setSequences(List<Sequence> sequences) {
		this.sequences = sequences;
	}
	

	//bi-directional many-to-one association to Annee
    @ManyToOne
    @MapsId("anneeacademique")
	@JoinColumn(name="ANNEEACADEMIQUE")
	public Annee getAnnee() {
		return this.annee;
	}

	public void setAnnee(Annee annee) {
		this.annee = annee;
	}
	
}