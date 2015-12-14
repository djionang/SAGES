package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ 
	@NamedQuery(name="Configuration.find", 
				query="select c from Configuration as c"),
			})
public class Configuration implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private int idconfiguration;
	
	private String login_admin;
	private String pass_admin;
	
	
	
	/**
	 * @return the idconfiguration
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdconfiguration() {
		return idconfiguration;
	}
	/**
	 * @param idconfiguration the idconfiguration to set
	 */
	public void setIdconfiguration(int idconfiguration) {
		this.idconfiguration = idconfiguration;
	}
	
	/**
	 * @return the login_admin
	 */
	@Column(unique=true)
	public String getLogin_admin() {
		return login_admin;
	}
	/**
	 * @param login_admin the login_admin to set
	 */
	public void setLogin_admin(String login_admin) {
		this.login_admin = login_admin;
	}
	/**
	 * @return the pass_admin
	 */
	public String getPass_admin() {
		return pass_admin;
	}
	/**
	 * @param pass_admin the pass_admin to set
	 */
	public void setPass_admin(String pass_admin) {
		this.pass_admin = pass_admin;
	}
	
	
	
	

}
