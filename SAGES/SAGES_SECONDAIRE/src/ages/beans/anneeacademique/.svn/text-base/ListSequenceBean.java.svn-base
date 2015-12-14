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


@ManagedBean(name="listSequenceBean")
@ViewScoped
public class ListSequenceBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<SequenceBean> sequences;
	
	public void setService(Service service) {
		this.service = service;
	}
			
	
	public List<SequenceBean> getSequences() {
		return sequences;
	}

	public void setSequences(List<SequenceBean> sequences) {
		this.sequences = sequences;
	}

	/**
	 * initialise mon bean avec la liste des dossiers enregistrés
	 */
	@PostConstruct
	protected void init(){
		if(service==null) Repertoire.logFatal(" Fatal error, service not found", getClass(), new RuntimeException());
		setSequences(this.service.listerSequences());
		
	}
}
