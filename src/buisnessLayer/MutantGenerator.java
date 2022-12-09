package buisnessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

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
		HashMap<String,String> mutantList = new HashMap<String,String>();
		int b = 0;

		for(String word : words.getWords().keySet()) { // Read each word
			String temp = word;
			b++;
			for(int a = 0; a < temp.length(); a++) {
				temp = word;

				for(int i = a ; i < temp.length(); i++)
				{

					for(int j = i; j < temp.length(); j++) {

						{
							// ا ع
							if(temp.charAt(i) == 'ا') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ع');
								temp = strBuilder.toString();
								 
									mutantList.put(temp, word);

							}
							else if(temp.charAt(i) == 'ع') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ا');
								temp = strBuilder.toString();
								 
									mutantList.put(temp, word);	
							}


							// ب بھ
							 if(i+1 < temp.length()) {
								if(temp.charAt(i) == 'ب' && temp.charAt(i+1) == 'ھ') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.deleteCharAt(i+1);
									temp = strBuilder.toString();
									 
										mutantList.put(temp, word);		
								}

								else if(temp.charAt(i) == 'ب') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.insert(i+1, 'ھ');
									strBuilder.setCharAt(i, 'ب');
									;
									temp = strBuilder.toString();
									 
										mutantList.put(temp, word);	
								}
							}
							// ب بھ
							 if(i+1 < temp.length()) {
								if(temp.charAt(i) == 'پ' && temp.charAt(i+1) == 'ھ') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.deleteCharAt(i+1);
									temp = strBuilder.toString();
									 
										mutantList.put(temp, word);		
								}

								else if(temp.charAt(i) == 'پ') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.insert(i+1, 'ھ');
									strBuilder.setCharAt(i, 'پ');

									temp = strBuilder.toString();
									 
										mutantList.put(temp, word);	
								}
							}

							// ت ط
							 if(temp.charAt(i) == 'ط') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ت');
								temp = strBuilder.toString();
								 
									mutantList.put(temp, word);
							}
							else if(temp.charAt(i) == 'ت') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ط');
								temp = strBuilder.toString();
								 
									mutantList.put(temp, word);
							}

							//ٹ ٹھ
							 if(i+1 < temp.length()) {
								if(temp.charAt(i) == 'ٹ' && temp.charAt(i+1) == 'ھ') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.deleteCharAt(i+1);
									temp = strBuilder.toString();
									 
										mutantList.put(temp, word);		
								}

								else if(temp.charAt(i) == 'ٹ') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.insert(i+1, 'ھ');
									strBuilder.setCharAt(i, 'ٹ');

									temp = strBuilder.toString();
									 
										mutantList.put(temp, word);	
								}
							}

							// ج جھ
							 if(i+1 < temp.length()) {
								if(temp.charAt(i) == 'ج' && temp.charAt(i+1) == 'ھ') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.deleteCharAt(i+1);
									temp = strBuilder.toString();
									 
										mutantList.put(temp, word);		
								}

								else if(temp.charAt(i) == 'ج') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.insert(i+1, 'ھ');
									strBuilder.setCharAt(i, 'ج');

									temp = strBuilder.toString();
									 
										mutantList.put(temp, word);	
								}
							}

							// چ چھ
							 if(i+1 < temp.length()) {
								if(temp.charAt(i) == 'چ' && temp.charAt(i+1) == 'ھ') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.deleteCharAt(i+1);
									temp = strBuilder.toString();
									 
										mutantList.put(temp, word);		
								}

								else if(temp.charAt(i) == 'چ') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.insert(i+1, 'ھ');
									strBuilder.setCharAt(i, 'چ');

									temp = strBuilder.toString();
									 
										mutantList.put(temp, word);	
								}
							}

							// د دھ
							 if(i+1 < temp.length()) {
								if(temp.charAt(i) == 'د' && temp.charAt(i+1) == 'ھ') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.deleteCharAt(i+1);
									temp = strBuilder.toString();
									 
										mutantList.put(temp, word);		
								}

								else if(temp.charAt(i) == 'د') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.insert(i+1, 'ھ');
									strBuilder.setCharAt(i, 'د');

									temp = strBuilder.toString();
									 
										mutantList.put(temp, word);	
								}
							}

							// ض ظ ز ذ
							 if(temp.charAt(i) == 'ذ') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ز');
								temp = strBuilder.toString();
								 
									mutantList.put(temp, word);

							}
							else if(temp.charAt(i) == 'ز') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ذ');
								temp = strBuilder.toString();
								 
									mutantList.put(temp, word);
							}

							// ظ ض
							if(temp.charAt(i) == 'ظ') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ض');
								temp = strBuilder.toString();
								 
									mutantList.put(temp, word);
							}
							else if (temp.charAt(i) == 'ض') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ظ');
								temp = strBuilder.toString();
								 
									mutantList.put(temp, word);
							}

							// ث ش
							 if(temp.charAt(i) == 'ش') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ث');
								temp = strBuilder.toString();
								 
									mutantList.put(temp, word);
							}
							 else if (temp.charAt(i) == 'ث') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ش');
								temp = strBuilder.toString();
								 
									mutantList.put(temp, word);
							}

							// ھ ح ہ
							 if(temp.charAt(i) == 'ح') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ہ');
								temp = strBuilder.toString();
								 
									mutantList.put(temp, word);
							}

							 else if (temp.charAt(i) == 'ہ') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ح');
								temp = strBuilder.toString();
								 
									mutantList.put(temp, word);
							}

							// س ص
							 if (temp.charAt(i) == 'س') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ص');
								temp = strBuilder.toString();
								 
									mutantList.put(temp, word);
							}
							 else if(temp.charAt(i) == 'ص') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'س');
								temp = strBuilder.toString();
								 
									mutantList.put(temp, word);
							}

							// کھ خ
							if(i+1 < temp.length()) {
								if(temp.charAt(i) == 'ک' && temp.charAt(i+1) == 'ھ') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.deleteCharAt(i+1);
									strBuilder.setCharAt(i, 'خ');
									temp = strBuilder.toString();
									
										mutantList.put(temp, word);		
								}

								else if(temp.charAt(i) == 'خ') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.insert(i+1, 'ھ');
									strBuilder.setCharAt(i, 'ک');

									temp = strBuilder.toString();
									 
										mutantList.put(temp, word);	
								}
							}

							// گ غ
							 if(temp.charAt(i) == 'غ') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'گ');
								temp = strBuilder.toString();
								 
									mutantList.put(temp, word);
							}
							else  if(temp.charAt(i) == 'گ') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'غ');
								temp = strBuilder.toString();
								 
									mutantList.put(temp, word);
							}

							// ک ق
							 if(temp.charAt(i) == 'ک') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ق');
								temp = strBuilder.toString();
								 
									mutantList.put(temp, word);
							}
							else  if(temp.charAt(i) == 'ق') {
								StringBuilder strBuilder = new StringBuilder(temp);
								strBuilder.setCharAt(i, 'ک');
								temp = strBuilder.toString();
								 
									mutantList.put(temp, word);
							}

							// ب
							 if(i+1 < temp.length()) {
								if(temp.charAt(i) == 'ل' && temp.charAt(i+1) == 'ھ') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.deleteCharAt(i+1);
									temp = strBuilder.toString();
									 
										mutantList.put(temp, word);		
								}

								else if(temp.charAt(i) == 'ل') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.insert(i+1, 'ھ');
									strBuilder.setCharAt(i, 'ل');
									temp = strBuilder.toString();

									 
										mutantList.put(temp, word);	
								}
							}
							// ب بھ
							 if(i+1 < temp.length()) {
								if(temp.charAt(i) == 'م' && temp.charAt(i+1) == 'ھ') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.deleteCharAt(i+1);
									temp = strBuilder.toString();
									 
										mutantList.put(temp, word);		
								}

								else if(temp.charAt(i) == 'م') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.insert(i+1, 'ھ');
									strBuilder.setCharAt(i, 'م');
									
									temp = strBuilder.toString();
									 
										mutantList.put(temp, word);	
								}
							}

							// ب بھ
							 if(i+1 < temp.length()) {
								if(temp.charAt(i) == 'ی' && temp.charAt(i+1) == 'ھ') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.deleteCharAt(i+1);
									temp = strBuilder.toString();
									 
										mutantList.put(temp, word);		
								}

								else if(temp.charAt(i) == 'ی') {
									StringBuilder strBuilder = new StringBuilder(temp);
									strBuilder.insert(i+1, 'ھ');
									strBuilder.setCharAt(i, 'ی');

									temp = strBuilder.toString();
									 
										mutantList.put(temp, word);	
								}
							}
						}
					}

					mutantList.remove(word);
				
				}
				System.out.println(b);
			}
		}
		System.out.println("test");
		Count.num += 100000;
		mutants.setMutant(mutantList); // store all mutants in hashmap
		//		for(String key : mutants.getMutant().keySet())
		//		{
		//			System.out.println(key);
		//		}
		
		return mutants;

	}

	


}
