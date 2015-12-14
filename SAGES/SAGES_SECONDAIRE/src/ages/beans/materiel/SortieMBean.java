package ages.beans.materiel;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.Service;

import ages.beans.GenericBean;

@ManagedBean(name="sortieBean")
@ViewScoped
public class SortieMBean extends GenericBean implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private int idsortie;
	private String raison;
	private Date dateretrait;
	private Date daterenreg;
	private int quantiteSortie;
	private String codemateriel;
	private String designation;
	private String utlisateur;
	private String typemateriel;
	private int quantite;
	
	
	public void setService(Service service) {
		this.service = service;
	}
	
	public String getCodemateriel() {
		return codemateriel;
	}
	public void setCodemateriel(String codemateriel) {
		this.codemateriel = codemateriel;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getTypemateriel() {
		return typemateriel;
	}
	public void setTypemateriel(String typemateriel) {
		this.typemateriel = typemateriel;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public int getIdsortie() {
		return idsortie;
	}
	public void setIdsortie(int idsortie) {
		this.idsortie = idsortie;
	}
	public String getRaison() {
		return raison;
	}
	public void setRaison(String raison) {
		this.raison = raison;
	}
	public Date getDateretrait() {
		return dateretrait;
	}
	public void setDateretrait(Date dateretrait) {
		this.dateretrait = dateretrait;
	}
	public int getQuantiteSortie() {
		return quantiteSortie;
	}
	public void setQuantiteSortie(int quantiteSortie) {
		this.quantiteSortie = quantiteSortie;
	}
	
	public String getUtlisateur() {
		return utlisateur;
	}
	public void setUtlisateur(String utlisateur) {
		this.utlisateur = utlisateur;
	}
	public Date getDaterenreg() {
		return daterenreg;
	}

	public void setDaterenreg(Date daterenreg) {
		this.daterenreg = daterenreg;
	}

	public void initialize(){
		
		if(this.idsortie!=0)
			this.service.initialiseSortieBean(this);
	}

}
