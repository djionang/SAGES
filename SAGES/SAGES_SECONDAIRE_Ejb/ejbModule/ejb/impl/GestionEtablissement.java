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
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import utils.Repertoire;
import ages.exception.AnneeEnCoursNonDefinieException;
import ages.exception.ChevauchementDateException;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;
import ejb.GestionEtablissementLocal;
import entities.Annee;
import entities.CodeCycle;
import entities.CodeEtablissement;
import entities.CodeSection;
import entities.Cycle;
import entities.Enseignement;
import entities.Etablissement;
import entities.Niveau;
import entities.OptionSerie;
import entities.Section;
import entities.Sequence;
import entities.Trimestre;
import entities.TrimestrePK;


@Stateless(mappedName = "GestionEtablissement")

@TransactionManagement(TransactionManagementType.BEAN)
public class GestionEtablissement implements GestionEtablissementLocal{

	@PersistenceContext(unitName="agespersist")
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listerCodesEtablissement() {
		Query query;
		
		query=em.createQuery("select e.codeetablissement, e.acronyme from Etablissement as e where e.supprime=:supprime");
		query.setParameter("supprime", false);
	
		return extracted(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OptionSerie> listerOptions() {
		Query query=em.createNamedQuery("OptionSerie.findAll");
		query.setParameter("supprime", false);
		return extracted(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Niveau> listerNiveaux() {
		Query query=em.createNamedQuery("Niveau.findAll");
		query.setParameter("supprime", false);
		return extracted(query);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Niveau> listerNiveauxB() {
		Query query=em.createNamedQuery("Niveau.findAllB");
		query.setParameter("supprime", false);
		return extracted(query);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> listercodesEnseignements() {
		Query query;
		try{
			query=em.createQuery("select e.libelleens from Enseignement as e where e.supprime=:supprime");
			query.setParameter("supprime", false);
		
		}
		finally{
			
		}
		return extracted(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cycle> listerCycles() {
		Query query=em.createNamedQuery("Cycle.findAll");
		query.setParameter("supprime", false);
		return extracted(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listerCodesSections() {
		Query query;
		try{
			query=em.createQuery("select s.codesection, s.libelle from Section as s where s.supprime=:supprime");
			query.setParameter("supprime", false);
		
		}
		finally{
			
		}
		return extracted(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Enseignement> listerEnseignements() {
		Query query=em.createNamedQuery("Enseignement.findAll");
		query.setParameter("supprime", false);
		return extracted(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Etablissement> listerEtablissements() {
		Query query=em.createNamedQuery("Etablissement.findAll");
		query.setParameter("supprime", false);
		return extracted(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Section> listerSections() {
		Query query=em.createNamedQuery("Section.findAll");
		query.setParameter("supprime", false);
		return extracted(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trimestre> listerTrimestres(String annee) {
		Query query=em.createNamedQuery("Trimestre.findAll");
		query.setParameter("supprime", false);
		query.setParameter("annee", annee);
		return extracted(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sequence> listerSequences(String annee) {
		Query query=em.createNamedQuery("Sequence.findAllByYear");
		query.setParameter("supprime", false);
		query.setParameter("annee", annee);
		return extracted(query);
	}

	@Override
	public String getAnneeEnCours() {
		
		// recupere ca dans la BD
		Query query=em.createQuery("select a from Annee a where a.close=:close order by a.dateDebut asc");
		query.setParameter("close", false);
		@SuppressWarnings("unchecked")
		List<Annee> liste=(List<Annee>)extracted(query);
		
		if(liste==null) return null;
		if(liste.isEmpty()) return "";
		
		Annee ane=liste.get(0);
		return ane.getAnneeacademique();
	}

	/**
	 * Enregistrement simultan� (ou presque) de plusieurs options
	 */
	public List<String> enregistrerOptionSeries(String libelle,List<String> niveaux){
		List<String> res=new ArrayList<String>();
		String codeoption;
		
		
		if(niveaux==null) return res;
		for(int i=0;i<niveaux.size();i++){
			codeoption=libelle+"_"+niveaux.get(i);
			try {
				enregistrerOptionSerie(codeoption, libelle, niveaux.get(i));
				
			} catch (DuplicateKeyException e) {
				continue;
			} catch (ElementNOtFoundException e) {
				continue;
			}
			
			res.add(niveaux.get(i));
					
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void enregistrerOptionSerie(String codeoption, String libelle,String niveau) throws DuplicateKeyException, ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		try{
			emtrans.getTransaction().begin();
			Query query=emtrans.createNamedQuery("OptionSerie.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("codeoption", codeoption);
			
			if(!query.getResultList().isEmpty())
				throw new DuplicateKeyException(codeoption);
			
			query=emtrans.createNamedQuery("Niveau.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("codeniveau", niveau);
			List<Niveau> niveaux=query.getResultList();
			if(niveaux.isEmpty())
				throw new ElementNOtFoundException(niveau, "Niveau");
			
			OptionSerie option=new OptionSerie();
			option.setCodeoption(codeoption);
			option.setLibelle(libelle);
			option.setNiveau(niveaux.get(0));
			option.setSupprime(false);
			emtrans.persist(option);
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
		
	}

	@Override
	public void enregistrerEtablissement(String codeetablissement, String nom,
			String logingest, String passgest) throws DuplicateKeyException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		try{

			emtrans.getTransaction().begin();
			if(codeetablissement==null || codeetablissement.isEmpty()){
				CodeEtablissement cd=new CodeEtablissement();
				emtrans.persist(cd);
				
				codeetablissement=Repertoire.genererCodeEtablissement(cd.getCode());
			
			}
			
			if(rechercheEtablissement(codeetablissement, emtrans)!=null)
				throw new DuplicateKeyException(codeetablissement);
			Etablissement etab=new Etablissement();
			etab.setCodeetablissement(codeetablissement);
			etab.setLogin_gest(logingest);
			etab.setPass_gest(passgest);
			
			etab.setNometab(nom);			
			
			etab.setSupprime(false);
			emtrans.persist(etab);
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
		
	}

	@Override
	public void enregistrerEnseignement(String libelleens, String description,
			String type) throws DuplicateKeyException, ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		try{
			emtrans.getTransaction().begin();
			if(emtrans.find(Enseignement.class,libelleens)!=null)
				throw new DuplicateKeyException(libelleens);
			
			
			//Etablissement etab=emtrans.find(Etablissement.class,etablissement);
			//if(etab==null) throw new ElementNOtFoundException(etablissement, "Etablissement");
			
			Enseignement ens=new Enseignement();
			ens.setDescription(description);
			//ens.setEtablissement(etab);
			ens.setLibelleens(libelleens);
			ens.setType(type);
			ens.setSupprime(false);
			emtrans.persist(ens);
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
		
	}

	@Override
	public void enregistrerSection(String codesection, String description,
			String libelle, String enseignement) throws ElementNOtFoundException, DuplicateKeyException{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		Query query;
		Section sect;
		try{
			emtrans.getTransaction().begin();
			
			CodeSection cd=new CodeSection();
			emtrans.persist(cd);
			
			codesection=Repertoire.genererCodeSection(cd.getCode());
			
			query=emtrans.createQuery("select s from Section as s where s.codesection=:codesection and supprime="+true);
			query.setParameter("codesection", codesection);
			
			try{
				Enseignement ens=emtrans.find(Enseignement.class,enseignement);
				sect=(Section)query.getSingleResult();
				sect.setCodesection(codesection);
				sect.setDescription(description);
				sect.setEnseignement(ens);
				sect.setLibelle(libelle);
				sect.setSupprime(false);
				emtrans.merge(sect);
			}
			catch(Exception e){
				Enseignement ens=emtrans.find(Enseignement.class,enseignement);
				if(ens==null) throw new ElementNOtFoundException(enseignement, "Enseignement");
				
				sect=new Section();
				sect.setCodesection(codesection);
				sect.setDescription(description);
				sect.setEnseignement(ens);
				sect.setLibelle(libelle);
				sect.setSupprime(false);
				emtrans.persist(sect);
			}
			
			
			
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
		
	}

	@Override
	public void enregistrerCycle(String codeCycle, String libelle,
			String codesection) throws ElementNOtFoundException, DuplicateKeyException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		try{
			
			
			 
			emtrans.getTransaction().begin();
			CodeCycle cd=new CodeCycle();
			emtrans.persist(cd);
			
			 codeCycle=Repertoire.genererCodeCycle(cd.getCode());
			 
			if(emtrans.find(Cycle.class,codeCycle)!=null)
				throw new DuplicateKeyException(codeCycle);
			
			
			Section sect=emtrans.find(Section.class,codesection);
			if(sect==null) throw new ElementNOtFoundException(codesection, "Section");
			
			Cycle cy=new Cycle();
			cy.setCodecycle(codeCycle);
			cy.setLibelle(libelle);
			cy.setSection(sect);
			cy.setSupprime(false);
			emtrans.persist(cy);
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void enregistrerNiveau(String codeNiveau, String libelle,
			String codeCycle) throws ElementNOtFoundException, DuplicateKeyException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		try{
			emtrans.getTransaction().begin();
			Query query=emtrans.createNamedQuery("Niveau.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("codeniveau", codeNiveau);
		
			if(!query.getResultList().isEmpty())
				throw new DuplicateKeyException(codeNiveau);			
			
			query=emtrans.createNamedQuery("Cycle.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("codecycle", codeCycle);
		
			List<Cycle> cycles=query.getResultList();
			if(cycles.isEmpty())
				throw new ElementNOtFoundException(codeCycle, "Cycle");
			
			Niveau nivo=new Niveau();
			nivo.setCodeniveau(codeNiveau);
			nivo.setCycle(cycles.get(0));
			nivo.setLibelle(libelle);
			nivo.setSupprime(false);
			emtrans.persist(nivo);
		    emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public String enregistrerAnneeAcademique(Date datedebut, Date datefin) throws DuplicateKeyException, ChevauchementDateException {
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();
		Annee annee;
		Query query;
		List<Annee> annees=null;
		
		try{
			emtrans.getTransaction().begin();
			
			String anneeacademique=Repertoire.genererAnneeAcademique(datedebut, datefin);		
			
			annee=emtrans.find(Annee.class,anneeacademique);
			if(annee!=null)
				throw new DuplicateKeyException(anneeacademique);
			
			query=emtrans.createQuery("select a from Annee as a where a.dateFin>:datedebut and a.supprime=:supprime");
			query.setParameter("datedebut",datedebut, TemporalType.DATE);
			query.setParameter("supprime",false);
			annees=query.getResultList();
			if(annees!=null&&!annees.isEmpty())
				throw new ChevauchementDateException("Annee");
			
			annee=new Annee();
			annee.setAnneeacademique(anneeacademique);
			annee.setDateDebut(datedebut);
			annee.setDateFin(datefin);
			annee.setClose(false);
			emtrans.persist(annee);
		    emtrans.getTransaction().commit();
			
		}
		finally{
			emtrans.close();
			emfact.close();
		}
		return annee.getAnneeacademique();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void enregistrerTrimestre(int numero, Date datedebut, Date datefin, Date dateimpressionbull,
			String annee) throws ElementNOtFoundException, ChevauchementDateException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		Annee an=null;
		Query query;
		List<Trimestre> trimestres;
		Trimestre tri;
		try{
			emtrans.getTransaction().begin();
			
			try{
				query=emtrans.createNamedQuery("Annee.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("close", false);
				query.setParameter("annee", annee);
				an=(Annee) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(annee, "Annee");
			}
			
			
			//verifier qu'il n'y a pas de chevauchement!!!
			
			query=emtrans.createQuery("select t from Trimestre as t where ((:datedebut between t.datedebut and t.datefin) or(:datefin between t.datedebut and t.datefin)) and t.supprime=:supprime");
			query.setParameter("datedebut",datedebut, TemporalType.DATE);
			query.setParameter("datefin",datefin, TemporalType.DATE);
			query.setParameter("supprime",false);
			trimestres=query.getResultList();
			
			if(trimestres!=null&&!trimestres.isEmpty())
				throw new ChevauchementDateException("Trimestre");
			
			
			// verifier que cela n'existe pas d�ja!!!
			try{
				query=emtrans.createQuery("select t from Trimestre as t where t.annee.anneeacademique=:annee and t.id.numerotrimestre=:numero and t.supprime=:supprime");
				query.setParameter("annee",annee);
				query.setParameter("numero",numero);
				query.setParameter("supprime",true);
				tri=(Trimestre) query.getSingleResult();
			}
			catch(Exception e){
				tri=null;
			}
			
			if(tri!=null){	// Trimestre trouv� d�ja supprim�; r�activation juste et modification des prt�s
				tri.setDatedebut(datedebut);
				tri.setDatefin(datefin);
				tri.setDateimpressionbulletin(dateimpressionbull);
				tri.setAnnee(an);
				tri.setSupprime(false);
				emtrans.merge(tri);
			}
			
			else{
				
				// Le trimestre existe pas, il faut le cr�er
				
				
				TrimestrePK triPK = new TrimestrePK();
				triPK.setAnneeacademique(annee);
				triPK.setNumerotrimestre(numero);
				
				tri = new Trimestre();
		
				tri.setDatedebut(datedebut);
				tri.setDatefin(datefin);
				tri.setDateimpressionbulletin(dateimpressionbull);
				tri.setAnnee(an);
				tri.setId(triPK);
				tri.setSupprime(false);
		
				emtrans.persist(tri);
			}			
			
			
		    emtrans.getTransaction().commit();
		    
		}finally{
				emtrans.close();
				emf.close();
			}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void enregistrerSequence(int numero, Date datedebut, Date datefin,
			int trimestre,String annee) throws DuplicateKeyException, ElementNOtFoundException, ages.exception.NonUniqueResultException, ChevauchementDateException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		
		Query query;
		Trimestre trim=null;
		Sequence seq=null;
		List<Sequence> sequences=null;
		
		try{
			emtrans.getTransaction().begin();			
	
			//la sequence la n'est pas encore enregistr�e?
			
			
			try{
				query=emtrans.createNamedQuery("Sequence.findOneByYear");
				query.setParameter("supprime", false);
				query.setParameter("numero", numero);
				query.setParameter("annee",annee );
				seq=(Sequence) query.getSingleResult();
			}
			catch(NoResultException e){
				// Sequence pas trouv�e, c'est cool
			}
			
			if(seq!=null)
				throw new DuplicateKeyException("Sequence");			
			
			query=emtrans.createQuery("select s from Sequence as s where ((:datedebut between s.datedebut and s.datefin) or(:datefin between s.datedebut and s.datefin)) and s.supprime=:supprime");
			query.setParameter("datedebut",datedebut, TemporalType.DATE);
			query.setParameter("datefin",datefin, TemporalType.DATE);
			query.setParameter("supprime",false);
			sequences=query.getResultList();
			if(sequences!=null&&!sequences.isEmpty())
				throw new ChevauchementDateException("Sequence");
			
			// cas ou le trimestre est choisi dynamiquement
			if(trimestre==0){
				//le trimestre contenant la date debut de la sequence la est ou?
				try{
					query=emtrans.createQuery("select t from Trimestre as t where (:debutsequence between t.datedebut and t.datefin) and t.supprime=:supprime and t.annee.anneeacademique=:annee");
					query.setParameter("supprime", false);
					query.setParameter("annee", annee);
					query.setParameter("debutsequence", datedebut,TemporalType.DATE);
					trim=(Trimestre) query.getSingleResult();
				}
				catch(NoResultException e){
					throw new ElementNOtFoundException(String.valueOf(trimestre), "Trimestre");
				}
				catch(NonUniqueResultException ex){
					throw new  ages.exception.NonUniqueResultException("Trimestre", "contenant la date "+datedebut);
				}
			}
			else{
				
				//le trimestre la existe meme?
				try{
					query=emtrans.createNamedQuery("Trimestre.findByCode");
					query.setParameter("supprime", false);
					query.setParameter("annee", annee);
					query.setParameter("numero", trimestre);
					trim=(Trimestre) query.getSingleResult();
				}
				catch(NoResultException e){
					throw new ElementNOtFoundException(String.valueOf(trimestre), "Trimestre");
				}
				
			}
			
			
			try{
				query=emtrans.createNamedQuery("Sequence.findOneByYear");
				query.setParameter("supprime", true);
				query.setParameter("numero", numero);
				query.setParameter("annee",annee );
				seq=(Sequence) query.getSingleResult();
				
				seq.setDatedebut(datedebut);
				seq.setDatefin(datefin);
				seq.setDateimpressionreleve(datefin);
				seq.setTrimestre(trim);
				seq.setSupprime(false);
				emtrans.merge(seq);
			}
			catch(NoResultException e){
				// Sequence pas trouv�e, c'est cool
				seq=new Sequence();
				seq.setDatedebut(datedebut);
				seq.setDatefin(datefin);
				seq.setDateimpressionreleve(datefin);
				seq.setNumerosequence(numero);
				seq.setTrimestre(trim);
				seq.setSupprime(false);
		
				emtrans.persist(seq);
			}
			
	
			
		    emtrans.getTransaction().commit();
		    
		}finally{
				emtrans.close();
				emf.close();
			}
	}

	@Override
	public Etablissement rechercheEtablissement(String codeetablissement) {
		return rechercheEtablissement(codeetablissement, em);
	}

	private Etablissement rechercheEtablissement(String codeetablissement, EntityManager em) {
		try{
			Query query=em.createNamedQuery("Etablissement.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("codeetablissement", codeetablissement);
			
			return (Etablissement) query.getSingleResult();
		}
		catch(Exception e){
			return null;
		}
	}
	
	@Override
	public Enseignement rechercheEnseignement(String libelleens) {
		return em.find(Enseignement.class, libelleens);
	}

	@Override
	public Section rechercheSection(String codesection) {
		return rechercheSection(codesection, em);
	}
	
	private Section rechercheSection(String codesection, EntityManager em){
		try{
			Query query=em.createNamedQuery("Section.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("codesection", codesection);
			
			return (Section) query.getSingleResult();
		}
		catch(Exception e){
			return null;
		}
		
	}

	@Override
	public OptionSerie rechercheOption(String codeoption) {
		return rechercheOption(codeoption, em);
	
	}
	
	private OptionSerie rechercheOption(String codeoption, EntityManager em){
		try{
			Query query=em.createNamedQuery("OptionSerie.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("codeoption", codeoption);
			
			return (OptionSerie) query.getSingleResult();
		}
		catch(Exception e){
			return null;
		}
		
	}

	@Override
	public Cycle rechercheCycle(String codecycle) {
		Query query=em.createNamedQuery("Cycle.findByCode");
		query.setParameter("supprime", false);
		query.setParameter("codecycle", codecycle);
		
		return  (Cycle) query.setMaxResults(1).getResultList().get(0);
	
	}

	@Override
	public Niveau rechercheNiveau(String codeniveau) {
		return rechercheNiveau(codeniveau, em);
	
	}
	
	
	private Niveau rechercheNiveau(String codeniveau, EntityManager em){
		Query query=em.createNamedQuery("Niveau.findByCode");
		query.setParameter("supprime", false);
		query.setParameter("codeniveau", codeniveau);
		
		return  (Niveau) query.setMaxResults(1).getResultList().get(0); 
	}

	@Override
	public Object[] rechercheClasse(String codeClasse, String annee) {
		Query query=em.createNamedQuery("Classe.findById");
		query.setParameter("supprime", false);
		query.setParameter("codeclasse", codeClasse);
		
		@SuppressWarnings("unchecked")
		List<Object[]> r= query.getResultList();
		if((r==null)|| r.isEmpty()) 
			return null;
		else
			return r.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Annee rechercheAnnee(String anneeacademique,String etablissement) throws AnneeEnCoursNonDefinieException {
		Query query=em.createNamedQuery("Annee.findByCode");
		query.setParameter("supprime", false);
		query.setParameter("close", false);
		query.setParameter("annee", anneeacademique);
		List<Annee> ans=query.getResultList();
		if(ans==null||ans.isEmpty()){
			throw new AnneeEnCoursNonDefinieException(etablissement);
		}
		else
			return ans.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Annee rechercheAnneeI(String anneeacademique,String etablissement) throws AnneeEnCoursNonDefinieException {
		Query query=em.createNamedQuery("Annee.find");
		query.setParameter("supprime", false);
		query.setParameter("annee", anneeacademique);
		List<Annee> ans=query.getResultList();
		if(ans==null||ans.isEmpty()){
			throw new AnneeEnCoursNonDefinieException(etablissement);
		}
		else
			return ans.get(0);
	}

	@Override
	public Trimestre rechercheTrimestre(int numero, String annee) {
		Query query;
		Trimestre trim=null;
		try{
			query=em.createNamedQuery("Trimestre.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("annee", annee);
			query.setParameter("numero", numero);
			trim=(Trimestre) query.getSingleResult();
		}
		catch(NoResultException e){
			// trimestre pas trouv�, c'est pas grave
			Repertoire.logWarn("Trimestre non trouv� "+numero+" - "+annee, getClass());
		
		}
		
		return trim;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Annee rechercheAnneeEnCours(String anneeacademique) throws AnneeEnCoursNonDefinieException {
		
		System.out.println("l'annee academique est"+    anneeacademique);
		Query query=em.createNamedQuery("Annee.findAnneeEnCours");
		query.setParameter("supprime", false);
		query.setParameter("annee", anneeacademique);
		List<Annee> ans=query.getResultList();
		if(ans==null||ans.isEmpty()){
			throw new AnneeEnCoursNonDefinieException(anneeacademique);
		}
		else
			return ans.get(0);
	}

	@Override
	public Sequence rechercheSequence(int numero, String annee) {
		Sequence seq=null;
		Query query;
		try{
			query=em.createNamedQuery("Sequence.findOneByYear");
			query.setParameter("supprime", false);
			query.setParameter("numero", numero);
			query.setParameter("annee",annee );
			seq=(Sequence) query.getSingleResult();
		}
		catch(NoResultException e){
			// Sequence pas trouv�e, c'est pas grave
		}
		
		return seq;
	}

	@Override
	public void modifierEtablissement(String codeetablissement, String nom,
			String acronyme, String devise, String logo, String type,
			String codepostal, String email, String telephone, String siteweb,
			String region, String departement, String arrondissement,
			String adresse, String pays,String devisepays) throws ElementNOtFoundException {
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			
			Etablissement etab=emtrans.find(Etablissement.class,codeetablissement);
			if(etab==null) throw new ElementNOtFoundException(codeetablissement, "Etablissement");
			
			etab.setAcronyme(acronyme);
			etab.setAdresse(adresse);
			etab.setArrondissement(arrondissement);
			etab.setCodepostal(codepostal);
			etab.setDepartement(departement);
			etab.setDevise(devise);
			etab.setEmail(email);
			etab.setLogo(logo);
			etab.setNometab(nom);
			etab.setRegion(region);
			etab.setSiteweb(siteweb);
			etab.setTelephone(telephone);
			etab.setType(type);
			etab.setPays(pays);
			etab.setDevisepays(devisepays);
			
			etab.setSupprime(false);
			emtrans.merge(etab);
		    emtrans.getTransaction().commit();
			
		}
		finally{
			emtrans.close();
			emfact.close();
		}
	}

	@Override
	public void modifierCycle(String codeCycle, String libelle,
			String codesection) throws ElementNOtFoundException {
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			
			Cycle cycle=emtrans.getReference(Cycle.class,codeCycle);
			Section sect=emtrans.find(Section.class,codesection);
			if(sect==null)
				throw new ElementNOtFoundException(codesection, "Section");
			
			if(cycle!=null){
				// codesection non modifi�, juste merge
				cycle.setLibelle(libelle);
				cycle.setSection(sect);
				cycle.setSupprime(false);
				emtrans.merge(sect);
			}
			
		    emtrans.getTransaction().commit();
			
		}
		finally{
			emtrans.close();
			emfact.close();
		}
		
	}

	@Override
	public void modifierSection(String codesection, String description,
			String libelle, String enseignement) throws DuplicateKeyException, ElementNOtFoundException {
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			
			Section sect=emtrans.getReference(Section.class,codesection);
			Enseignement ens=emtrans.find(Enseignement.class,enseignement);
			if(ens==null)
				throw new ElementNOtFoundException(enseignement, "Enseignement");
			
			if(sect!=null){
				// codesection non modifi�, juste merge
				sect.setLibelle(libelle);
				sect.setEnseignement(ens);
				sect.setDescription(description);
				emtrans.merge(sect);
			}
			
		    emtrans.getTransaction().commit();
			
		}
		finally{
			emtrans.close();
			emfact.close();
		}
		
	}

	@Override
	public void modifierNiveau(String codeNiveau, String libelle,
			String codeCycle) throws ElementNOtFoundException {
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			
			Niveau nivo=emtrans.getReference(Niveau.class,codeNiveau);
			if(nivo==null)
				throw new ElementNOtFoundException(codeNiveau, "Niveau");
			
			
			Cycle cycle=emtrans.find(Cycle.class,codeCycle);
			if(cycle==null)
				throw new ElementNOtFoundException(codeCycle, "Cycle");
			
			nivo.setCycle(cycle);
			nivo.setLibelle(libelle);
			emtrans.merge(nivo);	
		    emtrans.getTransaction().commit();
			
		}
		finally{
			emtrans.close();
			emfact.close();
		}
		
	}

	

	@Override
	public void modifierEnseignement(String libelleens, String description,
			String type) throws ElementNOtFoundException {
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			
			Enseignement ens=emtrans.find(Enseignement.class,libelleens);
			if(ens==null) 
				throw new ElementNOtFoundException(libelleens, "Enseignement");
			
			ens.setType(type);
			ens.setDescription(description);
			emtrans.merge(ens);
							
		    emtrans.getTransaction().commit();
			
		}
		finally{
			emtrans.close();
			emfact.close();
		}		
	}
	
	@Override
	public void modifierOption(String codeoption, String libelle,
			String niveau) throws ElementNOtFoundException {
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			
			OptionSerie opt=emtrans.find(OptionSerie.class,codeoption);
			if(opt==null)
				throw new ElementNOtFoundException(codeoption, "Optionserie");
			Niveau nivo=emtrans.find(Niveau.class,niveau);
			if(nivo==null)
				throw new ElementNOtFoundException(niveau, "Niveau");
			
			opt.setCodeoption(codeoption);
			opt.setLibelle(libelle);
			opt.setNiveau(nivo);
			emtrans.merge(opt);		
		    emtrans.getTransaction().commit();
			
		}
		finally{
			emtrans.close();
			emfact.close();
		}		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void modifierAnnee(String anneeacademique,Date datedebut, Date datefin) throws ElementNOtFoundException, ChevauchementDateException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		Annee an =null;
		Query query;
		List<Annee> annees;
		
		try{
			emtrans.getTransaction().begin();
			
			try{
				query=emtrans.createNamedQuery("Annee.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("close", false);
				query.setParameter("annee", anneeacademique);
				an=(Annee) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(anneeacademique, "Annee");
			}
			
			query=emtrans.createQuery("select a from Annee as a where ((a.dateDebut<:datefin) or(a.dateFin>:datedebut)) and a.supprime=:supprime and a.anneeacademique<>:annee");
			query.setParameter("datedebut",datedebut, TemporalType.DATE);
			query.setParameter("datefin",datefin, TemporalType.DATE);
			query.setParameter("supprime",false);
			query.setParameter("annee",anneeacademique);
			annees=query.getResultList();
			if(annees!=null&&!annees.isEmpty())
				throw new ChevauchementDateException("Annee");
			
			an.setDateDebut(datedebut);
			an.setDateFin(datefin);
			
			emtrans.merge(an);
		    emtrans.getTransaction().commit();
		    
		}finally{
				emtrans.close();
				emf.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void modifierTrimestre(int numero, Date datedebut, Date datefin, Date dateimpression,
			String annee) throws ElementNOtFoundException, ChevauchementDateException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		Trimestre tri =null;
		Query query;
		List<Trimestre> trimestres;
	
		try{
			emtrans.getTransaction().begin();
			
			try{
				query=emtrans.createNamedQuery("Trimestre.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("numero", numero);
				query.setParameter("annee", annee);
				tri=(Trimestre) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(String.valueOf(numero+" Ann�e "+annee), "Trimestre");
			}
			
			query=emtrans.createQuery("select t from Trimestre as t where ((:datedebut between t.datedebut and t.datefin) or(:datefin between t.datedebut and t.datefin)) and t.supprime=:supprime and t.annee.anneeacademique=:annee and t.id.numerotrimestre<>:numero");
			query.setParameter("datedebut",datedebut, TemporalType.DATE);
			query.setParameter("datefin",datefin, TemporalType.DATE);
			query.setParameter("supprime",false);
			query.setParameter("numero",numero);
			query.setParameter("annee",annee);
			trimestres=query.getResultList();
			
			if(trimestres!=null&&!trimestres.isEmpty())
				throw new ChevauchementDateException("Trimestre");
			
			tri.setDatedebut(datedebut);
			tri.setDatefin(datefin);
			tri.setDateimpressionbulletin(dateimpression);
			
			emtrans.merge(tri);
		    emtrans.getTransaction().commit();
		    
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
				emtrans.close();
				emf.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void modifierSequence(int numero, Date datedebut, Date datefin,int trimestre,
			String annee) throws ElementNOtFoundException, ChevauchementDateException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		Sequence seq =null;
		Query query;
		List<Sequence> sequences;
		try{
			emtrans.getTransaction().begin();
			
			try{
				query=emtrans.createNamedQuery("Sequence.findOneByYear");
				query.setParameter("supprime", false);
				query.setParameter("numero", numero);
				query.setParameter("annee",annee );
				seq=(Sequence) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(String.valueOf(numero), "Sequence");
			}
			
			query=emtrans.createQuery("select s from Sequence as s where ((s.datedebut<:datefin) or(s.datefin>:datedebut)) and s.supprime=:supprime and s.trimestre.annee.anneeacademique=:annee and s.numerosequence<>:numero");
			query.setParameter("datedebut",datedebut, TemporalType.DATE);
			query.setParameter("datefin",datefin, TemporalType.DATE);
			query.setParameter("supprime",false);
			query.setParameter("numero", numero);
			query.setParameter("annee",annee );
			sequences=query.getResultList();
			if(sequences!=null&&!sequences.isEmpty())
				throw new ChevauchementDateException("Sequence");
			
			seq.setDatedebut(datedebut);
			seq.setDatefin(datefin);
			seq.setDateimpressionreleve(datefin);
			seq.setNumerosequence(numero);
			
			emtrans.merge(seq);
		    emtrans.getTransaction().commit();
		    
		}finally{
				emtrans.close();
				emf.close();
		}
	}

	@Override
	public void supprimerSection(String codesection) throws ElementNOtFoundException {
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			
			Section sect=emtrans.getReference(Section.class,codesection);
			if(sect==null) throw new ElementNOtFoundException(codesection, "Section");
			sect.setSupprime(true);
			emtrans.merge(sect);
		    emtrans.getTransaction().commit();
			
		}
		finally{
			emtrans.close();
			emfact.close();
		}
		
	}

	@Override
	public void supprimerEnseignement(String libelleens) throws ElementNOtFoundException {
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			
			Enseignement ens=emtrans.getReference(Enseignement.class,libelleens);
			if(ens==null) throw new ElementNOtFoundException(libelleens, "Enseignement");
			
			ens.setSupprime(true);
			
			emtrans.merge(ens);
		    emtrans.getTransaction().commit();
			
		}
		finally{
			emtrans.close();
			emfact.close();
		}
	
	}

	@Override
	public void supprimerNiveau(String codeniveau) throws ElementNOtFoundException {
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			
			Niveau nivo=emtrans.getReference(Niveau.class,codeniveau);
			if(nivo==null)
				throw new ElementNOtFoundException(codeniveau, "Niveau");
			
			nivo.setSupprime(true);			
			emtrans.merge(nivo);
		    emtrans.getTransaction().commit();
			
		}
		finally{
			emtrans.close();
			emfact.close();
		}
		
	}

	@Override
	public void supprimerCycle(String codecycle) throws ElementNOtFoundException {
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			
			Cycle cycle=emtrans.getReference(Cycle.class,codecycle);
			if(cycle==null)
				throw new ElementNOtFoundException(codecycle, "Cycle");
			
			cycle.setSupprime(true);
			emtrans.merge(cycle);		
			
		    emtrans.getTransaction().commit();
			
		}
		finally{
			emtrans.close();
			emfact.close();
		}
		
	}

	@Override
	public void supprimerOption(String codeoption) throws ElementNOtFoundException {
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			
			OptionSerie opt=emtrans.find(OptionSerie.class,codeoption);
			if(opt==null)
				throw new ElementNOtFoundException(codeoption, "Optionserie");
			opt.setSupprime(true);
			emtrans.merge(opt);		
		    emtrans.getTransaction().commit();
			
		}
		finally{
			emtrans.close();
			emfact.close();
		}
		
	}

	@Override
	public void supprimerEtablissement(String codeetablissement) throws ElementNOtFoundException {
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			
			Etablissement etab=emtrans.find(Etablissement.class,codeetablissement);
			if(etab==null) throw new ElementNOtFoundException(codeetablissement, "Etablissement");
			
			etab.setSupprime(true);
			
			emtrans.merge(etab);
		    emtrans.getTransaction().commit();
			
		}
		finally{
			emtrans.close();
			emfact.close();
		}
		
	}

	@Override
	public void supprimerAnnee(String anneeacademique) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		Annee an =null;
		Query query;
	
		try{
			emtrans.getTransaction().begin();
			
			try{
				query=emtrans.createNamedQuery("Annee.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("close", false);
				query.setParameter("annee", anneeacademique);
				an=(Annee) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(anneeacademique, "Annee");
			}
			
			an.setSupprime(true);
			
			emtrans.merge(an);
		    emtrans.getTransaction().commit();
		    
		}finally{
				emtrans.close();
				emf.close();
		}
	}

	@Override
	public void supprimerTrimestre(int numero, String annee) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		Trimestre tri =null;
		Query query;
	
		try{
			emtrans.getTransaction().begin();
			
			try{
				query=emtrans.createNamedQuery("Trimestre.findByCode");
				query.setParameter("supprime", false);
				query.setParameter("numero", numero);
				query.setParameter("annee", annee);
				tri=(Trimestre) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(String.valueOf(numero), "Trimestre");
			}
			
			tri.setSupprime(true);
			
			emtrans.merge(tri);
		    emtrans.getTransaction().commit();
		    
		}finally{
				emtrans.close();
				emf.close();
		}
	}

	@Override
	public void supprimerSequence(int numero, String annee) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		Sequence seq =null;
		Query query;
	
		try{
			emtrans.getTransaction().begin();
			
			try{
				query=emtrans.createNamedQuery("Sequence.findOneByYear");
				query.setParameter("supprime", false);
				query.setParameter("numero", numero);
				query.setParameter("annee",annee );
				seq=(Sequence) query.getSingleResult();
			}
			catch(NoResultException e){
				throw new ElementNOtFoundException(String.valueOf(numero), "Sequence");
			}
			
			seq.setSupprime(true);
			
			emtrans.merge(seq);
		    emtrans.getTransaction().commit();
		    
		}finally{
				emtrans.close();
				emf.close();
		}
	}

	@SuppressWarnings("rawtypes")
	private List extracted(Query query) {
		return query.getResultList();
	}

	public int nextNumeroSequence(String annee){
			Query req= em.createNamedQuery("Sequence.maxInsertedId");
			req.setParameter("annee", annee);
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
	
	public int nextNumeroTrimestre(String annee){
		Query req= em.createNamedQuery("Trimestre.maxInsertedId");
		req.setParameter("annee", annee);
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
	public List<Annee> listerAnneesAcademiques() {
		Query query=em.createNamedQuery("Annee.findAll");
		query.setParameter("supprime", false);
		return extracted(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OptionSerie> listerOptions(String niveauDemande) {
		
		Query query=em.createNamedQuery("OptionSerie.findByLevel");
		query.setParameter("supprime", false);
		query.setParameter("niveau", niveauDemande);
		return extracted(query);
	}

	@Override
	public void admodifierEtablissement(String codeetablissement, String nom,
			String logingest, String passgest) throws ElementNOtFoundException {
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			
			Etablissement etab=emtrans.getReference(Etablissement.class,codeetablissement);
			if(etab==null) throw new ElementNOtFoundException(codeetablissement, "Etablissement");
			
			etab.setLogin_gest(logingest);
			etab.setPass_gest(passgest);
			etab.setNometab(nom);
			
			etab.setSupprime(false);
			emtrans.merge(etab);
		    emtrans.getTransaction().commit();
			
		}
		finally{
			emtrans.close();
			emfact.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> rechercherSequencesTrimestre(int trimestre,
			String annee) {
			Query query=em.createNamedQuery("Trimestre.findSequences");
			query.setParameter("supprime", false);
			query.setParameter("numero", trimestre);
			query.setParameter("annee", annee);
			return extracted(query);
	}

	
}
