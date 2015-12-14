package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the parametre_tranche database table.
 * 
 */
@Entity
@Table(name="parametre_tranche")

//Mes requetes de base sur l'entité
@NamedQueries({ 
	@NamedQuery(name="ParamTranche.findAll", 
				query="select p from ParametreTranche as p where p.annee.anneeacademique = :annee and p.supprime=:supprime"), 
	@NamedQuery(name="ParamTranche.findforClasse", 
				query="select p from ParametreTranche as p where p.annee.anneeacademique = :annee and p.supprime=:supprime and p.classe.codeclasse=:classe"),
	@NamedQuery(name="ParametreTranche.find", 
				query="select p from ParametreTranche as p where p.id.numero=:numero and p.classe.codeclasse=:codeclasse and p.supprime=:supprime and p.annee.anneeacademique=:annee"),
	@NamedQuery(name="ParametreTranche.maxInsertedId", 
				query="select max(pt.id.numero) from ParametreTranche as pt where pt.annee.anneeacademique=:annee and pt.classe.codeclasse=:classe and pt.supprime=:supprime"),
	@NamedQuery(name="ParametreTranche.listTypes", 
				query="select distinct pt.typetranche from ParametreTranche as pt"),
				
})

public class ParametreTranche implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ParametreTranchePK id;
	private Date delaiversement;
	private float montant;
	private boolean supprime;
	private String typetranche;
	private Annee annee;
	private Classe classe;

    public ParametreTranche() {
    }


	@EmbeddedId
	public ParametreTranchePK getId() {
		return this.id;
	}

	public void setId(ParametreTranchePK id) {
		this.id = id;
	}
	

    @Temporal( TemporalType.DATE)
	public Date getDelaiversement() {
		return this.delaiversement;
	}

	public void setDelaiversement(Date delaiversement) {
		this.delaiversement = delaiversement;
	}


	public float getMontant() {
		return this.montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	public String getTypetranche() {
		return this.typetranche;
	}

	public void setTypetranche(String typetranche) {
		this.typetranche = typetranche;
	}


	//bi-directional many-to-one association to Annee
	@MapsId("anneeacademique")
    @ManyToOne
	@JoinColumn(name="ANNEEACADEMIQUE")
	public Annee getAnnee() {
		return this.annee;
	}

	public void setAnnee(Annee annee) {
		this.annee = annee;
	}
	

	//bi-directional many-to-one association to Classe
	@MapsId("codeclasse")
    @ManyToOne
	@JoinColumn(name="CODECLASSE")
	public Classe getClasse() {
		return this.classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	
}