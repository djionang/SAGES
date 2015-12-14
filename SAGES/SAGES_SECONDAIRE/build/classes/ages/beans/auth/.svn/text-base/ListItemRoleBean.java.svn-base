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
 * ItemRoleBean
 * @author Brilswear
 *
 */


@ManagedBean(name="listItemRoleBean")
@RequestScoped
public class ListItemRoleBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<ItemRoleBean> itemroles;
	
	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
	}

	
	public List<ItemRoleBean> getItemroles() {
		return itemroles;
	}


	public void setItemroles(List<ItemRoleBean> itemroles) {
		this.itemroles = itemroles;
	}


	@PostConstruct
	public void initListeitems(){
		this.setItemroles(this.service.listerItemrole());
	}
}
