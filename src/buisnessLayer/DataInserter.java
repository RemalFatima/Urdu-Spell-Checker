package buisnessLayer;

import dataAccessLayer.MutantDAO;
import dataAccessLayer.WordDAO;

public class DataInserter {
	
	WordDAO wordDAO;
	MutantDAO mutantDAO;
	
	public DataInserter(){
		wordDAO = new WordDAO();
		mutantDAO = new MutantDAO();
	}
	
	
	public void insertBuiltInData(String path,boolean wordRefEnter) {
		wordDAO.insertBuiltInData(path);
		if(wordRefEnter)
			wordDAO.insertWordRef();
		mutantDAO.insertBuiltInMutants();
		
	}
	
	public void insertManualWord(String sentence, String userName)
	{
		wordDAO.manualAddWords(userName, sentence);
	}
	public void insertManualMutants(String word)
	{
		mutantDAO.manualWordMutant(word);
	}

}
