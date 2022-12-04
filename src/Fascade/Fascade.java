package Fascade;

import java.io.File;
import java.util.ArrayList;

import buisnessLayer.*;
import transferObject.Content;
import transferObject.Mutants;
import transferObject.Words;

public class Fascade implements IFascade {
	ICorrector corrector;
	IDataInserter dataInserter;
	IMutantGenerator mutantGenerator;
	IReaderXML readerXML;
	
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

}
