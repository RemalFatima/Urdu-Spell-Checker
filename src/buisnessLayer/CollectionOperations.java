package buisnessLayer;

import java.util.HashMap;

/*
 * Author : Absar Ali 20F-0232
 * HashMap methods
 */


public class CollectionOperations {
	
	
	public boolean containsAll(HashMap<String , String> map1, HashMap<String , String> map2) {

		if(map1.keySet().containsAll(map2.keySet()))
			return true;
		return false;
	}
	
	
	public HashMap<String,String> uniqueElements(HashMap<String,String> map1, HashMap<String,String> map2)
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
