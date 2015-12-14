package entities;
 
import java.io.Serializable;
import javax.persistence.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cahier_de_texte database table.
 * 
 */
@Entity
@Table(name="cahier_de_texte")
@NamedQueries({ 
	@NamedQuery(name="CahierDeTexte.findAll", 
				query="select c from CahierDeTexte as c where c.annee.anneeacademique = :annee and c.supprime=:supprime"),
	@NamedQuery(name="CahierDeTexte.findByCode", 
				query="select c from CahierDeTexte c where c.annee.anneeacademique = :annee and c.supprime=:supprime and c.codetexte=:codetexte"), 
	
	})
public class CahierDeTexte implements Serializable {
	private static final long serialVersionUID = 1L;
	private int codetexte;
	private Date datetravail;
	private Time heuredebut;
	private Time heurefin;
	private String objectifs;
	private String resume;
	private boolean supprime;
	private List<CahierCours> cahierCours;
	private List<Exercice> exercices;
	private Annee annee;
	private Cour cours;

    public CahierDeTexte() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCodetexte() {
		return this.codetexte;
	}

	public void setCodetexte(int codetexte) {
		this.codetexte = codetexte;
	}


    @Temporal( TemporalType.DATE)
	public Date getDatetravail() {
		return this.datetravail;
	}

	public void setDatetravail(Date datetravail) {
		this.datetravail = datetravail;
	}


	@Column(name="HEURE_DEBUT")
	public Time getHeuredebut() {
		return this.heuredebut;
	}

	public void setHeuredebut(Time heureDebut) {
		this.heuredebut = heureDebut;
	}


	public Time getHeurefin() {
		return this.heurefin;
	}

	public void setHeurefin(Time heurefin) {
		this.heurefin = heurefin;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}

	//bi-directional many-to-one association to Exercice
	@OneToMany(mappedBy="cahierDeTexte")
	public List<Exercice> getExercices() {
		return this.exercices;
	}

	public void setExercices(List<Exercice> exercices) {
		this.exercices = exercices;
	}


	public String getObjectifs() {
		return objectifs;
	}


	public void setObjectifs(String objectifs) {
		this.objectifs = objectifs;
	}


	public String getResume() {
		return resume;
	}


	public void setResume(String resume) {
		this.resume = resume;
	}

	//bi-directional many-to-one association to EtreDecrit
	@OneToMany(mappedBy="cahierDeTexte")
	public List<CahierCours> getCahierCours() {
		return cahierCours;
	}


	public void setCahierCours(List<CahierCours> cahierCours) {
		this.cahierCours = cahierCours;
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

	//bi-directional many-to-one association to Cour
    @ManyToOne
	@JoinColumn(name="COURS")
	public Cour getCours() {
		return cours;
	}


	public void setCours(Cour cours) {
		this.cours = cours;
	}
	
}