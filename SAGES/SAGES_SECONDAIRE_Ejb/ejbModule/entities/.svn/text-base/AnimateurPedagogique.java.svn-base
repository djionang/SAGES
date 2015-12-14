package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AnimateurPedagogique implements Serializable{
	private static final long serialVersionUID = 1L;
	private AnimateurPedagogiquePK id;
	private boolean supprime;
	private Enseignant enseignant;
	private Matiere matiere;
	private Date dateDecision;

    public AnimateurPedagogique() {
    }


    @EmbeddedId
	public AnimateurPedagogiquePK getId() {
		return id;
	}



	public void setId(AnimateurPedagogiquePK id) {
		this.id = id;
	}



	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	//bi-directional many-to-one association to Enseignant
	@MapsId("codeenseignant")
    @ManyToOne
	@JoinColumn(name="CODEENSEIGNANT")
	public Enseignant getEnseignant() {
		return this.enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	

	//bi-directional many-to-one association to Matiere
	@MapsId("codematiere")
    @ManyToOne
	@JoinColumn(name="CODEMATIERE")
	public Matiere getMatiere() {
		return this.matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	@Temporal(TemporalType.DATE)
	public Date getDateDecision() {
		return dateDecision;
	}


	public void setDateDecision(Date dateDecision) {
		this.dateDecision = dateDecision;
	}
}
