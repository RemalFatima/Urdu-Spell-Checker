package TestCases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import buisnessLayer.IMutantGenerator;
import buisnessLayer.MutantGenerator;
import transferObject.Mutant;
import transferObject.Mutants;
import transferObject.Words;

public class MutantGeneratorTests {



	/*
	 * Author : Absar Ali 20F-0232
	 * Test cases for class Mutant Generator
	 */



	

	//When the word have mutants

	MutantGenerator mutantGenerator = new MutantGenerator();


	@Test
	public void generateMutantValidWord() {

		mutantGenerator = new MutantGenerator();
		Mutants mutant = new Mutants();
		Words word = new Words();
		word.put("نام",5);
		mutant = mutantGenerator.generateMutants(word);
		word.getWords().clear();
		Mutants result = new Mutants();
		word.put("اام", 5);
		result = mutantGenerator.generateMutants(word);


		for(Mutant word1 : mutant.getMutant()) {
			System.out.println(word1.getMutantString());
		}
		assertTrue(result.getMutant().containsAll(mutant.getMutant()));


	}

	// When word has no possible mutant
	@Test
	public void generateMutantInvalidWord() {


		Mutants mutant = new Mutants();
		Words word = new Words();
		word.put("",5);
		mutant = mutantGenerator.generateMutants(word);
		Mutants result = new Mutants();
		assertEquals(mutant.getMutant(),result.getMutant());

	}

	// When no word is entered in mutant Generator
	@Test
	public void generateMutantNoWord() {


		Mutants mutant = new Mutants();
		Words word = new Words();
		mutant = mutantGenerator.generateMutants(word);
		Mutants result = new Mutants();
		assertEquals(mutant.getMutant(),result.getMutant());

	}



	@Test
	@DisplayName (" Testing replaceCharacters function for groups which changes 1 character at a time ") 
	public void testReplaceCharactersForSingleCharacter() {

		String word = "عام";
		String mutant = word;
		String[] group1List = {"ا","ع","آ"};
		ArrayList<String> group1 = new ArrayList<String>();
		group1.addAll(Arrays.asList(group1List));
		HashMap<String , String> map1 = mutantGenerator.replaceCharacters(0, mutant, word, group1);
		HashMap<String , String> map2 = new HashMap<String , String>();
		map2.put("عام", word);
		map2.put("اام", word);
		map2.put("آام", word);
		assertEquals(map1, map2);
	}


	@Test
	@DisplayName (" Testing how many mutants should be generated") 
	public void testApplyMutation() {

		Words word = new Words();
		word.put("ا", 1);
		mutantGenerator.applyMutation(word);
		Mutants expected = new Mutants();

		assertTrue(mutantGenerator.getMutants().getMutant().size() == 2);

	}
	
	
	@Test
	@DisplayName (" Testing how many mutants should be generated") 
	public void testApplyMutationWithNull() {

		Words word = new Words();
		word.put("", 1);
		mutantGenerator.applyMutation(word);
		Mutants expected = new Mutants();

		assertTrue(mutantGenerator.getMutants().getMutant().size() == 0);

	}


}
