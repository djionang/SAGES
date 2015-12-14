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
 * ListGroupeUserBean
 * @author Brilswear
 *
 */


@ManagedBean(name="listGroupeUserBean")
@RequestScoped
public class ListGroupeUserBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<GroupeUserBean> groupeusers;
	
	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
	}


	@PostConstruct
	public void initListeitems(){
		this.setGroupeusers(this.service.listerGroupesUsers());
	}


	public List<GroupeUserBean> getGroupeusers() {
		return groupeusers;
	}


	public void setGroupeusers(List<GroupeUserBean> groupeusers) {
		this.groupeusers = groupeusers;
	}
}
