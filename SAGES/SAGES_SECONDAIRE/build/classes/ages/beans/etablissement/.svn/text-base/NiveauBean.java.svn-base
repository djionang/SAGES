package ages.beans.etablissement;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;

@ManagedBean(name="niveauBean")
@ViewScoped
public class NiveauBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codeNiveau;
	private String libelle;
	private String codeCycle;
	private String libellecycle;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
			
	public String getCodeNiveau() {
		return codeNiveau;
	}
	public void setCodeNiveau(String codeNiveau) {
		this.codeNiveau = codeNiveau;
	}
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
	public void setService(Service service) {
		this.service = service;
	}
	public String getLibellecycle() {
		return libellecycle;
	}
	public void setLibellecycle(String libellecycle) {
		this.libellecycle = libellecycle;
	}
	
	
	/**
	 * Enregistrement du nouveau niveau
	 * @return
	 */
	public String saveNiveau(){
		try {
			this.service.saveNiveau(codeNiveau, libelle,codeCycle);
		} catch (DuplicateKeyException e) {
			Repertoire.addMessageerreur("Niveau de code "+codeNiveau+" déja enregistré");
			e.printStackTrace();
			return OperationResults.FAILURE;
		} catch (ElementNOtFoundException e) {
			Repertoire.addMessageerreur("Impossible de trouver le cycle "+codeCycle);
			e.printStackTrace();
		}
		Repertoire.addMessageinfo("Niveau enregistré");
		return OperationResults.NavListingNiveau;
	}
	
	/**
	 * Modification du niveau courant
	 * @return
	 */
	public String modifyNiveau(){
		try {
			this.service.modifyNiveau(codeNiveau, libelle,codeCycle);
		} catch (ElementNOtFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return OperationResults.FAILURE;
		}
		Repertoire.addMessageinfo("Modification niveau enregistrée");
		return OperationResults.NavListingNiveau;
	}
	
	/**
	 * Suppression du niveau courant
	 * @return
	 */
	public String deleteNiveau(){
		try {
			this.service.deleteNiveau(codeNiveau);
		} catch (ElementNOtFoundException e) {
			e.printStackTrace();
			return OperationResults.FAILURE;
		}
		Repertoire.addMessageinfo("Niveau supprimé");
		return OperationResults.NavListingNiveau;
	}
	
	
	public void initniveau(){
		if(codeNiveau!=null){
			this.service.initNiveau(this);
		}
	}
}
