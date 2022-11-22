package dataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import buisnessLayer.MutantGenerator;
import transferObject.Mutants;
import transferObject.Words;


/*
 * Author Absar Ali ( 20F-0232)
 *
 */

public class MutantDAO {
	
	String url = "jdbc:mysql://localhost:3307/content?useSSL=false";
    String user = "root";
    String password = "";
	 
    /*
     * Author Absar Ali ( 20F-0232)
     * Function to insert mutant into database
     */
    public void insertBuiltInMutants() {
    	WordDAO wordDAO = new WordDAO();
    	MutantGenerator mutantGenerator = new MutantGenerator();
    	Mutants mutants = mutantGenerator.generateMutants(wordDAO.getAllWords());
    	String query ;
    	Connection con = null;
    	try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	for(String key : mutants.getMutant().keySet()) {
			try {
				
				Statement st = con.createStatement();
				query = "INSERT INTO `Mutants` (mutant, CorrectWord) VALUE ('" + key + "' ,'" + mutants.getMutant().get(key) + "')";
				st.executeUpdate(query);
				
			} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}
    	}
    }
 
    public boolean manualWordMutant(String word)
    {
    	Mutants mutants = new Mutants();
    	Words words = new Words();
    	words.put(word, 3);
    	MutantGenerator mutantGenerator = new MutantGenerator();
    	mutants = mutantGenerator.generateMutants(words);
    	Connection con = null;
    	String query = "INSERT INTO `Words` (word, frequency) VALUE ('" + word + "' ," + 1 + ")";
    	
    	try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	
    	for(String key : mutants.getMutant().keySet()) {
			try {
				
				Statement st = con.createStatement();
				query = "INSERT INTO `Mutants` (mutant, CorrectWord) VALUE ('" + key + "' ,'" + mutants.getMutant().get(key) + "')";
				st.executeUpdate(query);
				
			} catch (SQLException e) {
				
				System.out.println(e.getMessage());
				return false;
			}
    	}
    	
    	return true;
    }
 
    public Mutants getAllMutants() {
		
     String query = "SELECT * FROM `mutants`";

     Mutants mutants = new Mutants();
   
     
     try {
     	Connection con = DriverManager.getConnection(url, user, password);
     	Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(query);
         while (rs.next()) {
         	
         	
         	mutants.getMutant().put(rs.getString(1),rs.getString(2));

         }

     } catch (SQLException ex) {
         System.out.println(ex.getMessage());
     }
     return mutants;
	}
   

}
