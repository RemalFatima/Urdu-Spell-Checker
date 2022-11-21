package TestCases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import buisnessLayer.Corrector;

public class Testcorrectorclass {

	@Test
	public void correctWordsWithCorrectWords() {
		Corrector c= new Corrector();
		ArrayList<String> result = new  ArrayList<String>();
		result.add("عام");
		assertEquals(result,c.correctWords("عام"));
		
	}

	@Test
	public void correctWordsWithInccorectWords()
	{
		Corrector c= new Corrector();
		ArrayList<String> result = new  ArrayList<String>();
		assertEquals(c.correctWords("تترین"),result);

	}
	
	@Test
	public void correctWordsWithNoWords()
	{
		Corrector c= new Corrector();
		ArrayList<String> result = new  ArrayList<String>();
		
		assertEquals(c.correctWords(""),result);

	}
	
	@Test
	public void incorrectWordsWithNoWords()
	{
		Corrector c= new Corrector();
		ArrayList<String> result = new  ArrayList<String>();
		result.add("");
		assertEquals(c.Incorrectwords(""),result);

	}
	
	@Test
	public void IncorrectWordsWithCorrectWords() {
		Corrector c= new Corrector();
		ArrayList<String> result = new  ArrayList<String>();
		assertEquals(result,c.Incorrectwords("عام"));
		
	}

	@Test
	public void IncorrectWordsWithInccorectWords()
	{
		Corrector c= new Corrector();
		ArrayList<String> result = new  ArrayList<String>();
		result.add("تترین");
		assertEquals(c.Incorrectwords("تترین"),result);

	}
}