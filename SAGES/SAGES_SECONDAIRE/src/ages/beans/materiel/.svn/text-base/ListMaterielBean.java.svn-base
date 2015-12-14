package ages.beans.materiel;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.Service;
import ages.beans.GenericBean;

@ManagedBean(name="listMaterielBean")
@ViewScoped
public class ListMaterielBean extends GenericBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	public void setService(Service service) {
		this.service = service;
	}
	private List<MaterielBean> materiels;
	
	private float total;

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public List<MaterielBean> getMateriels() {
		return materiels;
	}

	public void setMateriels(List<MaterielBean> materiels) {
		this.materiels = materiels;
	}
	
	@PostConstruct
	public void init(){
		setMateriels(this.service.listematriels());
		
		total=0;
		
		for(int i=0;i<materiels.size();i++){
			total+=materiels.get(i).getPrix();
		}
		
	}

}
