package dataAccessLayer;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import transferObject.Words;
import transferObject.wordTableData;

public interface IWordDAO {

	// Insert data into content table ( title , author , content in file )
	// Author  : Remal Fatima
	void insertContent(File file);

	Words getWords(String content, String title);

	void insertWords();

	void insertWordRef();

	void insertBuiltInData(String path);
	
	boolean updateWord(wordTableData data);

	Words getAllWords();

	// Author : Remal Fatima 
	// Manually add new words by user
	boolean manualAddWords(String userName, String word);
	public ArrayList<wordTableData> getWordsList();

}