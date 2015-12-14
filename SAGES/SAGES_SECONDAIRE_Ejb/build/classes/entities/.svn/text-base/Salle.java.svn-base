package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the salle database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Salle.findAll", 
			query="select s from Salle as s where s.supprime=:supprime"),
	@NamedQuery(name="Salle.findtypes",
			query="select distinct s.typesalle from Salle as s"),
	@NamedQuery(name="Salle.findByCode", 
			query="select s from Salle as s where s.supprime=:supprime and s.codesalle=:codesalle"),
	
	})
public class Salle implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codesalle;
	private int capacite;
	private String description;
	private String libelle;
	private boolean supprime;
	private TypeSalle typesalle;
	private List<Programmation> programmations;

    public Salle() {
    }


	@Id
	public String getCodesalle() {
		return this.codesalle;
	}

	public void setCodesalle(String codesalle) {
		this.codesalle = codesalle;
	}


	public int getCapacite() {
		return this.capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	//bi-directional many-to-one association to TypeSalle
    @ManyToOne
	@JoinColumn(name="TYPESALLE")
	public TypeSalle getTypesalle() {
		return this.typesalle;
	}

	public void setTypesalle(TypeSalle typesalle) {
		this.typesalle = typesalle;
	}

	//bi-directional many-to-one association to Deroulersalleevaluation
		@OneToMany(mappedBy="salle")
	public List<Programmation> getProgrammations() {
		return programmations;
	}


	/**
	 * @param programmations the programmations to set
	 */
	public void setProgrammations(List<Programmation> programmations) {
		this.programmations = programmations;
	}
	
}