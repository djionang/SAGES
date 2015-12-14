package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the planification_annuelle database table.
 * 
 */
@Entity
@Table(name="planification_annuelle")
public class PlanificationAnnuelle implements Serializable {
	private static final long serialVersionUID = 1L;
	private int codeplanification;
	private Date datedebutplanification;
	private Date datefinplanification;
	private boolean supprime;
	private List<PartieCour> partieCours;

    public PlanificationAnnuelle() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCodeplanification() {
		return this.codeplanification;
	}

	public void setCodeplanification(int codeplanification) {
		this.codeplanification = codeplanification;
	}


	/**
	 * @return the datedebutplanification
	 */
	public Date getDatedebutplanification() {
		return datedebutplanification;
	}


	/**
	 * @param datedebutplanification the datedebutplanification to set
	 */
	public void setDatedebutplanification(Date datedebutplanification) {
		this.datedebutplanification = datedebutplanification;
	}


	/**
	 * @return the datefinplanification
	 */
	public Date getDatefinplanification() {
		return datefinplanification;
	}


	/**
	 * @param datefinplanification the datefinplanification to set
	 */
	public void setDatefinplanification(Date datefinplanification) {
		this.datefinplanification = datefinplanification;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	//bi-directional many-to-one association to PartieCour
	@OneToMany(mappedBy="planificationAnnuelle")
	public List<PartieCour> getPartieCours() {
		return this.partieCours;
	}

	public void setPartieCours(List<PartieCour> partieCours) {
		this.partieCours = partieCours;
	}
	
}