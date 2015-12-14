package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the parametre_tranche database table.
 * 
 */
@Embeddable
public class ParametreTranchePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String codeclasse;
	private int numero;
	private String anneeacademique;

    public ParametreTranchePK() {
    }

	public String getCodeclasse() {
		return this.codeclasse;
	}
	public void setCodeclasse(String codeclasse) {
		this.codeclasse = codeclasse;
	}

	public int getNumero() {
		return this.numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
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
		if (!(other instanceof ParametreTranchePK)) {
			return false;
		}
		ParametreTranchePK castOther = (ParametreTranchePK)other;
		return 
			this.codeclasse.compareTo(castOther.codeclasse)==0
			&& (this.numero == castOther.numero)
			&& this.anneeacademique.compareTo(castOther.anneeacademique)==0;

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codeclasse.hashCode();
		hash = hash * prime + this.numero;
		hash = hash * prime + this.anneeacademique.hashCode();
		
		return hash;
    }
}