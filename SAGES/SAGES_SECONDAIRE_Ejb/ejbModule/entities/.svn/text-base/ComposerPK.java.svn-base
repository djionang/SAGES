package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the composer database table.
 * 
 */
@Embeddable
public class ComposerPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int ideleve;
	private int codeevaluation;

    public ComposerPK() {
    }

	

	
	/**
	 * @return the ideleve
	 */
	public int getIdeleve() {
		return ideleve;
	}




	/**
	 * @param ideleve the ideleve to set
	 */
	public void setIdeleve(int ideleve) {
		this.ideleve = ideleve;
	}




	/**
	 * @return the codeevaluation
	 */
	public int getCodeevaluation() {
		return codeevaluation;
	}

	/**
	 * @param codeevaluation the codeevaluation to set
	 */
	public void setCodeevaluation(int codeevaluation) {
		this.codeevaluation = codeevaluation;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codeevaluation;
		result = prime * result + ideleve;
		return result;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComposerPK other = (ComposerPK) obj;
		if (codeevaluation != other.codeevaluation)
			return false;
		if (ideleve != other.ideleve)
			return false;
		return true;
	}

	
}