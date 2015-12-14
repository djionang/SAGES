package ages.beans.auth;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;

import ages.beans.GenericBean;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;


/**
 * ItemRoleBean
 * @author Brilswear
 *
 */


@ManagedBean(name="itemRoleBean")
@RequestScoped
public class ItemRoleBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private int iditem;
	private String libelle;
	private String description;
	
	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
	}

	
	public int getIditem() {
		return iditem;
	}


	public void setIditem(int iditem) {
		this.iditem = iditem;
	}


	public Service getService() {
		return service;
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

	public String saveitemRole(){
		try {
			iditem=this.service.enregistrerItemRole(libelle.toUpperCase(),description);
		} 
		catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().isInstance(DuplicateKeyException.class)){
					Repertoire.addMessageerreur(" Item Role "+libelle+" Déja enregistré");
				}
				else
					Repertoire.addMessageerreurInnatendue(e);
			}
			else{
				Repertoire.addMessageerreurInnatendue(e);
			}
			return null;
		}
		return OperationResults.navWithParam("listing", null, null);
	}
	
	public String modifierItemRole(){
		try {
			this.service.modifierItemRole(iditem,libelle, description);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().isInstance(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur(" Item Role "+libelle+" Non trouvé");
				}
				else
					Repertoire.addMessageerreurInnatendue(e);
			}
			else{
				Repertoire.addMessageerreurInnatendue(e);
			}
			return null;
		}
		return OperationResults.navWithParam("listing", null, null);
	}
	public void initItemRole(){
		if(iditem!=0)
			this.service.initItemRole(this);
	}
	
	
	public String supprimerItemRole(){
		try {
			this.service.supprimerItemRole(iditem);
		} catch (ElementNOtFoundException e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().isInstance(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur(" Item Role "+libelle+" Déja enregistré");
				}
				else
					Repertoire.addMessageerreurInnatendue(e);
			}
			else{
				Repertoire.addMessageerreurInnatendue(e);
			}
		}
		return OperationResults.navWithParam("listing", null, null);
	}
	
	
}
