package ages.beans.etablissement.discipline;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.Repertoire;
import utils.Service;

import ages.beans.GenericBean;
import ages.beans.eleve.EleveBean;


@ManagedBean(name="listRetardBean")
@ViewScoped
public class ListRetardBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private String codeclasse;
	private List<RetardBean> retards;
	private List<EleveBean> eleves;
	private String matriculeeleve;
	
	
	public void setService(Service service) {
		this.service = service;
	}


	/**
	 * @return the retards
	 */
	public List<RetardBean> getRetards() {
		return retards;
	}


	/**
	 * @param retards the retards to set
	 */
	public void setRetards(List<RetardBean> retards) {
		this.retards = retards;
	}
	
	/**
	 * @return the codeclasse
	 */
	public String getCodeclasse() {
		return codeclasse;
	}


	/**
	 * @param codeclasse the codeclasse to set
	 */
	public void setCodeclasse(String codeclasse) {
		this.codeclasse = codeclasse;
	}


	/**
	 * @return the eleves
	 */
	public List<EleveBean> getEleves() {
		return eleves;
	}


	/**
	 * @param eleves the eleves to set
	 */
	public void setEleves(List<EleveBean> eleves) {
		this.eleves = eleves;
	}


	/**
	 * @return the matriculeeleve
	 */
	public String getMatriculeeleve() {
		return matriculeeleve;
	}


	/**
	 * @param matriculeeleve the matriculeeleve to set
	 */
	public void setMatriculeeleve(String matriculeeleve) {
		this.matriculeeleve = matriculeeleve;
	}


	public void loadRetards(){
		if(matriculeeleve!=null && !matriculeeleve.isEmpty()){
			retards=this.service.listerRetardsEleve(matriculeeleve);
			if(eleves.isEmpty())
				Repertoire.addMessageerreur("Aucun retard trouvé");
		}
		else{
			retards=null;
		}
	}
	
	public void loadElevesClasse(){
		if(codeclasse!=null && !codeclasse.isEmpty()){
			eleves=this.service.listerElevesinscrits(codeclasse);
			if(eleves.isEmpty())
				Repertoire.addMessageerreur("Aucun élève trouvé");
			retards=this.service.listerRetardsClasse(codeclasse);
		}
		else{
			eleves=null;
			retards=null;
		}
	}
}
