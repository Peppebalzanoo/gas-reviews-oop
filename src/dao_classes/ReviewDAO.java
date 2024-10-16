package dao_classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.Review;
import classes.User;
import database.Database;
import exceptions.OperationFailedException;

public class ReviewDAO {
	
	Database db = new Database();
	Connection conn = db.getConnection();
	
	public void addNewReviewToDB(Review r) throws OperationFailedException{
		
		String query = "insert into review (rating, body, writer, structure_reference) values (?,?,?,?)";

		try {
			
			 PreparedStatement preparedStmt = conn.prepareStatement(query);
		      
		      preparedStmt.setInt(1, r.getRating());
		      preparedStmt.setString(2, r.getBody());
		      preparedStmt.setInt(3, r.getWriter().getId());
		      preparedStmt.setInt(4,r.getStructure_reference());
		           
		      preparedStmt.execute();
		      
		      
		}catch (SQLException e) {
			
			 e.printStackTrace();
			throw new OperationFailedException();
			
		}
		     
	}
	
	public void approveReview (Integer review_id, Integer moderator_id) throws OperationFailedException {
		
		String query = "update review set is_public = true, moderator = ? where id = ?";

		try {
	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	      
	      preparedStmt.setInt(1,moderator_id);
	      preparedStmt.setInt(2,review_id);

	      preparedStmt.execute();
  
		}catch (SQLException e) {
		
			 e.printStackTrace();
		throw new OperationFailedException();
		
		}
	}
	
	public void rejectReview (Integer review_id, Integer moderator_id, String reason) throws  OperationFailedException {
		
		String query = "update review set is_public = false, moderator = ?, reason = ?  where id = ?";

		try {
		PreparedStatement preparedStmt = conn.prepareStatement(query);
	      
	      preparedStmt.setInt(1,moderator_id);
	      preparedStmt.setString(2,reason);
	      preparedStmt.setInt(3,review_id);

	      preparedStmt.execute();
  
		}catch (SQLException e) {
		
			 e.printStackTrace();
		throw new OperationFailedException();
		
		}
	}

	public ArrayList<Review> getAllStructurePublicReviews(Integer structure_reference) throws OperationFailedException{
		
		String query = "select id, rating, body, is_public, reason, writer, structure_reference, moderator "
						+ "from public.review "
						+ "where structure_reference = ? and is_public = true ";
		
		try {

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			   
			preparedStmt.setInt(1, structure_reference);
			
			ResultSet rs = preparedStmt.executeQuery();
		
			ArrayList<Review> reviewList = new ArrayList<Review>();
   
			if (!rs.next()) 
				
			{
				System.out.println("No results");
				throw new OperationFailedException("La ricerca non ha prodotto risultati.");
			
			}
				else 
				do{
					UserDAO ud = new UserDAO();
					
					User writer = ud.getUserById(rs.getInt(6));
				
					User moderator = ud.getUserById(rs.getInt(8));
				
					
					Review r = new Review(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getBoolean(4), rs.getString(5), writer, rs.getInt(6), moderator);
	
					reviewList.add(r);
				
					
				} while (rs.next());

			return reviewList;
	
		} catch (SQLException e) {
		
		 e.printStackTrace();
		 throw new OperationFailedException();
	
		}
		
		
	}

	public ArrayList<Review> getAllReviewsToApprove() throws OperationFailedException{
		
		String query = "select id, rating, body, is_public, reason, writer, structure_reference from public.review where is_public isnull ";

		try {

			PreparedStatement preparedStmt = conn.prepareStatement(query);
	
			ResultSet rs = preparedStmt.executeQuery();

			ArrayList<Review> reviewList = new ArrayList<Review>();

			if (!rs.next()) 
		
			{
				System.out.println("No results");
				throw new OperationFailedException("La ricerca non ha prodotto risultati.");
		
		}
			else 
				
				do{
					UserDAO ud = new UserDAO();
			
					User writer = ud.getUserById(rs.getInt(6));		
			
					Review r = new Review(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getBoolean(4), rs.getString(5), writer, rs.getInt(6), null);
					
					reviewList.add(r);
		
			
				} while (rs.next());

			return reviewList;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new OperationFailedException();

		}

	}

	public ArrayList<Review> getAllWriterReviewsToApprove(User u) throws OperationFailedException{
		
		String query = "select id, rating, body, is_public, reason, writer, structure_reference from public.review where is_public isnull and writer = ? ";

		try {

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			preparedStmt.setInt(1, u.getId());
	
			ResultSet rs = preparedStmt.executeQuery();

			ArrayList<Review> reviewList = new ArrayList<Review>();

			if (!rs.next()) 
		
			{
				System.out.println("No results");
				throw new OperationFailedException("La ricerca non ha prodotto risultati.");
		
		}
			else 
				
				do{
					UserDAO ud = new UserDAO();
			
					User writer = ud.getUserById(rs.getInt(6));		
			
					Review r = new Review(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getBoolean(4), rs.getString(5), writer, rs.getInt(6), null);
					
					System.out.println(rs.getString(3));
					reviewList.add(r);
		
			
				} while (rs.next());

			return reviewList;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new OperationFailedException();

		}

		
		
	}

	public ArrayList<Review> getAllApprovedWriterReviews(User u) throws OperationFailedException{
		
		String query = "select id, rating, body, is_public, reason, writer, structure_reference, moderator from public.review where is_public = true and writer = ? ";

		try {

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			preparedStmt.setInt(1, u.getId());
	
			ResultSet rs = preparedStmt.executeQuery();

			ArrayList<Review> reviewList = new ArrayList<Review>();

			if (!rs.next()) 
		
			{
				System.out.println("No results");
				throw new OperationFailedException("La ricerca non ha prodotto risultati.");
		
		}
			else 
				
				do{
					UserDAO ud = new UserDAO();
			
					User writer = ud.getUserById(rs.getInt(6));	

					User moderator = ud.getUserById(rs.getInt(8));
			
					Review r = new Review(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getBoolean(4), rs.getString(5), writer, rs.getInt(6), moderator);
					
					reviewList.add(r);
		
			
				} while (rs.next());

			return reviewList;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new OperationFailedException();

		}
		
	}
	
	public ArrayList<Review> getAllRejectedWriterReviews(User u) throws OperationFailedException{
		
			String query = "select id, rating, body, is_public, reason, writer, structure_reference, moderator from public.review where is_public = false and writer = ? ";

			try {

				PreparedStatement preparedStmt = conn.prepareStatement(query);
			
				preparedStmt.setInt(1, u.getId());
	
				ResultSet rs = preparedStmt.executeQuery();

				ArrayList<Review> reviewList = new ArrayList<Review>();

				if (!rs.next()) 
		
				{
					System.out.println("No results");
					throw new OperationFailedException("La ricerca non ha prodotto risultati.");
		
				}
				else 
				
					do{
						UserDAO ud = new UserDAO();
			
						User writer = ud.getUserById(rs.getInt(6));	

						User moderator = ud.getUserById(rs.getInt(8));
			
						Review r = new Review(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getBoolean(4), rs.getString(5), writer, rs.getInt(6), moderator);
					
						reviewList.add(r);
		
			
					} while (rs.next());

				return reviewList;

			} catch (SQLException e) {

				e.printStackTrace();
				throw new OperationFailedException();

			}
			
		}

	public ArrayList<Review> getAllApprovedAdminReviews(User u) throws OperationFailedException{
		
		String query = "select id, rating, body, is_public, reason, writer, structure_reference, moderator from public.review where is_public = true and moderator = ? ";

		try {

			PreparedStatement preparedStmt = conn.prepareStatement(query);
		
			preparedStmt.setInt(1, u.getId());

			ResultSet rs = preparedStmt.executeQuery();

			ArrayList<Review> reviewList = new ArrayList<Review>();

			if (!rs.next()) 
	
			{
				System.out.println("No results");
				throw new OperationFailedException("La ricerca non ha prodotto risultati.");
	
			}
			else 
			
				do{
					UserDAO ud = new UserDAO();
		
					User writer = ud.getUserById(rs.getInt(6));	

					User moderator = ud.getUserById(rs.getInt(8));
		
					Review r = new Review(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getBoolean(4), rs.getString(5), writer, rs.getInt(6), moderator);
				
					reviewList.add(r);
	
		
				} while (rs.next());

			return reviewList;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new OperationFailedException();

		}
		
	}

	public ArrayList<Review> getAllRejecteddAdminReviews(User u) throws OperationFailedException{
		
		String query = "select id, rating, body, is_public, reason, writer, structure_reference, moderator from public.review where is_public = false and moderator = ? ";

		try {

			PreparedStatement preparedStmt = conn.prepareStatement(query);
		
			preparedStmt.setInt(1, u.getId());

			ResultSet rs = preparedStmt.executeQuery();

			ArrayList<Review> reviewList = new ArrayList<Review>();

			if (!rs.next()) 
	
			{
				System.out.println("No results");
				throw new OperationFailedException("La ricerca non ha prodotto risultati.");
	
			}
			else 
			
				do{
					UserDAO ud = new UserDAO();
		
					User writer = ud.getUserById(rs.getInt(6));	

					User moderator = ud.getUserById(rs.getInt(8));
		
					Review r = new Review(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getBoolean(4), rs.getString(5), writer, rs.getInt(6), moderator);
				
					reviewList.add(r);
	
		
				} while (rs.next());

			return reviewList;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new OperationFailedException();

		}
		
	}
	
	public Boolean checkIfExistsAReviewByUserId(Integer structure_id, Integer user_id) throws OperationFailedException{
		
		String query = "select * from review where structure_reference = ? and writer = ?";

		try {

			PreparedStatement preparedStmt = conn.prepareStatement(query);
		
			preparedStmt.setInt(1, structure_id);
			preparedStmt.setInt(2, user_id);

			ResultSet rs = preparedStmt.executeQuery();

			if (!rs.next()) return false;
			else return true;
	

		} catch (SQLException e) {

			e.printStackTrace();
			throw new OperationFailedException();

		}
		
	}
}

