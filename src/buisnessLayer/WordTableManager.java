package buisnessLayer;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import transferObject.wordTableData;

/*
 *  Author Remal Fatima
 *  
 */

public class WordTableManager implements IWordTableManager {

	
	@Override
	public void fillTable(JTable table ) {
		IFascade bllFascade = new Fascade();
		for(wordTableData wordList : bllFascade.getWordsList()) {

			Object data[] = {wordList.getId(), wordList.getWord(), wordList.getFrequency()};
			DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
			tblModel.addRow(data);

		}
		
		
	}
	
	
	@Override
	public boolean update(int id, String word, int frequency) {
		try {
		IFascade dalFascade = new Fascade();
		wordTableData data = new wordTableData();
		data.put(id, word, frequency);
		dalFascade.updateWord(data);
		} catch(Exception ex) {
			
			return false;
		}
		return true;
	}


	
	

}
