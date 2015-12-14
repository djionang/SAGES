package ages.beans.statistique;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import utils.Repertoire;

public class BilanTrimDataModel extends ListDataModel<BilanTrimBean> implements SelectableDataModel<BilanTrimBean>, Serializable{
	
	private static final long serialVersionUID = 1L;

	public BilanTrimDataModel() {  
    }
	
	 public BilanTrimDataModel(List<BilanTrimBean> data) {  
	        super(data);  
	    }  
	      
	    @SuppressWarnings("unchecked")
		@Override  
	    public BilanTrimBean getRowData(String rowKey) {  
	        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("--------------------RRRR", getClass());
	        Repertoire.logDebug("----------key===----------"+rowKey, getClass());
	        
	        List<BilanTrimBean> bilans = (List<BilanTrimBean>) getWrappedData();  
	          
	        for(BilanTrimBean bilan : bilans) {  
	            if(String.valueOf(bilan.getCodeclasse()).equals(rowKey))  
	                return bilan;  
	        }  
	          
	        return null;  
	    }  	  

		@Override
		public Object getRowKey(BilanTrimBean arg0) {
			// TODO Auto-generated method stub
			return null;
		}  

}
