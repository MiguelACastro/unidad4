package controllers;

import java.util.List;

import javax.swing.JPanel;

import models.User;
import models.UserModel;
import views.UserView;

public class UserController {

	private UserView vista;
	
	public UserController() {
		vista = new UserView();
	}
	
	public JPanel users() {
		UserModel modelo = new UserModel();
		return vista.users(modelo.get());
	}
}
