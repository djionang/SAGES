package ages.beans.enseignant;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.UploadedFile;

import utils.OperationResults;
import utils.Service;
import utils.Repertoire;
import ages.beans.GenericBean;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;

@ManagedBean(name="enseignantBean")
@RequestScoped
public class EnseignantBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
		
	private String codeenseignant;
	private String nomens;
	private String prenomens;
	private String photo;
	private UploadedFile photosent;
	private String codepersonnel;
	
	private String sexe;
	private String adresse;
	private String email;
	private String telephone;
	
	private String civilite;
	private Date datearrivee;
	
	private String loginens;
	private String passens;
	
	private String competences;
	
	private boolean vacataire;
	private BigDecimal salairehoraire;
	private int travailmensuel;
	
	public EnseignantBean() {
	}
	
	public void setService(Service service) {
		this.service = service;
	}
	

	public String getCodeenseignant() {
		return codeenseignant;
	}

	public void setCodeenseignant(String codeenseignant) {
		this.codeenseignant = codeenseignant;
	}

	public String getNomens() {
		return nomens;
	}

	public void setNomens(String nomens) {
		this.nomens = nomens;
	}

	public String getPrenomens() {
		return prenomens;
	}

	public void setPrenomens(String prenomens) {
		this.prenomens = prenomens;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCodepersonnel() {
		return codepersonnel;
	}

	public void setCodepersonnel(String codepersonnel) {
		this.codepersonnel = codepersonnel;
	}
	

	public String getLoginens() {
		return loginens;
	}

	public void setLoginens(String loginens) {
		this.loginens = loginens;
	}

	public String getPassens() {
		return passens;
	}

	public void setPassens(String passens) {
		this.passens = passens;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public Date getDatearrivee() {
		if(datearrivee==null)
			datearrivee=new Date();
		return datearrivee;
	}

	public void setDatearrivee(Date datearrivee) {
		this.datearrivee = datearrivee;
	}

	public String getCompetences() {
		return competences;
	}

	public void setCompetences(String competence) {
		this.competences = competence;
	}

	public boolean isVacataire() {
		return vacataire;
	}

	public void setVacataire(boolean vacataire) {
		this.vacataire = vacataire;
	}

	public BigDecimal getSalairehoraire() {
		return salairehoraire;
	}

	public void setSalairehoraire(BigDecimal salairehoraire) {
		this.salairehoraire = salairehoraire;
	}

	public int getTravailmensuel() {
		return travailmensuel;
	}

	public void setTravailmensuel(int travailmensuel) {
		this.travailmensuel = travailmensuel;
	}

	public UploadedFile getPhotosent() {
		return photosent;
	}

	public void setPhotosent(UploadedFile photosent) {
		this.photosent = photosent;
	}

	/**
	 * Enregistre le enseignant dans la base
	 * @return la chaine de navigation pur les cas erreur et succes
	 * @throws ElementNOtFoundException 
	 */
	public String saveenseignant(){
		try {
			codeenseignant=this.service.saveEnseignant(nomens,	prenomens,photo,sexe,adresse,email,telephone,civilite,datearrivee,loginens,passens,competences,vacataire,salairehoraire, travailmensuel);
		}
		catch (Exception e) {
			if(e.getCause().getClass().equals(DuplicateKeyException.class))
				Repertoire.addMessageerreur("Enseignant de code "+codeenseignant+" déja enregistré");
			else
				Repertoire.addMessageerreur("Erreur Innatendue, Veuillez contacter l'administrateur");
			
			return OperationResults.FAILURE;
		}
		
		if(photosent!=null)
			Repertoire.savephotoEnseignant(codeenseignant,this.service.getInfosSession().getCodeetablissement(), photosent.getContents());
		Repertoire.addMessageinfo(OperationResults.EnregistrementOK);
		return OperationResults.NavListingBasic;
	}
	
	
	public String modifierenseignant(){
		try {
			this.service.modifierEnseignant(codeenseignant, nomens,prenomens,photo,sexe,adresse,email,telephone,civilite,datearrivee,competences,salairehoraire, travailmensuel);
		} catch (Exception e) {
			if(e.getCause().getCause().equals(ElementNOtFoundException.class))
				Repertoire.addMessageerreur(OperationResults.UpdateNO+" Enseignant non trouvé");
			else
				Repertoire.addMessageerreur("Erreur Innatendue, Veuillez contacter l'administrateur");
			
			return OperationResults.FAILURE;
		}
		if(photosent!=null)
			Repertoire.savephotoEnseignant(codeenseignant,this.service.getInfosSession().getCodeetablissement(), photosent.getContents());
		
		Repertoire.addMessageinfo(OperationResults.UpdateOK);
		return OperationResults.navWithParam("visualisation", "codeenseignant", this.codeenseignant);
	}
	
	public String modifierstatut(){
		try {
			this.service.modifierStatut(codeenseignant, vacataire);
		} catch (ElementNOtFoundException e) {
			Repertoire.addMessageerreur(OperationResults.UpdateNO+" Enseignant non trouvé");
			return OperationResults.FAILURE;
		}
		Repertoire.addMessageinfo(OperationResults.UpdateOK);
		return OperationResults.navWithParam("visualisation", "codeenseignant", this.codeenseignant);
	}
	
	public String supprimerenseignant(){
		if (this.codeenseignant!=null){
			try {
				this.service.supprimerEnseignant(this.codeenseignant);
			} catch (ElementNOtFoundException e) {
				Repertoire.addMessageerreur(OperationResults.SuppressionNO+" Enseignant non trouvé");
				return OperationResults.FAILURE;
			}
		}
		Repertoire.addMessageinfo(OperationResults.SuppressionOK);
		return OperationResults.navWithParam("listing", null, null);
	}
	
	public void initialize(){
		
		if(this.codeenseignant!=null)
			this.service.initEnseignantBean(this);
	}
}
