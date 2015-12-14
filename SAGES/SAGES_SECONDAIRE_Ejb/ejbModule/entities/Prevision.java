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
	@NamedQuery(name="Prevision.findAll", 
				query="select p from Prevision as p"),
	@NamedQuery(name="Prevision.listTypes", 
				query="select p.typeprevision from Prevision as p"),
	@NamedQuery(name="Prevision.find", 
				query="select p from Prevision as p where p.etab.codeetablissement=:codeetab  and p.annee.anneeacademique=:annee and p.idprevision=:idprevision"),
	@NamedQuery(name="Prevision.findS", 
				query="select p from Prevision as p where p.etab.codeetablissement=:codeetab  and p.annee.anneeacademique=:annee"),
})
public class Prevision implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idprevision;
	private String codeprevision;
	private String tranfert;
	private String description;
	private Date dateenreg;
	private float montant;
	private float reste;
	private boolean supprime;
	private String typeprevision;
	private Annee annee;
	private Etablissement etab;
	private Personnel personnel;
	
	@Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
	public int getIdprevision() {
		return idprevision;
	}
	public void setIdprevision(int idprevision) {
		this.idprevision = idprevision;
	}
	public String getCodeprevision() {
		return codeprevision;
	}
	public void setCodeprevision(String codeprevision) {
		this.codeprevision = codeprevision;
	}
	public String getTranfert() {
		return tranfert;
	}
	public void setTranfert(String tranfert) {
		this.tranfert = tranfert;
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
	public String getTypeprevision() {
		return typeprevision;
	}
	public void setTypeprevision(String typeprevision) {
		this.typeprevision = typeprevision;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getReste() {
		return reste;
	}
	public void setReste(float reste) {
		this.reste = reste;
	}
	
	

}
