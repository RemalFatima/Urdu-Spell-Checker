package dataAccessLayer;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.*;

import org.apache.log4j.Logger;
import org.w3c.dom.*;

import buisnessLayer.Fascade;
import buisnessLayer.IFascade;
import buisnessLayer.IReaderXML;
import buisnessLayer.Log;
import buisnessLayer.MutantGenerator;
import buisnessLayer.ReaderXML;
import transferObject.*;


// Author  : Remal Fatima


public class WordDAO implements IWordDAO {

	private IFascade bllFascade;
	private Words words = new Words();
	private HashMap<String,String> wordForeignKey = new HashMap<String,String>();
	private ArrayList<String> content = new ArrayList<String>();



//	static Log.logger Log.logger = Log.logger.getLog.logger(WordDAO.class);


	
	// Author  : Remal Fatima
	@Override
	public void insertContent(File file) {
		bllFascade = new Fascade();
		
		bllFascade.readFile(file);
		
		Content contents = bllFascade.getContent();
		
		String query = "INSERT INTO `Content` (Title, Author, content) VALUE ('" + contents.getTitle() + "' ,'" + contents.getAuthor() + "','" + contents.getContent() + "')";

		try {
			
			Statement st =DBhandler.getInstance().getConnection().createStatement();
			st.executeUpdate(query);


		} catch (SQLException ex) {
			Log.logger.info("Error in function insertContent in DAL WORDAO");
			Log.logger.info(ex.getCause());
			Log.logger.warn(ex.getMessage());
		}
	}

	@Override
	public Words getWords(String content, String title){

		
		for (String word: content.split(" ")) {
			if(!(words.getWords().containsKey(word) || wordForeignKey.containsKey(word))) {
				words.put(word, 1);
				wordForeignKey.put(word, title);
			}
			else
				words.put(word,words.getWords().get(word) + 1);
		}

		return words;
	}



	// Author  : Remal Fatima
	

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
			Log.logger.info("Error in function insertWords() in DAL WordDAO");
			Log.logger.info(ex.getCause());
			Log.logger.warn(ex.getMessage());
		}

		// Remove null words from Map
		words.getWords().remove("");
		wordForeignKey.remove("");
		Words word = getAllWords();
		// insert word if does not exists already else update frquency
		for(String key : words.getWords().keySet()) {


		
			try {
				Statement st = DBhandler.getInstance().getConnection().createStatement();
				if(!word.getWords().containsKey(key) ) {

					query = "INSERT INTO `Words` (word, frequency) VALUE ('" + key + "' ," + words.getWords().get(key) + ")";
					st.executeUpdate(query);
				}
				else {
					query = "Update Words set frequency = (frequency + " + words.getWords().get(key) + ") Where word = '" + key + "'";
					st.executeUpdate(query);
					System.out.println("update");
				}

			} catch (SQLException e) {


				Log.logger.info("Error in function insertWords() in DAL WordDAO");
				Log.logger.info(e.getCause());
				Log.logger.warn(e.getMessage());
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
						Log.logger.info("Error in function insertWordsRef() in DAL WordDAO");
						Log.logger.info(ex.getCause());
						Log.logger.warn(ex.getMessage());
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
	

	@Override
	public void insertBuiltInData(String path) {

		File folder = new File(path);
		
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

		
		try {

			Statement st = DBhandler.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {


				words.put(rs.getString(1),Integer.parseInt(rs.getString(2)));

			}

		} catch (SQLException ex) {
			Log.logger.info("Error in getting data from Database");
			Log.logger.info(ex.getCause());
			Log.logger.warn(ex.getMessage());
		}
		return words;
	}
	
	
	@Override
	public ArrayList<wordTableData> getWordsList() {

		String query = "SELECT * FROM `WORDLIST`";

		ArrayList<wordTableData> wordList = new ArrayList<wordTableData>();

		
		try {

			Statement st = DBhandler.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {

				wordTableData TB = new wordTableData();

				TB.put(Integer.parseInt(rs.getString(1)), rs.getString(2),Integer.parseInt(rs.getString(3)));
				wordList.add(TB);
			}

		} catch (SQLException ex) {
			Log.logger.info("Error in getting data from database Words");
			Log.logger.info(ex.getCause());
			Log.logger.warn(ex.getMessage());
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

		
		try {
			

			Statement st = DBhandler.getInstance().getConnection().createStatement();
			st.executeUpdate(query);
			Statement st2 = DBhandler.getInstance().getConnection().createStatement();
			query = "INSERT INTO `WordReference` (word, FileName) VALUE ('" + word + "' ,'" + userName + "')";

			st2.executeUpdate(query);



		} catch (SQLException ex) {

			Log.logger.info(ex.getCause());
			Log.logger.warn(ex.getMessage());
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


			Log.logger.info(e.getCause());
			Log.logger.warn(e.getMessage());
			return false;
		}

	}




}
