package ages.beans.enseignement;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;

/**
 * Classe ListEleveBean
 * Bean de gestion de la liste d'eleves
 * @author Bri
 *
 */

@ManagedBean(name="listCdtBean")
@ViewScoped
public class ListCdtBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private String codeclasse;
	private List<CdtBean> cdts;
	private int codecours;
	
	private List<CoursBean> cours;
	
	private String classe;
	
	
	public void setService(Service service) {
		this.service = service;
	}

	public List<CoursBean> getCours() {
		return cours;
	}

	public void setCours(List<CoursBean> cours) {
		this.cours = cours;
	}


	public String getCodeclasse() {
		return codeclasse;
	}

	public void setCodeclasse(String codeclasse) {
		this.codeclasse = codeclasse;
	}

	public List<CdtBean> getCdts() {
		return cdts;
	}

	public void setCdts(List<CdtBean> cdts) {
		this.cdts = cdts;
	}

	public int getCodecours() {
		return codecours;
	}

	public void setCodecours(int codecours) {
		this.codecours = codecours;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}


	public void loadCours(){
		if(codeclasse!=null){
			codecours=0;
			if(codeclasse.isEmpty()){
				cours=this.service.listecours();
			}
			else{
				cours=this.service.listecours(codeclasse);
			}	
		}
	}
	
	
	public void loadCdt(){
		if(codecours!=0){
			cdts=this.service.listerCdts(codecours);
		}
		else
			Repertoire.addMessageerreur("Aucun cours sélectionné");
	}
	
}
