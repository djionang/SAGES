package entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ 
	@NamedQuery(name="EnsTitulaire.findByAn", 
				query="select t from  EnsTitulaire as t  where t.classe.codeclasse=t.classe.codeclasse and t.supprime=:supprime  and t.annee.anneeacademique=:annee and t.classe.codeclasse=:codeclasse"),
	
	})
public class EnsTitulaire implements Serializable{

	private static final long serialVersionUID = 1L;

	private EnsTitulairePK id;
	
	private boolean supprime;
	
	private Enseignant enseignant;
	private Classe classe;
	private Annee annee;

    public EnsTitulaire() {
		
    }


	@EmbeddedId
	public EnsTitulairePK getId() {
		return this.id;
	}

	public void setId(EnsTitulairePK id) {
		this.id = id;
	}
	

  


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
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

	//bi-directional many-to-one association to Annee
	@MapsId("codeenseignant")
    @ManyToOne
	@JoinColumn(name="CODEENSEIGNANT")
	public Enseignant getEnseignant() {
		return enseignant;
	}


	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	//bi-directional many-to-one association to Annee
	@MapsId("codeclasse")
    @ManyToOne
	@JoinColumn(name="CODECLASSE")
	public Classe getClasse() {
		return classe;
	}


	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	
}
