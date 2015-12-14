package ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import ages.exception.ElementNOtFoundException;

import entities.Materiel;
import entities.SortieM;

@Local
public interface GestionMaterielLocal {

	public List<Materiel> listermateriel(String anneeAcEncours);

	public List<SortieM> listersortie(String anneeAcEncours);

	public String createMateriel(String designation, float prix, int quantite,
			String typemateriel, String etat, int i, String anneeEncours,
			String codeetablissement, Date date, String numeroserie) throws ElementNOtFoundException;

	public boolean modifierMateriel( String codemateriel,String designation, float prix, int quantite,
			String typemateriel, String etat, int i, String anneeEncours,
			String codeetablissement, Date date, int j, String numeroserie) throws ElementNOtFoundException;

	public boolean supprimerMateriel(int idmateriel) throws ElementNOtFoundException;

	public Materiel recherchermateriel(int codemateriel, String anneeAcEncours,
			String codeetablissement);

	public String createMaterielAjout(String designation, float prix, int quantite,
			String typemateriel, int quantiteF, String anneeEncours,
			String codeetablissement, Date dateenreg, String codemateriel, String numeroserie) throws ElementNOtFoundException;

	public List<Materiel> listermateriels(String anneeAcEncours);

	public String retraitMateriel(int idmateriel, String designation, float prix, int quantite,
			String typemateriel, int quantiteF, String anneeEncours,
			String codeetablissement, Date dateenreg, String codemateriel,
			String numeroserie, int quantiteF2, int quantite2, String raison, String utlisateur) throws ElementNOtFoundException;

	public SortieM recherchermaterielS(int idsortie, String anneeAcEncours,
			String codeetablissement);
}
