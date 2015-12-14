package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="Convocation.findforEleve", 
			query="select c from Convocation as c where c.annee.anneeacademique = :annee and c.supprime=:supprime and c.eleve.matricule=:matricule"),
	@NamedQuery(name="Convocation.findAllThisYear",
			query="select c from Convocation as c where c.annee.anneeacademique = :annee and c.supprime=:supprime"),
	@NamedQuery(name="Convocation.findById",
			query="select c from Convocation as c where c.annee.anneeacademique = :annee and c.supprime=:supprime and c.idconvocation=:idconvocation"),
			@NamedQuery(name="Convocation.findAllforPeriod",
			query="select c from Convocation as c where (c.datedelivrance BETWEEN :datedebut and :datefin) and c.supprime=:supprime"),
})
public class Convocation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idconvocation;
	private String libelle;
	private String description;
	private Date datedelivrance;
	private Date daterendezvous;
	private Eleve eleve;
	private Annee annee;
	private boolean supprime;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdconvocation() {
		return idconvocation;
	}
	
	public void setIdconvocation(int idconvocation) {
		this.idconvocation = idconvocation;
	}
	
	//bi-directional many-to-one association to Annee
    @ManyToOne
	@JoinColumn(name="MATRICULEELEVE")
	public Eleve getEleve() {
		return eleve;
	}
	
	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}
		
	
	//bi-directional many-to-one association to Annee
    @ManyToOne
	@JoinColumn(name="ANNEEACADEMIQUE")
	public Annee getAnnee() {
		return annee;
	}
	
	public void setAnnee(Annee annee) {
		this.annee = annee;
	}

	public boolean isSupprime() {
		return supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDatedelivrance() {
		return datedelivrance;
	}

	public void setDatedelivrance(Date datedelivrance) {
		this.datedelivrance = datedelivrance;
	}

	public Date getDaterendezvous() {
		return daterendezvous;
	}

	public void setDaterendezvous(Date daterendezvous) {
		this.daterendezvous = daterendezvous;
	}
}
