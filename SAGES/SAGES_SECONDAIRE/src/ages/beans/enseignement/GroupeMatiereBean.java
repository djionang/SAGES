package ages.beans.enseignement;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;


@ManagedBean(name="gmBean")
@RequestScoped
public class GroupeMatiereBean extends GenericBean implements Serializable{

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private static final long serialVersionUID = 1L;

	private String libelle;
	private String description;
	
	public GroupeMatiereBean(){
		
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

	public void setService(Service service) {
		this.service = service;
	}

	public String savegm(){
		try {
			this.service.saveGroupeMatiere(libelle,description);
		} catch (Exception e) {
			if(e.getCause()!=null&&e.getCause().equals(DuplicateKeyException.class)){
				Repertoire.addMessageerreur("Matiere déja existante");
			}
			else{
				Repertoire.logError("Erreur a l'enregistrement d'une matiere", getClass(), e);
				Repertoire.addMessageerreur("Erreur innatendue! Veuillez contacter l'administrateur");
			}
			return null;
		}
		Repertoire.addMessageinfoEnregistrementOK("Matiere");
		return OperationResults.navWithParam("visualisation", "libellegm", libelle);
	}
	
	
	public void initgm(){
		if(this.libelle!=null&& !this.libelle.isEmpty()){
			this.service.initGroupeMatiere(this);
		}		
	}
	
	
	public String supprimergm(){
		try {
			this.service.supprimerGroupeMatiere(libelle);
		} catch (Exception e) {
			if(e.getCause()!=null&&e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Matiere non trouvée");
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
			}
			return null;	
		}
		Repertoire.addMessageinfoSuppressionOK("Matiere");
		return "listing";
	}
	
	public String modifiergm(){
		try {
			this.service.modifierGroupeMatiere(libelle,description);
		}
		catch (Exception e) {
			if(e.getCause()!=null&&e.getCause().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreur("Matiere non trouvée");
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue! Veuillez contacter l'administrateur");
			}
			return null;
		}
		Repertoire.addMessageinfoModificationOK("Matiere");
		return OperationResults.navWithParam("visualisation", "libellegm", libelle);
	
	}

}
