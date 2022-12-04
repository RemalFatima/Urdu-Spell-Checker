package buisnessLayer;

import java.util.HashMap;

import dataAccessLayer.IWordDAO;
import dataAccessLayer.WordDAO;
import transferObject.Mutants;
import transferObject.Words;


/*
 * 		Author : Absar Ali ( 20F - 0232 ) 
 * 		
 * 		Mutant generator create mutants of words
 */

public class MutantGenerator implements IMutantGenerator {
	
	IWordDAO wordDAO;
	
	public MutantGenerator() {
		wordDAO = new WordDAO();
		
	}
	
	// Create Mutants 
	@Override
	public Mutants generateMutants(Words _wordsList) {
		
		Words words = _wordsList;
		Mutants mutants = new Mutants();
		HashMap<String, String> mutantList = new HashMap<String,String>();
		
		
		for(String word : words.getWords().keySet()) { // Read each word
			String temp = word;
			
			// Assuming there will be only 1 mistake change 1 character and create mutant on phonetic basis
			for(int i = 0 ; i < word.length() - 1; i++)
			{
				// ا ع
				if(word.charAt(i) == 'ا') {
					StringBuilder strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'ع');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
				}
				else if(word.charAt(i) == 'ع') {
					StringBuilder strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'ا');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
				}
				
				// ت ط
				else if(word.charAt(i) == 'ط') {
					StringBuilder strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'ت');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
				}
				else if(word.charAt(i) == 'ت') {
					StringBuilder strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'ط');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
				}
				
				
				// ض ظ ز ذ
				else if(word.charAt(i) == 'ذ') {
					StringBuilder strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'ز');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
					
				}
				else if(word.charAt(i) == 'ز') {
					StringBuilder strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'ذ');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
				}
				
				// ظ ض
				else if(word.charAt(i) == 'ظ') {
					StringBuilder strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'ض');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
				}
				else if(word.charAt(i) == 'ض') {
					StringBuilder strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'ظ');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
				}
				
				// ث ش
				else if(word.charAt(i) == 'ش') {
					StringBuilder strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'ث');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
				}
				else if(word.charAt(i) == 'ث') {
					StringBuilder strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'ش');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
				}
				
				// ھ ح ہ
				else if(word.charAt(i) == 'ح') {
					StringBuilder strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'ھ');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
					strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'ہ');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
				}
				else if(word.charAt(i) == 'ھ') {
					StringBuilder strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'ح');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
					strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'ہ');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
				}
				else if(word.charAt(i) == 'ہ') {
					StringBuilder strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'ح');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
					strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'ھ');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
				}
				
				// س ص
				else if(word.charAt(i) == 'س') {
					StringBuilder strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'ص');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
				}
				else if(word.charAt(i) == 'ص') {
					StringBuilder strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'س');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
				}
				
				// گ غ
				else if(word.charAt(i) == 'غ') {
					StringBuilder strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'گ');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
				}
				else if(word.charAt(i) == 'گ') {
					StringBuilder strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'غ');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
				}
				
				// ک ق
				else if(word.charAt(i) == 'ک') {
					StringBuilder strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'ق');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
				}
				else if(word.charAt(i) == 'ق') {
					StringBuilder strBuilder = new StringBuilder(temp);
					strBuilder.setCharAt(i, 'ک');
					if(!mutantList.containsKey(strBuilder))
						mutantList.put(strBuilder.toString(), word);
				}
				

			}
		}
		
		mutants.setMutant(mutantList); // store all mutants in hashmap
//		for(String key : mutants.getMutant().keySet())
//		{
//			System.out.println(key);
//		}
		return mutants;
		
	}
	
	
	
}
