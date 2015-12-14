package ages.beans.statistique;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import utils.Repertoire;

public class BilanDataModel extends ListDataModel<BilanBean> implements SelectableDataModel<BilanBean>, Serializable{
	
	private static final long serialVersionUID = 1L;

	public BilanDataModel() {  
    }
	
	 public BilanDataModel(List<BilanBean> data) {  
	        super(data);  
	    }  
	      
	    @SuppressWarnings("unchecked")
		@Override  
	    public BilanBean getRowData(String rowKey) {  
	        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("----------key===----------"+rowKey, getClass());
	        
	        List<BilanBean> bilans = (List<BilanBean>) getWrappedData();  
	          
	        for(BilanBean bilan : bilans) {  
	            if(String.valueOf(bilan.getCodeclasse()).equals(rowKey))  
	                return bilan;  
	        }  
	          
	        return null;  
	    }  	  

		@Override
		public Object getRowKey(BilanBean arg0) {
			// TODO Auto-generated method stub
			return null;
		}  

}
