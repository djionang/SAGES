package ages.beans.inscription;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.DuplicateKeyException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.beans.etablissement.classe.ClasseBean;
import ages.exception.ElementNOtFoundException;

@ManagedBean(name="parametreTrancheBean")
@ViewScoped
public class ParametreTrancheBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	

	private int id=-1;  //numero de la tranche
	private float montant;
	private String type;
	private Date delai;
	
	private List<TrancheBean> listeTranches;
	
	private ClasseBean classe;
	
	private TrancheBean selectedTranche;
	
	private String codeclasse;
	
	
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
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ClasseBean getClasse() {
		return classe;
	}
	public void setClasse(ClasseBean classe) {
		this.classe = classe;
	}
	public List<TrancheBean> getListeTranches() {
		return listeTranches;
	}
	public void setListeTranches(List<TrancheBean> listeTranches) {
		this.listeTranches = listeTranches;
	}
	
	
	public String getCodeclasse() {
		return codeclasse;
	}
	
	public void setCodeclasse(String codeclasse) {
		this.codeclasse = codeclasse;		
	}
	

	public void setService(Service service) {
		this.service = service;
	}

	
	public Date getDelai() {
		return delai;
	}
	public void setDelai(Date delai) {
		this.delai = delai;
	}
	
	
	public TrancheBean getSelectedTranche() {
		return selectedTranche;
	}
	public void setSelectedTranche(TrancheBean selectedTranche) {
		this.selectedTranche = selectedTranche;
	}
	public String enregistrerTranche(){
		if(this.classe==null){
			Repertoire.addMessageerreur("Aucune classe selectionnée");
		}
		else{
			try {
				this.service.saveParametreTranche(this.classe.getCodeClasse(), this.type, this.montant, this.delai);
			} 
			catch (Exception e) {
				if(e.getCause().getClass().isInstance(ElementNOtFoundException.class))
					Repertoire.addMessageerreur("Erreur, Probleme de cohérence dans les données");
				else
					if(e.getCause().getClass().isInstance(DuplicateKeyException.class))
			
						Repertoire.addMessageerreur("Erreur, Tranche déja enregistrée");
					return "";
				
			}
			
		}
		Repertoire.addMessageinfo("Nouvelle tranche sauvegardée");
		return  OperationResults.navWithParam("enregistrerTranche", "codeclasse", this.classe.getCodeClasse());
	}
	
	
	
		
	
	/**
	 * Initialise la liste des paramètres de tranches de la classe sélectionnée
	 * Est appélée au chargement de la page enregistrerTranche
	 */
	public void initParamclasse(){
		if(codeclasse==null){
			Repertoire.addMessageerreurParametreRequis("classe");
			return;
		}
		if(classe==null){	//instancier la classe
			classe=new ClasseBean();
		}
		classe.setCodeClasse(codeclasse);
		
		//recherche la classe de code celui passé en parametre de page et le transmettre a la classe en cours
		this.service.initialiseClasseBean(classe);
		if((classe.getLibelle()==null) || classe.getLibelle().isEmpty()){
			Repertoire.addMessageerreurParametreIncorrect("Classe", codeclasse);
			return;
		}
		listeTranches=this.service.listeTranchesClasse(classe.getCodeClasse());
		
	}
	
	public String retourner(){
		return  OperationResults.navWithParam("parametres", "codeclasse", this.codeclasse);
	}
	
}
