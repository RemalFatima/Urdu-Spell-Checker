package TestCases;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import buisnessLayer.SuggestionWords;
import transferObject.Mutant;

class SuggestionWordsTests {

	SuggestionWords suggestions = new SuggestionWords();
	
	@Test
	void suggestWordsForWordWithMutants() {
		ArrayList<Mutant> list = new ArrayList<Mutant>();
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list = suggestions.suggestWords("ععم");
		
		Mutant resultMutant = new Mutant();
		resultMutant.setCorrectWord("عام");
		resultMutant.setMutantString("ععم");
		
		assertTrue(list.get(0).getMutantString().equals(resultMutant.getMutantString()));
		
	}
	@Test
	void suggestWordsForWordWithNull() {
		ArrayList<Mutant> list = new ArrayList<Mutant>();
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list = suggestions.suggestWords("");
		
		
		
		assertTrue(list.isEmpty());
		
	}
	@Test
	void suggestWordsForWordWithNoMutant() {
		ArrayList<Mutant> list = new ArrayList<Mutant>();
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list = suggestions.suggestWords("ے");

		assertTrue(list.isEmpty());
		
	}

	
}