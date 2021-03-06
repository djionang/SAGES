package ejb.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import utils.Repertoire;
import ages.exception.DroitsScolairesNonDefinis;
import ages.exception.ElementNOtFoundException;
import ages.exception.EleveDSCompletException;
import ages.exception.JPAException;
import ages.exception.MontantInscriptionInsuffisant;
import ages.exception.TotalVersementExcedantException;
import ejb.GestionEleveLocal;
import ejb.GestionInscriptionLocal;
import ejb.GestionBulletinNotesLocal;
import entities.Annee;
import entities.Certificats;
import entities.Classe;
import entities.CodeDossier;
import entities.CodeVersement;
import entities.Eleve;
import entities.Enseignant;
import entities.Etablissement;
import entities.Niveau;
import entities.ParametreTranche;
import entities.ParametreTranchePK;
import entities.PreInscription;
import entities.Utilisateur;
import entities.Versement;

/**
 * Classe GestionInscription: de gestion des inscriptions
 * Gestion des transactions manuelles
 * @author Brilland et berlin
 *
 */

@Stateless(mappedName = "GestionInscription")
@TransactionManagement(TransactionManagementType.BEAN)
public class GestionInscription implements GestionInscriptionLocal{
	
	@PersistenceContext(unitName="agespersist")
	EntityManager em;
	
	//bean Local de gestion des eleves
	@EJB
	private GestionEleveLocal gestioneleve;
	
	@EJB
	private GestionBulletinNotesLocal gestionbul;
	
	@Override
	public String createPreInscription(String adresse, String anneeacademique,
			String anneeancienetablissement, String codedossier,
			Date datenaissance, Date datepreinscription, String email,
			String emailmere, String emailpere, String etat,
			int auteur, String nationalite, String niveaudemande,
			String nom, String nomancienetablissement, String nommere,
			String nompere, String numeropayement, String optiondemande,
			String passwordaccess, String prenom, String professionmere,
			String professionpere, String sexe, String telephone,
			String telephonemere, String telephonepere,
			String classeancienetablissement, String nomtuteur,
			String professiontuteur, String emailtuteur, String telephonetuteur,String lieuNaissance,String boitePostale,double dernieremoyenne,String matricule,float sommeverse) throws ElementNOtFoundException {
		
		String result=null;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		
		try{
			em2.getTransaction().begin();
			Annee an=em2.getReference(Annee.class, anneeacademique);
			if(an==null) throw new ElementNOtFoundException(anneeacademique, "Annee");
			
			Utilisateur uti=em2.getReference(Utilisateur.class,auteur);
			if(uti==null) throw new ElementNOtFoundException(String.valueOf(auteur), "utilisateur");
			
			Query query=em2.createNamedQuery("Niveau.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("codeniveau", niveaudemande);
			Niveau nivo=(Niveau) query.getSingleResult();
			
			if(nivo==null) 
				throw new ElementNOtFoundException(niveaudemande, "Niveau");
			
			CodeDossier cd=new CodeDossier();
			em2.persist(cd);
			
			String codeDossier=Repertoire.genererCodeDossier(cd.getCode());
			
			PreInscription pr=new PreInscription();
			
			pr.setCodedossier(codeDossier);
			pr.setAdresse(adresse);
			pr.setUtilisateur(uti);
			pr.setAnnee(an);
			pr.setAnneeancienetablissement(anneeancienetablissement);
			pr.setClasseancienetablissement(classeancienetablissement);
			pr.setDatenaissance(datenaissance);
			pr.setDatepreinscription(datepreinscription);
			pr.setEmail(email);
			pr.setEmailmere(emailmere);
			pr.setEmailpere(emailpere);
			pr.setEmailtuteur(emailtuteur);
			pr.setEtat(etat);
			pr.setLieunaissance(lieuNaissance);
			pr.setNationalite(nationalite);
			pr.setNiveaudemande(nivo);
			pr.setNom(nom);
			pr.setNomancienetablissement(nomancienetablissement);
			pr.setNommere(nommere);
			pr.setNompere(nompere);
			pr.setNomtuteur(nomtuteur);
			pr.setNumeropayement(numeropayement);
			pr.setOptiondemande(optiondemande);
			pr.setPasswordaccess(passwordaccess);
			pr.setPrenom(prenom);
			pr.setProfessionmere(professionmere);
			pr.setProfessionpere(professionpere);
			pr.setProfessiontuteur(professiontuteur);
			pr.setSexe(sexe);
			pr.setSupprime(false);
			pr.setTelephone(telephone);
			pr.setTelephonemere(telephonemere);
			pr.setTelephonepere(telephonepere);
			pr.setTelephonetuteur(telephonetuteur);
			pr.setBoitepostale(boitePostale);
			pr.setDernieremoyenne(dernieremoyenne);
			pr.setMatricule(matricule);
			
			em2.persist(pr);
			em2.getTransaction().commit();
			
			result=codeDossier;			
		
		}
		finally{
			em2.close();
			emf.close();
		}
		
		
		return result;
	}

	@Override
	public void enregistrerParametreTranche(String type, String classe,
			String annee, float montant, Date dateDelai) throws ElementNOtFoundException{
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		
		EntityManager emtrans=emfact.createEntityManager();
		
		try{		
		
			emtrans.getTransaction().begin();
			Classe c=emtrans.find(Classe.class, classe);
			if(c==null) throw new ElementNOtFoundException(classe, "Classe");
			
			Annee an=emtrans.find(Annee.class,annee);
			if(an==null) throw new ElementNOtFoundException(annee, "Annee");
			
			
			ParametreTranchePK id=new ParametreTranchePK();
			id.setNumero(nextParametreTrancheId(annee, classe));
			
			ParametreTranche paratra = new ParametreTranche();
			
			paratra.setSupprime(false);
			paratra.setId(id);
			paratra.setAnnee(an);
			paratra.setClasse(c);
			paratra.setMontant(montant);
			paratra.setTypetranche(type);
			paratra.setDelaiversement(dateDelai);
			
			emtrans.persist(paratra);
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emfact.close();
		}
	}


	@Override
	public boolean modifierInscription(String adresse, 
			String anneeancienetablissement, String codedossier,
			Date datenaissance, Date datepreinscription, String email,
			String emailmere, String emailpere,
			String nationalite, String niveaudemande,
			String nom, String nomancienetablissement, String nommere,
			String nompere, String numeropayement, String optiondemande,
			String passwordaccess, String prenom, String professionmere,
			String professionpere, String sexe, String telephone,
			String telephonemere, String telephonepere,
			String classeancienetablissement, String nomtuteur,
			String professiontuteur, String emailtuteur, String telephonetuteur,String lieuNaissance,String boitePostale,double dernieremoyenne,String matricule,float sommeverse) throws ElementNOtFoundException {

		boolean result=false;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		
		try{
					
			em2.getTransaction().begin();
			
			PreInscription pr=em2.getReference(PreInscription.class,codedossier);
			if(pr==null) throw new ElementNOtFoundException(codedossier, "Preinscription");
			
			Query query=em2.createNamedQuery("Niveau.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("codeniveau", niveaudemande);
			Niveau nivo=(Niveau) query.getSingleResult();
			
			if(nivo==null) 
				throw new ElementNOtFoundException(niveaudemande, "Niveau");
			
			
			pr.setAdresse(adresse);

			pr.setAnneeancienetablissement(anneeancienetablissement);
			pr.setClasseancienetablissement(classeancienetablissement);
			pr.setDatenaissance(datenaissance);
			pr.setDatepreinscription(datepreinscription);
			pr.setEmail(email);
			pr.setEmailmere(emailmere);
			pr.setEmailpere(emailpere);
			pr.setEmailtuteur(emailtuteur);
			pr.setLieunaissance(lieuNaissance);
			pr.setNationalite(nationalite);
			pr.setNiveaudemande(nivo);
			pr.setNom(nom);
			pr.setNomancienetablissement(nomancienetablissement);
			pr.setNommere(nommere);
			pr.setNompere(nompere);
			pr.setNomtuteur(nomtuteur);
			pr.setNumeropayement(numeropayement);
			pr.setOptiondemande(optiondemande);
			pr.setPasswordaccess(passwordaccess);
			pr.setPrenom(prenom);
			pr.setProfessionmere(professionmere);
			pr.setProfessionpere(professionpere);
			pr.setProfessiontuteur(professiontuteur);
			pr.setSexe(sexe);
			pr.setTelephone(telephone);
			pr.setTelephonemere(telephonemere);
			pr.setTelephonepere(telephonepere);
			pr.setTelephonetuteur(telephonetuteur);			
			pr.setBoitepostale(boitePostale);
			pr.setDernieremoyenne(dernieremoyenne);
			pr.setMatricule(matricule);
			
			em2.merge(pr);
			em2.getTransaction().commit();
			result=true;
			
		
		}finally{
			em2.close();
			emf.close();
		}
		
		return result;
	}

	@Override
	public void modifierParametreTranche(String type, String classe,
			String annee, float montant, Date dateDelai,int num) throws ElementNOtFoundException {
		// TODO Auto-generated method stub
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();
		try{		
			
			emtrans.getTransaction().begin();
			
			ParametreTranchePK id=new ParametreTranchePK();
			id.setNumero(num);
			id.setCodeclasse(classe);
			id.setAnneeacademique(annee);
			
			ParametreTranche pt=emtrans.find(ParametreTranche.class, id);
			if(pt==null){
				
				throw new ElementNOtFoundException(num+"--"+classe+"--"+annee, "ParametreTranche");
			}
			
			Classe c=emtrans.find(Classe.class, classe);
			if(c==null) throw new ElementNOtFoundException(classe, "Classe");
			
			pt.setClasse(c);
			pt.setMontant(montant);
			pt.setTypetranche(type);
			pt.setDelaiversement(dateDelai);
			
			emtrans.merge(pt);
			emtrans.getTransaction().commit();
		}	
		finally{
			emtrans.close();
			emfact.close();
		}
				
				
		  
			
	}

	@Override
	public boolean supprimerPreinscription(String codedossier) {
		// TODO Auto-generated method stub
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		boolean res=true;
		
		try{
			em2.getTransaction().begin();
			PreInscription pre=em2.getReference(PreInscription.class, codedossier);
			if(pre==null) throw (new RuntimeException("Essai de suppression d'une pr�inscription non encore enregistr�e "));
			
			pre.setSupprime(true);
			em2.merge(pre);
			em2.getTransaction().commit();
		}catch(Exception e){
			Repertoire.logFatal(" Fatal -- Suppression d'un dossier de preinscription", this.getClass(), e);
			if(em2.getTransaction().isActive())
				em2.getTransaction().rollback();
			res=false;
		}
			
		finally{
			emf.close();
		}
		return res;
	}

	@Override
	public PreInscription recherchePreinscription(String codedossier,String annee) {
		PreInscription pr=null;
		try{
			Query req=em.createNamedQuery("Preinscription.find");
			req.setParameter("annee", annee);
			req.setParameter("supprime", false);
			req.setParameter("codedossier", codedossier);
			
		    pr=(PreInscription) req.getSingleResult();
		}
		catch(Exception e){
			Repertoire.logWarn("Dossier non trouv� ou ++ trouv�s "+codedossier, this.getClass());
			e.printStackTrace();
		}
		return pr;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PreInscription> PreinscriptionAccepte(String annee) {
		List<PreInscription> list=null ;
		try{
			Query req=em.createNamedQuery("Preinscription.findByState");
			req.setParameter("annee", annee);
			req.setParameter("supprime", false);
			req.setParameter("etat", "accepte");
		    list=req.getResultList();
		}
		catch(Exception e){
			Repertoire.logWarn("Erreur lors du listing des preinscriptions", this.getClass());
		}
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PreInscription> PreinscriptionRejette(String annee) {
		List<PreInscription> list=null ;
		try{
			Query req=em.createNamedQuery("Preinscription.findByState");
			req.setParameter("annee", annee);
			req.setParameter("supprime", false);
			req.setParameter("etat", "rejette");	
		    list=req.getResultList();
		}
		catch(Exception e){
			Repertoire.logWarn("Erreur lors du listing des preinscriptions", this.getClass());
		}
		
		return list;
	}


	@Override
	public boolean validerPreinscription(String codedossier, String classe,
			String login, String anneac) {
		// TODO Auto-generated method stub
		if(classe!=null)
			validerPreinsAvecClasse(codedossier, classe, login, anneac);
		else
			validerPreinsSansClasse(codedossier);
		return true;
	}

	@Override
	public boolean invaliderPreinscription(String codedossier) throws ElementNOtFoundException {
		boolean result=false;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		
		try{
					
			em2.getTransaction().begin();
			
			PreInscription pr=em2.find(PreInscription.class,codedossier);
			if(pr==null) throw new ElementNOtFoundException(codedossier, "Preinscription");
			
			pr.setEtat("rejette");
			
			em2.merge(pr);
			em2.getTransaction().commit();
			result=true;
			
		
		}finally{
			em2.close();
			emf.close();
		}
		
		return result;
	}
//	
//	private boolean existPreinscription(String codedossier,String annee) {
//		// TODO Auto-generated method stub
//		String pr=null;
//		try{
//			Query req=em.createNamedQuery("Preinscription.findExist");
//			req.setParameter("annee", annee);
//			req.setParameter("supprime", false);
//			req.setParameter("codedossier", codedossier);
//			
//		    pr=(String) req.getSingleResult();
//		}
//		catch(Exception e){
//			Repertoire.logWarn("Dossier Soft non trouv� ou ++ trouv�s "+codedossier, this.getClass());
//			
//			return false;
//		}
//		if(pr==null) return false;
//		return true;
//	}

	/**
	 * Transferer ce gars dans la liste des eleves simplement
	 * @param codedossier
	 * @param codeclasse
	 * @return
	 */
	private boolean validerPreinsAvecClasse(String codedossier,String codeclasse,String login,String anneeac){
		boolean result=false;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		String matricule;
		try{
					
			em2.getTransaction().begin();
			
			PreInscription pr=em2.find(PreInscription.class,codedossier);
			if(pr==null) throw (new RuntimeException("Essai de modification d'une pr�inscription non encore enregistr�e "));
			
			Annee an=em2.find(Annee.class,anneeac);
			if(an==null) throw (new RuntimeException("Essai d'enregitrement de classe pour un dossier en une ann�e ind�finie "));
			
			Classe clas=em2.find(Classe.class, codeclasse);
			if(clas==null)
				throw (new RuntimeException("Classe d'enregistrement invalide "));
						
			Eleve el=new Eleve();
			
			if(pr.getMatricule()==null)
			matricule=Repertoire.genererCodeEleve(pr.getNiveaudemande().getIdniveau(), el.getIdeleve());
		
			matricule=pr.getMatricule();
			el.setMatricule(matricule);
			el.setAdresse(pr.getAdresse());
			el.setAncien(false);
			el.setAnnee(an);
			el.setAnneeancienetablissement(pr.getAnneeancienetablissement());
			el.setBoitepostale(pr.getBoitepostale());
			el.setClasse(clas);
			
			el.setClasseancienetablissement(pr.getClasseancienetablissement());
			el.setConfirme(false);
			el.setDatenaissance(pr.getDatenaissance());
			el.setDateEnregistrement(pr.getDatepreinscription());
			el.setEmail(pr.getEmail());
			el.setEmailmere(pr.getEmailmere());
			el.setEmailpere(pr.getEmailpere());
			el.setEmailtuteur(pr.getEmailtuteur());
			el.setLieunaissance(pr.getLieunaissance());
						
			el.setLogin(pr.getNom());
			el.setNationalite(pr.getNationalite());
			el.setNom(pr.getNom());
			el.setNomancienetablissment(pr.getNomancienetablissement());
			el.setNommere(pr.getNommere());
			el.setNompere(pr.getNompere());
			el.setNomtuteur(pr.getNomtuteur());
			el.setNumeroPayement(pr.getNumeropayement());
			el.setPassword(pr.getPasswordaccess());
			el.setPrenom(pr.getPrenom());
			el.setProfessionmere(pr.getProfessionmere());
			el.setProfessionpere(pr.getProfessionpere());
			el.setProfessiontuteur(pr.getProfessiontuteur());
			el.setSexe(pr.getSexe());
			el.setSupprime(false);
			el.setTelephone(pr.getTelephone());
			el.setTelephonemere(pr.getTelephonemere());
			el.setTelephonepere(pr.getTelephonepere());
			el.setTelephonetuteur(pr.getTelephonetuteur());
			el.setDernieremoyenne(pr.getDernieremoyenne());
			em2.remove(pr);
			em2.persist(el);
			
			em2.getTransaction().commit();
			result=true;
			
		
		}finally{
			em2.close();
			emf.close();
		}
		
		return result;
	}
	
	/**
	 * Validation d'une preinscription sans affectation de classe
	 * @param codedossier code du dossier a valider
	 * @return
	 */
	private boolean validerPreinsSansClasse(String codedossier){
		boolean result=false;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		
		try{
					
			em2.getTransaction().begin();
			
			PreInscription pr=em2.find(PreInscription.class,codedossier);
			if(pr==null) throw (new RuntimeException("Essai de modification d'une pr�inscription non encore enregistr�e "));
			
			pr.setEtat("accepte");
			
			em2.merge(pr);
			em2.getTransaction().commit();
			result=true;
			
		}
		catch(Exception e){
			Repertoire.logFatal(" Fatal -- Validation de pr�inscription", this.getClass(), e);
			if(em2.getTransaction().isActive())
				em2.getTransaction().rollback();
		}finally{
			em2.close();
			emf.close();
		}
		
		return result;
	}

	/**
	 * Renvoie le prochain nulmero de tranche a enregistrer pour une classe
	 * @param annee ann�e acad�mique
	 * @param classe classe concern�e
	 * @return
	 */
	private int nextParametreTrancheId(String annee, String classe){
		Query req= em.createNamedQuery("ParametreTranche.maxInsertedId");
		req.setParameter("annee", annee);
		req.setParameter("classe", classe);
		req.setParameter("supprime", false);
		
		int valeur;
		try{
			valeur=(Integer)req.getSingleResult()+1;
		}
		catch(java.lang.NullPointerException e){
			// Aucune tranche p�enregistr�e, initialisation a 0
			valeur=1;
		}
		return valeur;
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PreInscription> listerPreInscription(String annee) {
		// TODO Auto-generated method stub
		List<PreInscription> list=null ;
		try{
			Query req=em.createNamedQuery("Preinscription.findAll");
			req.setParameter("annee", annee);
			req.setParameter("supprime", false);
			
		    list=req.getResultList();
		}
		catch(Exception e){
			Repertoire.logWarn("Erreur lors du listing des preinscriptions", this.getClass());
		}
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParametreTranche> listeTranchesClasse(String codeclasse,
			String annee) {
		// TODO Auto-generated method stub
		List<ParametreTranche> list=null ;
		try{
			Query req=em.createNamedQuery("ParamTranche.findforClasse");
			req.setParameter("annee", annee);
			req.setParameter("supprime", false);
			req.setParameter("classe",codeclasse);	
		    list=req.getResultList();
		}
		catch(Exception e){
			Repertoire.logWarn("Erreur lors du listing des parametres", this.getClass());
		}
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Versement> listerVersementsEleve(String matricule, String annee) {
		// TODO Auto-generated method stub
		List<Versement> list=null;
		try{
		Query req= em.createNamedQuery("Versement.findforEleve");
		req.setParameter("annee", annee);
		req.setParameter("matricule", matricule);
		req.setParameter("supprime", false);
		list = req.getResultList();
	    }
	   catch(Exception e){
		   Repertoire.logWarn("Erreur lors du listing des tranches d'un eleve", this.getClass());
	    }
		return list;
				
	}

	@Override
	public List<String> affecterClasse(List<String> listedossiers, String codeclasse,String annee) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		
		List<String> dossiersSaved=new ArrayList<String>();
		String dossierCourant=null;
		String matricule=null;
		for(int i=0;i<listedossiers.size();i++){
			dossierCourant=listedossiers.get(i);
			try{
				
				em2.getTransaction().begin();
				
				PreInscription pr=em2.find(PreInscription.class,dossierCourant);
				if(pr==null) throw (new RuntimeException("Essai d'attribution de classe � une pr�inscription non encore enregistr�e "));
				

				
				
				Eleve el=new Eleve();
				em2.persist(el);
				if(pr.getMatricule()==null)
				 matricule=Repertoire.genererCodeEleve(pr.getNiveaudemande().getIdniveau(), el.getIdeleve());
				matricule=pr.getMatricule();
				el.setMatricule(matricule);
				el.setAdresse(pr.getAdresse());
				el.setAncien(false);
				el.setAnneeancienetablissement(pr.getAnneeancienetablissement());
				el.setBoitepostale(pr.getBoitepostale());
				
				Classe clas=em2.find(Classe.class, codeclasse);
				if(clas==null)
					throw (new RuntimeException("Classe d'enregistrement invalide "));
				el.setClasse(clas);
				
				el.setClasseancienetablissement(pr.getClasseancienetablissement());
				el.setConfirme(false);
				el.setDatenaissance(pr.getDatenaissance());
				el.setDateEnregistrement(pr.getDatepreinscription());
				el.setEmail(pr.getEmail());
				el.setEmailmere(pr.getEmailmere());
				el.setEmailpere(pr.getEmailpere());
				el.setEmailtuteur(pr.getEmailtuteur());
				el.setLieunaissance(pr.getLieunaissance());
				
				Annee an=em.find(Annee.class, annee);
				if(an==null)
					throw (new RuntimeException("Ann�e acad�mique d'enregistrement invalide "));
				el.setAnnee(an);
				
				el.setLogin(pr.getNom());
				el.setNationalite(pr.getNationalite());
				el.setNom(pr.getNom());
				el.setNomancienetablissment(pr.getNomancienetablissement());
				el.setNommere(pr.getNommere());
				el.setNompere(pr.getNompere());
				el.setNomtuteur(pr.getNomtuteur());
				el.setNumeroPayement(pr.getNumeropayement());
				el.setPassword(pr.getPasswordaccess());
				el.setPrenom(pr.getPrenom());
				el.setProfessionmere(pr.getProfessionmere());
				el.setProfessionpere(pr.getProfessionpere());
				el.setProfessiontuteur(pr.getProfessiontuteur());
				el.setSexe(pr.getSexe());
				el.setSupprime(false);
				el.setTelephone(pr.getTelephone());
				el.setTelephonemere(pr.getTelephonemere());
				el.setTelephonepere(pr.getTelephonepere());
				el.setTelephonetuteur(pr.getTelephonetuteur());
				el.setDernieremoyenne(pr.getDernieremoyenne());
				em2.remove(pr);
				em2.persist(el);
				
				em2.getTransaction().commit();
				dossiersSaved.add(dossierCourant);
				
			}
			catch(Exception e){
				Repertoire.logFatal(" Fatal -- Validation de pr�inscription", this.getClass(), e);
				if(em2.getTransaction().isActive())
					em2.getTransaction().rollback();
			}finally{
				em2.close();
				emf.close();
			}
		}
		return dossiersSaved;
		
	}

	@Override
	public void supprimerParametreTranche(int num, String codeclasse, String annee) throws ElementNOtFoundException {
		// TODO Auto-generated method stub
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			
			ParametreTranchePK id=new ParametreTranchePK();
			id.setNumero(num);
			id.setCodeclasse(codeclasse);
			id.setAnneeacademique(annee);
			
			ParametreTranche pt=emtrans.find(ParametreTranche.class, id);
			if(pt==null){
				
				throw new ElementNOtFoundException(num+"--"+codeclasse+"--"+annee, "ParametreTranche");
			}
			
			
			pt.setSupprime(true);
			emtrans.merge(pt);
			emtrans.getTransaction().commit();
		}
		
		finally{
			emtrans.close();
			emfact.close();
		}
		
	}

	@SuppressWarnings("null")
	@Override
	public List<Eleve> listerEleveRegulierClasse(String codeclasse, String annee) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
         List<Eleve> result= null;
		try{
			em2.getTransaction().begin();
			Annee an=em2.getReference(Annee.class,annee);
			if(an==null) throw (new RuntimeException("Ann�e acad�mique non trouv�e "+codeclasse));
			
			@SuppressWarnings("unchecked")
			List<Eleve> ele =  (List<Eleve>) em2.createQuery("select e from Eleve as e where e.classe.codeclasse=codeclasse and e.annee.annee=an");
			if(ele==null) throw(new RuntimeException("il y a pas d'eleve dans cette classe"+codeclasse));
			Iterator<Eleve> it = ele.iterator();
			
			while(it.hasNext()){
				
				Eleve elev = it.next();
				Date datemax= (Date) em2.createQuery("select max(dateversement) from Eleve where Eleve.matricule=elev.matricule");
				if(datemax.after(new Date())){
				result.add(elev);
				}
				
			}
		}catch(Exception e){
			Repertoire.logWarn("Impossible d'enregistrer les parametres de tranches", this.getClass());	
			 em.getTransaction().rollback();
	         }
		finally{
			emf.close();
		}
		return result;
	}


	@Override
	public boolean attribuerEnseignantPrincipal(String codeenseignant,
			String codeclasse, String annee) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		
		try{
			em2.getTransaction().begin();
			Annee an=em2.getReference(Annee.class,annee);
			if(an==null) throw (new RuntimeException("Ann�e acad�mique non trouv�e "+codeclasse));
			
			Enseignant ens = em2.getReference(Enseignant.class, codeenseignant);
			if(ens==null) throw(new RuntimeException("Aucun  enseignant trouv�"+codeenseignant));
			
			Classe cla = em2.getReference(Classe.class, codeclasse);
			if(cla==null) throw(new RuntimeException("classe non trouv�e"+codeclasse));
			
		    em2.merge(cla);
		    em2.getTransaction().commit();
		
		}catch(Exception e){
			Repertoire.logWarn("Impossible d'enregistrer les parametres de tranches", this.getClass());	
			 em.getTransaction().rollback();
		     return false;
	         }
		finally{
			emf.close();
		}
		return true;
	}

	@SuppressWarnings("null")
	@Override
	public List<Eleve> listerEleveNonRegulierClasse(String codeclasse,
			String annee) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
         List<Eleve> result= null;
		try{
			em2.getTransaction().begin();
			Annee an=em2.getReference(Annee.class,annee);
			if(an==null) throw (new RuntimeException("Ann�e acad�mique non trouv�e "+codeclasse));
			
			@SuppressWarnings("unchecked")
			List<Eleve> ele =  (List<Eleve>) em2.createQuery("select e from Eleve as e where e.classe.codeclasse=codeclasse and e.annee.annee=an");
			if(ele==null) throw(new RuntimeException("il y a pas d'eleve dans cette classe"+codeclasse));
			Iterator<Eleve> it = ele.iterator();
			
			while(it.hasNext()){
				
				Eleve elev = it.next();
				Date datemax= (Date) em2.createQuery("select max(dateversement) from Eleve where Eleve.matricule=elev.matricule");
				if(datemax.before(new Date())){
				result.add(elev);
				}
				
			}
		}catch(Exception e){
			Repertoire.logWarn("Impossible d'enregistrer les parametres de tranches", this.getClass());	
			 em.getTransaction().rollback();
	         }
		finally{
			emf.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Versement> listerVersementsEnregistres(Date datedebut, Date datefin, String annee) throws JPAException {
		
		Query query;
		
		if(datedebut==null){
			// si la date de debut de periode nulle
			
			if(datefin==null){
				//date de fin est nulle
				
				try{
					query=em.createQuery("select v from Versement as v where v.annee.anneeacademique = :annee and v.supprime=:supprime");
					query.setParameter("annee", annee);
					query.setParameter("supprime", false);
				}
				catch(Exception e){
					throw new JPAException("Lister Versements enregistr�s",e);
				}
			}
			else{
				// date debut nulle, mais date fin valide
				//recherche de tous les versements jusqu'� date fin
				try{
					query=em.createQuery("select v from Versement as v where v.annee.anneeacademique = :annee and v.supprime=:supprime and v.dateVersement <= :datefin");
					query.setParameter("annee", annee);
					query.setParameter("supprime", false);
					query.setParameter("datefin", datefin,TemporalType.DATE);
				}
				catch(Exception e){
					throw new JPAException("Lister Versements enregistr�s",e);
				}
			}
		}
		else{
			if(datefin==null){
				// date debut non nulle
				//recherche de tous les versements � partir de date debut
				try{
					query=em.createQuery("select v from Versement as v where v.annee.anneeacademique = :annee and v.supprime=:supprime and v.dateVersement >= :datedebut");
					query.setParameter("annee", annee);
					query.setParameter("supprime", false);
					query.setParameter("datedebut", datedebut,TemporalType.DATE);
				}
				catch(Exception e){
					throw new JPAException("Lister Versements enregistr�s",e);
				}
			}
			else{
				//datedebut et datefin non nulles
				//recherche entre date debut et date fin
				try{
					query=em.createQuery("select v from Versement as v where v.annee.anneeacademique = :annee and v.supprime=:supprime and v.dateVersement between :datedebut and :datefin");
					query.setParameter("annee", annee);
					query.setParameter("supprime", false);
					query.setParameter("datedebut", datedebut,TemporalType.DATE);
					query.setParameter("datefin", datefin,TemporalType.DATE);
				}
				catch(Exception e){
					throw new JPAException("Lister Versements enregistr�s",e);
				}
			}
		}
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> listeTypesTranches() {
		// TODO Auto-generated method stub
		Query query=em.createNamedQuery("ParametreTranche.listTypes");
		return query.getResultList();
		
	}

	@Override
	public boolean confirmeEleveOuiNon(String matriculeEleve, String annee) throws ElementNOtFoundException {
		Eleve eleve=gestioneleve.rechercheEleve(matriculeEleve, annee);
		if(eleve==null) throw new ElementNOtFoundException(matriculeEleve, "Eleve");
		return eleve.getConfirme();
	}
	
	

	public String enregistrerDroitScolaire(float montant,String matricule, String typeversement, Date dateVersement,String annee,String login) throws ElementNOtFoundException, MontantInscriptionInsuffisant, EleveDSCompletException, JPAException, DroitsScolairesNonDefinis, TotalVersementExcedantException{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		Versement vers;
		String codeclasse;
		List<ParametreTranche> tranches;
		Annee an;
		Eleve eleve;
		float total=0;
		
		
		try{

			em2.getTransaction().begin();
			
			Query req=em2.createNamedQuery("Eleve.findByMatricule");
			req.setParameter("annee",annee);
			req.setParameter("supprime", false);
			req.setParameter("matricule",matricule);
			
		    eleve=(Eleve) req.getSingleResult();
			if(eleve==null) 
				throw new ElementNOtFoundException(matricule, "Eleve");
					
			
			codeclasse=eleve.getClasse().getCodeclasse();
			
			tranches=listeTranchesClasse(codeclasse, annee);
			
			if(tranches.isEmpty())
				throw new DroitsScolairesNonDefinis(codeclasse);
			for(int i=0;i<tranches.size();i++)
				total+=tranches.get(i).getMontant();
			
			an=em2.find(Annee.class, annee);


			if(droitsScolairescomplet(matricule, annee)){
				// cas o� l'�l�ve a pay� la totalit� des frais
				throw new EleveDSCompletException(matricule);
			}
			
		
			if(!estInscrit(eleve)){
				// Eleve non inscrit, le montant doit valoir au minimum la 1ere tranche
				if(montant>=tranches.get(0).getMontant()){
					
					CodeVersement code=new CodeVersement();
					em2.persist(code);
					
					String codevers=Repertoire.genererCodeVersement(code.getCode());
					vers=new Versement();
					
					vers.setIdversement(codevers);
					vers.setEleve(eleve);
					vers.setAnnee(an);
					vers.setDateVersement(dateVersement);
					vers.setTypeversement(typeversement);
					vers.setMontant(montant);
					vers.setSupprime(false);
					vers.setLogin(login);
					em2.persist(vers);
					eleve.setConfirme(true);
					em2.merge(eleve);
					em2.flush();					
				}
				else
				{
					throw new MontantInscriptionInsuffisant(montant, codeclasse);
				}
			}
			else{
				// eleve inscrit
				float verse;
				Object res;
				Query query=em2.createQuery("select sum(v.montant) from Versement as v where v.annee.anneeacademique=:annee and v.eleve.matricule= :matricule and v.supprime=:supprime");
				query.setParameter("annee",annee);
				query.setParameter("matricule",matricule);
				query.setParameter("supprime", false);
				res=query.getSingleResult();
				if(res==null)
					verse=0;
				else
				    verse=Float.valueOf(res.toString()) ;
				
				if(verse+montant>total){
					throw new TotalVersementExcedantException(verse+montant, matricule);
				}
				else{
					CodeVersement code=new CodeVersement();
					em2.persist(code);
					
					String codevers=Repertoire.genererCodeVersement(code.getCode());
					vers=new Versement();
					
					vers.setIdversement(codevers);
					vers.setEleve(eleve);
					vers.setAnnee(an);
					vers.setDateVersement(dateVersement);
					vers.setMontant(montant);
					vers.setTypeversement(typeversement);
					vers.setLogin(login);
					vers.setSupprime(false);
					em2.persist(vers);
					em2.flush();
				}
			}
			
			em2.getTransaction().commit();
		}
		catch (NoResultException e) {
			throw new ElementNOtFoundException(matricule, "Eleve");
		}
		finally{

			em2.close();
			emf.close();
		}
		return vers.getIdversement();
	
	}
	
	public boolean estInscrit(Eleve eleve){
		return eleve.getConfirme();
	}
		

	public float recherchedroitsScolaires(String matricule, String annee){
		
		EntityManagerFactory emfactory=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtransac=emfactory.createEntityManager();
		Query query=emtransac.createQuery("select sum(v.montant) from Versement as v where v.annee.anneeacademique=:annee and v.eleve.matricule=:matricule and v.supprime=:supprime");
		query.setParameter("annee",annee);
		query.setParameter("matricule",matricule);
		query.setParameter("supprime", false);
		
		Object result=(Object) query.getSingleResult();
	
			
		return Float.valueOf(result.toString());
	}
	
	
	public boolean droitsScolairescomplet(String matricule, String annee){
		boolean retour=false;
		EntityManagerFactory emfactory=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtransac=emfactory.createEntityManager();
		float totalTranches=-1, totalPaye=-1;
		try{
			Query query=emtransac.createQuery("select sum(pt.montant) from ParametreTranche as pt where pt.annee.anneeacademique=:annee and pt.classe= (select c from Eleve as e join e.classe c where e.matricule=:matricule) and pt.supprime=:supprime");
			query.setParameter("annee",annee);
			query.setParameter("matricule",matricule);
			query.setParameter("supprime", false);
			
			Object result=(Object) query.getSingleResult();
			if(result==null){
				totalTranches=0;
			}
			else{
				totalTranches=Float.valueOf(result.toString());
			}
			
			query=emtransac.createQuery("select sum(v.montant) from Versement as v where v.annee.anneeacademique=:annee and v.eleve.matricule=:matricule and v.supprime=:supprime");
			query.setParameter("annee",annee);
			query.setParameter("matricule",matricule);
			query.setParameter("supprime", false);
			
			result=(Object) query.getSingleResult();
			if(result==null){
				totalPaye=0;
			}
			else{
				totalPaye=Float.valueOf(result.toString());
			}	
						
			retour=(totalPaye==totalTranches);
		}
		catch (Exception e) {
			Repertoire.logFatal("Erreur inconnu, Gestion Inscription L 1114", getClass(), e);
		}
		finally{
			emtransac.close();
			emfactory.close();
		}
		
		return retour;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ParametreTranche> rechercheTranchesClasse(String codeclasse,
			int id, String annee) {
		Query query=em.createNamedQuery("ParametreTranche.find");
		query.setParameter("supprime", false);
		query.setParameter("codeclasse", codeclasse);
		query.setParameter("annee", annee);
		query.setParameter("numero", id);
		return query.getResultList();
	}

	@Override
	public void copierTranches(String codeclasse, List<String> classescibles,
			String annee) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		Query query;
		List<ParametreTranche> tranches;
		Classe classe;
		Classe cible;
		String classecible;
		
		try{
			emtrans.getTransaction().begin();
			
			try{
				query=emtrans.createNamedQuery("Classe.findTranches");
				query.setParameter("supprime", false);
				query.setParameter("codeclasse", codeclasse);
				classe =(Classe) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(codeclasse, "Classe");
			}
			
			tranches=classe.getParametreTranches();
			
			for(int i=0;i<classescibles.size();i++){
				try{
					classecible=classescibles.get(i);
					
					if(codeclasse.compareTo(classecible)==0)
						continue;
					
					query=emtrans.createNamedQuery("Classe.findById");
					query.setParameter("supprime", false);
					query.setParameter("codeclasse", classecible);
					cible =(Classe) query.getSingleResult();
				}
				catch(NoResultException e){
					throw new ElementNOtFoundException(codeclasse, "Classe");
				}
				
				query=emtrans.createQuery("delete from ParametreTranche pt where pt.classe.codeclasse='"+classecible+"'");
				query.executeUpdate();
				
				for(int j=0;j<tranches.size();j++){
					enregistrerParametreTranche(cible,tranches.get(j),emtrans);
				}
				
			}
		    emtrans.getTransaction().commit();
		}		
		finally{
			emf.close();
		}
	}

	private void enregistrerParametreTranche(Classe classe,
			ParametreTranche pt, EntityManager emtrans) {
	
		ParametreTranchePK id=new ParametreTranchePK();
		id.setNumero(pt.getId().getNumero());
		id.setAnneeacademique(pt.getAnnee().getAnneeacademique());
		id.setCodeclasse(classe.getCodeclasse());
		
		ParametreTranche paratra = new ParametreTranche();
		
		paratra.setSupprime(false);
		paratra.setId(id);
		paratra.setAnnee(pt.getAnnee());
		paratra.setClasse(classe);
		paratra.setMontant(pt.getMontant());
		paratra.setTypetranche(pt.getTypetranche());
		paratra.setDelaiversement(pt.getDelaiversement());
		
		emtrans.persist(paratra);
		emtrans.flush();
	}

	@Override
	public Versement rechercherVersement(String idversement,String annee) {
		Versement vers=null;
		try{
			Query req=em.createNamedQuery("Versement.findById");
			req.setParameter("annee", annee);
			req.setParameter("supprime", false);
			req.setParameter("idversement", idversement);
			
		    vers=(Versement) req.getSingleResult();
		}
		catch(NoResultException e){
			Repertoire.logWarn("Versement non trouv� ou ++ trouv�s "+idversement, this.getClass());
			e.printStackTrace();
		}
		return vers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PreInscription> PreinscriptionAttente(String annee) {
		List<PreInscription> list=null ;
		try{
			Query req=em.createNamedQuery("Preinscription.findByState");
			req.setParameter("annee", annee);
			req.setParameter("supprime", false);
			req.setParameter("etat", "en cours");
		    list=req.getResultList();
		}
		catch(Exception e){
			Repertoire.logWarn("Erreur lors du listing des preinscriptions", this.getClass());
		}
		
		return list;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<PreInscription> PreinscriptionAttente(String annee,
			String niveau) {
		List<PreInscription> list=null ;
		try{
			Query req=em.createNamedQuery("Preinscription.findByStateAndLevel");
			req.setParameter("annee", annee);
			req.setParameter("supprime", false);
			req.setParameter("etat", "en cours");
			req.setParameter("niveau", niveau);
		    list=req.getResultList();
		}
		catch(Exception e){
			Repertoire.logWarn("Erreur lors du listing des preinscriptions", this.getClass());
		}
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PreInscription> PreinscriptionRejette(String annee,	String niveau) {
		List<PreInscription> list=null ;
		try{
			Query req=em.createNamedQuery("Preinscription.findByStateAndLevel");
			req.setParameter("annee", annee);
			req.setParameter("supprime", false);
			req.setParameter("etat", "rejette");
			req.setParameter("niveau", niveau);
		    list=req.getResultList();
		}
		catch(Exception e){
			Repertoire.logWarn("Erreur lors du listing des preinscriptions", this.getClass());
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PreInscription> PreinscriptionAccepte(String annee,	String niveau) {
		List<PreInscription> list=null ;
		try{
			Query req=em.createNamedQuery("Preinscription.findByStateAndLevel");
			req.setParameter("annee", annee);
			req.setParameter("supprime", false);
			req.setParameter("etat", "accepte");
			req.setParameter("niveau", niveau);
		    list=req.getResultList();
		}
		catch(Exception e){
			Repertoire.logWarn("Erreur lors du listing des preinscriptions", this.getClass());
		}
		
		return list;
	}

	@Override
	public Versement rechercherVersement(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifierversement(String id, String typeversement,
			float montant, Date dateVers) throws ElementNOtFoundException {
		// TODO Auto-generated method stub
				EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
				EntityManager emtrans=emfact.createEntityManager();
				try{		
					
					emtrans.getTransaction().begin();

					
					Versement ver=emtrans.find(Versement.class, id);
					if(ver==null) throw new ElementNOtFoundException(id, "Versement");
					
					ver.setDateVersement(dateVers);
					ver.setMontant(montant);
					ver.setTypeversement(typeversement);
					
					emtrans.merge(ver);
					emtrans.getTransaction().commit();
				}	
				finally{
					emtrans.close();
					emfact.close();
				}
						
						
		
	}

	@Override
	public void cloture(String anneeacademique) {
	

}


	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public void cloture(String anneeacademique, int idutilisateur,String annee,String codeetabli) throws ElementNOtFoundException {
		
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		
		try{
			
		em2.getTransaction().begin();
		System.out.println("l'annee academique est "+  anneeacademique);
		
		Query query=em2.createNamedQuery("Eleve.findAll");
		query.setParameter("annee", anneeacademique);
		query.setParameter("supprime", false);
		List<Eleve> eleves = query.getResultList();
		
		
		Annee an=em2.getReference(Annee.class, annee);
		if(an==null) throw new ElementNOtFoundException(annee, "Annee");
		
		Etablissement etab=em2.getReference(Etablissement.class, codeetabli);
		if(an==null) throw new ElementNOtFoundException(codeetabli, "Etablissement");
		
		
		Utilisateur uti=em2.getReference(Utilisateur.class,idutilisateur);
		if(uti==null) throw new ElementNOtFoundException(String.valueOf(idutilisateur), "utilisateur");
		
		
		if(eleves!=null){
			for(int i=0;i<eleves.size();i++){
				
				Query query2=em2.createNamedQuery("Niveau.findByCode");
				query2.setParameter("supprime", false);
				query2.setParameter("codeniveau",eleves.get(i).getClasse().getOptionserie().getNiveau().getCodeniveau());
				Niveau nivo=(Niveau) query2.getSingleResult();
				CodeDossier cd=new CodeDossier();
				em2.persist(cd);
				
				String codeDossier=Repertoire.genererCodeDossier(cd.getCode());
				float sommeverse;
				Query query1=em2.createNamedQuery("Versement.findAllforEleve");
				query1.setParameter("annee", anneeacademique);
				query1.setParameter("supprime", false);
				query1.setParameter("matricule", eleves.get(i).getIdeleve());
				Object result=(Object) query1.getSingleResult();
				if(result==null){
					sommeverse=0;
				}
				else{
					sommeverse=Float.valueOf(result.toString());
				}
			
				PreInscription pr=new PreInscription();
				
				pr.setCodedossier(codeDossier);
				pr.setAdresse(eleves.get(i).getAdresse());
				pr.setUtilisateur(uti);
				pr.setNomancienetablissement(etab.getNometab());
				pr.setAnnee(an);
				pr.setAnneeancienetablissement(eleves.get(i).getClasse().getAnnee().getAnneeacademique());
				pr.setClasseancienetablissement(eleves.get(i).getClasse().getLibelle());
				pr.setDatenaissance(eleves.get(i).getDatenaissance());
				pr.setDatepreinscription(eleves.get(i).getDateEnregistrement());
				pr.setEmail(eleves.get(i).getEmail());
				pr.setEmailmere(eleves.get(i).getEmailmere());
				pr.setEmailpere(eleves.get(i).getEmailpere());
				pr.setEmailtuteur(eleves.get(i).getEmailtuteur());
				pr.setEtat("en cours");
				pr.setLieunaissance(eleves.get(i).getLieunaissance());
				pr.setNationalite(eleves.get(i).getNationalite());
				pr.setNiveaudemande(nivo);
				pr.setNom(eleves.get(i).getNom().toUpperCase());
				pr.setNomancienetablissement(eleves.get(i).getNomancienetablissment());
				pr.setNommere(eleves.get(i).getNommere());
				pr.setNompere(eleves.get(i).getNompere());
				pr.setNomtuteur(eleves.get(i).getNomtuteur());
				pr.setNumeropayement("");
				pr.setOptiondemande("");
				pr.setPasswordaccess(eleves.get(i).getPassword());
				//String intput=(eleves.get(i).getPrenom()).substring(0,1).toUpperCase()+(eleves.get(i).getPrenom()).substring(1);
				
				pr.setPrenom(eleves.get(i).getPrenom());
				pr.setProfessionmere(eleves.get(i).getProfessionmere());
				pr.setProfessionpere(eleves.get(i).getProfessionpere());
				pr.setProfessiontuteur(eleves.get(i).getProfessiontuteur());
				pr.setSexe(eleves.get(i).getSexe());
				pr.setSupprime(false);
				pr.setTelephone(eleves.get(i).getTelephone());
				pr.setTelephonemere(eleves.get(i).getTelephonemere());
				pr.setTelephonepere(eleves.get(i).getTelephonepere());
				pr.setTelephonetuteur(eleves.get(i).getTelephonetuteur());
				pr.setBoitepostale(eleves.get(i).getBoitepostale());
				pr.setSommeverse(sommeverse);
				
				double moyenne= gestionbul.moyenneEleveS(eleves.get(i).getClasse().getCodeclasse(), eleves.get(i).getIdeleve(), anneeacademique);
				pr.setDernieremoyenne(moyenne);
				pr.setMatricule(eleves.get(i).getMatricule());
				
				em2.persist(pr);
			}
		
			}
		em2.getTransaction().commit();
		}
		finally{
			em2.close();
			emf.close();
		}
		
	}
	
public Etablissement rechercherGestionnaire(String login) {		
		 
		Query query;
		
		try{
			query=em.createNamedQuery("Etablissement.findGestionnaire");
			query.setParameter("login", login);
			query.setParameter("supprime", false);
			return (Etablissement) query.getSingleResult();
		}
		catch(Exception e){
			return null;
			
		}
		
		
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<String> listerAnnee() {
		Query query=em.createNamedQuery("Annee.findCode");
		query.setParameter("supprime", false);
		query.setParameter("close", false);
		return extracted(query);
	}
	
	@SuppressWarnings("rawtypes")
	private List extracted(Query query) {
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Versement> listerVersementsClasse(String classe,
			String annee) throws JPAException {
			Query query;
		
		
				
				try{
					query=em.createQuery("select v from Versement as v where v.annee.anneeacademique = :annee and v.eleve. classe.codeclasse = :classe and v.supprime=:supprime");
					query.setParameter("annee", annee);
					query.setParameter("classe", classe);
					query.setParameter("supprime", false);
				}
				catch(Exception e){
					throw new JPAException("Lister Versements enregistr�s",e);
				}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> listerAnneeAdmin() {
		Query query=em.createNamedQuery("Annee.findCodeAll");
		query.setParameter("supprime", false);
		return extracted(query);
	}
	
	
	@Override
	public int enregistrerCertificat(int matricule, String codeclasse,
			String anneeacademique) throws ElementNOtFoundException {
		int result=0;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		
		try{
			em2.getTransaction().begin();
			Annee an=em2.getReference(Annee.class, anneeacademique);
			if(an==null) throw new ElementNOtFoundException(anneeacademique, "Annee");

			Eleve el=em2.getReference(Eleve.class, matricule);
			if(el==null) throw new ElementNOtFoundException("matricule", "Eleve");
			
			Certificats cert=new Certificats();
			
			cert.setAnnee(an);
			cert.setEleve(el);
			em2.persist(cert);
			em2.getTransaction().commit();
		    
			result=cert.getCodecertificat();
		
		}catch(Exception e){
			return -1;
		}
		finally{
			em2.close();
			emf.close();
		}
		
		
		return result;
	}
	
}