package ages.beans.programmation;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.ScheduleModel;

import utils.Service;


@ManagedBean(name="timeEvtBean")
@ViewScoped
public class TimeEvtBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private ScheduleModel modeleEvt;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	
	public TimeEvtBean(){
		
	}	


	public ScheduleModel getModeleEvt() {
		return modeleEvt;
	}

	public void setModeleEvt(ScheduleModel modele) {
		this.modeleEvt = modele;
	}

	@PostConstruct
	public void initModeleEvt(){
		modeleEvt=this.service.listerProgrammationEvenements();
	}

}
