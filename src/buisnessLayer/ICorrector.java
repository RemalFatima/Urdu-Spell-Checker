package buisnessLayer;

import java.util.ArrayList;



/*
 * @Author : ManalSaqib 20F-0141
 */


public interface ICorrector {
	
	
	/*
	 * stores incorrect words into an array list and return that list 
	 */
	public ArrayList<String> Incorrectwords(String sentence);
	
	/*
	 * stores correct words into an array list and return that list 
	 */
	public ArrayList<String> correctWords(String sentence);

}
