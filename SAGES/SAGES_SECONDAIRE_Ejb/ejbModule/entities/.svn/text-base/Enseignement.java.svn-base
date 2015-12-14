package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the enseignement database table.
 * 
 */
@Entity
//Mes requetes de base sur l'entité
@NamedQueries({ 
	@NamedQuery(name="Enseignement.findAll", 
				query="select e from Enseignement as e where e.supprime=:supprime"), 
})
public class Enseignement implements Serializable {
	private static final long serialVersionUID = 1L;
	private String libelleens;
	private String description;
	private boolean supprime;
	private String type;
	private Etablissement etablissement;
	private List<Section> sections;

    public Enseignement() {
    }


	@Id
	public String getLibelleens() {
		return this.libelleens;
	}

	public void setLibelleens(String libelleens) {
		this.libelleens = libelleens;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


	//bi-directional many-to-one association to Etablissement
    @ManyToOne
	@JoinColumn(name="CODEETABLISSEMENT")
	public Etablissement getEtablissement() {
		return this.etablissement;
	}

	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}
	

	//bi-directional many-to-one association to Section
	@OneToMany(mappedBy="enseignement")
	public List<Section> getSections() {
		return this.sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	
}