/*
 * @Author : ManalSaqib 20F-0141
 * @class : Corrector stores incorrect words and correct words into an array list 
 * 
 */

package buisnessLayer;

import java.util.ArrayList;
import java.util.HashMap;

import Fascade.Fascade;
import Fascade.IFascade;
import dataAccessLayer.IWordDAO;
import dataAccessLayer.WordDAO;
import transferObject.Words;

public class Corrector implements ICorrector {
IFascade dalFascade ;
Words words = new Words();
/*
 * stores incorrect words into an array list and return that list 
 */
public ArrayList<String> Incorrectwords(String sentence)
{
	dalFascade = new Fascade();
	words = dalFascade.getAllWords();
	ArrayList<String> incorrectWords = new ArrayList<String>();
	for(String word : sentence.split(" ")) {
		if(!words.getWords().containsKey(word))
		{
			incorrectWords.add(word);	
			
		}
	}
	return incorrectWords;
}
/*
 * stores correct words into an array list and return that list 
 */
public ArrayList<String> correctWords(String sentence)
{
	dalFascade = new Fascade();
	words = dalFascade.getAllWords();
	ArrayList<String> correctWords = new ArrayList<String>();
	for(String word : sentence.split(" ")) {
		if(words.getWords().containsKey(word))
		{
			correctWords.add(word);	
	    }
	}
	return correctWords;
}
}
