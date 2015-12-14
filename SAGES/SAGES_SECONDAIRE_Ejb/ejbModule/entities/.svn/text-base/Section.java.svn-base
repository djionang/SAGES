package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the section database table.
 * 
 */
@Entity
//Mes requetes de base sur l'entité
@NamedQueries({ 
	@NamedQuery(name="Section.findAll", 
				query="select s from Section as s where s.supprime=:supprime"), 
	@NamedQuery(name="Section.findByCode", 
				query="select s from Section as s where s.supprime=:supprime and s.codesection=:codesection"), 

})
public class Section implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codesection;
	private String description;
	private String libelle;
	private boolean supprime;
	private List<Cycle> cycles;
	private Enseignement enseignement;

    public Section() {
    }


	@Id
	public String getCodesection() {
		return this.codesection;
	}

	public void setCodesection(String codesection) {
		this.codesection = codesection;
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


	//bi-directional many-to-one association to Cycle
	@OneToMany(mappedBy="section")
	public List<Cycle> getCycles() {
		return this.cycles;
	}

	public void setCycles(List<Cycle> cycles) {
		this.cycles = cycles;
	}
	

	//bi-directional many-to-one association to Enseignement
    @ManyToOne
	@JoinColumn(name="LIBELLEENS")
	public Enseignement getEnseignement() {
		return this.enseignement;
	}

	public void setEnseignement(Enseignement enseignement) {
		this.enseignement = enseignement;
	}
	
}