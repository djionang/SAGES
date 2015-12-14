package ressources;


import javax.naming.NamingException;

import ejb.GestionEleveRemote;
import ejb.GestionInscriptionRemote;


/**
 * Fabrique des Beans applicatifs remote, Design pattern Builder appliqué ici 
 * @author Brilland
 *
 */
public class RemoteEjbFactory {
	
	//bean Remote de gestion des eleves
	private static GestionEleveRemote gestioneleveR=null;	
	
	//bean Remote de gestion des preinscriptions
		private static GestionInscriptionRemote gestioninscrptionR=null;
	
	/**
	 * retourne le Bean de gestion des eleves remote
	 * @return gestioneleveR
	 */
	public static GestionEleveRemote getGestionEleveRemote(){
		if(gestioneleveR==null){
			try {
				gestioneleveR=(GestionEleveRemote) Contexte.getInitialContext().lookup("/ejb/GestionEleve");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}		
		return gestioneleveR;
	}
	
	/**
	 * retourne le Bean de gestion des inscriptions remote
	 * @return gestioneleveR
	 */
	public static GestionInscriptionRemote getGestionInscriptionRemote(){
		if(gestioninscrptionR==null){
			try {
				gestioninscrptionR=(GestionInscriptionRemote) Contexte.getInitialContext().lookup("/ejb/GestionEleve");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}		
		return gestioninscrptionR;
	}
	
}
