package ages.beans.etablissement.salle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.ScheduleModel;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;

/**
 * Bean géré de Salle
 * Utilisé pour le CRUDE Salle
 * @author Le Bir
 *
 */


@ManagedBean(name="salleBean")
@RequestScoped
public class SalleBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	public void setService(Service service) {
		this.service = service;
	}

	private String codeSalle;
	private int capacite;
	private String libelle;
	private String type;
	private String description;
	private int codeType;
	
	private ScheduleModel modele;
	
	public String getCodeSalle() {
		return codeSalle;
	}
	public void setCodeSalle(String codeSalle) {
		this.codeSalle = codeSalle;
	}
	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getCodeType() {
		return codeType;
	}
	public void setCodeType(int codeType) {
		this.codeType = codeType;
	}
	
	
	public ScheduleModel getModele() {
		return modele;
	}
	public void setModele(ScheduleModel modele) {
		this.modele = modele;
	}
	public String saveSalle(){
		try {
			this.service.saveSalle(codeSalle, capacite, description, codeType, libelle);
		} 
		catch (Exception e) {
			if(e.getCause().getClass().equals(DuplicateKeyException.class)){
				Repertoire.addMessageerreur("Salle "+codeSalle+" Déja existante");
				return OperationResults.FAILURE;
			}
			else{
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("TypeSalle "+codeType+" non trouvé");
					return OperationResults.FAILURE;
				}
				else{
					Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
					return OperationResults.FAILURE;
				}
			}
				
		}
		return OperationResults.SUCCESS;
	}
	
	public String modifierSalle(){
		try {
			this.service.modifierSalle(codeSalle, capacite, description, codeType, libelle);
			Repertoire.logDebug("------------------------------------------------NTB", getClass());
			Repertoire.addMessageerreur("toto");
		} catch (Exception e) {
			if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Salle "+codeSalle+" non trouvé dans la base");
				return OperationResults.FAILURE;
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				return OperationResults.FAILURE;
			}
				
		}
		Repertoire.addMessageinfoModificationOK("Salle");
		//return "detailsalle?faces-redirect=true&includeViewParams=true&codesalle="+this.codeSalle;
		return "listing";
	}
	
	/**
	 * Supression de salle
	 * @return le chemin de navigation pour la suite
	 */
	public String supprimerSalle(){
		
		try {
			this.service.deleteSalle(codeSalle);
		} catch (Exception e) {
			if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Salle "+codeSalle+" non trouvé dans la base");
				return OperationResults.FAILURE;
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				return OperationResults.FAILURE;
			}
				
		}
		return "ressoursallelisting";
	}
	
	
	public void initSalle(){
		if(this.codeSalle==null){
			Repertoire.addMessageerreur("Initialisation impossible, code Salle non renseigné");
			return;
		}
		this.service.initialiseSalleBean(this);
	}
	
	public String saveAtelier(){
		try {
			this.service.saveAtelier(codeSalle, capacite, description, libelle);
		} 
		catch (Exception e) {
			if(e.getCause().getClass().equals(DuplicateKeyException.class)){
				Repertoire.addMessageerreur("Atelier "+codeSalle+" Déja existant");
				return OperationResults.FAILURE;
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				return OperationResults.FAILURE;
			}
				
		}
		return OperationResults.NavListingAtelier;
	}
	
	public String modifierAtelier(){
		try {
			this.service.modifierSalle(codeSalle, capacite, description, codeType, libelle);
		
		} catch (Exception e) {
			if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Atelier "+codeSalle+" non trouvé ");
				return OperationResults.FAILURE;
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				return OperationResults.FAILURE;
			}
				
		}
		return "ressouratelierlisting";
	}
	
	
	/**
	 * Supression de salle
	 * @return le chemin de navigation pour la suite
	 */
	public String supprimerAtelier(){
		
		try {
			this.service.deleteSalle(codeSalle);
		} catch (ElementNOtFoundException e) {
			Repertoire.addMessageerreur("Erreur, Atelier "+codeSalle+" non trouvé");
			return OperationResults.FAILURE;
		}
		return "ressouratelierlisting";
	}
	
	public void initModeleSalle(){
		if(codeSalle!=null && !codeSalle.isEmpty()){
			modele=this.service.listerProgrammationSalle(codeSalle);
		}
	}
	
}
