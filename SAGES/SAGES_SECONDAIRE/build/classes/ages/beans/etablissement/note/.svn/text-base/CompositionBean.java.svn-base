package ages.beans.etablissement.note;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import utils.Service;
import ages.beans.GenericBean;
import ages.beans.eleve.EleveBean;

@ManagedBean(name="compositionBean")
@RequestScoped
public class CompositionBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
		
	private EleveBean eleve;
	
	private float note;
	
	private boolean absencejustifiee;
	
	public CompositionBean() {
	}
	
	/**
	 * @return the eleve
	 */
	public EleveBean getEleve() {
		return eleve;
	}

	/**
	 * @param eleve the eleve to set
	 */
	public void setEleve(EleveBean eleve) {
		this.eleve = eleve;
	}

	/**
	 * @return the note
	 */
	public float getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(float note) {
		this.note = note;
	}

	public void setService(Service service) {
		this.service = service;
	}

	/**
	 * @return the absencejustifiee
	 */
	public boolean isAbsencejustifiee() {
		return absencejustifiee;
	}

	/**
	 * @param absencejustifiee the absencejustifiee to set
	 */
	public void setAbsencejustifiee(boolean absencejustifiee) {
		this.absencejustifiee = absencejustifiee;
	}

}
