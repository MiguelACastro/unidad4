package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.HomeController;
import controllers.UserController;
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
			}
		});
		panelBoton.add(botonAdd);
		
		return panelUsers;
	}

}
