package ages.beans.etablissement;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import ages.beans.GenericBean;

import utils.Service;
import utils.Repertoire;

@ManagedBean(name="listCycleBean")
@ViewScoped
public class ListCycleBean extends GenericBean implements Serializable{
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<CycleBean> cycles;
	

	public ListCycleBean() {
		
	}

	public void setService(Service service) {
		this.service = service;
	}
			
	
	public List<CycleBean> getCycles() {
		return cycles;
	}

	public void setCycles(List<CycleBean> cycles) {
		this.cycles = cycles;
	}

	/**
	 * initialise mon bean avec la liste des dossiers enregistrés
	 */
	@PostConstruct
	protected void init(){
		if(service==null) Repertoire.logFatal(" Fatal error, service not found", getClass(), new RuntimeException());
		setCycles(this.service.listerCycles());
		
	}

	
}
