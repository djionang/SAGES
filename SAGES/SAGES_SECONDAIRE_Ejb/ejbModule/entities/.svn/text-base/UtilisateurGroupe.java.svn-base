package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the posseder_role database table.
 * 
 */
@Entity
@Table(name="Utilisateur_Groupe")
public class UtilisateurGroupe implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private boolean supprime;
	private GroupeUser groupeuser;
	private Utilisateur utilisateur;

    public UtilisateurGroupe() {
    }

   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}

	//bi-directional many-to-one association to Utilisateur
    @ManyToOne
	@JoinColumn(name="GROUPEUTILISATEUR")
	public GroupeUser getGroupeuser() {
		return groupeuser;
	}


	public void setGroupeuser(GroupeUser groupeuser) {
		this.groupeuser = groupeuser;
	}


	//bi-directional many-to-one association to Utilisateur
    @ManyToOne
	@JoinColumn(name="UTILISATEUR")
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
}