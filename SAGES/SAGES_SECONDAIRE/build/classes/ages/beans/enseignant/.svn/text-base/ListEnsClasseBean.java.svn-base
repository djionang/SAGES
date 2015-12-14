package ages.beans.enseignant;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;

@ManagedBean(name="listEnsClasseBean")
@ViewScoped
public class ListEnsClasseBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<EnsClasseBean> enseignants;
	
	private String codeclasse;
	
	public void setService(Service service) {
		this.service = service;
	}

	public List<EnsClasseBean> getEnseignants() {
		return enseignants;
	}

	public void setEnseignants(List<EnsClasseBean> enseignants) {
		this.enseignants = enseignants;
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

	public void loadEnseignants(){
		if(codeclasse!=null && ! codeclasse.isEmpty()){
			enseignants=this.service.listerEnseignantsClasse(codeclasse);
			if(enseignants==null || enseignants.isEmpty())
				Repertoire.addMessageerreur("Aucun enseignant trouvé");
		}
	}
}

