package ages.beans.application.contact;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.exception.AdminstrateurNotFoundException;

@ManagedBean(name="contactBean")
@RequestScoped
public class ContactBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
		
	
	private String nom;
	private String email;
	private String message;
	
	public ContactBean() {
	}
	

	public void setService(Service service) {
		this.service = service;
	}
	
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}



	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}



	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}



	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}



	public String envoyerMessage(){
		try {
			this.service.envoyerMessage(nom,email,message);
		} catch (Exception e) {
			if(e.getCause()!=null&&e.getCause().getClass().equals(AdminstrateurNotFoundException.class)){
				Repertoire.logError("Echec envoi de message", getClass(), e);
				Repertoire.addMessageerreur("Le message n'a pas pu être envoyé, Veuillez reéssayer Contacter l'administrateur pas tout autre moyen!!");
				
			}
			else{
				Repertoire.logError("Echec envoi de message", getClass(), e);
				Repertoire.addMessageerreur("Le message n'a pas pu être envoyé, Veuillez reéssayer plus tard!!");
			
			}
				
			return null;
		}
		Repertoire.addMessageinfo("Message envoyé");
		return null;
	}

	
	
}
