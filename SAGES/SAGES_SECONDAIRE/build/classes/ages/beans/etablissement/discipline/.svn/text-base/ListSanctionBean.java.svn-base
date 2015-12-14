package ages.beans.etablissement.discipline;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import utils.Service;

import ages.beans.GenericBean;


@ManagedBean(name="listSanctionBean")
@RequestScoped
public class ListSanctionBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	

	private List<SanctionBean> listeSanctions;
	
	

	public void setService(Service service) {
		this.service = service;
	}

	/**
	 * @return the listeSanctions
	 */
	public List<SanctionBean> getListeSanctions() {
		return listeSanctions;
	}

	/**
	 * @param listeSanctions the listeSanctions to set
	 */
	public void setListeSanctions(List<SanctionBean> listeSanctions) {
		this.listeSanctions = listeSanctions;
	}

	@PostConstruct
	public void initSanctions(){
		this.listeSanctions=this.service.listeSanctions();
	}

}
