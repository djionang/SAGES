package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the partie_cours database table.
 * 
 */
@Entity
@Table(name="partie_cours")
@NamedQueries({ 
	@NamedQuery(name="PartieCour.findAll", 
				query="select p from PartieCour as p where p.supprime=:supprime"), 
	@NamedQuery(name="PartieCour.findByCode", 
				query="select p from PartieCour as p where p.supprime=:supprime and p.codepartie=:codepartie"),
	@NamedQuery(name="PartieCour.findByLibelle", 
				query="select p from PartieCour as p where p.supprime=:supprime and p.libelle=:libelle and p.cour.codecours=:codecours"),

})
public class PartieCour implements Serializable {
	private static final long serialVersionUID = 1L;
	private int codepartie;
	private String libelle;
	private String description;
	private boolean supprime;
	private List<CahierCours> cahierCours;
	private PlanificationAnnuelle planificationAnnuelle;
	private Cour cour;

    public PartieCour() {
    }

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCodepartie() {
		return codepartie;
	}

	public void setCodepartie(int codepartie) {
		this.codepartie = codepartie;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	//bi-directional many-to-one association to EtreDecrit
	@OneToMany(mappedBy="partieCour")	
	public List<CahierCours> getCahierCours() {
		return cahierCours;
	}

	/**
	 * @param cahierCours the cahierCours to set
	 */
	public void setCahierCours(List<CahierCours> cahierCours) {
		this.cahierCours = cahierCours;
	}

	//bi-directional many-to-one association to PlanificationAnnuelle
    @ManyToOne
	@JoinColumn(name="CODEPLANIFICATION")
	public PlanificationAnnuelle getPlanificationAnnuelle() {
		return this.planificationAnnuelle;
	}

	public void setPlanificationAnnuelle(PlanificationAnnuelle planificationAnnuelle) {
		this.planificationAnnuelle = planificationAnnuelle;
	}
	

	//bi-directional many-to-one association to Cour
    @ManyToOne
	@JoinColumn(name="CODECOURS")
	public Cour getCour() {
		return this.cour;
	}

	public void setCour(Cour cour) {
		this.cour = cour;
	}
	
}