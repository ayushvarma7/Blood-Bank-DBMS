import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class MySQLAccess {
	
	 private Connection connect = null;
	  private Statement statement = null;
	  private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;
//	  private String databaseJDBC_URI = "jdbc:mysql://remotemysql.com:3306/D2uwXzqNcD";
private String databaseJDBC_URI = "jdbc:mysql://remotemysql.com:3306/SmFKVXaES2";

//	SmFKVXaES2   WDSvrZpWUU
//	  final private String host = "remotemysql.com";
//	  final private String user = "D2uwXzqNcD";                                           //Database connector for connecting the application to an
//	  final private String passwd = "CB7pOrZdn0";                                         //online database on remotesql.com managed by myphpadmin.


	final private String host = "remotemysql.com";
	final private String user = "SmFKVXaES2";                                           //Database connector for connecting the application to an
	final private String passwd = "WDSvrZpWUU";

	public Connection Connect_to_DataBase() throws Exception {
	    try {
	     
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      
	 
	      return connect = DriverManager
	          .getConnection(databaseJDBC_URI, user, passwd );
//				  .getConnection("jdbc:mysql://localhost:3307/blood_donation_database", "ayush", "test1234" );
//							  .getConnection("jdbc:mysql://remotemysql.com:3306/SmFKVXaES2", "SmFKVXaES2", "WDSvrZpWUU" );

       }catch(Exception e) {
    	   	JOptionPane myOption = new JOptionPane();
	    	myOption.showMessageDialog(null, "Connection failed, Please restart app");
    	   System.out.println("Connection failed");
	         return null;
                          }
	  }
	  
}
