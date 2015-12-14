package ages.beans.eleve;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.beans.etablissement.classe.ClasseBean;

@ManagedBean(name="deleguesBean")
@ViewScoped
public class DeleguesBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<ClasseBean> classes;
	
	private String codeclasse;
	
	public void setService(Service service) {
		this.service = service;
	}

	
	/**
	 * @return the codeclasse
	 */
	public String getCodeclasse() {
		return codeclasse;
	}

	/**
	 * @param codeclasse the codeclasse to set
	 */
	public void setCodeclasse(String codeclasse) {
		this.codeclasse = codeclasse;
	}

	/**
	 * @return the classes
	 */
	public List<ClasseBean> getClasses() {
		return classes;
	}


	/**
	 * @param classes the classes to set
	 */
	public void setClasses(List<ClasseBean> classes) {
		this.classes = classes;
	}

	
	public void loadClasses(){
		if(codeclasse!=null && ! codeclasse.isEmpty()){
			classes=this.service.listerClasse(codeclasse);
			if(classes==null || classes.isEmpty())
				Repertoire.addMessageerreur("Aucun délégué trouvé");
		}
		else{
			classes=this.service.listeclasses();
		}
	}
	
	@PostConstruct
	public void init(){
		loadClasses();
	}
}

