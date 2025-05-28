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
import java.util.Locale;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

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
		model.addColumn("Actualizar");
		model.addColumn("Eliminar");
		
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
					updatedAt,
					"✏️",
					"❌"
			};
			model.addRow(row);
		}
		tablaUsers.setModel(model);
		tablaUsers.getColumn("Actualizar").setCellRenderer(new ButtonRenderer());
		tablaUsers.getColumn("Actualizar").setCellEditor(new ButtonEditor(new JCheckBox(), users, this));

		tablaUsers.getColumn("Eliminar").setCellRenderer(new ButtonRenderer());
		tablaUsers.getColumn("Eliminar").setCellEditor(new ButtonEditor(new JCheckBox(), users, this));
		
		JScrollPane pane = new JScrollPane(tablaUsers);
		panelUsers.add(pane, BorderLayout.CENTER);
		
		JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelBoton.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0));
		panelUsers.add(panelBoton, BorderLayout.NORTH);
		
		JButton botonAdd = new JButton("Añadir");
		botonAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelUsers.removeAll();
				panelUsers.add(addUser());
				panelUsers.revalidate();
				panelUsers.repaint();
			}
		});
		panelBoton.add(botonAdd);
		
		return panelUsers;
	}
	

	public JPanel addUser() {
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

	public JPanel updateUser(User userToUpdate) {
		JPanel panelUpdate = new JPanel();
		panelUpdate.setLayout(new BoxLayout(panelUpdate, BoxLayout.Y_AXIS));

		JLabel nombreLabel = new JLabel("Nombre");
		nombreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelUpdate.add(nombreLabel);

		JTextField nombreField = new JTextField(20);
		nombreField.setText(userToUpdate.getName());
		nombreField.setAlignmentX(Component.LEFT_ALIGNMENT);
		nombreField.setMaximumSize(new Dimension(200, 25));
		nombreField.setPreferredSize(new Dimension(200, 25));
		panelUpdate.add(nombreField);
		panelUpdate.add(Box.createVerticalGlue());

		JLabel emailLabel = new JLabel("Email");
		emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelUpdate.add(emailLabel);

		JTextField emailField = new JTextField(20);
		emailField.setText(userToUpdate.getEmail());
		emailField.setAlignmentX(Component.LEFT_ALIGNMENT);
		emailField.setMaximumSize(new Dimension(200, 25));
		emailField.setPreferredSize(new Dimension(200, 25));
		panelUpdate.add(emailField);
		panelUpdate.add(Box.createVerticalGlue());

		JLabel rolLabel = new JLabel("rol");
		rolLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelUpdate.add(rolLabel);

		JTextField rolField = new JTextField(20);
		rolField.setText(userToUpdate.getRole());
		rolField.setAlignmentX(Component.LEFT_ALIGNMENT);
		rolField.setMaximumSize(new Dimension(200, 25));
		rolField.setPreferredSize(new Dimension(200, 25));
		panelUpdate.add(rolField);
		panelUpdate.add(Box.createVerticalGlue());

		JLabel telefonoLabel = new JLabel("telefono");
		telefonoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelUpdate.add(telefonoLabel);

		JTextField telefonoField = new JTextField(20);
		telefonoField.setText(userToUpdate.getPhone());
		telefonoField.setAlignmentX(Component.LEFT_ALIGNMENT);
		telefonoField.setMaximumSize(new Dimension(200, 25));
		telefonoField.setPreferredSize(new Dimension(200, 25));
		panelUpdate.add(telefonoField);
		panelUpdate.add(Box.createVerticalGlue());

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
			userToUpdate.setName(nombre);
			userToUpdate.setEmail(email);
			userToUpdate.setRole(rol);
			userToUpdate.setPhone(telefono);
			
			boolean resultado = new UserController().update(userToUpdate);
			if(resultado) {				
				panelUsers.removeAll();
				panelUsers.add(new UserController().users());
				panelUsers.revalidate();
				panelUsers.repaint();
			} else {
				JOptionPane.showMessageDialog(panelUsers.getTopLevelAncestor(), 
						"No se pudo actualizar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		panelUpdate.add(botonGuardar);
		
		panelUpdate.add(Box.createVerticalStrut(10));
		JButton botonCancelar= new JButton("Cancelar");
		botonCancelar.setAlignmentX(Component.LEFT_ALIGNMENT);
		botonCancelar.addActionListener(_ -> {
			panelUsers.removeAll();
			panelUsers.add(new UserController().users());
			panelUsers.revalidate();
			panelUsers.repaint();
		});
		panelUpdate.add(botonCancelar);
		return panelUpdate;
	}
	class ButtonRenderer extends JButton implements TableCellRenderer {
	    public ButtonRenderer() {
	        setOpaque(true);
	    }
	    public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {
	        setText((value == null) ? "" : value.toString());
	        return this;
	    }
	}

	class ButtonEditor extends DefaultCellEditor {
	    private JButton button;
	    private String label;
	    private boolean isPushed;
	    private ArrayList<User> users;
	    private UserView userView;
	    private int row;

	    public ButtonEditor(JCheckBox checkBox, ArrayList<User> users, UserView userView) {
	        super(checkBox);
	        this.users = users;
	        this.userView = userView;
	        button = new JButton();
	        button.setOpaque(true);
	        button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                fireEditingStopped();
	                if("✏️".equals(label)) {
	                	User userToUpdate = users.get(row);
	                	userView.panelUsers.removeAll();
	                	userView.panelUsers.add(userView.updateUser(userToUpdate));
	                	userView.panelUsers.revalidate();
	                	userView.panelUsers.repaint();
	                } else if ("❌".equals(label)) {
	                	int id = users.get(row).getId();
	                	int opcion = JOptionPane.showConfirmDialog(userView.tablaUsers.getTopLevelAncestor()
	                			, "¿Esta seguro que desea borrar este usuario?"
	                			, "Confirmar", JOptionPane.YES_NO_OPTION);
	                	if(opcion==JOptionPane.YES_OPTION) {
	                		boolean eliminado = new UserController().deleteUser(id);
	            			if(eliminado) {
	                            users.remove(row);
	                            ((DefaultTableModel) userView.tablaUsers.getModel()).removeRow(row);
	                            userView.tablaUsers.revalidate();
	                            userView.tablaUsers.repaint();
	                            JOptionPane.showMessageDialog(userView.tablaUsers.getTopLevelAncestor()
	                            		, "El usuario se elimino correctamente"
	                            		, "Usuario eliminado", JOptionPane.INFORMATION_MESSAGE);
	            			} else {
	            				JOptionPane.showMessageDialog(userView.tablaUsers.getTopLevelAncestor()
	            						, "No se pudo eliminar el usuario"
	            						, "Usuario no eliminado", JOptionPane.ERROR_MESSAGE);	            				
	            			}
	                	}
	                }
	            }
	        });
	    }

	    public Component getTableCellEditorComponent(JTable table, Object value,
	            boolean isSelected, int row, int column) {
	        this.row = row;
	        label = (value == null) ? "" : value.toString();
	        button.setText(label);
	        isPushed = true;
	        return button;
	    }

	    public Object getCellEditorValue() {
	        isPushed = false;
	        return label;
	    }
	}

}
