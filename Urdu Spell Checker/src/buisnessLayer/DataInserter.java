package buisnessLayer;

import Fascade.Fascade;
import Fascade.IFascade;
import dataAccessLayer.IMutantDAO;
import dataAccessLayer.IWordDAO;
import dataAccessLayer.MutantDAO;
import dataAccessLayer.WordDAO;


/*
 * 	Author Absar Ali
 */

public class DataInserter implements IDataInserter {
	
	private IFascade dalFascade;
	
	
	// Author : Remal Fatima
	// Calls function to insert data into database
	public void insertBuiltInData(String path,boolean wordRefEnter) {
		dalFascade = new Fascade();
		dalFascade.insertBuiltInData(path);
		if(wordRefEnter)
			dalFascade.insertWordRef();
		
		dalFascade.insertBuiltInMutants();
		
	}
	
	// Author : Remal Fatima 
	// Manually add new words by user
	public void insertManualWord(String sentence, String userName)
	{
		dalFascade = new Fascade();
		dalFascade.manualAddWords(userName, sentence);
	}
	
	// Author : Absar Ali
	// Create mutants of manually added words
	
	public void insertManualMutants(String word)
	{
		dalFascade = new Fascade();
		dalFascade.manualWordMutant(word);
	}

}
