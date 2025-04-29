package models;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class AuthModel {

	public AuthModel() {
		
	}
	
	public boolean autenticar(String user, String password) {
		try{
			InputStreamReader in = new InputStreamReader(this.getClass().getResourceAsStream("/files/users.txt"));
			BufferedReader userReader = new BufferedReader(in);
			
			String row;
			while((row = userReader.readLine()) != null) {
				String[] valores = row.split("\\|");
	
				if(valores[5].equals(user) && valores[6].equals(password)) {
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
	public void registro(String nombre, String apellido, String empresa, String ambito, 
			String cargo, String usuario, String password,String correo ) {
		try {
			FileWriter file = new FileWriter("src/files/users.txt", true);
			PrintWriter userWriter = new PrintWriter(file);
			
			userWriter.println("%s|%s|%s|%s|%s|%s|%s|%s"
					.formatted(nombre, apellido, empresa, ambito, cargo, usuario, password, correo));
			
			userWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
