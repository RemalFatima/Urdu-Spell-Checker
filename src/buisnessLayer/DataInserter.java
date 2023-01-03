package buisnessLayer;

import org.apache.log4j.Logger;

import dataAccessLayer.IMutantDAO;
import dataAccessLayer.IWordDAO;
import dataAccessLayer.MutantDAO;
import dataAccessLayer.WordDAO;


/*
 * 	Author Absar Ali
 */

public class DataInserter implements IDataInserter {
	
	//static Logger logger = Logger.getLogger(DataInserter.class);
	
	private IFascade dalFascade;
	
	
	// Author : Remal Fatima
	// Calls function to insert data into database
	public void insertBuiltInData(String path,boolean wordRefEnter) {
		dalFascade = new Fascade();
		dalFascade.insertBuiltInData(path);
		if(wordRefEnter) {
			dalFascade.insertWordRef();
			Log.logger.info("Ignoring Word references");
			
		}
		dalFascade.insertBuiltInMutants();
		Log.logger.info("Word mutants started generating");
		
		
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
