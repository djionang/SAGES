package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the personnel database table.
 * 
 */
@Entity
//Mes requetes de base sur l'entité
@NamedQueries({ 
	@NamedQuery(name="Personnel.findAll", 
				query="select p from Personnel as p where p.supprime=:supprime"), 
	@NamedQuery(name="Personnel.findByCode", 
				query="select p from Personnel as p where p.supprime=:supprime and p.codepersonnel=:codepersonnel"), 

})
public class Personnel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codepersonnel;
	private boolean actif;
	private String adresse;
	private String civilite;
	private Date datearrivee;
	private Date datedepart;
	private String email;
	private String fonction;
	private String nom;
	private String prenom;
	private String sexe;
	private boolean supprime;
	private String telephone;
	private List<Utilisateur> utilisateurs;
	private Etablissement etablissement;

    public Personnel() {
    }


	@Id
	public String getCodepersonnel() {
		return this.codepersonnel;
	}

	public void setCodepersonnel(String codepersonnel) {
		this.codepersonnel = codepersonnel;
	}



    
	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


    
	public String getCivilite() {
		return this.civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}


    @Temporal( TemporalType.DATE)
	public Date getDatearrivee() {
		return this.datearrivee;
	}

	public void setDatearrivee(Date datearrivee) {
		this.datearrivee = datearrivee;
	}


    @Temporal( TemporalType.DATE)
	public Date getDatedepart() {
		return this.datedepart;
	}

	public void setDatedepart(Date datedepart) {
		this.datedepart = datedepart;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getFonction() {
		return this.fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}


	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public boolean isActif() {
		return actif;
	}


	public void setActif(boolean actif) {
		this.actif = actif;
	}


	public String getSexe() {
		return sexe;
	}


	public void setSexe(String sexe) {
		this.sexe = sexe;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	//bi-directional many-to-one association to Utilisateur
	@OneToMany(mappedBy="personnel")
	public List<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	//bi-directional many-to-one association to Annee
    @ManyToOne
	public Etablissement getEtablissement() {
		return etablissement;
	}


	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}
	
}