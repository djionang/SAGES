package ages.beans.statistique;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import ages.beans.anneeacademique.TrimestreBean;

public class TrimestreDataModel extends ListDataModel<TrimestreBean> implements SelectableDataModel<TrimestreBean>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TrimestreDataModel(List<TrimestreBean> data) {  
        super(data);  
    } 

	@SuppressWarnings("unchecked")
	@Override
	public TrimestreBean getRowData(String num) {
		List<TrimestreBean> trimestres = (List<TrimestreBean>) getWrappedData();  
		String trim;
        for(TrimestreBean trimestre : trimestres) {  
        	 trim=String.valueOf(trimestre.getNumero());
            if(trim==num) 
                return trimestre;  
        }  
          
        return null;  
	}

	@Override
	public Object getRowKey(TrimestreBean trim) {
		return trim.getNumero();
	}

}
