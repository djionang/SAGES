package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the exercice database table.
 * 
 */
@Entity
public class Exercice implements Serializable {
	private static final long serialVersionUID = 1L;
	private int codeexercice;
	private String description;
	private String libelle;
	private boolean supprime;
	private String type;
	private CahierDeTexte cahierDeTexte;

    public Exercice() {
    }


	@Id
	public int getCodeexercice() {
		return this.codeexercice;
	}

	public void setCodeexercice(int codeexercice) {
		this.codeexercice = codeexercice;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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


	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


	//bi-directional many-to-one association to CahierDeTexte
    @ManyToOne
	@JoinColumn(name="CODETEXTE")
	public CahierDeTexte getCahierDeTexte() {
		return this.cahierDeTexte;
	}

	public void setCahierDeTexte(CahierDeTexte cahierDeTexte) {
		this.cahierDeTexte = cahierDeTexte;
	}
	
}