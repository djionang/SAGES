package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the cycle database table.
 * 
 */
@Entity
//Mes requetes de base sur l'entité
@NamedQueries({ 
	@NamedQuery(name="Cycle.findAll", 
				query="select c from Cycle as c where c.supprime=:supprime"), 
	@NamedQuery(name="Cycle.findByCode", 
				query="select c from Cycle as c where c.supprime=:supprime and c.codecycle=:codecycle"), 

})
public class Cycle implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codecycle;
	private String libelle;
	private boolean supprime;
	private Section section;
	private List<Niveau> niveaus;

    public Cycle() {
    }


	@Id
	public String getCodecycle() {
		return this.codecycle;
	}

	public void setCodecycle(String codecycle) {
		this.codecycle = codecycle;
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


	//bi-directional many-to-one association to Section
    @ManyToOne
	@JoinColumn(name="CODESECTION")
	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}
	

	//bi-directional many-to-one association to Niveau
	@OneToMany(mappedBy="cycle")
	public List<Niveau> getNiveaus() {
		return this.niveaus;
	}

	public void setNiveaus(List<Niveau> niveaus) {
		this.niveaus = niveaus;
	}
	
}