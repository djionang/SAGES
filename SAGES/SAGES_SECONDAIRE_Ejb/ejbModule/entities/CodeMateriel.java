package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CodeMateriel implements Serializable{
	private static final long serialVersionUID = 1L;
	private int code;
    public CodeMateriel() {
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
