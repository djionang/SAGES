package ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import entities.Depense;
import entities.Prevision;

import ages.exception.ElementNOtFoundException;


@Local
public interface GestionPayeLocal {

	public String createPrevision(String typeprevision, float montant, Date dateenreg,
			String anneeEncours, String codeetablissement, int idutilisateur, String description) throws ElementNOtFoundException;


	public String createPrevisionAjout(String codeprevision, int idprevision,
			float montant, Date dateenreg, String anneeEncours,
			String codeetablissement, int idutilisateur, String description) throws ElementNOtFoundException;


	public boolean modifierPrevision(String codeprevision, int idprevision,
			float montant, Date dateenreg, String anneeEncours,
			String codeetablissement, int idutilisateur, String description) throws ElementNOtFoundException;


	public Prevision rechercherprevision(int idprevision,
			String anneeAcEncours, String codeetablissement);


	public String createDepense(int idprevision, float montant, Date dateenreg,
			String typeprevision, String anneeEncours,
			String codeetablissement, int idutilisateur, String description) throws ElementNOtFoundException;


	public List<String> listeTypesPrevision();


	public List<Prevision> listerprevision(String anneeAcEncours,
			String codeetablissement);


	public List<Depense> listerdepense(String anneeAcEncours,
			String codeetablissement);


	public Depense rechercherdepense(int iddepense, String anneeAcEncours,
			String codeetablissement);


	public String createTransfert(String codeprevision, int idprevision,
			float montant, Date dateenreg, String anneeEncours,
			String codeetablissement, int idutilisateur, String description,
			String typeprevision2, int idprevision2) throws ElementNOtFoundException;

}
