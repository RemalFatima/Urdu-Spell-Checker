package transferObject;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * 		Author : Absar Ali ( 20F-0232 )
 * 		Mutant class is used to transfer object of mutants between buisness layer and data access layer
 */

public class Mutants {
	
	private ArrayList<Mutant> mutant = new ArrayList<Mutant>();

	public ArrayList<Mutant> getMutant() {
		return mutant;
	}

	public void setMutant(ArrayList<Mutant> mutant) {
		this.mutant = mutant;
	}
	
	public void setMutant(HashMap<String,String> mutants) {
		
		for(String newMutants : mutants.keySet() )
		{
			if(newMutants != mutants.get(newMutants))
			this.put(newMutants, mutants.get(newMutants));
		}
	}

	public void put(String mutant, String word) {
		if(mutant != word) {
		Mutant newMutant = new Mutant();
		newMutant.setCorrectWord(word);
		newMutant.setMutantString(mutant);
		this.mutant.add(newMutant);
		}
	}
	
	public void merge(Mutants mutants) {
		this.mutant.addAll(mutants.getMutant());
	}
	public boolean containsKey(String word) {
		for(Mutant mutant : getMutant())
			if(mutant.getMutantString().contains(word))
				return true;
		return false;
	}
	public boolean containsMutant(String newMutant, String word) {
		
			if(mutant.contains(newMutant)  && mutant.contains(word))
				return true;
		return false;
	}
	
	
	
	
	
}
