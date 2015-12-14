package ages.beans.enseignement;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;

/**
 * Classe ListEleveBean
 * Bean de gestion de la liste d'eleves
 * @author Bri
 *
 */

@ManagedBean(name="listPartieCoursBean")
@ViewScoped
public class ListPartieCoursBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private int codecours;

	private String codeclasse;
	
	private List<CoursBean> cours;
	
	private List<PartieCoursBean> partiescours;
	
	private String nouveaulibelle;
	private String nouvelledescription;
	private Date nouvelledatedebut;
	private Date nouvelledatefin;
	
	private int codepartie;
	
	
	public void setService(Service service) {
		this.service = service;
	}

	public int getCodepartie() {
		return codepartie;
	}

	public void setCodepartie(int codepartie) {
		this.codepartie = codepartie;
	}

	public List<CoursBean> getCours() {
		return cours;
	}

	public void setCours(List<CoursBean> cours) {
		this.cours = cours;
	}


	/**
	 * @return the codecours
	 */
	public int getCodecours() {
		return codecours;
	}

	/**
	 * @param codecours the codecours to set
	 */
	public void setCodecours(int codecours) {
		this.codecours = codecours;
	}

	/**
	 * @return the codeclasse
	 */
	public String getCodeclasse() {
		return codeclasse;
	}

	/**
	 * @param codeclasse the codeclasse to set
	 */
	public void setCodeclasse(String codeclasse) {
		this.codeclasse = codeclasse;
	}

	/**
	 * @return the partiescours
	 */
	public List<PartieCoursBean> getPartiescours() {
		return partiescours;
	}

	/**
	 * @param partiescours the partiescours to set
	 */
	public void setPartiescours(List<PartieCoursBean> partiescours) {
		this.partiescours = partiescours;
	}

	/**
	 * @return the nouveaulibelle
	 */
	public String getNouveaulibelle() {
		return nouveaulibelle;
	}

	/**
	 * @param nouveaulibelle the nouveaulibelle to set
	 */
	public void setNouveaulibelle(String nouveaulibelle) {
		this.nouveaulibelle = nouveaulibelle;
	}

	/**
	 * @return the nouvelledescription
	 */
	public String getNouvelledescription() {
		return nouvelledescription;
	}

	/**
	 * @param nouvelledescription the nouvelledescription to set
	 */
	public void setNouvelledescription(String nouvelledescription) {
		this.nouvelledescription = nouvelledescription;
	}

	/**
	 * @return the nouvelledatedebut
	 */
	public Date getNouvelledatedebut() {
		return nouvelledatedebut;
	}

	/**
	 * @param nouvelledatedebut the nouvelledatedebut to set
	 */
	public void setNouvelledatedebut(Date nouvelledatedebut) {
		this.nouvelledatedebut = nouvelledatedebut;
	}

	/**
	 * @return the nouvelledatefin
	 */
	public Date getNouvelledatefin() {
		return nouvelledatefin;
	}

	/**
	 * @param nouvelledatefin the nouvelledatefin to set
	 */
	public void setNouvelledatefin(Date nouvelledatefin) {
		this.nouvelledatefin = nouvelledatefin;
	}

	/**
	 * Initialisation du bean avec la liste des eleves devant s'inscrire
	 */
	@PostConstruct
	protected void init(){
		this.setCours(this.service.listecours());
	}
	
	public void loadCours(){
		if(codeclasse!=null){
			if(codeclasse.isEmpty()){
				cours=this.service.listecours();
			}
			else{
				cours=this.service.listecours(codeclasse);
			}
			partiescours=null;
			codecours=0;
		}
	}
	
	
	public void loadPartiesCours(){
		if(codecours!=0){
			partiescours=this.service.listerPartiesCours(codecours);
		}
		else
			Repertoire.addMessageerreur("Aucun cours sélectionné");
	}
	
	public void enregistrerPartieCours(ActionEvent ev){
		
		if(codecours==0){
			Repertoire.addMessageerreur("Aucun cours sélectionné");
			return;
		}
		
		if(nouvelledatefin.before(nouvelledatedebut)){
			Repertoire.addMessageerreur("La date de fin ne peut précéder la date de début");
			return;
		}
		
		try {
			this.service.enregistrerPartieCours(codecours,nouveaulibelle,nouvelledescription,nouvelledatedebut,nouvelledatefin);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(DuplicateKeyException.class)){
					Repertoire.addMessageerreur("Chapitre ou partie déja enregistrée", e);
				}
				else{
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Chapitre ou partie déja enregistrée", e);
					}
					else{
						Repertoire.addMessageerreurInnatendue(e);
					}
				}
			}
		} 
		nouveaulibelle=null;
		nouvelledatedebut=null;
		nouvelledatefin=null;
		nouvelledescription=null;
				
		loadPartiesCours();
	}
	
	public void initParties(){
		if(codepartie!=0){
			codecours=this.service.rechercherCodeCours(codepartie);
			
			if(codecours!=0)
				partiescours=this.service.listerPartiesCours(codecours);
		}
	}
	
}
