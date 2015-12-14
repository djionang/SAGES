package ages.beans.etablissement;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import utils.Service;
import utils.Repertoire;
import ages.beans.GenericBean;

@ManagedBean(name="listOptionBean")
@RequestScoped
public class ListOptionBean extends GenericBean implements Serializable{
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<OptionBean> options;	

	public ListOptionBean() {
		
	}

	public void setService(Service service) {
		this.service = service;
	}
			
	
	public List<OptionBean> getOptions() {
		return options;
	}

	public void setOptions(List<OptionBean> option) {
		this.options = option;
	}

	/**
	 * initialise mon bean avec la liste des dossiers enregistrés
	 */
	@PostConstruct
	protected void init(){
		if(service==null) Repertoire.logFatal(" Fatal error, service not found", getClass(), new RuntimeException());
		setOptions(this.service.listerOptions());
		
	}
}
