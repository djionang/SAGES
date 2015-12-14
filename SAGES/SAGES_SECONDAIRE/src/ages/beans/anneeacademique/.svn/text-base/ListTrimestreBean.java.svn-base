package ages.beans.anneeacademique;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import utils.Service;
import ages.beans.GenericBean;

@ManagedBean(name="listTrimestreBean")
@RequestScoped
public class ListTrimestreBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<TrimestreBean> trimestres;	
	
	public void setService(Service service) {
		this.service = service;
	}
	public List<TrimestreBean> getTrimestres() {
		return trimestres;
	}
	public void setTrimestres(List<TrimestreBean> trimestres) {
		this.trimestres = trimestres;
	}
	
	@PostConstruct
	public void initTrimestres(){
		setTrimestres(this.service.listetrimestres());
	}

}
