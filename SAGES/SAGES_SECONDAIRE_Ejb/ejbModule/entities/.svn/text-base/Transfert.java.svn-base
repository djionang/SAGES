package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

public class Transfert implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TransfertPK idtranfert;
	
	private String codetransfert;
	private Date datetransfert;
	private float montant;
	private boolean supprime;
	private String typedepense;
	private Annee annee;
	private Etablissement etab;
	private Personnel personnel;
	
	@EmbeddedId
	public TransfertPK getIdtranfert() {
		return idtranfert;
	}
	public void setIdtranfert(TransfertPK idtranfert) {
		this.idtranfert = idtranfert;
	}
	public String getCodetransfert() {
		return codetransfert;
	}
	public void setCodetransfert(String codetransfert) {
		this.codetransfert = codetransfert;
	}
	
	@Temporal( TemporalType.DATE)
	public Date getDatetransfert() {
		return datetransfert;
	}
	public void setDatetransfert(Date datetransfert) {
		this.datetransfert = datetransfert;
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
	public String getTypedepense() {
		return typedepense;
	}
	public void setTypedepense(String typedepense) {
		this.typedepense = typedepense;
	}
	
	@MapsId("anneeacademique")
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
	

}
