package ages.beans.auth;

import java.io.Serializable;

import utils.Service;

import ages.beans.GenericBean;


/**
 * GroupeCompte
 * Un utilisateur appartient t-il a un groupe donné?
 * @author Brilswear
 *
 */


public class GroupeCompte extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	private int idgroupe;
	private String libelle;
	private boolean selected;
	
	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
	}

	public int getIdgroupe() {
		return idgroupe;
	}

	public void setIdgroupe(int idgroupe) {
		this.idgroupe = idgroupe;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}


}
