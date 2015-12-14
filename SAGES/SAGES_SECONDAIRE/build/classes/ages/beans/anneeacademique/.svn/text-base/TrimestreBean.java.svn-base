package ages.beans.anneeacademique;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.ScheduleModel;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.exception.ChevauchementDateException;
import ages.exception.ElementNOtFoundException;

@ManagedBean(name="trimestreBean")
@RequestScoped
public class TrimestreBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
		
	private int numero;
	private Date datedebut;
	private Date datefin;
	private Date dateimpressionBull;
	private ScheduleModel modeleProgrammation;
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public Date getDatedebut() {
		return datedebut;	
	}
	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}
	public Date getDatefin() {
		return this.datefin;
	}
	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}
	
	/**
	 * @return the dateimpressionBull
	 */
	public Date getDateimpressionBull() {
		return dateimpressionBull;
	}
	/**
	 * @param dateimpressionBull the dateimpressionBull to set
	 */
	public void setDateimpressionBull(Date dateimpressionBull) {
		this.dateimpressionBull = dateimpressionBull;
	}
	
	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
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
	public String modifyTrimestre(){
		if(this.datedebut.after(this.datefin)){
			Repertoire.addMessageerreur("La date de fin de trimestre ne peut succerder celle de debut");
			return null;
		}
		
		
		try {
			this.service.modifyTrimestre(this.numero,this.datedebut,this.datefin,this.dateimpressionBull);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreurElementNonTrouve("Trimestre");
				}
				else{
					Repertoire.addMessageerreur("Erreur innatendue, veuillez contacter l'administrateur");
				}
			}
			else
				Repertoire.addMessageerreur("Erreur innatendue, veuillez contacter l'administrateur");
			return null;
		}
		Repertoire.addMessageinfoModificationOK("Trimestre");
		return OperationResults.NavListingTrimestres;
	}
	
	
	public String deleteTrimestre(){
		
		try {
			this.service.deleteTrimestre(this.numero);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreurElementNonTrouve("Trimestre");
				}
				else{
					if(e.getCause().getClass().equals(ChevauchementDateException.class)){
						Repertoire.addMessageerreur("Ce trimestre chevauche avec un autre enregistré");
					}
					else{
						Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
					}
				}
			}
			else
				Repertoire.addMessageerreur("Erreur innatendue, veuillez contacter l'administrateur");
			return null;
		}
		Repertoire.addMessageinfoSuppressionOK("Trimestre");
		return OperationResults.NavListingTrimestres;
	}
	
	
	public String saveTrimestre(){
		if(this.datedebut.after(this.datefin)){
			Repertoire.addMessageerreur("La date de fin de trimestre ne peut succerder celle de debut");
			return null;
		}
		
		try {
			this.service.saveTrimestre(this.numero,this.datedebut,this.datefin);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreurElementNonTrouve("Année");
				}
				else{
					if(e.getCause().getClass().equals(ChevauchementDateException.class)){
						Repertoire.addMessageerreur("Ce trimestre chevauche avec un autre enregistré");
					}
					else{
						Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
					}
					
				}
			}
			return null;
		}
		Repertoire.addMessageinfoEnregistrementOK("Trimestre");
		return OperationResults.NavListingTrimestres;
	}
	
	public void inittrimestre(){
		if(this.numero!=0)
			this.service.initTrimestre(this);
	}
	
	public void inittrimestreProgrammation(){
		if(this.numero!=0){
			this.service.initTrimestre(this);
			this.setModeleProgrammation(this.service.listeprogrammations(datedebut, datefin));
		}
			
	}
	
	public void initNumeroTrimestre(){
		this.numero=this.service.nextNumeroTrimestre();
	}
}
