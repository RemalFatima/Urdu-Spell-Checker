package buisnessLayer;

import dataAccessLayer.WordDAO;

public class DataInserter {
	
	WordDAO wordDAO;
	
	public DataInserter(){
		wordDAO = new WordDAO();
	}
	
	
	public void insertBuiltInData(String path) {
		wordDAO.insertBuiltInData(path);
	}

}
