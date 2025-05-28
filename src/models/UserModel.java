package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UserModel {
	public ArrayList<User> get() {
		ArrayList<User> users = new ArrayList<User>();
		
		String query = "SELECT * FROM users";
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "G2Uaw6xzdO");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				int userId = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String role = rs.getString(4);
				String phone = rs.getString(5);
				Date createdAt = rs.getDate(6);
				Date updatedAt = rs.getDate(7);
				
				users.add(new User(userId, name, email, role, phone, createdAt, updatedAt));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {}
		}
		return users;
	}
	
	public boolean addUser(User usuario)
	{
		String query = "INSERT INTO users (name, email, `role`, phone, create_at) VALUES (?, ?, ?, ?, curdate())";
		
		try (
				Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "G2Uaw6xzdO");
				PreparedStatement stmt = conn.prepareStatement(query);
			){
			stmt.setString(1, usuario.getName());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getRole());
			stmt.setString(4, usuario.getPhone());
			int rs = stmt.executeUpdate();
			 
			if(rs > 0) 
				return true; 
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean updateUser(User usuario)
	{
		String query = "UPDATE users SET name=?, email=?, `role`=?, phone=?, update_at=curdate() WHERE id=?";
		
		try (
				Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "G2Uaw6xzdO");
				PreparedStatement stmt = conn.prepareStatement(query);
			){
			stmt.setString(1, usuario.getName());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getRole());
			stmt.setString(4, usuario.getPhone());
			stmt.setInt(5, usuario.getId());
			int rs = stmt.executeUpdate();
			 
			if(rs > 0) 
				return true; 
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean deleteUser(int id) {
		String query = "DELETE FROM users WHERE id=?";
		
		try (
				Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "G2Uaw6xzdO");
				PreparedStatement stmt = conn.prepareStatement(query);
			){
			stmt.setInt(1, id);
			int rs = stmt.executeUpdate();
			 
			if(rs > 0) 
				return true; 
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
