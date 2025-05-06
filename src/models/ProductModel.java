package models;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLClientInfoException;

import javax.swing.table.DefaultTableModel;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductModel {
	
	public DefaultTableModel getProductos() {
		DefaultTableModel productosModel = new DefaultTableModel();
		productosModel.addColumn("ID");
		productosModel.addColumn("Nombre");
		productosModel.addColumn("Precio");
		productosModel.addColumn("Stock");
		
		try{
			InputStream jsonStream = this.getClass().getResourceAsStream("/files/products.json");
			ObjectMapper objectMapper = new ObjectMapper();
			
			JsonNode root = objectMapper.readTree(jsonStream);
			JsonNode productosNode = root.get("productos");
			
			objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
			Producto[] productos = objectMapper.convertValue(productosNode, Producto[].class);
			
			jsonStream.close();
		
			for(Producto producto : productos) {				
				String[] row = {
						String.valueOf(producto.getId()),
						producto.getNombre(),
						String.valueOf(producto.getPrecio()),
						String.valueOf(producto.getStock())
				};
				productosModel.addRow(row);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return productosModel;
	}
}
