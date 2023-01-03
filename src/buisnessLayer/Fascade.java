package buisnessLayer;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JTable;

import dataAccessLayer.*;
import transferObject.*;

public class Fascade implements IFascade {
	ICorrector corrector = new Corrector();
	IDataInserter dataInserter = new DataInserter();
	IMutantGenerator mutantGenerator = new MutantGenerator();
	IReaderXML readerXML = new ReaderXML();
	IWordDAO wordDAO = new WordDAO();
	IMutantDAO mutantDAO = new MutantDAO();
	IWordTableManager tableManager = new WordTableManager();
	
	/*
	 * stores incorrect words into an array list and return that list 
	 */
	@Override
	public ArrayList<String> Incorrectwords(String sentence){
		return corrector.Incorrectwords(sentence);
	}
	
	/*
	 * stores correct words into an array list and return that list 
	 */
	@Override
	public ArrayList<String> correctWords(String sentence){
		return corrector.correctWords(sentence);
	}
	
	// Calls function to insert data into database
	@Override
	public void insertBuiltInData(String path,boolean wordRefEnter) {
		dataInserter.insertBuiltInData(path, wordRefEnter);
	}
	
	// Manually add new words by user
	@Override
	public void insertManualWord(String sentence, String userName) {
		dataInserter.insertManualWord(sentence, userName);
	}
	
	// Create mutants of manually added words
	
	@Override
	public void insertManualMutants(String word) {
		dataInserter.insertManualMutants(word);
	}
	
	// Create Mutants 
	@Override
	public Mutants generateMutants(Words _wordsList) {
		return mutantGenerator.generateMutants(_wordsList);
	}
	
	// Reading XML File
	@Override
	public boolean readFile(File file) {
		return readerXML.readFile(file);
	}
	
	// Return content
	@Override
	public Content getContent() {
		return readerXML.getContent();
	}
	
	// insert built-in words mutant
	@Override
	public void insertBuiltInMutants() {
		mutantDAO.insertBuiltInMutants();
	}
	// insert mutants for manually added words
	@Override
	public boolean manualWordMutant(String word) {
		return mutantDAO.manualWordMutant(word);
	}
	
	// Return all mutants from database
	@Override
	public Mutants getAllMutants() {
		return mutantDAO.getAllMutants();
	}

	@Override
	public void insertBuiltInData(String path) {
		wordDAO.insertBuiltInData(path);
	}
	
	@Override
	public Words getAllWords() {
		return wordDAO.getAllWords();
	}
	
	@Override
	public boolean manualAddWords(String userName, String word) {
		return wordDAO.manualAddWords(userName, word);
	}
	
	@Override
	public void insertWordRef() {
		wordDAO.insertWordRef();
	}
	@Override
	public ArrayList<wordTableData> getWordsList(){
		return wordDAO.getWordsList();
	}
	@Override
	public boolean updateWord(wordTableData data) {
		return wordDAO.updateWord(data);
	}
	@Override
	public boolean update(int id, String word, int frequency) {
		return tableManager.update(id, word, frequency);
	}
	@Override
	public void fillTable(JTable table ) {
		 tableManager.fillTable(table);
	}
}
