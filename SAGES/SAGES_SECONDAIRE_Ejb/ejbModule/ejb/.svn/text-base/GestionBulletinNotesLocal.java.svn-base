package ejb;

import java.util.List;

import javax.ejb.Local;

import entities.Classe;
import entities.Sequence;



@Local

public interface GestionBulletinNotesLocal {
	
	public int rangEleveMatiere(String codematiere, String codeclasse,String matricule, String codeannee);
	
	public int rangEleveClasse(String codeclasse,int matricule,int seq, String annee);
	
	public int rangEleveClasseTrim(String codeclasse,int matricule,int seq1, int seq2);
	
	public double moyenneEleve(String codeclasse, int matricule, int seq,String annee);
	
	public double moyennePremierTrim(String Codeclasse, int seq1, int seq2);
	
	public double moyenneDernierTrim(String codeclasse, int seq1, int seq2);

	public double moyenneEleveTrim(String codeclasse, int matricule, int seq1, int seq2,String annee);
	
	public double moyenneEleveTrimDirect(String codeclasse, int matricule, int seq1, int seq2);
	
	public double moyenneClasseTrim(String annee,Classe classe,  Sequence sequence1, Sequence sequence2);
	
	public double totalpoint(String annee,String codeclasse,int matricule, int seq);
	
	public double totalpointTrim(String annee,String codeclasse,int matricule,  int seq1, int seq2);
	
	public long totalcoef(String codeclasse, String annee);
	public long totalcoefEleve(String codeclasse, int matricule,int seq);
	
	public float tauxreussite(String codeclasse,  int seq1,String annee);
	
	public float tauxreussitetrim(String codeclasse,  int seq1, int seq2);
	
	public int nombrereussite(String codeclasse, int seq,String annee) ;
	
	public int nombrereussiteTrim(String codeclasse, int seq1, int seq2) ;
	
	public int blame( Sequence seq1, int matricule);
	
	public int avertissement(Sequence seq1, int matricule);
	
	public int retard(String codeclasse,Sequence seq1, int matricule, String annee);
	
	public int absence(String codeclasse,Sequence seq1, int matricule, String annee);
	
	public int exclusion(Sequence seq1, int matricule);
	
	
	public int effectif(String classe, String annee);
	
	public double moyennePremierAnnuel(String codeclasse, int trim1, int trim2, int trim3);
	
	public double moyenneDernierAnnuel(String codeclasse, int trim1, int trim2, int trim3);
	
	public int nombrereussiteAnnuel(String codeclasse, int trim1, int trim2, int trim3);
	
	public float tauxreussiteAnnuel(String codeclasse, int trim1, int trim2, int trim3) ;
	
	public double moyenneEleveAnnuelDirect(String annee,String codeclasse, int matricule,
			int trim1, int trim2, int trim3);
	
	public int rangEleveClasseAnnuel(String annee,String codeclasse, int matricule, int trim1,
			int trim2, int trim3);
	
	public double moyenneClasseAnnuelDirect(String annee,String codeclasse,  int trim1, int trim2, int trim3);
	
	public double moyenneClasseAnnuel(String codeclasse,
			int trim1, int trim2, int trim3);
	public double totalpointAnnuel(String annee,String codeclasse,int matricule, int trim1, int trim2, int trim3);

	public float tauxClasse(String anneeEncours, Classe classe, Sequence seq);

	public double moyenneClasse(String anneeEncours, Classe classe, Sequence seq);

	public double moyennePremier(String anneeEncours, Classe clas, Sequence seq);

	public double moyenneDernier(String anneeEncours, Classe clas, Sequence seq);

	public double moyenneClasseTrimDirect(String anneeEncours, Classe classe,
			Sequence sequence1, Sequence sequence2);

	public double moyennePremier(String anneeEncours, Classe clas,
			Sequence sequence1, Sequence sequence2);

	public double moyenneDernier(String anneeEncours, Classe clas,
			Sequence sequence1, Sequence sequence2);


	public double moyenneClasseAnnuel(String anneeEncours, Classe clas, int i,
			int j, int k);

	public double moyenneDernierAnnuel(String anneeEncours, Classe clas, int i,
			int j, int k);

	public double moyennePremierAnnuel(String anneeEncours, Classe clas, int i,
			int j, int k);

	public int tauxreussitetrim(String anneeEncours, int sequence1,
			int sequence2, String string);
	
	public float tauxreussitetrim(String anneeEncours, Classe classe,
			Sequence sequence1, Sequence sequence2);
	
	public float tauxreussiteAnnuel(String anneeEncours, Classe clas, int trim1,
			int trim2, int trim3);

	public int tauxreussiteAnnuel(String anneeEncours, int trim1, int i,
			int j, String string);
	
	public double tauxreussitetrim1(String codeclasse, 
			int seq1, int seq2,String annee);
	
	public float tauxreussiteAnnuel1(String codeclasse, int trim1,
			int trim2, int trim3,String annee);
	public List<Object[]> listerElevesNotesClasse(int numerosequence, String codeclasse,String annee);

	public List<Object[]> listerElevesNotesClasseTrim(int trimestre,
			int i, int j, String codeclasse, String anneeAcEncours);


	public double moyenneEleveS(String codeclasse, int matricule,String annee);

	public double moyenneClasseS(String annee,String codeclasse);
	
	public double totalpointS(String annee,String codeclasse, int matricule) ;
	public double totalpointG(String annee,String codeclasse, int matricule,int seq1,int seq2,int seq3,
			int seq4,int seq5,int seq6,int seq7,int seq8);
	
	public int rangEleveClasseS(String codeclasse, int matricule,String annee);
	
	public double moyennePremierS(String anneeEncours, Classe clas);
	
	public double moyenneDernierS(String anneeEncours, Classe clas);
	
	public double tauxreussiteS(String codeclasse, String annee);
	
	public int rangEleveClasseAnnuelN(String annee,String codeclasse, int matricule, int seq1,
			int seq2, int seq3, int seq4, int seq5, int seq6, int seq7, int seq8);
	
	public double moyenneEleveAnnuelDirectN(String annee,String codeclasse, int matricule,
			int seq1, int seq2, int seq3, int seq4, int seq5, int seq6, int seq7, int seq8);
	
	public long totalcoefEleveS(String codeclasse, int matricule);
	
	public int blameS(int matricule);
	
	public int avertissementS(int matricule);
	
	
	public int retardS( String codeclasse, int matricule, String date1, String date2);
	
	public int absenceS( String codeclasse, int matricule, String date1, String date2);
	
	public int exclusionS(int matricule,String date) ;

	double moyennePremierAnnuelN(String annee, String codeclasse, int seq1,
			int seq2, int seq3, int seq4, int seq5, int seq6, int seq7, int seq8);

}
