package buisnessLayer;

import javax.swing.JTable;

import transferObject.wordTableData;

public interface IWordTableManager {

	void fillTable(JTable table);

	void sort(JTable table);

	wordTableData update(int id, String word, int frequency);

}