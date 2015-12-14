package entities;
 
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the classe database table.
 * 
 */
@Entity

@NamedQueries({ 
	@NamedQuery(name="Classe.find", 
				query="select c from Classe as c where c.supprime=:supprime"),
	@NamedQuery(name="Classe.findById", 
				query="select c from Classe as c where  c.supprime=:supprime and c.codeclasse=:codeclasse"),
	@NamedQuery(name="Classe.ListWithStudents", 
				query="select c, count(e) from Classe c left join c.eleves e where c.supprime=:supprime group by c"), 
	@NamedQuery(name="Classe.ListWithCours", 
				query="select c, count(c) from Classe c, Cour as cr  where c.supprime=:supprime and c.codeclasse=cr.classe.codeclasse and cr.annee.anneeacademique=:annee group by c"), 
	@NamedQuery(name="Classe.ListByOptionWithStudents", 
				query="select c, count(e) from Classe c left join c.eleves e where c.supprime=:supprime and c.optionserie.codeoption=:option group by c"), 
	@NamedQuery(name="Classe.findByCode", 
				query="select c, count(e) from Classe c left join c.eleves e where c.codeclasse=:codeclasse and c.supprime=:supprime group by c"), 
	@NamedQuery(name="Classe.findTranches", 
	             query="select c from Classe c left join fetch c.parametreTranches where c.codeclasse=:codeclasse  and c.supprime=:supprime"),
	@NamedQuery(name="Classe.comp", 
	            query="select count(e) from Classe c left join c.eleves e where e.annee.anneeacademique = :annee and c.supprime=:supprime and e.classe.codeclasse=:codeclasse"),
	
	})
public class Classe implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codeclasse;
	private int contenancemax;
	private String delegue1;
	private String delegue2;
	private String libelle;
	private String salleDefaut;
	private boolean supprime;
	private Annee annee;
	
	private OptionSerie optionserie;
	private List<Cour> cours;
	private List<Eleve> eleves;
	private List<ParametreTranche> parametreTranches;
	private List<EnsTitulaire> titulaires;

	public Classe() {
    }


	@Id
	public String getCodeclasse() {
		return this.codeclasse;
	}

	public void setCodeclasse(String codeclasse) {
		this.codeclasse = codeclasse;
	}


	public int getContenancemax() {
		return this.contenancemax;
	}

	public void setContenancemax(int contenancemax) {
		this.contenancemax = contenancemax;
	}


	public String getDelegue1() {
		return this.delegue1;
	}

	public void setDelegue1(String delegue1) {
		this.delegue1 = delegue1;
	}


	public String getDelegue2() {
		return this.delegue2;
	}

	public void setDelegue2(String delegue2) {
		this.delegue2 = delegue2;
	}


	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
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
	

	//bi-directional many-to-one association to Cour
	@OneToMany(mappedBy="classe")
	public List<Cour> getCours() {
		return this.cours;
	}

	public void setCours(List<Cour> cours) {
		this.cours = cours;
	}
	

	//bi-directional many-to-one association to Eleve
	@OneToMany(mappedBy="classe")
	public List<Eleve> getEleves() {
		return this.eleves;
	}

	public void setEleves(List<Eleve> eleves) {
		this.eleves = eleves;
	}

	//bi-directional many-to-one association to ParametreTranche
	@OneToMany(mappedBy="classe")
	public List<ParametreTranche> getParametreTranches() {
		return this.parametreTranches;
	}

	public void setParametreTranches(List<ParametreTranche> parametreTranches) {
		this.parametreTranches = parametreTranches;
	}
	
	
	//bi-directional many-to-one association to Optionclasse
    @ManyToOne
	@JoinColumn(name="CODEOPTION")
	public OptionSerie getOptionserie() {
		return optionserie;
	}


	public void setOptionserie(OptionSerie optionSerie) {
		this.optionserie = optionSerie;
	}


	//bi-directional many-to-one association to Trimestre
	@OneToMany(mappedBy="classe", fetch=FetchType.EAGER)
	public List<EnsTitulaire> getTitulaires() {
		return titulaires;
	}


	public void setTitulaires(List<EnsTitulaire> titulaires) {
		this.titulaires = titulaires;
	}


	public String getSalleDefaut() {
		return salleDefaut;
	}


	public void setSalleDefaut(String salleDefaut) {
		this.salleDefaut = salleDefaut;
	}
	
}