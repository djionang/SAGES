package ages.beans.auth;
/**
 * Classe utiliser pour creer, editer, supprimer un compte utilisateur
 */

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import ages.beans.etablissement.personnel.PersonnelBean;
import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;
import ages.exception.UnAuthorizedOperationException;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;


@ManagedBean(name="compteBean")
@ViewScoped
public class CompteBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private int idcompte;
	private String login;
	private String password;
	private String nom;
	private String prenom;
	private boolean active;
	
	private String codepersonnel;
	
	private List<Integer> groupesusers;
	private List<String> groupes;
		
	private String ancienmotdepasse;
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the ancienmotdepasse
	 */
	public String getAncienmotdepasse() {
		return ancienmotdepasse;
	}

	/**
	 * @param ancienmotdepasse the ancienmotdepasse to set
	 */
	public void setAncienmotdepasse(String ancienmotdepasse) {
		this.ancienmotdepasse = ancienmotdepasse;
	}

	public List<String> getGroupes() {
		return groupes;
	}

	public void setGroupes(List<String> groupes) {
		this.groupes = groupes;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
	}
	
	public List<Integer> getGroupesusers() {
		return groupesusers;
	}

	public void setGroupesusers(List<Integer> groupesusers) {
		this.groupesusers = groupesusers;
	}

	/**
	 * @return the codepersonnel
	 */
	public String getCodepersonnel() {
		return codepersonnel;
	}

	/**
	 * @param codepersonnel the codepersonnel to set
	 */
	public void setCodepersonnel(String codepersonnel) {
		this.codepersonnel = codepersonnel;
	}

	public int getIdcompte() {
		return idcompte;
	}

	public void setIdcompte(int idcompte) {
		this.idcompte = idcompte;
	}

	
	/**
	 * Modification d'un utilisateur
	 * @param e
	 */
	public void updateUser(ActionEvent e){
		try {
			this.service.modifierUtilisateur(login,password,ancienmotdepasse);
			Repertoire.addMessageinfo("Modification effectuée!");
		} catch (Exception e1) {
			if(e1.getCause()!=null){
				if(e1.getCause().getClass().equals(UnAuthorizedOperationException.class)){
					Repertoire.addMessageerreur("Opération non autorisée");
				}
				else{
					if(e1.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Utilisateur non trouvé");
					}
					else{
						Repertoire.addMessageerreurInnatendue(e1);
					}
				}					
			}
			else{
				Repertoire.addMessageerreurInnatendue(e1);
			}
		}
	}
	
	
	/**
	 * modification de mon compte
	 * @param e
	 */
	public void modifierMoncompte(ActionEvent e){
		try {
			this.service.modifierCompte(login,password,ancienmotdepasse);
			Repertoire.addMessageinfo("Modification effectuée!");
		} catch (Exception e1) {
			if(e1.getCause()!=null){
				if(e1.getCause().getClass().equals(UnAuthorizedOperationException.class)){
					Repertoire.addMessageerreur("Opération non autorisée");
				}
				else{
					if(e1.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Utilisateur non trouvé");
					}
					else{
						Repertoire.addMessageerreurInnatendue(e1);
					}
				}					
			}
			else{
				Repertoire.addMessageerreurInnatendue(e1);
			}
		}
	}
	
	/**
	 * Modification d'un compte administrateur
	 * @param e action declanchée par la validation du formulaire
	 */
	public void updateAdmin(ActionEvent e){
		try {
			this.service.modifierAdministrateur(login,password,ancienmotdepasse);
			Repertoire.addMessageinfo("Modification effectuée!");
		} catch (Exception e1) {
			if(e1.getCause()!=null){
				if(e1.getCause().getClass().equals(UnAuthorizedOperationException.class)){
					Repertoire.addMessageerreur("Opération non autorisée");
				}
				else{
					if(e1.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Administreur non trouvé");
					}
					else{
						Repertoire.addMessageerreurInnatendue(e1);
					}
				}					
			}
			else{
				Repertoire.addMessageerreurInnatendue(e1);
			}
		}
	}
	
	
	/**
	 * Initialise les informations sur un utilisateur
	 */
	public void initdataUser(){
		try {
			this.service.initData_Current_User(this);
		} catch (ElementNOtFoundException e) {
			Repertoire.addMessageerreur("Session utilisateur Non definie, veuiller vous reconnecter");
		}
	}
	
	
	/**
	 * Enregistrer un utilisateur personnel
	 * @return chaine de navigation suivante
	 */
	public String saveUser(){
		
		try {
			this.service.enregistrerUtilisateur(codepersonnel,login,password,groupesusers);
			Repertoire.addMessageinfo("Enregistrement effectuée!");
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(DuplicateKeyException.class)){
					Repertoire.addMessageerreur("Utilisateur de Login "+login+" Déja enregistré");
				}
				else{
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Un des groupes utilisateurs n'a pas pu être chargé, enregistrement non effectué");
					}
					else{
						Repertoire.addMessageerreurInnatendue(e);
					}
				}
			}
			else{
				Repertoire.addMessageerreurInnatendue(e);
			}
			return null;
		}
		Repertoire.addMessageinfoEnregistrementOK("Utilisateur");
		return "listingutilisateur";
	}
	
	
	public List<PersonnelBean>  chargerPersonnel(String query){
		List<PersonnelBean> liste=this.service.listerPersonnelLike(query);
		return liste;
	}
	
	public void initUserLoaded(){
		if(this.idcompte!=0)
			this.service.initCompte(this);
	}
	
	public String updateUser(){
		
		try {
			this.service.modifierUtilisateur(idcompte,codepersonnel,login, password, groupesusers);
			Repertoire.addMessageinfo("Modification effectuée!");
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(DuplicateKeyException.class)){
					Repertoire.addMessageerreur("Utilisateur de Login "+login+" Déja enregistré");
				}
				else{
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Un des groupes utilisateurs n'a pas pu être chargé, enregistrement non effectué");
					}
					else{
						Repertoire.addMessageerreurInnatendue(e);
					}
				}
			}
			else{
				Repertoire.addMessageerreurInnatendue(e);
			}
			return null;
		}
		Repertoire.addMessageinfoModificationOK("Utilisateur");
		return OperationResults.navWithParam("visualisation", "user", String.valueOf(idcompte));
	}
	
	public String desactivateUser(){
		try{
			this.service.desactiverUtilisateur(idcompte);
		}
		catch (Exception e) {
			if(e.getCause()!=null){
				
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Utilisateur n'a pas pu être chargé, Mise à jour non effectuée");
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
		Repertoire.addMessageinfo("Utilisateur Désactivé");
		
		return "listingutilisateur";
	}
	
	public String saveUserManager(){
		try {
			this.service.enregistrerManager(login,password);
		} catch (Exception e) {
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(DuplicateKeyException.class)){
					Repertoire.addMessageerreur("Utilisateur de Login "+login+" Déja enregistré");
				}
				else{
					if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
						Repertoire.addMessageerreur("Un des groupes utilisateurs n'a pas pu être chargé, enregistrement non effectué");
					}
					else{
						Repertoire.addMessageerreurInnatendue(e);
					}
				}
			}
			else{
				Repertoire.addMessageerreurInnatendue(e);
			}
			return null;
		}
		Repertoire.addMessageinfoEnregistrementOK("Utilisateur");
		return "listingutilisateur";
	}
}

