package ages.beans.inscription.tools;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;
import ages.beans.inscription.DossierBean;

public class DossierDataModel extends ListDataModel<DossierBean> implements SelectableDataModel<DossierBean>{
	 public DossierDataModel() {  
	    }  
	  
	    public DossierDataModel(List<DossierBean> data) {  
	        super(data);  
	    }  
	      
	    @SuppressWarnings("unchecked")
		@Override  
	    public DossierBean getRowData(String rowKey) {  
	        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
	          
	        List<DossierBean> dossiers = (List<DossierBean>) getWrappedData();  
	          
	        for(DossierBean dossier : dossiers) {  
	            if(dossier.getCodedossier().equals(rowKey))  
	                return dossier;  
	        }  
	          
	        return null;  
	    }  
	  
	    @Override  
	    public Object getRowKey(DossierBean dossier) {  
	        return dossier.getCodedossier();  
	    }  
}
