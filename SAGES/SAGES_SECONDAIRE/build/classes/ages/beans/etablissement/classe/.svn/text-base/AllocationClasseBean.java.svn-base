package ages.beans.etablissement.classe;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import utils.Service;
import utils.Repertoire;

import ages.beans.GenericBean;
import ages.beans.etablissement.classe.ClasseBean;

@ManagedBean(name="allocationClasseBean")
@ViewScoped
public class AllocationClasseBean extends GenericBean implements Serializable{
	
	

	private static final long serialVersionUID = 1L;
	private List<ClasseBean> listeClasses;
	private ClasseBean selectedClasse;
	
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	

	public void setService(Service service) {
		this.service = service;
	}


	public List<ClasseBean> getListeClasses() {
		return listeClasses;
	}


	public void setListeClasses(List<ClasseBean> listeClasses) {
		this.listeClasses = listeClasses;
	}


	public ClasseBean getSelectedClasse() {
		return selectedClasse;
	}


	public void setSelectedClasse(ClasseBean selectedClasse) {
		this.selectedClasse = selectedClasse;
	}

	
	/**
	 * Peuple la liste des élèves de la classe selectionné et rend le paneau d'affichage visible
	 * @param e 
	 */
	public void loadAllocation(ActionEvent e){
		if(selectedClasse!=null){
		}
		else{
			Repertoire.addMessageerreur("Aucune classe sélectionnée");
		}
		
	}
}
