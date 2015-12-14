package ages.beans.enseignement;

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


@ManagedBean(name="partieCoursBean")
@RequestScoped
public class PartieCoursBean extends GenericBean implements Serializable{

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private static final long serialVersionUID = 1L;

	private int codecours;
	
	private int codepartie;
	
	private String libelle;
	private String description;

	private Date datedebut;
	private Date datefin;

	
	public PartieCoursBean(){
		
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getCodecours() {
		return codecours;
	}

	public void setCodecours(int codecours) {
		this.codecours = codecours;
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

	/**
	 * @return the codepartie
	 */
	public int getCodepartie() {
		return codepartie;
	}

	/**
	 * @param codepartie the codepartie to set
	 */
	public void setCodepartie(int codepartie) {
		this.codepartie = codepartie;
	}

	/**
	 * @return the datedebut
	 */
	public Date getDatedebut() {
		return datedebut;
	}

	/**
	 * @param datedebut the datedebut to set
	 */
	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	/**
	 * @return the datefin
	 */
	public Date getDatefin() {
		return datefin;
	}

	/**
	 * @param datefin the datefin to set
	 */
	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public void initpartie(){
		if(codepartie!=0){
			this.service.initPartieCours(this);
		}
	}
	
	public String modifierPartieCours(){
		try {
			this.service.modifierPartieCours(codepartie,libelle,description,datedebut,datefin,codecours);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.logError("Partie cours non trouvée, echec modification", getClass(), e);
					Repertoire.addMessageerreur("Partie cours non trouvée, echec modification");
				}
				else{
					Repertoire.addMessageerreurInnatendue(e);
				}
			}
			else{
				Repertoire.addMessageerreurInnatendue(e);
			}
			return null;
		}
		Repertoire.addMessageinfoModificationOK("Partie [Chapitre]");
		return OperationResults.navWithParam("parties-chapitres", "codepartie", String.valueOf(codepartie));
	}
	
	public String supprimerPartieCours(){
		try {
			this.service.supprimerPartieCours(codepartie);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreurElementNonTrouve("Partie Cours ou Cours");
				}
				else{
					Repertoire.addMessageerreurInnatendue(e);
				}					
			}
			else{
				Repertoire.addMessageerreurInnatendue(e);
			}
			return null;
		}
		Repertoire.addMessageinfoSuppressionOK("Partie [Chapitre]");
		return OperationResults.navWithParam("parties-chapitres", "codepartie", String.valueOf(codepartie));
	}
	
}
