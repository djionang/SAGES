package utils;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 * Contient le contexte de l'application
 * notament l'inital context qui nous permettra d'acceder aux EJB du conteneur distant ou local.
 * @author Brilland
 *
 */
public class ContexteJNDI {
	// serveur d'application EJB URL:port
	public static String serveur=null;
	
	// Contexte JNDI du serveur
	private static InitialContext ic=null;
	
	
	
	/**
	 * Fournit le serveur (nom d'hote et numero de port du contebeur EJB
	 * @return l'URL du serveur
	 */
	public static String getServeur(){
		if(serveur==null)
			serveur="localhost:1099";
		return serveur;
	}
	
	/**
	 *  Retourne le contexte JNDI du serveur
	 * @return
	 */
	public static InitialContext getInitialContext(){
		if(ic==null){
			Properties props=new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY ,"org.jnp.interfaces.NamingContextFactory");
			props.put(Context.PROVIDER_URL,"jnp://"+getServeur());
			props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming");
			
			try {
				ic=new InitialContext();
			} catch (NamingException e) {
				Repertoire.logFatal("Impossible de creer le contexte dacces aux EJB", ContexteJNDI.class, e);
				return null;
			}
		}
		return ic;
	}
}
