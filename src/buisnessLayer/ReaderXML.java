package buisnessLayer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import dataAccessLayer.WordDAO;
import transferObject.TransferContent;

public class ReaderXML {
	
	private String title;
	private String author;
	private String contentInFile;
	
	//Hashmap to store words 
	private HashMap<String, Integer> words = new HashMap<String,Integer>();
	private HashMap<String,String> wordForeignKey = new HashMap<String,String>();
	
	public HashMap<String, Integer> getWords(String content){
		
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

	// Reading XML File
	public boolean readFile(File file)
	{
		try {  
			
			//an instance of factory that gives a document builder  
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
			
			//an instance of builder to parse the specified xml file  
			DocumentBuilder db = dbf.newDocumentBuilder();  
			Document doc = db.parse(file);  
			doc.getDocumentElement().normalize();  
			
			// Reading tags and their value
			Element tElement = (Element) doc.getElementsByTagName("title").item(0);
			Element aElement = (Element) doc.getElementsByTagName("author").item(0);
			Element cElement = (Element) doc.getElementsByTagName("body").item(0);
			cElement.removeAttribute("annotation");
			aElement.removeAttribute("gender");
			
			// assigning value in tags to variables
			title =  tElement.getTextContent(); 
			if(tElement != null){
				title = title.replaceAll("(?U)[\\W_]+", " "); // Removing Punctuation marks
			}
			author = aElement.getTextContent();
			if(aElement != null){
				author = author.replaceAll("(?U)[\\W_]+", " ");
			}
			contentInFile = cElement.getTextContent();
			if(cElement != null){
				contentInFile = contentInFile.replaceAll("(?U)[\\W_]+", " ");
				contentInFile = contentInFile.replaceAll("[a-zA-Z]", " ");
				contentInFile = contentInFile.replaceAll("[0-9]", " ");
				contentInFile = contentInFile.replaceAll("[áéóؐ]", " ");
				contentInFile = contentInFile.replaceAll("\\d.", " ");
				
				
				getWords(contentInFile);
			}

		
		}   
		catch (Exception e)   
		{  
			System.out.println("Error in Reading XML file");  
			return false;
		} 
		return true;
	}
	
	 
	// Return content
	public TransferContent getContent() {
		TransferContent content = new TransferContent();
		content.setTitle(this.title);
		content.setAuthor(this.author);
		content.setContent(this.contentInFile);
		content.setWords(this.words);
		content.setWordForeignKey(this.wordForeignKey);
		return content;
	}
	
	public static void main(String[] args) {
		ReaderXML rd = new ReaderXML();
		WordDAO wd = new WordDAO();
		wd.insertData("C:\\Users\\Z\\OneDrive\\Documents\\5th Semester\\SCD Course\\Project\\files");
	}
	
}
