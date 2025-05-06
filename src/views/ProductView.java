package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.ProductController;
import models.ProductModel;

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
		
		return panelProducto;
	}

	public void setProductos(DefaultTableModel productos) {
		tablaProductos.setModel(productos);
	}
}
