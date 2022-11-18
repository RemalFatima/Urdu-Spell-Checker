package dataAccessLayer;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import buisnessLayer.ReaderXML;
import transferObject.TransferContent;



public class WordDAO {
	
	ReaderXML readerXML;
	
	public WordDAO() {
		readerXML = new ReaderXML();
	}
	
	String url = "jdbc:mysql://localhost:3306/content?useSSL=false";
    String user = "root";
    String password = "Rem@9500";
    
    // Insert data into content table ( title , author , content in file )
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
    
    // Insert word into Words table ( word , frequency , title of file where found first )
    public void insertWords(Connection con) {
    	
		try {
			// create connection
			con = DriverManager.getConnection(url, user, password);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error in creating connection - Function insertWords in WordDAO");
			
		}
		
    	
    	// Get Content
    	TransferContent contents = readerXML.getContent();
    	// Remove null words from Map
    	contents.getWords().remove("");
    	contents.getWordForeignKey().remove("");

    	// Insert Words into table
    	for(String key : contents.getWords().keySet() ) {
    		String query = "INSERT INTO `Words` (word, frequency, title) VALUE ('" + key + "' ," + contents.getWords().get(key) + ",'" + contents.getWordForeignKey().get(key) + "')";
    	
    		try {
    			// execute query
    			Statement st = con.createStatement();
    			st.executeUpdate(query);

    		} catch (SQLException ex) {
    			System.out.println(ex.getMessage());
    			System.out.println("Error in executing query to insert words into tabe `Words` - Function insertWords in WordDAO");
    			
    		}
    	}
    	
    }
    
    // Insert Both content and words into database
    // Arguments take path of folder where file is located
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
    	insertWords(con);
    }
}
