package ages.beans.budget;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import utils.Service;
import ages.beans.GenericBean;


@ManagedBean(name="listDepenseBean")
@ViewScoped
public class ListDepenseBean extends GenericBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<DepenseBean> depenses;
	
	private List<PrevisionBean> previsions;
	
	private float totalCodedepense;
	
	private float total;
	
	private float totalprevision;
	
	private PieChartModel pieModel;
		
	public void setService(Service service) {
		this.service = service;
	}

	public List<DepenseBean> getDepenses() {
		return depenses;
	}

	public void setDepenses(List<DepenseBean> depenses) {
		this.depenses = depenses;
	}
	
	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public PieChartModel getPieModel() {  
    pieModel = new PieChartModel();  
	pieModel.set("Depense realisee", total); 
	pieModel.set("Non consommee", totalprevision); 

    return pieModel;  
    }

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public float getTotalCodedepense() {
		return totalCodedepense;
	}

	public void setTotalCodedepense(float totalCodedepense) {
		this.totalCodedepense = totalCodedepense;
	}

	public List<PrevisionBean> getPrevisions() {
		return previsions;
	}

	public void setPrevisions(List<PrevisionBean> previsions) {
		this.previsions = previsions;
	}

	public float getTotalprevision() {
		return totalprevision;
	}

	public void setTotalprevision(float totalprevision) {
		this.totalprevision = totalprevision;
	}

	@PostConstruct
	public void init(){
		setDepenses(this.service.listedepenses());
		
		total=0;
		
		for(int i=0;i<depenses.size();i++){
			total+=depenses.get(i).getMontant();
		}
		
		setPrevisions(this.service.listeprevisions());
		
		totalprevision=0;
		
		for(int i=0;i<previsions.size();i++){
			totalprevision+=previsions.get(i).getMontant();
		}
		
	}
	
	public String imprimerVersements(){
		return null;

	}

}
