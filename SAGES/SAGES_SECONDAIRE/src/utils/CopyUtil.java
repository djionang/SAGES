package utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ages.beans.anneeacademique.AnneeBean;
import ages.beans.anneeacademique.SequenceBean;
import ages.beans.anneeacademique.TrimestreBean;
import ages.beans.auth.CompteBean;
import ages.beans.auth.GroupeUserBean;
import ages.beans.auth.ItemRoleBean;
import ages.beans.eleve.EleveBean;
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
import ages.beans.etablissement.discipline.RetardBean;
import ages.beans.etablissement.discipline.SanctionBean;
import ages.beans.etablissement.discipline.TypeSanctionBean;
import ages.beans.etablissement.evaluation.EvaluationBean;
import ages.beans.etablissement.evaluation.TypeEvaluationBean;
import ages.beans.etablissement.note.CompositionBean;
import ages.beans.etablissement.personnel.PersonnelBean;
import ages.beans.etablissement.salle.SalleBean;
import ages.beans.etablissement.salle.TypeSalleBean;
import ages.beans.inscription.DossierBean;
import ages.beans.inscription.TrancheBean;
import ages.beans.inscription.VersementBean;
import ages.exception.ClassToBeanCopyException;
import ejb.GestionBulletinNotesLocal;
import ejb.GestionInscriptionLocal;
import entities.Absence;
import entities.Annee;
import entities.CahierDeTexte;
import entities.Classe;
import entities.Composer;
import entities.Convocation;
import entities.Cour;
import entities.Cycle;
import entities.Eleve;
import entities.Enseignant;
import entities.Enseignement;
import entities.Etablissement;
import entities.Evaluation;
import entities.GroupeUser;
import entities.Groupematiere;
import entities.ItemRole;
import entities.Matiere;
import entities.Niveau;
import entities.OptionSerie;
import entities.ParametreTranche;
import entities.PartieCour;
import entities.Personnel;
import entities.PreInscription;
import entities.Retard;
import entities.Salle;
import entities.Sanction;
import entities.Section;
import entities.Sequence;
import entities.Trimestre;
import entities.TypeEvaluation;
import entities.TypeSalle;
import entities.TypeSanction;
import entities.Utilisateur;
import entities.Versement;

/**
 * Cette classe copie les attributs d'un bean vers ceux d'une classe DAO et vice versa
 * @author Administrateur
 *
 */
public class CopyUtil {
	

	/**
	 * classeToClasseBean
	 * Convertit une Classe en ClasseBean
	 * @param clas Classe a convertir
	 * @param effectif effectif actuel de la classe
	 * @return le ClasseBean correspondant
	 */
	public static ClasseBean copieClasseToClasseBean(ClasseBean classb,Classe clas, int effectif){
		String nomens;
		try{
			classb.setCodeClasse(clas.getCodeclasse());
			
			//Dans le cas où la classe possède un enseigant titulaire, on met à jour son nom et son code au sein du bean
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
		catch(Exception e){
			Repertoire.logFatal("Impossible de transformer la Classe en ClasseBean",utils.CopyUtil.class, e);
		}
		return classb;
	}

	public static void copieEnseignementVersEnseignementBean(
			EnseignementBean eb, Enseignement ens) {
		if(ens==null) return;
		if(ens.getEtablissement()!=null){
			eb.setEtablissement(ens.getEtablissement().getCodeetablissement());
			eb.setAcronymeEtablissement(ens.getEtablissement().getAcronyme());
		}
			eb.setDescription(ens.getDescription());
			eb.setLibelleens(ens.getLibelleens());
			eb.setType(ens.getType());
		
	}

	public static void copieSectionVersSectionBean(
			SectionBean sb, Section s) {
		if(s==null) return;
		if(s.getEnseignement()!=null){
			sb.setEnseignement(s.getEnseignement().getLibelleens());
			
		}
		sb.setDescription(s.getDescription());
		sb.setCodesection(s.getCodesection());
		
		sb.setLibelle(s.getLibelle());
		
	}

	public static void copieEtablissementVersEtablissementBean(
			EtablissementBean eb, Etablissement etab) {
	
		if(etab!=null){
			eb.setAcronyme(etab.getAcronyme());
			eb.setAdresse(etab.getAdresse());
			eb.setArrondissement(etab.getArrondissement());
			
			eb.setCodeetablissement(etab.getCodeetablissement());
			eb.setCodepostal(etab.getCodepostal());
			eb.setDepartement(etab.getDepartement());
			eb.setDevise(etab.getDevise());
			eb.setEmail(etab.getEmail());
			eb.setEstCharge(true);
			eb.setLogo(etab.getLogo());
			eb.setNom(etab.getNometab());
			eb.setPays(etab.getPays());
			eb.setRegion(etab.getRegion());
			eb.setSiteweb(etab.getSiteweb());
			eb.setTelephone(etab.getTelephone());
			eb.setType(etab.getType());
			eb.setLogingest(etab.getLogin_gest());
		}
		
	}

	public static void copieCycleVersCycleBean(CycleBean cb, Cycle c) {
		if(c==null) return;
		if(c.getSection()!=null)
			cb.setCodesection(c.getSection().getCodesection());
		cb.setCodeCycle(c.getCodecycle());
		cb.setLibelle(c.getLibelle());
			
	}

	public static void copieNiveauVersNiveauBean(NiveauBean nb, Niveau n) {
		if(n==null) return;
		if(n.getCycle()!=null){
			nb.setCodeCycle(n.getCycle().getCodecycle());
			nb.setLibellecycle(n.getCycle().getLibelle());
		}
		nb.setCodeNiveau(n.getCodeniveau());
		nb.setLibelle(n.getLibelle());
			
	}
	
	public static void copieNiveauVersNiveauBeanB(NiveauBean nb, Niveau n) {
		if(n==null) return;
		nb.setCodeNiveau(n.getCodeniveau());
			
	}

	public static void copieOptionVersOptionBean(OptionBean ob, OptionSerie o){
		if(o==null) return;
		if(o.getNiveau()!=null){
			ob.setCodeniveau(o.getNiveau().getCodeniveau());
			ob.setLibelleniveau(o.getNiveau().getLibelle());
		}
		ob.setLibelle(o.getLibelle());
		ob.setCodeOption(o.getCodeoption());
	}

	/**
	 * PreinscriptionToDossierBean
	 * Copie un Preinscription dans un DossierBean
	 * @param dossier
	 * @param dest
	 */
	public static boolean copiePreinscriptionToDossierBean(DossierBean dossier, PreInscription preins){
		try{
			
		
		dossier.setAdresse(preins.getAdresse());
		dossier.setAncienEtablissement(preins.getNomancienetablissement());
		if(preins.getAnnee()!=null)
			dossier.setAnneeAcademique(preins.getAnnee().getAnneeacademique());
		dossier.setAnneeAncienEtablissement(preins.getAnneeancienetablissement());
		dossier.setBoitePostale(preins.getBoitepostale());
		dossier.setClasseAncienEtablissement(preins.getClasseancienetablissement());
		dossier.setCodedossier(preins.getCodedossier());
		dossier.setDateNaissance(preins.getDatenaissance());
		dossier.setDatePreinscription(preins.getDatepreinscription());
		dossier.setEmail(preins.getEmail());
		dossier.setEmailMere(preins.getEmailmere());
		dossier.setEmailPere(preins.getEmailpere());
		dossier.setEmailTuteur(preins.getEmailtuteur());
		dossier.setEtat(preins.getEtat());
		dossier.setLieuNaissance(preins.getLieunaissance());
		if(preins.getUtilisateur()!=null)
			dossier.setLoginPrincipal(preins.getUtilisateur().getLogin());
		dossier.setNationalite(preins.getNationalite());
		dossier.setNiveauDemande(preins.getNiveaudemande().getCodeniveau());
		dossier.setNom(preins.getNom());
		dossier.setNomMere(preins.getNommere());
		dossier.setNomPere(preins.getNompere());
		dossier.setNomTuteur(preins.getNomtuteur());
		dossier.setNumeroPayement(preins.getNumeropayement());
		dossier.setOptionDemande(preins.getOptiondemande());
		dossier.setPassword(preins.getPasswordaccess());
		dossier.setPrenom(preins.getPrenom());
		dossier.setProfessionMere(preins.getProfessionmere());
		dossier.setProfessionPere(preins.getProfessionpere());
		dossier.setProfessionTuteur(preins.getProfessiontuteur());
		dossier.setSexe(preins.getSexe());
		dossier.setTelephone(preins.getTelephone());
		dossier.setTelephoneMere(preins.getTelephonemere());
		dossier.setTelephonePere(preins.getTelephonepere());
		dossier.setTelephoneTuteur(preins.getTelephonetuteur());
		dossier.setDernieremoyenne(preins.getDernieremoyenne());
		dossier.setMatricule(preins.getMatricule());
		dossier.setSommeverse(preins.getSommeverse());
		}
		catch(Exception e){
			return false;
		}
		return true;
	}

	public static boolean copieSalleToSalleBean(SalleBean sb, Salle sal){
		try{
			
			sb.setCapacite(sal.getCapacite());
			sb.setCodeSalle(sal.getCodesalle());
			sb.setDescription(sal.getDescription());
			sb.setLibelle(sal.getLibelle());
			sb.setType(sal.getTypesalle().getLibelle());
			sb.setCodeType(sal.getTypesalle().getId());
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
	
	
	public static boolean copieEleveToEleveBean(Eleve eleve,EleveBean eb){
		eb.setIdeleve(eleve.getIdeleve());
		eb.setAdresse(eleve.getAdresse());
		eb.setAncien(eleve.getAncien());
		eb.setAncienEtablissement(eleve.getAnneeancienetablissement());
		eb.setAnneeAncienEtablissement(eleve.getAnneeancienetablissement());
		eb.setBoitePostale(eleve.getBoitepostale());
		eb.setClasseAncienEtablissement(eleve.getClasseancienetablissement());
		if(eleve.getClasse()!=null){
			eb.setCodeClasse(eleve.getClasse().getCodeclasse());
			eb.setLibelleClasse(eleve.getClasse().getLibelle());
		}
		
		eb.setConfirme(eleve.getConfirme());
		eb.setDateNaissance(eleve.getDatenaissance());
		eb.setDateEnregistrement(eleve.getDateEnregistrement());
		eb.setEmail(eleve.getEmail());
		eb.setEmailMere(eleve.getEmailmere());
		eb.setEmailPere(eleve.getEmailpere());
		eb.setEmailTuteur(eleve.getEmailtuteur());
		eb.setLieuNaissance(eleve.getLieunaissance());
		eb.setLogin(eleve.getLogin());
		eb.setMatricule(eleve.getMatricule());
		eb.setNationalite(eleve.getNationalite());
		eb.setNom(eleve.getNom());
		eb.setNomMere(eleve.getNommere());
		eb.setNomPere(eleve.getNompere());
		eb.setNomTuteur(eleve.getNomtuteur());
		eb.setNumeroPayement(eleve.getNumeroPayement());
		eb.setPassword(eleve.getPassword());
		eb.setPhoto(eleve.getPhoto());
		eb.setPrenom(eleve.getPrenom());
		eb.setProfessionMere(eleve.getProfessionmere());
		eb.setProfessionPere(eleve.getProfessionpere());
		eb.setProfessionTuteur(eleve.getProfessiontuteur());
		eb.setRedoublant(eleve.getRedoublant());
		eb.setSexe(eleve.getSexe());
		eb.setTelephone(eleve.getTelephone());
		eb.setTelephoneMere(eleve.getTelephonemere());
		eb.setTelephonePere(eleve.getTelephonepere());
		eb.setTelephoneTuteur(eleve.getTelephonetuteur());
		eb.setDernieremoyenne(eleve.getDernieremoyenne());
		return true;
	}
	
	public static boolean copieEleveToEleveBeanA(Eleve eleve,EleveBean eb,GestionBulletinNotesLocal gb){
		double moy=gb.moyenneEleveS(eleve.getClasse().getCodeclasse(), eleve.getIdeleve(), eleve.getClasse().getAnnee().getAnneeacademique());
		eb.setIdeleve(eleve.getIdeleve());
		eb.setAdresse(eleve.getAdresse());
		eb.setAncien(eleve.getAncien());
		eb.setAncienEtablissement(eleve.getAnneeancienetablissement());
		eb.setAnneeAncienEtablissement(eleve.getAnneeancienetablissement());
		eb.setBoitePostale(eleve.getBoitepostale());
		eb.setClasseAncienEtablissement(eleve.getClasseancienetablissement());
		if(eleve.getClasse()!=null){
			eb.setCodeClasse(eleve.getClasse().getCodeclasse());
			eb.setLibelleClasse(eleve.getClasse().getLibelle());
		}
		
		eb.setConfirme(eleve.getConfirme());
		eb.setDateNaissance(eleve.getDatenaissance());
		eb.setDateEnregistrement(eleve.getDateEnregistrement());
		eb.setEmail(eleve.getEmail());
		eb.setEmailMere(eleve.getEmailmere());
		eb.setEmailPere(eleve.getEmailpere());
		eb.setEmailTuteur(eleve.getEmailtuteur());
		eb.setLieuNaissance(eleve.getLieunaissance());
		eb.setLogin(eleve.getLogin());
		eb.setMatricule(eleve.getMatricule());
		eb.setNationalite(eleve.getNationalite());
		eb.setNom(eleve.getNom());
		eb.setNomMere(eleve.getNommere());
		eb.setNomPere(eleve.getNompere());
		eb.setNomTuteur(eleve.getNomtuteur());
		eb.setNumeroPayement(eleve.getNumeroPayement());
		eb.setPassword(eleve.getPassword());
		eb.setPhoto(eleve.getPhoto());
		eb.setPrenom(eleve.getPrenom());
		eb.setProfessionMere(eleve.getProfessionmere());
		eb.setProfessionPere(eleve.getProfessionpere());
		eb.setProfessionTuteur(eleve.getProfessiontuteur());
		eb.setRedoublant(eleve.getRedoublant());
		eb.setSexe(eleve.getSexe());
		eb.setTelephone(eleve.getTelephone());
		eb.setTelephoneMere(eleve.getTelephonemere());
		eb.setTelephonePere(eleve.getTelephonepere());
		eb.setTelephoneTuteur(eleve.getTelephonetuteur());
		eb.setDernieremoyenne(moy);
		return true;
	}
	
	
	public static boolean copieEleveToEleveBeanE(Eleve eleve,EleveBean eb,GestionInscriptionLocal gestionInscriptionLocal){
		float droitscolaire=gestionInscriptionLocal.recherchedroitsScolaires(eleve.getMatricule(), eleve.getAnnee().getAnneeacademique());
		eb.setIdeleve(eleve.getIdeleve());
		eb.setAdresse(eleve.getAdresse());
		eb.setAncien(eleve.getAncien());
		eb.setAncienEtablissement(eleve.getAnneeancienetablissement());
		eb.setAnneeAncienEtablissement(eleve.getAnneeancienetablissement());
		eb.setBoitePostale(eleve.getBoitepostale());
		eb.setClasseAncienEtablissement(eleve.getClasseancienetablissement());
		if(eleve.getClasse()!=null){
			eb.setCodeClasse(eleve.getClasse().getCodeclasse());
			eb.setLibelleClasse(eleve.getClasse().getLibelle());
		}
		
		eb.setConfirme(eleve.getConfirme());
		eb.setDateNaissance(eleve.getDatenaissance());
		eb.setDateEnregistrement(eleve.getDateEnregistrement());
		eb.setEmail(eleve.getEmail());
		eb.setEmailMere(eleve.getEmailmere());
		eb.setEmailPere(eleve.getEmailpere());
		eb.setEmailTuteur(eleve.getEmailtuteur());
		eb.setLieuNaissance(eleve.getLieunaissance());
		eb.setLogin(eleve.getLogin());
		eb.setMatricule(eleve.getMatricule());
		eb.setNationalite(eleve.getNationalite());
		eb.setNom(eleve.getNom());
		eb.setNomMere(eleve.getNommere());
		eb.setNomPere(eleve.getNompere());
		eb.setNomTuteur(eleve.getNomtuteur());
		eb.setNumeroPayement(eleve.getNumeroPayement());
		eb.setPassword(eleve.getPassword());
		eb.setPhoto(eleve.getPhoto());
		eb.setPrenom(eleve.getPrenom());
		eb.setProfessionMere(eleve.getProfessionmere());
		eb.setProfessionPere(eleve.getProfessionpere());
		eb.setProfessionTuteur(eleve.getProfessiontuteur());
		eb.setRedoublant(eleve.getRedoublant());
		eb.setSexe(eleve.getSexe());
		eb.setTelephone(eleve.getTelephone());
		eb.setTelephoneMere(eleve.getTelephonemere());
		eb.setTelephonePere(eleve.getTelephonepere());
		eb.setTelephoneTuteur(eleve.getTelephonetuteur());
		eb.setDroitscolaire(droitscolaire);
		return true;
	}
	
	
	public static boolean copieEleveToEleveBeanS(Eleve eleve,EleveBean eb,GestionBulletinNotesLocal gb, Integer portee){
		double moy=gb.moyenneEleve(eleve.getClasse().getCodeclasse(), eleve.getIdeleve(), portee, eleve.getClasse().getAnnee().getAnneeacademique());
		eb.setIdeleve(eleve.getIdeleve());
		eb.setAdresse(eleve.getAdresse());
		eb.setAncien(eleve.getAncien());
		eb.setAncienEtablissement(eleve.getAnneeancienetablissement());
		eb.setAnneeAncienEtablissement(eleve.getAnneeancienetablissement());
		eb.setBoitePostale(eleve.getBoitepostale());
		eb.setClasseAncienEtablissement(eleve.getClasseancienetablissement());
		if(eleve.getClasse()!=null){
			eb.setCodeClasse(eleve.getClasse().getCodeclasse());
			eb.setLibelleClasse(eleve.getClasse().getLibelle());
		}
		
		eb.setConfirme(eleve.getConfirme());
		eb.setDateNaissance(eleve.getDatenaissance());
		eb.setDateEnregistrement(eleve.getDateEnregistrement());
		eb.setEmail(eleve.getEmail());
		eb.setEmailMere(eleve.getEmailmere());
		eb.setEmailPere(eleve.getEmailpere());
		eb.setEmailTuteur(eleve.getEmailtuteur());
		eb.setLieuNaissance(eleve.getLieunaissance());
		eb.setLogin(eleve.getLogin());
		eb.setMatricule(eleve.getMatricule());
		eb.setNationalite(eleve.getNationalite());
		eb.setNom(eleve.getNom());
		eb.setNomMere(eleve.getNommere());
		eb.setNomPere(eleve.getNompere());
		eb.setNomTuteur(eleve.getNomtuteur());
		eb.setNumeroPayement(eleve.getNumeroPayement());
		eb.setPassword(eleve.getPassword());
		eb.setPhoto(eleve.getPhoto());
		eb.setPrenom(eleve.getPrenom());
		eb.setProfessionMere(eleve.getProfessionmere());
		eb.setProfessionPere(eleve.getProfessionpere());
		eb.setProfessionTuteur(eleve.getProfessiontuteur());
		eb.setRedoublant(eleve.getRedoublant());
		eb.setSexe(eleve.getSexe());
		eb.setTelephone(eleve.getTelephone());
		eb.setTelephoneMere(eleve.getTelephonemere());
		eb.setTelephonePere(eleve.getTelephonepere());
		eb.setTelephoneTuteur(eleve.getTelephonetuteur());
		eb.setDernieremoyenne(moy);
		return true;
	}
	
	public static boolean copieEleveToEleveBeanT(Eleve eleve,EleveBean eb,GestionBulletinNotesLocal gb, Integer portee){
		double moy = 0;
		if(portee==1){
			double moy1=gb.moyenneEleve(eleve.getClasse().getCodeclasse(), eleve.getIdeleve(), 1, eleve.getClasse().getAnnee().getAnneeacademique());
			double moy2=gb.moyenneEleve(eleve.getClasse().getCodeclasse(), eleve.getIdeleve(), 2, eleve.getClasse().getAnnee().getAnneeacademique());
			double moy3=gb.moyenneEleve(eleve.getClasse().getCodeclasse(), eleve.getIdeleve(), 3, eleve.getClasse().getAnnee().getAnneeacademique());
			moy=(moy1+moy2+moy3)/3;
		}
		if(portee==2){
			double moy1=gb.moyenneEleve(eleve.getClasse().getCodeclasse(), eleve.getIdeleve(), 4, eleve.getClasse().getAnnee().getAnneeacademique());
			double moy2=gb.moyenneEleve(eleve.getClasse().getCodeclasse(), eleve.getIdeleve(), 5, eleve.getClasse().getAnnee().getAnneeacademique());
			double moy3=gb.moyenneEleve(eleve.getClasse().getCodeclasse(), eleve.getIdeleve(), 6, eleve.getClasse().getAnnee().getAnneeacademique());
			moy=(moy1+moy2+moy3)/3;
		}
		if(portee==3){
		 moy=gb.moyenneEleveTrim(eleve.getClasse().getCodeclasse(),eleve.getIdeleve(),7,8,eleve.getClasse().getAnnee().getAnneeacademique());	
		}
		eb.setIdeleve(eleve.getIdeleve());
		eb.setAdresse(eleve.getAdresse());
		eb.setAncien(eleve.getAncien());
		eb.setAncienEtablissement(eleve.getAnneeancienetablissement());
		eb.setAnneeAncienEtablissement(eleve.getAnneeancienetablissement());
		eb.setBoitePostale(eleve.getBoitepostale());
		eb.setClasseAncienEtablissement(eleve.getClasseancienetablissement());
		if(eleve.getClasse()!=null){
			eb.setCodeClasse(eleve.getClasse().getCodeclasse());
			eb.setLibelleClasse(eleve.getClasse().getLibelle());
		}
		
		eb.setConfirme(eleve.getConfirme());
		eb.setDateNaissance(eleve.getDatenaissance());
		eb.setDateEnregistrement(eleve.getDateEnregistrement());
		eb.setEmail(eleve.getEmail());
		eb.setEmailMere(eleve.getEmailmere());
		eb.setEmailPere(eleve.getEmailpere());
		eb.setEmailTuteur(eleve.getEmailtuteur());
		eb.setLieuNaissance(eleve.getLieunaissance());
		eb.setLogin(eleve.getLogin());
		eb.setMatricule(eleve.getMatricule());
		eb.setNationalite(eleve.getNationalite());
		eb.setNom(eleve.getNom());
		eb.setNomMere(eleve.getNommere());
		eb.setNomPere(eleve.getNompere());
		eb.setNomTuteur(eleve.getNomtuteur());
		eb.setNumeroPayement(eleve.getNumeroPayement());
		eb.setPassword(eleve.getPassword());
		eb.setPhoto(eleve.getPhoto());
		eb.setPrenom(eleve.getPrenom());
		eb.setProfessionMere(eleve.getProfessionmere());
		eb.setProfessionPere(eleve.getProfessionpere());
		eb.setProfessionTuteur(eleve.getProfessiontuteur());
		eb.setRedoublant(eleve.getRedoublant());
		eb.setSexe(eleve.getSexe());
		eb.setTelephone(eleve.getTelephone());
		eb.setTelephoneMere(eleve.getTelephonemere());
		eb.setTelephonePere(eleve.getTelephonepere());
		eb.setTelephoneTuteur(eleve.getTelephonetuteur());
		eb.setDernieremoyenne(moy);
		return true;
	}


	public static void copieVersementToVersementBean(VersementBean vb, Versement v) throws ClassToBeanCopyException{
		try{
			vb.setDateVers(v.getDateVersement());
			vb.setId(v.getIdversement());
			vb.setMatricule(v.getEleve().getMatricule());
			vb.setMontant(v.getMontant());
			vb.setNom(v.getEleve().getNom());
			vb.setPrenom(v.getEleve().getPrenom());
			vb.setClasse(v.getEleve().getClasse().getCodeclasse());
			vb.setLibelleclasse(v.getEleve().getClasse().getLibelle());
		}
		catch(Exception e){
			throw new ClassToBeanCopyException("Tranche", "Versement");
		}
	}
	
	public static void copieParamTrancheToTrancheBean(ParametreTranche pt, TrancheBean tb){
		
		if(pt!=null){
			tb.setDelaiVersement(pt.getDelaiversement());
			tb.setId(pt.getId().getNumero());
			tb.setMontant(pt.getMontant());
			tb.setType(pt.getTypetranche());
			tb.setLoaded(true);
			tb.setCodeclasse(pt.getClasse().getCodeclasse());
		}
	}

	public static void copieAnneeToAnneeBean(Annee an, AnneeBean ab) {
		if(an==null) return;
		ab.setAnneeacademique(an.getAnneeacademique());
		ab.setClos(an.isClose());
		ab.setDatedebut(an.getDateDebut());
		ab.setDatefin(an.getDateFin());
		
	}

	public static void copieTrimestreTotrimestreBean(Trimestre tr,
			TrimestreBean tb) {
		if(tr==null) return;
		tb.setNumero(tr.getId().getNumerotrimestre());
		tb.setDatedebut(tr.getDatedebut());
		tb.setDatefin(tr.getDatefin());
		tb.setDateimpressionBull(tr.getDateimpressionbulletin());
	}

	public static void copieSequenceToSequenceBean(Sequence sq, SequenceBean sb) {
		if(sq==null) return;
		sb.setIdsequence(sq.getIdsequence());
		sb.setNumero(sq.getNumerosequence());
		sb.setDatedebut(sq.getDatedebut());
		sb.setDatefin(sq.getDatefin());
		sb.setNumero(sq.getNumerosequence());
		sb.setTrimestre(sq.getTrimestre().getId().getNumerotrimestre());
	}
	
	public static void copieSequenceToSequenceBeanN(Sequence sq, SequenceBean sb) {
		if(sq==null) return;
		System.out.println("je suis la pour comparer les dates");
		System.out.println("la date d'aujourd'hui est"+    new Date());
		if(sq.getDatefin().compareTo(new Date())>=0){
		sb.setIdsequence(sq.getIdsequence());
		sb.setNumero(sq.getNumerosequence());
		sb.setDatedebut(sq.getDatedebut());
		sb.setDatefin(sq.getDatefin());
		sb.setNumero(sq.getNumerosequence());
		sb.setTrimestre(sq.getTrimestre().getId().getNumerotrimestre());
		}
	}

	public static void copieEnseignantToEnseignantBean(Enseignant e,EnseignantBean eb) {
		if(e==null)
			return;
		if(e.getUtilisateur()!=null){
			if(e.getUtilisateur().getPersonnel()!=null){
				eb.setAdresse(e.getUtilisateur().getPersonnel().getAdresse());
				eb.setCivilite(e.getUtilisateur().getPersonnel().getCivilite());
				eb.setCodepersonnel(e.getUtilisateur().getPersonnel().getCodepersonnel());
				eb.setDatearrivee(e.getUtilisateur().getPersonnel().getDatearrivee());
				eb.setEmail(e.getUtilisateur().getPersonnel().getEmail());
				eb.setTelephone(e.getUtilisateur().getPersonnel().getTelephone());
			}
			
			eb.setLoginens(e.getUtilisateur().getLogin());
			eb.setPassens(e.getUtilisateur().getPassword());
		}
		
		
		
		eb.setCodeenseignant(e.getCodeenseignant());		
		eb.setCompetences(e.getComptences());			
		eb.setNomens(e.getNomens());		
		eb.setPhoto(e.getPhoto());
		eb.setPrenomens(e.getPrenomens());
		eb.setVacataire(e.isVacataire());
		if(eb.isVacataire()){
			eb.setSalairehoraire(e.getSalairehoraire());
			eb.setTravailmensuel(e.getTravailmensuel());
		}
		eb.setSexe(e.getSexe());
		
	}

	public static void copiePersonnelToPersonnelBean(Personnel p,
			PersonnelBean pb) {
		if(p==null) 
			return;
		pb.setAdresse(p.getAdresse());
		pb.setCivilite(p.getCivilite());
		pb.setCodepersonnel(p.getCodepersonnel());
		pb.setDatearrivee(p.getDatearrivee());
		pb.setDatedepart(p.getDatedepart());
		pb.setEmail(p.getEmail());
		pb.setFonction(p.getFonction());
		pb.setNom(p.getNom());
		pb.setPrenom(p.getPrenom());
		pb.setSexe(p.getSexe());
		pb.setTelephone(p.getTelephone());
	}

	public static void copieComposerToCompositionBean(Composer c,
			CompositionBean cb) {
		if(c==null) 
			return;
		cb.setEleve(new EleveBean());
		cb.setNote(c.getNote());
		cb.setAbsencejustifiee(c.isAbsencejustifiee());
		copieEleveToEleveBean(c.getEleve(), cb.getEleve());
	}
	
	public static void copieTypeSalleVersTypeSalleBean(
			TypeSalleBean tb, TypeSalle ts) {
		if(ts!=null){
			tb.setDescription(ts.getDescription());
			tb.setId(ts.getId());
			tb.setLibelle(ts.getLibelle());
		}
	}

	public static void copieMatiereVersMatiereBean(MatiereBean mb, Matiere mat) {
		if(mat!=null){
			if(mat.getAnimateurs()!=null&& !mat.getAnimateurs().isEmpty()){
				mb.setAnimateur(mat.getAnimateurs().get(0).getEnseignant().getNomens());
				mb.setCodeanimateur(mat.getAnimateurs().get(0).getEnseignant().getCodeenseignant());
			}
			
			mb.setCodematiere(mat.getCodematiere());
			mb.setDescription(mat.getDescription());
			mb.setLibelle(mat.getLibelle());
		}
	}
	
	
	/**
	 * Copie simple, sans animateur pedagogique d'une matiere vers une matiereBean
	 * @param mb matiereBean destination
	 * @param mat matiere a copier
	 */
	public static void copieSimpleMatiereVersMatiereBean(MatiereBean mb, Matiere mat) {
		if(mat!=null){
			mb.setCodematiere(mat.getCodematiere());
			mb.setDescription(mat.getDescription());
			mb.setLibelle(mat.getLibelle());
		
		}
	}

	public static void copieGroupeMatiereVersGroupeMatiereBean(
			GroupeMatiereBean gmb, Groupematiere gm) {
		if(gm!=null){					
			gmb.setDescription(gm.getDescription());
			gmb.setLibelle(gm.getLibellegm());
		}
	}

	public static void copieCourToCoursBean(CoursBean cb, Cour c) {
		cb.setCodeclasse(c.getClasse().getCodeclasse());
		cb.setCodecours(c.getCodecours());
		if(c.getEnseignant()!=null){
			cb.setCodeenseignant(c.getEnseignant().getCodeenseignant());
			if(c.getEnseignant().getPrenomens()!=null)
				cb.setLibelleenseignant(c.getEnseignant().getNomens()+" "+c.getEnseignant().getPrenomens());
			else
				cb.setLibelleenseignant(c.getEnseignant().getNomens());
		}
			
		cb.setCodematiere(c.getMatiere().getCodematiere());
		cb.setCoeficient(c.getCoeficient());
		cb.setDescription(c.getDescription());
		cb.setLibelle(c.getLibelleCours());
		cb.setLibelleclasse(c.getClasse().getLibelle());
		cb.setLibellegm(c.getGroupematiere().getLibellegm());
		cb.setLibellematiere(c.getMatiere().getLibelle());
		
	}
	
	public static void copieTypeEvaluationToTypeEvaluationBean(TypeEvaluationBean tb, TypeEvaluation te){
		if(te==null) return;
		tb.setCoefficient(te.getCoefficient());
		tb.setDescription(te.getDescription());
		tb.setTypeevaluation(te.getType());
	}
	
	public static void copieEvaluationToEvaluationBean(EvaluationBean eb, Evaluation e){
		if(e==null) return;
		eb.setCodematiere(e.getCour().getMatiere().getCodematiere());
		eb.setCodeclasse(e.getCour().getClasse().getCodeclasse());
		eb.setCodeevaluation(e.getCodeevaluation());
		eb.setCodesequence(e.getSequence().getIdsequence());
		eb.setCoefficient(e.getTypeEvaluation().getCoefficient());
		
		if(e.getProgrammations()!=null&& ! e.getProgrammations().isEmpty()){
			eb.setDatedebut(e.getProgrammations().get(0).getDatedebut());
			eb.setDatefin(e.getProgrammations().get(0).getDatefin());
			
		}
		eb.setLibelle(e.getLibelle());
		eb.setLibellecours(e.getCour().getLibelleCours());
		eb.setNumerosequence(e.getSequence().getNumerosequence());
		eb.setTypeevaluation(e.getTypeEvaluation().getType());
	}

	public static List<VersementBean> listVersementTolistVersementBean(List<Versement> listver) throws ClassToBeanCopyException {
		if(listver==null) return null;
		List<VersementBean> liste=new ArrayList<VersementBean>();
		for(int i=0;i<listver.size();i++){
			liste.add(new VersementBean());
			copieVersementToVersementBean(liste.get(i),listver.get(i));
		}
		return liste;
	}
	
	
	
	

	
	/**
	 * listPreinscriptionTolistdossierBean
	 * convertit une liste de Preinscription en une liste de DossierBean
	 * @param listpreins
	 * @return
	 */
	public static List<DossierBean> listPreinscriptionTolistdossierBean(List<PreInscription> listpreins){
		if(listpreins==null) return null;
		List<DossierBean> liste=new ArrayList<DossierBean>();
		for(int i=0;i<listpreins.size();i++){
			liste.add(new DossierBean());
			copiePreinscriptionToDossierBean(liste.get(i),listpreins.get(i));
			
		}
		return liste;
	}

	
	/**
	 * listClasseTolistClasseBean
	 * Convertit une liste de Classe en une liste de ClasseBean
	 * @param listclass Liste de Classe a convertir
	 * @param effectif 
	 * @return liste de ClasseBean correspondant
	 */ 
	public static List<ClasseBean> listClasseTolistClasseBean(List<Object[]> listclass, int effectif) {
		if(listclass==null) return null;
		List<ClasseBean> liste=new ArrayList<ClasseBean>();
		for(int i=0;i<listclass.size();i++){
			liste.add(new ClasseBean());
			copieClasseToClasseBean(liste.get(i),(Classe) listclass.get(i)[0],effectif);
				
		}
		return liste;
	}
	
	
	
	/**
	 * Transforme une liste de salle en liste de salleBean
	 * @param listsalle liste a transformer
	 * @return la liste de SalleBean correspondante
	 */
	public static List<SalleBean> listSalleTolistSalleBean(List<Salle> listsalle) {
		if(listsalle==null) return null;
		List<SalleBean> liste=new ArrayList<SalleBean>();
		for(int i=0;i<listsalle.size();i++){
			liste.add(new SalleBean());
			copieSalleToSalleBean(liste.get(i), listsalle.get(i));
		}
		return liste;
	}
	
	
	/**
	 * Transforme une liste de Niveau en liste de NiveauBean
	 * @param liste
	 * @return
	 */
	public static List<NiveauBean> listeNiveauTolisteNiveauBean(List<Niveau> listn){
		List<NiveauBean> listnb=new ArrayList<NiveauBean>();
		if(listn==null) return null;
		for(int i=0;i<listn.size();i++){
			listnb.add(new NiveauBean());
			copieNiveauVersNiveauBean(listnb.get(i), listn.get(i));
		}
		return listnb;
	}
	
	public static List<NiveauBean> listeNiveauTolisteNiveauBeanB(List<Niveau> listn){
		List<NiveauBean> listnb=new ArrayList<NiveauBean>();
		if(listn==null) return null;
		for(int i=0;i<listn.size();i++){
			listnb.add(new NiveauBean());
			copieNiveauVersNiveauBeanB(listnb.get(i), listn.get(i));
		}
		return listnb;
	}
		
	public static List<EleveBean> listEleveTolistEleveBean(List<Eleve> listeEleve){
		if(listeEleve==null) return null;
		List<EleveBean> liste=new ArrayList<EleveBean>();
		
		for(int i=0;i<listeEleve.size();i++){
			liste.add(new EleveBean());
			copieEleveToEleveBean(listeEleve.get(i),liste.get(i));
			
		}
		return liste;
	}
	
	public static List<EleveBean> listEleveTolistEleveBeanA(List<Eleve> listeEleve, GestionBulletinNotesLocal gb){
		if(listeEleve==null) return null;
		List<EleveBean> liste=new ArrayList<EleveBean>();
		
		for(int i=0;i<listeEleve.size();i++){
			liste.add(new EleveBean());
			copieEleveToEleveBeanA(listeEleve.get(i),liste.get(i),gb);
			
		}
		return liste;
	}
	
	public static List<EleveBean> listEleveTolistEleveBeanE(List<Eleve> listeEleve, GestionInscriptionLocal gestionInscriptionLocal){
		if(listeEleve==null) return null;
		List<EleveBean> liste=new ArrayList<EleveBean>();
		
		for(int i=0;i<listeEleve.size();i++){
			liste.add(new EleveBean());
			copieEleveToEleveBeanE(listeEleve.get(i),liste.get(i),gestionInscriptionLocal);
			
		}
		return liste;
	}
	
	public static List<EleveBean> listEleveTolistEleveBeanS(List<Eleve> listeEleve, GestionBulletinNotesLocal gb, Integer portee){
		if(listeEleve==null) return null;
		List<EleveBean> liste=new ArrayList<EleveBean>();
		
		for(int i=0;i<listeEleve.size();i++){
			liste.add(new EleveBean());
			copieEleveToEleveBeanS(listeEleve.get(i),liste.get(i),gb,portee);
			
		}
		return liste;
	}
	
	public static List<EleveBean> listEleveTolistEleveBeanT(List<Eleve> listeEleve, GestionBulletinNotesLocal gb, Integer portee){
		if(listeEleve==null) return null;
		List<EleveBean> liste=new ArrayList<EleveBean>();
		
		for(int i=0;i<listeEleve.size();i++){
			liste.add(new EleveBean());
			copieEleveToEleveBeanT(listeEleve.get(i),liste.get(i),gb,portee);
			
		}
		return liste;
	}
	
	public static List<TrancheBean> listParamTrancheTolistTrancheBean(List<ParametreTranche> listetr){
		if(listetr==null){
			return null;
		}
		List<TrancheBean> liste=new ArrayList<TrancheBean>();
		for(int i=0;i<listetr.size();i++){
			if(!listetr.get(i).getSupprime()){
				liste.add(new TrancheBean());
				copieParamTrancheToTrancheBean(listetr.get(i),liste.get(i));
			}			
		}
		return liste;
	}
	
	public static List<EnseignementBean> listeEnseignementVersListeEnseignementBean(List<Enseignement> listeens){
		List<EnseignementBean> listeeb=new ArrayList<EnseignementBean>();
		if(listeens!=null){
			for(int i=0;i<listeens.size();i++){
				listeeb.add(new EnseignementBean());
				copieEnseignementVersEnseignementBean(listeeb.get(i),listeens.get(i));
			}
		}
		return listeeb;
	}

	public static List<AnneeBean> listeAnneeVersListeAnneeBean(List<Annee> annees) {
		List<AnneeBean> listean=new ArrayList<AnneeBean>();
		if(annees!=null){
			for(int i=0;i<annees.size();i++){
				listean.add(new AnneeBean());
				copieAnneeToAnneeBean(annees.get(i),listean.get(i));
			}
		}
		return listean;
	}

	public static List<TrimestreBean> listeTrimestreToListeTrimestreBean(List<Trimestre> trims) {
		List<TrimestreBean> listetb=new ArrayList<TrimestreBean>();
		if(trims!=null){
			for(int i=0;i<trims.size();i++){
				listetb.add(new TrimestreBean());
				copieTrimestreTotrimestreBean(trims.get(i),listetb.get(i));
			}
		}
		return listetb;
	}

	public static List<SequenceBean> listeSequenceToListeSequenceBean(List<Sequence> seq) {
		List<SequenceBean> listesb=new ArrayList<SequenceBean>();
		if(seq!=null){
			for(int i=0;i<seq.size();i++){
				listesb.add(new SequenceBean());
				copieSequenceToSequenceBean(seq.get(i),listesb.get(i));
			}
		}
		return listesb;
	}
	
	
	public static List<SequenceBean> listeSequenceToListeSequenceBeanN(List<Sequence> seq) {
		List<SequenceBean> listesb=new ArrayList<SequenceBean>();
		if(seq!=null){
			for(int i=0;i<seq.size();i++){
				listesb.add(new SequenceBean());
				copieSequenceToSequenceBeanN(seq.get(i),listesb.get(i));
			}
		}
		return listesb;
	}

	public static List<EnseignantBean> listeEnseignantVersListeEnseignantBean(
			List<Enseignant> ens) {
		List<EnseignantBean> listeeb=new ArrayList<EnseignantBean>();
		if(ens!=null){
			for(int i=0;i<ens.size();i++){
				listeeb.add(new EnseignantBean());
				copieEnseignantToEnseignantBean(ens.get(i),listeeb.get(i));
			}
		}
		return listeeb;
	}

	public static List<PersonnelBean> listePersonnelVersListePersonnelBean(
			List<Personnel> pers) {
		List<PersonnelBean> listepb=new ArrayList<PersonnelBean>();
		if(pers!=null){
			for(int i=0;i<pers.size();i++){
				listepb.add(new PersonnelBean());
				copiePersonnelToPersonnelBean(pers.get(i),listepb.get(i));
			}
		}
		return listepb;
	}

	public static List<SectionBean> listeSectionVersListeSectionBean(
			List<Section> listes) {
		List<SectionBean> listesb=new ArrayList<SectionBean>();
		if(listesb!=null){
			for(int i=0;i<listes.size();i++){
				listesb.add(new SectionBean());
				copieSectionVersSectionBean(listesb.get(i),listes.get(i));
			}
		}
		return listesb;
	}

	public static List<CycleBean> listeCycleVerslisteCycleBean(List<Cycle> listc) {
		List<CycleBean> listecb=new ArrayList<CycleBean>();
		if(listc!=null){
			for(int i=0;i<listc.size();i++){
				listecb.add(new CycleBean());
				copieCycleVersCycleBean(listecb.get(i),listc.get(i));
			}
		}
		return listecb;
	}
	
	public static List<OptionBean> listeOptionVerslisteOptionBean(List<OptionSerie> listo) {
		List<OptionBean> listeob=new ArrayList<OptionBean>();
		if(listo!=null){
			for(int i=0;i<listo.size();i++){
				listeob.add(new OptionBean());
				copieOptionVersOptionBean(listeob.get(i),listo.get(i));
			}
		}
		return listeob;
	}

	public static List<EtablissementBean> listeEtablissementVersListeEtablissementBean(List<Etablissement> etabs) {
		List<EtablissementBean> listeeb=new ArrayList<EtablissementBean>();
		if(etabs!=null){
			for(int i=0;i<etabs.size();i++){
				listeeb.add(new EtablissementBean());
				copieEtablissementVersEtablissementBean(listeeb.get(i),etabs.get(i));
			}
		}
		return listeeb;
	}

	public static List<TypeSalleBean> listeTypeSalleVersTypeSalleBean(
			List<TypeSalle> ts) {
		List<TypeSalleBean> listesb=new ArrayList<TypeSalleBean>();
		if(ts!=null){
			for(int i=0;i<ts.size();i++){
				listesb.add(new TypeSalleBean());
				copieTypeSalleVersTypeSalleBean(listesb.get(i),ts.get(i));
			}
		}
		return listesb;
	}

	public static List<MatiereBean> listeMatiereVerslisteMatiereBean(
			List<Matiere> ms) {
		List<MatiereBean> listemb=new ArrayList<MatiereBean>();
		if(ms!=null){
			for(int i=0;i<ms.size();i++){
				listemb.add(new MatiereBean());
				copieMatiereVersMatiereBean(listemb.get(i),ms.get(i));
			}
		}
		return listemb;
	}
	
	/**
	 * Copie simple (sans animateur) d'une liste de matieres vers une liste de matierBean
	 * @param ms liste des matieres à copier
	 * @return la liste des matiereBean correspondante
	 */
	public static List<MatiereBean> listeSimpleMatiereVerslisteMatiereBean(
			List<Matiere> ms) {
		List<MatiereBean> listemb=new ArrayList<MatiereBean>();
		if(ms!=null){
			for(int i=0;i<ms.size();i++){
				listemb.add(new MatiereBean());
				copieSimpleMatiereVersMatiereBean(listemb.get(i),ms.get(i));
			}
		}
		return listemb;
	}

	public static List<GroupeMatiereBean> listeGroupeMatiereVerslisteGroupeMatiereBean(
			List<Groupematiere> gms) {
		List<GroupeMatiereBean> listemb=new ArrayList<GroupeMatiereBean>();
		if(gms!=null){
			for(int i=0;i<gms.size();i++){
				listemb.add(new GroupeMatiereBean());
				copieGroupeMatiereVersGroupeMatiereBean(listemb.get(i),gms.get(i));
			}
		}
		return listemb;
	}

	public static List<CoursBean> listCourVerslisteCoursBean(
			List<Cour> crs) {
		List<CoursBean> listecbs=new ArrayList<CoursBean>();
		if(crs!=null){
			for(int i=0;i<crs.size();i++){
				listecbs.add(new CoursBean());
				copieCourToCoursBean(listecbs.get(i),crs.get(i));
			}
		}
		return listecbs;
	}
	
	public static List<TypeEvaluationBean> listTypeEvaluationTolistTypeEvaluationBean(List<TypeEvaluation>  tes){
		List<TypeEvaluationBean> listetbs=new ArrayList<TypeEvaluationBean>();
		if(tes!=null){
			for(int i=0;i<tes.size();i++){
				listetbs.add(new TypeEvaluationBean());
				copieTypeEvaluationToTypeEvaluationBean(listetbs.get(i),tes.get(i));
			}
		}
		return listetbs;
	}
	
	public static List<EvaluationBean> listEvaluationTolistEvaluationBean(List<Evaluation>  evs){
		List<EvaluationBean> listeebs=new ArrayList<EvaluationBean>();
		if(evs!=null){
			for(int i=0;i<evs.size();i++){
				listeebs.add(new EvaluationBean());
				copieEvaluationToEvaluationBean(listeebs.get(i),evs.get(i));
			}
		}
		return listeebs;
	}
	
	public static List<CompositionBean> listComposerTolistCompositionBean(List<Composer>  cs){
		List<CompositionBean> listecbs=new ArrayList<CompositionBean>();
		if(cs!=null){
			for(int i=0;i<cs.size();i++){
				listecbs.add(new CompositionBean());
				copieComposerToCompositionBean(cs.get(i),listecbs.get(i));
			}
		}
		return listecbs;
	}
	
	public static List<CompositionBean> listEleveTolistCompositionBean(List<Eleve> eleves){
		List<CompositionBean> cbs=new ArrayList<CompositionBean>();
		for(int i=0;i<eleves.size();i++){
			cbs.add(new CompositionBean());
			cbs.get(i).setEleve(new EleveBean());
			copieEleveToEleveBean(eleves.get(i),cbs.get(i).getEleve());
		}
		return cbs;
	}
	
	public static List<TypeSanctionBean> listeTypeSanctionVersTypeSanctionBean(
			List<TypeSanction> ts) {
		List<TypeSanctionBean> listesb=new ArrayList<TypeSanctionBean>();
		if(ts!=null){
			for(int i=0;i<ts.size();i++){
				listesb.add(new TypeSanctionBean());
				copieTypeSanctionVersTypeSanctionBean(listesb.get(i),ts.get(i));
			}
		}
		return listesb;
	}

	public static void copieTypeSanctionVersTypeSanctionBean(
			TypeSanctionBean tsb, TypeSanction ts) {
		if(ts!=null){
			tsb.setDescription(ts.getDescription());
			tsb.setId(ts.getCodetype());
			tsb.setLibelle(ts.getLibelle());
		}
	}
	
	public static void copieSanctionVersSanctionBean(
			SanctionBean sb, Sanction s) {
		if(s!=null){
			sb.setAnnule(s.isAnnule());
			sb.setCodetype(s.getTypesanction().getCodetype());
			sb.setDatedecision(s.getDatedecision());
			sb.setDateeffet(s.getDateeffet().getTime());
			sb.setDuree(s.getDureesanction());
			sb.setIdsanction(s.getCodesanction());
			sb.setLibelletype(s.getTypesanction().getLibelle());
			sb.setMatriculeeleve(s.getEleve().getMatricule());
			sb.setMotif(s.getMotif());
			if(s.getEleve().getPrenom()!=null)
				sb.setNomeleve(s.getEleve().getNom()+" "+s.getEleve().getPrenom());
			else
				sb.setNomeleve(s.getEleve().getNom());
			sb.setUniteduree("heures");
		}
	}
	
	public static void copieConvocationVersConvocationBean(
			ConvocationBean cb, Convocation c) {
		if(c!=null){
			cb.setDatedelivrance(c.getDatedelivrance());
			cb.setDateeffet(c.getDaterendezvous());
			cb.setDescription(c.getDescription());
			cb.setIdconvocation(c.getIdconvocation());
			cb.setLibelle(c.getLibelle());
			cb.setMatriculeeleve(c.getEleve().getMatricule());
			if(c.getEleve().getPrenom()!=null)
				cb.setNomeleve(c.getEleve().getNom()+" "+c.getEleve().getPrenom());
			else
				cb.setNomeleve(c.getEleve().getNom());
		}
	}

	public static List<SanctionBean> listeSanctionToListeSanctionBean(
			List<Sanction> ss) {
		List<SanctionBean> listesb=new ArrayList<SanctionBean>();
		if(ss!=null){
			for(int i=0;i<ss.size();i++){
				listesb.add(new SanctionBean());
				copieSanctionVersSanctionBean(listesb.get(i),ss.get(i));
			}
		}
		return listesb;
	}
	
	public static List<ConvocationBean> listeConvocationToListeConvocationBean(
			List<Convocation> ss) {
		List<ConvocationBean> listesb=new ArrayList<ConvocationBean>();
		if(ss!=null){
			for(int i=0;i<ss.size();i++){
				listesb.add(new ConvocationBean());
				copieConvocationVersConvocationBean(listesb.get(i),ss.get(i));
			}
		}
		return listesb;
	}

	public static List<RetardBean> listRetardTolistRetardBean(
			List<Retard> rt) {
		List<RetardBean> listerb=new ArrayList<RetardBean>();
		if(rt!=null){
			for(int i=0;i<rt.size();i++){
				listerb.add(new RetardBean());
				copieRetardVersRetardBean(listerb.get(i),rt.get(i));
			}
		}
		return listerb;
	}

	public static void copieRetardVersRetardBean(RetardBean rb,
			Retard rt) {
		if(rt!=null){
			rb.setCoderetard(rt.getCoderetard());
			rb.setCodeclasse(rt.getEleve().getClasse().getCodeclasse());
			rb.setDateretard(rt.getDateretard());
			rb.setDuree(rt.getDureeretard());
			rb.setJustifie(rt.getJustifie());
			rb.setMatriculeeleve(rt.getEleve().getMatricule());
			if(rt.getEleve().getPrenom()!=null)
				rb.setNomeleve(rt.getEleve().getNom()+" "+rt.getEleve().getPrenom());
			else
				rb.setNomeleve(rt.getEleve().getNom());
			
			rb.setSupprime(rt.getSupprime());
		}
	}

	public static List<AbsenceBean> listAbsenceTolistAbsenceBean(
			List<Absence> as) {
		List<AbsenceBean> listeab=new ArrayList<AbsenceBean>();
		if(as!=null){
			for(int i=0;i<as.size();i++){
				listeab.add(new AbsenceBean());
				copieAbsenceVersAbsenceBean(listeab.get(i),as.get(i));
			}
		}
		return listeab;
	}
	
	public static void copieAbsenceVersAbsenceBean(AbsenceBean rb,
			Absence rt) {
		if(rt!=null){
			rb.setCodeabsence(rt.getCodeabsence());
			rb.setCodeclasse(rt.getEleve().getClasse().getCodeclasse());
			rb.setDateabsence(rt.getDateabsence());
			rb.setDuree(rt.getDuree());
			rb.setJustifie(rt.getJustifie());
			rb.setMatriculeeleve(rt.getEleve().getMatricule());
			
			if(rt.getEleve().getPrenom()!=null)
				rb.setNomeleve(rt.getEleve().getNom()+" "+rt.getEleve().getPrenom());
			else
				rb.setNomeleve(rt.getEleve().getNom());
			
			rb.setSupprime(rt.getSupprime());
		}
	}

	public static void copieItemRoleVersItemRoleBean(ItemRoleBean irb,
			ItemRole ir) {
		if(ir!=null){
			irb.setDescription(ir.getDescription());
			irb.setIditem(ir.getIditem());
			irb.setLibelle(ir.getLibelle());
		}
	}
	
	public static List<ItemRoleBean> listeItemroleToListeItemRoleBean(
			List<ItemRole> irs) {
		List<ItemRoleBean> listeirb=new ArrayList<ItemRoleBean>();
		if(irs!=null){
			for(int i=0;i<irs.size();i++){
				listeirb.add(new ItemRoleBean());
				copieItemRoleVersItemRoleBean(listeirb.get(i),irs.get(i));
			}
		}
		return listeirb;
	}

	public static List<GroupeUserBean> listeGroupeUserToListegroupeUserBean(
			List<GroupeUser> lgu) {
		List<GroupeUserBean> listegub=new ArrayList<GroupeUserBean>();
		if(listegub!=null){
			for(int i=0;i<lgu.size();i++){
				listegub.add(new GroupeUserBean());
				copieGroupeUserVersGroupeUserBean(listegub.get(i),lgu.get(i));
			}
		}
		return listegub;
	}

	public static void copieGroupeUserVersGroupeUserBean(
			GroupeUserBean gub, GroupeUser gu) {
		if(gu!=null){
			gub.setDescription(gu.getDescription());
			gub.setIdgroupe(gu.getIdgroupe());
			gub.setLibelle(gu.getLibelle());
			gub.setMontant(gu.getMontant());
		}
	}

	public static void copierPartieCoursToPartieCoursBean(
			PartieCoursBean pcb, PartieCour pc) {
		if(pc!=null){
			pcb.setCodepartie(pc.getCodepartie());
			pcb.setCodecours(pc.getCour().getCodecours());
			pcb.setDatedebut(pc.getPlanificationAnnuelle().getDatedebutplanification());
			pcb.setDatefin(pc.getPlanificationAnnuelle().getDatefinplanification());
			pcb.setDescription(pc.getDescription());
			pcb.setLibelle(pc.getLibelle());
		}
	}

	public static List<PartieCoursBean> listePartieCoursToListePartieCoursBean(
			List<PartieCour> pcs) {
		List<PartieCoursBean> listepcb=new ArrayList<PartieCoursBean>();
		if(pcs!=null){
			for(int i=0;i<pcs.size();i++){
				listepcb.add(new PartieCoursBean());
				copierPartieCoursToPartieCoursBean(listepcb.get(i),pcs.get(i));
			}
		}
		return listepcb;
	}

	

	public static List<CdtBean> listeCahierDtToCahierDtBean(
			List<CahierDeTexte> cdts) {
		List<CdtBean> listecdtb=new ArrayList<CdtBean>();
		if(cdts!=null){
			for(int i=0;i<cdts.size();i++){
				listecdtb.add(new CdtBean());
				copieCdtVersCdtBean(listecdtb.get(i),cdts.get(i));
			}
		}
		return listecdtb;
	}

	public static void copieCdtVersCdtBean(CdtBean cdtb,
			CahierDeTexte cdt) {
		if(cdt==null)
			return;
		
		cdtb.setCodecdt(cdt.getCodetexte());
		cdtb.setCodeclasse(cdt.getCours().getClasse().getCodeclasse());
		cdtb.setDatejour(cdt.getDatetravail());
		cdtb.setHeuredebut(cdt.getHeuredebut());
		cdtb.setHeurefin(cdt.getHeurefin());
		cdtb.setLibelle(cdt.getObjectifs());
		cdtb.setResume(cdt.getResume());
		cdtb.setCodecours(cdt.getCours().getCodecours());
		cdtb.setLibellecours(cdt.getCours().getLibelleCours());
	}

	public static List<CompteBean> listeCompteToListeCompteBean(
			List<Utilisateur> lcu) {
		List<CompteBean> listecu=new ArrayList<CompteBean>();
		if(lcu!=null){
			for(int i=0;i<lcu.size();i++){
				listecu.add(new CompteBean());
				copieUtilisateurVersCompteBean(listecu.get(i),lcu.get(i));
			}
		}
		return listecu;
	}

	public static void copieUtilisateurVersCompteBean(CompteBean cb,
			Utilisateur ut) {
		if(ut==null)
			return;
		
		
		cb.setLogin(ut.getLogin());
		if(ut.getPersonnel()!=null){
			cb.setCodepersonnel(ut.getPersonnel().getCodepersonnel());
			cb.setNom(ut.getPersonnel().getNom());
			cb.setPrenom(ut.getPersonnel().getPrenom());
		}
		
		cb.setPassword(ut.getPassword());
		cb.setIdcompte(ut.getIdutilisateur());
		cb.setActive(!ut.getSupprime());
	}



}
