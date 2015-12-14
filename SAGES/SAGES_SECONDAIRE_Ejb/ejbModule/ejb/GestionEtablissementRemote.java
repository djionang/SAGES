package ejb;

import java.util.List;

import javax.ejb.Remote;

import entities.Classe;
import entities.Niveau;
import entities.OptionSerie;
import entities.Salle;

@Remote
public interface GestionEtablissementRemote {
	/**
	    * cette methode nous permet de creer une classe
	    * @param anneeacademique
	    * @param codeclasse
	    * @param codeenseignant
	    * @param codeoption
	    * @param libelle
	    * @param contenancemax
	    * @param delegue1
	    * @param delegue2
	    * @return
	    */
		public String creerClasse(String anneeacademique, String codeclasse, String codeenseignant,
				String codeoption, String libelle,Integer contenancemax,String delegue1,String delegue2);
		
		/**
		 * 
		 * cette partie concerne la classe Classe
		 * 
		 */ 
		
		
		
		
		
	  
		/**
		 * cette methode nous permet de modigfier une classe
		 * @param anneeacademique
		 * @param codeclasse
		 * @param codeenseignant
		 * @param codeoption
		 * @param libelle
		 * @param contenancemax
		 * @param delegue1
		 * @param delegue2
		 * @return
		 */
		public boolean modifierClasse(String anneeacademique, String codeclasse, String codeenseignant,
				String codeoption, String libelle,Integer contenancemax,String delegue1,String delegue2);
		
		
		
		/**
		 * cette methode nous permet de supprimer une classe
		 * @param codeclasse
		 * @return
		 */
		public boolean supprimerClasse(String codeclasse);
		
		/**
		 * Retourne l'année académique en cours, telle que enregistrée dans la base
		 * @return
		 */
		public String getAnneeEnCours();
		

		   /**
		    * Lister les options disponibles su sein de l'etablissement
		    * @return
		    */
		   public List<OptionSerie> listerOptions();
		   
		   
		   /**
		    * Lister les differents niveaux disponibles au sein de l'etablissement
		    * @return
		    */
		   public List<Niveau> listerNiveaux();
		   
		   /**
			 * cette methode nous permet de lister toutes les clase d'un etablisement
			 * @return
			 */
			public List<Classe> listerClasse();
			
			/**
			 * Liste les types de salle de classe déja enregistrés
			 * @return
			 */
			public List<String> listerTypesSalles();
			
			/**
			 * Liste les salles de classe déja enregistrés
			 * @return
			 */
			public List<Salle> listerSalles();
			
			 
		   /**
		    * cette methode nous retourne la liste des classe non pleine
		    * @return
		    */
		   public List<Classe> Listeclassenonpleine();
		   
		   
		   public List<String> infosEtablissement();
}
