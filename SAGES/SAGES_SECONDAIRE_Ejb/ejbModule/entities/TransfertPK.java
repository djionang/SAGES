package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TransfertPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int codeprevision1;
	private int codeprevision2;
	private String anneeacademique;

    public void TranfertPK() {
    }
	
	public int getCodeprevision1() {
		return codeprevision1;
	}

	public void setCodeprevision1(int codeprevision1) {
		this.codeprevision1 = codeprevision1;
	}

	public int getCodeprevision2() {
		return codeprevision2;
	}

	public void setCodeprevision2(int codeprevision2) {
		this.codeprevision2 = codeprevision2;
	}

	public String getAnneeacademique() {
		return anneeacademique;
	}

	public void setAnneeacademique(String anneeacademique) {
		this.anneeacademique = anneeacademique;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((anneeacademique == null) ? 0 : anneeacademique.hashCode());
		result = prime * result + codeprevision1;
		result = prime * result + codeprevision2;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransfertPK other = (TransfertPK) obj;
		if (anneeacademique == null) {
			if (other.anneeacademique != null)
				return false;
		} else if (!anneeacademique.equals(other.anneeacademique))
			return false;
		if (codeprevision1 != other.codeprevision1)
			return false;
		if (codeprevision2 != other.codeprevision2)
			return false;
		return true;
	}

	
	

	

}
