package ressources;

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
public class Contexte {
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
			props.put(Context.PROVIDER_URL,getServeur());
			
			try {
				ic=new InitialContext(props);
			} catch (NamingException e) {
				e.printStackTrace();
				return null;
			}
		}
		return ic;
	}
}
