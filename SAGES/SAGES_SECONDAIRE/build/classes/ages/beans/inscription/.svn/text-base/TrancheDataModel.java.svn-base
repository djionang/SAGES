package ages.beans.inscription;

import java.io.Serializable;
import java.util.List;  
import javax.faces.model.ListDataModel; 
import org.primefaces.model.SelectableDataModel; 

import utils.Repertoire;

/**
 * Utilisé pour rendre le table d'eleves selectionnable par lignes
 * @author Administrateur
 *
 */
public class TrancheDataModel extends ListDataModel<TrancheBean> implements SelectableDataModel<TrancheBean>, Serializable{

	private static final long serialVersionUID = 1L;

		public TrancheDataModel() {  
	    }  
	  
	    public TrancheDataModel(List<TrancheBean> data) {  
	        super(data);  
	    }  
	      
	    @SuppressWarnings("unchecked")
		@Override  
	    public TrancheBean getRowData(String rowKey) {  
	        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("----------key===----------"+rowKey, getClass());
	        
	        List<TrancheBean> tranches = (List<TrancheBean>) getWrappedData();  
	          
	        for(TrancheBean tranche : tranches) {  
	            if(String.valueOf(tranche.getId()).equals(rowKey))  
	                return tranche;  
	        }  
	          
	        return null;  
	    }  	  
	    

		@Override
		public Object getRowKey(TrancheBean tranche) {
			//return String.valueOf(tranche.getId());
			return tranche.getType();
		}  
	}  
	   

