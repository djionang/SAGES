package ages.beans.inscription;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.exception.ElementNOtFoundException;

/**
 * Bean de listing des tranches versés par une eleve, ou a verser
 * @author Brilswear
 *
 */

@ManagedBean(name="copieTrancheBean")
@ViewScoped
public class CopieTrancheBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
		
	// Liste des tranches a verser pour une classe
	private List<TrancheBean> listeTranches;	
	
	private String codeclasse;
	
	private List<String> classescibles;
	
	public List<TrancheBean> getListeTranches() {
		return listeTranches;
	}

	public void setListeTranches(List<TrancheBean> listeTranches) {
		this.listeTranches = listeTranches;
	}


	public void setService(Service service) {
		this.service = service;
	}

	public String getCodeclasse() {
		return codeclasse;
	}

	public void setCodeclasse(String codeclasse) {
		this.codeclasse = codeclasse;
	}
	

	/**
	 * @return the classescibles
	 */
	public List<String> getClassescibles() {
		return classescibles;
	}

	/**
	 * @param classescibles the classescibles to set
	 */
	public void setClassescibles(List<String> classescibles) {
		this.classescibles = classescibles;
	}

	public void loadTranches(){
		if(codeclasse!=null){
			listeTranches=this.service.listeTranchesClasse(codeclasse);
		}
		else{
			Repertoire.addMessageerreur("Aucune filiere sélectionnée");
		}
		
	}
	
	public String copierTranches(){
		try {
			this.service.copierTranches(codeclasse,classescibles);
		} catch (Exception e) {
			if(e.getCause()!=null&&e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreurElementNonTrouve("Filiere");
				Repertoire.logError("Filiere non trouvée", getClass(), e);
			}			
			else{
				Repertoire.addMessageerreurInnatendue(e);
				Repertoire.logError("Erreur innatendue", getClass(), e);
			}
			return null;	
		}
		Repertoire.addMessageinfo("Copie effectuée avec succes");
		return null;
	}
		
}
