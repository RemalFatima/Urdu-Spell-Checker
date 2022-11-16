package buisnessLayer;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ReaderXML {
	
	private String title;
	private String author;
	private String content;
	private ArrayList<String> words = new ArrayList<String>();
	
	private ArrayList<String> getWords(String content){
		
		// Using split function.
        for (String word: content.split(" ")) {
        	words.add(word);
        }
		
		return words;
	}

	// Reading XML File
	private void readFile(File file)
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
			content = cElement.getTextContent();
			if(cElement != null){
				content = content.replaceAll("(?U)[\\W_]+", " ");
			}

		
		}   
		catch (Exception e)   
		{  
			System.out.println("Error in Reading XML file");  
		} 
	}
}
