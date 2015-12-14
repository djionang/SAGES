package ages.beans.application;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;

import utils.Service;

import ages.beans.GenericBean;
import ages.beans.etablissement.NiveauBean;
import ages.beans.etablissement.OptionBean;
import ages.beans.etablissement.classe.ClasseBean;
import ages.beans.etablissement.salle.TypeSalleBean;


/**
 * Configuration de l'application
 * Vérifie au chargement la présence de certaines entités dans la base de données
 * Comme: 
 * l'option par défaut
 * @author Brilswear
 *
 */
@ManagedBean(name="configuration")
@ApplicationScoped
public class ConfigurationBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static String imgpath="/upimages";
	
	public  static int largeur_logoEtab=75;
	
	public static int hauteur_logoEtab=75;
	
	public  static int largeur_photo=75;
	
	public static int hauteur_photo=75;
	
	private String agesLocale;
	
	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	//selection de sexe
	private SelectItem[] sexeItems={
			new SelectItem("masculin", "Masculin"),
		    new SelectItem("feminin", "Feminin")
	};
	
	private SelectItem[] civiliteItems={
			new SelectItem("celibataire", "Célibataire"),
		    new SelectItem("marie", "Marié"),
		    new SelectItem("divorce", "Divorcé"),
		    new SelectItem("", "--")
	};
	
	private SelectItem[] fonctionItems={
			new SelectItem("Censeur", "Censeur"),
			new SelectItem("Enseignant", "Enseignant"),
		    new SelectItem("Principal", "Principal"),
		    new SelectItem("Surveillant", "Surveillant"),
		    new SelectItem("Intendant", "Intendant"),
		    new SelectItem("P.A.", "P.A.")
	};
	
	private SelectItem[] typematerielItems={
			new SelectItem("Consomable", "Consomable"),
			new SelectItem("Non Consomable", "Non Consomable")
	};
	
	//etats possibles d'un eleve
	private SelectItem[] etatEleveItems={
			new SelectItem(false, "Non Redoublant"),
		    new SelectItem(true, "Redoublant")
	};
	
	//selection d'options
	private SelectItem[] optionItems;
	
	//selection de niveaux
	private SelectItem[] niveauItems;
	
	//selection des types de tranches
	private SelectItem[] typesTranchesItems;
	
	//selection des types de tranches
	private SelectItem[] typesPrevisionItems;
	
	private SelectItem[] annee;
	
	private SelectItem[] anneeadmin;
	
	//selection des types de tranches
	private SelectItem[] typesResponsabilite;
	
	private SelectItem[] applicationStateItems={
			new SelectItem("", "----"),
			new SelectItem("en cours", "En cours"),
		    new SelectItem("accepte", "Accepté"),
		    new SelectItem("rejette", "Rejetté")
	};
	
	
	private SelectItem[] etatDossierItems={
			new SelectItem("en cours", "En cours"),
		    new SelectItem("accepte", "Accepté"),
		    new SelectItem("rejette", "Rejetté")
	};
	
	private SelectItem[] typeSalleItems;
	
	private SelectItem[]  rechercheelevesItems={
			new SelectItem("matricule", "Matricule"),
		    new SelectItem("nom", "Nom"),
		    new SelectItem("classe", "Classe")
	};
	
	private String datejour;
	private static SimpleDateFormat agesDateFormat=new SimpleDateFormat("dd/MM/yyyy");
	private Locale locale=Locale.FRANCE; 
	
	
	//Options pour filtrer selon la	classe
	private SelectItem[] classeOption;
	
	private SelectItem[] etablissementItems;
	
	private SelectItem[] enseignementItems;
	
	

	private SelectItem[] cycleItems;
	
	private SelectItem[] sectionItems;
	
	public ConfigurationBean(){
		
	}
	
	
	public SelectItem[] getOptionItems() {
		//to remove
		initOptions();
		return optionItems;
	}


	public void setOptionItems(SelectItem[] optionItems) {
		this.optionItems = optionItems;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public SelectItem[] getNiveauItems() {
		//to remove
		initNiveaux();
		return niveauItems;
	}


	public void setNiveauItems(SelectItem[] niveauItems) {
		this.niveauItems = niveauItems;
	}

	
	
	public SelectItem[] getEtatDossierItems() {
		return etatDossierItems;
	}


	public void setEtatDossierItems(SelectItem[] etatDossierItems) {
		this.etatDossierItems = etatDossierItems;
	}

	public SelectItem[] getSexeItems() {
		return sexeItems;
	}

	public void setSexeItems(SelectItem[] sexeItems) {
		this.sexeItems = sexeItems;
	}

	public String getDatejour() {
		datejour=agesDateFormat.format(new Date());
		return datejour;
	}

	public void setDatejour(String datejour) {
		this.datejour = datejour;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}



	public SelectItem[] getApplicationStateItems() {
		return applicationStateItems;
	}



	public void setApplicationStateItems(SelectItem[] applicationStateItems) {
		this.applicationStateItems = applicationStateItems;
	}


	public SelectItem[] getRechercheelevesItems() {
		return rechercheelevesItems;
	}


	public void setRechercheelevesItems(SelectItem[] rechercheelevesItems) {
		this.rechercheelevesItems = rechercheelevesItems;
	}
	
	public SelectItem[] getTypeSalleItems() {
		//to remove
		initTypeSalleItems();
		return typeSalleItems;
	}


	public void setTypeSalleItems(SelectItem[] typeSalleItems) {
		this.typeSalleItems = typeSalleItems;
	}


	public static SimpleDateFormat getAgesDateFormat() {
		return agesDateFormat;
	}


	public static void setAgesDateFormat(SimpleDateFormat agesDateFormat) {
		ConfigurationBean.agesDateFormat = agesDateFormat;
	}


	public SelectItem[] getClasseOption() {
		//to remove
		initClasseOption();
		return classeOption;
	}


	public void setClasseOption(SelectItem[] classeOption) {
		this.classeOption = classeOption;
	}


	public SelectItem[] getEtatEleveItems() {
		return etatEleveItems;
	}


	public void setEtatEleveItems(SelectItem[] etatEleveItems) {
		this.etatEleveItems = etatEleveItems;
	}


	public SelectItem[] getTypesTranchesItems() {
		//to remove
		initTypeTranche();
		return typesTranchesItems;
	}


	public void setTypesTranchesItems(SelectItem[] typesTranchesItems) {
		this.typesTranchesItems = typesTranchesItems;
	}
	
	public SelectItem[] getEnseignementItems() {
		initEnseignementsItems();
		return enseignementItems;
	}




	public void setEnseignementItems(SelectItem[] enseignementItems) {
		this.enseignementItems = enseignementItems;
	}

	


	public String getAgesLocale() {
		if(agesLocale==null)
			agesLocale="fr";
		return agesLocale;
	}


	public void setAgesLocale(String agesLocale) {
		this.agesLocale = agesLocale;
	}




	public SelectItem[] getEtablissementItems() {
		//to remove
		initEtablissementItems();
		return etablissementItems;
	}


	public void setEtablissementItems(SelectItem[] etablissementItems) {
		this.etablissementItems = etablissementItems;
	}

	public SelectItem[] getCycleItems() {
		//to remove
		initCycleItems();
		return cycleItems;
	}




	public void setCycleItems(SelectItem[] cycleItems) {
		this.cycleItems = cycleItems;
	}




	public SelectItem[] getSectionItems() {
		initsectionItems();
		return sectionItems;
	}




	public void setSectionItems(SelectItem[] sectionItems) {
		this.sectionItems = sectionItems;
	}

public SelectItem[] getCiviliteItems() {
		return civiliteItems;
	}




	public void setCiviliteItems(SelectItem[] civiliteItems) {
		this.civiliteItems = civiliteItems;
	}




	public SelectItem[] getFonctionItems() {
		return fonctionItems;
	}




	public void setFonctionItems(SelectItem[] fonctionItems) {
		this.fonctionItems = fonctionItems;
	}




	
	public SelectItem[] getTypesResponsabilite() {
		initTypeResponsabilite();
		return typesResponsabilite;
	}


	public void setTypesResponsabilite(SelectItem[] typesResponsabilite) {
		this.typesResponsabilite = typesResponsabilite;
	}


	public SelectItem[] getAnnee() {
		initAnnee();
		return annee;
	}


	public void setAnnee(SelectItem[] annee) {
		this.annee = annee;
	}


	public SelectItem[] getAnneeadmin() {
		initAnneeAdmin();
		return anneeadmin;
	}


	public void setAnneeadmin(SelectItem[] anneeadmin) {
		this.anneeadmin = anneeadmin;
	}


	public SelectItem[] getTypesPrevisionItems() {
		initTypePrevision();
		return typesPrevisionItems;
	}


	public void setTypesPrevisionItems(SelectItem[] typesPrevisionItems) {
		this.typesPrevisionItems = typesPrevisionItems;
	}


	/**
	 * Initialise les propriétés de ConfigurationBean
	 */
	@PostConstruct
	protected void init(){		
		this.service.rechercherConfigurationApplication();
	}
	
	public void initsectionItems() {
		List<Object[]> listesections=this.service.listerCodessections();
		if((listesections==null)||(listesections.isEmpty())){ 
			sectionItems=new SelectItem[0];
			return;
		}
		
		// recupere les etablissements et fabrique la liste des options d'etablissements
		sectionItems=new SelectItem[listesections.size()];
		for(int i=0;i<listesections.size();i++){
			sectionItems[i]=new SelectItem(listesections.get(i)[0].toString(), listesections.get(i)[1].toString());
		}
		
	}




	public void initCycleItems() {
		List<Object[]> listecycles=this.service.listerCodesCycles();
		if((listecycles==null)||(listecycles.isEmpty())){ 
			cycleItems=new SelectItem[0];
			return;
		}
		
		// recupere les etablissements et fabrique la liste des options d'etablissements
		cycleItems=new SelectItem[listecycles.size()];
		for(int i=0;i<listecycles.size();i++){
			cycleItems[i]=new SelectItem(listecycles.get(i)[0].toString(), listecycles.get(i)[1].toString());
		}
		
	}




	public void initEnseignementsItems() {
		List<String> listens=this.service.listerCodesEnseignements();
		if((listens==null)||(listens.isEmpty())){ 
			enseignementItems=new SelectItem[0];
			return;
		}
		
		// recupere les enseignements et fabrique la liste des options d"enseignements
		enseignementItems=new SelectItem[listens.size()];
		for(int i=0;i<listens.size();i++){
			enseignementItems[i]=new SelectItem(listens.get(i), listens.get(i));
		}
		
	}




	public void initEtablissementItems() {
		// TODO Auto-generated method stub
		List<Object[]> listeEtabs=this.service.listerCodesEtab();
		if((listeEtabs==null)||(listeEtabs.isEmpty())){ 
			etablissementItems=new SelectItem[0];
			return;
		}
		
		// recupere les etablissements et fabrique la liste des options d'etablissements
		etablissementItems=new SelectItem[listeEtabs.size()];
		for(int i=0;i<listeEtabs.size();i++){
			etablissementItems[i]=new SelectItem((listeEtabs.get(i)[0]).toString(), (listeEtabs.get(i)[1]).toString());
		}
	}




	public void initTypeTranche() {
		List<String> types=this.service.listeTypeTranches();
		
		
		// si la liste des classes est nulle ou vide, on retourne une liste d'options vide
		if(types==null){ 
			typesTranchesItems=new SelectItem[0];
			return;
		}
		if(types.isEmpty()){ 
			typesTranchesItems=new SelectItem[0];
			return;
		}
		
		// recupere les classes et fabrique la liste des options de classe
		typesTranchesItems=new SelectItem[types.size()];
		for(int i=0;i<types.size();i++){
			typesTranchesItems[i]=new SelectItem(types.get(i), types.get(i));
		}
		
	}
	
	public void initTypePrevision() {
		List<String> types=this.service.listeTypePrevision();
		
		
		// si la liste des classes est nulle ou vide, on retourne une liste d'options vide
		if(types==null){ 
			typesPrevisionItems=new SelectItem[0];
			return;
		}
		if(types.isEmpty()){ 
			typesPrevisionItems=new SelectItem[0];
			return;
		}
		
		// recupere les classes et fabrique la liste des options de classe
		typesPrevisionItems=new SelectItem[types.size()];
		for(int i=0;i<types.size();i++){
			typesPrevisionItems[i]=new SelectItem(types.get(i), types.get(i));
		}
		
	}
	
	public void initAnnee() {
		List<String> an=this.service.listeTypeAnnee();
		
		
		// si la liste des classes est nulle ou vide, on retourne une liste d'options vide
		if(an==null){ 
			annee=new SelectItem[0];
			return;
		}
		if(an.isEmpty()){ 
			annee=new SelectItem[0];
			return;
		}
		
		// recupere les classes et fabrique la liste des options de classe
		annee=new SelectItem[an.size()];
		for(int i=0;i<an.size();i++){
			annee[i]=new SelectItem(an.get(i), an.get(i));
		}
		
	}
	
	public void initAnneeAdmin() {
		List<String> an=this.service.listeTypeAnneeAdmin();
		
		
		// si la liste des classes est nulle ou vide, on retourne une liste d'options vide
		if(an==null){ 
			anneeadmin=new SelectItem[0];
			return;
		}
		if(an.isEmpty()){ 
			anneeadmin=new SelectItem[0];
			return;
		}
		
		// recupere les classes et fabrique la liste des options de classe
		anneeadmin=new SelectItem[an.size()];
		for(int i=0;i<an.size();i++){
			anneeadmin[i]=new SelectItem(an.get(i), an.get(i));
		}
		
	}
	
	public void initTypeResponsabilite() {
		List<String> types=this.service.listeResponsabilite();
		
		// si la liste des classes est nulle ou vide, on retourne une liste d'options vide
		if(types==null){ 
			typesResponsabilite=new SelectItem[0];
			return;
		}
		if(types.isEmpty()){ 
			typesResponsabilite=new SelectItem[0];
			return;
		}
		
		// recupere les classes et fabrique la liste des options de classe
		typesResponsabilite=new SelectItem[types.size()];
		for(int i=0;i<types.size();i++){
			typesResponsabilite[i]=new SelectItem(types.get(i), types.get(i));
		}
		
	}


	/**
	 * Initialise les types de salles, avec des types par défaut et d'autres enregistrés dans la BD
	 */
	public void initTypeSalleItems(){
		
		// ajoutons a ces types pas défaut les types déja enrehistrés dans la BD 
		List<TypeSalleBean> typesEnregistres=this.service.listerTypesalle();
		
		if(typesEnregistres==null){ 
			typeSalleItems=new SelectItem[0];
			return;
		}
		if(typesEnregistres.isEmpty()){ 
			typeSalleItems=new SelectItem[0];
			return;
		}
		
		typeSalleItems=new SelectItem[typesEnregistres.size()];
		for(int i=0;i<typesEnregistres.size();i++){
			typeSalleItems[i]=new SelectItem(typesEnregistres.get(i).getId(),typesEnregistres.get(i).getLibelle());
			
		}
		
	}
	
	
	/**
	 * Initialise la liste des options pour le filtre selon la classe
	 */
	public void initClasseOption(){
		List<ClasseBean> classes=this.service.listeclasses();
		
		// si la liste des classes est nulle ou vide, on retourne une liste d'options vide
		if(classes==null){ 
			classeOption=new SelectItem[0];
			return;
		}
		if(classes.isEmpty()){ 
			classeOption=new SelectItem[0];
			return;
		}
		
		// recupere les classes et fabrique la liste des options de classe
		classeOption=new SelectItem[classes.size()];
		for(int i=0;i<classes.size();i++){
			classeOption[i]=new SelectItem(classes.get(i).getCodeClasse(), classes.get(i).getLibelle());
		}
			
	}
	
	
	/**
	 * Initialise la liste des options disponibles pour differentes classes
	 */
	public void initOptions(){
		List<OptionBean> listeOptions=this.service.listerOptions();
		
		if(listeOptions==null){
			optionItems=new SelectItem[0];
			return;
		}
		if(listeOptions.isEmpty()){ 
			optionItems=new SelectItem[0];
			return;
		}
		
		// recupere les options et fabrique la liste des options
		optionItems=new SelectItem[listeOptions.size()];
		for(int i=0;i<listeOptions.size();i++){
			optionItems[i]=new SelectItem(listeOptions.get(i).getCodeOption(), listeOptions.get(i).getLibelle()+" ("+listeOptions.get(i).getCodeniveau()+")");
		}
	}
	
	
	/**
	 * Initialise la liste des niveaux disponibles
	 */
	public void initNiveaux(){
		List<NiveauBean> listeNiveaux=this.service.listerNiveaux();
		
		if(listeNiveaux==null){
			niveauItems=new SelectItem[0];
			return;
		}
		if(listeNiveaux.isEmpty()){ 
			niveauItems=new SelectItem[0];
			return;
		}
		
		// recupere les niveaux et fabrique la liste des niveaux
		niveauItems=new SelectItem[listeNiveaux.size()];
		for(int i=0;i<listeNiveaux.size();i++){
			niveauItems[i]=new SelectItem(listeNiveaux.get(i).getCodeNiveau(), listeNiveaux.get(i).getLibelle());
		}
	}


	public SelectItem[] getTypematerielItems() {
		return typematerielItems;
	}


	public void setTypematerielItems(SelectItem[] typematerielItems) {
		this.typematerielItems = typematerielItems;
	}
		
}
