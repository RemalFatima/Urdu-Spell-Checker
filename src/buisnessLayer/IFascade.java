package buisnessLayer;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JTable;

import transferObject.Content;
import transferObject.Mutants;
import transferObject.Words;
import transferObject.wordTableData;

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

	// insert built-in words mutant
	void insertBuiltInMutants();

	// insert mutants for manually added words
	boolean manualWordMutant(String word);

	// Return all mutants from database
	Mutants getAllMutants();

	void insertBuiltInData(String path);

	Words getAllWords();

	boolean manualAddWords(String userName, String word);
	
	void insertWordRef();
	
	public ArrayList<wordTableData> getWordsList();
	
	public boolean updateWord(wordTableData data);
	
	public void fillTable(JTable table );
	
	public boolean update(int id, String word, int frequency);
		
	

}