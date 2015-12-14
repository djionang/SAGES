package ejb.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
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
import ages.exception.CoursNonDefiniException;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;
import ages.exception.PourcentageEvaluationExedantException;
import ejb.GestionExamensLocal;
import entities.Composer;
import entities.ComposerPK;
import entities.Cour;
import entities.Eleve;
import entities.Evaluation;
import entities.Programmation;
import entities.Sequence;
import entities.TypeEvaluation;

/**
 * Session Bean implementation class GestionExamens
 * Classe de gestion des eleves
 */
@Stateless(mappedName = "GestionExamens")
@TransactionManagement(TransactionManagementType.BEAN)
public class GestionExamens implements GestionExamensLocal{

	@PersistenceContext(unitName="agespersist")
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public GestionExamens() {
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeEvaluation> listerTypesEvaluations() {
		Query query=em.createNamedQuery("TypeEvaluation.findAll");
		query.setParameter("supprime",false);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Evaluation> listerEvaluation(String annee) {
		Query query=em.createNamedQuery("Evaluation.findAll");
		query.setParameter("supprime",false);
		query.setParameter("annee",annee);
		return query.getResultList();
	}

	@Override
	public int enregistrerEvaluation(String libelle, String typeevaluation,
			String codematiere,String codeclasse, int codesequence, Date datedebut, Date datefin,
			String annee) throws ElementNOtFoundException, PourcentageEvaluationExedantException, CoursNonDefiniException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		Query query;
		Evaluation eval=null;
		Cour cr=null;
		try{
			emtrans.getTransaction().begin();
			
			//rechercher le cours
			
			
			try{
				query=emtrans.createNamedQuery("Cours.findByCodes");
				query.setParameter("supprime", false);
				query.setParameter("annee", annee);
				query.setParameter("codeclasse",codeclasse);
				query.setParameter("codematiere",codematiere);
				cr=(Cour) query.getSingleResult();
			}
			catch(Exception e){
				Repertoire.logWarn("Cours non trouvé : Matiere_"+codematiere+" Classe_"+codeclasse+" Annee_"+annee, getClass());
				throw new CoursNonDefiniException(codematiere, codeclasse);
			}
			//recherche la sequence
			
			Sequence seq=null;
			try{
				query=emtrans.createNamedQuery("Sequence.findByid");
				query.setParameter("supprime", false);
				query.setParameter("id", codesequence);
				query.setParameter("annee",annee );
				seq=(Sequence) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(String.valueOf(codesequence), "Sequence");
			}
			
			//rechercher le type devaluation
			TypeEvaluation teval=null;
			try{
				query=emtrans.createNamedQuery("TypeEvaluation.findByCode");
				query.setParameter("supprime",false);
				query.setParameter("typeevaluation",typeevaluation);
				teval=(TypeEvaluation) query.getSingleResult();
			}
			catch(NoResultException ex){
				throw new ElementNOtFoundException(typeevaluation, "TypeEvaluation");
			}
			
			//Verifier si les evaluations pour ce cours ne donnent pas déja 100%
			
			Long pourcentagetotalLong=null;
			float pourcentagetotalFloat;
			try{
				query=emtrans.createNamedQuery("Evaluation.findPourcentageEvalue");
				query.setParameter("supprime",false);
				query.setParameter("cours",cr);
				query.setParameter("idsequence",codesequence);
				pourcentagetotalLong= (Long) query.getSingleResult();
				pourcentagetotalFloat=Float.parseFloat(pourcentagetotalLong.toString());
			}
			catch(NullPointerException ex){
				pourcentagetotalFloat=0;
			}
			
			if(pourcentagetotalFloat+teval.getCoefficient()>100)
				throw new PourcentageEvaluationExedantException(codesequence,cr.getCodecours());
			
			
			
			Programmation prog=new Programmation();
			eval=new Evaluation();
						
			eval.setCour(cr);
			eval.setLibelle(libelle);
			eval.setSequence(seq);
			eval.setSupprime(false);
			eval.setTypeEvaluation(teval);
	
			emtrans.persist(eval);
			emtrans.flush();
			
			prog.setDatedebut(datedebut);
			prog.setDatefin(datefin);
			prog.setEvaluation(eval);
			prog.setLibelle("Eval. "+libelle);
			prog.setSupprime(false);
			prog.setEvaluation(eval);
			emtrans.persist(prog);
		    emtrans.getTransaction().commit();
		    
		}finally{
			emtrans.close();
			emf.close();
		}
		return eval.getCodeevaluation();
	}

	@Override
	public void enregistrerTypeEvaluation(String typeevaluation,
			int coefficient, String description) throws DuplicateKeyException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		Query query;
		TypeEvaluation teval=null;
		try{
			emtrans.getTransaction().begin();
	
			try{
				query=emtrans.createNamedQuery("TypeEvaluation.findByCode");
				query.setParameter("supprime",false);
				query.setParameter("typeevaluation",typeevaluation);
				teval=(TypeEvaluation) query.getSingleResult();
			}
			catch(NoResultException ex){
				
			}
			
			if(teval!=null)
				throw new DuplicateKeyException("TypeEvaluation");
			
			teval = new TypeEvaluation();
			teval.setCoefficient(coefficient);
			teval.setType(typeevaluation);
			teval.setDescription(description);
			teval.setSupprime(false);
	
			emtrans.persist(teval);
		    emtrans.getTransaction().commit();
		    
		}finally{
			emtrans.close();
			emf.close();
		}
	}

	@Override
	public void supprimerTypeEvaluation(String typeevaluation) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		Query query;
		TypeEvaluation teval=null;
		try{
			emtrans.getTransaction().begin();

			try{
				query=emtrans.createNamedQuery("TypeEvaluation.findByCode");
				query.setParameter("supprime",false);
				query.setParameter("typeevaluation",typeevaluation);
				teval=(TypeEvaluation) query.getSingleResult();
			}
			catch(NoResultException ex){
				throw new ElementNOtFoundException(typeevaluation, "TypeEvaluation");
			}
			
			teval.setSupprime(true);

			emtrans.merge(teval);
		    emtrans.getTransaction().commit();
		    
		}finally{
			emtrans.close();
			emf.close();
		}
	}

	@Override
	public void supprimerEvaluation(int codeevaluation, String anneeAcEncours) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		Query query;
		Evaluation eval=null;
		try{
			emtrans.getTransaction().begin();

			try{
				query=emtrans.createNamedQuery("Evaluation.findByCode");
				query.setParameter("supprime",false);
				query.setParameter("codeevaluation",codeevaluation);
				eval=(Evaluation) query.getSingleResult();
			}
			catch(NoResultException ex){
				throw new ElementNOtFoundException(String.valueOf(codeevaluation), "Evaluation");
			}
			
			eval.setSupprime(true);

			emtrans.merge(eval);
		    emtrans.getTransaction().commit();
		    
		}finally{
			emtrans.close();
			emf.close();
		}
	}

	@Override
	public TypeEvaluation rechercheTypeEvaluation(String typeevaluation) {
		Query query;
		TypeEvaluation teval;
		try{
			query=em.createNamedQuery("TypeEvaluation.findByCode");
			query.setParameter("supprime",false);
			query.setParameter("typeevaluation",typeevaluation);
			teval=(TypeEvaluation) query.getSingleResult();
		}
		catch(NoResultException ex){
			teval=null;
		}
		
		return teval;
	}

	@Override
	public void modifierTypeEvaluation(String typeevaluation, int coefficient,
			String description) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		Query query;
		TypeEvaluation teval=null;
		try{
			emtrans.getTransaction().begin();

			try{
				query=emtrans.createNamedQuery("TypeEvaluation.findByCode");
				query.setParameter("supprime",false);
				query.setParameter("typeevaluation",typeevaluation);
				teval=(TypeEvaluation) query.getSingleResult();
			}
			catch(NoResultException ex){
				throw new ElementNOtFoundException(typeevaluation, "TypeEvaluation");
			}
			
			teval.setCoefficient(coefficient);
			teval.setDescription(description);
			teval.setSupprime(false);

			emtrans.merge(teval);
		    emtrans.getTransaction().commit();
		    
		}finally{
			emtrans.close();
			emf.close();
		}
	}

	@Override
	public void modifierEvaluation(int codeevaluation, String libelle,
			String typeevaluation, String codematiere,String codeclasse, int codesequence,
			Date datedebut, Date datefin, String annee) throws ElementNOtFoundException, PourcentageEvaluationExedantException, CoursNonDefiniException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		Query query;
		Evaluation eval=null;
		Cour cr=null;
		try{
			emtrans.getTransaction().begin();

			try{
				query=emtrans.createNamedQuery("Evaluation.findByCode");
				query.setParameter("supprime",false);
				query.setParameter("codeevaluation",codeevaluation);
				eval=(Evaluation) query.getSingleResult();
			}
			catch(NoResultException ex){
				throw new ElementNOtFoundException(String.valueOf(codeevaluation), "Evaluation");
			}
			
			//rechercher le cours
			
			
			try{
				query=emtrans.createNamedQuery("Cours.findByCodes");
				query.setParameter("supprime", false);
				query.setParameter("annee", annee);
				query.setParameter("codeclasse",codeclasse);
				query.setParameter("matiere",codematiere);
				cr=(Cour) query.getSingleResult();
			}
			catch(Exception e){
				Repertoire.logWarn("Cours non trouvé : Matiere_"+codematiere+" Classe_"+codeclasse, getClass());
				throw new CoursNonDefiniException(codematiere, codeclasse);
			}
			
			//recherche la sequence
			
			Sequence seq=null;
			try{
				query=emtrans.createNamedQuery("Sequence.findByid");
				query.setParameter("supprime", false);
				query.setParameter("id", codesequence);
				query.setParameter("annee",annee );
				seq=(Sequence) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(String.valueOf(codesequence), "Sequence");
			}
		
			TypeEvaluation teval=null;
			try{
				query=emtrans.createNamedQuery("TypeEvaluation.findByCode");
				query.setParameter("supprime",false);
				query.setParameter("typeevaluation",typeevaluation);
				teval=(TypeEvaluation) query.getSingleResult();
			}
			catch(NoResultException ex){
				throw new ElementNOtFoundException(typeevaluation, "TypeEvaluation");
			}
			
			//Verifier si les evaluations pour ce cours ne donnent pas déja 100%
			//rechercher le type devaluation
			Long pourcentagetotalLong=null;
			float pourcentagetotalFloat;
			try{
				query=emtrans.createNamedQuery("Evaluation.findPourcentageEvalueMinus");
				query.setParameter("supprime",false);
				query.setParameter("cours",cr);
				query.setParameter("code",codeevaluation);
				pourcentagetotalLong= (Long) query.getSingleResult();
				pourcentagetotalFloat=Float.parseFloat(pourcentagetotalLong.toString());
			}
			catch(NullPointerException ex){
				pourcentagetotalFloat=0;
			}
			
			if(pourcentagetotalFloat+teval.getCoefficient()>100)
				throw new PourcentageEvaluationExedantException(codesequence,cr.getCodecours());
			
			
			
			Programmation prog=new Programmation();
			
			
			eval.setCour(cr);
			eval.setLibelle(libelle);
			eval.setSequence(seq);
			eval.setSupprime(false);
			eval.setTypeEvaluation(teval);
			eval.setProgrammations(null);
			emtrans.merge(eval);
			emtrans.flush();
			
			prog.setDatedebut(datedebut);
			prog.setDatefin(datefin);
			prog.setEvaluation(eval);
			prog.setLibelle("Eval. "+libelle);
			prog.setSupprime(false);
			
			emtrans.persist(prog);
		    emtrans.getTransaction().commit();
		    
		}finally{
			emtrans.close();
			emf.close();
		}
	}

	@Override
	public Evaluation rechercheEvaluation(int codeevaluation) {
		Query query;
		Evaluation ev=null;
		try{
			query=em.createNamedQuery("Evaluation.findByCode");
			query.setParameter("supprime",false);
			query.setParameter("codeevaluation",codeevaluation);
			ev=(Evaluation) query.getSingleResult();
		}
		catch(NoResultException ex){
			
		}
		return ev;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Evaluation> listerEvaluation(int codesequence,
			String codeclasse, String annee) {
		Query query=em.createNamedQuery("Evaluation.findByClassSeq");
		query.setParameter("supprime",false);
		query.setParameter("annee",annee);
		query.setParameter("classe",codeclasse);
		query.setParameter("sequence",codesequence);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Evaluation> listerEvaluation(int codesequence,
			String codeclasse, String codematiere, String annee) {
		Query query=em.createNamedQuery("Evaluation.findByClassSeqMat");
		query.setParameter("supprime",false);
		query.setParameter("annee",annee);
		query.setParameter("classe",codeclasse);
		query.setParameter("sequence",codesequence);
		query.setParameter("matiere",codematiere);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eleve> listerElevesJustifies(int codesequence,
			String codeclasse, int evaluation, String annee) {
		Query query=em.createNamedQuery("Eleve.findJustifiesEvaluation");
		query.setParameter("supprime",false);
		query.setParameter("annee",annee);
		query.setParameter("classe",codeclasse);
		query.setParameter("sequence",codesequence);
		query.setParameter("codeevaluation",evaluation);
		query.setParameter("justifie",true);
		query.setParameter("sequence",codesequence);
		return query.getResultList();
	}

	@Override
	public void justifierAbsence(int evaluation, List<String> eleveschoisis,
			String motifabsence, String annee) throws ElementNOtFoundException {
						
		// pour chaque eleve rechercher ses compo, et les set à ...
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		Query query;
		Composer compo;
		try{
			emtrans.getTransaction().begin();
			System.out.println("la taille selectionne est:"+     eleveschoisis.size());
			
			for(int i=0;i<eleveschoisis.size();i++){
				
				Query query1=em.createNamedQuery("Eleve.findByMatricule");
				query1.setParameter("supprime",false);
				query1.setParameter("annee",annee);
				query1.setParameter("matricule",eleveschoisis.get(i));
				Eleve ele = (Eleve)query1.getSingleResult();
								
				try{
					query=emtrans.createNamedQuery("Composer.findById");
					query.setParameter("supprime",false);
					query.setParameter("codeevaluation",evaluation);
					query.setParameter("annee",annee);
					query.setParameter("ideleve", ele.getIdeleve());
					compo=(Composer) query.getSingleResult();
				}
				catch(NoResultException ex){
					throw new ElementNOtFoundException(eleveschoisis.get(i)+"-"+String.valueOf(evaluation), "Composer");
				}
				
				compo.setAbsencejustifiee(true);
				compo.setJustificationAbsence(motifabsence);
				emtrans.merge(compo);
			}
						
		    emtrans.getTransaction().commit();
		    
		}finally{
			emtrans.close();
			emf.close();
		}
	}

	@Override
	public void annulerJustificationabeval(int evaluation,
			List<String> eleveschoisis, String annee,String login) throws ElementNOtFoundException {
		// pour chaque eleve rechercher ses compo, et les set à ...
		
				EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
				EntityManager emtrans=emf.createEntityManager();
				Query query;
				Composer compo;
				try{
					emtrans.getTransaction().begin();
						
					for(int i=0;i<eleveschoisis.size();i++){
						
						Query query1=em.createNamedQuery("Eleve.findByMatricule");
						query1.setParameter("supprime",false);
						query1.setParameter("annee",annee);
						query1.setParameter("matricule",eleveschoisis.get(i));
						Eleve ele = (Eleve)query1.getSingleResult();
										
						try{
							query=emtrans.createNamedQuery("Composer.findById");
							query.setParameter("supprime",false);
							query.setParameter("codeevaluation",evaluation);
							query.setParameter("annee",annee);
							query.setParameter("ideleve", ele.getIdeleve());
							System.out.println("le "+i+"  element est:"+     ele.getIdeleve());
							compo=(Composer) query.getSingleResult();
						}
						catch(NoResultException ex){
							throw new ElementNOtFoundException(eleveschoisis.get(i)+"-"+evaluation, "Composer");
						}
						
						compo.setAbsencejustifiee(false);
						compo.setNote(0);
						compo.setLogin(login);
						compo.setDateEnregistrement(new Date());
						emtrans.merge(compo);
					}
								
				    emtrans.getTransaction().commit();
				    
				}finally{
					emtrans.close();
					emf.close();
				}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Composer> listerComposNotes(int codeevaluation,String annee) {
		Query query=em.createNamedQuery("Composer.findByEvaluation");
		query.setParameter("supprime",false);
		query.setParameter("annee",annee);
		query.setParameter("evaluation",codeevaluation);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eleve> listerElevesevalues(int codeevaluation,
			String annee) {
		Query query=em.createNamedQuery("Evaluation.EleveEvalues");
		query.setParameter("supprime",false);
		query.setParameter("annee",annee);
		query.setParameter("evaluation",codeevaluation);
		return query.getResultList();
	}

	@Override
	public String enregistrerNotes(Hashtable<String, Float> notes,
			int evaluation, String annee,String login) throws ElementNOtFoundException {

		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		Query query;
		Composer compo;
		List<String> matricules=new ArrayList<String>();
		Eleve eleve ;
		Evaluation eval;
		
		/*
		 * PRINCIPE DE L'ALGORITHME
		 * Pour chaque élève, on va dans la table des composition regarder s'il a déja une note pour l'évaluation.
		 * Si tel est le cas, on verifie que cette note est differente de la nouvelle, puis on la sauvegarde
		 * Sinon (l'eleve n'a pas encore de note pour cette evaluation) on lui cree une compostion(evaluation et note) et on sauvegarde
		 */
		
		try{
			emtrans.getTransaction().begin();
			
			for (Enumeration<String> mats = notes.keys();mats.hasMoreElements();)
				matricules.add(mats.nextElement());

			for(int i=0;i<matricules.size();i++){

				//Recherche une composition correspondant a l'eleve et à l'évaluation
				try{
					query=emtrans.createNamedQuery("Composer.findByCode");
					query.setParameter("supprime",false);
					query.setParameter("codeevaluation",evaluation);
					query.setParameter("annee",annee);
					query.setParameter("matricule",matricules.get(i));
					compo=(Composer) query.getSingleResult();					
					
					
					//Si la composition est trouvée, verifie que la note est differente de celle enregistrée
					// sauvegarde s'il ya difference
					if(compo.getNote()!=notes.get(matricules.get(i)) && !compo.isAbsencejustifiee()){
						compo.setNote(notes.get(matricules.get(i)));
						compo.setLogin(login);
						compo.setDateEnregistrement(new Date());
						emtrans.merge(compo);
					}
					
				}
				catch(NoResultException ex){
					
					//cas où la composition n'est pas retrouvée pour l'élève
					//on lui crée une composition, affecte la note et sauvegarde
					compo=new Composer();
					compo.setAbsencejustifiee(false);
					compo.setNote(notes.get(matricules.get(i)));
					compo.setSupprime(false);
					compo.setLogin(login);
					compo.setDateEnregistrement(new Date());
					
					try{
						query=emtrans.createNamedQuery("Evaluation.findByCode");
						query.setParameter("supprime",false);
						query.setParameter("codeevaluation",evaluation);
						eval=(Evaluation) query.getSingleResult();
					}
					catch(NoResultException ex2){
						throw new ElementNOtFoundException(String.valueOf(evaluation), "Evaluation");
					}
					
					try{
						Query req=emtrans.createNamedQuery("Eleve.findByMatricule");
						req.setParameter("annee", annee);
						req.setParameter("supprime", false);
						req.setParameter("matricule", matricules.get(i));
						
					   eleve=(Eleve) req.getSingleResult();
					}
					catch(NoResultException e){
						
						throw new ElementNOtFoundException(matricules.get(i), "Eleve");
					}
					ComposerPK id=new ComposerPK();
					id.setCodeevaluation(evaluation);
					id.setIdeleve(eleve.getIdeleve());
					compo.setId(id);
					
					compo.setEleve(eleve);
					compo.setEvaluation(eval);
					emtrans.persist(compo);
				}
				
			}
						
		    emtrans.getTransaction().commit();
		    return "SUCCES";
		}finally{
			emtrans.close();
			emf.close();
		}
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listerElevesNotesEvalues(int codeevaluation,
			String annee) {
		Query query=em.createNamedQuery("Eleve.findElevesNotesEvalues");
		query.setParameter("supprime",false);
		query.setParameter("annee",annee);
		query.setParameter("codeevaluation",codeevaluation);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eleve> listerElevesNonEvalues(int codeevaluation,
			String annee) {
		Query query=em.createNamedQuery("Eleve.findElevesNonEvalues");
		query.setParameter("supprime",false);
		query.setParameter("annee",annee);
		query.setParameter("evaluation",codeevaluation);
		return query.getResultList();
	}

	@Override
	public List<Eleve> listerTableauxHonneur(String codeclasse, int trimestre,String annee) {
		return null;
	}

	
}


