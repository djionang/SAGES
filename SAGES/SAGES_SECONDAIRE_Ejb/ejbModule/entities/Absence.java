package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the absence database table.
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name="Absence.findAll", 
				query="select a from Absence as a where a.eleve.annee.anneeacademique = :annee and a.supprime=:supprime"), 
	@NamedQuery(name="Absence.fingByClasse", 
				query="select a from Absence a where a.eleve.annee.anneeacademique = :annee and a.supprime=:supprime and a.eleve.classe.codeclasse=:classe"), 
	@NamedQuery(name="Absence.fingByEleve", 
				query="select a from Absence a where a.eleve.annee.anneeacademique = :annee and a.supprime=:supprime and a.eleve.matricule=:matricule and a.dateabsence between :datedebut and :datefin"), 
	@NamedQuery(name="Absence.fingById", 
				query="select a from Absence a where a.codeabsence = :code and a.supprime=:supprime"), 

})
public class Absence implements Serializable {
	private static final long serialVersionUID = 1L;
	private int codeabsence;
	private Date dateabsence;
	private int duree;
	private int justifie;
	private boolean supprime;
	private Eleve eleve;

    public Absence() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCodeabsence() {
		return this.codeabsence;
	}

	public void setCodeabsence(int codeabsence) {
		this.codeabsence = codeabsence;
	}

	

	/**
	 * @return the justifie
	 */
	@Basic(optional=false)
	public int getJustifie() {
		return justifie;
	}


	/**
	 * @param justifie the justifie to set
	 */
	public void setJustifie(int justifie) {
		this.justifie = justifie;
	}

	@Basic(optional=false)
	public boolean getSupprime() {
		return this.supprime;
	}



	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	//bi-directional many-to-one association to Eleve
    @ManyToOne
	@JoinColumn(name="MATRICULE")
	public Eleve getEleve() {
		return this.eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}


	/**
	 * @return the duree
	 */
	public int getDuree() {
		return duree;
	}


	/**
	 * @param duree the duree to set
	 */
	public void setDuree(int duree) {
		this.duree = duree;
	}


	/**
	 * @return the dateabsence
	 */
	@Basic(optional=false)
	public Date getDateabsence() {
		return dateabsence;
	}


	/**
	 * @param dateabsence the dateabsence to set
	 */
	public void setDateabsence(Date dateabsence) {
		this.dateabsence = dateabsence;
	}
	
}