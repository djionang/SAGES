package ages.beans.materiel;

import java.io.Serializable;
import java.util.Date;

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


@ManagedBean(name="materielBean")
@ViewScoped
public class MaterielBean extends GenericBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// declaration du service
			@ManagedProperty(value="#{service}")
			protected Service service;
			private int idmateriel;
			private String codemateriel;
			private String designation;
			private String typemateriel;
			private int quantite;
			private Date dateenreg;
			private float prix;
			private String etat;
			private int quantiteF;
			private String numeroserie;
			private String raison;
			private String utlisateur;
			private int reste;
			
			private MaterielBean selectedMateriel;
			
			
			public MaterielBean() {
			}
			

			public void setService(Service service) {
				this.service = service;
			}
			
			
			public String getCodemateriel() {
				return codemateriel;
			}
			public void setCodemateriel(String codemateriel) {
				this.codemateriel = codemateriel;
			}
			public String getDesignation() {
				return designation;
			}
			public void setDesignation(String designation) {
				this.designation = designation;
			}
			public String getTypemateriel() {
				return typemateriel;
			}
			public void setTypemateriel(String typemateriel) {
				this.typemateriel = typemateriel;
			}
			public int getQuantite() {
				return quantite;
			}
			public void setQuantite(int quantite) {
				this.quantite = quantite;
			}
			public float getPrix() {
				return prix;
			}
			public void setPrix(float prix) {
				this.prix = prix;
			}
			
			
			public String getEtat() {
				return etat;
			}
			public void setEtat(String etat) {
				this.etat = etat;
			}
			public int getQuantiteF() {
				return quantiteF;
			}
			public void setQuantiteF(int quantiteF) {
				this.quantiteF = quantiteF;
			}
			public Date getDateenreg() {
				return dateenreg;
			}
			public void setDateenreg(Date dateenreg) {
				this.dateenreg = dateenreg;
			}
			
			public MaterielBean getSelectedMateriel() {
				return selectedMateriel;
			}
			public void setSelectedMateriel(MaterielBean selectedMateriel) {
				this.selectedMateriel = selectedMateriel;
			}
			
			
			public int getIdmateriel() {
				return idmateriel;
			}


			public void setIdmateriel(int idmateriel) {
				this.idmateriel = idmateriel;
			}


			public String getNumeroserie() {
				return numeroserie;
			}


			public void setNumeroserie(String numeroserie) {
				this.numeroserie = numeroserie;
			}


			public String getRaison() {
				return raison;
			}


			public void setRaison(String raison) {
				this.raison = raison;
			}


			public String getUtlisateur() {
				return utlisateur;
			}


			public void setUtlisateur(String utlisateur) {
				this.utlisateur = utlisateur;
			}


			public int getReste() {
				return reste;
			}


			public void setReste(int reste) {
				this.reste = reste;
			}


			public String savemateriel(){
				
				try {
					if(quantite < quantiteF){ 
						FacesMessage message = new FacesMessage("la quantite fonctionnelle est superieur a la quantite disponible");  
				        FacesContext.getCurrentInstance().addMessage(null, message);
				        return null;
					}else
					this.service.saveMateriel(this);
					Repertoire.addMessageinfo(OperationResults.EnregistrementOK);
					return "materiellisting";
					
				} catch (Exception e) {
					if(e.getCause()!=null && e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Etablissement cible non trouvé", e);
						return null;
					}
				}
				return "";
				
			}
			
			
			public String enregMateriel(){
				
				try {
					if(quantite < quantiteF){ 
						FacesMessage message = new FacesMessage("la quantite fonctionnelle est superieur a la quantite disponible");  
				        FacesContext.getCurrentInstance().addMessage(null, message);
				        return null;
						}else{
					this.service.saveMaterielAjouter(this.selectedMateriel.codemateriel,this.selectedMateriel.designation,this.selectedMateriel.typemateriel,this.dateenreg,this.quantite,this.quantiteF,this.prix,this.numeroserie);
					Repertoire.addMessageinfo(OperationResults.EnregistrementOK);
					return "materiellisting";
						}
				} catch (Exception e) {
					if(e.getCause()!=null && e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Materiel cible non trouvé", e);
						return null;
					}
				}
				
				return "";
			}
			
			public String sortieMateriel(){
				
				try {
					if(this.selectedMateriel.reste < quantiteF){ 
					FacesMessage message = new FacesMessage("la quantite de retrait est superieur a la quantite disponible");  
			        FacesContext.getCurrentInstance().addMessage(null, message);
			        return null;
					}else{
					this.service.saveMaterielRetrait(this.selectedMateriel.idmateriel,this.selectedMateriel.codemateriel,this.selectedMateriel.designation,this.selectedMateriel.typemateriel,this.dateenreg,this.quantite,this.quantiteF,this.prix,this.selectedMateriel.quantiteF,this.selectedMateriel.quantite,this.selectedMateriel.numeroserie,raison,utlisateur);
					Repertoire.addMessageinfo(OperationResults.EnregistrementOK);
					return "sortielisting";
					}
				} catch (Exception e) {
					if(e.getCause()!=null && e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Materiel cible non trouvé", e);
						return null;
					}
				}
				return "";
				
			}
			
			
			public String modifiermateriel(){
				try {
					this.service.modifierMateriel(this);
				} catch (ElementNOtFoundException e) {
					Repertoire.addMessageerreur(OperationResults.UpdateNO+" Materiel non trouvée");
					return OperationResults.FAILURE;
				}
				Repertoire.addMessageinfo(OperationResults.UpdateOK);
				return OperationResults.navWithParam("visualisation", "codemateriel", this.codemateriel);
			}
			
			
			public String supprimermateriel(){
				if (this.idmateriel!=0){
					try {
						this.service.supprimerMateriel(this.idmateriel);
					} catch (ElementNOtFoundException e) {
						Repertoire.addMessageerreur(OperationResults.SuppressionNO+" Materiel non trouvé");
						return OperationResults.FAILURE;
					}
				}
				Repertoire.addMessageinfo(OperationResults.SuppressionOK);
				return "materiellisting";
			}
			
			
			public void initialize(){
				
				if(this.idmateriel!=0)
					this.service.initialiseMaterielBean(this);
			}
			
			
			public void loadMateriel(){
				if(this.idmateriel==0)  // aucun matricule passé en paramètre
					return;
				if(selectedMateriel!=null&&selectedMateriel.getIdmateriel()!=idmateriel){
					this.chargerMateriel();
					return;
				}
				
				if(selectedMateriel==null)
					this.chargerMateriel();
			}
			
			
			public void chargerMateriel(){
				try {
					selectedMateriel=new MaterielBean();
					selectedMateriel.setIdmateriel(idmateriel);
					this.service.initMateriel(selectedMateriel);
				} catch (Exception e1) {

					e1.printStackTrace();
				}
					
			}
			
			public void chargerMateriel(ActionEvent e){
				try {
					selectedMateriel=new MaterielBean();
					selectedMateriel.setIdmateriel(idmateriel);
					this.service.initMateriel(selectedMateriel);
				} catch (Exception e1) {

					e1.printStackTrace();
				}
					
			}
			
			

}
