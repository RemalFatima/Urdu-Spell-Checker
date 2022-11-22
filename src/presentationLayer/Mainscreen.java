
package presentationLayer;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import buisnessLayer.*;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

/*
 * @Author: ManalSaqib 20F-0141 
 * Main screen interface to highlight the incorrect words
 * 
 * @Author Absar Ali
 * Changed interface colors
 */

public class Mainscreen extends JFrame {

	private JPanel contentPane;
	private JTextPane textArea = new JTextPane();
	private JTextField xmlDataPathTextField;

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
			if(incorrectWords.contains(word))
				appendToPane(textArea, word, Color.red);
			else
				appendToPane(textArea,word,Color.white);
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

	        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
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
		setTitle("Urdu Spell Checler");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 499);
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBackground(new Color(56, 74, 107));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(281, 0, 598, 460);
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
			}
		});
		checkBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		textArea.setBounds(0, 182, 593, 250);
		spellCheckPanel.add(textArea);
		textArea.setBackground(new Color(34, 46, 66));
		textArea.setForeground(Color.WHITE);
		textArea.setCaretColor(Color.white);
		
		JLabel lblNewLabel = new JLabel("اپنا متن یہاں درج کریں: ");
		lblNewLabel.setBounds(388, 145, 195, 25);
		spellCheckPanel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(Color.CYAN);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JPanel importPanel = new JPanel();
		importPanel.setBackground(new Color(34, 46, 66));
		tabbedPane.addTab("Import Data", null, importPanel, null);
		importPanel.setLayout(null);
		
		xmlDataPathTextField = new JTextField();
		xmlDataPathTextField.setText("C:\\Users\\Hp\\Downloads\\makhzan-master\\makhzan-master\\text");
		xmlDataPathTextField.setBackground(Color.LIGHT_GRAY);
		xmlDataPathTextField.setBounds(10, 212, 573, 28);
		importPanel.add(xmlDataPathTextField);
		xmlDataPathTextField.setColumns(10);
		
		JLabel xmlPathLbl = new JLabel("ذیل میں XML فائلوں کا راستہ درج کریں:");
		xmlPathLbl.setForeground(Color.WHITE);
		xmlPathLbl.setFont(new Font("Tahoma", Font.BOLD, 17));
		xmlPathLbl.setBounds(259, 162, 324, 38);
		importPanel.add(xmlPathLbl);
		
		JButton btnNewButton = new JButton("داخل کریں۔");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataInserter dataInserter = new DataInserter();
				dataInserter.insertBuiltInData(xmlDataPathTextField.getText());
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnNewButton.setBounds(223, 265, 124, 38);
		importPanel.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Add Words", null, panel_2, null);
		
		JPanel sidePanel = new JPanel();
		sidePanel.setBackground(new Color(96, 210, 196));
		sidePanel.setBounds(0, 0, 280, 460);
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
	}
}
