package ages.beans.etablissement.personnel;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.exception.ElementNOtFoundException;

@ManagedBean(name="personnelBean")
@ViewScoped
public class PersonnelBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
		
	private String codepersonnel;
	private String nom;
	private String prenom;
	private String adresse;
	private String email;
	private String telephone;;
	private String civilite;
	private Date datearrivee;
	private Date datedepart;	
	private String fonction;	
	private String sexe;
	

	
	public PersonnelBean() {
	}
	

	public void setService(Service service) {
		this.service = service;
	}
	

	public String getCodepersonnel() {
		return codepersonnel;
	}

	public void setCodepersonnel(String codepersonnel) {
		this.codepersonnel = codepersonnel;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public Date getDatearrivee() {
		return datearrivee;
	}

	public void setDatearrivee(Date datearrivee) {
		this.datearrivee = datearrivee;
	}

	public Date getDatedepart() {
		return datedepart;
	}

	public void setDatedepart(Date datedepart) {
		this.datedepart = datedepart;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * Enregistre le personnel dans la base
	 * @return la chaine de navigation pur les cas erreur et succes
	 * @throws ElementNOtFoundException 
	 */
	public String savepersonnel(){
		
		try {
			this.service.savePersonnel(nom,prenom, adresse,email,telephone,civilite,datearrivee,fonction,sexe);
		} catch (Exception e) {
			if(e.getCause()!=null && e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Etablissement cible non trouvé", e);
				return null;
			}
		}
		
		Repertoire.addMessageinfo(OperationResults.EnregistrementOK);
		return OperationResults.NavListingBasic;
	}
	
	public String modifierpersonnel(){
		try {
			this.service.modifierPersonnel(codepersonnel, nom,prenom, adresse,email,telephone,civilite,datearrivee,fonction,sexe);
		} catch (ElementNOtFoundException e) {
			Repertoire.addMessageerreur(OperationResults.UpdateNO+" Classe non trouvée");
			return OperationResults.FAILURE;
		}
		Repertoire.addMessageinfo(OperationResults.UpdateOK);
		return OperationResults.navWithParam("visualisation", "codepersonnel", this.codepersonnel);
	}
	
	public String supprimerpersonnel(){
		if (this.codepersonnel!=null){
			try {
				this.service.supprimerPersonnel(this.codepersonnel);
			} catch (ElementNOtFoundException e) {
				Repertoire.addMessageerreur(OperationResults.SuppressionNO+" Personnel non trouvé");
				return OperationResults.FAILURE;
			}
		}
		Repertoire.addMessageinfo(OperationResults.SuppressionOK);
		return OperationResults.navWithParam("listing", null, null);
	}
	
	public void initialize(){
		
		if(this.codepersonnel!=null)
			this.service.initialisePersonnelBean(this);
	}
}
