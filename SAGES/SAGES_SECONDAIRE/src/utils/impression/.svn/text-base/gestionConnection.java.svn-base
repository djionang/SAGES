package utils.impression;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * GestionConnection
 * @author Djionang
 *
 */

public class gestionConnection {
	
	  private  Connection con;
	  
	    public Connection getCon() {
		return con;
	}


	public void setCon(Connection con) {
		this.con = con;
	}


		public String loginBD = "root";
	    public String passBD = "root";
	    
	    
	    
	    public boolean connexionBD() 
	    {  
	        String  user=  loginBD;//"root";
	        String  password= passBD;//"root";
	        String path="jdbc:mysql://localhost:3306/sages_secondaire";
	        try{
	            System.out.println("connection");
	            Class.forName("org.gjt.mm.mysql.Driver");
	            con = (Connection) DriverManager.getConnection(path,user,password);
	            return true;
	        }
	        catch(Exception e){
	            javax.swing.JOptionPane.showMessageDialog(null, e);
	            return false;
	        }
	    }


	    // Deconnexion a la BD
	    private Boolean disconnect()
	    {      System.out.println("deconnection");
	            try{
	                    con.close();
	                    return true;
	            }
	            catch (Exception e){
	                    return false;
	            }
	    }
	    //Quelques infos de l'etablissement
	    public Vector<String> infoEtablissement(String codeetablissement) throws SQLException{
	        Vector<String> infoEtab = new Vector<String>() ;
	        ResultSet resultat = null;
	        connexionBD();
	        resultat = con.createStatement().executeQuery("select nom ,devise,adresse ,telephone from Etablissement  where etablissement.codeetablissement='"+codeetablissement+"'");
	        while(resultat.next()){
	           infoEtab.add(resultat.getString(1));
	           infoEtab.add(resultat.getString(2));
	           infoEtab.add(resultat.getString(3));
	           infoEtab.add(resultat.getString(4));
	        }
	        disconnect();
	    return infoEtab;
	    }
	    
	    
	    public Vector<String> ListerEleveProvisoire(String codeclasse,String annee) throws SQLException{
	    	Vector<String> elevepro = new Vector<String>() ;
	        ResultSet resultat = null;
	        connexionBD();
	        resultat = con.createStatement().executeQuery("select matricule,nom,prenom,datenaissance from eleve where eleve.codeclasse='"+codeclasse+"' and eleve.anneeacademique='"+annee+"'");
	        while(resultat.next()){
	        	elevepro.add(resultat.getString(1));
	        	System.out.println("voici le matricule de l'eleve  "+resultat.getString(1));
	        	elevepro.add(resultat.getString(2));
	        	elevepro.add(resultat.getString(3));
	        	elevepro.add(resultat.getString(4));
	        }
	        disconnect();
	    return elevepro;
	    }
 
	    
	    public Vector<String> RangEleve(String codeclasse,String annee) throws SQLException{
	    	Vector<String> elevepro = new Vector<String>() ;
	        ResultSet resultat = null;
	        connexionBD();
	        resultat = con.createStatement().executeQuery("select matricule,nom,prenom,datenaissance from eleve where eleve.codeclasse='"+codeclasse+"' and eleve.anneeacademique='"+annee+"'");
	        while(resultat.next()){
	        	elevepro.add(resultat.getString(1));
	        	System.out.println("voici le matricule de l'eleve  "+resultat.getString(1));
	        	elevepro.add(resultat.getString(2));
	        	elevepro.add(resultat.getString(3));
	        	elevepro.add(resultat.getString(4));
	        }
	        disconnect();
	    return elevepro;
	    }
	    
	    public int rangEleveMatiere(String codeclasse,String codematiere,String annee){
	    	
	    	//List ls = new List();
	    	ResultSet resultat = null;
	        connexionBD();
	        try {
				resultat = con.createStatement().executeQuery("select note,composer.matricule from composer, evaluation, cours,optionmatiere,matiere where evaluation.codeevaluation=composer.codeevaluation and  cours.codecours = evaluation.codecours and cours.codeclasse='"+codeclasse+"' and cours.code=optionmatiere.codeom and matiere.codematiere=optionmatiere.codematiere and matiere.codematiere='"+codematiere+"'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
		
		return 0;
	    }
	    
	    public float rangEleveClasse(){
	    	
	    	
	    	ResultSet resultat = null;
	        connexionBD();
	        try {
				resultat = con.createStatement().executeQuery("select composer.matricule,sum(note*cours.`COEFICIENT`)/sum(cours.`COEFICIENT`)"+
	    	"as moyenne from composer,cours, eleve, evaluation,"+
	    	" classe where composer.`CODEEVALUATION`=evaluation.`CODEEVALUATION` and composer.matricule=eleve.matricule and"+
	    	"evaluation.codecours=cours.codecours and cours.codeclasse=classe.codeclasse and"+
	    	"eleve.codeclasse= classe.codeclasse GROUP BY eleve.matricule");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        return 0;
          
	    }
	    
	    public static void main(String[] args){
	    	
	    	gestionConnection  gc = new gestionConnection();
	    	gc.rangEleveMatiere("","","");
	    	
	    }
	    
	    


			


}
