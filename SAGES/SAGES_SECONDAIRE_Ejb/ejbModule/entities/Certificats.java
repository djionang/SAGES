/**
 * 
 */
package entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * @author ISESTMA
 *
 */

@Entity
public class Certificats implements Serializable{

	private static final long serialVersionUID = 1L;

	private int codecertificat;
	private Annee annee;
	private Eleve eleve;
	
	

	public Certificats() {
	
	}
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCodecertificat() {
		return codecertificat;
	}
	
	public void setCodecertificat(int codecertificat) {
		this.codecertificat = codecertificat;
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
	@JoinColumn(name="ideleve")
	public Eleve getEleve() {
		return eleve;
	}
	
	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}
	
	

}
