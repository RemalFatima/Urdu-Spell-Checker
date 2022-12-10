package buisnessLayer;

import java.util.ArrayList;
import java.util.Arrays;
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

	Mutants mutants = new Mutants();
	HashMap<String,String> mutantList = new HashMap<String,String>();
	int b = 0;


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

	// replace characters to create new mutants
	public HashMap<String , String> replaceCharacters( int index, String mutant, String word, ArrayList<String> group) {
		HashMap<String , String> mutantList = new HashMap<String,String>();

		if(group == group3)
		{

			StringBuilder strBuilder = new StringBuilder(mutant);
			char c;
			c = strBuilder.charAt(index);
			strBuilder.deleteCharAt(index);
			strBuilder.insert(index, c+"ھ");
			//strBuilder.insert(index, c);
			mutant = strBuilder.toString();
			mutantList.put(mutant, word);

		}
		else if(group == group2)
		{
			StringBuilder strBuilder = new StringBuilder(mutant);
			strBuilder.deleteCharAt(index);
			mutant = strBuilder.toString();
			//	System.out.println(mutant + "Deleted ھ");
			mutantList.put(mutant, word);
		}

		else {
			for(String letters :  group) {
				StringBuilder strBuilder = new StringBuilder(mutant);
				strBuilder.deleteCharAt(index);
				strBuilder.insert(index,letters);
				mutant = strBuilder.toString();
				mutantList.put(mutant, word);
			}
		}

		//	System.out.println("New List of " + word);
		//	for(String key : mutantList.keySet())
		//	System.out.println(key + " " + mutantList.get(key));
		return mutantList;
	}

	
	// Calls change character at each index of string to create new mutants
	public void createMutant(String temp,String word)
	{

		try {
			for(int i = 0; i < temp.length(); i++) {

				{

					if(temp.charAt(i) == 'ا' || temp.charAt(i) == 'ع' || temp.charAt(i) == 'آ') {

						HashMap<String,String> newList = new HashMap<String,String>();
						newList = replaceCharacters(i,temp,word,group1);
						if(!containsAll(mutantList,newList)) {
							newList = unique(mutantList,newList);
							generate(newList,word);

						}
					}

					else if((temp.charAt(i) == 'ب' || temp.charAt(i) == 'پ'
							|| temp.charAt(i) == 'ٹ' || temp.charAt(i) == 'ج' || temp.charAt(i) == 'ج'
							|| temp.charAt(i) == 'د' || temp.charAt(i) == 'ڈ' || temp.charAt(i) == 'ر'
							|| temp.charAt(i) == 'ڑ' || temp.charAt(i) == 'م' || temp.charAt(i) == 'ل'
							)  && i+1 < temp.length() && temp.charAt(i+1) == 'ھ') {

						HashMap<String,String> newList = new HashMap<String,String>();
						newList = replaceCharacters(i+1,temp,word,group2);
						if(!containsAll(mutantList,newList)) {
							newList = unique(mutantList,newList);
							if(mutantList.size() > 1000)
								break;
							generate(newList,word);
							temp = word;
						}
					}

					else if((temp.charAt(i) == 'ب' || temp.charAt(i) == 'پ' 
							|| temp.charAt(i) == 'ٹ' || temp.charAt(i) == 'ج' || temp.charAt(i) == 'ج'
							|| temp.charAt(i) == 'د' || temp.charAt(i) == 'ڈ'  || temp.charAt(i) == 'م' || temp.charAt(i) == 'ل' ) ) {

						HashMap<String,String> newList = new HashMap<String,String>();
						newList = replaceCharacters(i,temp,word,group3);
						if(mutantList.size() > 1000)
							break;
						if(!containsAll(mutantList,newList)) {
							newList = unique(mutantList,newList);
							generate(newList,word);
							temp = word;
						}
					}


					else if(temp.charAt(i) == 'ت' || temp.charAt(i) == 'ط' ) {

						HashMap<String,String> newList = new HashMap<String,String>();
						newList = replaceCharacters(i,temp,word,group4);
						if(!containsAll(mutantList,newList)) {
							newList = unique(mutantList,newList);
							generate(newList,word);
						}
					}
					else if(temp.charAt(i) == 'ح' || temp.charAt(i) == 'ہ' || temp.charAt(i) == 'ۃ') {

						HashMap<String,String> newList = new HashMap<String,String>();
						newList = replaceCharacters(i,temp,word,group5);
						if(!containsAll(mutantList,newList)) {
							newList = unique(mutantList,newList);
							generate(newList,word);
						}
					}
					else if(temp.charAt(i) == 'ذ' || temp.charAt(i) == 'ز') {

						HashMap<String,String> newList = new HashMap<String,String>();
						newList = replaceCharacters(i,temp,word,group7);
						if(!containsAll(mutantList,newList)) {
							newList = unique(mutantList,newList);
							generate(newList,word);
						}
					}
					else if(temp.charAt(i) == 'س' || temp.charAt(i) == 'ص'|| temp.charAt(i) == 'ث') {

						HashMap<String,String> newList = new HashMap<String,String>();
						newList = replaceCharacters(i,temp,word,group8);
						if(!containsAll(mutantList,newList)) {
							newList = unique(mutantList,newList);
							generate(newList,word);
						}
					}
					else if(temp.charAt(i) == 'ض' || temp.charAt(i) == 'ظ') {

						HashMap<String,String> newList = new HashMap<String,String>();
						newList = replaceCharacters(i,temp,word,group9);
						if(!containsAll(mutantList,newList)) {
							newList = unique(mutantList,newList);
							generate(newList,word);
						}
					}
					else if(temp.charAt(i) == 'گ' || temp.charAt(i) == 'غ') {

						HashMap<String,String> newList = new HashMap<String,String>();
						newList = replaceCharacters(i,temp,word,group10);
						if(!containsAll(mutantList,newList)) {
							newList = unique(mutantList,newList);
							generate(newList,word);
						}
					}
					else if(temp.charAt(i) == 'ق' || temp.charAt(i) == 'ک') {

						HashMap<String,String> newList = new HashMap<String,String>();
						newList = replaceCharacters(i,temp,word,group11);
						if(!containsAll(mutantList,newList)) {
							newList = unique(mutantList,newList);
							generate(newList,word);
						}
					}


				}

			} }catch(Exception e) {

			}


	}

	// apply mutation to each word passed in paramters
	public void applyMutation(Words words) {
		for(String word : words.getWords().keySet()) { // Read each word
			mutantList.clear();
			String temp = word;




			try {

				createMutant(temp,word);
			} catch(Exception e) {}
			for(String key : mutantList.keySet()) {

				if(!mutants.containsMutant(key, word)) {
					mutants.put(key, word);

					//	b++;
					//	System.out.println(b);



				}






			}
			//System.out.println(b);
		}
	}

	// generate mutants of mutant
	private void generate(HashMap<String,String> newList, String word) {
		//System.out.println(!containsAll(mutantList,newList));
		mutantList.putAll(newList);
		for(String key : newList.keySet())
			createMutant(key,word);
	}

	
	// checks if one map is a subset of other
	private boolean containsAll(HashMap<String , String> map1, HashMap<String , String> map2) {

		if(map1.keySet().containsAll(map2.keySet()))
			return true;
		return false;
	}
	
	// check if one there is intersection between maps
	private HashMap<String,String> unique(HashMap<String,String> map1, HashMap<String,String> map2)
	{
		HashMap<String,String> newList = new HashMap<String,String>();
		for(String key : map2.keySet())
		{
			if(!map1.keySet().contains(key)) {
				newList.put(key, map2.get(key));

				//System.out.println(key);
			}
		}
		return newList;
	}

}
