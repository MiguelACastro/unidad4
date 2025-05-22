package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.HomeController;
import controllers.UserController;
import models.Producto;
import models.User;

public class UserView {
	private JPanel panelUsers;
	private JTable tablaUsers;
	
	public UserView() {
		panelUsers = new JPanel();
		tablaUsers = new JTable();
	}
	
	public JPanel users(ArrayList<User> users) {
		panelUsers.setLayout(new BorderLayout());
		panelUsers.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Id");
		model.addColumn("Nombre");
		model.addColumn("Email");
		model.addColumn("Rol");
		model.addColumn("Telefono");
		model.addColumn("Fecha de creacion");
		model.addColumn("Fecha de actualizacion");
		
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			
			
			String phone = Optional.ofNullable(user.getPhone()).orElse("N/A");
			
			Date defaultDate = new Date(0);
			Date createdAtDate = Optional.ofNullable(user.getCreatedAt()).orElse(defaultDate);
			String createdAt = !createdAtDate.equals(defaultDate)?createdAtDate.toString():"N/A";
			
			Date updatedAtDate = Optional.ofNullable(user.getUpdateAt()).orElse(defaultDate);
			String updatedAt = !updatedAtDate.equals(defaultDate)?updatedAtDate.toString():"N/A";
			String[] row = {
					String.valueOf(user.getId()),
					user.getName(),
					user.getEmail(),
					user.getRole(),
					phone,
					createdAt,
					updatedAt
			};
			model.addRow(row);
		}
		tablaUsers.setModel(model);
		
		JScrollPane pane = new JScrollPane(tablaUsers);
		panelUsers.add(pane, BorderLayout.CENTER);
		
		JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelBoton.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0));
		panelUsers.add(panelBoton, BorderLayout.NORTH);
		
		JButton botonEliminar = new JButton("Borrar");
		botonEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panelBoton.add(botonEliminar);
		
		JButton botonAdd = new JButton("Añadir");
		botonAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelUsers.removeAll();
				panelUsers.add(addProduct());
				panelUsers.revalidate();
				panelUsers.repaint();
			}
		});
		panelBoton.add(botonAdd);
		
		return panelUsers;
	}
	

	public JPanel addProduct() {
		JPanel panelAdd = new JPanel();
		panelAdd.setLayout(new BoxLayout(panelAdd, BoxLayout.Y_AXIS));

		JLabel nombreLabel = new JLabel("Nombre");
		nombreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelAdd.add(nombreLabel);

		JTextField nombreField = new JTextField(20);
		nombreField.setAlignmentX(Component.LEFT_ALIGNMENT);
		nombreField.setMaximumSize(new Dimension(200, 25));
		nombreField.setPreferredSize(new Dimension(200, 25));
		panelAdd.add(nombreField);
		panelAdd.add(Box.createVerticalGlue());

		JLabel emailLabel = new JLabel("Email");
		emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelAdd.add(emailLabel);

		JTextField emailField = new JTextField(20);
		emailField.setAlignmentX(Component.LEFT_ALIGNMENT);
		emailField.setMaximumSize(new Dimension(200, 25));
		emailField.setPreferredSize(new Dimension(200, 25));
		panelAdd.add(emailField);
		panelAdd.add(Box.createVerticalGlue());

		JLabel rolLabel = new JLabel("rol");
		rolLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelAdd.add(rolLabel);

		JTextField rolField = new JTextField(20);
		rolField.setAlignmentX(Component.LEFT_ALIGNMENT);
		rolField.setMaximumSize(new Dimension(200, 25));
		rolField.setPreferredSize(new Dimension(200, 25));
		panelAdd.add(rolField);
		panelAdd.add(Box.createVerticalGlue());

		JLabel telefonoLabel = new JLabel("telefono");
		telefonoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelAdd.add(telefonoLabel);

		JTextField telefonoField = new JTextField(20);
		telefonoField.setAlignmentX(Component.LEFT_ALIGNMENT);
		telefonoField.setMaximumSize(new Dimension(200, 25));
		telefonoField.setPreferredSize(new Dimension(200, 25));
		panelAdd.add(telefonoField);
		panelAdd.add(Box.createVerticalGlue());

		JButton botonGuardar = new JButton("Guardar");
		botonGuardar.setAlignmentX(Component.LEFT_ALIGNMENT);
		botonGuardar.addActionListener(_ -> {
			String nombre = nombreField.getText();
			String email = emailField.getText();
			String rol = rolField.getText();
			String telefono = telefonoField.getText();
			if(telefono.isBlank()) {
				telefono = null;
			}
			User usuario = new User(0, nombre, email, rol, telefono, null, null);
			boolean resultado = new UserController().addUser(usuario);
			if(resultado) {				
				panelUsers.removeAll();
				panelUsers.add(new UserController().users());
				panelUsers.revalidate();
				panelUsers.repaint();
			} else {
				JOptionPane.showMessageDialog(panelUsers.getTopLevelAncestor(), 
						"No se pudo añadir el usuario", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		panelAdd.add(botonGuardar);
		
		panelAdd.add(Box.createVerticalStrut(10));
		JButton botonCancelar= new JButton("Cancelar");
		botonCancelar.setAlignmentX(Component.LEFT_ALIGNMENT);
		botonCancelar.addActionListener(_ -> {
			panelUsers.removeAll();
			panelUsers.add(new UserController().users());
			panelUsers.revalidate();
			panelUsers.repaint();
		});
		panelAdd.add(botonCancelar);
		return panelAdd;
	}
}
