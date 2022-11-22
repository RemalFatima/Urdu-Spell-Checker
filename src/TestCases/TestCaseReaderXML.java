package TestCases;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import buisnessLayer.ReaderXML;

public class TestCaseReaderXML {

	@Test
	public void correctFile(){
		ReaderXML r = new ReaderXML();
		
		File file = new File("C:\\Users\\Z\\Downloads\\0001.xml");
		Assertions.assertTrue(r.readFile(file));
	}
	
	@Test
	public void emptyFile(){
		ReaderXML r = new ReaderXML();
		
		File file = new File("C:\\Users\\Z\\OneDrive\\Desktop");
		Assertions.assertFalse(r.readFile(file));
	}
	
	@Test
	public void missingAutor(){
		ReaderXML r = new ReaderXML();
		
		File file = new File("C:\\Users\\Z\\Downloads\\0047.xml");
		Assertions.assertTrue(r.readFile(file));
	}

}
