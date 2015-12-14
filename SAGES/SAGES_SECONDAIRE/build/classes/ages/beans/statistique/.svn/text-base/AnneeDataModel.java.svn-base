package ages.beans.statistique;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import ages.beans.anneeacademique.AnneeBean;

public class AnneeDataModel extends ListDataModel<AnneeBean> implements SelectableDataModel<AnneeBean>, Serializable{

	private static final long serialVersionUID = 1L;

	public AnneeDataModel() {  
    }  
  
    public AnneeDataModel(List<AnneeBean> data) {  
        super(data);  
    }  
      
    @SuppressWarnings("unchecked")
	@Override  
    public AnneeBean getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<AnneeBean> annees = (List<AnneeBean>) getWrappedData();  
          
        for(AnneeBean annee : annees) {  
            if(annee.getAnneeacademique().equals(rowKey))  
                return annee;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(AnneeBean annee) {  
        return annee.getAnneeacademique();  
    } 




}
