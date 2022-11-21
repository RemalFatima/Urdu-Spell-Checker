
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
import buisnessLayer.Corrector;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

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
		setBounds(100, 100, 514, 359);
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBackground(new Color(117, 117, 117));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		textArea.setBackground(new Color(63, 63, 63));
		textArea.setForeground(Color.WHITE);
		
		
		textArea.setBounds(0, 126, 498, 194);
		contentPane.add(textArea);
		textArea.setCaretColor(Color.white);
		
		JButton checkBtn = new JButton("چیک کریں");
		checkBtn.setForeground(Color.DARK_GRAY);
		checkBtn.setBackground(new Color(192, 192, 192));
		checkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				highlight();
			}
		});
		checkBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		checkBtn.setBounds(10, 83, 114, 32);
		contentPane.add(checkBtn);
		
		JLabel lblNewLabel = new JLabel("اپنا متن یہاں درج کریں: ");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(Color.CYAN);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(336, 90, 162, 25);
		contentPane.add(lblNewLabel);
		
		JButton importBtn = new JButton("بلٹ ان ڈیٹا درآمد کریں");
		importBtn.setForeground(Color.DARK_GRAY);
		importBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		importBtn.setBackground(Color.LIGHT_GRAY);
		importBtn.setBounds(315, 11, 173, 25);
		contentPane.add(importBtn);
		
		JButton addWordBtn = new JButton("نیا لفظ شامل کریں");
		addWordBtn.setForeground(Color.DARK_GRAY);
		addWordBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		addWordBtn.setBackground(Color.LIGHT_GRAY);
		addWordBtn.setBounds(160, 13, 145, 25);
		contentPane.add(addWordBtn);
		
		JLabel lblNewLabel_1 = new JLabel("___________________________________________________________________________________");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(0, 47, 498, 14);
		contentPane.add(lblNewLabel_1);
	}
}
