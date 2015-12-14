package ejb.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;
import ejb.GestionEleveLocal;
import entities.Annee;
import entities.Classe;
import entities.Eleve;

/**
 * Session Bean implementation class GestionEleve
 * Classe de gestion des eleves
 */
@Stateless(name="GestionEleve",mappedName = "GestionEleve")
@TransactionManagement(TransactionManagementType.BEAN)
public class GestionEleve implements GestionEleveLocal {

	@PersistenceContext(unitName="agespersist")
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public GestionEleve() {
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Eleve> listerEleves(String anneeAcademique) {
		
		Query query=em.createNamedQuery("Eleve.findAll");
		query.setParameter("annee", anneeAcademique);
		query.setParameter("supprime", false);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eleve> listeEleveNonConfirme(String codeclasse,String annee) {
		List<Eleve> eleves;
		try{
			Query req=em.createNamedQuery("Eleve.findAllNonInscrits");
			req.setParameter("annee", annee);
			req.setParameter("supprime", false);
			req.setParameter("confirme", false);
			req.setParameter("classe", codeclasse);
			
		    eleves=req.getResultList();
		}
		catch(Exception e){
			Repertoire.logWarn("Eleves non inscris non trouveés, classe "+codeclasse, this.getClass());
			e.printStackTrace();
			eleves=new ArrayList<Eleve>();
		}
		return eleves;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eleve> listeEleveConfirme(String codeclasse,String annee) {
		List<Eleve> eleves;
		try{
			Query req=em.createNamedQuery("Eleve.findAllNonInscrits");
			req.setParameter("annee", annee);
			req.setParameter("supprime", false);
			req.setParameter("confirme", true);
			req.setParameter("classe", codeclasse);
			
		    eleves=req.getResultList();
		}
		catch(Exception e){
			Repertoire.logWarn("Eleves inscrits non trouveés, classe "+codeclasse, this.getClass());
			e.printStackTrace();
			eleves=new ArrayList<Eleve>();
		}
		return eleves;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eleve> listerProvisoireElevesClasse(String codeclasse,
			String annee) {
		List<Eleve> eleves;
		try{
			Query req=em.createNamedQuery("Eleve.findAllFromClasse");
			req.setParameter("annee", annee);
			req.setParameter("supprime", false);
			req.setParameter("classe", codeclasse);
			
		    eleves=req.getResultList();
		}
		catch(Exception e){
			Repertoire.logWarn("Eleves provisoires non trouvés, classe "+codeclasse, this.getClass());
			e.printStackTrace();
			eleves=new ArrayList<Eleve>();
		}
		return eleves;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eleve> listerElevesNERClasse(String codeClasse,
			String anneeAcEncours) {
		Query query=em.createNamedQuery("Eleve.findNERFromClasse");
		query.setParameter("annee", anneeAcEncours);
		query.setParameter("supprime", false);
		query.setParameter("classe", codeClasse);
		query.setParameter("date", new Date(),TemporalType.DATE);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eleve> listerElevesERClasse(String codeClasse,
			String anneeAcEncours) {
		
		//Query query=em.createNamedQuery("Eleve.findERFromClasse");
		Query query=em.createNamedQuery("Eleve.findAllFromClasse");
		query.setParameter("annee", anneeAcEncours);
		query.setParameter("supprime", false);
		query.setParameter("classe", codeClasse);
		//query.setParameter("date", new Date(),TemporalType.DATE);
		return query.getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Integer> listerElevesERClasseN(String codeClasse,
			String anneeAcEncours) {
		
		//Query query=em.createNamedQuery("Eleve.findERFromClasse");
		Query query=em.createNamedQuery("Eleve.findAllFromClasseID");
		query.setParameter("annee", anneeAcEncours);
		query.setParameter("supprime", false);
		query.setParameter("classe", codeClasse);
		//query.setParameter("date", new Date(),TemporalType.DATE);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eleve> listerElevesClasse(String codeclasse,String annee) {
		List<Eleve> eleves;
		try{
			Query req=em.createNamedQuery("Eleve.findAllFromClasse");
			req.setParameter("annee", annee);
			req.setParameter("supprime", false);
			req.setParameter("classe", codeclasse);
			
		    eleves=req.getResultList();
		}
		catch(Exception e){
			Repertoire.logWarn("Eleves inscrits non trouveés, classe "+codeclasse, this.getClass());
			e.printStackTrace();
			eleves=new ArrayList<Eleve>();
		}
		return eleves;
	}



	@Override
	public boolean supprimerEleve(String matricule,String annee) throws ElementNOtFoundException {
		Eleve el;
		Query req;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		try{
			emtrans.getTransaction().begin();
			try{
				req=emtrans.createNamedQuery("Eleve.findByMatricule");
				req.setParameter("annee", annee);
				req.setParameter("supprime", false);
				req.setParameter("matricule", matricule);
				
			    el=(Eleve) req.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(matricule, "Eleve");
			}
			
			el.setSupprime(true);
		
			emtrans.merge(el);
			emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
		return true;
	}

	@Override
	public String enregistrerEleve(String matricule,String nom, String prenom,
			Date dateNaissance, String lieuNaissance, String sexe,
			String nationalite, String photo, String codeClasse,
			boolean redoublant, String adresse, String email, String telephone,
			String boitePostale, String nomPere, String nomMere,
			String nomTuteur, String telephonePere, String telephoneMere,
			String telephoneTuteur, String professionPere,
			String professionMere, String professionTuteur, String emailPere,
			String emailMere, String emailTuteur, boolean ancien,
			String ancienEtablissement, String anneeAncienEtablissement,
			String classeAncienEtablissement,String annee) throws ElementNOtFoundException, DuplicateKeyException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		Query req;
		String result="";
		Classe clas;
		
		try{
					
			emtrans.getTransaction().begin();
			
			Annee an=emtrans.find(Annee.class,annee);
			if(an==null) throw (new RuntimeException("Essai d'enregitrement d'un eleve pour  une année indéfinie "));
			
			

			try{
				req=emtrans.createQuery("select c from Classe as c where c.supprime=:supprime  and c.codeclasse=:classe");
				req.setParameter("supprime", false);
				req.setParameter("classe", codeClasse);
				
			    clas=(Classe) req.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(codeClasse, "Classe");
			}
			
			Eleve el=new Eleve();
			el.setMatricule("123");
			emtrans.persist(el);
			emtrans.flush();	
			
			// Si le matricule n'a pas été passé automatiquement, on le genere
			if(matricule==null|| matricule.isEmpty()){
				matricule=Repertoire.genererCodeEleve(clas.getOptionserie().getNiveau().getIdniveau(), el.getIdeleve());
			}
			else{
				// verifions que le matricule passé la n'existe pas déja!!!
				try{
					req=emtrans.createNamedQuery("Eleve.findByMatricule");
					req.setParameter("annee", annee);
					req.setParameter("supprime", false);
					req.setParameter("matricule", matricule);
					
				    el=(Eleve) req.getSingleResult();
				    
				    throw new DuplicateKeyException("Eleve "+matricule);
				}
				catch(NoResultException e){}
			}
			
			
			// et dans tous les cas on met a jour les informations sur l'eleve
			try{
				req=emtrans.createNamedQuery("Eleve.findByMatricule");
				req.setParameter("annee", annee);
				req.setParameter("supprime", false);
				req.setParameter("matricule", matricule);
				
			    el=(Eleve) req.getSingleResult();
			    
			    throw new DuplicateKeyException("Eleve "+matricule);
			}
			catch(NoResultException e){
				el.setMatricule(matricule);
				el.setAdresse(adresse);
				el.setAncien(ancien);
				el.setAnnee(an);
				el.setAnneeancienetablissement(anneeAncienEtablissement);
				el.setBoitepostale(boitePostale);
				el.setClasse(clas);
				el.setPhoto(photo+matricule+".jpg");
				el.setClasseancienetablissement(classeAncienEtablissement);
				el.setConfirme(false);
				el.setDatenaissance(dateNaissance);
				el.setDateEnregistrement(new Date());
				el.setEmail(email);
				el.setEmailmere(emailMere);
				el.setEmailpere(emailPere);
				el.setEmailtuteur(emailTuteur);
				el.setLieunaissance(lieuNaissance);
							
				el.setLogin(el.getMatricule());
				el.setNationalite(nationalite);
				el.setNom(nom);
				el.setNomancienetablissment(ancienEtablissement);
				el.setNommere(nomMere);
				el.setNompere(nomPere);
				el.setNomtuteur(nomTuteur);
				el.setNumeroPayement("");
				el.setPassword(el.getMatricule());
				el.setPrenom(prenom);
				el.setProfessionmere(professionMere);
				el.setProfessionpere(professionPere);
				el.setProfessiontuteur(professionTuteur);
				el.setSexe(sexe);
				el.setSupprime(false);
				el.setTelephone(telephone);
				el.setTelephonemere(telephoneMere);
				el.setTelephonepere(telephonePere);
				el.setTelephonetuteur(telephoneTuteur);
				
				emtrans.merge(el);
				
			}
			
			emtrans.getTransaction().commit();
			
			result=el.getMatricule();
		
		}finally{
			emtrans.close();
			emf.close();
		}
		
		return result;
	}



	@Override
	public Eleve rechercheEleve(String matricule, String annee) {
		Eleve el;
		try{
			Query req=em.createNamedQuery("Eleve.findByMatricule");
			req.setParameter("annee", annee);
			req.setParameter("supprime", false);
			req.setParameter("matricule", matricule);
			
		    el=(Eleve) req.getSingleResult();
		}
		catch(NoResultException e){
			Repertoire.logInfo("Recherche élève infructueuse "+matricule, this.getClass());
			el=null;
		}
		return el;
	}
	
	public Eleve rechercheEleveId(int ideleve, String annee) {
		Eleve el;
		try{
			Query req=em.createNamedQuery("Eleve.findById");
			req.setParameter("annee", annee);
			req.setParameter("supprime", false);
			req.setParameter("ideleve", ideleve);
			
		    el=(Eleve) req.getSingleResult();
		}
		catch(NoResultException e){
			Repertoire.logInfo("Recherche élève infructueuse "+ideleve, this.getClass());
			el=null;
		}
		return el;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eleve> rechercherEleves(String motcle, String codeclasse,
			String filtre,String anneeAcEncours) {
		String filtreNom="";
		String filtrePrenom="";
		String filtremat="";
		String[] mots=motcle.trim().replaceAll(" ", ";").split(";");
		Query query=null;
		
		if(filtre.compareToIgnoreCase("nom")==0){
			for(int i=1;i<mots.length;i++){
				if(!mots[i].isEmpty()){
					filtreNom+=" or e.nom LIKE '%"+mots[i]+"%' ";
					filtrePrenom+=" or e.prenom LIKE '%"+mots[i]+"%' ";
				}			
			}
			
			String requete="select e from Eleve as e where e.nom LIKE '%"+mots[0]+"%'"+filtreNom+" or e.prenom LIKE '%"+mots[0]+"%'"+filtrePrenom+" and e.annee.anneeacademique=:annee and e.classe.codeclasse=:classe and e.supprime=:supprime";
					
			query=em.createQuery(requete);
			query.setParameter("annee", anneeAcEncours);
			query.setParameter("classe", codeclasse);
			query.setParameter("supprime", false);
		}
		else{
			if(filtre.compareToIgnoreCase("matricule")==0){
				for(int i=1;i<mots.length;i++){
					if(!mots[i].isEmpty()){
						filtremat+=" or e.matricule LIKE '%"+mots[i]+"%' ";
					}			
				}
				
				String requete="select e from Eleve as e where e.matricule LIKE '%"+mots[0]+"%'"+filtremat+"and e.annee.anneeacademique=:annee and e.classe.codeclasse=:classe";
						
				query=em.createQuery(requete);
				query.setParameter("annee", anneeAcEncours);
				query.setParameter("classe", codeclasse);
			}
		}
		
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eleve> rechercherElevesClasse(String motcle, String codeclasse,
			String anneeAcEncours) {
		String filtreNom="";
		String filtrePrenom="";
		String[] mots=motcle.trim().replaceAll(" ", ";").split(";");
		
		for(int i=1;i<mots.length;i++){
			if(!mots[i].isEmpty()){
				filtreNom+=" or e.nom LIKE '%"+mots[i]+"%' ";
				filtrePrenom+=" or e.prenom LIKE '%"+mots[i]+"%' ";
			}			
		}
		
		String requete="select e from Eleve as e where e.nom LIKE '%"+mots[0]+"%'"+filtreNom+" or e.prenom LIKE '%"+mots[0]+"%'"+filtrePrenom+" and e.annee.anneeacademique=:annee and e.classe.codeclasse=:classe and e.supprime=:supprime";
				
		Query query=em.createQuery(requete);
		query.setParameter("annee", anneeAcEncours);
		query.setParameter("classe", codeclasse);
		query.setParameter("supprime", false);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eleve> rechercherEleves(String motcle, String anneeAcEncours) {
		String filtreNom="";
		String filtrePrenom="";
		String[] mots=motcle.trim().replaceAll(" ", ";").split(";");
		
		for(int i=1;i<mots.length;i++){
			if(!mots[i].isEmpty()){
				filtreNom+=" or e.nom LIKE '%"+mots[i]+"%' ";
				filtrePrenom+=" or e.prenom LIKE '%"+mots[i]+"%' ";
			}
			
		}
		
		String requete="select e from Eleve as e where e.nom LIKE '%"+mots[0]+"%'"+filtreNom+" or e.prenom LIKE '%"+mots[0]+"%'"+filtrePrenom+" and e.annee.anneeacademique=:annee and e.supprime=:supprime";
				
		Query query=em.createQuery(requete);
		query.setParameter("annee", anneeAcEncours);
		query.setParameter("supprime", false);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eleve> rechercherElevesFiltre(String motcle, String filtre,
			String anneeAcEncours) {
		String filtreNom="";
		String filtrePrenom="";
		String filtremat="";
		String[] mots=motcle.trim().replaceAll(" ", ";").split(";");
		Query query=null;
		
		if(filtre.compareToIgnoreCase("nom")==0){
			for(int i=1;i<mots.length;i++){
				if(!mots[i].isEmpty()){
					filtreNom+=" or e.nom LIKE '%"+mots[i]+"%' ";
					filtrePrenom+=" or e.prenom LIKE '%"+mots[i]+"%' ";
				}			
			}
			
			String requete="select e from Eleve as e where e.nom LIKE '%"+mots[0]+"%'"+filtreNom+" or e.prenom LIKE '%"+mots[0]+"%'"+filtrePrenom+" and e.annee.anneeacademique=:annee and e.supprime=:supprime";
					
			query=em.createQuery(requete);
			query.setParameter("annee", anneeAcEncours);
			query.setParameter("supprime", false);
		}
		else{
			if(filtre.compareToIgnoreCase("matricule")==0){
				for(int i=1;i<mots.length;i++){
					if(!mots[i].isEmpty()){
						filtremat+=" or e.matricule LIKE '%"+mots[i]+"%' ";
					}			
				}
				
				String requete="select e from Eleve as e where e.matricule LIKE '%"+mots[0]+"%'"+filtremat+"and e.annee.anneeacademique=:annee and e.supprime=:supprime";
						
				query=em.createQuery(requete);
				query.setParameter("annee", anneeAcEncours);
				query.setParameter("supprime", false);
			}
		}	
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eleve> listerElevesinscrits(String anneeAcEncours) {
		Query query=em.createNamedQuery("Eleve.findAll");
		query.setParameter("supprime", false);
		query.setParameter("annee", anneeAcEncours);
		return query.getResultList();
	}

	@Override
	public void migrateClass(String matricule, String nouvelleClasse,String annee) throws ElementNOtFoundException {
		Eleve el;
		Classe clas;
		Query req;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		try{
			emtrans.getTransaction().begin();
			try{
				req=emtrans.createNamedQuery("Eleve.findByMatricule");
				req.setParameter("annee", annee);
				req.setParameter("supprime", false);
				req.setParameter("matricule", matricule);
				
			    el=(Eleve) req.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(matricule, "Eleve");
			}
			
			try{
				req=emtrans.createQuery("select c from Classe as c where c.supprime=:supprime and  c.codeclasse=:classe");
				//req.setParameter("annee", annee);
				req.setParameter("supprime", false);
				req.setParameter("classe", nouvelleClasse);
				
			    clas=(Classe) req.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(nouvelleClasse, "Classe");
			}
			el.setClasse(clas);
			emtrans.merge(el);
			emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
	}

	@Override
	public void modifierEleve(int ideleve,String matricule, String nom, String prenom,
			Date dateNaissance, String lieuNaissance, String sexe,
			String nationalite, String photo, String codeClasse,
			boolean redoublant, String adresse, String email, String telephone,
			String boitePostale, String nomPere, String nomMere,
			String nomTuteur, String telephonePere, String telephoneMere,
			String telephoneTuteur, String professionPere,
			String professionMere, String professionTuteur, String emailPere,
			String emailMere, String emailTuteur, boolean ancien,
			String ancienEtablissement, String anneeAncienEtablissement,
			String classeAncienEtablissement,String annee) throws ElementNOtFoundException, DuplicateKeyException {
		
		Eleve el;
		Query req;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		try{
			emtrans.getTransaction().begin();
			try{
				req=emtrans.createNamedQuery("Eleve.findById");
				req.setParameter("annee", annee);
				req.setParameter("supprime", false);
				req.setParameter("ideleve", ideleve);
				
			    el=(Eleve) req.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(String.valueOf(ideleve), "Eleve");
			}
			
			try{
				req=emtrans.createNamedQuery("Eleve.findByMatricule");
				req.setParameter("annee", annee);
				req.setParameter("supprime", false);
				req.setParameter("matricule", matricule);
				
			    el=(Eleve) req.getSingleResult();
			    
			    // un autre gars possede déja ce matricule!!!
			    if(el.getIdeleve()!=ideleve)
			    	throw new DuplicateKeyException("Eleve "+matricule);
			}
			catch(NoResultException e){}
			
			el.setMatricule(matricule);
			el.setAdresse(adresse);
			el.setAncien(ancien);
			el.setAnneeancienetablissement(anneeAncienEtablissement);
			el.setBoitepostale(boitePostale);
			el.setClasseancienetablissement(classeAncienEtablissement);
			el.setDatenaissance(dateNaissance);
			el.setEmail(email);
			el.setEmailmere(emailMere);
			el.setEmailpere(emailPere);
			el.setEmailtuteur(emailTuteur);
			el.setLieunaissance(lieuNaissance);
			el.setNationalite(nationalite);
			el.setNom(nom);
			el.setNomancienetablissment(ancienEtablissement);
			el.setNommere(nomMere);
			el.setNompere(nomPere);
			el.setNomtuteur(nomTuteur);
			el.setPrenom(prenom);
			el.setProfessionmere(professionMere);
			el.setProfessionpere(professionPere);
			el.setProfessiontuteur(professionTuteur);
			el.setRedoublant(redoublant);
			el.setSexe(sexe);
			el.setTelephone(telephone);
			el.setTelephonemere(telephoneMere);
			el.setTelephonepere(telephonePere);
			el.setTelephonetuteur(telephoneTuteur);
		
			emtrans.merge(el);
			emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
	}
	
	public int effectif(String codeclasse) {
		int effect;
		try{
			Query req=em.createNamedQuery("Eleve.count");
			req.setParameter("codeclasse", codeclasse);
			
		    effect=Integer.parseInt(req.getSingleResult().toString()) ;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return effect;
	}
	
}


