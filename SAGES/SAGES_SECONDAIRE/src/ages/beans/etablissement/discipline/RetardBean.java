package ages.beans.etablissement.discipline;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


import utils.OperationResults;
import utils.Service;
import utils.Repertoire;

import ages.beans.GenericBean;
import ages.beans.eleve.EleveBean;
import ages.exception.ElementNOtFoundException;

/**
 * Bean géré de Salle
 * Utilisé pour le CRUD Retard
 * @author Le Bir
 *
 */


@ManagedBean(name="retardBean")
@ViewScoped
public class RetardBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	public void setService(Service service) {
		this.service = service;
	}

	private int coderetard;
	private Date dateretard;
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
	 * @return the coderetard
	 */
	public int getCoderetard() {
		return coderetard;
	}

	/**
	 * @param coderetard the coderetard to set
	 */
	public void setCoderetard(int coderetard) {
		this.coderetard = coderetard;
	}

	/**
	 * @return the dateretard
	 */
	public Date getDateretard() {
		return dateretard;
	}

	/**
	 * @param dateretard the dateretard to set
	 */
	public void setDateretard(Date dateretard) {
		this.dateretard = dateretard;
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


	public String saveRetard(){
		try {
			this.service.saveRetard(dateretard, justifie,duree, matriculeeleve);
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
		Repertoire.addMessageinfoEnregistrementOK("Retard");
		return null;
	}

	public String modifierRetard(){
		if(duree<justifie){
			Repertoire.addMessageerreur("La duree ne peut etre inferieure aux heures justifiées");
			return null;
		}
		try {
			this.service.modifierRetard(coderetard,dateretard, duree,justifie);
			
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Retard non trouvée",e);
					return null;
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
		Repertoire.addMessageinfoModificationOK("Retard");
		return OperationResults.navWithParam("visualisation", "coderetard", String.valueOf(coderetard));
	}

	
	public String supprimerRetard(){
		
		try {
			this.service.supprimerRetard(coderetard);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Retard non trouvée");				}
				else{
					Repertoire.addMessageerreurInnatendue(e);
				}
			}
			else{
				Repertoire.addMessageerreurInnatendue(e);
			}
			return null;
				
		}
		Repertoire.addMessageinfoSuppressionOK("Retard");
		return "listingretards";
	}

	public void initRetard(){
		if(this.coderetard==0){
			Repertoire.addMessageerreur("Initialisation impossible, code Retard non renseigné");
			return;
		}
		this.service.initialiseRetardBean(this);
	}
	
	public void loadEleves(){
		if(codeclasse!=null && !codeclasse.isEmpty()){
			eleves=this.service.listerElevesinscrits(codeclasse);
			if(eleves.isEmpty())
				Repertoire.addMessageerreur("Aucun élève trouvé");
		}
	}
	
	public String updateRetard(){
		if(duree<justifie){
			Repertoire.addMessageerreur("La duree ne peut etre inferieure aux heures justifiées");
			return null;
		}
		try {
			this.service.modifierRetard(coderetard, dateretard, duree, justifie);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreurElementNonTrouve("Retard");
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
		Repertoire.addMessageinfoModificationOK("Retard");
		return "listingretards";
	}
	
	public String deleteRetard(){
		try{
			this.service.supprimerRetard(coderetard);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreurElementNonTrouve("Retard");
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
		Repertoire.addMessageinfoSuppressionOK("Retard");
		return "listingretards";
	}
	
	public void initialize(){
		if(coderetard!=0){
			this.service.initialiseRetardBean(this);
		}
	}
	
}
