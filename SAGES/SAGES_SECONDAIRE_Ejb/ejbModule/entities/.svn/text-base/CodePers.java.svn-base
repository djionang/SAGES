package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the absence database table.
 * 
 */
@Entity
public class CodePers implements Serializable {
	private static final long serialVersionUID = 1L;
	private int code;
    public CodePers() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	
}