package ejb.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import utils.Repertoire;

import ages.exception.AnneeEnCoursNonDefinieException;
import ages.exception.ElementNOtFoundException;
import ejb.GestionProgrammationLocal;
import entities.Annee;
import entities.Cour;
import entities.Programmation;

/**
 * Session Bean implementation class GestionEleve
 * Classe de gestion des eleves
 */
@Stateless(mappedName = "GestionProgrammation")
@TransactionManagement(TransactionManagementType.BEAN)
public class GestionProgrammation implements GestionProgrammationLocal {

	@PersistenceContext(unitName="agespersist")
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public GestionProgrammation() {
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Programmation> listerProgrammations(Date datedebut, Date datefin,String annee) {
		Query query=em.createNamedQuery("Programmation.findByPeriod");
		query.setParameter("supprime", false);
		query.setParameter("annee", annee);
		query.setParameter("datedebut", datedebut);
		query.setParameter("datefin", datefin);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Programmation> listerProgrammationsCoursClasse(String codeclasse,Date datedebut,
			Date datefin, String annee) {
		Query query=em.createNamedQuery("Programmation.findCoursClasseByPeriod");
		query.setParameter("supprime", false);
		query.setParameter("annee", annee);
		query.setParameter("datedebut", datedebut);
		query.setParameter("datefin", datefin);
		query.setParameter("codeclasse", codeclasse);
		return query.getResultList();
	}

	@Override
	public void enregistrerProgrammationCours(String codeclasse, int codecours,
			Date debutlundi, Date finlundi, Date debutmardi, Date finmardi,
			Date debutmercredi, Date finmercredi, Date debutjeudi,
			Date finjeudi, Date debutvendredi, Date finvendredi,
			Date debutsamedi, Date finsamedi,String annee,String codeetablissement) throws AnneeEnCoursNonDefinieException, ElementNOtFoundException {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		Annee an=null;
		Cour c=null;
		try{
			emtrans.getTransaction().begin();
			
			an=rechercheAnnee(annee, codeetablissement, emtrans);
			
			if(an==null)
				throw new AnneeEnCoursNonDefinieException(codeetablissement);
			
			c=rechercheCour(codecours, annee, emtrans);
			if(c==null)
				throw new ElementNOtFoundException(String.valueOf(codecours), "Cour");
			
			// enregistrer programmation Lundi
			if(debutlundi!=null){
				saveProgrammationCours(Calendar.MONDAY, an,debutlundi,finlundi,c, emtrans);
			}
			
			if(debutmardi!=null){
				saveProgrammationCours(Calendar.TUESDAY, an,debutmardi,finmardi,c, emtrans);
			}
			
			if(debutmercredi!=null){
				saveProgrammationCours(Calendar.WEDNESDAY, an,debutmercredi,finmercredi,c, emtrans);
			}
			
			if(debutjeudi!=null){
				saveProgrammationCours(Calendar.THURSDAY, an,debutjeudi,finjeudi,c, emtrans);
			}
			
			if(debutvendredi!=null){
				saveProgrammationCours(Calendar.FRIDAY, an,debutvendredi,finvendredi,c, emtrans);
			}
			
			if(debutsamedi!=null){
				saveProgrammationCours(Calendar.SATURDAY, an,debutsamedi,finsamedi,c, emtrans);
			}
			
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
	}

	@Override
	public void editerProgrammationCours(String codeclasse, int codecours,
			Date debutlundi, Date finlundi, Date debutmardi, Date finmardi,
			Date debutmercredi, Date finmercredi, Date debutjeudi,
			Date finjeudi, Date debutvendredi, Date finvendredi,
			Date debutsamedi, Date finsamedi,String annee,String codeetablissement) throws AnneeEnCoursNonDefinieException, ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		Annee an=null;
		Cour c=null;
		try{
			emtrans.getTransaction().begin();
			
			an=rechercheAnnee(annee, codeetablissement, emtrans);
			
			if(an==null)
				throw new AnneeEnCoursNonDefinieException(codeetablissement);
			
			c=rechercheCour(codecours, annee, emtrans);
			if(c==null)
				throw new ElementNOtFoundException(String.valueOf(codecours), "Cour");
			
			Query query=emtrans.createQuery("delete from Programmation p where p.annee.anneeacademique='"+annee+"' and p.cours.codecours='"+codecours+"' and p.supprime='"+false+"'");
			query.executeUpdate();		
			
			
			// enregistrer programmation Lundi
			if(debutlundi!=null){
				saveProgrammationCours(Calendar.MONDAY, an,debutlundi,finlundi,c, emtrans);
			}
			
			if(debutmardi!=null){
				saveProgrammationCours(Calendar.TUESDAY, an,debutmardi,finmardi,c, emtrans);
			}
			
			if(debutmercredi!=null){
				saveProgrammationCours(Calendar.WEDNESDAY, an,debutmercredi,finmercredi,c, emtrans);
			}
			
			if(debutjeudi!=null){
				saveProgrammationCours(Calendar.THURSDAY, an,debutjeudi,finjeudi,c, emtrans);
			}
			
			if(debutvendredi!=null){
				saveProgrammationCours(Calendar.FRIDAY, an,debutvendredi,finvendredi,c, emtrans);
			}
			
			if(debutsamedi!=null){
				saveProgrammationCours(Calendar.SATURDAY, an,debutsamedi,finsamedi,c, emtrans);
			}
			
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
	}

	
	private void saveProgrammationCours(int joursemaine,Annee an,Date hdebut, Date hfin,Cour c, EntityManager em){
		
		Calendar currentdatedeb=Calendar.getInstance();
		Calendar currentdatefin=Calendar.getInstance();
		
		Calendar cal=Calendar.getInstance();
		cal.setTime(an.getDateDebut());		

		Calendar debut=Calendar.getInstance();
		debut.setTime(hdebut);
		
		Calendar fin=Calendar.getInstance();
		fin.setTime(hfin);
		
		while(cal.get(Calendar.DAY_OF_WEEK)!=joursemaine){
			cal.add(Calendar.DATE,1);
		}
		
		
		while(cal.getTime().before(an.getDateFin())){
			Programmation pro=new Programmation();
			pro.setAnnee(an);
			
			currentdatedeb.setTime(cal.getTime());
			
			currentdatedeb.set(Calendar.HOUR_OF_DAY, debut.get(Calendar.HOUR_OF_DAY));
			currentdatedeb.set(Calendar.MINUTE, debut.get(Calendar.MINUTE));
			currentdatedeb.set(Calendar.SECOND, debut.get(Calendar.SECOND));
			
			currentdatefin.setTime(cal.getTime());
			
			currentdatefin.set(Calendar.HOUR_OF_DAY, fin.get(Calendar.HOUR_OF_DAY));
			currentdatefin.set(Calendar.MINUTE, fin.get(Calendar.MINUTE));
			currentdatefin.set(Calendar.SECOND, fin.get(Calendar.SECOND));
			
			pro.setDatedebut(currentdatedeb.getTime());
			pro.setDatefin(currentdatefin.getTime());
			pro.setCours(c);
			
			pro.setLibelle(c.getLibelleCours());
			
			pro.setSupprime(false);
			em.persist(pro);
			em.flush();
			
			cal.add(Calendar.DATE,7);
		}
		
	}
	
	private Annee rechercheAnnee(String anneeacademique,String etablissement,EntityManager em) {
		Query query=em.createNamedQuery("Annee.findByCode");
		query.setParameter("supprime", false);
		query.setParameter("close", false);
		query.setParameter("annee", anneeacademique);
		
		try{
			return (Annee) query.getSingleResult();
		}
		catch(Exception e){
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Programmation> rechercherProgrammationCoursJour(int codecours,
			Date datedebut, Date datefin,String annee) {
		Query query=em.createNamedQuery("Programmation.findCoursByPeriod");
		query.setParameter("supprime", false);
		query.setParameter("annee", annee);
		query.setParameter("datedebut", datedebut);
		query.setParameter("datefin", datefin);
		query.setParameter("codecours", codecours);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Programmation> listerProgrammationsSalle(String codesalle,
			Date datedebut, Date datefin, String annee) {
			Query query=em.createNamedQuery("Programmation.findProgBySalle");
			query.setParameter("supprime", false);
			query.setParameter("annee", annee);
			query.setParameter("datedebut", datedebut);
			query.setParameter("datefin", datefin);
			query.setParameter("codesalle", codesalle);
			return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Programmation> listerProgrammationsEvt(Date datedebut,
			Date datefin, String annee) {
		Query query=em.createNamedQuery("Programmation.findProgEvt");
		query.setParameter("supprime", false);
		query.setParameter("annee", annee);
		query.setParameter("datedebut", datedebut);
		query.setParameter("datefin", datefin);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> listerTypesActivites(String annee, String etablissement) {
		Annee an=rechercheAnnee(annee, etablissement, em);
		Query query=em.createNamedQuery("Activite.findTypes");
		query.setParameter("supprime", false);
		query.setParameter("datedebut", an.getDateDebut());
		query.setParameter("datefin", an.getDateFin());
		return query.getResultList();
	}
	
}


