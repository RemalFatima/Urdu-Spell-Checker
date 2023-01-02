package buisnessLayer;

import javax.swing.JTable;

import transferObject.wordTableData;

public interface IWordTableManager {

	void fillTable(JTable table);

	

	boolean update(int id, String word, int frequency);

}