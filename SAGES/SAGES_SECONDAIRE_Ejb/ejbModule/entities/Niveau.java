package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the niveau database table.
 * 
 */
@Entity
//Mes requetes de base sur l'entité
@NamedQueries({ 
	@NamedQuery(name="Niveau.findAll", 
				query="select n from Niveau as n where n.supprime=:supprime"),
	@NamedQuery(name="Niveau.findAllB", 
				query="select n.codeniveau from Niveau as n where n.supprime=:supprime"),
	@NamedQuery(name="Niveau.findByCode", 
				query="select n from Niveau as n where n.supprime=:supprime and n.codeniveau=:codeniveau"),
	@NamedQuery(name="Niveau.findByCycle", 
				query="select n from Niveau as n where n.supprime=:supprime and n.cycle.codecycle=:codecycle"),

})

public class Niveau implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idniveau;
	private String codeniveau;
	private String libelle;
	private boolean supprime;
	private Cycle cycle;
	private List<OptionSerie> optionSeries;

	

	public Niveau() {
    }
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdniveau() {
		return idniveau;
	}


	public void setIdniveau(int idniveau) {
		this.idniveau = idniveau;
	}

	@Column(unique=true,nullable=false)
	public String getCodeniveau() {
		return this.codeniveau;
	}

	public void setCodeniveau(String codeniveau) {
		this.codeniveau = codeniveau;
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


	//bi-directional many-to-one association to Cycle
    @ManyToOne
	@JoinColumn(name="CODECYCLE")
	public Cycle getCycle() {
		return this.cycle;
	}

	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}
	

	//bi-directional many-to-many association to Optionclasse
	@OneToMany(mappedBy="niveau")
	public List<OptionSerie> getOptionSeries() {
		return this.optionSeries;
	}

	public void setOptionSeries(List<OptionSerie> optionSeries) {
		this.optionSeries = optionSeries;
	}
		
}