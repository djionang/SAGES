package ages.beans.auth;

import java.io.Serializable;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import utils.Repertoire;
import utils.Service;

import ages.beans.GenericBean;


/**
 * Bean user
 * Gere les connexions a l'application
 * @author Administrateur Bri
 *
 */

@ManagedBean(name="userBean")
@SessionScoped
public class UserBean extends GenericBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private String login;
	private String password;
	private String annee;
	private Boolean logged=false;
	private String result;
	
	
	
	private Set<String> roles;
	
	public boolean isPasswordValid(String password) {
		return getPassword().equals(password);
	}

	boolean hasRole(String role) {
		return roles.contains(role);
	}	
	
	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	/**
	 * @return the roles
	 */
	public Set<String> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getLogged() {
		return logged;
	}
	public void setLogged(Boolean logged) {
		this.logged = logged;
	}
	
	
	public String connexion(){
		if(logged==true){
			// le gars est connecté, il faut le deconnecter
			logged=false;
			
			return "logout";
		}
		else{
			// il n'est pas connecté, il faut le connecter et initialiser ses roles.
			return "loginpage";
		}		
	}
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}


	/**
	 * Login d'un utilisateur
	 * @return
	 */
	public String loguser(){
		
		if(logged){
			logged=false;
			
			return "logout";
		}
			
		try{
			logged=this.service.connectUser(this);
		}
		catch(Exception e){
			return "accueil";
		}
		
		return "login";  // Appelle Acegi pour qu'il fasse son identification
	}
	
	
	/**
	 * Message renvoyé lors utilisateur inconnu ou login-pwd incompatibles
	 */
	public void showMessage(){
		if(result!=null&&result.compareTo("lid")==0){
			Repertoire.addMessageerreur("Utilisateur non reconnu");
		}
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
