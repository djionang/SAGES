package ages.beans.etablissement.discipline;

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
 * Bean géré de Type Sanction
 * Utilisé pour le CRUDE Type Sanction
 * @author Le Bir
 *
 */


@ManagedBean(name="typeSanctionBean")
@RequestScoped
public class TypeSanctionBean extends GenericBean implements Serializable{
	
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

	public String saveTypeSanction(){
		try {
			this.service.saveTypeSanction(libelle, description);
		} 
		catch (Exception e) {
			if(e.getCause().getClass().equals(DuplicateKeyException.class)){
				Repertoire.addMessageerreur("TypeSanction "+libelle+" Déja existant");
				return OperationResults.FAILURE;
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				return OperationResults.FAILURE;
				
			}
				
		}
		return "listing";
	}
	
	public String modifierTypeSanction(){
		try {
			this.service.modifierTypeSanction(id, libelle, description);
		} catch (Exception e) {
			if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Sanction "+libelle+" non trouvé");
				return OperationResults.FAILURE;
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				return OperationResults.FAILURE;
			}
				
		}
		Repertoire.addMessageinfoModificationOK("Sanction");
		
		return "listing";
	}
	
	/**
	 * Supression de sanction
	 * @return le chemin de navigation pour la suite
	 */
	public String supprimerTypeSanction(){
		
		try {
			this.service.deleteTypeSanction(id);
		} catch (Exception e) {
			if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("TypeSanction "+libelle+" non trouvé dans la base");
				return OperationResults.FAILURE;
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				return OperationResults.FAILURE;
			}
				
		}
		return "listing";
	}
	
	
	public void initTypeSanction(){
		if(this.id==0){
			Repertoire.addMessageerreur("Initialisation impossible, code Sanction non renseigné");
			return;
		}
		this.service.initialiseTypeSanctionBean(this);
	}
	
	
	
	
}
