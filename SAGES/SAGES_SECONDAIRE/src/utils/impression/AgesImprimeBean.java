package utils.impression;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import utils.Repertoire;
import utils.Service;
import ages.beans.application.ConfigurationBean;
import ages.beans.etablissement.note.CompositionBean;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfCopyFields;
import com.lowagie.text.pdf.PdfReader;

import entities.Classe;
import entities.Eleve;
import entities.EnsTitulaire;
import entities.Etablissement;
import entities.Sequence;


@ManagedBean(name="agesImprimeBean")
@ViewScoped
public class AgesImprimeBean{
	 
	private final String repertoireJasper="/reports/"; 
	 
	 @SuppressWarnings("rawtypes")
	 HashMap paramjasper = new HashMap();
	 
	 gestionConnection gest = new gestionConnection();
	    
	 
	 public AgesImprimeBean(){
     
	 }
	
	 
	 public AgesImprimeBean(String loginBD,String passBD) throws JRException{
	      gest.loginBD = loginBD;
	      gest.passBD = passBD;	        
	 }
	 
	
	public void imprimerElevesNonEnregle(String codeClasse) {
		// TODO Auto-generated method stub
		Repertoire.addMessageerreur("Non encore fonctionnel");
	}

	public void imprimerElevesNonEnregle() {
		// TODO Auto-generated method stub
		Repertoire.addMessageerreur("Non encore fonctionnel");
	}

	
	
	@SuppressWarnings({ "unchecked" })
	public void imprimerElevesEnregle(Service service ,String classe,String annee,String logo, String codeetablissement) throws JRException, IOException {
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"SAGES Liste des élèves en regle.jasper");
    	
		paramjasper.put("CLASSE",classe);
		paramjasper.put("CODEETAB",codeetablissement);
		paramjasper.put("ANNEE",annee);
		
	    gest.connexionBD();
	    
	    Classe cla=null;
		try{
			cla= service.getGestionressource().rechercheclasse(classe);
		
		}
		catch(Exception e){
			
			e.printStackTrace();
		}

		JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,paramjasper, gest.getCon());
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		
		/***********************************************************************
		 * Pour afficher une boîte de dialogue pour enregistrer le fichier sous
		 * le nom rapport.pdf
		 **********************************************************************/
		response.addHeader("Content-disposition","attachment;filename=Liste-eleves-en-regle-"+cla.getLibelle()+".pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();
	}
	

	/**
	 * Imprime toutes les candidatures recues pour une classe
	 * @throws SQLException
	 * @throws JRException
	 * @throws IOException
	 */
	public void imprimerCandidaturesRecues() throws SQLException, JRException, IOException {
		Repertoire.addMessageerreur("Non encore fonctionnel");
	}

	/**
	 * Imprime les candidatures recues pour une classe
	 * @param codeClasse code de la classe
	 */
	public void imprimerCandidaturesRecues(String codeetablissement)throws SQLException, JRException, IOException {
		// TODO Auto-generated method stub
		Repertoire.addMessageerreur("Non encore fonctionnel");
	}

	public void imprimerCandidaturesAcceptees(String codeetablissement) {
		// TODO Auto-generated method stub
		Repertoire.addMessageerreur("Non encore fonctionnel");
	}

	@SuppressWarnings("unchecked")
	public void imprimerCanditatureAcceptesPDF(String niveau,String codeetablissment) throws SQLException, JRException, IOException {
		
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		paramjasper.put("CODENIVEAU",niveau);
		paramjasper.put("CODEETABLISSEMENT",codeetablissment);


		gest.connexionBD();

		JasperPrint jasperPrint = JasperFillManager.fillReport(getRealPath(repertoireJasper+"Candidature Acceptée.jasper"),paramjasper, gest.getCon());
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		System.out.println("ok pour le compilation et la generation en pdf");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		/***********************************************************************
		 * Pour afficher une boîte de dialogue pour enregistrer le fichier sous
		 * le nom rapport.pdf
		 **********************************************************************/
		response.addHeader("Content-disposition",
				"attachment;filename=CanditatureAcceptees-"+niveau+".pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();
		System.out.println("ok pour le compilation et la generation en pdf");
		
	}

	public void imprimerCanditatureRejetesPDF(String codeetablissement) {
		// TODO Auto-generated method stub
		Repertoire.addMessageerreur("Non encore fonctionnel");
	}

	@SuppressWarnings("unchecked")
	public void imprimerCanditatureRejetesPDF(String codeetablissment, String codeniveau) throws SQLException, JRException, IOException {
		
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		paramjasper.put("CODENIVEAU",codeniveau);
		paramjasper.put("CODEETABLISSEMENT",codeetablissment);
	
	
		gest.connexionBD();
	
		JasperPrint jasperPrint = JasperFillManager.fillReport(getRealPath(repertoireJasper+"Candidature Rejetées.jasper"),
				paramjasper, gest.getCon());
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		System.out.println("ok pour le compilation et la generation en pdf");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		/***********************************************************************
		 * Pour afficher une boîte de dialogue pour enregistrer le fichier sous
		 * le nom rapport.pdf
		 **********************************************************************/
		response.addHeader("Content-disposition",
				"attachment;filename=CanditatureRejetees-"+codeniveau+".pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();
		
	}

	public void imprimerCanditatureAttentePDF(String codeetablissement) {
		// TODO Auto-generated method stub
		Repertoire.addMessageerreur("Non encore fonctionnel");
	}

	@SuppressWarnings("unchecked")
	public void imprimerCanditatureAttentePDF(String codeetablissement,
			String codeniveau) throws JRException, IOException {
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		paramjasper.put("CODENIVEAU",codeniveau);
		paramjasper.put("CODEETABLISSEMENT",codeetablissement);
	
	
		gest.connexionBD();
	
		JasperPrint jasperPrint = JasperFillManager.fillReport(getRealPath(repertoireJasper+"Candidature Attente.jasper"),
				paramjasper, gest.getCon());
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		System.out.println("ok pour le compilation et la generation en pdf");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		/***********************************************************************
		 * Pour afficher une boîte de dialogue pour enregistrer le fichier sous
		 * le nom rapport.pdf
		 **********************************************************************/
		response.addHeader("Content-disposition",
				"attachment;filename=Canditature-en-Attente-"+codeniveau+".pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();
	}

	@SuppressWarnings("unchecked")
	public void imprimerTableauHoneurPDF(String datejour,String codeetablissment,String logoetablissement, Integer ideleve,String classe,int numerotrimestre) throws SQLException, JRException, IOException {
		
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"Tableau D'honneur.jasper");
    	
		paramjasper.put("DATE_JOUR",datejour);
		paramjasper.put("codeetablissement",codeetablissment);
		paramjasper.put("NUMEROTRIMESTRE",numerotrimestre);
		paramjasper.put("classe",classe);
		paramjasper.put("matricule",ideleve);
	    paramjasper.put("LOGO",getRealPath(logoetablissement));
		
		gest.connexionBD();

		JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,	paramjasper, gest.getCon());
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);


		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		/***********************************************************************
		 * Pour afficher une boîte de dialogue pour enregistrer le fichier sous
		 * le nom rapport.pdf
		 **********************************************************************/
		response.addHeader("Content-disposition",
				"attachment;filename=rapport.pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();
		
	}

	@SuppressWarnings("unchecked")
	/**
	 * Impression en pdf des bulletins trimestriels
	 * @param service interface des services metiers
	 * @param codeetablissement code de l'établissement concerné
	 * @param sequence1 numéro de la 1ere séquence du trimestre
	 * @param sequence2 numéro de la 2eme séquence du trimestre
	 * @param classe code de la classe concernée
	 * @param eleves liste des élèves de la classe
	 * @param numerotrimestre numéro du trimestre concerné
	 * @param logo logo de l'établissement
	 * @param modele code du modèle
	 * @throws JRException
	 * @throws IOException
	 */
	public void imprimerBulletinsPDFTrimestre(Service service,String codeetablissement,int sequence1, int sequence2,String classe, List<Integer> eleves,
			int numerotrimestre,String logo,int modele) throws JRException, IOException {
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"Bulletins Ages Trimestrielle.jasper");

    	ByteArrayOutputStream bstream=new ByteArrayOutputStream();
		PdfCopyFields copie;
		JasperPrint jasperPrint=null;
		byte[] bytes;
        
        gest=new gestionConnection();

		gest.connexionBD();
		Classe cla=null;
		try{
			cla= service.getGestionressource().rechercheclasse(classe);
		
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
		Sequence seq1=service.getGestionEtablissement().rechercheSequence(sequence1,service.getAnneeEncours());	
		Sequence seq2=service.getGestionEtablissement().rechercheSequence(sequence2,service.getAnneeEncours());	
		double moypremier = service.getGestionBulletin().moyennePremierTrim(classe, sequence1, sequence2);
		
		double moydernier = service.getGestionBulletin().moyenneDernierTrim(classe, sequence1, sequence2);
		
		double moyclasse = service.getGestionBulletin().moyenneClasseTrim(service.getAnneeEncours(),cla, seq1,seq2);
		
		

		Etablissement etab = service.getGestionEtablissement().rechercheEtablissement(codeetablissement);
		int effectif = service.getGestionBulletin().effectif(cla.getCodeclasse(),service.getAnneeEncours());
		double tauxreussite = service.getGestionBulletin().tauxreussitetrim1(classe, sequence1, sequence2,service.getAnneeEncours());
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		
		paramjasper.put("classe",classe);
		paramjasper.put("libelleclasse",cla.getLibelle());
    	
    	paramjasper.put("numero_sequence",numerotrimestre);
        
        paramjasper.put("numerosequence1",sequence1);
        paramjasper.put("numerosequence2",sequence2);
        paramjasper.put("pays",etab.getPays());
        paramjasper.put("type",etab.getType());
        paramjasper.put("region",etab.getRegion());
        paramjasper.put("departement",etab.getDepartement());
        paramjasper.put("nometab",etab.getNometab());
        paramjasper.put("adresseetab",etab.getAdresse());
        paramjasper.put("devise",etab.getDevise());
        paramjasper.put("boitepostal",etab.getCodepostal());
        paramjasper.put("teletab",etab.getTelephone());
        paramjasper.put("siteetab",etab.getSiteweb());
        paramjasper.put("optionclasse",cla.getOptionserie().getCodeoption());
        paramjasper.put("effectif",effectif);
        
        if((cla.getTitulaires()!=null) &&(!cla.getTitulaires().isEmpty())){
        	
        paramjasper.put("nomenstitulaire",cla.getTitulaires().get(0).getEnseignant().getNomens());
        paramjasper.put("prenomenstitulaire",cla.getTitulaires().get(0).getEnseignant().getPrenomens());
        
        }
        
        paramjasper.put("moyenneclasse",moyclasse);
        paramjasper.put("moyennepremier",moypremier);
        paramjasper.put("moyennedernier",moydernier);
        paramjasper.put("tauxreussite",tauxreussite*100);

        paramjasper.put("annee",service.getAnneeEncours());
        paramjasper.put("codeetablissement",codeetablissement);
        paramjasper.put("LOGO","");
        paramjasper.put("SUBREPORT_DIR",getRealPath(repertoireJasper)+File.separatorChar);
		
		try {
			copie = new PdfCopyFields(bstream);
		
			for(int i=0;i<eleves.size();i++){
			
				double moyenne= service.getGestionBulletin().moyenneEleveTrimDirect(classe, eleves.get(i), sequence1, sequence2);
				double totalpoint =service.getGestionBulletin().totalpointTrim(service.getAnneeEncours(),classe, eleves.get(i), sequence1, sequence2);
				long totalcoef = service.getGestionBulletin().totalcoef(classe,service.getAnneeEncours());
				int  retard = service.getGestionBulletin().retard(classe,seq1, eleves.get(i),service.getAnneeEncours());
				int absence = service.getGestionBulletin().absence(classe,seq2, eleves.get(i),service.getAnneeEncours());
				int avertissement = service.getGestionBulletin().avertissement(seq2, eleves.get(i));
				int blame = service.getGestionBulletin().blame(seq2, eleves.get(i));
				int exclusion = service.getGestionBulletin().exclusion(seq2, eleves.get(i));
				int rang = service.getGestionBulletin().rangEleveClasseTrim(classe, eleves.get(i), sequence1, sequence2);
				double moyenneseq1= service.getGestionBulletin().moyenneEleve(classe, eleves.get(i), sequence1,service.getAnneeEncours());
				
				double moyenneseq2= service.getGestionBulletin().moyenneEleve(classe, eleves.get(i), sequence2,service.getAnneeEncours());
				int rang1 = service.getGestionBulletin().rangEleveClasse(classe, eleves.get(i), sequence1,service.getAnneeEncours());
				int rang2 = service.getGestionBulletin().rangEleveClasse(classe, eleves.get(i), sequence2,service.getAnneeEncours());
		        paramjasper.put("matricule",eleves.get(i));
		        Eleve ele = service.getGestionEleve().rechercheEleveId(eleves.get(i), service.getAnneeEncours());
		        paramjasper.put("nomeleve",ele.getNom());
		        paramjasper.put("prenomeleve",ele.getPrenom());
		        paramjasper.put("datenaissance",ele.getDatenaissance().toString());
		        paramjasper.put("lieu",ele.getLieunaissance());
		        paramjasper.put("mat",ele.getMatricule());
		        paramjasper.put("adressetuteur",ele.getNomtuteur());
		        paramjasper.put("retard",retard);
		        paramjasper.put("absence",absence);
		        paramjasper.put("avertissement",avertissement);
		        paramjasper.put("blame",blame);
		        paramjasper.put("exclusion",exclusion);
		        paramjasper.put("moyenne",moyenne);
		        paramjasper.put("totalpoint",totalpoint);
		        paramjasper.put("totalcoef",totalcoef);
		        paramjasper.put("rang",rang);
		        paramjasper.put("rang1",rang1);
		        paramjasper.put("rang2",rang2);
		        paramjasper.put("moyenneseq1",moyenneseq1);
		        paramjasper.put("moyenneseq2",moyenneseq2);
		        paramjasper.put("sequence1",sequence1-2);
		        paramjasper.put("sequence2",sequence2-2);
		       
		        paramjasper.put("PHOTO_ELEVE","");    
				jasperPrint = JasperFillManager.fillReport(reportPath,paramjasper, gest.getCon());

				bytes = JasperExportManager.exportReportToPdf(jasperPrint);
				
				
				try {
					copie.addDocument(new PdfReader(bytes));
				} catch (DocumentException e) {
					e.printStackTrace();
				}
			}
			
			copie.close();
			bstream.flush();
			
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
							
		response.addHeader("Content-disposition","attachment;filename=bulletin_trimestriel_"+numerotrimestre+"_"+classe+".pdf");
				
		response.setContentLength(bstream.size());
		response.getOutputStream().write(bstream.toByteArray());
		
		response.getOutputStream().flush();
		response.setContentType("application/pdf");
		context.responseComplete();
	}
	
	@SuppressWarnings("unchecked")
	public void imprimerBulletinsPDFTrimestriel(Service service,String codeetablissement,int trim1, int trim2, int trim3,String classe, List<Integer> eleves,
			String logo,int modele,int numerotrimestre) throws JRException, IOException {
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"Bulletins Ages trimestriel V3.jasper");

    	ByteArrayOutputStream bstream=new ByteArrayOutputStream();
		PdfCopyFields copie;
		JasperPrint jasperPrint=null;
		byte[] bytes;
        
        gest=new gestionConnection();

		gest.connexionBD();
		Sequence seq1=service.getGestionEtablissement().rechercheSequence(trim1,service.getAnneeEncours());
		Sequence seq2=service.getGestionEtablissement().rechercheSequence(trim2,service.getAnneeEncours());
		Sequence seq3=service.getGestionEtablissement().rechercheSequence(trim3,service.getAnneeEncours());

		Classe cla=null;
		try{
			cla= service.getGestionressource().rechercheclasse(classe);
		
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
		double moypremier = service.getGestionBulletin().moyennePremierAnnuel(service.getAnneeEncours(),cla, trim1, trim2,trim3);
		
		double moydernier = service.getGestionBulletin().moyenneDernierAnnuel(service.getAnneeEncours(),cla, trim1, trim2,trim3);
		
		double moyclasse = service.getGestionBulletin().moyenneClasseAnnuelDirect(service.getAnneeEncours(),cla.getCodeclasse(), trim1, trim2,trim3);
		
		

		Etablissement etab = service.getGestionEtablissement().rechercheEtablissement(codeetablissement);
		int effectif = service.getGestionBulletin().effectif(cla.getCodeclasse(),service.getAnneeEncours());
		double tauxreussite = service.getGestionBulletin().tauxreussiteAnnuel1(classe, trim1, trim2, trim3,service.getAnneeEncours());
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		
		paramjasper.put("classe",classe);
		paramjasper.put("libelleclasse",cla.getLibelle());
    	
    	paramjasper.put("numero_trimestre",numerotrimestre);
        
    	if(trim1==1){
        paramjasper.put("seq1",1);
        paramjasper.put("seq2",2);
        paramjasper.put("seq3",3);
        paramjasper.put("session",1);
    	}
    	if(trim1==4){
    	paramjasper.put("seq1",4);
        paramjasper.put("seq2",5);
        paramjasper.put("seq3",6);
        paramjasper.put("session",2);
    	}
        paramjasper.put("pays",etab.getPays());
        paramjasper.put("type",etab.getType());
        paramjasper.put("region",etab.getRegion());
        paramjasper.put("departement",etab.getDepartement());
        paramjasper.put("nometab",etab.getNometab());
        paramjasper.put("adresseetab",etab.getAdresse());
        paramjasper.put("devise",etab.getDevise());
        paramjasper.put("boitepostal",etab.getCodepostal());
        paramjasper.put("teletab",etab.getTelephone());
        paramjasper.put("siteetab",etab.getSiteweb());
        paramjasper.put("optionclasse",cla.getOptionserie().getCodeoption());
        paramjasper.put("effectif",effectif);
        
        if((cla.getTitulaires()!=null) &&(!cla.getTitulaires().isEmpty())){
        	
        paramjasper.put("nomenstitulaire",cla.getTitulaires().get(0).getEnseignant().getNomens());
        paramjasper.put("prenomenstitulaire",cla.getTitulaires().get(0).getEnseignant().getPrenomens());
        
        }
        
        paramjasper.put("moyenneclasse",moyclasse);
        paramjasper.put("moyennepremier",moypremier);
        paramjasper.put("moyennedernier",moydernier);
        paramjasper.put("tauxreussite",tauxreussite*100);

        paramjasper.put("annee",service.getAnneeEncours());
           
        paramjasper.put("codeetablissement",codeetablissement);
        paramjasper.put("LOGO",getRealPath(logo));
        if(getRealPath(ConfigurationBean.imgpath).substring(0,getRealPath(ConfigurationBean.imgpath).length()-76).length()==28){
        System.out.println("le chemin du logo est:"+  getRealPath(ConfigurationBean.imgpath).substring(0,getRealPath(ConfigurationBean.imgpath).length()-76)+codeetablissement+"//logo.jpg");
        paramjasper.put("LOGO",getRealPath(ConfigurationBean.imgpath).substring(0,getRealPath(ConfigurationBean.imgpath).length()-76)+codeetablissement+"//logo.jpg");
        }else{ paramjasper.put("LOGO",getRealPath(ConfigurationBean.imgpath).substring(0,getRealPath(ConfigurationBean.imgpath).length()-75)+codeetablissement+"//logo.jpg");
       // 
        //System.out.println("le chemin du logo est:"+  getRealPath(ConfigurationBean.imgpath).substring(0,getRealPath(ConfigurationBean.imgpath).length()-75)+codeetablissement+"//logo.jpg");
        }
        //paramjasper.put("LOGO","C:\\jboss\\server\\default\\tmp\\"+"logo.jpg");
        paramjasper.put("SUBREPORT_DIR",getRealPath(repertoireJasper)+File.separatorChar);
		
		try {
			copie = new PdfCopyFields(bstream);
		
			for(int i=0;i<eleves.size();i++){
			
				double moyenne= service.getGestionBulletin().moyenneEleveAnnuelDirect(service.getAnneeEncours(),classe, eleves.get(i), trim1, trim2,trim3);
				double totalpoint =service.getGestionBulletin().totalpointAnnuel(service.getAnneeEncours(),classe, eleves.get(i), trim1, trim2, trim3);
				long totalcoef = service.getGestionBulletin().totalcoef(classe,service.getAnneeEncours());
				int  retard1 = service.getGestionBulletin().retard(classe,seq1, eleves.get(i),service.getAnneeEncours());
				int  retard2 = service.getGestionBulletin().retard(classe,seq2, eleves.get(i),service.getAnneeEncours());
				int  retard3 = service.getGestionBulletin().retard(classe,seq3, eleves.get(i),service.getAnneeEncours());
				int retard = retard1 +retard2+retard3 ;
				int absence1 = service.getGestionBulletin().absence(classe,seq1, eleves.get(i),service.getAnneeEncours());
				int absence2 = service.getGestionBulletin().absence(classe,seq2, eleves.get(i),service.getAnneeEncours());
				int absence3 = service.getGestionBulletin().absence(classe,seq3, eleves.get(i),service.getAnneeEncours());
				int absence = absence1+absence2+absence3 ;
				int rang = service.getGestionBulletin().rangEleveClasseAnnuel(service.getAnneeEncours(),classe, eleves.get(i), trim1, trim2, trim3);
				double moyennetrim1= service.getGestionBulletin().moyenneEleve(classe, eleves.get(i), trim1,service.getAnneeEncours());
				
				double moyennetrim2= service.getGestionBulletin().moyenneEleve(classe, eleves.get(i), trim2,service.getAnneeEncours());
				double moyennetrim3= service.getGestionBulletin().moyenneEleve(classe, eleves.get(i), trim3,service.getAnneeEncours());
				int rang1 = service.getGestionBulletin().rangEleveClasse(classe, eleves.get(i), trim1,service.getAnneeEncours());
				int rang2 = service.getGestionBulletin().rangEleveClasse(classe, eleves.get(i), trim2,service.getAnneeEncours());
				int rang3 = service.getGestionBulletin().rangEleveClasse(classe, eleves.get(i), trim3,service.getAnneeEncours());
		        paramjasper.put("matricule",eleves.get(i));
		        Eleve ele = service.getGestionEleve().rechercheEleveId(eleves.get(i), service.getAnneeEncours());
		        paramjasper.put("nomeleve",ele.getNom());
		        paramjasper.put("prenomeleve",ele.getPrenom());
		        paramjasper.put("datenaissance",ele.getDatenaissance().toString());
		        paramjasper.put("lieu",ele.getLieunaissance());
		        paramjasper.put("adressetuteur",ele.getNomtuteur());
		        paramjasper.put("retard",retard);
		        paramjasper.put("absence",absence);
		        paramjasper.put("moyenne",moyenne);
		        paramjasper.put("totalpoint",totalpoint);
		        paramjasper.put("totalcoef",totalcoef);
		        paramjasper.put("rang",rang);
		        paramjasper.put("rang1",rang1);
		        paramjasper.put("rang2",rang2);
		        paramjasper.put("rang3",rang3);
		        paramjasper.put("moyennetrim1",moyennetrim1);
		        paramjasper.put("moyennetrim2",moyennetrim2);
		        paramjasper.put("moyennetrim3",moyennetrim3);
		       
		       
		        paramjasper.put("PHOTO_ELEVE",getRealPath(ele.getPhoto()));    
				jasperPrint = JasperFillManager.fillReport(reportPath,paramjasper, gest.getCon());

				bytes = JasperExportManager.exportReportToPdf(jasperPrint);
				
				
				try {
					copie.addDocument(new PdfReader(bytes));
				} catch (DocumentException e) {
					e.printStackTrace();
				}
			}
			
			copie.close();
			bstream.flush();
			
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
							
		response.addHeader("Content-disposition","attachment;filename=bulletin_trimestriel_"+numerotrimestre+"_"+classe+".pdf");
				
		response.setContentLength(bstream.size());
		response.getOutputStream().write(bstream.toByteArray());
		
		response.getOutputStream().flush();
		response.setContentType("application/pdf");
		context.responseComplete();
	}



	@SuppressWarnings("unchecked")
	public void imprimerBulletinsPDFSequence(Service service,String codeetablissement,String classe, List<Integer> eleves,
			int numerosequence,String logo,int modele) throws JRException, IOException {
		//String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"Bulletins Ages"+modele+".jasper");
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"Bulletins Ages sequentiel V3.jasper");
    	ByteArrayOutputStream bstream=new ByteArrayOutputStream();
		PdfCopyFields copie;
		JasperPrint jasperPrint=null;
		byte[] bytes;
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();

        gest=new gestionConnection();

		gest.connexionBD();

		Classe cla=null;
		EnsTitulaire titu=null;

		Sequence seq=service.getGestionEtablissement().rechercheSequence(numerosequence,service.getAnneeEncours());
		try{
			cla= service.getGestionressource().rechercheclasse(classe);
			titu=service.getGestionressource().rechercheTitulaire(service.getAnneeEncours(), classe);
		
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
		double moypremier = service.getGestionBulletin().moyennePremier(service.getAnneeEncours(),cla, seq);
		
		double moydernier = service.getGestionBulletin().moyenneDernier(service.getAnneeEncours(),cla, seq);
		
		double moyclasse = service.getGestionBulletin().moyenneClasse(service.getAnneeEncours(),cla, seq);
		
		

		Etablissement etab = service.getGestionEtablissement().rechercheEtablissement(codeetablissement);
		int effectif = service.getGestionBulletin().effectif(cla.getCodeclasse(),service.getAnneeEncours());
		float tauxreussite = service.getGestionBulletin().tauxreussite(classe, numerosequence,service.getAnneeEncours());

		paramjasper.put("classe",classe);
		paramjasper.put("libelleclasse",cla.getLibelle());
        paramjasper.put("numero_sequence",numerosequence);
        System.out.println("le chemin du logo est:"+  getRealPath(ConfigurationBean.imgpath).substring(0,getRealPath(ConfigurationBean.imgpath).length()-76)+codeetablissement+"//logo.jpg");
        
        
       // paramjasper.put("LOGO","C:\\jboss\\server\\default\\tmp\\"+"logo.jpg");
        if(getRealPath(ConfigurationBean.imgpath).substring(0,getRealPath(ConfigurationBean.imgpath).length()-76).length()==28)
        paramjasper.put("LOGO",getRealPath(ConfigurationBean.imgpath).substring(0,getRealPath(ConfigurationBean.imgpath).length()-76)+codeetablissement+"//logo.jpg");
        else paramjasper.put("LOGO",getRealPath(ConfigurationBean.imgpath).substring(0,getRealPath(ConfigurationBean.imgpath).length()-75)+codeetablissement+"//logo.jpg");
        
        paramjasper.put("SUBREPORT_DIR",getRealPath(repertoireJasper));
        paramjasper.put("numerosequence1",numerosequence);
        paramjasper.put("pays",etab.getPays());
        paramjasper.put("type",etab.getType());
        paramjasper.put("region",etab.getRegion());
        paramjasper.put("departement",etab.getDepartement());
        paramjasper.put("nometab",etab.getNometab());
        paramjasper.put("adresseetab",etab.getAdresse());
        paramjasper.put("devise",etab.getDevise());
        paramjasper.put("boitepostal",etab.getCodepostal());
        paramjasper.put("teletab",etab.getTelephone());
        paramjasper.put("siteetab",etab.getSiteweb());
        paramjasper.put("optionclasse",cla.getOptionserie().getCodeoption());
        paramjasper.put("effectif",effectif);
        
        if((cla.getTitulaires()!=null) &&(!cla.getTitulaires().isEmpty())){
        	
        paramjasper.put("nomenstitulaire",titu.getEnseignant().getNomens());
        paramjasper.put("prenomenstitulaire",titu.getEnseignant().getPrenomens());
        
        }
        
        paramjasper.put("moyenneclasse",moyclasse);
        paramjasper.put("moyennepremier",moypremier);
        paramjasper.put("moyennedernier",moydernier);
        paramjasper.put("tauxreussite",tauxreussite*100);

        paramjasper.put("annee",service.getAnneeEncours());

        paramjasper.put("codeetablissement",codeetablissement);
        paramjasper.put("SUBREPORT_DIR",getRealPath(repertoireJasper)+File.separatorChar);
		
		try {
			copie = new PdfCopyFields(bstream);
		
			for(int i=0;i<eleves.size();i++){


				double moyenne= service.getGestionBulletin().moyenneEleve(classe, eleves.get(i), numerosequence,service.getAnneeEncours());
				double totalpoint =service.getGestionBulletin().totalpoint(service.getAnneeEncours(),classe, eleves.get(i), numerosequence);
				long totalcoef = service.getGestionBulletin().totalcoef(classe, service.getAnneeEncours());
				int  retard = service.getGestionBulletin().retard(classe,seq, eleves.get(i),service.getAnneeEncours());
				int absence = service.getGestionBulletin().absence(classe,seq, eleves.get(i),service.getAnneeEncours());
				int rang = service.getGestionBulletin().rangEleveClasse(classe, eleves.get(i), numerosequence,service.getAnneeEncours());
				
		        paramjasper.put("matricule",eleves.get(i));
		        Eleve ele = service.getGestionEleve().rechercheEleveId(eleves.get(i), service.getAnneeEncours());
		        paramjasper.put("nomeleve",ele.getNom());
		        paramjasper.put("prenomeleve",ele.getPrenom());
		        
		        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
				String date1= format.format(ele.getDatenaissance());
		        
		        paramjasper.put("datenaissance",date1.toString());
		        paramjasper.put("lieu",ele.getLieunaissance());
		        paramjasper.put("adressetuteur",ele.getNomtuteur());
		        paramjasper.put("retard",retard);
		        paramjasper.put("absence",absence);
		        paramjasper.put("moyenne",moyenne);
		        paramjasper.put("totalpoint",totalpoint);
		        paramjasper.put("totalcoef",totalcoef);
		        paramjasper.put("rang",rang);
		       
		        paramjasper.put("PHOTO_ELEVE",getRealPath(ele.getPhoto()));    
				jasperPrint = JasperFillManager.fillReport(reportPath,paramjasper, gest.getCon());

				bytes = JasperExportManager.exportReportToPdf(jasperPrint);
				
				//PdfReader reader=new PdfReader(bytes);
				
				try {
					copie.addDocument(new PdfReader(bytes));
				} catch (DocumentException e) {
					e.printStackTrace();
				}
			}
			
			copie.close();
			bstream.flush();
			
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
							
		response.addHeader("Content-disposition","attachment;filename=bulletin_sequentiel"+numerosequence+"_"+classe+".pdf");
				
		response.setContentLength(bstream.size());
		response.getOutputStream().write(bstream.toByteArray());
		
		response.getOutputStream().flush();
		response.setContentType("application/pdf");
		context.responseComplete();
	}
	
	public double moyennepremier(Service service,String classe,List<Integer> el){
		int i;
		int rang = 0;
		double moyenne1=0;
		double moyenne2=0;
		double moyenne3=0;
		for(i=0;i<el.size();i++){
			 rang = service.getGestionBulletin().rangEleveClasseS(classe, el.get(i),service.getAnneeEncours());
			 if(rang==1){
				 moyenne1= service.getGestionBulletin().moyenneEleveAnnuelDirect(service.getAnneeEncours(),classe, el.get(i),1,2,3); 
				 moyenne2= service.getGestionBulletin().moyenneEleveAnnuelDirect(service.getAnneeEncours(),classe, el.get(i),4,5,6);
				 moyenne3= service.getGestionBulletin().moyenneEleveTrimDirect(classe, el.get(i),7,8);
				 break;
			 }
			 
			}
		return (moyenne1+moyenne2+moyenne3)/3;
	}
	
	public double moyennedernier(Service service,String classe,List<Integer> el){
		int i;
		int rang = 0;
		double moyenne1=0;
		double moyenne2=0;
		double moyenne3=0;
		int effectif = service.getGestionBulletin().effectif(classe,service.getAnneeEncours());
		for(i=0;i<el.size();i++){
			 rang = service.getGestionBulletin().rangEleveClasseS(classe, el.get(i),service.getAnneeEncours());
			if(classe=="trois1" ||classe=="trois2" || classe=="trois21" || classe=="TermD" || classe=="PremD" || classe=="PremA1" || classe=="PremA2" || classe=="Prem21"){
			 if(rang==(effectif-10)){
				 moyenne1= service.getGestionBulletin().moyenneEleveAnnuelDirect(service.getAnneeEncours(),classe, el.get(i),1,2,3); 
				 moyenne2= service.getGestionBulletin().moyenneEleveAnnuelDirect(service.getAnneeEncours(),classe, el.get(i),4,5,6);
				 moyenne3= service.getGestionBulletin().moyenneEleveTrimDirect(classe, el.get(i),7,8);
				 break;
			 
			 }
			}else{
				if(rang==effectif){
					 moyenne1= service.getGestionBulletin().moyenneEleveAnnuelDirect(service.getAnneeEncours(),classe, el.get(i),1,2,3); 
					 moyenne2= service.getGestionBulletin().moyenneEleveAnnuelDirect(service.getAnneeEncours(),classe, el.get(i),4,5,6);
					 moyenne3= service.getGestionBulletin().moyenneEleveTrimDirect(classe, el.get(i),7,8);
					 break;
				 }
				
			}
			}
		return (moyenne1+moyenne2+moyenne3)/3;
	}
	
	@SuppressWarnings({ "unchecked"})
	public void imprimerBulletinsPDFAnnuelN(Service service,String codeetablissement,String classe, List<Integer> eleves,
			String logo,int modele) throws JRException, IOException {
		//String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"Bulletins Ages"+modele+".jasper");
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"Bulletins Ages Annuel V3.jasper");
    	ByteArrayOutputStream bstream=new ByteArrayOutputStream();
		PdfCopyFields copie;
		JasperPrint jasperPrint=null;
		byte[] bytes;
		int  r1=0,r2=0,r3=0,r4=0,r5=0,r6=0,r7=0,r8=0;
		int a1=0,a2=0,a3=0,a4=0,a5=0,a6=0,a7=0,a8=0;
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();

        gest=new gestionConnection();

		gest.connexionBD();

		Classe cla=null;
		try{
			cla= service.getGestionressource().rechercheclasse(classe);
		
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
		//ici je renvoie la liste de la classe pour pourvoir calculer la moyenne du premier et du dernier
		 List<Integer> el = new ArrayList<Integer>();
		 el=service.getGestionEleve().listerElevesERClasseN(cla.getCodeclasse(), service.getAnneeEncours());
		
		/*for(int i=0;i<listeEleve.size();i++){
			el.add(listeEleve.get(i).getIdeleve());
		}*/
		 
		 Sequence seq1=service.getGestionEtablissement().rechercheSequence(1,service.getAnneeEncours());
	     Sequence seq2=service.getGestionEtablissement().rechercheSequence(2,service.getAnneeEncours());
		 Sequence seq3=service.getGestionEtablissement().rechercheSequence(3,service.getAnneeEncours());
		 Sequence seq4=service.getGestionEtablissement().rechercheSequence(4,service.getAnneeEncours());
		 Sequence seq5=service.getGestionEtablissement().rechercheSequence(5,service.getAnneeEncours());
		 Sequence seq6=service.getGestionEtablissement().rechercheSequence(6,service.getAnneeEncours());
		 Sequence seq7=service.getGestionEtablissement().rechercheSequence(7,service.getAnneeEncours());
		 Sequence seq8=service.getGestionEtablissement().rechercheSequence(8,service.getAnneeEncours());
		
		double moypremier = moyennepremier(service,classe,el);
		double moydernier = moyennedernier(service,classe,el);
		
		double moyclasse = service.getGestionBulletin().moyenneClasseS(service.getAnneeEncours(),cla.getCodeclasse());
		
		

		Etablissement etab = service.getGestionEtablissement().rechercheEtablissement(codeetablissement);
		int effectif = service.getGestionBulletin().effectif(cla.getCodeclasse(),service.getAnneeEncours());
		double tauxreussite = service.getGestionBulletin().tauxreussiteS(classe,service.getAnneeEncours());

		paramjasper.put("classe",classe);
		paramjasper.put("seq1",1);
		paramjasper.put("seq3",3);
		paramjasper.put("seq4",4);
		paramjasper.put("seq6",6);
		paramjasper.put("seq7",7);
		paramjasper.put("seq8",8);
		paramjasper.put("libelleclasse",cla.getLibelle());
        paramjasper.put("LOGO","");
        
        paramjasper.put("SUBREPORT_DIR",getRealPath(repertoireJasper));
        paramjasper.put("pays",etab.getPays());
        paramjasper.put("type",etab.getType());
        paramjasper.put("region",etab.getRegion());
        paramjasper.put("departement",etab.getDepartement());
        paramjasper.put("nometab",etab.getNometab());
        paramjasper.put("adresseetab",etab.getAdresse());
        paramjasper.put("devise",etab.getDevise());
        paramjasper.put("boitepostal",etab.getCodepostal());
        paramjasper.put("teletab",etab.getTelephone());
        paramjasper.put("siteetab",etab.getSiteweb());
        paramjasper.put("optionclasse",cla.getOptionserie().getCodeoption());
        paramjasper.put("effectif",effectif);
        
        if((cla.getTitulaires()!=null) &&(!cla.getTitulaires().isEmpty())){
        	
        paramjasper.put("nomenstitulaire",cla.getTitulaires().get(0).getEnseignant().getNomens());
        paramjasper.put("prenomenstitulaire",cla.getTitulaires().get(0).getEnseignant().getPrenomens());
        
        }
        
        paramjasper.put("moyenneclasse",moyclasse);
        paramjasper.put("moyennepremier",moypremier);
        paramjasper.put("moyennedernier",moydernier);
        paramjasper.put("tauxreussite",tauxreussite*100);

        paramjasper.put("annee",service.getAnneeEncours());

        paramjasper.put("codeetablissement",codeetablissement);
        paramjasper.put("SUBREPORT_DIR",getRealPath(repertoireJasper)+File.separatorChar);
		
		try {
			copie = new PdfCopyFields(bstream);
		
			for(int i=0;i<eleves.size();i++){
				
				double moyennetrim1= service.getGestionBulletin().moyenneEleveAnnuelDirect(service.getAnneeEncours(),classe, eleves.get(i),1,2,3);
				int rang1 = service.getGestionBulletin().rangEleveClasseAnnuel(service.getAnneeEncours(),classe, eleves.get(i),1,2,3);
				double moyennetrim2= service.getGestionBulletin().moyenneEleveAnnuelDirect(service.getAnneeEncours(),classe, eleves.get(i),4,5,6);
				int rang2 = service.getGestionBulletin().rangEleveClasseAnnuel(service.getAnneeEncours(),classe, eleves.get(i),4,5,6);
				double moyennetrim3= service.getGestionBulletin().moyenneEleveTrimDirect(classe, eleves.get(i),7,8);
				int rang3 = service.getGestionBulletin().rangEleveClasseTrim(classe, eleves.get(i),7,8);
				
				double moyenne= service.getGestionBulletin().moyenneEleveS(classe, eleves.get(i),service.getAnneeEncours());

				double totalpoint =service.getGestionBulletin().totalpointG(service.getAnneeEncours(),classe, eleves.get(i),1,2,3,4,5,6,7,8);
				long totalcoef = service.getGestionBulletin().totalcoefEleve(classe, eleves.get(i),1);
				
				/*if(classe=="trois1" ||classe=="trois2" || classe=="trois21" || classe=="" || classe=="PremD" || classe=="PremA1" || classe=="PremA2" || classe=="Prem21" || classe!="" || classe=="" || classe=="PremC"){
					;
				}else{*/
					 r1 = service.getGestionBulletin().retard(classe,seq1, eleves.get(i),service.getAnneeEncours());
					 a1 = service.getGestionBulletin().absence(classe,seq1, eleves.get(i),service.getAnneeEncours());
					 r2 = service.getGestionBulletin().retard(classe,seq2, eleves.get(i),service.getAnneeEncours());
					 a2 = service.getGestionBulletin().absence(classe,seq2, eleves.get(i),service.getAnneeEncours());
					 r3 = service.getGestionBulletin().retard(classe,seq3, eleves.get(i),service.getAnneeEncours());
					 a3 = service.getGestionBulletin().absence(classe,seq3, eleves.get(i),service.getAnneeEncours());
					 r4 = service.getGestionBulletin().retard(classe,seq4, eleves.get(i),service.getAnneeEncours());
					 a4 = service.getGestionBulletin().absence(classe,seq4, eleves.get(i),service.getAnneeEncours());
					 r5 = service.getGestionBulletin().retard(classe,seq5, eleves.get(i),service.getAnneeEncours());
					 a5 = service.getGestionBulletin().absence(classe,seq5, eleves.get(i),service.getAnneeEncours());
					 r6 = service.getGestionBulletin().retard(classe,seq6, eleves.get(i),service.getAnneeEncours());
					 a6 = service.getGestionBulletin().absence(classe,seq6, eleves.get(i),service.getAnneeEncours());
					 r7 = service.getGestionBulletin().retard(classe,seq7, eleves.get(i),service.getAnneeEncours());
					 a7 = service.getGestionBulletin().absence(classe,seq7, eleves.get(i),service.getAnneeEncours());
					 r8 = service.getGestionBulletin().retard(classe,seq8, eleves.get(i),service.getAnneeEncours());
					 a8 = service.getGestionBulletin().absence(classe,seq8, eleves.get(i),service.getAnneeEncours());
					
				//}
				
				
				int rang = service.getGestionBulletin().rangEleveClasseS(classe, eleves.get(i),service.getAnneeEncours());
				
		        paramjasper.put("matricule",eleves.get(i));
		        Eleve ele = service.getGestionEleve().rechercheEleveId(eleves.get(i), service.getAnneeEncours());
		        paramjasper.put("nomeleve",ele.getNom());
		        paramjasper.put("prenomeleve",ele.getPrenom());
		        paramjasper.put("datenaissance",ele.getDatenaissance().toString());
		        paramjasper.put("lieu",ele.getLieunaissance());
		        paramjasper.put("adressetuteur",ele.getNomtuteur());
		        paramjasper.put("mat",ele.getMatricule());
		        paramjasper.put("retard",r1+r2+r3+r4+r5+r6+r7+r8);
		        paramjasper.put("absence",a1+a2+a3+a4+a5+a6+a7+a8);
		        paramjasper.put("moyenne",moyenne);
		        paramjasper.put("moyennetrim1",moyennetrim1);
		        paramjasper.put("moyennetrim2",moyennetrim2);
		        paramjasper.put("moyennetrim3",moyennetrim3);
		        paramjasper.put("rang1",rang1);
		        paramjasper.put("rang2",rang2);
		        paramjasper.put("rang3",rang3);
		        paramjasper.put("totalpoint",totalpoint);
		        paramjasper.put("totalcoef",totalcoef);
		        paramjasper.put("rang",rang);
		       
		        paramjasper.put("PHOTO_ELEVE","");    
				jasperPrint = JasperFillManager.fillReport(reportPath,paramjasper, gest.getCon());

				bytes = JasperExportManager.exportReportToPdf(jasperPrint);
				
				//PdfReader reader=new PdfReader(bytes);
				
				try {
					copie.addDocument(new PdfReader(bytes));
				} catch (DocumentException e) {
					e.printStackTrace();
				}
			}
			
			copie.close();
			bstream.flush();
			
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
							
		response.addHeader("Content-disposition","attachment;filename=bulletin_annuel_"+classe+".pdf");
				
		response.setContentLength(bstream.size());
		response.getOutputStream().write(bstream.toByteArray());
		
		response.getOutputStream().flush();
		response.setContentType("application/pdf");
		context.responseComplete();
	}
	
	
	@SuppressWarnings("unchecked")
	/**
	 * Impression en pdf des bulletins annuel
	 * @param service interface des services metiers
	 * @param codeetablissement code de l'établissement concerné
	 * @param sequence1 numéro de la 1ere séquence du trimestre
	 * @param sequence2 numéro de la 2eme séquence du trimestre
	 * @param classe code de la classe concernée
	 * @param eleves liste des élèves de la classe
	 * @param numerotrimestre numéro du trimestre concerné
	 * @param logo logo de l'établissement
	 * @param modele code du modèle
	 * @throws JRException
	 * @throws IOException
	 */
	public void imprimerBulletinsPDFAnnuel(Service service,String codeetablissement,int trim1, int trim2, int trim3,String classe, List<Integer> eleves,
			String logo,int modele) throws JRException, IOException {
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"Bulletins Ages Annuel.jasper");

    	ByteArrayOutputStream bstream=new ByteArrayOutputStream();
		PdfCopyFields copie;
		JasperPrint jasperPrint=null;
		byte[] bytes;
        
        gest=new gestionConnection();

		gest.connexionBD();
		Classe cla=null;
		double moypremier = service.getGestionBulletin().moyennePremierAnnuel(classe, trim1, trim2,trim3);
		
		double moydernier = service.getGestionBulletin().moyenneDernierAnnuel(classe, trim1, trim2,trim3);
		
		double moyclasse = service.getGestionBulletin().moyenneClasseAnnuel(classe, trim1, trim2,trim3);
		Sequence seq1=service.getGestionEtablissement().rechercheSequence(trim1,service.getAnneeEncours());
		Sequence seq2=service.getGestionEtablissement().rechercheSequence(trim2,service.getAnneeEncours());
		Sequence seq3=service.getGestionEtablissement().rechercheSequence(trim3,service.getAnneeEncours());
		try{
			cla= service.getGestionressource().rechercheclasse(classe);
		
		}
		catch(Exception e){
			
			e.printStackTrace();
		}

		Etablissement etab = service.getGestionEtablissement().rechercheEtablissement(codeetablissement);
		int effectif = service.getGestionBulletin().effectif(cla.getCodeclasse(),service.getAnneeEncours());
		double tauxreussite = service.getGestionBulletin().tauxreussiteAnnuel(classe, trim1, trim2, trim3,service.getAnneeEncours());
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		
		paramjasper.put("classe",classe);
		paramjasper.put("libelleclasse",cla.getLibelle());
    	
    	paramjasper.put("numero_sequence",0);
        
        paramjasper.put("trim1",trim1);
        paramjasper.put("trim2",trim2);
        paramjasper.put("trim3",trim3);
        paramjasper.put("pays",etab.getPays());
        paramjasper.put("type",etab.getType());
        paramjasper.put("region",etab.getRegion());
        paramjasper.put("departement",etab.getDepartement());
        paramjasper.put("nometab",etab.getNometab());
        paramjasper.put("adresseetab",etab.getAdresse());
        paramjasper.put("devise",etab.getDevise());
        paramjasper.put("boitepostal",etab.getCodepostal());
        paramjasper.put("teletab",etab.getTelephone());
        paramjasper.put("siteetab",etab.getSiteweb());
        paramjasper.put("optionclasse",cla.getOptionserie().getCodeoption());
        paramjasper.put("effectif",effectif);
        
        if((cla.getTitulaires()!=null) &&(!cla.getTitulaires().isEmpty())){
        	
        paramjasper.put("nomenstitulaire",cla.getTitulaires().get(0).getEnseignant().getNomens());
        paramjasper.put("prenomenstitulaire",cla.getTitulaires().get(0).getEnseignant().getPrenomens());
        
        }
        
        paramjasper.put("moyenneclasse",moyclasse);
        paramjasper.put("moyennepremier",moypremier);
        paramjasper.put("moyennedernier",moydernier);
        paramjasper.put("tauxreussite",tauxreussite*100);

        paramjasper.put("annee",cla.getAnnee().getAnneeacademique());
           
        paramjasper.put("codeetablissement",codeetablissement);
        paramjasper.put("LOGO","");
        paramjasper.put("SUBREPORT_DIR",getRealPath(repertoireJasper)+File.separatorChar);
		
		try {
			copie = new PdfCopyFields(bstream);
		
			for(int i=0;i<eleves.size();i++){
			
				double moyenne= service.getGestionBulletin().moyenneEleveAnnuelDirect(service.getAnneeEncours(),classe, eleves.get(i), trim1, trim2,trim3);
				double totalpoint =service.getGestionBulletin().totalpointAnnuel(service.getAnneeEncours(),classe, eleves.get(i), trim1, trim2, trim3);
				long totalcoef = service.getGestionBulletin().totalcoef(classe,service.getAnneeEncours());
				int  retard1 = service.getGestionBulletin().retard(classe,seq1, eleves.get(i),service.getAnneeEncours());
				/*int  retard2 = service.getGestionBulletin().retard(classe,trim2, eleves.get(i));
				int  retard3 = service.getGestionBulletin().retard(classe,trim3, eleves.get(i));*/
				int retard = retard1 ;
				int absence1 = service.getGestionBulletin().absence(classe,seq1, eleves.get(i),service.getAnneeEncours());
				/*int absence2 = service.getGestionBulletin().absence(classe,trim2, eleves.get(i));
				int absence3 = service.getGestionBulletin().absence(classe,trim3, eleves.get(i));*/
				int absence = absence1 ;
				int avertissement1 = service.getGestionBulletin().avertissement(seq1, eleves.get(i));
				int avertissement2 = service.getGestionBulletin().avertissement(seq2, eleves.get(i));
				int avertissement3 = service.getGestionBulletin().avertissement(seq3, eleves.get(i));
				int avertissement = avertissement1 + avertissement2 + avertissement3;
				int blame1 = service.getGestionBulletin().blame(seq1, eleves.get(i));
				int blame2 = service.getGestionBulletin().blame(seq2, eleves.get(i));
				int blame3 = service.getGestionBulletin().blame(seq3, eleves.get(i));
				int blame = blame1 + blame2 + blame3;
				int exclusion1 = service.getGestionBulletin().exclusion(seq1, eleves.get(i));
				int exclusion2 = service.getGestionBulletin().exclusion(seq2, eleves.get(i));
				int exclusion3 = service.getGestionBulletin().exclusion(seq3, eleves.get(i));
				int exclusion = exclusion1 + exclusion2 + exclusion3;
				int rang = service.getGestionBulletin().rangEleveClasseAnnuel(service.getAnneeEncours(),classe, eleves.get(i), trim1, trim2, trim3);
				double moyennetrim1= service.getGestionBulletin().moyenneEleve(classe, eleves.get(i), trim1,service.getAnneeEncours());
				
				double moyennetrim2= service.getGestionBulletin().moyenneEleve(classe, eleves.get(i), trim2,service.getAnneeEncours());
				double moyennetrim3= service.getGestionBulletin().moyenneEleve(classe, eleves.get(i), trim3,service.getAnneeEncours());
				int rang1 = service.getGestionBulletin().rangEleveClasse(classe, eleves.get(i), trim1,service.getAnneeEncours());
				int rang2 = service.getGestionBulletin().rangEleveClasse(classe, eleves.get(i), trim2,service.getAnneeEncours());
				int rang3 = service.getGestionBulletin().rangEleveClasse(classe, eleves.get(i), trim3,service.getAnneeEncours());
		        paramjasper.put("matricule",eleves.get(i));
		        Eleve ele = service.getGestionEleve().rechercheEleveId(eleves.get(i), cla.getAnnee().getAnneeacademique());
		        paramjasper.put("nomeleve",ele.getNom());
		        paramjasper.put("prenomeleve",ele.getPrenom());
		        paramjasper.put("datenaissance",ele.getDatenaissance().toString());
		        paramjasper.put("lieu",ele.getLieunaissance());
		        paramjasper.put("adressetuteur",ele.getNomtuteur());
		        paramjasper.put("retard",retard);
		        paramjasper.put("absence",absence);
		        paramjasper.put("avertissement",avertissement);
		        paramjasper.put("blame",blame);
		        paramjasper.put("exclusion",exclusion);
		        paramjasper.put("moyenne",moyenne);
		        paramjasper.put("totalpoint",totalpoint);
		        paramjasper.put("totalcoef",totalcoef);
		        paramjasper.put("rang",rang);
		        paramjasper.put("rang1",rang1);
		        paramjasper.put("rang2",rang2);
		        paramjasper.put("rang3",rang3);
		        paramjasper.put("moyennetrim1",moyennetrim1);
		        paramjasper.put("moyennetrim2",moyennetrim2);
		        paramjasper.put("moyennetrim3",moyennetrim3);
		       
		       
		        paramjasper.put("PHOTO_ELEVE","");    
				jasperPrint = JasperFillManager.fillReport(reportPath,paramjasper, gest.getCon());

				bytes = JasperExportManager.exportReportToPdf(jasperPrint);
				
				
				try {
					copie.addDocument(new PdfReader(bytes));
				} catch (DocumentException e) {
					e.printStackTrace();
				}
			}
			
			copie.close();
			bstream.flush();
			
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
							
		response.addHeader("Content-disposition","attachment;filename=bulletin_annuel_"+classe+".pdf");
				
		response.setContentLength(bstream.size());
		response.getOutputStream().write(bstream.toByteArray());
		
		response.getOutputStream().flush();
		response.setContentType("application/pdf");
		context.responseComplete();
	}


	@SuppressWarnings("unchecked")
	public void imprimerFacturesVersement(int ideleve, String idversement, String classe,
			String logo,String codetab,String annee) throws JRException, IOException {
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		
		paramjasper.put("MATRICULE",ideleve);
		
		System.out.println("le classe est"+classe);
		
		paramjasper.put("CLASSE",classe);
		
		paramjasper.put("ANNEE",annee);
		
		paramjasper.put("ETAB",codetab);
		 if(getRealPath(ConfigurationBean.imgpath).substring(0,getRealPath(ConfigurationBean.imgpath).length()-76).length()==28)
		 paramjasper.put("LOGO",getRealPath(ConfigurationBean.imgpath).substring(0,getRealPath(ConfigurationBean.imgpath).length()-76)+codetab+"//logo.jpg");
		 else paramjasper.put("LOGO",getRealPath(ConfigurationBean.imgpath).substring(0,getRealPath(ConfigurationBean.imgpath).length()-75)+codetab+"//logo.jpg");
		
	    paramjasper.put("NUMEROVERSEMENT",idversement);
	    
		gest.connexionBD();

		JasperPrint jasperPrint = JasperFillManager.fillReport(getRealPath(repertoireJasper+"SAGES Reçu éleve.jasper"),paramjasper, gest.getCon());
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		System.out.println("ok pour le compilation et la generation en pdf");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		/***********************************************************************
		 * Pour afficher une boîte de dialogue pour enregistrer le fichier sous
		 * le nom rapport.pdf
		 **********************************************************************/
		response.addHeader("Content-disposition",
				"attachment;filename=SAGES-Reçu-étudiant"+idversement+".pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();
		System.out.println("ok pour le compilation et la generation en pdf");
	}
	
	
	@SuppressWarnings("unchecked")
	public void imprimerCertificat(int ideleve, String classe,
			String logo,String codetab,String annee, int numerocertificat) throws JRException, IOException {
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		
		paramjasper.put("ideleve",ideleve);
		
		paramjasper.put("numero",numerocertificat);
		
		paramjasper.put("annee",annee);
		
		paramjasper.put("etab",codetab);
		 if(getRealPath(ConfigurationBean.imgpath).substring(0,getRealPath(ConfigurationBean.imgpath).length()-75).length()==28)
		 paramjasper.put("logo",getRealPath(ConfigurationBean.imgpath).substring(0,getRealPath(ConfigurationBean.imgpath).length()-75)+codetab+"//logos.jpg");
		 else paramjasper.put("logo",getRealPath(ConfigurationBean.imgpath).substring(0,getRealPath(ConfigurationBean.imgpath).length()-74)+codetab+"//logos.jpg");
		
	    
		gest.connexionBD();

		JasperPrint jasperPrint = JasperFillManager.fillReport(getRealPath(repertoireJasper+"certificat de scolarite.jasper"),paramjasper, gest.getCon());
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		System.out.println("ok pour le compilation et la generation en pdf");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		/***********************************************************************
		 * Pour afficher une boîte de dialogue pour enregistrer le fichier sous
		 * le nom rapport.pdf
		 **********************************************************************/
		response.addHeader("Content-disposition",
				"attachment;filename=Certificat-de-scolarité"+numerocertificat+".pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();
		System.out.println("ok pour le compilation et la generation en pdf");
	}

/*	public void imprimerlisteEleveInscrits() {
		// TODO Auto-generated method stub
		Repertoire.addMessageerreur("Non encore fonctionnel");
	}*/

	@SuppressWarnings("unchecked")
	public void imprimerlisteEleveInscrits(String classe,String codeetablissement,String logo, String annee) throws JRException, IOException {
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"SAGES Liste des élèves inscrit.jasper");
    	
		paramjasper.put("CLASSE",classe);
		paramjasper.put("CODEETAB",codeetablissement);
		paramjasper.put("ANNEE",annee);
	    //paramjasper.put("LOGO",getRealPath(logo));
		
		gest.connexionBD();

		JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,	paramjasper, gest.getCon());
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		/***********************************************************************
		 * Pour afficher une boîte de dialogue pour enregistrer le fichier sous
		 * le nom rapport.pdf
		 **********************************************************************/
		response.addHeader("Content-disposition",
				"attachment;filename= SAGES-Liste-des-elevess-inscrits-"+classe+".pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();
	
	}
	
	
	
	private String getRealPath(String path){
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);
	}

	public void imprimerConvocation(int idconvocation) {
		// TODO Auto-generated method stub
		Repertoire.addMessageerreur("Non encore fonctionnel");
	}

	public void imprimerCandidature(String codedossier, String codeetab) {
		// TODO Auto-generated method stub
		Repertoire.addMessageerreur("Non encore fonctionnel");
	}


	@SuppressWarnings({ "unchecked"})
	public void imprimerVersements(Date datedebut, Date datefin,
			String codeetab,String annee) throws IOException, JRException {
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String date1= format.format(datedebut);
		String date2= format.format(datefin);
		System.out.println("nouvelle date  "+ date1);
		System.out.println("nouvelle date  "+ date2);
		//datedebut=new Date("d/M/y");
		paramjasper.put("annee",annee);
		
		paramjasper.put("CODEETAB",codeetab);
		
	    paramjasper.put("datedebut",date1);
	    
	    paramjasper.put("datefin",date2);
	    
		gest.connexionBD();

		JasperPrint jasperPrint = JasperFillManager.fillReport(getRealPath(repertoireJasper+"Mon bilan financier.jasper"),paramjasper, gest.getCon());
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		System.out.println("ok pour le compilation et la generation en pdf");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		/***********************************************************************
		 * Pour afficher une boîte de dialogue pour enregistrer le fichier sous
		 * le nom rapport.pdf
		 **********************************************************************/
		response.addHeader("Content-disposition",
				"attachment;filename=SAGES-Bilan-financier-du-"+date2+".pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();
	}
	
	
	@SuppressWarnings({ "unchecked"})
	public void imprimeretatversement(String codeetab,String annee) throws IOException, JRException {
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		//datedebut=new Date("d/M/y");
		paramjasper.put("annee",annee);
		
		paramjasper.put("CODEETAB",codeetab);
	    
		gest.connexionBD();

		JasperPrint jasperPrint = JasperFillManager.fillReport(getRealPath(repertoireJasper+"Etat versement par classe.jasper"),paramjasper, gest.getCon());
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		System.out.println("ok pour le compilation et la generation en pdf");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		/***********************************************************************
		 * Pour afficher une boîte de dialogue pour enregistrer le fichier sous
		 * le nom rapport.pdf
		 **********************************************************************/
		response.addHeader("Content-disposition",
				"attachment;filename=Etat Versement par classe"+annee+".pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();
	}

	
	/**
	 * Export d'un formulaire vierge de notes pour saisie externe
	 * @param codeetablissement
	 * @param nometablissement
	 * @param anneac
	 * @param libelleclasse
	 * @param codeclasse
	 * @param eleves
	 * @param codematiere
	 * @param codesequence
	 * @throws IOException
	 */
	public void exporterFormulaireNotes(String codeetablissement, String nometablissement, String anneeac, String libelleclasse, String codeclasse,List<Eleve> eleves) throws IOException {
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"notes.xls");
		
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(reportPath));

		HSSFWorkbook wb= new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		int numeroeleve=0;
		String prenom="";
		
		// change le name de la sheet
		wb.setSheetName(0, "Notes "+libelleclasse);
		// change le nom de l'etablissement

        HSSFRow row = sheet.getRow(0);
        HSSFCell celletab = row.getCell( 1);
        if (celletab == null)
      	  celletab = row.createCell( 1);

        celletab.setCellType(HSSFCell.CELL_TYPE_STRING);
        celletab.setCellValue(new HSSFRichTextString(nometablissement));


		// change l'année académique
	    HSSFCell cellannee = row.getCell( 5);
			if (cellannee == null)
		      	cellannee = row.createCell( 5);

		      cellannee.setCellType(HSSFCell.CELL_TYPE_STRING);
		      cellannee.setCellValue(new HSSFRichTextString("Année "+anneeac));


		// change le libelle de la classe

		 HSSFRow row2 = sheet.getRow(2);
	     HSSFCell celllibc = row2.getCell( 5);
	     if (celllibc == null)
	        celllibc = row2.createCell(5);
	      
	     celllibc.setCellType(HSSFCell.CELL_TYPE_STRING);
	     celllibc.setCellValue(new HSSFRichTextString(libelleclasse));

		// change le code de la classe

	     HSSFCell cellcodc = row2.getCell( 6);
	     if (cellcodc == null)
	        cellcodc = row2.createCell( 6);
	      
	     cellcodc.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cellcodc.setCellValue(new HSSFRichTextString(codeclasse));

		    
		// enregistre now les matricules des élèves

		HSSFRow rowdata;
	    HSSFCell cellno;
		HSSFCell cellmat;
		HSSFCell cellnom;
		HSSFCell celldate;


		for(int i=0;i<eleves.size();i++){

			rowdata=sheet.getRow(i+5);

			cellno = rowdata.getCell( 1);
			cellmat = rowdata.getCell(2);
			cellnom = rowdata.getCell(3);
			celldate = rowdata.getCell( 4);
			
			if (cellno == null)
		         cellno = row.createCell( 1);
		      
		        cellno.setCellType(HSSFCell.CELL_TYPE_STRING);
		        numeroeleve=i+1;
		        cellno.setCellValue(new HSSFRichTextString(numeroeleve+""));

			if (cellmat == null)
		         cellmat = row.createCell( 2);
		      
		        cellmat.setCellType(HSSFCell.CELL_TYPE_STRING);
		        cellmat.setCellValue(new HSSFRichTextString(eleves.get(i).getMatricule()));

			if (cellnom == null)
		         cellnom = row.createCell(3);
		      
				prenom=eleves.get(i).getPrenom()==null?"":eleves.get(i).getPrenom();
		        cellnom.setCellType(HSSFCell.CELL_TYPE_STRING);
		        cellnom.setCellValue(new HSSFRichTextString(eleves.get(i).getNom()+" "+prenom));

			if (celldate == null)
		         celldate = row.createCell( 4);
		      
		        celldate.setCellType(HSSFCell.CELL_TYPE_STRING);
		        celldate.setCellValue(new HSSFRichTextString(new SimpleDateFormat("dd/MM/yyyy").format(eleves.get(i).getDatenaissance()==null?"":eleves.get(i).getDatenaissance())));


		}
				
				
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		wb.write(outByteStream);
		
		byte [] bytes = outByteStream.toByteArray();

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		
		response.addHeader("Content-disposition","inline;filename=Formulaire-notes-"+libelleclasse+".xls");
		response.setContentType("application/vnd.ms-excel");
		
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.getOutputStream().flush();
		context.responseComplete();


	}

	@SuppressWarnings("unchecked")
	public void imprimerlisteEnseignants(String codeetablissement, String acronymeetablissement) throws JRException, IOException {
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"Liste des enseignants.jasper");
    	
		paramjasper.put("CODEETABLISSEMENT",codeetablissement);
	    //paramjasper.put("LOGO",getRealPath(logo));
		
		gest.connexionBD();

		JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,	paramjasper, gest.getCon());
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		/***********************************************************************
		 * Pour afficher une boîte de dialogue pour enregistrer le fichier sous
		 * le nom rapport.pdf
		 **********************************************************************/
		response.addHeader("Content-disposition",
				"attachment;filename=Liste-Enseignants-"+acronymeetablissement+".pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();
	}

	@SuppressWarnings("unchecked")
	public void imprimerNotesClasse(String codeclasse,String libelleclasse, String codematiere,
			String libellematiere, int numerosequence, String codeetablissement, String annee) throws IOException, JRException {
		
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"SAGES Fiche des notes.jasper");
    	
		paramjasper.put("CODEETABLISSEMENT",codeetablissement);
		paramjasper.put("classe",codeclasse);
		paramjasper.put("matiere",codematiere);
		paramjasper.put("numerosequence",numerosequence);
		paramjasper.put("ANNEE",annee);
	    //paramjasper.put("LOGO",getRealPath(logo));
		
		gest.connexionBD();

		JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,	paramjasper, gest.getCon());
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		/***********************************************************************
		 * Pour afficher une boîte de dialogue pour enregistrer le fichier sous
		 * le nom rapport.pdf
		 **********************************************************************/
		response.addHeader("Content-disposition",
				"attachment;filename=Notes-"+libelleclasse+"-"+libellematiere+"-Seq"+numerosequence+".pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void exporterFormulaireToutesNotes(String codeetab, String annee,String codeclasse) throws IOException, JRException {
		
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"RELEVE DE NOTESV1.jasper");
    	
		paramjasper.put("CODEETAB",codeetab);
		paramjasper.put("CLASSE",codeclasse);
		paramjasper.put("ANNEE",annee);
	    //paramjasper.put("LOGO",getRealPath(logo));
		
		gest.connexionBD();

		JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,	paramjasper, gest.getCon());
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		/***********************************************************************
		 * Pour afficher une boîte de dialogue pour enregistrer le fichier sous
		 * le nom rapport.pdf
		 **********************************************************************/
		response.addHeader("Content-disposition",
				"attachment;filename=RELEVE-DE-NOTES-DE-"+codeclasse+".pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();
		
	}


	@SuppressWarnings("unchecked")
	public void imprimerlisteEleveProvisoire(String codeClasse,String codeetablissement,String annee) throws JRException, IOException {
		
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"SAGES Liste des élèves provisoire.jasper");
    	
		paramjasper.put("CLASSE",codeClasse);
		paramjasper.put("CODEETAB",codeetablissement);
		paramjasper.put("ANNEE",annee);

	    //paramjasper.put("LOGO",getRealPath(logo));
		
		gest.connexionBD();

		JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,	paramjasper, gest.getCon());
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		/***********************************************************************
		 * Pour afficher une boîte de dialogue pour enregistrer le fichier sous
		 * le nom rapport.pdf
		 **********************************************************************/
		response.addHeader("Content-disposition",
				"attachment;filename=SAGES-Liste-des-élèves-provisoire-"+codeClasse+".pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();
	}


	public void imprimerListeEleveProvisoire() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Exporter les notes d'une évaluatioin pour une classe en XLS
	 * @param compositions liste des compositions(note des élèves) enregistrées pour l'évaluation
	 * @param codeetablissement code de l'établissement
	 * @param nometablissement nom de l'établissemnt
	 * @param anneeac année académique
	 * @param libelleclasse libelle de la classe concernée
	 * @param codeclasse code de la classe concernée
	 * @param sequence numéro de la séquence
	 * @param libellematiere libellé de la matière concernée
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void exporterNotesClasseXLS(List<CompositionBean> compositions,String codeetablissement, String nometablissement, String anneeac, String libelleclasse, String codeclasse, int sequence, String libellematiere) throws FileNotFoundException, IOException {

		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"notes.xls");
		
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(reportPath));

		HSSFWorkbook wb= new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		int numeroeleve=0;
		String prenom="";
		
		// change le name de la sheet
		wb.setSheetName(0, "Notes "+libelleclasse+", "+libellematiere+"-séquence "+sequence);
		// change le nom de l'etablissement

        HSSFRow row = sheet.getRow(0);
        HSSFCell celletab = row.getCell( 1);
        if (celletab == null)
      	  celletab = row.createCell( 1);

        celletab.setCellType(HSSFCell.CELL_TYPE_STRING);
        celletab.setCellValue(new HSSFRichTextString(nometablissement));


		// change l'année académique
	    HSSFCell cellannee = row.getCell( 5);
			if (cellannee == null)
		      	cellannee = row.createCell( 5);

		      cellannee.setCellType(HSSFCell.CELL_TYPE_STRING);
		      cellannee.setCellValue(new HSSFRichTextString("Année "+anneeac+" Séq:"+sequence));


		// change le libelle de la classe

		 HSSFRow row2 = sheet.getRow(2);
	     HSSFCell celllibc = row2.getCell( 5);
	     if (celllibc == null)
	        celllibc = row2.createCell(5);
	      
	     celllibc.setCellType(HSSFCell.CELL_TYPE_STRING);
	     celllibc.setCellValue(new HSSFRichTextString(libelleclasse));

		// change le code de la classe

	     HSSFCell cellcodc = row2.getCell( 6);
	     if (cellcodc == null)
	        cellcodc = row2.createCell( 6);
	      
	     cellcodc.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cellcodc.setCellValue(new HSSFRichTextString(codeclasse));

		    
		// enregistre now les matricules des élèves

		HSSFRow rowdata;
	    HSSFCell cellno;
		HSSFCell cellmat;
		HSSFCell cellnom;
		HSSFCell celldate;
		HSSFCell cellnote;


		for(int i=0;i<compositions.size();i++){

			rowdata=sheet.getRow(i+5);

			cellno = rowdata.getCell( 1);
			cellmat = rowdata.getCell(2);
			cellnom = rowdata.getCell(3);
			celldate = rowdata.getCell( 4);
			cellnote = rowdata.getCell( 5);
			
			if (cellno == null)
		         cellno = row.createCell( 1);
		      
		        cellno.setCellType(HSSFCell.CELL_TYPE_STRING);
		        numeroeleve=i+1;
		        cellno.setCellValue(new HSSFRichTextString(numeroeleve+""));

			if (cellmat == null)
		         cellmat = row.createCell( 2);
		      
		        cellmat.setCellType(HSSFCell.CELL_TYPE_STRING);
		        cellmat.setCellValue(new HSSFRichTextString(compositions.get(i).getEleve().getMatricule()));

			if (cellnom == null)
		         cellnom = row.createCell(3);
		      
				prenom=compositions.get(i).getEleve().getPrenom()==null?"":compositions.get(i).getEleve().getPrenom();
		        cellnom.setCellType(HSSFCell.CELL_TYPE_STRING);
		        cellnom.setCellValue(new HSSFRichTextString(compositions.get(i).getEleve().getNom()+" "+prenom));

			if (celldate == null)
		         celldate = row.createCell( 4);
		      
		         celldate.setCellType(HSSFCell.CELL_TYPE_STRING);
		         celldate.setCellValue(new HSSFRichTextString(new SimpleDateFormat("dd/MM/yyyy").format(compositions.get(i).getEleve().getDateNaissance()==null?"":compositions.get(i).getEleve().getDateNaissance())));
		    
		    //Inscription dans le fichier de la note de l'élève
		    if (cellnote == null)
			         cellnote = row.createCell( 5);
			      
			         cellnote.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			         cellnote.setCellValue(compositions.get(i).getNote());


		}
		
		wb.setActiveSheet(0);
				
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		wb.write(outByteStream);
		
		byte [] bytes = outByteStream.toByteArray();

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		
		response.addHeader("Content-disposition","inline;filename=Fichier-notes-"+libelleclasse+"-"+libellematiere+"-seq.xls");
		response.setContentType("application/vnd.ms-excel");
		
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.getOutputStream().flush();
		context.responseComplete();
	}


	@SuppressWarnings("unchecked")
	public void exporterBilanreussite(String codeetab, String annee,
			String codeclasse, int sequence) throws JRException, IOException {
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"Taux reussite matiere new.jasper");
    	
		paramjasper.put("CODEETAB",codeetab);
		paramjasper.put("classe",codeclasse);
		paramjasper.put("annee",annee);
		paramjasper.put("seq",sequence);
	    //paramjasper.put("LOGO",getRealPath(logo));
		
		gest.connexionBD();

		JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,	paramjasper, gest.getCon());
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		/***********************************************************************
		 * Pour afficher une boîte de dialogue pour enregistrer le fichier sous
		 * le nom rapport.pdf
		 **********************************************************************/
		response.addHeader("Content-disposition",
				"attachment;filename=Bilan-Reussite-Matiere-De-"+codeclasse+".pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();
	}
	
	
	@SuppressWarnings("unchecked")
	public void exporterBilanreussiteRecapSequence(String codeetab, String annee,
			String codeclasse, int sequence) throws JRException, IOException {
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"Taux reussite recapitulatif.jasper");
    	
		paramjasper.put("CODEETAB",codeetab);
		paramjasper.put("classe",codeclasse);
		paramjasper.put("annee",annee);
		paramjasper.put("seq",sequence);
	    //paramjasper.put("LOGO",getRealPath(logo));
		
		gest.connexionBD();

		JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,	paramjasper, gest.getCon());
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		/***********************************************************************
		 * Pour afficher une boîte de dialogue pour enregistrer le fichier sous
		 * le nom rapport.pdf
		 **********************************************************************/
		response.addHeader("Content-disposition",
				"attachment;filename=Bilan-Reussite-Matiere-De-"+codeclasse+".pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();
	}

	public int rechercherIndice(List<String> liste, String elt){
		
		for(int i=0; i<liste.size();i++){
			if(liste.get(i).compareTo(elt)==0) return i;
		}
		return 0;
	}
	
	
	public void imprimerRecapsequence(List<Object[]> liste, String libelleclasse, String nometablissement, String anneeac, int sequence, String pays, String deviseetab, String devisepays,int effectif ) throws IOException {
		List<String> matieres=new ArrayList<String>();
		String texte;
		
		String matricule=(String) liste.get(0)[0];
		matieres.add((String) liste.get(0)[4]);
		
		int i=1;
		
		//peuple la liste des matères à partir des infos sur le premier eleve
		while(((String) liste.get(i)[0]).compareTo(matricule)==0 && i<liste.size()){
			
			matieres.add((String) liste.get(i)[4]);
			i++;
		}
		
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"pvrecap.xls");
		
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(reportPath));

		HSSFWorkbook wb= new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		// change le name de la sheet
		wb.setSheetName(0, "PVR "+libelleclasse);
		
		//Inserre le texte de republique
        HSSFRow row = sheet.getRow(0);
        if(row==null)
        	row=sheet.createRow(0);
        
        HSSFCell cell= row.getCell( 0);
        if (cell == null)
      	  cell = row.createCell( 0);

        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        texte=pays==null || pays.compareTo("")==0?"":"République du "+pays;
        cell.setCellValue(new HSSFRichTextString(texte));
        
        //Inserre l'entête du procès verbal
       cell= row.getCell( 3);
        if (cell == null)
      	  cell = row.createCell(3);

        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(new HSSFRichTextString("PROCES VERBAL RECAPITULATIF"));
        
        //Inserre le nom de l'etablissement
        cell = row.getCell( 9);
        if (cell == null)
      	  cell = row.createCell( 9);

        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        texte=nometablissement==null?"":nometablissement;
        cell.setCellValue(new HSSFRichTextString(texte));
        
        
        row = sheet.getRow(1);
        if(row==null)
        	row=sheet.createRow(1);

		// Inserre la devise
	    cell= row.getCell( 0);
		if (cell == null)
	      	cell = row.createCell( 0);
		texte=devisepays==null?"":devisepays;
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(texte));
		
		
		//Inserre la SEQUENCE
		cell= row.getCell( 3);
		if (cell == null)
		cell = row.createCell( 3);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("DE LA SEQUENCE:  "+ String.valueOf(sequence-1)));

		//Inserre la devise de l'érablissemt
		cell= row.getCell( 9);
		if (cell == null)
	      	cell = row.createCell( 9);
		texte=deviseetab==null?"":deviseetab;
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(texte));

		
		row = sheet.getRow(2);
        if(row==null)
        	row=sheet.createRow(2);

		//Inserre la devise de l'établissemt
		cell= row.getCell( 9);
		if (cell == null)
	      	cell = row.createCell( 9);
		texte=libelleclasse==null || libelleclasse.compareTo("")==0?"":"Classe: "+libelleclasse;
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(texte));

		row = sheet.getRow(3);
        if(row==null)
        	row=sheet.createRow(3);

		//Inserre la devise de l'établissemt
		cell= row.getCell( 9);
		if (cell == null)
	      	cell = row.createCell( 9);
		texte=anneeac==null || anneeac.compareTo("")==0?"":"Année: "+anneeac;
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(texte));

	     int j=0, k=0;
	     
	     row = sheet.getRow(4);
		 if(row==null)
	        	row=sheet.createRow(4);
		 
		 cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("N°"));
		 
		 cell = row.getCell(1);
	     if (cell == null)
	        cell = row.createCell(1);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Matricule"));
	     
	     
	     cell = row.getCell(2);
	     if (cell == null)
	        cell = row.createCell(2);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Nom et prénoms"));
	     
    	 while(k<matieres.size()){
    		 cell = row.getCell(3+k);
		     if (cell == null)
		        cell = row.createCell(3+k);
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     cell.setCellValue(new HSSFRichTextString((String) matieres
		    		 .get(k)));
		     
			k++;
    	 }
    	 
    	 //insere les numeros de lignes
    	 int numrow1=5;
    	 int m=0;
	     while(m<effectif){
	    	 row = sheet.getRow(numrow1+m);
			 if(row==null)
		        	row=sheet.createRow(numrow1+m);
			 
			 cell = row.getCell(0);
		     if (cell == null)
		        cell = row.createCell(0);
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     cell.setCellValue(new HSSFRichTextString(String.valueOf(m+1)));
		     m++;
	     }
	     
	     //parcours la liste pour afficher la liste des élèves et leurs notes
	     
	     int numrow=5;
	     while(j<liste.size()){
	    	 row = sheet.getRow(numrow);
			 if(row==null)
		        	row=sheet.createRow(numrow);
		     
	    	 cell = row.getCell(1);
		     if (cell == null)
		        cell = row.createCell(1);
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     cell.setCellValue(new HSSFRichTextString((String) liste.get(j)[0]));
		     
		     cell = row.getCell(2);
		     if (cell == null)
		        cell = row.createCell(2);
		     
		     texte=(String) liste.get(j)[1];
		     texte+=(String) liste.get(j)[2]==null?"":" "+(String) liste.get(j)[2];
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     cell.setCellValue(new HSSFRichTextString(texte));
		     
		     //afficher les notes d'un élève donnée
		     k=0;
	    	 while(k<matieres.size() && (j+k)<liste.size()){
	    		 System.out.println("la taille de la liste est :"+   liste.size());
	    		 System.out.println("la taille de la matiere est :"+   matieres.size());
	    		 int indice= rechercherIndice(matieres,(String)liste.get(j+k)[4]);
	    		 System.out.println("l'indice est du :"+(j+k)+" eme element est "+   indice);
	    		 
	    		 System.out.println("le nom est :  "+     (String)liste.get(j)[1]);
	    		 System.out.println("le compteur est :  "+     k);

	    		//j'avais 3+k a la place de 3+indice
	    		 cell = row.getCell(3+indice);
			     if (cell == null)	 
			     cell = row.createCell(3+indice);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setCellValue( (Double.valueOf((liste.get(j+k)[3]).toString())) );
			     
				k++;
	    	 }
	    	 j+= matieres.size();
	    	 numrow++;
	     }
		
	     
	     //insertion des information sur le bas de page
	     row = sheet.getRow(numrow+1);
		 if(row==null)
	        	row=sheet.createRow(numrow+1);
    	 cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("NB: Veuillez adrésser une requête à la direction des études en cas d'erreur sur vos notes."));
	     
	     row = sheet.getRow(numrow+2);
		 if(row==null)
	        	row=sheet.createRow(numrow+2);
    	 cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Vous disposez de 48h pour toute revendication. Passé ce délai, aucune revendication ne sera prise en compte."));
	     
	     
	     row = sheet.getRow(numrow+4);
		 if(row==null)
	        	row=sheet.createRow(numrow+4);
    	 cell = row.getCell(12);
	     if (cell == null)
	    	 cell = row.createCell(12);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Le principal/proviseur"));
	     
	     row = sheet.getRow(numrow+7);
	     if(row==null)
	        	row=sheet.createRow(numrow+7);
	     cell = row.getCell(12);
	     if (cell == null)
	        cell = row.createCell(12);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Nkoabang le ....../....../....."));
	     
	     row = sheet.getRow(numrow+9);
	     if(row==null)
	        	row=sheet.createRow(numrow+9);
	     cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Légende: hist=Histoire, geog=Geographie, maths= mathématique, infos= Informatique, phys= Physique, chim=Chimie, litt= Littérature, lang=Langue,"));
	     
	     row = sheet.getRow(numrow+10);
	     if(row==null)
	        	row=sheet.createRow(numrow+10);
	     cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("esf=Education sociale et familiale, edci=Education à la citoyenneté, svte= Science de la vie et de la terre,epsp= Education physique et sportive."));
	     
	     
	     
	     wb.setActiveSheet(0);
		
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		wb.write(outByteStream);
		
		byte [] bytes = outByteStream.toByteArray();

		FacesContext  context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		
		response.addHeader("Content-disposition","inline;filename=PVR-"+libelleclasse+"- Seq"+sequence+".xls");
		response.setContentType("application/vnd.ms-excel");
		
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.getOutputStream().flush();
		context.responseComplete();
		
	}
	
	
	
	public void imprimerRecapsequenceM(List<Object[]> liste, String libelleclasse, String nometablissement, String anneeac, int sequence, String pays, String deviseetab, String devisepays,int effectif, long sommecoef ) throws IOException {
		List<String> matieres=new ArrayList<String>();
		String texte;
		
		String matricule=(String) liste.get(0)[0];
		matieres.add((String) liste.get(0)[4]);
		
		int i=1;
		
		//peuple la liste des matères à partir des infos sur le premier eleve
		while(((String) liste.get(i)[0]).compareTo(matricule)==0 && i<liste.size()){
			
			matieres.add((String) liste.get(i)[4]);
			i++;
		}
		
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"pvrecap.xls");
		
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(reportPath));

		HSSFWorkbook wb= new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		// change le name de la sheet
		wb.setSheetName(0, "PVR "+libelleclasse);
		
		//Inserre le texte de republique
        HSSFRow row = sheet.getRow(0);
        if(row==null)
        	row=sheet.createRow(0);
        
        HSSFCell cell= row.getCell( 0);
        if (cell == null)
      	  cell = row.createCell( 0);

        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        texte=pays==null || pays.compareTo("")==0?"":"République du "+pays;
        cell.setCellValue(new HSSFRichTextString(texte));
        
        //Inserre l'entête du procès verbal
       cell= row.getCell( 3);
        if (cell == null)
      	  cell = row.createCell(3);

        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(new HSSFRichTextString("PROCES VERBAL RECAPITULATIF"));
        
        //Inserre le nom de l'etablissement
        cell = row.getCell( 9);
        if (cell == null)
      	  cell = row.createCell( 9);

        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        texte=nometablissement==null?"":nometablissement;
        cell.setCellValue(new HSSFRichTextString(texte));
        
        
        row = sheet.getRow(1);
        if(row==null)
        	row=sheet.createRow(1);

		// Inserre la devise
	    cell= row.getCell( 0);
		if (cell == null)
	      	cell = row.createCell( 0);
		texte=devisepays==null?"":devisepays;
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(texte));
		
		
		//Inserre la SEQUENCE
		cell= row.getCell( 3);
		if (cell == null)
		cell = row.createCell( 3);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("DE LA SEQUENCE:  "+ String.valueOf(sequence-1)));

		//Inserre la devise de l'érablissemt
		cell= row.getCell( 9);
		if (cell == null)
	      	cell = row.createCell( 9);
		texte=deviseetab==null?"":deviseetab;
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(texte));

		
		row = sheet.getRow(2);
        if(row==null)
        	row=sheet.createRow(2);

		//Inserre la devise de l'établissemt
		cell= row.getCell( 9);
		if (cell == null)
	      	cell = row.createCell( 9);
		texte=libelleclasse==null || libelleclasse.compareTo("")==0?"":"Classe: "+libelleclasse;
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(texte));

		row = sheet.getRow(3);
        if(row==null)
        	row=sheet.createRow(3);

		//Inserre la devise de l'établissemt
		cell= row.getCell( 9);
		if (cell == null)
	      	cell = row.createCell( 9);
		texte=anneeac==null || anneeac.compareTo("")==0?"":"Année: "+anneeac;
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(texte));

	     int j=0, k=0;
	     
	     row = sheet.getRow(4);
		 if(row==null)
	        	row=sheet.createRow(4);
		 
		 cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("N°"));
		 
		 cell = row.getCell(1);
	     if (cell == null)
	        cell = row.createCell(1);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Matricule"));
	     
	     
	     cell = row.getCell(2);
	     if (cell == null)
	        cell = row.createCell(2);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Nom et prénoms"));
	     
    	 while(k<matieres.size()){
    		 cell = row.getCell(3+k);
		     if (cell == null)
		        cell = row.createCell(3+k);
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     cell.setCellValue(new HSSFRichTextString((String) matieres
		    		 .get(k)));
		     
			k++;
    	 }
    	 
    	//moyenne
    	 cell = row.getCell(3+matieres.size());
	     if (cell == null)
	        cell = row.createCell(3+matieres.size());
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Moyenne"));
    	 
    	 // coefficient
    	 row = sheet.getRow(5);
		 if(row==null)
	        	row=sheet.createRow(5);
		 
		 cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Coef"));
    	 
    	 // initialisation des coefficient
    	 k=0;
    	 while(k<matieres.size()){
    		 int indice= rechercherIndice(matieres,(String)liste.get(j+k)[4]);

    		 cell = row.getCell(3+indice);
		     if (cell == null)
		        cell = row.createCell(3+indice);
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     Integer coef=Integer.valueOf((liste.get(j+k)[5]).toString());
		     cell.setCellValue( coef );
		     
			k++;
    	 }
    	 
    	 //insere les numeros de lignes
    	 int numrow1=6;
    	 int m=0;
	     while(m<effectif){
	    	 row = sheet.getRow(numrow1+m);
			 if(row==null)
		        	row=sheet.createRow(numrow1+m);
			 
			 cell = row.getCell(0);
		     if (cell == null)
		        cell = row.createCell(0);
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     cell.setCellValue(new HSSFRichTextString(String.valueOf(m+1)));
		     m++;
	     }
	     
	     //parcours la liste pour afficher la liste des élèves et leurs notes
	     
	     int numrow=6;
	     while(j<liste.size()){
	    	 row = sheet.getRow(numrow);
			 if(row==null)
		        	row=sheet.createRow(numrow);
		     
	    	 cell = row.getCell(1);
		     if (cell == null)
		        cell = row.createCell(1);
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     cell.setCellValue(new HSSFRichTextString((String) liste.get(j)[0]));
		     
		     cell = row.getCell(2);
		     if (cell == null)
		        cell = row.createCell(2);
		     
		     texte=(String) liste.get(j)[1];
		     texte+=(String) liste.get(j)[2]==null?"":" "+(String) liste.get(j)[2];
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     cell.setCellValue(new HSSFRichTextString(texte));
   
		     //afficher les notes d'un élève donnée
		     double som=0;
		     k=0;
	    	 while(k<matieres.size() && (j+k)<liste.size()){
	    		 System.out.println("la taille de la liste est :"+   liste.size());
	    		 System.out.println("la taille de la matiere est :"+   matieres.size());
	    		 int indice= rechercherIndice(matieres,(String)liste.get(j+k)[4]);
	    		 System.out.println("l'indice est du :"+(j+k)+" eme element est "+   indice);
	    		 
	    		 System.out.println("le nom est :  "+     (String)liste.get(j)[1]);
	    		 System.out.println("le compteur est :  "+     k);

	    		//j'avais 3+k a la place de 3+indice
	    		 cell = row.getCell(3+indice);
			     if (cell == null)	 
			     cell = row.createCell(3+indice);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     Double note=Double.valueOf((liste.get(j+k)[3]).toString());;
			     cell.setCellValue(note);
			     k++;
					HSSFRow row1 = sheet.getRow(5);
					HSSFCell cell1 = row1.getCell(3+indice);
					som+=note*(cell1.getNumericCellValue());
	    	 }
	    	 
	    	//moyenne
	    	 cell = row.getCell(3+matieres.size());
		     if (cell == null)
		        cell = row.createCell(3+matieres.size());
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     cell.setCellValue( som/sommecoef );
	    	 j+= matieres.size();
	    	 numrow++;
	     }
		
	     
	     //insertion des information sur le bas de page
	     row = sheet.getRow(numrow+1);
		 if(row==null)
	        	row=sheet.createRow(numrow+1);
    	 cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("NB: Veuillez adrésser une requête à la direction des études en cas d'erreur sur vos notes."));
	     
	     row = sheet.getRow(numrow+2);
		 if(row==null)
	        	row=sheet.createRow(numrow+2);
    	 cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Vous disposez de 48h pour toute revendication. Passé ce délai, aucune revendication ne sera prise en compte."));
	     
	     
	     row = sheet.getRow(numrow+4);
		 if(row==null)
	        	row=sheet.createRow(numrow+4);
    	 cell = row.getCell(12);
	     if (cell == null)
	    	 cell = row.createCell(12);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Le principal/proviseur"));
	     
	     row = sheet.getRow(numrow+7);
	     if(row==null)
	        	row=sheet.createRow(numrow+7);
	     cell = row.getCell(12);
	     if (cell == null)
	        cell = row.createCell(12);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Nkoabang le ....../....../....."));
	     
	     row = sheet.getRow(numrow+9);
	     if(row==null)
	        	row=sheet.createRow(numrow+9);
	     cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Légende: hist=Histoire, geog=Geographie, maths= mathématique, infos= Informatique, phys= Physique, chim=Chimie, litt= Littérature, lang=Langue,"));
	     
	     row = sheet.getRow(numrow+10);
	     if(row==null)
	        	row=sheet.createRow(numrow+10);
	     cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("esf=Education sociale et familiale, edci=Education à la citoyenneté, svte= Science de la vie et de la terre,epsp= Education physique et sportive."));
	     
	     
	     
	     wb.setActiveSheet(0);
		
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		wb.write(outByteStream);
		
		byte [] bytes = outByteStream.toByteArray();

		FacesContext  context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		
		response.addHeader("Content-disposition","inline;filename=PVR-"+libelleclasse+"- Seq"+sequence+".xls");
		response.setContentType("application/vnd.ms-excel");
		
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.getOutputStream().flush();
		context.responseComplete();
		
	}
	
	public void imprimerRecapTrim(List<Object[]> liste,List<Object[]> liste1, List<Object[]> liste2,  String libelleclasse, String nometablissement, String anneeac, int sequence, String pays, String deviseetab, String devisepays,int effectif ) throws IOException {
		List<String> matieres=new ArrayList<String>();
		String texte;
		
		String matricule=(String) liste.get(0)[0];
		matieres.add((String) liste.get(0)[4]);
		
		System.out.println("l'effectif des eleves de la classe est :"+   effectif);
		
		System.out.println("la classe de  :"+   libelleclasse);
		
		int i=1;
		
		//peuple la liste des matères à partir des infos sur le premier eleve
		while(((String) liste.get(i)[0]).compareTo(matricule)==0 && i<liste.size()){
			
			matieres.add((String) liste.get(i)[4]);
			i++;
		}
		
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"pvrecap.xls");
		
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(reportPath));

		HSSFWorkbook wb= new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		// change le name de la sheet
		wb.setSheetName(0, "PVR "+libelleclasse);
		
		//Inserre le texte de republique
        HSSFRow row = sheet.getRow(0);
        if(row==null)
        	row=sheet.createRow(0);
        
        HSSFCell cell= row.getCell( 0);
        if (cell == null)
      	  cell = row.createCell( 0);

        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        texte=pays==null || pays.compareTo("")==0?"":"République du "+pays;
        cell.setCellValue(new HSSFRichTextString(texte));
        
        //Inserre l'entête du procès verbal
       cell= row.getCell( 3);
        if (cell == null)
      	  cell = row.createCell(3);

        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(new HSSFRichTextString("PROCES VERBAL RECAPITULATIF"));
        
        //Inserre le nom de l'etablissement
        cell = row.getCell( 9);
        if (cell == null)
      	  cell = row.createCell( 9);

        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        texte=nometablissement==null?"":nometablissement;
        cell.setCellValue(new HSSFRichTextString(texte));
        
        
        row = sheet.getRow(1);
        if(row==null)
        	row=sheet.createRow(1);

		// Inserre la devise du pays
	    cell= row.getCell( 0);
		if (cell == null)
	      	cell = row.createCell( 0);
		texte=devisepays==null?"":devisepays;
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(texte));
		
		
		//Inserre le trimestre
		cell= row.getCell( 3);
		if (cell == null)
		cell = row.createCell( 3);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("DU TRIMESTRE:  "+ String.valueOf(sequence)));

		//Inserre la devise de l'établissemt
		cell= row.getCell( 9);
		if (cell == null)
	      	cell = row.createCell( 9);
		texte=deviseetab==null?"":deviseetab;
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(texte));

		
		row = sheet.getRow(2);
        if(row==null)
        	row=sheet.createRow(2);

		//Inserre le libelle de la classe
		cell= row.getCell( 9);
		if (cell == null)
	      	cell = row.createCell( 9);
		texte=libelleclasse==null || libelleclasse.compareTo("")==0?"":"Classe: "+libelleclasse;
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(texte));

		row = sheet.getRow(3);
        if(row==null)
        	row=sheet.createRow(3);

		//Inserre l'année academique
		cell= row.getCell( 9);
		if (cell == null)
	      	cell = row.createCell( 9);
		texte=anneeac==null || anneeac.compareTo("")==0?"":"Année: "+anneeac;
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(texte));

	     int j=0, k=0;
	     
	     row = sheet.getRow(4);
		 if(row==null)
	        	row=sheet.createRow(4);
		 
		 cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("N°"));
		 
		 cell = row.getCell(1);
	     if (cell == null)
	        cell = row.createCell(1);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Matricule"));
	     
	     
	     cell = row.getCell(2);
	     if (cell == null)
	        cell = row.createCell(2);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Nom et prénoms"));
	     
    	 while(k<matieres.size()){
    		 cell = row.getCell(3+k);
		     if (cell == null)
		        cell = row.createCell(3+k);
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     cell.setCellValue(new HSSFRichTextString((String) matieres
		    		 .get(k)));
		     
			k++;
    	 }
    	 
    	 int numrow1=5;
    	 int m=0;
	     while(m<effectif){
	    	 row = sheet.getRow(numrow1+m);
			 if(row==null)
		        	row=sheet.createRow(numrow1+m);
			 
			 cell = row.getCell(0);
		     if (cell == null)
		        cell = row.createCell(0);
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     cell.setCellValue(new HSSFRichTextString(String.valueOf(m+1)));
		     m++;
	     }
	     
	     //remplissage des noms et prenoms
	     int numrow=5;
	     while(j<liste.size()){
	    	 row = sheet.getRow(numrow);
			 if(row==null)
		        	row=sheet.createRow(numrow);
		     
	    	 cell = row.getCell(1);
		     if (cell == null)
		        cell = row.createCell(1);
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     cell.setCellValue(new HSSFRichTextString((String) liste.get(j)[0]));
		     
		     cell = row.getCell(2);
		     if (cell == null)
		        cell = row.createCell(2);
		     
		     texte=(String) liste.get(j)[1];
		     texte+=(String) liste.get(j)[2]==null?"":" "+(String) liste.get(j)[2];
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     cell.setCellValue(new HSSFRichTextString(texte));
		    
		     //remplissage des notes de chaque matiere
		     k=0;
	    	 while(k<matieres.size()){
	    		 System.out.println("la taille de la liste est :"+   liste.size());
	    		 System.out.println("la taille de la matiere est :"+   matieres.size());
	    		 int indice= rechercherIndice(matieres,(String)liste.get(j+k)[4]);
	    		 System.out.println("l'indice est du :"+(j+k)+" eme element est "+   indice);
	    		 
	    		 System.out.println("le nom est :  "+     (String)liste.get(j)[1]);
	    		 System.out.println("le compteur est :  "+     k);
	    		// int indice= rechercherIndice(matieres,(String)liste.get(j+k)[4]);
		    		//j'avais 3+k a la place de 3+indice
		    		// cell = row.getCell(3+indice);
	    		 cell = row.getCell(3+indice);
			     if (cell == null)
			        cell = row.createCell(3+indice);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     Double note1=Double.valueOf((liste.get(j+k)[3]).toString());
			     Double note2=Double.valueOf((liste1.get(j+k)[3]).toString());
			     Double note3=Double.valueOf((liste2.get(j+k)[3]).toString());
			     Double moy =(note1+note2+note3)/3;
			     cell.setCellValue( moy );
			     
				k++;
	    	 }
	    	 j+= matieres.size();
	    	 numrow++;
	     }
		
	     row = sheet.getRow(numrow+1);
		 if(row==null)
	        	row=sheet.createRow(numrow+1);
    	 cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("NB: Veuillez adrésser une requête à la direction des études en cas d'erreur sur vos notes."));
	     
	     row = sheet.getRow(numrow+2);
		 if(row==null)
	        	row=sheet.createRow(numrow+2);
    	 cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Vous disposez de 48h pour toute revendication. Passé ce délai, aucune revendication ne sera prise en compte."));
	     
	     
	     row = sheet.getRow(numrow+4);
		 if(row==null)
	        	row=sheet.createRow(numrow+4);
    	 cell = row.getCell(12);
	     if (cell == null)
	    	 cell = row.createCell(12);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Le principal/proviseur"));
	     
	     row = sheet.getRow(numrow+7);
	     if(row==null)
	        	row=sheet.createRow(numrow+7);
	     cell = row.getCell(12);
	     if (cell == null)
	        cell = row.createCell(12);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Nkoabang le ....../....../....."));
	     
	     row = sheet.getRow(numrow+9);
	     if(row==null)
	        	row=sheet.createRow(numrow+9);
	     cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Légende: hist=Histoire, geog=Geographie, maths= mathématique, infos= Informatique, phys= Physique, chim=Chimie, litt= Littérature, lang=Langue,"));
	     
	     row = sheet.getRow(numrow+10);
	     if(row==null)
	        	row=sheet.createRow(numrow+10);
	     cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("esf=Education sociale et familiale, edci=Education à la citoyenneté, svte= Science de la vie et de la terre,epsp= Education physique et sportive."));
	     
	     
	     
	     wb.setActiveSheet(0);
		
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		wb.write(outByteStream);
		
		byte [] bytes = outByteStream.toByteArray();

		FacesContext  context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		
		response.addHeader("Content-disposition","inline;filename=PVR-"+libelleclasse+"- Seq"+sequence+".xls");
		response.setContentType("application/vnd.ms-excel");
		
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.getOutputStream().flush();
		context.responseComplete();
		
	}
	
	public void imprimerRecapTrimM(List<Object[]> liste,List<Object[]> liste1, List<Object[]> liste2,  String libelleclasse, String nometablissement, String anneeac, int sequence, String pays, String deviseetab, String devisepays,int effectif,long sommecoef ) throws IOException {
		List<String> matieres=new ArrayList<String>();
		String texte;
		
		String matricule=(String) liste.get(0)[0];
		matieres.add((String) liste.get(0)[4]);
		
		int i=1;
		
		//peuple la liste des matères à partir des infos sur le premier eleve
		while(((String) liste.get(i)[0]).compareTo(matricule)==0 && i<liste.size()){
			
			matieres.add((String) liste.get(i)[4]);
			i++;
		}
		
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"pvrecap.xls");
		
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(reportPath));

		HSSFWorkbook wb= new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		// change le name de la sheet
		wb.setSheetName(0, "PVR "+libelleclasse);
		
		//Inserre le texte de republique
        HSSFRow row = sheet.getRow(0);
        if(row==null)
        	row=sheet.createRow(0);
        
        HSSFCell cell= row.getCell( 0);
        if (cell == null)
      	  cell = row.createCell( 0);

        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        texte=pays==null || pays.compareTo("")==0?"":"République du "+pays;
        cell.setCellValue(new HSSFRichTextString(texte));
        
        //Inserre l'entête du procès verbal
       cell= row.getCell( 3);
        if (cell == null)
      	  cell = row.createCell(3);

        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(new HSSFRichTextString("PROCES VERBAL RECAPITULATIF"));
        
        //Inserre le nom de l'etablissement
        cell = row.getCell( 9);
        if (cell == null)
      	  cell = row.createCell( 9);

        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        texte=nometablissement==null?"":nometablissement;
        cell.setCellValue(new HSSFRichTextString(texte));
        
        
        row = sheet.getRow(1);
        if(row==null)
        	row=sheet.createRow(1);

		// Inserre la devise du pays
	    cell= row.getCell( 0);
		if (cell == null)
	      	cell = row.createCell( 0);
		texte=devisepays==null?"":devisepays;
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(texte));
		
		
		//Inserre le trimestre
		cell= row.getCell( 3);
		if (cell == null)
		cell = row.createCell( 3);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("DU TRIMESTRE:  "+ String.valueOf(sequence)));

		//Inserre la devise de l'établissemt
		cell= row.getCell( 9);
		if (cell == null)
	      	cell = row.createCell( 9);
		texte=deviseetab==null?"":deviseetab;
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(texte));

		
		row = sheet.getRow(2);
        if(row==null)
        	row=sheet.createRow(2);

		//Inserre le libelle de la classe
		cell= row.getCell( 9);
		if (cell == null)
	      	cell = row.createCell( 9);
		texte=libelleclasse==null || libelleclasse.compareTo("")==0?"":"Classe: "+libelleclasse;
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(texte));

		row = sheet.getRow(3);
        if(row==null)
        	row=sheet.createRow(3);

		//Inserre l'année academique
		cell= row.getCell( 9);
		if (cell == null)
	      	cell = row.createCell( 9);
		texte=anneeac==null || anneeac.compareTo("")==0?"":"Année: "+anneeac;
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(texte));

	     int j=0, k=0;
	     
	     row = sheet.getRow(4);
		 if(row==null)
	        	row=sheet.createRow(4);
		 
		 cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("N°"));
		 
		 cell = row.getCell(1);
	     if (cell == null)
	        cell = row.createCell(1);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Matricule"));
	     
	     
	     cell = row.getCell(2);
	     if (cell == null)
	        cell = row.createCell(2);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Nom et prénoms"));
	     
    	 while(k<matieres.size()){
    		 cell = row.getCell(3+k);
		     if (cell == null)
		        cell = row.createCell(3+k);
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     cell.setCellValue(new HSSFRichTextString((String) matieres
		    		 .get(k)));
		     
			k++;
    	 }
    	 
    	 
    	 //moyenne
    	 cell = row.getCell(3+matieres.size());
	     if (cell == null)
	        cell = row.createCell(3+matieres.size());
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Moyenne"));
    	 
    	 // coefficient
    	 row = sheet.getRow(5);
		 if(row==null)
	        	row=sheet.createRow(5);
		 
		 cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Coef"));
    	 
    	 // initialisation des coefficient
    	 k=0;
    	 while(k<matieres.size()){
    		 int indice= rechercherIndice(matieres,(String)liste.get(j+k)[4]);

    		 cell = row.getCell(3+indice);
		     if (cell == null)
		        cell = row.createCell(3+indice);
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     Integer coef=Integer.valueOf((liste.get(j+k)[5]).toString());
		     cell.setCellValue( coef );
		     
			k++;
    	 }
    	 
    	 //numero des eleves
    	 int numrow1=6;
    	 int m=0;
	     while(m<effectif){
	    	 row = sheet.getRow(numrow1+m);
			 if(row==null)
		        	row=sheet.createRow(numrow1+m);
			 
			 cell = row.getCell(0);
		     if (cell == null)
		        cell = row.createCell(0);
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     cell.setCellValue(new HSSFRichTextString(String.valueOf(m+1)));
		     m++;
	     }
	     
	     //remplissage des noms et prenoms
	     int numrow=6;
	     while(j<liste.size()){
	    	 row = sheet.getRow(numrow);
			 if(row==null)
		        	row=sheet.createRow(numrow);
		     
	    	 cell = row.getCell(1);
		     if (cell == null)
		        cell = row.createCell(1);
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     cell.setCellValue(new HSSFRichTextString((String) liste.get(j)[0]));
		     
		     cell = row.getCell(2);
		     if (cell == null)
		        cell = row.createCell(2);
		     
		     texte=(String) liste.get(j)[1];
		     texte+=(String) liste.get(j)[2]==null?"":" "+(String) liste.get(j)[2];
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     cell.setCellValue(new HSSFRichTextString(texte));
		    
		     //remplissage des notes de chaque matiere
		     double som=0;
		     double moy=0;
		     k=0;
	    	 while(k<matieres.size()){
	    		
	    		 int indice= rechercherIndice(matieres,(String)liste.get(j+k)[4]);

	    		 cell = row.getCell(3+indice);
			     if (cell == null)
			        cell = row.createCell(3+indice);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     Double note1=Double.valueOf((liste.get(j+k)[3]).toString());
			     Double note2=Double.valueOf((liste1.get(j+k)[3]).toString());
			     Double note3=Double.valueOf((liste2.get(j+k)[3]).toString());
			         moy =(note1+note2+note3)/3;
			     cell.setCellValue( moy );
			     
				k++;
				HSSFRow row1 = sheet.getRow(5);
				HSSFCell cell1 = row1.getCell(3+indice);
				som+=moy*(cell1.getNumericCellValue());
	    	 }
	    	//moyenne
	    	 cell = row.getCell(3+matieres.size());
		     if (cell == null)
		        cell = row.createCell(3+matieres.size());
		     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		     cell.setCellValue( som/sommecoef );
	    	 
	    	 j+= matieres.size();
	    	 numrow++;
	     }
		
	     row = sheet.getRow(numrow+1);
		 if(row==null)
	        	row=sheet.createRow(numrow+1);
    	 cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("NB: Veuillez adrésser une requête à la direction des études en cas d'erreur sur vos notes."));
	     
	     row = sheet.getRow(numrow+2);
		 if(row==null)
	        	row=sheet.createRow(numrow+2);
    	 cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Vous disposez de 48h pour toute revendication. Passé ce délai, aucune revendication ne sera prise en compte."));
	     
	     
	     row = sheet.getRow(numrow+4);
		 if(row==null)
	        	row=sheet.createRow(numrow+4);
    	 cell = row.getCell(12);
	     if (cell == null)
	    	 cell = row.createCell(12);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Le principal/proviseur"));
	     
	     row = sheet.getRow(numrow+7);
	     if(row==null)
	        	row=sheet.createRow(numrow+7);
	     cell = row.getCell(12);
	     if (cell == null)
	        cell = row.createCell(12);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Nkoabang le ....../....../....."));
	     
	     row = sheet.getRow(numrow+9);
	     if(row==null)
	        	row=sheet.createRow(numrow+9);
	     cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("Légende: hist=Histoire, geog=Geographie, maths= mathématique, infos= Informatique, phys= Physique, chim=Chimie, litt= Littérature, lang=Langue,"));
	     
	     row = sheet.getRow(numrow+10);
	     if(row==null)
	        	row=sheet.createRow(numrow+10);
	     cell = row.getCell(0);
	     if (cell == null)
	        cell = row.createCell(0);
	     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	     cell.setCellValue(new HSSFRichTextString("esf=Education sociale et familiale, edci=Education à la citoyenneté, svte= Science de la vie et de la terre,epsp= Education physique et sportive."));
	     
	     
	     
	     wb.setActiveSheet(0);
		
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		wb.write(outByteStream);
		
		byte [] bytes = outByteStream.toByteArray();

		FacesContext  context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		
		response.addHeader("Content-disposition","inline;filename=PVR-"+libelleclasse+"- Seq"+sequence+".xls");
		response.setContentType("application/vnd.ms-excel");
		
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.getOutputStream().flush();
		context.responseComplete();
		
	}


	@SuppressWarnings("unchecked")
	public void exporterBilanreussiteT(String codeetab, String annee,
			String codeclasse, int trimestre) throws JRException, IOException {
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"Taux reussite matiereT.jasper");
    	
		paramjasper.put("CODEETAB",codeetab);
		paramjasper.put("classe",codeclasse);
		paramjasper.put("annee",annee);
		paramjasper.put("seq",trimestre);
	    //paramjasper.put("LOGO",getRealPath(logo));
		
		gest.connexionBD();

		JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,	paramjasper, gest.getCon());
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		/***********************************************************************
		 * Pour afficher une boîte de dialogue pour enregistrer le fichier sous
		 * le nom rapport.pdf
		 **********************************************************************/
		response.addHeader("Content-disposition",
				"attachment;filename=Bilan-Reussite-Matiere-De-"+codeclasse+".pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();
		
	}


	@SuppressWarnings("unchecked")
	public void imprimerBilanPDFAnnuel(Service service,
			String codeetablissement, String codeclasse, List<Integer> eleves,
			String logo, int rechercherModeleBulletin) throws JRException, IOException {
		
		@SuppressWarnings("rawtypes")
		HashMap paramjasper = new HashMap();
		String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(repertoireJasper+"SAGES Liste des élèves et moyenne.jasper");
    	
		paramjasper.put("CODEETAB",codeetablissement);
		paramjasper.put("CLASSE",codeclasse);
		paramjasper.put("ANNEE",service.getAnneeEncours());
	    paramjasper.put("LOGO",getRealPath(logo));
		
		gest.connexionBD();

		JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,	paramjasper, gest.getCon());
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		/***********************************************************************
		 * Pour afficher une boîte de dialogue pour enregistrer le fichier sous
		 * le nom rapport.pdf
		 **********************************************************************/
		response.addHeader("Content-disposition",
				"attachment;filename=Bilan-Bulletins-De-"+codeclasse+".pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();
		
	}



}
