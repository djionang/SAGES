package ages.beans.statistique;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import utils.Repertoire;

public class BilanAnnuelDataModel extends ListDataModel<BilanAnBean> implements SelectableDataModel<BilanAnBean>, Serializable{

	private static final long serialVersionUID = 1L;

	public BilanAnnuelDataModel() {  
    }
	
	 public BilanAnnuelDataModel(List<BilanAnBean> data) {  
	        super(data);  
	    }  
	      
	    @SuppressWarnings("unchecked")
		@Override  
	    public BilanAnBean getRowData(String rowKey) {  
	        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("----------key===----------"+rowKey, getClass());
	        
	        List<BilanAnBean> bilans = (List<BilanAnBean>) getWrappedData();  
	          
	        for(BilanAnBean bilan : bilans) {  
	            if(String.valueOf(bilan.getCodeclasse()).equals(rowKey))  
	                return bilan;  
	        }  
	          
	        return null;  
	    }  	  

		@Override
		public Object getRowKey(BilanAnBean bilan) {
			return null;
		}  

}
