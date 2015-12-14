package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="Versement.findforEleve", 
			query="select v from Versement as v where v.annee.anneeacademique =:annee and v.supprime=:supprime and v.eleve.matricule=:matricule"),
	@NamedQuery(name="Versement.findAllforEleve", 
			query="select sum(v.montant) from Versement as v where v.annee.anneeacademique =:annee and v.supprime=:supprime and v.eleve.ideleve=:matricule"),
	@NamedQuery(name="Versement.findAllThisYear",
			query="select v from Versement as v where v.annee.anneeacademique =:annee and v.supprime=:supprime"),
	@NamedQuery(name="Versement.findById",
			query="select v from Versement as v where v.annee.anneeacademique =:annee and v.supprime=:supprime and v.idversement=:idversement"),

})
public class Versement implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String idversement;
	private String typeversement;
	private Date dateVersement;
	private Eleve eleve;
	private String login;
	private Float montant;
	private Annee annee;
	private boolean supprime;
	
	@Id 
	public String getIdversement() {
		return idversement;
	}
	
	public void setIdversement(String idversement) {
		this.idversement = idversement;
	}
		
	public Date getDateVersement() {
		return dateVersement;
	}
	
	public void setDateVersement(Date dateVersement) {
		this.dateVersement = dateVersement;
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

	
	public Float getMontant() {
		return montant;
	}
	
	public void setMontant(Float montant) {
		this.montant = montant;
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

	public String getTypeversement() {
		return typeversement;
	}

	public void setTypeversement(String typeversement) {
		this.typeversement = typeversement;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
