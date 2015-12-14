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
	@NamedQuery(name="SortieM.findAll", 
				query="select s from SortieM as s where s.annee.anneeacademique = :annee"),
	@NamedQuery(name="SortieM.find", 
				query="select s from SortieM as s where s.etablissement.codeetablissement=:codeetab and s.idsortie=:idsortie"), 
})
public class SortieM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idsortie;
	private String raison;
	private String utlisateur;
	private Date dateretrait;
	private int quantiteSortie;
	private Annee annee;
	private Etablissement etablissement;
	private Materiel materiel;
	
	public SortieM(){
		
	}
	
	@Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
	public int getIdsortie() {
		return idsortie;
	}
	public void setIdsortie(int idsortie) {
		this.idsortie = idsortie;
	}
	public String getRaison() {
		return raison;
	}
	public void setRaison(String raison) {
		this.raison = raison;
	}
	
	@Temporal( TemporalType.DATE)
	public Date getDateretrait() {
		return dateretrait;
	}
	public void setDateretrait(Date dateretrait) {
		this.dateretrait = dateretrait;
	}
	public int getQuantiteSortie() {
		return quantiteSortie;
	}
	public void setQuantiteSortie(int quantiteSortie) {
		this.quantiteSortie = quantiteSortie;
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
	
	 @ManyToOne
	@JoinColumn(name="codemateriel")
	public Materiel getMateriel() {
		return materiel;
	}
	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}

	public String getUtlisateur() {
		return utlisateur;
	}

	public void setUtlisateur(String utlisateur) {
		this.utlisateur = utlisateur;
	}
	
	

}
