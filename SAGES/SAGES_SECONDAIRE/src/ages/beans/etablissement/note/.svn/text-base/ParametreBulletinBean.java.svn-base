package ages.beans.etablissement.note;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;

@ManagedBean(name="paramBullBean")
@ViewScoped
public class ParametreBulletinBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
		
	private int modele;
	
	
	public ParametreBulletinBean() {
	}
	

	public void setService(Service service) {
		this.service = service;
	}


	
	public int getModele() {
		return modele;
	}


	public void setModele(int modele) {
		this.modele = modele;
	}


	public String enregistrerParametres(){
		this.service.enregistrerModeleBulletin(modele);
		Repertoire.addMessageinfo("Modèle Bulletin enregistré");
		return null;
	}
	
	@PostConstruct
	public void init(){
		modele=this.service.rechercherModeleBulletin();
	}
	
}
