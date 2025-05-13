package models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLClientInfoException;
import java.util.Iterator;

import javax.management.ObjectName;
import javax.swing.table.DefaultTableModel;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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

	public void addProducto(Producto producto) {
		
		try{
			File archivo = new File("src/files/products.json");
			ObjectMapper objectMapper = new ObjectMapper();
			
			JsonNode root = objectMapper.readTree(archivo);
			ArrayNode productosNode = (ArrayNode) root.get("productos");

			ObjectNode nuevoProducto = objectMapper.createObjectNode();
			nuevoProducto.put("id", producto.getId());
			nuevoProducto.put("nombre", producto.getNombre());
			nuevoProducto.put("precio", producto.getPrecio());
			nuevoProducto.put("stock", producto.getStock());
			
			productosNode.add(nuevoProducto);
			
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(archivo, root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void eliminarProducto(int id) {
		try{
			File archivo = new File("src/files/products.json");
			ObjectMapper objectMapper = new ObjectMapper();
			
			JsonNode root = objectMapper.readTree(archivo);
			ArrayNode productosNode = (ArrayNode) root.get("productos");

			Iterator<JsonNode> productos = productosNode.elements();
			while(productos.hasNext()) {
				JsonNode producto = productos.next();
				if(producto.get("id").asInt() == id) {
					productos.remove();
				}
			}
			
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(archivo, root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
