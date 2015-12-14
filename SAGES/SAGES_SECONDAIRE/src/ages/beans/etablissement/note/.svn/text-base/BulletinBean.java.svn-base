package ages.beans.etablissement.note;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.beans.eleve.EleveBean;

@ManagedBean(name="bulletinBean")
@ViewScoped
public class BulletinBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
		
	private int codesequence;
	
	private String codeclasse;
	
	private String matriculeeleve;
		
	private List<EleveBean> listeeleves;
	
	public BulletinBean() {
	}
	

	public void setService(Service service) {
		this.service = service;
	}



	public int getCodesequence() {
		return codesequence;
	}


	public void setCodesequence(int codesequence) {
		this.codesequence = codesequence;
	}


	public String getCodeclasse() {
		return codeclasse;
	}


	public void setCodeclasse(String codeclasse) {
		this.codeclasse = codeclasse;
	}


	public String getMatriculeeleve() {
		return matriculeeleve;
	}


	public void setMatriculeeleve(String matriculeleve) {
		this.matriculeeleve = matriculeleve;
	}


	public List<EleveBean> getListeeleves() {
		return listeeleves;
	}


	public void setListeeleves(List<EleveBean> listeeleves) {
		this.listeeleves = listeeleves;
	}


	public void loadElevesClasse(){
		if(codeclasse!=null && ! codeclasse.isEmpty())
			this.listeeleves=this.service.listerElevesinscrits(codeclasse);
	}
	
	
	public void loadBulletin(ActionEvent e){
		Repertoire.addMessageerreur("Non encore implementé");
	}
	
}
