package ages.beans.etablissement.discipline;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.OperationResults;
import utils.Service;
import utils.Repertoire;

import ages.beans.GenericBean;
import ages.beans.eleve.EleveBean;
import ages.exception.ElementNOtFoundException;

/**
 * Bean géré de Sanction
 * Utilisé pour le CRUD Sanction
 * @author Le Bir
 *
 */


@ManagedBean(name="sanctionBean")
@ViewScoped
public class SanctionBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	public void setService(Service service) {
		this.service = service;
	}

	private int idsanction;
	private String matriculeeleve;
	private String nomeleve;
	private boolean annule;
	private Date datedecision;
	private Date dateeffet;
	private int duree;
	private String uniteduree;
	private String motif;
	private int codetype;
	private String libelletype;
	private String codeclasse;
	private List<EleveBean> eleves;
	private List<String> elevescibles;


	/**
	 * @return the idsanction
	 */
	public int getIdsanction() {
		return idsanction;
	}

	/**
	 * @param idsanction the idsanction to set
	 */
	public void setIdsanction(int idsanction) {
		this.idsanction = idsanction;
	}


	/**
	 * @return the nomeleve
	 */
	public String getNomeleve() {
		return nomeleve;
	}

	/**
	 * @param nomeleve the nomeleve to set
	 */
	public void setNomeleve(String nomeleve) {
		this.nomeleve = nomeleve;
	}

	/**
	 * @return the annule
	 */
	public boolean isAnnule() {
		return annule;
	}

	/**
	 * @param annule the annule to set
	 */
	public void setAnnule(boolean annule) {
		this.annule = annule;
	}

	/**
	 * @return the datedecision
	 */
	public Date getDatedecision() {
		if(datedecision==null)
			datedecision=new Date();
		return datedecision;
	}

	/**
	 * @param datedecision the datedecision to set
	 */
	public void setDatedecision(Date datedecision) {
		this.datedecision = datedecision;
	}

	/**
	 * @return the dateeffet
	 */
	public Date getDateeffet() {
		if(dateeffet==null)
			dateeffet=new Date();
		return dateeffet;
	}

	/**
	 * @param dateeffet the dateeffet to set
	 */
	public void setDateeffet(Date dateeffet) {
		this.dateeffet = dateeffet;
	}

	/**
	 * @return the duree
	 */
	public int getDuree() {
		return duree;
	}

	/**
	 * @param duree the duree to set
	 */
	public void setDuree(int duree) {
		this.duree = duree;
	}

	/**
	 * @return the uniteduree
	 */
	public String getUniteduree() {
		return uniteduree;
	}

	/**
	 * @param uniteduree the uniteduree to set
	 */
	public void setUniteduree(String uniteduree) {
		this.uniteduree = uniteduree;
	}

	/**
	 * @return the motif
	 */
	public String getMotif() {
		return motif;
	}

	/**
	 * @param motif the motif to set
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}

	/**
	 * @return the codetype
	 */
	public int getCodetype() {
		return codetype;
	}

	/**
	 * @param codetype the codetype to set
	 */
	public void setCodetype(int codetype) {
		this.codetype = codetype;
	}

	/**
	 * @return the libelletype
	 */
	public String getLibelletype() {
		return libelletype;
	}

	/**
	 * @param libelletype the libelletype to set
	 */
	public void setLibelletype(String libelletype) {
		this.libelletype = libelletype;
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
	 * @return the elevescibles
	 */
	public List<String> getElevescibles() {
		return elevescibles;
	}

	/**
	 * @param elevescibles the elevescibles to set
	 */
	public void setElevescibles(List<String> elevescibles) {
		this.elevescibles = elevescibles;
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

	public String saveSanction(){
		try {
			this.service.saveSanction(motif, codetype, Repertoire.convertDuree(uniteduree, duree), new Date(),dateeffet,elevescibles);
		} 
		catch (Exception e) {
			if(e.getCause()!=null){
				
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Type Sanction non trouvé",e);
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
		Repertoire.addMessageinfoEnregistrementOK("Sanction");
		return "dissanctlisting";
	}

	public String modifierSanction(){
		try {
			this.service.modifierSanction(idsanction,motif, codetype, Repertoire.convertDuree(uniteduree, duree), datedecision,dateeffet,annule);
			
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Sanction non trouvée",e);
					return null;
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
		Repertoire.addMessageinfoModificationOK("Sanction");
		return OperationResults.navWithParam("visualisation", "codesanction", String.valueOf(idsanction));
	}

	/**
	 * Supression de salle
	 * @return le chemin de navigation pour la suite
	 */
	public String supprimerSanction(){
		
		try {
			this.service.annulerSanction(idsanction);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Sanction non trouvée");				}
				else{
					Repertoire.addMessageerreurInnatendue(e);
				}
			}
			else{
				Repertoire.addMessageerreurInnatendue(e);
			}
			return null;
				
		}
		Repertoire.addMessageinfoSuppressionOK("Sanction");
		return "dissanctlisting";
	}

	public void initSanction(){
		if(this.idsanction==0){
			Repertoire.addMessageerreur("Initialisation impossible, code Sanction non renseigné");
			return;
		}
		this.service.initialiseSanctionBean(this);
	}
	
	public void loadEleves(){
		if(codeclasse!=null && !codeclasse.isEmpty()){
			eleves=this.service.listerElevesinscrits(codeclasse);
			if(eleves.isEmpty())
				Repertoire.addMessageerreur("Aucun élève trouvé");
		}
	}
	
}
