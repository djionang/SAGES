package ages.beans.inscription;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.exception.ElementNOtFoundException;

@ManagedBean(name="VersementBean")
@ViewScoped
public class VersementBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	private String id;
	private String nom;
	private String prenom;
	private String matricule;
	private String classe;
	private String libelleclasse;
	private float montant;
	private Date dateVers;
	private String typeversement;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	public Date getDateVers() {
		return dateVers;
	}
	public void setDateVers(Date dateVers) {
		this.dateVers = dateVers;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getTypeversement() {
		return typeversement;
	}
	public void setTypeversement(String typeversement) {
		this.typeversement = typeversement;
	}
	
	public void setService(Service service) {
		this.service = service;
	}

	
	public String getLibelleclasse() {
		return libelleclasse;
	}
	public void setLibelleclasse(String libelleclasse) {
		this.libelleclasse = libelleclasse;
	}
	public void initVersement(){
		if(id!=null){
			this.service.initVersementBean(this);
		}
		/*else{
			System.out.println("je suis pluot ici avec mon code versement"+   id);
			Repertoire.logDebug("Code versement null", ages.beans.inscription.VersementBean.class);
			Repertoire.addMessageerreurParametreRequis("code versement");
		}*/
	}
	
	
	public String modifierVersement(){
		
		try {
			this.service.modifierVersement(this.id,this.typeversement, this.montant, this.dateVers);
		} catch (Exception e) {
			if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
				Repertoire.addMessageerreurElementNonTrouve("Versement");
				Repertoire.logError("Versement non trouvé", getClass(), e);
			}
			else{
				Repertoire.addMessageerreur("Erreur innatendue, Veuillez contacter l'administrateur");
				Repertoire.logFatal("Erreur Innatendue", getClass(), e);
			}
			return null;
		}
		
		Repertoire.addMessageinfoModificationOK("");
		return "inscriptiond?faces-redirect=true&includeViewParams=true&matricule="+matricule+"&idversement="+id;
	}
	
	
	
}
