package ages.beans.budget;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.Service;

import ages.beans.GenericBean;


@ManagedBean(name="transfertBean")
@ViewScoped
public class TransfertBean extends GenericBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codetransfert;
	private Date datetransfert;
	private float montant;
	private boolean supprime;
	private String typedepense;
	
	@ManagedProperty(value="#{service}")
	protected Service service;
			
	public void setService(Service service) {
		this.service = service;
	}
	
	public String getCodetransfert() {
		return codetransfert;
	}
	public void setCodetransfert(String codetransfert) {
		this.codetransfert = codetransfert;
	}
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
	
	

}
