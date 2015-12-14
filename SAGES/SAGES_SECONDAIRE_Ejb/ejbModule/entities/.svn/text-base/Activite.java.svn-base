package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the activite database table.
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name="Activite.findAll", 
				query="select a from Activite as a where (a.datedebut BETWEEN :datedebut and :datefin) and a.supprime=:supprime"),
	@NamedQuery(name="Activite.findByCode", 
				query="select a from Activite a where a.codeactivite=:codeactivite and (a.datedebut BETWEEN :datedebut and :datefin) and a.supprime=:supprime"), 
	@NamedQuery(name="Activite.findTypes", 
				query="select DISTINCT a.type from Activite a where (a.datedebut BETWEEN :datedebut and :datefin) and a.supprime=:supprime"), 
	
	})
public class Activite implements Serializable {
	private static final long serialVersionUID = 1L;
	private int codeactivite;
	private String description;
	private String libelle;
	private boolean supprime;
	private String type;
	private List<Programmation> programmations;	
	private Date datedebut;
	private Date datefin;
	private String repetition;

	
    public Activite() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCodeactivite() {
		return this.codeactivite;
	}

	public void setCodeactivite(int codeactivite) {
		this.codeactivite = codeactivite;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	//bi-directional many-to-one association to Derouleractivitesalle
		@OneToMany(mappedBy="activite")
	public List<Programmation> getProgrammations() {
		return programmations;
	}


	/**
	 * @param programmations the programmations to set
	 */
	public void setProgrammations(List<Programmation> programmations) {
		this.programmations = programmations;
	}


	public Date getDatedebut() {
		return datedebut;
	}


	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}


	public Date getDatefin() {
		return datefin;
	}


	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}


	public String getRepetition() {
		return repetition;
	}


	public void setRepetition(String repetition) {
		this.repetition = repetition;
	}
	
}