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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
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
						JOptionPane.showMessageDialog(null, "Bienvenido!", "Credenciales correctas", JOptionPane.INFORMATION_MESSAGE);
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
		this.setMinimumSize(new Dimension(900,300));
		this.setLocationRelativeTo(null);
		
		
	}

	public void register() {
		
	}

}
