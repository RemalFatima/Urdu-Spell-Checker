package buisnessLayer;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Fascade.Fascade;
import Fascade.IFascade;
import transferObject.wordTableData;

/*
 *  Author Remal Fatima
 *  
 */

public class WordTableManager implements IWordTableManager {

	// fills data in table from database
	@Override
	public void fillTable(JTable table ) {
		IFascade bllFascade = new Fascade();
		for(wordTableData wordList : bllFascade.getWordsList()) {

			Object data[] = {wordList.getId(), wordList.getWord(), wordList.getFrequency()};
			DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
			tblModel.addRow(data);

		}
		
		
	}
	
	// updates data in database
	@Override
	public wordTableData update(int id, String word, int frequency) {
		IFascade dalFascade = new Fascade();
		wordTableData data = new wordTableData();
		data.put(id, word, frequency);
		dalFascade.updateWord(data);
		return data;
	}


	@Override
	public void sort(JTable table) {
		// TODO Auto-generated method stub
		
	}
	

}
