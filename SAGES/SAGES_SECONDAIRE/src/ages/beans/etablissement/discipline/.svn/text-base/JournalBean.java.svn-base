package ages.beans.etablissement.discipline;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import utils.Service;
import utils.Repertoire;

import ages.beans.GenericBean;
import ages.beans.anneeacademique.SequenceBean;
import ages.beans.anneeacademique.TrimestreBean;
import ages.beans.eleve.EleveBean;



@ManagedBean(name="journalBean")
@ViewScoped
public class JournalBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	

	private String matriculeeleve;
	
	private String nomeleve;
	
	private String codeclasse;
	private List<EleveBean> eleves;

	private List<DisciplineItem> items;
	
	private String vue;
	private int valeurvue;
	private SelectItem[] vues;
	
	private String anneeacademique;

	public void setService(Service service) {
		this.service = service;
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


	public List<DisciplineItem> getItems() {
		return items;
	}


	public void setItems(List<DisciplineItem> items) {
		this.items = items;
	}


	/**
	 * @return the vue
	 */
	public String getVue() {
		return vue;
	}


	/**
	 * @param vue the vue to set
	 */
	public void setVue(String vue) {
		this.vue = vue;
	}


	/**
	 * @return the valeurvue
	 */
	public int getValeurvue() {
		return valeurvue;
	}


	/**
	 * @param valeurvue the valeurvue to set
	 */
	public void setValeurvue(int valeurvue) {
		this.valeurvue = valeurvue;
	}


	public SelectItem[] getVues() {
		return vues;
	}


	public void setVues(SelectItem[] vues) {
		this.vues = vues;
	}


	public String getNomeleve() {
		if(matriculeeleve!=null){
			
			//recherche dans la liste des élèves celui dont le matricule "matriculeeleve"
			for(int i=0;i<eleves.size();i++){
				if(eleves.get(i).getMatricule().compareTo(matriculeeleve)==0){
					//si eleve trouvé, charger son nom, et son prenom si non vide
					nomeleve=eleves.get(i).getNom();
					if(eleves.get(i).getPrenom()!=null)
						nomeleve+=eleves.get(i).getPrenom();
					break;
				}
			}
			
		}
		return nomeleve;
	}


	public void setNomeleve(String nomeleve) {
		this.nomeleve = nomeleve;
	}


	public String getAnneeacademique() {
		anneeacademique=this.service.getAnneeEncours();
		return anneeacademique;
	}


	public void setAnneeacademique(String anneeacademique) {
		this.anneeacademique = anneeacademique;
	}


	public void loadEleves(){
		if(codeclasse!=null && !codeclasse.isEmpty()){
			eleves=this.service.listerElevesinscrits(codeclasse);
			if(eleves.isEmpty())
				Repertoire.addMessageerreur("Aucun élève trouvé");
		}
		else
			eleves=null;
		items=null;
	}
	
	
	public void loadItems(ActionEvent e){
		if(matriculeeleve!=null&&!matriculeeleve.isEmpty()){
			if(vue.compareToIgnoreCase("annuel")==0)
				items=this.service.rechercherJournalDisciplinaire(matriculeeleve);
			else
				items=this.service.rechercherJournalDisciplinaire(matriculeeleve,vue,valeurvue);
		}
		else
			items=null;
	}
	
	
	public void loadVues(){
		if(vue==null||vue.isEmpty()){
			Repertoire.addMessageerreur("Aucune vue sélectionnée");
			return;
		}
		
		if(vue.compareToIgnoreCase("annuel")==0)
			return;
		
		if(vue.compareToIgnoreCase("sequentiel")==0){
			initSequenceItems();
		}
			
		if(vue.compareToIgnoreCase("trimestriel")==0){
			initTrimestreItems();
		}	
		
	}
	
	private void initSequenceItems() {
		List<SequenceBean> listesq=this.service.listerSequences();
		if((listesq==null)||(listesq.isEmpty())){ 
			vues=new SelectItem[0];
			return;
		}
		
		vues=new SelectItem[listesq.size()];
		for(int i=0;i<listesq.size();i++){
			vues[i]=new SelectItem(listesq.get(i).getNumero(), "Séquence "+listesq.get(i).getNumero());
		}
		
	}
	
	private void initTrimestreItems() {
		List<TrimestreBean> listetr=this.service.listetrimestres();
		if((listetr==null)||(listetr.isEmpty())){ 
			vues=new SelectItem[0];
			return;
		}
		
		vues=new SelectItem[listetr.size()];
		for(int i=0;i<listetr.size();i++){
			vues[i]=new SelectItem(listetr.get(i).getNumero(), "Trimestre "+listetr.get(i).getNumero());
		}
		
	}
		
}
