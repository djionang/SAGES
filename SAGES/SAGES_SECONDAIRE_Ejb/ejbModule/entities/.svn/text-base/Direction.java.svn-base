package entities;
 
import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the censeur database table.
 * 
 */
@Entity
public class Direction implements Serializable {
	private static final long serialVersionUID = 1L;
	private String login;
	private String fonction;
	private boolean supprime;
	private Utilisateur utilisateur;

    public Direction() {
    }


	@Id
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	//bi-directional one-to-one association to Utilisateur
	@OneToOne
	@JoinColumn(name="UTILISATEUR")
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	public String getFonction() {
		return fonction;
	}


	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	
}