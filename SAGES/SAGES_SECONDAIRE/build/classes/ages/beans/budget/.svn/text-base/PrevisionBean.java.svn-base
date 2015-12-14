package ages.beans.budget;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.exception.ElementNOtFoundException;

@ManagedBean(name="previsionBean")
@ViewScoped
public class PrevisionBean extends GenericBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idprevision;
	private String codeprevision;
	private String tranfert;
	private String description;
	private Date dateenreg;
	private float montant;
	private float reste;
	private boolean supprime;
	private String typeprevision;
	private String nomrespon;
	private String prenomrespon;
	
	private float totalCode;
	
	private float total;
	
	private List<PrevisionBean> listePre;
	
	private PrevisionBean selectedPrevision;
	
	
	@ManagedProperty(value="#{service}")
	protected Service service;
			
	public void setService(Service service) {
		this.service = service;
	}
	
	public int getIdprevision() {
		return idprevision;
	}
	public void setIdprevision(int idprevision) {
		this.idprevision = idprevision;
	}
	public String getCodeprevision() {
		return codeprevision;
	}
	public void setCodeprevision(String codeprevision) {
		this.codeprevision = codeprevision;
	}
	public String getTranfert() {
		return tranfert;
	}
	public void setTranfert(String tranfert) {
		this.tranfert = tranfert;
	}
	public Date getDateenreg() {
		return dateenreg;
	}
	public void setDateenreg(Date dateenreg) {
		this.dateenreg = dateenreg;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	public boolean isSupprime() {
		return supprime;
	}
	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}
	public String getTypeprevision() {
		return typeprevision;
	}
	public void setTypeprevision(String typeprevision) {
		this.typeprevision = typeprevision;
	}
	
	
	public PrevisionBean getSelectedPrevision() {
		return selectedPrevision;
	}

	public void setSelectedPrevision(PrevisionBean selectedPrevision) {
		this.selectedPrevision = selectedPrevision;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getReste() {
		return reste;
	}

	public void setReste(float reste) {
		this.reste = reste;
	}

	public String getNomrespon() {
		return nomrespon;
	}

	public void setNomrespon(String nomrespon) {
		this.nomrespon = nomrespon;
	}

	public String getPrenomrespon() {
		return prenomrespon;
	}

	public void setPrenomrespon(String prenomrespon) {
		this.prenomrespon = prenomrespon;
	}

	public float getTotalCode() {
		return totalCode;
	}

	public void setTotalCode(float totalCode) {
		this.totalCode = totalCode;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public List<PrevisionBean> getListePre() {
		return listePre;
	}

	public void setListePre(List<PrevisionBean> listePre) {
		this.listePre = listePre;
	}

	public String saveprevision(){
		
		try {
			this.service.savePrevision(this.typeprevision,this.montant,this.dateenreg,this.description);
			Repertoire.addMessageinfo(OperationResults.EnregistrementOK);
			return "previsionlisting";
			
		} catch (Exception e) {
			if(e.getCause()!=null && e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Etablissement cible non trouvé", e);
				return null;
			}
		}
		return "";
		
	}
	
	public String enregPrevision(){
		
		try {

			this.service.savePrevisionAjouter(this.selectedPrevision.codeprevision,this.selectedPrevision.idprevision,this.selectedPrevision.typeprevision,this.dateenreg,this.description,this.montant);
			Repertoire.addMessageinfo(OperationResults.EnregistrementOK);
			return "previsionlisting";
		} catch (Exception e) {
			if(e.getCause()!=null && e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Prevision cible non trouvé", e);
				return null;
			}
		}
		
		return "";
	}
	
	public String transfert(){
		
		try {

			this.service.savetranfert(this.selectedPrevision.codeprevision,this.selectedPrevision.idprevision,this.selectedPrevision.typeprevision,this.dateenreg,this.description,this.montant,typeprevision,idprevision);
			Repertoire.addMessageinfo(OperationResults.EnregistrementOK);
			return "previsionlisting";
		} catch (Exception e) {
			if(e.getCause()!=null && e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Prevision cible non trouvé", e);
				return null;
			}
		}
		
		return "";
	}
	
	public String modifierprevision(){
		try {
			this.service.modifierPrevision(this.codeprevision,this.idprevision,this.typeprevision,this.montant,this.dateenreg,this.description);
		} catch (ElementNOtFoundException e) {
			Repertoire.addMessageerreur(OperationResults.UpdateNO+" Materiel non trouvée");
			return OperationResults.FAILURE;
		}
		Repertoire.addMessageinfo(OperationResults.UpdateOK);
		return OperationResults.navWithParam("visualisationp", "codeprevision", this.codeprevision);
	}
	
	public void initialize(){
		
		if(this.idprevision!=0)
			this.service.initialisePrevisionBean(this);
	}
	
	
	public void loadPrevision(){
		if(this.idprevision==0)  // aucun matricule passé en paramètre
			return;
		if(selectedPrevision!=null&&selectedPrevision.getIdprevision()!=idprevision){
			this.chargerPrevision();
			return;
		}
		
		if(selectedPrevision==null)
			this.chargerPrevision();
	}
	
	
	public void chargerPrevision(){
		try {
			selectedPrevision=new PrevisionBean();
			selectedPrevision.setIdprevision(idprevision);
			this.service.initPrevision(selectedPrevision);
		} catch (Exception e1) {

			e1.printStackTrace();
		}
			
	}
	
	public void chargerPrevision(ActionEvent e){
		try {
			selectedPrevision=new PrevisionBean();
			selectedPrevision.setIdprevision(idprevision);
			this.service.initPrevision(selectedPrevision);
		} catch (Exception e1) {

			e1.printStackTrace();
		}
			
	}
	
	
	public String depense(){
		
		try {
			if(this.selectedPrevision.reste < montant){ 
			FacesMessage message = new FacesMessage("la quantite de retrait est superieur a la quantite disponible");  
	        FacesContext.getCurrentInstance().addMessage(null, message);
	        return null;
			}else{
			this.service.saveDepense(this.selectedPrevision.idprevision,this.dateenreg,this.montant,this.description,this.typeprevision);
			Repertoire.addMessageinfo(OperationResults.EnregistrementOK);
			return "depenselisting";
			}
		} catch (Exception e) {
			if(e.getCause()!=null && e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Prevision cible non trouvé", e);
				return null;
			}
		}
		return "";
		
	}
	
	@PostConstruct
	public void init(){
		setListePre(this.service.listeprevisions());
		total=0;
		
		for(int i=0;i<listePre.size();i++){
			total+=listePre.get(i).getMontant();
		}
		
	}
	
	

}
