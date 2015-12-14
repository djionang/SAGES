package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the optionclasse database table.
 * 
 */
@Entity
//Mes requetes de base sur l'entité
@NamedQueries({ 
	@NamedQuery(name="OptionSerie.findAll", 
				query="select o from OptionSerie as o where o.supprime=:supprime"), 
	@NamedQuery(name="OptionSerie.findByCode", 
				query="select o from OptionSerie as o where o.supprime=:supprime and o.codeoption=:codeoption"), 
	@NamedQuery(name="OptionSerie.findByLevel", 
				query="select o from OptionSerie as o where o.supprime=:supprime and o.niveau.codeniveau=:niveau"), 


})
public class OptionSerie implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codeoption;
	private String libelle;
	private boolean supprime;
	private List<Classe> classes;
	private Niveau niveau;

    public OptionSerie() {
    }


	@Id
	public String getCodeoption() {
		return this.codeoption;
	}

	public void setCodeoption(String codeoption) {
		this.codeoption = codeoption;
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


	//bi-directional many-to-one association to Classe
	@OneToMany(mappedBy="optionserie")
	public List<Classe> getClasses() {
		return this.classes;
	}

	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}

	//bi-directional many-to-one association to Annee
    @ManyToOne
	@JoinColumn(name="CODENIVEAU")
	public Niveau getNiveau() {
		return niveau;
	}


	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}


}