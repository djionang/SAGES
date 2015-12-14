/**
 * 
 */
package ejb.impl;

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
import ages.exception.ElementNOtFoundException;
import ejb.GestionPayeLocal;
import entities.Annee;
import entities.CodeDepense;
import entities.CodePrevision;
import entities.Depense;
import entities.Etablissement;
import entities.Prevision;
import entities.Transfert;
import entities.TransfertPK;
import entities.Utilisateur;

/**
 * @author Berlin
 *
 */
@Stateless(mappedName ="GestionPaye")
@TransactionManagement(TransactionManagementType.BEAN)
public class GestionPaye implements GestionPayeLocal{

	
	
	@PersistenceContext(unitName="agespersist")
	EntityManager em;
	
	@SuppressWarnings("static-access")
	@Override
	public String createPrevision(String typeprevision, float montant,
			Date dateenreg, String annee, String codeetablissement,
			int idutilisateur,String description) throws ElementNOtFoundException {
		
		
		String result=null;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		
		try{
			em2.getTransaction().begin();
			Annee an=em2.getReference(Annee.class, annee);
			if(an==null) throw new ElementNOtFoundException(annee, "Annee");

			
			Etablissement etab=em2.getReference(Etablissement.class, codeetablissement);
			if(etab==null) throw new ElementNOtFoundException(codeetablissement, "Etablissement");
			
			Utilisateur util=em2.getReference(Utilisateur.class, idutilisateur);
			if(util==null) throw new ElementNOtFoundException(toString().valueOf(idutilisateur), "Utilisateur");


			CodePrevision cd=new CodePrevision();
			em2.persist(cd);
			
			String codePrevision=Repertoire.genererCodePrevision(cd.getCode());
			Prevision pre=new Prevision();
			
			pre.setAnnee(an);
			pre.setEtab(etab);
			pre.setPersonnel(util.getPersonnel());
			pre.setCodeprevision(codePrevision);
			pre.setDateenreg(dateenreg);
			pre.setTypeprevision(typeprevision);
			pre.setTranfert("Non");
			pre.setMontant(montant);
			pre.setReste(montant);
			pre.setDescription(description);
			em2.persist(pre);
			em2.getTransaction().commit();
			result=codePrevision;			
			
		}
		finally{
			em2.close();
			emf.close();
	}
		return result;

	}


	@SuppressWarnings("static-access")
	@Override
	public String createPrevisionAjout(String codeprevision, int idprevision,
			float montant, Date dateenreg, String annee,
			String codeetablissement, int idutilisateur, String description) throws ElementNOtFoundException {
		String result=null;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		
		try{
			em2.getTransaction().begin();
			Annee an=em2.getReference(Annee.class, annee);
			if(an==null) throw new ElementNOtFoundException(annee, "Annee");

			
			Etablissement etab=em2.getReference(Etablissement.class, codeetablissement);
			if(etab==null) throw new ElementNOtFoundException(codeetablissement, "Etablissement");
			
			Utilisateur util=em2.getReference(Utilisateur.class, idutilisateur);
			if(util==null) throw new ElementNOtFoundException(toString().valueOf(idutilisateur), "Utilisateur");
			
			Prevision p=em2.getReference(Prevision.class, idprevision);
			if(p==null) throw new ElementNOtFoundException(toString().valueOf(idprevision), "Prevision");
			
			Prevision pre=new Prevision();
			
			pre.setAnnee(an);
			pre.setEtab(etab);
			pre.setPersonnel(util.getPersonnel());
			pre.setCodeprevision(codeprevision);
			pre.setDateenreg(dateenreg);
			pre.setTypeprevision(p.getTypeprevision());
			pre.setTranfert("Non");
			pre.setMontant(montant);
			pre.setReste(montant);
			pre.setDescription(description);
			em2.persist(pre);
			em2.getTransaction().commit();
			result=codeprevision;			
			
		}
		finally{
			em2.close();
			emf.close();
	}
		return result;
	}


	@SuppressWarnings("static-access")
	@Override
	public boolean modifierPrevision(String codeprevision, int idprevision,
			float montant, Date dateenreg, String annee,
			String codeetablissement, int idutilisateur, String description) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		
		try{
			em2.getTransaction().begin();
			Annee an=em2.getReference(Annee.class, annee);
			if(an==null) throw new ElementNOtFoundException(annee, "Annee");

			
			Etablissement etab=em2.getReference(Etablissement.class, codeetablissement);
			if(etab==null) throw new ElementNOtFoundException(codeetablissement, "Etablissement");
			
			Utilisateur util=em2.getReference(Utilisateur.class, idutilisateur);
			if(util==null) throw new ElementNOtFoundException(toString().valueOf(idutilisateur), "Utilisateur");
			
			Prevision p=em2.getReference(Prevision.class, idprevision);
			if(p==null) throw new ElementNOtFoundException(toString().valueOf(idprevision), "Prevision");
			
			p.setAnnee(an);
			p.setEtab(etab);
			p.setPersonnel(util.getPersonnel());
			p.setCodeprevision(codeprevision);
			p.setDateenreg(dateenreg);
			p.setTypeprevision(p.getTypeprevision());
			p.setMontant(montant);
			p.setReste(montant);
			p.setDescription(description);
			em2.merge(p);
			em2.getTransaction().commit();
			return true;			
			
		}
		finally{
			em2.close();
			emf.close();
		}

	}


	@Override
	public Prevision rechercherprevision(int idprevision,
			String annee, String codeetablissement) {
		Prevision prev=null;
		try{
			Query req=em.createNamedQuery("Prevision.find");
			req.setParameter("idprevision", idprevision);
			req.setParameter("codeetab", codeetablissement);
			req.setParameter("annee", annee);
		   prev=(Prevision) req.getSingleResult();
		}
		catch(Exception e){
			Repertoire.logWarn("prevision non trouvé ou ++ trouvés "+idprevision, this.getClass());
			e.printStackTrace();
		}
		return prev;
	}


	@SuppressWarnings("static-access")
	@Override
	public String createDepense(int idprevision, float montant, Date dateenreg,
			String typeprevision, String annee,
			String codeetablissement, int idutilisateur, String description) throws ElementNOtFoundException {
		
		String result=null;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		
		try{
			em2.getTransaction().begin();
			Annee an=em2.getReference(Annee.class, annee);
			if(an==null) throw new ElementNOtFoundException(annee, "Annee");

			
			Etablissement etab=em2.getReference(Etablissement.class, codeetablissement);
			if(etab==null) throw new ElementNOtFoundException(codeetablissement, "Etablissement");
			
			Utilisateur util=em2.getReference(Utilisateur.class, idutilisateur);
			if(util==null) throw new ElementNOtFoundException(toString().valueOf(idutilisateur), "Utilisateur");
			
			Prevision p=em2.getReference(Prevision.class, idprevision);
			if(p==null) throw new ElementNOtFoundException(toString().valueOf(idprevision), "Prevision");
			p.setReste(p.getMontant()-montant);
			em2.merge(p);
			
			CodeDepense cd=new CodeDepense();
			em2.persist(cd);
			
			String codedepense=Repertoire.genererCodeDepense(cd.getCode());
			Depense de=new Depense();
			
			de.setAnnee(an);
			de.setEtab(etab);
			de.setPersonnel(util.getPersonnel());
			de.setPrevision(p);
			de.setCodedepense(codedepense);
			de.setDateenreg(dateenreg);
			de.setMontant(montant);
			de.setTypedepense(typeprevision);
			de.setDescription(description);
			em2.persist(de);
			em2.getTransaction().commit();
			result=codedepense;			
			
		}
		finally{
			em2.close();
			emf.close();
	}
		return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<String> listeTypesPrevision() {
		Query query=em.createNamedQuery("Prevision.listTypes");
		return query.getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Prevision> listerprevision(String annee,
			String codeetablissement) {
		List<Prevision> list=null ;
		try{
			Query req=em.createNamedQuery("Prevision.findS");
			req.setParameter("annee", annee);
			req.setParameter("codeetab", codeetablissement);		
				list=req.getResultList();
			}
			catch(Exception e){
			 Repertoire.logWarn("Erreur lors du listing des prevision", this.getClass());
			}
				
		return list;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Depense> listerdepense(String annee,
			String codeetablissement) {
		List<Depense> list=null ;
		try{
			Query req=em.createNamedQuery("Depense.findS");
			req.setParameter("annee", annee);
			req.setParameter("codeetab", codeetablissement);		
				list=req.getResultList();
			}
			catch(Exception e){
			 Repertoire.logWarn("Erreur lors du listing des depense", this.getClass());
			}
		return list;		
	}


	@Override
	public Depense rechercherdepense(int iddepense, String annee,
			String codeetablissement) {
		Depense dep=null;
		try{
			Query req=em.createNamedQuery("Depense.find");
			req.setParameter("iddepense", iddepense);
			req.setParameter("codeetab", codeetablissement);
			req.setParameter("annee", annee);
		   dep=(Depense) req.getSingleResult();
		}
		catch(Exception e){
			Repertoire.logWarn("depense non trouvé ou ++ trouvés "+iddepense, this.getClass());
			e.printStackTrace();
		}
		return dep;
	}


	@SuppressWarnings("static-access")
	@Override
	public String createTransfert(String codeprevision, int idprevision,
			float montant, Date dateenreg, String annee,
			String codeetablissement, int idutilisateur, String description,
			String typeprevision2,int idprevision2) throws ElementNOtFoundException {
		String result=null;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		
		try{
			em2.getTransaction().begin();
			Annee an=em2.getReference(Annee.class, annee);
			if(an==null) throw new ElementNOtFoundException(annee, "Annee");

			
			Etablissement etab=em2.getReference(Etablissement.class, codeetablissement);
			if(etab==null) throw new ElementNOtFoundException(codeetablissement, "Etablissement");
			
			Utilisateur util=em2.getReference(Utilisateur.class, idutilisateur);
			if(util==null) throw new ElementNOtFoundException(toString().valueOf(idutilisateur), "Utilisateur");
			
			Prevision p=em2.getReference(Prevision.class, idprevision);
			if(p==null) throw new ElementNOtFoundException(toString().valueOf(idprevision), "Prevision");
			p.setReste(p.getReste()-montant);
			em2.merge(p);
			
			Prevision p2=em2.getReference(Prevision.class, idprevision2);
			if(p2==null) throw new ElementNOtFoundException(toString().valueOf(idprevision2), "Prevision");
			
			TransfertPK trpk = new TransfertPK();
			trpk.setAnneeacademique(an.getAnneeacademique());
			trpk.setCodeprevision1(p.getIdprevision());
			trpk.setCodeprevision2(p2.getIdprevision());
			
			Transfert tr = new Transfert();
			tr.setIdtranfert(trpk);
			tr.setDatetransfert(dateenreg);
			tr.setEtab(etab);
			tr.setAnnee(an);
			tr.setMontant(montant);
			tr.setPersonnel(util.getPersonnel());
			tr.setSupprime(false);
			em2.persist(tr);
			
			Prevision pre=new Prevision();
			
			pre.setAnnee(an);
			pre.setEtab(etab);
			pre.setPersonnel(util.getPersonnel());
			pre.setCodeprevision(p2.getCodeprevision());
			pre.setDateenreg(dateenreg);
			pre.setTypeprevision(p2.getTypeprevision());
			pre.setTranfert("OUI");
			pre.setMontant(montant);
			pre.setReste(montant);
			pre.setDescription(description);
			em2.persist(pre);
			em2.getTransaction().commit();
			result=codeprevision;			
			
		}
		finally{
			em2.close();
			emf.close();
	}
		return result;
	}
}
