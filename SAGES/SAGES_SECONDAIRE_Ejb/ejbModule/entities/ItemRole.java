package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name="ItemRole.findAll", 
				query="select i from ItemRole as i where i.supprime=:supprime"), 
	@NamedQuery(name="ItemRole.findById", 
				query="select i from ItemRole as i where i.supprime=:supprime and i.iditem=:iditem"), 
	@NamedQuery(name="ItemRole.findBylibelle", 
				query="select i from ItemRole as i where i.supprime=:supprime and i.libelle=:libelle"),
})
public class ItemRole implements Serializable {
	private static final long serialVersionUID = 1L;
	private int iditem;
	private String libelle;
	private String description;
	private boolean supprime;
	private List<GpUserRole> gprole;

    public ItemRole() {
    }

	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIditem() {
		return iditem;
	}

	public void setIditem(int iditem) {
		this.iditem = iditem;
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

	//bi-directional many-to-one association to GpUserRole
	@OneToMany(mappedBy="role")
	public List<GpUserRole> getGprole() {
		return gprole;
	}

	public void setGprole(List<GpUserRole> gprole) {
		this.gprole = gprole;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ItemRole)) {
			return false;
		}
		ItemRole castOther = (ItemRole)other;
		return 
			this.iditem==castOther.iditem
			&& this.libelle.compareToIgnoreCase(castOther.libelle)==0
			&& this.description.compareToIgnoreCase(castOther.description)==0;

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + String.valueOf(iditem).hashCode();
		hash = hash * prime + libelle.hashCode();
		hash = hash * prime + description.hashCode();
		
		return hash;
    }
	
}