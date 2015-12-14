package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the sanction database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="TypeSanction.findAll", 
			query="select t from TypeSanction as t where t.supprime=:supprime"),
	@NamedQuery(name="TypeSanction.findByCode",
			query="select t from TypeSanction as t where t.supprime=:supprime and t.codetype=:id"),
	@NamedQuery(name="TypeSanction.findBylibelle",
			query="select t from TypeSanction as t where t.supprime=:supprime and t.libelle=:libelle"),

})
public class TypeSanction implements Serializable {
	private static final long serialVersionUID = 1L;
	private int codetype;
	private String libelle;
	private String description;
	private boolean supprime;
	
	private List<Sanction> sanctions;

    public TypeSanction() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCodetype() {
		return this.codetype;
	}

	public void setCodesanction(int codetype) {
		this.codetype = codetype;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}


	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @param codetype the codetype to set
	 */
	public void setCodetype(int codetype) {
		this.codetype = codetype;
	}


	/**
	 * @return the sanctions
	 */
	//bi-directional many-to-one association to Retard
	@OneToMany(mappedBy="typesanction")
	public List<Sanction> getSanctions() {
		return sanctions;
	}


	/**
	 * @param sanctions the sanctions to set
	 */
	public void setSanctions(List<Sanction> sanctions) {
		this.sanctions = sanctions;
	}

	
}