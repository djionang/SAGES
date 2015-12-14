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

@ManagedBean(name="listgmBean")
@ViewScoped
public class ListGroupeMatiereBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<GroupeMatiereBean> groupesmatieres;
	private MatiereBean selectedgm;
	
	
	public void setService(Service service) {
		this.service = service;
	}


	public MatiereBean getSelectedgm() {
		return selectedgm;
	}


	public void setSelectedgm(MatiereBean selectedgm) {
		this.selectedgm = selectedgm;
	}


	public List<GroupeMatiereBean> getGroupesmatieres() {
		return groupesmatieres;
	}


	public void setGroupesmatieres(List<GroupeMatiereBean> groupesmatieres) {
		this.groupesmatieres = groupesmatieres;
	}


	/**
	 * Initialisation du bean avec la liste des eleves devant s'inscrire
	 */
	@PostConstruct
	protected void init(){
		this.setGroupesmatieres(this.service.listegroupesmatieres());
	}
	
	
}
