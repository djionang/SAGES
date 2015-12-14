package ages.beans.enseignement;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import utils.Service;
import ages.beans.GenericBean;

/**
 * Classe ListEleveBean
 * Bean de gestion de la liste d'eleves
 * @author Bri
 *
 */

@ManagedBean(name="listCoursBean")
@ViewScoped
public class ListCoursBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<CoursBean> cours;
	
	private String classe;
	
	
	public void setService(Service service) {
		this.service = service;
	}

	public List<CoursBean> getCours() {
		return cours;
	}

	public void setCours(List<CoursBean> cours) {
		this.cours = cours;
	}


	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	/**
	 * Initialisation du bean avec la liste des eleves devant s'inscrire
	 */
	@PostConstruct
	protected void init(){
		this.setCours(this.service.listecours());
	}
	
	public void loadCours(ActionEvent ev){
		if(classe!=null){
			if(classe.isEmpty()){
				cours=this.service.listecours();
			}
			else{
				cours=this.service.listecours(classe);
			}	
		}
	}
	
}
