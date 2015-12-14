package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the eleve database table.
 * 
 */
@Entity

//Mes requetes de base sur l'entité
@NamedQueries({ 
	@NamedQuery(name="Eleve.findAll", 
				query="select e from Eleve as e where e.annee.anneeacademique = :annee and e.supprime=:supprime order by e.nom,e.prenom asc"), 
	@NamedQuery(name="Eleve.findAllNonInscrits", 
				query="select e from Eleve as e where e.annee.anneeacademique = :annee and e.classe.codeclasse= :classe and e.supprime=:supprime and e.confirme=:confirme order by e.nom,e.prenom asc"), 
	@NamedQuery(name="Eleve.findAllInscrits", 
				query="select e from Eleve as e where e.annee.anneeacademique = :annee and e.classe.codeclasse= :classe and e.supprime=:supprime and e.confirme=:confirme order by e.nom,e.prenom asc"), 
	@NamedQuery(name="Eleve.findByMatricule", 
				query="select e from Eleve as e where e.annee.anneeacademique = :annee and e.supprime=:supprime and e.matricule=:matricule"), 
	@NamedQuery(name="Eleve.findByName", 
				query="select e from Eleve as e where e.annee.anneeacademique = :annee and e.supprime=:supprime and (e.nom LIKE :nom or e.prenom LIKE :nom) order by e.nom,e.prenom asc"),
	@NamedQuery(name="Eleve.findAllFromClasse", 
				query="select e from Eleve as e where e.annee.anneeacademique = :annee and e.supprime=:supprime and e.classe.codeclasse= :classe order by e.nom,e.prenom asc"),
	@NamedQuery(name="Eleve.findAllFromClasseID", 
				query="select e.ideleve from Eleve as e where e.annee.anneeacademique = :annee and e.supprime=:supprime and e.classe.codeclasse= :classe order by e.nom,e.prenom asc"),
	@NamedQuery(name="Eleve.findProvisoireFromClasse", 
				query="select e from Eleve as e where e.annee.anneeacademique = :annee and e.supprime=:supprime and e.classe.codeclasse= :classe order by e.nom,e.prenom asc"),
	@NamedQuery(name="Eleve.findERFromClasse", 
				query="select e from Eleve as e where e.classe.codeclasse=:classe and ((( select sum(v.montant) from Versement v where v.annee.anneeacademique=:annee and v.eleve=e and v.supprime=:supprime)>=(select sum (pt.montant) from ParametreTranche as pt where pt.classe=e.classe and pt.annee.anneeacademique=:annee and pt.supprime=:supprime and pt.delaiversement <= :date)) OR ((select sum (pt.montant) from ParametreTranche as pt where pt.classe=e.classe and pt.annee.anneeacademique=:annee and pt.supprime=:supprime and pt.delaiversement <= :date)) IS NULL)"),
	@NamedQuery(name="Eleve.findNERFromClasse", 
				query="select e from Eleve as e where e.classe.codeclasse=:classe and (( select sum(v.montant) from Versement v where v.annee.anneeacademique=:annee and v.eleve=e and v.supprime=:supprime)<(select sum (pt.montant) from ParametreTranche as pt where pt.classe=e.classe and pt.annee.anneeacademique=:annee and pt.supprime=:supprime and pt.delaiversement <= :date))"),
	@NamedQuery(name="Eleve.findJustifiesEvaluation", 
				query="select e from Eleve as e join e.composers as c where e.classe.codeclasse=:classe and e.annee.anneeacademique=:annee and c.evaluation.codeevaluation=:codeevaluation and c.supprime=:supprime and c.absencejustifiee=:justifie and c.evaluation.sequence.idsequence=:sequence"),
	@NamedQuery(name="Eleve.findElevesNotesEvalues", 
				query="select e, c.note, c.absencejustifiee from Eleve as e left join e.composers as c where e.annee.anneeacademique=:annee and c.evaluation.codeevaluation=:codeevaluation and c.supprime=:supprime and e.supprime=:supprime and e.classe=c.evaluation.cour.classe ORDER BY e.nom, e.prenom"),
	@NamedQuery(name="Eleve.findElevesNonEvalues", 
				query="select e from Eleve as e where e.annee.anneeacademique=:annee and e.supprime=:supprime and (not exists (select c.note from Composer as c where c.eleve=e and c.evaluation.codeevaluation=:evaluation and c.supprime=:supprime)) and e.classe=(select cl from Evaluation ev join ev.cour cr join cr.classe cl where cr.annee.anneeacademique=:annee and ev.codeevaluation=:evaluation) ORDER BY e.nom, e.prenom"),
	@NamedQuery(name="Eleve.findById", 
				query="select e from Eleve as e where e.annee.anneeacademique = :annee and e.supprime=:supprime and e.ideleve=:ideleve"), 
	@NamedQuery(name="Eleve.count", 
				query="select count(e) from Eleve as e where  e.classe.codeclasse=:codeclasse and e.annee.anneeacademique = :annee and e.supprime=:supprime"),
	
})
public class Eleve implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private int ideleve;
	
	private String matricule;
	private String adresse;
	private boolean ancien;
	private String anneeancienetablissement;
	private String boitepostale;
	private String classeancienetablissement;
	private boolean confirme;
	private Date datenaissance;
	private Date dateEnregistrement;
	private String email;
	private String emailmere;
	private String emailpere;
	private String emailtuteur;
	private String lieunaissance;
	private String login;
	private String nationalite;
	private String nom;
	private String nomancienetablissment;
	private String nommere;
	private String nompere;
	private String nomtuteur;
	private String password;
	private String photo;
	private String prenom;
	private String professionmere;
	private String professionpere;
	private String professiontuteur;
	private boolean redoublant;
	private String sexe;
	private boolean supprime;
	private String telephone;
	private String telephonemere;
	private String telephonepere;
	private String telephonetuteur;
	private double dernieremoyenne;
	private List<Absence> absences;
	private List<Composer> composers;
	private Annee annee;
	private Classe classe;
	private List<Retard> retards;
	private List<Sanction> sanctions;
	private String numeroPayement;
	private List<Versement> versements;
	//private EtudCoursPK id;

    public Eleve() {
    }

    
    /*@EmbeddedId
	public EtudCoursPK getId() {
		return id;
	}


	public void setId(EtudCoursPK id) {
		this.id = id;
	}
*/

	/**
	 * @return the ideleve
	 */
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdeleve() {
		return ideleve;
	}


	/**
	 * @param ideleve the ideleve to set
	 */
	public void setIdeleve(int ideleve) {
		this.ideleve = ideleve;
	}


	@Column(nullable=false,unique=true)
	public String getMatricule() {
		return this.matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}


	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public boolean getAncien() {
		return this.ancien;
	}

	public void setAncien(boolean ancien) {
		this.ancien = ancien;
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


	public boolean getConfirme() {
		return this.confirme;
	}

	public void setConfirme(boolean confirme) {
		this.confirme = confirme;
	}


    @Temporal( TemporalType.DATE)
	public Date getDatenaissance() {
		return this.datenaissance;
	}

	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
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


	public String getLieunaissance() {
		return this.lieunaissance;
	}

	public void setLieunaissance(String lieunaissance) {
		this.lieunaissance = lieunaissance;
	}


	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}


	public String getNationalite() {
		return this.nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}


	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getNomancienetablissment() {
		return this.nomancienetablissment;
	}

	public void setNomancienetablissment(String nomancienetablissment) {
		this.nomancienetablissment = nomancienetablissment;
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


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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


	public boolean getRedoublant() {
		return this.redoublant;
	}

	public void setRedoublant(boolean redoublant) {
		this.redoublant = redoublant;
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


	//bi-directional many-to-one association to Absence
	@OneToMany(mappedBy="eleve")
	public List<Absence> getAbsences() {
		return this.absences;
	}

	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}
	

	//bi-directional many-to-one association to Composer
	@OneToMany(mappedBy="eleve")
	public List<Composer> getComposers() {
		return this.composers;
	}

	public void setComposers(List<Composer> composers) {
		this.composers = composers;
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
	

	//bi-directional many-to-one association to Classe
    @ManyToOne
	@JoinColumn(name="CODECLASSE")
	public Classe getClasse() {
		return this.classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	

	//bi-directional many-to-one association to Retard
	@OneToMany(mappedBy="eleve")
	public List<Retard> getRetards() {
		return this.retards;
	}

	public void setRetards(List<Retard> retards) {
		this.retards = retards;
	}
	

	//bi-directional many-to-one association to Sanction
	@OneToMany(mappedBy="eleve")
	public List<Sanction> getSanctions() {
		return this.sanctions;
	}

	public void setSanctions(List<Sanction> sanctions) {
		this.sanctions = sanctions;
	}
	
	@Temporal( TemporalType.DATE)
	public Date getDateEnregistrement() {
		return dateEnregistrement;
	}


	public void setDateEnregistrement(Date dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}


	public String getNumeroPayement() {
		return numeroPayement;
	}


	public void setNumeroPayement(String numeroPayement) {
		this.numeroPayement = numeroPayement;
	}

	//bi-directional many-to-one association to AnneeEnCour
	@OneToMany(mappedBy="eleve")
	public List<Versement> getVersements() {
		return versements;
	}


	public void setVersements(List<Versement> versements) {
		this.versements = versements;
	}


	public double getDernieremoyenne() {
		return dernieremoyenne;
	}


	public void setDernieremoyenne(double dernieremoyenne) {
		this.dernieremoyenne = dernieremoyenne;
	}
	
}