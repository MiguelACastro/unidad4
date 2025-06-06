package controllers;


import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import models.ProductModel;
import models.Producto;
import views.ProductView;

public class ProductController {
	
	private ProductModel modelo;
	private ProductView vista;
	
	public ProductController(ProductModel modelo, ProductView vista) {
		this.modelo = modelo;
		this.vista = vista;
	}
	
	public JPanel productos() {
		return vista.productos();
	}
	
	public void getProductos() {
		vista.setProductos(modelo.getProductos());
	}
	
	public void addProducto(Producto producto) {
		modelo.addProducto(producto);
	}

	public void eliminarProducto(int id) {
		modelo.eliminarProducto(id);
	}
	
}
