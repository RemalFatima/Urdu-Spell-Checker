package buisnessLayer;

import java.util.ArrayList;

import Fascade.Fascade;
import Fascade.IFascade;
import transferObject.Mutant;
import transferObject.Mutants;
/*
	Author : MANAL SAQIB  ( 20F-0141 )

    Introduce a suggestion class 

 */

import transferObject.Words;

public class SuggestionWords {

	private IFascade dalFascade= new Fascade();
	Mutants mutants ;
	Words words;
	public SuggestionWords() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				mutants = dalFascade.getAllMutants();
				words = dalFascade.getAllWords();
			}
		}).start();

	}
	public ArrayList<Mutant> suggestionWords(String word) {

		ArrayList<Mutant> list = new ArrayList<Mutant>();

		for(Mutant mutant : mutants.getMutant()) {
			if(mutant.getMutantString().equals(word))
				list.add(mutant);
		}
		return list;

	}

	public ArrayList<String> autoCorrection(String text){
		ArrayList<String> autoCorrections = new ArrayList<String>();
		if(text.length() > 2) {
			for(String word : words.getWords().keySet()) {
				if(word.contains(text) && text.length() + 3 >= word.length() && words.getWords().get(word) > 2)
					autoCorrections.add(word);
					
			}
		}
		return autoCorrections;
	}

}
