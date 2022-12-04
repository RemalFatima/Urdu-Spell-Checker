package dataAccessLayer;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;

import transferObject.Words;

public interface IWordDAO {

	// Insert data into content table ( title , author , content in file )
	// Author  : Remal Fatima
	void insertContent(File file, Connection con);

	HashMap<String, Integer> getWords(String content, String title);

	void insertWords();

	void insertWordRef();

	void insertBuiltInData(String path);

	Words getAllWords();

	// Author : Remal Fatima 
	// Manually add new words by user
	boolean manualAddWords(String userName, String word);

}