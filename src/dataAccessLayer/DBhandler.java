package dataAccessLayer;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBhandler {
  private static DBhandler instance;
  private Connection con;
  String url = "jdbc:mysql://localhost:3307/content?useSSL=false";
  String user = "root";
  String password = "";
  private DBhandler() throws SQLException {
    try {
      this.con = DriverManager.getConnection(url, user, password);
    } catch (Exception ex) {
      System.out.println("Something is wrong with the DB con String : " + ex.getMessage());
    }
  }
  public Connection getConnection() {
    return con;
  }
  public static DBhandler getInstance() throws SQLException {
    if (instance == null) {
      instance = new DBhandler();
    } else if (instance.getConnection().isClosed()) {
      instance = new DBhandler();
    }
    return instance;
  }
  
}