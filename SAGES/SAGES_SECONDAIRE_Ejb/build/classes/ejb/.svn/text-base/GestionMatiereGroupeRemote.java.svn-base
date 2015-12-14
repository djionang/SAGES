package ejb;

import javax.ejb.Remote;

import ages.exception.DuplicateKeyException;
import ages.exception.ElementNOtFoundException;




@Remote
public interface GestionMatiereGroupeRemote {
	
	


	public void enregistrerMatiere(String codematiere,String libelle,String codeenseignant)throws DuplicateKeyException;
	 
	public void modifierMatiere(String codematiere,String libelle,String codeenseignant) throws ElementNOtFoundException;
	
	public void supprimerMatiere(String codematiere) throws ElementNOtFoundException;
	
	public void enregistrerGroupeMatiere(String codegm,String libellegm,String description) throws DuplicateKeyException;
	 
	public void modifierGroupeMatiere(String codegm,String libellegm,String description) throws ElementNOtFoundException;
	
	public void supprimerGroupeMatiere(String codegm) throws ElementNOtFoundException;
	
	public void enregistrerOptionMatiere(String codeom,String libelle,String codematiere)throws ElementNOtFoundException,DuplicateKeyException;
	 
	public void modifierOptionMatiere(String codeom,String libelle,String codematiere)throws ElementNOtFoundException;
	
	public void supprimerOptionMatiere(String codeom)throws ElementNOtFoundException;
	
	
	public void assignerGroupeMatiere(String code,String codematiere)throws ElementNOtFoundException;

}
