package ages.beans.inscription;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.beans.etablissement.classe.ClasseBean;

/**
 * Bean de listing des tranches versés par une eleve, ou a verser
 * @author Brilswear
 *
 */

@ManagedBean(name="listeTrancheBean")
@ViewScoped
public class ListeTrancheBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	// classe sélectionnée
	private ClasseBean selectedClasse;
	
	// Liste des tranches a verser pour une classe
	private List<TrancheBean> listeTranchesp;
	
	//modele d'affichage pour la liste des tranches a verser pour une classe
	private TrancheDataModel modeleTranche;
	
	private String codeclasse;
	
	// Tranche sélectionné, pour la gestion (Modificatio ou suppression)
	private TrancheBean selectedTranche;
	
	private float total;
	
	public ClasseBean getSelectedClasse() {
		return selectedClasse;
	}

	public void setSelectedClasse(ClasseBean selectedClasse) {
		this.selectedClasse = selectedClasse;
	}


	public List<TrancheBean> getListeTranchesp() {
		return listeTranchesp;
	}


	public void setListeTranchesp(List<TrancheBean> listeTranchesp) {
		this.listeTranchesp = listeTranchesp;
	}


	public TrancheDataModel getModeleTranche() {
		return modeleTranche;
	}


	public void setModeleTranche(TrancheDataModel modeleTranche) {
		this.modeleTranche = modeleTranche;
	}

	public TrancheBean getSelectedTranche() {
		return selectedTranche;
	}


	public void setSelectedTranche(TrancheBean selectedTranche) {
		this.selectedTranche = selectedTranche;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getCodeclasse() {
		return codeclasse;
	}

	public void setCodeclasse(String codeclasse) {
		this.codeclasse = codeclasse;
	}

	public float getTotal() {
		if(listeTranchesp!=null){
			total=0;
			for(int i=0;i<listeTranchesp.size();i++){
				total+=listeTranchesp.get(i).getMontant();
			}
		}
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public void chargerTranches(ActionEvent e){
		if((selectedClasse!=null)&&(selectedClasse.getCodeClasse()!=null)){
			listeTranchesp=this.service.listeTranchesClasse(selectedClasse.getCodeClasse());
			
			modeleTranche=new TrancheDataModel(listeTranchesp);
		}
		else{
			Repertoire.addMessageerreur("Aucune filiere sélectionnée");
		}
		
	}
		

	public void initialize(){
		if(codeclasse!=null){
			selectedClasse=new ClasseBean();
			selectedClasse.setCodeClasse(codeclasse);
			this.service.initialiseClasseBean(selectedClasse);
			if(this.selectedClasse.getLibelle()!=null){
				chargerTranches(null);
			}
			else{
				selectedClasse=null;
			}
		}
	}
	
	public String navnouvelleTranche(){
		if(selectedClasse==null){
			Repertoire.addMessageerreur("Aucune filiere selectionnée");
			return "";
		}
		else{			
			return"enregistrerTranche?faces-redirect=true&includeViewParams=true&codeclasse="+this.selectedClasse.getCodeClasse();
		}
	}
	
	public String navmodifierTranche(){
		if(selectedClasse==null){
			Repertoire.addMessageerreur("Aucune filiere selectionnée");
			return "";
		}
		else{
			if(selectedTranche==null){
				Repertoire.addMessageerreur("Aucune Tranche selectionnée");
				return "";
			}
			else
				return"modifierTranche?faces-redirect=true&includeViewParams=true&codeclasse="+this.selectedClasse.getCodeClasse()+"&idtranche="+this.selectedTranche.getId();
		}
	}

	public String navsupprimerTranche(){
		if(selectedClasse==null){
			Repertoire.addMessageerreur("Aucune filiere selectionnée");
			return "";
		}
		else{
			if(selectedTranche==null){
				Repertoire.addMessageerreur("Aucune Tranche selectionnée");
				return "";
			}
			else
				return"supprimerTranche?faces-redirect=true&includeViewParams=true&codeclasse="+this.selectedClasse.getCodeClasse()+"&idtranche="+this.selectedTranche.getId();
		}
	}

	
	
}
