package entities;
 
import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the composer database table.
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name="Composer.findAll", 
				query="select c from Composer as c where c.supprime=:supprime"),
	@NamedQuery(name="Composer.findByCode", 
				query="select c from Composer as c where c.supprime=:supprime and c.eleve.matricule=:matricule and c.id.codeevaluation=:codeevaluation and c.evaluation.cour.annee.anneeacademique=:annee"),
	@NamedQuery(name="Composer.findById", 
				query="select c from Composer as c where c.supprime=:supprime and c.eleve.ideleve=:ideleve and c.id.codeevaluation=:codeevaluation and c.evaluation.cour.annee.anneeacademique=:annee"),
	@NamedQuery(name="Composer.findByEvaluation", 
				query="select c from Composer as c where c.supprime=:supprime and c.evaluation.codeevaluation=:evaluation and c.eleve.annee.anneeacademique=:annee"),
	
	})
public class Composer implements Serializable {
	private static final long serialVersionUID = 1L;
	private ComposerPK id;
	private float note;
	private boolean supprime;
	private Evaluation evaluation;
	private Eleve eleve;
	private String login;
	private Date dateEnregistrement;
	private boolean absencejustifiee;
	private String justificationAbsence;

    public Composer() {
    }

	@EmbeddedId
	public ComposerPK getId() {
		return this.id;
	}

	public void setId(ComposerPK id) {
		this.id = id;
	}
	

	public float getNote() {
		return this.note;
	}

	public void setNote(float note) {
		this.note = note;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	//bi-directional many-to-one association to Evaluation
    @ManyToOne
    @MapsId("codeevaluation")
	@JoinColumn(name="CODEEVALUATION")
	public Evaluation getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}
	

	//bi-directional many-to-one association to Eleve
    @ManyToOne
    @MapsId("ideleve")
	@JoinColumn(name="ideleve")
	public Eleve getEleve() {
		return this.eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

   
	/**
	 * @return the absenceJustifiee
	 */
	public boolean isAbsencejustifiee() {
		return absencejustifiee;
	}


	/**
	 * @param absenceJustifiee the absenceJustifiee to set
	 */
	public void setAbsencejustifiee(boolean absencejustifiee) {
		this.absencejustifiee = absencejustifiee;
	}


	/**
	 * @return the justificationAbsence
	 */
	public String getJustificationAbsence() {
		return justificationAbsence;
	}


	/**
	 * @param justificationAbsence the justificationAbsence to set
	 */
	public void setJustificationAbsence(String justificationAbsence) {
		this.justificationAbsence = justificationAbsence;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Date getDateEnregistrement() {
		return dateEnregistrement;
	}

	public void setDateEnregistrement(Date dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}
	
}