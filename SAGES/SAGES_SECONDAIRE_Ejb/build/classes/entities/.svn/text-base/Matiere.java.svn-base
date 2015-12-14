package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the matiere database table.
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name="Matiere.findAll", 
				query="select m from Matiere as m left join fetch m.animateurs where m.supprime=:supprime"), 
	@NamedQuery(name="Matiere.findByCode", 
				query="select m from Matiere as m left join fetch m.animateurs where m.supprime=:supprime and m.codematiere=:codematiere"), 
	@NamedQuery(name="Matiere.findByClasse", 
				query="select m from Matiere as m join m.cours as c where m.supprime=:supprime and c.supprime=:supprime and c.classe.codeclasse=:codeclasse and c.annee.anneeacademique=:annee"), 

})
public class Matiere implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codematiere;
	private String libelle;
	private String description;
	private boolean supprime;
	private List<Cour> cours;
	private List<AnimateurPedagogique> animateurs;

    public Matiere() {
    }


	@Id
	public String getCodematiere() {
		return this.codematiere;
	}

	public void setCodematiere(String codematiere) {
		this.codematiere = codematiere;
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

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(mappedBy="matiere")
	public List<Cour> getCours() {
		return cours;
	}


	public void setCours(List<Cour> cours) {
		this.cours = cours;
	}

	@OneToMany(mappedBy="matiere")
	public List<AnimateurPedagogique> getAnimateurs() {
		return animateurs;
	}


	public void setAnimateurs(List<AnimateurPedagogique> animateurs) {
		this.animateurs = animateurs;
	}
	
}