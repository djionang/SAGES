package ages.beans.anneeacademique;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.ScheduleModel;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
/**
 * AnneeEnCoursBean
 * Bean de gestion de l'anée scolaire en cours
 * @author Brilswear
 *
 */

@ManagedBean(name="anneeEnCoursBean")
@RequestScoped
public class AnneeEnCoursBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String anneeacademique;
	private Date datedebut;	
	private Date datefin;
	private boolean clos;
	private ScheduleModel modeleProgrammation;
	
	// declaration et injection du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	public void setService(Service service) {
		this.service = service;
	}
	
	
	public String getAnneeacademique() {
		return anneeacademique;
	}
	public void setAnneeacademique(String anneeacademique) {
		this.anneeacademique = anneeacademique;
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
	
	public boolean isClos() {
		return clos;
	}


	public void setClos(boolean clos) {
		this.clos = clos;
	}	

	/**
	 * @return the modeleProgrammation
	 */
	public ScheduleModel getModeleProgrammation() {
		return modeleProgrammation;
	}


	/**
	 * @param modeleProgrammation the modeleProgrammation to set
	 */
	public void setModeleProgrammation(ScheduleModel modeleProgrammation) {
		this.modeleProgrammation = modeleProgrammation;
	}


	public String navmodifier(){
		return OperationResults.navWithParam("modifierAnnee", "codeannee", this.anneeacademique);
	}
	
	
	@PostConstruct
	public void initAnnee(){
		this.service.initAnneeEnCours(this);
		if(this.anneeacademique==null){
			Repertoire.addMessageerreurAnneeECNotDefined();
		}
		else
			this.modeleProgrammation=this.service.listeprogrammations(datedebut, datefin);
	}
}
