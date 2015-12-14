package ages.beans.inscription;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.exception.ClassToBeanCopyException;
import ages.exception.JPAException;

@ManagedBean(name="listDossierBean")
@ViewScoped
public class ListDossierBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<DossierBean> dossiers;
	
	private List<DossierBean> auxdossiers;
	
	// Utile dans le cas de la gestion individuelle d'un dossier
	private DossierBean selectedDossier;
	
		
	// code de la classe retenue, dans laquelle on va envoyer les gamins la!!!
	private String  codeClasse;
	
	private String niveauDemande;
	

	public List<DossierBean> getDossiers() {
//		if(dossiers==null) dossiers=new ArrayList<DossierBean>();
		//recupere les dossiers dans la db
		
		return dossiers;
	}

	public void setDossiers(List<DossierBean> dossiers) {
		this.dossiers = dossiers;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	public DossierBean getSelectedDossier() {
		return selectedDossier;
	}

	public void setSelectedDossier(DossierBean selectedDossier) {
		this.selectedDossier = selectedDossier;
	}
	
	public String getCodeClasse() {
		return codeClasse;
	}

	public void setCodeClasse(String codeClasse) {
		this.codeClasse = codeClasse;
	}

	

	public String getNiveauDemande() {
		return niveauDemande;
	}

	public void setNiveauDemande(String niveauDemande) {
		this.niveauDemande = niveauDemande;
	}

	public void supprimerDossier(ActionEvent event){
		if(this.selectedDossier!=null){
			this.service.deletedossier(this.selectedDossier.getCodedossier());
			selectedDossier=null;
		}
		
	}
	
	public String sauvegarderAffectation(){
		return "";
	}
	
	
	public List<DossierBean> getAuxdossiers() {
		return auxdossiers;
	}

	public void setAuxdossiers(List<DossierBean> auxdossiers) {
		this.auxdossiers = auxdossiers;
	}

	/**
	 * initialise mon bean avec la liste des dossiers enregistrés
	 */
	@PostConstruct
	public void init(){
		/*Repertoire.logDebug("Initialisation des dossiers", ages.beans.inscription.ListDossierBean.class);
		
		if(service==null) Repertoire.logFatal(" Fatal error, service not found", ages.beans.inscription.ListDossierBean.class, new RuntimeException());*/
		setDossiers(this.service.listedossiers());
		
	}
	
	public void chargerDossier(ActionEvent event){
		try {
			auxdossiers=this.service.listedossiers();
			
			dossiers=new ArrayList<DossierBean>();

			System.out.println("le code niveau est  "+   niveauDemande);
			if(niveauDemande!=null && !niveauDemande.isEmpty()){
				
				for(int i=0;i<auxdossiers.size();i++){
					if(auxdossiers.get(i).getNiveauDemande().compareTo(niveauDemande)==0){
						dossiers.add(auxdossiers.get(i));
					}
				}
			}
			else{
				dossiers=auxdossiers;
			}
		} 
		catch (Exception e) {
			if(e.getCause().getClass().equals(JPAException.class)){
				Repertoire.addMessageerreur("Erreur grave lors de la recherche des dossiers");
				
			}
			else
				if(e.getCause().getClass().equals(ClassToBeanCopyException.class)){
					Repertoire.addMessageerreur("Erreur de cohérence lors de la recherche des dossieres");
				}
				else
					Repertoire.addMessageerreur("Erreur Innatendue, Veuillez contacter l'administrateur");
			
		}
	}
	
	public String imprimerCandidatures(){
		try{
			this.service.imprimerAllCandidatures();
		}
		catch(Exception e){
			Repertoire.addMessageerreur("Erreur d'inpression de la liste");
			e.printStackTrace();
		}		
			
		return null;
	}
	
	public String imprimerCandidaturesAcceptees(){
		try{
			this.service.imprimerCandidaturesAcceptees(niveauDemande);
		}
		catch(Exception e){
			Repertoire.addMessageerreur("Erreur d'impression de la liste");
			e.printStackTrace();
		}		
			
		return null;
	}
	
	public String imprimerCandidaturesRejetees(){
		try{
			this.service.imprimerCandidaturesRejetees(niveauDemande);
		}
		catch(Exception e){
			Repertoire.addMessageerreur("Erreur d'inpression de la liste");
			e.printStackTrace();
		}		
			
		return null;
	}
	
	public String imprimerCandidaturesAttente(){
		
		try{
			this.service.imprimerCandidaturesAttente(niveauDemande);
		}
		catch(Exception e){
			Repertoire.addMessageerreur("Erreur d'inpression de la liste");
			e.printStackTrace();
		}		
			
		return null;
	}
	
	
	
	public void initacceptees(){
		dossiers=this.service.listedossiersAcceptes();
	}
	
	public void initrejetees(){
		dossiers=this.service.listedossiersRejetes();
	}
	
	public void initattente(){
		dossiers=this.service.listedossiersAttente();
	}
	
	public void loadDossiersAcceptesNiveau(){
		if(niveauDemande==null || niveauDemande.isEmpty())
			dossiers=this.service.listedossiersAcceptes();
		else
			dossiers=this.service.listedossiersAcceptes(niveauDemande);
	}
	
	public void loadDossiersRejetesNiveau(){
		if(niveauDemande==null || niveauDemande.isEmpty())
			dossiers=this.service.listedossiersRejetes();
		else
			dossiers=this.service.listedossiersRejetes(niveauDemande);
		Repertoire.addMessageerreur("codeniveau="+niveauDemande+" taille="+dossiers.size());
		
	}
	
	public void loadDossiersAttenteNiveau(){
		if(niveauDemande==null || niveauDemande.isEmpty())
			dossiers=this.service.listedossiersAttente();
		else
			dossiers=this.service.listedossiersAttente(niveauDemande);
		
	}
}
