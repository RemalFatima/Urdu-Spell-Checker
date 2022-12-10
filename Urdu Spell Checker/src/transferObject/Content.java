package transferObject;

import java.util.ArrayList;
import java.util.HashMap;


//Transfer Content between business layer and data access layer
public class Content {

	private String title;
	private String author;
	private String content;
	private HashMap<String,Integer> words = new HashMap<String,Integer>();
	private HashMap<String,String> wordForeignKey = new HashMap<String,String>();
	
	public HashMap<String, String> getWordForeignKey() {
		return wordForeignKey;
	}
	public void setWordForeignKey(HashMap<String, String> wordForeignKey) {
		this.wordForeignKey = wordForeignKey;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public HashMap<String,Integer> getWords() {
		return words;
	}
	public void setWords(HashMap<String,Integer> words) {
		this.words = words;
	}
	
	
}
