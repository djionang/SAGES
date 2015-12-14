package ages.beans.etablissement.personnel;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import utils.OperationResults;
import utils.Service;
import ages.beans.GenericBean;

@ManagedBean(name="listPersonnelBean")
@RequestScoped
public class ListPersonnelBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<PersonnelBean> personnels;	
	
	public void setService(Service service) {
		this.service = service;
	}
	
	@PostConstruct
	public void initPersonnels(){
		setPersonnels(this.service.listepersonnel());
	}

	public List<PersonnelBean> getPersonnels() {
		return personnels;
	}

	public void setPersonnels(List<PersonnelBean> personnels) {
		this.personnels = personnels;
	}	

	public String saveenseignant(){
		return OperationResults.EnregistrementOK;
	}

}
