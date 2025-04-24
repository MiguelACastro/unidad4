package controllers;

import models.AuthModel;
import views.AuthView;

public class AuthController {

	private AuthView vista;
	private AuthModel modelo;

	public AuthController() {
		vista = new AuthView();
		modelo = new AuthModel();
	}
	
	public void login() {
		vista.login();
		
	}
	
	public void register() {
		vista.register();
	}
	
}
