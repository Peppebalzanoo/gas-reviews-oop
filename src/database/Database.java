package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


	public class Database {

		
	    private final String url = "";
	    private final String user = "";
	    private final String password = "";
	    
	    static Connection conn = null;
	 
	  
	    public Connection connect() throws SQLException {
	        
	    	conn = null;
	      
	            conn = DriverManager.getConnection(url, user, password);
	            System.out.println("Connected to the PostgreSQL server successfully.");
	        
	 
	        return conn;

	    }
		
	    public Connection getConnection() {
	    
	    	return conn;
	    	
	    }
	    
	    public void setConnection() throws SQLException {
	    	
	    	if (conn == null) {
	    		
	    		conn = this.connect();

	    	}
	    	
	    }

}
