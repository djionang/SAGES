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


@ManagedBean(name="listNiveauBean")
@ViewScoped
public class ListNiveauBean extends GenericBean implements Serializable{
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<NiveauBean> niveaux;
	

	public ListNiveauBean() {
		
	}

	public void setService(Service service) {
		this.service = service;
	}
			
	
	public List<NiveauBean> getNiveaux() {
		return niveaux;
	}

	public void setNiveaux(List<NiveauBean> niveaux) {
		this.niveaux = niveaux;
	}

	/**
	 * initialise mon bean avec la liste des dossiers enregistrés
	 */
	@PostConstruct
	protected void init(){
		if(service==null) Repertoire.logFatal(" Fatal error, service not found", getClass(), new RuntimeException());
		setNiveaux(this.service.listerNiveaux());
		
	}
}
