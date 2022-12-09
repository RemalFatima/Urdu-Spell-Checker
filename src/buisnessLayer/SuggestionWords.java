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
	
	private IFascade dalFascade;
	public ArrayList<Mutant> suggestionWords(String word) {
		dalFascade = new Fascade();
		
		Mutants mutants = dalFascade.getAllMutants();
		
		ArrayList<Mutant> list = new ArrayList<Mutant>();
		
		for(Mutant mutant : mutants.getMutant()) {
			if(mutant.getMutantString().equals(word))
				list.add(mutant);
		}
		return list;
			
		
		
	}

}
