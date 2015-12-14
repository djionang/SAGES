package ages.beans.inscription;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;


import utils.OperationResults;
import utils.Service;
import utils.Repertoire;
import ages.beans.GenericBean;
import ages.beans.eleve.EleveBean;
import ages.exception.ClassToBeanCopyException;
import ages.exception.DroitsScolairesNonDefinis;
import ages.exception.ElementNOtFoundException;
import ages.exception.EleveDSCompletException;
import ages.exception.JPAException;
import ages.exception.MontantInscriptionInsuffisant;
import ages.exception.TotalVersementExcedantException;

@ManagedBean(name="inscriptionBean")
@ViewScoped
public class InscriptionBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;	
	
	//matricule passé en paramètre pour la recherche
	private String matricule;
	
	//élève recupéré
	private EleveBean selectedEleve;
	
	private String classe;
	
	private boolean feesFull;
	
	private float totalAverser;
	
	private float totalverse;
		
	private List<TrancheBean> listeTranchesClasse;
	
	private List<VersementBean> listeVersements;
	
	private Float montantVerse;
	
	private Date dateVersement;
	
	private String idversement;
	private int numerocertificat;
	
	private String typeversement;
	
	private List<EleveBean> listeeleves;
	
	// détermine si l'élève sélectionné est inscrit ou non pour l'année en cours
	private boolean eleveInscrit;
	
	public Date getDateVersement() {
		if(dateVersement==null){
			dateVersement=new Date();
		}
			
		return dateVersement;
	}
	public void setDateVersement(Date dateVersement) {
		this.dateVersement = dateVersement;
	}
	
	
	public EleveBean getSelectedEleve() {
		return selectedEleve;
	}
	public void setSelectedEleve(EleveBean selectedEleve) {
		this.selectedEleve = selectedEleve;
	}
	
	
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public List<TrancheBean> getListeTranchesClasse() {
		return listeTranchesClasse;
	}
	public void setListeTranchesClasse(List<TrancheBean> listeTranchesClasse) {
		this.listeTranchesClasse = listeTranchesClasse;
	}
	public Float getMontantVerse() {
		return montantVerse;
	}
	public void setMontantVerse(Float montantVerse) {
		this.montantVerse = montantVerse;
	}
	public void setService(Service service) {
		this.service = service;
	}
	
	public boolean isFeesFull() {
		return feesFull;
	}
	public void setFeesFull(boolean feesFull) {
		this.feesFull = feesFull;
	}
	public boolean isEleveInscrit() {
		return eleveInscrit;
	}
	public void setEleveInscrit(boolean eleveInscrit) {
		this.eleveInscrit = eleveInscrit;
	}
	
	
	public float getTotalAverser() {
		return totalAverser;
	}
	public void setTotalAverser(float totalAverser) {
		this.totalAverser = totalAverser;
	}
	public float getTotalverse() {
		return totalverse;
	}
	public void setTotalverse(float totalverse) {
		this.totalverse = totalverse;
	}
	

	public List<VersementBean> getListeVersements() {
		return listeVersements;
	}
	public void setListeVersements(List<VersementBean> listeVersements) {
		this.listeVersements = listeVersements;
	}
	
	
	
	/**
	 * @return the idversement
	 */
	public String getIdversement() {
		return idversement;
	}
	/**
	 * @param idversement the idversement to set
	 */
	public void setIdversement(String idversement) {
		this.idversement = idversement;
	}
	public String getTypeversement() {
		return typeversement;
	}
	public void setTypeversement(String typeversement) {
		this.typeversement = typeversement;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public List<EleveBean> getListeeleves() {
		return listeeleves;
	}
	public void setListeeleves(List<EleveBean> listeeleves) {
		this.listeeleves = listeeleves;
	}
	/**
	 * Enregistre l'inscription (versement des frais pour l'élève sélectioné)
	 * @return
	 */
	public String saveInscription(){
		if(this.selectedEleve==null){
			Repertoire.addMessageerreur("Aucun élève sélectionné");
			return OperationResults.RETRY;
		}
		
		
		try {
			idversement=this.service.saveInscription(this.montantVerse,this.selectedEleve.getMatricule(),this.typeversement,this.dateVersement);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Erreur de cohérence, élément non trouvé");
					e.printStackTrace();
					return OperationResults.FAILURE;
				}
				else{
					if(e.getCause().getClass().equals(MontantInscriptionInsuffisant.class)){
						Repertoire.addMessageerreur("Montant inscription non correct, doit être un multiple de montants de  tranches consécutives impayées");
						e.printStackTrace();
						return OperationResults.FAILURE;
					}
					else{
						if(e.getCause().getClass().equals(EleveDSCompletException.class)){
							Repertoire.addMessageerreur("La totalité des frais de scolarité ont été versés pour cet étudiant: "+this.selectedEleve.getMatricule());
							e.printStackTrace();
							return OperationResults.FAILURE;
						}
						else{
							if(e.getCause().getClass().equals(JPAException.class)){
								Repertoire.addMessageerreurInnatendue(e);
								return OperationResults.FAILURE;
							}
							else{
								if(e.getCause().getClass().equals(DroitsScolairesNonDefinis.class)){
									Repertoire.addMessageerreur("Aucun parametres de scolarités définis pour la filiere");
									
									e.printStackTrace();
									return OperationResults.FAILURE;
								}
								else{
									if(e.getCause().getClass().equals(TotalVersementExcedantException.class)){
										Repertoire.addMessageerreur("Le montant total excede le montant de pension requis");
										
										e.printStackTrace();
										return OperationResults.FAILURE;
									}
									else{
										Repertoire.addMessageerreurInnatendue(e);
									}
								}
							}
						}
					}
				}
			}
			else{
				Repertoire.addMessageerreurInnatendue(e);
			}
			
			return null;
		}	
		
		Repertoire.addMessageinfo("Versement enregistré");
		return "inscriptiond?faces-redirect=true&includeViewParams=true&matricule="+this.selectedEleve.getMatricule()+"&idversement="+idversement;
	}
	
	/**
	 * Charge les informations de l'eleve dont la matricule a été entré
	 * @param e évenement click sur le bouton
	 */
	public void chargerEleve(ActionEvent e){
		selectedEleve=new EleveBean();
		selectedEleve.setMatricule(matricule.trim());
		this.service.initEleve(selectedEleve);

		if(selectedEleve.getNom()!=null){
			if(selectedEleve.getCodeClasse()!=null){
				listeTranchesClasse=this.service.listeTranchesClasse(selectedEleve.getCodeClasse());
				try {
					setEleveInscrit(this.service.estInscrit(matricule));
				} catch (Exception e1) {
					if(e1.getCause().getClass().equals(ElementNOtFoundException.class))
					// TODO Auto-generated catch block
						Repertoire.addMessageerreur("Erreur grave, étudiant sélectionné non trouvé dans la base.");
					else
						Repertoire.addMessageerreur("Erreur Innatendue, Veuillez contacter l'adminitrateur.");
					
				}
			}				
			else{
				Repertoire.addMessageerreur("Paramètres de paiement non définis pour la filiere de cet étudiant");
				return;
			}
		}
		else{
			Repertoire.addMessageerreur("Matricule étudiant non trouvé");
			return;
		}
			
	}
	
	/**
	 * Charge le bean eleve a partir du matricule recu en parametre d'une page
	 */
	public void chargerEleve(){
		this.chargerEleve(null);
	}
	
	/**
	 * Charge le bean eleve ainsi que des Droits scolaires à partir du matricule recu en parametre d'une page
	 */
	public void loadElevesFees(){
		if(this.matricule==null)  // aucun matricule passé en paramètre
			return;
		if(selectedEleve!=null&&selectedEleve.getMatricule().compareToIgnoreCase(matricule)!=0){
			this.chargerEleveFees();
			return;
		}
		
		if(selectedEleve==null)
			this.chargerEleveFees();
	}
	
		
	/**
	 * Charge la la liste des tranches déja payées par un eleve
	 * @param e
	 */
	
	public void chargerEleveFees(){
		try {
			selectedEleve=new EleveBean();
			selectedEleve.setMatricule(matricule);
			this.service.initEleve(selectedEleve);
			
	
			if(selectedEleve.getNom()!=null){
				if(selectedEleve.getCodeClasse()!=null){
					//initialisation de la liste des tranches a verser
					setListeTranchesClasse(this.service.listeTranchesClasse(selectedEleve.getCodeClasse()));
					
					//initialisation de la liste des tranches versées
					setListeVersements(this.service.listerTranchesVersees(selectedEleve.getMatricule()));
					
					setEleveInscrit(this.service.estInscrit(matricule));
										
					setTotalAverser(totalAverser());
					
					setTotalverse(totalverse());					

					setFeesFull(totalAverser==totalverse);
				
				}				
				else{

					reinitialiserTout();
					Repertoire.addMessageerreur("Erreur Grave, Aucune filiere définie pour cet étudiant");
					return;
				}
			}
			else{

				reinitialiserTout();
				Repertoire.addMessageerreur("Matricule étudiant non trouvé");
				return;
			}
		} catch (Exception e1) {

			reinitialiserTout();
			if(e1.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Matricule étudiant non trouvé");
				Repertoire.logError("Erreur, matricule etudiant non trouvé", getClass(), e1);
				return;
			}
			else
				if(e1.getCause().getClass().equals(ClassToBeanCopyException.class)){
					Repertoire.addMessageerreur("Erreur innatendue, Veuillez reessayer l'opération");
					Repertoire.logError("Erreur de copie Versement-VersementBean", getClass(), e1);
					return;
				}
			
		}
			
	}
	
	private void reinitialiserTout() {
		selectedEleve=null;
		listeTranchesClasse=null;
		listeVersements=null;
		feesFull=false;
		eleveInscrit=false;
		totalAverser=0;
		totalverse=0;
	}
	/**
	 * Calcul du montant total déja versé par l'élève
	 * @return
	 */
	private float totalverse(){
		float somme=0;
		if(listeVersements!=null)
			for(int i=0;i<listeVersements.size();i++){
				somme+=listeVersements.get(i).getMontant();
			}
		return somme;
	}
	
	/**
	 * Calcul du montant total a verser(cout des Droits scolaires) pour l'élève
	 * @return
	 */
	private float totalAverser(){
		float somme=0;
		if(listeTranchesClasse!=null)
			for(int i=0;i<listeTranchesClasse.size();i++){
				somme+=listeTranchesClasse.get(i).getMontant();
			}
		return somme;
	}
	
	public void loadElevesClasse(){
		if(classe!=null && ! classe.isEmpty())
			this.setListeeleves(this.service.listerEleveProvisoireClasse(classe));
		
	}
	
	/**
	 * Enregistre un versement (inscription)
	 * @return le resultat de navigation, 
	 * soit erreur si l'enregistrement a échoué,
	 * soit success si l'enregistrement a réussi,
	 * soit retry si il y a eu une erreur lors du remplissage du formulaire, il faut alors reessayer.
	 */	
	public String saveDroitScolaire(){
		if(this.selectedEleve==null){
			Repertoire.addMessageerreur("Aucun étudiant sélectionné");
			return OperationResults.RETRY;
		}
		
		
		try {
			idversement=this.service.saveInscription(this.montantVerse,this.selectedEleve.getMatricule(), this.typeversement,this.dateVersement);
			setListeVersements(this.service.listerTranchesVersees(selectedEleve.getMatricule()));
			
			setEleveInscrit(this.service.estInscrit(matricule));
								
			setTotalAverser(totalAverser());
			
			setTotalverse(totalverse());					

			setFeesFull(totalAverser==totalverse);
		} 
		catch (Exception e) {
			if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Erreur de cohérence, élément non trouvé");
				return OperationResults.FAILURE;
			}
			else 
				if(e.getCause().getClass().equals(MontantInscriptionInsuffisant.class)){
					Repertoire.addMessageerreur("Montant inscription non correct, doir être un multiple de montants de  tranches consécutives impayées");
					return null;
				}
				else 
					if(e.getCause().getClass().equals(EleveDSCompletException.class)){
						Repertoire.addMessageerreur("L'étudiant a payé la totalité des droits de scolarité");
						return "visualiser?faces-redirect=true&includeViewParams=true&matricule="+this.selectedEleve.getMatricule();
					}
					else 
						if(e.getCause().getClass().equals(DroitsScolairesNonDefinis.class)){
							Repertoire.addMessageerreur("Echec enregistrement, Droits scolaires non paramétrés");
							return OperationResults.FAILURE;
						}
						else 
							if(e.getCause().getClass().equals(TotalVersementExcedantException.class)){
								Repertoire.addMessageerreur("Echec enregistrement, Versements excedant la totalité des droits scolaires");
								return null;
							}
							else{
								Repertoire.addMessageinfo("Erreur interne innatendue, veuillez contacter l'administrateur");
								
								return OperationResults.FAILURE;
							}
					
		}
		Repertoire.addMessageinfo("Versement enregistré");
		
		return "inscriptiond?faces-redirect=true&includeViewParams=true&matricule="+this.selectedEleve.getMatricule()+"&idversement="+idversement;
	}
	
public String saveCertificat(){
		
		if(this.selectedEleve==null){
			Repertoire.addMessageerreur("Aucun étudiant sélectionné");
			return OperationResults.RETRY;
		}
		
		
		try {
			numerocertificat=this.service.saveCertificat(selectedEleve.getIdeleve(),selectedEleve.getCodeClasse());
			this.service.imprimerCertificat(selectedEleve.getIdeleve(),selectedEleve.getCodeClasse(),numerocertificat);
		} 
		catch (Exception e) {
			if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Erreur de cohérence, élément non trouvé");
				return OperationResults.FAILURE;
			}else{
					Repertoire.addMessageinfo("Erreur interne innatendue, veuillez contacter l'administrateur");
								
					return OperationResults.FAILURE;
				}
					
		}
		Repertoire.addMessageinfo("Certificat enregistré");
		
		return OperationResults.SUCCESS;
	}
	
}
	
	
