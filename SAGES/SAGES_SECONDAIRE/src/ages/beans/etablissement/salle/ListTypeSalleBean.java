package ages.beans.etablissement.salle;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import utils.Service;

import ages.beans.GenericBean;


@ManagedBean(name="listTypeSalleBean")
@RequestScoped
public class ListTypeSalleBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	

	private List<TypeSalleBean> listeTypeSalles;
	
	
	public void setService(Service service) {
		this.service = service;
	}



	public List<TypeSalleBean> getListeTypeSalles() {
		return listeTypeSalles;
	}



	public void setListeTypeSalles(List<TypeSalleBean> listeTypeSalles) {
		this.listeTypeSalles = listeTypeSalles;
	}



	@PostConstruct
	public void initSalles(){
		setListeTypeSalles(this.service.listerTypesalle());
	}

	
}
