package dataAccessLayer;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import buisnessLayer.ReaderXML;
import transferObject.*;


// Author  : Remal Fatima


public class WordDAO {
	
	ReaderXML readerXML;
	private HashMap<String, Integer> words = new HashMap<String,Integer>();
	private HashMap<String,String> wordForeignKey = new HashMap<String,String>();
	private ArrayList<String> content = new ArrayList<String>();
	
	
	public WordDAO() {
		readerXML = new ReaderXML();
	}
	
	String url = "jdbc:mysql://localhost:3307/content?useSSL=false";
    String user = "root";
    String password = "";
    
    // Insert data into content table ( title , author , content in file )
    // Author  : Remal Fatima
    public void insertContent(File file, Connection con) {
    	
    	// Read File
    	readerXML.readFile(file);
    	// Get content from file
    	TransferContent contents = readerXML.getContent();
    	// query
    	String query = "INSERT INTO `Content` (Title, Author, content) VALUE ('" + contents.getTitle() + "' ,'" + contents.getAuthor() + "','" + contents.getContent() + "')";
    	
    	 try {
         	// execute query
         	Statement st = con.createStatement();
             st.executeUpdate(query);

         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
             System.out.println("Error in inserting content into table `Content` - Function insertContent in WordDAO");
         }
    }
    
	public HashMap<String, Integer> getWords(String content, String title){
		
		// Using split function.
        for (String word: content.split(" ")) {
        	if(!(words.containsKey(word) || word == " ")) {
        		words.put(word, 1);
        		wordForeignKey.put(word, title);
        	}
        	else
        		words.put(word,words.get(word) + 1);
        }
		
		return words;
	}
    
	
	   
    // Author  : Remal Fatima
    // Insert word into Words table ( word , frequency , title of file where found first )
   	
    public void returnContent() {
    	
    	String query = "Delete From `Words`";
    	Connection con = null;
    	String content;
    	String title;
    	
    	try {
        	con = DriverManager.getConnection(url, user, password);
        	Statement st = con.createStatement();
        	//st.execute(query);
        	query = "Select * from `Content`";
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
        
            	title = rs.getString(1);
            	content = rs.getString(3);
            	getWords(content,title);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    	
    	// Remove null words from Map
    	words.remove("");
    	wordForeignKey.remove("");
    	Words word = getAllWords();
    	
    	for(String key : words.keySet()) {
			
			
		
    		try {
			
				Statement st = con.createStatement();
//				query = "INSERT INTO `WordReference` (word, FileName) VALUES ('" + key + "' ,'" + wordForeignKey.get(key) + "')";

				if(!word.getWords().containsKey(key) ) {
				
					query = "INSERT INTO `Words` (word, frequency) VALUE ('" + key + "' ," + words.get(key) + ")";
					st.executeUpdate(query);
				}
				else {
					query = "Update Words set frequency = (frequency + " + words.get(key) + ") Where word = '" + key + "'";
					st.executeUpdate(query);
					System.out.println("update");
				}
	        
				} catch (SQLException e) {
					
					
					System.out.println(e.getMessage());
				}
    	}
    	
    }
    
 
    
    // Author  : Remal Fatima
    // Insert Both content and words into database
    // Arguements take path of folder where file is located
    
    public void insertData(String path) {
    	
    	File folder = new File(path);
    	Connection con = null;
    	
    	// create connection
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Insert content ( title , author , content in file ) 
    	for ( File file : folder.listFiles()) {
            
    		insertContent(file,con);
    		
        }
    	// insert words into DB
    	//insertWords(con);
    }
    
    
    /*
     * 		Author Absar Ali ( 20F-0232 ) 
     * 		Function : getAllWords()
     * 		Return Words and frequency from Database
     */
    
    public Words getAllWords() {
		
        String query = "SELECT word , frequency FROM `Words`";

        Words words = new Words();
      
        
        try {
        	Connection con = DriverManager.getConnection(url, user, password);
        	Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
            	
            	
            	words.put(rs.getString(1),Integer.parseInt(rs.getString(2)));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return words;
	}
    
  //  public static void main(String[] args) {
 //   	WordDAO wd = new WordDAO();
 //   	//wd.insertData("C:\\Users\\Hp\\Downloads\\makhzan-master\\makhzan-master\\text");
 //   	wd.returnContent();
 //   }
}
