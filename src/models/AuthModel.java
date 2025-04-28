package models;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AuthModel {

	public AuthModel() {
		
	}
	
	public boolean autenticar(String user, String password) {
		try{
			InputStreamReader in = new InputStreamReader(this.getClass().getResourceAsStream("/files/users.txt"));
			BufferedReader userReader = new BufferedReader(in);
			
			String row;
			while((row = userReader.readLine()) != null) {
				String[] valores = row.split(",");
								
				if(valores[0].equals(user) && valores[2].equals(password)) {
					return true;
				}
			}

			userReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	public void registro(String user, String biografia, String preferencias, String colonia) {
		System.out.println("Usuario: %s, Biografia: %s, Preferencias: %sColonia: %s"
				.formatted(user, biografia, preferencias, colonia));
	}
}
