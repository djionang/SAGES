package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the enseigner database table.
 * 
 */
@Embeddable
public class EnseignerPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String codeniveau;
	private String codeenseignant;

    public EnseignerPK() {
    }

	public String getCodeniveau() {
		return this.codeniveau;
	}
	public void setCodeniveau(String codeniveau) {
		this.codeniveau = codeniveau;
	}

	public String getCodeenseignant() {
		return this.codeenseignant;
	}
	public void setCodeenseignant(String codeenseignant) {
		this.codeenseignant = codeenseignant;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EnseignerPK)) {
			return false;
		}
		EnseignerPK castOther = (EnseignerPK)other;
		return 
			this.codeniveau.equals(castOther.codeniveau)
			&& this.codeenseignant.equals(castOther.codeenseignant);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codeniveau.hashCode();
		hash = hash * prime + this.codeenseignant.hashCode();
		
		return hash;
    }
}