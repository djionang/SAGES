package ages.beans.etablissement.note;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.sf.jasperreports.engine.JRException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.model.UploadedFile;

import utils.OperationResults;
import utils.Repertoire;
import utils.Service;
import ages.beans.GenericBean;
import ages.beans.etablissement.evaluation.EvaluationBean;
import ages.exception.ElementNOtFoundException;

@ManagedBean(name="notesBean")
@ViewScoped
public class NotesBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
		
	private int codeevaluation;
	
	private EvaluationBean evaluation;
	
	private List<CompositionBean> compositions;
	
	private UploadedFile fichierImport;
	
	public boolean notesEnregistrees;
	
	public NotesBean() {
	}
	

	public void setService(Service service) {
		this.service = service;
	}


	/**
	 * @return the compositions
	 */
	public List<CompositionBean> getCompositions() {
		return compositions;
	}



	/**
	 * @param compositions the compositions to set
	 */
	public void setCompositions(List<CompositionBean> compositions) {
		this.compositions = compositions;
	}


	/**
	 * @return the evaluation
	 */
	public EvaluationBean getEvaluation() {
		return evaluation;
	}


	/**
	 * @param evaluation the evaluation to set
	 */
	public void setEvaluation(EvaluationBean evaluation) {
		this.evaluation = evaluation;
	}


	/**
	 * @return the codeevaluation
	 */
	public int getCodeevaluation() {
		return codeevaluation;
	}


	/**
	 * @param codeevaluation the codeevaluation to set
	 */
	public void setCodeevaluation(int codeevaluation) {
		this.codeevaluation = codeevaluation;
	}

	/**
	 * @return the fichierImport
	 */
	public UploadedFile getFichierImport() {
		return fichierImport;
	}


	/**
	 * @param fichierImport the fichierImport to set
	 */
	public void setFichierImport(UploadedFile fichierImport) {
		this.fichierImport = fichierImport;
	}


	/**
	 * @return the notesEnregistrees
	 */
	public boolean isNotesEnregistrees() {
		return notesEnregistrees;
	}


	/**
	 * @param notesEnregistrees the notesEnregistrees to set
	 */
	public void setNotesEnregistrees(boolean notesEnregistrees) {
		this.notesEnregistrees = notesEnregistrees;
	}


	public void initialize(){
		if(codeevaluation!=0){
			this.service.initNotes(this);
		}
	}
	
	
	/**
	 * Enregistre les notes pour l'évaluation passée en paramètre
	 * @return
	 */
	public String enregistrerNotes(){
		try {
		
		String result=this.service.enregistrerNotes(compositions,codeevaluation);
		
		if(result.compareTo("ECHEC")==0){
			Repertoire.addMessageerreur("vous avez entrez une ou plusieurs note(s) qui n'est (sont) pas comprise en zero et 20");
			return null;
		}
		
		} catch (Exception e) {
			if(e.getCause()!=null)
				if(e.getCause().getClass().equals(ElementNOtFoundException.class)){
					Repertoire.addMessageerreur("Evaluation ou Eleve non trouvé");
				}
				else
					Repertoire.addMessageerreur("Erreur innatendue, veuillez contacter l'administrateur");
			else
				Repertoire.addMessageerreur("Erreur innatendue, veuillez contacter l'administrateur");
			Repertoire.logError(" Erreur lors de l'enregistrement des notes", getClass(), e);
			return null;
		}
		Repertoire.addMessageinfo("Enregistrement validé");
		return null;
	}
	
	
	/**
	 * Enregistrer des notes a partir de fichier excel (.xls) importés
	 * @return la chaine de navigation pour la suite des opérations
	 */
	public String enregistrerImport(){
		if(fichierImport!=null && fichierImport.getSize()!=0){
			
			ByteArrayInputStream stream=new ByteArrayInputStream(fichierImport.getContents());
			
			try {
			      POIFSFileSystem fs = new POIFSFileSystem(stream);
			      HSSFWorkbook wb = new HSSFWorkbook(fs);
			      HSSFSheet sheet = wb.getSheetAt(0);
			      HSSFRow row = null;
			      HSSFCell cellmat = null;
				  HSSFCell cellnote = null;
				  int numcell=0;
			      
				  //recupere tous les eleves figurant dans le fichier importé
			      for (Iterator<Row> rowIt = sheet.rowIterator(); rowIt.hasNext();) {
			    	  
			    	  if(numcell>=4){
			    		  row = (HSSFRow) rowIt.next();
					        cellnote = row.getCell(5);

							cellmat = row.getCell( 2);
							if(cellmat!=null && cellnote!=null){
								
								// nous verifions ici si la note est comprise dans la fourchette voulue
								if((float)cellnote.getNumericCellValue()>20 || (float)cellnote.getNumericCellValue()<0){
								Repertoire.addMessageerreur("vous avez entrez une ou plusieurs note(s) qui n'est (sont) pas comprise en zero et 20");
								return "";
								}
								//fonction qui take le matricule et la note dun eleve pour mettre dans le bean
								insererNoteEleve(cellmat.getStringCellValue(),(float)cellnote.getNumericCellValue());
							}
							else{
								if(cellmat!=null && cellnote==null){
									
									//fonction qui take le matricule et la note dun eleve pour mette dans le bean
									insererNoteEleve(cellmat.getStringCellValue(),(float) 0);
								}
							}
			    	  }
			    	  else{
			    		  numcell++;
			    		  rowIt.next();
			    	  }
			    		  
			       
			      }// End for
			      
			      
			    } catch (FileNotFoundException e) {
			      e.printStackTrace();
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
			try {
				this.service.enregistrerNotes(compositions, codeevaluation);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Repertoire.addMessageinfo("Notes enregistrées");
		return OperationResults.navWithParam("visualiserNotes", "codeevaluation", String.valueOf(codeevaluation));
	}
	
	
	private void insererNoteEleve(String matricule,	float note) {
		for(int i=0;i<compositions.size();i++){
			if(compositions.get(i).getEleve().getMatricule().compareTo(matricule)==0){
				compositions.get(i).setNote(note);
				break;
			}
		}
	}


	public void initializeimport(){
		if(codeevaluation!=0){
			this.service.initNotesImport(this);
		}
	}
	
	public String exporterPDF(){
		if(codeevaluation!=0){
			try {
				this.service.imprimerNotesClasse(codeevaluation);
			} catch (IOException e) {
				Repertoire.addMessageerreur("Une erreur est survenue lors de l'impresson",e);
				e.printStackTrace();
			} catch (JRException e) {
				Repertoire.addMessageerreur("Une erreur est survenue lors de l'impresson",e);
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Exporter les notes de l'évaluation en XLS
	 * @return
	 */
	public String exporterXLS(){
		try {
			this.service.exporterNotesClasseXLS(compositions,evaluation.getCodeclasse(),evaluation.getCodematiere(),evaluation.getCodesequence());
		} catch (FileNotFoundException e) {
			Repertoire.addMessageerreur("Une erreur est survenue lors de l'exportation",e);
			e.printStackTrace();
		} catch (IOException e) {
			Repertoire.addMessageerreur("Une erreur est survenue lors de l'exportation",e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Fonction de redirection vers la page d'enregistrement des notes à la main(saisie)
	 * @return
	 */
	public String modifierNotes(){
		return OperationResults.navWithParam("enregistrerNoteSaisie", "codeevaluation", String.valueOf(codeevaluation));
		
	}
	
	
	/**
	 * Fonction de redirection vers la page d'importation des notes via fichier excel
	 * @return
	 */
	public String importerXLS(){
		return OperationResults.navWithParam("enregistrerNoteimport", "codeevaluation", String.valueOf(codeevaluation));
	}
	
	
	/**
	 * Fonction de redirection vers la page de visualisation des notes
	 * @return
	 */
	public String visualiserNotes(){
		return OperationResults.navWithParam("visualiserNotes", "codeevaluation", String.valueOf(codeevaluation));
	}
	
}
