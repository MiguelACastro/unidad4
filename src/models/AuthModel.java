package models;

public class AuthModel {

	public AuthModel() {
		
	}
	
	public boolean autenticar(String user, String password) {
		return "Admin".equals(user) && "123456".equals(password);
	}
	
	public void registro(String user, String biografia, String preferencias, String colonia) {
		System.out.println("Usuario: %s, Biografia: %s, Preferencias: %sColonia: %s"
				.formatted(user, biografia, preferencias, colonia));
	}
}
