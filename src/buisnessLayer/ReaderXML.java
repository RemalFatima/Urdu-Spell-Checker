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
	//private ArrayList<String> words = new ArrayList<String>();
	private HashMap<String, Integer> words = new HashMap<String,Integer>();
	
	public HashMap<String, Integer> getWords(String content){
		
		// Using split function.
        for (String word: content.split(" ")) {
        	if(!words.containsKey(word))
        		words.put(word, 1);
        	else
        		words.put(word,words.get(word) + 1);
        }
		
		return words;
	}

	// Reading XML File
	public void readFile(File file)
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
			Element cElement = (Element) doc.getElementsByTagName("section").item(0);
			
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
				getWords(contentInFile);
			}

		
		}   
		catch (Exception e)   
		{  
			System.out.println("Error in Reading XML file");  
		} 
	}
	
	
	public TransferContent getContent() {
		TransferContent content = new TransferContent();
		content.setTitle(this.title);
		content.setAuthor(this.author);
		content.setContent(this.contentInFile);
		content.setWords(this.words);
		return content;
	}
	
	public static void main(String[] args) {
		ReaderXML rd = new ReaderXML();
		WordDAO wd = new WordDAO();
		wd.insertContent();
		wd.insertWords();
	}
	
}
