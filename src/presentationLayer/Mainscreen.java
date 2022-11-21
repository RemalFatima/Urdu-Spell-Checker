/*
 * @Author: ManalSaqib 20F-0141 
 * Main screen interface to highlight the incorrect words 
 */
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
				appendToPane(textArea,word,Color.black);
			appendToPane(textArea,"  ", Color.black);
		}
		appendToPane(textArea," ", Color.black);
		
		
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(2, 2, 108));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		textArea.setForeground(Color.CYAN);
		
		
		textArea.setBounds(0, 67, 434, 194);
		contentPane.add(textArea);
		
		JButton checkBtn = new JButton("چیک کریں");
		checkBtn.setForeground(Color.BLACK);
		checkBtn.setBackground(SystemColor.inactiveCaption);
		checkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				highlight();
			}
		});
		checkBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		checkBtn.setBounds(10, 27, 114, 32);
		contentPane.add(checkBtn);
		
		JLabel lblNewLabel = new JLabel("اپنا متن یہاں درج کریں: ");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(Color.CYAN);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(230, 30, 194, 25);
		contentPane.add(lblNewLabel);
	}
}
