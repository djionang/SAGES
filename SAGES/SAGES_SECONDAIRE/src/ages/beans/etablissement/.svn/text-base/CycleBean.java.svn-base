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

@ManagedBean(name="cycleBean")
@RequestScoped
public class CycleBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codeCycle;
	private String libelle;
	private String codesection;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
			
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getCodeCycle() {
		return codeCycle;
	}
	public void setCodeCycle(String codeCycle) {
		this.codeCycle = codeCycle;
	}
	public String getCodesection() {
		return codesection;
	}
	public void setCodesection(String codesection) {
		this.codesection = codesection;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	
	public String saveCycle(){
		try {
			this.service.saveCycle(codeCycle, libelle,codesection);
		} 
		catch (Exception e) {
			if(e.getCause().getClass().equals(DuplicateKeyException.class)){
				Repertoire.addMessageerreur("Cycle de code "+codeCycle+" déja enregistré");
				e.printStackTrace();
				return OperationResults.FAILURE;
			}
			else{
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Section cible non trouvée ");
					e.printStackTrace();
					return null;
				}
				else{
					Repertoire.addMessageerreurimpression(e);
					return null;
				}
			}
			
		}
		Repertoire.addMessageinfo("Nouveau cycle enregistré");
		return OperationResults.NavListingCycle;
	}
	
	public String modifyCycle(){
		try {
			this.service.modifyCycle(codeCycle, libelle,codesection);
		} catch (Exception e) {
			if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Cycle non trouvé ");
				e.printStackTrace();
				return null;
			}
			else{
				Repertoire.addMessageerreurimpression(e);
				return null;
			}
			
		}
		Repertoire.addMessageinfo("Mise à jour cycle enregistrée");
		return OperationResults.NavListingCycle;
	}
	
	public String deleteCycle(){
		try {
			this.service.deleteCycle(codeCycle);
		} catch (Exception e) {
			if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Cycle non trouvé ");
				e.printStackTrace();
				return OperationResults.FAILURE;
			}
			else{
				Repertoire.addMessageerreurimpression(e);
				return null;
			}
			
		}
		Repertoire.addMessageinfo("Cycle supprimé");
		return OperationResults.NavListingCycle;
	}
	
	
	public void initcycle(){
		if(codeCycle!=null){
			this.service.initCycle(this);
		}
	}
}
