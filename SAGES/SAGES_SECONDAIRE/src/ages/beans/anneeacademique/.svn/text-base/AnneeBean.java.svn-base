package ages.beans.anneeacademique;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.exception.AnneeEnCoursNonDefinieException;
import ages.exception.ChevauchementDateException;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;


@ManagedBean(name="anneeBean")
@ViewScoped
public class AnneeBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String anneeacademique;
	private Date datedebut;	
	private Date datefin;
	private boolean clos;
	
	// declaration et injection du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	public void setService(Service service) {
		this.service = service;
	}
	
	
	public String getAnneeacademique() {
		return anneeacademique;
	}
	public void setAnneeacademique(String anneeacademique) {
		this.anneeacademique = anneeacademique;
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
	
	public boolean isClos() {
		return clos;
	}


	public void setClos(boolean clos) {
		this.clos = clos;
	}


	public String saveAnnee(){
		anneeacademique=Repertoire.extraireAnneeOnly(datedebut)+"-"+Repertoire.extraireAnneeOnly(datefin);
		try {
			this.service.saveAnnee(datedebut,datefin);
		} catch (Exception e) {
			if(e.getCause().getClass().equals(DuplicateKeyException.class)){
				Repertoire.addMessageerreur("Cette année chevauche avec une autre Déja enregistrée");
				
			}
			else
				if(e.getCause().getClass().equals(ChevauchementDateException.class)){
					Repertoire.addMessageerreur("Cette année chevauche avec une autre enregistrée");
				}
				else{
					Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				}
			return "";
		}
		Repertoire.addMessageinfo("Nouvelle année enregistrée");
		return OperationResults.NavListingAnnees;
	}
	
	
	public String modifyAnnee(){
		
		try {
			this.service.modifyAnnee(anneeacademique,datedebut,datefin);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreurElementNonTrouve("Annee");
				}
				else
					if(e.getCause().getClass().equals(ChevauchementDateException.class)){
						Repertoire.addMessageerreur("Cette année chevauche avec une autre enregistrée");
					}
					else{
						Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
					}
			}
			else
				Repertoire.addMessageerreur("Erreur innatendue, veuillez contacter l'administrateur");
		}
		
		return OperationResults.NavAnneeEncours;
	}
	
	public String deleteAnnee(){
		
		try {
			this.service.deleteAnnee(anneeacademique);
		}
		 catch (Exception e) {
				if(e.getCause()!=null){
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreurElementNonTrouve("Annee");
					}
					else
						Repertoire.addMessageerreur("Erreur innatendue, veuillez contacter l'administrateur");
				}
				else
					Repertoire.addMessageerreur("Erreur innatendue, veuillez contacter l'administrateur");
			}
		Repertoire.addMessageinfoSuppressionOK("Annee");
		return OperationResults.NavListingAnnees;
	}
	

	public String navmodifier(){
		return OperationResults.navWithParam("modifierAnnee", "codeannee", this.anneeacademique);
	}
	

	public String navcloturer(){
		return OperationResults.navWithParam("cloturerAnnee", "codeannee", this.anneeacademique);
	}
	
	public void initAnnee(){
		try {
			this.service.initAnnee(this);
		} catch (Exception e) {
			if(e.getCause()!=null)
				if(e.getCause().getClass().equals(AnneeEnCoursNonDefinieException.class))
					Repertoire.addMessageerreurElementNonTrouve("Annee");
				else
					Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
			else
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				
		}
		
	}
	
public String cloture(){
		try {
			this.service.clotureanne(anneeacademique);
		} catch (Exception e) {
			if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreurElementNonTrouve("Annee");
				Repertoire.logError("Annee non trouvée", getClass(), e);
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				Repertoire.logFatal("Erreur Innatendue", getClass(), e);
			}
			return null;
		}
		
		Repertoire.addMessageinfoClotureOK("anneeacademique");
		return "accueil2";
	}
}
