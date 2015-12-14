package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the groupematiere database table.
 * 
 */
@Entity

@NamedQueries({ 
	@NamedQuery(name="Groupematiere.findAll", 
				query="select gm from Groupematiere as gm where gm.supprime=:supprime"), 
	@NamedQuery(name="Groupematiere.findByCode", 
				query="select gm from Groupematiere as gm where gm.supprime=:supprime and gm.libellegm=:libellegm"), 
	@NamedQuery(name="Groupematiere.findByUCode", 
				query="select gm from Groupematiere as gm where gm.libellegm=:libellegm"), 

})
public class Groupematiere implements Serializable {
	private static final long serialVersionUID = 1L;
	private String libellegm;
	private boolean supprime;
	private String description;

	private List<Cour> cours;


	public Groupematiere() {
    }

	@Id
	public String getLibellegm() {
		return this.libellegm;
	}

	public void setLibellegm(String libelle) {
		this.libellegm = libelle;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(mappedBy="groupematiere")
	public List<Cour> getCours() {
		return cours;
	}


	public void setCours(List<Cour> cours) {
		this.cours = cours;
	}
	
}