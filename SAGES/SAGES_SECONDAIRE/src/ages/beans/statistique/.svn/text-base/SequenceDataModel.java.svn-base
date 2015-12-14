package ages.beans.statistique;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import ages.beans.anneeacademique.SequenceBean;

public class SequenceDataModel extends ListDataModel<SequenceBean> implements SelectableDataModel<SequenceBean>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SequenceDataModel() {  
    }  
  
    public SequenceDataModel(List<SequenceBean> data) {  
        super(data);  
    } 

	@SuppressWarnings("unchecked")
	@Override
	public SequenceBean getRowData(String num) {
		List<SequenceBean> sequences = (List<SequenceBean>) getWrappedData();  
		String seq;
        for(SequenceBean sequence : sequences) {  
        	 seq=String.valueOf(sequence.getNumero());
            if(seq==num) 
                return sequence;  
        }  
          
        return null;  
	}

	@Override
	public Object getRowKey(SequenceBean seq) {
		return seq.getNumero();
	}

}
