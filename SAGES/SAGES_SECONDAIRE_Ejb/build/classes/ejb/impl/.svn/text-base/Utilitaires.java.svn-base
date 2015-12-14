package ejb.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ages.exception.AdminstrateurNotFoundException;
import ejb.UtilitairesLocal;
import entities.Configuration;
import entities.Etablissement;

/**
 * Session Bean implementation class GestionEleve
 * Classe de gestion des eleves
 */
@Stateless(mappedName = "Utilitaires")
@TransactionManagement(TransactionManagementType.BEAN)
public class Utilitaires implements UtilitairesLocal {

	@PersistenceContext(unitName="agespersist")
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public Utilitaires() {
    }
    
    
    @SuppressWarnings("unchecked")
	@Override
	public void envoyerMessage(String nom, String email, String messagesend) throws AdminstrateurNotFoundException, AddressException {
		List<String> emailsAdmins;
		Etablissement etab;
		Query query;
		final String MAILER_VERSION = "Java";
		
		query=em.createNamedQuery("Utilisateur.findEmailMasters");
		query.setParameter("supprime", false);
		query.setParameter("master", true);
		emailsAdmins=query.getResultList();
		
		if(emailsAdmins==null || emailsAdmins.isEmpty())
			throw new AdminstrateurNotFoundException();
		
		query=em.createNamedQuery("Etablissement.findAll");
		query.setParameter("supprime", false);
		etab=(Etablissement) query.setMaxResults(1).getSingleResult();
		
		String serveur=etab.getServeurMail();
		
		String from=etab.getEmail();
		//String passfrom=etab.getPassmail();
		
		List<InternetAddress> adresses=new ArrayList<InternetAddress>();
		for(int i=0;i<emailsAdmins.size();i++){
			adresses.add(new InternetAddress(emailsAdmins.get(i)));
		}
		
		
		try {
            Properties prop = System.getProperties();
            prop.put("mail.smtp.host", serveur);
            Session session = Session.getDefaultInstance(prop,null);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));           
            
            
            InternetAddress[] internetAddresses = (InternetAddress[]) adresses.toArray();
            
            message.setRecipients(Message.RecipientType.TO,internetAddresses);
            message.setSubject("Etablissement "+etab.getNometab()+" Contact-- "+nom+", "+email);
            message.setContent(messagesend, "text/html");
            message.setHeader("X-Mailer", MAILER_VERSION);
            message.setSentDate(new Date());
            session.setDebug(true);
            Transport.send(message);
	   } catch (AddressException e) {
	            e.printStackTrace();
	   } catch (MessagingException e) {
	            e.printStackTrace();
	   }
}


	@Override
	public void enregistrerModeleBulletin(int modele, String etablissement) {
		
		Etablissement etab;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		try{
			Query query=emtrans.createNamedQuery("Etablissement.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("codeetablissement", etablissement);
			
			etab= (Etablissement) query.getSingleResult();
			etab.setModeleBulletin(modele);
			
			emtrans.merge(etab);
			emtrans.getTransaction().commit();
			
		}finally{
			emtrans.close();
			emf.close();
		}
	}


	@Override
	public int rechercherModeleBulletin(String codeetablissement) {
		
		Etablissement etab;
		
		try{
			Query query=em.createNamedQuery("Etablissement.findByCode");
			query.setParameter("supprime", false);
			query.setParameter("codeetablissement", codeetablissement);
			
			etab= (Etablissement) query.getSingleResult();
		}
		catch(NoResultException e){
			return 0;
		}
		
		return etab.getModeleBulletin();			
		
	}


	@Override
	public Configuration rechercherConfiguration() {
		Query req;
		Configuration conf=null;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		try{
			emtrans.getTransaction().begin();
			
			
			try{
				req=emtrans.createNamedQuery("Configuration.find");
			
				conf=(Configuration) req.getSingleResult();
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
		return conf;
	}
	
}


