package dataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import buisnessLayer.Fascade;
import buisnessLayer.IFascade;
import buisnessLayer.IMutantGenerator;
import buisnessLayer.Log;
import buisnessLayer.MutantGenerator;
import transferObject.Mutant;
import transferObject.Mutants;
import transferObject.Words;


/*
 * Author Absar Ali ( 20F-0232)
 *
 */

public class MutantDAO implements IMutantDAO {


	private IFascade bllFascade;
	//static Log.logger Log.logger = Log.logger.getLog.logger(MutantDAO.class);

	
	@Override
	public void insertBuiltInMutants() {

		bllFascade = new Fascade();
		Mutants mutants =  new Mutants();

		String query ;

		Words wordList = bllFascade.getAllWords();
		for( String word : wordList.getWords().keySet()) {
			mutants.getMutant().clear();
			Words _word = new Words();
			_word.put(word, wordList.getWords().get(word));
			mutants = bllFascade.generateMutants(_word);
			System.out.println(mutants.getMutant().size() + " size ");
			for(Mutant mutant : mutants.getMutant()) {
				try {
					Statement st = DBhandler.getInstance().getConnection().createStatement();
					//System.out.println(key.getCorrectWord());
					query = "INSERT INTO `Mutants` (mutant, CorrectWord) VALUE ('" + mutant.getMutantString()+ "' ,'" + mutant.getCorrectWord() + "')";
					st.executeUpdate(query);

				} catch (SQLException ex) {

					Log.logger.info("Error in function insertContent in DAL MUTANTDAO");
					Log.logger.info(ex.getCause());
					Log.logger.warn(ex.getMessage());
				}
			}
		}
	}

	
	@Override
	public boolean manualWordMutant(String word)
	{
		bllFascade = new Fascade();
		Mutants mutants = new Mutants();
		Words words = new Words();
		words.put(word, 3);
		mutants = bllFascade.generateMutants(words);
		String query = "INSERT INTO `Words` (word, frequency) VALUE ('" + word + "' ," + 1 + ")";


		for(Mutant key : mutants.getMutant()) {
			try {

				Statement st = DBhandler.getInstance().getConnection().createStatement();
				query = "INSERT INTO `Mutants` (mutant, CorrectWord) VALUE ('" + key.getMutantString() + "' ,'" + key.getCorrectWord() + "')";
				st.executeUpdate(query);

			} catch (SQLException e) {

				System.out.println(e.getMessage());
				return false;
			}
		}

		return true;
	}

	
	@Override
	public Mutants getAllMutants() {

		String query = "SELECT * FROM `mutants`";

		Mutants mutants = new Mutants();


		try {

			Statement st = DBhandler.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {


				mutants.put(rs.getString(1),rs.getString(2));

			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return mutants;
	}


}
