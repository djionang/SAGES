package ages.beans.etablissement;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import utils.OperationResults;
import utils.Service;
import utils.Repertoire;
import ages.beans.GenericBean;
import ages.exception.ElementNOtFoundException;


@ManagedBean(name="optionBean")
@RequestScoped
public class OptionBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codeOption;
	private String libelle;
	private String codeniveau;
	private String libelleniveau;
	
	//Pour l'enregistremlent multiple d'options
	private List<String> niveaux;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
			
	public String getCodeOption() {
		return codeOption;
	}
	public void setCodeOption(String codeOption) {
		this.codeOption = codeOption;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}	

	public String getCodeniveau() {
		return codeniveau;
	}
	public void setCodeniveau(String codeniveau) {
		this.codeniveau = codeniveau;
	}
		
	public void setService(Service service) {
		this.service = service;
	}
	
	public List<String> getNiveaux() {
		return niveaux;
	}
	
	public void setNiveaux(List<String> niveaux) {
		this.niveaux = niveaux;
	}
	
	
	public String getLibelleniveau() {
		return libelleniveau;
	}
	
	public void setLibelleniveau(String libelleniveau) {
		this.libelleniveau = libelleniveau;
	}
	

	/**
	 * Enregitre une option, pour un ensemble de niveaux
	 * @return
	 */
	public String saveoption(){
		if(niveaux!=null){
			List<String> res=this.service.saveOptionSerie(libelle,niveaux);
			for(int i=0;i<res.size();i++)
				Repertoire.addMessageinfo("Enregistré: Option "+libelle+" --> "+niveaux.get(i));
		}
		else{
			Repertoire.addMessageerreur("Aucun niveau spécifié, impossible de savugarder");
			return OperationResults.FAILURE;
		}
		Repertoire.addMessageinfo("Nouvelle option enregistrée");
		return OperationResults.NavListingOption;
	}
	
	
	/**
	 * Modifier l'option courante
	 * @return
	 */
	public String modifyOption(){
		try {
			this.service.modifyOption(codeOption, libelle,codeniveau);
		} catch (ElementNOtFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return OperationResults.FAILURE;
		}
		Repertoire.addMessageinfo("Option modifiée avec succes");
		return OperationResults.NavListingOption;
	}
	
	
	/**
	 * Supprimer l'option courante
	 * @return
	 */
	public String deleteOption(){
		try {
			this.service.deleteOption(codeOption);
		} catch (ElementNOtFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return OperationResults.FAILURE;
		}
		Repertoire.addMessageinfo("Option supprimée");
		return OperationResults.NavListingOption;
	}
	
	
	public void initoption(){
		if(codeOption!=null){
			this.service.initOption(this);
		}
	}

}
