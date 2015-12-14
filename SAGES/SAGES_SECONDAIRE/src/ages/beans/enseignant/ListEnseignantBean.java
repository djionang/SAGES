package ages.beans.enseignant;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;

@ManagedBean(name="listEnseignantBean")
@RequestScoped
public class ListEnseignantBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<EnseignantBean> enseignants;
	
	public void setService(Service service) {
		this.service = service;
	}
	
	@PostConstruct
	public void initEnseignants(){
		setEnseignants(this.service.listeenseignants());
	}

	public List<EnseignantBean> getEnseignants() {
		return enseignants;
	}

	public void setEnseignants(List<EnseignantBean> enseignants) {
		this.enseignants = enseignants;
	}
	
	public String imprimerListeEnseignants(){
		try{
				this.service.imprimerListeEnseignants();
			
		}
		catch(Exception e){
			Repertoire.addMessageerreur("Erreur survenue lors de l'impression");
			e.printStackTrace();
		}
		
		return null;
	}
	
}
