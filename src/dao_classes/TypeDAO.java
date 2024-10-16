package dao_classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import exceptions.OperationFailedException;

public class TypeDAO {

	Database db = new Database();
	Connection conn = db.getConnection();
	
	public ArrayList<String> getAllTypes() throws OperationFailedException {
		
		String query = "select distinct title from restaurant_type union select title from accommodation_type union select  title from attraction_type"; 
						
				try {

					PreparedStatement preparedStmt = conn.prepareStatement(query);
					
					ResultSet rs = preparedStmt.executeQuery();

					ArrayList<String> TypesList = new ArrayList<String>();
					
					 while (rs.next()) {

							TypesList.add(rs.getString("title"));
					 }
					 

				return TypesList;

				} catch (SQLException e) {
				
				 e.printStackTrace();
				 
				 throw new OperationFailedException();

				}
		
	}
	
	public ArrayList<String> getRestaurantTypes() throws OperationFailedException{
		
		String query = "select distinct title from restaurant_type"; 
		
		try {

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			ResultSet rs = preparedStmt.executeQuery();

			ArrayList<String> TypesList = new ArrayList<String>();
			
			 while (rs.next()) {

					TypesList.add(rs.getString("title"));
			 }
			 

		return TypesList;

		} catch (SQLException e) {
		
		 e.printStackTrace();
		 
		 throw new OperationFailedException();

		}
		
	
	}

	public ArrayList<String> getAccommodationTypes() throws OperationFailedException{
	
		String query = "select distinct title from accommodation_type"; 
		
		try {

			PreparedStatement preparedStmt = conn.prepareStatement(query);
		
			ResultSet rs = preparedStmt.executeQuery();

			ArrayList<String> TypesList = new ArrayList<String>();
		
			while (rs.next()) {

					TypesList.add(rs.getString("title"));
			}
		 

			return TypesList;

		} catch (SQLException e) {
	
			e.printStackTrace();
	 
			throw new OperationFailedException();

		}
	}
	
	public ArrayList<String> getAttractionTypes() throws OperationFailedException{
		
			String query = "select distinct title from attraction_type"; 
		
			try {

				PreparedStatement preparedStmt = conn.prepareStatement(query);
				
				ResultSet rs = preparedStmt.executeQuery();

				ArrayList<String> TypesList = new ArrayList<String>();
			
				while (rs.next()) {

						TypesList.add(rs.getString("title"));
				}
			 

				return TypesList;
				
			} catch (SQLException e) {
		
				e.printStackTrace();
		 
				throw new OperationFailedException();
			}
	}
}