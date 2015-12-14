package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the posseder_role database table.
 * 
 */
@Entity
@Table(name="GROUPEUSER_ROLE")
@NamedQueries({ 
	@NamedQuery(name="GpUserRole.findAll", 
				query="select g from GpUserRole as g where g.supprime=:supprime"), 
	@NamedQuery(name="GpUserRole.findById", 
				query="select g from GpUserRole as g where g.supprime=:supprime and g.idgroupeRole=:idgrouperole"), 
	@NamedQuery(name="GpUserRole.findBylibelle", 
				query="select g from GpUserRole as g where g.supprime=:supprime and g.libelle=:libelle"),
	@NamedQuery(name="GpUserRole.findByGroupeUser", 
				query="select g from GpUserRole as g where g.supprime=:supprime and g.groupeuser.idgroupe=:idgroupe"),
})
public class GpUserRole implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idgroupeRole;
	private String libelle;
	private boolean supprime;
	private ItemRole role;
	private GroupeUser groupeuser;

    public GpUserRole() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdgroupeRole() {
		return idgroupeRole;
	}


	public void setIdgroupeRole(int idgroupeRole) {
		this.idgroupeRole = idgroupeRole;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	//bi-directional many-to-one association to Role
    @ManyToOne
    @JoinColumn(name="ROLE")
	public ItemRole getRole() {
		return this.role;
	}

	public void setRole(ItemRole role) {
		this.role = role;
	}


	//bi-directional many-to-one association to Utilisateur
    @ManyToOne
	@JoinColumn(name="GROUPEUSER")
	public GroupeUser getGroupeuser() {
		return groupeuser;
	}


	public void setGroupeuser(GroupeUser groupeuser) {
		this.groupeuser = groupeuser;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
		
	
}