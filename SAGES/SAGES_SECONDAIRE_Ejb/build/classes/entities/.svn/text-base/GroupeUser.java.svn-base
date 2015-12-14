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
	@NamedQuery(name="GroupeUser.findAll", 
				query="select g from GroupeUser as g where g.supprime=:supprime"), 
	@NamedQuery(name="GroupeUser.findByCode", 
				query="select g from GroupeUser as g where g.supprime=:supprime and g.idgroupe=:idgroupe"), 
	@NamedQuery(name="GroupeUser.findByLabel", 
				query="select g from GroupeUser as g where g.supprime=:supprime and g.libelle=:libelle"), 
	@NamedQuery(name="GroupeUser.listTypes", 
				query="select distinct g.libelle from GroupeUser as g"),
})
public class GroupeUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idgroupe;
	private String libelle;
	private String description;
	private boolean supprime;
	private float montant;
	private List<UtilisateurGroupe> utilisateurgroupes;
	private List<GpUserRole> gpUserRoles;

    public GroupeUser() {
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

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdgroupe() {
		return idgroupe;
	}


	public void setIdgroupe(int idgroupe) {
		this.idgroupe = idgroupe;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	//bi-directional many-to-one association to PossederRole
	@OneToMany(mappedBy="groupeuser")
	public List<UtilisateurGroupe> getUtilisateurgroupes() {
		return utilisateurgroupes;
	}


	public void setUtilisateurgroupes(List<UtilisateurGroupe> utilisateurgroupes) {
		this.utilisateurgroupes = utilisateurgroupes;
	}
	
	@OneToMany(mappedBy="groupeuser")
	public List<GpUserRole> getGpUserRoles() {
		return gpUserRoles;
	}

	public void setGpUserRoles(List<GpUserRole> gpUserRoles) {
		this.gpUserRoles = gpUserRoles;
	}


	public float getMontant() {
		return montant;
	}


	public void setMontant(float montant) {
		this.montant = montant;
	}

	
}