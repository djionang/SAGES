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

@ManagedBean(name="enseignementBean")
@RequestScoped
public class EnseignementBean extends GenericBean implements Serializable{
	private static final long serialVersionUID = 1L;
		
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
			
	private String libelleens;
	private String description;
	private String type;
	private String etablissement;
	private String acronymeEtablissement;
	
	
	public String getLibelleens() {
		return libelleens;
	}


	public void setLibelleens(String libelleens) {
		this.libelleens = libelleens;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}



	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getEtablissement() {
		return etablissement;
	}


	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}


	public void setService(Service service) {
		this.service = service;
	}


	public String getAcronymeEtablissement() {
		return acronymeEtablissement;
	}


	public void setAcronymeEtablissement(String acronymeEtablissement) {
		this.acronymeEtablissement = acronymeEtablissement;
	}


	public String saveEnseignement(){
		try {
			this.service.saveEnseignement(libelleens, description,type);
		} catch (DuplicateKeyException e) {
			Repertoire.addMessageerreur("Enseignement de libellé "+libelleens+" déja enregistré");
			e.printStackTrace();
			return OperationResults.FAILURE;
		} catch (ElementNOtFoundException e) {
			Repertoire.addMessageerreur("Impossible de trouver l'établissement "+etablissement);
			e.printStackTrace();
		}
		Repertoire.addMessageinfo("Nouvel enseignement enregistré");
		return OperationResults.NavListingEnseignement;
	}
	
	public String modifyEnseignement(){
		try {
			this.service.modifyEnseignement(libelleens, description, type);
		} catch (ElementNOtFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return OperationResults.FAILURE;
		}
		Repertoire.addMessageinfo("Mise à jour enregistrée");
		return OperationResults.NavListingEnseignement;
	}
	
	public String deleteEnseignement(){
		try {
			this.service.deleteEnseignement(libelleens);
		} catch (ElementNOtFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return OperationResults.FAILURE;
		}
		Repertoire.addMessageinfo("Enseignement supprimé");
		return OperationResults.NavListingEnseignement;
	}
	
	
	public void initenseignement(){
		if(libelleens!=null){
			this.service.initEnseignement(this);
		}
	}


	

}
