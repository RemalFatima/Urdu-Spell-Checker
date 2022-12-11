package TestCases;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import buisnessLayer.CollectionOperations;

public class CollectionOperatonsTests {
	
	CollectionOperations operations = new CollectionOperations();

	@Test
	public void containsAllEqualMaps() {
		
		HashMap<String , String> map1 = new HashMap<String,String>();
		map1.put("A", "A");
		HashMap<String , String> map2 = new HashMap<String,String>();
		map2.put("A", "A");
		
		assertTrue(operations.containsAll(map1, map2));
	}
	@Test
	public void containsAllUnequalMaps() {
		
		HashMap<String , String> map1 = new HashMap<String,String>();
		map1.put("A", "A");
		HashMap<String , String> map2 = new HashMap<String,String>();
		map2.put("B", "B");
		
		assertFalse(operations.containsAll(map1, map2));
	}
	
	@Test
	public void UniqueElementsWithEqualMaps() {
		
		HashMap<String , String> map1 = new HashMap<String,String>();
		map1.put("A", "A");
		HashMap<String , String> map2 = new HashMap<String,String>();
		map2.put("A", "A");
		HashMap<String , String> result = new HashMap<String,String>();
		
		
		assertEquals(operations.uniqueElements(map1, map2),result);
	}
	@Test
	public void UniqueElementsWithUnequalMaps() {
		
		HashMap<String , String> map1 = new HashMap<String,String>();
		map1.put("A", "A");
		HashMap<String , String> map2 = new HashMap<String,String>();
		map2.put("B", "B");
		HashMap<String , String> result = new HashMap<String,String>();
		result.put("B", "B");
		
		assertEquals(operations.uniqueElements(map1, map2),result);
	}


}
