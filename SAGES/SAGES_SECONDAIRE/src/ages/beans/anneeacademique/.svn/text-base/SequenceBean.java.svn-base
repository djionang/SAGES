package ages.beans.anneeacademique;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.ScheduleModel;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.exception.ChevauchementDateException;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;
import ages.exception.NonUniqueResultException;

@ManagedBean(name="sequenceBean")
@ViewScoped
public class SequenceBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;

	private int idsequence;
	private int numero;
	private Date datedebut;
	private Date datefin;
	private int trimestre;
	private boolean cloture;
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
		return datefin;
	}
	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}
	
	public int getTrimestre() {
		return trimestre;
	}
	public void setTrimestre(int trimestre) {
		this.trimestre = trimestre;
	}
	
	public void setService(Service service) {
		this.service = service;
	}
	
	/**
	 * @return the idsequence
	 */
	public int getIdsequence() {
		return idsequence;
	}
	/**
	 * @param idsequence the idsequence to set
	 */
	public void setIdsequence(int idsequence) {
		this.idsequence = idsequence;
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
	public boolean isCloture() {
		return cloture;
	}
	public void setCloture(boolean cloture) {
		this.cloture = cloture;
	}
	
	
	public String modifySequence(){
		try {
			this.service.modifySequence(this.numero,this.datedebut,this.datefin,trimestre);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Sequence non trouvée");
				}
				else{
					if(e.getCause().getClass().equals(ChevauchementDateException.class)){
						Repertoire.addMessageerreur("Cette séquence chevauche avec une autre enregistrée");
					}
					else{
						Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
					}
				}
			}
			return null;
		}
		Repertoire.addMessageinfoModificationOK("Sequence");
		return OperationResults.NavListingSequences;
	}
	
	public String deleteSequence(){
		try {
			this.service.deleteSequence(this.numero);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Sequence non trouvée");
				}
				else{
					Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				}
			}
			return null;
		}
		Repertoire.addMessageinfoSuppressionOK("Sequence");
		return OperationResults.NavListingSequences;
	}
	
	
	public String saveSequence(){
		try {
			this.service.saveSequence(numero,datedebut,datefin,trimestre);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(DuplicateKeyException.class)){
					Repertoire.addMessageerreur("Séquence de numéro "+numero+" déja enregistrée");
				}
				else
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						if(trimestre==0)
							Repertoire.addMessageerreur("Aucun trimestre de correspondance trouvé");
						else
							Repertoire.addMessageerreur("Trimestre N° "+trimestre+" non trouvé");
						Repertoire.logError("Trimestre non trouvé", getClass(), e);
					}
					else
						if(e.getCause().getClass().equals(NonUniqueResultException.class)){
							Repertoire.addMessageerreur("Trimestre non trouvé, la date de debut de séquence ne correspond à aucun trimestre enregistré");
						}
						else{
							if(e.getCause().getClass().equals(ChevauchementDateException.class)){
								Repertoire.logError("Chevauchement de sequence", getClass(), e);
								Repertoire.addMessageerreur("Cette séquence chevauche avec une autre enregistrée");
							}
							else{
								Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
							}							
						}
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
			}
			return null;
		}
		Repertoire.addMessageinfoEnregistrementOK("Sequence");
		return OperationResults.NavListingSequences;
		
	}
	
	public void initNumeroSequence(){
		this.numero=this.service.nextNumeroSequence();
	}
	
	public void initsequenceProgrammation(){
		if(this.numero!=0){
			this.service.initSequence(this);
			this.setModeleProgrammation(this.service.listeprogrammations(datedebut, datefin));
		}
			
	}
		
	
	public void initsequence(){
		if(this.numero!=0)
			this.service.initSequence(this);
	}
}
