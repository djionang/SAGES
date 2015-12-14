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
import ages.beans.eleve.EleveBean;
import ages.beans.etablissement.classe.ClasseBean;

@ManagedBean(name="listeProvisoireBean")
@ViewScoped
public class ListeProvisoireBean extends GenericBean implements Serializable{
	
	

	private static final long serialVersionUID = 1L;
	private List<EleveBean> listeEleves;
	private ClasseBean selectedClasse;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
		
		
	public List<EleveBean> getListeEleves() {
		return listeEleves;
	}


	public void setService(Service service) {
		this.service = service;
	}


	public void setListeEleves(List<EleveBean> listeEleves) {
		this.listeEleves = listeEleves;
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
	public void loadEleve(ActionEvent e){
		if(selectedClasse!=null){
			listeEleves=this.service.listerEleveProvisoireClasse(selectedClasse.getCodeClasse());
		}
		else{
			Repertoire.addMessageerreur("Aucune classe sélectionnée");
		}
		
	}
	
	public String imprimerListeProvisoire(){
		
		if(selectedClasse!=null){
			try {
				this.service.imprimerListeProvisoire(selectedClasse.getCodeClasse(),service.getInfosSession().getCodeetablissement());
			} catch (Exception e) {
				Repertoire.addMessageerreur("Erreur survenue lors de l'impression");
				e.printStackTrace();
			}
		}
		else{
			this.service.imprimerListeProvisoire();
		}
		return null;
	}
}
