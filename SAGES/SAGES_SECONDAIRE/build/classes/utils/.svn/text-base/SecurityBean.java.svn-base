package utils;

import java.io.Serializable;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ages.beans.GenericBean;
import ages.exception.ElementNOtFoundException;


@ManagedBean(name="securityBean")
@SessionScoped
public class SecurityBean extends GenericBean implements Serializable{

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private static final long serialVersionUID = 1L;

	private int codeevaluation;
	
	public SecurityBean(){
		
	}
	
	public int getCodeevaluation() {
		return codeevaluation;
	}

	public void setCodeevaluation(int codeevaluation) {
		this.codeevaluation = codeevaluation;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
	}

	public void controlEditNotes(){
		try {
			if(this.service.editNotesAbled(codeevaluation))
			{
				//l'utilisateur a les droits de modification, rien à faire
			}
			else{
				
				
				//l'utilisateur n'a pas les droits, il doit etre redirigé vers une page d'erreur
				FacesContext facesContext = FacesContext.getCurrentInstance();
			    String redirect = "interdit";// define the navigation rule that must be used in order to redirect the user to the adequate page...
			    NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
			    myNav.handleNavigation(facesContext, null, redirect);
			}
		} catch (ElementNOtFoundException e) {
			Repertoire.addMessageerreur("Evalution non retrouvée", e);
			e.printStackTrace();
		}
	}

}
