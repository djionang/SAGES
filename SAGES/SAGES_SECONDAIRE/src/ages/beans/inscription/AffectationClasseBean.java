package ages.beans.inscription;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import utils.Service;
import utils.Repertoire;


import ages.beans.GenericBean;

@ManagedBean(name="affectationClasseBean")
@RequestScoped
public class AffectationClasseBean extends GenericBean implements Serializable{

	private static final long serialVersionUID = 1L;
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;

	private List<DossierBean> dossiers;
	
	// code de la classe retenue, dans laquelle on va envoyer les gamins la!!!
	private String  codeClasse;
	
	public AffectationClasseBean() {
		
	}

	public List<DossierBean> getDossiers() {
		if(dossiers==null) dossiers=new ArrayList<DossierBean>();		
		return dossiers;
	}

	public void setDossiers(List<DossierBean> dossiers) {
		this.dossiers = dossiers;
	}

	public String getCodeClasse() {
		return codeClasse;
	}

	public void setCodeClasse(String codeClasse) {
		this.codeClasse = codeClasse;
	}

	public void setService(Service service) {
		this.service = service;
	}

		
	@PostConstruct
	protected void init(){
		if(service==null) Repertoire.logFatal(" Fatal error, service not found", ages.beans.inscription.ListDossierBean.class, new RuntimeException());
		dossiers=this.service.listedossiersAcceptes();
	}
	
	
	/**
	 * Sauvegarde des classes pour les dossier
	 * @return la chaine de navigation pour la suite
	 */
	public String sauvegarderAffectation(){
		//enregistre
		List<String> listeDossiers=new ArrayList<String>();
		List<String> result;
		for(int i=0;i<dossiers.size();i++){
			if(dossiers.get(i).getSelected()){
				listeDossiers.add(dossiers.get(i).getCodedossier());
			}
		}
		
		if(listeDossiers.size()==0){
			Repertoire.addMessageerreur("Aucun étudiant selectionné");
			return "affectationn";
		}

		result=this.service.assignerClasse(listeDossiers,codeClasse);
		
		// si echec(liste de codes transférés vide ou nulle) retour de la page d'erreur
		if((result==null)||result.isEmpty()){
			Repertoire.addMessageerreur("Erreur innatendue survenue lors de l'enregistrement");
			return "affectationn";
		}
		
		for(int i=0;i<result.size();i++){
			Repertoire.addMessageinfo(" Dossier "+result.get(i)+" Transféré avec succes");
		}
		
		return "affectationd";
	}
	
	}
