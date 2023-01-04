package buisnessLayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

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

	//static Log.logger Log.logger = Log.logger.getLog.logger(MutantGenerator.class);

	// Groups
	private String[] group1List = {"ا","ع","آ"};
	private String[] group2List = {"بھ","پھ","تھ","ٹھ","جھ","چھ","دھ","ڈھ","رھ","ڑھ","لھ","مھ","نھ","یھ"};
	private String[] group3List = {"ب","ھ","ٹ","ج","چ","د","ڈ","ر","ڑ","ل","م","ن","ی"};
	private String[] group4List = {"ت","ط"};
	private String[] group5List = {"ح","ہ","ۃ"};
	private String[] group6List = {"خ","کھ"};
	private String[] group7List = {"ز","ذ"};
	private String[] group8List = {"ث","ص","س"};
	private String[] group9List = {"ض","ظ"};
	private String[] group10List = {"گ","غ"};
	private String[] group11List = {"ک","ق"};

	private ArrayList<String> group1 = new ArrayList<String>();
	private ArrayList<String> group2 = new ArrayList<String>();
	private ArrayList<String> group3 = new ArrayList<String>();
	private ArrayList<String> group4 = new ArrayList<String>();
	private ArrayList<String> group5 = new ArrayList<String>();
	private ArrayList<String> group6 = new ArrayList<String>();
	private ArrayList<String> group7 = new ArrayList<String>();
	private ArrayList<String> group8 = new ArrayList<String>();
	private ArrayList<String> group9 = new ArrayList<String>();
	private ArrayList<String> group10 = new ArrayList<String>();
	private ArrayList<String> group11 = new ArrayList<String>();

	private Mutants mutants = new Mutants();
	private HashMap<String,String> mutantList = new HashMap<String,String>();

	CollectionOperations collectionOperation = new CollectionOperations();


	public MutantGenerator() {
		group1.addAll(Arrays.asList(group1List));
		group2.addAll(Arrays.asList(group2List));
		group3.addAll(Arrays.asList(group3List));
		group4.addAll(Arrays.asList(group4List));
		group4.addAll(Arrays.asList(group5List));
		group6.addAll(Arrays.asList(group6List));
		group7.addAll(Arrays.asList(group7List));
		group8.addAll(Arrays.asList(group8List));
		group9.addAll(Arrays.asList(group9List));
		group10.addAll(Arrays.asList(group10List));
		group11.addAll(Arrays.asList(group11List));
	}

	// Create Mutants 
	@Override
	public Mutants generateMutants(Words _wordsList) {

		Words words = _wordsList;

		try {
			applyMutation(_wordsList);
		} catch(Exception e) {};

		return mutants;



	}

	public Mutants getMutants() {
		return mutants;
	}

	public void setMutants(Mutants mutants) {
		this.mutants = mutants;
	}

	
	public HashMap<String , String> replaceCharacters( int index, String mutant, String word, ArrayList<String> group) {
		HashMap<String , String> mutantList = new HashMap<String,String>();

		if(group == group6)
		{

			StringBuilder strBuilder = new StringBuilder(mutant);
			if(index + 1 < mutant.length() && strBuilder.charAt(index+1) == 'ھ') {
				strBuilder.deleteCharAt(index);
				strBuilder.deleteCharAt(index);
				strBuilder.insert(index, 'خ');
			}
			else {
				strBuilder.deleteCharAt(index);
				strBuilder.insert(index, "کھ");
			}
			mutant = addMutantToMap(word, mutantList, strBuilder);

		}
		else if(group == group3)
		{

			StringBuilder strBuilder = new StringBuilder(mutant);
			char c;
			c = strBuilder.charAt(index);
			strBuilder.deleteCharAt(index);
			strBuilder.insert(index, c+"ھ");
			mutant = addMutantToMap(word, mutantList, strBuilder);

		}
		else if(group == group2)
		{
			StringBuilder strBuilder = new StringBuilder(mutant);
			strBuilder.deleteCharAt(index+1);
			mutant = addMutantToMap(word, mutantList, strBuilder);
		}

		else {
			for(String letters :  group) {
				StringBuilder strBuilder = new StringBuilder(mutant);
				strBuilder.deleteCharAt(index);
				strBuilder.insert(index,letters);
				mutant = addMutantToMap(word, mutantList, strBuilder);
			}
		}

		//	System.out.println("New List of " + word);
		//	for(String key : mutantList.keySet())
		//	System.out.println(key + " " + mutantList.get(key));
		return mutantList;
	}

	
	private String addMutantToMap(String word, HashMap<String, String> mutantList, StringBuilder strBuilder) {
		String mutant;
		mutant = strBuilder.toString();
		mutantList.put(mutant, word);
		return mutant;
	}


	
	public void createMutant(String temp,String word)
	{

		try {
			for(int i = 0; i < temp.length(); i++) {

				{

					if(isGroup1(temp, i)) {

						HashMap<String, String> newList = createNewList(temp, word, i, group1);
						updateList(word, newList);
					}

					else if(isGroup2(temp, i)) {

						HashMap<String, String> newList = createNewList(temp, word, i, group2);
						if(mutantList.size() > 1000)
							break;
						updateList(word,newList);
					}

					else if(isGroup3(temp, i) ) {

						HashMap<String, String> newList = createNewList(temp, word, i, group3);
						if(mutantList.size() > 1000)
							break;
						updateList(word,newList);
					}


					else if(isGroup4(temp, i) ) {

						HashMap<String, String> newList = createNewList(temp, word, i, group4);
						updateList(word, newList);
					}
					else if(isGroup5(temp, i)) {

						HashMap<String, String> newList = createNewList(temp, word, i, group5);
						updateList(word, newList);
					}
					else if(isGroup7(temp, i)) {

						HashMap<String, String> newList = createNewList(temp, word, i, group7);
						updateList(word, newList);
					}
					else if(isGroup8(temp, i)) {

						HashMap<String, String> newList = createNewList(temp, word, i, group8);
						updateList(word, newList);
					}
					else if(isGroup9(temp, i)) {

						HashMap<String, String> newList = createNewList(temp, word, i, group9);
						updateList(word, newList);
					}
					else if(isGroup10(temp, i)) {

						HashMap<String, String> newList = createNewList(temp, word, i, group10);
						updateList(word, newList);
					}
					else if(isGroup6(temp, i)) {

						HashMap<String, String> newList = createNewList(temp, word, i, group6);
						updateList(word, newList);
					}
					else if(isGroup11(temp, i)) {

						HashMap<String, String> newList = createNewList(temp, word, i, group11);
						updateList(word, newList);
					}

					Log.logger.debug("Mutant created " + temp  +" for word "+  word);


				}
			}

		}catch(Exception e) {

			Log.logger.info("Failed to create mutant in function createMutant() in BLL MutantGenerator");
			Log.logger.warn(e.getMessage());

		}


	}

	
	public void updateList(String word, HashMap<String, String> newList) {
		if(!collectionOperation.containsAll(mutantList,newList)) {
			newList = collectionOperation.uniqueElements(mutantList,newList);
			mutantList.putAll(newList);

			for(String key : newList.keySet())
				createMutant(key,word);
		}
	}

	
	public HashMap<String, String> createNewList(String temp, String word, int i, ArrayList<String> group) {
		HashMap<String,String> newList = new HashMap<String,String>();
		newList = replaceCharacters(i,temp,word,group);
		return newList;
	}

	
	private boolean isGroup1(String temp, int i) {
		return temp.charAt(i) == 'ا' || temp.charAt(i) == 'ع' || temp.charAt(i) == 'آ';
	}

	private boolean isGroup11(String temp, int i) {
		return temp.charAt(i) == 'ق' || temp.charAt(i) == 'ک';
	}

	private boolean isGroup6(String temp, int i) {
		return temp.charAt(i) == 'ق' || (temp.charAt(i) == 'ک'  
				&& i+1 < temp.length() && temp.charAt(i+1) == 'ھ');
	}

	private boolean isGroup10(String temp, int i) {
		return temp.charAt(i) == 'گ' || temp.charAt(i) == 'غ';
	}

	private boolean isGroup9(String temp, int i) {
		return temp.charAt(i) == 'ض' || temp.charAt(i) == 'ظ';
	}

	private boolean isGroup8(String temp, int i) {
		return temp.charAt(i) == 'س' || temp.charAt(i) == 'ص'|| temp.charAt(i) == 'ث';
	}

	private boolean isGroup7(String temp, int i) {
		return temp.charAt(i) == 'ذ' || temp.charAt(i) == 'ز';
	}

	private boolean isGroup5(String temp, int i) {
		return temp.charAt(i) == 'ح' || temp.charAt(i) == 'ہ' || temp.charAt(i) == 'ۃ';
	}

	private boolean isGroup4(String temp, int i) {
		return temp.charAt(i) == 'ت' || temp.charAt(i) == 'ط';
	}

	private boolean isGroup3(String temp, int i) {
		return temp.charAt(i) == 'ب' || temp.charAt(i) == 'پ' || temp.charAt(i) == 'ن'
				|| temp.charAt(i) == 'ٹ' || temp.charAt(i) == 'ج' || temp.charAt(i) == 'ج'
				|| temp.charAt(i) == 'د' || temp.charAt(i) == 'ڈ'  || temp.charAt(i) == 'م' || temp.charAt(i) == 'ل';
	}

	private boolean isGroup2(String temp, int i) {
		return (temp.charAt(i) == 'ب' || temp.charAt(i) == 'پ'|| temp.charAt(i) == 'ن'
				|| temp.charAt(i) == 'ٹ' || temp.charAt(i) == 'ج' || temp.charAt(i) == 'ج'
				|| temp.charAt(i) == 'د' || temp.charAt(i) == 'ڈ' || temp.charAt(i) == 'ر'
				|| temp.charAt(i) == 'ڑ' || temp.charAt(i) == 'م' || temp.charAt(i) == 'ل'
				)  && i+1 < temp.length() && temp.charAt(i+1) == 'ھ';
	}


	// apply mutation to each word passed in paramters
	public void applyMutation(Words words) {

		for(String word : words.getWords().keySet()) { // Read each word

			mutantList.clear();
			String temp = word;

			try {

				createMutant(temp,word);

			} catch(Exception e) {

				Log.logger.info("Failed to create mutant in function applyMutation() in BLL MutantGenerator");
				Log.logger.warn(e.getMessage());
			}
			mutantList.remove(word);
			for(String key : mutantList.keySet()) {

				if(!mutants.containsMutant(key, word)) 
					mutants.put(key, word);
			}
		}
	}



}
