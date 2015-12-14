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


@ManagedBean(name="listAbsenceBean")
@ViewScoped
public class ListAbsenceBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private String codeclasse;
	private List<AbsenceBean> absences;
	private List<EleveBean> eleves;
	private String matriculeeleve;
	
	
	public void setService(Service service) {
		this.service = service;
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


	public List<AbsenceBean> getAbsences() {
		return absences;
	}


	public void setAbsences(List<AbsenceBean> absences) {
		this.absences = absences;
	}


	public void loadRetards(){
		if(matriculeeleve!=null && !matriculeeleve.isEmpty()){
			setAbsences(this.service.listerAbsencesEleve(matriculeeleve));
			if(eleves.isEmpty())
				Repertoire.addMessageerreur("Aucun retard trouvé");
		}
	}
	
	public void loadElevesClasse(){
		if(codeclasse!=null && !codeclasse.isEmpty()){
			eleves=this.service.listerElevesinscrits(codeclasse);
			if(eleves.isEmpty())
				Repertoire.addMessageerreur("Aucun élève trouvé");
			setAbsences(this.service.listerAbsencesClasse(codeclasse));
		}
	}
}
