package transferObject;

public class wordTableData {
	
	private int id;
	private String word;
	private int frequency;
	/**
	 * @param id
	 * @param word
	 * @param frequency
	 */
	public void put(int id , String word, int frequency) {
		this.id = id;
		this.word = word;
		this.frequency = frequency;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	

}
