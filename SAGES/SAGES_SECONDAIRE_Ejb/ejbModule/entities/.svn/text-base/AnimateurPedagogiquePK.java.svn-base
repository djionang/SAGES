package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class AnimateurPedagogiquePK implements Serializable{
	//default serial version id, required for serializable classes.
		private static final long serialVersionUID = 1L;
		private String codematiere;
		private String codeenseignant;

	    public AnimateurPedagogiquePK () {
	    }

		public String getCodematiere() {
			return this.codematiere;
		}
		public void setCodematiere(String codematiere) {
			this.codematiere = codematiere;
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
			if (!(other instanceof AnimateurPedagogiquePK)) {
				return false;
			}
			AnimateurPedagogiquePK castOther = (AnimateurPedagogiquePK)other;
			return 
				this.codematiere.equals(castOther.codematiere)
				&& this.codeenseignant.equals(castOther.codeenseignant);

	    }
	    
		public int hashCode() {
			final int prime = 31;
			int hash = 17;
			hash = hash * prime + this.codematiere.hashCode();
			hash = hash * prime + this.codeenseignant.hashCode();
			
			return hash;
	    }
}
