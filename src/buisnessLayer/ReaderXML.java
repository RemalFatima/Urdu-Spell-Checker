package buisnessLayer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import dataAccessLayer.WordDAO;
import transferObject.Content;

public class ReaderXML implements IReaderXML {
	
	private String title;
	private String author;
	private String contentInFile;
	

	


	// Reading XML File
	@Override
	public boolean readFile(File file)
	{
		try {  
			
			//an instance of factory that gives a document builder  
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
			
			//an instance of builder to parse the specified xml file  
			DocumentBuilder db = dbf.newDocumentBuilder();  
			Document doc = db.parse(file);  
			doc.getDocumentElement().normalize();  
			Element aElement;
			// Reading tags and their value
			Element tElement = (Element) doc.getElementsByTagName("title").item(0);
			
			aElement = (Element) doc.getElementsByTagName("author").item(0);
			
			Element cElement = (Element) doc.getElementsByTagName("document").item(0);
	
			// assigning value in tags to variables
			title =  tElement.getTextContent(); 
			if(tElement != null){
				title = title.replaceAll("(?U)[\\W_]+", " "); // Removing Punctuation marks
			}
			if(aElement != null) {
			author = aElement.getTextContent();
			author = author.replaceAll("(?U)[\\W_]+", " ");
			author = author.replaceAll("[a-zA-Z]", " ");
			
			}
			else
				author = "No Author";
			contentInFile = cElement.getTextContent();
			if(cElement != null){
				contentInFile = contentInFile.replaceAll("(?U)[\\W_]+", " ");
				contentInFile = contentInFile.replaceAll("[a-zA-Z]", " ");
				contentInFile = contentInFile.replaceAll("[0-9]", " ");
				contentInFile = contentInFile.replaceAll("[áéóؐ]", " ");
				
				
				
			
			}

		
		}   
		catch (Exception e)   
		{  
			System.out.println("Error in Reading XML file in " + file.getName());  
			System.out.println(e.getMessage());  
			
			return false;
		} 
		return true;
	}
	
	 
	// Return content
	@Override
	public Content getContent() {
		Content content = new Content();
		content.setTitle(this.title);
		content.setAuthor(this.author);
		content.setContent(this.contentInFile);
		return content;
	}
	

	
}