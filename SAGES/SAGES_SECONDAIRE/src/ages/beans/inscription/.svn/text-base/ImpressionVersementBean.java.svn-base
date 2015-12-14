package ages.beans.inscription;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import ages.beans.eleve.EleveBean;

import net.sf.jasperreports.engine.JRException;

import utils.Repertoire;
import utils.Service;


@ManagedBean(name="printVersBean")
@RequestScoped
public class ImpressionVersementBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	private Service service;
	private String idversement;
	
	private String message;
	
	private String matricule;
	
	private VersementBean versement;
	/**
	 * @return the idversement
	 */
	public String getIdversement() {
		return idversement;
	}

	/**
	 * @param idversement the idversement to set
	 */
	public void setIdversement(String idversement) {
		this.idversement = idversement;
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

	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public void loadToPrint(){
		versement=this.service.rechercherVersement(idversement);
		if(versement==null)
			message="Erreur, versement non Validé";
		else
			message="Versement enregistré "+idversement;
	}
	
	public String imprimerVersement(){
		EleveBean selectedEleve = new EleveBean();
		selectedEleve.setMatricule(matricule);
		this.service.initEleve(selectedEleve);
		if(idversement==null || idversement.isEmpty() || matricule==null || matricule.isEmpty())
			Repertoire.addMessageerreur("Impossible de lancer l'impression, Erreur de parametres");
		else
			try {
				this.service.imprimerFactureVersement(selectedEleve.getIdeleve(),selectedEleve.getCodeClasse(),idversement);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return null;
	}
}
