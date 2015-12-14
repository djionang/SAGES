package ages.beans.etablissement.note;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.sf.jasperreports.engine.JRException;

import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.beans.eleve.EleveBean;

@ManagedBean(name="printBullBean")
@ViewScoped
public class ImpressionBulletinBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
		
	private Integer portee;
	
	private String codeclasse;
	
	private boolean chekbox=false;
	
	private boolean chekbox1=false;
			
	private List<EleveBean> listeeleves;
	
	private List<Integer> elevestoprint;
	
    private List<EleveBean> listeelevesA; //gestion de la liste des moyennes des élèves par classe
    
    
    private List<EleveBean> listeelevesE; //gestion de la liste des états financier des élèves par classe
    
    private List<EleveBean> listeelevesS; //gestion de la liste des moyennes des élèves par classe et sequence
    
    private List<EleveBean> listeelevesT; //gestion de la liste des moyennes des élèves par classe et trimestre
	
	private List<Integer> elevestoprintA;
	
	public ImpressionBulletinBean() {
	
	}
	

	public void setService(Service service) {
		this.service = service;
	}



	public List<Integer> getElevestoprint() {
		
		return elevestoprint;
	}


	public void setElevestoprint(List<Integer> elevestoprint) {
		this.elevestoprint = elevestoprint;
	}


	public String getCodeclasse() {
		return codeclasse;
	}


	public void setCodeclasse(String codeclasse) {
		this.codeclasse = codeclasse;
	}


	public List<EleveBean> getListeeleves() {
		return listeeleves;
	}


	public void setListeeleves(List<EleveBean> listeeleves) {
		this.listeeleves = listeeleves;
	}


	/**
	 * @return the portee
	 */
	public Integer getPortee() {
		return portee;
	}


	/**
	 * @param portee the portee to set
	 */
	public void setPortee(Integer portee) {
		this.portee = portee;
	}


	public List<EleveBean> getListeelevesA() {
		return listeelevesA;
	}


	public void setListeelevesA(List<EleveBean> listeelevesA) {
		this.listeelevesA = listeelevesA;
	}


	public List<Integer> getElevestoprintA() {
		return elevestoprintA;
	}


	public void setElevestoprintA(List<Integer> elevestoprintA) {
		this.elevestoprintA = elevestoprintA;
	}


	public List<EleveBean> getListeelevesS() {
		return listeelevesS;
	}


	public void setListeelevesS(List<EleveBean> listeelevesS) {
		this.listeelevesS = listeelevesS;
	}


	public List<EleveBean> getListeelevesT() {
		return listeelevesT;
	}


	public void setListeelevesT(List<EleveBean> listeelevesT) {
		this.listeelevesT = listeelevesT;
	}


	public List<EleveBean> getListeelevesE() {
		return listeelevesE;
	}


	public void setListeelevesE(List<EleveBean> listeelevesE) {
		this.listeelevesE = listeelevesE;
	}


	public boolean isChekbox() {
		return chekbox;
	}


	public void setChekbox(boolean chekbox) {
		this.chekbox = chekbox;
	}

    public boolean isChekbox1() {
		return chekbox1;
	}


	public void setChekbox1(boolean chekbox1) {
		this.chekbox1 = chekbox1;
	}


	public void chekbox(){
		if (chekbox==true){
			chekbox1=true;
		}
    }
	public void loadElevesClasse(){
		if(codeclasse!=null && ! codeclasse.isEmpty())
			this.listeeleves=this.service.listerEleveEnregle(codeclasse);
		loadelevestoprint();
	}
	
	public void loadElevesClasseA(){
		if(codeclasse!=null && ! codeclasse.isEmpty())
			this.listeelevesA=this.service.listerEleveEnregleA(codeclasse);
	}
	
	//ceci permet d'afficher et d'imprimer la liste des états financier d'une classe
	public void loadElevesClasseE(){
		if(codeclasse!=null && ! codeclasse.isEmpty())
			this.listeelevesE=this.service.listerEleveEnregleE(codeclasse);
	}
	
	public void loadElevesClasseS(){
		if(codeclasse!=null && ! codeclasse.isEmpty())
			this.listeelevesS=this.service.listerEleveEnregleS(codeclasse,portee);
	}
	
	public void loadElevesClasseT(){
		if(codeclasse!=null && ! codeclasse.isEmpty())
			this.listeelevesT=this.service.listerEleveEnregleT(codeclasse,portee);
	}
	
	/**
	 * Charger uniquement les gars qui méritent les tableaux pour le trimestre choisie
	 */
	public void loadElevesClasseTab(){
		if(codeclasse!=null && ! codeclasse.isEmpty()){
			this.listeeleves=this.service.listerElevesTableauHonneurInscrits(codeclasse,portee);
			if(listeeleves==null || listeeleves.isEmpty())
				Repertoire.addMessageerreur("Aucun tableau d'honneur trouvé pour le trimestre");
			else
				loadelevestoprint();
		}
			
	}
	
	/**
	 * Initizlisztion des bulletins a imprimer
	 * Par defaut tous les bulletins sont a imprimer
	 */
	private void loadelevestoprint(){
		if(listeeleves!=null){
			elevestoprint=new ArrayList<Integer>();
			
			for(int i=0;i<listeeleves.size();i++){
				elevestoprint.add(listeeleves.get(i).getIdeleve());
				
			}
		}
	}
	
	
	public String imprimerBulletinsTrimestre(){

		try {
			this.service.imprimerBulletinTrimestre(codeclasse, elevestoprint, portee);
		} catch (NumberFormatException e) {
			Repertoire.addMessageerreurimpression(e);
		} catch (JRException e) {
			Repertoire.addMessageerreurimpression(e);
		} catch (IOException e) {
			Repertoire.addMessageerreurimpression(e);
		}
		
		return null;
		
	}
	
	public String imprimerBulletinsTrimestreC(){

		try {
			this.service.imprimerBulletinTrimestriel(codeclasse, elevestoprint, portee);
		} catch (NumberFormatException e) {
			Repertoire.addMessageerreurimpression(e);
		} catch (JRException e) {
			Repertoire.addMessageerreurimpression(e);
		} catch (IOException e) {
			Repertoire.addMessageerreurimpression(e);
		}
		
		return null;
		
	}
	
	public String imprimerBulletinsAnnuel(){

		try {
			this.service.imprimerBulletinAnnuel(codeclasse, elevestoprint);
		} catch (NumberFormatException e) {
			Repertoire.addMessageerreurimpression(e);
		} catch (JRException e) {
			Repertoire.addMessageerreurimpression(e);
		} catch (IOException e) {
			Repertoire.addMessageerreurimpression(e);
		}
		
		return null;
		
	}
	
	public String imprimerBilanAnnuel(){

		try {
			this.service.imprimerBilanAnnuel(codeclasse, elevestoprint);
		} catch (NumberFormatException e) {
			Repertoire.addMessageerreurimpression(e);
		} catch (JRException e) {
			Repertoire.addMessageerreurimpression(e);
		} catch (IOException e) {
			Repertoire.addMessageerreurimpression(e);
		}
		
		return null;
		
	}
	
	
	public String imprimerBilanSequentiel(){

		try {
			this.service.imprimerBilanAnnuel(codeclasse, elevestoprint);
		} catch (NumberFormatException e) {
			Repertoire.addMessageerreurimpression(e);
		} catch (JRException e) {
			Repertoire.addMessageerreurimpression(e);
		} catch (IOException e) {
			Repertoire.addMessageerreurimpression(e);
		}
		
		return null;
		
	}
	
	
	public String imprimerBilanTrimestriel(){

		try {
			this.service.imprimerBilanAnnuel(codeclasse, elevestoprint);
		} catch (NumberFormatException e) {
			Repertoire.addMessageerreurimpression(e);
		} catch (JRException e) {
			Repertoire.addMessageerreurimpression(e);
		} catch (IOException e) {
			Repertoire.addMessageerreurimpression(e);
		}
		
		return null;
		
	}
	
	
	
	public String imprimerBulletinsSequence(){
	
		try {
			this.service.imprimerBulletinSequence(codeclasse, elevestoprint,portee);
			
		} catch (NumberFormatException e) {
			Repertoire.addMessageerreurimpression(e);
		} catch (JRException e) {
			Repertoire.addMessageerreurimpression(e);
		} catch (IOException e) {
			Repertoire.addMessageerreurimpression(e);
		}
		return null;
	}




	public String imprimerTableauxTrimestre(){
			
		try {
			this.service.imprimerTableauHonneurTrimestre(codeclasse, elevestoprint,portee);
		} catch (SQLException e) {
			Repertoire.addMessageerreurimpression(e);
		} catch (JRException e) {
			Repertoire.addMessageerreurimpression(e);
		} catch (IOException e) {
			Repertoire.addMessageerreurimpression(e);
		}
				
		return null;
	}
	
	
	public String imprimerTableauxSequence(){
		
			
				try {
					this.service.imprimerTableauHonneurSequence(codeclasse, elevestoprint, portee);
				} catch (SQLException e) {
					Repertoire.addMessageerreurimpression(e);
				} catch (JRException e) {
					Repertoire.addMessageerreurimpression(e);
				} catch (IOException e) {
					Repertoire.addMessageerreurimpression(e);
				}
	
		
		return null;
	}
	
	
	public String telechargerBilan(){
		try {
			this.service.telechargerbilanreussite(codeclasse,portee);
		} catch (IOException e) {
			Repertoire.addMessageerreur("Erreur innatendue lors de la génération du formulaire de notes");
			Repertoire.logError("Erreur innatendue lors de la génération du formulaire de notes "+codeclasse, getClass(), e);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String telechargerTauxSequence(){
		try {
			this.service.telechargertauxsequence(codeclasse,portee);
		} catch (IOException e) {
			Repertoire.addMessageerreur("Erreur innatendue lors de la génération du formulaire de notes");
			Repertoire.logError("Erreur innatendue lors de la génération du formulaire de notes "+codeclasse, getClass(), e);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String telechargerBilanT(){
		try {
			this.service.telechargerbilanreussiteT(codeclasse,portee);
		} catch (IOException e) {
			Repertoire.addMessageerreur("Erreur innatendue lors de la génération du formulaire de notes");
			Repertoire.logError("Erreur innatendue lors de la génération du formulaire de notes "+codeclasse, getClass(), e);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String telechargerPVR(){
		try {
			this.service.imprimerRecapitulatif(codeclasse,portee);
		} catch (IOException e) {
			Repertoire.addMessageerreur("Erreur innatendue lors de la génération du formulaire de notes");
			Repertoire.logError("Erreur innatendue lors de la génération du formulaire de notes "+codeclasse, getClass(), e);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String telechargerPVRT(){
		try {
			this.service.imprimerRecapTrim(codeclasse,portee);
		} catch (IOException e) {
			Repertoire.addMessageerreur("Erreur innatendue lors de la génération du formulaire de notes");
			Repertoire.logError("Erreur innatendue lors de la génération du formulaire de notes "+codeclasse, getClass(), e);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String telechargerPVRM(){
		try {
			this.service.imprimerRecapitulatifM(codeclasse,portee);
		} catch (IOException e) {
			Repertoire.addMessageerreur("Erreur innatendue lors de la génération du formulaire de notes");
			Repertoire.logError("Erreur innatendue lors de la génération du formulaire de notes "+codeclasse, getClass(), e);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String telechargerPVRTM(){
		try {
			this.service.imprimerRecapTrimM(codeclasse,portee);
		} catch (IOException e) {
			Repertoire.addMessageerreur("Erreur innatendue lors de la génération du formulaire de notes");
			Repertoire.logError("Erreur innatendue lors de la génération du formulaire de notes "+codeclasse, getClass(), e);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
