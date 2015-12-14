package ages.beans.application;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import utils.Service;
import utils.Repertoire;

import ages.beans.GenericBean;
import ages.beans.anneeacademique.AnneeBean;
import ages.beans.anneeacademique.SequenceBean;
import ages.beans.anneeacademique.TrimestreBean;
import ages.beans.auth.GroupeUserBean;
import ages.beans.budget.PrevisionBean;
import ages.beans.enseignant.EnseignantBean;
import ages.beans.enseignement.CoursBean;
import ages.beans.enseignement.GroupeMatiereBean;
import ages.beans.enseignement.MatiereBean;
import ages.beans.etablissement.CycleBean;
import ages.beans.etablissement.NiveauBean;
import ages.beans.etablissement.OptionBean;
import ages.beans.etablissement.classe.ClasseBean;
import ages.beans.etablissement.classe.ClasseDataModel;
import ages.beans.etablissement.discipline.TypeSanctionBean;
import ages.beans.etablissement.evaluation.TypeEvaluationBean;
import ages.beans.etablissement.personnel.PersonnelBean;
import ages.beans.etablissement.salle.SalleBean;
import ages.beans.materiel.MaterielBean;
import ages.beans.statistique.AnneeDataModel;
import ages.beans.statistique.SequenceDataModel;
import ages.beans.statistique.TrimestreDataModel;


/**
 * Cache, bean contenant les elements les plus sollicités dans la BD
 * Joue le role de cache pour les ressources pas couramment modifiées, 
 * comme les salles de classes, les salles, les options, les matieres,...
 * @author Administrateur
 *
 */

@ManagedBean(name="etablissement")
@ApplicationScoped
public class EtablissementCache extends GenericBean{
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<ClasseBean> listeClasses;
	private List<SalleBean> listeSalles;
	
	private List<SalleBean> listeAteliers;
	
	private List<CycleBean> listeCycles;
	private List<NiveauBean> listeNiveaux;
	
	private List<NiveauBean> listeNiveauxB;
	private List<GroupeMatiereBean> listeGms;
	
	private List<MatiereBean> listeMatieres;
	
	private List<TrimestreBean> listeTrimestres;
	
	private List<AnneeBean> listeAnnees;
	
	private List<EnseignantBean> listeEnseignants;
	
	private List<SequenceBean> listesequences;
	
	private List<SequenceBean> listesequencesB;
	
	private List<TrimestreBean> listetrimestres;
	
	private List<CoursBean> listecours;
	
	private List<TypeEvaluationBean> listetypeEvaluations;
	
	private List<TypeSanctionBean> listetypeSanctions;
	
	private ClasseDataModel modeleClasse;
	
	private SequenceDataModel modeleSequence;
	
	private SequenceDataModel modeleSequenceB;
	
	private TrimestreDataModel modeleTrimestre;
	
	private AnneeDataModel modeleAnnee;
	
	private List<GroupeUserBean> groupesUtilisateurs;
	
	private List<PersonnelBean> personnels;
	
	private List<MaterielBean> materiels;
	
	private List<PrevisionBean> previsions;
	
	private List<OptionBean> listeoptions;
	
	
	public List<ClasseBean> getListeClasses() {		
		initClasses();
		return listeClasses;
	}

	public void setListeClasses(List<ClasseBean> listeClasses) {
		this.listeClasses = listeClasses;
	}

	
	public List<SalleBean> getListeSalles() {
		//to remove
		initSalles();
		return listeSalles;
	}

	public void setListeSalles(List<SalleBean> listeSalles) {
		this.listeSalles = listeSalles;
	}

	public List<CycleBean> getListeCycles() {
		initCycles();
		return listeCycles;
	}

	public void setListeCycles(List<CycleBean> listeCycles) {
		this.listeCycles = listeCycles;
	}
	
	public void setService(Service service) {
		this.service = service;
	}

	public ClasseDataModel getModeleClasse() {
		//to remove
		initClasses();
		return modeleClasse;
	}

	public void setModeleClasse(ClasseDataModel modeleClasse) {
		this.modeleClasse = modeleClasse;
	}

	public List<MatiereBean> getListeMatieres() {
		initMatieres();
		return listeMatieres;
	}

	public void setListeMatieres(List<MatiereBean> listeMatieres) {
		this.listeMatieres = listeMatieres;
	}

	public List<NiveauBean> getListeNiveaux() {
		initNiveaux();
		return listeNiveaux;
	}

	public void setListeNiveaux(List<NiveauBean> listeNiveaux) {
		this.listeNiveaux = listeNiveaux;
	}

	public List<TrimestreBean> getListeTrimestres() {
		initTrimestres();
		return listeTrimestres;
	}

	public void setListeTrimestres(List<TrimestreBean> listeTrimestres) {
		this.listeTrimestres = listeTrimestres;
	}

	public List<EnseignantBean> getListeEnseignants() {
		initEnseignants();
		return listeEnseignants;
	}

	public void setListeEnseignants(List<EnseignantBean> listeEnseignant) {
		this.listeEnseignants = listeEnseignant;
	}


	public List<GroupeMatiereBean> getListeGms() {
		initGroupeMatiere();
		return listeGms;
	}

	public void setListeGms(List<GroupeMatiereBean> listeGms) {
		this.listeGms = listeGms;
	}

	/**
	 * @return the listesequences
	 */
	public List<SequenceBean> getListesequences() {
		initSequences();
		return listesequences;
	}

	/**
	 * @param listesequences the listesequences to set
	 */
	public void setListesequences(List<SequenceBean> listesequences) {
		this.listesequences = listesequences;
	}

	public List<SequenceBean> getListesequencesB() {
		initSequencesB();
		return listesequencesB;
	}

	public void setListesequencesB(List<SequenceBean> listesequencesB) {
		this.listesequencesB = listesequencesB;
	}

	/**
	 * @return the listecours
	 */
	public List<CoursBean> getListecours() {
		initCours();
		return listecours;
	}

	/**
	 * @param listecours the listecours to set
	 */
	public void setListecours(List<CoursBean> listecours) {
		this.listecours = listecours;
	}

	/**
	 * @return the listetypeEvaluations
	 */
	public List<TypeEvaluationBean> getListetypeEvaluations() {
		initTypeEvaluation();
		return listetypeEvaluations;
	}

	/**
	 * @param listetypeEvaluations the listetypeEvaluations to set
	 */
	public void setListetypeEvaluations(
			List<TypeEvaluationBean> listetypeEvaluations) {
		this.listetypeEvaluations = listetypeEvaluations;
	}

	/**
	 * @return the listetypeSanctions
	 */
	public List<TypeSanctionBean> getListetypeSanctions() {
		initTypeSanction();
		return listetypeSanctions;
	}

	/**
	 * @param listetypeSanctions the listetypeSanctions to set
	 */
	public void setListetypeSanctions(List<TypeSanctionBean> listetypeSanctions) {
		this.listetypeSanctions = listetypeSanctions;
	}

	public List<GroupeUserBean> getGroupesUtilisateurs() {
		initGroupesUsers();
		return groupesUtilisateurs;
	}

	public void setGroupesUtilisateurs(List<GroupeUserBean> groupesUtilisateurs) {
		this.groupesUtilisateurs = groupesUtilisateurs;
	}

	public List<PersonnelBean> getPersonnels() {
		initPersonnels();
		return personnels;
	}

	public void setPersonnels(List<PersonnelBean> personnels) {
		this.personnels = personnels;
	}

	public List<OptionBean> getListeoptions() {
		initOptions();
		return listeoptions;
	}

	public void setListeoptions(List<OptionBean> listeoptions) {
		this.listeoptions = listeoptions;
	}

	public List<SalleBean> getListeAteliers() {
		initAteliers();
		return listeAteliers;
	}

	public void setListeAteliers(List<SalleBean> listeAteliers) {
		this.listeAteliers = listeAteliers;
	}

	public SequenceDataModel getModeleSequence() {
		initSequences();
		return modeleSequence;
	}

	public void setModeleSequence(SequenceDataModel modeleSequence) {
		this.modeleSequence = modeleSequence;
	}

	public List<TrimestreBean> getListetrimestres() {
		initTrimestres();
		return listetrimestres;
	}

	public void setListetrimestres(List<TrimestreBean> listetrimestres) {
		this.listetrimestres = listetrimestres;
	}


	public TrimestreDataModel getModeleTrimestre() {
		initTrimestres();
		return modeleTrimestre;
	}

	public void setModeleTrimestre(TrimestreDataModel modeleTrimestre) {
		this.modeleTrimestre = modeleTrimestre;
	}

	public List<AnneeBean> getListeAnnees() {
		initAnnees();
		return listeAnnees;
	}

	public void setListeAnnees(List<AnneeBean> listeAnnees) {
		this.listeAnnees = listeAnnees;
	}

	public AnneeDataModel getModeleAnnee() {
		initAnnees();
		return modeleAnnee;
	}

	public void setModeleAnnee(AnneeDataModel modeleAnnee) {
		this.modeleAnnee = modeleAnnee;
	}

	public List<NiveauBean> getListeNiveauxB() {
		initNiveauxB();
		return listeNiveauxB;
	}

	public void setListeNiveauxB(List<NiveauBean> listeNiveauxB) {
		this.listeNiveauxB = listeNiveauxB;
	}

	public List<MaterielBean> getMateriels() {
		initMateriels();
		return materiels;
	}

	public void setMateriels(List<MaterielBean> materiels) {
		this.materiels = materiels;
	}

	public List<PrevisionBean> getPrevisions() {
		initPrevisions();
		return previsions;
	}

	public void setPrevisions(List<PrevisionBean> previsions) {
		this.previsions = previsions;
	}

	public SequenceDataModel getModeleSequenceB() {
		return modeleSequenceB;
	}

	public void setModeleSequenceB(SequenceDataModel modeleSequenceB) {
		this.modeleSequenceB = modeleSequenceB;
	}

	@PostConstruct
	protected void init(){
		
		if(service==null){ 
			Repertoire.logDebug("Service null", getClass());
		}
	}
	
	
	// Initialise la liste des classes
	public void initClasses(){
		listeClasses=this.service.listeclasses();
		//initialisation du modele des classes
		modeleClasse=new ClasseDataModel(listeClasses);
	}
	
	// Initialise la liste des trimestres
		public void initTrimestres(){
			listeTrimestres=this.service.listetrimestres();
			modeleTrimestre = new TrimestreDataModel(listeTrimestres);
		}
		
		// Initialise la liste des annees
		public void initAnnees(){
			listeAnnees=this.service.listeannees();
			modeleAnnee = new AnneeDataModel(listeAnnees);
		}
	
	/**
	 * initialise les salles
	 */
	public void initSalles(){
		this.listeSalles=this.service.listerSalles();
	}
	
	/**
	 * Initialise les cycles
	 */
	public void initCycles(){
		this.listeCycles=this.service.listerCycles();
	}
	
	public void initMatieres(){
		this.listeMatieres=this.service.listematieres();
	}
	
	public void initNiveaux(){
		this.setListeNiveaux(this.service.listerNiveaux());
	}
	
	public void initNiveauxB(){
		this.setListeNiveauxB(this.service.listerNiveauxB());
	}
	
	private void initEnseignants(){
		this.listeEnseignants=this.service.listeenseignants();
	}
	
	private void initGroupeMatiere(){
		this.setListeGms(this.service.listegroupesmatieres());
	}
	
	private void initSequences(){
		this.listesequences=this.service.listerSequences();
		modeleSequence = new SequenceDataModel(listesequences);
	}
	
	private void initSequencesB(){
		this.listesequencesB=this.service.listerSequencesB();
		modeleSequenceB = new SequenceDataModel(listesequencesB);
	}
	
	
	private void initCours(){
		this.listecours=this.service.listecours();
	}
	
	private void initTypeEvaluation(){
		this.listetypeEvaluations=this.service.listerTypesEvaluations();
	}
	
	private void initTypeSanction(){
		this.setListetypeSanctions(this.service.listerTypesanction());
	}
	
	private void initGroupesUsers(){
		this.groupesUtilisateurs=this.service.listerGroupesUsers();
	}
	
	private void initPersonnels(){
		this.personnels=this.service.listepersonnel();
	}
	
	private void initMateriels(){
		this.materiels=this.service.listematrielss();
	}
	
	private void initPrevisions(){
		this.previsions=this.service.listeprevisions();
	}
	
	private void initOptions(){
		this.listeoptions=this.service.listerOptions();
	}
	
	private void initAteliers(){
		initSalles();
		listeAteliers=new ArrayList<SalleBean>();
		for(int i=0;i<listeSalles.size();i++){
			if(listeSalles.get(i).getType().compareToIgnoreCase("atelier")==0)
				listeAteliers.add(listeSalles.get(i));
		}
	}
	
	public List<String> completeTypesEvt(String query){
		return this.service.listeTypesEvtStartingWith(query);
	}
}

