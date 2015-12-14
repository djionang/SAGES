package ejb.impl;


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

import utils.Repertoire;

import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;
import ejb.GestionMatiereGroupeLocal;
import entities.AnimateurPedagogique;
import entities.AnimateurPedagogiquePK;
import entities.Annee;
import entities.Classe;
import entities.Cour;
import entities.Enseignant;
import entities.Groupematiere;
import entities.Matiere;

@Stateless(mappedName = "GestionMatiere")
@TransactionManagement(TransactionManagementType.BEAN)
public class GestionMatiereGroupe implements GestionMatiereGroupeLocal{
	
	@PersistenceContext(unitName="agespersist")
	EntityManager em;
	
	@Override
	public void enregistrerMatiere(String codematiere, String libelle,
			String description) throws DuplicateKeyException {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		Query query;
		Matiere mat=null;
		try{
			emtrans.getTransaction().begin();
			try{
				query=emtrans.createNamedQuery("Matiere.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("codematiere", codematiere);
				mat=(Matiere) query.getSingleResult();
			}
			catch(NoResultException e){
			
			}
			
			if(mat!=null)
				throw new DuplicateKeyException("Matiere");
			mat =new Matiere();
			
			mat.setCodematiere(codematiere);
			mat.setLibelle(libelle);
			mat.setDescription(description);
			mat.setSupprime(false);
			
			emtrans.persist(mat);
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
		
	}

	@Override
	public void modifierMatiere(String codematiere, String libelle,
			String description) throws ElementNOtFoundException {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		Query query;
		Matiere mat=null;
		try{
			emtrans.getTransaction().begin();
			try{
				query=emtrans.createNamedQuery("Matiere.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("codematiere", codematiere);
				mat=(Matiere) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(codematiere, "Matiere");
			}
			
			mat.setLibelle(libelle);
			mat.setDescription(description);
			mat.setSupprime(false);
			
			emtrans.merge(mat);
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
		
	}

	@Override
	public void supprimerMatiere(String codematiere) throws ElementNOtFoundException {
		
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();
		
		Query query;
		Matiere mat=null;
		try{
			emtrans.getTransaction().begin();
			try{
				query=emtrans.createNamedQuery("Matiere.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("codematiere", codematiere);
				mat=(Matiere) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(codematiere, "Matiere");
			}
			
			mat.setSupprime(true);
			
			emtrans.merge(mat);
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emfact.close();
		}
		
	}

	@Override
	public void enregistrerGroupeMatiere( String libellegm,
			String description) throws DuplicateKeyException {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		Groupematiere grpemat=null;
		Query query;
		
		try{
			emtrans.getTransaction().begin();
			try{
				query=emtrans.createNamedQuery("Groupematiere.findByUCode");
				query.setParameter("libellegm", libellegm);
				grpemat= (Groupematiere) query.getSingleResult();
			}
			catch(NoResultException e){
			
			}
			
			if(grpemat!=null){
				grpemat.setDescription(description);
				grpemat.setSupprime(false);
				emtrans.merge(grpemat);
			}
			else{
				grpemat =new Groupematiere();				
				grpemat.setDescription(description);
				grpemat.setLibellegm(libellegm);
				grpemat.setSupprime(false);				
				emtrans.persist(grpemat);
			}			
			
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
		
	}

	@Override
	public void modifierGroupeMatiere(String libellegm,
			String description) throws ElementNOtFoundException {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		Query query;
		Groupematiere gm=null;
	
		try{
			emtrans.getTransaction().begin();
			try{
				query=emtrans.createNamedQuery("Groupematiere.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("libellegm", libellegm);
				gm= (Groupematiere) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(libellegm, "GroupeMatiere");
			}
			
			gm.setLibellegm(libellegm);
			gm.setDescription(description);
			gm.setSupprime(false);
			
			emtrans.merge(gm);
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
		
	}

	@Override
	public void supprimerGroupeMatiere(String libellegm) throws ElementNOtFoundException {
		
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();
		Query query;
		Groupematiere gm=null;
		
		try{
			emtrans.getTransaction().begin();
			try{
				query=emtrans.createNamedQuery("Groupematiere.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("libellegm", libellegm);
				gm= (Groupematiere) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(libellegm, "GroupeMatiere");
			}
			gm.setSupprime(true);
			
			emtrans.merge(gm);
		    emtrans.getTransaction().commit();
			
		}
		finally{
			emtrans.close();
			emfact.close();
		}
		
	}

	@Override
	public void assignerGroupeMatiere(String code, String codematiere) throws ElementNOtFoundException {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		try{
			emtrans.getTransaction().begin();

			
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
		
	}

	public Matiere rechercherMatier(String codematiere, EntityManager em) {
		Query query;
		Matiere mat=null;
		try{
			System.out.println("le code matiere est " +codematiere);
			query=em.createNamedQuery("Matiere.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("codematiere", codematiere);
			mat=(Matiere) query.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
		return mat;
	}
	
	@Override
	public Matiere rechercherMatiere(String codematiere) {
		
		return rechercherMatier(codematiere, em);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Matiere> listerMatieres() {
		Query query;
		
		query=em.createNamedQuery("Matiere.findAll");
		query.setParameter("supprime", false);
		return (List<Matiere>) query.getResultList();
	}

	@Override
	public void modifierAnimateurMatiere(String codematiere,
			String nouvelAnimateur) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		Query query;
		Matiere mat=null;
		Enseignant ens=null;
		
		try{
			emtrans.getTransaction().begin();
			try{
				query=emtrans.createNamedQuery("Matiere.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("codematiere", codematiere);
				mat=(Matiere) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(codematiere, "Matiere");
			}
			
			try{
				query=emtrans.createNamedQuery("Enseignant.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("code",nouvelAnimateur);
				ens=(Enseignant) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(nouvelAnimateur, "Enseignant");
			}
			AnimateurPedagogiquePK id=new AnimateurPedagogiquePK();
			AnimateurPedagogique ap=new AnimateurPedagogique();
			ap.setId(id);
			ap.setDateDecision(new Date());
			ap.setEnseignant(ens);
			ap.setMatiere(mat);
			ap.setSupprime(false);
			
			emtrans.persist(ap);
			
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
	}

	@Override
	public Groupematiere rechercherGroupeMatiere(String libelle) {
		Query query;
		Groupematiere gm=null;
		try{
			query=em.createNamedQuery("Groupematiere.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("libellegm", libelle);
			gm=(Groupematiere) query.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
		return gm;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Groupematiere> listerGroupesMatieres() {
		Query query;
		
		query=em.createNamedQuery("Groupematiere.findAll");
		query.setParameter("supprime", false);
		return (List<Groupematiere>) query.getResultList();
	}

	@Override
	public int enregistrerCours(String codeclasse, String codematiere,
			String libelle, String description, String libellegm, int coeficient,String annee) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		Cour cours=null;
		Classe cla=null;
		Groupematiere gm=null;
		Matiere mat=null;
		Annee an =null;
		Query query;
		
		try{
			emtrans.getTransaction().begin();
			
			try{
				query=emtrans.createNamedQuery("Cours.findByCodes");
				query.setParameter("codeclasse", codeclasse);
				query.setParameter("codematiere", codematiere);
				query.setParameter("supprime", false);
				query.setParameter("annee", annee);
				cours=  (Cour) query.getSingleResult();
			}
			catch(NoResultException e){
			
			}
			
			
			try{
				query=emtrans.createNamedQuery("Classe.findById");
				query.setParameter("supprime", false);
				query.setParameter("codeclasse", codeclasse);
				cla =(Classe) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(codeclasse, "Classe");
			}
			
			try{
				query=emtrans.createNamedQuery("Annee.findAnneeEnCours");
				query.setParameter("supprime", false);
				query.setParameter("annee", annee);
				an =(Annee) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(annee, "Annee");
			}
			
			try{
				query=emtrans.createNamedQuery("Matiere.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("codematiere", codematiere);
				mat=(Matiere) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(codematiere, "Matiere");
			}
			
			try{
				query=emtrans.createNamedQuery("Groupematiere.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("libellegm", libellegm);
				gm=(Groupematiere) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(libellegm, "GroupeMatiere");
			}
			
			if(cours!=null){
				cours.setClasse(cla);
				cours.setCoeficient(coeficient);
				cours.setDescription(description);
				cours.setGroupematiere(gm);
				cours.setLibelleCours(libelle);
				cours.setMatiere(mat);
				cours.setAnnee(an);
				cours.setSupprime(false);
				emtrans.merge(cours);
			}
			else{
				cours =new Cour();				
				cours.setClasse(cla);
				cours.setCoeficient(coeficient);
				cours.setDescription(description);
				cours.setGroupematiere(gm);
				cours.setLibelleCours(libelle);
				cours.setMatiere(mat);
				cours.setAnnee(an);
				cours.setSupprime(false);			
				emtrans.persist(cours);
			}			
			
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
		return cours.getCodecours();
	}
	
	public int enregistrerCour(String codeclasse, String codematiere,
			String libelle, String description, String libellegm, int coeficient,String annee) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		Cour cours=null;
		Classe cla=null;
		Groupematiere gm=null;
		Matiere mat=null;
		Annee an = null;
		Query query;
		
		try{
			
			emtrans.getTransaction().begin();
			
			query=emtrans.createNamedQuery("Annee.findAnneeEnCours");
			query.setParameter("supprime", false);
			query.setParameter("annee", annee);
			an =(Annee) query.getSingleResult();
			
			
			query=em.createNamedQuery("Cours.findAll");
			query.setParameter("supprime", false);
			query.setParameter("annee", "2012-2013");
			@SuppressWarnings({ "unchecked" })
			
			List<Cour> list = (List<Cour>) query.getResultList();
			
		for(int i=0;i<list.size();i++){
			try{
				query=emtrans.createNamedQuery("Classe.findById");
				query.setParameter("supprime", false);
				query.setParameter("codeclasse", list.get(i).getClasse().getCodeclasse());
				cla =(Classe) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(list.get(i).getClasse().getCodeclasse(), "Classe");
			}
			
			try{
				query=emtrans.createNamedQuery("Matiere.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("codematiere", list.get(i).getMatiere().getCodematiere());
				mat=(Matiere) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(list.get(i).getMatiere().getCodematiere(), "Matiere");
			}
			
			try{
				query=emtrans.createNamedQuery("Groupematiere.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("libellegm", list.get(i).getGroupematiere().getLibellegm());
				gm=(Groupematiere) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(libellegm, "GroupeMatiere");
			}

				cours =new Cour();				
				cours.setClasse(cla);
				cours.setCoeficient(list.get(i).getCoeficient());
				cours.setDescription(list.get(i).getDescription());
				cours.setGroupematiere(gm);
				cours.setLibelleCours(list.get(i).getLibelleCours());
				cours.setMatiere(mat);
				cours.setAnnee(an);
				cours.setSupprime(false);			
				emtrans.persist(cours);
				
		}
		emtrans.getTransaction().commit();   
			
		}finally{
			emtrans.close();
			
			emf.close();
		}
		return cours.getCodecours();
	}
	
	
	
	

	@Override
	public Cour rechercherCours(String codeclasse, String codematiere,String annee) {
		Query query;
		Cour cr=null;
		try{
			query=em.createNamedQuery("Cours.findByCodes");
			query.setParameter("supprime", false);
			query.setParameter("codeclasse",codeclasse);
			query.setParameter("codematiere", codematiere);
			query.setParameter("annee", annee);
			cr=(Cour) query.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
		return cr;
	}

	@Override
	public Cour rechercherCours(int codecours,String annee) {
		
		return rechercheCour(codecours, annee, em);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cour> listerCours(String annee) {
		Query query;
		
		query=em.createNamedQuery("Cours.findAll");
		query.setParameter("supprime", false);
		query.setParameter("annee", annee);
		return (List<Cour>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cour> listerCours(String classe,String annee) {
		Query query;
		
		query=em.createNamedQuery("Cours.findByClasse");
		query.setParameter("supprime", false);
		query.setParameter("codeclasse", classe);
		query.setParameter("annee", annee);
		return (List<Cour>) query.getResultList();
	}

	@Override
	public void modifierCours(int codecours, String libelle,
			String description, String libellegm, int coeficient, String codeclasse,String annee) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		Query query;
		Cour cours=null;
		Groupematiere gm=null;
		Classe cla=null;
		try{
			emtrans.getTransaction().begin();
			
			
			try{
				query=emtrans.createNamedQuery("Cours.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("annee", annee);
				query.setParameter("code",codecours);
				cours=(Cour) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(String.valueOf(codecours), "Cours");
			}
			
			
			if(cours.getGroupematiere().getLibellegm()!=libellegm){

				try{
					query=emtrans.createNamedQuery("Groupematiere.findByCode");
					query.setParameter("supprime", false);
					query.setParameter("libellegm", libellegm);
					gm= (Groupematiere) query.getSingleResult();
				}
				catch(NoResultException e){
					throw new ElementNOtFoundException(libellegm, "GroupeMatiere");
				}
				cours.setGroupematiere(gm);
			}
			
			if(cours.getClasse().getCodeclasse()!=codeclasse){
				try{
					query=emtrans.createNamedQuery("Classe.findById");
					query.setParameter("supprime", false);
					query.setParameter("codeclasse", codeclasse);
					cla =(Classe) query.getSingleResult();
				}
				catch(NoResultException e){
					throw new ElementNOtFoundException(codeclasse, "Classe");
				}
				
				cours.setClasse(cla);
			}
			

			cours.setLibelleCours(libelle);
			cours.setDescription(description);
			cours.setCoeficient(coeficient);
				
			emtrans.merge(cours);
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
	}

	@Override
	public void supprimerCours(int codecours,String annee) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		Query query;
		Cour cours=null;
		try{
			emtrans.getTransaction().begin();
			
			try{
				query=emtrans.createNamedQuery("Cours.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("annee", annee);
				query.setParameter("code",codecours);
				cours=(Cour) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(String.valueOf(codecours), "Cours");
			}
			
			cours.setSupprime(true);
			emtrans.merge(cours);
			
			query=emtrans.createQuery("delete from Programmation p where p.evaluation.cour.codecours="+codecours);
			query.executeUpdate();
			
			query=emtrans.createQuery("delete from Evaluation e where e.cour.codecours="+codecours);
			query.executeUpdate();
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
	}

	@Override
	public void modifierEnseignantCours(int codecours, String codeenseignant,String annee) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		Query query;
		Cour cours=null;
		Enseignant ens=null;
		try{
			emtrans.getTransaction().begin();
			try{
				query=emtrans.createNamedQuery("Enseignant.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("code",codeenseignant);
				ens=(Enseignant) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(codeenseignant, "Enseignant");
			}
			
			
			try{
				query=emtrans.createNamedQuery("Cours.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("annee", annee);
				query.setParameter("code",codecours);
				cours=(Cour) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(String.valueOf(codecours), "Cours");
			}
			
			cours.setEnseignant(ens);
			emtrans.merge(cours);
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
	}

	private Cour rechercheCour(int codecours,String annee,EntityManager em){
			Query query;
			Cour cr=null;
			try{
				query=em.createNamedQuery("Cours.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("annee", annee);
				query.setParameter("code",codecours);
				cr=(Cour) query.getSingleResult();
			}
			catch(Exception e){
				Repertoire.logWarn("Cours non trouvé :"+codecours, getClass());
				return null;
			}
			return cr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cour> listerCoursNonProgrammes(String codeclasse,
			String annee) {
		Query query;
		
		query=em.createNamedQuery("Cours.findNProgByClasse");
		query.setParameter("supprime", false);
		query.setParameter("codeclasse", codeclasse);
		query.setParameter("annee", annee);
		return (List<Cour>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cour> listerCoursProgrammes(String codeclasse,
			String annee) {
		Query query;
		
		query=em.createNamedQuery("Cours.findProgByClasse");
		query.setParameter("supprime", false);
		query.setParameter("codeclasse", codeclasse);
		query.setParameter("annee", annee);
		return (List<Cour>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Matiere> listerMatieresClasse(String codeclasse, String annee,
			String etablissement) {
		Query query;
		
		query=em.createNamedQuery("Matiere.findByClasse");
		query.setParameter("supprime", false);
		query.setParameter("codeclasse", codeclasse);
		query.setParameter("annee", annee);
		return (List<Matiere>) query.getResultList();
	}

}
