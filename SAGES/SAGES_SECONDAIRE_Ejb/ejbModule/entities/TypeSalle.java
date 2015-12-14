package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the tranche database table.
 * 
 */
@Entity

//mes requêtes sur l'entités

@NamedQueries({
	@NamedQuery(name="TypeSalle.findAll", 
			query="select t from TypeSalle as t where t.supprime=:supprime"),
	@NamedQuery(name="TypeSalle.findByCode",
			query="select t from TypeSalle as t where t.supprime=:supprime and t.id=:id"),
	@NamedQuery(name="TypeSalle.findBylibelle",
			query="select t from TypeSalle as t where t.supprime=:supprime and t.libelle=:libelle"),

})

public class TypeSalle implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String libelle;
	
	private String description;
	
	private boolean supprime;
	
	private List<Salle> salles;

    public TypeSalle() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(mappedBy="typesalle")
	public List<Salle> getSalles() {
		return salles;
	}


	public void setSalles(List<Salle> salles) {
		this.salles = salles;
	}


	public boolean isSupprime() {
		return supprime;
	}


	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}
	
}