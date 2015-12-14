package entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the sanction database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Sanction.findAll", 
			query="select s from Sanction as s where s.eleve.annee.anneeacademique=:annee"),
	@NamedQuery(name="Sanction.findByCode",
			query="select s from Sanction as s where s.codesanction=:id"),
	@NamedQuery(name="Sanction.findByEleve",
			query="select s from Sanction as s where s.eleve.matricule=:matricule and s.annule=:annule and s.datedecision between :datedebut and :datefin"),
	
})
public class Sanction implements Serializable {
	private static final long serialVersionUID = 1L;
	private int codesanction;
	private boolean annule;
	private Date datedecision;
	private Calendar dateeffet;
	private int dureesanction;
	private String motif;
	private TypeSanction typesanction;
	private Eleve eleve;

    public Sanction() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCodesanction() {
		return this.codesanction;
	}

	public void setCodesanction(int codesanction) {
		this.codesanction = codesanction;
	}


	public int getDureesanction() {
		return this.dureesanction;
	}

	public void setDureesanction(int duree) {
		this.dureesanction = duree;
	}


	public String getMotif() {
		return this.motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	//bi-directional many-to-one association to Eleve
    @ManyToOne
	@JoinColumn(name="MATRICULE")
	public Eleve getEleve() {
		return this.eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}


	public boolean isAnnule() {
		return annule;
	}


	public void setAnnule(boolean annule) {
		this.annule = annule;
	}


	public Date getDatedecision() {
		return datedecision;
	}


	public void setDatedecision(Date datedecision) {
		this.datedecision = datedecision;
	}


	/**
	 * @return the dateeffet
	 */
	public Calendar getDateeffet() {
		return dateeffet;
	}


	/**
	 * @param dateeffet the dateeffet to set
	 */
	public void setDateeffet(Calendar dateeffet) {
		this.dateeffet = dateeffet;
	}


	//bi-directional many-to-one association to Eleve
    @ManyToOne
	@JoinColumn(name="TYPESANCTION")
	public TypeSanction getTypesanction() {
		return typesanction;
	}


	/**
	 * @param type the type to set
	 */
	public void setTypesanction(TypeSanction typesanction) {
		this.typesanction = typesanction;
	}

	
}