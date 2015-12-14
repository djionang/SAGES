package ages.beans.inscription;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import net.sf.jasperreports.engine.JRException;

import org.primefaces.model.chart.PieChartModel;

import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.exception.ClassToBeanCopyException;
import ages.exception.JPAException;


/**
 * Listing des tranches payées pour une période
 * @author berlin et Brilswear
 *
 */

@ManagedBean(name="ListeVersementBean")
@ViewScoped
public class ListeVersementBean extends GenericBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
		// declaration du service
		@ManagedProperty(value="#{service}")
		protected Service service;
		//Liste de versements enregistrés pour un élève
		private List<VersementBean> listeVers;
		
		private List<VersementBean> listeVerss;
		
		private List<VersementBean> listeVersss;
		
		private List<VersementBean> listeVersement;
		
		private List<VersementBean> auxVers;
		
		private List<VersementBean> rechVers;
		
		
		private Date datedebut;
		
		private Date datefin;
		
		private float totalclasse;
		
		private float total;
		
		private String classe;
		
		//private VersementDataModel modelVersement;
		
		private VersementBean selectedversement;
		
		private PieChartModel pieModel;  
		
		
		public List<VersementBean> getListeVers() {
			return listeVers;
		}

		public void setListeVers(List<VersementBean> listeVers) {
			this.listeVers = listeVers;
		}
		
		

		public List<VersementBean> getListeVersss() {
			return listeVersss;
		}

		public void setListeVersss(List<VersementBean> listeVersss) {
			this.listeVersss = listeVersss;
		}

		public void setService(Service service) {
			this.service = service;
		}

		
		public Date getDatedebut() {
			return datedebut;
		}

		public void setDatedebut(Date datedebut) {
			this.datedebut = datedebut;
		}

		public Date getDatefin() {
			return datefin;
		}

		public void setDatefin(Date datefin) {
			this.datefin = datefin;
		}

		public float getTotal() {
			
			return total;
		}

		public void setTotal(float total) {
			this.total = total;
		}


		public String getClasse() {
			return classe;
		}

		public void setClasse(String classe) {
			this.classe = classe;
		}
		
		
		
		

		public float getTotalclasse() {
			if(listeVersss!=null){
				totalclasse=0;
				for(int i=0;i<listeVersss.size();i++){
					totalclasse+=listeVersss.get(i).getMontant();
				}
			}
			return totalclasse;
		}
		
		
		public List<VersementBean> getRechVers() {
			
			return rechVers;
		}

		public void setRechVers(List<VersementBean> rechVers) {
			this.rechVers = rechVers;
		}

		public float classetotal(String classe){
			List<VersementBean> rechVers;
			try {
				rechVers = this.service.listeVersementsClasse(classe);
				if(rechVers!=null){
					totalclasse=0;
					for(int i=0;i<rechVers.size();i++){
						totalclasse+=rechVers.get(i).getMontant();
					}
				}
				return totalclasse;
			} catch (JPAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			} catch (ClassToBeanCopyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
			
			
		}

		public void setTotalclasse(float totalclasse) {
			this.totalclasse = totalclasse;
		}
		
		public PieChartModel getPieModel() {  
	    	pieModel = new PieChartModel();  
	    	System.out.println("je suis la et la taille est "+  listeVers.size());
	        if(listeVerss!=null){
				for(int i=0;i<listeVerss.size();i++){
					pieModel.set(listeVerss.get(i).getLibelleclasse(), classetotal(listeVerss.get(i).getClasse())); 
				}
			} 
	        return pieModel;  
	    }

		public List<VersementBean> getListeVerss() {
			return listeVerss;
		}

		public void setListeVerss(List<VersementBean> listeVerss) {
			this.listeVerss = listeVerss;
		}

		public VersementBean getSelectedversement() {
			return selectedversement;
		}

		public void setSelectedversement(VersementBean selectedversement) {
			this.selectedversement = selectedversement;
		}

		public List<VersementBean> getListeVersement() {
			return listeVersement;
		}

		public void setListeVersement(List<VersementBean> listeVersement) {
			this.listeVersement = listeVersement;
		}

		@PostConstruct
		public void initVersementsEnregistres(){
			try {
				
				setListeVers(this.service.listeVersementsEnregistres(datedebut,datefin));
				setListeVerss(this.service.listeVersementsEnregistres(datedebut,datefin));
				total=0;
				
				for(int i=0;i<listeVerss.size();i++){
					total+=listeVerss.get(i).getMontant();
				}
			} 
			catch (Exception e) {
				if(e.getCause().getClass().equals(JPAException.class)){
					Repertoire.addMessageerreur("Erreur grave lors de la recherche des versements enregistrés");
					
				}
				else
					if(e.getCause().getClass().equals(ClassToBeanCopyException.class)){
						Repertoire.addMessageerreur("Erreur de cohérence lors de la recherche des versements enregistrés");
					}
					else
						Repertoire.addMessageerreur("Erreur Innatendue, Veuillez contacter l'administrateur");
				
				
			}
		}

		
		public void chargerVersements(ActionEvent event){
			try {
				
				auxVers=this.service.listeVersementsEnregistres(datedebut,datefin);
				
				listeVersss=new ArrayList<VersementBean>();
				
				total=0;
				
				for(int i=0;i<auxVers.size();i++){
					total+=auxVers.get(i).getMontant();
				}
				
				if(classe!=null &&!classe.isEmpty()){
					
					for(int i=0;i<auxVers.size();i++){
						if(auxVers.get(i).getClasse().compareTo(classe)==0){
							listeVersss.add(auxVers.get(i));
						}
					}
				}
				else{
					listeVersss=auxVers;
				}
				listeVersement=listeVersss;
			} 
			catch (Exception e) {
				e.printStackTrace();
				if(e.getCause().getClass().equals(JPAException.class)){
					Repertoire.addMessageerreur("Erreur grave lors de la recherche des versements enregistrés");
					
				}
				else
					if(e.getCause().getClass().equals(ClassToBeanCopyException.class)){
						Repertoire.addMessageerreur("Erreur de cohérence lors de la recherche des versements enregistrés");
					}
					else
						Repertoire.addMessageerreur("Erreur Innatendue, Veuillez contacter l'administrateur");
				
			}
		}
		
		public String imprimerVersements(){
			try {
				this.service.imprimerVersements(datedebut,datefin);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		

		
}
