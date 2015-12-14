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
import ejb.GestionMaterielLocal;
import entities.Annee;
import entities.CodeMateriel;
import entities.Etablissement;
import entities.Materiel;
import entities.SortieM;

/**
 * Classe GestionMateriel: de gestion du materiels
 * Gestion du materiels
 * @author Berlin
 *
 */

@Stateless(mappedName ="GestionMateriel")
@TransactionManagement(TransactionManagementType.BEAN)
public class GestionMateriel implements GestionMaterielLocal {

	@PersistenceContext(unitName="agespersist")
	EntityManager em;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Materiel> listermateriel(String annee) {
		// TODO Auto-generated method stub
		List<Materiel> list=null ;
		try{
			System.out.println("l'anne est "+ annee);
			Query req=em.createNamedQuery("Materiel.findAll");
					
				list=req.getResultList();
			}
			catch(Exception e){
			 Repertoire.logWarn("Erreur lors du listing du materiel", this.getClass());
			}
				
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SortieM> listersortie(String annee) {
		// TODO Auto-generated method stub
				List<SortieM> list=null ;
				try{
					Query req=em.createNamedQuery("SortieM.findAll");
					req.setParameter("annee", annee);
							
						list=req.getResultList();
					}
					catch(Exception e){
					 Repertoire.logWarn("Erreur lors du listing du materiels sortie", this.getClass());
					}
						
				return list;
	}

	@Override
	public String createMateriel(String designation, float prix, int quantite,
			String typemateriel, String etat,int quantiteF, String annee,
			String codeetablissement,Date dateenreg,String numeroserie) throws ElementNOtFoundException {
		String result=null;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		
		try{
			em2.getTransaction().begin();
			Annee an=em2.getReference(Annee.class, annee);
			if(an==null) throw new ElementNOtFoundException(annee, "Annee");
			
			System.out.println("l'anne est "+ annee);
			
			Etablissement etab=em2.getReference(Etablissement.class, codeetablissement);
			if(etab==null) throw new ElementNOtFoundException(codeetablissement, "Etablissement");

			CodeMateriel cd=new CodeMateriel();
			em2.persist(cd);
			
			String codeMateriel=Repertoire.genererCodeMateriel(cd.getCode());
			Materiel mat=new Materiel();
			
			mat.setAnnee(an);
			mat.setEtablissement(etab);
			mat.setCodemateriel(codeMateriel);
			mat.setDesignation(designation);
			mat.setEtat(etat);
			mat.setPrix(prix);
			mat.setQuantite(quantite);
			mat.setQuantiteF(quantiteF);
			mat.setReste(quantiteF);
			mat.setTypemateriel(typemateriel);
			mat.setDateenreg(dateenreg);
			mat.setNumeroserie(numeroserie);
			em2.persist(mat);
			em2.getTransaction().commit();
			result=codeMateriel;			
		
		}
		finally{
			em2.close();
			emf.close();
		}
		
		
		return result;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean modifierMateriel(String codemateriel,String designation, float prix,
			int quantite, String typemateriel, String etat,int quantiteF,
			String anneeEncours, String codeetablissement,Date dateenreg,int id,String numeroserie) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		
		try{
			em2.getTransaction().begin();
			
			Materiel mat=em2.getReference(Materiel.class, id);
			if(mat==null) throw new ElementNOtFoundException(toString().valueOf(id), "Materiel");

			mat.setDesignation(designation);
			mat.setCodemateriel(codemateriel);
			mat.setEtat(etat);
			mat.setPrix(prix);
			mat.setQuantite(quantite);
			mat.setQuantiteF(quantiteF);
			mat.setReste(quantiteF);
			mat.setTypemateriel(typemateriel);
			mat.setDateenreg(dateenreg);
			mat.setNumeroserie(numeroserie);
			em2.merge(mat);
			em2.getTransaction().commit();
			return true;			
		
		}
		finally{
			em2.close();
			emf.close();
		}

	}

	@SuppressWarnings("static-access")
	@Override
	public boolean supprimerMateriel(int codemateriel) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		
		try{
			em2.getTransaction().begin();
			Materiel mat=em2.getReference(Materiel.class, codemateriel);
			if(mat==null) throw new ElementNOtFoundException(toString().valueOf(codemateriel), "Materiel");
			
			em2.remove(mat);
			em2.getTransaction().commit();
			
			return true;			
		
		}
		finally{
			em2.close();
			emf.close();
		}

	}

	@Override
	public Materiel recherchermateriel(int codemateriel,
			String annee, String codeetablissement) {
		Materiel mat=null;
		try{
			Query req=em.createNamedQuery("Materiel.find");
			req.setParameter("codemateriel", codemateriel);
			req.setParameter("codeetab", codeetablissement);
			
		    mat=(Materiel) req.getSingleResult();
		}
		catch(Exception e){
			Repertoire.logWarn("materiel non trouvé ou ++ trouvés "+codemateriel, this.getClass());
			e.printStackTrace();
		}
		return mat;
	}

	@Override
	public String createMaterielAjout(String designation, float prix,
			int quantite, String typemateriel, int quantiteF,
			String annee, String codeetablissement, Date dateenreg,
			String codemateriel,String numeroserie) throws ElementNOtFoundException {
		String result=null;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		
		try{
			em2.getTransaction().begin();
			Annee an=em2.getReference(Annee.class, annee);
			if(an==null) throw new ElementNOtFoundException(annee, "Annee");
			
			Etablissement etab=em2.getReference(Etablissement.class, codeetablissement);
			if(etab==null) throw new ElementNOtFoundException(codeetablissement, "Etablissement");
			
			Materiel mat=new Materiel();
			
			mat.setAnnee(an);
			mat.setEtablissement(etab);
			mat.setCodemateriel(codemateriel);
			mat.setDesignation(designation);
			mat.setPrix(prix);
			mat.setQuantite(quantite);
			mat.setQuantiteF(quantiteF);
			mat.setReste(quantiteF);
			mat.setTypemateriel(typemateriel);
			mat.setDateenreg(dateenreg);
			mat.setEtat("");
			mat.setNumeroserie(numeroserie);
			em2.persist(mat);
			em2.getTransaction().commit();
			
			result=codemateriel;			
		
		}
		finally{
			em2.close();
			emf.close();
		}
		
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Materiel> listermateriels(String annee) {
		List<Materiel> list=null ;
		try{
			System.out.println("l'anne est "+ annee);
			Query req=em.createNamedQuery("Materiel.findAllS");
					
				list=req.getResultList();
			}
			catch(Exception e){
			 Repertoire.logWarn("Erreur lors du listing du materiel", this.getClass());
			}
				
		return list;
	}

	@SuppressWarnings("static-access")
	@Override
	public String retraitMateriel(int id,String designation, float prix, int quantite,
			String typemateriel, int quantiteF, String annee,
			String codeetablissement, Date dateenreg, String codemateriel,
			String numeroserie, int quantiteF2, int quantite2,String raison,String utlisateur) throws ElementNOtFoundException {
		String result=null;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager em2=emf.createEntityManager();
		
		try{
			em2.getTransaction().begin();
			Annee an=em2.getReference(Annee.class, annee);
			if(an==null) throw new ElementNOtFoundException(annee, "Annee");
			
			Etablissement etab=em2.getReference(Etablissement.class, codeetablissement);
			if(etab==null) throw new ElementNOtFoundException(codeetablissement, "Etablissement");
			
			Materiel mat=em2.getReference(Materiel.class, id);
			if(mat==null) throw new ElementNOtFoundException(toString().valueOf(id), "Materiel");
			mat.setReste(quantiteF2-quantiteF);
			em2.merge(mat);
			
			
			SortieM sort= new SortieM();
			
			sort.setAnnee(an);
			sort.setEtablissement(etab);
			sort.setMateriel(mat);
			sort.setRaison(raison);
			sort.setDateretrait(dateenreg);
			sort.setQuantiteSortie(quantiteF);
			sort.setUtlisateur(utlisateur);
			em2.persist(sort);
			em2.getTransaction().commit();
			
			result=codemateriel;			
		
		}
		finally{
			em2.close();
			emf.close();
		}
		
		
		return result;
	}

	@Override
	public SortieM recherchermaterielS(int idsortie, String anneeAcEncours,
			String codeetablissement) {
		SortieM sort=null;
		try{
			Query req=em.createNamedQuery("SortieM.find");
			req.setParameter("idsortie", idsortie);
			req.setParameter("codeetab", codeetablissement);
			
		   sort=(SortieM) req.getSingleResult();
		}
		catch(Exception e){
			Repertoire.logWarn("materiel non trouvé ou ++ trouvés "+idsortie, this.getClass());
			e.printStackTrace();
		}
		return sort;
	}

}
