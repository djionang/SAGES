package ages.beans.budget;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.Service;
import ages.beans.GenericBean;


@ManagedBean(name="listPrevisionBean")
@ViewScoped
public class ListPrevisionBean extends GenericBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<PrevisionBean> previsions;
	
	private float totalCode;
	
	private float total;
			
	public void setService(Service service) {
		this.service = service;
	}

	public List<PrevisionBean> getPrevisions() {
		return previsions;
	}

	public void setPrevisions(List<PrevisionBean> previsions) {
		this.previsions = previsions;
	}
	
	public float getTotalCode() {
		return totalCode;
	}

	public void setTotalCode(float totalCode) {
		this.totalCode = totalCode;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	@PostConstruct
	public void init(){
		setPrevisions(this.service.listeprevisions());
		
		total=0;
		
		for(int i=0;i<previsions.size();i++){
			total+=previsions.get(i).getMontant();
		}
		
	}
	

}
