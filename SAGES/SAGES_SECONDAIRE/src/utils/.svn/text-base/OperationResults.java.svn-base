package utils;


/**
 * Gestionnaire des valeurs de retours des opérations
 * @author Brilswear
 *
 */
public class OperationResults {
	//echec avec erreur
	public static final String FAILURE="_error";
	
	//success
	public static final String SUCCESS="_success";
	
	//reessayer
	public static final String RETRY="";
	
	public static final String NavListingBasic="listing";
	
	public static final String NavListingEtablissement="listingEtablissements";
	
	public static final String NavListingOption="listingOptions";
	
	public static final String NavListingEnseignement="listingEnseignements";
	
	public static final String NavListingNiveau="listingNiveaux";
	
	public static final String NavListingCycle="listingCycles";
	
	public static final String NavListingSection="listingSections";
	
	public static final String NavListingAnnees="anneelisting";
	
	public static final String NavListingAtelier="ressouratelierlisting";

	public static final String NavListingTrimestres = "anneetrim";

	public static final String NavListingSequences = "anneeseq";

	public static final String NavAnneeEncours = "anneean";
	
	public static String EnregistrementOK="Enregistrement effectué";
	
	public static String EnregistrementNO="Enregistrement non effectué";
	
	public static String UpdateNO="MIse à jour non effectué";
	
	public static String UpdateOK="Mise à jour effectuée";
	
	public static String SuppressionOK="Suppression effectuée";
	
	public static String SuppressionNO="Suppression non effectuée";
	
	public static String navWithParam(String nextPage, String nomparam, String valeurParam){
		if((nomparam==null)||(nomparam.isEmpty()))
			return nextPage;
		return nextPage+"?faces-redirect=true&includeViewParams=true&"+nomparam+"="+valeurParam;
	}
}
