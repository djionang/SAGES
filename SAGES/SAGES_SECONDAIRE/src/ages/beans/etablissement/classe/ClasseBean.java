package ages.beans.etablissement.classe;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.beans.eleve.EleveBean;
import ages.beans.enseignant.EnsClasseBean;
import ages.exception.AnneeEnCoursNonDefinieException;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;

@ManagedBean(name="classeBean")
@ViewScoped
public class ClasseBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
		
	
	private String codeClasse;
	private String libelle;
	
	private String codeEnseignantTitulaire;	
	private String nomenseignantTitulaire;
	
	private String delegue1; 
	private String delegue2;
	
	private String nouveaudelegue1; 
	private String nouveaudelegue2;
	
	private int contenanceActuelle;
	private int effectifMax;
	
	private float sommeattendue;
	
	private float sommepercue;
	
	
	private String salleDefaut;
	
	private String option;
	
	private String niveau;
	
	private List<EleveBean> eleves;
	
	private List<EnsClasseBean> enseignants;
	
	

	public void setService(Service service) {
		this.service = service;
	}

	public ClasseBean() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCodeClasse() {
		return codeClasse;
	}
	public void setCodeClasse(String codeClasse) {
		this.codeClasse = codeClasse;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public int getEffectifMax() {
		return effectifMax;
	}
	public void setEffectifMax(int effectifMax) {
		this.effectifMax = effectifMax;
	}
	public String getCodeEnseignantTitulaire() {
		return codeEnseignantTitulaire;
	}
	public void setCodeEnseignantTitulaire(String codeEnseignant) {
		this.codeEnseignantTitulaire = codeEnseignant;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getDelegue1() {
		return delegue1;
	}
	public void setDelegue1(String delegue1) {
		this.delegue1 = delegue1;
	}
	public String getDelegue2() {
		return delegue2;
	}
	public void setDelegue2(String delegue2) {
		this.delegue2 = delegue2;
	}
	public int getContenanceActuelle() {
		return contenanceActuelle;
	}
	public void setContenanceActuelle(int contenanceActuelle) {
		this.contenanceActuelle = contenanceActuelle;
	}
	
	
	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getSalleDefaut() {
		return salleDefaut;
	}

	public void setSalleDefaut(String salleDefaut) {
		this.salleDefaut = salleDefaut;
	}

	public String getNouveaudelegue1() {
		return nouveaudelegue1;
	}

	public void setNouveaudelegue1(String nouveaudelegue1) {
		this.nouveaudelegue1 = nouveaudelegue1;
	}

	public String getNouveaudelegue2() {
		return nouveaudelegue2;
	}

	public void setNouveaudelegue2(String nouveaudelegue2) {
		this.nouveaudelegue2 = nouveaudelegue2;
	}

	public List<EleveBean> getEleves() {
		return eleves;
	}

	public void setEleves(List<EleveBean> eleves) {
		this.eleves = eleves;
	}

	public List<EnsClasseBean> getEnseignants() {
		return enseignants;
	}

	public void setEnseignants(List<EnsClasseBean> enseignants) {
		this.enseignants = enseignants;
	}

	public String getNomenseignantTitulaire() {
		return nomenseignantTitulaire;
	}

	public void setNomenseignantTitulaire(String nomenseignantTitulaire) {
		this.nomenseignantTitulaire = nomenseignantTitulaire;
	}
	
	public float getSommepercue() {
		return sommepercue;
	}
	public void setSommepercue(float sommepercue) {
		this.sommepercue = sommepercue;
	}
	public float getSommeattendue() {
		return sommeattendue;
	}
	public void setSommeattendue(float sommeattendue) {
		this.sommeattendue = sommeattendue;
	}

	/**
	 * Enregistre la classe dans la base
	 * @return la chaine de navigation pur les cas erreur et succes
	 * @throws ElementNOtFoundException 
	 */
	public String saveclasse(){
		try {
			this.service.saveClasse(codeClasse, effectifMax, libelle,option,salleDefaut);
			Repertoire.addMessageinfo("Classe enregistrée");
		} 
		catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(DuplicateKeyException.class)){
					Repertoire.addMessageerreur("Classe "+codeClasse+" déja enregistrée");
					e.printStackTrace();
					return OperationResults.FAILURE;
				}
				else
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur(" salle de reception non définie");
						e.printStackTrace();
						return OperationResults.FAILURE;
					}
					else{
						if(e.getCause().getClass().equals(AnneeEnCoursNonDefinieException.class)){
							Repertoire.addMessageerreur("Annee academique non définie");
							e.printStackTrace();
							return OperationResults.FAILURE;
						}
						else{
							Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
							e.printStackTrace();
							return OperationResults.FAILURE;
						}
					}
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				e.printStackTrace();
				return OperationResults.FAILURE;
			}
			
		}
		Repertoire.addMessageinfoEnregistrementOK("Classe");
		return OperationResults.SUCCESS;
	}
	
	public String modifierclasse(){
		try {
			this.service.modifierClasse(codeClasse, effectifMax, libelle,option,salleDefaut);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Classe "+codeClasse+" Non trouvée");
					e.printStackTrace();
					return OperationResults.FAILURE;
				}
				
				else{
					Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
					e.printStackTrace();
					return OperationResults.FAILURE;
				}
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				e.printStackTrace();
				return OperationResults.FAILURE;
			}
			
		}
		Repertoire.addMessageinfo("Mise à jour sauvegardée");
		return OperationResults.navWithParam("visualisation", "codeclasse", this.codeClasse);
	}
	
	public String supprimerclasse(){
		if (this.codeClasse!=null){
			try {
				this.service.deleteClasse(this.codeClasse);
			} catch (Exception e) {
				if(e.getCause()!=null){
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Classe "+codeClasse+" Non trouvée");
						e.printStackTrace();
						return OperationResults.FAILURE;
					}
					
					else{
						Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
						e.printStackTrace();
						return OperationResults.FAILURE;
					}
				}
				else{
					Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
					e.printStackTrace();
					return OperationResults.FAILURE;
				}
				
			}
			
			Repertoire.addMessageinfo("Suppression effectuée");
			return OperationResults.navWithParam("listing", null, null);
		
		}
		Repertoire.addMessageerreur("Aucune classe sélectionnée");
		
		return null;
	}
	
	/**
	 * Modification des responsables d'une classe (en l'occurence les délégués et le prof principal)
	 * @return
	 */
	public String modifierresponsables(){
		try {
			this.service.modifierDelegues(codeClasse, nouveaudelegue1, nouveaudelegue2,codeEnseignantTitulaire);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Classe "+codeClasse+" Non trouvée");
					e.printStackTrace();
				}
				
				else{
					Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
					e.printStackTrace();
				}
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				e.printStackTrace();
			}
			return null;
		}
		Repertoire.addMessageinfo("Mise à jour sauvegardée");
		return "etabclassedeleguevue";
	}
	
	public void initialize(){
		
		if(this.codeClasse!=null){
			this.service.initialiseClasseBean(this);
			this.eleves=this.service.listerElevesinscrits(codeClasse);
			this.setEnseignants(this.service.listerEnseignantsClasse(codeClasse));
		}
	}
}
