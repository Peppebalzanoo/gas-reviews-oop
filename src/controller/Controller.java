package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.table.DefaultTableModel;
import classes.Accommodation;
import classes.Attraction;
import classes.LoginManager;
import classes.Restaurant;
import classes.Review;
import classes.User;
import dao_classes.AccommodationDAO;
import dao_classes.AttractionDAO;
import dao_classes.RestaurantDAO;
import dao_classes.ReviewDAO;
import dao_classes.TypeDAO;
import dao_classes.UserDAO;
import database.Database;
import exceptions.NoActiveUserException;
import exceptions.OperationFailedException;
import frames.EditProfileFrame;
import frames.LoginManagerFrame;
import frames.MainFrame;
import utilities.ScaleImage;

public class Controller {

	
	 MainFrame MainFrameCtrl;
	 LoginManagerFrame LoginManagerFrameCtrl;
	 EditProfileFrame EditProfileFrameCtrl;
	 
	 ScaleImage scaleIcons = new ScaleImage();
	ArrayList<Accommodation> acc = new ArrayList<Accommodation>();
	ArrayList<Restaurant> res = new ArrayList<Restaurant>();
	ArrayList<Attraction> att = new ArrayList<Attraction>();

	
	public static void main(String[] args)  {

		Controller ctrl = new Controller();
		connectToDB();
	
		ctrl.setVisibleMainFrame(true);
		
	}
	
	
	public void addNewAdmin() {
		
		String s = (String)JOptionPane.showInputDialog(
                MainFrameCtrl,
                "Inserisci username dell'utente che vuoi promuovere ad admin: ",
                "Promuovi ad admin.",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null);
		if ( s != null) {
				
			UserDAO uDAO = new UserDAO();
			LoginManager lm = new LoginManager();
		
			if (JOptionPane.showConfirmDialog(null, 
		            "Sei sicuro di voler promuovere l'utente " + s + " ad admin?\nL'utente avr� i diritti per accedere alla pagina di amministrazione.", "Attenzione!", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
				
				try {
					lm.promoteUserToAdmin(uDAO.getUserByUsername(s));
				} catch (OperationFailedException e) {
					JOptionPane.showMessageDialog(MainFrameCtrl,
							e.getMessage(),
							"Oops!",
							JOptionPane.ERROR_MESSAGE);	
				}
				
			}
		}
	}
	
	
	public void logoutEvent() {
		LoginManager lm = new LoginManager();
		lm.setActiveUser(null);
	}
	
	
	public void openAdminPage(DefaultTableModel df, DefaultTableModel dfApprovedReview, DefaultTableModel dfRejectedReview, JLayeredPane layeredPane, JPanel panel) {
		ReviewDAO rewDAO = new ReviewDAO();
		LoginManager lm = new LoginManager();
		try {
			if(lm.isActiveUserAdmin()) {
				MainFrameCtrl.updateLayeredPane(layeredPane, panel);
				MainFrameCtrl.clearTable(df);
				MainFrameCtrl.clearTable(dfApprovedReview);
				MainFrameCtrl.clearTable(dfRejectedReview);
				ArrayList<Review> reviewList;
				try {
					reviewList = rewDAO.getAllReviewsToApprove();
					for(Review r: reviewList) {
						Object[] O = { r.getWriter().getUsername(), r.getBody(), r.getRating(), r.getId()};
						MainFrameCtrl.aggRiga(O, df);
					}
				} catch (OperationFailedException e) {
	
				}
				ArrayList<Review> reviewApprovedList;
				try {
					reviewApprovedList = rewDAO.getAllApprovedAdminReviews(lm.getActiveUser());
					for(Review r: reviewApprovedList) {
						Object[] O = { r.getWriter().getUsername(), r.getBody(), r.getRating()};
						MainFrameCtrl.aggRiga(O, dfApprovedReview);
					}
				} catch (OperationFailedException e) {
	
				}
				ArrayList<Review> reviewRejectedList;
				try {
					reviewRejectedList = rewDAO.getAllRejecteddAdminReviews(lm.getActiveUser());
					for(Review r: reviewRejectedList) {
						Object[] O = { r.getWriter().getUsername(), r.getBody(), r.getRating(), r.getReason()};
						MainFrameCtrl.aggRiga(O, dfRejectedReview);
					}
				} catch (OperationFailedException e) {
	
				}

			} else {
				JOptionPane.showMessageDialog(LoginManagerFrameCtrl,
						"Non hai i permessi per accedere a questa pagina.",
						"Oops!",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (NoActiveUserException e) {
	
		}
		
		
	}
	
	
	
	public void showProfileTab(DefaultTableModel df, DefaultTableModel dfApprovedReview, DefaultTableModel dfRejectedReview, JLabel insertUsernameLabelProfile) {
		ReviewDAO rewDAO = new ReviewDAO();
		LoginManager lm = new LoginManager();
		try {
			insertUsernameLabelProfile.setText("   " + lm.getActiveUser().getUsername());
			MainFrameCtrl.clearTable(df);
			MainFrameCtrl.clearTable(dfApprovedReview);
			MainFrameCtrl.clearTable(dfRejectedReview);
			
			try {
				ArrayList<Review> reviewList = rewDAO.getAllWriterReviewsToApprove(lm.getActiveUser());
				for(Review r: reviewList) {
					Object[] O = { r.getBody(), r.getRating()};
					MainFrameCtrl.aggRiga(O, df);
				}
			}catch(OperationFailedException e) {
	
			}
			
			try {
				ArrayList<Review> reviewInApprovedList = rewDAO.getAllApprovedWriterReviews(lm.getActiveUser());
				for(Review r: reviewInApprovedList) {
					Object[] O = { r.getBody(), r.getRating(), r.getModerator().getUsername()};
					MainFrameCtrl.aggRiga(O, dfApprovedReview);
				}
			}catch (OperationFailedException e) {
				
	
			}
			
			try {
				ArrayList<Review> reviewInRejectedList = rewDAO.getAllRejectedWriterReviews(lm.getActiveUser());
				for(Review r: reviewInRejectedList) {
					Object[] O = { r.getBody(), r.getRating(), r.getModerator().getUsername(), r.getReason()};
					MainFrameCtrl.aggRiga(O, dfRejectedReview);
				}
			}catch(OperationFailedException e) {
	
				
			}
			
		}  catch (NoActiveUserException e) {
			

		}
	}
	
	
	public void openModify() {
		this.setVisibleEditProfileFrame(true);
	}
	
	
	public void populateReviewTable(DefaultTableModel df, String structureReference) {
		ReviewDAO rewDAO = new ReviewDAO();
		try {
			
			if (df.getRowCount() > 0) {
			     for (int j = df.getRowCount() -1; j > -1; j--) {
			         df.removeRow(j);
			     }
			 }
			
			ArrayList<Review> reviewList = rewDAO.getAllStructurePublicReviews(Integer.parseInt(structureReference));
			for(Review r: reviewList) {
				Object[] O = { r.getWriter().getUsername(), r.getBody(), r.getRating()};
				MainFrameCtrl.aggRiga(O, df);
			}
			
		} catch (OperationFailedException e) {
			
			JOptionPane.showMessageDialog(LoginManagerFrameCtrl,
					"Non � presente nessuna recensione per la struttura selezionata.\nSii il primo a recensirla!",
					"Oops!",
					JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		
	}
		
	public void addReview(String rating, String body, String structureReference) {
		try {
	    	Integer ratingAverage = null;
	    	if(rating.contentEquals("1 Stella")) {
				ratingAverage = 1;
			} else if(rating.contentEquals("2 Stelle")) {
				ratingAverage = 2;
			} else if(rating.contentEquals("3 Stelle")) {
				ratingAverage = 3;
			} else if(rating.contentEquals("4 Stelle")) {
				ratingAverage = 4;
			} else if(rating.contentEquals("5 Stelle")) {
				ratingAverage = 5;
			}

			User u = LoginManager.getActiveUser();
			Review rew = new Review(ratingAverage, body, u, Integer.parseInt(structureReference));
			
			if (JOptionPane.showConfirmDialog(null, 
		            "Sei sicuro di voler inserire la recensione?\nIl tuo username sar� pubblico una volta approvata.", "Confermare?",
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	
				if(rew.checkIfExistsAReviewByUserId( Integer.parseInt(structureReference), u.getId())) {
					
					JOptionPane.showMessageDialog(LoginManagerFrameCtrl,
							"Hai gi� inserito una recensione per questa struttura!",
							"Oops!",
							JOptionPane.ERROR_MESSAGE);
					
					MainFrameCtrl.restoreReviewDetails();
					
				} else {
					
					rew.addReview();
					
					JOptionPane.showMessageDialog(LoginManagerFrameCtrl,
							"La recensione � stata inserita correttamente!\nDovr� essere approvata da un moderatore.",
							"Yees!",
							JOptionPane.INFORMATION_MESSAGE);

					MainFrameCtrl.restoreReviewDetails();
				}
				
				
				
				
		        }
			
			
			
		} catch (NoActiveUserException e) {
			setVisibleLoginManagerFrame(true);
		}catch (OperationFailedException e) {
			
			JOptionPane.showMessageDialog(MainFrameCtrl,
					e.getMessage(),
					"Oops!",
					JOptionPane.ERROR_MESSAGE);		
			
		}
		
	}
	
	public void setStructureInformation(int rowPosition, JLabel nameStructureLb, JLabel insertAddreLabel, JLabel insertPriceLabel, JLabel insertStar, JLabel structureReferenceLabel, JLabel insertPhotoLabel, JLabel specifications, JLabel types ) {
		Integer activeStructure = MainFrameCtrl.getSelectedStructure();
		int i = 0;
		if(activeStructure == 0 ) {
			for(Accommodation a: acc) {
				i++;
				if(i == rowPosition) {
					Accommodation accomod = a;
					nameStructureLb.setText(accomod.getTitle());
					insertAddreLabel.setText(accomod.getAddress());
					insertPriceLabel.setText(accomod.getPrice());
					String s = String.valueOf(accomod.getRating_average());
					insertStar.setText(s);
					structureReferenceLabel.setText(accomod.getStructure_reference().toString());
					MainFrameCtrl.insertInLabel(accomod.getPicture(), insertPhotoLabel);
					specifications.setText("   Numero stanze: " + accomod.getRooms());
					types.setText("   Tipo: " + accomod.getTypeList().toString());
				}
			}
		} else if(activeStructure == 1) {
			for(Restaurant r: res) {
				i++;
				if(i == rowPosition) {
					Restaurant resta = r;
					nameStructureLb.setText(resta.getTitle());
					insertAddreLabel.setText(resta.getAddress());
					insertPriceLabel.setText(resta.getPrice());
					String s = String.valueOf(resta.getRating_average());
					insertStar.setText(s);
					structureReferenceLabel.setText(resta.getStructure_reference().toString());
					MainFrameCtrl.insertInLabel(resta.getPicture(), insertPhotoLabel);
					specifications.setText("   Numero sale: " + resta.getRooms() + "   Numero posti a sedere: " + resta.getSeats());
					types.setText("   Tipo: " + resta.getTypeList().toString());
				}
			}
		}else {
			for(Attraction a: att) {
				i++;
				if(i == rowPosition) {
					Attraction atr = a;
					nameStructureLb.setText(atr.getTitle());
					insertAddreLabel.setText(atr.getAddress());
					insertPriceLabel.setText(atr.getPrice());
					String s = String.valueOf(atr.getRating_average());
					insertStar.setText(s);
					structureReferenceLabel.setText(atr.getStructure_reference().toString());
					MainFrameCtrl.insertInLabel(atr.getPicture(), insertPhotoLabel);
					specifications.setText("   Amministrazione: " + atr.getManagement());
					types.setText("   Tipo: " + atr.getTypeList().toString());
				}
			}	
		}
	
	}
	
	public void populateTypeComboBox(JComboBox box) {
		TypeDAO td = new TypeDAO();
		
		try {
			
			Integer typeStructure = MainFrameCtrl.getSelectedStructure();

			ArrayList<String> s = null;
			box.removeAllItems();
			MainFrameCtrl.getTypesComboBox().addItem("Qualsiasi");
			MainFrameCtrl.getTypesComboBox().setSelectedItem("Qualsiasi");
			if(typeStructure == 0) {
				s = td.getAccommodationTypes();
			} else if(typeStructure == 1) {
				s = td.getRestaurantTypes();
			}else if(typeStructure == 2){
				s = td.getAttractionTypes();
			}
			
			for(String j: s) {
				box.addItem(j);
			}
			

		}catch (Exception e) {
			
			JOptionPane.showMessageDialog(MainFrameCtrl,
					"Impossibile aggiornare i filtri",
					"Oops!",
					JOptionPane.ERROR_MESSAGE);		
			
		}
	}
	
	public void loginEvent(String username, String password) {
		
		LoginManager lm_instance = new LoginManager();

		try {
			lm_instance.login(username, password);
			LoginManagerFrameCtrl.dispose();
			
		} catch (OperationFailedException e) {
			JOptionPane.showMessageDialog(LoginManagerFrameCtrl,
				    e.getMessage(),
				    "Oops!",
				    JOptionPane.ERROR_MESSAGE);	
		}
	}
	
	public void createNewUserEvent(String username, String password, String confirmPassword) {
		
		LoginManager lm_instance = new LoginManager();
		
		if ( !password.equals(confirmPassword) ) {
			
			JOptionPane.showMessageDialog(LoginManagerFrameCtrl,
					"Le password non coincidono",
					"Oops!",
					JOptionPane.ERROR_MESSAGE);		
		}else {

			try {
			
				lm_instance.createNewUser(username, password);
				lm_instance.login(username, password);
				LoginManagerFrameCtrl.dispose();
			
			} catch (OperationFailedException e) {

			
				JOptionPane.showMessageDialog(LoginManagerFrameCtrl,
						e.getMessage(),
						"Oops!",
						JOptionPane.ERROR_MESSAGE);
			}
		
			catch (IllegalArgumentException e){
	
				JOptionPane.showMessageDialog(LoginManagerFrameCtrl,
						e.getMessage(),
						"Oops!",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public static void connectToDB() {	
		
		 Database db = new Database();
			try {
				db.setConnection();
			} catch (SQLException e) {
				Object[] options = {"Riprova",
	            "Chiudi"};
						int n = JOptionPane.showOptionDialog(null,
						"Non � stato possibile connettersi al Server",
						"Oops!",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.ERROR_MESSAGE,
						null,     
						options,
						options[0]);

						switch(n){
							
							case -1: System.exit(-1);							
						
							case 0: Controller.main(null);
						
							case 1: System.exit(-1);						
						}			
			}
	}
	
	public void forgottenPassword() {
		
		JOptionPane.showMessageDialog(LoginManagerFrameCtrl,
			   "Questa funzionalit� non � stata ancora implementata!\nCerca di ricordartela!",
			    "Oops!",
			    JOptionPane.ERROR_MESSAGE);	
		
	}
	public void programClosing() {
		
        if (JOptionPane.showConfirmDialog(null, 
	            "Sei sicuro di voler chiudere l'applicazione?", "Attenzione!", 
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
        	
        	  this.disconnectFromDB();
        	  System.exit(0);
        }
	}

	public void disconnectFromDB()  {
		
		Database db = new Database();
		Connection conn = db.getConnection();
		try {
			conn.close();
			System.out.println("Disconnected from the PostgreSQL server successfully.");
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(MainFrameCtrl,
					e.getMessage(),
					"Oops!",
					JOptionPane.ERROR_MESSAGE);		
		}
	}
	
    public void setVisibleMainFrame(Boolean isVisible) {
    	
    	MainFrameCtrl = new MainFrame(this);
		MainFrameCtrl.setVisible(isVisible);
		this.populateTypeComboBox(MainFrameCtrl.getTypesComboBox());
	}
    
 public void setVisibleEditProfileFrame(Boolean isVisible) {
    	
	    EditProfileFrameCtrl = new EditProfileFrame (this);
	    EditProfileFrameCtrl .setVisible(isVisible);

	}
  
  
    public void setVisibleLoginManagerFrame(Boolean isVisible) {
    	
    	LoginManagerFrameCtrl = new LoginManagerFrame(this);
		LoginManagerFrameCtrl.setVisible(isVisible);
	}
    
    public String setPriceFilter(String s) {
    	String priceFilter = null;
		if(s.equals("�")) {
 		   priceFilter = "1";
		}else if(s.equals("��")) {
			priceFilter = "2";
		}else if(s.equals("���")){
			priceFilter= "3";
		}
		return priceFilter;
    }
    
    public String setRatingAverageFilter(String rating_average_filter) {
    	String ratingAverageFilter = null;
    	if(rating_average_filter.contentEquals("Qualsiasi")) {
			ratingAverageFilter = "";
		}else if(rating_average_filter.contentEquals("Minimo 1")) {
			ratingAverageFilter = "1";
		} else if(rating_average_filter.contentEquals("Minimo 2")) {
			ratingAverageFilter = "2";
		} else if(rating_average_filter.contentEquals("Minimo 3")) {
			ratingAverageFilter = "3";
		} else if(rating_average_filter.contentEquals("Minimo 4")) {
			ratingAverageFilter = "4";
		} else if(rating_average_filter.contentEquals("Minimo 5")) {
			ratingAverageFilter = "5";
		} else {
			ratingAverageFilter = null;
		}
    	return ratingAverageFilter;
    }
      
	public void populateStructuresTable(String title_filter, String country_filter, String type_filter, String price_filter, String address_filter, String rating_average_filter, DefaultTableModel df) {
		acc.clear();
		res.clear();
		att.clear();

	
		try {
			
			String priceFilter = setPriceFilter(price_filter);
			String ratingAverageFilter = setRatingAverageFilter(rating_average_filter);
			
			if(type_filter.equals("Qualsiasi")) {
				type_filter = "";
			}
			
			if (df.getRowCount() > 0) {
			     for (int j = df.getRowCount() -1; j > -1; j--) {
			         df.removeRow(j);
			     }
			 }
			
			Integer typeStructure = MainFrameCtrl.getSelectedStructure();
			AccommodationDAO ad = new AccommodationDAO();
			RestaurantDAO rd = new RestaurantDAO();
			AttractionDAO atd = new AttractionDAO();
			
			if(typeStructure == 0) {

				acc = ad.getAccommodationsByFilters(title_filter, country_filter, type_filter, address_filter, priceFilter, ratingAverageFilter);
				for(Accommodation a: acc) {
					ImageIcon img = scaleIcons.scaleImg(a.getPicture(), 100, 100);
					Object[] O = {img, a.getTitle(), a.getAddress(), a.getRating_average()};
					MainFrameCtrl.aggRiga(O, df);
				}
			} else if(typeStructure == 1) {
				res = rd.getRestaurantsByFilters(title_filter, country_filter, type_filter, address_filter, priceFilter, ratingAverageFilter);
				for(Restaurant r: res) {
					ImageIcon img = scaleIcons.scaleImg(r.getPicture(), 100, 100);
					Object[] O = {img, r.getTitle(), r.getAddress(), r.getRating_average()};
					MainFrameCtrl.aggRiga(O, df);
				}
			} else if(typeStructure == 2) {
				att = atd.getAttractionsByFilters(title_filter, country_filter, type_filter, address_filter, priceFilter, ratingAverageFilter);
				for(Attraction at: att) {
					ImageIcon img = scaleIcons.scaleImg(at.getPicture(), 100, 100);
					Object[] O = {img, at.getTitle(), at.getAddress(), at.getRating_average()};
					MainFrameCtrl.aggRiga(O, df);
				}
			}
			
			
		}catch(OperationFailedException e) {
			
			
		}
		
	}
	
	public void openProfilePage(JLayeredPane layeredPane, JPanel panel) {
		
		
		try {
			
			LoginManager.getActiveUser();
			
			MainFrameCtrl.updateLayeredPane(layeredPane, panel);
			
		} catch (NoActiveUserException e) {
			
			setVisibleLoginManagerFrame(true);
		}
		
	}
	
	public String getUsernameText() {
		try {
			User u = LoginManager.getActiveUser();
			return u.getUsername();
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(MainFrameCtrl,
					e.getMessage(),
					"Oops!",
					JOptionPane.ERROR_MESSAGE);		
			
		}
		return null;
	}
		
	public void updateUserPasswordEvent(String actualPassword, String newPassword, String confirmNewPassword) {
	
		LoginManager lm = new LoginManager();
		
		if ( !actualPassword.contentEquals(lm.getActiveUserPassword())) {
			
			JOptionPane.showMessageDialog(LoginManagerFrameCtrl,
					"La password attuale non � corrretta!",
					"Oops!",
					JOptionPane.ERROR_MESSAGE);		
			
		}else if(!newPassword.equals(confirmNewPassword)) {
			
			JOptionPane.showMessageDialog(LoginManagerFrameCtrl,
					"Le nuove password non corrispondono!",
					"Oops!",
					JOptionPane.ERROR_MESSAGE);		
			
		}else {
			
			
			try {
				lm.changeActiveUserPassword(newPassword);
				JOptionPane.showMessageDialog(LoginManagerFrameCtrl,
						"Password modificata correttamente",
						"Oops!",
						JOptionPane.INFORMATION_MESSAGE);	
				
				 EditProfileFrameCtrl.dispose();
		
				
			} catch (OperationFailedException e) {
				
				JOptionPane.showMessageDialog(LoginManagerFrameCtrl,
						e.getMessage(),
						"Oops!",
						JOptionPane.ERROR_MESSAGE);	
				
			} catch (NoActiveUserException e) {
				
				JOptionPane.showMessageDialog(MainFrameCtrl,
						e.getMessage(),
						"Oops!",
						JOptionPane.ERROR_MESSAGE);		
			}
		}
		
	}
	
	public void moderateEvent(Integer review_id ) {
		Object[] options = {"Approva",
        "Nega"};
				int n = JOptionPane.showOptionDialog(null,
				"Desideri approvare questa recensione?",
				"Modera recensione",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,     
				options,
				options[0]);
				
				ReviewDAO rew = new ReviewDAO();
				LoginManager lm = new LoginManager();
				switch(n){
				
					case 0: 
					try {
						rew.approveReview(review_id, lm.getActiveUser().getId());
						
						JOptionPane.showMessageDialog(MainFrameCtrl,
								"L'operazione � stata completata con successo!",
								"Oops!",
								JOptionPane.INFORMATION_MESSAGE);		
						
					} catch (OperationFailedException e) {
						
						JOptionPane.showMessageDialog(MainFrameCtrl,
								e.getMessage(),
								"Oops!",
								JOptionPane.ERROR_MESSAGE);		
						
					} catch (NoActiveUserException e) {
						
						JOptionPane.showMessageDialog(MainFrameCtrl,
								e.getMessage(),
								"Oops!",
								JOptionPane.ERROR_MESSAGE);			
					}
					break;
				
					case 1: 
												
						String s = (String)JOptionPane.showInputDialog(
						                    MainFrameCtrl,
						                    "Inserisci una motivazione: ",
						                    "Richiesta motivazione.",
						                    JOptionPane.PLAIN_MESSAGE,
						                    null,
						                    null,
						                    null);
						
						if(s != null) {
							
						
						try {
						
							rew.rejectReview(review_id, lm.getActiveUser().getId(), s);
							
							JOptionPane.showMessageDialog(MainFrameCtrl,
									"L'operazione � stata completata con successo!",
									"Oops!",
									JOptionPane.INFORMATION_MESSAGE);	
						
						} catch (OperationFailedException e) {
						
							JOptionPane.showMessageDialog(MainFrameCtrl,
									e.getMessage(),
									"Oops!",
									JOptionPane.ERROR_MESSAGE);		
							
						} catch (NoActiveUserException e) {

							JOptionPane.showMessageDialog(MainFrameCtrl,
									e.getMessage(),
									"Oops!",
									JOptionPane.ERROR_MESSAGE);		
						}
						
					}
							break;
					
				}
	}

	
	
	
}
