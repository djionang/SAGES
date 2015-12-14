package ages.beans.materiel;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.Service;

import ages.beans.GenericBean;

@ManagedBean(name="listSortieMBean")
@ViewScoped
public class ListSortieMBean extends GenericBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
		
	private List<SortieMBean> sortiems;
	
	public void setService(Service service) {
		this.service = service;
	}

	public List<SortieMBean> getSortiems() {
		return sortiems;
	}

	public void setSortiems(List<SortieMBean> sortiems) {
		this.sortiems = sortiems;
	}
	
	@PostConstruct
	public void init(){
		setSortiems(this.service.listesortiems());
		
	}

}
