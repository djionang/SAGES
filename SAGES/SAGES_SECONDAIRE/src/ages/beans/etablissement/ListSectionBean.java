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

@ManagedBean(name="listSectionBean")
@ViewScoped
public class ListSectionBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<SectionBean> sections;
	

	public ListSectionBean() {
		
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	
	

	public List<SectionBean> getsections() {
		return sections;
	}

	public void setsections(List<SectionBean> sections) {
		this.sections = sections;
	}		
	
	/**
	 * initialise mon bean avec la liste des dossiers enregistrés
	 */
	@PostConstruct
	protected void init(){
		if(service==null) Repertoire.logFatal(" Fatal error, service not found", getClass(), new RuntimeException());
		sections=this.service.listerSections();
		
	}
}
