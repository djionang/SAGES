package ages.beans.enseignement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.exception.ElementNOtFoundException;


@ManagedBean(name="cdtBean")
@ViewScoped
public class CdtBean extends GenericBean implements Serializable{

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private static final long serialVersionUID = 1L;

	private int codecdt;
	private String libelle;
	private String resume;
	private Date datejour;
	private String libellecours;
	
	private Date heuredebut;
	private Date heurefin;
	
	
	private String codeclasse;
	
	private int codecours;
	
	private List<String> selectedchapitres;
	private List<CoursBean> cours;
	private List<PartieCoursBean> parties;
	
	public CdtBean(){
		
	}
	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	

	public int getCodecours() {
		return codecours;
	}

	public void setCodecours(int codecours) {
		this.codecours = codecours;
	}

	public String getCodeclasse() {
		return codeclasse;
	}

	public void setCodeclasse(String codeclasse) {
		this.codeclasse = codeclasse;
	}

	public void setService(Service service) {
		this.service = service;
	}


	/**
	 * @return the codecdt
	 */
	public int getCodecdt() {
		return codecdt;
	}

	/**
	 * @param codecdt the codecdt to set
	 */
	public void setCodecdt(int codecdt) {
		this.codecdt = codecdt;
	}

	/**
	 * @return the resume
	 */
	public String getResume() {
		return resume;
	}

	/**
	 * @param resume the resume to set
	 */
	public void setResume(String resume) {
		this.resume = resume;
	}

	/**
	 * @return the datejour
	 */
	public Date getDatejour() {
		return datejour;
	}

	/**
	 * @param datejour the datejour to set
	 */
	public void setDatejour(Date datejour) {
		this.datejour = datejour;
	}

	/**
	 * @return the heuredebut
	 */
	public Date getHeuredebut() {
		return heuredebut;
	}

	/**
	 * @param heuredebut the heuredebut to set
	 */
	public void setHeuredebut(Date heuredebut) {
		this.heuredebut = heuredebut;
	}

	/**
	 * @return the heurefin
	 */
	public Date getHeurefin() {
		return heurefin;
	}

	/**
	 * @param heurefin the heurefin to set
	 */
	public void setHeurefin(Date heurefin) {
		this.heurefin = heurefin;
	}

	/**
	 * @return the selectedchapitres
	 */
	public List<String> getSelectedchapitres() {
		return selectedchapitres;
	}

	/**
	 * @param selectedchapitres the selectedchapitres to set
	 */
	public void setSelectedchapitres(List<String> selectedchapitres) {
		this.selectedchapitres = selectedchapitres;
	}

	public String getLibellecours() {
		return libellecours;
	}

	public void setLibellecours(String libellecours) {
		this.libellecours = libellecours;
	}

	/**
	 * @return the cours
	 */
	public List<CoursBean> getCours() {
		return cours;
	}

	/**
	 * @param cours the cours to set
	 */
	public void setCours(List<CoursBean> cours) {
		this.cours = cours;
	}

	public List<PartieCoursBean> getParties() {
		return parties;
	}

	public void setParties(List<PartieCoursBean> parties) {
		this.parties = parties;
	}

	public String saveCdt(){
		try {
			this.codecdt=this.service.saveCdt(listStringToInteger(selectedchapitres),libelle,resume,datejour,heuredebut,heurefin);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().isInstance(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Erreur, Chapitres Non trouvés", e);
				}
				else{
					Repertoire.addMessageerreurInnatendue(e);
				}				
			}	
			else
				Repertoire.addMessageerreurInnatendue(e);
			
			return null;
		}
		Repertoire.addMessageinfoEnregistrementOK("Entrée de Cahier de texte");
		return OperationResults.navWithParam("visualisation", "codecdt", String.valueOf(codecdt));
	}
	
	
	public void initcdt(){
		if(this.codecdt!=0){
			this.service.initCdt(this);
		}		
	}

	public void initcdtModifie(){
		if(this.codecdt!=0){
			this.service.initCdt(this);
			parties=this.service.listerPartiesCours(codecours);
			selectedchapitres=listIntegerToString(this.service.listerCodesChapitresCdt(this.codecdt));
		}		
	}
	
	
	public String supprimerCdt(){
		try {
			this.service.supprimerCdt(codecdt);
		} catch (Exception e) {
			if(e.getCause()!=null&&e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Entrée non trouvée");
			}
			else{
				Repertoire.addMessageerreurInnatendue(e);
			}
			return null;	
		}
		Repertoire.addMessageinfoSuppressionOK("Entrée Cahier de texte");
		return "gestscoolcdtvue";
	}
	
	
	public String modifierCdt(){
		if(heurefin.before(heuredebut)){
			Repertoire.addMessageerreur("L'heure de fin ne peut précéder l'heure de début");
			return null;
		}
		try {
			this.service.modifierCdt(codecdt,listStringToInteger(selectedchapitres),libelle,resume,datejour,heuredebut,heurefin);
		}
		catch (Exception e) {
			if(e.getCause()!=null&&e.getCause().equals(ElementNOtFoundException.class)){
				if(e.getCause()!=null){
					if(e.getCause().getClass().isInstance(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Erreur, Chapitres Non trouvés", e);
					}
					else{
						Repertoire.addMessageerreurInnatendue(e);
					}				
				}	
				else
					Repertoire.addMessageerreurInnatendue(e);
				
				return null;
			}
		}
			
		Repertoire.addMessageinfoEnregistrementOK("Entrée de Cahier de texte");
		return OperationResults.navWithParam("visualisation", "codecdt", String.valueOf(codecdt));
		
	}
	
	public void loadCours(){
		if(codeclasse!=null){
			if(codeclasse.isEmpty()){
				cours=this.service.listecours();
			}
			else{
				cours=this.service.listecours(codeclasse);
			}	
		}
	}
	
	
	public void loadPartiesCours(){
		if(codecours!=0){
			parties=this.service.listerPartiesCours(codecours);
		}
		else
			Repertoire.addMessageerreur("Aucun cours sélectionné");
	}
	
	private List<Integer> listStringToInteger(List<String> liste){
		if(liste==null)
			return null;
		
		List<Integer> ret=new ArrayList<Integer>();
		for(int i=0;i<liste.size();i++){
			ret.add(Integer.parseInt(liste.get(i)));
		}
		return ret;
	}
	
	private List<String> listIntegerToString(List<Integer> liste){
		if(liste==null)
			return null;
		
		List<String> ret=new ArrayList<String>();
		for(int i=0;i<liste.size();i++){
			ret.add(liste.get(i).toString());
		}
		return ret;
	}
		
}
