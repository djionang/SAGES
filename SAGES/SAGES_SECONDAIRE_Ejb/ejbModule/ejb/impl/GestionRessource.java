package ejb.impl;

import java.math.BigDecimal;
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

import utils.Repertoire;
import ages.exception.AnneeEnCoursNonDefinieException;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;
import ejb.GestionRessourceLocal;
import entities.Annee;
import entities.Classe;
import entities.CodePers;
import entities.Direction;
import entities.EnsTitulaire;
import entities.EnsTitulairePK;
import entities.Enseignant;
import entities.Etablissement;
import entities.OptionSerie;
import entities.Personnel;
import entities.Salle;
import entities.TypeSalle;
import entities.Utilisateur;


@Stateless(mappedName = "GestionRessource")
@TransactionManagement(TransactionManagementType.BEAN)
public class GestionRessource implements GestionRessourceLocal{

	
	@PersistenceContext(unitName="agespersist")
	EntityManager em;

	@Override
	public void enregistrerSalle(String codeSalle, int capacite,
			String description, int type,String libelle) throws DuplicateKeyException, ElementNOtFoundException {
		EntityManagerFactory emfact= Persistence.createEntityManagerFactory("agespersist");
		EntityManager em=emfact.createEntityManager();		
		
		try{
			Query query;
			em.getTransaction().begin();
			
			
			query=em.createNamedQuery("Salle.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("codesalle", codeSalle);
			
			Salle sal=null; 
			try{
				sal=(Salle) query.getSingleResult() ;
			}
			catch(NoResultException e1){
				Repertoire.logDebug("Salle de code "+codeSalle+" non trouvée, insertion possible", getClass());
			}
			catch(NonUniqueResultException e2){
				Repertoire.logDebug("Recherche Salle de code "+codeSalle+" ++ trouvée, Erreur de coherence de la BD", getClass());
				sal=new Salle();
			}
			if(sal!=null) throw new DuplicateKeyException(codeSalle);
			
			
			
			query=em.createNamedQuery("TypeSalle.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("id", type);
			TypeSalle typesalle=null;
			
			try{
				typesalle=(TypeSalle) query.getSingleResult();
			}
			catch(NoResultException e){
				Repertoire.logWarn("TypeSalle "+type+" non trouvé", getClass());
			}
			if(typesalle==null)
				throw new ElementNOtFoundException(String.valueOf(type), "TypeSalle");
			
			
			
			sal=new Salle();
			sal.setCapacite(capacite);
			sal.setCodesalle(codeSalle);
			sal.setDescription(description);
			sal.setTypesalle(typesalle);
			sal.setLibelle(libelle);
			sal.setSupprime(false);
			
			em.persist(sal);
			em.getTransaction().commit();
			
		}
		finally{
			em.close();
			emfact.close();
		}
		
	}

	@Override
	public void enregistrerClasse(String codeclasse, String annee,
			String option,  int contenancemax, String libelle,String salledefaut) throws ElementNOtFoundException, DuplicateKeyException, AnneeEnCoursNonDefinieException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			
			if(emtrans.find(Classe.class, codeclasse)!=null)
				throw new DuplicateKeyException(codeclasse);
			
			if(annee==null)
				throw new AnneeEnCoursNonDefinieException("Etablissement");
			
			Annee an=emtrans.find(Annee.class,annee);
			if(an==null) 
				throw new AnneeEnCoursNonDefinieException("Etablissement");
			
			OptionSerie opt=emtrans.find(OptionSerie.class, option);
			if(opt==null) throw new ElementNOtFoundException(option, "Option");
			
			Classe cla = new Classe();
			
			cla.setCodeclasse(codeclasse);
			cla.setAnnee(an);
			cla.setContenancemax(contenancemax);
			cla.setLibelle(libelle);
			cla.setOptionserie(opt);
			cla.setSalleDefaut(salledefaut);
			emtrans.persist(cla);
			emtrans.getTransaction().commit();
			
		}
		finally{
			emtrans.close();
			emf.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void enregistrerAtelier(String codeSalle, int capacite,
			String description, String libelle) throws DuplicateKeyException {
		EntityManagerFactory emfact= Persistence.createEntityManagerFactory("agespersist");
		EntityManager em=emfact.createEntityManager();		
		
		try{
			
			em.getTransaction().begin();
			//
			Salle sal =em.find(Salle.class, codeSalle) ;
			if(sal!=null) throw new DuplicateKeyException(codeSalle);
			
			Query query=em.createNamedQuery("TypeSalle.findBylibelle");
			query.setParameter("supprime", false);
			query.setParameter("libelle", "Atelier");
			List<TypeSalle> typesalles= query.getResultList();
			TypeSalle typesalle;
			if(typesalles==null||typesalles.isEmpty()){
				typesalle=new TypeSalle();
				typesalle.setLibelle("Atelier");
				typesalle.setDescription("Ateliers de travaux pratiques et techniques");
				em.persist(typesalle); 
			}else{
				typesalle=typesalles.get(0);
			}
				// enregistrer le type 
			sal=new Salle();
			sal.setCapacite(capacite);
			sal.setCodesalle(codeSalle);
			sal.setDescription(description);
			sal.setTypesalle(typesalle);
			sal.setLibelle(libelle);
			sal.setSupprime(false);
			
			em.persist(sal);
			em.getTransaction().commit();
			
		}
		finally{
			em.close();
			emfact.close();
		}
	}

	@Override
	public String enregistrerEnseignant(String nomens, String prenomens,
			String photo, String sexe, String adresse, String email,
			String telephone, String civilite, Date datearrivee,
			String loginens, String passens, String competences,
			boolean vacataire, BigDecimal salairehoraire, int travailmensuel) {
		
		String codeenseignant = null;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
	
			CodePers code=new CodePers();
			
			emtrans.persist(code);
			
			codeenseignant=Repertoire.genererCodeEnseignant(code.getCode());
			
			Personnel pers=new Personnel();
			pers.setCodepersonnel(codeenseignant);
			
			pers.setActif(true);
			pers.setAdresse(adresse);
			pers.setCivilite(civilite);
			pers.setDatearrivee(datearrivee);
			pers.setDatedepart(null);
			pers.setEmail(email);
			pers.setFonction("Enseignant");
			pers.setNom(nomens);
			pers.setPrenom(prenomens);
			pers.setSexe(sexe);
			pers.setSupprime(false);
			pers.setTelephone(telephone);
			
			emtrans.persist(pers);
			
			Utilisateur user=new Utilisateur();
			if(loginens==null || loginens.isEmpty())
				loginens=codeenseignant;
			user.setLogin(loginens);
			user.setPassword(passens);
			user.setSupprime(false);
			user.setPersonnel(pers);
			emtrans.persist(user);
			
			Enseignant ens=new Enseignant();
			ens.setCodeenseignant(codeenseignant);
			ens.setComptences(competences);
			ens.setNomens(nomens);
			ens.setPhoto(photo+codeenseignant+".jpg");
			ens.setPrenomens(prenomens);
			ens.setSalairehoraire(salairehoraire);
			ens.setSexe(sexe);
			ens.setSupprime(false);
			ens.setTravailmensuel(travailmensuel);
			ens.setUtilisateur(user);
			ens.setVacataire(vacataire);
			emtrans.persist(ens);
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
		return codeenseignant;
	}

	@Override
	public void enregistrerPersonnel(String nom, String prenom, String adresse,
			String email, String telephone, String civilite, Date datearrivee,
			String fonction, String sexe,String codeetablissement) throws ElementNOtFoundException {
		String codepers = null;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
	
			//recupérer l'etablissement concerné avant de faire des enregistrements
			Etablissement etab=emtrans.getReference(Etablissement.class,codeetablissement);
			if(etab==null) throw new ElementNOtFoundException(codeetablissement, "Etablissement");
			
			
			CodePers code=new CodePers();
			
			emtrans.persist(code);
			
			codepers=Repertoire.genererCodePersonnel(code.getCode());
			
			Personnel pers=new Personnel();
			pers.setCodepersonnel(codepers);
			
			pers.setActif(true);
			pers.setAdresse(adresse);
			pers.setCivilite(civilite);
			pers.setDatearrivee(datearrivee);
			pers.setDatedepart(null);
			pers.setEmail(email);
			pers.setFonction(fonction);
			pers.setNom(nom);
			pers.setPrenom(prenom);
			pers.setSexe(sexe);
			pers.setSupprime(false);
			pers.setTelephone(telephone);
			pers.setEtablissement(etab);
			
			emtrans.persist(pers);
			
			if(fonction.compareToIgnoreCase("Principal")==0||fonction.compareToIgnoreCase("Censeur")==0||fonction.compareToIgnoreCase("Surveillant")==0||fonction.compareToIgnoreCase("Intendant")==0){
				Utilisateur user=new Utilisateur();
				user.setLogin(pers.getCodepersonnel());
				user.setPassword(pers.getCodepersonnel());
				user.setSupprime(false);
				user.setPersonnel(pers);
				emtrans.persist(user);
				
				Direction dir=new Direction();
				dir.setFonction(fonction);
				dir.setLogin(codepers);
				dir.setSupprime(false);
				dir.setUtilisateur(user);
				emtrans.persist(dir);
			}
			else
				if(fonction.compareToIgnoreCase("Enseignant")==0){
					Utilisateur user=new Utilisateur();
					user.setLogin(pers.getCodepersonnel());
					user.setPassword(pers.getCodepersonnel());
					user.setSupprime(false);
					user.setPersonnel(pers);
					emtrans.persist(user);
					
					Enseignant ens=new Enseignant();
					ens.setCodeenseignant(codepers);
					ens.setNomens(nom);
					ens.setPhoto(codepers+".jpg");
					ens.setPrenomens(prenom);
					ens.setSexe(sexe);
					ens.setSupprime(false);
					ens.setUtilisateur(user);
					emtrans.persist(ens);
				}
					
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
		
	}

	@Override
	public void modifierSalle(String codesalle,String libelle, int type, int capacite,String description) throws ElementNOtFoundException {
		EntityManagerFactory emfact= Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();		
		
		try{
			emtrans.getTransaction().begin();
			Salle sal=emtrans.find(Salle.class,codesalle);
			if(sal==null) throw new ElementNOtFoundException(codesalle, "Salle");
			
			Query query=emtrans.createNamedQuery("TypeSalle.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("id", type);
			TypeSalle typesalle=(TypeSalle) query.getSingleResult();
			
			sal.setCapacite(capacite);
			sal.setLibelle(libelle);
			sal.setTypesalle(typesalle);
			sal.setDescription(description);
			
			emtrans.merge(sal);
			emtrans.getTransaction().commit();
			
		}
		finally{
			emtrans.close();
			emfact.close();
		}
		
	}

	@Override
	public void modifierClasse(String codeclasse, String annee,
			String option, int contenancemax, String libelle,String salledefaut) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			Annee an=emtrans.find(Annee.class,annee);
			if(an==null) throw new ElementNOtFoundException(annee, "Annee");
			
			OptionSerie opt=emtrans.find(OptionSerie.class, option);
			if(opt==null) throw new ElementNOtFoundException(option, "Option");
			
			Query query=emtrans.createNamedQuery("Classe.find");
			query.setParameter("annee", annee);
			query.setParameter("supprime", false);
			query.setParameter("codeclasse", codeclasse);
			query.setParameter("annee", annee);
			Classe cla =(Classe) query.getSingleResult();
			if(cla==null) throw new ElementNOtFoundException(codeclasse, "Classe");;
			
			
			cla.setAnnee(an);
			cla.setContenancemax(contenancemax);
			cla.setLibelle(libelle);
			cla.setOptionserie(opt);
			cla.setSalleDefaut(salledefaut);
			emtrans.merge(cla);
			emtrans.getTransaction().commit();
			
		}
		finally{
			emtrans.close();
			emf.close();
		}
		
	}

	@Override
	public void modifierEnseignant(String codeenseignant, String nomens,
			String prenomens, String photo, String sexe, String adresse,
			String email, String telephone, String civilite, Date datearrivee,
			String competences,BigDecimal salairehoraire, int travailmensuel) throws ElementNOtFoundException {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			Query req=emtrans.createNamedQuery("Enseignant.findByCode");
			req.setParameter("supprime", false);
			req.setParameter("code", codeenseignant);
			Enseignant e= (Enseignant) req.getSingleResult();
			
			if(e==null) 
				throw new ElementNOtFoundException(codeenseignant, "Enseignant");
			
			e.setComptences(competences);
			e.setNomens(nomens);
			
			e.setPrenomens(prenomens);
			if(e.isVacataire()){
				e.setSalairehoraire(salairehoraire);
				e.setTravailmensuel(travailmensuel);
				
			}
			
			e.setSexe(sexe);
			
			e.getUtilisateur().getPersonnel().setActif(true);
			e.getUtilisateur().getPersonnel().setAdresse(adresse);
			e.getUtilisateur().getPersonnel().setCivilite(civilite);
			e.getUtilisateur().getPersonnel().setDatearrivee(datearrivee);
			e.getUtilisateur().getPersonnel().setEmail(email);
			e.getUtilisateur().getPersonnel().setNom(nomens);
			e.getUtilisateur().getPersonnel().setPrenom(prenomens);
			e.getUtilisateur().getPersonnel().setSexe(sexe);
			e.getUtilisateur().getPersonnel().setTelephone(telephone);
			
			emtrans.merge(e);
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
		
	}

	@Override
	public void modifierStatut(String codeenseignant, boolean vacataire) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			Query req=emtrans.createNamedQuery("Enseignant.findByCode");
			req.setParameter("supprime", false);
			req.setParameter("code", codeenseignant);
			Enseignant e= (Enseignant) req.getSingleResult();
			
			if(e==null) throw new ElementNOtFoundException(codeenseignant, "Enseignant");
			
			e.setVacataire(vacataire);
			
			emtrans.merge(e);
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
	}

	@Override
	public void modifierPersonnel(String codepersonnel, String nom,
			String prenom, String adresse, String email, String telephone,
			String civilite, Date datearrivee, String fonction, String sexe) throws ElementNOtFoundException {
		
		String ancienneFonction;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			Query req=emtrans.createNamedQuery("Personnel.findByCode");
			req.setParameter("supprime", false);
			req.setParameter("codepersonnel", codepersonnel);
			Personnel p= (Personnel) req.getSingleResult();
			
			if(p==null) 
				throw new ElementNOtFoundException(codepersonnel, "Persponnel");
			
			ancienneFonction=p.getFonction();
			
			p.setAdresse(adresse);
			p.setCivilite(civilite);
			p.setDatearrivee(datearrivee);
			p.setEmail(email);
			p.setFonction(fonction);
			p.setNom(nom);
			p.setPrenom(prenom);
			p.setSexe(sexe);
			p.setTelephone(telephone);
			emtrans.merge(p);
			
			//Dans le cas où l'ancienne fonction est differente de la nouvelle, changement de table
			if(ancienneFonction.compareToIgnoreCase(fonction)!=0){
				if(ancienneFonction.compareToIgnoreCase("Enseignant")==0){
					Query query=emtrans.createNamedQuery("Enseignant.findByCode");
					query.setParameter("supprime", false);
					query.setParameter("code", codepersonnel); 
					Enseignant e= (Enseignant) query.getSingleResult();
					emtrans.remove(e);
					emtrans.flush();
					if(fonction.compareToIgnoreCase("P.A.")!=0){
						Direction dir=new Direction();
						dir.setFonction(fonction);
						dir.setSupprime(false);
						dir.setUtilisateur(e.getUtilisateur());
						dir.setLogin(e.getUtilisateur().getLogin());
						emtrans.persist(dir);
					}
					else{	// enseignant vers P.A. supprimer l'utilisateur
						
						Utilisateur user=e.getUtilisateur();
						emtrans.remove(user);
						emtrans.flush();
					}
				}
				else
					if(ancienneFonction.compareToIgnoreCase("Principal")==0||ancienneFonction.compareToIgnoreCase("Censeur")==0||ancienneFonction.compareToIgnoreCase("Surveillant")==0||ancienneFonction.compareToIgnoreCase("Intendant")==0){
						Query query=emtrans.createQuery("select d from Direction d join d.utilisateur u join u.personnel p where p.codepersonnel=:code");
						query.setParameter("code", codepersonnel);
						Direction dir=(Direction) query.getSingleResult();
						emtrans.remove(dir);
						if(fonction.compareToIgnoreCase("P.A.")!=0){
							Enseignant ens=new Enseignant();
							ens.setCodeenseignant(codepersonnel);
							ens.setNomens(nom);
							ens.setPrenomens(prenom);
							ens.setSexe(sexe);
							ens.setSupprime(false);
							ens.setVacataire(false);
							ens.setUtilisateur(dir.getUtilisateur());
							
							emtrans.persist(ens);
						}
						else{	// Direction vers P.A. supprimer l'utilisateur
														
							Utilisateur user=(Utilisateur) dir.getUtilisateur();
							emtrans.remove(user);
							emtrans.flush();
						}
					}
					else{  // Ancien était P.A.
						if(fonction.compareToIgnoreCase("Enseignant")==0){
							Utilisateur user=new Utilisateur();
							user.setLogin(codepersonnel);
							user.setPassword(codepersonnel);
							user.setSupprime(false);
							user.setPersonnel(p);
							emtrans.persist(user);
							
							Enseignant ens=new Enseignant();
							ens.setCodeenseignant(codepersonnel);
							ens.setNomens(nom);
							ens.setPrenomens(prenom);
							ens.setSexe(sexe);
							ens.setSupprime(false);
							ens.setVacataire(false);						
							
							ens.setUtilisateur(user);
							
							emtrans.persist(ens);
						}
						else{  //P.A. Vers Direction
							Utilisateur user=new Utilisateur();
							user.setLogin(codepersonnel);
							user.setPassword(codepersonnel);
							user.setSupprime(false);
							user.setPersonnel(p);
							emtrans.persist(user);
							emtrans.flush();
							
							Direction dir=new Direction();
							dir.setLogin(codepersonnel);
							dir.setUtilisateur(user);
							dir.setFonction(fonction);
							dir.setSupprime(false);
							emtrans.persist(dir);
							emtrans.flush();
						}
					}
			}			
			
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
	}

	@Override
	public void modifierTypeSalle(int id, String libelle, String description) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			Query query=emtrans.createNamedQuery("TypeSalle.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("id", id);
			
			TypeSalle tps=null;
			try{
				tps= (TypeSalle) query.getSingleResult();
			}
			catch(NoResultException e1){
				Repertoire.logDebug("Type Salle de code "+id+" non trouvé, suppression impossible", getClass());
			}
			catch(NonUniqueResultException e2){
				Repertoire.logDebug("Recherche Salle de code "+id+" ++ trouvée, Erreur de coherence de la BD", getClass());
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
	public void modifierDelegues(String codeclasse, String annee,
			String delegue1, String delegue2,String titulaire) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			Annee an=emtrans.find(Annee.class,annee);
			if(an==null) throw new ElementNOtFoundException(annee, "Annee");
			
			Query query=emtrans.createNamedQuery("Classe.findById");
			query.setParameter("supprime", false);
			query.setParameter("codeclasse", codeclasse);
			Classe cla =(Classe) query.getSingleResult();
			if(cla==null) 
				throw new ElementNOtFoundException(codeclasse, "Classe");
			
			Enseignant ens=rechercheEnseignant(titulaire, emtrans);
			if(ens==null) 
				throw new ElementNOtFoundException(titulaire, "Enseignant");
			
			cla.setDelegue1(delegue1);
			cla.setDelegue2(delegue2);
			emtrans.merge(cla);
			
			
			query=emtrans.createQuery("delete from EnsTitulaire e where e.annee.anneeacademique='"+annee+"' and e.classe.codeclasse='"+codeclasse+"' and e.supprime="+false);
			query.executeUpdate();
			emtrans.flush();
			
			EnsTitulairePK id=new EnsTitulairePK();
			id.setAnneeacademique(annee);
			id.setCodeclasse(codeclasse);
			id.setCodeenseignant(titulaire);
			
			EnsTitulaire titus=new EnsTitulaire();
			titus.setAnnee(an);
			titus.setClasse(cla);
			titus.setId(id);
			titus.setEnseignant(ens);
			titus.setSupprime(false);
			
			emtrans.persist(titus);
			
			emtrans.getTransaction().commit();
			
		}
		finally{
			emtrans.close();
			emf.close();
		}
	}

	@Override
	public void supprimerSalle(String codesalle) throws ElementNOtFoundException {
		EntityManagerFactory emfact=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emfact.createEntityManager();
		
		
		try{
			emtrans.getTransaction().begin();
			Salle sal=emtrans.find(Salle.class,codesalle);
			if(sal==null) throw new ElementNOtFoundException(codesalle, "Salle");
			
			sal.setSupprime(true);
			emtrans.merge(sal);
			emtrans.getTransaction().commit();
		}
		finally{			
			emtrans.close();
			emfact.close();
		}
		
	}

	@Override
	public void supprimerClasse(String codeclasse, String annee) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			Query query=emtrans.createNamedQuery("Classe.find");
			query.setParameter("annee", annee);
			query.setParameter("supprime", false);
			query.setParameter("codeclasse", codeclasse);
			query.setParameter("annee", annee);
			Classe cla =(Classe) query.getSingleResult();
			if(cla==null) throw new ElementNOtFoundException(codeclasse, "Classe");;
			
			cla.setSupprime(true);
			emtrans.merge(cla);
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
	}

	@Override
	public void supprimerEnseignant(String codeenseignant) throws ElementNOtFoundException {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			Query req=emtrans.createNamedQuery("Enseignant.findByCode");
			req.setParameter("supprime", false);
			req.setParameter("code", codeenseignant);
			Enseignant e= (Enseignant) req.getSingleResult();
			
			if(e==null) throw new ElementNOtFoundException(codeenseignant, "Enseignant");
			e.setSupprime(true);
			emtrans.merge(e);
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
		
	}

	@Override
	public void supprimerPersonnel(String codepersonnel) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			Query req=emtrans.createNamedQuery("Personnel.findByCode");
			req.setParameter("supprime", false);
			req.setParameter("codepersonnel", codepersonnel);
			Personnel p= (Personnel) req.getSingleResult();
			
			if(p==null) throw new ElementNOtFoundException(codepersonnel, "Persponnel");
			p.setSupprime(true);
			emtrans.merge(p);
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Salle> listerSalle() {
		
		Query req=em.createNamedQuery("Salle.findAll");
		req.setParameter("supprime", false);
		
	    return (List<Salle>) req.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeSalle> listerTypesSalles() {
		Query query=em.createNamedQuery("TypeSalle.findAll");
		query.setParameter("supprime", false);
		return (List<TypeSalle>)query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listerClasseWithSize(String annee){
		
		Query query=em.createNamedQuery("Classe.ListWithStudents");
		query.setParameter("supprime", false);
		return query.getResultList();
			
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> listerClasseCours(String annee){
		
		Query query=em.createNamedQuery("Classe.ListWithCours");
		query.setParameter("supprime", false);
		query.setParameter("annee", annee);
		return query.getResultList();
			
	}

	@Override
	public List<Salle> listerOccupationsSalle(String codesalle, String annee) {
		/*Query query=em.createNamedQuery("Salle.findUsages");
		query.setParameter("codesalle", codesalle);
		query.setParameter("annee", annee);
		query.setParameter("supprime", false);
		return query.setMaxResults(1).getResultList();*/
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Personnel> listerPersonnels() {
		Query req=em.createNamedQuery("Personnel.findAll");
		req.setParameter("supprime", false);
		
	    return (List<Personnel>) req.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Enseignant> listerEnseignants() {
		Query req=em.createNamedQuery("Enseignant.findAll");
		req.setParameter("supprime", false);
		
	    return (List<Enseignant>) req.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Personnel> listerPersonnelsLike(String queri) {
		String filtreNom="";
		String filtrePrenom="";
		String filtreCode="";
		String[] mots=queri.trim().replaceAll(" ", ";").split(";");
		
		for(int i=1;i<mots.length;i++){
			if(!mots[i].isEmpty()){
				filtreNom+=" or p.nom LIKE '%"+mots[i]+"%' ";
				filtrePrenom+=" or p.prenom LIKE '%"+mots[i]+"%' ";
				filtreCode+=" or p.codepersonnel LIKE '%"+mots[i]+"%' ";
			}			
		}
		
		String requete="select p from Personnel as p where (p.nom LIKE '%"+mots[0]+"%' "+filtreNom+" or p.prenom LIKE '%"+mots[0]+"%'"+filtrePrenom+" or p.codepersonnel LIKE '%"+mots[0]+"%'"+filtreCode+") and p.supprime=:supprime";
				
		Query query=em.createQuery(requete);
		query.setParameter("supprime", false);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listerClasseWithSize(String codeoption,
			String annee) {
		Query query=em.createNamedQuery("Classe.ListByOptionWithStudents");
		query.setParameter("annee", annee);
		query.setParameter("supprime", false);
		query.setParameter("option", codeoption);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listerEnseignantsClasse(String codeclasse) {
		Query req=em.createNamedQuery("Enseignant.findByClasse");
		req.setParameter("supprime", false);
		req.setParameter("codeclasse", codeclasse);
		
	    return req.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Enseignant> listerEnseignants(String codeClasse) {
		Query req=em.createNamedQuery("Enseignant.findAllForClass");
		req.setParameter("supprime", false);
		req.setParameter("codeclasse", codeClasse);
		
	    return (List<Enseignant>) req.getResultList();
	}

	@Override
	public Salle rechercherSalle(String codesalle) {
		return em.find(Salle.class, codesalle);
		
	}

	@Override
	public Enseignant rechercheEnseignant(String codeenseignant) {
		Query req=em.createNamedQuery("Enseignant.findByCode");
		req.setParameter("supprime", false);
		req.setParameter("code", codeenseignant);
		return (Enseignant) req.getSingleResult();
	}

	@Override
	public Personnel recherchePersonnel(String codepersonnel) {
		Query req;
		try{
			req=em.createNamedQuery("Personnel.findByCode");
			req.setParameter("supprime", false);
			req.setParameter("codepersonnel", codepersonnel);
			return (Personnel) req.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
		
	}

	private Enseignant rechercheEnseignant(String codeenseignant,
			EntityManager emtrans) {
		try{
			Query req=emtrans.createNamedQuery("Enseignant.findByCode");
			req.setParameter("supprime", false);
			req.setParameter("code", codeenseignant);
			return (Enseignant) req.getSingleResult();
		}
		catch(Exception e){
			return null;
		}
		
	}

	public Classe rechercheclasse(String codeclasse){
		
		Classe cla;
		try{
			Query req=em.createNamedQuery("Classe.findById");
			req.setParameter("supprime", false);
			req.setParameter("codeclasse", codeclasse);
			
		    cla=(Classe) req.getSingleResult();
		}
		catch(NoResultException e){
			Repertoire.logInfo("Recherche classe infructueuse "+codeclasse, this.getClass());
			cla=null;
		}
		return cla;
		
	}
	
  public EnsTitulaire rechercheTitulaire(String annee,String codeclasse){
		
	EnsTitulaire titu;
		try{
			Query req=em.createNamedQuery("EnsTitulaire.findByAn");
			req.setParameter("supprime", false);
			req.setParameter("annee", annee);
			req.setParameter("codeclasse", codeclasse);
			
			titu=(EnsTitulaire) req.getSingleResult();
		}
		catch(NoResultException e){
			Repertoire.logInfo("Recherche titulaire infructueuse "+annee, this.getClass());
			titu=null;
		}
		return titu;
		
	}

	public Enseignant rechercherenseignant(String codeclasse){
		
		Enseignant ens;
		try{
			Query req=em.createNativeQuery("select e from enseignant as e, enstitulaire as enst, classe as cla where " +
					"enst.codeclasse=cla.codeclasse and enst.codeenseignant= e.codeenseignant and enst.codeclasse="+codeclasse+" and e.supprime="+false+"");
			
		    ens=(Enseignant) req.getSingleResult();
		}
		catch(NoResultException e){
			Repertoire.logInfo("Recherche enseignant titulaire infructueux "+codeclasse, this.getClass());
			ens=null;
		}
		return ens;
		
	}

	@Override
	public TypeSalle rechercherTypeSalle(int id) {
		
		Query query=em.createNamedQuery("TypeSalle.findByCode");
		query.setParameter("supprime", false);
		query.setParameter("id", id);
		TypeSalle tps=null;
		try{
			tps= (TypeSalle) query.getSingleResult();
		}
		catch(NoResultException e1){
			Repertoire.logDebug("Type Salle de code "+id+" non trouvé, suppression impossible", getClass());
		}
		catch(NonUniqueResultException e2){
			Repertoire.logDebug("Type Salle de code "+id+" ++ trouvé, Erreur de coherence de la BD", getClass());
		}
		
		return tps;
		
	}

	@Override
	public void enregistrerTypeSalle(String libelle, String description) throws DuplicateKeyException {
		EntityManagerFactory emfact= Persistence.createEntityManagerFactory("agespersist");
		EntityManager em=emfact.createEntityManager();		
		
		try{
			em.getTransaction().begin();
			
			Query query=em.createNamedQuery("TypeSalle.findBylibelle");
			query.setParameter("supprime", false);
			query.setParameter("libelle",libelle);
			
			TypeSalle tps=null;
			try{
				tps= (TypeSalle) query.getSingleResult();
			}
			catch(NoResultException e1){
				Repertoire.logDebug("Type Salle de code "+libelle+" non trouvé, suppression impossible", getClass());
			}
			catch(NonUniqueResultException e2){
				Repertoire.logDebug("Recherche Salle de code "+libelle+" ++ trouvée, Erreur de coherence de la BD", getClass());
				tps=new TypeSalle();
			}
			
			if(tps!=null)
				throw new DuplicateKeyException("TypeSalle= "+libelle+" Existant");
			tps=new TypeSalle();
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
	public void supprimerTypeSalle(int id) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		
		try{
			emtrans.getTransaction().begin();
			Query query=emtrans.createNamedQuery("TypeSalle.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("id", id);
			TypeSalle tps=null;
			try{
				tps= (TypeSalle) query.getSingleResult();
			}
			catch(NoResultException e1){
				Repertoire.logDebug("Type Salle de code "+id+" non trouvé, suppression impossible", getClass());
			}
			catch(NonUniqueResultException e2){
				Repertoire.logDebug("Recherche Salle de code "+id+" ++ trouvée, Erreur de coherence de la BD", getClass());
				return;
			}
			
			if(tps==null) throw new ElementNOtFoundException(String.valueOf(id), "typeSalle");
			
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
	public List<Object[]> listerClasseWithSize() {
		Query query=em.createNamedQuery("Classe.ListWithStudents");
		query.setParameter("close", true);
		query.setParameter("supprime", false);
		return query.getResultList();
	}

	@Override
	public int effectif(String anneeEncours,Classe cla) {
		Query query=em.createNamedQuery("Classe.comp");
		query.setParameter("annee", anneeEncours);
		query.setParameter("supprime", false);
		query.setParameter("codeclasse", cla.getCodeclasse());
		return Integer.parseInt(query.getSingleResult().toString()) ;
	}


		
}
