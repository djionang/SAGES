package ejb.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;
import ages.exception.UnAuthorizedOperationException;
import ejb.GestionUtilisateurLocal;
import entities.Configuration;
import entities.Etablissement;
import entities.GpUserRole;
import entities.GroupeUser;
import entities.ItemRole;
import entities.Personnel;
import entities.Utilisateur;
import entities.UtilisateurGroupe;


@Stateless(mappedName = "GestionUtilisateur")
@TransactionManagement(TransactionManagementType.BEAN)
public class GestionUtilisateur implements GestionUtilisateurLocal{

	
	@PersistenceContext(unitName="agespersist")
	EntityManager em;

	@Override
	public int enregistrerItemRole(String libelle, String description) throws DuplicateKeyException {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		int iditem=-1;
		ItemRole item;
		
		try{
			emtrans.getTransaction().begin();
			
			item=rechercherItemRole(libelle, emtrans);
			if(item!=null)
				throw new DuplicateKeyException("Item role "+libelle+" Dupliqué");		
			
			
			item=new ItemRole();
			item.setDescription(description);
			item.setLibelle(libelle);
			item.setSupprime(false);
			
			emtrans.persist(item);
			emtrans.getTransaction().commit();
			iditem=item.getIditem();
		}
		finally{
			emtrans.close();
			emf.close();
		}
		return iditem;
	}
	

	public ItemRole rechercherItemRole(String libelle){
		return rechercherItemRole(libelle, em);
	}

	@Override
	public ItemRole rechercherItemRole(int iditem) {
		return rechercherItemRole(iditem, em);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ItemRole> listerItemroles() {
		Query query=em.createNamedQuery("ItemRole.findAll");
		query.setParameter("supprime", false);
		return query.getResultList();
	}


	@Override
	public void modifierItemRole(int iditem, String libelle, String description) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		ItemRole item;
		try{
			emtrans.getTransaction().begin();
			item=rechercherItemRole(iditem, emtrans);
			if(item==null)
				throw new ElementNOtFoundException("ItemRole",String.valueOf(iditem));
		
			item.setDescription(description);
			item.setLibelle(libelle);
			item.setSupprime(false);
			emtrans.merge(item);
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
	}


	@Override
	public void supprimerItemRole(int iditem) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		ItemRole item;
		try{
			emtrans.getTransaction().begin();
			item=rechercherItemRole(iditem, emtrans);
			if(item==null)
				throw new ElementNOtFoundException("ItemRole",String.valueOf(iditem));
		
			item.setSupprime(true);
			emtrans.merge(item);
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
	}


	@Override
	public Utilisateur rechercherUtilisateur(String user) {
		
		return rechercherUtilisateur(user, em);
		
	}
	
	public Utilisateur rechercherUtilisateur(int iduser){
		return rechercherUtilisateur(iduser, em);		
	}


	@Override
	public void modifierUtilisateur(String login, String password,
			String ancienmotdepasse, int iduser) throws UnAuthorizedOperationException, ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		Utilisateur user;
		try{
			emtrans.getTransaction().begin();
			user=rechercherUtilisateur(iduser, emtrans);
			if(user==null)
				throw new ElementNOtFoundException("utilisateur",String.valueOf(iduser));
			
			if(user.getPassword().compareTo(ancienmotdepasse)==0)
				throw new UnAuthorizedOperationException("Modifier User", String.valueOf(iduser));
			
			user.setLogin(login);
			user.setPassword(password);
			emtrans.merge(user);
		}
		finally{
			emtrans.close();
			emf.close();
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<GroupeUser> listerGroupesUser() {
		Query query=em.createNamedQuery("GroupeUser.findAll");
		query.setParameter("supprime", false);
		return query.getResultList();
	}


	@Override
	public void enregistrerUtilisateur(String codepersonnel, String login,
			String password, List<Integer> groupesusers) throws DuplicateKeyException, ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();

		Utilisateur user;
		Personnel pers;
		
		
		try{
			emtrans.getTransaction().begin();
			
			user=rechercherUtilisateur(login, emtrans);
			if(user!=null)
				throw new DuplicateKeyException("Login-Utilisateur="+login);
			
			pers=rechercherPersonnel(codepersonnel, emtrans);
			if(pers==null)
				throw new ElementNOtFoundException(codepersonnel, "Personnel");
		
			user=new Utilisateur();
			user.setLogin(login);
			user.setPassword(password);
			user.setPersonnel(pers);
			
			user.setSupprime(false);
			
			emtrans.persist(user);
			emtrans.flush();
			
			for(int i=0;i<groupesusers.size();i++){
				GroupeUser groupe=rechercherGroupeUser(groupesusers.get(i),emtrans);
				UtilisateurGroupe ugp=new UtilisateurGroupe();
				ugp.setGroupeuser(groupe);
				ugp.setSupprime(false);
				ugp.setUtilisateur(user);
	
				emtrans.persist(ugp);
			}
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
	}

	private GroupeUser rechercherGroupeUser(int idgroupe, EntityManager emtrans) {
		Query query;
		
		try{
			query=em.createNamedQuery("GroupeUser.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("idgroupe", idgroupe);
			return (GroupeUser) query.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}


	private Utilisateur rechercherUtilisateur(int iduser, EntityManager em){
		Query query;
		
		try{
			query=em.createNamedQuery("Utilisateur.findById");
			query.setParameter("supprime", false);
			query.setParameter("iduser", iduser);
			return (Utilisateur) query.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}
	
	private Utilisateur rechercherUtilisateur(String user,EntityManager em) {
		Query query;
		
		try{
			query=em.createNamedQuery("Utilisateur.findByLogin");
			query.setParameter("supprime", false);
			query.setParameter("login", user);
			return (Utilisateur) query.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
		
	}
	
	private ItemRole rechercherItemRole(int iditem, EntityManager em) {
		Query query;
		
		try{
			query=em.createNamedQuery("ItemRole.findById");
			query.setParameter("supprime", false);
			query.setParameter("iditem",iditem);
			
			return (ItemRole) query.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}
	
	private ItemRole rechercherItemRole(String libelle, EntityManager em){
		Query query;
		
		try{
			query=em.createNamedQuery("ItemRole.findBylibelle");
			query.setParameter("supprime", false);
			query.setParameter("libelle",libelle);
			
			return (ItemRole) query.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}
	
	
	private Personnel rechercherPersonnel(String codepersonnel, EntityManager em){
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
	
	
	private GroupeUser rechercherGroupeUser(String libelle, EntityManager emtrans) {
		Query query;
		
		try{
			query=em.createNamedQuery("GroupeUser.findByLabel");
			query.setParameter("supprime", false);
			query.setParameter("libelle", libelle);
			return (GroupeUser) query.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}
	

	private GroupeUser rechercherGroupeUserL(String libelle, EntityManager emtrans) {
		Query query;
		
		try{
			query=em.createNamedQuery("GroupeUser.findByLabel");
			query.setParameter("supprime", false);
			query.setParameter("libelle", libelle);
			return (GroupeUser) query.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<String> rechercherRolesUtilisateur(String login) {
		List<String> roles;
		Set<String> result=new HashSet<String>();
		
		Query req=em.createQuery("select gpr.libelle from Utilisateur as user join user.utilisateurgroupes as gps join gps.groupeuser as gu join gu.gpUserRoles as gpr where user.login=:login and gpr.supprime="+false+" and gu.supprime="+false);
		
		req.setParameter("login", login);
		
	    roles= req.getResultList();		
		
		for(String role:roles){
			result.add(role);
		}
		return result;
	}


	@Override
	public int enregistrerGroupeUtilisateur(String libelle, String description,
			Map<Integer, List<Boolean>> roles) throws DuplicateKeyException, ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();

		GroupeUser gpuser;
		
		try{
			emtrans.getTransaction().begin();
			
			gpuser=rechercherGroupeUser(libelle, emtrans);
			if(gpuser!=null)
				throw new DuplicateKeyException("Groupe-Utilisateur="+libelle);
			
			
			gpuser=new GroupeUser();
			gpuser.setLibelle(libelle);
			gpuser.setDescription(description);
			
			gpuser.setSupprime(false);
			
			emtrans.persist(gpuser);
			emtrans.flush();
			// now gere les relation avec role
			
			for(int iditem:roles.keySet()){
				List<Boolean> value=roles.get(iditem);
				
				// si les 4 valeurs sont true
				if(value.get(0)==true&&value.get(1)==true&&value.get(2)==true&&value.get(3)==true){
					enregistrerManager(iditem,gpuser,emtrans);
				}
				else{
					for(int i=0;i<4;i++){
						if(value.get(i))
							enregistrerGroupeRole(iditem,i,gpuser,emtrans);
					}
				}
			}
			
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
		return gpuser.getIdgroupe();
	}


	private void enregistrerGroupeRole(int iditem, int i, GroupeUser gpuser,
			EntityManager emtrans) throws ElementNOtFoundException {
		String suffixrole="";
		switch(i){
			case 0:suffixrole="_CREATE";break;
			case 1:suffixrole="_UPDATE";break;
			case 2:suffixrole="_DELETE";break;
			case 3:suffixrole="_VIEW";break;
		}
		
		ItemRole role=rechercherItemRole(iditem, emtrans);
		if(role==null)
			throw new ElementNOtFoundException(String.valueOf(iditem),"ItemRole");
		
		GpUserRole gpr=new GpUserRole();
		gpr.setGroupeuser(gpuser);
		gpr.setRole(role);
		gpr.setSupprime(false);
		gpr.setLibelle("ROLE_"+role.getLibelle()+suffixrole);
		emtrans.persist(gpr);
		emtrans.flush();
	}


	private void enregistrerManager(int iditem, GroupeUser gpuser,
			EntityManager emtrans) throws ElementNOtFoundException {
		ItemRole role=rechercherItemRole(iditem, emtrans);
		if(role==null)
			throw new ElementNOtFoundException(String.valueOf(iditem),"ItemRole");
		
		GpUserRole gpr=new GpUserRole();
		gpr.setGroupeuser(gpuser);
		gpr.setRole(role);
		gpr.setSupprime(false);
		gpr.setLibelle("ROLE_"+role.getLibelle()+"_MANAGER");
		emtrans.persist(gpr);
		emtrans.flush();
	}


	@Override
	public GroupeUser rechercherGroupeUser(int idgroupe) {
		return rechercherGroupeUser(idgroupe, em);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<GpUserRole> listerRoles(int idgroupe) {
		Query query=em.createNamedQuery("GpUserRole.findByGroupeUser");
		query.setParameter("supprime", false);
		query.setParameter("idgroupe", idgroupe);
		return query.getResultList();
			
	}


	@Override
	public void modifierGroupeUtilisateur(int idgroupe, String libelle,
			String description, Map<Integer, List<Boolean>> roles) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		GroupeUser gpuser;
		try{
			emtrans.getTransaction().begin();
			gpuser=rechercherGroupeUser(idgroupe, emtrans);
			if(gpuser==null)
				throw new ElementNOtFoundException("Groupe utilisateur",String.valueOf(idgroupe));
			
			Query query=emtrans.createQuery("delete from GpUserRole gpr where gpr.groupeuser.idgroupe="+idgroupe);
			query.executeUpdate();
			
			gpuser.setLibelle(libelle);
			gpuser.setDescription(description);
			
			gpuser.setSupprime(false);
			gpuser.setGpUserRoles(null);
			emtrans.merge(gpuser);
			emtrans.flush();
			// now gere les relation avec role
			
			for(int iditem:roles.keySet()){
				List<Boolean> value=roles.get(iditem);
				
				// si les 4 valeurs sont true
				if(value.get(0)==true&&value.get(1)==true&&value.get(2)==true&&value.get(3)==true){
					enregistrerManager(iditem,gpuser,emtrans);
				}
				else{
					for(int i=0;i<4;i++){
						if(value.get(i))
							enregistrerGroupeRole(iditem,i,gpuser,emtrans);
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
	public void supprimerGroupeUser(int idgroupe) throws ElementNOtFoundException {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		GroupeUser gu;
		try{
			emtrans.getTransaction().begin();
			gu=rechercherGroupeUser(idgroupe, emtrans);
			if(gu==null)
				throw new ElementNOtFoundException("Groupe utilisateur",String.valueOf(idgroupe));
						
			gu.setSupprime(true);
			emtrans.merge(gu);
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
		
		
	}
	
	public void modifierGroupeUser( String libelle,
			float montant) throws UnAuthorizedOperationException, ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		GroupeUser gu;
		try{
			emtrans.getTransaction().begin();
			gu=rechercherGroupeUserL(libelle, emtrans);
			if(gu==null)
				throw new ElementNOtFoundException("utilisateur",String.valueOf(libelle));
			gu.setMontant(montant);
			emtrans.merge(gu);
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> listerComptesUtilisateurs() {
		Query query=em.createNamedQuery("Utilisateur.findAll");
		return query.getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> listerGroupesUserCodes() {
		Query query=em.createQuery("select g.idgroupe from GroupeUser as g where g.supprime="+false);
		
		return query.getResultList();
	}


	@Override
	public void modifierUtilisateur(int idcompte, String codepersonnel,
			String login, String password, List<Integer> groupesusers) throws ElementNOtFoundException, DuplicateKeyException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();

		Utilisateur user;
		Utilisateur user2;
		Personnel pers;
		
		
		try{
			emtrans.getTransaction().begin();
			
			user=rechercherUtilisateur(idcompte, emtrans);
			if(user==null)
				throw new ElementNOtFoundException(String.valueOf(idcompte), "Utilisateur");
			
			if(login.compareTo(user.getLogin())!=0){	// le gars veut changer de login
				user2=rechercherUtilisateur(login, emtrans);
				
				if(user2!=null)
					throw new DuplicateKeyException("Login-Utilisateur="+login);
			}
			
			
			pers=rechercherPersonnel(codepersonnel, emtrans);
			if(pers==null)
				throw new ElementNOtFoundException(codepersonnel, "Personnel");
		
			user.setLogin(login);
			user.setPassword(password);
			user.setPersonnel(pers);
			
			user.setSupprime(false);
			user.setUtilisateurgroupes(new ArrayList<UtilisateurGroupe>());
			
			emtrans.merge(user);
			emtrans.flush();
			
			for(int i=0;i<groupesusers.size();i++){
				GroupeUser groupe=rechercherGroupeUser(groupesusers.get(i),emtrans);
				UtilisateurGroupe ugp=new UtilisateurGroupe();
				ugp.setGroupeuser(groupe);
				ugp.setSupprime(false);
				ugp.setUtilisateur(user);
				emtrans.persist(ugp);
			}
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
	}


	@Override
	public void supprimerUtilisateur(int idcompte) throws ElementNOtFoundException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
		Utilisateur user;
		try{
			emtrans.getTransaction().begin();
			user=rechercherUtilisateur(idcompte, emtrans);
			System.out.println("le code utilisateur est "+ idcompte);
			if(user==null)
				throw new ElementNOtFoundException("Utilisateur",String.valueOf(idcompte));
			user.setSupprime(true);
			emtrans.merge(user);
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
	}


	@Override
	public void enregistrerGestionnaire(String login, String password) throws DuplicateKeyException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();

		Utilisateur user;
		
		
		try{
			emtrans.getTransaction().begin();
			
			user=rechercherUtilisateur(login, emtrans);
			if(user!=null)
				throw new DuplicateKeyException("Login-Utilisateur="+login);
			
			user=new Utilisateur();
			user.setLogin(login);
			user.setPassword(password);
			
			user.setSupprime(false);
			
			emtrans.persist(user);
			emtrans.flush();
			
			GroupeUser groupe=rechercherGroupeAdmin(emtrans);
			UtilisateurGroupe ugp=new UtilisateurGroupe();
			ugp.setGroupeuser(groupe);
			ugp.setSupprime(false);
			ugp.setUtilisateur(user);
	
			emtrans.persist(ugp);
			
			emtrans.getTransaction().commit();
		}
		finally{
			emtrans.close();
			emf.close();
		}
	}


	private GroupeUser rechercherGroupeAdmin(EntityManager emtrans) {
		Query query;
		GroupeUser gpuser=null;
		ItemRole ir=null;
		try{
			query=emtrans.createNamedQuery("GroupeUser.findByLabel");
			query.setParameter("supprime", false);
			query.setParameter("libelle", "MANAGER");
			return (GroupeUser) query.getSingleResult();
		}
		catch(NoResultException e){
			try {
				ir=rechercherItemRole("MANAGER", emtrans);
				if(ir==null){
					enregistrerItemRole("MANAGER", "MANAGER");
					ir=rechercherItemRole("MANAGER", emtrans);
				}				

				gpuser=new GroupeUser();
				gpuser.setLibelle("MANAGER");
				gpuser.setDescription("MANAGER");
				
				gpuser.setSupprime(false);
				
				emtrans.persist(gpuser);
				emtrans.flush();
				// now gere les relation avec role
								
				GpUserRole gpr=new GpUserRole();
				gpr.setGroupeuser(gpuser);
				gpr.setRole(ir);
				gpr.setSupprime(false);
				gpr.setLibelle("ROLE_ALLACCESS");
				emtrans.persist(gpr);
				emtrans.flush();
								
				
			} catch (DuplicateKeyException e1) {
				e1.printStackTrace();
			}
			return gpuser;
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> listerManager() {
		Query query=em.createQuery("select u from Utilisateur as u join u.utilisateurgroupes as ugp where ugp.groupeuser.libelle=:role");
		query.setParameter("role", "MANAGER");
		return query.getResultList();
	}


	@Override
	public Etablissement rechercherGestionnaire(String login) {		
		
		Query query;
		
		try{
			query=em.createNamedQuery("Etablissement.findGestionnaire");
			query.setParameter("login", login);
			query.setParameter("supprime", false);
			return (Etablissement) query.getSingleResult();
		}
		catch(Exception e){
			return null;
			
		}
		
		
	}


	@Override
	public void modifierAdministrateur(String login, String password,
			String ancienmotdepasse) {
		Query req;
		Configuration conf=null;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		try{
			emtrans.getTransaction().begin();
			
			
			try{
				req=emtrans.createNamedQuery("Configuration.find");
			
				conf=(Configuration) req.getSingleResult();
				
				if(conf.getPass_admin().compareTo(ancienmotdepasse)==0){
					conf.setLogin_admin(login);
					conf.setPass_admin(password);
				}
				emtrans.merge(conf);
			}
			catch(Exception e){
				// aucune configuration trouvée ou ++ configurations trouvées
				// on les supprimes toutes, et on reinitialise la configuration
				
				Query query=emtrans.createQuery("delete from Configuration c");
				query.executeUpdate();
				
				conf=new Configuration();
				conf.setLogin_admin("admin");
				conf.setPass_admin("admin");
				
				emtrans.persist(conf);
			
			}
			
			
			  emtrans.getTransaction().commit();
		}
		catch(Exception e){  
			
				
		}finally{
			emtrans.close();
			emf.close();
		}
		
	}


	@Override
	public void modifierGestionnaire(String codeetab,String login, String password,
			String ancienmotdepasse) {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		try{
			emtrans.getTransaction().begin();
			
			
			try{
				Etablissement etab=emtrans.getReference(Etablissement.class,codeetab);
				if(etab==null) throw new ElementNOtFoundException(codeetab, "Etablissement");
				
				etab.setLogin_gest(login);
				etab.setPass_gest(password);
				
				emtrans.merge(etab);
			}
			catch(Exception e){
				
			}
			
			
			  emtrans.getTransaction().commit();
		}
		catch(Exception e){  
			
				
		}finally{
			emtrans.close();
			emf.close();
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> listerGroupesUserCodes(int idutilisateur) {
		Query query=em.createQuery("select ug.groupeuser.idgroupe from UtilisateurGroupe ug where ug.supprime="+false+" and ug.groupeuser.supprime="+false+" and ug.utilisateur.idutilisateur="+idutilisateur);
		
		return query.getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> listeTypesResponabilite() {
		// TODO Auto-generated method stub
		Query query=em.createNamedQuery("GroupeUser.listTypes");
		return query.getResultList();
		
	}
	
}
