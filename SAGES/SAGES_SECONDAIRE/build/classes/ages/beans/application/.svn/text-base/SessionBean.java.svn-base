package ages.beans.application;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;


import org.primefaces.component.tabview.Tab;
import org.primefaces.event.TabChangeEvent;

import utils.Service;

@ManagedBean(name="sessionBean")
@SessionScoped
public class SessionBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;

	// mettre les roles
	
	private String codeetablissement;
	private String loginadmin;
	private String passadmin;
	private String logoetablissement;
	private String acronymeetablissement;
	
	private String anneeacademique;
	private Date datedebutannee;
	private Date datefinannee;
	
	//numero du menu droit sélectionné
	private int rightTabIndex;
	// numéro du menu gauche sélectionné
	private int leftTabIndex;
	
	// Managerenregistré??
	private boolean managerdefined;
	
	private int idutilisateur;  // identifiant de l'utilisateur loggué
	
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public String getCodeetablissement() {
		return codeetablissement;
	}
	public void setCodeetablissement(String codeetablissement) {
		this.codeetablissement = codeetablissement;
	}
	public String getLogoetablissement() {
		return logoetablissement;
	}
	public void setLogoetablissement(String logoetablissement) {
		this.logoetablissement = logoetablissement;
	}
	public String getAcronymeetablissement() {
		return acronymeetablissement;
	}
	public void setAcronymeetablissement(String acronymeetablissement) {
		this.acronymeetablissement = acronymeetablissement;
	}
	public String getAnneeacademique() {
		return anneeacademique;
	}
	public void setAnneeacademique(String anneeacademique) {
		this.anneeacademique = anneeacademique;
	}
	public Date getDatedebutannee() {
		return datedebutannee;
	}
	public void setDatedebutannee(Date datedebutannee) {
		this.datedebutannee = datedebutannee;
	}
	public Date getDatefinannee() {
		return datefinannee;
	}
	public void setDatefinannee(Date datefinannee) {
		this.datefinannee = datefinannee;
	}
	
	public int getRightTabIndex() {
		return rightTabIndex;
	}
	public void setRightTabIndex(int rightTabIndex) {
		this.rightTabIndex = rightTabIndex;
	}
	public int getLeftTabIndex() {
		return leftTabIndex;
	}
	public void setLeftTabIndex(int leftTabIndex) {
		this.leftTabIndex = leftTabIndex;
	}
	public boolean isManagerdefined() {
		managerdefined=this.service.checkManagerExist();
		return managerdefined;
	}
	public void setManagerdefined(boolean managerdefined) {
		this.managerdefined = managerdefined;
	}
	
	public String getLoginadmin() {
		return loginadmin;
	}
	public void setLoginadmin(String loginadmin) {
		this.loginadmin = loginadmin;
	}
	public String getPassadmin() {
		return passadmin;
	}
	public void setPassadmin(String passadmin) {
		this.passadmin = passadmin;
	}
	@PostConstruct
	public void init() {
		leftTabIndex=-1;
		rightTabIndex=-1;
	}
	
	
	/**
	 * Gestionnaire de menu actif (gauche)
	 * @param event evenement intercepté lors du changement de menu
	 */
	public void leftTabChange(TabChangeEvent event) {
		 Tab activeTab = event.getTab();
		 try{
			 leftTabIndex=(Integer) activeTab.getAttributes().get("activeIndex");
		 }
		 catch(NullPointerException e){
			 
		 }
		 
	}
	
	/**
	 * Gestionnaire de menu actif (droit)
	 * @param event evenement intercepté lors du changement de menu
	 */
	public void rightTabChange(TabChangeEvent event) {
		 Tab activeTab = event.getTab();
		 try{
			 rightTabIndex=(Integer) activeTab.getAttributes().get("activeIndex");
		 }
		 catch(NullPointerException e){
			 
		 }
		 
	 }
	public int getIdutilisateur() {
		return idutilisateur;
	}
	public void setIdutilisateur(int idutilisateur) {
		this.idutilisateur = idutilisateur;
	}
	
	public void initSessionUser(String etablissement, String logo, String acronyme, String annee, Date datedebut, Date datefin, int iduser, String loginadmin1, String passadmin1){
		this.anneeacademique=annee;
		codeetablissement=etablissement;
		logoetablissement=logo;
		acronymeetablissement=acronyme;
		datedebutannee=datedebut;
		datefinannee=datefin;
		idutilisateur=iduser;
		loginadmin=loginadmin1;
		passadmin=passadmin1;
		
	}
	
}
