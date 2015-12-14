package ejb.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;

import utils.Repertoire;

import ejb.GestionDisciplineLocal;
import entities.Absence;
import entities.Annee;
import entities.Convocation;
import entities.Eleve;
import entities.Retard;
import entities.Sanction;
import entities.TypeSanction;

/**
 * Session Bean implementation class GestionEleve
 * Classe de gestion des eleves
 */
@Stateless(mappedName = "GestionDiscipline")
@TransactionManagement(TransactionManagementType.BEAN)
public class GestionDiscipline implements GestionDisciplineLocal {

	@PersistenceContext(unitName="agespersist")
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public GestionDiscipline() {
    }

	@Override
	public void saveTypeSanction(String libelle, String description) throws DuplicateKeyException {
		EntityManagerFactory emfact= Persistence.createEntityManagerFactory("agespersist");
		EntityManager em=emfact.createEntityManager();		
		
		try{
			em.getTransaction().begin();
			
			Query query=em.createNamedQuery("TypeSanction.findBylibelle");
			query.setParameter("supprime", false);
			query.setParameter("libelle",libelle);
			
			TypeSanction tps=null;
			try{
				tps= (TypeSanction) query.getSingleResult();
			}
			catch(NoResultException e1){
				Repertoire.logDebug("Type Sanction de code "+libelle+" non trouvé, suppression impossible", getClass());
			}
			catch(NonUniqueResultException e2){
				Repertoire.logDebug("Recherche Sanction de code "+libelle+" ++ trouvée, Erreur de coherence de la BD", getClass());
				tps=new TypeSanction();
			}
			
			if(tps!=null)
				throw new DuplicateKeyException("TypeSanction= "+libelle+" Existant");
			tps=new TypeSanction();
			tps.setDescription(description);
			tps.setLibelle(libelle);
			tps.setSupprime(false);
			
			em.persist(tps);
			em.getTransaction().commit();
			
		}
		finally{
			em.close();
			emfact.close();
		}
	}

	@Override
	public TypeSanction rechercherTypeSanction(int id) {
		Query query=em.createNamedQuery("TypeSanction.findByCode");
		query.setParameter("supprime", false);
		query.setParameter("id", id);
		TypeSanction tps=null;
		try{
			tps= (TypeSanction) query.getSingleResult();
		}
		catch(NoResultException e1){
			Repertoire.logDebug("Type Sanction de code "+id+" non trouvé, suppression impossible", getClass());
		}
		catch(NonUniqueResultException e2){
			Repertoire.logDebug("Type Sanction de code "+id+" ++ trouvé, Erreur de coherence de la BD", getClass());
		}
		
		return tps;
	}

	@Override
	public void modifierTypeSanction(int id, String libelle, String description) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			Query query=emtrans.createNamedQuery("TypeSanction.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("id", id);
			
			TypeSanction tps=null;
			try{
				tps= (TypeSanction) query.getSingleResult();
			}
			catch(NoResultException e1){
				Repertoire.logDebug("Type Sanction de code "+id+" non trouvé, suppression impossible", getClass());
			}
			catch(NonUniqueResultException e2){
				Repertoire.logDebug("Recherche Sanction de code "+id+" ++ trouvée, Erreur de coherence de la BD", getClass());
				return;
			}
			tps.setDescription(description);
			tps.setLibelle(libelle);
			tps.setSupprime(false);
			emtrans.merge(tps);
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
	}

	@Override
	public void supprimerTypeSanction(int id) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			Query query=emtrans.createNamedQuery("TypeSanction.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("id", id);
			TypeSanction tps=null;
			try{
				tps= (TypeSanction) query.getSingleResult();
			}
			catch(NoResultException e1){
				Repertoire.logDebug("Type Sanction de code "+id+" non trouvé, suppression impossible", getClass());
			}
			catch(NonUniqueResultException e2){
				Repertoire.logDebug("Recherche Sanction de code "+id+" ++ trouvée, Erreur de coherence de la BD", getClass());
				return;
			}
			
			if(tps==null) throw new ElementNOtFoundException(String.valueOf(id), "typeSanction");
			
			tps.setSupprime(true);
			emtrans.merge(tps);
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeSanction> listerTypeSanctions() {
		Query query=em.createNamedQuery("TypeSanction.findAll");
		query.setParameter("supprime", false);
		return (List<TypeSanction>)query.getResultList();
	}

	@Override
	public void enregistrerSanction(String motif, int codetype, int duree,
			Date datedecision, Date dateeffet, List<String> elevescibles,
			String annee) throws ElementNOtFoundException {
		EntityManagerFactory emfact= Persistence.createEntityManagerFactory("agespersist");
		EntityManager em=emfact.createEntityManager();	
		TypeSanction tps=null;
		List<Sanction> sanctions =new ArrayList<Sanction>();
		Calendar cal=Calendar.getInstance();
		Query query;
		try{
			em.getTransaction().begin();
			
			try{
				query=em.createNamedQuery("TypeSanction.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("id",codetype);			
			
				tps= (TypeSanction) query.getSingleResult();
			}
			catch(NoResultException e1){
				throw new ElementNOtFoundException(String.valueOf(codetype), "Typesanction");
			}
			catch(NonUniqueResultException e2){
				Repertoire.logDebug("Recherche Type Sanction de code "+codetype+" ++ trouvés, Erreur de coherence de la BD", getClass());
				throw e2;
			}
			
			cal.setTime(dateeffet);
			
			for(int i=0;i<elevescibles.size();i++){
				
				sanctions.add(new Sanction());
				sanctions.get(i).setAnnule(false);
				sanctions.get(i).setDatedecision(datedecision);
				sanctions.get(i).setDureesanction(duree);
				sanctions.get(i).setDateeffet(cal);
				sanctions.get(i).setMotif(motif);
				sanctions.get(i).setTypesanction(tps);
				sanctions.get(i).setEleve(rechercheEleve(elevescibles.get(i), annee, em));
				em.persist(sanctions.get(i));
			}

			em.getTransaction().commit();
			
		}
		finally{
			em.close();
			emfact.close();
		}
	}
	
	private Eleve rechercheEleve(String matricule, String annee,EntityManager em){
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
	
	private Annee rechercheAnnee(String annee,EntityManager em){
		Annee an;
		try{
			Query req=em.createNamedQuery("Annee.findByCode");
			req.setParameter("annee", annee);
			req.setParameter("supprime", false);
			req.setParameter("close", false);
			
		    an=(Annee) req.getSingleResult();
		}
		catch(NoResultException e){
			an=null;
		}
		return an;
	}

	@Override
	public void modifierSanction(int idsanction, String motif, int codetype,
			int duree, Date datedecision, Date dateeffet, boolean annule,String anneeAcEncours) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		Sanction s=null;
		Query query;
		Calendar cal=Calendar.getInstance();
		TypeSanction tps;
		try{
			emtrans.getTransaction().begin();			
			
			try{
				query=emtrans.createNamedQuery("Sanction.findByCode");
			
				query.setParameter("id", idsanction);			
			
				s= (Sanction) query.getSingleResult();
			}
			catch(NoResultException e1){
				Repertoire.logDebug("Sanction de code "+idsanction+" non trouvé, suppression impossible", getClass());
			}
			catch(NonUniqueResultException e2){
				Repertoire.logDebug("Recherche Sanction de code "+idsanction+" ++ trouvée, Erreur de coherence de la BD", getClass());
				return;
			}
			
			try{
				query=em.createNamedQuery("TypeSanction.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("id",codetype);			
			
				tps= (TypeSanction) query.getSingleResult();
			}
			catch(NoResultException e1){
				throw new ElementNOtFoundException(String.valueOf(codetype), "Typesanction");
			}
			catch(NonUniqueResultException e2){
				Repertoire.logDebug("Recherche Type Sanction de code "+codetype+" ++ trouvés, Erreur de coherence de la BD", getClass());
				throw e2;
			}
			
			cal.setTime(dateeffet);
			
			s.setDatedecision(datedecision);
			s.setDateeffet(cal);
			s.setDureesanction(duree);
			s.setMotif(motif);
			s.setTypesanction(tps);
			s.setAnnule(annule);
			emtrans.merge(s);
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
	}

	@Override
	public void annulerSanction(int idsanction) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		Sanction s=null;
		Query query;
		try{
			emtrans.getTransaction().begin();
			
			
			try{
				query=emtrans.createNamedQuery("Sanction.findByCode");
			
				query.setParameter("id", idsanction);			
			
				s= (Sanction) query.getSingleResult();
			}
			catch(NoResultException e1){
				Repertoire.logDebug("Sanction de code "+idsanction+" non trouvé, suppression impossible", getClass());
			}
			catch(NonUniqueResultException e2){
				Repertoire.logDebug("Recherche Sanction de code "+idsanction+" ++ trouvée, Erreur de coherence de la BD", getClass());
				return;
			}
			s.setAnnule(true);
			emtrans.merge(s);
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sanction> listerSanctions(String annee) {
		Query query=em.createNamedQuery("Sanction.findAll");
		query.setParameter("annee", annee);
		return (List<Sanction>)query.getResultList();
	}

	@Override
	public Sanction rechercherSanction(int idsanction) {
		Query query;
		Sanction s=null;
		try{
			query=em.createNamedQuery("Sanction.findByCode");
			query.setParameter("id", idsanction);
				
			s= (Sanction) query.getSingleResult();
		}
		catch(NoResultException e1){
			Repertoire.logDebug("Sanction de code "+idsanction+" non trouvé, suppression impossible", getClass());
		}
		catch(NonUniqueResultException e2){
			Repertoire.logDebug("Sanction de code "+idsanction+" ++ trouvé, Erreur de coherence de la BD", getClass());
		}
		
		return s;
	}

	@Override
	public void enregistrerAbsence(Date dateabsence, int justifie,int duree, 
			String matriculeeleve, String annee) throws ElementNOtFoundException {
		EntityManagerFactory emfact= Persistence.createEntityManagerFactory("agespersist");
		EntityManager em=emfact.createEntityManager();		
		
		Eleve eleve;
		Absence abs;
		try{
			em.getTransaction().begin();
			
			eleve=rechercheEleve(matriculeeleve, annee, em);
			if(eleve==null){
				throw new ElementNOtFoundException(matriculeeleve, "Eleve");
			}
			
			abs=new Absence();
			abs.setDateabsence(dateabsence);
			abs.setDuree(duree);
			abs.setEleve(eleve);
			abs.setJustifie(justifie);
			abs.setSupprime(false);
			
			em.persist(abs);
			em.getTransaction().commit();
			
		}
		finally{
			em.close();
			emfact.close();
		}
	}

	@Override
	public void modifierAbsence(int codeabsence, Date dateabsence, int duree,
			int justifie) throws ElementNOtFoundException {
		EntityManagerFactory emfact= Persistence.createEntityManagerFactory("agespersist");
		EntityManager em=emfact.createEntityManager();		
		Query query;
		Absence ab;
		try{
			em.getTransaction().begin();
			
			try{
				query=em.createNamedQuery("Absence.fingById");
				query.setParameter("code", codeabsence);
				query.setParameter("supprime", false);
					
				ab= (Absence) query.getSingleResult();
			}
			catch(NoResultException e1){
				throw new ElementNOtFoundException(String.valueOf(codeabsence), "Absence");
			}	
			
			ab.setDateabsence(dateabsence);
			ab.setDuree(duree);
			ab.setJustifie(justifie);
			ab.setSupprime(false);
			
			em.merge(ab);
			em.getTransaction().commit();
			
		}
		finally{
			em.close();
			emfact.close();
		}
	}

	@Override
	public void supprimerAbsence(int codeabsence, String anneeAcEncours) throws ElementNOtFoundException {
		EntityManagerFactory emfact= Persistence.createEntityManagerFactory("agespersist");
		EntityManager em=emfact.createEntityManager();		
		Query query;
		Absence ab;
		try{
			em.getTransaction().begin();
			
			try{
				query=em.createNamedQuery("Absence.fingById");
				query.setParameter("code", codeabsence);
				query.setParameter("supprime", false);
					
				ab= (Absence) query.getSingleResult();
			}
			catch(NoResultException e1){
				throw new ElementNOtFoundException(String.valueOf(codeabsence), "Absence");
			}	
			
			ab.setSupprime(false);
			
			em.merge(ab);
			em.getTransaction().commit();
			
		}
		finally{
			em.close();
			emfact.close();
		}
	}

	@Override
	public void enregistrerRetard(Date dateretard, int duree, int justifie,
			String matriculeeleve, String annee) throws ElementNOtFoundException {
		EntityManagerFactory emfact= Persistence.createEntityManagerFactory("agespersist");
		EntityManager em=emfact.createEntityManager();		
		Annee anne;
		Eleve eleve;
		Retard rt;
		try{
			em.getTransaction().begin();
			
			eleve=rechercheEleve(matriculeeleve, annee, em);
			if(eleve==null){
				throw new ElementNOtFoundException(matriculeeleve, "Eleve");
			}
			
			anne=rechercheAnnee(annee, em);
			if(annee==null)
				throw new ElementNOtFoundException(annee, "Annee");
			
			rt=new Retard();
			rt.setDateretard(dateretard);
			rt.setDureeretard(duree);
			rt.setEleve(eleve);
			rt.setJustifie(justifie);
			rt.setAnnee(anne);
			rt.setSupprime(false);
			
			em.persist(rt);
			em.getTransaction().commit();
			
		}
		finally{
			em.close();
			emfact.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Retard> listerRetardsClasse(String codeclasse,
			String annee) {
		Query query=em.createNamedQuery("Retard.fingByClasse");
		query.setParameter("supprime", false);
		query.setParameter("annee", annee);
		query.setParameter("classe", codeclasse);
		return (List<Retard>)query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Retard> listerRetardsEleve(String matriculeeleve,
			String annee,Date datedebut, Date datefin) {
		Query query=em.createNamedQuery("Retard.fingByEleve");
		query.setParameter("supprime", false);
		query.setParameter("annee", annee);
		query.setParameter("matricule", matriculeeleve);
		query.setParameter("datedebut", datedebut,TemporalType.DATE);
		query.setParameter("datefin", datefin,TemporalType.DATE);
		return (List<Retard>)query.getResultList();
	}

	@Override
	public Retard rechercherRetard(int coderetard) {
		Query query;
		Retard r=null;
		try{
			query=em.createNamedQuery("Retard.fingById");
			query.setParameter("code", coderetard);
			query.setParameter("supprime", false);
				
			r= (Retard) query.getSingleResult();
		}
		catch(NoResultException e1){
			
		}		
		
		return r;
	}

	@Override
	public void supprimerRetard(int coderetard) throws ElementNOtFoundException {
		EntityManagerFactory emfact= Persistence.createEntityManagerFactory("agespersist");
		EntityManager em=emfact.createEntityManager();		
		Query query;
		Retard rt;
		try{
			em.getTransaction().begin();
			
			try{
				query=em.createNamedQuery("Retard.fingById");
				query.setParameter("code", coderetard);
				query.setParameter("supprime", false);
					
				rt= (Retard) query.getSingleResult();
			}
			catch(NoResultException e1){
				throw new ElementNOtFoundException(String.valueOf(coderetard), "Retard");
			}	
			
			rt.setSupprime(true);
			
			em.merge(rt);
			em.getTransaction().commit();
			
		}
		finally{
			em.close();
			emfact.close();
		}
	}

	@Override
	public void modifierRetard(int coderetard, Date dateretard, int duree,
			int justifie) throws ElementNOtFoundException {
		EntityManagerFactory emfact= Persistence.createEntityManagerFactory("agespersist");
		EntityManager em=emfact.createEntityManager();		
		Query query;
		Retard rt;
		try{
			em.getTransaction().begin();
			
			try{
				query=em.createNamedQuery("Retard.fingById");
				query.setParameter("code", coderetard);
				query.setParameter("supprime", false);
					
				rt= (Retard) query.getSingleResult();
			}
			catch(NoResultException e1){
				throw new ElementNOtFoundException(String.valueOf(coderetard), "Retard");
			}	
			
			rt.setDateretard(dateretard);
			rt.setDureeretard(duree);
			rt.setJustifie(justifie);
			rt.setSupprime(false);
			
			em.merge(rt);
			em.getTransaction().commit();
			
		}
		finally{
			em.close();
			emfact.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Absence> listerAbsencesEleve(String matriculeeleve,
			String annee,Date datedebut, Date datefin) {
		Query query=em.createNamedQuery("Absence.fingByEleve");
		query.setParameter("supprime", false);
		query.setParameter("annee", annee);
		query.setParameter("matricule", matriculeeleve);
		query.setParameter("datedebut", datedebut,TemporalType.DATE);
		query.setParameter("datefin", datefin,TemporalType.DATE);
		return (List<Absence>)query.getResultList();
	}

@SuppressWarnings("unchecked")
	@Override
	public List<Absence> listerAbsencesClasse(String codeclasse,
			String annee) {
		Query query=em.createNamedQuery("Absence.fingByClasse");
		query.setParameter("supprime", false);
		query.setParameter("annee", annee);
		query.setParameter("classe", codeclasse);
		return (List<Absence>)query.getResultList();
	}

	@Override
	public Absence rechercheAbsence(int codeabsence) {
		Query query;
		Absence a=null;
		try{
			query=em.createNamedQuery("Absence.fingById");
			query.setParameter("code", codeabsence);
			query.setParameter("supprime", false);
				
			a= (Absence) query.getSingleResult();
		}
		catch(NoResultException e1){
			
		}		
		
		return a;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Sanction> listerSanctionsEleve(String matriculeeleve,
			String annee,Date datedebut, Date datefin) {
		
		Query query;
			query=em.createNamedQuery("Sanction.findByEleve");
			query.setParameter("matricule", matriculeeleve);
			query.setParameter("annule", false);
			query.setParameter("datedebut", datedebut,TemporalType.DATE);
			query.setParameter("datefin", datefin,TemporalType.DATE);
			return query.getResultList();
	
	}

	@Override
	public Convocation rechercherConvocation(int idconvocation,String annee) {
		return rechercherConvocation(idconvocation, em, annee);
	}

	@Override
	public void enregistrerConvocations(String libelle, String description,
			Date datedelivrance, Date dateeffet, List<String> elevescibles,String annee) throws ElementNOtFoundException {
		EntityManagerFactory emfact= Persistence.createEntityManagerFactory("agespersist");
		EntityManager em=emfact.createEntityManager();		
		Annee anne;
		Eleve eleve;

		Convocation conv;
		try{
			em.getTransaction().begin();
			
			for(int i=0;i<elevescibles.size();i++){
				eleve=rechercheEleve(elevescibles.get(i), annee, em);
				if(eleve==null){
					throw new ElementNOtFoundException(elevescibles.get(i), "Eleve");
				}
				
				anne=rechercheAnnee(annee, em);
				if(annee==null)
					throw new ElementNOtFoundException(annee, "Annee");
				
				conv=new Convocation();
				conv.setAnnee(anne);
				conv.setDatedelivrance(datedelivrance);
				conv.setDaterendezvous(dateeffet);
				conv.setDescription(description);
				conv.setLibelle(libelle);
				conv.setSupprime(false);
				conv.setEleve(eleve);
				
				
				em.persist(conv);
				em.flush();
			}
			
			
			em.getTransaction().commit();
			
		}
		finally{
			em.close();
			emfact.close();
		}
	}

	@Override
	public void modifierConvocation(int idconvocation, String libelle,String description,
			Date datedelivrance, Date dateeffet, String matriculeeleve,String annee) throws ElementNOtFoundException {
		EntityManagerFactory emfact= Persistence.createEntityManagerFactory("agespersist");
		EntityManager em=emfact.createEntityManager();		
		Annee anne;
		Eleve eleve;

		Convocation conv;
		try{
			em.getTransaction().begin();
				
				conv=rechercherConvocation(idconvocation,em,annee);
				if(conv==null)
					throw new ElementNOtFoundException(String.valueOf(idconvocation),"Convocation");
				
				eleve=rechercheEleve(matriculeeleve, annee, em);
				if(eleve==null){
					throw new ElementNOtFoundException(matriculeeleve, "Eleve");
				}
				
				anne=rechercheAnnee(annee, em);
				if(annee==null)
					throw new ElementNOtFoundException(annee, "Annee");
				
				conv.setAnnee(anne);
				conv.setDatedelivrance(datedelivrance);
				conv.setDaterendezvous(dateeffet);
				conv.setLibelle(libelle);
				conv.setSupprime(false);
				conv.setEleve(eleve);
				conv.setDescription(description);
				
				em.merge(conv);
				em.flush();
			
			em.getTransaction().commit();
			
		}
		finally{
			em.close();
			emfact.close();
		}
	}

	private Convocation rechercherConvocation(int idconvocation,
			EntityManager em,String annee) {
		try{
			Query query=em.createNamedQuery("Convocation.findById");
			query.setParameter("supprime", false);
			query.setParameter("annee", annee);
			query.setParameter("idconvocation", idconvocation);
			return (Convocation) query.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Convocation> listerConvocations(String annee) {
		Query query=em.createNamedQuery("Convocation.findAllThisYear");
		query.setParameter("supprime", false);
		query.setParameter("annee", annee);
		return query.getResultList();
	}

	@Override
	public void supprimerConvocation(int idconvocation,String annee) throws ElementNOtFoundException {
		EntityManagerFactory emfact= Persistence.createEntityManagerFactory("agespersist");
		EntityManager em=emfact.createEntityManager();	

		Convocation conv;
		try{
			em.getTransaction().begin();
				
			conv=rechercherConvocation(idconvocation,em,annee);
			if(conv==null)
				throw new ElementNOtFoundException(String.valueOf(idconvocation),"Convocation");
			
			conv.setSupprime(true);				
			
			em.merge(conv);
			em.flush();
			
			em.getTransaction().commit();
			
		}
		finally{
			em.close();
			emfact.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Convocation> listerConvocations(Date datedebut, Date datefin) {
		Query query=em.createNamedQuery("Convocation.findAllforPeriod");
		query.setParameter("supprime", false);
		query.setParameter("datedebut", datedebut,TemporalType.TIMESTAMP);
		query.setParameter("datefin", datefin,TemporalType.TIMESTAMP);
		return query.getResultList();
	}

}


