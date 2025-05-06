package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import controllers.AuthController;
import controllers.ProductController;
import models.AuthModel;
import models.ProductModel;

public class HomeView extends JFrame{

	private Font fuenteTitulo = new Font("Cambria", Font.BOLD, 36);
	private Font fuenteSubtitulo = fuenteTitulo.deriveFont(32f);
	
	private final Color rojo = new Color(244,66,52);
	private final Color rojoFuerte = new Color(183, 27, 28);
	
	private JPanel panelDerecha = new JPanel(new BorderLayout());
	
	public HomeView() {	
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.pack();
		this.setLocationRelativeTo(null);
		
		this.setPreferredSize(getPreferredSize());;
		
		this.revalidate();
		this.repaint();
	}
	
	public void home( ) {
		JPanel panelHome = new JPanel(new BorderLayout());
		panelHome.setPreferredSize(new Dimension(600,600));
		
		JPanel panelNavegacion = new JPanel();
		panelNavegacion.setLayout(new BoxLayout(panelNavegacion, BoxLayout.Y_AXIS));
		panelNavegacion.setBorder(new EmptyBorder(50,  50, 50, 50));
		panelNavegacion.setBackground(rojoFuerte);
		panelHome.add(panelNavegacion, BorderLayout.WEST);
		
		JLabel labelTitulo = new JLabel("Bienvenido!");
		labelTitulo.setForeground(Color.WHITE);
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(fuenteTitulo);
		panelNavegacion.add(labelTitulo);
		
		panelNavegacion.add(Box.createVerticalGlue());
		
		JLabel usuarios = new JLabel("Usuarios");
		usuarios.setForeground(Color.WHITE);
		usuarios.setIcon(getImageIcon("user.png", 50, 50));
		usuarios.addMouseListener(clickableLabel());
		usuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Hola", "Usuarios", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panelNavegacion.add(usuarios);
		
		panelNavegacion.add(Box.createVerticalGlue());
		
		JLabel productos = new JLabel("Productos");
		productos.setForeground(Color.WHITE);
		productos.setIcon(getImageIcon("product.png", 50, 50));
		productos.addMouseListener(clickableLabel());
		productos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrar(new ProductController(new ProductModel(), new ProductView()).productos());
			}
		});
		panelNavegacion.add(productos);
		
		panelNavegacion.add(Box.createVerticalGlue());
		
		JLabel settings = new JLabel("Settings");
		settings.setForeground(Color.WHITE);
		settings.setIcon(getImageIcon("settings.png", 50, 50));
		settings.addMouseListener(clickableLabel());
		settings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Hola", "Settings", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panelNavegacion.add(settings);
		
		
		JMenuBar barraSuperior = new JMenuBar();
		barraSuperior.setBackground(rojo);
		panelDerecha.add(barraSuperior, BorderLayout.NORTH);
		
		JButton logout = new JButton("Logout");
		logout.setBorder(null);
		logout.setForeground(Color.WHITE);
		logout.setBackground(rojo);
		logout.setIcon(getImageIcon("exit.png", 50, 50));
		logout.setPreferredSize(new Dimension(80, 50));
		JFrame frame = this;
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AuthController().login();
			}
		});
		barraSuperior.add(logout);

		panelHome.add(panelDerecha, BorderLayout.CENTER);
		
		this.add(panelHome);
		this.mostrar(panelDashboard());
	}
	
	public JPanel panelDashboard() {
		JPanel panelDashboard = new JPanel(new GridBagLayout());
		panelDashboard.setBackground(Color.WHITE);
		
		GridBagConstraints reglas = new GridBagConstraints();
		reglas.gridx = 0;
		reglas.gridy = 0;
		reglas.gridheight = 1;
		reglas.gridwidth = 1;
		reglas.anchor = GridBagConstraints.NORTHEAST;
		reglas.insets = new Insets(30, 30, 30, 30);
		JPanel widgetUsuarios = new JPanel();
		widgetUsuarios.setLayout(new BoxLayout(widgetUsuarios, BoxLayout.Y_AXIS));
		widgetUsuarios.setBackground(new Color(255, 182, 39));
		panelDashboard.add(widgetUsuarios, reglas);
		
		JLabel tituloUsuarios = new JLabel("Numero de usuarios: ");
		tituloUsuarios.setFont(fuenteSubtitulo.deriveFont(16f));
		tituloUsuarios.setForeground(Color.WHITE);
		widgetUsuarios.add(tituloUsuarios);
		
		widgetUsuarios.add(Box.createGlue());
		
		JLabel numeroUsuarios = new JLabel("2");
		numeroUsuarios.setFont(fuenteTitulo.deriveFont(64f));
		numeroUsuarios.setForeground(Color.WHITE);
		widgetUsuarios.add(numeroUsuarios);
		
		reglas.gridx = 1;
		reglas.gridy = 0;
		reglas.gridheight = 1;
		reglas.gridwidth =1;
		reglas.anchor = GridBagConstraints.CENTER;
		reglas.fill = GridBagConstraints.HORIZONTAL;
		JPanel widgetProductos = new JPanel();
		widgetProductos.setLayout(new BoxLayout(widgetProductos, BoxLayout.Y_AXIS));
		widgetProductos.setBackground(new Color(8, 61, 119));
		panelDashboard.add(widgetProductos, reglas);
		
		JLabel tituloProductos = new JLabel("Numero de productos: ");
		tituloProductos.setFont(fuenteSubtitulo.deriveFont(16f));
		tituloProductos.setForeground(Color.WHITE);
		widgetProductos.add(tituloProductos);
		
		widgetProductos.add(Box.createGlue());
		
		JLabel numeroProductos = new JLabel("1,100");
		numeroProductos.setFont(fuenteTitulo.deriveFont(64f));
		numeroProductos.setForeground(Color.WHITE);
		widgetProductos.add(numeroProductos);
		
		reglas.gridx = 2;
		reglas.gridy = 0;
		reglas.gridheight = 1;
		reglas.gridwidth = 1;
		reglas.anchor = GridBagConstraints.NORTHEAST;
		reglas.fill = GridBagConstraints.BOTH;
		JPanel widgetStock = new JPanel();
		widgetStock.setLayout(new BoxLayout(widgetStock, BoxLayout.Y_AXIS));
		widgetStock.setBackground(new Color(87, 117, 144));
		panelDashboard.add(widgetStock, reglas);
		
		JLabel tituloStock = new JLabel("Productos sin stock: ");
		tituloStock.setFont(fuenteSubtitulo.deriveFont(16f));
		tituloStock.setForeground(Color.WHITE);
		widgetStock.add(tituloStock);
		
		widgetStock.add(Box.createGlue());
		
		JLabel numeroStock = new JLabel("50");
		numeroStock.setFont(fuenteTitulo.deriveFont(64f));
		numeroStock.setForeground(Color.WHITE);
		widgetStock.add(numeroStock);
		
		reglas.gridx = 0;
		reglas.gridy = 1;
		reglas.gridheight = 1;
		reglas.gridwidth = 2;
		reglas.anchor = GridBagConstraints.EAST;
		DefaultCategoryDataset datos1 = new DefaultCategoryDataset();
		datos1.addValue(100, "Dia", "02/05/2025");
		datos1.addValue(190, "Dia", "03/05/2025");
		datos1.addValue(200, "Dia", "04/05/2025");
		datos1.addValue(300, "Dia", "05/05/2025");
		datos1.addValue(130, "Dia", "06/05/2025");
		datos1.addValue(250, "Dia", "07/05/2025");
		datos1.addValue(170, "Dia", "08/05/2025");
		JFreeChart valorInventario = ChartFactory.createBarChart("Entrada de productos", "Dia", "Numero de productos", datos1);
		ChartPanel graficoBarras = new ChartPanel(valorInventario);
		graficoBarras.setMinimumSize(new Dimension(600, 300));
		panelDashboard.add(graficoBarras, reglas);
		
		reglas.gridx = 2;
		reglas.gridy = 1;
		reglas.gridheight = 1;
		reglas.gridwidth = 1;
		reglas.anchor = GridBagConstraints.WEST;

		DefaultPieDataset<String> datos = new DefaultPieDataset<String>();
		datos.setValue("Celulares", 400d);
		datos.setValue("Laptops", 200d);
		datos.setValue("Televisiones", 150d);
		datos.setValue("Impresoras", 50d);
		datos.setValue("Teclados", 150d);
		datos.setValue("CPUs", 50d);
		datos.setValue("Tarjetas Graficas", 50d);
		datos.setValue("Tabletas", 100d);
		JFreeChart grafica = ChartFactory.createPieChart("Composicion de inventario", datos);
		grafica.removeLegend();
		ChartPanel usuarios = new ChartPanel(grafica);
		usuarios.setMinimumSize(new Dimension(300, 300));
		panelDashboard.add(usuarios, reglas);
		return panelDashboard;
		
	}
	
	public void mostrar(JPanel panel) {
		if(panelDerecha.getComponentCount() > 1) {			
			panelDerecha.remove(1);
		}
		panelDerecha.add(panel, BorderLayout.CENTER);
		this.pack();
		this.setMinimumSize(getMinimumSize());
		this.setPreferredSize(getPreferredSize());
		this.setLocationRelativeTo(null);
		revalidate();
		repaint();
	}
	
	private MouseAdapter clickableLabel() {
		return new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		};
	}
	
	private Icon getImageIcon(String filename, int height, int width) {
		Image image  = new ImageIcon(this.getClass().getResource("/images/" + filename))
				.getImage().getScaledInstance(height, width, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
}
