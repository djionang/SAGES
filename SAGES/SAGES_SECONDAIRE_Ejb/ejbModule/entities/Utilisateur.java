package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the utilisateur database table.
 * 
 */
@Entity
//Mes requetes de base sur l'entité
@NamedQueries({ 
	@NamedQuery(name="Utilisateur.findAll", 
				query="select u from Utilisateur as u"), 
	@NamedQuery(name="Utilisateur.findAllValid", 
				query="select u from Utilisateur as u where u.supprime=:supprime"),
	@NamedQuery(name="Utilisateur.findByLogin", 
				query="select u from Utilisateur as u where u.supprime=:supprime and u.login=:login"), 
				@NamedQuery(name="Utilisateur.findById", 
				query="select u from Utilisateur as u where u.supprime=:supprime and u.idutilisateur=:iduser"),
 

})
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idutilisateur;
	private String login;
	private String password;
	private String typeuser;
	private boolean supprime;
	private List<Enseignant> enseignants;
	private List<UtilisateurGroupe> utilisateurgroupes;
	private List<PreInscription> preInscriptions;
	private Personnel personnel;

    public Utilisateur() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdutilisateur() {
		return idutilisateur;
	}


	public void setIdutilisateur(int idutilisateur) {
		this.idutilisateur = idutilisateur;
	}

	//bi-directional many-to-one association to PossederRole
	@OneToMany(mappedBy="utilisateur")		
	public List<UtilisateurGroupe> getUtilisateurgroupes() {
		return utilisateurgroupes;
	}

	public void setUtilisateurgroupes(List<UtilisateurGroupe> utilisateurgroupes) {
		this.utilisateurgroupes = utilisateurgroupes;
	}

	@Column(nullable=false,unique=true)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}



	//bi-directional many-to-one association to Enseignant
	@OneToMany(mappedBy="utilisateur")
	public List<Enseignant> getEnseignants() {
		return this.enseignants;
	}

	public void setEnseignants(List<Enseignant> enseignants) {
		this.enseignants = enseignants;
	}


	//bi-directional many-to-one association to PreInscription
	@OneToMany(mappedBy="utilisateur")
	public List<PreInscription> getPreInscriptions() {
		return this.preInscriptions;
	}

	public void setPreInscriptions(List<PreInscription> preInscriptions) {
		this.preInscriptions = preInscriptions;
	}

	//bi-directional many-to-one association to Personnel
    @ManyToOne
	@JoinColumn(name="CODEPERSONNEL")
	public Personnel getPersonnel() {
		return this.personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}


	public String getTypeuser() {
		return typeuser;
	}


	public void setTypeuser(String typeuser) {
		this.typeuser = typeuser;
	}


}