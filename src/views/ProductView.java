package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.ProductController;
import models.ProductModel;
import models.Producto;

public class ProductView {
	
	private JPanel panelProducto;
	private JTable tablaProductos;
	private ProductController controller = new ProductController(new ProductModel(), this);
	
	public ProductView() {
		panelProducto = new JPanel();
		tablaProductos = new JTable();
	}
	
	public JPanel productos() {
		panelProducto.setLayout(new BorderLayout());
		panelProducto.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		controller.getProductos();
		
		JScrollPane pane = new JScrollPane(tablaProductos);
		panelProducto.add(pane, BorderLayout.CENTER);
		
		JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelBoton.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0));
		panelProducto.add(panelBoton, BorderLayout.NORTH);
		
		JButton botonActualizar = new JButton("Actualizar");
		botonActualizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.getProductos();
			}
		});
		panelBoton.add(botonActualizar);
		
		JButton botonAdd = new JButton("Añadir");
		botonAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addProduct();
			}
		});
		panelBoton.add(botonAdd);
		
		return panelProducto;
	}
	
	public void addProduct() {
		JPanel panelAdd = new JPanel();
		panelAdd.setLayout(new BoxLayout(panelAdd, BoxLayout.Y_AXIS));
		
		JLabel idLabel = new JLabel("ID");
		panelAdd.add(idLabel);
		
		JTextField idField = new JTextField();
		panelAdd.add(idField);
		
		JLabel nombreLabel = new JLabel("Nombre");
		panelAdd.add(nombreLabel);
		
		JTextField nombreField = new JTextField();
		panelAdd.add(nombreField);
		
		JLabel precioLabel = new JLabel("Precio");
		panelAdd.add(precioLabel);
		
		JTextField precioField = new JTextField();
		panelAdd.add(precioField);
		
		JLabel stockLabel = new JLabel("Stock");
		panelAdd.add(stockLabel);
		
		JTextField stockField = new JTextField();
		panelAdd.add(stockField);
		
		String[] opciones = {"Cancelar", "Agregar"};
		
		int agregar = JOptionPane.showOptionDialog(panelProducto.getTopLevelAncestor(), panelAdd, "Añadir producto"
				, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
		
		if(agregar == 1) {
			int id = Integer.valueOf(idField.getText());
			String nombre = nombreField.getText();
			double precio = Double.valueOf(precioField.getText());
			int stock = Integer.valueOf(stockField.getText());
			
			
			System.out.println("%d, %s, %f, %d"
					.formatted(id, nombre, precio, stock));
			
			controller.addProducto(new Producto(id, nombre, precio, stock));
		}
	}
	
	public void setProductos(DefaultTableModel productos) {
		tablaProductos.setModel(productos);
	}
}
