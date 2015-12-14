package ages.beans.budget;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import ages.beans.GenericBean;

import utils.Service;

@ManagedBean(name="listTransfertBean")
@ViewScoped
public class ListTransfertBean extends GenericBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
			// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
			
	public void setService(Service service) {
		this.service = service;
	}

}
