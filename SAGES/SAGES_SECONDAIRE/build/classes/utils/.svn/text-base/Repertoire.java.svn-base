package utils;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import ages.beans.application.ConfigurationBean;



/**
 * Cette classe est un repertoire de fonctions couramment utilisées en dans le compte du projet AGES
 * @author Brilland Nkuete
 *
 */
public class Repertoire {
	
	private static Logger log;
	private static SimpleDateFormat agesDateFormat=new SimpleDateFormat("dd/MM/yyyy");
	private static String logPrefix="AGES-LOG ";
	
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
			log.error("pas un entier" , e);
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
	
	public static void addMessageinfo(String summary) {  
        FacesMessage message = new FacesMessage( summary);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	public static void addMessageinfoEnregistrementOK(String classe) {  
        FacesMessage message = new FacesMessage( "Enregistrement "+classe+" Effectué");  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	public static void addMessageinfoModificationOK(String classe) {  
        FacesMessage message = new FacesMessage( "Mise à jour "+classe+" sauvegardée");  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	public static void addMessageinfoClotureOK(String annee) {  
        FacesMessage message = new FacesMessage( "Cloture de l'annee "+annee+" sauvegardée");  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	public static void addMessageinfoSuppressionOK(String classe) {  
        FacesMessage message = new FacesMessage( "Suppression "+classe+" effectuée");  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	/**
	 * @param summary
	 */
	public static void addMessageerreur(String summary) { 
		
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	public static void addMessageerreur(String summary,Exception e) { 
		logError(summary, Repertoire.class, e);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	public static void addMessageerreurimpression(Exception e) { 
		logError("Erreur survenue lors de l'impression", Repertoire.class, e);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur survenue lors de l'impression, impossible de continuer!!!",  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	public static void addMessageerreurInnatendue() {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur innatendue, Veuillez contacter l'administrateur",  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	public static void addMessageerreurInnatendue(Exception e) {  
		logError("Unknown", Repertoire.class, e);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur innatendue, Veuillez contacter l'administrateur",  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	public static void addMessageerreurAnneeECNotDefined() {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aucune année scolaire en exercice",  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	public static void addMessageerreurParametreIncorrect(String classeObjet, String codeObjet) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, " Paramètre incorrect, "+classeObjet+" "+codeObjet+" introuvable",  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	public static void addMessageerreurParametreRequis(String classeObjet) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, " Paramètre  "+classeObjet+" Requis",  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	public static void addMessageerreurElementNonTrouve(String classeObjet) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, " Element  "+classeObjet+" Non trouvé",  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	public static void addMessageerreurErreurInnatendue() {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, " Erreur innantendue, Veuillez contacter l'administrateur",null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	/**
	 * Log un message de niveau info
	 * @param message message a logger
	 * @param classe classe ayant émis le log
	 */
	public static void logInfo(String message, Class<?> classe){
		log=Logger.getLogger(classe);
		log.info(logPrefix+message);
	}
	
	/**
	 * Log un message de niveau DEBUG
	 * @param message message a logger
	 * @param classe classe ayant émis le log
	 */
	public static void logDebug(String message, Class<?> classe){
		log=Logger.getLogger(classe);
		log.debug(logPrefix+message);	
	}
	
	/**
	 * Log un message de niveau Warn
	 * @param message message a logger
	 * @param classe classe ayant émis le log
	 */
	public static void logWarn(String message, Class<?> classe){
		log=Logger.getLogger(classe);
		log.warn(logPrefix+message);
	}
	
	/**
	 * Log un message de niveau Fatal
	 * @param message message a logger
	 * @param classe classe ayant émis le log
	 */
	public static void logFatal(String message, Class<?> classe, Throwable e){
		log=Logger.getLogger(classe);
		log.fatal(logPrefix+message, e);
	}
	
	/**
	 * Log un message de niveau Erreur
	 * @param message message a logger
	 * @param classe classe ayant émis le log
	 */
	public static void logError(String message, Class<?> classe, Throwable e){
		log=Logger.getLogger(classe);
		log.error(logPrefix+message, e);
	}

	
	public static String extraireAnneeOnly(Date date){
		 SimpleDateFormat shortyear=new SimpleDateFormat("yyyy");
		 return shortyear.format(date);
	}
	
	/**
	 * Cree les repertoires de sauvegarde des images pour un etablissement
	 * @param codeEtablissement
	 * @return true si l'operation se deroule bien, false sinon
	 */
	public static boolean creerRepertoiresImages(String codeEtablissement){
		try{
			File rep_etab=new File(getrealpath(ConfigurationBean.imgpath).substring(0,getrealpath(ConfigurationBean.imgpath).length()-76)+codeEtablissement);
			System.out.println("le chemin de dossier est"+   rep_etab.getAbsolutePath());
			
			if(!rep_etab.exists()||!rep_etab.isDirectory()){
				rep_etab.mkdir();
			}
			
			File rep_eleve=new File(getrealpath(ConfigurationBean.imgpath).substring(0,getrealpath(ConfigurationBean.imgpath).length()-76)+codeEtablissement+"/eleves");
			if(!rep_eleve.exists()||!rep_eleve.isDirectory()){
				rep_eleve.mkdir();
			}
			
			File rep_enseignant=new File(getrealpath(ConfigurationBean.imgpath).substring(0,getrealpath(ConfigurationBean.imgpath).length()-76)+codeEtablissement+"/enseignants");
			if(!rep_enseignant.exists()||!rep_enseignant.isDirectory()){
				rep_enseignant.mkdir();
			}
		}
		catch(Exception e){
			logError("Erreur lors de la création des repertoires de sauvegarde des images", null, e);
			return false;
		}
		return true;
		
	}
	
	public static void savelogoEtab(String codeEtab,byte[] img){
		int width=ConfigurationBean.largeur_logoEtab;
		int height=ConfigurationBean.hauteur_logoEtab;
		// a modifier
		creerRepertoiresImages(codeEtab);
		saveImageAsJPG(codeEtab,"logo", img, width, height);
	}
	
	public static void savephotoEleve(String codeEleve,String codeEtab,byte[] img){
		int width=ConfigurationBean.largeur_photo;
		int height=ConfigurationBean.hauteur_photo;
		// a modifier
		creerRepertoiresImages(codeEtab);
		saveImageAsJPG(ConfigurationBean.imgpath+File.separator+codeEtab+File.separator+"eleves",codeEleve, img, width, height);
	}
	
	public static void savephotoEnseignant(String codeEnseignant,String codeEtab,byte[] img){
		int width=ConfigurationBean.largeur_logoEtab;
		int height=ConfigurationBean.hauteur_logoEtab;
		// a modifier
		creerRepertoiresImages(codeEtab);
		saveImageAsJPG(ConfigurationBean.imgpath+File.separator+codeEtab+File.separator+"enseignants",codeEnseignant, img, width, height);
	}



	private static void saveImageAsJPG(String repertoire,String nomfichier, byte[] contenuFichier, int width, int height){
			
		try{
			
			//Lecture de l'image contenu dans le tableau de bytes
			InputStream in = new ByteArrayInputStream(contenuFichier);
			BufferedImage bim= ImageIO.read(in);
			
			//Transformation de l'image pour lui donner la taille qui nous interesse
			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); 
		    Graphics2D g = bi.createGraphics(); 
		    g.drawImage(bim, 0, 0, width, height, null); 
		    
		    //Sauvegarde de l'image sur disque
		    
		    ImageIO.write(bi, "JPG", new File(getrealpath(ConfigurationBean.imgpath).substring(0,getrealpath(ConfigurationBean.imgpath).length()-76)+repertoire+"/"+nomfichier+".jpg")); 
		    System.out.println("CHEMIN==============="+new File(getrealpath(ConfigurationBean.imgpath).substring(0,getrealpath(ConfigurationBean.imgpath).length()-76)+repertoire).getAbsolutePath());
		     	   
	   } catch (Exception e) { 
	      e.printStackTrace(); 
	      
	    }//end try 
	}
	
	public static int convertDuree(String periode, int nbre){
		if(periode.compareTo("jours")==0)
			return nbre*24;
		else
			if(periode.compareTo("heures")==0)
				return nbre;
			else
				if(periode.compareTo("semaines")==0)
					return nbre*24*7;
				else					
					if(periode.compareTo("mois")==0)
						return nbre*30*24;
					else
						return nbre;
	}
	
	public static String getrealpath(String path){
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);
	}

	/**
	 * Supprime les repertoires de sauvegarde d'images pour un établissement
	 * @param codeetablissement code de l'etablissement supprimé
	 */
	public static void suprimerRepertoiresImages(String codeetablissement) {
		try{
			File rep_etab=new File(getrealpath(ConfigurationBean.imgpath)+"/"+codeetablissement);
			if(rep_etab.exists()&&rep_etab.isDirectory()){
				deleteDirectory(rep_etab);
			}
			
		}
		catch(Exception e){
			logError("Erreur lors de la création des repertoires de sauvegarde des images", null, e);
			
		}
	}
	
	
	/**
	 * Supprimer un repertoire non vide en java
	 * @param path chemin du repertoire a supprimer
	 * @return true si la suppression se passe bien, false sinon
	 */
	static public boolean deleteDirectory(File path) { 
        boolean resultat = true; 
        
        if( path.exists() ) { 
                File[] files = path.listFiles(); 
                for(int i=0; i<files.length; i++) { 
                        if(files[i].isDirectory()) { 
                                resultat &= deleteDirectory(files[i]); 
                        } 
                        else { 
                        resultat &= files[i].delete(); 
                        } 
                } 
        } 
        resultat &= path.delete(); 
        return( resultat ); 
} 
	
}
