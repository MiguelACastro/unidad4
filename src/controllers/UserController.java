package controllers;

import java.util.List;

import javax.swing.JPanel;

import models.User;
import models.UserModel;
import views.UserView;

public class UserController {

	private UserView vista;
	private UserModel modelo;
	
	public UserController() {
		vista = new UserView();
		modelo = new UserModel();
	}
	
	public JPanel users() {
		return vista.users(modelo.get());
	}
	
	public boolean addUser(User usuario) {
		return modelo.addUser(usuario);
	}
	
	public JPanel updateUser(User userToUpdate) {
		return vista.updateUser(userToUpdate);
	}
	
	public boolean update(User user) {
		return modelo.updateUser(user);
	}
}
