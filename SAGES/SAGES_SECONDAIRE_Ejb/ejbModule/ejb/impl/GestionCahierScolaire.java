/**
 * 
 */
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

import utils.Repertoire;

import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;

import ejb.GestionCahierScolaireLocal;
import entities.Annee;
import entities.CahierCours;
import entities.CahierCoursPK;
import entities.CahierDeTexte;
import entities.Cour;
import entities.PartieCour;
import entities.PlanificationAnnuelle;

/**
 * @author Administrateur
 *
 */

@Stateless(mappedName="GestionCahierScolaire")
@TransactionManagement(TransactionManagementType.BEAN)
public class GestionCahierScolaire implements GestionCahierScolaireLocal{
	
	@PersistenceContext(unitName="agespersist")
	EntityManager em;

	
	@Override
	public List<PartieCour> listerPartieCoursEnseignant(String codeenseignant) {
		return null;
	}

	@Override
	public CahierDeTexte rechercherCdt(int codecdt,String annee) {
		return rechercheCahierTexte(codecdt, annee, em);
	}

	@Override
	public PartieCour rechercherPartieCours(int codepartie) {
		return recherchePartieCour(codepartie, em);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartieCour> listerPartiesCours(int codecours) {
		Query query=em.createNamedQuery("PartieCour.findAll");
		query.setParameter("supprime", false);
		return query.getResultList();
	}

	@Override
	public int creerCahierTexte(List<Integer> selectedchapitres,
			String libelle, String resume, Date datejour, Date heuredebut,
			Date heurefin, String annee) throws DuplicateKeyException, ElementNOtFoundException {
		EntityManagerFactory emf =Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2 = emf.createEntityManager();
		int codec;
		Cour cr=null;
		try{
			em2.getTransaction().begin();
			
			Annee an=rechercheAnnee(annee, em2);
			if(an==null)
				throw new ElementNOtFoundException(annee, "Annee");
			
			CahierDeTexte cdt = new CahierDeTexte();
			cdt.setObjectifs(libelle);
			cdt.setResume(resume);			

			cdt.setAnnee(an);
			
			cdt.setHeuredebut(new java.sql.Time(heuredebut.getTime()));
			cdt.setHeurefin(new java.sql.Time(heurefin.getTime()));
			cdt.setObjectifs(libelle);
			cdt.setResume(resume);
			cdt.setDatetravail(datejour);
			cdt.setSupprime(false);
			em2.persist(cdt);
			em2.flush();
			int i=0;
			
			for(int idp:selectedchapitres){
				if(i==0){
					cr=creerCahierCours(cdt, idp, em2).getPartieCour().getCour();
					i++;
				}
				em2.persist(creerCahierCours(cdt, idp, em2));
			}
			
			cdt.setCours(cr);
			em2.merge(cdt);
			em2.flush();
		    em2.getTransaction().commit();
		    codec=cdt.getCodetexte();
			
		}finally{
			em2.close();
			emf.close();
		}
		return codec;
	}

	@Override
	public void modifierCahierTexte(int codecdt,
			List<Integer> selectedchapitres, String libelle, String resume,
			Date datejour, Date heuredebut, Date heurefin, String annee) throws ElementNOtFoundException {
		EntityManagerFactory emf =Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2 = emf.createEntityManager();
		Cour cr=null;
		Query query;
		
		try{
			em2.getTransaction().begin();
			
			CahierDeTexte cdt=rechercheCahierTexte(codecdt,annee,em2);
			if(cdt==null) throw (new ElementNOtFoundException(String.valueOf(codecdt), "Cahier De Texte"));
		
			Annee an=rechercheAnnee(annee, em2);
			if(an==null)
				throw new ElementNOtFoundException(annee, "Annee");
			
			cdt.setAnnee(an);
			
			
			cdt.setHeuredebut(new java.sql.Time(heuredebut.getTime()));
			cdt.setHeurefin(new java.sql.Time(heurefin.getTime()));
			cdt.setObjectifs(libelle);
			cdt.setResume(resume);
			cdt.setDatetravail(datejour);
			cdt.setCahierCours(new ArrayList<CahierCours>());
			cdt.setSupprime(false);
			em2.merge(cdt);
			em2.flush();
			int i=0;
			
			query=em2.createQuery("delete from CahierCours cc where cc.cahierDeTexte.codetexte='"+codecdt+"'");
			query.executeUpdate();
			
			for(int idp:selectedchapitres){
				if(i==0){
					cr=creerCahierCours(cdt, idp, em2).getPartieCour().getCour();
					i++;
				}
				em2.persist(creerCahierCours(cdt, idp, em2));
			}
			
			cdt.setCours(cr);
			em2.merge(cdt);
			em2.flush();
		    em2.getTransaction().commit();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
		
			em2.close();
			emf.close();
		}

	}

	@Override
	public void supprimerCahierTexte(int codetexte,String annee) throws ElementNOtFoundException {
		EntityManagerFactory emf =Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2 = emf.createEntityManager();
		
		try{
			em2.getTransaction().begin();
			
			CahierDeTexte cdt=rechercheCahierTexte(codetexte,annee, em2);
			if(cdt==null) throw (new ElementNOtFoundException(String.valueOf(codetexte), "Cahier De Texte"));
		
			cdt.setSupprime(true);
			em2.merge(cdt);
		    em2.getTransaction().commit();
			
		}finally{
			em2.close();
			emf.close();
		}
	}

	@Override
	public void modifierChapitre(int codepartie, String libelle,
			String description, Date datedebut, Date datefin, int codecours,
			String annee) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		PartieCour pt=null;
		Cour c=null;
		PlanificationAnnuelle plan;
		PlanificationAnnuelle planaux=null;
		try{
			emtrans.getTransaction().begin();
			pt=recherchePartieCour(codepartie, emtrans);
			if(pt==null)
				throw new ElementNOtFoundException(String.valueOf(codepartie), "PartieCour");
			
			if(codecours!=0){
				c=rechercheCour(codecours, annee, emtrans);
				if(c==null)
					throw new ElementNOtFoundException(String.valueOf(codecours), "Cour");
				pt.setCour(c);
			}
			
			
			
			pt.setDescription(description);
			pt.setLibelle(libelle);
			pt.setSupprime(false);
			
			if(!pt.getPlanificationAnnuelle().getDatedebutplanification().equals(datedebut)|| !pt.getPlanificationAnnuelle().getDatefinplanification().equals(datefin)){
				planaux=pt.getPlanificationAnnuelle();
				
				plan=new PlanificationAnnuelle();
				plan.setDatedebutplanification(datedebut);
				plan.setDatefinplanification(datefin);
				plan.setSupprime(false);
				emtrans.persist(plan);
				emtrans.flush();
				
				pt.setPlanificationAnnuelle(plan);
			}
			
			emtrans.merge(pt);
			if(planaux!=null)
				emtrans.remove(planaux);
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
	}

	@Override
	public void supprimerChapitre(int codepartie, String anneeAcEncours)
			throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		PartieCour pt=null;
		try{
			emtrans.getTransaction().begin();
			pt=recherchePartieCour(codepartie, emtrans);
			if(pt==null)
				throw new ElementNOtFoundException(String.valueOf(codepartie), "PartieCour");
			
			pt.setSupprime(true);
			emtrans.merge(pt);
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
	}
	
	
	private PartieCour recherchePartieCour(int codepartie, EntityManager em){
		Query query;
		try{
			query=em.createNamedQuery("PartieCour.findByCode");
			query.setParameter("supprime",false);
			query.setParameter("codepartie", codepartie);
			return (PartieCour) query.getSingleResult();
		}
		catch(NoResultException e){
			return null;
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
	

	private PartieCour recherchePartieCour(String libellepartie,int codecours,EntityManager em){
		Query query;
		PartieCour pcr=null;
		try{
			query=em.createNamedQuery("Cours.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("libelle", libellepartie);
			query.setParameter("codecours",codecours);
			pcr=(PartieCour) query.getSingleResult();
		}
		catch(Exception e){
			Repertoire.logWarn("Partie Cours non trouvé :"+libellepartie, getClass());
			return null;
		}
		return pcr;
	}
	
	private CahierDeTexte rechercheCahierTexte(int codecahier,String annee,EntityManager em){
		Query query;
		CahierDeTexte text=null;
		try{
			query=em.createNamedQuery("CahierDeTexte.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("codetexte", codecahier);
			query.setParameter("annee",annee);
			text=(CahierDeTexte) query.getSingleResult();
		}
		catch(Exception e){
			Repertoire.logWarn("Cahier de texte non trouvé :"+String.valueOf(codecahier), getClass());
			return null;
		}
		return text;
}

	
	private CahierCours creerCahierCours(CahierDeTexte cdt, int codepartie,EntityManager em){
		PartieCour pt=recherchePartieCour(codepartie, em);
		if(pt==null)
			return null;
		
		CahierCours cc=new CahierCours();
		CahierCoursPK id=new CahierCoursPK();
		id.setCodepartie(codepartie);
		id.setCodetexte(cdt.getCodetexte());
		cc.setId(id);
		cc.setCahierDeTexte(cdt);
		cc.setPartieCour(pt);
		cc.setSupprime(false);
		return cc;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<CahierDeTexte> listerCdt(int codecours,String annee) {
		Query query=em.createNamedQuery("CahierDeTexte.findAll");
		query.setParameter("supprime", false);
		query.setParameter("annee", annee);
		return query.getResultList();
	}
	
	private Annee rechercheAnnee(String annee, EntityManager em){
		Query query;
		try{
			query=em.createNamedQuery("Annee.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("close", false);
			query.setParameter("annee", annee);
			return (Annee) query.getSingleResult();
		}
		catch(NoResultException e){
			Repertoire.logWarn("Annee non trouvée :"+annee, getClass());
			return null;
		}
	}

	@Override
	public void creerChapitre(String libelle,
			String description, int codecours, Date datedebut,
			Date datefin,String annee) throws DuplicateKeyException, ElementNOtFoundException {
		EntityManagerFactory emf =Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2 = emf.createEntityManager();
				
		try{
			em2.getTransaction().begin();
			
			PartieCour pc=recherchePartieCour(description, codecours, em2);
			if(pc!=null)
				throw new DuplicateKeyException("Partie cours "+libelle+" Dupliqué");
			
			PlanificationAnnuelle pa=new PlanificationAnnuelle();
			pa.setDatedebutplanification(datedebut);
			pa.setDatefinplanification(datefin);
			pa.setSupprime(false);

			em2.persist(pa);
			
			
			Cour cr=rechercheCour(codecours, annee, em2);
			if(cr==null) 
				throw new ElementNOtFoundException(String.valueOf(codecours),"Cours");

			PartieCour pcr = new PartieCour();
			pcr.setCour(cr);
			pcr.setDescription(description);
			pcr.setLibelle(libelle);
			pcr.setSupprime(false);
			pcr.setPlanificationAnnuelle(pa);
			em2.persist(pcr);
		    em2.getTransaction().commit();
			
		}finally{
			em2.close();
			emf.close();
		}
	}

	@Override
	public int rechercherCodeCours(int codepartie) {
		try{
			Query query=em.createQuery("select p.cour.codecours from PartieCour p where p.supprime="+false+" and p.codepartie="+codepartie);
			return (Integer) query.getSingleResult();
		}
		catch(Exception e){
			return 0;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> listerCodesChapitresCdt(int codecdt) {
		Query query=em.createQuery("select part.codepartie from CahierDeTexte cdt join cdt.cahierCours cc join cc.partieCour part where part.supprime="+false+" and cdt.supprime="+false+" and cdt.codetexte="+codecdt);
		return query.getResultList();
	}

}
