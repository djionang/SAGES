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
 * Bean géré de Convocation
 * Utilisé pour le CRUD Convocation
 * @author Le Bir
 *
 */


@ManagedBean(name="convocationBean")
@ViewScoped
public class ConvocationBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	public void setService(Service service) {
		this.service = service;
	}

	private int idconvocation;
	private String matriculeeleve;
	private String nomeleve;
	private Date datedelivrance;
	private Date dateeffet;
	private String libelle;
	private String description;
	private String codeclasse;
	private List<EleveBean> eleves;
	private List<String> elevescibles;


	/**
	 * @return the idconvocation
	 */
	public int getIdconvocation() {
		return idconvocation;
	}

	/**
	 * @param idconvocation the idconvocation to set
	 */
	public void setIdconvocation(int idconvocation) {
		this.idconvocation = idconvocation;
	}

	/**
	 * @return the datedelivrance
	 */
	public Date getDatedelivrance() {
		if(datedelivrance!=null)
			return datedelivrance;
		else
			return new Date();
	}

	/**
	 * @param datedelivrance the datedelivrance to set
	 */
	public void setDatedelivrance(Date datedelivrance) {
		this.datedelivrance = datedelivrance;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the dateeffet
	 */
	public Date getDateeffet() {
		if(dateeffet==null)
			dateeffet=new Date();
		return dateeffet;
	}

	/**
	 * @param dateeffet the dateeffet to set
	 */
	public void setDateeffet(Date dateeffet) {
		this.dateeffet = dateeffet;
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

	/**
	 * @return the elevescibles
	 */
	public List<String> getElevescibles() {
		return elevescibles;
	}

	/**
	 * @param elevescibles the elevescibles to set
	 */
	public void setElevescibles(List<String> elevescibles) {
		this.elevescibles = elevescibles;
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

	public String saveConvocation(){
		try {
			this.service.saveConvocation(libelle, description, new Date(), dateeffet,elevescibles);
		} 
		catch (Exception e) {
			if(e.getCause()!=null){
				
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Elèves non trouvés",e);
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
		Repertoire.addMessageinfoEnregistrementOK("Convocation");
		return "listingconvocations";
	}

	public String modifierConvocation(){
		try {
			this.service.modifierConvocation(idconvocation,libelle,description, datedelivrance, dateeffet,matriculeeleve);
			
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Convocation non trouvée",e);
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
		Repertoire.addMessageinfoModificationOK("Convocation");
		return OperationResults.navWithParam("visualisation", "codeconvocation", String.valueOf(idconvocation));
	}

	/**
	 * Supression de salle
	 * @return le chemin de navigation pour la suite
	 */
	public String supprimerConvocation(){
		
		try {
			this.service.supprimerConvocation(idconvocation);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Convocation non trouvée");				}
				else{
					Repertoire.addMessageerreurInnatendue(e);
				}
			}
			else{
				Repertoire.addMessageerreurInnatendue(e);
			}
			return null;
				
		}
		Repertoire.addMessageinfoSuppressionOK("Convocation");
		return "listingconvocations";
	}

	public void initConvocation(){
		if(this.idconvocation==0){
			Repertoire.addMessageerreur("Initialisation impossible, code Convocation non renseigné");
			return;
		}
		this.service.initialiseConvocationBean(this);
	}
	
	public void loadEleves(){
		if(codeclasse!=null && !codeclasse.isEmpty()){
			eleves=this.service.listerElevesinscrits(codeclasse);
			if(eleves.isEmpty())
				Repertoire.addMessageerreur("Aucun élève trouvé");
		}
	}
	
	public String imprimerconvocation(){
		try{
			this.service.imprimerConvocation(idconvocation);
		}
		catch(Exception e){
			e.printStackTrace();
			Repertoire.addMessageerreur("Erreur survenue lors de l'impression");
			
		}
		return null;
	}
}
