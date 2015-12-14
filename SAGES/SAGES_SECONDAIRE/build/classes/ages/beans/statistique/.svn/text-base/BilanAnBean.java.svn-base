package ages.beans.statistique;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.Service;
import ages.beans.GenericBean;
/**
 * AnneeEnCoursBean
 * Bean de gestion de l'anée scolaire en cours
 * @author DJIONANG
 *
 */

@ManagedBean(name="bilanAnBean")
@ViewScoped
public class BilanAnBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String anneeacademique;
	private String codeclasse;
	private String libelleclasse;
	private double moyenneclasse;
	private double moyeneepremier;
	private double moyennedernier;
	private float tauxechec;
	private float tauxreussite;
	private int effectif;
	private Date datedebut;
	private Date datefin;
	private Integer portee;
	private boolean loaded=false;

	// declaration et injection du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	public void setService(Service service) {
		this.service = service;
	}
	
	
	public String getAnneeacademique() {
		return anneeacademique;
	}
	public void setAnneeacademique(String anneeacademique) {
		this.anneeacademique = anneeacademique;
	}

	public String getCodeclasse() {
		return codeclasse;
	}


	public void setCodeclasse(String codeclasse) {
		this.codeclasse = codeclasse;
	}


	public Integer getPortee() {
		return portee;
	}


	public void setPortee(Integer portee) {
		this.portee = portee;
	}


	public double getMoyenneclasse() {
		return moyenneclasse;
	}


	public void setMoyenneclasse(double moyenneclasse) {
		this.moyenneclasse = moyenneclasse;
	}


	public float getTauxreussite() {
		return tauxreussite;
	}


	public void setTauxreussite(float tauxreussite) {
		this.tauxreussite = tauxreussite;
	}


	public int getEffectif() {
		return effectif;
	}


	public void setEffectif(int effectif) {
		this.effectif = effectif;
	}

	public Date getDatedebut() {
		return datedebut;
	}


	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}


	public Date getDatefin() {
		return datefin;
	}


	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}


	public String getLibelleclasse() {
		return libelleclasse;
	}


	public void setLibelleclasse(String libelleclasse) {
		this.libelleclasse = libelleclasse;
	}


	public double getMoyeneepremier() {
		return moyeneepremier;
	}


	public void setMoyeneepremier(double moyeneepremier) {
		this.moyeneepremier = moyeneepremier;
	}


	public double getMoyennedernier() {
		return moyennedernier;
	}


	public void setMoyennedernier(double moyennedernier) {
		this.moyennedernier = moyennedernier;
	}


	public float getTauxechec() {
		return tauxechec;
	}


	public void setTauxechec(float tauxechec) {
		this.tauxechec = tauxechec;
	}


	public boolean isLoaded() {
		return loaded;
	}


	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
	
	
	public void initBilanAn(){
		/*if(codeclasse==null){
			Repertoire.addMessageerreurParametreRequis("classe");
			return;
		}
		
		if(anneeacademique==null){
			Repertoire.addMessageerreurParametreRequis("Annee");
			return;
		}*/
		loaded=this.service.initbilanAn(this);
	}
	

}
