package controllers;

import views.HomeView;

public class HomeController {

	private HomeView vista;

	public HomeController() {
		vista = new HomeView();
	}
	
	public void home() {
		vista.home();
		
	}
}
