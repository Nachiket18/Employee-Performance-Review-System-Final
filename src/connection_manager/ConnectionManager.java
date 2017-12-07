package connection_manager;

import java.sql.*;

/**
 * Class for creating Connection Manager class which returns a connection object for connecting to database
 * @author GreyHood
 *
 */
public class ConnectionManager 
{
	/**
	 * Creates Connection object
	 * @return Connection object for connecting to database
	 */
	public static Connection getConn() 
	{
		Connection conn = null;
		try 
		{
			Class.forName("nl.cwi.monetdb.jdbc.MonetDriver");
			conn = DriverManager.getConnection("jdbc:monetdb://localhost:50000/", "monetdb", "monetdb");
			return conn;
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		return conn;		
	}	
}