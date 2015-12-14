package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the pre_inscription database table.
 * 
 */
@Entity
@Table(name="pre_inscription")

//Mes requetes de base sur l'entité
@NamedQueries({ 
	@NamedQuery(name="Preinscription.findAll", 
				query="select p from PreInscription as p where p.annee.anneeacademique = :annee and p.supprime=:supprime order by p.nom asc"), 
	@NamedQuery(name="Preinscription.findExist", 
				query="select p.codedossier from PreInscription as p where p.annee.anneeacademique = :annee and p.supprime=:supprime and p.codedossier=:codedossier"), 
	@NamedQuery(name="Preinscription.findByState", 
				query="select p from PreInscription as p where p.annee.anneeacademique = :annee and p.supprime=:supprime and p.etat=:etat"), 
	@NamedQuery(name="Preinscription.find", 
				query="select p from PreInscription as p where p.annee.anneeacademique = :annee and p.supprime=:supprime and p.codedossier=:codedossier"), 
	@NamedQuery(name="Preinscription.findByStateAndLevel", 
	query="select p from PreInscription as p where p.annee.anneeacademique = :annee and p.supprime=:supprime and p.etat=:etat and p.niveaudemande.codeniveau=:niveau"), 
})
public class PreInscription implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codedossier;
	private String adresse;
	private String anneeancienetablissement;
	private String boitepostale;
	private String classeancienetablissement;
	private Date datenaissance;
	private Date datepreinscription;
	private String email;
	private String emailmere;
	private String emailpere;
	private String emailtuteur;
	private String etat;
	private String lieunaissance;
	private String nationalite;
	private Niveau niveaudemande;
	private String nom;
	private String matricule;
	private String nomancienetablissement;
	private String nommere;
	private String nompere;
	private String nomtuteur;
	private String numeropayement;
	private String optiondemande;
	private String passwordaccess;
	private String prenom;
	private String professionmere;
	private String professionpere;
	private String professiontuteur;
	private String sexe;
	private boolean supprime;
	private String telephone;
	private String telephonemere;
	private String telephonepere;
	private String telephonetuteur;
	private double dernieremoyenne;
	private float sommeverse;
	private Utilisateur utilisateur;
	private Annee annee;

    public PreInscription() {
    }


	@Id
	public String getCodedossier() {
		return this.codedossier;
	}

	public void setCodedossier(String codedossier) {
		this.codedossier = codedossier;
	}


	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getAnneeancienetablissement() {
		return this.anneeancienetablissement;
	}

	public void setAnneeancienetablissement(String anneeancienetablissement) {
		this.anneeancienetablissement = anneeancienetablissement;
	}


	public String getBoitepostale() {
		return this.boitepostale;
	}

	public void setBoitepostale(String boitepostale) {
		this.boitepostale = boitepostale;
	}


	public String getClasseancienetablissement() {
		return this.classeancienetablissement;
	}

	public void setClasseancienetablissement(String classeancienetablissement) {
		this.classeancienetablissement = classeancienetablissement;
	}


    @Temporal( TemporalType.DATE)
	public Date getDatenaissance() {
		return this.datenaissance;
	}

	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}


    @Temporal( TemporalType.DATE)
	public Date getDatepreinscription() {
		return this.datepreinscription;
	}

	public void setDatepreinscription(Date datepreinscription) {
		this.datepreinscription = datepreinscription;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getEmailmere() {
		return this.emailmere;
	}

	public void setEmailmere(String emailmere) {
		this.emailmere = emailmere;
	}


	public String getEmailpere() {
		return this.emailpere;
	}

	public void setEmailpere(String emailpere) {
		this.emailpere = emailpere;
	}


	public String getEmailtuteur() {
		return this.emailtuteur;
	}

	public void setEmailtuteur(String emailtuteur) {
		this.emailtuteur = emailtuteur;
	}


	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}


	public String getLieunaissance() {
		return this.lieunaissance;
	}

	public void setLieunaissance(String lieunaissance) {
		this.lieunaissance = lieunaissance;
	}

	public String getNationalite() {
		return this.nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	//bi-directional many-to-one association to Annee
    @ManyToOne
	@JoinColumn(name="NIVEAU")
	public Niveau getNiveaudemande() {
		return this.niveaudemande;
	}

	public void setNiveaudemande(Niveau niveaudemande) {
		this.niveaudemande = niveaudemande;
	}


	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getNomancienetablissement() {
		return this.nomancienetablissement;
	}

	public void setNomancienetablissement(String nomancienetablissement) {
		this.nomancienetablissement = nomancienetablissement;
	}


	public String getNommere() {
		return this.nommere;
	}

	public void setNommere(String nommere) {
		this.nommere = nommere;
	}


	public String getNompere() {
		return this.nompere;
	}

	public void setNompere(String nompere) {
		this.nompere = nompere;
	}


	public String getNomtuteur() {
		return this.nomtuteur;
	}

	public void setNomtuteur(String nomtuteur) {
		this.nomtuteur = nomtuteur;
	}


	public String getNumeropayement() {
		return this.numeropayement;
	}

	public void setNumeropayement(String numeropayement) {
		this.numeropayement = numeropayement;
	}


	public String getOptiondemande() {
		return this.optiondemande;
	}

	public void setOptiondemande(String optiondemande) {
		this.optiondemande = optiondemande;
	}


	public String getPasswordaccess() {
		return this.passwordaccess;
	}

	public void setPasswordaccess(String passwordaccess) {
		this.passwordaccess = passwordaccess;
	}


	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getProfessionmere() {
		return this.professionmere;
	}

	public void setProfessionmere(String professionmere) {
		this.professionmere = professionmere;
	}


	public String getProfessionpere() {
		return this.professionpere;
	}

	public void setProfessionpere(String professionpere) {
		this.professionpere = professionpere;
	}


	public String getProfessiontuteur() {
		return this.professiontuteur;
	}

	public void setProfessiontuteur(String professiontuteur) {
		this.professiontuteur = professiontuteur;
	}


	public String getSexe() {
		return this.sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
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


	public String getTelephonemere() {
		return this.telephonemere;
	}

	public void setTelephonemere(String telephonemere) {
		this.telephonemere = telephonemere;
	}


	public String getTelephonepere() {
		return this.telephonepere;
	}

	public void setTelephonepere(String telephonepere) {
		this.telephonepere = telephonepere;
	}


	public String getTelephonetuteur() {
		return this.telephonetuteur;
	}

	public void setTelephonetuteur(String telephonetuteur) {
		this.telephonetuteur = telephonetuteur;
	}


	//bi-directional many-to-one association to Utilisateur
    @ManyToOne
	@JoinColumn(name="UTILISATEUR")
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
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


	public double getDernieremoyenne() {
		return dernieremoyenne;
	}


	public void setDernieremoyenne(double dernieremoyenne) {
		this.dernieremoyenne = dernieremoyenne;
	}


	public String getMatricule() {
		return matricule;
	}


	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}


	public float getSommeverse() {
		return sommeverse;
	}


	public void setSommeverse(float sommeverse) {
		this.sommeverse = sommeverse;
	}
	
}