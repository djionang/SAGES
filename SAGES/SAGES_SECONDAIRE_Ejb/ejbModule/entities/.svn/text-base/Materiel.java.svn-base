
package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

@NamedQueries({ 
	@NamedQuery(name="Materiel.findAll", 
				query="select m from Materiel as m"),
	@NamedQuery(name="Materiel.findAllS", 
				query="select distinct m from Materiel as m"),
	@NamedQuery(name="Materiel.find", 
				query="select m from Materiel as m where m.etablissement.codeetablissement=:codeetab and m.idmateriel=:codemateriel"), 
})
public class Materiel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idmateriel;
	private String codemateriel;
	private String designation;
	private String typemateriel;
	private Date dateenreg;
	private int quantite;
	private int reste;
	private float prix;
	private String etat;
	private String numeroserie;
	private int quantiteF;
	private Annee annee;
	private Etablissement etablissement;
	
	
	public Materiel(){
		
	}
	
	@Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
	public int getIdmateriel() {
		return idmateriel;
	}
	public void setIdmateriel(int idmateriel) {
		this.idmateriel = idmateriel;
	}
	
	
	public String getCodemateriel() {
		return codemateriel;
	}
	public void setCodemateriel(String codemateriel) {
		this.codemateriel = codemateriel;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getTypemateriel() {
		return typemateriel;
	}
	public void setTypemateriel(String typemateriel) {
		this.typemateriel = typemateriel;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	
	
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
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
    
  //bi-directional many-to-one association to Annee
    @ManyToOne
	public Etablissement getEtablissement() {
		return etablissement;
	}
	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}
	public int getQuantiteF() {
		return quantiteF;
	}
	public void setQuantiteF(int quantiteF) {
		this.quantiteF = quantiteF;
	}
	
	@Temporal( TemporalType.DATE)
	public Date getDateenreg() {
		return dateenreg;
	}
	public void setDateenreg(Date dateenreg) {
		this.dateenreg = dateenreg;
	}

	public String getNumeroserie() {
		return numeroserie;
	}

	public void setNumeroserie(String numeroserie) {
		this.numeroserie = numeroserie;
	}

	public int getReste() {
		return reste;
	}

	public void setReste(int reste) {
		this.reste = reste;
	}
	
	

}
