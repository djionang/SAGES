package ages.beans.etablissement;

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

@ManagedBean(name="sectionBean")
@RequestScoped
public class SectionBean extends GenericBean implements Serializable{
	private static final long serialVersionUID = 1L;
		
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private String codesection;
	private String description;
	private String libelle;
	private String enseignement;
	
	public String getCodesection() {
		return codesection;
	}
	public void setCodesection(String codesection) {
		this.codesection = codesection;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getEnseignement() {
		return enseignement;
	}
	public void setEnseignement(String enseignement) {
		this.enseignement = enseignement;
	}
	public void setService(Service service) {
		this.service = service;
	}
	
	public String saveSection(){
		try {
			codesection=libelle;
			this.service.saveSection(codesection,description,libelle,enseignement);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(DuplicateKeyException.class)){
					Repertoire.addMessageerreur("Section de libell� "+codesection+" d�ja enregistr�");
					e.printStackTrace();
					return OperationResults.FAILURE;
				}
				else
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Impossible de trouver l'�nseignement "+enseignement);
						e.printStackTrace();
						return OperationResults.FAILURE;
					}
					else{
						Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
						e.printStackTrace();
						return OperationResults.FAILURE;
					}
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				e.printStackTrace();
				return OperationResults.FAILURE;
			}
			
		} 
		Repertoire.addMessageinfo("Nouvelle section enregistr�e");
		return OperationResults.NavListingSection;
	}
	
	public String modifySection(){
		try {
			this.service.modifySection(codesection, description,libelle,enseignement);
		} 
		catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Section de libell� "+codesection+" Non trouv�e");
					e.printStackTrace();
					return OperationResults.FAILURE;
				}
				
				else{
					Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
					e.printStackTrace();
					return OperationResults.FAILURE;
				}
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				e.printStackTrace();
				return OperationResults.FAILURE;
			}
			
		} 
		
		Repertoire.addMessageinfo("Modification Section enregistr�e");
		return OperationResults.NavListingSection;
	}
	
	
	
	public String deleteSection(){
		try {
			this.service.deleteSection(codesection);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Section de libell� "+codesection+" Non trouv�e");
					e.printStackTrace();
					return OperationResults.FAILURE;
				}
				
				else{
					Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
					e.printStackTrace();
					return OperationResults.FAILURE;
				}
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				e.printStackTrace();
				return OperationResults.FAILURE;
			}
			
		} 
		Repertoire.addMessageinfo("Section supprim�e");
		return OperationResults.NavListingSection;
	}
	
	
	public void initsection(){
		if(codesection!=null){
			this.service.initSection(this);
		}
	}
}
