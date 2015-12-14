package ages.beans.etablissement.discipline;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import utils.Service;

import ages.beans.GenericBean;


@ManagedBean(name="listTypeSanctionBean")
@RequestScoped
public class ListTypeSanctionBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	

	private List<TypeSanctionBean> listeTypeSanctions;
	
	
	public void setService(Service service) {
		this.service = service;
	}



	public List<TypeSanctionBean> getListeTypeSanctions() {
		return listeTypeSanctions;
	}



	public void setListeTypeSanctions(List<TypeSanctionBean> listeTypeSanctions) {
		this.listeTypeSanctions = listeTypeSanctions;
	}



	@PostConstruct
	public void initSanctions(){
		setListeTypeSanctions(this.service.listerTypesanction());
	}

	
}
