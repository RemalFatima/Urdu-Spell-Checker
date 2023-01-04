

	/*
	 *	 @Author : Manal saqib  20F-0141  
	 *	 Added function suggestWords
	 * 
	 *	 @Author : AbsarAli  20F-0232 
	 *   Auto corrector function to show the auto completion with 3 edit distance
	 * 
	 */
package buisnessLayer;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import transferObject.Mutant;
import transferObject.Mutants;
/*
	Author : MANAL SAQIB  ( 20F-0141 )

    Introduce a suggestion class 

 */

import transferObject.Words;

public class SuggestionWords {

	//static Logger logger = Logger.getLogger(SuggestionWords.class);
	private IFascade dalFascade= new Fascade();
	Mutants mutants ;
	Words words;
	public SuggestionWords() {
		loadWordAndMutant();
		
	}


	public void loadWordAndMutant() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
				mutants = dalFascade.getAllMutants();
				words = dalFascade.getAllWords();
				}catch(Exception e) {
					Log.logger.info("Error in creating object of SuggestionWords");
					Log.logger.info(e.getCause());
					Log.logger.warn(e.getMessage());
					
				}
			}
		}).start();
	}
	
	
	public ArrayList<Mutant> suggestWords(String word) {

		ArrayList<Mutant> list = new ArrayList<Mutant>();
		try {
		for(Mutant mutant : mutants.getMutant()) {
			if(mutant.getMutantString().equals(word))
				list.add(mutant);
		}
		} catch(Exception e) {
			Log.logger.warn(e.getMessage());
		}
		
		return list;

	}

	/*
	 * @Author : AbsarAli  20F-0232 
	 * @class : Auto corrector function to show the auto completion with 3 edit distance
	 * 
	 */

	public ArrayList<String> autoCorrection(String text){
		ArrayList<String> autoCorrections = new ArrayList<String>();
		try {
		if(text.length() > 2) {
			for(String word : words.getWords().keySet()) {

				if(similarityExists(text, word))

					autoCorrections.add(word);
					
			}
		}
		} catch (Exception e) {
			Log.logger.info("Error in function autoCorrection() in BLL SuggestionWords");
			Log.logger.info(e.getCause());
			Log.logger.warn(e.getMessage());
		}
		return autoCorrections;
	}


	public boolean similarityExists(String text, String word) {
		return word.contains(text) && text.length() + 3 >= word.length() && words.getWords().get(word) > 2;
	}

}
