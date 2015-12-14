package ejb.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ejb.GestionBulletinNotesLocal;
import entities.Classe;
import entities.Sequence;



/**
 * Session Bean implementation class GestionBulletin
 * Classe de gestion des buletin
 * @author Berlin et brilland
 */
@Stateless(mappedName = "GestionBulletinNotes")
@TransactionManagement(TransactionManagementType.BEAN)
public class GestionBulletinNotes implements GestionBulletinNotesLocal{
	
	@PersistenceContext(unitName="agespersist")
	EntityManager em;

	@Override
	public int rangEleveMatiere(String codematiere, String codeclasse,String matricule,
			String codeannee) {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("agespersist");
		EntityManager emtrans=emf.createEntityManager();
	
		@SuppressWarnings("unused")
		Query query;
		
		emtrans.getTransaction().begin();
			
			List<String> le = new ArrayList<String>();
			String e = "abc";
			le.add (e);
			
			Collections.sort(le);
            
            
			//Iterator<Composer> it = le.iterator();
			//while (it.hasNext()) {
			
			//}
			
			//query = emtrans.createQuery("SELECT ");
		
		return le.indexOf(e);
	}

	@Override
	public int rangEleveClasse(String codeclasse, int matricule, int seq,String annee) {
		try{
			Query req = em.createNativeQuery("select count(query3.moyenne) as rang from (select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme, sum(cours.coeficient) as sommecoef from"
	               +" cours, evaluation, composer, sequence,classe,annee where"
	               +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
	               +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
	               +"  sequence.idsequence and sequence.numerosequence='"+seq+"'"
	               +" and cours.codeclasse=classe.codeclasse and cours.anneeacademique=annee.anneeacademique and cours.anneeacademique='"+annee+"'" +
	               " and classe.codeclasse='"+codeclasse+"'  group by"
	               +" composer.ideleve) as query1 ) as query2 group by query2.ideleve order by moyenne asc) as query3 where query3.moyenne>='"+moyenneEleve(codeclasse,matricule,seq,annee)+"'");
				
			return Integer.parseInt(req.getSingleResult().toString());

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}

	
	@SuppressWarnings("unused")
	public static void main(String[] args){
		GestionBulletinNotes gbn = new GestionBulletinNotes();
//		gbn.moyenneEleve("sixi", 1262, 1);                            
	}


	@Override
	public int effectif(String codeclasse,String annee) {
		int effect;
		try{
			Query req=em.createNamedQuery("Eleve.count");
			req.setParameter("codeclasse", codeclasse);
			req.setParameter("annee", annee);
			req.setParameter("supprime", false);
		    effect=Integer.parseInt(req.getSingleResult().toString()) ;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return effect;
	}

	@Override
	public double moyenneEleve(String codeclasse, int matricule, int seq,String annee) {
		try{
		Query req = em.createNativeQuery("select ROUND(query2.moyenne,2) as moyenne "
		+" from  (select somme/sommecoef as moyenne , query1.somme, query1.sommecoef from"
		+" (select sum(note*cours.coeficient) as somme, sum(cours.coeficient) as sommecoef from"
		+" cours, evaluation, composer, sequence,classe,eleve where cours.codecours=evaluation.codecours and cours.supprime=false and composer.absencejustifiee=false"
		+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
        +" sequence.idsequence and sequence.numerosequence='"+seq+"' and composer.ideleve='"+matricule+"'"
        +" and eleve.codeclasse=classe.codeclasse and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"') as query1) as query2");
		
		return Double.parseDouble(req.getSingleResult().toString()) ;

		}catch(Exception e){
		e.printStackTrace();
		return 0;
		}
        
       
	}

	public double moyenneClasse(String codeclasse, int seq) {
		try{
		Query req = em.createNativeQuery("select ROUND(query3.moyclasse,2) as moyclasse from (select avg(query2.moyenne) as moyclasse from  (select"
          +" somme/sommecoef as moyenne , query1.somme, query1.sommecoef from"
          +" (select distinct composer.ideleve,sum(note*cours.coeficient) as somme, sum(cours.coeficient) as sommecoef from"
            +"  cours, evaluation, composer, sequence,classe where"
            +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
            +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
            +" sequence.idsequence and sequence.numerosequence='"+seq+"'"
            +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
            +" composer.ideleve) as query1)as query2) as query3");
		return Double.parseDouble(req.getSingleResult().toString()) ;

		}catch(Exception e){
		e.printStackTrace();
		return 0;
	}
	}

	@Override
	public double moyenneEleveTrim(String codeclasse, int matricule,
			int seq1, int seq2,String annee) {
		double moyennetrim;
		moyennetrim = (moyenneEleve(codeclasse,matricule,seq1,annee)+ moyenneEleve(codeclasse,matricule,seq2,annee))/2;
		
		return moyennetrim;
	}

	@Override
	public double totalpoint(String annee,String codeclasse, int matricule,int seq) {
		try{
			Query req = em.createNativeQuery("SELECT sum(note*cours.coeficient) as somme from"
					+" cours, classe,composer, evaluation, sequence"
					+" where cours.codeclasse=classe.codeclasse and cours.codecours=evaluation.codecours and composer.absencejustifiee=false and"
					+" evaluation.codeevaluation=composer.codeevaluation and cours.supprime=false and evaluation.idsequence=sequence.idsequence"
					+" and cours.anneeacademique='"+annee+"'"
					+" and sequence.anneeacademique='"+annee+"'and sequence.numerosequence='"+seq+"' and composer.ideleve='"+matricule+"' and"
					+" classe.codeclasse='"+codeclasse+"'");
			return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
			e.printStackTrace();
			return 0;
			}
	}

	@Override
	public double totalpointTrim(String annee,String codeclasse,int matricule, int seq1, int seq2) {
		double total;
		 total = (totalpoint(annee,codeclasse,matricule,seq1) + totalpoint(annee,codeclasse,matricule,seq2))/2;
		 return total;
	}

	@Override
	public long totalcoef(String codeclasse,String annee) {
		try{
			Query req = em.createNativeQuery("SELECT sum(cours.coeficient) as sommecoef from cours, classe"
              +" where cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"' and cours.anneeacademique='"+annee+"' and cours.supprime=false");
			return Long.parseLong(req.getSingleResult().toString()) ;

			}catch(Exception e){
			e.printStackTrace();
			return 0;
			}
	}
	
	public long totalcoefEleve(String codeclasse, int matricule,int seq) {
		try{
			Query req = em.createNativeQuery("SELECT sum(cours.coeficient) as sommecoef from"
					+" cours, classe,composer, evaluation, sequence"
					+" where cours.codeclasse=classe.codeclasse and cours.codecours=evaluation.codecours and composer.absencejustifiee=false and"
					+" evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence=sequence.idsequence"
					+" and sequence.numerosequence='"+seq+"' and composer.ideleve='"+matricule+"' and"
					+" classe.codeclasse='"+codeclasse+"'");
			return Long.parseLong(req.getSingleResult().toString()) ;

			}catch(Exception e){
			e.printStackTrace();
			return 0;
			}
	}


	@Override
	public float tauxreussite(String codeclasse, int seq,String annee) {
		try{
		int effectif = effectif(codeclasse,annee);
		int nbr =  nombrereussite(codeclasse,seq,annee);
		return (float)nbr/effectif;
		}catch(Exception  e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public int nombrereussite(String codeclasse, int seq,String annee) {
		try{
			Query req = em.createNativeQuery("select count(query3.moyenne) as nombre from (select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme, sum(cours.coeficient) as sommecoef from"
	                +"  cours, evaluation, composer, sequence,classe where"
	                +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
	                +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
	                +"  sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence='"+seq+"'"
	                +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
	                +" composer.ideleve) as query1 ) as query2 group by query2.ideleve order by moyenne asc) as query3 where query3.moyenne>=10");

			return Integer.parseInt(req.getSingleResult().toString()) ;

			}catch(Exception e){
			e.printStackTrace();
			return 0;
			}
	}

	/*@Override
	public int blame(int seq, int matricule) {

		try{
			Query req = em.createNativeQuery("SELECT sum(sa.dureesanction) as sanction FROM sanction as sa,typesanction as tysa"
					+" ,eleve,sequence where sa.typesanction=tysa.codetype and tysa.libelle='blame' and"
					+" sa.matricule= eleve.ideleve and eleve.ideleve='"+matricule+"' and sa.datedecision<sequence.datefin"
					+" and sequence.numerosequence='"+seq+"' and sa.annule=false");
			return Integer.parseInt(req.getSingleResult().toString()) ;
		}catch(Exception e){
			
			return 0;
		}
	}*/
	
	
	public int blame(Sequence seq, int matricule) {

		try{
			Query req = em.createNativeQuery("SELECT sum(sa.dureesanction) as sanction FROM sanction as sa,typesanction as tysa"
					+" ,eleve,sequence where sa.typesanction=tysa.codetype and tysa.libelle='blame' and"
					+" sa.matricule= eleve.ideleve and eleve.ideleve='"+matricule+"' "
					+" and sequence.numerosequence='"+seq.getNumerosequence()+"' ");
			return Integer.parseInt(req.getSingleResult().toString()) ;
		}catch(Exception e){
			
			return 0;
		}
	}

	@Override
	public int avertissement( Sequence seq, int matricule) {

		try{
			Query req = em.createNativeQuery("SELECT sum(sa.dureesanction) as sanction FROM sanction as sa,typesanction as tysa"
					+" ,eleve,sequence where sa.typesanction=tysa.codetype and tysa.libelle='avertissement' and"
					+" sa.matricule= eleve.ideleve and eleve.ideleve='"+matricule+"' and sa.datedecision<sequence.datefin"
					+" and sequence.numerosequence='"+seq.getNumerosequence()+"'");
			return Integer.parseInt(req.getSingleResult().toString()) ;
		}catch(Exception e){
			
			return 0;
		}
	}

	/*@Override
	public int retard( int seq, int matricule) {

		try{
			Query req = em.createNativeQuery("SELECT sum(re.dureeretard) as retard FROM retard as re,eleve,sequence where"
					+" re.matricule= eleve.ideleve and eleve.ideleve='"+matricule+"' and re.dateretard<sequence.datefin"
					+" and sequence.numerosequence='"+seq+"' and re.justifie=false");
			return Integer.parseInt(req.getSingleResult().toString()) ;
		}catch(Exception e){
						
			return 0;
		}

	}*/
	
	@Override
	public int retard( String codeclasse,Sequence seq, int matricule,String annee) {

		try{
			Query req = em.createNativeQuery("SELECT sum(re.dureeretard) as retard FROM retard as re,eleve,sequence where"
					+" re.matricule= eleve.ideleve and eleve.ideleve='"+matricule+"' and eleve.anneeacademique='"+annee+"' "
					+" and sequence.numerosequence='"+seq.getNumerosequence()+"' and eleve.codeclasse='"+codeclasse+"' and re.dateretard between '"+seq.getDatedebut()+"' and '"+seq.getDatefin()+"' ");
			return Integer.parseInt(req.getSingleResult().toString()) ;
		}catch(Exception e){
						
			return 0;
		}

	}

	/*@Override
	public int absence( int seq, int matricule) {

		try{
			Query req = em.createNativeQuery("SELECT sum(ab.duree) as absence FROM absence as ab,eleve,sequence where"
					+" ab.matricule= eleve.ideleve and eleve.ideleve='"+matricule+"' and ab.dateabsence<sequence.datefin"
					+" and sequence.numerosequence='"+seq+"' and ab.justifie=false");
			return Integer.parseInt(req.getSingleResult().toString()) ;
		}catch(Exception e){
			
			return 0;
		}

	}*/
	
	@Override
	public int absence(String codeclasse, Sequence seq, int matricule,String annee) {

		try{
			Query req = em.createNativeQuery("SELECT sum(ab.duree)- sum(ab.justifie) as absence FROM absence as ab,eleve,sequence where"
					+" ab.matricule= eleve.ideleve and eleve.ideleve='"+matricule+"' and eleve.anneeacademique='"+annee+"' "
					+" and sequence.numerosequence='"+seq.getNumerosequence()+"' and eleve.codeclasse='"+codeclasse+"' and ab.dateabsence between '"+seq.getDatedebut()+"' and '"+seq.getDatefin()+"' ");
			return Integer.parseInt(req.getSingleResult().toString()) ;
		}catch(Exception e){
			
			return 0;
		}

	}

	@Override
	public int exclusion( Sequence seq, int matricule) {
		
		try{
			Query req = em.createNativeQuery("SELECT sum(sa.dureesanction) as sanction FROM sanction as sa,typesanction as tysa"
					+" ,eleve,sequence where sa.typesanction=tysa.codetype and tysa.libelle='exclusion' and"
					+" sa.matricule= eleve.ideleve and eleve.ideleve='"+matricule+"' and sa.datedecision<sequence.datefin"
					+" and sequence.numerosequence='"+seq+"' ");
			return Integer.parseInt(req.getSingleResult().toString()) ;
		}catch(Exception e){
			
			return 0;
		}
		
	}

	@Override
	public double moyennePremierTrim(String codeclasse, int seq1, int seq2) {
		try{
			Query req = em.createNativeQuery("select ROUND(max(toutmoy.moy),2) as premier from(select (trim1.moyenne+trim2.moyenne)/2 as moy,trim1.moyenne as moy1,"
					+" trim2.moyenne as moy2,trim1.ideleve as matricule from ((select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.numerosequence="+seq1+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trim1,"
					+" (select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
                   +" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
                    +" sum(cours.coeficient) as sommecoef from"
                   +" cours, evaluation, composer, sequence,classe where"
                   +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
                   +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
                   +" sequence.idsequence and sequence.numerosequence="+seq2+""
                  +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
                  +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
                   +" as trim2) where trim2.ideleve=trim1.ideleve group by trim1.ideleve order by moy asc) as toutmoy");
				
			return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}

	@Override
	public double moyenneDernierTrim(String codeclasse, int seq1, int seq2) {
		try{
			Query req = em.createNativeQuery("select ROUND(min(toutmoy.moy),2) as premier from(select (trim1.moyenne+trim2.moyenne)/2 as moy,trim1.moyenne as moy1,"
					+" trim2.moyenne as moy2,trim1.ideleve as matricule from ((select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.numerosequence="+seq1+""
					+"  and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trim1,"
					+" (select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
                   +" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
                    +" sum(cours.coeficient) as sommecoef from"
                   +" cours, evaluation, composer, sequence,classe where"
                   +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
                   +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
                   +" sequence.idsequence and sequence.numerosequence="+seq2+""
                  +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
                  +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
                   +" as trim2) where trim2.ideleve=trim1.ideleve group by trim1.ideleve order by moy asc) as toutmoy");
				
			return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}

	@Override
	public double tauxreussitetrim1(String codeclasse, int seq1, int seq2,String annee) {
		try{
			int effectif = effectif(codeclasse,annee);
			int nbr =  nombrereussiteTrim(codeclasse,seq1,seq2);
			return (double)nbr/effectif;
			}catch(Exception  e){
				e.printStackTrace();
				return 0;
			}
	}

	@Override
	public int nombrereussiteTrim(String codeclasse, int seq1, int seq2) {
		try{
			Query req = em.createNativeQuery("select count(toutmoy.moy) as nombre from(select (trim1.moyenne+trim2.moyenne)/2 as moy,trim1.moyenne as moy1,"
					+" trim2.moyenne as moy2,trim1.ideleve as matricule from ((select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.numerosequence="+seq1+""
					+"  and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trim1,"
					+" (select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
                   +" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
                    +" sum(cours.coeficient) as sommecoef from"
                   +" cours, evaluation, composer, sequence,classe where"
                   +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
                   +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
                   +" sequence.idsequence and sequence.numerosequence="+seq2+""
                  +"  and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
                  +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
                   +" as trim2) where trim2.ideleve=trim1.ideleve group by trim1.ideleve order by moy asc) as toutmoy " +
                   " where toutmoy.moy>=10");
				
			 return Integer.parseInt(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}

	@Override
	public double moyenneEleveTrimDirect(String codeclasse, int matricule,
			int seq1, int seq2) {
		try{
			Query req = em.createNativeQuery("select ROUND(toutmoy.moy,2) as mo from(select (trim1.moyenne+trim2.moyenne)/2 as moy,trim1.moyenne as moy1,"
					+" trim2.moyenne as moy2,trim1.ideleve as matricule from ((select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe,eleve where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.numerosequence="+seq1+""
					+" and eleve.codeclasse=classe.codeclasse and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trim1,"
					+" (select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
                   +" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
                    +" sum(cours.coeficient) as sommecoef from"
                   +" cours, evaluation, composer, sequence,classe,eleve where"
                   +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
                   +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
                   +" sequence.idsequence and sequence.numerosequence="+seq2+""
                  +" and eleve.codeclasse=classe.codeclasse and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
                  +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
                   +" as trim2) where trim2.ideleve=trim1.ideleve and trim1.ideleve='"+matricule+"' group by trim1.ideleve order by moy asc) as toutmoy");
				
			return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}

	@Override
	public int rangEleveClasseTrim(String codeclasse, int matricule, int seq1,
			int seq2) {
		try{
			Query req = em.createNativeQuery("select count(toutmoy.moy) as nombre from(select (trim1.moyenne+trim2.moyenne)/2 as moy,trim1.moyenne as moy1,"
					+" trim2.moyenne as moy2,trim1.ideleve as matricule from ((select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe,eleve where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.numerosequence="+seq1+""
					+" and eleve.codeclasse=classe.codeclasse and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trim1,"
					+" (select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
                   +" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
                    +" sum(cours.coeficient) as sommecoef from"
                   +" cours, evaluation, composer, sequence,classe,eleve where"
                   +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
                   +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
                   +" sequence.idsequence and sequence.numerosequence="+seq2+""
                  +" and eleve.codeclasse=classe.codeclasse and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
                  +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
                   +" as trim2) where trim2.ideleve=trim1.ideleve group by trim1.ideleve order by moy asc) as toutmoy " +
                   "  where toutmoy.moy>='"+moyenneEleveTrimDirect(codeclasse,matricule,seq1,seq2)+"'");
				
			 return Integer.parseInt(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}

	
	public double moyennePremierAnnuel(String codeclasse, int trim1, int trim2, int trim3) {
		try{
			Query req = em.createNativeQuery("select ROUND(max(toutmoy.moy),3) as premier from(select (trimes1.moyenne+trimes2.moyenne +trimes3.moyenne)/3 as moy,trimes1.moyenne as moy1,"
					+" trimes2.moyenne as moy2,trimes1.ideleve as matricule from ((select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.numerosequence="+trim1+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trimes1,"
					+"(select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.numerosequence="+trim2+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trimes2,"
					+" (select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
                   +" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
                    +" sum(cours.coeficient) as sommecoef from"
                   +" cours, evaluation, composer, sequence,classe where"
                   +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
                   +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
                   +" sequence.idsequence and sequence.numerosequence="+trim3+""
                  +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
                  +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
                   +" as trimes3) where trimes2.ideleve=trimes1.ideleve and trimes2.ideleve=trimes3.ideleve group by trimes1.ideleve order by moy asc) as toutmoy");
				
			return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}
	
	public double moyenneDernierAnnuel(String codeclasse, int trim1, int trim2, int trim3) {
		try{
			Query req = em.createNativeQuery("select ROUND(min(toutmoy.moy),3) as dernier from(select (trimes1.moyenne+trimes2.moyenne +trimes3.moyenne)/3 as moy,trimes1.moyenne as moy1,"
					+" trimes2.moyenne as moy2,trimes1.ideleve as matricule from ((select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.numerosequence="+trim1+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trimes1,"
					+"(select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.numerosequence="+trim2+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trimes2,"
					+" (select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
                   +" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
                    +" sum(cours.coeficient) as sommecoef from"
                   +" cours, evaluation, composer, sequence,classe where"
                   +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
                   +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
                   +" sequence.idsequence and sequence.numerosequence="+trim3+""
                  +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
                  +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
                   +" as trimes3) where trimes2.ideleve=trimes1.ideleve and trimes2.ideleve=trimes3.ideleve group by trimes1.ideleve order by moy asc) as toutmoy");
				
			return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}
	
	public int nombrereussiteAnnuel(String codeclasse, int trim1, int trim2, int trim3,String annee) {
		try{
			Query req = em.createNativeQuery("select  count(toutmoy.moy) as nombre from(select (trimes1.moyenne+trimes2.moyenne +trimes3.moyenne)/3 as moy,trimes1.moyenne as moy1,"
					+" trimes2.moyenne as moy2,trimes1.ideleve as matricule from ((select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+trim1+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trimes1,"
					+"(select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+trim2+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trimes2,"
					+" (select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
                   +" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
                    +" sum(cours.coeficient) as sommecoef from"
                   +" cours, evaluation, composer, sequence,classe where"
                   +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
                   +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
                   +" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+trim3+""
                  +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
                  +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
                   +" as trimes3) where trimes2.ideleve=trimes1.ideleve and trimes2.ideleve=trimes3.ideleve group by trimes1.ideleve order by moy asc) as toutmoy where toutmoy.moy>=10");
				
			return Integer.parseInt(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}
	
	public float tauxreussiteAnnuel1(String codeclasse, int trim1, int trim2, int trim3,String annee) {
		try{
			int effectif = effectif(codeclasse,annee);
			int nbr =  nombrereussiteAnnuel(codeclasse,trim1,trim2,trim3,annee);
			return (float)nbr/effectif;
			}catch(Exception  e){
				e.printStackTrace();
				return 0;
			}
	}
	
	public double moyenneEleveAnnuelDirect(String annee,String codeclasse, int matricule,
			int trim1, int trim2, int trim3) {
		try{
			Query req = em.createNativeQuery("select ROUND(toutmoy.moy,3) as mo from(select (trimes1.moyenne+trimes2.moyenne+trimes3.moyenne)/3 as moy,trimes1.moyenne as moy1,"
					+" trimes2.moyenne as moy2,trimes1.ideleve as matricule from ((select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe,eleve where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+trim1+""
					+" and composer.ideleve='"+matricule+"' and eleve.codeclasse=classe.codeclasse and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trimes1,"
					+"(select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"'and sequence.numerosequence="+trim2+"" 
					+" and composer.ideleve='"+matricule+"' and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trimes2,"
					+" (select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
                   +" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
                    +" sum(cours.coeficient) as sommecoef from"
                   +" cours, evaluation, composer, sequence,classe,eleve where"
                   +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
                   +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
                   +" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+trim3+""
                  +" and composer.ideleve='"+matricule+"' and eleve.codeclasse=classe.codeclasse and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
                  +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
                   +" as trimes3) ) as toutmoy");
				
			return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}
	
	public double moyenneEleveAnnuelDirectN(String annee,String codeclasse, int matricule,
			int seq1, int seq2, int seq3, int seq4, int seq5, int seq6, int seq7, int seq8) {
		try{
			Query req = em.createNativeQuery("select ROUND(toutmoy.moy,3) as mo from(select (seq1.moyenne+seq2.moyenne+seq3.moyenne+seq4.moyenne+seq5.moyenne+seq6.moyenne+seq7.moyenne+seq8.moyenne)/8 as moy,seq1.moyenne as moy1,"
					+" seq2.moyenne as moy2,seq1.ideleve as matricule from ((select query2.moyenne as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe,eleve where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+seq1+""
					+" and composer.ideleve='"+matricule+"' and eleve.codeclasse=classe.codeclasse and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq1,"
					+"(select query2.moyenne as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"'and sequence.numerosequence="+seq2+"" 
					+" and composer.ideleve='"+matricule+"' and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq2,"
					+"(select query2.moyenne as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"'and sequence.numerosequence="+seq3+"" 
					+" and composer.ideleve='"+matricule+"' and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq3,"
					+"(select query2.moyenne as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"'and sequence.numerosequence="+seq4+"" 
					+" and composer.ideleve='"+matricule+"' and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq4,"
					+"(select query2.moyenne as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"'and sequence.numerosequence="+seq5+"" 
					+" and composer.ideleve='"+matricule+"' and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq5,"
					+"(select query2.moyenne as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"'and sequence.numerosequence="+seq6+"" 
					+" and composer.ideleve='"+matricule+"' and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq6,"
					+"(select query2.moyenne as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"'and sequence.numerosequence="+seq7+"" 
					+" and composer.ideleve='"+matricule+"' and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq7,"
					+" (select query2.moyenne as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
                   +" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
                    +" sum(cours.coeficient) as sommecoef from"
                   +" cours, evaluation, composer, sequence,classe,eleve where"
                   +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
                   +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
                   +" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+seq8+""
                  +" and composer.ideleve='"+matricule+"' and eleve.codeclasse=classe.codeclasse and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
                  +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
                   +" as seq8) ) as toutmoy");
				
			return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}
	
	public double moyenneEleveS(String codeclasse, int matricule,String annee) {
		try{
		Query req = em.createNativeQuery("select ROUND(query2.moyenne,2) as moyenne "
		+" from  (select somme/sommecoef as moyenne , query1.somme, query1.sommecoef from"
		+" (select sum(note*cours.coeficient) as somme, sum(cours.coeficient) as sommecoef from"
		+" cours, evaluation, composer, sequence,classe,eleve where cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
		+" and evaluation.codeevaluation=composer.codeevaluation  and composer.ideleve='"+matricule+"'"
        +" and eleve.codeclasse=classe.codeclasse and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"') as query1) as query2");
		
		return Double.parseDouble(req.getSingleResult().toString()) ;

		}catch(Exception e){
		e.printStackTrace();
		return 0;
		}
        
       
	}
	
	public double moyenneClasseS(String annee,String codeclasse) {
		try{
		Query req = em.createNativeQuery("select ROUND(query3.moyclasse,2) as moyclasse from (select avg(query2.moyenne) as moyclasse from  (select"
          +" somme/sommecoef as moyenne , query1.somme, query1.sommecoef from"
          +" (select distinct composer.ideleve,sum(note*cours.coeficient) as somme, sum(cours.coeficient) as sommecoef from"
            +"  cours, evaluation, composer, sequence,classe where"
            +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
            +" and evaluation.codeevaluation=composer.codeevaluation and sequence.anneeacademique='"+annee+"'"
            +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
            +" composer.ideleve) as query1)as query2) as query3");
		return Double.parseDouble(req.getSingleResult().toString()) ;

		}catch(Exception e){
		e.printStackTrace();
		return 0;
	}
	}
	
	public double totalpointS(String annee,String codeclasse, int matricule) {
		try{
			Query req = em.createNativeQuery("SELECT sum(note*cours.coeficient) as somme from"
					+" cours, classe,composer, evaluation, sequence"
					+" where cours.codeclasse=classe.codeclasse and cours.codecours=evaluation.codecours and composer.absencejustifiee=false and"
					+" evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence=sequence.idsequence"
					+" and sequence.anneeacademique='"+annee+"'and composer.ideleve='"+matricule+"' and"
					+" classe.codeclasse='"+codeclasse+"'");
			return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
			e.printStackTrace();
			return 0;
			}
	}
	
	
	public double totalpointG(String annee,String codeclasse, int matricule,int seq1,int seq2,int seq3,
			int seq4,int seq5,int seq6,int seq7,int seq8) {
		
		double total;
		 total = (totalpointTrim(annee,codeclasse,matricule,seq7,seq8) + totalpointAnnuel(annee,codeclasse,matricule,seq1,seq2,seq3)+totalpointAnnuel(annee,codeclasse,matricule,seq4,seq5,seq6))/3;
		 return total;
		
	}
	
	

	public int rangEleveClasseS(String codeclasse, int matricule,String annee) {
		try{
			Query req = em.createNativeQuery("select count(query3.moyenne) as rang from (select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme, sum(cours.coeficient) as sommecoef from"
	               +" cours, evaluation, composer, sequence,classe,annee where"
	               +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
	               +" and evaluation.codeevaluation=composer.codeevaluation"
	               +" and cours.codeclasse=classe.codeclasse and cours.anneeacademique=annee.anneeacademique and cours.anneeacademique='"+annee+"'" +
	               " and classe.codeclasse='"+codeclasse+"'  group by"
	               +" composer.ideleve) as query1 ) as query2 group by query2.ideleve order by moyenne asc) as query3 where query3.moyenne>='"+moyenneEleveS(codeclasse,matricule,annee)+"'");
				
			return Integer.parseInt(req.getSingleResult().toString());

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}
	
	
	public double moyennePremierS(String anneeEncours, Classe clas) {
		try{
			Query req = em.createNativeQuery("select max(query3.moyenne) as moyennepremier from (select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme, sum(cours.coeficient) as sommecoef from"
	               +" cours, evaluation, composer, sequence,classe where"
	               +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
	               +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
	               +"  sequence.idsequence and sequence.anneeacademique='"+anneeEncours+"'"
	               +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+clas.getCodeclasse()+"'  group by"
	               +" composer.ideleve) as query1 ) as query2 group by query2.ideleve order by moyenne asc) as query3");
				
			return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}
	
	public double moyenneDernierS(String anneeEncours, Classe clas) {
		try{
			Query req = em.createNativeQuery("select min(query3.moyenne) as moyennedernier from (select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme, sum(cours.coeficient) as sommecoef from"
	               +" cours, evaluation, composer, sequence,classe where"
	               +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
	               +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
	               +"  sequence.idsequence  and sequence.anneeacademique='"+anneeEncours+"'"
	               +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+clas.getCodeclasse()+"'  group by"
	               +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc) as query3");
			return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public double tauxreussiteS(String codeclasse, String annee) {
		try{
		int effectif = effectif(codeclasse,annee);
		int nbr =  nombrereussiteS(codeclasse,annee);
		return (double)nbr/effectif;
		}catch(Exception  e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public int nombrereussiteS(String codeclasse,String annee) {
		try{
			Query req = em.createNativeQuery("select count(query3.moyenne) as nombre from (select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme, sum(cours.coeficient) as sommecoef from"
	                +"  cours, evaluation, composer, sequence,classe where"
	                +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
	                +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
	                +"  sequence.idsequence and sequence.anneeacademique='"+annee+"'"
	                +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
	                +" composer.ideleve) as query1 ) as query2 group by query2.ideleve order by moyenne asc) as query3 where query3.moyenne>=10");

			return Integer.parseInt(req.getSingleResult().toString()) ;

			}catch(Exception e){
			e.printStackTrace();
			return 0;
			}
	}
	
	public long totalcoefEleveS(String codeclasse, int matricule) {
		try{
			Query req = em.createNativeQuery("SELECT sum(cours.coeficient) as sommecoef from"
					+" cours, classe,composer, evaluation, sequence"
					+" where cours.codeclasse=classe.codeclasse and cours.codecours=evaluation.codecours and composer.absencejustifiee=false and"
					+" evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence=sequence.idsequence"
					+" and composer.ideleve='"+matricule+"' and"
					+" classe.codeclasse='"+codeclasse+"'");
			return Long.parseLong(req.getSingleResult().toString()) ;

			}catch(Exception e){
			e.printStackTrace();
			return 0;
			}
	}
	
	
	public int blameS(int matricule) {

		try{
			Query req = em.createNativeQuery("SELECT sum(sa.dureesanction) as sanction FROM sanction as sa,typesanction as tysa"
					+" ,eleve,sequence where sa.typesanction=tysa.codetype and tysa.libelle='blame' and"
					+" sa.matricule= eleve.ideleve and eleve.ideleve='"+matricule+"' ");
			return Integer.parseInt(req.getSingleResult().toString()) ;
		}catch(Exception e){
			
			return 0;
		}
	}

	@Override
	public int avertissementS(int matricule) {

		try{
			Query req = em.createNativeQuery("SELECT sum(sa.dureesanction) as sanction FROM sanction as sa,typesanction as tysa"
					+" ,eleve,sequence where sa.typesanction=tysa.codetype and tysa.libelle='avertissement' and"
					+" sa.matricule= eleve.ideleve and eleve.ideleve='"+matricule+"' and sa.datedecision<sequence.datefin");
			return Integer.parseInt(req.getSingleResult().toString()) ;
		}catch(Exception e){
			
			return 0;
		}
	}

	/*@Override
	public int retard( int seq, int matricule) {

		try{
			Query req = em.createNativeQuery("SELECT sum(re.dureeretard) as retard FROM retard as re,eleve,sequence where"
					+" re.matricule= eleve.ideleve and eleve.ideleve='"+matricule+"' and re.dateretard<sequence.datefin"
					+" and sequence.numerosequence='"+seq+"' and re.justifie=false");
			return Integer.parseInt(req.getSingleResult().toString()) ;
		}catch(Exception e){
						
			return 0;
		}

	}*/
	@Override
	public int retardS( String codeclasse, int matricule, String date1, String date2) {

		try{
			Query req = em.createNativeQuery("SELECT sum(re.dureeretard) as retard FROM retard as re,eleve,sequence where"
					+" re.matricule= eleve.ideleve and eleve.ideleve='"+matricule+"'  and eleve.codeclasse='"+codeclasse+"' and re.dateretard between '"+date1+"' and '"+date2+"' ");
			return Integer.parseInt(req.getSingleResult().toString()) ;
		}catch(Exception e){
						
			return 0;
		}

	}

	/*@Override
	public int absence( int seq, int matricule) {

		try{
			Query req = em.createNativeQuery("SELECT sum(ab.duree) as absence FROM absence as ab,eleve,sequence where"
					+" ab.matricule= eleve.ideleve and eleve.ideleve='"+matricule+"' and ab.dateabsence<sequence.datefin"
					+" and sequence.numerosequence='"+seq+"' and ab.justifie=false");
			return Integer.parseInt(req.getSingleResult().toString()) ;
		}catch(Exception e){
			
			return 0;
		}

	}*/
	@Override
	public int absenceS( String codeclasse, int matricule, String date1, String date2) {

		try{
			Query req = em.createNativeQuery("SELECT sum(ab.duree)- sum(ab.justifie) as absence FROM absence as ab,eleve,sequence where"
					+" ab.matricule= eleve.ideleve and eleve.ideleve='"+matricule+"' and eleve.codeclasse='"+codeclasse+"' and ab.dateabsence between '"+date1+"' and '"+date2+"' ");
			return Integer.parseInt(req.getSingleResult().toString()) ;
		}catch(Exception e){
			
			return 0;
		}

	}
	@Override
	public int exclusionS(int matricule,String date) {
		
		try{
			Query req = em.createNativeQuery("SELECT sum(sa.dureesanction) as sanction FROM sanction as sa,typesanction as tysa"
					+" ,eleve,sequence where sa.typesanction=tysa.codetype and tysa.libelle='exclusion' and"
					+" sa.matricule= eleve.ideleve and eleve.ideleve='"+matricule+"' and sa.datedecision<'"+date+"'");
			return Integer.parseInt(req.getSingleResult().toString()) ;
		}catch(Exception e){
			
			return 0;
		}
		
	}
	
	
	
	public int rangEleveClasseAnnuel(String annee,String codeclasse, int matricule, int trim1,
			int trim2, int trim3) {
		try{
			Query req = em.createNativeQuery("select count(toutmoy.moy) as nombre from(select (trimes1.moyenne+trimes2.moyenne +trimes3.moyenne)/3 as moy,trimes1.moyenne as moy1,"
					+" trimes2.moyenne as moy2,trimes1.ideleve as matricule from ((select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe,eleve where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+trim1+""
					+" and eleve.codeclasse=classe.codeclasse and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trimes1,"
					+"(select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+trim2+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trimes2,"
					+" (select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
                   +" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
                    +" sum(cours.coeficient) as sommecoef from"
                   +" cours, evaluation, composer, sequence,classe,eleve where"
                   +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
                   +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
                   +" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+trim3+""
                  +" and eleve.codeclasse=classe.codeclasse and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
                  +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
                   +" as trimes3) where trimes2.ideleve=trimes1.ideleve and trimes2.ideleve=trimes3.ideleve group by trimes1.ideleve order by moy asc) as toutmoy " +
                   "  where toutmoy.moy>='"+moyenneEleveAnnuelDirect(annee,codeclasse,matricule,trim1,trim2, trim3)+"'");
				
			 return Integer.parseInt(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}
	
	
	public int rangEleveClasseAnnuelN(String annee,String codeclasse, int matricule, int seq1,
			int seq2, int seq3, int seq4, int seq5, int seq6, int seq7, int seq8) {
		try{
			Query req = em.createNativeQuery("select count(toutmoy.moy) as nombre from(select (seq1.moyenne+seq2.moyenne +seq3.moyenne+seq4.moyenne+seq5.moyenne+seq6.moyenne+seq7.moyenne+seq8.moyenne)/8 as moy,seq1.moyenne as moy1,"
					+" seq2.moyenne as moy2,seq1.ideleve as matricule from ((select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe,eleve where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+seq1+""
					+" and eleve.codeclasse=classe.codeclasse and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq1,"
					+"(select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+seq2+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq2,"
					+"(select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+seq3+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq3,"
					+"(select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+seq4+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq4,"
					+"(select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+seq5+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq5,"
					+"(select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+seq6+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq6,"
					+"(select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+seq7+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq7,"
					+" (select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
                   +" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
                    +" sum(cours.coeficient) as sommecoef from"
                   +" cours, evaluation, composer, sequence,classe,eleve where"
                   +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
                   +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
                   +" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+seq8+""
                  +" and eleve.codeclasse=classe.codeclasse and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
                  +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
                   +" as seq8) where trimes2.ideleve=trimes1.ideleve and trimes2.ideleve=trimes3.ideleve group by trimes1.ideleve order by moy asc) as toutmoy " +
                   "  where toutmoy.moy>='"+moyenneEleveAnnuelDirectN(annee,codeclasse,matricule,seq1,seq2, seq3,seq4,seq5,seq6,seq7,seq8)+"'");
				
			 return Integer.parseInt(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}
	
	@Override
	public double moyennePremierAnnuelN(String annee,String codeclasse, int seq1,
			int seq2, int seq3, int seq4, int seq5, int seq6, int seq7, int seq8) {
		try{
			Query req = em.createNativeQuery("select ROUND(max(toutmoy.moy),2) as premier from (select (seq1.moyenne+seq2.moyenne +seq3.moyenne+seq4.moyenne+seq5.moyenne+seq6.moyenne+seq7.moyenne+seq8.moyenne)/8 as moy,seq1.moyenne as moy1,"
					+" seq2.moyenne as moy2,seq1.ideleve as matricule from ((select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe,eleve where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+seq1+""
					+" and eleve.codeclasse=classe.codeclasse and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq1,"
					+"(select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+seq2+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq2,"
					+"(select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+seq3+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq3,"
					+"(select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+seq4+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq4,"
					+"(select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+seq5+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq5,"
					+"(select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+seq6+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq6,"
					+"(select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+seq7+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as seq7,"
					+" (select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
                   +" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
                    +" sum(cours.coeficient) as sommecoef from"
                   +" cours, evaluation, composer, sequence,classe,eleve where"
                   +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
                   +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
                   +" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+seq8+""
                  +" and eleve.codeclasse=classe.codeclasse and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
                  +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
                   +" as seq8) where seq2.ideleve=seq1.ideleve and seq2.ideleve=seq3.ideleve and seq3.ideleve=seq4.ideleve and seq4.ideleve=seq5.ideleve and seq5.ideleve=seq6.ideleve and seq6.ideleve=seq7.ideleve and seq7.ideleve=seq8.ideleve group by seq1.ideleve order by moy asc) as toutmoy ");
				
			return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}
	
	
	public double moyenneClasseAnnuelDirect(String annee,String codeclasse,  int trim1, int trim2, int trim3) {
		try{
			Query req = em.createNativeQuery("select ROUND(avg(toutmoy.moy),2) as moyg from(select (trimes1.moyenne+trimes2.moyenne +trimes3.moyenne)/3 as moy,trimes1.moyenne as moy1,"
					+" trimes2.moyenne as moy2,trimes1.ideleve as matricule from ((select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+trim1+""
					+" and cours.codeclasse=classe.codeclasse and  classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trimes1,"
					+"(select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+trim2+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trimes2,"
					+" (select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
                   +" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
                    +" sum(cours.coeficient) as sommecoef from"
                   +" cours, evaluation, composer, sequence,classe where"
                   +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
                   +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
                   +" sequence.idsequence and sequence.anneeacademique='"+annee+"' and sequence.numerosequence="+trim3+""
                  +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+codeclasse+"'  group by"
                  +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
                   +" as trimes3) where trimes2.ideleve=trimes1.ideleve group by trimes1.ideleve order by moy asc) as toutmoy");
				
			 return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}
	
	public double moyenneClasseAnnuel(String codeclasse,
			int trim1, int trim2, int trim3) {
		double moyenneclassetrim;
		
		moyenneclassetrim=( (moyenneClasse(codeclasse,  trim1) + moyenneClasse(codeclasse,  trim2)) + moyenneClasse(codeclasse,  trim3))/3;
		return moyenneclassetrim;
	}
	
	public double totalpointAnnuel(String annee,String codeclasse,int matricule, int trim1, int trim2, int trim3) {
		double total;
		 total = ((totalpoint(annee,codeclasse,matricule,trim1) + totalpoint(annee,codeclasse,matricule,trim2)) +totalpoint(annee,codeclasse,matricule,trim3))/3;
		 return total;
	}

	@Override
	public float tauxClasse(String anneeEncours, Classe classe, Sequence seq) {
		try{
			int effectif = effectif(classe.getCodeclasse(),anneeEncours);
			int nbr =  nombrereussite(classe.getCodeclasse(),seq.getNumerosequence(),anneeEncours);
			return (float)nbr/effectif;
			}catch(Exception  e){
				e.printStackTrace();
				return 0;
			}
	}

	@Override
	public double moyenneClasse(String anneeEncours, Classe classe, Sequence seq) {
		try{
			Query req = em.createNativeQuery("select ROUND(query3.moyclasse,2) as moyclasse from (select avg(query2.moyenne) as moyclasse from  (select"
	          +" somme/sommecoef as moyenne , query1.somme, query1.sommecoef from"
	          +" (select distinct composer.ideleve,sum(note*cours.coeficient) as somme, sum(cours.coeficient) as sommecoef from"
	            +"  cours, evaluation, composer, sequence,classe where"
	            +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
	            +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
	            +" sequence.idsequence and sequence.anneeacademique='"+anneeEncours+"' and sequence.numerosequence='"+seq.getNumerosequence()+"'"
	            +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+classe.getCodeclasse()+"'  group by"
	            +" composer.ideleve) as query1)as query2) as query3");
			return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public double moyennePremier(String anneeEncours, Classe clas, Sequence seq) {
		try{
			Query req = em.createNativeQuery("select max(query3.moyenne) as moyennepremier from (select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme, sum(cours.coeficient) as sommecoef from"
	               +" cours, evaluation, composer, sequence,classe where"
	               +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
	               +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
	               +"  sequence.idsequence and sequence.anneeacademique='"+anneeEncours+"' and sequence.numerosequence='"+seq.getNumerosequence()+"'"
	               +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+clas.getCodeclasse()+"'  group by"
	               +" composer.ideleve) as query1 ) as query2 group by query2.ideleve order by moyenne asc) as query3");
				
			return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}

	@Override
	public double moyenneDernier(String anneeEncours, Classe clas, Sequence seq) {
		try{
			Query req = em.createNativeQuery("select min(query3.moyenne) as moyennedernier from (select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme, sum(cours.coeficient) as sommecoef from"
	               +" cours, evaluation, composer, sequence,classe where"
	               +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
	               +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
	               +"  sequence.idsequence  and sequence.anneeacademique='"+anneeEncours+"' and sequence.numerosequence='"+seq.getNumerosequence()+"'"
	               +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+clas.getCodeclasse()+"'  group by"
	               +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc) as query3");
			return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}


	@Override
	public double moyenneClasseTrimDirect(String anneeEncours, Classe classe,
			Sequence sequence1, Sequence sequence2) {
		try{
			Query req = em.createNativeQuery("select ROUND(avg(toutmoy.moy),2) as moyg from(select (trim1.moyenne+trim2.moyenne)/2 as moy,trim1.moyenne as moy1,"
					+" trim2.moyenne as moy2,trim1.ideleve as matricule from ((select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+anneeEncours+"' and sequence.numerosequence="+sequence1.getNumerosequence()+""
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+classe.getCodeclasse()+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trim1,"
					+" (select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
                   +" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
                    +" sum(cours.coeficient) as sommecoef from"
                   +" cours, evaluation, composer, sequence,classe where"
                   +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
                   +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
                   +" sequence.idsequence and sequence.anneeacademique='"+anneeEncours+"' and sequence.numerosequence="+sequence2.getNumerosequence()+""
                  +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+classe.getCodeclasse()+"'  group by"
                  +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
                   +" as trim2) where trim2.ideleve=trim1.ideleve group by trim1.ideleve order by moy asc) as toutmoy");
				
			 return Integer.parseInt(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}


	@Override
	public double moyenneClasseTrim(String annee, Classe classe,
			Sequence sequence1, Sequence sequence2) {
      double moyenneclassetrim;
		
		moyenneclassetrim=( moyenneClasse(annee,classe,  sequence1) + moyenneClasse(annee,classe,  sequence2))/2;
		return moyenneclassetrim;
	}

	@Override
	public float tauxreussitetrim(String anneeEncours, Classe classe,
			Sequence sequence1, Sequence sequence2) {
		try{
			int effectif = effectif(classe.getCodeclasse(),anneeEncours);
			int nbr =  nombrereussiteTrim(classe.getCodeclasse(),sequence1.getNumerosequence(),sequence2.getNumerosequence());
			return (float)nbr/effectif;
			}catch(Exception  e){
				e.printStackTrace();
				return 0;
			}
	}

	@Override
	public double moyennePremier(String anneeEncours, Classe clas,
			Sequence sequence1, Sequence sequence2) {
		try{
			Query req = em.createNativeQuery("select ROUND(max(toutmoy.moy),2) as premier from(select (trim1.moyenne+trim2.moyenne)/2 as moy,trim1.moyenne as moy1,"
					+" trim2.moyenne as moy2,trim1.ideleve as matricule from ((select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+anneeEncours+"' and sequence.numerosequence="+sequence1.getNumerosequence()+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+clas.getCodeclasse()+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trim1,"
					+" (select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
                   +" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
                    +" sum(cours.coeficient) as sommecoef from"
                   +" cours, evaluation, composer, sequence,classe where"
                   +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
                   +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
                   +" sequence.idsequence and sequence.anneeacademique='"+anneeEncours+"' and sequence.numerosequence="+sequence2.getNumerosequence()+""
                  +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+clas.getCodeclasse()+"'  group by"
                  +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
                   +" as trim2) where trim2.ideleve=trim1.ideleve group by trim1.ideleve order by moy asc) as toutmoy");
				
			return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}

	@Override
	public double moyenneDernier(String anneeEncours, Classe clas,
			Sequence sequence1, Sequence sequence2) {
		try{
			Query req = em.createNativeQuery("select ROUND(min(toutmoy.moy),2) as premier from(select (trim1.moyenne+trim2.moyenne)/2 as moy,trim1.moyenne as moy1,"
					+" trim2.moyenne as moy2,trim1.ideleve as matricule from ((select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+anneeEncours+"' and sequence.numerosequence="+sequence1.getNumerosequence()+""
					+"  and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+clas.getCodeclasse()+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trim1,"
					+" (select ROUND(query2.moyenne,2) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
                   +" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
                    +" sum(cours.coeficient) as sommecoef from"
                   +" cours, evaluation, composer, sequence,classe where"
                   +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
                   +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
                   +" sequence.idsequence and sequence.anneeacademique='"+anneeEncours+"' and sequence.numerosequence="+sequence2.getNumerosequence()+""
                  +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+clas.getCodeclasse()+"'  group by"
                  +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
                   +" as trim2) where trim2.ideleve=trim1.ideleve group by trim1.ideleve order by moy asc) as toutmoy");
				
			return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}

	@Override
	public float tauxreussiteAnnuel(String anneeEncours, Classe clas, int trim1,
			int trim2, int trim3) {
		try{
			int effectif = effectif(clas.getCodeclasse(),anneeEncours);
			int nbr =  nombrereussiteAnnuel(clas.getCodeclasse(),trim1,trim2,trim3);
			return (float)nbr/effectif;
			}catch(Exception  e){
				e.printStackTrace();
				return 0;
			}
	}

	@Override
	public double moyenneClasseAnnuel(String annee, Classe clas, int trim1,
			int trim2, int trim3) {
		double moyenneclassetrim;
		
		moyenneclassetrim=( (moyenneClasse(clas.getCodeclasse(),  trim1) + moyenneClasse(clas.getCodeclasse(),  trim2)) + moyenneClasse(clas.getCodeclasse(),  trim3))/3;
		return moyenneclassetrim;
	}

	@Override
	public double moyenneDernierAnnuel(String anneeEncours, Classe clas, int trim1,
			int trim2, int trim3) {
		try{
			Query req = em.createNativeQuery("select ROUND(min(toutmoy.moy),3) as premier from(select (trimes1.moyenne+trimes2.moyenne +trimes3.moyenne)/3 as moy,trimes1.moyenne as moy1,"
					+" trimes2.moyenne as moy2,trimes1.ideleve as matricule from ((select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+anneeEncours+"' and sequence.numerosequence="+trim1+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+clas.getCodeclasse()+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trimes1,"
					+"(select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+anneeEncours+"' and sequence.numerosequence="+trim2+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+clas.getCodeclasse()+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trimes2,"
					+" (select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
                   +" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
                    +" sum(cours.coeficient) as sommecoef from"
                   +" cours, evaluation, composer, sequence,classe where"
                   +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
                   +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
                   +" sequence.idsequence and sequence.anneeacademique='"+anneeEncours+"' and sequence.numerosequence="+trim3+""
                  +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+clas.getCodeclasse()+"'  group by"
                  +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
                   +" as trimes3) where trimes2.ideleve=trimes1.ideleve and trimes2.ideleve=trimes3.ideleve group by trimes1.ideleve order by moy asc) as toutmoy");
				
			return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}

	@Override
	public double moyennePremierAnnuel(String anneeEncours, Classe clas, int trim1,
			int trim2, int trim3) {
		try{
			Query req = em.createNativeQuery("select ROUND(max(toutmoy.moy),3) as premier from(select (trimes1.moyenne+trimes2.moyenne +trimes3.moyenne)/3 as moy,trimes1.moyenne as moy1,"
					+" trimes2.moyenne as moy2,trimes1.ideleve as matricule from ((select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+anneeEncours+"' and sequence.numerosequence="+trim1+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+clas.getCodeclasse()+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trimes1,"
					+"(select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
					+" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
					+" sum(cours.coeficient) as sommecoef from"
					+" cours, evaluation, composer, sequence,classe where"
					+" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
					+" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
					+" sequence.idsequence and sequence.anneeacademique='"+anneeEncours+"' and sequence.numerosequence="+trim2+"" 
					+" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+clas.getCodeclasse()+"'  group by"
					+" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
					+" as trimes2,"
					+" (select ROUND(query2.moyenne,3) as moyenne, query2.ideleve as ideleve from"
					+" (select  somme/sommecoef as moyenne , query1.somme, query1.sommecoef, query1.ideleve from"
                   +" (select distinct composer.ideleve as ideleve,sum(note*cours.coeficient) as somme,"
                    +" sum(cours.coeficient) as sommecoef from"
                   +" cours, evaluation, composer, sequence,classe where"
                   +" cours.codecours=evaluation.codecours and composer.absencejustifiee=false"
                   +" and evaluation.codeevaluation=composer.codeevaluation and evaluation.idsequence="
                   +" sequence.idsequence and sequence.anneeacademique='"+anneeEncours+"' and sequence.numerosequence="+trim3+""
                  +" and cours.codeclasse=classe.codeclasse and classe.codeclasse='"+clas.getCodeclasse()+"'  group by"
                  +" composer.ideleve) as query1 )as query2 group by query2.ideleve order by moyenne asc)"
                   +" as trimes3) where trimes2.ideleve=trimes1.ideleve and trimes2.ideleve=trimes3.ideleve group by trimes1.ideleve order by moy asc) as toutmoy");
				
			return Double.parseDouble(req.getSingleResult().toString()) ;

			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}

	@Override
	public float tauxreussitetrim(String codeclasse, int seq1, int seq2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float tauxreussiteAnnuel(String codeclasse, int trim1, int trim2,
			int trim3) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int tauxreussitetrim(String anneeEncours, int sequence1,
			int sequence2, String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int tauxreussiteAnnuel(String anneeEncours, int trim1, int i, int j,
			String string) {
		// TODO Auto-generated method stub
		return 0;
	}


	@SuppressWarnings("unchecked")
	public List<Object[]> listerElevesNotesClasse(int numerosequence, String codeclasse,String annee) {
		Query req = em.createNativeQuery("SELECT eleve.matricule, eleve.nom, eleve.prenom, composer.note,evaluation.libelle, cours.coeficient "+
				"FROM composer,eleve,sequence,evaluation,cours where composer.ideleve=eleve.ideleve"+
				" and evaluation.codeevaluation=composer.codeevaluation and evaluation.codecours=cours.codecours and "+
				" evaluation.idsequence=sequence.idsequence and sequence.numerosequence='"+numerosequence+"' and"+
				" eleve.codeclasse='"+codeclasse+"' and eleve.anneeacademique='"+annee+"' order by eleve.nom");
		return req.getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listerElevesNotesClasseTrim(int k, int i,
			int j, String codeclasse, String annee) {
		Query req = em.createNativeQuery("SELECT eleve.matricule, eleve.nom, eleve.prenom, AVG(composer.note),evaluation.libelle "+
				"FROM composer,eleve,sequence,evaluation where composer.ideleve=eleve.ideleve"+
				" and evaluation.codeevaluation=composer.codeevaluation and "+
				" evaluation.idsequence=sequence.idsequence and sequence.numerosequence='"+k+"' " +
				"and sequence.numerosequence='"+j+"' and sequence.numerosequence='"+i+"' and"+
				" eleve.codeclasse='"+codeclasse+"' and eleve.anneeacademique='"+annee+"' order by eleve.nom");
		return req.getResultList();
	}

	@Override
	public int nombrereussiteAnnuel(String codeclasse, int trim1, int trim2,
			int trim3) {
		// TODO Auto-generated method stub
		return 0;
	}


}
