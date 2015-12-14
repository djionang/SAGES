package ages.beans.inscription;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import utils.Repertoire;
/**
 * Utilisé pour rendre le table de versement
 * @author Administrateur
 *
 */
public class VersementDataModel extends ListDataModel<VersementBean> implements  Serializable{

	private static final long serialVersionUID = 1L;

		public VersementDataModel() {  
	    }  
	  
	    public VersementDataModel(List<VersementBean> data) {  
	        super(data);  
	    }
	       
	    @SuppressWarnings("unchecked")
		public VersementBean getRowData(String rowKey) {  
	        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
	          
	    	 Repertoire.logDebug("--------------------RRRR", getClass());
		     Repertoire.logDebug("--------------------RRRR", getClass());
		     Repertoire.logDebug("--------------------RRRR", getClass());
		     Repertoire.logDebug("--------------------RRRR", getClass());
		     Repertoire.logDebug("--------------------RRRR", getClass());
		     Repertoire.logDebug("----------key===----------"+rowKey, getClass());
	    	
	    	List<VersementBean> vers = (List<VersementBean>) getWrappedData();  
	          
	        for(VersementBean ver : vers) {  
	            if(ver.getId().equals(rowKey))  
	                return ver;  
	        }  
	          
	        return null;  
	    }  
	  
	    public Object getRowKey(VersementBean ver) {  
	        return ver.getId();  
	    }  
	}  
	   

