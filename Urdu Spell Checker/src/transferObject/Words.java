package transferObject;
import java.util.HashMap;

/*
 	Author : Absar Ali ( 20F-0232 )

	To move the object of Words between Buisness layer & data access layer
 
 */

public class Words {

	
	private HashMap<String, Integer> words = new HashMap<String,Integer>();
	
	
	public HashMap<String, Integer> getWords() {
		return words;
	}
	public void setWords(HashMap<String, Integer> words) {
		this.words = words;
	}
	
	public void put(String word, int frequency)
	{
		words.put(word, frequency);
	}

	
	
}
