package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the retard database table.
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name="Retard.findAll", 
				query="select r from Retard as r where r.annee.anneeacademique = :annee and r.supprime=:supprime"), 
	@NamedQuery(name="Retard.fingByClasse", 
				query="select r from Retard r where r.annee.anneeacademique = :annee and r.supprime=:supprime and r.eleve.classe.codeclasse=:classe"), 
	@NamedQuery(name="Retard.fingByEleve", 
				query="select r from Retard r where r.annee.anneeacademique = :annee and r.supprime=:supprime and r.eleve.matricule=:matricule and r.dateretard between :datedebut and :datefin"), 
	@NamedQuery(name="Retard.fingById", 
				query="select r from Retard r where r.coderetard = :code and r.supprime=:supprime"), 

})
public class Retard implements Serializable {
	private static final long serialVersionUID = 1L;
	private int coderetard;
	private Date dateretard;
	private int dureeretard;
	private int justifie;
	private boolean supprime;
	private Annee annee;
	private Eleve eleve;

    public Retard() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCoderetard() {
		return this.coderetard;
	}

	public void setCoderetard(int coderetard) {
		this.coderetard = coderetard;
	}

    @Temporal( TemporalType.DATE)
	public Date getDateretard() {
		return this.dateretard;
	}

	public void setDateretard(Date dateretard) {
		this.dateretard = dateretard;
	}


	public int getDureeretard() {
		return this.dureeretard;
	}

	public void setDureeretard(int duree) {
		this.dureeretard = duree;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	//bi-directional many-to-one association to Annee
    @ManyToOne
	@JoinColumn(name="ANNEEACADEMIQUE")
	public Annee getAnnee() {
		return this.annee;
	}

	public void setAnnee(Annee annee) {
		this.annee = annee;
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
	 * @return the justifie
	 */
	public int getJustifie() {
		return justifie;
	}


	/**
	 * @param justifie the justifie to set
	 */
	public void setJustifie(int justifie) {
		this.justifie = justifie;
	}
	
}