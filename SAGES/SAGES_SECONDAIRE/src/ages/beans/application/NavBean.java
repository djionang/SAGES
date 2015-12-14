package ages.beans.application;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Dispatch bean
 * Bean de redirection des liens vers les differentes pages utilisateur
 * @author crecpro-Bri
 *
 */
@ManagedBean(name="navBean")
@ApplicationScoped
public class NavBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 */
	public NavBean(){		
	}
	
	/////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////
	/////////////// NAV GENERALE
	
	/**
	 * page d'accueil
	 * @return
	 */
	public String accueil(){
		
		return "accueil";
	}
	
	/**
	 * page d'accueil lorsque loggué
	 * @return
	 */
	public String accueil2(){
		return "accueil2";
	}
	
	/**
	 * Page a contact
	 * @return
	 */
	public String contact(){
		return "contact";
	}
	
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	///////////////////////INSCRIPTION
	/**
	 * enregistrer un nouveau dossier de candidature
	 * @return chaine de direction face config jsf
	 */
	public String nouvellecandidature(){
		return "nouvellecandidature";
	}
	
	/**
	 * visualiser une candidature
	 * @return
	 */
	public String visualisercandidature(){
		return "visualisercandidature";
	}
	
	/**
	 * inscription d'un nouvel eleve
	 * @return chaine de navigation faces-config
	 */
	public String modifiercandidature(){
		return "modifiercandidature";
	}
	
	/**
	 * lister les candidatures
	 * @return
	 */
	public String listercandidatures(){
		return "listercandidatures";
	}
	
	public String listercandidaturesacceptees(){
		return "listercandidaturesacceptees";
	}
	
	public String listercandidaturesrejetees(){
		return "listercandidaturesrejetees";
	}
	
	public String listercandidaturesattente(){
		return "listercandidaturesattente";
	}
	
	/**
	 * inscription d'un ancien eleve
	 * @return
	 */
	public String inscriptionancien(){
		return "inscriptionancien";
	}
	
	/**
	 * inscription d'un nouvel eleve
	 * @return
	 */
	public String inscriptioneleve(){
		return "inscriptioneleve";
	}
	
	public String inscriptionlisteprovisoire(){
		return "inscriptionlisteprovisoire";
	}
	
	/**
	 * envoie d'un eleve dans une salle de classe
	 * @return
	 */
	public String inscriptioneleveversclasse(){
		return "inscriptioneleveversclasse";
	}
	
	public String inscriptionfraispropag(){
		return "inscriptionfraispropag";
	}
	
	/**
	 * enregistrement des frais de scolarité
	 * @return
	 */
	public String inscriptionfrais(){
		return "inscriptionfrais";
	}
	
	/**
	 * enregistrement des frais de scolarité
	 * @return
	 */
	public String inscriptionfraisN(){
		return "inscriptionfraisN";
	}
	
	/**
	 * enregistrement des frais de scolarité
	 * @return
	 */
	public String inscriptionfraisNR(){
		return "inscriptionfraisNR";
	}
	
	/**
	 * Visualiser les frais versés par d'un élève
	 * @return
	 */
	public String inscriptionfraisindiv(){
		return "inscriptionfraisindiv";
	}
	
	
	
	/**
	 * liste des versements des frais exigibles
	 * @return
	 */
	public String inscriptionlisteversement(){
		return "inscriptionlisteversement";
	}
	
	/**
	 * liste des versements des frais exigibles
	 * @return
	 */
	public String bilanfinancier(){
		return "bilanfinancier";
	}
	
	
	/**
	 * bilan financier par classe
	 * @return
	 */
	public String etatverclas(){
		return "etatverclas";
	}
	
	
	/**
	 * liste des tranches payées par un eleve
	 * @return
	 */
	public String inscriptiondetailtranches(){
		return "inscriptiondetailtranches";
	}
	
	/**
	 * parametre des tranches a verser
	 * @return
	 */
	public String inscriptionfraisparam(){
		return "inscriptionfraisparam";
	}
	
	public String elevesenregle(){
		return "elevesenregle";
	}
	
	public String elevesnenregle(){
		return "elevesnenregle";
	}
	
	public String elevesinscrits(){
		return "elevesinscrits";
	}
	
	public String inscriptionetateleve(){
		return "inscriptionetateleve";
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////PROGRAMMATION
	
	/**
	 * Visualiser la programmation des enseignements
	 * @return
	 */
	public String progenseivue(){
		return "progenseivue";
	}

	
	/**
	 * visualiser la programmation des salles
	 * @return
	 */
	public String progsallevue(){
		return "progsallevue";
	}
	
	/**
	 * visualiser la programmation des ateliers
	 * @return
	 */
	public String progateliervue(){
	return "progateliervue";
	}

	
	/**
	 * visualiser la programmation des classes
	 * @return
	 */
	public String progclassevue(){
	return "progclassevue";
	}
	
	/**
	 * editer la programmation d'une classe
	 * @return
	 */
	public String progclasseedit(){
	return "progclasseedit";
	}
	
	/**
	 * visauliser la programmation des activites
	 * @return
	 */
	public String progactivvue(){
		return "progactivevue";
	}
	
	/**
	 * Listing des activites
	 * @return
	 */
	public String listingactivites(){
		return "listingactivites";
	}
	
	/**
	 * editer la programmation des activités
	 * @return
	 */
	public String progactivedit(){
		return "progactivedit";
	}
	
	public String progactivsave(){
		return "progactivsave";
	}
	
	public String progactivview(){
		return "progactivview";
	}
	
	
	public String progactivdelete(){
		return "progactivdelete";
	}
	
	/**
	 * visualiser la programmation des examens
	 * @return
	 */
	public String progexamvue(){
	return "progexamvue";
	}
	
	/**
	 * editer la programmation des examens
	 * @return
	 */
	public String progexamedit(){
	return "progexamedit";
	}
	
	/**
	 * visualiser la programmation annuelle des examens
	 * @return
	 */
	public String progexamannuel(){
	return "progexamannuel";
	}

	public String ajoutertimetable(){
		return "ajoutertimetable";
	}
	
	public String editertimetable(){
		return "editertimetable";
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	//////////////////GESTION	SCOLAIRE
	
	/**
	 * creer une matiere
	 * @return
	 */
	public String gestmatcreate(){
	return "gestmatcreate";
	}
	
	/**
	 * visualiser les matieres
	 * @return
	 */
	public String gestmatlisting(){
	return "gestmatlisting";
	}
	
		
	/**
	 * creer un groupe de matiere	
	 * @return
	 */
	public String gestgrpecreate(){
	return "gestgrpecreate";
	}
	
	/**
	 * visualiser les groupes de matieres
	 * @return
	 */
	public String gestgrpelisting(){
	return "gestgrpelisting";
	}
	
	/**
	 * editer un groupe de matieres
	 * @return
	 */
	public String gestgrpeedit(){
	return "gestgrpeedit";
	}
	
	
	
	/**
	 * creer un enseignement		
	 * @return
	 */
	public String gestenseicreate(){
	return "gestenseicreate";
	}
	
	/**
	 * visualiser les enseignements
	 * @return
	 */
	public String gestenseivue(){
	return "gestenseivue";
	}
	
	/**
	 * editer les enseignements
	 * @return
	 */
	public String gestenseiedit(){
	return "gestenseiedit";
	}
	
	/**
	 * creer une entree de cahier de texte
	 * @return
	 */
	public String gestscoolcdtcreate(){
	return "gestscoolcdtcreate";
	}
	
	/**
	 * visualiser un cahier de texte		
	 * @return
	 */
	public String gestscoolcdtvue(){
	return "gestscoolcdtvue";
	}
	
	/**
	 * modifier le cahier de texte
	 * @return
	 */
	public String gestscoolcdtedit(){
	return "gestscoolcdtedit";
	}
	
	/**
	 * Enregistrer un nouveau cours
	 * @return
	 */
	public String gestcourscreate(){
		return "gestcourscreate";
	}
	
	
	/**
	 * Enregistrer copier des cours cours
	 * @return
	 */
	public String gestcourscopier(){
		return "gestcourscopier";
	}
	
	/**
	 * Copier les cours
	 * @return
	 */
	public String gestcourscopie(){
		return "gestcourscopie";
	}
	
	
	/**
	 * Lister les cours
	 * @return
	 */
	public String gestcourslisting(){
		return "gestcourslisting";
	}
	
	
	/**
	 * Enregistrer un nouveau chapitre cours
	 * @return
	 */
	public String gestcourspartiecreate(){
		return "gestcourspartiecreate";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	/////////////////////////ELEVES
	
	/**
	 * Rechercher un eleve
	 * @return
	 */
	public String rechercheeleve(){
	return "rechercheeleve";
	}
	
	/**
	 * certificat
	 * @return
	 */
	public String certificateleve(){
	return "certificateleve";
	}
	
	/**
	 * Enregistrer un nouvel eleve
	 * @return
	 */
	public String enregistrerEleve(){
		return "enregistrerEleve";
	}
	
	
	public String elevelisting(){
		return "elevelisting";
	}
	
	public String classelisting(){
		return "classelisting";
	}

	
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////PERSONNEL
	
	
	/**
	 * visualiser les fonction d'un enseignant		
	 * @return
	 */
	public String personenseignfonct(){
		return "personenseignfonct";
	}
	
	/**
	 * visualiser les taches attribuées a un enseignant
	 * @return
	 */
	public String personenseignwork(){
	return "personenseignwork";
	}
	
	/**
	 * visualiser les responsabilites attibuées a un enseignant
	 * @return
	 */
	public String personenseignrespo(){
	return "personenseignrespo";
	}
	
	/**
	 * parametrer la paye pr un personnel
	 * @return
	 */
	public String personenseignpayeparam(){
	return "personenseignpayeparam";
	}

	/**
	 * evaluer le salaire d'un enseignant	
	 * @return
	 */
	public String personenseignpayeeval(){
	return "personenseignpayeeval";
	}
	
	/**
	 * 
	 * @return
	 */
	public String personenseignpayerespo(){
	return "personenseignpayerespo";
	}
	
	/**
	 * fonctionn d'une personnel de direction
	 * @return
	 */
	public String persondirectfonc(){
	return "persondirectfonc";
	}
	
	/**
	 * attributions d'un personnel de direction
	 * @return
	 */
	public String persondirectattrib(){
	return "persondirectattrib";
	}

	
	///////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////COMPTES
	/**
	 * GESTION DES ROLES	
	 * @return
	 */
	public String comptrole(){
	return "comptrole";
	}

	/**
	 * gestion des comptes	
	 * @return
	 */
	public String comptcompte(){
	return "comptcompte";
	}
	
	/**
	 * association des droits aux comptes
	 * @return
	 */
	public String comptdroit(){
	return "comptdroit";
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	//////////////////////////EXAMENS/BULLETINS
	/**
	 * creer une evaluation
	 * @return
	 */
	public String examevalcreate(){
	return "examevalcreate";
	}
	
	/**
	 * creer une liste evaluation
	 * @return
	 */
	public String examevallistecreate(){
	return "examevallistecreate";
	}
	
	/**
	 * visualiser les evaluations
	 * @return
	 */
	public String examevallisting(){
	return "examevallisting";
	}
	
	
	/**
	 * Création de type d'examen
	 * @return
	 */
	public String typeevalcreate(){
		return "typeevalcreate";
	}
		
		/**
		 * visualiser les types d'evaluations
		 * @return
		 */
	public String typeevallisting(){
		return "typeevallisting";
	}
	
	
	
	/**
	 * gestion absences aux examens
	 * @return
	 */
	public String absenceexamen(){
	return "absenceexamen";
	}
	
	/**
	 * invalider une absence a un examen
	 * @return
	 */
	public String invabsenceexamen(){
		return "invabsenceexamen";
		}
	

	/**
	 * parametrer le contenu du bulletin
	 * @return
	 */
	public String exambullparam(){
	return "exambullparam";
	}

	
	/**
	 * creer une note
	 * @return
	 */
	public String examnotecreate(){
		return "examnotecreate";
	}
	
	/**
	 * creer une note
	 * @return
	 */
	public String examnoteelevecreate(){
		return "examnoteelevecreate";
	}
	
	/**
	 * Telecharger un formulaire vierge de saisie des notes
	 * @return
	 */
	public String telformulairenote(){
		return "telformulairenote";
	}
	
	/**
	* visualiser les bulletins	
	* @return
	*/
	public String exambullvue(){
		return "exambullvue";
	}
	
	/**
	* imprimer les bulletins trimestriels
	* @return
	*/
	public String examimpressbulltrim(){
		return "examimpressbulltrim";
	}
	
	/**
	* imprimer les bulletins trimestriels complexe
	* @return
	*/
	public String examimpressbulltrimC(){
		return "examimpressbulltrimC";
	}
	
	/**
	* imprimer les bulletins annuel
	* @return
	*/
	public String examimpressbullannuel(){
		return "examimpressbullannuel";
	}
	
	/**
	* imprimer les bulletins sequentiels
	* @return
	*/
	public String examimpressbullseq(){
		return "examimpressbullseq";
	}
	
	/**
	* imprimer les le formulaire des notes de toutes les sequences
	* @return
	*/
	public String examimprimtoutesnotes(){
		return "examimprimtoutesnotes";
	}
	
	/**
	* imprimer des tableaux
	* @return
	*/
	public String examimpresstab(){
		return "examimpresstab";
	}
	
	/**
	* imprimer des sanctions
	* @return
	*/
	public String examimpresssanct(){
		return "examimpresssanct";
	}
	
	
	public String examimpressbilanbul(){
		return "examimpressbilanbul";
	}
	
	public String examimpressbilanbulSeq(){
		return "examimpressbilanbulSeq";
	}
	
	public String examimpressbilanbulTrim(){
		return "examimpressbilanbulTrim";
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	//////////////////////////DISCIPLINE
	/**
	* creer un retard
	* @return
	*/
	public String disretardcreate(){
	return "disretardcreate";
	}
	
	/**
	* visualiser les retards	
	* @return
	*/
	public String disretardvue(){
	return "disretardvue";
	}

	/**
	* creer une absence
	* @return
	*/
	public String disabsencecreate(){
	return "disabsencecreate";
	}
	
	/**
	* editer un retard
	* @return
	*/
	public String distretardedit(){
	return "distretardedit";
	}
	
	/**
	* creer une sanction
	* @return
	*/
	public String dissanctcreate(){
	return "dissanctcreate";
	}
	
	/**
	* visualiser les sanctions	
	* @return
	*/
	public String dissanctlisting(){
	return "dissanctlisting";
	}

	/**
	* visualiser le journal des sanctions
	* @return
	*/
	public String disjournalvue(){
	return "disjournalvue";
	}
	
	/**
	 * Creer un type de sanction
	 * @return
	 */
	public String createtypesanction(){
		return "createtypesanction";
	}
	
	/**
	 * Listing des types de sanctions
	 * @return
	 */
	public String listingtypesanction(){
		return "listingtypesanction";
	}
	
	/**
	 * Listing des retards
	 * @return
	 */
	public String listingretards(){
		return "listingretards";
	}
	
	/**
	 * Listing des absences
	 * @return
	 */
	public String listingabsences(){
		return "listingabsences";
	}
	
	/**
	 * creation de convocation
	 * @return
	 */
	public String disconvoccreate(){
		return "disconvoccreate";
	}
	
	/**
	 * Listing de convocations
	 * @return
	 */
	public String listingconvocations(){
		return "listingconvocations";
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	//////////////////////////ETABLISSEMENT
	
	/**
	 * Enregistrement d'un établissement
	 * @return
	 */
	public String etabcreate(){
		return "etabcreate";
	}
	
	/**
	 * Listing des établissements enregistrés
	 * @return
	 */
	public String etablisting(){
		return "etablisting";
	}
	
	/**
	 * Editer l'etablissement en cours
	 * @return
	 */
	public String modifieretablissement(){
		return "modifieretablissement";
	}
	
	/**
	* voir les classes de l'etablissement
	* @return
	*/
	public String etabclasseelevvue(){
	return "etabclasseelevvue";
	}
		
	
	public String etabclasseoptionvue(){
		return "etabclasseoptionvue";
	}
	
	/**
	* voir les enseignants d'une classe
	* @return
	*/
	public String etabclasseenseignvue(){
	return "etabclasseenseignvue";
	}
	
	/**
	* visualiser les delegues d'une classe	
	* @return
	*/
	public String etabclassedeleguevue(){
	return "etabclassedeleguevue";
	}
	
	/**
	* creer un cycle
	* @return
	*/
	public String etabcyclecreate(){
	return "etabcyclecreate";
	}
	
	/**
	* Visualiser les cycles
	* @return
	*/
	public String etabcyclelisting(){
	return "etabcyclelisting";
	}
	
		
	/**
	* creer un niveau
	* @return
	*/
	public String etabnivocreate(){
	return "etabnivocreate";
	}
	
	/**
	* visualiser les niveaux	
	* @return
	*/
	public String etabnivolisting(){
	return "etabnivolisting";
	}

	
	/**
	* creer une option
	* @return
	*/
	public String etaboptioncreate(){
	return "etaboptioncreate";
	}
	
	/**
	* visualiser les options
	* @return
	*/
	public String etaboptionlisting(){
	return "etaboptionlisting";
	}
	
		
	/**
	* associer option et classe ou niveau	
	* @return
	*/
	public String etaboptionversclassenivo(){
	return "etaboptionversclassenivo";
	}
	
	/**
	* creer une section
	* @return
	*/
	public String etabsectioncreate(){
	return "etabsectioncreate";
	}
	
	/**
	* visualiser les sections
	* @return
	*/
	public String etabsectionlisting(){
	return "etabsectionlisting";
	}
	
	
	/**
	* creer une enseignement
	* @return
	*/
	public String etabenseicreate(){
	return "etabenseicreate";
	}
	
	/**
	* visualiser les enseignements	
	* @return
	*/
	public String etabenseilisting(){
	return "etabenseilisting";
	}
	
	
	
	/**
	* gestion des trimestres
	* @return
	*/
	public String anneetrim(){
	return "anneetrim";
	}
	
	/**
	 * Navigation vers la page de création d'un nouveau trimestre
	 * @return
	 */
	public String creerTrimestre(){
		return "creerTrimestre";
	}
	
	/**
	* creation des années académiques
	* @return
	*/
	public String anneean(){
	return "anneean";
	}
	
	
	/**
	* gestion des create
	* @return
	*/
	public String anneecreate(){
	return "anneecreate";
	}
	
	public String anneeseq(){
		return "anneeseq";
	}
	
	public String anneebilan(){
		return "anneebilan";
	}
	
	/**
	 * Cloturer une année
	 * @return
	 */
	public String anneecloture(){
		return "anneecloture";
	}
	
	/**
	 * Listing des années enregistrées
	 * @return
	 */
	public String anneelisting(){
		return "anneelisting";
	}
	
	public String creerSequence(){
		return "creerSequence";
	}
	
	/////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	///////////////////////RESSOURCES
	
	
	/**
	* creer une salle	
	* @return
	*/
	public String ressoursallecreate(){
	return "ressoursallecreate";
	}
	
	/**
	* visualiser les salles
	* @return
	*/
	public String ressoursallevue(){
	return "ressoursallevue";
	}
	
	/**
	* Supprimer une salle
	* @return
	*/
	public String ressoursalledelete(){
	return "ressoursalledelete";
	}
	
	/**
	* visualiser les salles
	* @return
	*/
	public String ressoursallelisting(){
	return "ressoursallelisting";
	}
	
	/**
	* editer une salle
	* @return
	*/
	public String ressoursalleedit(){
	return "ressoursalleedit";
	}
	
	/**
	* creer un atelier
	* @return
	*/
	public String ressourateliercreate(){
	return "ressourateliercreate";
	}

	
	/**
	* visualiser les ateliers
	* @return
	*/
	public String ressourateliervue(){
	return "ressourateliervue";
	}
	
	/**
	* editer un atelier	
	* @return
	*/
	public String ressouratelieredit(){
	return "ressouratelieredit";
	}
	
	/**
	* listing des ateliers	
	* @return
	*/
	public String ressouratelierlisting(){
	return "ressouratelierlisting";
	}
	
	
	public String ressourtypesallelisting(){
		return "ressourtypesallelisting";
	}
	
	public String ressourtypesallecreate(){
		return "ressourtypesallecreate";
	}
	
	/**
	* creer une classe
	* @return
	*/
	public String ressourclassecreate(){
	return "ressourclassecreate";
	}
	
	/**
	* visualiser les classes
	* @return
	*/
	public String ressourclassevue(){
	return "ressourclassevue";
	}
	
	/**
	* visualiser (listing) les classes
	* @return
	*/
	public String ressourclasselisting(){
	return "ressourclasselisting";
	}
	
	/**
	* editer une classe
	* @return
	*/
	public String ressourclasseedit(){
	return "ressourclasseedit";
	}	
	
	
	/**
	 * création d'un enseignant non enregitré en tant que personnel
	 * @return
	 */
	public String personenseigncreate(){
		return "personenseigncreate";
	}
	
	public String personenseignlisting(){
		return "personenseignlisting";
	}
	
	public String enseignantedit(){
		return "enseignantedit";
	}
	
	public String enseignantdelete(){
		return "enseignantdelete";
	}
	
	public String enseignantvue(){
		return "enseignantvue";
	}
	
	public String personelcreate(){
		return "personelcreate";
	}
	
	public String personellisting(){
		return "personellisting";
	}
	
	public String personelvue(){
		return "personelvue";
	}
	
	public String personeledit(){
		return "personeledit";
	}
	
	public String personeldelete(){
		return "personeldelete";
	}
	
	public String creeritemrole(){
		return "creeritemrole";
	}
	
	/**
	 * Listing des item de roles
	 * @return
	 */
	public String listingdroitdacces(){
		return "listingdroitdacces";
	}
	
	public String creergroupeuser(){
		return"creergroupeuser";
	}
	
	public String gestionmoncompte(){
		return "gestionmoncompte";
	}
	
	public String creerutilisateur(){
		return "creerutilisateur";
	}
	
	public String listingutilisateur(){
		return "listingutilisateur";
	}
	
	public String listinggroupeuser(){
		return "listinggroupeuser";
	}
	
	public String creermanager(){
		return "creermanager";
	}
	
	public String editcompteadmin(){
		return "editcompteadmin";
	}
	
	public String assigner(){
		return "assigner";
	}
	
	public String listingrespo(){
		return "listingrespo";
	}
	
	public String modifierrespo(){
		return "modifierrespo";
	}
	
	/////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	///////////////////////Bilan et Statistique
	
	
	
	public String bilanseq(){
		return "bilanseq";
	}
	
	public String bilantrim(){
		return "bilantrim";
	}
	
	public String bilanannuel(){
		return "bilanannuel";
	}
	
	public String visualiserbilanseq(){
		return "visualiserbilanseq";
	}
	
	public String visualiserbilantrim(){
		return "visualiserbilantrim";
	}
	
	public String visualiserbilanannuel(){
		return "visualiserbilanannuel";
	}
	
	public String bilanreussitematiere(){
		return "bilanreussitematiere";
	}
	
	public String tauxreussitesequence(){
		return "tauxreussitesequence";
	}
	
	public String pvr(){
		return "pvr";
	}
	
	public String pvrt(){
		return "pvrt";
	}
	
	public String pvrm(){
		return "pvrm";
	}
	
	public String pvrtm(){
		return "pvrtm";
	}
	
////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
//////////////////GESTION	MATERIELS

	public String materielvue(){
		return "materielvue";
	}
	
	public String materieledit(){
		return "materieledit";
	}
	
	public String materieldelete(){
		return "materieldelete";
	}

	public String ajoutermateriel(){
		return "ajoutermateriel";
	}
	
	public String ajoutermaterielN(){
		return "ajoutermaterielN";
	}
	
	public String materielcreate(){
		return "materielcreate";
	}
	
	public String materiellisting(){
		return "materiellisting";
	}
	
	public String sortielisting(){
		return "sortielisting";
	}
	
	public String materielvueS(){
		return "materielvueS";
	}
	
	public String sortiemateriel(){
		return "sortiemateriel";
	}
	
	
////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
//////////////////GESTION	BUDGET

public String previsionvue(){
return "previsionvue";
}

public String previsionedit(){
return "previsionedit";
}


public String ajouterprevision(){
return "ajouterprevision";
}

public String ajouterprevisionN(){
return "ajouterprevisionN";
}

public String previsioncreate(){
return "previsioncreate";
}

public String previsionlisting(){
return "previsionlisting";
}

public String depenselisting(){
return "depenselisting";
}

public String depensevueD(){
return "depensevueD";
}

public String depense(){
return "depense";
}

public String transfert(){
return "transfert";
}


}
