package transferObject;

import java.util.HashMap;

/*
 * 		Author : Absar Ali ( 20F-0232 )
 * 		Mutant class is used to transfer object of mutants between buisness layer and data access layer
 */

public class Mutants {
	
	private HashMap<String, String> mutant = new HashMap<String,String>();

	public HashMap<String, String> getMutant() {
		return mutant;
	}

	public void setMutant(HashMap<String, String> mutant) {
		this.mutant = mutant;
	}
	
}
