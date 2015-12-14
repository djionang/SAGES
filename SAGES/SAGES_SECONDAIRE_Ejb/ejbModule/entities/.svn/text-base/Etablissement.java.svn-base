package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the etablissement database table.
 * 
 */
@Entity
//Mes requetes de base sur l'entité
@NamedQueries({ 
	@NamedQuery(name="Etablissement.findAll", 
				query="select e from Etablissement as e where e.supprime=:supprime"), 
	@NamedQuery(name="Etablissement.findByCode", 
				query="select e from Etablissement as e where e.supprime=:supprime and e.codeetablissement=:codeetablissement"),
	@NamedQuery(name="Etablissement.findGestionnaire", 
				query="select e from Etablissement as e where e.supprime=:supprime and e.login_gest=:login"),
})
public class Etablissement implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String codeetablissement;
	private String nometab;
	private String acronyme;
	private String devise;
	private String logo;
	private String type;
	
	
	private String codepostal;
	private String email;
	private String serveurMail;
	private String passmail;
	private String telephone;
	private String siteweb;
	
	private String pays;
	private String devisepays;
	private String region;
	private String departement;
	private String arrondissement;
	private String adresse;
	
	//login du gestionnaire de l'etablissement
	private String login_gest;
	
	//mot de passe du gestionnaire de l'etablissement
	private String pass_gest;
	
	private boolean supprime;
	
	private List<Enseignement> enseignements;
	
	private List<Personnel> personnels;
	
	private int modeleBulletin;
	
    public Etablissement() {
    }


	@Id
	public String getCodeetablissement() {
		return this.codeetablissement;
	}

	public void setCodeetablissement(String codeetablissement) {
		this.codeetablissement = codeetablissement;
	}


	public String getAcronyme() {
		return this.acronyme;
	}

	public void setAcronyme(String acronyme) {
		this.acronyme = acronyme;
	}


	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getDevise() {
		return this.devise;
	}

	public void setDevise(String devise) {
		this.devise = devise;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogo() {
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}


	public String getNometab() {
		return this.nometab;
	}

	public void setNometab(String nom) {
		this.nometab = nom;
	}


	public boolean getSupprime() {
		return this.supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}


	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getSiteweb() {
		return siteweb;
	}


	public void setSiteweb(String site_web) {
		this.siteweb = site_web;
	}


	public String getCodepostal() {
		return codepostal;
	}


	public void setCodepostal(String code_postal) {
		this.codepostal = code_postal;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getDepartement() {
		return departement;
	}


	public void setDepartement(String departement) {
		this.departement = departement;
	}


	public String getArrondissement() {
		return arrondissement;
	}


	public void setArrondissement(String arrondissement) {
		this.arrondissement = arrondissement;
	}


	//bi-directional many-to-one association to Enseignement
	@OneToMany(mappedBy="etablissement")
	public List<Enseignement> getEnseignements() {
		return this.enseignements;
	}

	public void setEnseignements(List<Enseignement> enseignements) {
		this.enseignements = enseignements;
	}


	public String getPays() {
		return pays;
	}


	public void setPays(String pays) {
		this.pays = pays;
	}


	/**
	 * @return the serveurMail
	 */
	public String getServeurMail() {
		return serveurMail;
	}


	/**
	 * @param serveurMail the serveurMail to set
	 */
	public void setServeurMail(String serveurMail) {
		this.serveurMail = serveurMail;
	}


	/**
	 * @return the passmail
	 */
	public String getPassmail() {
		return passmail;
	}


	/**
	 * @param passmail the passmail to set
	 */
	public void setPassmail(String passmail) {
		this.passmail = passmail;
	}


	public String getDevisepays() {
		return devisepays;
	}


	public void setDevisepays(String devisepays) {
		this.devisepays = devisepays;
	}


	/**
	 * @return the login_gest
	 */
	@Column(unique=true)
	public String getLogin_gest() {
		return login_gest;
	}


	/**
	 * @param login_gest the login_gest to set
	 */
	public void setLogin_gest(String login_gest) {
		this.login_gest = login_gest;
	}


	/**
	 * @return the pass_gest
	 */
	public String getPass_gest() {
		return pass_gest;
	}


	/**
	 * @param pass_gest the pass_gest to set
	 */
	public void setPass_gest(String pass_gest) {
		this.pass_gest = pass_gest;
	}

	//bi-directional many-to-one association to Retard
	@OneToMany(mappedBy="etablissement")
	public List<Personnel> getPersonnels() {
		return personnels;
	}


	public void setPersonnels(List<Personnel> personnels) {
		this.personnels = personnels;
	}


	public int getModeleBulletin() {
		return modeleBulletin;
	}


	public void setModeleBulletin(int modeleBulletin) {
		this.modeleBulletin = modeleBulletin;
	}
	
}