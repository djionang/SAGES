package entities;
 
import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the annee database table.
 * 
 */
@Entity

@NamedQueries({ 
	@NamedQuery(name="Annee.findAnneeEnCours", 
				query="select a from Annee as a where a.supprime=:supprime and a.anneeacademique=:annee"),
	@NamedQuery(name="Annee.findByCode", 
				query="select a from Annee as a where a.supprime=:supprime and a.close=:close and a.anneeacademique=:annee order by a.dateDebut asc"),
	@NamedQuery(name="Annee.find", 
				query="select a from Annee as a where a.supprime=:supprime and a.anneeacademique=:annee order by a.dateDebut asc"),
	@NamedQuery(name="Annee.findAll", 
				query="select a from Annee as a where a.supprime=:supprime order by a.dateDebut asc"),
	@NamedQuery(name="Annee.findCode", 
				query="select a.anneeacademique from Annee as a where a.supprime=:supprime and a.close=:close"),
	@NamedQuery(name="Annee.findCodeAll", 
				query="select a.anneeacademique from Annee as a where a.supprime=:supprime"),
	
	})
public class Annee implements Serializable {
	private static final long serialVersionUID = 1L;
	private String anneeacademique;
	private Date dateDebut;
	private Date dateFin;
	private boolean supprime;
	private boolean close;
	private List<Classe> classes;
	private List<Eleve> eleves;
	private List<ParametreTranche> parametreTranches;
	private List<PreInscription> preInscriptions;
	private List<Retard> retards;
	private List<Trimestre> trimestres;
	private List<EnsTitulaire> titulaires;
	private List<Versement> versements;
	private List<Programmation> programmations;
	private List<CahierDeTexte> cdts;

    public Annee() {
    }

	@Id
	public String getAnneeacademique() {
		return this.anneeacademique;
	}

	public void setAnneeacademique(String anneeacademique) {
		this.anneeacademique = anneeacademique;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	public Date getDateDebut() {
		return dateDebut;
	}


	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}


	public Date getDateFin() {
		return dateFin;
	}


	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	//bi-directional many-to-one association to Classe
	@OneToMany(mappedBy="annee")
	public List<Classe> getClasses() {
		return this.classes;
	}

	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}
	

	//bi-directional many-to-one association to Eleve
	@OneToMany(mappedBy="annee")
	public List<Eleve> getEleves() {
		return this.eleves;
	}

	public void setEleves(List<Eleve> eleves) {
		this.eleves = eleves;
	}
	

	//bi-directional many-to-one association to ParametreTranche
	@OneToMany(mappedBy="annee")
	public List<ParametreTranche> getParametreTranches() {
		return this.parametreTranches;
	}

	public void setParametreTranches(List<ParametreTranche> parametreTranches) {
		this.parametreTranches = parametreTranches;
	}
	

	//bi-directional many-to-one association to PreInscription
	@OneToMany(mappedBy="annee")
	public List<PreInscription> getPreInscriptions() {
		return this.preInscriptions;
	}

	public void setPreInscriptions(List<PreInscription> preInscriptions) {
		this.preInscriptions = preInscriptions;
	}
	

	//bi-directional many-to-one association to Retard
	@OneToMany(mappedBy="annee")
	public List<Retard> getRetards() {
		return this.retards;
	}

	public void setRetards(List<Retard> retards) {
		this.retards = retards;
	}
	


	//bi-directional many-to-one association to Trimestre
	@OneToMany(mappedBy="annee")
	public List<Trimestre> getTrimestres() {
		return this.trimestres;
	}

	public void setTrimestres(List<Trimestre> trimestres) {
		this.trimestres = trimestres;
	}

	//bi-directional many-to-one association to Trimestre
	@OneToMany(mappedBy="annee")
	public List<EnsTitulaire> getTitulaires() {
		return titulaires;
	}


	public void setTitulaires(List<EnsTitulaire> titulaires) {
		this.titulaires = titulaires;
	}

	//bi-directional many-to-one association to AnneeEnCour
	@OneToMany(mappedBy="annee")
	public List<Versement> getVersements() {
		return versements;
	}


	public void setVersements(List<Versement> versements) {
		this.versements = versements;
	}

	@Basic(optional=false)
	public boolean isClose() {
		return close;
	}


	public void setClose(boolean close) {
		this.close = close;
	}

	/**
	 * @return the programmations
	 */
	//bi-directional many-to-one association to AnneeEnCour
		@OneToMany(mappedBy="annee")
	public List<Programmation> getProgrammations() {
		return programmations;
	}

	/**
	 * @param programmations the programmations to set
	 */
	public void setProgrammations(List<Programmation> programmations) {
		this.programmations = programmations;
	}

	//bi-directional many-to-one association to Cdts
	@OneToMany(mappedBy="annee")
	public List<CahierDeTexte> getCdts() {
		return cdts;
	}

	public void setCdts(List<CahierDeTexte> cdts) {
		this.cdts = cdts;
	}
	
}