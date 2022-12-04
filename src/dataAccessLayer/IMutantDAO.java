package dataAccessLayer;

import transferObject.Mutants;

public interface IMutantDAO {

	/*
	 * Author Absar Ali ( 20F-0232)
	 * Function to insert mutant into database
	 */
	void insertBuiltInMutants();

	boolean manualWordMutant(String word);

	Mutants getAllMutants();

}