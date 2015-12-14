package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the cours database table.
 * 
 */
@Entity
@Table(name="cours")

@NamedQueries({ 
	@NamedQuery(name="Cours.findAll", 
				query="select c from Cour as c left join c.classe cl where c.annee.anneeacademique = :annee and c.supprime=:supprime"),
	@NamedQuery(name="Cours.findByCode", 
				query="select c from Cour as c where c.codecours = :code and c.supprime=:supprime and c.annee.anneeacademique=:annee"),
	@NamedQuery(name="Cours.findByClasse", 
				query="select c from Cour c where c.classe.codeclasse=:codeclasse and c.annee.anneeacademique = :annee and c.supprime=:supprime and c.classe.supprime=:supprime"), 
	@NamedQuery(name="Cours.findByCodes", 
				query="select c from Cour c where c.classe.codeclasse=:codeclasse and c.matiere.codematiere=:codematiere and c.annee.anneeacademique = :annee and c.supprime=:supprime"), 
	@NamedQuery(name="Cours.findNProgByClasse", 
				query="select c from Cour c where c.classe.codeclasse=:codeclasse and c.annee.anneeacademique = :annee and c.supprime=:supprime and c.classe.supprime=:supprime and c.programmations is empty"), 
	@NamedQuery(name="Cours.findProgByClasse", 
				query="select c from Cour c where c.classe.codeclasse=:codeclasse and c.annee.anneeacademique = :annee and c.supprime=:supprime and c.classe.supprime=:supprime and c.programmations is not empty"), 
	
	})
public class Cour implements Serializable {
	private static final long serialVersionUID = 1L;
	private int codecours;
	private String libelleCours;
	private int coeficient;
	private String description;
	private boolean supprime;
	private List<Programmation> programmations;
	private Classe classe;
	private Matiere matiere;
	private Enseignant enseignant;
	private Groupematiere groupematiere;
	private Annee annee;
	
	private List<Evaluation> evaluations;
	private List<PartieCour> partieCours;
	private List<CahierDeTexte> cdts;
	

    public Cour() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCodecours() {
		return codecours;
	}


	public void setCodecours(int codecours) {
		this.codecours = codecours;
	}


	public int getCoeficient() {
		return this.coeficient;
	}


	public void setCoeficient(int coeficient) {
		this.coeficient = coeficient;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	//bi-directional many-to-one association to Attribuersallecour
	@OneToMany(mappedBy="cours")
	public List<Programmation> getProgrammations() {
		return programmations;
	}


	/**
	 * @param programmations the programmations to set
	 */
	public void setProgrammations(List<Programmation> programmations) {
		this.programmations = programmations;
	}


	//bi-directional many-to-one association to Classe
    @ManyToOne
	@JoinColumn(name="CODECLASSE")
	public Classe getClasse() {
		return this.classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	

	//bi-directional many-to-one association to Optionmatiere
    @ManyToOne
	@JoinColumn(name="CODEMATIERE")
	public Matiere getMatiere() {
		return this.matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	

	//bi-directional many-to-one association to Enseignant
    @ManyToOne
	@JoinColumn(name="CODEENSEIGNANT")
	public Enseignant getEnseignant() {
		return this.enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	

	//bi-directional many-to-one association to Evaluation
	@OneToMany(mappedBy="cour")
	public List<Evaluation> getEvaluations() {
		return this.evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}
	

	//bi-directional many-to-one association to PartieCour
	@OneToMany(mappedBy="cour")
	public List<PartieCour> getPartieCours() {
		return this.partieCours;
	}

	public void setPartieCours(List<PartieCour> partieCours) {
		this.partieCours = partieCours;
	}

	//bi-directional many-to-one association to Optionmatiere
    @ManyToOne
	@JoinColumn(name="CODEGM")
	public Groupematiere getGroupematiere() {
		return groupematiere;
	}


	public void setGroupematiere(Groupematiere groupematiere) {
		this.groupematiere = groupematiere;
	}


	public String getLibelleCours() {
		return libelleCours;
	}


	public void setLibelleCours(String libelleCours) {
		this.libelleCours = libelleCours;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	//bi-directional many-to-one association to CDT
	@OneToMany(mappedBy="cours")
	public List<CahierDeTexte> getCdts() {
		return cdts;
	}


	public void setCdts(List<CahierDeTexte> cdts) {
		this.cdts = cdts;
	}

	//bi-directional many-to-one association to Annee
    @ManyToOne
	@JoinColumn(name="ANNEEACADEMIQUE")
	public Annee getAnnee() {
		return annee;
	}


	public void setAnnee(Annee annee) {
		this.annee = annee;
	}
	
}