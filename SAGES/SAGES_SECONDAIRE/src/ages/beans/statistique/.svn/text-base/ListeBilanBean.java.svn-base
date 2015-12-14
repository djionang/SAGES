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
import ages.beans.anneeacademique.SequenceBean;

/**
 * Bean de listing des tranches versés par une eleve, ou a verser
 * @author Brilswear
 *
 */

@ManagedBean(name="listeBilanBean")
@ViewScoped
public class ListeBilanBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	// classe sélectionnée
	private SequenceBean selectedSequence;
	
	// Liste des tranches a verser pour une classe
	private List<BilanBean> listeBilansp;
	
	//modele d'affichage pour la liste des tranches a verser pour une classe
	private BilanDataModel modeleBilan;
	
	private int numerosequence;
	
	// Tranche sélectionné, pour la gestion (Modificatio ou suppression)
	private BilanBean selectedBilan;
	
	private float total;
	
	private float totalmoyenne;
	
	private int totaleffectif;
	
	private PieChartModel pieModel;  
	  
    public ListeBilanBean() {  
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

	public SequenceBean getSelectedSequence() {
		return selectedSequence;
	}

	public void setSelectedSequence(SequenceBean selectedSequence) {
		this.selectedSequence = selectedSequence;
	}

	public void setService(Service service) {
		this.service = service;
	}

	

	public int getNumerosequence() {
		return numerosequence;
	}

	public void setNumerosequence(int numerosequence) {
		this.numerosequence = numerosequence;
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

	public List<BilanBean> getListeBilansp() {
		return listeBilansp;
	}

	public void setListeBilansp(List<BilanBean> listeBilansp) {
		this.listeBilansp = listeBilansp;
	}

	public BilanDataModel getModeleBilan() {
		return modeleBilan;
	}

	public void setModeleBilan(BilanDataModel modeleBilan) {
		this.modeleBilan = modeleBilan;
	}

	public BilanBean getSelectedBilan() {
		return selectedBilan;
	}

	public void setSelectedBilan(BilanBean selectedBilan) {
		this.selectedBilan = selectedBilan;
	}

	public void chargerBilan(ActionEvent e){
		if((selectedSequence!=null)&&(selectedSequence.getNumero()!=0)){
			listeBilansp=this.service.listeBilanSeq(selectedSequence.getNumero());
			
			modeleBilan=new BilanDataModel(listeBilansp);
		}
		else{
			Repertoire.addMessageerreur("Aucune sequence sélectionnée");
		}
		
	}
		

	public void initialize(){
		if(numerosequence!=0){
			selectedSequence=new SequenceBean();
			selectedSequence.setNumero(numerosequence);
			this.service.initialiseSequenceBean(selectedSequence);
			System.out.println("le numero de sequence est"+  selectedSequence.getNumero());
			if(this.selectedSequence.getNumero()!=0){
				chargerBilan(null);
			}
			else{
				selectedSequence=null;
			}
		}
	}

	@SuppressWarnings("unused")
	private void chargerCirculaire() {  
		pieModel = new PieChartModel();  
    	System.out.println("je suis la et la taille est "+  listeBilansp.size());
        if(listeBilansp!=null){
			for(int i=0;i<listeBilansp.size();i++){
				pieModel.set(listeBilansp.get(i).getLibelleclasse(), listeBilansp.get(i).getTauxreussite()); 
			}
		}  
    }
	
	@SuppressWarnings("unused")
	private void chargerBande() {  
	        categoryModel = new CartesianChartModel();  
	        ChartSeries boys = new ChartSeries();  
	        if(listeBilansp!=null){
				for(int i=0;i<listeBilansp.size();i++){
					boys.set(listeBilansp.get(i).getLibelleclasse(), listeBilansp.get(i).getTauxreussite()); 
				}
			}  
	        categoryModel.addSeries(boys);    
    } 
	
}
