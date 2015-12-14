package ages.beans.etablissement.evaluation;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.beans.enseignement.MatiereBean;
import ages.exception.CoursNonDefiniException;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;
import ages.exception.PourcentageEvaluationExedantException;

@ManagedBean(name="evaluationBean")
@ViewScoped
public class EvaluationBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
		
	
	private int codeevaluation;
	private String libelle;
	
	private String codematiere;
	private String codeclasse;
	private String libellecours;
	private int codesequence;	
	private int numerosequence;
	private int coefficient;
	private String typeevaluation;
	
	private Date datedebut;
	
	private Date datefin;
	
	private List<MatiereBean> listeMatieres;
	

	public EvaluationBean() {
	}
	


	public void setService(Service service) {
		this.service = service;
	}


	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	

	/**
	 * @return the coefficient
	 */
	public int getCoefficient() {
		return coefficient;
	}



	/**
	 * @param coefficient the coefficient to set
	 */
	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}



	/**
	 * @return the codeevaluation
	 */
	public int getCodeevaluation() {
		return codeevaluation;
	}



	/**
	 * @param codeevaluation the codeevaluation to set
	 */
	public void setCodeevaluation(int codeevaluation) {
		this.codeevaluation = codeevaluation;
	}


	/**
	 * @return the codesequence
	 */
	public int getCodesequence() {
		return codesequence;
	}



	/**
	 * @param codesequence the codesequence to set
	 */
	public void setCodesequence(int codesequence) {
		this.codesequence = codesequence;
	}



	/**
	 * @return the typeevaluation
	 */
	public String getTypeevaluation() {
		return typeevaluation;
	}



	/**
	 * @param typeevaluation the typeevaluation to set
	 */
	public void setTypeevaluation(String typeevaluation) {
		this.typeevaluation = typeevaluation;
	}



	/**
	 * @return the datedebut
	 */
	public Date getDatedebut() {
		if(datedebut==null)
			datedebut=new Date();
		return datedebut;
	}



	/**
	 * @param datedebut the datedebut to set
	 */
	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}



	/**
	 * @return the datefin
	 */
	public Date getDatefin() {
		if(datefin==null)
			datefin=new Date();
		return datefin;
	}



	/**
	 * @param datefin the datefin to set
	 */
	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}



	/**
	 * @return the libellecours
	 */
	public String getLibellecours() {
		return libellecours;
	}



	/**
	 * @param libellecours the libellecours to set
	 */
	public void setLibellecours(String libellecours) {
		this.libellecours = libellecours;
	}



	/**
	 * @return the numerosequence
	 */
	public int getNumerosequence() {
		return numerosequence;
	}



	/**
	 * @param numerosequence the numerosequence to set
	 */
	public void setNumerosequence(int numerosequence) {
		this.numerosequence = numerosequence;
	}



	/**
	 * @return the codematiere
	 */
	public String getCodematiere() {
		return codematiere;
	}



	/**
	 * @param codematiere the codematiere to set
	 */
	public void setCodematiere(String codematiere) {
		this.codematiere = codematiere;
	}



	/**
	 * @return the codeclasse
	 */
	public String getCodeclasse() {
		return codeclasse;
	}



	/**
	 * @param codeclasse the codeclasse to set
	 */
	public void setCodeclasse(String codeclasse) {
		this.codeclasse = codeclasse;
	}



	public List<MatiereBean> getListeMatieres() {
		return listeMatieres;
	}



	public void setListeMatieres(List<MatiereBean> listeMatieres) {
		this.listeMatieres = listeMatieres;
	}



	/**
	 * Enregistre la classe dans la base
	 * @return la chaine de navigation pur les cas erreur et succes
	 * @throws ElementNOtFoundException 
	 */
	public String saveEvaluation(){
		try {
			codeevaluation=this.service.saveEvaluation(libelle,typeevaluation, codematiere,codeclasse,codesequence,datedebut,datefin);
			
		} 
		catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(DuplicateKeyException.class)){
					Repertoire.addMessageerreur("Evaluation déja enregistrée");
					Repertoire.logError("Evaluation déja enregistrée", getClass(), e);
				}
				else
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Annee academique non trouvée");
						Repertoire.logError("Annee academique non trouvée", getClass(), e);
					}
					else{
						if(e.getCause().getClass().equals(PourcentageEvaluationExedantException.class)){
							Repertoire.addMessageerreur("Total des évaluations programmées exedant 100%");
							Repertoire.logError("Total des évaluations programmées exedant 100%", getClass(), e);
						}
						else{
							if(e.getCause().getClass().equals(CoursNonDefiniException.class)){
								Repertoire.addMessageerreur("Cours Non défini pour la matiere et classe sélectionnés");
								Repertoire.logError("cours non défini", getClass(), e);
							}
							else{
								Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
								Repertoire.logError("Erreur inconnue", getClass(), e);
							}
						}
						
					}
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				Repertoire.logError("Erreur innatendue", getClass(), e);
				
			}
			return null;
		}
		Repertoire.addMessageinfoEnregistrementOK("Evaluation");
		return OperationResults.navWithParam("visualisation", "codeevaluation", String.valueOf(codeevaluation));
	}
	
	
	/**
	 * Enregistre la classe dans la base
	 * @return la chaine de navigation pur les cas erreur et succes
	 * @throws ElementNOtFoundException 
	 */
	public String saveSetEvaluation(){
		for(int i=0;i<listeMatieres.size();i++){
			
		try {
			
			codeevaluation=this.service.saveEvaluation(listeMatieres.get(i).getLibelle(),typeevaluation, listeMatieres.get(i).getCodematiere(),codeclasse,codesequence,datedebut,datefin);
			
		} 
		catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(DuplicateKeyException.class)){
					Repertoire.addMessageerreur("Evaluation déja enregistrée");
					Repertoire.logError("Evaluation déja enregistrée", getClass(), e);
				}
				else
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Annee academique non trouvée");
						Repertoire.logError("Annee academique non trouvée", getClass(), e);
					}
					else{
						if(e.getCause().getClass().equals(PourcentageEvaluationExedantException.class)){
							Repertoire.addMessageerreur("Total des évaluations programmées exedant 100%");
							Repertoire.logError("Total des évaluations programmées exedant 100%", getClass(), e);
						}
						else{
							if(e.getCause().getClass().equals(CoursNonDefiniException.class)){
								Repertoire.addMessageerreur("Cours Non défini pour la matiere et classe sélectionnés");
								Repertoire.logError("cours non défini", getClass(), e);
							}
							else{
								Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
								Repertoire.logError("Erreur inconnue", getClass(), e);
							}
						}
						
					}
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				Repertoire.logError("Erreur innatendue", getClass(), e);
				
			}
			return null;
		}
		}
		Repertoire.addMessageinfoEnregistrementOK("Evaluation");
		return OperationResults.navWithParam("listing", null, null);
	}
	
	
	
	public String modifierevaluation(){
		try {
			this.service.modifierEvaluation(codeevaluation, libelle,typeevaluation, codematiere,codeclasse,codesequence,datedebut,datefin);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Evaluation Non trouvée");
					Repertoire.logError("Evaluation ou séquence non trouvée", getClass(), e);
				}
				
				else{
					if(e.getCause().getClass().equals(CoursNonDefiniException.class)){
						Repertoire.addMessageerreur("Cours Non défini pour la matiere et classe sélectionnés");
						Repertoire.logError("cours non défini", getClass(), e);
					}
					else{
						if(e.getCause().getClass().equals(PourcentageEvaluationExedantException.class)){
							Repertoire.addMessageerreur("Total des évaluations programmées exedant 100%");
							Repertoire.logError("Total des évaluations programmées exedant 100%", getClass(), e);
						}
						else{
							Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
							Repertoire.logError("Erreur inconnue", getClass(), e);
						}
						
					}
					
				}
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				e.printStackTrace();
			}
			return null;
		}
		Repertoire.addMessageinfo("Mise à jour sauvegardée");
		return OperationResults.navWithParam("visualisation", "codeevaluation", String.valueOf(this.codeevaluation));
	}
	
	public String supprimerevaluation(){
		if (this.codeevaluation!=0){
			try {
				this.service.deleteEvaluation(this.codeevaluation);
			} catch (Exception e) {
				if(e.getCause()!=null){
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Evaluation Non trouvée");
						Repertoire.logError("Evaluation non trouvée", getClass(), e);
					}
					
					else{
						Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
						Repertoire.logError("Erreur innatendue", getClass(), e);
					}
				}
				else{
					Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
					e.printStackTrace();
				}
				return null;
			}
			
			Repertoire.addMessageinfo("Suppression effectuée");
			return OperationResults.navWithParam("listing", null, null);
		
		}
		Repertoire.addMessageerreur("Aucune classe sélectionnée");
		
		return null;
	}
	
	public void initialize(){
		
		if(this.codeevaluation!=0)
			this.service.initialiseEvaluationBean(this);
	}
	
	
	/**
	 * Charge les matieres d'une classe pour affichage dans le combo des matieres
	 * sur la page de creation des evaluations
	 */
	public void loadMatieresClasse(){
		if(codeclasse!=null && !codeclasse.isEmpty()){
			listeMatieres=this.service.listematieresClasse(codeclasse);
			if(listeMatieres!=null && ! listeMatieres.isEmpty()){
				libelle=this.service.rechercherlibelleCours(listeMatieres.get(0).getCodematiere(),codeclasse);
			}
		}
		
	}
	
	
	/**
	 * Charge les matieres d'une classe pour affichage dans le combo des matieres
	 * sur la page de creation des evaluations
	 */
	public void loadMatieresClasseN(){
		if(codeclasse!=null && !codeclasse.isEmpty()){
			listeMatieres=this.service.listematieresClasseN(codeclasse);
			if(listeMatieres!=null && ! listeMatieres.isEmpty()){
				libelle=this.service.rechercherlibelleCours(listeMatieres.get(0).getCodematiere(),codeclasse);
			}
		}
		
	}
	
	/**
	 * Initialise le libellé d'une évaluation
	 * en fonction de la sequence, du libelle de la matiere choisie.
	 */
	public void initLibelleEvaluation(){
		if(codematiere!=null && !codematiere.isEmpty()){
			libelle=this.service.rechercherlibelleCours(codematiere,codeclasse);			
		}
	}
}
