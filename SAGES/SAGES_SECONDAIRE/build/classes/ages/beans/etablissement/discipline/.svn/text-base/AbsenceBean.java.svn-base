package ages.beans.etablissement.discipline;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.Service;
import utils.Repertoire;

import ages.beans.GenericBean;
import ages.beans.eleve.EleveBean;
import ages.exception.ElementNOtFoundException;



@ManagedBean(name="absenceBean")
@ViewScoped
public class AbsenceBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	public void setService(Service service) {
		this.service = service;
	}

	private int codeabsence;
	private Date dateabsence;
	private int duree;
	private int justifie;
	private boolean supprime;
	private String matriculeeleve;
	private String nomeleve;
	
	private String codeclasse;
	private List<EleveBean> eleves;


	/**
	 * @return the nomeleve
	 */
	public String getNomeleve() {
		return nomeleve;
	}

	/**
	 * @param nomeleve the nomeleve to set
	 */
	public void setNomeleve(String nomeleve) {
		this.nomeleve = nomeleve;
	}

	/**
	 * @return the duree
	 */
	public int getDuree() {
		return duree;
	}

	/**
	 * @param duree the duree to set
	 */
	public void setDuree(int duree) {
		this.duree = duree;
	}

	/**
	 * @return the matriculeeleve
	 */
	public String getMatriculeeleve() {
		return matriculeeleve;
	}

	/**
	 * @param matriculeeleve the matriculeeleve to set
	 */
	public void setMatriculeeleve(String matriculeeleve) {
		this.matriculeeleve = matriculeeleve;
	}

	/**
	 * @return the codeabsence
	 */
	public int getCodeabsence() {
		return codeabsence;
	}

	/**
	 * @param codeabsence the codeabsence to set
	 */
	public void setCodeabsence(int codeabsence) {
		this.codeabsence = codeabsence;
	}

	/**
	 * @return the dateabsence
	 */
	public Date getDateabsence() {
		return dateabsence;
	}

	/**
	 * @param dateabsence the dateabsence to set
	 */
	public void setDateabsence(Date dateabsence) {
		this.dateabsence = dateabsence;
	}


	/**
	 * @return the justifie
	 */
	public int getJustifie() {
		return justifie;
	}

	/**
	 * @param justifie the justifie to set
	 */
	public void setJustifie(int justifie) {
		this.justifie = justifie;
	}

	/**
	 * @return the supprime
	 */
	public boolean isSupprime() {
		return supprime;
	}

	/**
	 * @param supprime the supprime to set
	 */
	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
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
	 * @return the eleves
	 */
	public List<EleveBean> getEleves() {
		return eleves;
	}

	/**
	 * @param eleves the eleves to set
	 */
	public void setEleves(List<EleveBean> eleves) {
		this.eleves = eleves;
	}


	public void initAbsence(){
		if(this.codeabsence==0){
			Repertoire.addMessageerreur("Initialisation impossible, code Absence non renseigné");
			return;
		}
		this.service.initialiseAbsenceBean(this);
	}
	
	public void loadEleves(){
		if(codeclasse!=null && !codeclasse.isEmpty()){
			eleves=this.service.listerElevesinscrits(codeclasse);
			if(eleves.isEmpty())
				Repertoire.addMessageerreur("Aucun élève trouvé");
		}
	}
	
	public String saveAbsence(){
		if(duree<justifie){
			Repertoire.addMessageerreur("La duree ne peut etre inferieure aux heures justifiées");
			return null;
		}
		try {
			this.service.saveAbsence(dateabsence, justifie,duree, matriculeeleve);
		} 
		catch (Exception e) {
			if(e.getCause()!=null){
				
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Eleve non trouvé",e);
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
		Repertoire.addMessageinfoEnregistrementOK("Absence");
		return "listingabsences";
	}

	public String updateAbsence(){
		if(duree<justifie){
			Repertoire.addMessageerreur("La duree ne peut etre inferieure aux heures justifiées");
			return null;
		}
		try {
			this.service.modifierAbsence(codeabsence, dateabsence, duree, justifie);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreurElementNonTrouve("Absence");
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
		Repertoire.addMessageinfoModificationOK("Absence");
		return "listingabsences";
	}
	
	public String deleteAbsence(){
		try{
			this.service.supprimerAbsence(codeabsence);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreurElementNonTrouve("Absence");
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
		Repertoire.addMessageinfoSuppressionOK("Absence");
		return "listingabsences";
	}
	
	public void initialize(){
		if(codeabsence!=0){
			this.service.initialiseAbsenceBean(this);
		}
	}
	
}
