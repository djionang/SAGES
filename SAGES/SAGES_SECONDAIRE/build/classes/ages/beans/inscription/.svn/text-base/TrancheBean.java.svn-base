package ages.beans.inscription;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.exception.ElementNOtFoundException;

@ManagedBean(name="trancheBean")
@RequestScoped
public class TrancheBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private int id;  //numero de la tranche
	private float montant;
	private Date delaiVersement;
	private String type;
	
	private String codeclasse;
	
	private boolean loaded=false;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	public Date getDelaiVersement() {
		return delaiVersement;
	}
	public void setDelaiVersement(Date delaiVersement) {
		this.delaiVersement = delaiVersement;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCodeclasse() {
		return codeclasse;
	}
	public void setCodeclasse(String codeclasse) {
		this.codeclasse = codeclasse;
	}
	
	public boolean isLoaded() {
		return loaded;
	}
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
	

	public void setService(Service service) {
		this.service = service;
	}

	
	public void initTrancheclasse(){
		
		/*if(codeclasse==null){
			
			Repertoire.addMessageerreurParametreRequis("classe");
			return;
		}
		
		if(id==0){
			Repertoire.addMessageerreurParametreRequis("Tranche");
			return;
		}*/
		loaded=this.service.initTrancheClasse(this);
	}
	
	
	public String modifierTranche(){
		
		try {
			this.service.modifierParametreTranche(this.codeclasse,this.id,this.type, this.montant, this.delaiVersement);
		} catch (Exception e) {
			if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreurElementNonTrouve("ParamatreTranche");
				Repertoire.logError("ParametreTranche non trouvé", getClass(), e);
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				Repertoire.logFatal("Erreur Innatendue", getClass(), e);
			}
			return null;
		}
		
		Repertoire.addMessageinfoModificationOK("");
		return  OperationResults.navWithParam("parametres", "codeclasse", this.codeclasse);
	}
	
	
	public String supprimerTranche(){
		
		try {
			this.service.deleteParametreTranche(this.codeclasse, this.id);
		} catch (Exception e) {
			if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreurElementNonTrouve("ParamatreTranche");
				Repertoire.logError("ParametreTranche non trouvé", getClass(), e);
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				Repertoire.logFatal("Erreur Innatendue", getClass(), e);
			}
			return null;
		}
	
		Repertoire.addMessageinfoSuppressionOK("Tranche");
		return  OperationResults.navWithParam("parametres", "codeclasse", this.codeclasse);
	}
		
}
