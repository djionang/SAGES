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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

@NamedQueries({ 
	@NamedQuery(name="Depense.findAll", 
				query="select d from Depense as d"),
	@NamedQuery(name="Depense.findS", 
				query="select d from Depense as d where d.etab.codeetablissement=:codeetab  and d.annee.anneeacademique=:annee"),
	@NamedQuery(name="Depense.find", 
				query="select d from Depense as d where d.etab.codeetablissement=:codeetab  and d.annee.anneeacademique=:annee and d.iddepense=:iddepense"),
})
public class Depense implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int iddepense;
	private String codedepense;
	private String description;
	private Date dateenreg;
	private float montant;
	private boolean supprime;
	private String typedepense;
	private Prevision prevision;
	private Annee annee;
	private Etablissement etab;
	private Personnel personnel;
	
	
	@Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
	public int getIddepense() {
		return iddepense;
	}
	public void setIddepense(int iddepense) {
		this.iddepense = iddepense;
	}
	public String getCodedepense() {
		return codedepense;
	}
	public void setCodedepense(String codedepense) {
		this.codedepense = codedepense;
	}
	public String getTypedepense() {
		return typedepense;
	}
	public void setTypedepense(String typedepense) {
		this.typedepense = typedepense;
	}
	
	@Temporal( TemporalType.DATE)
	public Date getDateenreg() {
		return dateenreg;
	}
	
	public void setDateenreg(Date dateenreg) {
		this.dateenreg = dateenreg;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	public boolean isSupprime() {
		return supprime;
	}
	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}
	
	@ManyToOne
	@JoinColumn(name="ANNEEACADEMIQUE")
	public Annee getAnnee() {
		return annee;
	}
	
	public void setAnnee(Annee annee) {
		this.annee = annee;
	}
	
	@ManyToOne
	public Etablissement getEtab() {
		return etab;
	}
	public void setEtab(Etablissement etab) {
		this.etab = etab;
	}
	@ManyToOne
	public Personnel getPersonnel() {
		return personnel;
	}
	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}
	
	@ManyToOne
	public Prevision getPrevision() {
		return prevision;
	}
	public void setPrevision(Prevision prevision) {
		this.prevision = prevision;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
