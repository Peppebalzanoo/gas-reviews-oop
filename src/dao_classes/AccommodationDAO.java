package dao_classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import classes.Accommodation;
import database.Database;
import exceptions.OperationFailedException;

public class AccommodationDAO {
	
	Database db = new Database();
	Connection conn = db.getConnection();

	public ArrayList<String> getAccommodationtTypes(Integer accommodation_reference) throws OperationFailedException{
		
		String query = "select title from accommodation_type where accommodation_reference = ?";

		try {
	
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	   
			preparedStmt.setInt(1, accommodation_reference);
		
			ResultSet rs = preparedStmt.executeQuery();

			ArrayList<String> restaurantTypes = new ArrayList<String>();

			 while (rs.next()) {
		
					restaurantTypes.add(rs.getString("title"));
					
			 }

		return restaurantTypes;
	
		} catch (SQLException e) {
		
		 e.printStackTrace();
		 throw new OperationFailedException();
	
		}
	
	}
	
	public ArrayList<Accommodation> getAccommodationsByFilters (String title_filter , String country_filter, String type_filter, String address_filter, String price_filter, String rating_average_filter) throws OperationFailedException {
		
		Boolean isNotTypeFilterSetted = false;
		
		if (title_filter == "" || title_filter == null) title_filter = "%";
		if (country_filter == "" || country_filter == null) country_filter = "%";
		if (type_filter == "" || type_filter == null) isNotTypeFilterSetted = true;	
		if (address_filter == "" || address_filter == null) address_filter = "%";
		if (price_filter == "" || price_filter == null) price_filter = "%";
		if (rating_average_filter == "" || rating_average_filter == null) rating_average_filter = "0";
		
		String query = "select st.id, st.title, st.country, st.address, st.price, st.rating_average, st.picture, ac.id, ac.rooms, ac.structure_reference "
				+ "from public.structure as st inner join public.accommodation as ac on st.id = ac.structure_reference "
				+ "where ( lower(title) like ? or lower(address) like ? ) "
				+ "and ( cast((country) as character varying) = ? "
				+ "and price like ? "
				+ "and rating_average >= ? ) "
				+ "order by title";

		try {

			
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			   
			preparedStmt.setString(1, "%" + title_filter.toLowerCase() + "%");
			preparedStmt.setString(2, "%" + address_filter.toLowerCase() + "%");
			preparedStmt.setString(3, country_filter.toLowerCase());
			preparedStmt.setString(4, price_filter);
			preparedStmt.setInt(5, Integer.parseInt(rating_average_filter));
   
			ResultSet rs = preparedStmt.executeQuery();
		
			ArrayList<Accommodation> accommodationsList = new ArrayList<Accommodation>();
   
			if (!rs.next()) 
				
			{
				System.out.println("No results");
				throw new OperationFailedException("La ricerca non ha prodotto risultati.");
			
			}
				else 
				do{
				

					ImageIcon img = new ImageIcon(rs.getBytes(7));
					ArrayList<String> typesList = this.getAccommodationtTypes(rs.getInt(8));
				
					Accommodation a = new Accommodation(rs.getInt(1),
											rs.getString(2), 
											rs.getString(3), 
											rs.getString(4), 
											rs.getString(5), 
											rs.getInt(6),
											img,
											rs.getInt(1),
											rs.getString(8),
											typesList);
					
					if(typesList.contains(type_filter) ||  isNotTypeFilterSetted ) accommodationsList.add(a);
					
				} while (rs.next());

			return accommodationsList;
	
		} catch (SQLException e) {
		
		 e.printStackTrace();
		 throw new OperationFailedException();
	
		}
		
	}
	

}
