package classes;

import dao_classes.LoginManagerDAO;
import dao_classes.UserDAO;
import exceptions.OperationFailedException;
import exceptions.NoActiveUserException;


public class LoginManager {
	
	private static User activeUser;

	public static User getActiveUser() throws NoActiveUserException {
		
		if (activeUser == null) throw new NoActiveUserException();
		else return activeUser;

	}

	public static void setActiveUser(User activeUser) {
		LoginManager.activeUser = activeUser;
	}
	
	public void login(String username, String password) throws OperationFailedException {
		
		LoginManagerDAO lm_instance = new LoginManagerDAO();

		Boolean status = lm_instance.checkCredentials(username, password);
		
		if (status == true) {
			
			UserDAO ud_instance = new UserDAO();
		
			LoginManager.setActiveUser(ud_instance.getUserByUsername(username));
				
		} else throw new OperationFailedException("Credenziali errate!");
	
	}
	
	public void createNewUser(String username, String password) throws OperationFailedException, IllegalArgumentException {
			
		LoginManagerDAO lm_instance = new LoginManagerDAO();
		
		User u = new User(username, password);
		Boolean status =  !lm_instance.checkIfUserExists(u);
		

		if (status == true) {
			
			lm_instance.createNewUser(u);
			
		} else throw new OperationFailedException("L'username " + u.getUsername() + " non è disponibile.");
		
	}
		
	public void promoteUserToAdmin(User u) throws OperationFailedException {
		
		LoginManagerDAO lm_instance = new LoginManagerDAO();
		
		lm_instance.updateUserRoleAsAdmin(u);
	}
		
	public boolean isActiveUserAdmin() throws NoActiveUserException {

		
		User u = getActiveUser();
		String s = u.getRole();
		
		if (s.equals("admin")) return true;
		else return false;
	}
	
	public void changeActiveUserPassword(String password) throws OperationFailedException, NoActiveUserException {
		
		activeUser.setPassword(password);
		
		LoginManagerDAO lm_instance = new LoginManagerDAO();
		
		lm_instance.updateUserPassword(activeUser);
	}
	
	public String getActiveUserPassword(){
		
		return activeUser.getPassword();
	}
}


