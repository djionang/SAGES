package ages.beans.enseignement;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.Service;
import ages.beans.GenericBean;

/**
 * Classe ListEleveBean
 * Bean de gestion de la liste d'eleves
 * @author Bri
 *
 */

@ManagedBean(name="listMatiereBean")
@ViewScoped
public class ListMatiereBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<MatiereBean> matieres;
	private MatiereBean selectedMatiere;
	
	
	public void setService(Service service) {
		this.service = service;
	}

	public List<MatiereBean> getMatieres() {
		return matieres;
	}

	public void setMatieres(List<MatiereBean> matieres) {
		this.matieres = matieres;
	}

	public MatiereBean getSelectedMatiere() {
		return selectedMatiere;
	}

	public void setSelectedMatiere(MatiereBean selectedMatiere) {
		this.selectedMatiere = selectedMatiere;
	}

	/**
	 * Initialisation du bean avec la liste des eleves devant s'inscrire
	 */
	@PostConstruct
	protected void init(){
		this.matieres=this.service.listematieres();
	}
	
	
}
