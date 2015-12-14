package ages.beans.etablissement.discipline;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import utils.Repertoire;
import utils.Service;

import ages.beans.GenericBean;
import ages.exception.ClassToBeanCopyException;
import ages.exception.JPAException;


@ManagedBean(name="listConvocationBean")
@RequestScoped
public class ListConvocationBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;	

	private List<ConvocationBean> listeConvocations;	
	
	private Date datedebut;
	private Date datefin;

	public void setService(Service service) {
		this.service = service;
	}

	
	public List<ConvocationBean> getListeConvocations() {
		return listeConvocations;
	}


	public void setListeConvocations(List<ConvocationBean> listeConvocations) {
		this.listeConvocations = listeConvocations;
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

	@PostConstruct
	public void initSanctions(){
		this.setListeConvocations(this.service.listeConvocations());
	}
	
	public void chargerConvocations(ActionEvent event){
		if(datefin.before(datedebut)){
			Repertoire.addMessageerreur("Impossible de charger, la période de chargement est incohérente");
			return;
		}
		try {
			setListeConvocations(this.service.listeConvocations(datedebut,datefin));
		} 
		catch (Exception e) {
			if(e.getCause().getClass().equals(JPAException.class)){
				Repertoire.addMessageerreur("Erreur grave lors de la recherche des convocations enregistrées");
				
			}
			else
				if(e.getCause().getClass().equals(ClassToBeanCopyException.class)){
					Repertoire.addMessageerreur("Erreur de cohérence lors de la recherche des convocations enregistrées");
				}
				else
					Repertoire.addMessageerreur("Erreur Innatendue, Veuillez contacter l'administrateur");
			
			
		}
	}

}
