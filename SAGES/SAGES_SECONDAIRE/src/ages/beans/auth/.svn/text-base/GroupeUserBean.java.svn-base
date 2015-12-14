package ages.beans.auth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import entities.ItemRole;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;

import ages.beans.GenericBean;
import ages.exception.ElementNOtFoundException;


/**
 * GroupeUserBean
 * @author Brilswear et Djionang
 *
 */


@ManagedBean(name="groupeUserBean")
@ViewScoped
public class GroupeUserBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private int idgroupe;
	private String libelle;
	private String description;
	private float montant;
	
	private List<Habilitations> habilitations;
	
	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
	}

	public int getIdgroupe() {
		return idgroupe;
	}

	public void setIdgroupe(int idgroupe) {
		this.idgroupe = idgroupe;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Habilitations> getHabilitations() {
		return habilitations;
	}

	public void setHabilitations(List<Habilitations> habilitations) {
		this.habilitations = habilitations;
	}
	
	
	/**
	 * Initialisation pour la création d'un nouvean groupe
	 */
	public void initEmpty(){
		habilitations=new ArrayList<Habilitations>();
		List<ItemRoleBean> items=this.service.listerItemrole();
		for(int i=0;i<items.size();i++){
			if((items.get(i).getLibelle().compareTo("ROLE_APPMANAGER")!=0)&&(items.get(i).getLibelle().compareTo("ROLE_ALLACCESS")!=0)){
				habilitations.add(new Habilitations());
				habilitations.get(i).setItem(new ItemRole());
				habilitations.get(i).getItem().setLibelle(items.get(i).getLibelle());
				habilitations.get(i).getItem().setDescription(items.get(i).getDescription());
				habilitations.get(i).getItem().setIditem(items.get(i).getIditem());
			}
			
		}
	}
	
	public void initGroupeUser(){
		if(idgroupe!=0)
			this.service.initGroupeUser(this);
	}
	
	public String savegroupeUser(){
		Map<Integer,List<Boolean>> roles=new HashMap<Integer, List<Boolean>>();
		
		for(int i=0;i<habilitations.size();i++){
			List<Boolean> values=new ArrayList<Boolean>();
			Integer iditem=habilitations.get(i).getItem().getIditem();
			values.add(habilitations.get(i).isCreate());			
			values.add(habilitations.get(i).isUpdate());			
			values.add(habilitations.get(i).isDelete());
			values.add(habilitations.get(i).isView());
			roles.put(iditem, values);
		}
		
		try{
			idgroupe=this.service.enregistrerGroupeUtilisateur(libelle,description,roles);
		}
		catch(Exception e){
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Un des Niveaux de role n'a pas été trouvé");
				}
				else
					Repertoire.addMessageerreurInnatendue(e);
			}
			else{
				Repertoire.addMessageerreurInnatendue(e);
			}
			return null;
		}
		return OperationResults.navWithParam("visualisation", "idgroupe", String.valueOf(idgroupe)) ;
	}
	
	public String updategroupeUser(){
		Map<Integer,List<Boolean>> roles=new HashMap<Integer, List<Boolean>>();
		
		for(int i=0;i<habilitations.size();i++){
			List<Boolean> values=new ArrayList<Boolean>();
			Integer iditem=habilitations.get(i).getItem().getIditem();
			values.add(habilitations.get(i).isCreate());			
			values.add(habilitations.get(i).isUpdate());
			values.add(habilitations.get(i).isDelete());
			values.add(habilitations.get(i).isView());
			roles.put(iditem, values);
		}
		
		try{
			this.service.modifierGroupeUtilisateur(idgroupe,libelle,description,roles);
		}
		catch(Exception e){
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Le groupe n'a pas été retrouvé");
				}
				else
					Repertoire.addMessageerreurInnatendue(e);
			}
			else{
				Repertoire.addMessageerreurInnatendue(e);
			}
			return null;
		}
		return OperationResults.navWithParam("visualisation", "idgroupe", String.valueOf(idgroupe)) ;
	}

	
	public String supprimerGroupeUser(){
				
		try{
			this.service.supprimerGroupeUtilisateur(idgroupe);
		}
		catch(Exception e){
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Le groupe n'a pas été retrouvé");
				}
				else
					Repertoire.addMessageerreurInnatendue(e);
			}
			else{
				Repertoire.addMessageerreurInnatendue(e);
			}
			return null;
		}
		Repertoire.addMessageinfoSuppressionOK("Groupe Utilisateur");
		return OperationResults.navWithParam("listing", null,null) ;
	}
	
	public String assigner(){
		
		try{
			this.service.modifierGroupeUtilisateur(this.libelle,this.montant);
		}
		catch(Exception e){
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Le groupe n'a pas été retrouvé");
				}
				else
					Repertoire.addMessageerreurInnatendue(e);
			}
			else{
				Repertoire.addMessageerreurInnatendue(e);
			}
			return null;
		}
		Repertoire.addMessageinfoEnregistrementOK(libelle);
		return OperationResults.navWithParam("listingrespo", null,null) ;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}
	
	
}
