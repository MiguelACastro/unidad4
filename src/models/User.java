package models;

import java.util.Date;

public class User {
	private int id;
	private String name;
	private String email;
	private String role;
	private String phone;
	private Date createdAt;
	private Date updatedAt;
	
	public User(int id, String name, String email, String role, String phone, Date createdAt, Date updateAt) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.role = role;
		this.phone = phone;
		this.createdAt = createdAt;
		this.updatedAt = updateAt;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getRole() {
		return role;
	}
	public String getPhone() {
		return phone;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdateAt() {
		return updatedAt;
	}	
}
