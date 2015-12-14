package utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cette classe est un repertoire de fonctions couramment utilisées en dans le compte du projet AGES
 * @author Brilland Nkuete
 *
 */
public class Repertoire {
	
	private static Logger log;
	private static SimpleDateFormat agesDateFormat=new SimpleDateFormat("dd/MM/yyyy");
	private static String logPrefix="AGES-LOG ";
	
	private static char[] caracteres={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	/**
	 * Renvoie l'année en cours sur deux ou quatres caracteres
	 * @param size nombre (2 ou 4) de caracteres sur lesquels va tenir la date
	 * @return l'année en cours sur 2 caracteres si le parametre est 2, sinon sur 4 caracteres
	 */
	private static String getFullYear(int size){
		 SimpleDateFormat shortyear=new SimpleDateFormat("yyyy");
		switch(size){
		case 2: return shortyear.format(new Date()).substring(2);
		case 4: 
		default:return shortyear.format(new Date());
		}
	}
	
	
	/**
	 * Vérifie q'une chaine correspond a un entier
	 * @param value valeur a controler
	 * @return true si la chaine est convertissable en entier, false sinon
	 */
	public boolean isInteger(String value){
		try{
			Integer.parseInt(value);
		}
		catch(Exception e){
			log.log(Level.WARNING, "Conversion en entier impossible "+value, e);
			return false;
		}
		return true;
	}

	public static SimpleDateFormat getAgesDateFormat() {
		return agesDateFormat;
	}

	public static void setAgesFormat(SimpleDateFormat agesFormat) {
		Repertoire.agesDateFormat = agesFormat;
	}
	
	
	
	/**
	 * Log un message de niveau info
	 * @param message message a logger
	 * @param classe classe ayant émis le log
	 */
	public static void logInfo(String message, Class<?> classe){
		log=Logger.getLogger(logPrefix);
		log.info(logPrefix+message);
	}
	
	/**
	 * Log un message de niveau DEBUG
	 * @param message message a logger
	 * @param classe classe ayant émis le log
	 */
	public static void logDebug(String message, Class<?> classe){
		log=Logger.getLogger(logPrefix);
		log.log(Level.CONFIG, logPrefix+message);	
	}
	
	/**
	 * Log un message de niveau Warn
	 * @param message message a logger
	 * @param classe classe ayant émis le log
	 */
	public static void logWarn(String message, Class<?> classe){
		log=Logger.getLogger(logPrefix);
		log.log(Level.WARNING, logPrefix+message);
	}
	
	/**
	 * Log un message de niveau Fatal
	 * @param message message a logger
	 * @param classe classe ayant émis le log
	 */
	public static void logFatal(String message, Class<?> classe, Throwable e){
		log=Logger.getLogger(logPrefix);
		log.log(Level.SEVERE, logPrefix+message, e);
	}


	public static String genererAnneeAcademique(Date debut,Date fin){
		SimpleDateFormat shortyear=new SimpleDateFormat("yyyy");
		 return shortyear.format(debut)+"-"+shortyear.format(fin);
	}
	
	public static String genererCodeEnseignant(int code){
		return getFullYear(2)+"E"+Digit3Mins(String.valueOf(code));
		 
	}
	
	public static String genererCodePersonnel(int code){
		return getFullYear(2)+"P"+Digit3Mins(String.valueOf(code));
		 
	}
	
	public static String genererCodeDossier(int code){
		return getFullYear(2)+"D"+Digit3Mins(String.valueOf(code));
		 
	}
	
	public static String genererCodeMateriel(int code){
		return getFullYear(2)+"M"+Digit3Mins(String.valueOf(code));
		 
	}
	
	public static String genererCodePrevision(int code){
		return getFullYear(2)+"PR"+Digit3Mins(String.valueOf(code));
		 
	}
	
	public static String genererCodeDepense(int code){
		return getFullYear(2)+"DE"+Digit3Mins(String.valueOf(code));
		 
	}
	
	public static String genererCodeEtablissement(int code){
		return getFullYear(2)+"ETAB"+Digit3Mins(String.valueOf(code));
		 
	}
	
	public static String genererCodeNiveau(int code){
		return getFullYear(2)+"N"+Digit3Mins(String.valueOf(code));
		 
	}
	
	public static String genererCodeCycle(int code){
		return getFullYear(2)+"C"+Digit3Mins(String.valueOf(code));
		 
	}
	
	public static String genererCodeSection(int code){
		return getFullYear(2)+"S"+Digit3Mins(String.valueOf(code));
		 
	}
	
	public static String genererCodeEleve(int codeniveau, int codeeleve){
		return getFullYear(2)+"EL"+Digit3Mins(String.valueOf(codeeleve));
		 
	}
	
	
	/**
	 * construit une chaine de longueur au moins trois a partir d'une chaine quelconque
	 * @param valeur
	 * @return
	 */
	private static String Digit3Mins(String valeur){
		if(valeur==null) 
			return "000";
		
		while(valeur.length()<3){
			valeur="0"+valeur;
		}
		return valeur;
	}


	public static String genererCodeVersement(int code) {
		return getFullYear(2)+"SEC"+Digit3Mins(String.valueOf(code));
	}
	
	
}
