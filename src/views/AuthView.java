package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controllers.AuthController;
import models.AuthModel;

public class AuthView extends JFrame{

	private AuthModel functions;
	
	private Font fuenteTitulo = new Font("Cambria", Font.BOLD, 36);
	private Font fuenteSubtitulo = fuenteTitulo.deriveFont(32f);
	
	private Font fuenteGrande = new Font("Cambria", Font.PLAIN, 28);
	private Font fuenteMediana = fuenteGrande.deriveFont(14f);
	private Font fuenteChica = fuenteGrande.deriveFont(10f);
	
	public AuthView() {
		functions = new AuthModel();	
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.pack();
		this.setLocationRelativeTo(null);
		
		this.setMinimumSize(new Dimension(600, 600));
		this.setPreferredSize(getPreferredSize());;
		
		this.revalidate();
		this.repaint();
	}
	
	public void login() {
		this.getContentPane().removeAll();
		revalidate();
		repaint();
		
		JPanel panelFondo = new JPanel(new BorderLayout());
		panelFondo.setBackground(Color.RED);
		panelFondo.setBorder(new EmptyBorder(30, 30, 30, 30)); //Margenes 
		
		JPanel panelImagen = new JPanel(new BorderLayout());
		panelImagen.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
	
		JLabel etiquetaBienvenida = new JLabel("¡Bienvenido de nuevo!");
		etiquetaBienvenida.setFont(fuenteTitulo);
		panelImagen.add(etiquetaBienvenida, BorderLayout.NORTH);
		
		Image imagenLogo =  new ImageIcon(this.getClass().getResource("/images/logo.png"))
				.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		JLabel logo = new JLabel(new ImageIcon(imagenLogo));	
		panelImagen.add(logo, BorderLayout.SOUTH);
		
		JPanel panelLogin = new JPanel();
		panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.Y_AXIS));
		
		JLabel etiquetaLogin = new JLabel("Iniciar sesión");
		etiquetaLogin.setFont(fuenteSubtitulo);	
		etiquetaLogin.setHorizontalAlignment(JLabel.CENTER);
		panelLogin.add(etiquetaLogin);
		panelLogin.add(Box.createRigidArea(new Dimension(0, 30)));
		
		JLabel etiquetaUsuario = new JLabel("Nombre de usuario");
		etiquetaUsuario.setFont(fuenteMediana);
		panelLogin.add(etiquetaUsuario);
		
		Image imagenUsuario=  new ImageIcon(this.getClass().getResource("/images/usuario.png"))
				.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		JTextField campoUsuario = new JTextField(){  

			protected void paintComponent(Graphics g) {  
                super.paintComponent(g);  
                int y = (getHeight() - imagenUsuario.getHeight(null))/2;  
                g.drawImage(imagenUsuario, getWidth()-imagenUsuario.getWidth(null), y, this);  
            }  
        };  ;
		panelLogin.add(campoUsuario);
		panelLogin.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JLabel etiquetaPassword = new JLabel("Contraseña");
		etiquetaPassword.setFont(fuenteMediana);
		etiquetaPassword.setBounds(140, 160, 120, 20);
		panelLogin.add(etiquetaPassword);
		
		Image imagenPassword =  new ImageIcon(this.getClass().getResource("/images/password.png"))
				.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		JPasswordField campoPassword = new JPasswordField(){  
            protected void paintComponent(Graphics g) {  
                super.paintComponent(g);  
                int y = (getHeight() - imagenPassword.getHeight(null))/2;  
                g.drawImage(imagenPassword, getWidth()-imagenPassword.getWidth(null), y, this);  
            }  
        };
		campoPassword.setBounds(140, 180, 204, 20);
		panelLogin.add(campoPassword);
		panelLogin.add(Box.createRigidArea(new Dimension(0, 5)));
		
		JPanel panelEtiquetas = new JPanel();
		
		JLabel etiquetaRecuperacion= new JLabel("¿Olvidó su contraseña?");
		etiquetaRecuperacion.setFont(fuenteChica);
		etiquetaRecuperacion.setBounds(250, 210, 120, 20);
		panelEtiquetas.add(etiquetaRecuperacion);
		panelEtiquetas.add(Box.createHorizontalStrut(50));
		
		
		JLabel etiquetaCrearCuenta = new JLabel("Crear cuenta");
		etiquetaCrearCuenta.setFont(fuenteChica);
		etiquetaCrearCuenta.setBounds(250, 210, 120, 20);
		panelEtiquetas.add(etiquetaCrearCuenta);
		etiquetaCrearCuenta.addMouseListener(new MouseListener() {
			
			
			
			@Override
			public void mouseEntered(MouseEvent e) {
				etiquetaCrearCuenta.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				register();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
		});
		
		
		panelEtiquetas.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelLogin.add(panelEtiquetas);
		panelLogin.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JCheckBox recordar = new JCheckBox("Recordarme");
		recordar.setFont(fuenteChica);
		recordar.setBounds(140, 210, 80, 20);
		panelLogin.add(recordar);
		panelLogin.add(Box.createRigidArea(new Dimension(0, 10)));
		
		
		JButton botonLogin = new JButton("Acceder");
		botonLogin.setFont(fuenteMediana);
		botonLogin.setBounds(192, 250, 100, 30);
		panelLogin.add(botonLogin);
		botonLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag1 = false;;
				boolean flag2 = false;;
				if(campoUsuario.getText().length() == 0 || campoUsuario.getText().chars().anyMatch(c -> c == ' ')) {
					campoUsuario.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				} else {
					campoUsuario.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
					flag1 = true;
				}
				
				String paswd = new String(campoPassword.getPassword()); 
				if(paswd.length() < 6 || paswd.chars().anyMatch(c->c ==' ')) {
					campoPassword.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				} else {
					campoPassword.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
					flag2 = true;
				}
				
				if(flag1 && flag2) {
					if(functions.autenticar(campoUsuario.getText(), paswd)) {
						home();
					}
					else {
						JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Credenciales incorrectas", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		
		JPanel panelInterfaz = new JPanel(new GridBagLayout());
		panelInterfaz.add(panelImagen);
		panelInterfaz.add(panelLogin);
		panelLogin.revalidate();
		
		panelFondo.add(panelInterfaz, BorderLayout.CENTER);
		this.add(panelFondo);
		this.pack();
		this.setMinimumSize(new Dimension(900,600));
		this.setLocationRelativeTo(null);
		
		
	}

	public void register() {
		this.getContentPane().removeAll();
		revalidate();
		repaint();
		JPanel panelRegistro = new JPanel();
		
		panelRegistro.setLayout(null);
		panelRegistro.setPreferredSize(new Dimension(500, 500));
		
		JLabel etiqueta = new JLabel("Registro");
		etiqueta.setFont(fuenteGrande);
		etiqueta.setBounds(172, 30, 140, 30);
		etiqueta.setOpaque(true);
		etiqueta.setBackground(Color.ORANGE);		
		etiqueta.setHorizontalAlignment(JLabel.CENTER);
		
		panelRegistro.add(etiqueta);
		
		//Nombre de usuario
		JLabel textoUsuario = new JLabel("Nombre de usuario");
		textoUsuario.setFont(fuenteMediana);
		textoUsuario.setBounds(140, 80, 120, 20);
		textoUsuario.setBackground(Color.ORANGE);
		textoUsuario.setOpaque(true);
		panelRegistro.add(textoUsuario);
		
		JTextField campoUsuario = new JTextField();
		campoUsuario.setBounds(140, 100, 204, 20);
		panelRegistro.add(campoUsuario);
		
		
		//Biografia
		JLabel textoBio = new JLabel("Biografia");
		textoBio.setFont(fuenteMediana);
		textoBio.setBounds(140, 120, 120, 20);
		panelRegistro.add(textoBio);
		
		JTextArea campoBio = new JTextArea();
		campoBio.setBounds(140, 140, 204, 80);
		campoBio.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelRegistro.add(campoBio);
		
		
		//Preferencias
		JLabel textoPreferencias= new JLabel("Preferencias");
		textoPreferencias.setFont(fuenteMediana);
		textoPreferencias.setBounds(140, 220, 120, 20);
		panelRegistro.add(textoPreferencias);
					
		JCheckBox checkBoxDulce = new JCheckBox("Dulce");
		checkBoxDulce.setFont(fuenteMediana);
		checkBoxDulce.setBounds(140, 240, 80, 20);
		panelRegistro.add(checkBoxDulce);
		
		JCheckBox checkBoxSalado = new JCheckBox("Salado");
		checkBoxSalado.setFont(fuenteMediana);
		checkBoxSalado.setBounds(220, 240, 80, 20);
		panelRegistro.add(checkBoxSalado);
		
		JCheckBox checkBoxSaludable = new JCheckBox("Saludable");
		checkBoxSaludable.setFont(fuenteMediana);
		checkBoxSaludable.setBounds(300, 240, 100, 20);
		panelRegistro.add(checkBoxSaludable);
		
		
		//Terminos
		JLabel textoTerminos = new JLabel("Terminos");
		textoTerminos.setFont(fuenteMediana);
		textoTerminos.setBounds(140, 260, 60, 20);
		textoTerminos.setBackground(Color.ORANGE);
		textoTerminos.setOpaque(true);
		panelRegistro.add(textoTerminos);
		
		JRadioButton botonAceptar = new JRadioButton("Acepto los términos");
		botonAceptar.setFont(fuenteChica);
		botonAceptar.setBounds(140, 280, 120, 20);
		botonAceptar.setActionCommand("Si");
		panelRegistro.add(botonAceptar);
		
		JRadioButton botonRechazar = new JRadioButton("No acepto los términos");
		botonRechazar.setFont(fuenteChica);
		botonRechazar.setBounds(260, 280, 130, 20);
		botonRechazar.setActionCommand("No");
		panelRegistro.add(botonRechazar);
		
		ButtonGroup opcionesTerminos = new ButtonGroup();
		opcionesTerminos.add(botonAceptar);
		opcionesTerminos.add(botonRechazar);
		
		
		//Elegir colonia
		JLabel textoColonia = new JLabel("Colonia");
		textoColonia.setFont(fuenteMediana);
		textoColonia.setBounds(140, 300, 50, 20);
		textoColonia.setBackground(Color.ORANGE);
		textoColonia.setBackground(Color.ORANGE);
		textoColonia.setOpaque(true);
		panelRegistro.add(textoColonia);
		
		String[] coloniasDataset = {"Ayuntamiento", "Balandra", "Calafia", "Diana Laura", "El Progreso"};
		
		JComboBox<String> elegirColonia = new JComboBox<String>(coloniasDataset);
		elegirColonia.setBounds(140, 320, 120, 30);
		panelRegistro.add(elegirColonia);
		
		//Boton Crear Cuenta
		JButton botonRegistro = new JButton("Crear Cuenta");
		botonRegistro.setFont(fuenteMediana);
		botonRegistro.setBounds(182, 360, 120, 30);
		panelRegistro.add(botonRegistro);
		JFrame frame = this;
		botonRegistro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flagUsuario = false;
				boolean flagBio = false;
				boolean flagPreferencias = false;
				boolean flagTerminos = false;;
				if(campoUsuario.getText().length() == 0 || campoUsuario.getText().chars().anyMatch(c -> c == ' ')) {
					campoUsuario.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
					flagUsuario = false;
				} else {
					campoUsuario.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
					flagUsuario = true;
				}
				
				if(campoBio.getText().length() > 0 && campoBio.getText().length() < 5) {
					campoBio.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
					flagBio = false;
				} else {
					campoBio.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
					flagBio = true;
				}
				
				if(!checkBoxDulce.isSelected() && !checkBoxSalado.isSelected() && !checkBoxSaludable.isSelected()) {
					textoPreferencias.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
					flagPreferencias = false;
				} else {
					textoPreferencias.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					flagPreferencias = true;
				}
				
				if(opcionesTerminos.getSelection() == null || opcionesTerminos.getSelection().getActionCommand().equals("No")) {
					textoTerminos.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
					flagTerminos = false;
				} else {
					textoTerminos.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					flagTerminos = true;
				}
				if(flagUsuario && flagBio && flagPreferencias && flagTerminos) {
					String usuario = campoUsuario.getText();
					String bio = campoBio.getText();
					StringBuilder preferencias = new StringBuilder();
					if(checkBoxDulce.isSelected()) {
						preferencias.append("Dulce, ");
					}
					if(checkBoxSalado.isSelected()) {
						preferencias.append("Salado, ");
					}
					if(checkBoxSaludable.isSelected()) {
						preferencias.append("Saludable, ");
					}
					String colonia = (String) elegirColonia.getSelectedItem();
					
					functions.registro(usuario, bio, preferencias.toString(), colonia);
					
					JOptionPane.showMessageDialog(frame, "Se ha registrado exitosamente", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		JLabel etiquetaCrearCuenta = new JLabel("¿Ya tiene una cuenta? Iniciar sesión");
		etiquetaCrearCuenta.setFont(fuenteChica);
		etiquetaCrearCuenta.setBounds(170, 400, 160, 20);
		panelRegistro.add(etiquetaCrearCuenta);
		
		etiquetaCrearCuenta.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent e) {
				etiquetaCrearCuenta.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				login();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
		});
		this.add(panelRegistro);
		
		this.setMinimumSize(new Dimension(500,500));
		this.setSize(getMinimumSize());
		this.setLocationRelativeTo(null);
		this.revalidate();
		this.repaint();
	}

	public void home( ) {
		
		JPanel panelHome = new JPanel(new BorderLayout());
		panelHome.setPreferredSize(new Dimension(600,600));
		
		JLabel labelTitulo = new JLabel("Bienvenido!");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(fuenteTitulo);
		panelHome.add(labelTitulo, BorderLayout.NORTH);
		
		JButton logout = new JButton("Logout");
		logout.setPreferredSize(new Dimension(80, 50));
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		panelHome.add(logout, BorderLayout.SOUTH);
		
		this.getContentPane().removeAll();
		this.add(panelHome);
		this.pack();
		this.setLocationRelativeTo(null);
	}
}
