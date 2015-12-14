package ages.beans.etablissement.salle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import utils.OperationResults;
import utils.Service;
import utils.Repertoire;

import ages.beans.GenericBean;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;

/**
 * Bean géré de Salle
 * Utilisé pour le CRUDE Salle
 * @author Le Bir
 *
 */


@ManagedBean(name="typeSalleBean")
@RequestScoped
public class TypeSalleBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	

	private int id;
	
	private String libelle;
	
	private String description;
	
	
	

	public void setService(Service service) {
		this.service = service;
	}
	
	
	public String getLibelle() {
		return libelle;
	}
	
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String saveTypeSalle(){
		try {
			this.service.saveTypeSalle(libelle, description);
		} 
		catch (Exception e) {
			if(e.getCause().getClass().equals(DuplicateKeyException.class)){
				Repertoire.addMessageerreur("TypeSalle "+libelle+" Déja existant");
				return OperationResults.FAILURE;
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				return OperationResults.FAILURE;
				
			}
				
		}
		return OperationResults.SUCCESS;
	}
	
	public String modifierTypeSalle(){
		try {
			this.service.modifierTypeSalle(id, libelle, description);
		} catch (Exception e) {
			if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Salle "+libelle+" non trouvé");
				return OperationResults.FAILURE;
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				return OperationResults.FAILURE;
			}
				
		}
		Repertoire.addMessageinfoModificationOK("Salle");
		
		return "listing";
	}
	
	/**
	 * Supression de salle
	 * @return le chemin de navigation pour la suite
	 */
	public String supprimerTypeSalle(){
		
		try {
			this.service.deleteTypeSalle(id);
		} catch (Exception e) {
			if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("TypeSalle "+libelle+" non trouvé dans la base");
				return OperationResults.FAILURE;
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				return OperationResults.FAILURE;
			}
				
		}
		return "listing";
	}
	
	
	public void initTypeSalle(){
		if(this.id==0){
			Repertoire.addMessageerreur("Initialisation impossible, code Salle non renseigné");
			return;
		}
		this.service.initialiseTypeSalleBean(this);
	}
	
	
	
	
}
