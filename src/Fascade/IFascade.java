package Fascade;

import java.io.File;
import java.util.ArrayList;

import transferObject.Content;
import transferObject.Mutants;
import transferObject.Words;

public interface IFascade {

	/*
	 * stores incorrect words into an array list and return that list 
	 */
	ArrayList<String> Incorrectwords(String sentence);

	/*
	 * stores correct words into an array list and return that list 
	 */
	ArrayList<String> correctWords(String sentence);

	// Calls function to insert data into database
	void insertBuiltInData(String path, boolean wordRefEnter);

	// Manually add new words by user
	void insertManualWord(String sentence, String userName);

	void insertManualMutants(String word);

	// Create Mutants 
	Mutants generateMutants(Words _wordsList);

	// Reading XML File
	boolean readFile(File file);

	// Return content
	Content getContent();

}