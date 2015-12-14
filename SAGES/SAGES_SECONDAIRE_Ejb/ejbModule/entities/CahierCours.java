package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the etre_decrit database table.
 * 
 */
@Entity
@Table(name="cahiercours")
public class CahierCours implements Serializable {
	private static final long serialVersionUID = 1L;
	private CahierCoursPK id;
	private boolean supprime;
	private CahierDeTexte cahierDeTexte;
	private PartieCour partieCour;

    public CahierCours() {
    }


	@EmbeddedId
	public CahierCoursPK getId() {
		return this.id;
	}

	public void setId(CahierCoursPK id) {
		this.id = id;
	}
	

	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	//bi-directional many-to-one association to CahierDeTexte
    @ManyToOne
	@JoinColumn(name="CODECDT")
    @MapsId("codetexte")
	public CahierDeTexte getCahierDeTexte() {
		return this.cahierDeTexte;
	}

	public void setCahierDeTexte(CahierDeTexte cahierDeTexte) {
		this.cahierDeTexte = cahierDeTexte;
	}
	

	//bi-directional many-to-one association to PartieCour
    @ManyToOne
	@JoinColumn(name="PARTIECOURS")
    @MapsId("codepartie")
	public PartieCour getPartieCour() {
		return this.partieCour;
	}

	public void setPartieCour(PartieCour partieCour) {
		this.partieCour = partieCour;
	}
	
}