package ages.beans.eleve;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.UploadedFile;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;
import ages.exception.EleveDupliqueException;


@ManagedBean(name="eleveBean")
@ViewScoped
public class EleveBean extends GenericBean implements Serializable{

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private static final long serialVersionUID = 1L;

	private String matricule;
	
	// code de la classe de lelev
	private String  codeClasse;
	private String libelleClasse;
	
	private int ideleve;
		
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String lieuNaissance;
	private String sexe;
	private String nationalite;
	
	private String login;	
	private String password;
	private String email;
	private String adresse;
	
	private boolean confirme;
	private boolean redoublant;
	private boolean ancien;
	
	
	private String telephone;

	private String ancienEtablissement;
	private String classeAncienEtablissement;
	private String anneeAncienEtablissement;
	
	private String boitePostale;		

	
	private String numeroPayement;
	private Date dateEnregistrement;
	
	private String nomPere;		
	private String professionPere;
	private String telephonePere;
	private String emailPere;
	private String adressePere;
	
	private String nomMere;		
	private String professionMere;
	private String telephoneMere;
	private String emailMere;
	private String adresseMere;
	
	private String nomTuteur;		
	private String professionTuteur;
	private String telephoneTuteur;
	private String emailTuteur;
	private String adresseTuteur;
	private double dernieremoyenne;
	
	private float droitscolaire;
	
	private String nouvelleClasse;
	
	
	// Dans l'affectation multiple de classes aux eleves, permet de savoir si le dossier a 
	// été sélectionné ou non.
	private Boolean selected;
		
	private String photo;
	
	private UploadedFile photoUploaded;
	
	
	public EleveBean(){
		
	}
	

	/**
	 * @return the ideleve
	 */
	public int getIdeleve() {
		return ideleve;
	}


	/**
	 * @param ideleve the ideleve to set
	 */
	public void setIdeleve(int ideleve) {
		this.ideleve = ideleve;
	}


	public String getNomTuteur() {
		return nomTuteur;
	}

	public void setNomTuteur(String nomTuteur) {
		this.nomTuteur = nomTuteur;
	}

	public String getProfessionTuteur() {
		return professionTuteur;
	}

	public void setProfessionTuteur(String professionTuteur) {
		this.professionTuteur = professionTuteur;
	}

	public String getTelephoneTuteur() {
		return telephoneTuteur;
	}

	public void setTelephoneTuteur(String telephoneTuteur) {
		this.telephoneTuteur = telephoneTuteur;
	}

	public String getEmailTuteur() {
		return emailTuteur;
	}

	public void setEmailTuteur(String emailTuteur) {
		this.emailTuteur = emailTuteur;
	}
	
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
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

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date datenaissance) {
		this.dateNaissance = datenaissance;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getNumeroPayement() {
		return numeroPayement;
	}

	public void setNumeroPayement(String numeroPayement) {
		this.numeroPayement = numeroPayement;
	}

	public Date getDateEnregistrement() {
		return dateEnregistrement;
	}

	public void setDateEnregistrement(Date dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}

	public String getAncienEtablissement() {
		return ancienEtablissement;
	}

	public void setAncienEtablissement(String ancienEtablissement) {
		this.ancienEtablissement = ancienEtablissement;
	}

	public String getClasseAncienEtablissement() {
		return classeAncienEtablissement;
	}

	public void setClasseAncienEtablissement(String classeAncienEtablissement) {
		this.classeAncienEtablissement = classeAncienEtablissement;
	}

	public String getAnneeAncienEtablissement() {
		return anneeAncienEtablissement;
	}

	public void setAnneeAncienEtablissement(String anneeAncienEtablissement) {
		this.anneeAncienEtablissement = anneeAncienEtablissement;
	}

	public String getNomPere() {
		return nomPere;
	}

	public void setNomPere(String nomPere) {
		this.nomPere = nomPere;
	}

	public String getProfessionPere() {
		return professionPere;
	}

	public void setProfessionPere(String professionPere) {
		this.professionPere = professionPere;
	}

	public String getTelephonePere() {
		return telephonePere;
	}

	public void setTelephonePere(String telephonePere) {
		this.telephonePere = telephonePere;
	}

	public String getEmailPere() {
		return emailPere;
	}

	public void setEmailPere(String emailPere) {
		this.emailPere = emailPere;
	}

	public String getNomMere() {
		return nomMere;
	}

	public void setNomMere(String nomMere) {
		this.nomMere = nomMere;
	}

	public String getProfessionMere() {
		return professionMere;
	}

	public void setProfessionMere(String professionMere) {
		this.professionMere = professionMere;
	}

	public String getTelephoneMere() {
		return telephoneMere;
	}

	public void setTelephoneMere(String telephoneMere) {
		this.telephoneMere = telephoneMere;
	}

	public String getEmailMere() {
		return emailMere;
	}

	public void setEmailMere(String emailMere) {
		this.emailMere = emailMere;
	}

	public String getBoitePostale() {
		return boitePostale;
	}

	public void setBoitePostale(String boitePostale) {
		this.boitePostale = boitePostale;
	}
	
	public String getCodeClasse() {
		return codeClasse;
	}

	public void setCodeClasse(String codeClasse) {
		this.codeClasse = codeClasse;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}


	public String getMatricule() {
		return matricule;
	}


	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}


	public String getLibelleClasse() {
		return libelleClasse;
	}


	public void setLibelleClasse(String libelleClasse) {
		this.libelleClasse = libelleClasse;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public boolean isConfirme() {
		return confirme;
	}


	public void setConfirme(boolean confirme) {
		this.confirme = confirme;
	}


	public boolean isRedoublant() {
		return redoublant;
	}


	public void setRedoublant(boolean redoublant) {
		this.redoublant = redoublant;
	}


	public boolean isAncien() {
		return ancien;
	}


	public void setAncien(boolean ancien) {
		this.ancien = ancien;
	}


	public String getAdressePere() {
		return adressePere;
	}


	public void setAdressePere(String adressePere) {
		this.adressePere = adressePere;
	}


	public String getAdresseMere() {
		return adresseMere;
	}


	public void setAdresseMere(String adresseMere) {
		this.adresseMere = adresseMere;
	}


	public String getAdresseTuteur() {
		return adresseTuteur;
	}


	public void setAdresseTuteur(String adresseTuteur) {
		this.adresseTuteur = adresseTuteur;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public void setService(Service service) {
		this.service = service;
	}


	public UploadedFile getPhotoUploaded() {
		return photoUploaded;
	}


	public void setPhotoUploaded(UploadedFile photoUploaded) {
		this.photoUploaded = photoUploaded;
	}

	public String getNouvelleClasse() {
		return nouvelleClasse;
	}


	public void setNouvelleClasse(String nouvelleClasse) {
		this.nouvelleClasse = nouvelleClasse;
	}


	public double getDernieremoyenne() {
		return dernieremoyenne;
	}


	public void setDernieremoyenne(double dernieremoyenne) {
		this.dernieremoyenne = dernieremoyenne;
	}


	public float getDroitscolaire() {
		return droitscolaire;
	}


	public void setDroitscolaire(float droitscolaire) {
		this.droitscolaire = droitscolaire;
	}


	public String saveeleve(){
		try {
			
			matricule=this.service.saveEleve(matricule,nom,prenom,dateNaissance,lieuNaissance,sexe,nationalite,photo,codeClasse,redoublant,adresse,email,telephone,boitePostale,nomPere,nomMere,nomTuteur,telephonePere,telephoneMere,telephoneTuteur,professionPere,professionMere,professionTuteur,emailPere,emailMere,emailTuteur,ancien,ancienEtablissement,anneeAncienEtablissement,classeAncienEtablissement);
		
		}
		catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(EleveDupliqueException.class))
					Repertoire.addMessageerreur("Eleve "+nom+" "+prenom+" déja enregistré");
				else
					if(e.getCause().getClass().equals(DuplicateKeyException.class))
						Repertoire.addMessageerreur("Matricule "+matricule+" déja enregistré");
					else
					Repertoire.addMessageerreurInnatendue(e);			
			}
			else
				Repertoire.addMessageerreurInnatendue(e);
			return null;
		}
		
		if(photoUploaded!=null && photoUploaded.getSize()!=0){
				Repertoire.savephotoEleve(matricule,this.service.getInfosSession().getCodeetablissement(), photoUploaded.getContents());
				
		}
			
		Repertoire.addMessageinfo(OperationResults.EnregistrementOK);
		return "droitsscolairesN?faces-redirect=true&includeViewParams=true&matricule="+this.matricule;
				/*OperationResults.navWithParam("visualisation", "matricule", this.matricule);*/
		
	}
	
	public void initEleve(){
		if(this.matricule!=null && !this.matricule.isEmpty()){
			this.service.initEleve(this);
		}
	}
	
	public String updateeleve(){
		try {
			this.service.modifierEleve(ideleve,matricule,nom,prenom,dateNaissance,lieuNaissance,sexe,nationalite,photo,codeClasse,redoublant,adresse,email,telephone,boitePostale,nomPere,nomMere,nomTuteur,telephonePere,telephoneMere,telephoneTuteur,professionPere,professionMere,professionTuteur,emailPere,emailMere,emailTuteur,ancien,ancienEtablissement,anneeAncienEtablissement,classeAncienEtablissement);
		} catch (Exception e) {
			if(e.getCause().getClass().equals(ElementNOtFoundException.class))
				Repertoire.addMessageerreur(OperationResults.UpdateNO+" Eleve non trouvé "+ideleve);
			else
				if(e.getCause().getClass().equals(DuplicateKeyException.class))
					Repertoire.addMessageerreur(" Erreur, Matricule déja utilisé !");
				else
					Repertoire.addMessageerreur("Erreur Innatendue, Veuillez contacter l'administrateur");
			
			return OperationResults.FAILURE;
		}

		if(photoUploaded!=null && photoUploaded.getSize()!=0){
			Repertoire.savephotoEleve(matricule,this.service.getInfosSession().getCodeetablissement(), photoUploaded.getContents());
			
		}
		
		Repertoire.addMessageinfo(OperationResults.UpdateOK);
		return OperationResults.navWithParam("visualisation", "matricule", this.matricule);
	
	}
	
	
	public String supprimerEleve(){
		if (this.matricule!=null){
			try {
				this.service.supprimerEleve(matricule);
			} catch (ElementNOtFoundException e) {
				Repertoire.addMessageerreur(OperationResults.SuppressionNO+" Enseignant non trouvé");
				return OperationResults.FAILURE;
			}
		}
		Repertoire.addMessageinfo(OperationResults.SuppressionOK);
		return OperationResults.navWithParam("listing", null, null);
		
	}
	
	
	public String migrateClass(){
		try {
			this.service.migrateClass(matricule,nouvelleClasse);
		} catch (Exception e) {
			if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Etudiant ou Filiere inconnue");
				return null;
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				Repertoire.logError("Erreur lors de la migration de filiere d'un etudiant", getClass(), e);
				return null;
			}
		}
		return OperationResults.navWithParam("visualisation", "matricule", this.matricule); 
	}
	

}
