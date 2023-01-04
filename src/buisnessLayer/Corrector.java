/*
 * @Author : ManalSaqib 20F-0141
 * @class : Corrector stores incorrect words and correct words into an array list 
 * 
 */

package buisnessLayer;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.*;

import dataAccessLayer.IWordDAO;
import dataAccessLayer.WordDAO;
import transferObject.Words;

public class Corrector implements ICorrector {

	//static Log.logger Log.logger = Log.logger.getLog.logger(Corrector.class);
	IFascade dalFascade ;
	Words words = new Words();
	
	public ArrayList<String> Incorrectwords(String sentence)
	{
		dalFascade = new Fascade();
		words = dalFascade.getAllWords();
		ArrayList<String> incorrectWords = new ArrayList<String>();
		try {
			for(String word : sentence.split(" ")) {
				if(!words.getWords().containsKey(word))
				{
					incorrectWords.add(word);	

				}
			}
		} catch(Exception e) {
			Log.logger.info("Error in function IncorrectWords() in BLL Corrector");
			Log.logger.info(e.getCause());
			Log.logger.warn(e.getMessage());
		}
		return incorrectWords;
	}
	
	public ArrayList<String> correctWords(String sentence)
	{
		dalFascade = new Fascade();
		words = dalFascade.getAllWords();
		ArrayList<String> correctWords = new ArrayList<String>();
		try {
			for(String word : sentence.split(" ")) {
				if(words.getWords().containsKey(word))
				{
					correctWords.add(word);	
				}
			}
		}catch(Exception e) {
			Log.logger.info("Error in function IncorrectWords() in BLL Corrector");
			Log.logger.info(e.getCause());
			Log.logger.warn(e.getMessage());
		}
		return correctWords;
	}
}
