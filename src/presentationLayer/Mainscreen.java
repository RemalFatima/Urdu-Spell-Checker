
package presentationLayer;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.Utilities;

import org.apache.log4j.Logger;

import buisnessLayer.*;
import dataAccessLayer.*;
import transferObject.Mutant;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

/*
 * @Author: ManalSaqib 20F-0141 
 * Main screen interface to highlight the incorrect words
 * 
 * @Author AbsarAli
 * Changed interface colors
 * 
 * @Author: ManalSaqib 20F-0141 
 * Add click able event on mouse click to show suggestions of correct words
 * changed interface 
 * 
 *  * @Author: ManalSaqib 20F-0141 
 * Modify on mouse click function to call suggestion class 

 * 
 */


public class Mainscreen extends JFrame {

	static Logger logger = Logger.getLogger(Mainscreen.class);

	private JPanel contentPane;
	private JTextPane textArea = new JTextPane();

	private JTextField xmlDataPathTextField;
	private JTextField userNameTextField;
	private JTextField WordTextField;
	private JTextPane suggestionTextArea;
	public JProgressBar progressBar;
	SuggestionWords suggestions = new SuggestionWords();
	private JTable table;
	private JTextField wordField;
	private JTextField editField;
	private JTextField idField;
	private JTextField freqField;
	int row,column;


	// Highlight incorrect words from JTextPane by deleting old text and overwriting with new text

	public void highlight() {
		//textArea.setText(  "  یہ خُون میں آکسیجَن کو مِلاتا ہے جِس   "); 
		String oldSentence = textArea.getText();
		oldSentence = oldSentence.replaceAll("(?U)[\\W_]+", " ");

		Corrector corrector = new Corrector();
		ArrayList<String> correctWords = new ArrayList<String>();
		correctWords = corrector.correctWords(oldSentence);
		ArrayList<String> incorrectWords = new ArrayList<String>();
		incorrectWords =  corrector.Incorrectwords(oldSentence);
		textArea.setText("");
		for(String word : oldSentence.split(" ")) {

			if(incorrectWords.contains(word)) {
				appendToPane(textArea, word, Color.red);
			}
			else {
				appendToPane(textArea,word,Color.white);
			}
			appendToPane(textArea," ", Color.white);

		}
		appendToPane(textArea," ", Color.white);

	}
	/*
	 * append new text 
	 */
	private void appendToPane(JTextPane tp, String msg, Color c)
	{
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

		aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Tahoma");
		aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

		int len = tp.getDocument().getLength();
		tp.setCaretPosition(len);
		tp.setCharacterAttributes(aset, false);
		tp.replaceSelection(msg);

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainscreen frame = new Mainscreen();
					frame.setVisible(true);
					frame.highlight();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Mainscreen() {
		setTitle("Urdu Spell Checker");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1211, 719);
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBackground(new Color(56, 74, 107));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(281, 0, 914, 680);
		contentPane.add(tabbedPane);

		JPanel spellCheckPanel = new JPanel();
		spellCheckPanel.setBackground(new Color(60, 81, 115));
		tabbedPane.addTab("Spell Checker", null, spellCheckPanel, null);
		spellCheckPanel.setLayout(null);

		JButton checkBtn = new JButton("چیک کریں");
		checkBtn.setBounds(10, 129, 136, 42);
		spellCheckPanel.add(checkBtn);
		checkBtn.setForeground(Color.DARK_GRAY);
		checkBtn.setBackground(new Color(192, 192, 192));
		checkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				highlight();
				JOptionPane.showMessageDialog(new JFrame(), "The wrong words have been highlighted"," Check Please", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		checkBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textArea.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				checkLastWord();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				checkLastWord();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				checkLastWord();
			}

			protected void checkLastWord() {
				try {
					if(textArea.getText() != null || textArea.getText() != "") {
						int start = Utilities.getWordStart(textArea,textArea.getCaretPosition());
						int end = Utilities.getWordEnd(textArea, textArea.getCaretPosition());
						String text = textArea.getDocument().getText(start, end - start);
						//  System.out.println(text);
						ArrayList<String> wordList = suggestions.autoCorrection(text);
						if(wordList.size() >= 1)
						{
							suggestionTextArea.setText("");
							for(String word : wordList) {
								appendToPane(suggestionTextArea,word+"\n", Color.white);
								//suggestionTextArea.setText(word + "\n");

							}
						}

					}
				} catch (Exception e) {

				}

			}
		});


		/*
		 * @author: Manal saqib 
		 * : modify on click mouse function to show suggestions and replace it with menu 
		 */
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try
				{
					String wrd=null;
					int pt=textArea.viewToModel(e.getPoint());
					int spt=Utilities.getWordStart(textArea,pt);
					int ept=Utilities.getWordEnd(textArea,pt);
					textArea.setSelectionStart(spt);
					textArea.setSelectionEnd(ept);
					wrd=textArea.getSelectedText();
					suggestionTextArea.setText("");
					JPopupMenu popupmenu = new JPopupMenu("Words");  
					if(suggestions.suggestWords(wrd).size() >= 1) {
						for(Mutant mutant : suggestions.suggestWords(wrd)) {



							JMenuItem suggestedWord = new JMenuItem(mutant.getCorrectWord());
							suggestedWord.addActionListener(new ActionListener(){  
								public void actionPerformed(ActionEvent e) {    
									replaceWord(mutant, suggestedWord);
								}

								public void replaceWord(Mutant mutant, JMenuItem suggestedWord) {
									String newText = textArea.getText();
									newText = newText.replace(mutant.getMutantString(), suggestedWord.getText());
									textArea.setText("");
									appendToPane(textArea, newText ,Color.white);
								}  
							});  
							popupmenu.add(suggestedWord);

						}
						popupmenu.show(textArea,e.getX(),e.getY());
					}
				}
				catch(Exception e1)
				{
					logger.info("Error in showing mutants in menus");
					logger.info(e1.getCause());
					logger.warn(e1.getMessage());
				}
			}
		});
		textArea.setBounds(20, 182, 679, 459);
		spellCheckPanel.add(textArea);
		textArea.setBackground(new Color(0, 0, 72));
		textArea.setForeground(Color.WHITE);
		textArea.setCaretColor(new Color(255, 255, 255));

		JLabel lblNewLabel = new JLabel("اپنا متن یہاں درج کریں: ");
		lblNewLabel.setBounds(479, 138, 220, 25);
		spellCheckPanel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(Color.CYAN);

		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));

		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));


		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(296, 182, 287, 250);
		//spellCheckPanel.add(textArea_1);
		JScrollPane sp = new JScrollPane (textArea_1,JScrollPane .VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spellCheckPanel.add(sp);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(734, 182, 165, 459);
		spellCheckPanel.add(scrollPane_1);
		suggestionTextArea = new JTextPane();
		scrollPane_1.setViewportView(suggestionTextArea);
		suggestionTextArea.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 20));
		suggestionTextArea.setForeground(new Color(255, 255, 255));
		suggestionTextArea.setBackground(new Color(0, 0, 72));

		JLabel lblNewLabel_3 = new JLabel("کیا آپ نے یہ لکھنا چاہا؟");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBackground(Color.CYAN);
		lblNewLabel_3.setBounds(736, 138, 168, 25);
		spellCheckPanel.add(lblNewLabel_3);



		suggestionTextArea = new JTextPane();
		suggestionTextArea.setBounds(734, 182, 165, 459);
		spellCheckPanel.add(suggestionTextArea);
		suggestionTextArea.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 20));
		suggestionTextArea.setForeground(new Color(255, 255, 255));
		suggestionTextArea.setBackground(new Color(0, 0, 72));




		JPanel importPanel = new JPanel();
		importPanel.setBackground(new Color(34, 46, 66));
		tabbedPane.addTab("Import Data", null, importPanel, null);
		importPanel.setLayout(null);

		xmlDataPathTextField = new JTextField();
		xmlDataPathTextField.setText("C:\\Users\\Hp\\Downloads\\makhzan-master\\makhzan-master\\text");
		xmlDataPathTextField.setBackground(Color.LIGHT_GRAY);
		xmlDataPathTextField.setBounds(195, 222, 573, 28);
		importPanel.add(xmlDataPathTextField);
		xmlDataPathTextField.setColumns(10);

		JLabel xmlPathLbl = new JLabel("ذیل میں XML فائلوں کا راستہ درج کریں:");
		xmlPathLbl.setForeground(Color.WHITE);
		xmlPathLbl.setFont(new Font("Tahoma", Font.BOLD, 17));
		xmlPathLbl.setBounds(444, 172, 324, 38);
		importPanel.add(xmlPathLbl);

		JCheckBox wordRefChkBtn = new JCheckBox("insert word references");
		wordRefChkBtn.setBounds(195, 183, 140, 23);
		importPanel.add(wordRefChkBtn);

		JButton btnNewButton = new JButton("داخل کریں۔");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				new Thread(new Runnable() {
					@Override
					public void run() {
						DataInserter dataInserter = new DataInserter();
						dataInserter.insertBuiltInData(xmlDataPathTextField.getText(), wordRefChkBtn.isSelected() );
						JOptionPane.showMessageDialog(new JFrame(), "The data has successfully imported","Updated", JOptionPane.INFORMATION_MESSAGE);
					}
				}).start();



			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnNewButton.setBounds(408, 275, 124, 38);
		importPanel.add(btnNewButton);

		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setBounds(195, 506, 573, 28);
		importPanel.add(progressBar);



		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(34, 46, 66));
		tabbedPane.addTab("Add Words", null, panel_2, null);
		panel_2.setLayout(null);

		userNameTextField = new JTextField();
		userNameTextField.setBounds(378, 144, 176, 34);
		panel_2.add(userNameTextField);
		userNameTextField.setColumns(10);

		WordTextField = new JTextField();
		WordTextField.setColumns(10);
		WordTextField.setBounds(378, 255, 176, 34);
		panel_2.add(WordTextField);

		JButton addWordBtn = new JButton("لفظ شامل کریں۔");
		addWordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(WordTextField.getText() != "" && userNameTextField.getText() != "")
				{
					DataInserter dataInserter = new DataInserter();
					dataInserter.insertManualWord(userNameTextField.getText(), WordTextField.getText());
					dataInserter.insertManualMutants(WordTextField.getText());
					JOptionPane.showMessageDialog(new JFrame(), "The word has successfully added","Updated", JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});
		addWordBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		addWordBtn.setBounds(388, 373, 158, 40);
		panel_2.add(addWordBtn);

		JLabel userNameLbl = new JLabel("اپنا نام درج کریں");
		userNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		userNameLbl.setForeground(Color.WHITE);
		userNameLbl.setBounds(446, 99, 108, 34);
		panel_2.add(userNameLbl);

		JLabel addWordLbl = new JLabel("اپنا لفظ درج کریں۔");
		addWordLbl.setForeground(Color.WHITE);
		addWordLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addWordLbl.setBounds(446, 210, 108, 34);
		panel_2.add(addWordLbl);

		JPanel wordsList = new JPanel();
		wordsList.setBackground(new Color(34, 46, 66));
		tabbedPane.addTab("New tab", null, wordsList, null);
		wordsList.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 649, 630);
		wordsList.add(scrollPane);

		table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				column = table.getSelectedColumn();
				if(column == 1) {
					idField.setText(table.getValueAt(row, 0).toString());
					wordField.setText( table.getValueAt(row, column).toString());
					freqField.setText(table.getValueAt(row, 2).toString());
				}

			}
		});

		table.getModel().addTableModelListener(new TableModelListener() {

			public void tableChanged(TableModelEvent e) {

			}
		});


		table.setColumnSelectionAllowed(true);


		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null},
				},
				new String[] {
						"Word ID", "Word", "Frequenncy"
				}
				) {
			Class[] columnTypes = new Class[] {
					Integer.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		TableRowSorter sorter = new TableRowSorter(table.getModel());
		table.setRowSorter(sorter);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);

		wordField = new JTextField();
		wordField.setEditable(false);
		wordField.setBounds(694, 181, 205, 52);
		wordsList.add(wordField);
		wordField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("لفظ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(848, 132, 51, 38);
		wordsList.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("لفظ میں ترمیم کریں۔");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel_2_1.setBounds(669, 412, 230, 38);
		wordsList.add(lblNewLabel_2_1);

		editField = new JTextField();
		editField.setColumns(10);
		editField.setBounds(694, 461, 205, 52);
		wordsList.add(editField);

		JButton updateBtn = new JButton("اپ ڈیٹ");

		updateBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isUpdateEligible()) {

					IWordTableManager tableManager = new WordTableManager();
					tableManager.update(Integer.parseInt(idField.getText()), editField.getText(), Integer.parseInt(freqField.getText()));
					table.setValueAt(editField.getText(), row, column);
					JOptionPane.showMessageDialog(new JFrame(), "Your request has been submited","Request Change", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Select a word to update","Updated", JOptionPane.WARNING_MESSAGE);
				}

			}

			public boolean isUpdateEligible() {
				return editField.getText() != "" && idField.getText() != "" && wordField.getText() != "" && freqField.getText() != "";
			}
		});


		updateBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		updateBtn.setBounds(724, 582, 129, 38);
		wordsList.add(updateBtn);

		JLabel lblNewLabel_2_2 = new JLabel("ID");
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel_2_2.setBounds(861, 11, 38, 38);
		wordsList.add(lblNewLabel_2_2);

		idField = new JTextField();
		idField.setEditable(false);
		idField.setColumns(10);
		idField.setBounds(694, 60, 205, 52);
		wordsList.add(idField);

		JLabel lblNewLabel_2_3 = new JLabel("تعدد");
		lblNewLabel_2_3.setForeground(Color.WHITE);
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel_2_3.setBounds(848, 264, 51, 38);
		wordsList.add(lblNewLabel_2_3);

		freqField = new JTextField();
		freqField.setEditable(false);
		freqField.setColumns(10);
		freqField.setBounds(694, 313, 205, 52);
		wordsList.add(freqField);




		JPanel sidePanel = new JPanel();
		sidePanel.setBackground(new Color(96, 210, 196));
		sidePanel.setBounds(0, 0, 280, 680);
		contentPane.add(sidePanel);
		sidePanel.setLayout(null);

		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(Mainscreen.class.getResource("/images/cooltext423951303068138 (1).png")));
		logo.setBounds(0, 11, 260, 67);
		sidePanel.add(logo);

		JLabel lblNewLabel_1 = new JLabel("____________________________");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 89, 260, 24);
		sidePanel.add(lblNewLabel_1);

		JButton spellCheckerBtn = new JButton("اردو املا کی اصلاح");
		spellCheckerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}


		});
		spellCheckerBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		spellCheckerBtn.setForeground(Color.BLACK);
		spellCheckerBtn.setBackground(Color.LIGHT_GRAY);
		spellCheckerBtn.setBounds(13, 134, 247, 55);
		sidePanel.add(spellCheckerBtn);

		JButton importBtn = new JButton("بلٹ ورڈز درآمد کریں");
		importBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		importBtn.setForeground(Color.BLACK);
		importBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		importBtn.setBackground(Color.LIGHT_GRAY);
		importBtn.setBounds(13, 245, 247, 55);
		sidePanel.add(importBtn);

		JLabel lblNewLabel_1_1 = new JLabel("____________________________");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBackground(Color.WHITE);
		lblNewLabel_1_1.setBounds(10, 200, 260, 24);
		sidePanel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("____________________________");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBackground(Color.WHITE);
		lblNewLabel_1_1_1.setBounds(10, 311, 260, 24);
		sidePanel.add(lblNewLabel_1_1_1);

		JButton spellCheckerBtn_1_1 = new JButton("نیا لفظ درج کریں");
		spellCheckerBtn_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		spellCheckerBtn_1_1.setForeground(Color.BLACK);
		spellCheckerBtn_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		spellCheckerBtn_1_1.setBackground(Color.LIGHT_GRAY);
		spellCheckerBtn_1_1.setBounds(13, 356, 247, 55);
		sidePanel.add(spellCheckerBtn_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("____________________________");
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBackground(Color.WHITE);
		lblNewLabel_1_1_1_1.setBounds(13, 440, 260, 24);
		sidePanel.add(lblNewLabel_1_1_1_1);

		JButton wordListBtn = new JButton("الفاظ کی تفصیل");
		wordListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);




			}
		});


		loadWordTable();
		
		wordListBtn.setForeground(Color.BLACK);
		wordListBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		wordListBtn.setBackground(Color.LIGHT_GRAY);
		wordListBtn.setBounds(16, 485, 247, 55);
		sidePanel.add(wordListBtn);
	}
	public void loadWordTable() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				IWordTableManager tableManager = new WordTableManager();
				tableManager.fillTable(table);
			}
		}).start();
	}
}
