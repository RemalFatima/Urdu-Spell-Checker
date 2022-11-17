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
	
	String url = "jdbc:mysql://localhost:3307/content?useSSL=false";
    String user = "root";
    String password = "Rem@9500";
    
    public void insertContent() {
    	readerXML.readFile(new File("C:\\Users\\Z\\Downloads\\0001.xml"));
    	
    	TransferContent contents = readerXML.getContent();
    	String query = "INSERT INTO `content` (Title, Author, content) VALUE ('" + contents.getTitle() + "' ,'" + contents.getAuthor() + "','" + contents.getContent() + "')";
    	
    	 try {
         	Connection con = DriverManager.getConnection(url, user, password);
         	Statement st = con.createStatement();
             st.executeUpdate(query);

         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
    }
    public void insertWords() {
    	Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	readerXML.readFile(new File("C:\\Users\\Z\\Downloads\\0001.xml"));
    	
    	TransferContent contents = readerXML.getContent();
    	for(String key : contents.getWords().keySet() ) {
    		String query = "INSERT INTO `Words` (word, frequency, title) VALUE ('" + key + "' ," + contents.getWords().get(key) + ",'" + contents.getTitle() + "')";
    	
    		try {
    			Statement st = con.createStatement();
    			st.executeUpdate(query);

    		} catch (SQLException ex) {
    			System.out.println(ex.getMessage());
    		}
    	}
    }
}
