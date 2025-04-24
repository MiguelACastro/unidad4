package models;

public class AuthModel {

	public AuthModel() {
		
	}
	
	public boolean autenticar(String user, String password) {
		return "Admin".equals(user) && "123456".equals(password);
	}
}
