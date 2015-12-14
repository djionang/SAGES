package ejb;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Local;

import entities.Etablissement;
import entities.GpUserRole;
import entities.GroupeUser;
import entities.ItemRole;
import entities.Utilisateur;

import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;
import ages.exception.UnAuthorizedOperationException;


/**
 * Gestion des entités liées à la  gestion des utilisateurs
 * 
 */

@Local
public interface GestionUtilisateurLocal {

	public int enregistrerItemRole(String libelle, String description) throws DuplicateKeyException;

	public ItemRole rechercherItemRole(int iditem);

	public List<ItemRole> listerItemroles();

	public void modifierItemRole(int iditem, String libelle, String description) throws ElementNOtFoundException;

	public void supprimerItemRole(int iditem) throws ElementNOtFoundException;

	public Utilisateur rechercherUtilisateur(String loginuser);

	public Utilisateur rechercherUtilisateur(int iduser);

	public void modifierUtilisateur(String login, String password,
			String ancienmotdepasse, int iduser) throws UnAuthorizedOperationException, ElementNOtFoundException;

	public List<GroupeUser> listerGroupesUser();

	public void enregistrerUtilisateur(String codepersonnel, String login,
			String password, List<Integer> groupesusers) throws DuplicateKeyException, ElementNOtFoundException;

	public Set<String> rechercherRolesUtilisateur(String login);

	public int enregistrerGroupeUtilisateur(String libelle, String description,
			Map<Integer, List<Boolean>> roles) throws DuplicateKeyException, ElementNOtFoundException;

	public GroupeUser rechercherGroupeUser(int idgroupe);

	public List<GpUserRole> listerRoles(int idgroupe);

	public void modifierGroupeUtilisateur(int idgroupe, String libelle,
			String description, Map<Integer, List<Boolean>> roles) throws ElementNOtFoundException;

	public void supprimerGroupeUser(int idgroupe) throws ElementNOtFoundException;

	public List<Utilisateur> listerComptesUtilisateurs();

	public List<Integer> listerGroupesUserCodes();

	public void modifierUtilisateur(int idcompte, String codepersonnel,
			String login, String password, List<Integer> groupesusers) throws ElementNOtFoundException, DuplicateKeyException;

	public void supprimerUtilisateur(int idcompte) throws ElementNOtFoundException;

	public void enregistrerGestionnaire(String login, String password) throws DuplicateKeyException;

	public List<Utilisateur> listerManager();

	
	/**
	 * Recherche un gestionnaire d'etablissement
	 * @param login
	 * @param password
	 * @return
	 */
	public Etablissement rechercherGestionnaire(String login);

	/**
	 * Modification du login et mot de passe d'un administrateur de l'application
	 * @param login an
	 * @param password
	 * @param ancienmotdepasse
	 */
	public void modifierAdministrateur(String login, String password,
			String ancienmotdepasse);

	
	/**
	 * Modifier le gestionnaire d'un établissement
	 * @param codeetablissement de de l'etablissement
	 * @param login login du gestiooanire
	 * @param password nouveau mot de passe
	 * @param ancienmotdepasse ancien mot de passe
	 */
	public void modifierGestionnaire(String codeetablissement, String login,
			String password, String ancienmotdepasse2);
	
	/**
	 * Listing des groupes utilisateurs auquel appartient un utilisateur
	 * @param idutilisateur id de l'utilisateur
	 * @return
	 */
	public List<Integer> listerGroupesUserCodes(int idutilisateur);
	
	public List<String> listeTypesResponabilite();
	
	public void modifierGroupeUser( String libelle,
			float montant) throws UnAuthorizedOperationException, ElementNOtFoundException;
	
	

}
