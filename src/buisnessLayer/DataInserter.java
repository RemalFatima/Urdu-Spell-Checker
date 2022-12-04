package buisnessLayer;

import dataAccessLayer.IMutantDAO;
import dataAccessLayer.IWordDAO;
import dataAccessLayer.MutantDAO;
import dataAccessLayer.WordDAO;


/*
 * 	Author Absar Ali
 */

public class DataInserter implements IDataInserter {
	
	IWordDAO wordDAO;
	IMutantDAO mutantDAO;
	
	public DataInserter(){
		wordDAO = new WordDAO();
		mutantDAO = new MutantDAO();
	}
	
	// Author : Remal Fatima
	// Calls function to insert data into database
	public void insertBuiltInData(String path,boolean wordRefEnter) {
		wordDAO.insertBuiltInData(path);
		if(wordRefEnter)
			wordDAO.insertWordRef();
		mutantDAO.insertBuiltInMutants();
		
	}
	
	// Author : Remal Fatima 
	// Manually add new words by user
	public void insertManualWord(String sentence, String userName)
	{
		wordDAO.manualAddWords(userName, sentence);
	}
	
	// Author : Absar Ali
	// Create mutants of manually added words
	
	public void insertManualMutants(String word)
	{
		mutantDAO.manualWordMutant(word);
	}

}
