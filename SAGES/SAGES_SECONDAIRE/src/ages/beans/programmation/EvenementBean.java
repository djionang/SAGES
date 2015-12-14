package ages.beans.programmation;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.Service;


@ManagedBean(name="evtBean")
@ViewScoped
public class EvenementBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Date datedebut;
	private Date datefin;
	private String libelle;
	private String description;
	private String repetition;
	private int codeactivite;
	private String type;
	
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	public EvenementBean(){
		
	}	

	public EvenementBean(String libelle,Date datedebut, Date datefin) {
		super();
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.libelle = libelle;
	}

	public Date getDatedebut() {
		return datedebut;
	}
	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}
	public Date getDatefin() {
		return datefin;
	}
	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRepetition() {
		return repetition;
	}

	public void setRepetition(String repetition) {
		this.repetition = repetition;
	}

	public int getCodeactivite() {
		return codeactivite;
	}

	public void setCodeactivite(int codeactivite) {
		this.codeactivite = codeactivite;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	public String saveActivite(){
		return null;
	}
	
}
