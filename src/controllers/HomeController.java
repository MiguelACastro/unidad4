package controllers;

import models.AuthModel;
import views.HomeView;

public class HomeController {

	private HomeView vista;
	private AuthModel modelo;

	public HomeController() {
		vista = new HomeView();
	}
	
	public void home() {
		vista.home();
		
	}
}
