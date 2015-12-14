package ages.beans.statistique;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.beans.anneeacademique.AnneeBean;
import ages.exception.AnneeEnCoursNonDefinieException;

/**
 * Bean de listing des tranches versés par une eleve, ou a verser
 * @author Brilswear
 *
 */

@ManagedBean(name="listeBilanAnBean")
@ViewScoped
public class ListeBilanAnBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	// annee sélectionnée
	private AnneeBean selectedAnnee;
	
	// Liste des bilan pour un etablissement
	private List<BilanAnBean> listeBilansp;
	
	//modele d'affichage pour la liste des annees pour un etablissement
	private BilanAnnuelDataModel modeleBilan;
	
	private String anneeacademique;
	
	private float total;
	
	private float totalmoyenne;
	
	private int totaleffectif;

	private PieChartModel pieModel;  
	  
    public ListeBilanAnBean() {  
    	// createPieModel();  
    }  
    private CartesianChartModel categoryModel;  
    
  
    public CartesianChartModel getCategoryModel() { 
    	categoryModel = new CartesianChartModel();  
        ChartSeries boys = new ChartSeries();  
        if(listeBilansp!=null){
			for(int i=0;i<listeBilansp.size();i++){
				boys.set(listeBilansp.get(i).getLibelleclasse(), listeBilansp.get(i).getTauxreussite()); 
			}
		}  
        categoryModel.addSeries(boys);
        return categoryModel;  
    } 
  
    public PieChartModel getPieModel() {  
    	pieModel = new PieChartModel();  
    	System.out.println("je suis la et la taille est "+  listeBilansp.size());
        if(listeBilansp!=null){
			for(int i=0;i<listeBilansp.size();i++){
				pieModel.set(listeBilansp.get(i).getLibelleclasse(), listeBilansp.get(i).getTauxreussite()); 
			}
		} 
        return pieModel;  
    }  


	public float getTotalmoyenne() {
		if(listeBilansp!=null){
			totalmoyenne=0;
			for(int i=0;i<listeBilansp.size();i++){
				totalmoyenne+=listeBilansp.get(i).getMoyenneclasse();
			}
			totalmoyenne=totalmoyenne/listeBilansp.size();
		}
		return totalmoyenne;
	}

	public void setTotalmoyenne(float totalmoyenne) {
		this.totalmoyenne = totalmoyenne;
	}

	public int getTotaleffectif() {
		
		if(listeBilansp!=null){
			totaleffectif=0;
			for(int i=0;i<listeBilansp.size();i++){
				totaleffectif+=listeBilansp.get(i).getEffectif();
			}
		}
		return totaleffectif;
	}

	public void setTotaleffectif(int totaleffectif) {
		this.totaleffectif = totaleffectif;
	}

	

	public void setService(Service service) {
		this.service = service;
	}
	
	
	public String getAnneeacademique() {
		return anneeacademique;
	}

	public void setAnneeacademique(String anneeacademique) {
		this.anneeacademique = anneeacademique;
	}

	public float getTotal() {
		if(listeBilansp!=null){
			total=0;
			for(int i=0;i<listeBilansp.size();i++){
				total+=listeBilansp.get(i).getTauxreussite();
			}
			total=total/listeBilansp.size();
		}
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public BilanAnnuelDataModel getModeleBilan() {
		return modeleBilan;
	}

	public void setModeleBilan(BilanAnnuelDataModel modeleBilan) {
		this.modeleBilan = modeleBilan;
	}

	public void setListeBilansp(List<BilanAnBean> listeBilansp) {
		this.listeBilansp = listeBilansp;
	}

	public AnneeBean getSelectedAnnee() {
		return selectedAnnee;
	}

	public void setSelectedAnnee(AnneeBean selectedAnnee) {
		this.selectedAnnee = selectedAnnee;
	}

	public void chargerBilanAn(ActionEvent e){
		if((selectedAnnee!=null)&&(selectedAnnee.getAnneeacademique())!=null){
			listeBilansp=this.service.listeBilanAnnuel(selectedAnnee.getAnneeacademique());
			
			modeleBilan=new BilanAnnuelDataModel(listeBilansp);
		}
		else{
			Repertoire.addMessageerreur("Aucune annee sélectionnée");
		}
		
	}
		

	public void initialize(){
		if(anneeacademique!=null){
			selectedAnnee=new AnneeBean();
			selectedAnnee.setAnneeacademique(anneeacademique);
			try {
				this.service.initialiseAnneeBean(selectedAnnee);
			} catch (AnneeEnCoursNonDefinieException e) {
				System.out.println("annee non definie");
				e.printStackTrace();
			}
			System.out.println("le numero de trimestre est"+  selectedAnnee.getAnneeacademique());
			if(this.selectedAnnee.getAnneeacademique()!=null){
				chargerBilanAn(null);
			}
			else{
				selectedAnnee=null;
			}
		}
	}
	
	
}
