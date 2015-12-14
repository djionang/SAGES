package ages.beans.eleve;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import utils.Repertoire;
import utils.Service;

import ages.beans.GenericBean;

@ManagedBean(name="rechercheEleveBean")
@ViewScoped
public class RechercheEleveBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	private String motcle;
	private String filtre;
	private String codeclasse;
	private List<EleveBean> listeEleves;
	private EleveBean selectedEleve;
	
	private EleveDataModel modeleEleve;

	public List<EleveBean> getListeEleves() {
		return listeEleves;
	}

	public void setListeEleves(List<EleveBean> listeEleves) {
		this.listeEleves = listeEleves;
	}
	
	public EleveBean getSelectedEleve() {
		return selectedEleve;
	}

	public void setSelectedEleve(EleveBean selectedEleve) {
		this.selectedEleve = selectedEleve;
	}
	
	public EleveDataModel getModeleEleve() {
		return modeleEleve;
	}

	public void setModeleEleve(EleveDataModel modeleEleve) {
		this.modeleEleve = modeleEleve;
	}

	public String getMotcle() {
		return motcle;
	}

	public void setMotcle(String motcle) {
		this.motcle = motcle;
	}

	public String getFiltre() {
		return filtre;
	}

	public void setFiltre(String filtre) {
		this.filtre = filtre;
	}

	public String getCodeclasse() {
		return codeclasse;
	}

	public void setCodeclasse(String codeclasse) {
		this.codeclasse = codeclasse;
	}

	public void setService(Service service) {
		this.service = service;
	}

	
	public void chercherEleve(ActionEvent e){
		// si filtre est vide et classe vide rechercher dans tout l'etab
		if(filtre.isEmpty()){
			if(codeclasse.isEmpty()){
				listeEleves=this.service.rechercherEleves(motcle);
			}
			else{
				listeEleves=this.service.rechercherElevesClasse(motcle,codeclasse);
			}
		}
		else{
			if(codeclasse.isEmpty()){
				listeEleves=this.service.rechercherElevesClasseFiltre(motcle,filtre,codeclasse);
			}
			else{
				listeEleves=this.service.rechercherElevesFiltre(motcle,filtre);
			}
		}		
		modeleEleve=new EleveDataModel(listeEleves);
	}

	public String navfinances(){
		Repertoire.addMessageerreur("inside");
		if(selectedEleve==null){
			Repertoire.addMessageerreur("Aucun étudiant sélectionné");
			return null;
		}
			
		return "inscriptionfraisindiv?faces-redirect=true&includeViewParams=true&matricule="+this.selectedEleve.getMatricule();
	}
	
	public String navversement(){
		if(selectedEleve==null){
			Repertoire.addMessageerreur("Aucun étudiant sélectionné");
			return null;
		}	
		//return "inscriptionfrais?faces-redirect=true&includeViewParams=true&matricule="+this.selectedEleve.getMatricule();
		return "inscriptionfrais";
		
	}
}
