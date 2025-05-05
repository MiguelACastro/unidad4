package app;

import controllers.AuthController;
import controllers.HomeController;

public class Main {

	public static void main(String[] args) {
//		AuthController app = new AuthController();
//		app.login();
		new HomeController().home();
	}

}
