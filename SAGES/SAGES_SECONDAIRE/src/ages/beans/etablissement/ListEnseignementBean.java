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

@ManagedBean(name="listEnseignementBean")
@ViewScoped
public class ListEnseignementBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<EnseignementBean> enseignements;
		
	public ListEnseignementBean() {
		
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	
	

	public List<EnseignementBean> getEnseignements() {
		return enseignements;
	}

	public void setEnseignements(List<EnseignementBean> enseignements) {
		this.enseignements = enseignements;
	}
	

	
	/**
	 * initialise mon bean avec la liste des dossiers enregistrés
	 */
	@PostConstruct
	protected void init(){
		if(service==null) Repertoire.logFatal(" Fatal error, service not found", getClass(), new RuntimeException());
		enseignements=this.service.listerEnseignements();
		
	}
}
