package ages.beans.statistique;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.model.chart.PieChartModel;

import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.beans.anneeacademique.TrimestreBean;

/**
 * Bean de listing des tranches versés par une eleve, ou a verser
 * @author Brilswear
 *
 */

@ManagedBean(name="listeBilanTrimBean")
@ViewScoped
public class ListeBilanTrimBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	// trimestre sélectionnée
	private TrimestreBean selectedTrimestre;
	
	// Liste des bilan trimestre pour une classe
	private List<BilanTrimBean> listeBilansp;
	
	//modele d'affichage pour la liste des trimestres pour une classe
	private BilanTrimDataModel modeleBilan;
	
	private int numerotrimestre;
	
	private float total;
	
	private float totalmoyenne;
	
	private int totaleffectif;

	private PieChartModel pieModel;  
	  
    public ListeBilanTrimBean() {  
    	// createPieModel();  
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
	
	public int getNumerotrimestre() {
		return numerotrimestre;
	}

	public void setNumerotrimestre(int numerotrimestre) {
		this.numerotrimestre = numerotrimestre;
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

	

	

	public BilanTrimDataModel getModeleBilan() {
		return modeleBilan;
	}

	public void setModeleBilan(BilanTrimDataModel modeleBilan) {
		this.modeleBilan = modeleBilan;
	}

	public TrimestreBean getSelectedTrimestre() {
		return selectedTrimestre;
	}

	public void setSelectedTrimestre(TrimestreBean selectedTrimestre) {
		this.selectedTrimestre = selectedTrimestre;
	}

	public void chargerBilanTrim(ActionEvent e){
		if((selectedTrimestre!=null)&&(selectedTrimestre.getNumero()!=0)){
			listeBilansp=this.service.listeBilanTrim(selectedTrimestre.getNumero());
			
			modeleBilan=new BilanTrimDataModel(listeBilansp);
		}
		else{
			Repertoire.addMessageerreur("Aucun trimestre sélectionné");
		}
		
	}
		

	public void initialize(){
		if(numerotrimestre!=0){
			selectedTrimestre=new TrimestreBean();
			selectedTrimestre.setNumero(numerotrimestre);
			this.service.initialiseTrimestreBean(selectedTrimestre);
			System.out.println("le numero de trimestre est"+  selectedTrimestre.getNumero());
			if(this.selectedTrimestre.getNumero()!=0){
				chargerBilanTrim(null);
			}
			else{
				selectedTrimestre=null;
			}
		}
	}
	
	public List<BilanTrimBean> getListeBilansp() {
		return listeBilansp;
	}

	public void setListeBilansp(List<BilanTrimBean> listeBilansp) {
		this.listeBilansp = listeBilansp;
	}
	
	
}
