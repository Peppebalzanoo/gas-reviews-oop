package dao_classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import classes.User;
import database.Database;
import exceptions.OperationFailedException;



public class LoginManagerDAO {
	
	Database db = new Database();
	Connection conn = db.getConnection();
	
	public Boolean checkCredentials(String username, String password) throws OperationFailedException{
		
		String query = "select * from public.user where username = ? and password = ?";

		try {
			
			 PreparedStatement preparedStmt = conn.prepareStatement(query);
		      
		      preparedStmt.setString(1, username);
		      preparedStmt.setString(2, password);
		      
		      ResultSet rs = preparedStmt.executeQuery();
		        
		      	if(rs.next()) return true;
		      
		} catch (SQLException e) {
			
			throw new OperationFailedException();		
		}
		
		return false;
	}
	
	public boolean checkIfUserExists(User u) throws OperationFailedException{

	      String query = "select * from public.user where username = ?";

	      try {
	    	  
	    	  PreparedStatement preparedStmt = conn.prepareStatement(query);
		      
		      preparedStmt.setString(1, u.getUsername());
	 
		      ResultSet rs = preparedStmt.executeQuery();
		      
		      
		      	if(rs.next()) return true;
			
	      }catch (SQLException e) {
	    	  
	    	  e.printStackTrace();
	    	  throw new OperationFailedException();   	  
	      }
	     
	      return false;
	}

	public void createNewUser (User u) throws OperationFailedException{
		
		String query = "insert into public.user (username, password) values (?,?)";
	
		try {
			
	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	      
	      preparedStmt.setString(1, u.getUsername());
	      preparedStmt.setString(2, u.getPassword());

	      preparedStmt.execute();
					
		}catch (SQLException e) {
    	  
			 e.printStackTrace();
    	  throw new OperationFailedException();   	  
      }
		
	}
	
	  
	public void updateUserRoleAsAdmin(User u) throws OperationFailedException {
		
		 String query = "update public.user set role = 'admin' where id = ?";

		 try {
			 
			 PreparedStatement preparedStmt = conn.prepareStatement(query);
		      
		      preparedStmt.setInt(1, u.getId());
		      
		      preparedStmt.execute();
			 
		 } catch (SQLException e) {		
			 
			 e.printStackTrace();
			 throw new OperationFailedException();
		 }
	     
	}
	
	public void updateUserPassword(User u) throws OperationFailedException {
		
		 String query = "update public.user set password = ? where id = ?";

		 try {
			 
			 PreparedStatement preparedStmt = conn.prepareStatement(query);
		      
			 preparedStmt.setString(1, u.getPassword());
		     preparedStmt.setInt(2, u.getId());
		      
		     preparedStmt.execute();
		      
			 
		 } catch (SQLException e) {		
			 
			 e.printStackTrace();
			 throw new OperationFailedException();
		 }
	     
	}
	
	
}
