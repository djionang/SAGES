package ages.beans.etablissement;

import java.io.Serializable;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.application.ConfigurationBean;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;

/**
 * Bean de gestion des informations relatives a un établissement
 * @author Brilswear
 *
 */

@ManagedBean(name="etablissementBean")
@ViewScoped
public class EtablissementBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
			
	private String codeetablissement;
	private String nom;
	private String acronyme;
	private String devise;
	private String logo;
	private String type;
	
	
	private String codepostal;
	private String email;
	private String telephone;
	private String siteweb;
	
	private String region;
	private String departement;
	private String arrondissement;
	private String adresse;
	private String pays;
	private String devisepays;
	
	private UploadedFile logoUploaded;
	
	private boolean estCharge;
	
	private String logingest;
	private String passgest;

	public void setService(Service service) {
		this.service = service;
	}
	
	public String getCodeetablissement() {
		return codeetablissement;
	}


	public void setCodeetablissement(String codeetablissement) {
		this.codeetablissement = codeetablissement;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAcronyme() {
		return acronyme;
	}

	public void setAcronyme(String acronyme) {
		this.acronyme = acronyme;
	}

	public String getDevise() {
		return devise;
	}

	public void setDevise(String devise) {
		this.devise = devise;
	}	
	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getCodepostal() {
		return codepostal;
	}


	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
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

	public String getSiteweb() {
		return siteweb;
	}

	public void setSiteweb(String siteweb) {
		this.siteweb = siteweb;
	}

	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}

	public String getDepartement() {
		return departement;
	}


	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getArrondissement() {
		return arrondissement;
	}

	public void setArrondissement(String arrondissement) {
		this.arrondissement = arrondissement;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public boolean isEstCharge() {
		return estCharge;
	}

	public void setEstCharge(boolean estCharge) {
		this.estCharge = estCharge;
	}

	public UploadedFile getLogoUploaded() {
		return logoUploaded;
	}

	public void setLogoUploaded(UploadedFile logoUploaded) {
		this.logoUploaded = logoUploaded;
	}

	public String getDevisepays() {
		return devisepays;
	}

	public void setDevisepays(String devisepays) {
		this.devisepays = devisepays;
	}

	
	
	/**
	 * @return the logingest
	 */
	public String getLogingest() {
		return logingest;
	}

	/**
	 * @param logingest the logingest to set
	 */
	public void setLogingest(String logingest) {
		this.logingest = logingest;
	}

	/**
	 * @return the passgest
	 */
	public String getPassgest() {
		return passgest;
	}

	/**
	 * @param passgest the passgest to set
	 */
	public void setPassgest(String passgest) {
		this.passgest = passgest;
	}

	private String getRealPath(String path){
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);
	}
	
	public String saveEtablissement(){
		try {
			logo=ConfigurationBean.imgpath+"/"+codeetablissement+"/logo.jpg";
			//logo=getrealpath(ConfigurationBean.imgpath).substring(0,getrealpath(ConfigurationBean.imgpath).length()-76)+"/"+codeetablissement+"/logo.jpg";
			String chemin=getRealPath(logo).substring(72);
			System.out.println("le chemin est"+ chemin);
			this.service.saveEtablissement(codeetablissement, nom,	logingest,passgest);
			Repertoire.creerRepertoiresImages(codeetablissement);
		} catch (Exception e) {
			if(e.getCause().getClass().equals(DuplicateKeyException.class)){
				Repertoire.addMessageerreur("Etablissement de code "+codeetablissement+" déja enregistré");
			}
			else{
				Repertoire.addMessageerreurimpression(e);
			}
				
			return OperationResults.FAILURE;
		}
		
		Repertoire.addMessageinfoEnregistrementOK("Etablissement");	
		return OperationResults.NavListingEtablissement;
	}
	
	
	public static String getrealpath(String path){
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);
	}
	

	public String modifierEtablissement(){
		if(logoUploaded!=null){			
			logo=getrealpath(ConfigurationBean.imgpath).substring(0,getrealpath(ConfigurationBean.imgpath).length()-76)+codeetablissement;
			//String chemin=getRealPath(logo).substring(72);
			System.out.println("le chemin est"+ logo);
		}
		System.out.println("/////////////////////////////////////////////////////");
		try {
			this.service.modifyEtablissement(codeetablissement, nom,acronyme,devise,logo,type,codepostal,email,telephone,siteweb,region,departement,arrondissement,adresse,pays,devisepays);
			
		} catch (ElementNOtFoundException e) {
			Repertoire.addMessageerreur("Etablissement non trouvé");
			Repertoire.logError("Modificxation de l'etablissement non trouvé "+codeetablissement, getClass(), e);
		}
		
		if(logoUploaded!=null){			
			Repertoire.savelogoEtab(codeetablissement, logoUploaded.getContents());
		}
		
		Repertoire.addMessageinfo("Modification enregistrée");
		return null;
	}
	
	public String admodifierEtablissement(){
		
		try {
			this.service.admodifyEtablissement(codeetablissement, nom,	logingest,passgest);
			
		} catch (Exception e) {
			Repertoire.addMessageerreur("Etablissement non trouvé");
			Repertoire.logError("Modificxation de l'etablissement non trouvé "+codeetablissement, getClass(), e);
		}
		
		if(logoUploaded!=null){			
			Repertoire.savelogoEtab(codeetablissement, logoUploaded.getContents());
		}
		
		Repertoire.addMessageinfo("Modification enregistrée");
		return OperationResults.NavListingEtablissement;
	}
	
	public String deleteEtablissement(){
		try {
			
			this.service.deleteEtablissement(codeetablissement);
		} catch (ElementNOtFoundException e) {
			return OperationResults.FAILURE;
		}
		
		Repertoire.suprimerRepertoiresImages(codeetablissement);
		Repertoire.addMessageinfo("Etablissement supprimé");
		return OperationResults.NavListingEtablissement;
	}	
	
	public void initetablissement(){
		if(this.codeetablissement!=null)
			this.service.initEtablissement(this);
		
		if(!this.estCharge){
			FacesContext facesContext = FacesContext.getCurrentInstance();
		    String redirect = "accueil";// define the navigation rule that must be used in order to redirect the user to the adequate page...
		    NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
		    myNav.handleNavigation(facesContext, null, redirect);
		}
	}
	
	public void initeditetablissement(){
		this.service.initEtablissementEdit(this);
		
		if(!this.estCharge){
			FacesContext facesContext = FacesContext.getCurrentInstance();
		    String redirect = "accueil";// define the navigation rule that must be used in order to redirect the user to the adequate page...
		    NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
		    myNav.handleNavigation(facesContext, null, redirect);
		}
	}

	
	
	/**
	 * Navigation ves la modification de l'établissement courant
	 * @return
	 */
	public String navmodify(){
		return OperationResults.navWithParam("modifierEtablissement", codeetablissement, this.codeetablissement);
		 
	}


}
