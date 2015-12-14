package ages.beans.anneeacademique;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.Service;
import utils.Repertoire;
import ages.beans.GenericBean;


@ManagedBean(name="listAnneeBean")
@ViewScoped
public class ListAnneeBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	
	
	private List<AnneeBean> annees;
	
	
	
	public void setService(Service service) {
		this.service = service;
	}	

	
	
	/**
	 * @return the annees
	 */
	public List<AnneeBean> getAnnees() {
		return annees;
	}



	/**
	 * @param annees the annees to set
	 */
	public void setAnnees(List<AnneeBean> annees) {
		this.annees = annees;
	}



	/**
	 * initialise mon bean avec la liste des dossiers enregistrés
	 */
	@PostConstruct
	protected void init(){
		if(service==null) Repertoire.logFatal(" Fatal error, service not found", getClass(), new RuntimeException());
		this.setAnnees(this.service.listerAnneesAcademiques());
		
	}
}
