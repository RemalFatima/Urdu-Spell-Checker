package TestCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dataAccessLayer.WordDAO;
import transferObject.Words;

class TestWordDAO {

	@Test
	void testGetWordsWithNormalSentence() {
		WordDAO wordDao = new WordDAO();
		Words expectedWords = new Words();
		expectedWords.put("this", 1);
		expectedWords.put("is", 1);
		Assertions.assertEquals(expectedWords.getWords(), wordDao.getWords("this is", "test").getWords());
	}

	
	@Test
	void testGetWordsWithNull() {
		WordDAO wordDao = new WordDAO();
		Words expectedWords = new Words();
		expectedWords.put("", 1);
		Assertions.assertEquals(expectedWords.getWords(), wordDao.getWords("", "test").getWords());
	}

}
