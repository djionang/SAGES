package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the etre_decrit database table.
 * 
 */
@Embeddable
public class CahierCoursPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int codepartie;
	private int codetexte;

    public CahierCoursPK() {
    }

	public int getCodepartie() {
		return codepartie;
	}

	public void setCodepartie(int codepartie) {
		this.codepartie = codepartie;
	}

	public int getCodetexte() {
		return codetexte;
	}

	public void setCodetexte(int codetexte) {
		this.codetexte = codetexte;
	}



	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CahierCoursPK)) {
			return false;
		}
		CahierCoursPK castOther = (CahierCoursPK)other;
		return 
			this.codepartie==castOther.codepartie
			&& this.codetexte==castOther.codetexte;

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + String.valueOf(codepartie).hashCode();
		hash = hash * prime + String.valueOf(codetexte).hashCode();
		
		return hash;
    }
}