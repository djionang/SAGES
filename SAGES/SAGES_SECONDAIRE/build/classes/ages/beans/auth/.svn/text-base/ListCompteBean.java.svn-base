package ages.beans.auth;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import utils.Service;
import ages.beans.GenericBean;


/**
 * ListCompteBean
 * @author Brilswear
 *
 */


@ManagedBean(name="listCompteBean")
@RequestScoped
public class ListCompteBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<CompteBean> comptes;
	
	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
	}

	public List<CompteBean> getComptes() {
		return comptes;
	}

	public void setComptes(List<CompteBean> comptes) {
		this.comptes = comptes;
	}



	@PostConstruct
	public void initListeitems(){
		this.setComptes(this.service.listerComptesUtilisateurs());
	}
}
