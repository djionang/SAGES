package ages.beans.programmation;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.model.ScheduleModel;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.enseignement.CoursBean;
import ages.beans.etablissement.classe.ClasseBean;
import ages.exception.AnneeEnCoursNonDefinieException;
import ages.exception.ElementNOtFoundException;


@ManagedBean(name="timeClasseBean")
@ViewScoped
public class TimeClasseBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private ScheduleModel modele;
	private String codeclasse;
	public String libelleclasse;
	private String annee;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<CoursBean> cours;
	private int codecours;
	
	private Date debutlundi;
	private Date finlundi;
	
	private Date debutmardi;
	private Date finmardi;
	
	private Date debutmercredi;
	private Date finmercredi;
	
	private Date debutjeudi;
	private Date finjeudi;
	
	private Date debutvendredi;
	private Date finvendredi;
	
	private Date debutsamedi;
	private Date finsamedi;
	
	public TimeClasseBean(){
		
	}	


	public ScheduleModel getModele() {
		return modele;
	}

	public void setModele(ScheduleModel modele) {
		this.modele = modele;
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


	public String getLibelleclasse() {
		return libelleclasse;
	}


	public void setLibelleclasse(String libelleclasse) {
		this.libelleclasse = libelleclasse;
	}


	public List<CoursBean> getCours() {
		return cours;
	}


	public void setCours(List<CoursBean> cours) {
		this.cours = cours;
	}


	public Date getDebutlundi() {
		return debutlundi;
	}


	public void setDebutlundi(Date debutlundi) {
		this.debutlundi = debutlundi;
	}


	public Date getFinlundi() {
		return finlundi;
	}


	public void setFinlundi(Date finlundi) {
		this.finlundi = finlundi;
	}


	public Date getDebutmardi() {
		return debutmardi;
	}


	public void setDebutmardi(Date debutmardi) {
		this.debutmardi = debutmardi;
	}


	public Date getFinmardi() {
		return finmardi;
	}


	public void setFinmardi(Date finmardi) {
		this.finmardi = finmardi;
	}


	public Date getDebutmercredi() {
		return debutmercredi;
	}


	public void setDebutmercredi(Date debutmercredi) {
		this.debutmercredi = debutmercredi;
	}


	public Date getFinmercredi() {
		return finmercredi;
	}


	public void setFinmercredi(Date finmercredi) {
		this.finmercredi = finmercredi;
	}


	public Date getDebutjeudi() {
		return debutjeudi;
	}


	public void setDebutjeudi(Date debutjeudi) {
		this.debutjeudi = debutjeudi;
	}


	public Date getFinjeudi() {
		return finjeudi;
	}


	public void setFinjeudi(Date finjeudi) {
		this.finjeudi = finjeudi;
	}


	public Date getDebutvendredi() {
		return debutvendredi;
	}


	public void setDebutvendredi(Date debutvendredi) {
		this.debutvendredi = debutvendredi;
	}


	public Date getFinvendredi() {
		return finvendredi;
	}


	public void setFinvendredi(Date finvendredi) {
		this.finvendredi = finvendredi;
	}


	public Date getDebutsamedi() {
		return debutsamedi;
	}


	public void setDebutsamedi(Date debutsamedi) {
		this.debutsamedi = debutsamedi;
	}


	public Date getFinsamedi() {
		return finsamedi;
	}


	public void setFinsamedi(Date finsamedi) {
		this.finsamedi = finsamedi;
	}


	public int getCodecours() {
		return codecours;
	}


	public void setCodecours(int codecours) {
		this.codecours = codecours;
	}


	public String getAnnee() {
		if(annee==null || annee.isEmpty())
			annee=this.service.getAnneeEncours();
		return annee;
	}


	public void setAnnee(String annee) {
		this.annee = annee;
	}


	public void loadTimetable(ActionEvent e){
		if(codeclasse!=null && !codeclasse.isEmpty()){
			modele=this.service.listerProgrammationClasse(codeclasse);
			ClasseBean cb=new ClasseBean();
			cb.setCodeClasse(codeclasse);
			this.service.initialiseClasseBean(cb);
			libelleclasse=cb.getLibelle();
			if(modele.getEventCount()==0)
				Repertoire.addMessageerreur("Aucun cours programmé pour l'instant en "+libelleclasse);
			
		}
	}
	
	public String navajoutertimetable(){
		if(codeclasse==null || codeclasse.isEmpty()){
			Repertoire.addMessageerreur("Aucune classe sélectionnée");
			return null;
		}
		return OperationResults.navWithParam("add-emploi-de-temps", "codeclasse", codeclasse);
	}
	
	public String naveditertimetable(){
		if(codeclasse==null || codeclasse.isEmpty()){
			Repertoire.addMessageerreur("Aucune classe sélectionnée");
			return null;
		}
		return OperationResults.navWithParam("edit-emploi-de-temps", "codeclasse", codeclasse);
	}
	
	public void initializeAdd(){
		if(codeclasse!=null && !codeclasse.isEmpty()){
			this.service.initTimeTableAdd(this);
		}
	}
	
	public void initializeUpdate(){
		if(codeclasse!=null && !codeclasse.isEmpty()){
			this.service.initTimeTableUpdate(this);
		}
	}
	
	public String enregistrerProgrammation(){
		if(codeclasse==null || codeclasse.isEmpty() || codecours==0){
			Repertoire.addMessageerreur("Erreur de parametres");
			return null;
		}
		
		controledates();
		
		try {
			this.service.enregistrerProgrammationCours(codeclasse,codecours,debutlundi,finlundi,debutmardi,finmardi,debutmercredi,finmercredi,debutjeudi,finjeudi,debutvendredi,finvendredi,debutsamedi,finsamedi);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(AnneeEnCoursNonDefinieException.class)){
					Repertoire.addMessageerreurAnneeECNotDefined();
					Repertoire.logError("Annee en cours non définie", getClass(), e);
				}
				else
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Cours non trouvé", e);
					}
					else
						Repertoire.addMessageerreurInnatendue(e);
			}
			return null;
		}
		return OperationResults.navWithParam("emploi-de-temps.xhtml", "codeclasse", String.valueOf(codeclasse));
	}
	
	public String editerProgrammation(){
		if(codeclasse==null || codeclasse.isEmpty() || codecours==0){
			Repertoire.addMessageerreur("Erreur de parametres");
			return null;
		}
		
		controledates();
		
		try {
			this.service.editerProgrammationCours(codeclasse,codecours,debutlundi,finlundi,debutmardi,finmardi,debutmercredi,finmercredi,debutjeudi,finjeudi,debutvendredi,finvendredi,debutsamedi,finsamedi);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(AnneeEnCoursNonDefinieException.class)){
					Repertoire.addMessageerreurAnneeECNotDefined();
					Repertoire.logError("Annee en cours non définie", getClass(), e);
				}
				else
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Cours non trouvé", e);
					}
					else
						Repertoire.addMessageerreurInnatendue(e);
			}
			return null;
		}
		
		return OperationResults.navWithParam("emploi-de-temps.xhtml", "codeclasse", String.valueOf(codeclasse));
	}
	
	public void loadProgrammationCours(){
		if(codecours!=0){
			this.service.chargerProgrammationCours(this);
		}
	}
	
	public void loadTimetableClasse(){
		loadTimetable(null);
	}
	
	public String navReturn(){
		return OperationResults.navWithParam("emploi-de-temps.xhtml", "codeclasse", String.valueOf(codeclasse));
	}
	
	
	private void controledates(){
		try{
			if(finlundi.before(debutlundi)){
				debutlundi=null;
			}
		}
		catch(NullPointerException e){
			Repertoire.logWarn("debut lundi ou fin lundi null dans programmation", getClass());
			debutlundi=null;
		}
		
		try{
			if(finmardi.before(debutmardi)){
				debutmardi=null;
			}
		}
		catch(NullPointerException e){
			Repertoire.logWarn("debut mardi ou fin mardi null dans programmation", getClass());
			debutmardi=null;
		}
		
		try{
			if(finmercredi.before(debutmercredi)){
				debutmercredi=null;
			}
		}
		catch(NullPointerException e){
			Repertoire.logWarn("debut mercredi ou fin mercredi null dans programmation", getClass());
			debutmercredi=null;
		}
		
		try{
			if(finjeudi.before(debutjeudi)){
				debutjeudi=null;
			}
		}
		catch(NullPointerException e){
			Repertoire.logWarn("debut jeudi ou fin jeudi null dans programmation", getClass());
			debutjeudi=null;
		}
		
		try{
			if(finvendredi.before(debutvendredi)){
				debutvendredi=null;
			}
		}
		catch(NullPointerException e){
			Repertoire.logWarn("debut vendredi ou fin vendredi null dans programmation", getClass());
			debutvendredi=null;
		}
		
		try{
			if(finsamedi.before(debutsamedi)){
				debutsamedi=null;
			}
		}
		catch(NullPointerException e){
			Repertoire.logWarn("debut samedi ou fin samedi null dans programmation", getClass());
			debutsamedi=null;
		}
		
	}
}
