package ages.beans.eleve;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.beans.etablissement.classe.ClasseBean;

/**
 * Classe ListEleveBean
 * Bean de gestion de la liste d'eleves
 * @author Bri
 *
 */

@ManagedBean(name="listEleveBean")
@ViewScoped
public class ListEleveBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<EleveBean> eleves;
	private EleveBean selectedEleve;
	
	private ClasseBean selectedClasse;
	
	private String classe;
	
	private EleveDataModel modeleEleve;	
	
	public List<EleveBean> getEleves() {
		return eleves;
	}

	public void setEleves(List<EleveBean> eleves) {
		this.eleves = eleves;
	}

	public EleveBean getSelectedEleve() {
		return selectedEleve;
	}

	public void setSelectedEleve(EleveBean selectedEleve) {
		this.selectedEleve = selectedEleve;
	}
	
	public EleveDataModel getModeleEleve() {
		return modeleEleve;
	}

	public void setModeleEleve(EleveDataModel modeleEleve) {
		this.modeleEleve = modeleEleve;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public ClasseBean getSelectedClasse() {
		return selectedClasse;
	}

	public void setSelectedClasse(ClasseBean selectedClasse) {
		this.selectedClasse = selectedClasse;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}


	/**
	 * Initialisation du bean avec la liste des eleves devant s'inscrire
	 */
	@PostConstruct
	protected void init(){
		eleves=this.service.listerElevesinscrits();
		modeleEleve = new EleveDataModel(eleves);
		
	}
	
	public void loadEleves(ActionEvent ev){
		if(classe!=null){
			if(classe.isEmpty()){
				eleves=this.service.listerElevesinscrits();
				modeleEleve = new EleveDataModel(eleves);
			}
			else{
				System.out.println("**********************************"+classe);
				eleves=this.service.listerElevesinscrits(classe);
				modeleEleve = new EleveDataModel(eleves);
				System.out.println("***********TAILLE***********************"+eleves.size());
				System.out.println("**************TAILLE********************"+eleves.size());
			}				
		}
	}
	
	
	


	public void loadEleveEnregle(ActionEvent e){
		if(selectedClasse!=null){
			eleves=this.service.listerEleveEnregle(selectedClasse.getCodeClasse());
		}
		else{
			Repertoire.addMessageerreur("Aucune filiere sélectionnée");
		}
	}
	
	public void loadEleveNonEnregle(ActionEvent e){
		if(selectedClasse!=null){
			eleves=this.service.listerEleveNonEnregle(selectedClasse.getCodeClasse());
		}
		else{
			Repertoire.addMessageerreur("Aucune filiere sélectionnée");
		}
	}
	
	
	public void loadEleveInscrits(ActionEvent e){
		if(selectedClasse!=null){
			eleves=this.service.listerElevesinscrits(selectedClasse.getCodeClasse());
		}
		else{
			Repertoire.addMessageerreur("Aucune filiere sélectionnée");
		}
	}
	
	
	public String imprimerNonEnregle(){
		if(selectedClasse!=null){
			this.service.imprimerElevesNonEnRegle(selectedClasse.getCodeClasse());
			
		}
		else{
			this.service.imprimerElevesNonEnregle();
		}
		
		return null;
	}
	
	public String imprimerElevesEnregle(){
		
		try{
			if(selectedClasse!=null){
				this.service.imprimerElevesEnRegle(service,selectedClasse.getCodeClasse());
				
			}
			/*else{
				this.service.imprimerElevesEnregle();
			}*/
		}
		catch(Exception e){
			Repertoire.addMessageerreur("Erreur survenue lors de l'impression");
			e.printStackTrace();
		}
		return null;
	}
	
	public String imprimerInscrits(){
		try{
			if(selectedClasse!=null){
		
				this.service.imprimerElevesInscrits(selectedClasse.getCodeClasse());
				
			}
			/*else{
				this.service.imprimerElevesInscrits();
			}*/
		}
		catch(Exception e){
			Repertoire.addMessageerreur("Erreur survenue lors de l'impression");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public String imprimerListeElevesClasse(){
		try{
			if(classe!=null && !classe.isEmpty()){
		
				this.service.imprimerElevesInscrits(classe);
				
			}
			else{
				Repertoire.addMessageerreur("Veuillez selecttionner une filiere pour l'impression");
			}
		}
		catch(Exception e){
			Repertoire.addMessageerreur("Erreur survenue lors de l'impression");
			e.printStackTrace();
		}
		
		return null;
	}
	
}
