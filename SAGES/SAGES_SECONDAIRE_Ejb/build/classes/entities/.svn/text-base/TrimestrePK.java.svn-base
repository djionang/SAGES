package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the trimestre database table.
 * 
 */
@Embeddable
public class TrimestrePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int numerotrimestre;
	private String anneeacademique;

    public TrimestrePK() {
    }

	public int getNumerotrimestre() {
		return this.numerotrimestre;
	}
	public void setNumerotrimestre(int numero) {
		this.numerotrimestre = numero;
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
		if (!(other instanceof TrimestrePK)) {
			return false;
		}
		TrimestrePK castOther = (TrimestrePK)other;
		return 
			(this.numerotrimestre == castOther.numerotrimestre)
			&& this.anneeacademique.equals(castOther.anneeacademique);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.numerotrimestre;
		hash = hash * prime + this.anneeacademique.hashCode();
		
		return hash;
    }
}