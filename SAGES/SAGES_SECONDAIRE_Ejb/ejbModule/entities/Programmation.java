package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the programmation database table.
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name="Programmation.findAll", 
				query="select p from Programmation as p where p.annee.anneeacademique = :annee and p.supprime=:supprime"),
	@NamedQuery(name="Programmation.findByPeriod", 
				query="select p from Programmation as p where (p.datedebut BETWEEN :datedebut and :datefin) and p.annee.anneeacademique=:annee and p.supprime=:supprime"), 
	@NamedQuery(name="Programmation.findByCode", 
				query="select p from Programmation p where p.annee.anneeacademique=:annee and p.id=:id and p.supprime=:supprime"), 
	@NamedQuery(name="Programmation.findCoursClasseByPeriod", 
				query="select p from Programmation as p where (p.datedebut BETWEEN :datedebut and :datefin) and p.annee.anneeacademique=:annee and p.supprime=:supprime and p.cours.classe.codeclasse=:codeclasse"),
	@NamedQuery(name="Programmation.findCoursByPeriod", 
				query="select p from Programmation as p where (p.datedebut BETWEEN :datedebut and :datefin) and p.annee.anneeacademique=:annee and p.supprime=:supprime and p.cours.codecours=:codecours"),
	@NamedQuery(name="Programmation.findProgBySalle", 
				query="select p from Programmation as p where (p.datedebut BETWEEN :datedebut and :datefin) and p.annee.anneeacademique=:annee and p.supprime=:supprime and p.salle.codesalle=:codesalle"),
	@NamedQuery(name="Programmation.findProgEvt", 
				query="select p from Programmation as p left join p.activite a where (p.datedebut BETWEEN :datedebut and :datefin) and p.annee.anneeacademique=:annee and p.supprime=:supprime"),
				
})
public class Programmation implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String libelle;
	private Date datedebut;
	private Date datefin;
	private boolean supprime;
	private Salle salle;
	private Cour cours;
	private Activite activite;
	private Evaluation evaluation;
	private Annee annee;

    public Programmation() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the datedebut
	 */
	 @Temporal( TemporalType.TIMESTAMP)
	public Date getDatedebut() {
		return datedebut;
	}


	/**
	 * @param datedebut the datedebut to set
	 */
	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}


	/**
	 * @return the datefin
	 */
	 @Temporal( TemporalType.TIMESTAMP)
	public Date getDatefin() {
		return datefin;
	}


	/**
	 * @param datefin the datefin to set
	 */
	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	//bi-directional many-to-one association to Salle
    @ManyToOne
	@JoinColumn(name="CODESALLE")
	public Salle getSalle() {
		return this.salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}


	/**
	 * @return the cours
	 */
	//bi-directional many-to-one association to Eleve
    @ManyToOne
	@JoinColumn(name="COURS")
	public Cour getCours() {
		return cours;
	}


	/**
	 * @param cours the cours to set
	 */
	
	public void setCours(Cour cours) {
		this.cours = cours;
	}


	/**
	 * @return the activite
	 */
	//bi-directional many-to-one association to Eleve
    @ManyToOne
	@JoinColumn(name="ACTIVITE")
	public Activite getActivite() {
		return activite;
	}


	/**
	 * @param activite the activite to set
	 */
	public void setActivite(Activite activite) {
		this.activite = activite;
	}


	/**
	 * @return the evaluation
	 */
	//bi-directional many-to-one association to Eleve
    @ManyToOne
	@JoinColumn(name="EVALUATION")
	public Evaluation getEvaluation() {
		return evaluation;
	}


	/**
	 * @param evaluation the evaluation to set
	 */
	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}
	
	//bi-directional many-to-one association to Annee
    @ManyToOne
	@JoinColumn(name="ANNEEACADEMIQUE")
	public Annee getAnnee() {
		return this.annee;
	}

	public void setAnnee(Annee annee) {
		this.annee = annee;
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
	
}