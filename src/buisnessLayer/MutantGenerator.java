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



	// Create Mutants 
	@Override
	public Mutants generateMutants(Words _wordsList) {

		Words words = _wordsList;
		Mutants mutants = new Mutants();
		HashMap<String, String> mutantList = new HashMap<String,String>();


		for(String word : words.getWords().keySet()) { // Read each word
			String temp = word;

			// create combination of mutants
			for(int a = 0; a < word.length(); a++) {
				temp = word;
				
				for(int i = a ; i < word.length(); i++)
				{
					
					for(int j = i; j < word.length(); j++) {

						{
							// ا ع
							if(word.charAt(i) == 'ا') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ع');
								temp = strBuilder.toString();
								if(!mutantList.containsKey(temp))
									mutantList.put(temp, word);
							}
							else if(word.charAt(i) == 'ع') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ا');
								temp = strBuilder.toString();
								if(!mutantList.containsKey(temp))
									mutantList.put(temp, word);				}

							// ت ط
							if(word.charAt(i) == 'ط') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ت');
								temp = strBuilder.toString();
								if(!mutantList.containsKey(temp))
									mutantList.put(temp, word);
							}
							if(word.charAt(i) == 'ت') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ط');
								temp = strBuilder.toString();
								if(!mutantList.containsKey(temp))
									mutantList.put(temp, word);
							}


							// ض ظ ز ذ
							if(word.charAt(i) == 'ذ') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ز');
								temp = strBuilder.toString();
								if(!mutantList.containsKey(temp))
									mutantList.put(temp, word);

							}
							else if(word.charAt(i) == 'ز') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ذ');
								temp = strBuilder.toString();
								if(!mutantList.containsKey(temp))
									mutantList.put(temp, word);
							}

							// ظ ض
							if(word.charAt(i) == 'ظ') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ض');
								temp = strBuilder.toString();
								if(!mutantList.containsKey(temp))
									mutantList.put(temp, word);
							}
							else if(word.charAt(i) == 'ض') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ظ');
								temp = strBuilder.toString();
								if(!mutantList.containsKey(temp))
									mutantList.put(temp, word);
							}

							// ث ش
							if(word.charAt(i) == 'ش') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ث');
								temp = strBuilder.toString();
								if(!mutantList.containsKey(temp))
									mutantList.put(temp, word);
							}
							else if(word.charAt(i) == 'ث') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ش');
								temp = strBuilder.toString();
								if(!mutantList.containsKey(temp))
									mutantList.put(temp, word);
							}

							// ھ ح ہ
							if(word.charAt(i) == 'ح') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ہ');
								temp = strBuilder.toString();
								if(!mutantList.containsKey(temp))
									mutantList.put(temp, word);
							}

							else  if(word.charAt(i) == 'ہ') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ح');
								temp = strBuilder.toString();
								if(!mutantList.containsKey(temp))
									mutantList.put(temp, word);
							}

							// س ص
							if(word.charAt(i) == 'س') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ص');
								temp = strBuilder.toString();
								if(!mutantList.containsKey(temp))
									mutantList.put(temp, word);
							}
							else  if(word.charAt(i) == 'ص') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'س');
								temp = strBuilder.toString();
								if(!mutantList.containsKey(temp))
									mutantList.put(temp, word);
							}

							// گ غ
							if(word.charAt(i) == 'غ') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'گ');
								temp = strBuilder.toString();
								if(!mutantList.containsKey(temp))
									mutantList.put(temp, word);
							}
							else  if(word.charAt(i) == 'گ') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'غ');
								temp = strBuilder.toString();
								if(!mutantList.containsKey(temp))
									mutantList.put(temp, word);
							}

							// ک ق
							if(word.charAt(i) == 'ک') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ق');
								temp = strBuilder.toString();
								if(!mutantList.containsKey(temp))
									mutantList.put(temp, word);
							}
							else  if(word.charAt(i) == 'ق') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ک');
								temp = strBuilder.toString();
								if(!mutantList.containsKey(temp))
									mutantList.put(temp, word);
							}
						}
					}

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

	// Recursive function to get More mutants from a single mutant
	public HashMap<String,String> regenereateMutant(String word) {
		Words newWord = new Words();
		newWord.put(word, 5);
		HashMap<String,String> mutantList = new HashMap<String,String>();
		mutantList.putAll(generateMutants(newWord).getMutant());
		return mutantList;
	}


}
