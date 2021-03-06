package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.mail.internet.AddressException;
import javax.naming.NamingException;

import net.sf.jasperreports.engine.JRException;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import utils.impression.AgesImprimeBean;
import ages.beans.anneeacademique.AnneeBean;
import ages.beans.anneeacademique.AnneeEnCoursBean;
import ages.beans.anneeacademique.SequenceBean;
import ages.beans.anneeacademique.TrimestreBean;
import ages.beans.application.ConfigurationBean;
import ages.beans.application.SessionBean;
import ages.beans.auth.CompteBean;
import ages.beans.auth.GroupeUserBean;
import ages.beans.auth.Habilitations;
import ages.beans.auth.ItemRoleBean;
import ages.beans.auth.UserBean;
import ages.beans.budget.DepenseBean;
import ages.beans.budget.PrevisionBean;
import ages.beans.eleve.EleveBean;
import ages.beans.enseignant.EnsClasseBean;
import ages.beans.enseignant.EnseignantBean;
import ages.beans.enseignement.CdtBean;
import ages.beans.enseignement.CoursBean;
import ages.beans.enseignement.GroupeMatiereBean;
import ages.beans.enseignement.MatiereBean;
import ages.beans.enseignement.PartieCoursBean;
import ages.beans.etablissement.CycleBean;
import ages.beans.etablissement.EnseignementBean;
import ages.beans.etablissement.EtablissementBean;
import ages.beans.etablissement.NiveauBean;
import ages.beans.etablissement.OptionBean;
import ages.beans.etablissement.SectionBean;
import ages.beans.etablissement.classe.ClasseBean;
import ages.beans.etablissement.discipline.AbsenceBean;
import ages.beans.etablissement.discipline.ConvocationBean;
import ages.beans.etablissement.discipline.DisciplineItem;
import ages.beans.etablissement.discipline.RetardBean;
import ages.beans.etablissement.discipline.SanctionBean;
import ages.beans.etablissement.discipline.TypeSanctionBean;
import ages.beans.etablissement.evaluation.EvaluationBean;
import ages.beans.etablissement.evaluation.TypeEvaluationBean;
import ages.beans.etablissement.note.CompositionBean;
import ages.beans.etablissement.note.NotesBean;
import ages.beans.etablissement.personnel.PersonnelBean;
import ages.beans.etablissement.salle.SalleBean;
import ages.beans.etablissement.salle.TypeSalleBean;
import ages.beans.inscription.DossierBean;
import ages.beans.inscription.ParametreTrancheBean;
import ages.beans.inscription.TrancheBean;
import ages.beans.inscription.VersementBean;
import ages.beans.materiel.MaterielBean;
import ages.beans.materiel.SortieMBean;
import ages.beans.programmation.EvenementBean;
import ages.beans.programmation.TimeClasseBean;
import ages.beans.statistique.BilanAnBean;
import ages.beans.statistique.BilanBean;
import ages.beans.statistique.BilanTrimBean;
import ages.exception.AdminstrateurNotFoundException;
import ages.exception.AnneeEnCoursNonDefinieException;
import ages.exception.ChevauchementDateException;
import ages.exception.ClassToBeanCopyException;
import ages.exception.CoursNonDefiniException;
import ages.exception.DroitsScolairesNonDefinis;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;
import ages.exception.EleveDSCompletException;
import ages.exception.EtablissementUndefinedException;
import ages.exception.JPAException;
import ages.exception.MontantInscriptionInsuffisant;
import ages.exception.NonUniqueResultException;
import ages.exception.PourcentageEvaluationExedantException;
import ages.exception.TotalVersementExcedantException;
import ages.exception.UnAuthorizedOperationException;
import ages.exception.UnknownUserException;
import ejb.GestionBulletinNotesLocal;
import ejb.GestionCahierScolaireLocal;
import ejb.GestionDisciplineLocal;
import ejb.GestionEleveLocal;
import ejb.GestionEtablissementLocal;
import ejb.GestionExamensLocal;
import ejb.GestionInscriptionLocal;
import ejb.GestionMaterielLocal;
import ejb.GestionMatiereGroupeLocal;
import ejb.GestionPayeLocal;
import ejb.GestionProgrammationLocal;
import ejb.GestionRessourceLocal;
import ejb.GestionUtilisateurLocal;
import ejb.UtilitairesLocal;
import entities.Absence;
import entities.Annee;
import entities.Classe;
import entities.Configuration;
import entities.Cour;
import entities.Cycle;
import entities.Depense;
import entities.Eleve;
import entities.Enseignant;
import entities.Enseignement;
import entities.Etablissement;
import entities.Evaluation;
import entities.GpUserRole;
import entities.GroupeUser;
import entities.Groupematiere;
import entities.ItemRole;
import entities.Materiel;
import entities.Matiere;
import entities.Niveau;
import entities.OptionSerie;
import entities.ParametreTranche;
import entities.Personnel;
import entities.PreInscription;
import entities.Prevision;
import entities.Programmation;
import entities.Retard;
import entities.Salle;
import entities.Sanction;
import entities.Section;
import entities.Sequence;
import entities.SortieM;
import entities.Trimestre;
import entities.TypeEvaluation;
import entities.TypeSalle;
import entities.TypeSanction;
import entities.Utilisateur;
import entities.Versement;

//////////////////////////////////////////////////
//	           CLASSE Service.JAVA               //
//	FOURNISSEURS DE SERVICES A LA PARTIE CLIENTE//
//  *************************************       //
//////////////////////////////////////////////////


public class Service implements Serializable{
	
	private static final long serialVersionUID = 1L;

	//////////////////////////////////////////////////
	//	INTERFACES EJB DE GESTION DES MODULES       //
	//	DECLARATION  DES VARIABLES ET MAPPING       //
	//  *************************************       //
	//////////////////////////////////////////////////
	
	//bean Local de gestion des eleves
	@EJB(mappedName="SAGES_SE/GestionEleve/local")
	private GestionEleveLocal gestioneleveL;	
	
	//bean Local de gestion des preinscriptions
	@EJB(mappedName="SAGES_SE/GestionInscription/local")
	private GestionInscriptionLocal gestioninscriptionL; 
	
	//bean Local de gestion des ressources de l'etablissement
	@EJB(mappedName="SAGES_SE/GestionEtablissement/local")
	private GestionEtablissementLocal gestionetablissementL;
	
	//bean Local de gestion des ressources de l'etablissement
	@EJB(mappedName="SAGES_SE/GestionRessource/local")
	private GestionRessourceLocal gestionressourceL;
	
	//bean Local de gestion des matieres
	@EJB(mappedName="SAGES_SE/GestionMatiereGroupe/local")
	private GestionMatiereGroupeLocal gestionmatiereL;
	
	@EJB(mappedName="SAGES_SE/GestionProgrammation/local")
	private GestionProgrammationLocal gestionprogrammationL;
	
	@EJB(mappedName="SAGES_SE/GestionExamens/local")
	private GestionExamensLocal gestionexamensL;
	
	@EJB(mappedName="SAGES_SE/Utilitaires/local")
	private UtilitairesLocal applicationUtils;
	
	@EJB(mappedName="SAGES_SE/GestionDiscipline/local")
	private GestionDisciplineLocal gestiondiscipline;
	
	@EJB(mappedName="SAGES_SE/GestionUtilisateur/local")
	private GestionUtilisateurLocal gestionutilisateur;
	
	@EJB(mappedName="SAGES_SE/GestionCahierScolaire/local")
	private GestionCahierScolaireLocal gestionCdt;
	
	@EJB(mappedName="SAGES_SE/GestionBulletinNotes/local")
	private GestionBulletinNotesLocal gestionBulletin;
	
	@EJB(mappedName="SAGES_SE/GestionMateriel/local")
	private GestionMaterielLocal gestionmaterielL;
	
	@EJB(mappedName="SAGES_SE/GestionPaye/local")
	private GestionPayeLocal gestionpaye;
	
	
	//////////////////////////////////////////////////
	//	GETTERS DES EJB FOURNISSEURS DE SERVICES    //
	//  *************************************       //
	//////////////////////////////////////////////////
	
	
	private AgesImprimeBean ab;
	
		
	/**
	 * Retourne le bean de gestion des ressources de l'etablissement
	 * @return
	 */
	public GestionEtablissementLocal getGestionEtablissement() {
		if(gestionetablissementL==null){
			try {
				gestionetablissementL=(GestionEtablissementLocal) ContexteJNDI.getInitialContext().lookup("GestionEtablissement");
			} catch (NamingException e) {
				Repertoire.logFatal("Impossible de retrouver l'EJB GestionEtablissement", utils.Service.class, e);
			}
		}		
		return gestionetablissementL;
	}
	
	
	/**
	 * Gestion des utilisateurs
	 * @return
	 */
	public GestionUtilisateurLocal getGestionutilisateur() {
		if(gestionutilisateur==null){
			try {
				gestionutilisateur=(GestionUtilisateurLocal) ContexteJNDI.getInitialContext().lookup("GestionUtilisateur");
			} catch (NamingException e) {
				Repertoire.logFatal("Impossible de retrouver l'EJB GestionEtablissement", utils.Service.class, e);
			}
		}		
		return gestionutilisateur;
	}
	/*
	 * 
	 */
	public GestionMaterielLocal getGestionMateriel() {
		
		if(gestionmaterielL==null){
			try {
				gestionmaterielL=(GestionMaterielLocal) ContexteJNDI.getInitialContext().lookup("GestionMateriel");
			} catch (NamingException e) {
				Repertoire.logFatal("Impossible de retrouver l'EJB GestionMateriel", utils.Service.class, e);
			}
		}		
		return gestionmaterielL;
	}
	
	
	public GestionPayeLocal getGestionPaye() {
		
		if(gestionpaye==null){
			try {
				gestionpaye=(GestionPayeLocal) ContexteJNDI.getInitialContext().lookup("GestionPaye");
			} catch (NamingException e) {
				Repertoire.logFatal("Impossible de retrouver l'EJB GestionPaye", utils.Service.class, e);
			}
		}		
		return gestionpaye;
	}

	/**
	 * Retourne le bean de gestion de la discipline
	 * @return
	 */
	public GestionDisciplineLocal getGestionDiscipline() {
		if(gestiondiscipline==null){
			try {
				gestiondiscipline=(GestionDisciplineLocal) ContexteJNDI.getInitialContext().lookup("GestionDiscipline");
			} catch (NamingException e) {
				Repertoire.logFatal("Impossible de retrouver l'EJB GestionDiscipline", utils.Service.class, e);
			}
		}		
		return gestiondiscipline;
	}
	
	
	/**
	 * Retourne le bean de gestion des ressources de l'etablissement
	 * @return
	 */
	public GestionExamensLocal getGestionExamens() {
		if(gestionexamensL==null){
			try {
				gestionexamensL=(GestionExamensLocal) ContexteJNDI.getInitialContext().lookup("GestionExamens");
			} catch (NamingException e) {
				Repertoire.logFatal("Impossible de retrouver l'EJB GestionExamens", utils.Service.class, e);
			}
		}		
		return gestionexamensL;
	}

	/**
	 * Retourne le bean de gestion des ressources de l'etablissement
	 * @return
	 */
	public GestionProgrammationLocal getGestionProgrammation() {
		if(gestionprogrammationL==null){
			try {
				gestionprogrammationL=(GestionProgrammationLocal) ContexteJNDI.getInitialContext().lookup("GestionProgrammation");
			} catch (NamingException e) {
				Repertoire.logFatal("Impossible de retrouver l'EJB GestionProgrammation", utils.Service.class, e);
			}
		}		
		return gestionprogrammationL;
	}
	
	/**
	 * retourne le Bean de gestion des eleves local
	 * @return gestioneleveR
	 */
	public GestionEleveLocal getGestionEleve(){
		if(gestioneleveL==null){
			try {
				gestioneleveL=(GestionEleveLocal) ContexteJNDI.getInitialContext().lookup("GestionEleve");
			} catch (NamingException e) {
				Repertoire.logFatal("Impossible de retrouver l'EJB GestionEleve", getClass(), e);
			}
		}		
		return gestioneleveL;
	}

	/**
	 * retourne le Bean de gestion des inscriptions Local
	 * @return gestioneleveR
	 */
	public GestionInscriptionLocal getGestionInscription(){
		if(gestioninscriptionL==null){
			try {
				gestioninscriptionL=(GestionInscriptionLocal) ContexteJNDI.getInitialContext().lookup("GestionInscription");
			} catch (Exception e) {
				Repertoire.logFatal("Failed Recuperation dans JNDI de Gestion inscription", this.getClass(), e);
			}
		}		
		return gestioninscriptionL;
	}
		
	public GestionRessourceLocal getGestionressource() {
		if(gestionressourceL==null){
			try {
				gestionressourceL=(GestionRessourceLocal) ContexteJNDI.getInitialContext().lookup("GestionRessource");
			} catch (NamingException e) {
				Repertoire.logFatal("Impossible de retrouver l'EJB GestionRessource", getClass(), e);
			}
		}
		return gestionressourceL;
	}


	public GestionMatiereGroupeLocal getGestionmatiere() {
		if(gestionmatiereL==null){
			try {
				gestionmatiereL=(GestionMatiereGroupeLocal) ContexteJNDI.getInitialContext().lookup("GestionMatiere");
			} catch (Exception e) {
				Repertoire.logFatal("Failed Recuperation dans JNDI de Gestion Matiere", this.getClass(), e);
			}
		}
		return gestionmatiereL;
	}


	public UtilitairesLocal getUtilitaire() {
		if(applicationUtils==null){
			try {
				applicationUtils=(UtilitairesLocal) ContexteJNDI.getInitialContext().lookup("Utilitaires");
			} catch (NamingException e) {
				Repertoire.logFatal("Impossible de retrouver l'EJB Utilitaire", utils.Service.class, e);
			}
		}		
		return applicationUtils;
	}
	
	
	public void setGestionmatiereL(GestionMatiereGroupeLocal gestionmatiereL) {
		this.gestionmatiereL = gestionmatiereL;
	}
	
	
	public GestionCahierScolaireLocal getGestionCdt(){
		if(gestionCdt==null){
			try {
				gestionCdt=(GestionCahierScolaireLocal) ContexteJNDI.getInitialContext().lookup("GestionCahierScolaire");
			} catch (Exception e) {
				Repertoire.logFatal("Failed Recuperation dans JNDI de Gestion Cahier scolaire", this.getClass(), e);
			}
		}		
		return gestionCdt;
	}

	private AgesImprimeBean getImpressionUtils(){
		if(ab==null)
			ab=new AgesImprimeBean();
		
		return ab;
	}
	
	/**
	 * @return the gestionBulletin
	 */
	public GestionBulletinNotesLocal getGestionBulletin() {
		
		if(gestionBulletin==null){
			try {
				gestionBulletin=(GestionBulletinNotesLocal) ContexteJNDI.getInitialContext().lookup("GestionBulletinNotes");
			} catch (Exception e) {
				Repertoire.logFatal("Failed Recuperation dans JNDI de Gestion Bulletin", this.getClass(), e);
			}
		}
		return gestionBulletin;
	}


	/**
	 * @param gestionBulletin the gestionBulletin to set
	 */
	public void setGestionBulletin(GestionBulletinNotesLocal gestionBulletin) {
		this.gestionBulletin = gestionBulletin;
	}
	
	
		//////////////////////////////////////////////////
		//			FONCTIONS UTILITAIRES               //
		//  	*************************************   //
		//////////////////////////////////////////////////
	

	public SessionBean getInfosSession(){
		FacesContext context = FacesContext.getCurrentInstance();
	    SessionBean session = (SessionBean) context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{sessionBean}", SessionBean.class).getValue(context.getELContext());
		
	    return session;
	}

	private String getAnneeAcEncours(){
	    FacesContext context = FacesContext.getCurrentInstance();
	    SessionBean session = (SessionBean) context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{sessionBean}", SessionBean.class).getValue(context.getELContext());
	
	    if (session.getAnneeacademique()==null){
	    	return "";
	    }
	    		
	    return session.getAnneeacademique();
	}
	
	private String getLoginUser() throws UnknownUserException{
		FacesContext context = FacesContext.getCurrentInstance();
	    UserBean user = (UserBean) context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{userBean}", UserBean.class).getValue(context.getELContext());
	    if(user!=null)
	    	if(user.getLogin()==null)
	    		return "";
	    	else
	    		return user.getLogin();
	    else
	    	throw new  UnknownUserException(" Utilisateur non reconnu, acces aux services refus�");
		
	}
	
	
	private int getIduser(){
				
		FacesContext context = FacesContext.getCurrentInstance();
	    return (Integer) context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{sessionBean.idutilisateur}", Integer.class).getValue(context.getELContext());

	}
	
	
	private void initSession(Utilisateur user, String annee,String loginadmin, String passadmin) throws EtablissementUndefinedException{
				
		FacesContext context = FacesContext.getCurrentInstance();
	    SessionBean session = (SessionBean) context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{sessionBean}", SessionBean.class).getValue(context.getELContext());
	    Etablissement etab;
	    
	    etab=user.getPersonnel().getEtablissement();
	    
	    if(user.getPersonnel()==null){
	    	System.out.println("********************");
	    	System.out.println("*********ERROR NULPOINTER USER***********");
	    }
	    else{
	    	if(etab==null){
	    		System.out.println("********************");
		    	System.out.println("*********ERROR NULPOINTER ETAB***********");
	    	}
	    }
	    
	    
	    Annee anee=null;
	    try {
			anee=getGestionEtablissement().rechercheAnneeEnCours(annee);
		} catch (Exception e) {
			Repertoire.addMessageerreur("Impossible de d�terminer l'ann�e scolaire");
			e.printStackTrace();
		}
	    
	    resetAnneeEncours(annee);
	    
	    session.initSessionUser(etab.getCodeetablissement(), etab.getLogo(), etab.getAcronyme(), anee.getAnneeacademique(), anee.getDateDebut(), anee.getDateFin(), user.getIdutilisateur(),loginadmin,passadmin);
	
	    
	}
	
	
	

	/**
	 * Initialisation de session d'un administrateur de l'application
	 * Son idutilisateur vaut 0
	 * @param conf
	 * @param annee 
	 */
	private void initSession(Configuration conf, String annee) {
		FacesContext context = FacesContext.getCurrentInstance();
	    SessionBean session = (SessionBean) context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{sessionBean}", SessionBean.class).getValue(context.getELContext());
	    
		session.initSessionUser("", "", "", "", null, null, 0,"","");
	}


	/**
	 * Initialisation de session d'un gestionnaire d'etablissement
	 * Son idutilisateur vaut -1
	 * @param conf
	 */
	private void initSession(Etablissement et,String annee,String loginadmin,String passadmin) {
		
		FacesContext context = FacesContext.getCurrentInstance();
	    SessionBean session = (SessionBean) context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{sessionBean}", SessionBean.class).getValue(context.getELContext());
	   
	    resetAnneeEncours(annee);
	    Annee anee=null;
	    try {
			anee=getGestionEtablissement().rechercheAnneeEnCours(annee);
		} catch (Exception e) {
			Repertoire.addMessageerreur("Impossible de d�terminer l'ann�e scolaire");
			e.printStackTrace();
		}
	    
	    session.initSessionUser(et.getCodeetablissement(), et.getLogo(), et.getAcronyme(), anee.getAnneeacademique(), anee.getDateDebut(), anee.getDateFin(), -1,loginadmin,passadmin);
	
	}
	
	
	private void resetAnneeEncours(String etablissement){
		Annee anee=null;
	    try {
			anee=getGestionEtablissement().rechercheAnneeEnCours(etablissement);
		} catch (Exception e) {
			Repertoire.addMessageerreur("Impossible de d�terminer l'ann�e scolaire");
			e.printStackTrace();
		}
	    FacesContext context = FacesContext.getCurrentInstance();
	    SessionBean session = (SessionBean) context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{sessionBean}", SessionBean.class).getValue(context.getELContext());
	    
	    session.setAnneeacademique(anee.getAnneeacademique());
	    session.setDatedebutannee(anee.getDateDebut());
	    session.setDatefinannee(anee.getDateFin());
	}
	
	
	
	//////////////////////////////////////////////////
	//	       GESTION DES INSCRIPTIONS             //
	//  *************************************       //
	//////////////////////////////////////////////////
	
	
	/**
	 * Initialisation d'un dossier, recherche des ses attributs dans la BD
	 * @param dossier
	 */
	public void initDossierBean(DossierBean dossier){
		PreInscription preins=getGestionInscription().recherchePreinscription(dossier.getCodedossier(),getAnneeAcEncours());
		if(preins==null) return;
		
		if(!CopyUtil.copiePreinscriptionToDossierBean(dossier,preins)){
			Repertoire.logInfo("Copie de dossier a dossierBean d�roul� avec echec", utils.CopyUtil.class);
		}
	}


	public void initialiseClasseBean(ClasseBean classe){
		Object[] clase=getGestionEtablissement().rechercheClasse(classe.getCodeClasse(),getAnneeAcEncours());
		if(clase!=null)
			CopyUtil.copieClasseToClasseBean(classe,(Classe)clase[0], Integer.parseInt(clase[1].toString()));
		
	}
	
	
	public void initOption(OptionBean ob){
		OptionSerie opt=getGestionEtablissement().rechercheOption(ob.getCodeOption());
		CopyUtil.copieOptionVersOptionBean(ob, opt);
	}


	/**
	 * initialise une bean SalleBEan a partir de son code deja initalise en lui
	 * @param salle
	 */
	public void initialiseSalleBean(SalleBean sb){
		Salle salle=getGestionressource().rechercherSalle(sb.getCodeSalle());
		CopyUtil.copieSalleToSalleBean(sb, salle);
	}
	
	
	public void initNiveau(NiveauBean nb) {
		Niveau n=getGestionEtablissement().rechercheNiveau(nb.getCodeNiveau());
		CopyUtil.copieNiveauVersNiveauBean(nb, n);
		
	}


	public void initSection(SectionBean sb) {
		Section sect=getGestionEtablissement().rechercheSection(sb.getCodesection());
		if(sect==null) return;
		CopyUtil.copieSectionVersSectionBean(sb, sect);		
	}


	public void initCycle(CycleBean cb) {
		Cycle c=getGestionEtablissement().rechercheCycle(cb.getCodeCycle());
		CopyUtil.copieCycleVersCycleBean(cb, c);
	}

	/**
	 * Recherche d'un eleve a partir de son matricule
	 * @param matricule de l'eleve recherch�
	 * @return
	 */
	public void initEleve(EleveBean eb){
		Eleve eleve=getGestionEleve().rechercheEleve(eb.getMatricule(), getAnneeAcEncours());
		if(eleve==null){
			return ;
		}
		CopyUtil.copieEleveToEleveBean(eleve,eb);
	
	}


	public void initEnseignement(EnseignementBean eb) {
	Enseignement ens=getGestionEtablissement().rechercheEnseignement(eb.getLibelleens());
	if(ens!=null)
		CopyUtil.copieEnseignementVersEnseignementBean(eb, ens);
	}


	public void initialisePersonnelBean(PersonnelBean pb) {
		Personnel p=getGestionressource().recherchePersonnel(pb.getCodepersonnel());
		CopyUtil.copiePersonnelToPersonnelBean(p, pb);
	}


	public void initEnseignantBean(EnseignantBean eb) {
		Enseignant e=getGestionressource().rechercheEnseignant(eb.getCodeenseignant());
		CopyUtil.copieEnseignantToEnseignantBean(e, eb);
	}


	
	public boolean initTrancheClasse(TrancheBean tb) {
		List<ParametreTranche> pt=getGestionInscription().rechercheTranchesClasse(tb.getCodeclasse(),tb.getId(),getAnneeAcEncours());
		if(pt.isEmpty()){			
			return false;
		}
		 CopyUtil.copieParamTrancheToTrancheBean(pt.get(0),tb);
		 return true;
	}


	/**
	 * Recherche les informations sur l'�tablissement dans la base de donn�es
	 * @param etablissementBean
	 */
	public void initEtablissement(EtablissementBean eb) {
		Etablissement etab=getGestionEtablissement().rechercheEtablissement(eb.getCodeetablissement());
		if(etab!=null)
			CopyUtil.copieEtablissementVersEtablissementBean(eb,etab);
				
	}


	public void initTrimestre(TrimestreBean tb) {
		Trimestre tr=this.getGestionEtablissement().rechercheTrimestre(tb.getNumero(),getAnneeAcEncours());
		CopyUtil.copieTrimestreTotrimestreBean(tr,tb);
	}


	public void initAnnee(AnneeBean ab) throws AnneeEnCoursNonDefinieException {
		Annee an=getGestionEtablissement().rechercheAnnee(ab.getAnneeacademique(),getInfosSession().getCodeetablissement());
		CopyUtil.copieAnneeToAnneeBean(an,ab);
	}


	public void initBilanEnCours(ages.beans.statistique.BilanTrimBean bilanBean) {
		// TODO Auto-generated method stub
		
	}


	public void initSequence(SequenceBean sb) {
		Sequence sq=this.getGestionEtablissement().rechercheSequence(sb.getNumero(),getAnneeAcEncours());
		CopyUtil.copieSequenceToSequenceBean(sq,sb);
	}


	/**
	 * Initialise le bean annee en cours pour un etablissement
	 * @param aeb
	 */
	public void initAnneeEnCours(AnneeEnCoursBean aeb) {
		Annee an;
		try {
			 FacesContext context = FacesContext.getCurrentInstance();
			 SessionBean session = (SessionBean) context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{sessionBean}", SessionBean.class).getValue(context.getELContext());
			an = this.getGestionEtablissement().rechercheAnneeEnCours(session.getAnneeacademique());
			aeb.setAnneeacademique(an.getAnneeacademique());
			aeb.setClos(an.isClose());
			aeb.setDatedebut(an.getDateDebut());
			aeb.setDatefin(an.getDateFin());
		
		} catch (Exception e) {
			if(e.getCause().getClass().equals(AnneeEnCoursNonDefinieException.class ))
				Repertoire.logError("Annee en cours non d�finie", getClass(), e);
		}
		
		
	}


	public void initialiseTypeSalleBean(TypeSalleBean tsb) {
		if(tsb.getId()==0)
			return;
		TypeSalle ts=getGestionressource().rechercherTypeSalle(tsb.getId());
		CopyUtil.copieTypeSalleVersTypeSalleBean(tsb, ts);
	}


	public void initMatiere(ages.beans.enseignement.MatiereBean mb) {
		Matiere mat=getGestionmatiere().rechercherMatiere(mb.getCodematiere());
		CopyUtil.copieMatiereVersMatiereBean(mb, mat);
	}


	public void initGroupeMatiere(GroupeMatiereBean gmb) {
		Groupematiere gm=getGestionmatiere().rechercherGroupeMatiere(gmb.getLibelle());
		CopyUtil.copieGroupeMatiereVersGroupeMatiereBean(gmb, gm);
	}


	/**
	 * retourne la liste de toutes les candidatures enregistr�es
	 * @return
	 */
	public List<DossierBean> listedossiers(){
		List<PreInscription> result=null;
		result= getGestionInscription().listerPreInscription(getAnneeAcEncours());
		return CopyUtil.listPreinscriptionTolistdossierBean(result);
	}
	
	/**
	 * Retourne la liste de toutes les candidatures accept�es
	 * @return
	 */
	public List<DossierBean> listedossiersAcceptes(){
		List<PreInscription> result=null;
		result= getGestionInscription().PreinscriptionAccepte(getAnneeAcEncours());
		return CopyUtil.listPreinscriptionTolistdossierBean(result);
	}
	
	
	public List<SalleBean> listerSalles() {
		List<Salle> salles= getGestionressource().listerSalle();
		return CopyUtil.listSalleTolistSalleBean(salles);
	}
	


	/**
	 * Liste des types de salles d�ja enregistr�s dans la BD
	 * @return
	 */
	public List<TypeSalleBean> listerTypesalle(){
		 
		List<TypeSalle> liste=getGestionressource().listerTypesSalles();
		
		return CopyUtil.listeTypeSalleVersTypeSalleBean(liste);
	}
	
	
	/**
	 * Liste des options enregistr�es
	 * @return la liste
	 */
	public List<OptionBean> listerOptions(){
		List<OptionBean> liste=new ArrayList<OptionBean>();
		List<OptionSerie> listo;
		listo=getGestionEtablissement().listerOptions();
		
		if(listo==null) return liste;
		if(listo.isEmpty()) return liste;
		
		return CopyUtil.listeOptionVerslisteOptionBean(listo);
	}
	
	/**
	 * liste des niveaux enregistr�s
	 * @return la liste
	 */
	public List<NiveauBean> listerNiveaux(){
		List<NiveauBean> liste=new ArrayList<NiveauBean>();
		List<Niveau> listn=new ArrayList<Niveau>();
		listn=getGestionEtablissement().listerNiveaux();
		
		if(listn==null) return liste;
		if(listn.isEmpty()) return liste;
		
		return CopyUtil.listeNiveauTolisteNiveauBean(listn);
	}
	
	public List<NiveauBean> listerNiveauxB(){
		List<NiveauBean> liste=new ArrayList<NiveauBean>();
		List<Niveau> listn=new ArrayList<Niveau>();
		listn=getGestionEtablissement().listerNiveauxB();
		
		if(listn==null) return liste;
		if(listn.isEmpty()) return liste;
		
		return CopyUtil.listeNiveauTolisteNiveauBeanB(listn);
	}
	
	/**
	 * Liste les differents types de tranches d�ja enregistr�s
	 * @return la liste des types de transches pr�sents dans la base
	 */
	public List<String> listeTypeTranches(){
		return getGestionInscription().listeTypesTranches();
	}


	public List<VersementBean> listeVersementsEnregistres(Date datedebut,Date datefin) throws JPAException, ClassToBeanCopyException{
		List<Versement> versements=getGestionInscription().listerVersementsEnregistres(datedebut,datefin,getAnneeAcEncours());
		return CopyUtil.listVersementTolistVersementBean(versements);
	}
	
	public List<VersementBean> listeVersementsClasse(String classe) throws JPAException, ClassToBeanCopyException{
		List<Versement> versements=getGestionInscription().listerVersementsClasse(classe,getAnneeAcEncours());
		return CopyUtil.listVersementTolistVersementBean(versements);
	}

	/**
	 * liste des cycles enregistr�s
	 * @return la liste des cycles la!!
	 */
	public List<CycleBean> listerCycles(){
		List<CycleBean> liste=new ArrayList<CycleBean>();
		List<Cycle> listc=new ArrayList<Cycle>();
		listc=getGestionEtablissement().listerCycles();
		
		if(listc==null) return liste;
		if(listc.isEmpty()) return liste;
		
		return CopyUtil.listeCycleVerslisteCycleBean(listc);
	}


	
	public List<EleveBean> listerEleveProvisoireClasse(String codeclasse){
		List<EleveBean> result=CopyUtil.listEleveTolistEleveBean(getGestionEleve().listerProvisoireElevesClasse(codeclasse, getAnneeAcEncours()));
				
		return result;	
	}
	

	public List<EleveBean> listerElevesinscrits(String codeclasse){
		List<EleveBean> result=null;
		result=CopyUtil.listEleveTolistEleveBean(getGestionEleve().listeEleveConfirme(codeclasse, getAnneeAcEncours()));
		return result;
	}
	
	public List<EleveBean> listerElevesnoninscrits(String codeclasse){
		List<EleveBean> result=CopyUtil.listEleveTolistEleveBean(getGestionEleve().listeEleveNonConfirme(codeclasse, getAnneeAcEncours()));
		
		return result;
	}

	
	/**
	 * Liste les parametres de tranches enregistr�s pour une salle de classe
	 * @param codeclasse code de la classe
	 * @return la liste de tous les parametres
	 */
	public List<TrancheBean> listeTranchesClasse(String codeclasse){
		return CopyUtil.listParamTrancheTolistTrancheBean(getGestionInscription().listeTranchesClasse(codeclasse, getAnneeAcEncours()));
	}
	
	
	
	
	public List<ClasseBean> listeclasses(){
		List<Object[]> result=null;
		int effectif = 0;
		List<ClasseBean> liste=new ArrayList<ClasseBean>();
		List<TrancheBean> listetra= new ArrayList<TrancheBean>();
		List<VersementBean> listever= new ArrayList<VersementBean>();
		String nomens;
		ClasseBean classb = null;

		try{
			result= getGestionressource().listerClasseWithSize(getAnneeAcEncours());
			
			//result= getGestionressource().listerClasseCours(getAnneeAcEncours());
			
			for(int i=0;i<result.size();i++){
				float total=0;   // total tranche d'une classe
				float totalverse=0;  // total versement d'une classe
//				liste.add(new ClasseBean());
				 effectif = getGestionressource().effectif(getAnneeEncours(),(Classe) result.get(i)[0]);
				
				 if(effectif!=0){
				 Classe clas = (Classe) result.get(i)[0];
				  classb= new ClasseBean();
				 classb.setCodeClasse(clas.getCodeclasse());
				 
					
					//Dans le cas o� la classe poss�de un enseigant titulaire, on met � jour son nom et son code au sein du bean
					if((clas.getTitulaires()!=null) &&(!clas.getTitulaires().isEmpty())){
						classb.setCodeEnseignantTitulaire(clas.getTitulaires().get(0).getEnseignant().getCodeenseignant());
						
						nomens=clas.getTitulaires().get(0).getEnseignant().getNomens();
						
						if(clas.getTitulaires().get(0).getEnseignant().getPrenomens()!=null && !clas.getTitulaires().get(0).getEnseignant().getPrenomens().isEmpty()){
							nomens=nomens+" "+clas.getTitulaires().get(0).getEnseignant().getPrenomens();
						}
						classb.setNomenseignantTitulaire(nomens);
					}
					
					listetra=CopyUtil.listParamTrancheTolistTrancheBean(getGestionInscription().listeTranchesClasse(clas.getCodeclasse(), getAnneeAcEncours()));
					
					if(listetra!=null){
						
						for(int j=0;j<listetra.size();j++){
							total+=listetra.get(j).getMontant();
						}
					}
					
					listever=CopyUtil.listVersementTolistVersementBean(getGestionInscription().listerVersementsClasse(clas.getCodeclasse(),getAnneeAcEncours()));
					if(listever!=null){

					for(int k=0;k<listever.size();k++){
						totalverse+=listever.get(k).getMontant();
					}
					}
						
					classb.setContenanceActuelle(effectif);
					
					classb.setSommeattendue(total*effectif);
					
					classb.setSommepercue(totalverse);
					
					classb.setDelegue1(clas.getDelegue1());
					classb.setDelegue2(clas.getDelegue2());
					classb.setEffectifMax(clas.getContenancemax());
					classb.setLibelle(clas.getLibelle());
					classb.setSalleDefaut(clas.getSalleDefaut());
					if(clas.getOptionserie()!=null){
						classb.setOption(clas.getOptionserie().getCodeoption());
						classb.setNiveau(clas.getOptionserie().getNiveau().getLibelle());
					}
				liste.add(classb);
				 }
			}
			
			
		}
		catch(Exception e){
			Repertoire.logFatal("listing classes option error", this.getClass(), e);
			
		}
		
		return liste;
	}
	


	public List<EnseignementBean> listerEnseignements() {
		List<Enseignement> etabs=getGestionEtablissement().listerEnseignements();
		return CopyUtil.listeEnseignementVersListeEnseignementBean(etabs);
	}


	public List<Object[]> listerCodesEtab() {
		
		return getGestionEtablissement().listerCodesEtablissement();
	}


	public List<String> listerCodesEnseignements() {
		return getGestionEtablissement().listercodesEnseignements();
	}


	public List<Object[]> listerCodesCycles() {
		List<Cycle> lc=getGestionEtablissement().listerCycles();
		List<Object[]> liste=new ArrayList<Object[]>();
		for(int i=0;i<lc.size();i++){
			liste.add(new Object[2]);
			liste.get(i)[0]=lc.get(i).getCodecycle();
			liste.get(i)[1]=lc.get(i).getLibelle();
		}
		
		return liste;
	}


	/**
	 * Liste les codes et libell�s des sections
	 * @return
	 */
	public List<Object[]> listerCodessections() {
		return getGestionEtablissement().listerCodesSections();
	}


	public List<SectionBean> listerSections() {
		List<Section> etabs=getGestionEtablissement().listerSections();
		return CopyUtil.listeSectionVersListeSectionBean(etabs);
	
	}


	public List<SequenceBean> listerSequences() {
		return CopyUtil.listeSequenceToListeSequenceBean(getGestionEtablissement().listerSequences(getAnneeAcEncours()));	

	}

	
	
	public List<SequenceBean> listerSequencesB() {
		Etablissement e=getGestionutilisateur().rechercherGestionnaire(getInfosSession().getLoginadmin());
		if(e!=null && e.getPass_gest().compareTo(getInfosSession().getPassadmin())==0){
		System.out.println("je suis chez l'administrateur");
		return CopyUtil.listeSequenceToListeSequenceBean(getGestionEtablissement().listerSequences(getAnneeAcEncours()));
		}else{
		System.out.println("je suis chez un utilisateur simple");
		return CopyUtil.listeSequenceToListeSequenceBeanN(getGestionEtablissement().listerSequences(getAnneeAcEncours()));	
		}
	}


	public List<EtablissementBean> listerEtablissements() {
		List<Etablissement> etabs=getGestionEtablissement().listerEtablissements();
		return CopyUtil.listeEtablissementVersListeEtablissementBean(etabs);
	}


	public List<PersonnelBean> listepersonnel() {
		List<Personnel> pers =getGestionressource().listerPersonnels();
		
		return CopyUtil.listePersonnelVersListePersonnelBean(pers);
	}


	public List<EnseignantBean> listeenseignants() {
		List<Enseignant> ens=getGestionressource().listerEnseignants();
		return CopyUtil.listeEnseignantVersListeEnseignantBean(ens);
	}


	public List<MatiereBean> listematieres() {
		List<Matiere> liste=getGestionmatiere().listerMatieres();
		return CopyUtil.listeMatiereVerslisteMatiereBean(liste);
	}


	public List<EvenementBean> listerOccupationsSalle(String codesalle){
		Salle s=getGestionressource().listerOccupationsSalle(codesalle, getAnneeAcEncours()).get(0);
		List <EvenementBean> listr=new ArrayList<EvenementBean>();
		for(int i=0;i<s.getProgrammations().size();i++){
			listr.add(new EvenementBean(s.getProgrammations().get(i).getLibelle(),s.getProgrammations().get(i).getDatedebut(),s.getProgrammations().get(i).getDatefin()));
		}
		
		return listr;
	}


	public List<TrimestreBean> listetrimestres() {
		return CopyUtil.listeTrimestreToListeTrimestreBean(getGestionEtablissement().listerTrimestres(getAnneeAcEncours()));
	}


	public List<VersementBean> listerTranchesVersees(String matricule) throws ClassToBeanCopyException {
		return CopyUtil.listVersementTolistVersementBean(getGestionInscription().listerVersementsEleve(matricule, getAnneeAcEncours()));
	}


	/**
	 * Enregistre une inscription d'un nouvel �l�ve
	 * @return true si tout se passe bien, false sinon
	 * @throws EleveDSCompletException 
	 * @throws MontantInscriptionInsuffisant 
	 * @throws ElementNOtFoundException 
	 * @throws JPAException 
	 * @throws TotalVersementExcedantException 
	 * @throws DroitsScolairesNonDefinis 
	 */
	public String saveInscription(float montantVerse, String matricule, String typeversement,Date dateVersement) throws ElementNOtFoundException, MontantInscriptionInsuffisant, EleveDSCompletException, JPAException, DroitsScolairesNonDefinis, TotalVersementExcedantException{
		// dans l'EJB, r�partir le montant suivant les parametres tranches de la classe, jusqu'a un certain niveau.
		// verifie que le user est un gestionnaire d'etablissement
		Etablissement e=getGestionutilisateur().rechercherGestionnaire(getInfosSession().getLoginadmin());
		if(e!=null && e.getPass_gest().compareTo(getInfosSession().getPassadmin())==0){
		return getGestionInscription().enregistrerDroitScolaire(montantVerse, matricule, typeversement, dateVersement, getAnneeAcEncours(),getInfosSession().getLoginadmin());
		}else{
		Utilisateur util= getGestionutilisateur().rechercherUtilisateur(getIduser());
		return getGestionInscription().enregistrerDroitScolaire(montantVerse, matricule, typeversement, dateVersement, getAnneeAcEncours(),util.getLogin());
		}
		
	}
	
	public int saveCertificat(int ideleve, String codeclasse) throws ElementNOtFoundException{
		// dans l'EJB, r�partir le montant suivant les parametres tranches de la classe, jusqu'a un certain niveau.
		// verifie que le user est un gestionnaire d'etablissement
		return getGestionInscription().enregistrerCertificat(ideleve,  codeclasse, getAnneeAcEncours());
	}
	
	
	/**
	 * Enregistre le versement de droits scolaires pour un �l�ve
	 * @param montantVerse montant vers� par l'�l�ve
	 * @param matricule matricule de l'�l�ve
	 * @param dateVersement date de versement
	 * @throws ElementNOtFoundException
	 * @throws MontantInscriptionInsuffisant
	 * @throws EleveDSCompletException
	 * @throws JPAException 
	 * @throws TotalVersementExcedantException 
	 * @throws DroitsScolairesNonDefinis 
	 */
	public String saveDroitScolaire(float montantVerse, String matricule,String typeversement,Date dateVersement) throws ElementNOtFoundException, MontantInscriptionInsuffisant, EleveDSCompletException, JPAException, DroitsScolairesNonDefinis, TotalVersementExcedantException{
		
		// dans l'EJB, r�partir le montant suivant les parametres tranches de la classe, jusqu'a un certain niveau.
				// verifie que le user est un gestionnaire d'etablissement
		Etablissement e=getGestionutilisateur().rechercherGestionnaire(getInfosSession().getLoginadmin());
		if(e!=null && e.getPass_gest().compareTo(getInfosSession().getPassadmin())==0){
		return getGestionInscription().enregistrerDroitScolaire(montantVerse, matricule, typeversement, dateVersement, getAnneeAcEncours(),getInfosSession().getLoginadmin());
		}else{
		Utilisateur util= getGestionutilisateur().rechercherUtilisateur(getIduser());
		return getGestionInscription().enregistrerDroitScolaire(montantVerse, matricule, typeversement, dateVersement, getAnneeAcEncours(),util.getLogin());
		}
	}

	
	public void saveSalle(String codeSalle, int capacite, String description,int type,String libelle) throws ages.exception.DuplicateKeyException, ElementNOtFoundException{
		
		getGestionressource().enregistrerSalle(codeSalle, capacite, description, type,libelle);
	}
	
	/**
	 * Enregistre un dossier de candidature
	 * @param dossier bean de l'IHM recup�r�
	 * @return String success si ok, error si pb 
	 * @throws ElementNOtFoundException 
	 */
	public String savedossier(DossierBean dossier) throws ElementNOtFoundException{
		String result="";
		//String prenom=(dossier.getPrenom()).substring(0,1).toUpperCase()+(dossier.getPrenom()).substring(1);
		result= getGestionInscription().createPreInscription(dossier.getAdresse(), getAnneeAcEncours(), dossier.getAnneeAncienEtablissement(), dossier.getCodedossier(), dossier.getDateNaissance(),dossier.getDatePreinscription(),dossier.getEmail(), dossier.getEmailMere() ,dossier.getEmailPere(), dossier.getEtat(),getIduser(), dossier.getNationalite(), dossier.getNiveauDemande(), dossier.getNom().toUpperCase(), dossier.getAncienEtablissement(), dossier.getNomMere(), dossier.getNomPere(), dossier.getNumeroPayement(), dossier.getOptionDemande(), dossier.getPassword(), dossier.getPrenom(),
				dossier.getProfessionMere(), dossier.getProfessionPere(),dossier.getSexe(), dossier.getTelephone(), dossier.getTelephoneMere(), dossier.getTelephonePere(), dossier.getClasseAncienEtablissement(), dossier.getNomTuteur(), dossier.getProfessionTuteur(), dossier.getEmailTuteur(), dossier.getTelephoneTuteur(),dossier.getLieuNaissance(),dossier.getBoitePostale(),dossier.getDernieremoyenne(),dossier.getMatricule(),dossier.getSommeverse());
				
		return result;
	}


	/**
	 * Enregistre une classe
	 * Rends persistant le code, l'option, le libell�, la contenance maximale et l'ann�e acad�mique
	 * @param salleDefaut 
	 * @param classe
	 * @return
	 * @throws ElementNOtFoundException 
	 * @throws ages.exception.DuplicateKeyException 
	 * @throws AnneeEnCoursNonDefinieException  aucune ann�e en cours param�tr�e
	 */
	public void saveClasse(String codeclasse,  int contenancemax, String libelle,String option, String salleDefaut) throws ElementNOtFoundException, ages.exception.DuplicateKeyException, AnneeEnCoursNonDefinieException{
		getGestionressource().enregistrerClasse(codeclasse, getAnneeAcEncours(), option, contenancemax, libelle,salleDefaut);
	}


	/**
	 * Enregistre une optionSerie (option ou serie, comme allemand ou espagnol ou tronc commun)
	 * @param codeOption
	 * @param libelle
	 * @param niveau
	 * @throws ages.exception.DuplicateKeyException
	 * @throws ElementNOtFoundException
	 */
	public List<String> saveOptionSerie(String libelle,List<String> niveaux){
		return getGestionEtablissement().enregistrerOptionSeries(libelle,niveaux);
	}


	/**
	 * Enregistrement d'un etablissement
	 * 
	 * @param codeetablissement
	 * @param nom
	 * @param logingest
	 * @param passgest
	 * @throws ages.exception.DuplicateKeyException
	 */
	public void saveEtablissement(String codeetablissement, String nom,
			String logingest, String passgest) throws ages.exception.DuplicateKeyException {
		getGestionEtablissement().enregistrerEtablissement(codeetablissement, nom, logingest, passgest);
		
	}


	/**
	 * enregistrement d'un nouvel enseignement au sens primaire, maternel...
	 * @param libelleens
	 * @param description
	 * @param type
	 * @throws ElementNOtFoundException 
	 * @throws ages.exception.DuplicateKeyException 
	 */
	public void saveEnseignement(String libelleens, String description,
			String type) throws ages.exception.DuplicateKeyException, ElementNOtFoundException {
		getGestionEtablissement().enregistrerEnseignement(libelleens, description, type);
		
	}


	/**
	 * Enregistrement d'une section ex: anglophone, francophone
	 * @param codesection
	 * @param description
	 * @param libelle
	 * @param enseignement
	 * @throws ages.exception.DuplicateKeyException 
	 * @throws ElementNOtFoundException 
	 */
	public void saveSection(String codesection, String description,
			String libelle, String enseignement) throws ElementNOtFoundException, ages.exception.DuplicateKeyException {
		getGestionEtablissement().enregistrerSection(codesection, description,libelle, enseignement);
		
	}


	public void saveCycle(String codeCycle, String libelle, String codesection) throws ElementNOtFoundException, ages.exception.DuplicateKeyException {
		getGestionEtablissement().enregistrerCycle(codeCycle, libelle, codesection);
		
	}


	public String saveAnnee(Date datedebut, Date datefin) throws ages.exception.DuplicateKeyException, ChevauchementDateException {
		String res= getGestionEtablissement().enregistrerAnneeAcademique(datedebut, datefin);
		FacesContext context = FacesContext.getCurrentInstance();
	    SessionBean session = (SessionBean) context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{sessionBean}", SessionBean.class).getValue(context.getELContext());
	    
	    resetAnneeEncours(session.getAnneeacademique());
	    return res;
	}


	public void saveParametreTranche(String codeclasse,String type, float montant, Date delaivers) throws ElementNOtFoundException, DuplicateKeyException{
		
		getGestionInscription().enregistrerParametreTranche(type, codeclasse, getAnneeAcEncours(), montant, delaivers);
		
	}


	public void saveNiveau(String codeNiveau, String libelle, String codeCycle) throws ElementNOtFoundException, ages.exception.DuplicateKeyException {
		getGestionEtablissement().enregistrerNiveau(codeNiveau, libelle, codeCycle);
		
	}


	public void modifierSalle(String codeSalle, int capacite, String description,int type,String libelle) throws ElementNOtFoundException{
		getGestionressource().modifierSalle(codeSalle, libelle, type, capacite, description);
	}
	
	public void modifierClasse(String codeclasse,  int contenancemax, String libelle,String option, String salleDefaut) throws ElementNOtFoundException{
		getGestionressource().modifierClasse(codeclasse, getAnneeAcEncours(), option, contenancemax, libelle,salleDefaut);
	}

	public void modifyEtablissement(String codeetablissement, String nom,
			String acronyme, String devise, String logo, String type,
			String codepostal, String email, String telephone, String siteweb,
			String region, String departement, String arrondissement,
			String adresse, String pays, String devisepays) throws ElementNOtFoundException {
		getGestionEtablissement().modifierEtablissement(codeetablissement, nom, acronyme, devise, logo, type, codepostal, email, telephone, siteweb, region, departement, arrondissement, adresse,pays,devisepays);
	}


	public void modifyEnseignement(String libelleens, String description,String type) throws ElementNOtFoundException {
		getGestionEtablissement().modifierEnseignement(libelleens, description,type);
		
	}


	public void modifySection(String codesection, String description,
			String libelle, String enseignement) throws ages.exception.DuplicateKeyException, ElementNOtFoundException {
		getGestionEtablissement().modifierSection(codesection, description,
				libelle,enseignement);
		
	}


	public void modifyCycle(String codeCycle, String libelle, String codesection) throws ElementNOtFoundException {
		getGestionEtablissement().modifierCycle(codeCycle, libelle,codesection);
		
	}


	public void modifyOption(java.lang.String codeOption,
			java.lang.String libelle, java.lang.String niveau) throws ElementNOtFoundException {
		getGestionEtablissement().modifierOption(codeOption, libelle, niveau);
		
	}


	public void modifyNiveau(String codeNiveau, String libelle, String codeCycle) throws ElementNOtFoundException {
		getGestionEtablissement().modifierNiveau(codeNiveau,libelle,codeCycle);
		
	}


	/**
	 * supprime un dossier de candidature
	 * @param dossier bean de l'IHM recup�r�
	 * @return true si la suppression se passe bien, false sinon. 
	 * @throws ElementNOtFoundException 
	 */
	public boolean modifydossier(DossierBean dossier) throws ElementNOtFoundException{
		boolean result=false;
		result= getGestionInscription().modifierInscription(dossier.getAdresse(), dossier.getAnneeAncienEtablissement(), dossier.getCodedossier(), dossier.getDateNaissance(), dossier.getDatePreinscription(), dossier.getEmail(), dossier.getEmailMere(), dossier.getEmailPere(), dossier.getNationalite(), dossier.getNiveauDemande(), dossier.getNom(), dossier.getAncienEtablissement(), dossier.getNomMere(), dossier.getNomPere(), dossier.getNumeroPayement(), dossier.getOptionDemande(), dossier.getPassword(), dossier.getPrenom(), dossier.getProfessionMere(), dossier.getProfessionPere(),dossier.getSexe(), dossier.getTelephone(), dossier.getTelephoneMere(), dossier.getTelephonePere(), dossier.getClasseAncienEtablissement(), dossier.getNomTuteur(), dossier.getProfessionTuteur(), dossier.getEmailTuteur(), dossier.getTelephoneTuteur(), dossier.getLieuNaissance(), dossier.getBoitePostale(),dossier.getDernieremoyenne(),dossier.getMatricule(),dossier.getSommeverse());
		return result;
	}


	public void modifierPersonnel(String codepersonnel, String nom,
			String prenom, String adresse, String email, String telephone,
			String civilite, Date datearrivee, 
			String fonction, String sexe) throws ElementNOtFoundException {
		getGestionressource().modifierPersonnel(codepersonnel, nom,	prenom, adresse, email,telephone,civilite, datearrivee, fonction, sexe);
	}


	public void modifierParametreTranche(String codeClasse, int id,
			String type, float montant, Date delai) throws ElementNOtFoundException {
		getGestionInscription().modifierParametreTranche(type, codeClasse, getAnneeAcEncours(), montant, delai, id);
	}


	public void deleteSection(String codesection) throws ElementNOtFoundException {
		getGestionEtablissement().supprimerSection(codesection);
		
	}


	public void deleteNiveau(String codeNiveau) throws ElementNOtFoundException {
		getGestionEtablissement().supprimerNiveau(codeNiveau);
		
	}


	public void deleteOption(java.lang.String codeOption) throws ElementNOtFoundException {
		getGestionEtablissement().supprimerOption(codeOption);
		
	}


	public void deleteCycle(String codeCycle) throws ElementNOtFoundException {
		getGestionEtablissement().supprimerCycle(codeCycle);
		
	}


	public void deleteEnseignement(String libelleens) throws ElementNOtFoundException {
		getGestionEtablissement().supprimerEnseignement(libelleens);
		
	}


	public void deleteParametreTranche(String codeclasse,int id) throws ElementNOtFoundException {
		getGestionInscription().supprimerParametreTranche(id, codeclasse, getAnneeAcEncours());
		
	}


	/**
	 * supprime un dossier de candidature
	 * @param dossier bean de l'IHM recup�r�
	 * @return true si la suppression se passe bien, false sinon. 
	 */
	public boolean deletedossier(String dossier){
		boolean result=false;
		result= getGestionInscription().supprimerPreinscription(dossier);
		return result;
	}


	public void deleteSalle(String codesalle) throws ElementNOtFoundException{
		getGestionressource().supprimerSalle(codesalle);
	}


	public void deleteClasse(String codeclasse) throws ElementNOtFoundException{
		getGestionressource().supprimerClasse(codeclasse, getAnneeAcEncours());
	}


	public void deleteEtablissement(String codeetablissement) throws ElementNOtFoundException {
		getGestionEtablissement().supprimerEtablissement(codeetablissement);
		
	}



	public void deleteSequence(int numero) throws ElementNOtFoundException {
		getGestionEtablissement().supprimerSequence(numero,getAnneeAcEncours());
	}


	public void supprimerEnseignant(String codeenseignant) throws ElementNOtFoundException {
		getGestionressource().supprimerEnseignant(codeenseignant);
		
	}


	public void supprimerPersonnel(String codepersonnel) throws ElementNOtFoundException {
		getGestionressource().supprimerPersonnel(codepersonnel);
		
	}


	public void deleteTypeSalle(int id) throws ElementNOtFoundException {
		getGestionressource().supprimerTypeSalle(id);
	}


	public void supprimerEleve(String matricule) throws ElementNOtFoundException{
		getGestionEleve().supprimerEleve(matricule, getAnneeAcEncours());
	}


	public void supprimerMatiere(String codematiere) throws ElementNOtFoundException {
		getGestionmatiere().supprimerMatiere(codematiere);
	}


	public void supprimerGroupeMatiere(String libelle) throws ElementNOtFoundException {
		getGestionmatiere().supprimerGroupeMatiere(libelle);
	}


	/**
	 * Assigner une classe a un ensemble de dossiers
	 * @param listedossiers
	 * @param codeclasse
	 * @return
	 */
	public List<String> assignerClasse(List<String> listedossiers,String codeclasse){
		
		return getGestionInscription().affecterClasse(listedossiers, codeclasse,getAnneeAcEncours());						
		
	}


	/**
	 * Retourne l'ann�e acad�mique en cours, cele d�finie par l'administrateur
	 * @return
	 */
	public String getAnneeEncours() {
		 FacesContext context = FacesContext.getCurrentInstance();
		   SessionBean session = (SessionBean) context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{sessionBean}", SessionBean.class).getValue(context.getELContext());
		/*try {
			return getGestionEtablissement().rechercheAnneeEnCours(session.getAnneeacademique()).getAnneeacademique();
		} catch (AnneeEnCoursNonDefinieException e) {
			e.printStackTrace();
			return null;
		}*/
		return session.getAnneeacademique();
	}


	/**
	 * V�rifie qu'un �l�ve est d�ja inscrit pour l'ann�e en cours
	 * @param matricule Matricule de l'�l�ve dont on veut v�rifier l'inscription
	 * @return true si l'eleve de matricule pass� en param�tre est inscrit, false sinon
	 * @throws ElementNOtFoundException 
	 */
	public boolean estInscrit(String matricule) throws ElementNOtFoundException{
		return getGestionInscription().confirmeEleveOuiNon(matricule, getAnneeAcEncours());
	}


	/**
	 * Valide une candidature sans affectation dans une classe
	 * @param codedossier le dossier de candidature
	 * @param classe Boolean indiquent si le transfert vers une classe a �t� �ffectu�(true) ou non
	 * @return
	 * @throws UnknownUserException 
	 */
	public boolean validatedossier(String codedossier,String classe) throws UnknownUserException{
		
		return getGestionInscription().validerPreinscription(codedossier,classe,getLoginUser(),getAnneeAcEncours());
		
	}


	/**
	 * Valide une candidature
	 * @param dossier le dossier de candidature
	 * @param classe Boolean indiquent si le transfert vers une classe a �t� �ffectu�(true) ou non
	 * @return
	 * @throws ElementNOtFoundException 
	 */
	public boolean invalidatedossier(String codedossier) throws ElementNOtFoundException{
		
		return getGestionInscription().invaliderPreinscription(codedossier);
		
	}


	public void savePersonnel(String nom, String prenom,
			String adresse, String email, String telephone, String civilite,
			Date datearrivee, String fonction, String sexe) throws ElementNOtFoundException {
		getGestionressource().enregistrerPersonnel(nom, prenom,
				 adresse, email, telephone, civilite,
				datearrivee, fonction, sexe,getInfosSession().getCodeetablissement());
		
	}


	public String saveEnseignant( String nomens,
			String prenomens, String photo,String sexe,String adresse, String email, String telephone, String civilite, Date datearrivee, String loginens, String passens, String competences, boolean vacataire, BigDecimal salairehoraire, int travailmensuel) {
		
		photo=ConfigurationBean.imgpath+"/"+getInfosSession().getCodeetablissement()+"/enseignants/";
		return getGestionressource().enregistrerEnseignant(nomens,	prenomens,photo, sexe,adresse, email, telephone, civilite, datearrivee,loginens, passens, competences, vacataire,salairehoraire, travailmensuel);
		
	}

	public void modifyAnnee(String anneeacademique, Date datedebut, Date datefin) throws ElementNOtFoundException, ChevauchementDateException {
		getGestionEtablissement().modifierAnnee(anneeacademique,datedebut, datefin);
		try {
			initSession(getGestionutilisateur().rechercherUtilisateur(getIduser()),anneeacademique,null,null);
		} catch (EtablissementUndefinedException e) {
			Repertoire.addMessageerreur("Etablissement non d�fini");
			e.printStackTrace();
		}
	}


	public void deleteAnnee(String anneeacademique) throws ElementNOtFoundException {
		getGestionEtablissement().supprimerAnnee(anneeacademique);
		try {
			initSession(getGestionutilisateur().rechercherUtilisateur(getIduser()),anneeacademique,null,null);
		} catch (EtablissementUndefinedException e) {
			Repertoire.addMessageerreur("Etablissement non d�fini");
			e.printStackTrace();
		}
	}


	public void modifyTrimestre(int numero, Date datedebut, Date datefin,Date dateimpressionbulls) throws ElementNOtFoundException, ChevauchementDateException {
		getGestionEtablissement().modifierTrimestre(numero,datedebut,datefin,dateimpressionbulls,getAnneeAcEncours());
	}


	public void deleteTrimestre(int numero) throws ElementNOtFoundException {
		getGestionEtablissement().supprimerTrimestre(numero,getAnneeAcEncours());
	}


	public void modifySequence(int numero, Date datedebut, Date datefin, int trimestre) throws ElementNOtFoundException, ChevauchementDateException {
		getGestionEtablissement().modifierSequence(numero,datedebut,datefin,trimestre,getAnneeAcEncours());
	}


	public void saveTrimestre(int numero, Date datedebut, Date datefin) throws ElementNOtFoundException, ChevauchementDateException {
		getGestionEtablissement().enregistrerTrimestre(numero,datedebut,datefin,datefin,getAnneeAcEncours());
	}


	public void modifierEnseignant(String codeenseignant, String nomens,
			String prenomens, String photo, String sexe, String adresse,
			String email, String telephone, String civilite, Date datearrivee,
			String competences,BigDecimal salairehoraire, int travailmensuel) throws ElementNOtFoundException {

		getGestionressource().modifierEnseignant(codeenseignant,nomens,prenomens, photo,sexe,  adresse,
				email, telephone, civilite, datearrivee,competences,				salairehoraire,travailmensuel);
	}


	public void modifierStatut(String codeenseignant, boolean vacataire) throws ElementNOtFoundException {
		getGestionressource().modifierStatut(codeenseignant,vacataire);
	}


	public String saveEleve(String matricule,String nom, String prenom, Date dateNaissance,
			String lieuNaissance, String sexe, String nationalite,
			String photo, String codeClasse, boolean redoublant,
			String adresse, String email, String telephone,
			String boitePostale, String nomPere, String nomMere,
			String nomTuteur, String telephonePere, String telephoneMere,
			String telephoneTuteur, String professionPere,
			String professionMere, String professionTuteur, String emailPere,
			String emailMere, String emailTuteur, boolean ancien,
			String ancienEtablissement, String anneeAncienEtablissement,
			String classeAncienEtablissement) throws ElementNOtFoundException, DuplicateKeyException {
		
		photo=ConfigurationBean.imgpath+"/"+getInfosSession().getCodeetablissement()+"/eleves/";
		return getGestionEleve().enregistrerEleve(matricule,nom, prenom, dateNaissance, lieuNaissance, sexe, nationalite, photo, codeClasse, redoublant, adresse, email, telephone, boitePostale, nomPere, nomMere, nomTuteur, telephonePere, telephoneMere, telephoneTuteur, professionPere, professionMere, professionTuteur, emailPere, emailMere, emailTuteur, ancien, ancienEtablissement, anneeAncienEtablissement, classeAncienEtablissement,getAnneeAcEncours());
	
	}


	public void saveAtelier(String codeSalle, int capacite, String description,
			String libelle) throws ages.exception.DuplicateKeyException {
		getGestionressource().enregistrerAtelier(codeSalle, capacite, description, libelle);
	}


	public List<EleveBean> rechercherEleves(String motcle) {
		return CopyUtil.listEleveTolistEleveBean(getGestionEleve().rechercherEleves(motcle,getAnneeAcEncours()));
	}


	public List<EleveBean> rechercherElevesClasse(String motcle,
			String codeclasse) {
		return CopyUtil.listEleveTolistEleveBean(getGestionEleve().rechercherElevesClasse(motcle,codeclasse,getAnneeAcEncours()));
	}


	public List<EleveBean> rechercherElevesClasseFiltre(String motcle,
			String filtre, String codeclasse) {
		
		return CopyUtil.listEleveTolistEleveBean(getGestionEleve().rechercherEleves(motcle,codeclasse,filtre,getAnneeAcEncours()));
	}


	public List<EleveBean> rechercherElevesFiltre(String motcle, String filtre) {
		return CopyUtil.listEleveTolistEleveBean(getGestionEleve().rechercherElevesFiltre(motcle,filtre,getAnneeAcEncours()));
	}


	public void saveTypeSalle(String libelle, String description) throws ages.exception.DuplicateKeyException {
		getGestionressource().enregistrerTypeSalle(libelle, description);
	}


	public void modifierTypeSalle(int id, String libelle, String description) throws ElementNOtFoundException {
		getGestionressource().modifierTypeSalle(id,libelle, description);
	}


	public void modifierEleve(int ideleve, String matricule,String nom, String prenom, Date dateNaissance,
			String lieuNaissance, String sexe, String nationalite,
			String photo, String codeClasse, boolean redoublant,
			String adresse, String email, String telephone,
			String boitePostale, String nomPere, String nomMere,
			String nomTuteur, String telephonePere, String telephoneMere,
			String telephoneTuteur, String professionPere,
			String professionMere, String professionTuteur, String emailPere,
			String emailMere, String emailTuteur, boolean ancien,
			String ancienEtablissement, String anneeAncienEtablissement,
			String classeAncienEtablissement) throws ElementNOtFoundException, DuplicateKeyException {
		getGestionEleve().modifierEleve(ideleve,matricule,nom, prenom,dateNaissance,lieuNaissance, sexe, nationalite,photo, codeClasse, redoublant,
				adresse, email, telephone,boitePostale, nomPere, nomMere,nomTuteur, telephonePere, telephoneMere,telephoneTuteur, professionPere,
				professionMere, professionTuteur,emailPere,	emailMere,emailTuteur,ancien,ancienEtablissement, anneeAncienEtablissement,classeAncienEtablissement,getAnneeAcEncours());
	}


	public void migrateClass(String matricule, String nouvelleClasse) throws ElementNOtFoundException {
		getGestionEleve().migrateClass(matricule,nouvelleClasse,getAnneeAcEncours());
	}


	public void saveMatiere(String codematiere, String libelle,
			String description) throws ages.exception.DuplicateKeyException {
		getGestionmatiere().enregistrerMatiere(codematiere, libelle, description);
	}


	public void modifierMatiere(String codematiere, String libelle,
			String description) throws ElementNOtFoundException {
		getGestionmatiere().modifierMatiere(codematiere, libelle, description);
	}


	public List<EleveBean> listerEleveEnregle(String codeClasse) {
		return CopyUtil.listEleveTolistEleveBean(getGestionEleve().listerElevesERClasse(codeClasse, getAnneeAcEncours()));
	}
	
	public List<EleveBean> listerEleveEnregleA(String codeClasse) {
		return CopyUtil.listEleveTolistEleveBeanA(getGestionEleve().listerElevesERClasse(codeClasse, getAnneeAcEncours()),getGestionBulletin());
	}
	
	public List<EleveBean> listerEleveEnregleE(String codeClasse) {
		return CopyUtil.listEleveTolistEleveBeanE(getGestionEleve().listerElevesERClasse(codeClasse, getAnneeAcEncours()),getGestionInscription());
	}
	
	public List<EleveBean> listerEleveEnregleS(String codeClasse, Integer portee) {
		return CopyUtil.listEleveTolistEleveBeanS(getGestionEleve().listerElevesERClasse(codeClasse, getAnneeAcEncours()),getGestionBulletin(),portee);
	}
	
	public List<EleveBean> listerEleveEnregleT(String codeClasse, Integer portee) {
		return CopyUtil.listEleveTolistEleveBeanT(getGestionEleve().listerElevesERClasse(codeClasse, getAnneeAcEncours()),getGestionBulletin(),portee);
	}


	public List<EleveBean> listerEleveNonEnregle(String codeClasse) {
		return CopyUtil.listEleveTolistEleveBean(getGestionEleve().listerElevesNERClasse(codeClasse, getAnneeAcEncours()));
	}


	public List<EleveBean> listerElevesinscrits() {
		return CopyUtil.listEleveTolistEleveBean(getGestionEleve().listerElevesinscrits( getAnneeAcEncours()));
	}


	public void modifierAnimateurMatiere(String codematiere,
			String nouvelAnimateur) throws ElementNOtFoundException {
		getGestionmatiere().modifierAnimateurMatiere(codematiere,nouvelAnimateur);
	}


	public void modifierGroupeMatiere(String libelle, String description) throws ElementNOtFoundException {
		getGestionmatiere().modifierGroupeMatiere(libelle, description);
	}


	public void saveGroupeMatiere(String libelle, String description) throws ages.exception.DuplicateKeyException {
		getGestionmatiere().enregistrerGroupeMatiere(libelle, description);
	}


	public List<GroupeMatiereBean> listegroupesmatieres() {
		return CopyUtil.listeGroupeMatiereVerslisteGroupeMatiereBean(getGestionmatiere().listerGroupesMatieres());
	}


	public void initCours(CoursBean cb) {
		Cour cours=null;
		
		if(cb.getCodecours()!=0){
			cours=getGestionmatiere().rechercherCours(cb.getCodecours(),getAnneeAcEncours());
		}
		else{
			if((cb.getCodematiere()!=null&& !cb.getCodematiere().isEmpty()&&cb.getCodeclasse()!=null&& !cb.getCodeclasse().isEmpty())){
				cours=getGestionmatiere().rechercherCours(cb.getCodeclasse(),cb.getCodematiere(),getAnneeAcEncours());
			}
		}
		
		
		CopyUtil.copieCourToCoursBean(cb, cours);
	}

	
	public void modifierCours(int codecours, String libelle,
			String description,  String libellegm,int coeficient, String codeclasse) throws ElementNOtFoundException {
		this.getGestionmatiere().modifierCours(codecours, libelle,description, libellegm,coeficient,codeclasse,getAnneeAcEncours());
	}


	public void supprimerCours(int codecours) throws ElementNOtFoundException {
		this.getGestionmatiere().supprimerCours(codecours,getAnneeAcEncours());
	}


	public List<CoursBean> listecours() {
		return CopyUtil.listCourVerslisteCoursBean(getGestionmatiere().listerCours(getAnneeAcEncours()));
	}


	public List<CoursBean> listecoursN(String classe) {
		
		String an = null;
		List<Annee> ans=getGestionEtablissement().listerAnneesAcademiques();
		if(!ans.isEmpty()&& ans!=null){
			an=ans.get(ans.size()-2).getAnneeacademique();
		return CopyUtil.listCourVerslisteCoursBean(getGestionmatiere().listerCours(classe,an));
		}else{
		return CopyUtil.listCourVerslisteCoursBean(getGestionmatiere().listerCours(classe,getAnneeAcEncours()));
		}
	}
	
	public List<CoursBean> listecours(String classe) {
		return CopyUtil.listCourVerslisteCoursBean(getGestionmatiere().listerCours(classe,getAnneeAcEncours()));
	}


	public int saveCours(String codeclasse, String codematiere,
			String libelle, String description, String libellegm, int coeficient) throws ElementNOtFoundException {
		return getGestionmatiere().enregistrerCours(codeclasse, codematiere,
				libelle, description, libellegm, coeficient,getAnneeAcEncours());
	}
	
	public int copieCours(String codeclasse, String codematiere,
			String libelle, String description, String libellegm, int coeficient) throws ElementNOtFoundException {
		return getGestionmatiere().enregistrerCours(codeclasse, codematiere,
				libelle, description, libellegm, coeficient,getAnneeAcEncours());
	}


	public void modifierEnseignantCours(int codecours, String codeenseignant) throws ElementNOtFoundException {
		this.getGestionmatiere().modifierEnseignantCours(codecours,codeenseignant,getAnneeAcEncours());
	}


	public void saveSequence(int numero, Date datedebut, Date datefin,
			int trimestre) throws ages.exception.DuplicateKeyException, ElementNOtFoundException, NonUniqueResultException, ChevauchementDateException {
		getGestionEtablissement().enregistrerSequence(numero,datedebut,datefin,trimestre,getAnneeAcEncours());
	}


	public int nextNumeroTrimestre() {
		return getGestionEtablissement().nextNumeroTrimestre(getAnneeAcEncours());
	}


	public int nextNumeroSequence() {
		return getGestionEtablissement().nextNumeroSequence(getAnneeAcEncours());
	}
	
	public ScheduleModel listeprogrammations(Date datedebut, Date datefin){
		ScheduleModel modele=new DefaultScheduleModel();
		List<Programmation> programmations=getGestionProgrammation().listerProgrammations(datedebut,datefin,getAnneeAcEncours());
		
		if(programmations!=null){
			for(int i=0;i<programmations.size();i++){
				modele.addEvent(new DefaultScheduleEvent(programmations.get(i).getLibelle(),programmations.get(i).getDatedebut(), programmations.get(i).getDatefin()));
			}
		}
		return modele;
	}

	public int saveEvaluation(String libelle, String typeevaluation,
			String codematiere,String codeclasse, int codesequence,Date datedebut, Date datefin) throws ElementNOtFoundException, PourcentageEvaluationExedantException, CoursNonDefiniException {
	    return getGestionExamens().enregistrerEvaluation(libelle, typeevaluation, codematiere,codeclasse, codesequence,datedebut,datefin, getAnneeAcEncours());
	}


	public void deleteEvaluation(int codeevaluation) throws ElementNOtFoundException {
		getGestionExamens().supprimerEvaluation(codeevaluation,getAnneeAcEncours());
	}

	public void initialiseEvaluationBean(EvaluationBean eb) {
		Evaluation ev=getGestionExamens().rechercheEvaluation(eb.getCodeevaluation());
		CopyUtil.copieEvaluationToEvaluationBean(eb, ev);
	
	}

	public List<EvaluationBean> listeevaluations() {
		return CopyUtil.listEvaluationTolistEvaluationBean(getGestionExamens().listerEvaluation(getAnneeAcEncours()));
	}

	/**
	 * Modifier une evaluation
	 * @param codeevaluation code de l'evaluation
	 * @param libelle nouveau libelle
	 * @param typeevaluation nouveau type
	 * @param codematiere nouvelle matiere
	 * @param codeclasse nouvelle classe
	 * @param codesequence nouvelle sequence
	 * @param datedebut nouvelle date de debut
	 * @param datefin nouvelle date de fin
	 * @throws ElementNOtFoundException renvoy�e au cas o� levaluation n'existe pas
	 * @throws PourcentageEvaluationExedantException renvoy�e au cas o� la somme totale des coefficients, le nouveau coefficient compris d�passe les 100%
	 * @throws CoursNonDefiniException renvoy�e au cas ou le cours correspondant n' pas encore �t� d�fini
	 */
	public void modifierEvaluation(int codeevaluation, String libelle,
			String typeevaluation, String codematiere, String codeclasse, int codesequence, Date datedebut, Date datefin) throws ElementNOtFoundException, PourcentageEvaluationExedantException, CoursNonDefiniException {
		getGestionExamens().modifierEvaluation(codeevaluation, libelle,typeevaluation, codematiere,codeclasse, codesequence, datedebut, datefin,getAnneeAcEncours());
	}

	public void modifierTypeEvaluation(String typeevaluation, int coefficient,String description) throws ElementNOtFoundException {
		getGestionExamens().modifierTypeEvaluation(typeevaluation, coefficient, description);
	}

	/**
	 * Initialiser un bean type evaluation a partir de son id
	 * @param teb
	 */
	public void initialiseTypeEvaluationBean(TypeEvaluationBean teb) {
		TypeEvaluation tev=getGestionExamens().rechercheTypeEvaluation(teb.getTypeevaluation());
		CopyUtil.copieTypeEvaluationToTypeEvaluationBean(teb, tev);
	}

	
	/**
	 * Supprimer un type d'�valuation
	 * @param typeevaluation libell� du type � supprimer
	 * @throws ElementNOtFoundException renvoy�e au cas o� le type n'existe pas
	 */
	public void deleteTypeEvaluation(String typeevaluation) throws ElementNOtFoundException {
		getGestionExamens().supprimerTypeEvaluation(typeevaluation);
	}
	
	/**
	 * Sauvegarder un type d'�valuation
	 * @param typeevaluation libell� du type
	 * @param coefficient coefficient sur la note finale
	 * @param description description du type
	 * @throws ages.exception.DuplicateKeyException renvoy� i le type existe d�ja
	 */
	public void saveTypeEvaluation(String typeevaluation, int coefficient, String description) throws ages.exception.DuplicateKeyException {
		getGestionExamens().enregistrerTypeEvaluation(typeevaluation,coefficient, description);
	}
	
	/**
	 * Liste des types d'�valuation enregistr�s
	 * @return
	 */
	public List<TypeEvaluationBean> listerTypesEvaluations() {
		return CopyUtil.listTypeEvaluationTolistTypeEvaluationBean(getGestionExamens().listerTypesEvaluations());
	}

	/**
	 * Liste des ann�es acad�miques  enregistr�es jusqu'ici
	 * @return
	 */
	public List<AnneeBean> listerAnneesAcademiques() {
		List<Annee> ans=getGestionEtablissement().listerAnneesAcademiques();
		
		return CopyUtil.listeAnneeVersListeAnneeBean(ans);
	}

	/**
	 * Justifier l'absence de plusieurs �l�ves a une �valuation
	 * @param evaluation evaluation dite
	 * @param eleveschoisis �l�ves voulant de justifier
	 * @param motifabsence motif de l'absence
	 * @throws ElementNOtFoundException 
	 */
	public void savejustificationEvaluation(int evaluation,
			List<String> eleveschoisis, String motifabsence) throws ElementNOtFoundException {
		getGestionExamens().justifierAbsence(evaluation,eleveschoisis,motifabsence,getAnneeAcEncours());
	}
	
	/**
	 * Annuler la justification d'une absence � un examen
	 * @param evaluation evaluation dite
	 * @param eleveschoisis liste d'�l�ves dont on veut annuler la justification
	 * @throws ElementNOtFoundException 
	 */
	public void annulerJustification(int evaluation, List<String> eleveschoisis) throws ElementNOtFoundException {
		
		Etablissement e=getGestionutilisateur().rechercherGestionnaire(getInfosSession().getLoginadmin());
		if(e!=null && e.getPass_gest().compareTo(getInfosSession().getPassadmin())==0){
		getGestionExamens().annulerJustificationabeval(evaluation,eleveschoisis,getAnneeAcEncours(),getInfosSession().getLoginadmin());
		}else{
		Utilisateur util= getGestionutilisateur().rechercherUtilisateur(getIduser());
		getGestionExamens().annulerJustificationabeval(evaluation,eleveschoisis,getAnneeAcEncours(),util.getLogin());
		}
		
		
	}

	/**
	 * Liste les �valuations d'une classe pour une sequence
	 * @param codesequence sequence
	 * @param codeclasse classe
	 * @return la liste des �valuation programm�es pour la classe de code codeclasse, pendant la sequence codesequence
	 */
	public List<EvaluationBean> listeevaluations(int codesequence,
			String codeclasse) {
		return CopyUtil.listEvaluationTolistEvaluationBean(getGestionExamens().listerEvaluation(codesequence,codeclasse,getAnneeAcEncours()));
	}
	
	/**
	 * Liste les �valuations d'une sequence, pour une classe et une matiere donn�es
	 * @param codesequence
	 * @param codeclasse
	 * @param codematiere
	 * @return
	 */
	public List<EvaluationBean> listeevaluations(int codesequence,
			String codeclasse, String codematiere) {
		return CopyUtil.listEvaluationTolistEvaluationBean(getGestionExamens().listerEvaluation(codesequence,codeclasse,codematiere,getAnneeAcEncours()));
	}
	
	/**
	 * Liste des eleves ayant justifi� leurs absence a une evaluation
	 * @param codesequence
	 * @param evaluation
	 * @param codeclasse
	 * @return
	 */
	public List<EleveBean> listerElevesJustifies(int codesequence,
			int evaluation, String codeclasse) {
		//rechercher dans la base les gars qui ont justifi� leurs absences a levaluation
		return CopyUtil.listEleveTolistEleveBean(getGestionExamens().listerElevesJustifies(codesequence,codeclasse,evaluation,getAnneeAcEncours()));
	}

	public void initNotes(NotesBean notesBean) {
		List<CompositionBean> cbs=new ArrayList<CompositionBean>();
		Boolean absencejustifiee;
		Float note;
		notesBean.setEvaluation(new EvaluationBean());
		CopyUtil.copieEvaluationToEvaluationBean(notesBean.getEvaluation(), getGestionExamens().rechercheEvaluation(notesBean.getCodeevaluation()));
		
		List<Object[]> listeElevesEvalues=getGestionExamens().listerElevesNotesEvalues(notesBean.getCodeevaluation(),getAnneeAcEncours());
		System.out.println("la liste des eleves est:"+   listeElevesEvalues.size());
		for(int i=0;i<listeElevesEvalues.size();i++){
			cbs.add(new CompositionBean());
			cbs.get(i).setEleve(new EleveBean());
			CopyUtil.copieEleveToEleveBean((Eleve) listeElevesEvalues.get(i)[0], cbs.get(i).getEleve());
			absencejustifiee=(Boolean) listeElevesEvalues.get(i)[2];
			note=(Float) listeElevesEvalues.get(i)[1];
			if(note==null)
				cbs.get(i).setNote(0);
			else
				cbs.get(i).setNote(note);
						
			cbs.get(i).setAbsencejustifiee(absencejustifiee);
						
		}
		int size=listeElevesEvalues.size();
		List<Eleve> listenonEvalues=getGestionExamens().listerElevesNonEvalues(notesBean.getCodeevaluation(),getAnneeAcEncours());
		System.out.println("la liste des eleves non evqlue est:"+   listenonEvalues.size());
		if(listenonEvalues!=null){
			for(int i=0;i<listenonEvalues.size();i++){
				cbs.add(new CompositionBean());
				cbs.get(i+size).setEleve(new EleveBean());
				CopyUtil.copieEleveToEleveBean((Eleve) listenonEvalues.get(i), cbs.get(i+size).getEleve());
				cbs.get(i+size).setAbsencejustifiee(false);
				cbs.get(i+size).setNote(0);
			}
		}
		
		notesBean.setCompositions(cbs);
	}

	/**
	 * Initialise le bean NotesBean de la liste des notes des �l�ves pour une �valuation
	 * @param notesBean
	 */
	public void initNotesImport(NotesBean notesBean) {
		
		List<Object[]> listeElevesEvalues=getGestionExamens().listerElevesNotesEvalues(notesBean.getCodeevaluation(),getAnneeAcEncours());
		
		if(listeElevesEvalues.isEmpty())
			notesBean.setNotesEnregistrees(false);
		else
			notesBean.setNotesEnregistrees(true);
		
		List<CompositionBean> cbs=new ArrayList<CompositionBean>();
		Boolean absencejustifiee;
		Float note;
		notesBean.setEvaluation(new EvaluationBean());
		CopyUtil.copieEvaluationToEvaluationBean(notesBean.getEvaluation(), getGestionExamens().rechercheEvaluation(notesBean.getCodeevaluation()));
		
		for(int i=0;i<listeElevesEvalues.size();i++){
			cbs.add(new CompositionBean());
			cbs.get(i).setEleve(new EleveBean());
			CopyUtil.copieEleveToEleveBean((Eleve) listeElevesEvalues.get(i)[0], cbs.get(i).getEleve());
			absencejustifiee=(Boolean) listeElevesEvalues.get(i)[2];
			note=(Float) listeElevesEvalues.get(i)[1];
			if(note==null)
				cbs.get(i).setNote(0);
			else
				cbs.get(i).setNote(note);
						
			cbs.get(i).setAbsencejustifiee(absencejustifiee);
						
		}
		int size=listeElevesEvalues.size();
		List<Eleve> listenonEvalues=getGestionExamens().listerElevesNonEvalues(notesBean.getCodeevaluation(),getAnneeAcEncours());
		
		if(listenonEvalues!=null){
			for(int i=0;i<listenonEvalues.size();i++){
				cbs.add(new CompositionBean());
				cbs.get(i+size).setEleve(new EleveBean());
				CopyUtil.copieEleveToEleveBean((Eleve) listenonEvalues.get(i), cbs.get(i+size).getEleve());
				cbs.get(i+size).setAbsencejustifiee(false);
				cbs.get(i+size).setNote(0);
			}
		}
		
		notesBean.setCompositions(cbs);
		
	}
	
	
	/**
	 * Enregistrement des notes pour une evaluation
	 * @param compositions liste des eleves et leurs notes encapsul�es dans une classe cdomposition
	 * @param codeevaluation code de l'�valuation concern�e
	 * @throws ElementNOtFoundException renvoy�e si un des �l�ves ou l'�valuation n'est pas retrouv�e
	 */
	public String enregistrerNotes(List<CompositionBean> compositions,int codeevaluation) throws ElementNOtFoundException, Exception {
		final Hashtable<String,Float> notes=new Hashtable<String, Float>();
		for(int i=0;i<compositions.size();i++){
			// nous v�rifions ici si la note est comprise dans la fourchette voulue
			if(compositions.get(i).getNote()>20 || compositions.get(i).getNote()<0){
	       return "ECHEC";
			}
			
			notes.put(compositions.get(i).getEleve().getMatricule(), compositions.get(i).getNote());
		}
		// verifie que le user est un gestionnaire d'etablissement
		Etablissement e=getGestionutilisateur().rechercherGestionnaire(getInfosSession().getLoginadmin());
		if(e!=null && e.getPass_gest().compareTo(getInfosSession().getPassadmin())==0){
		return getGestionExamens().enregistrerNotes(notes,codeevaluation,getAnneeAcEncours(),getInfosSession().getLoginadmin());
		}else{
		Utilisateur util= getGestionutilisateur().rechercherUtilisateur(getIduser());
		return getGestionExamens().enregistrerNotes(notes,codeevaluation,getAnneeAcEncours(),util.getLogin());	
		}
	}


	/**
	 * Envoie d'email a un administrateur
	 * @param nom nom de l'emetteur
	 * @param email email de l'administrateur
	 * @param message message a envoyer
	 * @throws AddressException adresse invalide envoy�e
	 * @throws AdminstrateurNotFoundException aucun administrateur trouv�
	 */
	public void envoyerMessage(String nom, String email, String message) throws AddressException, AdminstrateurNotFoundException {
		getUtilitaire().envoyerMessage(nom, email, message);
	}

	/**
	 * Connexion d'un utilisateur
	 * @param userBean
	 * @return
	 * @throws EtablissementUndefinedException
	 */
	public Boolean connectUser(UserBean userBean) throws EtablissementUndefinedException {
		
		// verifie si le user est un administrateur (de toute l'application)
		//Pour cela recherche la configuration de l'application qui contient l'administrateur g�n�ral
		Configuration conf=getUtilitaire().rechercherConfiguration();
		
		
		if(conf!=null && conf.getLogin_admin().compareTo(userBean.getLogin())==0){
			// Cas o� l'utilisateur est l'administrateur g�n�ral
			
			initSession(conf,userBean.getAnnee());
			return true;
		}
		

		
		// verifie que le user est un gestionnaire d'etablissement
		Etablissement e=getGestionutilisateur().rechercherGestionnaire(userBean.getLogin());
		if(e!=null && e.getPass_gest().compareTo(userBean.getPassword())==0){
			initSession(e,userBean.getAnnee(),userBean.getLogin(),userBean.getPassword());			
			
			return true;
		}
		else{
			System.out.println("****************NON GESTIONNAIRE DETABLISSEMENT***********************************");
			System.out.println("***************************************************");
		}
		
		// Verifie si c'est un utilisateur local a un etablissement
		Utilisateur user=getGestionutilisateur().rechercherUtilisateur(userBean.getLogin());
		
		if(user==null|| user.getPassword().compareTo(userBean.getPassword())!=0){
			
			return false;
		}
		else{
			userBean.setLogged(true);
			initSession(user,userBean.getAnnee(),"","");
			return true;
		}
		
		
	}
	
	
	/**
	 * Copie la configuration des tranches d'une classe vers un ensemble d'autres classes.
	 * @param codeclasse
	 * @param classescibles
	 * @throws ElementNOtFoundException
	 */
	public void copierTranches(String codeclasse, List<String> classescibles) throws ElementNOtFoundException {
		getGestionInscription().copierTranches(codeclasse, classescibles,getAnneeAcEncours());
	}

	public List<EleveBean> listerElevesAbsents(int evaluation) {
		return CopyUtil.listEleveTolistEleveBean(getGestionExamens().listerElevesevalues(evaluation, getAnneeAcEncours()));
	}

	public void saveTypeSanction(String libelle, String description) throws DuplicateKeyException {
		getGestionDiscipline().saveTypeSanction(libelle,description);
	}

	public void modifierTypeSanction(int id, String libelle, String description) {
		getGestionDiscipline().modifierTypeSanction(id,libelle,description);
	}

	public void deleteTypeSanction(int id) throws ElementNOtFoundException {
		getGestionDiscipline().supprimerTypeSanction(id);
	}

	public void initialiseTypeSanctionBean(TypeSanctionBean tsb) {
		TypeSanction ts=getGestionDiscipline().rechercherTypeSanction(tsb.getId());
		CopyUtil.copieTypeSanctionVersTypeSanctionBean(tsb, ts);
	}

	public List<TypeSanctionBean> listerTypesanction() {
		return CopyUtil.listeTypeSanctionVersTypeSanctionBean(getGestionDiscipline().listerTypeSanctions());
	}

	public void initialiseSanctionBean(SanctionBean sanctionBean) {
		
		CopyUtil.copieSanctionVersSanctionBean(sanctionBean, getGestionDiscipline().rechercherSanction(sanctionBean.getIdsanction()));
	}


	public void saveSanction(String motif, int codetype, int duree,
			Date datedecision, Date dateeffet, List<String> elevescibles) throws ElementNOtFoundException {
		getGestionDiscipline().enregistrerSanction(motif, codetype, duree, datedecision,dateeffet,elevescibles,getAnneeAcEncours());
	}


	public void modifierSanction(int idsanction, String motif, int codetype,
			int duree, Date datedecision, Date dateeffet, boolean annule) throws ElementNOtFoundException {
		getGestionDiscipline().modifierSanction(idsanction,motif, codetype, duree, datedecision,dateeffet,annule,getAnneeAcEncours());
	}


	public void annulerSanction(int idsanction) {
		getGestionDiscipline().annulerSanction(idsanction);
	}

	public List<SanctionBean> listeSanctions() {
		
		return CopyUtil.listeSanctionToListeSanctionBean(getGestionDiscipline().listerSanctions(getAnneeAcEncours()));
	}


	public void saveAbsence(Date dateabsence, int justifie, int duree, String matriculeeleve) throws ElementNOtFoundException {
		getGestionDiscipline().enregistrerAbsence(dateabsence,justifie, duree,  matriculeeleve,getAnneeAcEncours());
	}


	public void supprimerAbsence(int codeabsence) throws ElementNOtFoundException {
		getGestionDiscipline().supprimerAbsence(codeabsence,getAnneeAcEncours());
	}


	public void modifierAbsence(int codeabsence, Date dateabsence, int duree, int justifie) throws ElementNOtFoundException {
		getGestionDiscipline().modifierAbsence(codeabsence, dateabsence,duree, justifie);
	}


	public void initialiseAbsenceBean(AbsenceBean absenceBean) {
		CopyUtil.copieAbsenceVersAbsenceBean(absenceBean, getGestionDiscipline().rechercheAbsence(absenceBean.getCodeabsence()));
	}


	public void supprimerRetard(int coderetard) throws ElementNOtFoundException {
		getGestionDiscipline().supprimerRetard(coderetard);
	}


	public void saveRetard(Date dateretard, int justifie, int duree,
			String matriculeeleve) throws ElementNOtFoundException {
		getGestionDiscipline().enregistrerRetard(dateretard, duree, justifie, matriculeeleve,getAnneeAcEncours());
	}


	public void modifierRetard(int coderetard, Date dateretard, int duree,
			int justifie) throws ElementNOtFoundException {
		getGestionDiscipline().modifierRetard(coderetard,dateretard,duree,justifie);
	}
	
	public void initialiseRetardBean(RetardBean rb){
		if(rb.getCoderetard()!=0){
			CopyUtil.copieRetardVersRetardBean(rb, getGestionDiscipline().rechercherRetard(rb.getCoderetard()));
		}
	}


	public List<RetardBean> listerRetardsClasse(String codeclasse) {
		return CopyUtil.listRetardTolistRetardBean(getGestionDiscipline().listerRetardsClasse(codeclasse,getAnneeAcEncours()));
	}


	public List<RetardBean> listerRetardsEleve(String matriculeeleve) {
		Annee an=null;
		try {
			an = getGestionEtablissement().rechercheAnneeEnCours("");
		} catch (AnneeEnCoursNonDefinieException e) {
			e.printStackTrace();
		}
		return CopyUtil.listRetardTolistRetardBean(getGestionDiscipline().listerRetardsEleve(matriculeeleve,getAnneeAcEncours(),an.getDateDebut(),an.getDateFin()));
	}


	public List<AbsenceBean> listerAbsencesEleve(String matriculeeleve) {
		Annee an=null;
		try {
			an = getGestionEtablissement().rechercheAnneeEnCours("");
		} catch (AnneeEnCoursNonDefinieException e) {
			e.printStackTrace();
		}
		return CopyUtil.listAbsenceTolistAbsenceBean(getGestionDiscipline().listerAbsencesEleve(matriculeeleve,getAnneeAcEncours(),an.getDateDebut(),an.getDateFin()));
	}


	public List<AbsenceBean> listerAbsencesClasse(String codeclasse) {
		return CopyUtil.listAbsenceTolistAbsenceBean(getGestionDiscipline().listerAbsencesClasse(codeclasse,getAnneeAcEncours()));
	}


	public List<DisciplineItem> rechercherJournalDisciplinaire(
			String matriculeeleve) {
		int totaldure=0;
		int totaljustifie=0;
		List<DisciplineItem> items=new ArrayList<DisciplineItem>();
		
		Annee an=null;
		try {
			an = getGestionEtablissement().rechercheAnneeEnCours(getAnneeAcEncours());
		} catch (AnneeEnCoursNonDefinieException e1) {
			e1.printStackTrace();
		}
		
		List<Absence> absences=getGestionDiscipline().listerAbsencesEleve(matriculeeleve, getAnneeAcEncours(),an.getDateDebut(),an.getDateFin());
		for(int i=0;i<absences.size();i++){
			totaldure+=absences.get(i).getDuree();
			totaljustifie+=absences.get(i).getJustifie();
		}
		items.add(new DisciplineItem());
		items.get(0).setLabel("Absences");
		items.get(0).setHeures(totaldure);
		items.get(0).setNombre(" Non Just:"+(totaldure-totaljustifie)+"    Just.:"+totaljustifie);
		
		List<Retard> retards=getGestionDiscipline().listerRetardsEleve(matriculeeleve, getAnneeAcEncours(),an.getDateDebut(),an.getDateFin());
		
		totaldure=0;
		totaljustifie=0;
		
		for(int i=0;i<retards.size();i++){
			totaldure+=retards.get(i).getDureeretard();
			totaljustifie+=retards.get(i).getJustifie();
		}
		items.add(new DisciplineItem());
		items.get(1).setLabel("Retards");
		items.get(1).setHeures(totaldure);
		items.get(1).setNombre(" Non Just.: "+(totaldure-totaljustifie)+ "    Just.:"+totaljustifie);
		
		List<Sanction> sanctions=getGestionDiscipline().listerSanctionsEleve(matriculeeleve,getAnneeAcEncours(),an.getDateDebut(),an.getDateFin());
		Hashtable<String,List<Integer>> sanct=new Hashtable<String, List<Integer>>();
		
		String type;
		for(int i=0;i<sanctions.size();i++){
			type=sanctions.get(i).getTypesanction().getLibelle();
					
			
			if(!sanct.containsKey(type)){
				List<Integer> val=new ArrayList<Integer>();
				val.add(0, 0);
				val.add(0, 0);
				sanct.put(type, val);
			}
			sanct.get(type).add(0, sanct.get(type).get(0)+1);
			sanct.get(type).add(1, sanct.get(type).get(1)+sanctions.get(i).getDureesanction());
		
		}
		
		int j=2;
		for (Enumeration<String> e = sanct.keys(); e.hasMoreElements();){
			type=e.nextElement();
			List<Integer> values=sanct.get(type);
			items.add(new DisciplineItem());
			items.get(j).setLabel(type);
			items.get(j).setHeures(values.get(1));
			items.get(j).setNombre(" Nombre: "+values.get(0));
			j++;
		}
		
		return items;
	}


	public List<DisciplineItem> rechercherJournalDisciplinaire(
			String matriculeeleve, String vue, int valeurvue) {
		
		Date datedebut=null;
		Date datefin=null;
		
		if(vue==null || vue.isEmpty())
			return null;
		
		if(vue.compareToIgnoreCase("sequentiel")==0){
			datedebut=getGestionEtablissement().rechercheSequence(valeurvue, getAnneeAcEncours()).getDatedebut();
			datefin=getGestionEtablissement().rechercheSequence(valeurvue, getAnneeAcEncours()).getDatefin();
		}
		else{
			if(vue.compareToIgnoreCase("trimestriel")==0){
				datedebut=getGestionEtablissement().rechercheTrimestre(valeurvue, getAnneeAcEncours()).getDatedebut();
				datefin=getGestionEtablissement().rechercheTrimestre(valeurvue, getAnneeAcEncours()).getDatefin();
			}
		}
		
		int totaldure=0;
		int totaljustifie=0;
		List<DisciplineItem> items=new ArrayList<DisciplineItem>();
		
		List<Absence> absences=getGestionDiscipline().listerAbsencesEleve(matriculeeleve, getAnneeAcEncours(),datedebut,datefin);
		for(int i=0;i<absences.size();i++){
			totaldure+=absences.get(i).getDuree();
			totaljustifie+=absences.get(i).getJustifie();
		}
		items.add(new DisciplineItem());
		items.get(0).setLabel("Absences");
		items.get(0).setHeures(totaldure);
		items.get(0).setNombre(" Non Just:"+(totaldure-totaljustifie)+"    Just.:"+totaljustifie);
		
		List<Retard> retards=getGestionDiscipline().listerRetardsEleve(matriculeeleve, getAnneeAcEncours(),datedebut,datefin);
		
		totaldure=0;
		totaljustifie=0;
		
		for(int i=0;i<retards.size();i++){
			totaldure+=retards.get(i).getDureeretard();
			totaljustifie+=retards.get(i).getJustifie();
		}
		items.add(new DisciplineItem());
		items.get(1).setLabel("Retards");
		items.get(1).setHeures(totaldure);
		items.get(1).setNombre(" Non Just.: "+(totaldure-totaljustifie)+ "    Just.:"+totaljustifie);
		
		List<Sanction> sanctions=getGestionDiscipline().listerSanctionsEleve(matriculeeleve,getAnneeAcEncours(),datedebut,datefin);
		Hashtable<String,List<Integer>> sanct=new Hashtable<String, List<Integer>>();
		
		String type;
		for(int i=0;i<sanctions.size();i++){
			type=sanctions.get(i).getTypesanction().getLibelle();
					
			
			if(!sanct.containsKey(type)){
				List<Integer> val=new ArrayList<Integer>();
				val.add(0, 0);
				val.add(0, 0);
				sanct.put(type, val);
			}
			sanct.get(type).add(0, sanct.get(type).get(0)+1);
			sanct.get(type).add(1, sanct.get(type).get(1)+sanctions.get(i).getDureesanction());
		
		}
		
		int j=2;
		for (Enumeration<String> e = sanct.keys(); e.hasMoreElements();){
			type=e.nextElement();
			List<Integer> values=sanct.get(type);
			items.add(new DisciplineItem());
			items.get(j).setLabel(type);
			items.get(j).setHeures(values.get(1));
			items.get(j).setNombre(" Nombre: "+values.get(0));
			j++;
		}
		
		return items;
	}


	public UserBean rechercherUtilisateurParLogin(String login) {
		UserBean ub=new UserBean();
		
		
		//Rechercher dabord chez les administrateurs
		Configuration conf=getUtilitaire().rechercherConfiguration();
		if(conf!=null && conf.getLogin_admin().compareTo(login)==0){
			ub.setLogin(conf.getLogin_admin());
			ub.setPassword(conf.getPass_admin());
			
			Set<String> roles=new HashSet<String>();
			roles.add("ROLE_ADMINISTRATEUR");
			ub.setRoles(roles);
		}
		else{
			// rechercher chez les gestionnaires
			Etablissement etab=getGestionutilisateur().rechercherGestionnaire(login);
			
			if(etab!=null){
				ub.setLogin(etab.getLogin_gest());
				ub.setPassword(etab.getPass_gest());
				
				Set<String> roles=new HashSet<String>();
				roles.add("ROLE_GESTIONNAIRE");
				ub.setRoles(roles);
			}
			else{
				Utilisateur user=getGestionutilisateur().rechercherUtilisateur(login);
				
				if(user==null)
					return null;
				
				ub.setLogin(user.getLogin());
				ub.setPassword(user.getPassword());
				ub.setRoles(new HashSet<String>());
				Set<String> roles=null;
				
				roles=getGestionutilisateur().rechercherRolesUtilisateur(login);
				ub.setRoles(roles);
			}
			
		}
		
		
		return ub;
	
	}


	public VersementBean rechercherVersement(String idversement) {
		VersementBean versement=new VersementBean();
		Versement vers=getGestionInscription().rechercherVersement(idversement,getAnneeAcEncours());
		if(vers==null)
			return null;
		try {
			CopyUtil.copieVersementToVersementBean(versement, vers);
		} catch (ClassToBeanCopyException e) {
			Repertoire.logError("Impossible de copier Versement vers VersementBean", getClass(), e);
		}
		return versement;
	}


	public void initialiseConvocationBean(ConvocationBean cb) {
		CopyUtil.copieConvocationVersConvocationBean(cb, getGestionDiscipline().rechercherConvocation(cb.getIdconvocation(),getAnneeAcEncours()));
	}


	public void saveConvocation(String libelle, String description,
			Date datedelivrance, Date dateeffet, List<String> elevescibles) throws ElementNOtFoundException {
		getGestionDiscipline().enregistrerConvocations(libelle, description,
				datedelivrance, dateeffet, elevescibles,getAnneeAcEncours());
	}


	public void modifierConvocation(int idconvocation, String libelle,String description,
			Date datedelivrance, Date dateeffet, String matriculeeleve) throws ElementNOtFoundException {
		getGestionDiscipline().modifierConvocation(idconvocation, libelle,description,
				datedelivrance,dateeffet, matriculeeleve,getAnneeAcEncours());
	}


	public void supprimerConvocation(int idconvocation) throws ElementNOtFoundException {
		getGestionDiscipline().supprimerConvocation(idconvocation,getAnneeAcEncours());
	}


	public List<ConvocationBean> listeConvocations() {
		return CopyUtil.listeConvocationToListeConvocationBean(getGestionDiscipline().listerConvocations(getAnneeAcEncours()));
	}


	public List<ConvocationBean> listeConvocations(Date datedebut, Date datefin) {
		return CopyUtil.listeConvocationToListeConvocationBean(getGestionDiscipline().listerConvocations(datedebut,datefin));
	}


	public void enregistrerPartieCours(int codecours, String nouveaulibelle,
			String nouvelledescription, Date nouvelledatedebut,
			Date nouvelledatefin) throws DuplicateKeyException, ElementNOtFoundException {
		getGestionCdt().creerChapitre(nouveaulibelle, nouvelledescription, codecours, nouvelledatedebut, nouvelledatefin,getAnneeAcEncours());
	}


	public List<PartieCoursBean> listerPartiesCours(int codecours) {
		return CopyUtil.listePartieCoursToListePartieCoursBean(getGestionCdt().listerPartiesCours(codecours));
	}


	public void initPartieCours(PartieCoursBean pcb) {
		CopyUtil.copierPartieCoursToPartieCoursBean(pcb,getGestionCdt().rechercherPartieCours(pcb.getCodepartie()));
	}


	public void modifierPartieCours(int codepartie, String libelle,
			String description, Date datedebut, Date datefin,int codecours) throws ElementNOtFoundException {
		getGestionCdt().modifierChapitre(codepartie, libelle, description, datedebut, datefin, codecours, getAnneeAcEncours());
	}
	
	public void supprimerPartieCours(int codepartie) throws ElementNOtFoundException{
		getGestionCdt().supprimerChapitre(codepartie,getAnneeAcEncours());
	}


	public int saveCdt(List<Integer> selectedchapitres, String libelle,
			String resume, Date datejour, Date heuredebut, Date heurefin) throws DuplicateKeyException, ElementNOtFoundException {
		return getGestionCdt().creerCahierTexte(selectedchapitres, libelle,
				resume, datejour,heuredebut, heurefin,getAnneeAcEncours());
	}


	public void initCdt(CdtBean cdtBean) {
		CopyUtil.copieCdtVersCdtBean(cdtBean,getGestionCdt().rechercherCdt(cdtBean.getCodecdt(),getAnneeAcEncours()));
	}


	public void supprimerCdt(int codecdt) throws ElementNOtFoundException {
		getGestionCdt().supprimerCahierTexte(codecdt,getAnneeAcEncours());
	}


	public void modifierCdt(int codecdt, List<Integer> selectedchapitres,
			String libelle, String resume, Date datejour, Date heuredebut,
			Date heurefin) throws ElementNOtFoundException {
		getGestionCdt().modifierCahierTexte(codecdt, selectedchapitres, libelle, resume, datejour, heuredebut, heurefin, getAnneeAcEncours());
	}


	public int enregistrerItemRole(String libelle, String description) throws DuplicateKeyException {
		return getGestionutilisateur().enregistrerItemRole(libelle,description);
	}


	public void initItemRole(ItemRoleBean itemRoleBean) {
		CopyUtil.copieItemRoleVersItemRoleBean(itemRoleBean,getGestionutilisateur().rechercherItemRole(itemRoleBean.getIditem()));
	}


	public void modifierItemRole(int iditem, String libelle, String description) throws ElementNOtFoundException {
		getGestionutilisateur().modifierItemRole(iditem,libelle,description);
	}


	public void supprimerItemRole(int iditem) throws ElementNOtFoundException {
		getGestionutilisateur().supprimerItemRole(iditem);
	}


	public List<ItemRoleBean> listerItemrole() {
		return CopyUtil.listeItemroleToListeItemRoleBean(getGestionutilisateur().listerItemroles());
	}


	public List<GroupeUserBean> listerGroupesUsers() {
		return CopyUtil.listeGroupeUserToListegroupeUserBean(getGestionutilisateur().listerGroupesUser());
	}


	public void modifierUtilisateur(String login, String password,
			String ancienmotdepasse) throws UnAuthorizedOperationException, ElementNOtFoundException {
		
		// verifie si le user est un administrateur (de toute l'application)
		//Pour cela recherche la configuration de l'application qui contient l'administrateur g�n�ral
		Configuration conf=getUtilitaire().rechercherConfiguration();
		
		
		if(conf!=null && conf.getLogin_admin().compareTo(login)==0 && conf.getPass_admin().compareTo(ancienmotdepasse)==0){
			// Cas o� l'utilisateur est l'administrateur g�n�ral
			getGestionutilisateur().modifierAdministrateur(login, password,ancienmotdepasse);
			
			
		}
		else{
			// verifie que le user est un gestionnaire d'etablissement
			Etablissement e=getGestionutilisateur().rechercherGestionnaire(login);
			if(e!=null && e.getPass_gest().compareTo(ancienmotdepasse)==0){
				getGestionutilisateur().modifierGestionnaire(e.getCodeetablissement(),login, password,ancienmotdepasse);
			}
			else{
				getGestionutilisateur().modifierUtilisateur(login,password,ancienmotdepasse,getIduser());
			}
		}
		
		
		
		
	}


	/**
	 * Initialise les donn�es de compte d'un utilisateur
	 * Il peut s'agir d'un administrateur, d'un gestionnaire ou d'un utilisateur simple
	 * @param compteBean
	 * @throws ElementNOtFoundException
	 */
	public void initData_Current_User(CompteBean compteBean) throws ElementNOtFoundException {
		String login=null;	
		
		try{
			login=getLoginUser();
		}
		catch(Exception e){
			return;
		}
		
		// verifie si le user est un administrateur (de toute l'application)
		//Pour cela recherche la configuration de l'application qui contient l'administrateur g�n�ral
		Configuration conf=getUtilitaire().rechercherConfiguration();
		
		
		if(conf!=null && conf.getLogin_admin().compareTo(login)==0){
			// Cas o� l'utilisateur est l'administrateur g�n�ral
			
			compteBean.setLogin(conf.getLogin_admin());
			compteBean.setPassword(conf.getPass_admin());
			compteBean.setNom("Administrateur  "+conf.getLogin_admin());
			
		}
		else{
			// verifie que le user est un gestionnaire d'etablissement
			Etablissement e=getGestionutilisateur().rechercherGestionnaire(login);
			if(e!=null ){
				
				compteBean.setLogin(login);
				compteBean.setPassword(e.getPass_gest());
				compteBean.setNom("Gestionnaire  "+login);
			}
			else{
				Utilisateur user=getGestionutilisateur().rechercherUtilisateur(login);
				if(user==null)
					throw new ElementNOtFoundException("Utilisateur",login);
				else{
					compteBean.setLogin(user.getLogin());
					compteBean.setPassword(user.getPassword());
					compteBean.setNom(user.getPersonnel().getNom()+(!(user.getPersonnel().getPrenom()==null)&&!user.getPersonnel().getPrenom().isEmpty()?"":" "+user.getPersonnel().getPrenom()));
				}
			}
		}
		
		
		
		
		
		
		
	}


	public void enregistrerUtilisateur(String codepersonnel, String login,
			String password, List<Integer> groupesusers) throws DuplicateKeyException, ElementNOtFoundException {
		getGestionutilisateur().enregistrerUtilisateur(codepersonnel,login,password,groupesusers);
	}


	public List<CdtBean> listerCdts(int codecours) {
		return CopyUtil.listeCahierDtToCahierDtBean(getGestionCdt().listerCdt(codecours,getAnneeAcEncours()));
	}

	/**
	 * Enregistrer un groupe utilisateur, et les roles aussi
	 * @param libelle libelle du groupe
	 * @param description description du groupe
	 * @param roles roles de l'utilisateur
	 * @return l'id du groupe enregistr�
	 * @throws DuplicateKeyException propag�e si un groupe utilisateur
	 * @throws ElementNOtFoundException Propag�e si un ou plusieurs roles n'ont pas �t� trouv�
	 */
	public int enregistrerGroupeUtilisateur(String libelle, String description,
			Map<Integer, List<Boolean>> roles) throws DuplicateKeyException, ElementNOtFoundException {
		return getGestionutilisateur().enregistrerGroupeUtilisateur(libelle,description,roles);
	}


	/**
	 * Initialise un groupe utilisateur, avec ses roles 
	 * @param groupeUserBean
	 */
	public void initGroupeUser(GroupeUserBean groupeUserBean) {
		GroupeUser gpu=getGestionutilisateur().rechercherGroupeUser(groupeUserBean.getIdgroupe());
		groupeUserBean.setDescription(gpu.getDescription());
		groupeUserBean.setLibelle(gpu.getLibelle());
		groupeUserBean.setMontant(gpu.getMontant());
		List<Habilitations> habilitations=new ArrayList<Habilitations>();
		
		List<ItemRole> items = getGestionutilisateur().listerItemroles();
		Hashtable<ItemRole,List<Boolean>> itemset=new Hashtable<ItemRole, List<Boolean>>();
		List<Boolean> values=new ArrayList<Boolean>();
		values.add(new Boolean(false));
		values.add(new Boolean(false));
		values.add(new Boolean(false));
		values.add(new Boolean(false));
		
		for(int i=0;i<items.size();i++){
			
			if((items.get(i).getLibelle().compareTo("ROLE_ADMINISTRATEUR")!=0)&&(items.get(i).getLibelle().compareTo("ROLE_GESTIONNAIRE")!=0)){
				itemset.put(items.get(i), values.subList(0, 4));
			}
			
		}
		
		List<GpUserRole> listeRoles=getGestionutilisateur().listerRoles(groupeUserBean.getIdgroupe());
		
		for(int i=0;i<listeRoles.size();i++){
			if(listeRoles.get(i).getLibelle().endsWith("_MANAGER")){
				List<Boolean> val=new ArrayList<Boolean>();
				val.add(new Boolean(true));
				val.add(new Boolean(true));
				val.add(new Boolean(true));
				val.add(new Boolean(true));
				
				itemset.put(listeRoles.get(i).getRole(),val);
							
			}
			else{
				if(listeRoles.get(i).getLibelle().endsWith("_CREATE")){
					List<Boolean> val=new ArrayList<Boolean>();
					val.add(new Boolean(true));
					val.add((itemset.get(listeRoles.get(i).getRole())).get(1));
					val.add((itemset.get(listeRoles.get(i).getRole())).get(2));
					val.add((itemset.get(listeRoles.get(i).getRole())).get(3));
					
					itemset.put(listeRoles.get(i).getRole(),val);
				}
				else{
					if(listeRoles.get(i).getLibelle().endsWith("_UPDATE")){
						List<Boolean> val=new ArrayList<Boolean>();
						val.add((itemset.get(listeRoles.get(i).getRole())).get(0));
						val.add(new Boolean(true));
						val.add((itemset.get(listeRoles.get(i).getRole())).get(2));
						val.add((itemset.get(listeRoles.get(i).getRole())).get(3));
						
						itemset.put(listeRoles.get(i).getRole(),val);			
					}
					else{
						if(listeRoles.get(i).getLibelle().endsWith("_DELETE")){
							List<Boolean> val=new ArrayList<Boolean>();
							val.add((itemset.get(listeRoles.get(i).getRole())).get(0));
							val.add((itemset.get(listeRoles.get(i).getRole())).get(1));
							val.add(new Boolean(true));
							val.add((itemset.get(listeRoles.get(i).getRole())).get(3));
							
							itemset.put(listeRoles.get(i).getRole(),val);					
						}
						else{
							if(listeRoles.get(i).getLibelle().endsWith("_VIEW")){
								List<Boolean> val=new ArrayList<Boolean>();
								val.add((itemset.get(listeRoles.get(i).getRole())).get(0));
								val.add((itemset.get(listeRoles.get(i).getRole())).get(1));
								val.add((itemset.get(listeRoles.get(i).getRole())).get(2));
								val.add(new Boolean(true));
								
								itemset.put(listeRoles.get(i).getRole(),val);					
							}
							else{
								
							}
						}
					}
				}
				
			}
		}
		
		// parcours de chacun des itemsRole avec pour chacun son quadruplet de roles
		
		int i=0;
		for (Enumeration<ItemRole> e = itemset.keys(); e.hasMoreElements();){
			ItemRole item=e.nextElement();
			
			if((item.getLibelle().compareTo("ROLE_APPMANAGER")!=0)&&(item.getLibelle().compareTo("ROLE_ALLACCESS")!=0)){
				habilitations.add(new Habilitations());
				habilitations.get(i).setItem(new ItemRole());
				habilitations.get(i).getItem().setLibelle(item.getLibelle());
				habilitations.get(i).getItem().setDescription(item.getDescription());
				habilitations.get(i).getItem().setIditem(item.getIditem());
				
				habilitations.get(i).setCreate(itemset.get(item).get(0));
				habilitations.get(i).setUpdate(itemset.get(item).get(1));
				habilitations.get(i).setDelete(itemset.get(item).get(2));
				habilitations.get(i).setView(itemset.get(item).get(3));
				i++;
			}
			
		}
		
		groupeUserBean.setHabilitations(habilitations);
		
	}


	public List<PersonnelBean> listerPersonnelLike(String query) {
		return CopyUtil.listePersonnelVersListePersonnelBean(getGestionressource().listerPersonnelsLike(query));
	}


	public void modifierGroupeUtilisateur(int idgroupe, String libelle,
			String description, Map<Integer, List<Boolean>> roles) throws ElementNOtFoundException {
		getGestionutilisateur().modifierGroupeUtilisateur(idgroupe, libelle,description, roles);
	}


	public void supprimerGroupeUtilisateur(int idgroupe) throws ElementNOtFoundException {
		getGestionutilisateur().supprimerGroupeUser(idgroupe);
	}


	public List<CompteBean> listerComptesUtilisateurs() {
		return CopyUtil.listeCompteToListeCompteBean(getGestionutilisateur().listerComptesUtilisateurs());
	}


	public void initCompte(CompteBean cb) {
		Utilisateur user=getGestionutilisateur().rechercherUtilisateur(cb.getIdcompte());
		
		if(user==null) // si l'utilisateur n'est pas retrouv�
			return;
		
		cb.setActive(!user.getSupprime());
		cb.setCodepersonnel(user.getPersonnel().getCodepersonnel());
		cb.setIdcompte(user.getIdutilisateur());
		cb.setLogin(user.getLogin());
		cb.setPassword(user.getPassword());
		cb.setNom(user.getPersonnel().getNom());
		cb.setPrenom(user.getPersonnel().getPrenom());
		cb.setGroupesusers(getGestionutilisateur().listerGroupesUserCodes(user.getIdutilisateur()));
	}


	public void modifierUtilisateur(int idcompte, String codepersonnel,
			String login, String password, List<Integer> groupesusers) throws ElementNOtFoundException, DuplicateKeyException {
		getGestionutilisateur().modifierUtilisateur(idcompte, codepersonnel,login, password, groupesusers);
	}


	public void desactiverUtilisateur(int idcompte) throws ElementNOtFoundException {
		getGestionutilisateur().supprimerUtilisateur(idcompte);
	}


	public void enregistrerManager(String login, String password) throws DuplicateKeyException {
		getGestionutilisateur().enregistrerGestionnaire(login,password);
	}


	public boolean checkManagerExist() {
		List<Utilisateur> liste=getGestionutilisateur().listerManager();
		return liste!=null && !liste.isEmpty();
		
	}


	public int rechercherCodeCours(int codepartie) {
		return getGestionCdt().rechercherCodeCours(codepartie);
	}


	/**
	 * @param codeoption
	 * @return
	 */
	public List<ClasseBean> listeClassesOptions(String codeoption) {
		List<Object[]> result=null;
		int effectif = 0;
		List<ClasseBean> liste=new ArrayList<ClasseBean>();
		String nomens;
		ClasseBean classb = null;
		try{
			result= getGestionressource().listerClasseWithSize(codeoption,getAnneeAcEncours());		
			for(int i=0;i<result.size();i++){
//				liste.add(new ClasseBean());
				 effectif = getGestionressource().effectif(getAnneeEncours(),(Classe) result.get(i)[0]);
				 Classe clas = (Classe) result.get(i)[0];
				  classb= new ClasseBean();
				 classb.setCodeClasse(clas.getCodeclasse());
					
					//Dans le cas o� la classe poss�de un enseigant titulaire, on met � jour son nom et son code au sein du bean
					if((clas.getTitulaires()!=null) &&(!clas.getTitulaires().isEmpty())){
						classb.setCodeEnseignantTitulaire(clas.getTitulaires().get(0).getEnseignant().getCodeenseignant());
						
						nomens=clas.getTitulaires().get(0).getEnseignant().getNomens();
						
						if(clas.getTitulaires().get(0).getEnseignant().getPrenomens()!=null && !clas.getTitulaires().get(0).getEnseignant().getPrenomens().isEmpty()){
							nomens=nomens+" "+clas.getTitulaires().get(0).getEnseignant().getPrenomens();
						}
						classb.setNomenseignantTitulaire(nomens);
					}
						
					classb.setContenanceActuelle(effectif);
					
					classb.setDelegue1(clas.getDelegue1());
					classb.setDelegue2(clas.getDelegue2());
					classb.setEffectifMax(clas.getContenancemax());
					classb.setLibelle(clas.getLibelle());
					classb.setSalleDefaut(clas.getSalleDefaut());
					if(clas.getOptionserie()!=null){
						classb.setOption(clas.getOptionserie().getCodeoption());
						classb.setNiveau(clas.getOptionserie().getNiveau().getLibelle());
					}
			}
			liste.add(classb);
			
		}
		catch(Exception e){
			Repertoire.logFatal("listing classes option error", this.getClass(), e);
			
		}
		
		return liste;
	}


	public List<EnsClasseBean> listerEnseignantsClasse(String codeclasse) {
		List<Object[]> ens=getGestionressource().listerEnseignantsClasse(codeclasse);
		List<EnsClasseBean> enseignants=new ArrayList<EnsClasseBean>();
		
		Hashtable<Enseignant,String> map=new Hashtable<Enseignant, String>();
		
		for(int i=0;i<ens.size();i++){
			if(!map.containsKey(ens.get(i)[0])){
				map.put((Enseignant)ens.get(i)[0], (String)ens.get(i)[1]);
			}
			else{
				String matieres=map.get(ens.get(i)[0])+", "+(String)ens.get(i)[1];
				map.put((Enseignant)ens.get(i)[0], matieres);
			}				
		}
		
		String nomens=null;
		for(Enumeration<Enseignant> e=map.keys();e.hasMoreElements();){
			
			EnsClasseBean en=new EnsClasseBean();
			Enseignant ensei=e.nextElement();
			en.setCodeenseignant(ensei.getCodeenseignant());
			nomens=ensei.getNomens();
			
			if(ensei.getPrenomens()!=null && !ensei.getPrenomens().isEmpty())
				nomens=nomens+" "+ensei.getPrenomens();
			
			en.setNom(nomens);
			en.setMatieres(map.get(ensei));
			
			enseignants.add(en);
		}
		
		return enseignants;
		
	}


	public List<ClasseBean> listerClasse(String codeclasse) {
		List<ClasseBean> classes=new ArrayList<ClasseBean>();
		Object[] clas=getGestionEtablissement().rechercheClasse(codeclasse, getAnneeAcEncours());
		if(clas!=null){
			
			classes.add(new ClasseBean());
			CopyUtil.copieClasseToClasseBean(classes.get(0),(Classe) clas[0],Integer.parseInt(clas[1].toString()));
		}
		return classes;	
	}


	public List<Integer> listerCodesChapitresCdt(int codecdt) {
		return getGestionCdt().listerCodesChapitresCdt(codecdt);
	}


	public List<EleveBean> listerElevesTableauHonneurInscrits(String codeclasse,int trimestre) {
		return CopyUtil.listEleveTolistEleveBean(getGestionExamens().listerTableauxHonneur(codeclasse,trimestre,getAnneeAcEncours()));
	}


	public void imprimerBulletinSequence(String codeclasse,
			List<Integer> eleves, int sequence) throws JRException, IOException {
		
		for(int i=0;i<eleves.size();i++){
			getImpressionUtils().imprimerBulletinsPDFSequence(this,getInfosSession().getCodeetablissement(),codeclasse, eleves,sequence,getGestionEtablissement().rechercheEtablissement(getInfosSession().getCodeetablissement()).getLogo(),rechercherModeleBulletin());
		
		}
	}


	public void imprimerBulletinTrimestre(String codeclasse,
			List<Integer> eleves, int trimestre) throws JRException, IOException {
			List<Integer> sequences=getGestionEtablissement().rechercherSequencesTrimestre(trimestre, getAnneeAcEncours());
			int seq1=1;
			int seq2=2;
			
			
			if(sequences==null ||sequences.isEmpty()){
				return;
			}
			else{
				if(sequences.size()==1){
					seq1=sequences.get(0);
					seq2=sequences.get(0);
				}
				else{
					seq1=sequences.get(0);
					seq2=sequences.get(1);
					
				}
				
			}
			
			
			
			getImpressionUtils().imprimerBulletinsPDFTrimestre(this,getInfosSession().getCodeetablissement(),seq1,seq2, codeclasse,eleves,trimestre,getInfosSession().getLogoetablissement(),rechercherModeleBulletin());
		
	}
	
	public void imprimerBulletinAnnuel(String codeclasse,
			List<Integer> eleves) throws JRException, IOException {
			
			getImpressionUtils().imprimerBulletinsPDFAnnuelN(this,getInfosSession().getCodeetablissement(),codeclasse,eleves,getGestionEtablissement().rechercheEtablissement(getInfosSession().getCodeetablissement()).getLogo(),rechercherModeleBulletin());
		
	}
	
	public void imprimerBilanAnnuel(String codeclasse,
			List<Integer> eleves) throws JRException, IOException {
			
			getImpressionUtils().imprimerBilanPDFAnnuel(this,getInfosSession().getCodeetablissement(),codeclasse,eleves,getGestionEtablissement().rechercheEtablissement(getInfosSession().getCodeetablissement()).getLogo(),rechercherModeleBulletin());
		
	}
	
	public void imprimerBulletinTrimestriel(String codeclasse,
			List<Integer> eleves,int trimestre) throws JRException, IOException {
			
			if(trimestre==1){
			getImpressionUtils().imprimerBulletinsPDFTrimestriel(this,getInfosSession().getCodeetablissement(),1,2,3, codeclasse,eleves,getGestionEtablissement().rechercheEtablissement(getInfosSession().getCodeetablissement()).getLogo(),rechercherModeleBulletin(),trimestre);
			}else{
			if(trimestre==2){
			getImpressionUtils().imprimerBulletinsPDFTrimestriel(this,getInfosSession().getCodeetablissement(),4,5,6, codeclasse,eleves,getGestionEtablissement().rechercheEtablissement(getInfosSession().getCodeetablissement()).getLogo(),rechercherModeleBulletin(),trimestre);
			}else{
			getImpressionUtils().imprimerBulletinsPDFTrimestre(this, getInfosSession().getCodeetablissement(),7,8, codeclasse, eleves,3, getGestionEtablissement().rechercheEtablissement(getInfosSession().getCodeetablissement()).getLogo(), rechercherModeleBulletin());
			}
			}
	}


	public void imprimerTableauHonneurSequence(String codeclasse,
			List<Integer> eleves, int sequence) throws SQLException, JRException, IOException {
		
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		
		for(int i=0;i<eleves.size();i++){
			getImpressionUtils().imprimerTableauHoneurPDF (format.format(new Date()),getInfosSession().getCodeetablissement(), getInfosSession().getLogoetablissement(), eleves.get(i), codeclasse, sequence);
		
		}
	}


	public void imprimerTableauHonneurTrimestre(String codeclasse,
			List<Integer> eleves, Integer portee) throws SQLException, JRException, IOException {
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		
		for(int i=0;i<eleves.size();i++){
			getImpressionUtils().imprimerTableauHoneurPDF(format.format(new Date()),getInfosSession().getCodeetablissement(), getInfosSession().getLogoetablissement(), eleves.get(i), codeclasse, 1);
		
		}
	}


	public void enregistrerModeleBulletin(int modele) {
		getUtilitaire().enregistrerModeleBulletin(modele,getInfosSession().getCodeetablissement());
	}


	public int rechercherModeleBulletin() {
		int modele=getUtilitaire().rechercherModeleBulletin(getInfosSession().getCodeetablissement());
		if(modele<=0 || modele>3)
			return 1;
		else
			return modele;
	}


	public List<DossierBean> listedossiersAcceptes(String codeniveau) {
		return CopyUtil.listPreinscriptionTolistdossierBean(getGestionInscription().PreinscriptionAccepte(getAnneeAcEncours(),codeniveau));
	}


	public List<DossierBean> listedossiersRejetes() {
		return CopyUtil.listPreinscriptionTolistdossierBean(getGestionInscription().PreinscriptionRejette(getAnneeAcEncours()));
	}


	public List<DossierBean> listedossiersRejetes(String codeniveau) {
		return CopyUtil.listPreinscriptionTolistdossierBean(getGestionInscription().PreinscriptionRejette(getAnneeAcEncours(),codeniveau));
	}


	public List<DossierBean> listedossiersAttente(String codeniveau) {
		return CopyUtil.listPreinscriptionTolistdossierBean(getGestionInscription().PreinscriptionAttente(getAnneeAcEncours(),codeniveau));
	}


	public List<DossierBean> listedossiersAttente() {
		return CopyUtil.listPreinscriptionTolistdossierBean(getGestionInscription().PreinscriptionAttente(getAnneeAcEncours()));
	}


	public void imprimerCandidaturesAttente(String codeniveau) throws JRException, IOException {
		if(codeniveau==null || codeniveau.isEmpty())
			getImpressionUtils().imprimerCanditatureAttentePDF(getInfosSession().getCodeetablissement());
		else
			getImpressionUtils().imprimerCanditatureAttentePDF(getInfosSession().getCodeetablissement(),codeniveau);
	}


	public void imprimerCandidaturesRejetees(String codeniveau) throws SQLException, JRException, IOException {
		if(codeniveau==null || codeniveau.isEmpty())
			getImpressionUtils().imprimerCanditatureRejetesPDF(getInfosSession().getCodeetablissement());
		else
			getImpressionUtils().imprimerCanditatureRejetesPDF(getInfosSession().getCodeetablissement(),codeniveau);
	}


	public void imprimerCandidaturesAcceptees(String codeniveau) throws SQLException, JRException, IOException {
		if(codeniveau==null || codeniveau.isEmpty())
			getImpressionUtils().imprimerCandidaturesAcceptees(getInfosSession().getCodeetablissement());
		else
			getImpressionUtils().imprimerCanditatureAcceptesPDF(codeniveau, getInfosSession().getCodeetablissement());
	}


	public void imprimerAllCandidatures() throws SQLException, JRException, IOException {
		getImpressionUtils().imprimerCandidaturesRecues(getInfosSession().getCodeetablissement());
	}


	public void imprimerListeProvisoire(String codeClasse,String codeetablissement) throws SQLException, JRException, IOException {
		getImpressionUtils().imprimerlisteEleveProvisoire(codeClasse, codeetablissement,getAnneeEncours());
	}


	public void imprimerListeProvisoire() {
		 getImpressionUtils().imprimerListeEleveProvisoire();
	}


	public void imprimerElevesNonEnRegle(String codeClasse) {
		getImpressionUtils().imprimerElevesNonEnregle(codeClasse);
	}


	public void imprimerElevesNonEnregle() {
		getImpressionUtils().imprimerElevesNonEnregle();
	}


	public void imprimerElevesEnRegle(Service service,String codeClasse) throws JRException, IOException {
		getImpressionUtils().imprimerElevesEnregle(service,codeClasse,getAnneeEncours(),getInfosSession().getLogoetablissement(),getInfosSession().getCodeetablissement());
	}


	/*public void imprimerElevesEnregle() {
		getImpressionUtils().imprimerElevesEnregle();
	}*/


	public void imprimerFactureVersement(int ideleve,String codeClasse, String idversement) throws JRException, IOException {
		getImpressionUtils().imprimerFacturesVersement(ideleve ,idversement,codeClasse,getInfosSession().getLogoetablissement()
				,getInfosSession().getCodeetablissement(),getAnneeEncours());
	}
	
	public void imprimerCertificat(int ideleve,String codeClasse,int numerocertificat) throws JRException, IOException {
		getImpressionUtils().imprimerCertificat(ideleve ,codeClasse,getInfosSession().getLogoetablissement()
				,getInfosSession().getCodeetablissement(),getAnneeEncours(),numerocertificat);
	}



	public void imprimerElevesInscrits(String codeClasse) throws JRException, IOException {
		getImpressionUtils().imprimerlisteEleveInscrits(codeClasse, getInfosSession().getCodeetablissement(), getInfosSession().getLogoetablissement(),getAnneeEncours());
	}


	/*public void imprimerElevesInscrits() {
		getImpressionUtils().imprimerlisteEleveInscrits();
	}*/


	public List<OptionBean> listerOptions(String niveauDemande) {
		List<OptionSerie> listo=getGestionEtablissement().listerOptions(niveauDemande);
		
		return CopyUtil.listeOptionVerslisteOptionBean(listo);
	}


	public void imprimerConvocation(int idconvocation) {
		getImpressionUtils().imprimerConvocation(idconvocation);
	}


	public void modifierDelegues(String codeclasse, String delegue1,
			String delegue2, String nouveautitulaire) throws ElementNOtFoundException {
		getGestionressource().modifierDelegues(codeclasse, getAnneeAcEncours(), delegue1, delegue2,nouveautitulaire);
	}


	public List<EnseignantBean> listeenseignants(String codeClasse) {
		List<Enseignant> ens=getGestionressource().listerEnseignants(codeClasse);
		return CopyUtil.listeEnseignantVersListeEnseignantBean(ens);
	}


	public ScheduleModel listerProgrammationClasse(String codeclasse) {
		ScheduleModel modele=new DefaultScheduleModel();
		Annee an;
		String nomens="";
		try {
			an = getGestionEtablissement().rechercheAnneeEnCours(getInfosSession().getCodeetablissement());
		} catch (AnneeEnCoursNonDefinieException e) {
			Repertoire.addMessageerreur("Aucune ann�e en cours trouv�e", e);
			return null;
		}
		
		
		List<Programmation> programmations=getGestionProgrammation().listerProgrammationsCoursClasse(codeclasse,an.getDateDebut(),an.getDateFin(),getAnneeAcEncours());
		
		if(programmations!=null){
			for(int i=0;i<programmations.size();i++){
				if(programmations.get(i).getCours()!=null){
					if(programmations.get(i).getCours().getEnseignant()!=null){
						nomens=programmations.get(i).getCours().getEnseignant().getNomens();
					}
				}
				
				modele.addEvent(new DefaultScheduleEvent(programmations.get(i).getLibelle()+" "+nomens,programmations.get(i).getDatedebut(), programmations.get(i).getDatefin()));
			}
		}
		return modele;
	}


	public void initTimeTableAdd(TimeClasseBean tBean) {
		ClasseBean cla=new ClasseBean();
		initialiseClasseBean(cla);
		
		tBean.setLibelleclasse(cla.getLibelle());
		
		List<Cour> cours=getGestionmatiere().listerCoursNonProgrammes(tBean.getCodeclasse(),getAnneeAcEncours());
		tBean.setCours(CopyUtil.listCourVerslisteCoursBean(cours));
	}


	public void initTimeTableUpdate(TimeClasseBean tBean) {
		ClasseBean cla=new ClasseBean();
		initialiseClasseBean(cla);
		
		tBean.setLibelleclasse(cla.getLibelle());
		
		List<Cour> cours=getGestionmatiere().listerCoursProgrammes(tBean.getCodeclasse(),getAnneeAcEncours());
		tBean.setCours(CopyUtil.listCourVerslisteCoursBean(cours));
		Repertoire.addMessageerreur("Inside the init method");
	}


	public void imprimerCandidature(String codedossier) {
		getImpressionUtils().imprimerCandidature(codedossier,getInfosSession().getCodeetablissement());
	}


	public void imprimerVersements(Date datedebut, Date datefin) throws IOException, JRException {
		getImpressionUtils().imprimerVersements(datedebut, datefin,getInfosSession().getCodeetablissement(),getAnneeEncours());
	}
	
	public void imprimeretatversement() throws IOException, JRException {
		getImpressionUtils().imprimeretatversement(getInfosSession().getCodeetablissement(),getAnneeEncours());
	}



	public void enregistrerProgrammationCours(String codeclasse, int codecours,
			Date debutlundi, Date finlundi, Date debutmardi, Date finmardi,
			Date debutmercredi, Date finmercredi, Date debutjeudi,
			Date finjeudi, Date debutvendredi, Date finvendredi,
			Date debutsamedi, Date finsamedi) throws AnneeEnCoursNonDefinieException, ElementNOtFoundException {
		getGestionProgrammation().enregistrerProgrammationCours(codeclasse,codecours,
				debutlundi, finlundi, debutmardi, finmardi,
				debutmercredi, finmercredi, debutjeudi,
				finjeudi, debutvendredi, finvendredi,
				debutsamedi,finsamedi,getAnneeAcEncours(),getInfosSession().getCodeetablissement());
	}


	public void editerProgrammationCours(String codeclasse, int codecours,
			Date debutlundi, Date finlundi, Date debutmardi, Date finmardi,
			Date debutmercredi, Date finmercredi, Date debutjeudi,
			Date finjeudi, Date debutvendredi, Date finvendredi,
			Date debutsamedi, Date finsamedi) throws AnneeEnCoursNonDefinieException, ElementNOtFoundException {
		getGestionProgrammation().editerProgrammationCours(codeclasse,codecours,
				debutlundi, finlundi, debutmardi, finmardi,
				debutmercredi, finmercredi, debutjeudi,
				finjeudi, debutvendredi, finvendredi,
				debutsamedi,finsamedi,getAnneeAcEncours(),getInfosSession().getCodeetablissement());
	}


	public void chargerProgrammationCours(TimeClasseBean tBean) {
		// recupere le 1er lundi 
		Calendar caldebut=Calendar.getInstance();
		Calendar calfin=Calendar.getInstance();
		Annee an=null;
		
		List<Programmation> progs;
		
		try {
			an=getGestionEtablissement().rechercheAnneeEnCours(getInfosSession().getCodeetablissement());
		} catch (AnneeEnCoursNonDefinieException e) {
			e.printStackTrace();
		}
		
		caldebut.setTime(an.getDateDebut());
		
		while(caldebut.get(Calendar.DAY_OF_WEEK)!=Calendar.MONDAY){
			caldebut.add(Calendar.DATE,1);
		}
		
		caldebut.set(Calendar.HOUR_OF_DAY, 0);
		caldebut.set(Calendar.MINUTE, 0);
		caldebut.set(Calendar.SECOND, 0);
		
		calfin.setTime(caldebut.getTime());
		calfin.add(Calendar.DATE,5);
		calfin.set(Calendar.HOUR_OF_DAY, 23);
		calfin.set(Calendar.MINUTE, 59);
		calfin.set(Calendar.SECOND, 59);
		
		progs=getGestionProgrammation().rechercherProgrammationCoursJour(tBean.getCodecours(),caldebut.getTime(),calfin.getTime(),getAnneeAcEncours());
		
		for(int i=0;i<progs.size();i++){
			caldebut.setTime(progs.get(i).getDatedebut());
			if(caldebut.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY){
				tBean.setDebutlundi(progs.get(i).getDatedebut());
				tBean.setFinlundi(progs.get(i).getDatefin());
			}
			else if(caldebut.get(Calendar.DAY_OF_WEEK)==Calendar.TUESDAY){
				tBean.setDebutmardi(progs.get(i).getDatedebut());
				tBean.setFinmardi(progs.get(i).getDatefin());
			}
			else if(caldebut.get(Calendar.DAY_OF_WEEK)==Calendar.WEDNESDAY){
				tBean.setDebutmercredi(progs.get(i).getDatedebut());
				tBean.setFinmercredi(progs.get(i).getDatefin());
			}
			else if(caldebut.get(Calendar.DAY_OF_WEEK)==Calendar.THURSDAY){
				tBean.setDebutjeudi(progs.get(i).getDatedebut());
				tBean.setFinjeudi(progs.get(i).getDatefin());
			}
			else if(caldebut.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY){
				tBean.setDebutvendredi(progs.get(i).getDatedebut());
				tBean.setFinvendredi(progs.get(i).getDatefin());
			}
			else if(caldebut.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
				tBean.setDebutsamedi(progs.get(i).getDatedebut());
				tBean.setFinsamedi(progs.get(i).getDatefin());
			}
		}
		
	}


	public ScheduleModel listerProgrammationSalle(String codesalle) {
		ScheduleModel modele=new DefaultScheduleModel();
		Annee an;
		try {
			an = getGestionEtablissement().rechercheAnneeEnCours(getInfosSession().getCodeetablissement());
		} catch (AnneeEnCoursNonDefinieException e) {
			Repertoire.addMessageerreur("Aucune ann�e en cours trouv�e", e);
			return null;
		}
		
		
		List<Programmation> programmations=getGestionProgrammation().listerProgrammationsSalle(codesalle,an.getDateDebut(),an.getDateFin(),getAnneeAcEncours());
		
		if(programmations!=null){
			for(int i=0;i<programmations.size();i++){							
				modele.addEvent(new DefaultScheduleEvent(programmations.get(i).getLibelle(),programmations.get(i).getDatedebut(), programmations.get(i).getDatefin()));
			}
		}
		return modele;
	}


	public ScheduleModel listerProgrammationEvenements() {
		ScheduleModel modele=new DefaultScheduleModel();
		Annee an;
		try {
			an = getGestionEtablissement().rechercheAnneeEnCours(getInfosSession().getCodeetablissement());
		} catch (AnneeEnCoursNonDefinieException e) {
			Repertoire.addMessageerreur("Aucune ann�e en cours trouv�e", e);
			return null;
		}
		
		
		List<Programmation> programmations=getGestionProgrammation().listerProgrammationsEvt(an.getDateDebut(),an.getDateFin(),getAnneeAcEncours());
		
		if(programmations!=null){
			for(int i=0;i<programmations.size();i++){							
				modele.addEvent(new DefaultScheduleEvent(programmations.get(i).getLibelle(),programmations.get(i).getDatedebut(), programmations.get(i).getDatefin()));
			}
		}
		return modele;
	}


	public List<String> listeTypesEvtStartingWith(String query) {
		List<String> liste=getGestionProgrammation().listerTypesActivites(getAnneeAcEncours(),getInfosSession().getCodeetablissement());
		List<String> result=new ArrayList<String>();
		
		for(int i=0;i<liste.size();i++){
			if(i==10)
				break;
			
			if(liste.get(i).startsWith(query)){
				result.add(query);
			}
		}
		
		return result;
	}


	public void telechargerformulairenotes(String codeclasse) throws IOException {
		
		String codeetab=getInfosSession().getCodeetablissement();
		
		String libelleclasse=((Classe)(getGestionEtablissement().rechercheClasse(codeclasse, getAnneeAcEncours())[0])).getLibelle();
		
		List<Eleve> eleves=getGestionEleve().listerElevesClasse(codeclasse, getAnneeAcEncours());
		
		
		getImpressionUtils().exporterFormulaireNotes(codeetab,getGestionEtablissement().rechercheEtablissement(codeetab).getNometab(),getAnneeAcEncours(),libelleclasse,codeclasse,eleves);
		
	}
	
	
	public void telechargerformulaireToutesnotes(String codeclasse) throws IOException, JRException {
		
		String codeetab=getInfosSession().getCodeetablissement();
		
		getImpressionUtils().exporterFormulaireToutesNotes(codeetab,getAnneeAcEncours(),codeclasse);
		
	}
	
	
	public void telechargerbilanreussite(String codeclasse,int sequence) throws IOException, JRException {
		
		String codeetab=getInfosSession().getCodeetablissement();
		
		getImpressionUtils().exporterBilanreussite(codeetab,getAnneeAcEncours(),codeclasse,sequence);
		
	}
	
	public void telechargertauxsequence(String codeclasse,int sequence) throws IOException, JRException {
		
		String codeetab=getInfosSession().getCodeetablissement();
		
		getImpressionUtils().exporterBilanreussiteRecapSequence(codeetab,getAnneeAcEncours(),codeclasse,sequence);
		
	}
	
	
	public void telechargerbilanreussiteT(String codeclasse,int trimestre) throws IOException, JRException {
		
		String codeetab=getInfosSession().getCodeetablissement();
		
		getImpressionUtils().exporterBilanreussiteT(codeetab,getAnneeAcEncours(),codeclasse,trimestre);
		
	}


	/**
	 * Rechercher et initialiser la configuration de l'application
	 */
	public void rechercherConfigurationApplication() {
		 getUtilitaire().rechercherConfiguration();
	}

	
	/**
	 * Modification d'un etablissement par un administrateur
	 * @param codeetablissement
	 * @param codeetablissement2
	 * @param nom
	 * @param logingest
	 * @param passgest
	 * @throws ElementNOtFoundException 
	 */
	public void admodifyEtablissement(String codeetablissement, String nom, String logingest,
			String passgest) throws ElementNOtFoundException {
		getGestionEtablissement().admodifierEtablissement(codeetablissement, nom,logingest,passgest );
	}


	public void initEtablissementEdit(EtablissementBean eb) {
		Etablissement etab=getGestionEtablissement().rechercheEtablissement(getInfosSession().getCodeetablissement());
		if(etab!=null)
			CopyUtil.copieEtablissementVersEtablissementBean(eb,etab);
	}


	public void imprimerListeEnseignants() throws JRException, IOException {
		getImpressionUtils().imprimerlisteEnseignants(getInfosSession().getCodeetablissement(), getInfosSession().getAcronymeetablissement());
	}

	/**
	 * Imprimmer les notes d'une classe (exporter en PDF) pour une �valuation donn�e
	 * @param codeevaluation code de l'�valuation
	 * @throws IOException
	 * @throws JRException
	 */
	public void imprimerNotesClasse(int codeevaluation) throws IOException, JRException {
		Evaluation ev=getGestionExamens().rechercheEvaluation(codeevaluation);
		getImpressionUtils().imprimerNotesClasse(ev.getCour().getClasse().getCodeclasse(),ev.getCour().getClasse().getLibelle(),ev.getCour().getMatiere().getCodematiere(),ev.getCour().getMatiere().getLibelle(),ev.getSequence().getNumerosequence(), getInfosSession().getCodeetablissement(),getAnneeEncours());
	}

	
	/**
	 * Liste les matieres d'une classe
	 * @param codeclasse code de la classe dont on doit lister les matieres
	 * @return la liste des matieres de la classe de code "codeclasse"
	 */
	public List<MatiereBean> listematieresClasseN(String codeclasse) {
		
		/*String an = null;
		List<Annee> ans=getGestionEtablissement().listerAnneesAcademiques();
		List<Matiere> listmat=getGestionmatiere().listerMatieresClasse(codeclasse,ans.get(ans.size()-2).getAnneeacademique(), getInfosSession().getCodeetablissement());
		if(!ans.isEmpty() && ans!=null && listmat!=null && ! listmat.isEmpty()){
			an=ans.get(ans.size()-2).getAnneeacademique();
			return CopyUtil.listeSimpleMatiereVerslisteMatiereBean(getGestionmatiere().listerMatieresClasse(codeclasse,an, getInfosSession().getCodeetablissement()));
		}else{*/
		return CopyUtil.listeSimpleMatiereVerslisteMatiereBean(getGestionmatiere().listerMatieresClasse(codeclasse,getAnneeAcEncours(), getInfosSession().getCodeetablissement()));
	
		
	}
	
	
	/**
	 * Liste les matieres d'une classe
	 * @param codeclasse code de la classe dont on doit lister les matieres
	 * @return la liste des matieres de la classe de code "codeclasse"
	 */
	public List<MatiereBean> listematieresClasse(String codeclasse) {
		
		return CopyUtil.listeSimpleMatiereVerslisteMatiereBean(getGestionmatiere().listerMatieresClasse(codeclasse,getAnneeAcEncours(), getInfosSession().getCodeetablissement()));
	
	}


	/**
	 * Recherche le libelle d'un cours
	 * @param codematiere code de la matiere concern�e
	 * @param codeclasse code de la classe concern�e
	 * @return le libelle du cours liant la matiere "codematiere" � la classe "codeclasse"
	 */
	public String rechercherlibelleCours(String codematiere, String codeclasse) {
		Cour cours=getGestionmatiere().rechercherCours(codeclasse,codematiere,getAnneeAcEncours());
		if(cours==null)
			return "";
		
		return cours.getLibelleCours();
	}


	public void exporterNotesClasseXLS(List<CompositionBean> compositions, String codeclasse, String codematiere, int sequence) throws FileNotFoundException, IOException {
		String codeetab=getInfosSession().getCodeetablissement();
		String libelleclasse=((Classe)(getGestionEtablissement().rechercheClasse(codeclasse, getAnneeAcEncours())[0])).getLibelle();
		String libellematiere=getGestionmatiere().rechercherMatiere(codematiere).getLibelle();
		getImpressionUtils().exporterNotesClasseXLS(compositions,codeetab,getGestionEtablissement().rechercheEtablissement(codeetab).getNometab(),getAnneeAcEncours(),libelleclasse,codeclasse,sequence,libellematiere);
	}

	/**
	 * Modification d'un administrateur
	 * @param login nouveau login
	 * @param password nouveau mot de passe
	 * @param ancienmotdepasse ancien mot de passe
	 */

	public void modifierAdministrateur(String login, String password,
			String ancienmotdepasse) {
		getGestionutilisateur().modifierAdministrateur(login, password, ancienmotdepasse);
	}


	/**
	 * Dit si oui ou non un utilisateur a le droit de modifier les notes d'une �valuation
	 * @param codeevaluation code de l'�valuation concern�e
	 * @return true si l'utilisateur courant possede les droits de modification, false sinon
	 * @throws ElementNOtFoundException propag�e si l'�valuation de code pass� en parametres n'est pas retrouv�e
	 */
	public boolean editNotesAbled(int codeevaluation) throws ElementNOtFoundException {
		FacesContext context = FacesContext.getCurrentInstance();
	    SessionBean session = (SessionBean) context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{sessionBean}", SessionBean.class).getValue(context.getELContext());
	    
	    if(session.getIdutilisateur()==0 || session.getIdutilisateur()==-1){// Cas d'un administrateur ou d'un gestionnaire d'etablissement de l'application
	    	return false;
	    }
	    else{
	    	Evaluation ev=getGestionExamens().rechercheEvaluation(codeevaluation);
	    	if(ev==null){
	    		throw new ElementNOtFoundException(String.valueOf(codeevaluation), "Evaluation");
	    	}
	    	
	    	
	    	if(ev.getCour().getEnseignant().getUtilisateur().getIdutilisateur()==session.getIdutilisateur()){// Cas d'un gestionnaire d'etablissement de l'application
		    	return true;
		    }
		    else{
		    	return false;
		    }
	    }
	}


	public void modifierGroupeUtilisateur( String libelle,
			float montant) {
		try {
			getGestionutilisateur().modifierGroupeUser(libelle, montant);
		} catch (UnAuthorizedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ElementNOtFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public List<String> listeResponsabilite() {
		return getGestionutilisateur().listeTypesResponabilite();
	}


	public void initVersementBean(VersementBean versB) {
		if(versB.getId()==null)
			return;
		Versement ver=getGestionInscription().rechercherVersement(versB.getId(),getAnneeEncours());
		Eleve el = getGestionEleve().rechercheEleve(ver.getEleve().getMatricule(), getAnneeEncours());
		versB.setDateVers(ver.getDateVersement());
		versB.setMatricule(el.getMatricule());
		versB.setMontant(ver.getMontant());
		versB.setNom(el.getNom());
		versB.setPrenom(el.getPrenom());
		versB.setTypeversement(ver.getTypeversement());
		versB.setId(ver.getIdversement());
		versB.setClasse(el.getClasse().getCodeclasse());
		
	}


	public void modifierVersement(String id, String typeversement,
			float montant, Date dateVers) throws ElementNOtFoundException {
		getGestionInscription().modifierversement( id, typeversement,montant,  dateVers);
		
	}


	public void clotureanne(String anneeacademique) throws ElementNOtFoundException {
		getGestionInscription().cloture(anneeacademique,getInfosSession().getIdutilisateur(),getAnneeEncours(),getInfosSession().getCodeetablissement());
		
	}


	public List<String> listeTypeAnnee() {
		return getGestionInscription().listerAnnee();
	}
	
	public List<String> listeTypeAnneeAdmin() {
		return getGestionInscription().listerAnneeAdmin();
	}


	public void initialiseSequenceBean(SequenceBean sequence) {
		Sequence seq=getGestionEtablissement().rechercheSequence(sequence.getNumero(),getAnneeAcEncours());
		if(seq!=null)
			CopyUtil.copieSequenceToSequenceBean(seq,sequence);
		
	}


	public List<BilanBean> listeBilanSeq(int numero) {
		List<Object[]> result=null;
		int effectif = 0;
		float taux;
		double moyenne;
		List<BilanBean> liste=new ArrayList<BilanBean>();
		BilanBean bilanb = null;
		try{
			Sequence seq=getGestionEtablissement().rechercheSequence(numero,getAnneeAcEncours());
			result= getGestionressource().listerClasseCours(getAnneeAcEncours());	
			for(int i=0;i<result.size();i++){
//				liste.add(new ClasseBean());
				 effectif = getGestionressource().effectif(getAnneeEncours(),(Classe) result.get(i)[0]);
				 taux = getGestionBulletin().tauxClasse(getAnneeEncours(),(Classe) result.get(i)[0],seq)*100;
				 moyenne = getGestionBulletin().moyenneClasse(getAnneeEncours(),(Classe) result.get(i)[0],seq);
				 Classe clas = (Classe) result.get(i)[0];
				 
				  bilanb= new BilanBean();
				  bilanb.setCodeclasse(clas.getCodeclasse());
				  bilanb.setLibelleclasse(clas.getLibelle());
				  bilanb.setDatedebut(seq.getDatedebut());
				  bilanb.setDatefin(seq.getDatefin());
				  bilanb.setEffectif(effectif);
				  bilanb.setMoyenneclasse(moyenne);
				  bilanb.setTauxreussite(taux);
				  bilanb.setNumerosequence(seq.getNumerosequence());
				liste.add(bilanb);
			}
			
			
		}
		catch(Exception e){
			Repertoire.logFatal("listing bilan error", this.getClass(), e);
			
		}
		
		return liste;
	}


	public boolean initbilan(BilanBean bilanBean) {
		int effectif = 0;
		float taux;
		double moyenne;
		double moyennep;
		double moyenned;
		float tauxechec;
		try{
			Sequence seq=getGestionEtablissement().rechercheSequence(bilanBean.getNumerosequence(),getAnneeAcEncours());	
//				liste.add(new ClasseBean());
			 Classe clas = getGestionressource().rechercheclasse(bilanBean.getCodeclasse());
				 effectif = getGestionressource().effectif(getAnneeEncours(),clas);
				 taux = getGestionBulletin().tauxClasse(getAnneeEncours(),clas,seq)*100;
				 tauxechec = 100-taux;
				 moyenne = getGestionBulletin().moyenneClasse(getAnneeEncours(),clas,seq);
				 moyennep= getGestionBulletin().moyennePremier(getAnneeEncours(),clas,seq);
				 moyenned= getGestionBulletin().moyenneDernier(getAnneeEncours(),clas,seq);
				 bilanBean.setCodeclasse(clas.getCodeclasse());
				  bilanBean.setLibelleclasse(clas.getLibelle());
				  bilanBean.setDatedebut(seq.getDatedebut());
				  bilanBean.setDatefin(seq.getDatefin());
				  bilanBean.setEffectif(effectif);
				  bilanBean.setMoyenneclasse(moyenne);
				  bilanBean.setTauxreussite(taux);
				  bilanBean.setTauxechec(tauxechec);
				  bilanBean.setMoyeneepremier(moyennep);
				  bilanBean.setMoyennedernier(moyenned);
				  bilanBean.setNumerosequence(seq.getNumerosequence());

			return true;
			
		}
		catch(Exception e){
			Repertoire.logFatal("initiation bilan error", this.getClass(), e);
			return false;
		}
	}


	public void initBilanTrimEnCours(BilanTrimBean BilanTrimBean) {
		// TODO Auto-generated method stub
		
	}


	public boolean initbilanTrim(BilanTrimBean bilanTrimBean) {
		int effectif = 0;
		float taux;
		double moyenne;
		double moyennep;
		double moyenned;
		float tauxechec;
		try{
			Classe clas = getGestionressource().rechercheclasse(bilanTrimBean.getCodeclasse());
			Trimestre trim=getGestionEtablissement().rechercheTrimestre(bilanTrimBean.getTrim(),getAnneeAcEncours());
			List<Integer> sequences=getGestionEtablissement().rechercherSequencesTrimestre(bilanTrimBean.getTrim(), getAnneeAcEncours());
			int seq1=1;
			int seq2=2;
			
			
			if(sequences==null ||sequences.isEmpty()){
				return false;
			}
			else{
				if(sequences.size()==1){
					seq1=sequences.get(0);
					seq2=sequences.get(0);
				}
				else{
					seq1=sequences.get(0);
					seq2=sequences.get(1);
					
				}
				
			}
			Sequence sequence1=getGestionEtablissement().rechercheSequence(seq1,getAnneeAcEncours());	
			Sequence sequence2=getGestionEtablissement().rechercheSequence(seq2,getAnneeAcEncours());		
			effectif = getGestionressource().effectif(getAnneeEncours(),clas);
			taux = getGestionBulletin().tauxreussitetrim(getAnneeEncours(),clas,sequence1,sequence2)*100;
			moyenne = getGestionBulletin().moyenneClasseTrim(getAnneeEncours(),clas,sequence1,sequence2);
			moyennep= getGestionBulletin().moyennePremier(getAnneeEncours(),clas,sequence1,sequence2);
			moyenned= getGestionBulletin().moyenneDernier(getAnneeEncours(),clas,sequence1,sequence2);
			tauxechec = 100-taux;
				 
				 
			bilanTrimBean.setCodeclasse(clas.getCodeclasse());
			bilanTrimBean.setLibelleclasse(clas.getLibelle());
			bilanTrimBean.setDatedebut(trim.getDatedebut());
			bilanTrimBean.setDatefin(trim.getDatefin());
			bilanTrimBean.setEffectif(effectif);
			bilanTrimBean.setMoyenneclasse(moyenne);
			bilanTrimBean.setTauxreussite(taux);
			bilanTrimBean.setTauxechec(tauxechec);
			bilanTrimBean.setMoyeneepremier(moyennep);
			bilanTrimBean.setMoyennedernier(moyenned);
			bilanTrimBean.setTrim(bilanTrimBean.getTrim());

			return true;
			
		}
		catch(Exception e){
			Repertoire.logFatal("initiation bilan error", this.getClass(), e);
			return false;
		}
	}


	public void initialiseTrimestreBean(TrimestreBean trimestre) {
		Trimestre trim=getGestionEtablissement().rechercheTrimestre(trimestre.getNumero(),getAnneeAcEncours());
		if(trim!=null)
			CopyUtil.copieTrimestreTotrimestreBean(trim,trimestre);
		
	}


	public List<BilanTrimBean> listeBilanTrim(int numero) {
		List<Object[]> result=null;
		int effectif = 0;
		float taux;
		double moyenne;
		List<BilanTrimBean> liste=new ArrayList<BilanTrimBean>();
		BilanTrimBean bilanb = null;
		try{
			Trimestre trim=getGestionEtablissement().rechercheTrimestre(numero,getAnneeAcEncours());
			List<Integer> sequences=getGestionEtablissement().rechercherSequencesTrimestre(numero, getAnneeAcEncours());
			int seq1=1;
			int seq2=2;
			
			
			if(sequences==null ||sequences.isEmpty()){
				return null;
			}
			else{
				if(sequences.size()==1){
					seq1=sequences.get(0);
					seq2=sequences.get(0);
				}
				else{
					seq1=sequences.get(0);
					seq2=sequences.get(1);
					
				}
				
			}
			Sequence sequence1=getGestionEtablissement().rechercheSequence(seq1,getAnneeAcEncours());	
			Sequence sequence2=getGestionEtablissement().rechercheSequence(seq2,getAnneeAcEncours());	
			result= getGestionressource().listerClasseCours(getAnneeAcEncours());	
			for(int i=0;i<result.size();i++){
//				liste.add(new ClasseBean());
				 effectif = getGestionressource().effectif(getAnneeEncours(),(Classe) result.get(i)[0]);
				 taux = getGestionBulletin().tauxreussitetrim(getAnneeEncours(),(Classe) result.get(i)[0],sequence1,sequence2)*100;
				 moyenne = getGestionBulletin().moyenneClasseTrim(getAnneeEncours(),(Classe) result.get(i)[0],sequence1,sequence2);
				 Classe clas = (Classe) result.get(i)[0];
				 
				  bilanb= new BilanTrimBean();
				  bilanb.setCodeclasse(clas.getCodeclasse());
				  bilanb.setLibelleclasse(clas.getLibelle());
				  bilanb.setDatedebut(trim.getDatedebut());
				  bilanb.setDatefin(trim.getDatefin());
				  bilanb.setEffectif(effectif);
				  bilanb.setMoyenneclasse(moyenne);
				  bilanb.setTauxreussite(taux);
				  bilanb.setTrim(numero);
				liste.add(bilanb);
			}
			
			
		}
		catch(Exception e){
			Repertoire.logFatal("listing bilan trimestriel error", this.getClass(), e);
			
		}
		
		return liste;
	}


	public boolean initbilanAn(BilanAnBean bilanAnBean) {
		int effectif = 0;
		float taux;
		double moyenne;
		double moyennep;
		double moyenned;
		float tauxechec;
		FacesContext context = FacesContext.getCurrentInstance();
		 SessionBean session = (SessionBean) context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{sessionBean}", SessionBean.class).getValue(context.getELContext());
		try{
			Classe clas = getGestionressource().rechercheclasse(bilanAnBean.getCodeclasse());
			Annee an=getGestionEtablissement().rechercheAnneeI(bilanAnBean.getAnneeacademique(),session.getCodeetablissement());		
			effectif = getGestionressource().effectif(getAnneeEncours(),clas);
			
			taux = getGestionBulletin().tauxreussiteAnnuel(getAnneeEncours(),clas,1,2,3)*100;
			moyenne = getGestionBulletin().moyenneClasseAnnuel(getAnneeEncours(),clas,1,2,3);
			moyennep= getGestionBulletin().moyennePremierAnnuel(getAnneeEncours(),clas,1,2,3);
			moyenned= getGestionBulletin().moyenneDernierAnnuel(getAnneeEncours(),clas,1,2,3);
			tauxechec = 100-taux;
				 
				 
			bilanAnBean.setCodeclasse(clas.getCodeclasse());
			bilanAnBean.setLibelleclasse(clas.getLibelle());
			bilanAnBean.setDatedebut(an.getDateDebut());
			bilanAnBean.setDatefin(an.getDateFin());
			bilanAnBean.setEffectif(effectif);
			bilanAnBean.setMoyenneclasse(moyenne);
			bilanAnBean.setTauxreussite(taux);
			bilanAnBean.setTauxechec(tauxechec);
			bilanAnBean.setMoyeneepremier(moyennep);
			bilanAnBean.setMoyennedernier(moyenned);
			bilanAnBean.setAnneeacademique(an.getAnneeacademique());

			return true;
			
		}
		catch(Exception e){
			Repertoire.logFatal("initiation bilan annuel error", this.getClass(), e);
			return false;
		}
	}


	public void initBilanAnEnCours(BilanTrimBean BilanTrimBean) {
		// TODO Auto-generated method stub
		
	}


	public void initialiseAnneeBean(AnneeBean annee) throws AnneeEnCoursNonDefinieException {
		 FacesContext context = FacesContext.getCurrentInstance();
		 SessionBean session = (SessionBean) context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{sessionBean}", SessionBean.class).getValue(context.getELContext());
		Annee an=getGestionEtablissement().rechercheAnneeI(annee.getAnneeacademique(),session.getCodeetablissement());
		if(an!=null)
			CopyUtil.copieAnneeToAnneeBean(an,annee);
		
	}


	public List<BilanAnBean> listeBilanAnnuel(String anneeacademique) {
		List<Object[]> result=null;
		int effectif = 0;
		float taux;
		double moyenne;
		List<BilanAnBean> liste=new ArrayList<BilanAnBean>();
		BilanAnBean bilanb = null;
		FacesContext context = FacesContext.getCurrentInstance();
		 SessionBean session = (SessionBean) context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{sessionBean}", SessionBean.class).getValue(context.getELContext());
		try{

			result= getGestionressource().listerClasseCours(getAnneeAcEncours());	
			for(int i=0;i<result.size();i++){

				Annee an=getGestionEtablissement().rechercheAnneeI(anneeacademique,session.getCodeetablissement());		
				effectif = getGestionressource().effectif(getAnneeEncours(),(Classe) result.get(i)[0]);
				
				taux = getGestionBulletin().tauxreussiteAnnuel(getAnneeEncours(),(Classe) result.get(i)[0],1,2,3)*100;
				moyenne = getGestionBulletin().moyenneClasseAnnuel(getAnneeEncours(),(Classe) result.get(i)[0],1,2,3);
				 Classe clas = (Classe) result.get(i)[0];
				 
				  bilanb= new BilanAnBean();
				  bilanb.setCodeclasse(clas.getCodeclasse());
				  bilanb.setLibelleclasse(clas.getLibelle());
				  bilanb.setDatedebut(an.getDateDebut());
				  bilanb.setDatefin(an.getDateFin());
				  bilanb.setEffectif(effectif);
				  bilanb.setMoyenneclasse(moyenne);
				  bilanb.setTauxreussite(taux);
				  bilanb.setAnneeacademique(an.getAnneeacademique());
				liste.add(bilanb);
			}
			
			
		}
		catch(Exception e){
			Repertoire.logFatal("listing bilan annuel error", this.getClass(), e);
			
		}
		
		return liste;
	}


	public List<AnneeBean> listeannees() {
		return CopyUtil.listeAnneeVersListeAnneeBean(getGestionEtablissement().listerAnneesAcademiques());
	}

	public void modifierCompte(String login, String password,
			String ancienmotdepasse) throws UnAuthorizedOperationException, ElementNOtFoundException {
		getGestionutilisateur().modifierUtilisateur(login,password,ancienmotdepasse,getIduser());
		
	}


	public List<MaterielBean> listematriels() {
		List<Materiel> result=null;
		MaterielBean mat=null;
		result= getGestionMateriel().listermateriel(getAnneeAcEncours());
		List<MaterielBean> liste=new ArrayList<MaterielBean>();
		for(int i=0;i<result.size();i++){
			mat = new MaterielBean();
			mat.setCodemateriel(result.get(i).getCodemateriel());
			mat.setDesignation(result.get(i).getDesignation());
			mat.setPrix(result.get(i).getPrix());
			mat.setQuantite(result.get(i).getQuantite());
			mat.setTypemateriel(result.get(i).getTypemateriel());
			mat.setQuantiteF(result.get(i).getQuantiteF());
			mat.setEtat(result.get(i).getEtat());
			mat.setIdmateriel(result.get(i).getIdmateriel());
			mat.setNumeroserie(result.get(i).getNumeroserie());
			liste.add(mat);
			
		}
		return liste;
	}


	public List<SortieMBean> listesortiems() {
		List<SortieM> result=null;
		SortieMBean sort=null;
		result= getGestionMateriel().listersortie(getAnneeAcEncours());
		List<SortieMBean> liste=new ArrayList<SortieMBean>();
		for(int i=0;i<result.size();i++){
		    sort = new SortieMBean();
			sort.setDateretrait(result.get(i).getDateretrait());
			sort.setQuantiteSortie(result.get(i).getQuantiteSortie());
			sort.setRaison(result.get(i).getRaison());
			sort.setIdsortie(result.get(i).getIdsortie());
			sort.setDesignation(result.get(i).getMateriel().getDesignation());
			sort.setCodemateriel(result.get(i).getMateriel().getCodemateriel());
			sort.setQuantite(result.get(i).getMateriel().getQuantiteF());
			sort.setTypemateriel(result.get(i).getMateriel().getTypemateriel());
			sort.setDaterenreg(result.get(i).getMateriel().getDateenreg());
			liste.add(sort);
			
		}
		return liste;
	}


	public String saveMateriel(MaterielBean materiel) throws ElementNOtFoundException {
		String result="";
		
		result= getGestionMateriel().createMateriel(materiel.getDesignation(),materiel.getPrix(),materiel.getQuantite(),materiel.getTypemateriel(),materiel.getEtat(),materiel.getQuantiteF(),getAnneeEncours(),getInfosSession().getCodeetablissement(),materiel.getDateenreg(),materiel.getNumeroserie());		
		return result;
		
	}


	public boolean modifierMateriel(MaterielBean materiel) throws ElementNOtFoundException {
		
		return getGestionMateriel().modifierMateriel(materiel.getCodemateriel(),materiel.getDesignation(),materiel.getPrix(),materiel.getQuantite(),materiel.getTypemateriel(),materiel.getEtat(),materiel.getQuantiteF(),getAnneeEncours(),getInfosSession().getCodeetablissement(),materiel.getDateenreg(),materiel.getIdmateriel(),materiel.getNumeroserie());		
	}


	public boolean supprimerMateriel(int idmateriel) throws ElementNOtFoundException {

		return getGestionMateriel().supprimerMateriel(idmateriel);		
	}


	public void initialiseMaterielBean(MaterielBean mat) {
		Materiel materiel=null;
		materiel= getGestionMateriel().recherchermateriel(mat.getIdmateriel(),getAnneeAcEncours(),getInfosSession().getCodeetablissement());
			mat.setCodemateriel(materiel.getCodemateriel());
			mat.setDesignation(materiel.getDesignation());
			mat.setPrix(materiel.getPrix());
			mat.setQuantite(materiel.getQuantite());
			mat.setTypemateriel(materiel.getTypemateriel());
			mat.setQuantiteF(materiel.getQuantiteF());
			mat.setEtat(materiel.getEtat());
			mat.setDateenreg(materiel.getDateenreg());
			mat.setIdmateriel(materiel.getIdmateriel());
			mat.setNumeroserie(materiel.getNumeroserie());
			mat.setReste(materiel.getReste());
			mat.setDateenreg(materiel.getDateenreg());

		
	}


	public void initMateriel(MaterielBean mat) {
		Materiel materiel=null;
		materiel= getGestionMateriel().recherchermateriel(mat.getIdmateriel(),getAnneeAcEncours(),getInfosSession().getCodeetablissement());
			mat.setCodemateriel(materiel.getCodemateriel());
			mat.setDesignation(materiel.getDesignation());
			mat.setPrix(materiel.getPrix());
			mat.setQuantite(materiel.getQuantite());
			mat.setTypemateriel(materiel.getTypemateriel());
			mat.setQuantiteF(materiel.getQuantiteF());
			mat.setEtat(materiel.getEtat());
			mat.setDateenreg(materiel.getDateenreg());
			mat.setIdmateriel(materiel.getIdmateriel());
			mat.setNumeroserie(materiel.getNumeroserie());
			mat.setReste(materiel.getReste());
			mat.setDateenreg(materiel.getDateenreg());
	}


	public String saveMaterielAjouter(String codemateriel, String designation,
			String typemateriel, Date dateenreg, int quantite, int quantiteF,
			float prix, String numeroserie) throws ElementNOtFoundException {
		String result="";
		result= getGestionMateriel().createMaterielAjout(designation,prix,quantite,typemateriel,quantiteF,getAnneeEncours(),getInfosSession().getCodeetablissement(),dateenreg,codemateriel,numeroserie);		
		return result;
		
	}


	public List<MaterielBean> listematrielss() {
		List<Materiel> result=null;
		MaterielBean mat=null;
		result= getGestionMateriel().listermateriels(getAnneeAcEncours());
		List<MaterielBean> liste=new ArrayList<MaterielBean>();
		for(int i=0;i<result.size();i++){
			mat = new MaterielBean();
			mat.setCodemateriel(result.get(i).getCodemateriel());
			mat.setDesignation(result.get(i).getDesignation());
			mat.setPrix(result.get(i).getPrix());
			mat.setQuantite(result.get(i).getQuantite());
			mat.setTypemateriel(result.get(i).getTypemateriel());
			mat.setQuantiteF(result.get(i).getQuantiteF());
			mat.setEtat(result.get(i).getEtat());
			mat.setIdmateriel(result.get(i).getIdmateriel());
			mat.setNumeroserie(result.get(i).getNumeroserie());
			mat.setReste(result.get(i).getReste());
			mat.setDateenreg(result.get(i).getDateenreg());
			liste.add(mat);
			
		}
		return liste;
	}


	public String saveMaterielRetrait(int idmateriel, String codemateriel, String designation,
			String typemateriel, Date dateenreg, int quantite, int quantiteF,
			float prix, int quantiteF2, int quantite2, String numeroserie, String raison, String utlisateur) throws ElementNOtFoundException {
		String result="";
		result= getGestionMateriel().retraitMateriel(idmateriel,designation,prix,quantite,typemateriel,quantiteF,getAnneeEncours(),getInfosSession().getCodeetablissement(),dateenreg,codemateriel,numeroserie,quantiteF2,quantite2,raison,utlisateur);		
		return result;
		
	}


	public void initialiseSortieBean(SortieMBean sort) {
		SortieM sot=null;
		sot= getGestionMateriel().recherchermaterielS(sort.getIdsortie(),getAnneeAcEncours(),getInfosSession().getCodeetablissement());
			sort.setCodemateriel(sot.getMateriel().getCodemateriel());
			sort.setDesignation(sot.getMateriel().getDesignation());
			sort.setQuantiteSortie(sot.getQuantiteSortie());
			sort.setIdsortie(sot.getIdsortie());
			sort.setRaison(sot.getRaison());
			sort.setTypemateriel(sot.getMateriel().getTypemateriel());
			sort.setUtlisateur(sot.getUtlisateur());
			sort.setDateretrait(sot.getDateretrait());
			sort.setQuantite(sot.getMateriel().getQuantiteF());
			sort.setDaterenreg(sot.getMateriel().getDateenreg());
	}


	public String savePrevision(String typeprevision, float montant,
			Date dateenreg, String description) throws ElementNOtFoundException {
		String result="";
		
		result= getGestionPaye().createPrevision(typeprevision,montant,dateenreg,getAnneeEncours(),getInfosSession().getCodeetablissement(),getInfosSession().getIdutilisateur(),description);		
		return result;
		
	}


	public String savePrevisionAjouter(String codeprevision, int idprevision,
			String typeprevision, Date dateenreg, String description,
			float montant) throws ElementNOtFoundException {
		String result="";
		
		result= getGestionPaye().createPrevisionAjout(codeprevision,idprevision,montant,dateenreg,getAnneeEncours(),getInfosSession().getCodeetablissement(),getInfosSession().getIdutilisateur(),description);		
		return result;
		
	}


	public boolean modifierPrevision(String codeprevision, int idprevision,
			String typeprevision, float montant, Date dateenreg,
			String description) throws ElementNOtFoundException {
		return getGestionPaye().modifierPrevision(codeprevision,idprevision,montant,dateenreg,getAnneeEncours(),getInfosSession().getCodeetablissement(),getInfosSession().getIdutilisateur(),description);		
		
	}


	public void initialisePrevisionBean(PrevisionBean prev) {
		Prevision pre=null;
		pre= getGestionPaye().rechercherprevision(prev.getIdprevision(),getAnneeAcEncours(),getInfosSession().getCodeetablissement());
		prev.setCodeprevision(pre.getCodeprevision());
		prev.setDateenreg(pre.getDateenreg());
		prev.setMontant(pre.getMontant());
		prev.setReste(pre.getReste());
		prev.setDescription(pre.getDescription());
		prev.setTranfert(pre.getTranfert());
		prev.setTypeprevision(pre.getTypeprevision());
		prev.setIdprevision(pre.getIdprevision());
		prev.setNomrespon(pre.getPersonnel().getNom());
		prev.setPrenomrespon(pre.getPersonnel().getPrenom());
		
	}


	public void initPrevision(PrevisionBean prev) {
		Prevision pre=null;
		pre= getGestionPaye().rechercherprevision(prev.getIdprevision(),getAnneeAcEncours(),getInfosSession().getCodeetablissement());
		prev.setCodeprevision(pre.getCodeprevision());
		prev.setDateenreg(pre.getDateenreg());
		prev.setDescription(pre.getDescription());
		prev.setMontant(pre.getMontant());
		prev.setReste(pre.getReste());
		prev.setTranfert(pre.getTranfert());
		prev.setTypeprevision(pre.getTypeprevision());
		prev.setIdprevision(pre.getIdprevision());
		prev.setNomrespon(pre.getPersonnel().getNom());
		prev.setPrenomrespon(pre.getPersonnel().getPrenom());
		
	}


	public String saveDepense(int idprevision, Date dateenreg, float montant,
			String description, String typeprevision) throws ElementNOtFoundException {
String result="";
		
		result= getGestionPaye().createDepense(idprevision,montant,dateenreg,typeprevision,getAnneeEncours(),getInfosSession().getCodeetablissement(),getInfosSession().getIdutilisateur(),description);		
		return result;
		
	}


	public List<String> listeTypePrevision() {
		return getGestionPaye().listeTypesPrevision();
	}


	public List<PrevisionBean> listeprevisions() {
		List<Prevision> result=null;
		PrevisionBean pre=null;
		result= getGestionPaye().listerprevision(getAnneeAcEncours(), getInfosSession().getCodeetablissement());
		List<PrevisionBean> liste=new ArrayList<PrevisionBean>();
		for(int i=0;i<result.size();i++){
			pre = new PrevisionBean();
			pre.setCodeprevision(result.get(i).getCodeprevision());
			pre.setIdprevision(result.get(i).getIdprevision());
			pre.setDateenreg(result.get(i).getDateenreg());
			pre.setDescription(result.get(i).getDescription());
			pre.setMontant(result.get(i).getMontant());
			pre.setReste(result.get(i).getReste());
			pre.setTranfert(result.get(i).getTranfert());
			pre.setTypeprevision(result.get(i).getTypeprevision());
			pre.setNomrespon(result.get(i).getPersonnel().getNom());
			pre.setPrenomrespon(result.get(i).getPersonnel().getPrenom());

			liste.add(pre);
			
		}
		return liste;
	}


	public List<DepenseBean> listedepenses() {
		List<Depense> result=null;
		DepenseBean dep=null;
		result= getGestionPaye().listerdepense(getAnneeAcEncours(), getInfosSession().getCodeetablissement());
		List<DepenseBean> liste=new ArrayList<DepenseBean>();
		for(int i=0;i<result.size();i++){
			dep = new DepenseBean();
			dep.setCodedepense(result.get(i).getCodedepense());
			dep.setIddepense(result.get(i).getIddepense());
			dep.setDateenreg(result.get(i).getDateenreg());
			dep.setDescription(result.get(i).getDescription());
			dep.setMontant(result.get(i).getMontant());
			dep.setTypedepense(result.get(i).getTypedepense());
			dep.setNomrespon(result.get(i).getPersonnel().getNom());
			dep.setPrenomrespon(result.get(i).getPersonnel().getPrenom());

			liste.add(dep);
			
		}
		return liste;
	}


	public void initialiseDepenseBean(DepenseBean dep) {
		Depense depe=null;
		depe= getGestionPaye().rechercherdepense(dep.getIddepense(), getAnneeAcEncours(), getInfosSession().getCodeetablissement());
			dep.setCodedepense(depe.getCodedepense());
			dep.setIddepense(depe.getIddepense());
			dep.setDateenreg(depe.getDateenreg());
			dep.setDescription(depe.getDescription());
			dep.setMontant(depe.getMontant());
			dep.setTypedepense(depe.getTypedepense());
			dep.setNomrespon(depe.getPersonnel().getNom());
			dep.setPrenomrespon(depe.getPersonnel().getPrenom());

		
	}


	public String savetranfert(String codeprevision, int idprevision,
			String typeprevision, Date dateenreg, String description,
			float montant, String typeprevision2, int idprevision2) throws ElementNOtFoundException {
		String result="";
		
		result= getGestionPaye().createTransfert(codeprevision,idprevision,montant,dateenreg,getAnneeEncours(),getInfosSession().getCodeetablissement(),getInfosSession().getIdutilisateur(),description,typeprevision2,idprevision2);		
		return result;
		
	}

	/**
	 * 
	 * @param codeclasse
	 * @param eleves
	 * @param sequence
	 * @throws JRException
	 * @throws IOException
	 */
	public void imprimerRecapitulatif(String codeclasse, int sequence) throws JRException, IOException {
		List<Object[]> liste=getGestionBulletin().listerElevesNotesClasse(sequence, codeclasse, getAnneeAcEncours());
		Etablissement etab = getGestionEtablissement().rechercheEtablissement(getInfosSession().getCodeetablissement());
		Classe cla = getGestionressource().rechercheclasse(codeclasse);
		int effectif = getGestionBulletin().effectif(codeclasse, getAnneeEncours());
		getImpressionUtils().imprimerRecapsequence(liste,cla.getLibelle(),etab.getNometab(),getAnneeEncours(),sequence,etab.getPays(),etab.getDevise(),etab.getDevisepays(),effectif);
		
	}
	
	
	/**
	 * 
	 * @param codeclasse
	 * @param eleves
	 * @param sequence
	 * @throws JRException
	 * @throws IOException
	 */
	public void imprimerRecapitulatifM(String codeclasse, int sequence) throws JRException, IOException {
		List<Object[]> liste=getGestionBulletin().listerElevesNotesClasse(sequence, codeclasse, getAnneeAcEncours());
		Etablissement etab = getGestionEtablissement().rechercheEtablissement(getInfosSession().getCodeetablissement());
		Classe cla = getGestionressource().rechercheclasse(codeclasse);
		int effectif = getGestionBulletin().effectif(codeclasse, getAnneeEncours());
		long sommecoef = getGestionBulletin().totalcoef(codeclasse, getAnneeEncours());
		getImpressionUtils().imprimerRecapsequenceM(liste,cla.getLibelle(),etab.getNometab(),getAnneeEncours(),sequence,etab.getPays(),etab.getDevise(),etab.getDevisepays(),effectif,sommecoef);
		
	}
	
	/**
	 * 
	 * @param codeclasse
	 * @param eleves
	 * @param trimestre
	 * @throws JRException
	 * @throws IOException
	 */
	public void imprimerRecapTrim(String codeclasse, int trimestre) throws JRException, IOException {
		List<Object[]> liste1 = null;
		List<Object[]> liste2 = null;
		List<Object[]> liste3 = null;
		
		if(trimestre==1){
		
			 liste1=getGestionBulletin().listerElevesNotesClasse(1, codeclasse, getAnneeAcEncours());
			 liste2=getGestionBulletin().listerElevesNotesClasse(2, codeclasse, getAnneeAcEncours());
			 liste3=getGestionBulletin().listerElevesNotesClasse(3, codeclasse, getAnneeAcEncours());
		
		}
		
		if(trimestre==2){
			
			 liste1=getGestionBulletin().listerElevesNotesClasse(4, codeclasse, getAnneeAcEncours());
			 liste2=getGestionBulletin().listerElevesNotesClasse(5, codeclasse, getAnneeAcEncours());
			 liste3=getGestionBulletin().listerElevesNotesClasse(6, codeclasse, getAnneeAcEncours());
		
		}
		
		if(trimestre==3){
			
			 liste1=getGestionBulletin().listerElevesNotesClasse(7, codeclasse, getAnneeAcEncours());
			 liste2=getGestionBulletin().listerElevesNotesClasse(8, codeclasse, getAnneeAcEncours());
		
		}
		Etablissement etab = getGestionEtablissement().rechercheEtablissement(getInfosSession().getCodeetablissement());
		Classe cla = getGestionressource().rechercheclasse(codeclasse);
		int effectif = getGestionBulletin().effectif(codeclasse, getAnneeEncours());
		getImpressionUtils().imprimerRecapTrim(liste1,liste2,liste3, cla.getLibelle(),etab.getNometab(),getAnneeEncours(),trimestre,etab.getPays(),etab.getDevise(),etab.getDevisepays(),effectif);
		
	}
	
	
	/**
	 * 
	 * @param codeclasse
	 * @param eleves
	 * @param trimestre
	 * @throws JRException
	 * @throws IOException
	 */
	public void imprimerRecapTrimM(String codeclasse, int trimestre) throws JRException, IOException {
		List<Object[]> liste1 = null;
		List<Object[]> liste2 = null;
		List<Object[]> liste3 = null;
		
		if(trimestre==1){
		
			 liste1=getGestionBulletin().listerElevesNotesClasse(1, codeclasse, getAnneeAcEncours());
			 liste2=getGestionBulletin().listerElevesNotesClasse(2, codeclasse, getAnneeAcEncours());
			 liste3=getGestionBulletin().listerElevesNotesClasse(3, codeclasse, getAnneeAcEncours());
		
		}
		
		if(trimestre==2){
			
			 liste1=getGestionBulletin().listerElevesNotesClasse(4, codeclasse, getAnneeAcEncours());
			 liste2=getGestionBulletin().listerElevesNotesClasse(5, codeclasse, getAnneeAcEncours());
			 liste3=getGestionBulletin().listerElevesNotesClasse(6, codeclasse, getAnneeAcEncours());
		
		}
		
		if(trimestre==3){
			
			 liste1=getGestionBulletin().listerElevesNotesClasse(7, codeclasse, getAnneeAcEncours());
			 liste2=getGestionBulletin().listerElevesNotesClasse(8, codeclasse, getAnneeAcEncours());
		
		}
		Etablissement etab = getGestionEtablissement().rechercheEtablissement(getInfosSession().getCodeetablissement());
		Classe cla = getGestionressource().rechercheclasse(codeclasse);
		int effectif = getGestionBulletin().effectif(codeclasse, getAnneeEncours());
		long sommecoef = getGestionBulletin().totalcoef(codeclasse, getAnneeEncours());
		getImpressionUtils().imprimerRecapTrimM(liste1,liste2,liste3, cla.getLibelle(),etab.getNometab(),getAnneeEncours(),trimestre,etab.getPays(),etab.getDevise(),etab.getDevisepays(),effectif, sommecoef);
		
	}

	

}
	
