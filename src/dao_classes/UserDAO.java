package dao_classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import classes.User;
import database.Database;
import exceptions.OperationFailedException;


public class UserDAO {

	
	Database db = new Database();
	Connection conn = db.getConnection();
	
	public User getUserByUsername(String username) throws OperationFailedException {
		
		 String query = "select id, username, password, role from public.user where username = ?";

		 try {
			 
			 PreparedStatement preparedStmt = conn.prepareStatement(query);
		      
		      preparedStmt.setString(1, username);
		      
		      ResultSet rs = preparedStmt.executeQuery();
		      
		      	if(!rs.next()) throw new OperationFailedException("Utente non trovato");
		      		
		      	Integer id = rs.getInt("id");
		      	String usrn = rs.getString("username");
		      	String password = rs.getString("password");
		      	String role = rs.getString("role");
		      		      	
		      	User newUtente = new User(id,usrn,password,role);
			    
			return newUtente;
			 
		 } catch (SQLException e) {		
			 
			 e.printStackTrace();
			 throw new OperationFailedException();
		 }
	     
	}

	public User getUserById(Integer Id) throws OperationFailedException {
		
		 String query = "select id, username, password, role from public.user where id = ?";

		 try {
			 
			 PreparedStatement preparedStmt = conn.prepareStatement(query);
		      
		      preparedStmt.setInt(1, Id);
		      
		      ResultSet rs = preparedStmt.executeQuery();
		      
		      	if(!rs.next()) throw new OperationFailedException("Utente non trovato");
		      		
		      	Integer id = rs.getInt("id");
		      	String usrn = rs.getString("username");
		      	String password = rs.getString("password");
		      	String role = rs.getString("role");
		      		      	
		      	User newUtente = new User(id,usrn,password,role);
			    
			return newUtente;
			 
		 } catch (SQLException e) {		
			 
			 e.printStackTrace();
			 throw new OperationFailedException();
		 }
	     
	}

	
}

