package dataAccessLayer;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import Fascade.Fascade;
import Fascade.IFascade;
import buisnessLayer.Count;
import buisnessLayer.IReaderXML;
import buisnessLayer.MutantGenerator;
import buisnessLayer.ReaderXML;
import transferObject.*;


// Author  : Remal Fatima


public class WordDAO implements IWordDAO {

	private IFascade bllFascade;
	private HashMap<String, Integer> words = new HashMap<String,Integer>();
	private HashMap<String,String> wordForeignKey = new HashMap<String,String>();
	private ArrayList<String> content = new ArrayList<String>();





	// Insert data into content table ( title , author , content in file )
	// Author  : Remal Fatima
	@Override
	public void insertContent(File file) {
		bllFascade = new Fascade();
		// Read File
		bllFascade.readFile(file);
		// Get content from file
		Content contents = bllFascade.getContent();
		// query
		String query = "INSERT INTO `Content` (Title, Author, content) VALUE ('" + contents.getTitle() + "' ,'" + contents.getAuthor() + "','" + contents.getContent() + "')";

		try {
			// execute query
			Statement st =DBhandler.getInstance().getConnection().createStatement();
			st.executeUpdate(query);
			Count.num += 1;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error in inserting content into table `Content` - Function insertContent in WordDAO");
		}
	}

	@Override
	public HashMap<String, Integer> getWords(String content, String title){

		// Using split function.
		for (String word: content.split(" ")) {
			if(!(words.containsKey(word) || wordForeignKey.containsKey(word))) {
				words.put(word, 1);
				wordForeignKey.put(word, title);
			}
			else
				words.put(word,words.get(word) + 1);
		}

		return words;
	}



	// Author  : Remal Fatima
	// Insert word into Words table ( word , frequency , title of file where found first )

	@Override
	public void insertWords() {

		String query = "Delete From `Words`";
		Connection con = null;
		String content;
		String title;

		try {

			Statement st = DBhandler.getInstance().getConnection().createStatement();
			//st.execute(query);
			query = "Select * from `Content`";
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				title = rs.getString(1);
				content = rs.getString(3);
				getWords(content,title);
			}
			rs.close();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		// Remove null words from Map
		words.remove("");
		wordForeignKey.remove("");
		Words word = getAllWords();
		// insert word if does not exists already else update frquency
		for(String key : words.keySet()) {


			Count.num += 1;
			try {
				Statement st = DBhandler.getInstance().getConnection().createStatement();
				if(!word.getWords().containsKey(key) ) {

					query = "INSERT INTO `Words` (word, frequency) VALUE ('" + key + "' ," + words.get(key) + ")";
					st.executeUpdate(query);
				}
				else {
					query = "Update Words set frequency = (frequency + " + words.get(key) + ") Where word = '" + key + "'";
					st.executeUpdate(query);
					System.out.println("update");
				}

			} catch (SQLException e) {


				System.out.println(e.getMessage());
			}
		}

	}

	@Override
	public void insertWordRef() {

		String query ;
		Connection con = null;
		String content;
		String title;

		try {

			Statement st = DBhandler.getInstance().getConnection().createStatement();
			//st.execute(query);
			query = "Select * from `Content`";
			ResultSet rs = st.executeQuery(query);
			wordForeignKey.clear();
			while (rs.next()) {

				title = rs.getString(1);
				content = rs.getString(3);
				int titleID = Integer.parseInt(rs.getString(4));
				for(String word : content.split(" "))
				{
					if(!wordForeignKey.containsKey(word))
						wordForeignKey.put(word, title);
				}
				wordForeignKey.remove("");
				for(String key : wordForeignKey.keySet()) {
					try {
						Statement st2 = DBhandler.getInstance().getConnection().createStatement();
						query = "INSERT INTO `WordReference` (word, FileName, FileID) VALUE ('" + key + "' ,'" + wordForeignKey.get(key) + "', " + titleID + ")";
						st2.executeUpdate(query);
					} catch (SQLException ex) {
						System.out.println(ex.getMessage());
					}
				}
				wordForeignKey.clear();

			}


		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}



	// Author  : Remal Fatima
	// Insert Both content and words into database
	// Arguements take path of folder where file is located

	@Override
	public void insertBuiltInData(String path) {

		File folder = new File(path);


		// create connection

		// Insert content ( title , author , content in file ) 
		for ( File file : folder.listFiles()) {

			insertContent(file);


		}
		insertWords();
		//	insertWordRef();

	}


	/*
	 * 		Author Absar Ali ( 20F-0232 ) 
	 * 		Function : getAllWords()
	 * 		Return Words and frequency from Database
	 */

	@Override
	public Words getAllWords() {

		String query = "SELECT word , frequency FROM `Words`";

		Words words = new Words();

		// Read words from database
		try {

			Statement st = DBhandler.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {


				words.put(rs.getString(1),Integer.parseInt(rs.getString(2)));

			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return words;
	}
	@Override
	public ArrayList<wordTableData> getWordsList() {

		String query = "SELECT * FROM `WORDLIST`";

		ArrayList<wordTableData> wordList = new ArrayList<wordTableData>();

		// Read words from database
		try {

			Statement st = DBhandler.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {

				wordTableData TB = new wordTableData();
				
				TB.put(Integer.parseInt(rs.getString(1)), rs.getString(2),Integer.parseInt(rs.getString(3)));
				wordList.add(TB);
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return wordList;
	}

	// Author : Remal Fatima 
	// Manually add new words by user
	@Override
	public boolean manualAddWords(String userName, String word)
	{
		Words words = getAllWords(); // reads from database

		String query = "INSERT INTO `Words` (word, frequency) VALUE ('" + word + "' ," + 1 + ")";

		// insert new word into Words & WordReference Table
		try {
			// execute query

			Statement st = DBhandler.getInstance().getConnection().createStatement();
			st.executeUpdate(query);
			Statement st2 = DBhandler.getInstance().getConnection().createStatement();
			query = "INSERT INTO `WordReference` (word, FileName) VALUE ('" + word + "' ,'" + userName + "')";

			st2.executeUpdate(query);



		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error in inserting word` - Function manualAddWords in WordDAO");
		}

		return true;
	}

	
	// updates word in database
	@Override
	public boolean updateWord(wordTableData data) {
		try {
			Statement st = DBhandler.getInstance().getConnection().createStatement();
			
			 
				String query = "Update Words set word = '" + data.getWord() + "' Where wordID = " + data.getId() + "";
				st.executeUpdate(query);
				System.out.println("updated");
				return true;
			

		} catch (SQLException e) {


			System.out.println(e.getMessage());
			return false;
		}
		
	}




}
