package entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the enseignant database table.
 * 
 */
@Entity
//Mes requetes de base sur l'entité
@NamedQueries({ 
	@NamedQuery(name="Enseignant.findAll", 
				query="select e from Enseignant as e where e.supprime=:supprime"), 
	@NamedQuery(name="Enseignant.findByCode", 
				query="select e from Enseignant as e where e.supprime=:supprime and e.codeenseignant=:code"), 
	@NamedQuery(name="Enseignant.findByClasse", 
				query="select e ,c.matiere.codematiere from Enseignant as e join e.cours as c where e.supprime=:supprime and c.supprime=:supprime and c.classe.codeclasse=:codeclasse and c.classe.supprime=:supprime"), 
	@NamedQuery(name="Enseignant.findAllForClass", 
				query="select distinct e from Enseignant as e join e.cours as c where e.supprime=:supprime and c.supprime=:supprime and c.classe.codeclasse=:codeclasse and c.classe.supprime=:supprime"), 
		
})
public class Enseignant implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codeenseignant;
	private String nomens;
	private String prenomens;
	private String sexe;
	private boolean supprime;
	private String photo;
	private List<Cour> cours;
	private Utilisateur utilisateur;
	private List<EnsTitulaire> titularisations;
	private String comptences;
	
	private boolean vacataire;
	private BigDecimal salairehoraire;
	private int travailmensuel;

    public Enseignant() {
    }


	@Id
	public String getCodeenseignant() {
		return this.codeenseignant;
	}

	public void setCodeenseignant(String codeenseignant) {
		this.codeenseignant = codeenseignant;
	}


	public String getNomens() {
		return this.nomens;
	}

	public void setNomens(String nom) {
		this.nomens = nom;
	}


	public String getPrenomens() {
		return this.prenomens;
	}

	public void setPrenomens(String prenom) {
		this.prenomens = prenom;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	//bi-directional many-to-one association to Cour
	@OneToMany(mappedBy="enseignant")
	public List<Cour> getCours() {
		return this.cours;
	}

	public void setCours(List<Cour> cours) {
		this.cours = cours;
	}
		

	//bi-directional many-to-one association to Utilisateur
    @ManyToOne
	@JoinColumn(name="LOGIN")
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	

	//bi-directional many-to-one association to Trimestre
	@OneToMany(mappedBy="enseignant")
	public List<EnsTitulaire> getTitularisations() {
		return titularisations;
	}


	public void setTitularisations(List<EnsTitulaire> titularisations) {
		this.titularisations = titularisations;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getComptences() {
		return comptences;
	}


	public void setComptences(String comptences) {
		this.comptences = comptences;
	}


	public boolean isVacataire() {
		return vacataire;
	}


	public void setVacataire(boolean vacataire) {
		this.vacataire = vacataire;
	}


	public BigDecimal getSalairehoraire() {
		return salairehoraire;
	}


	public void setSalairehoraire(BigDecimal salairehoraire) {
		this.salairehoraire = salairehoraire;
	}


	public int getTravailmensuel() {
		return travailmensuel;
	}


	public void setTravailmensuel(int travailmensuel) {
		this.travailmensuel = travailmensuel;
	}


	public String getSexe() {
		return sexe;
	}


	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Enseignant)) {
			return false;
		}
		Enseignant castOther = (Enseignant)other;
		return 
			this.codeenseignant.equals(castOther.codeenseignant)
			&& this.nomens.equals(castOther.nomens);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codeenseignant.hashCode();
		
		return hash;
    }
	
}