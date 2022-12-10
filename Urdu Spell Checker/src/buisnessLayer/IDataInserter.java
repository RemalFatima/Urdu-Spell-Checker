package buisnessLayer;

public interface IDataInserter {
	
	    // Author : Remal Fatima
		// Calls function to insert data into database
		public void insertBuiltInData(String path,boolean wordRefEnter) ;
		
		// Author : Remal Fatima 
		// Manually add new words by user
		public void insertManualWord(String sentence, String userName);
		
		// Author : Absar Ali
		// Create mutants of manually added words
		
		public void insertManualMutants(String word);

}
