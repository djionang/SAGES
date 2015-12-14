package ages.beans.etablissement.salle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import utils.Service;

import ages.beans.GenericBean;


@ManagedBean(name="listSalleBean")
@RequestScoped
public class ListSalleBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	

	private List<SalleBean> listeSalles;
	
	private List<SalleBean> listeAteliers;
	
	private List<SalleBean> listeAll;
	
	
	public List<SalleBean> getListeSalles() {
		return listeSalles;
	}


	public void setListeSalles(List<SalleBean> listeSalles) {
		this.listeSalles = listeSalles;
	}


	public List<SalleBean> getListeAteliers() {
		return listeAteliers;
	}


	public void setListeAteliers(List<SalleBean> listeAteliers) {
		this.listeAteliers = listeAteliers;
	}
	

	public void setService(Service service) {
		this.service = service;
	}



	public List<SalleBean> getListeAll() {
		return listeAll;
	}


	public void setListeAll(List<SalleBean> listeAll) {
		this.listeAll = listeAll;
	}


	@PostConstruct
	public void initSalles(){
		listeAteliers=new ArrayList<SalleBean>();
		listeSalles=new ArrayList<SalleBean>();
		setListeAll(this.service.listerSalles());
		filtreAteliersSalle(this.listeAll,listeAteliers,listeSalles);
	}

	
	/**
	 * Filtre parmi l'ensemble des ateliers une liste de salles
	 * @param listeSal 
	 * @param listeAt 
	 * @param listesal liste de salle a filtrer
	 * @return la liste d'ateliers incluse dans la liste des salles
	 */
	private void filtreAteliersSalle(List<SalleBean> listeall, List<SalleBean> listeAt, List<SalleBean> listeSal) {
		
		for(int i=0;i<listeall.size();i++){
			if(listeall.get(i).getType().compareToIgnoreCase("atelier")==0)
				listeAt.add(listeall.get(i));
			else
				listeSal.add(listeall.get(i));
		}
	}
}
