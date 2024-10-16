package classes;


public class User {
	
	private Integer id;
	private String username;
	private String password;
	private String role;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username){
		
		if(username.isBlank()) throw new IllegalArgumentException("Il campo username non può essere vuoto");
		else if (username.length() < 4) throw new IllegalArgumentException("Il campo username deve avere almeno 4 caratteri");
		else if(!username.toLowerCase().equals(username)) throw new IllegalArgumentException("Il campo username non può avere caratteri maiuscoli");
		else if(username.contains(" ")) throw new IllegalArgumentException("Il campo username non può contenere spazi");
		
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		
		if(password.isBlank()) throw new IllegalArgumentException("Il campo password non può essere vuoto");
		else if (password.length() < 6) throw new IllegalArgumentException("Il campo password deve avere almeno 6 caratteri");
		else if (password.toLowerCase().equals(password)) throw new IllegalArgumentException("Il campo password deve avere almeno un carattere maiuscolo");
		else if (password.toUpperCase().equals(password)) throw new IllegalArgumentException("Il campo password deve avere almeno un carattere minuscolo");
		else if(password.contains(" ")) throw new IllegalArgumentException("Il campo password non può contenere spazi");
		
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public User() {
	}
	
	
	public User(String username, String password) {
		
		this.setUsername(username);
		this.setPassword(password);
	}

	public User(String username, String password, String role) {
		
		this.setUsername(username);
		this.setPassword(password);
		this.setRole(role);
	}
	
	public User(Integer id, String username, String password, String role) {
		
		this.setId(id);
		this.setUsername(username);
		this.setPassword(password);
		this.setRole(role);
	}
	
}
