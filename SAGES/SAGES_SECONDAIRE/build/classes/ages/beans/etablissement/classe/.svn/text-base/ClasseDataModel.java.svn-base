package ages.beans.etablissement.classe;

import java.io.Serializable;
import java.util.List;  
import javax.faces.model.ListDataModel; 
import org.primefaces.model.SelectableDataModel; 

/**
 * Utilisé pour rendre le table d'eleves selectionnable par lignes
 * @author Administrateur
 *
 */
public class ClasseDataModel extends ListDataModel<ClasseBean> implements SelectableDataModel<ClasseBean>, Serializable{

	private static final long serialVersionUID = 1L;

		public ClasseDataModel() {  
	    }  
	  
	    public ClasseDataModel(List<ClasseBean> data) {  
	        super(data);  
	    }  
	      
	    @SuppressWarnings("unchecked")
		@Override  
	    public ClasseBean getRowData(String rowKey) {  
	        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
	          
	        List<ClasseBean> classes = (List<ClasseBean>) getWrappedData();  
	          
	        for(ClasseBean classe : classes) {  
	            if(classe.getCodeClasse().equals(rowKey))  
	                return classe;  
	        }  
	          
	        return null;  
	    }  
	  
	    @Override  
	    public Object getRowKey(ClasseBean classe) {  
	        return classe.getCodeClasse();  
	    }  
	}  
	   

