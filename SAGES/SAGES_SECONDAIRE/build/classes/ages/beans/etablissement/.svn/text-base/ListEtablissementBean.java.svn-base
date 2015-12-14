package ages.beans.etablissement;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.Service;
import utils.Repertoire;
import ages.beans.GenericBean;

@ManagedBean(name="listEtablissementBean")
@ViewScoped
public class ListEtablissementBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<EtablissementBean> etablissements;
		
	public ListEtablissementBean() {
		
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	
	

	public List<EtablissementBean> getEtablissements() {
		return etablissements;
	}

	public void setEtablissements(List<EtablissementBean> etablissements) {
		this.etablissements = etablissements;
	}
	

	
	/**
	 * initialise mon bean avec la liste des dossiers enregistrés
	 */
	@PostConstruct
	protected void init(){
		if(service==null) Repertoire.logFatal(" Fatal error, service not found", getClass(), new RuntimeException());
		etablissements=this.service.listerEtablissements();
		
	}
}
