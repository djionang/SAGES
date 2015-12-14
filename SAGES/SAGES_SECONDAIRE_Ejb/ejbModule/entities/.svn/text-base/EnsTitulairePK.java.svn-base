package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EnsTitulairePK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codeclasse;
	private String codeenseignant;
	private String anneeacademique;
	
	public String getCodeclasse() {
		return codeclasse;
	}

	public void setCodeclasse(String codeclasse) {
		this.codeclasse = codeclasse;
	}

	public String getCodeenseignant() {
		return codeenseignant;
	}

	public void setCodeenseignant(String codeenseignant) {
		this.codeenseignant = codeenseignant;
	}

	public String getAnneeacademique() {
		return anneeacademique;
	}

	public void setAnneeacademique(String anneeacademique) {
		this.anneeacademique = anneeacademique;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EnsTitulairePK)) {
			return false;
		}
		EnsTitulairePK castOther = (EnsTitulairePK)other;
		return 
			this.codeclasse.equals(castOther.codeclasse)
			&& this.codeenseignant.equals(castOther.codeenseignant)
			&& this.anneeacademique.equals(castOther.anneeacademique);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codeenseignant.hashCode();
		hash = hash * prime + this.codeclasse.hashCode();
		hash = hash * prime + this.anneeacademique.hashCode();
		return hash;
	}
}
