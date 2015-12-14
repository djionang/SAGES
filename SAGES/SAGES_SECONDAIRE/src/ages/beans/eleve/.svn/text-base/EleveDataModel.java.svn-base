package ages.beans.eleve;

import java.io.Serializable;
import java.util.List;  
import javax.faces.model.ListDataModel; 
import org.primefaces.model.SelectableDataModel; 
/**
 * Utilisé pour rendre le table d'eleves selectionnable par lignes
 * @author Administrateur
 *
 */
public class EleveDataModel extends ListDataModel<EleveBean> implements SelectableDataModel<EleveBean>, Serializable{

	private static final long serialVersionUID = 1L;

		public EleveDataModel() {  
	    }  
	  
	    public EleveDataModel(List<EleveBean> data) {  
	        super(data);  
	    }
	       
	    @SuppressWarnings("unchecked")
		@Override  
	    public EleveBean getRowData(String rowKey) {  
	        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
	          
	        List<EleveBean> eleves = (List<EleveBean>) getWrappedData();  
	          
	        for(EleveBean eleve : eleves) {  
	            if(eleve.getMatricule().equals(rowKey))  
	                return eleve;  
	        }  
	          
	        return null;  
	    }  
	  
	    @Override  
	    public Object getRowKey(EleveBean eleve) {  
	        return eleve.getMatricule();  
	    }  
	}  
	   

