package ages.beans.inscription;

/**
 * DossierBean.java
 * Bean used to manage application and save application forms data
 * by Bri@Crecpro
 */


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.beans.etablissement.OptionBean;
import ages.exception.ElementNOtFoundException;

@ManagedBean(name="dossierBean")
@ViewScoped
public class DossierBean extends GenericBean implements Serializable{
	
		private static final long serialVersionUID = 1L;

		// declaration du service
		@ManagedProperty(value="#{service}")
		protected Service service;
	
		private String codedossier;
		private String matricule;
		private String nom;
		private String prenom;
		private Date dateNaissance;
		private String lieuNaissance;
		private String sexe;
		private String nationalite;
		private String password;
		private String email;
		private String adresse;
		
		private String loginPrincipal;
		
		private String etat="en cours";
		private String niveauDemande;
		private String optionDemande;
		
		private String anneeAcademique;
		
		private String boitePostale;		

		private String telephone;
		private String numeroPayement;
		private Date datePreinscription;
		private String ancienEtablissement;
		private String classeAncienEtablissement;
		private String anneeAncienEtablissement;
		
		private String nomPere;		
		private String professionPere;
		private String telephonePere;
		private String emailPere;
		
		private String nomMere;		
		private String professionMere;
		private String telephoneMere;
		private String emailMere;
		
		private String nomTuteur;		
		private String professionTuteur;
		private String telephoneTuteur;
		private String emailTuteur;
		private double dernieremoyenne;
		private float  sommeverse;
		private List<OptionBean> options;
		// Dans l'affectation multiple de classes aux eleves, permet de savoir si le dossier a 
		// été sélectionné ou non.
		private Boolean selected;
		
		// code de la classe retenue, dans laquelle on va envoyer le gamin la!!!
		private String  codeClasse;
		
				
		
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
		
		
		public void setService(Service service) {
			this.service = service;
		}
		

		public String getNumeroPayement() {
			return numeroPayement;
		}

		public void setNumeroPayement(String numeroPayement) {
			this.numeroPayement = numeroPayement;
		}

		public Date getDatePreinscription() {
			if(datePreinscription==null)
				datePreinscription=new Date();
			return datePreinscription;
		}

		public void setDatePreinscription(Date datepreinscription) {
			this.datePreinscription = datepreinscription;
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

		public String getEtat() {
			return etat;
		}

		public void setEtat(String etat) {
			this.etat = etat;
		}

		public String getNiveauDemande() {
			return niveauDemande;
		}

		public void setNiveauDemande(String niveauDemande) {
			this.niveauDemande = niveauDemande;
		}

		public String getOptionDemande() {
			return optionDemande;
		}

		public void setOptionDemande(String optiondemande) {
			optionDemande = optiondemande;
		}

		
		public String getAnneeAcademique() {
			return anneeAcademique;
		}

		public void setAnneeAcademique(String anneeAcademique) {
			this.anneeAcademique = anneeAcademique;
		}

		public String getCodedossier() {
			return codedossier;
		}

		public void setCodedossier(String codedossier) {
			this.codedossier = codedossier;
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

		public String getLoginPrincipal() {
			return loginPrincipal;
		}

		public void setLoginPrincipal(String loginPrincipal) {
			this.loginPrincipal = loginPrincipal;
		}

		public double getDernieremoyenne() {
			return dernieremoyenne;
		}

		public void setDernieremoyenne(double dernieremoyenne) {
			this.dernieremoyenne = dernieremoyenne;
		}

		

		public List<OptionBean> getOptions() {
			return options;
		}

		public void setOptions(List<OptionBean> options) {
			this.options = options;
		}

		public String getMatricule() {
			return matricule;
		}

		public void setMatricule(String matricule) {
			this.matricule = matricule;
		}

		public float getSommeverse() {
			return sommeverse;
		}

		public void setSommeverse(float sommeverse) {
			this.sommeverse = sommeverse;
		}

		///////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////
		//////ACTIONS DE NOS FORMULAIRES/////////////////////////////////////////////////
		/**
		 * Enregistrement du dossier
		 * Passer par l'EJB, créer un factory qui prend l'objet courant
		 * @return une chaine qui dit ou on doit continuer
		 */
		public String savedossier(){
			String result;
			try {
				result = this.service.savedossier(this);
			} catch (Exception e) {
				if(e.getCause()!=null){
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Parametres incorrects", e);
					}
					else{
						Repertoire.addMessageerreurInnatendue(e);
					}
				}
				else{
					Repertoire.addMessageerreurInnatendue(e);
				}
				return OperationResults.FAILURE;
			}
			
			if(result.compareTo(OperationResults.FAILURE)==0){
				Repertoire.addMessageinfo("Candidature non enregistrée");
				return OperationResults.FAILURE;
			}
			else{
				this.codedossier=result;
				Repertoire.addMessageinfo("Candidature enregistrée");
				return OperationResults.navWithParam("candidature", "codedossier", codedossier);
			}
			
		}
		
		
		public String modifierdossier(){
			Repertoire.logDebug("Modification de candidature", this.getClass());
			boolean result;
			try {
				result = this.service.modifydossier(this);
			} catch (Exception e) {
				if(e.getCause()!=null){
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Dossier de candidature "+ this.codedossier+" non trouvé", e);
					}
					else{
						Repertoire.addMessageerreurInnatendue(e);
					}
				}
				else{
					Repertoire.addMessageerreurInnatendue(e);
				}
				return OperationResults.FAILURE;
			}
			
			if(result)
				Repertoire.addMessageinfo("Candidature Modifiée");			
			else
				Repertoire.addMessageinfo("Candidature Non Modifiée");
			
			return OperationResults.navWithParam("candidature", "codedossier", codedossier);
		}
		
		
		/**
		 * Supression d'un dossier de candidature
		 * @return la chaine de navigation vers la page suivante
		 */
		public String supprimerDossier(){
			boolean result=this.service.deletedossier(codedossier);
			
			if(result)
				Repertoire.addMessageinfo("Candidature supprimée");			
			else
				Repertoire.addMessageinfo("Candidature non supprimée");
				
			return "listercandidatures";
		}

		/**
		 * initialise le contenu du dossier en fonction du parametre codedossier recu
		 */
		public void initDossier(){
			if(codedossier!=null)
				this.service.initDossierBean(this);
			/*else{
				Repertoire.logDebug("Code dossier null", ages.beans.inscription.DossierBean.class);
				Repertoire.addMessageerreurParametreRequis("code dossier");
			}*/
		}
		
		
		/**
		 * Valide une candidature
		 * @return une chaine de navigation pour la page suivante
		 */
		public String validerCandidature(){
			boolean result=true;
			
			
			try{
				if(etat.compareTo("accepte")==0){
					
					if(codeClasse!=null){   // au moins une classe a été sélectionnée
						if(!codeClasse.isEmpty()){
							//la classe est non vide, valide le dossier et envoie l'eleve dans une classe 
							result=this.service.validatedossier(this.codedossier, this.codeClasse);
						}						
						else
							// la classe est vide(non selectionnée) valide juste le dossier
							result=this.service.validatedossier(this.codedossier,null);
					}
					else{// la classe vaut null
						result=this.service.validatedossier(this.codedossier, null);
					}
					
					if(result){  // dossier retenu et opération réussie
						Repertoire.addMessageinfo("Candidature Validée");
						
					}
					else{
						Repertoire.addMessageinfo("Echec de la validation du dossier");
						
					}
						
				}
				else{  // invalider la candidature
					if(etat.compareTo("rejette")==0){
						result=this.service.invalidatedossier(this.codedossier);
						
						if(result){
							Repertoire.addMessageinfo("Candidature invalidée");
						}
						else{
							Repertoire.addMessageinfo("Echec de l'invalidation");
						}
							
					}
				}
			}
			catch(Exception e){
				if(e.getCause()!=null){
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Dossier de candidature non trouvé");
					}
					else{
						Repertoire.addMessageerreurInnatendue(e);
					}
				}
				else{
					Repertoire.addMessageerreurInnatendue(e);
				}
				return null;
			}
			
			return "listercandidatures";
		
		}
		
		/**
		 * Valide une candidature
		 * @return une chaine de navigation pour la page suivante
		 */
		public String Droitscolaire(){
			boolean result=true;
			
			
			try{
				if(etat.compareTo("accepte")==0){
					
					if(codeClasse!=null){   // au moins une classe a été sélectionnée
						if(!codeClasse.isEmpty()){
							//la classe est non vide, valide le dossier et envoie l'eleve dans une classe 
							result=this.service.validatedossier(this.codedossier, this.codeClasse);
						}						
						else
							// la classe est vide(non selectionnée) valide juste le dossier
							result=this.service.validatedossier(this.codedossier,null);
					}
					else{// la classe vaut null
						result=this.service.validatedossier(this.codedossier, null);
					}
					
					if(result){  // dossier retenu et opération réussie
						Repertoire.addMessageinfo("Candidature Validée");
						
					}
					else{
						Repertoire.addMessageinfo("Echec de la validation du dossier");
						
					}
						
				}
				else{  // invalider la candidature
					if(etat.compareTo("rejette")==0){
						result=this.service.invalidatedossier(this.codedossier);
						
						if(result){
							Repertoire.addMessageinfo("Candidature invalidée");
						}
						else{
							Repertoire.addMessageinfo("Echec de l'invalidation");
						}
							
					}
				}
			}
			catch(Exception e){
				if(e.getCause()!=null){
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Dossier de candidature non trouvé");
					}
					else{
						Repertoire.addMessageerreurInnatendue(e);
					}
				}
				else{
					Repertoire.addMessageerreurInnatendue(e);
				}
				return null;
			}
			
			return "droitsscolairesN?faces-redirect=true&includeViewParams=true&matricule="+this.matricule;
		
		}

	public void loadoptions(){
		if(niveauDemande!=null && ! niveauDemande.isEmpty()){
			if(niveauDemande.compareTo("-")==0){
				options=null;
			}
			else{
				options=this.service.listerOptions(niveauDemande);
			}
		}
	}
		
	public String imprimerCandidature(){
		this.service.imprimerCandidature(codedossier);
		return null;
	}
}
