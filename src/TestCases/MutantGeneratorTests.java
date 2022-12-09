package TestCases;

import static org.junit.Assert.*;

import org.junit.Test;

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
	
	
	
	/*
	 * 
	 * Function : generateMutants(Words _wordList)
	 * 
	 */

	//When the word have mutants
	@Test
	public void generateMutantValidWord() {
		
		IMutantGenerator mutantGenerator = new MutantGenerator();
		Mutants mutant = new Mutants();
		Words word = new Words();
		word.put("انسان",5);
		mutant = mutantGenerator.generateMutants(word);
		Mutants result = new Mutants();
		result.put("اام","عام");
		result.put("ععم", "عام");
		result.put("اعم", "عام");
		for(Mutant word1 : mutant.getMutant()) {
			System.out.println(word1.getMutantString());
		}
		assertEquals(mutant.getMutant(),result.getMutant());
		
		
	}
	
	// When word has no possible mutant
	@Test
	public void generateMutantInvalidWord() {
		
		IMutantGenerator mutantGenerator = new MutantGenerator();
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
			
			IMutantGenerator mutantGenerator = new MutantGenerator();
			Mutants mutant = new Mutants();
			Words word = new Words();
			mutant = mutantGenerator.generateMutants(word);
			Mutants result = new Mutants();
			assertEquals(mutant.getMutant(),result.getMutant());
			
		}
	
	

}
